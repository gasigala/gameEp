import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;

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
        // TODO Auto-generated method stub
        
    }

    
}
