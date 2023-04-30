package main;

import objects.OBJ_Key;
import java.awt.*;
import java.awt.image.BufferedImage;
import objects.*;

public class UI {

    BufferedImage keyImage; // we keep this to continue displaying items

    GamePanel gp;
    Graphics2D g2;
    Font arial_40, arial_80B;
    BufferedImage heart_full, heart_half, heart_blank;
    public boolean isMessageOn = false;
    public String message = "";
    private int messageCounter = 0;
    public boolean isGameFinished = false;
    public String currentDialogue = "";

    public UI(GamePanel gp) {
        this.gp = gp;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80B = new Font("Arial", Font.BOLD, 80);
        OBJ_Key key = new OBJ_Key(gp);
        keyImage = key.image;

        // CREATE HUD OBJECT
        SuperObject heart = new OBJ_Heart(gp);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_blank = heart.image3;

    }

    public void showMessage(String message) {
        this.message = message;
        isMessageOn = true;

    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;

        // PLAY STATE
        if (gp.gameState == gp.playState) {
            drawPlayerLife();

            if (isGameFinished) {
                g2.setFont(arial_80B);
                g2.setColor(Color.yellow);

                String text = "You won!";

                int x = getXForCenteredText(text);
                int y = gp.screenHeight / 2 - gp.tileSize * 3;
                g2.drawString(text, x, y);
                gp.gameThread = null;


            } else {
                g2.setFont(arial_40);
                g2.setColor(Color.white);

                int x = gp.tileSize / 2;
                int y = gp.tileSize * 3 / 2;
                g2.drawImage(keyImage, x, y, gp.tileSize, gp.tileSize, null);
                g2.drawString("x" + gp.player.numOfKeys, x + gp.tileSize, y + gp.tileSize);
                g2.drawString("row: " + (gp.player.worldY / gp.tileSize) + ", col: " + (gp.player.worldX / gp.tileSize), x, y + gp.tileSize * 2);

                // MESSAGE
                if (isMessageOn) {
                    g2.setFont(g2.getFont().deriveFont(30F));
                    g2.drawString(message, gp.tileSize / 2, gp.tileSize * 5);
                    messageCounter++;

                    if (messageCounter > 120) {
                        messageCounter = 0;
                        isMessageOn = false;
                    }
                }
            }
        }

        // PAUSE STATE
        if (gp.gameState == gp.pauseState) {
            drawPlayerLife();
            drawPauseScreen();
        }
        // DIALOGUE STATE
        if (gp.gameState == gp.dialogueState) {
            drawPlayerLife();
            drawDialogueScreen();
        }

    }

    private void drawPlayerLife() {

        // gp.player.life = 1; // this is just to test whether life x ♡ are correctly displayed

        int x = gp.tileSize / 2;
        int y = gp.tileSize / 2;
        int i = 0;

        // DRAW MAX LIFE
        while (i < gp.player.maxLife / 2) {
            g2.drawImage(heart_blank, x, y, null);
            i++;
            x += gp.tileSize;
        }

        // RESET
        x = gp.tileSize / 2;
        y = gp.tileSize / 2;
        i = 0;

        // DRAW CURRENT LIFE
        while (i < gp.player.life) {
            g2.drawImage(heart_half, x, y, null);
            i++;
            if (i < gp.player.life) {
                g2.drawImage(heart_full, x, y, null);
            }
            i++;
            x += gp.tileSize;
        }
    }

    private void drawDialogueScreen() {

        // WINDOW
        int x = gp.tileSize * 2;
        int y = gp.tileSize / 2;
        int width = gp.screenWidth - (gp.tileSize * 4);
        int height = gp.tileSize * 4;
        drawSubWindow(x, y, width, height);

        // TEXT
        x += gp.tileSize;
        y += gp.tileSize;
        g2.setFont(new Font("Purisa Bold", Font.PLAIN, 32));
        for (String line : currentDialogue.split("\n")) {
            g2.drawString(line, x, y);
            y += 40;
        }
    }

    public void drawSubWindow(int x, int y, int width, int height) {

        Color c = new Color(0, 0, 0, 210); // create an RGB color (int red, int green, int blue)
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
    }

    public void drawPauseScreen() {
        g2.setFont(arial_80B);
        g2.setColor(Color.yellow);
        String text = "PAUSED";

        int x = getXForCenteredText(text);
        int y = gp.screenHeight / 2 - gp.tileSize * 3;
        g2.drawString(text, x, y);
    }

    public int getXForCenteredText(String text) {
        int textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth / 2 - textLength / 2;
        return x;
    }
}
