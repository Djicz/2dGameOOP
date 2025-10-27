package object;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class object_sword extends SuperObject {

    public object_sword() {
        name = "sword";
        stat = 1;
        knockBackPower = 10;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/sword_normal.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}
