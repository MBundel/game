package entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;

public class Player extends Entity {

    KeyHandler keyH;
    public final int screenX;
    public final int screenY;
    public int numOfKeys = 4;

    public Player(GamePanel gp, KeyHandler keyH) {

        super(gp);
        this.keyH = keyH;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 15; // 72
        speed = 4;
        direction = "down";

        // PLAYER STATUS
        maxLife = 6;
        life = maxLife;
    }

    public void getPlayerImage() {

        up1     = setUp("/player/link_up_1");
        up2     = setUp("/player/link_up_2");
        down1   = setUp("/player/link_down_1");
        down2   = setUp("/player/link_down_2");
        left1   = setUp("/player/link_left_1");
        left2   = setUp("/player/link_left_2");
        right1  = setUp("/player/link_right_1");
        right2  = setUp("/player/link_right_2");

    }

    public BufferedImage setUp(String imageName) {
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(getClass().getResourceAsStream(imageName + ".png"));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }

    public void update() {

        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {

            if (keyH.upPressed) {
                direction = "up";
            }
            if (keyH.downPressed) {
                direction = "down";
            }
            if (keyH.leftPressed) {
                direction = "left";
            }
            if (keyH.rightPressed) {
                direction = "right";
            }

            // CHECK TILE COLLISION
            collisionOn = false;
            gp.cChecker.checkTile(this);

            // CHECK OBJECT COLLISION
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            // CHECK NPC COLLISION
            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);
            
            // IF COLLISION IS FALSE, PLAYER CAN MOVE
            if (!collisionOn) {
                switch(direction) {
                    case "up" -> worldY -= speed;
                    case "down" -> worldY += speed;
                    case "left" -> worldX -= speed;
                    case "right" -> worldX += speed;
                }
            }

            spriteCounter++;
            if (spriteCounter > 10) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    private void interactNPC(int i) {

        if (i != 999) {

            if (gp.keyH.enterPressed) {
                gp.gameState = gp.dialogueState;
                gp.npc[i].speak();
            }
        }
        gp.keyH.enterPressed = false;
    }

    public void pickUpObject(int i) {
        if (i != 999) {

            String objectName = gp.obj[i].name;

            switch (objectName) {
                case "Key":
                    numOfKeys++;
                    gp.obj[i] = null;
                    gp.playSE(1);
                    gp.ui.showMessage("Key picked up");
                    break;
                case "Door":
                    if (numOfKeys > 0) {
                        gp.playSE(3);
                        gp.obj[i] = null;
                        numOfKeys--;
                    }
                    break;
                case "Boots":
                    gp.playSE(2);
                    speed += 3;
                    gp.obj[i] = null;
                    break;
                case "Chest":
                    gp.stopMusic();
                    gp.playSE(4);
                    gp.ui.isGameFinished = true;
                    break;
            }
        }
    }

    public void draw(Graphics2D g2) {
        // g2.setColor(Color.WHITE);
        // g2.fillRect(x, y, gp.tileSize, gp.tileSize);

        BufferedImage image = null;

        switch (direction) {
            case "up" -> {
                if (spriteNum == 1) image = up1;
                else if (spriteNum == 2) image = up2;
            }
            case "down" -> {
                if (spriteNum == 1) image = down1;
                else if (spriteNum == 2) image = down2;
            }
            case "left" -> {
                if (spriteNum == 1) image = left1;
                else if (spriteNum == 2) image = left2;
            }
            case "right" -> {
                if (spriteNum == 1) image = right1;
                else if (spriteNum == 2) image = right2;
            }
        }
        g2.drawImage(image, screenX, screenY, null);
    }
}
