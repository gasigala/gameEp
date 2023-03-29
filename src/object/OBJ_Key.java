package src.object;

import java.io.IOException;

import javax.imageio.ImageIO;

import src.main.GamePanel;

import java.io.FileInputStream;

public class OBJ_Key extends SuperObject{
    GamePanel gp;

    public OBJ_Key(GamePanel gp){
        this.gp = gp;
        name = "Key";
        try{
            image = ImageIO.read(new FileInputStream("res/objects/key.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);

        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
