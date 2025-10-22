package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class object_door extends SuperObject {
    public object_door() {
        name = "door";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/door.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
        collision = true;
    }
}
