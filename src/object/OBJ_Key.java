package src.object;

import java.io.IOException;

import javax.imageio.ImageIO;
import java.io.FileInputStream;

public class OBJ_Key extends SuperObject{
    public OBJ_Key(){
        name = "Key";
        try{
            image = ImageIO.read(new FileInputStream("res/objects/key.png"));

        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
