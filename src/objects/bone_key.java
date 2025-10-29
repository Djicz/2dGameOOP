package objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class bone_key extends SuperObject{
    public bone_key() {
        name = "Bone key";
        information = "Bone of Orc";
        type = keyItems;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/undeadItems/bone_key.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}
