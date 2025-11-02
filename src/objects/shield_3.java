package objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class shield_3 extends SuperObject{
    public shield_3() {
        name = "Shield Level 3";
        stat = 3;
        information = "Defense: 3";
        type = shieldItems;
        price = 30;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/shields/shield_3.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}
