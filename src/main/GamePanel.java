package main;
import java.awt.Color;
import java.awt.*;

import javax.swing.JPanel;
import javax.swing.plaf.DimensionUIResource;

import entityFolder.Player;
import objects.SuperObject;
import tiles.TileManager;

public class GamePanel extends JPanel implements Runnable {
    
    final int originalTileSize = 16; // 16 x 16
    final int scale = 3 ; 

    public final int tileSize = originalTileSize * scale; // 48 x 48
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels


    // WORLD SETTINGS
    public final int maxWorldCol    = 50;
    public final int maxWorldRow    = 50;
    public final int maxWorldWidth  = tileSize * maxWorldCol;
    public final int maxWorldHeight = tileSize * maxWorldRow;
    

    //FPS
    int FPS = 60;

    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Thread gameThread; 
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public Player player = new Player(this, keyH); 
    public SuperObject obj[] = new SuperObject[10];



    public GamePanel(){

        this.setPreferredSize(new DimensionUIResource(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame(){
        aSetter.setObjects();
    }

public void startGameThread(){

    gameThread = new Thread(this);
    gameThread.start();
}

    @Override
    public void run(){
        double drawInterval = 1_000_000_000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while(gameThread != null){
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime)/ drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if(delta >= 1){
            update();
            repaint();
            delta--;
            drawCount++;
            }

            if(timer >= 1_000_000_000){
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update(){
        player.update();
       
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
    
        Graphics2D g2 = (Graphics2D)g;

        // TILE
        tileM.draw(g2);
        //OBJECT
        for(int i = 0; i <obj.length; i++){
            if(obj[i] != null){
                obj[i].draw(g2, this);
            }
        }


        //PLAYER
        player.draw(g2);

        g2.dispose();

}
}