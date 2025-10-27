package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class object_chest extends SuperObject {
    public object_chest() {
        name = "chest";
        information = "1 chiếc rương bình thường";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/chest.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
