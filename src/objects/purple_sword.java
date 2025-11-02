package objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class purple_sword extends SuperObject{
    public purple_sword() {
        name = "Purple Sword";
        stat = 3;
        knockBackPower = 4;
        information = "Attack: 3\nKnock Back Power: 4";
        type = weaponItems;
        price = 100;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/swords/purpil_sword.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}
