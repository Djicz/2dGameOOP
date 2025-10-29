package objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class object_heart extends SuperObject {
    public object_heart() {
        name = "heart";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/heart_full.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/objects/heart_half.png"));
            image3 = ImageIO.read(getClass().getResourceAsStream("/objects/heart_blank.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
