package objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class shield_2 extends SuperObject{
    public shield_2() {
        name = "Shield Level 2";
        stat = 2;
        information = "Defense: 2";
        type = shieldItems;
        price = 20;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/shields/shield_2.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}
