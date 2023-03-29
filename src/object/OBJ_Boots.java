package src.object;

import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.FileInputStream;
import src.main.GamePanel;


public class OBJ_Boots extends SuperObject{
    GamePanel gp;

    public OBJ_Boots( GamePanel gp){
        this.gp = gp;
        name = "Boots";
        try{
            image = ImageIO.read(new FileInputStream("res/objects/boots.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);

        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
