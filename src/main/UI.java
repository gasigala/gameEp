package src.main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;


public class UI {
    GamePanel gp;
    Graphics2D g2;
    Font maruMonica, purisaB, pixelLanky;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public String currentDialouge ="";
    public int commandNum = 0;

    public UI(GamePanel gp){
        this.gp = gp;
        try {
            InputStream is = getClass().getResourceAsStream("/res/fonts/x12y16pxMaruMonica.ttf");
            maruMonica = Font.createFont(Font.TRUETYPE_FONT, is);
            is = getClass().getResourceAsStream("/res/fonts/Purisa Bold.ttf");
            purisaB = Font.createFont(Font.TRUETYPE_FONT, is);
            is = getClass().getResourceAsStream("/res/fonts/pixelanky CE DEMO Regular.ttf");
            pixelLanky = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }

    }


    public void showMessage(String text){
         message = text;
         messageOn = true;
    }

    public void draw(Graphics2D g2){
       this.g2 = g2;
       g2.setFont(pixelLanky);
       g2.setColor(Color.white);
       if(gp.gameState == gp.titleState){
            drawTitleScreen();
       }
        if(gp.gameState == gp.playState){
            //do playstate stuff later
        }
        if(gp.gameState == gp.pauseState){
            drawPauseScreen();
        }
        if(gp.gameState == gp.dialougeState){
            drawDialougeScreen();
        }
    }

    public void drawDialougeScreen(){
        //window (subwindow)
        int x = gp.tileSize*2;
        int y = gp.tileSize/2;
        int width = gp.screenWidth - (gp.tileSize *4);
        int height = gp.tileSize*4;
        drawSubWindow(x, y, width, height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32f));
        x += gp.tileSize;
        y += gp.tileSize;

        for(String line: currentDialouge.split("\n")){
            g2.drawString(line,x, y);
            y+=40;
        }

    }

    public void drawSubWindow(int x, int y, int width, int height){
        Color c = new Color(0, 0, 0,220);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);
        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5, y+5, width -10, height-10, 25, 25);
    }

    public void drawPauseScreen(){
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
        String text = "Paused";
        int y = gp.screenHeight/2;
        int x = getXForCenteredText(text);
        g2.drawString(text, x, y);
    }

    public void drawTitleScreen(){
       // g2.setColor(new Color(178,132,190));
       //g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);


        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
        String text = "OTMU";
        int y = gp.tileSize *3;
        int x = getXForCenteredText(text);
        //shadow
        g2.setColor(Color.CYAN);
        g2.drawString(text, x+5, y +5 );
        //main color
        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        //menu
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48f));

        text = "NEW GAME";
        x = getXForCenteredText(text);
        y += gp.tileSize *4;
        g2.drawString(text,x, y);
        if(commandNum == 0){
            g2.drawString("<", x-gp.tileSize, y);
        }

        text = "LOAD GAME";
        x = getXForCenteredText(text);
        y += gp.tileSize;
        g2.drawString(text,x, y);
        if(commandNum == 1){
            g2.drawString("<", x-gp.tileSize, y);
        }

        text = "QUIT";
        x = getXForCenteredText(text);
        y += gp.tileSize;
        g2.drawString(text,x, y);
        if(commandNum == 2){
            g2.drawString("<", x-gp.tileSize, y);
        }
    }

    public int getXForCenteredText(String text){
        int lenght = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - lenght/2;
        return x;

    }
}
