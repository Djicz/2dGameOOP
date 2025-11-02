package objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class red_sword extends SuperObject{
    public red_sword() {
        name = "Red Sword";
        stat = 4;
        knockBackPower = 10;
        information = "Attack: 4\nKnock Back Power: 10";
        type = weaponItems;
        price = 1000;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/swords/red_sword.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}
