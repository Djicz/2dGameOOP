package objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class object_key extends SuperObject{
    public object_key() {
        name = "objects";
        information = "1 chiếc chìa khóa bình thường";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
