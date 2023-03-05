import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;


public class GamePanel extends JPanel implements Runnable{
    final int originalTileSize = 16;
    //16 x 16 tile size 
    final int scale = 3;
    final int tileSize = originalTileSize * scale;

    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol;
    //768 px
    final int screenHeight = tileSize * maxScreenRow; 
    //576 px

    Thread gameThread;

    public GamePanel(){
        this.setPreferredSize( new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
    }


    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        while(gameThread !=  null){
            //update the information of the game such as character position 
            update();
            //draw the screen with the updated information
            repaint();
        }
        
    }

    public void update(){
        

    } 

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        g2.setColor(Color.white);
        g2.fillRect(100, 100, tileSize, tileSize);
        g2.dispose();

    }

    
}
