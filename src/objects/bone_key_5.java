package objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class bone_key_5 extends SuperObject{
    public bone_key_5() {
        name = "Bone Key 5";
        information = "Bone of Orc";
        type = keyItems;
        price = 100;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/undeadItems/bone_key_5.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}
