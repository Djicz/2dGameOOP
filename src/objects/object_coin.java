package objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class object_coin extends SuperObject {
    public object_coin() {
        name = "coin";
        stat = 5;
        type = potionItems;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/coin_bronze.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

}
