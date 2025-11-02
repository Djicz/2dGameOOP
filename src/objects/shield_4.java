package objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class shield_4 extends SuperObject{
    public shield_4() {
        name = "Shield Level 4";
        stat = 4;
        information = "Defense: 4";
        type = shieldItems;
        price = 40;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/shields/shield_4.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}
