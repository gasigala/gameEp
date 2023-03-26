package src.main;
import javax.swing.JPanel;

import src.entity.Player;
import src.tile.TileManager;
import src.object.SuperObject;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;


public class GamePanel extends JPanel implements Runnable{
    final int originalTileSize = 16;
    //16 x 16 tile size 
    final int scale = 3;
    public final int tileSize = originalTileSize * scale;

    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    //768 px
    public final int screenHeight = tileSize * maxScreenRow; 
    //576 px

    //world settings
    public final int maxWorldCol = 50;
    public final int maxworldRow = 50;

    //fps
    int FPS = 60;

    //System
    TileManager tileM = new TileManager(this);
    KeyHandler  keyH = new KeyHandler();
    Sound music = new Sound();
    Sound se = new Sound();
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    Thread gameThread;


    //Entitys and objects
    public Player player = new Player(this, keyH);
    public SuperObject obj[] = new SuperObject[10];

    public GamePanel(){

        this.setPreferredSize( new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener( keyH);
        //game panel can be focused to recieve input
        this.setFocusable(true);

    }

    public void setupGame(){
        aSetter.setObject();
        playMusic(0);
    }

    public void startGameThread(){

        gameThread = new Thread(this);
        gameThread.start();

    }

    @Override
    public void run(){
        double drawInterval = 1000000000/FPS; 
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer =0;
        int drawCount =0;

        while(gameThread != null){
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += currentTime - lastTime;
            lastTime = currentTime;

            if(delta >= 1){
                update();
                repaint();
                delta--;
                drawCount ++;

            }
            if(timer >= 1000000000){
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
        //drawing tiles etc

        tileM.draw(g2);
        for(int i =0; i < obj.length; i++){
            if(obj[i] != null){
                obj[i].draw(g2, this);
            }
        }
        player.draw(g2);
        //UI
        ui.draw(g2);
        g2.dispose();

    }

    public void playMusic(int i){
        music.setFile(i);
        music.play();
        music.loop();
    }
    public void stopMusic(){
        music.stop();
    }

    public void playSE(int i){
        se.setFile(i);
        se.play();
    }
}
