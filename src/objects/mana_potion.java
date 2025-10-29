package objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class mana_potion extends SuperObject{
    public mana_potion() {
        name = "Mana Potion";
        information = "used to restore a certain amount of mana";
        type = potionItems;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/items/mana_potion.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}
