package src.object;

import java.io.IOException;

import javax.imageio.ImageIO;
import java.io.FileInputStream;
import src.main.GamePanel;


public class OBJ_Door extends SuperObject{
    GamePanel gp;
    public OBJ_Door(GamePanel gp){
        name = "Door";
        try{
            image = ImageIO.read(new FileInputStream("res/objects/door.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);

        }
        catch(IOException e){
            e.printStackTrace();
        }
        collision = true;
    }
}
