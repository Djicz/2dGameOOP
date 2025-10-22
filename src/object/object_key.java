package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class object_key extends SuperObject{
    public object_key() {
        name = "key";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
