package src.object;

import java.io.IOException;

import javax.imageio.ImageIO;
import java.io.FileInputStream;

public class OBJ_Door extends SuperObject{
    public OBJ_Door(){
        name = "Door";
        try{
            image = ImageIO.read(new FileInputStream("gameEp/res/objects/door.png"));

        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
