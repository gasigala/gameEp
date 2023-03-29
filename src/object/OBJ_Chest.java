package src.object;

import java.io.IOException;

import javax.imageio.ImageIO;
import java.io.FileInputStream;
import src.main.GamePanel;

public class OBJ_Chest extends SuperObject{
    GamePanel gp;

    public OBJ_Chest(GamePanel gp){
        this.gp = gp;
        name = "Chest";
        try{
            image = ImageIO.read(new FileInputStream("res/objects/chest.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);

        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
