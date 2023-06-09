package main;
import java.awt.Color;
import java.awt.*;

import javax.swing.JPanel;
import javax.swing.plaf.DimensionUIResource;

import entity.Entity;
import entity.Player;
import objects.SuperObject;
import tiles.MapBuilder;
import tiles.TileManager;

public class GamePanel extends JPanel implements Runnable {

    final int originalTileSize = 64;
    final int scale = 1;

    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    // WORLD SETTINGS
    public final int maxWorldCol    = 100;
    public final int maxWorldRow    = 100;
    // build new map
    public MapBuilder map = new MapBuilder();

    //FPS
    int FPS = 60;

    //SYSTEM
    TileManager tileM = new TileManager(this);
    public KeyHandler keyH = new KeyHandler(this);
    Sound music = new Sound();
    Sound sound = new Sound();
    Thread gameThread;
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    public EventHandler eHandler = new EventHandler(this);
    Thread getGameThread;

    //ENTITY AND OBJECT
    public Player player = new Player(this, keyH); 
    public SuperObject[] obj = new SuperObject[10];
    public Entity[] npc = new Entity[10];

    // GAME STATE
    public int gameState;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int titleState = 0;

    public GamePanel() {

        this.setPreferredSize(new DimensionUIResource(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

    }

    public void setupGame(){
        aSetter.setObjects();
        aSetter.setNPC();
        gameState = playState;
        if(gameState != titleState) {
            playMusic(0);
        }
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

        while(gameThread != null){
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime)/ drawInterval;
            lastTime = currentTime;

            if(delta >= 1){
            update();
            repaint();
            delta--;
            }
        }
    }

    public void update(){
        if (gameState == playState){
            player.update();
            for (int i = 0; i < npc.length; i++) {
                if (npc[i] != null) {
                    npc[i].update();
                }
            }
        }
        if (gameState == pauseState){

        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
    
        Graphics2D g2 = (Graphics2D)g;

        //TITLE SCREEN
        if(gameState == titleState){
            // do titleState stuff
        }

        //OTHERS
        else {
            //OTHERS
            // TILE
            tileM.draw(g2);
            //OBJECT
            for (int i = 0; i < obj.length; i++) {
                if (obj[i] != null) {
                    obj[i].draw(g2, this);
                }
            }

            // NPC
            for (int i = 0; i < npc.length; i++) {
                if (npc[i] != null) {
                    npc[i].draw(g2, this);
                }
            }

            // PLAYER
            player.draw(g2);

            //UI
            ui.draw(g2);


            g2.dispose();
        }

    }
    public  void playMusic(int i){
        music.setFile(i);
        music.play();
        music.loop();
    }

    public  void stopMusic(){
        music.stop();
    }
    public  void  playSE(int i){ //Soundeffect
       sound.setFile(i);
       sound.play();
    }
}
