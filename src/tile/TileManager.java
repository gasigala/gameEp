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
    public Tile[] tile;
    public int[][] mapTileNum;

    public TileManager(GamePanel gp){
        this.gp = gp;
        tile = new Tile[10];
        mapTileNum =  new int[gp.maxWorldCol][gp.maxworldRow];
        getTileImage();
        loadMap("gameEp/res/maps/world01.txt");

    }

    public void getTileImage(){
        try{
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(new FileInputStream("gameEp/res/tiles/grass.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(new FileInputStream("gameEp/res/tiles/wall.png"));
            tile[1].collision = true;

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(new FileInputStream("gameEp/res/tiles/ocean.png"));
            tile[2].collision = true;

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(new FileInputStream("gameEp/res/tiles/earth.png"));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(new FileInputStream("gameEp/res/tiles/tree.png"));
            tile[4].collision = true; 

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(new FileInputStream("gameEp/res/tiles/sand.png"));

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
            while(col < gp.maxWorldCol && row < gp.maxworldRow){
                String line = scnr.nextLine();
                while(col < gp.maxWorldCol){
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                //probably do not need the if right here
                if(col == gp.maxWorldCol){
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

        int worldCol= 0;
        int worldRow = 0;

        while(worldCol < gp.maxWorldCol && worldRow < gp.maxworldRow){
            // i know java is usually row col but im just following this indian dude 
            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            //used to offset the diffrence
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            //only draw the part of the screen that the player can see
            //gp tile size provides sort of a buffer
            if(worldX  + gp.tileSize > gp.player.worldX - gp.player.screenX && 
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){
                    g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);

                }
            worldCol++;

            if(worldCol == gp.maxWorldCol){
                worldCol = 0;
                worldRow ++;
            }
        }

    }
}
