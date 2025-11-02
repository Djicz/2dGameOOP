package objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class skill_book extends SuperObject{
    public skill_book() {
        name = "Skill Book";
        information = "Fire ball!";
        type = skillItems;
        price = 1000;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/undeadItems/skill_book.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}
