package main;

import objects.OBJ_Key;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {

    GamePanel gp;
    Font arial_40, arial_80B;
    BufferedImage keyImage;
    public boolean isMessageOn = false;

    public boolean isGameFinished = false;
    public String message ="";
    private  int messageCounter = 0;

    public UI(GamePanel gp) {
        this.gp =gp;
        arial_40 = new  Font("Arial", Font.PLAIN, 40);
        arial_80B = new  Font("Arial", Font.BOLD, 80);
        OBJ_Key key = new OBJ_Key(gp);
        keyImage = key.image;
    }

    public  void  showMessage(String message){
        this.message = message;
        isMessageOn = true;

    }

    public  void draw(Graphics2D g2){


        if(isGameFinished) {
            g2.setFont(arial_80B);
            g2.setColor(Color.yellow);

            String text = "You won!";
            int textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            int x = gp.screenWidth/2 -textLength/2;
            int y = gp.screenHeight/2 - gp.tileSize*3;
            g2.drawString(text, x, y);
            gp.gameThread = null;


        }
        else {
            g2.setFont(arial_40);
            g2.setColor(Color.white);


        g2.drawImage(keyImage,gp.tileSize/2, gp.tileSize/2,gp.tileSize,gp.tileSize,null);
        g2.drawString("x" + gp.player.numOfKeys, 74 , 65);

    // MESSAGE
        if(isMessageOn) {
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
}
