package objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class shield_5 extends SuperObject{
    public shield_5() {
        name = "Shield Level 5";
        stat = 5;
        information = "Defense: 5";
        type = shieldItems;
        price = 50;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/shields/shield_5.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}
