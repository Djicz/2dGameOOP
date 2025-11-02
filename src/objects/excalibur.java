package objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class excalibur extends SuperObject{
    public excalibur() {
        name = "EXCALIBUR";
        stat = 1000;
        knockBackPower = 1;
        information = "CHEM PHAT CUT LUON";
        type = weaponItems;
        price = 0;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/swords/excalibur.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}
