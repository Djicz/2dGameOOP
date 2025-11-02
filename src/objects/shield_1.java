package objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class shield_1 extends SuperObject{
    public shield_1() {
        name = "Shield Level 1";
        stat = 1;
        information = "Defense: 1";
        type = shieldItems;
        price = 10;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/shields/shield_1.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}
