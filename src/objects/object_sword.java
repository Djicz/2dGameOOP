package objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class object_sword extends SuperObject {

    public object_sword() {
        name = "Normal Sword";
        stat = 1;
        knockBackPower = 0;
        information = "Normal sword\nAttack: 1";
        type = weaponItems;
        price = 1;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/swords/sword_normal.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}
