package entity;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Link64 extends Entity {

    public Link64(GamePanel gp) {

        super(gp);
        direction = "left";
        speed = 8;
        getImage();
        setDialogue();

    }

    public void getImage() {

        left1   = setUp("/player/link64_left_1");
        left2   = setUp("/player/link64_left_2");
        left3  = setUp("/player/link64_left_3");
        left4  = setUp("/player/link64_left_4");
        right1     = setUp("/player/link64_right_1");
        right2     = setUp("/player/link64_right_2");
        right3   = setUp("/player/link64_right_3");
        right4   = setUp("/player/link64_right_4");

    }

    public void setDialogue() {

        dialogues[0] = "Ich spring von Level zu Level zu Level...";
        dialogues[1] = "...bis der Endboss kommt!";

    }

    @Override
    // extremely simple but different "AI":
    // switch direction whenever you run into sth
    public void setAction() {

        if (!collisionOn) {
            if (direction.equals("left")) direction = "left";
            else if (direction.equals("right")) direction = "right";
        }
        if (collisionOn) {
            if (direction.equals("left")) direction = "right";
            else if (direction.equals("right")) direction = "left";
        }
    }

    @Override
    public void update() {
        setAction();
        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkPlayer(this);

        // IF COLLISION IS FALSE, PLAYER CAN MOVE
        if (!collisionOn) {
            switch (direction) {
                case "left" -> worldX -= speed;
                case "right" -> worldX += speed;
            }
        }

        spriteCounter++;
        if (spriteCounter > 5) {
            if (spriteNum == 1) {
                spriteNum = 2;
            }
            else if (spriteNum == 2) {
                spriteNum = 3;
            }
            else if (spriteNum == 3) {
                spriteNum = 4;
            }
            else if (spriteNum == 4) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }

    @Override
    public void draw(Graphics2D g2, GamePanel gp) {

        BufferedImage image = null;
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

            switch (direction) {
                case "left" -> {
                    if (spriteNum == 1) image = left1;
                    else if (spriteNum == 2) image = left2;
                    else if (spriteNum == 3) image = left3;
                    else if (spriteNum == 4) image = left4;
                }
                case "right" -> {
                    if (spriteNum == 1) image = right1;
                    else if (spriteNum == 2) image = right2;
                    else if (spriteNum == 3) image = right3;
                    else if (spriteNum == 4) image = right4;
                }
            }
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
    }

    @Override
    // do character-specific stuff, like saying sth else if the player
    // is in possession of a special artefact
    public void speak() {

        super.speak();

    }
}
