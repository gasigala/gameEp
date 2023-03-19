package src.object;

import java.io.IOException;

import javax.imageio.ImageIO;
import java.io.FileInputStream;

public class OBJ_Chest extends SuperObject{
    public OBJ_Chest(){
        name = "Door";
        try{
            image = ImageIO.read(new FileInputStream("res/objects/chest.png"));

        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
