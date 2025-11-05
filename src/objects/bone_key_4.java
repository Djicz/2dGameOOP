package objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class bone_key_4 extends SuperObject{
    public bone_key_4() {
        name = "Bone  4";
        information = "Bone of Orc";
        type = keyItems;
        price = 100;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/undeadItems/bone_key_4.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}
