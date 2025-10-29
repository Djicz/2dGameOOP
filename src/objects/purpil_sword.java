package objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class purpil_sword extends SuperObject{
    public purpil_sword() {
        name = "purpil sword";
        stat = 3;
        knockBackPower = 4;
        information = "Attack: 3\nKnock Back Power: 4";
        type = weaponItems;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/swords/purpil_sword.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}
