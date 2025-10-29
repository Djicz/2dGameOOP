package objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class skill_book extends SuperObject{
    public skill_book() {
        name = "Skill book";
        information = "Fire ball!";
        type = skillItems;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/undeadItems/skill_book.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}
