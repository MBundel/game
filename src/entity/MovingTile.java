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
//        gp.cChecker.checkObject(this, false);
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

            BufferedImage image1 = setUp("/tiles/water10");
            BufferedImage image2 = setUp("/tiles/water11");
            BufferedImage image3 = setUp("/tiles/water12");
            BufferedImage image4 = setUp("/tiles/water13");

            // this should be a loop drawing islands of varying size
            g2.drawImage(image1, screenX, screenY, gp.tileSize, gp.tileSize, null);
            g2.drawImage(image2, screenX + gp.tileSize, screenY, gp.tileSize, gp.tileSize, null);
            g2.drawImage(image3, screenX, screenY + gp.tileSize, gp.tileSize, gp.tileSize, null);
            g2.drawImage(image4, screenX + gp.tileSize, screenY + gp.tileSize, gp.tileSize, gp.tileSize, null);
        }
    }
}