package objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class key extends SuperObject{
    public key() {
        name = "Key";
        information = "Open dungeon!";
        type = keyItems;
        price = 100;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/undeadItems/key.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}
