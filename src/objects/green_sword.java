package objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class green_sword extends SuperObject {
    public green_sword() {
        name = "Green Sword";
        stat = 2;
        knockBackPower = 4;
        information = "Attack: 2\nKnock Back Power: 4";
        type = weaponItems;
        price = 20;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/swords/green_sword.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}
