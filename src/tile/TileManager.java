package src.tile;

import java.util.Scanner;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileInputStream;
import java.awt.Graphics2D;
import src.main.GamePanel;
import src.main.UtilityTool;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int[][] mapTileNum;

    public TileManager(GamePanel gp){
        this.gp = gp;
        tile = new Tile[50];
        mapTileNum =  new int[gp.maxWorldCol][gp.maxworldRow];
        getTileImage();
        loadMap("res/maps/worldV2.txt");

    }

    public void getTileImage(){
            //doing this bc if the map is made up of single and double digits the map looks fucked up
            setup(0, "grass00", false);
            setup(1, "grass00", false);
            setup(2, "grass00", false);
            setup(3, "grass00", false);
            setup(4, "grass00", false);
            setup(5, "grass00", false);
            setup(6, "grass00", false);
            setup(7, "grass00", false);
            setup(8, "grass00", false);
            setup(9, "grass00", false);
            //REAL #TILEs
            setup(10, "grass00", false);
            setup(11, "grass01", false);
            setup(12, "water00", true);
            setup(13, "water01", true);
            setup(14, "water02", true);
            setup(15, "water03", true);
            setup(16, "water04", true);
            setup(17, "water05", true);
            setup(18, "water06", true);
            setup(19, "water07", true);
            setup(20, "water08", true);
            setup(21, "water09", true);
            setup(22, "water10", true);
            setup(23, "water11", true);
            setup(24, "water12", true);
            setup(25, "water13", true);

            setup(26, "road00", false);
            setup(27, "road01", false);
            setup(28, "road02", false);
            setup(29, "road03", false);
            setup(30, "road04", false);
            setup(31, "road05", false);
            setup(32, "road06", false);
            setup(33, "road07", false);
            setup(34, "road08", false);
            setup(35, "road09", false);
            setup(36, "road10", false);
            setup(37, "road11", false);
            setup(38, "road12", false);

            setup(39, "earth", false);
            setup(40, "wall", true);
            setup(41, "tree", true);









    }

    public void setup(int index, String imageName, boolean collision){
        UtilityTool uTool = new UtilityTool();
        try{
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(new FileInputStream("res/tiles/"+ imageName + ".png"));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;
        }
        catch(Exception e){
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
            System.out.println("error when trying to load map");
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
                    
                    g2.drawImage(tile[tileNum].image, screenX, screenY, null);

                }
            worldCol++;

            if(worldCol == gp.maxWorldCol){
                worldCol = 0;
                worldRow ++;
            }
        }

    }
}
