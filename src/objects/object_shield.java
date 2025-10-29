package objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class object_shield extends SuperObject {
    public object_shield() {
        name = "shield";
        stat = 1;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/shield_wood.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}
