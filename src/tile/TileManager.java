package src.tile;

import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileInputStream;
import java.awt.Graphics2D;

import src.main.GamePanel;

public class TileManager {
    GamePanel gp;
    Tile[] tile;
    int[][] mapTileNum;

    public TileManager(GamePanel gp){
        this.gp = gp;
        tile = new Tile[10];
        mapTileNum =  new int[gp.maxScreenCol][gp.maxScreenRow];
        getTileImage();
        loadMap("gameEp/res/maps/map01.txt");
        // for(int i = 0; i < mapTileNum.length; i++){
        //     for (int j =0; j < mapTileNum[i].length; j++){
        //         System.out.print(mapTileNum[i][j] + " ");
        //     }
        //     System.out.println();
        // }
    }

    public void getTileImage(){
        try{
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(new FileInputStream("gameEp/res/tiles/grass.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(new FileInputStream("gameEp/res/tiles/wall.png"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(new FileInputStream("gameEp/res/tiles/ocean.png"));

        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath){
        try{
            Scanner scnr = new Scanner(new File(filePath));

            int col = 0;
            int row = 0;
            while(col < gp.maxScreenCol && row < gp.maxScreenRow){
                String line = scnr.nextLine();
                System.out.println("line is "+ line);
                while(col < gp.maxScreenCol){
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                //probably do not need the if right here
                if(col == gp.maxScreenCol){
                    col = 0;
                    row++;
                }
            }
        }
        catch(Exception e){
            System.out.println("error bozo");
        }
    }

    public void draw(Graphics2D g2){

        int row = 0;
        int col = 0;
        int x = 0;
        int y = 0;


        while(col < gp.maxScreenCol && row < gp.maxScreenRow){
            // i know java is usually row col but im just following this indian dude 
            int tileNum = mapTileNum[col][row];

            g2.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
            col++;
            x += gp.tileSize;

            if(col == gp.maxScreenCol){
                col = 0;
                x = 0;
                row ++;
                y += gp.tileSize;
            }
        }

    }
}
