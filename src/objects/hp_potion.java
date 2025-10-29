package objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class hp_potion extends SuperObject {
    public hp_potion() {
        name = "HP Potion";
        information = "Used to restore a certain amount of health";
        type = potionItems;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/items/hp_potion.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}
