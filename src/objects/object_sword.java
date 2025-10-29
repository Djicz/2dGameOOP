package objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class object_sword extends SuperObject {

    public object_sword() {
        name = "sword";
        stat = 1;
        knockBackPower = 0;
        information = "Normal sword, attack: 1";
        type = weaponItems;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/swords/sword_normal.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}
