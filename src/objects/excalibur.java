package objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class excalibur extends SuperObject{
    public excalibur() {
        name = "EXCALIBUR";
        stat = 1000;
        knockBackPower = 100;
        information = "CHEM PHAT CUT LUON";
        type = weaponItems;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/swords/excalibur.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}
