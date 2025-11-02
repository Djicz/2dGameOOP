package objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class bone_key extends SuperObject{
    public bone_key() {
        name = "Bone Key";
        information = "Bone of Orc";
        type = keyItems;
        price = 100;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/undeadItems/bone_key.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}
