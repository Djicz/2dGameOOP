package objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class blue_sword extends SuperObject {
    public blue_sword() {
        name = "blue sword";
        stat = 1;
        knockBackPower = 2;
        information = "Attack: 1\nKnock Back Power: 2";
        type = weaponItems;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/swords/blue_sword.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}
