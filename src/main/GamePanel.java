package src.main;
import javax.swing.JPanel;

import src.entity.Player;
import src.tile.TileManager;

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

    //fps
    int FPS = 60;

    TileManager tileM = new TileManager(this);
    KeyHandler  keyH = new KeyHandler();
    Thread gameThread;
    Player player = new Player(this, keyH);

    public GamePanel(){

        this.setPreferredSize( new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener( keyH);
        //game panel can be focused to recieve input
        this.setFocusable(true);

    }


    public void startGameThread(){

        gameThread = new Thread(this);
        gameThread.start();

    }

    // @Override
    // public void run() {
        
    //     //0.0166
    //     double drawInterval = 1000000000/FPS; 
    //     double nextDrawTime = System.nanoTime() + drawInterval;
    //     while(gameThread !=  null){
            
    //         //update the information of the game such as character position 
    //         update();
    //         //draw the screen with the updated information
    //         repaint();

    //         try {
    //             double remainingTime = nextDrawTime - System.nanoTime();
    //             remainingTime = remainingTime/1000000;

    //             if(remainingTime < 0){
    //                 remainingTime = 0;
    //             }

    //             Thread.sleep((long) remainingTime);
    //             nextDrawTime += drawInterval;

    //         } catch (InterruptedException e) {
    //             e.printStackTrace();
    //         }
    //     }
        
    // }

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

        tileM.draw(g2);
        player.draw(g2);
        g2.dispose();

    }
    
}
