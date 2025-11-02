package objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class blue_sword extends SuperObject {
    public blue_sword() {
        name = "Blue Sword";
        stat = 1;
        knockBackPower = 2;
        information = "Attack: 1\nKnock Back Power: 2";
        type = weaponItems;
        price = 10;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/swords/blue_sword.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}
