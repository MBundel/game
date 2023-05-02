package entity;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MovingTile extends Entity {

    boolean movesUpAndDown;

    public MovingTile(GamePanel gp, boolean movesUpAndDown, int speed) {

        super(gp);
        this.movesUpAndDown = movesUpAndDown;
        if (movesUpAndDown) direction = "down";
        else direction = "left";
        this.speed = speed;

    }

    @Override
    public void setAction() {

        if (movesUpAndDown) {
            if (!collisionOn) {
                if (direction.equals("down")) direction = "down";
                else if (direction.equals("up")) direction = "up";
            }
            if (collisionOn) {
                if (direction.equals("down")) direction = "up";
                else if (direction.equals("up")) direction = "down";
            }
        }
        else {
            if (!collisionOn) {
                if (direction.equals("left")) direction = "left";
                else if (direction.equals("right")) direction = "right";
            }
            if (collisionOn) {
                if (direction.equals("left")) direction = "right";
                else if (direction.equals("right")) direction = "left";
            }
        }
    }

    @Override
    public void update() {
        setAction();
        collisionOn = false;
        gp.cChecker.checkTileOnTile(this);
//        gp.cChecker.checkEntity(this, gp.npc);
//        gp.cChecker.checkPlayer(this);

        // IF COLLISION IS OFF, TILE CAN MOVE
        if (!collisionOn) {
            switch (direction) {
                case "left" -> worldX -= speed;
                case "right" -> worldX += speed;
                case "up" -> worldY -= speed;
                case "down" -> worldY += speed;
            }
        }
    }

    @Override
    public void draw(Graphics2D g2, GamePanel gp) {

        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

            BufferedImage image1 = setUp("/NPC/island/island_upperLeft");
            BufferedImage image2 = setUp("/NPC/island/island_top");
            BufferedImage image3 = setUp("/NPC/island/island_upperRight");
            BufferedImage image4 = setUp("/NPC/island/island_left");
            BufferedImage image5 = setUp("/NPC/island/island");
            BufferedImage image6 = setUp("/NPC/island/island_right");
            BufferedImage image7 = setUp("/NPC/island/island_lowerLeft");
            BufferedImage image8 = setUp("/NPC/island/island_bottom");
            BufferedImage image9 = setUp("/NPC/island/island_lowerRight");

            // this should be a loop drawing islands of varying size
            g2.drawImage(image1, screenX, screenY, gp.tileSize, gp.tileSize, null);
            g2.drawImage(image2, screenX + gp.tileSize, screenY, gp.tileSize, gp.tileSize, null);
            g2.drawImage(image3, screenX + gp.tileSize * 2, screenY, gp.tileSize, gp.tileSize, null);
            g2.drawImage(image4, screenX, screenY + gp.tileSize, gp.tileSize, gp.tileSize, null);
            g2.drawImage(image5, screenX + gp.tileSize, screenY + gp.tileSize, gp.tileSize, gp.tileSize, null);
            g2.drawImage(image6, screenX + gp.tileSize * 2, screenY + gp.tileSize, gp.tileSize, gp.tileSize, null);
            g2.drawImage(image7, screenX, screenY + gp.tileSize * 2, gp.tileSize, gp.tileSize, null);
            g2.drawImage(image8, screenX + gp.tileSize, screenY + gp.tileSize * 2, gp.tileSize, gp.tileSize, null);
            g2.drawImage(image9, screenX + gp.tileSize * 2, screenY + gp.tileSize * 2, gp.tileSize, gp.tileSize, null);
        }
    }
}