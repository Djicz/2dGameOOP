package entity;

import main.GamePanel;

import java.awt.image.BufferedImage;
import java.util.Random;

public class NPC_OldMan extends Entity {
    public NPC_OldMan(GamePanel gp) {
        super(gp);

        direction = "down";
        speed = 1;
        getNPCImage();
        setDialogue();
    }

    public void getNPCImage() {
        up1 = getImage("/NPC/oldman/oldman_up_1");
        up2 = getImage("/NPC/oldman/oldman_up_2");
        down1 = getImage("/NPC/oldman/oldman_down_1");
        down2 = getImage("/NPC/oldman/oldman_down_2");
        left1 = getImage("/NPC/oldman/oldman_left_1");
        left2 = getImage("/NPC/oldman/oldman_left_2");
        right1 = getImage("/NPC/oldman/oldman_right_1");
        right2 = getImage("/NPC/oldman/oldman_right_2");
    }

    public void setDialogue() {
        dialogues[0] = "lo thanh chi ton";

    }

    public void setAction() {
        actionLockCounter++;
        if(actionLockCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(100) + 1; // Random 1 so bat ki tu 0 -> 99 ( +1 ) = 1 -> 100
            // Random huong di chuyen cua NPC
            if (i <= 25) {
                direction = "up";
            } else if (i <= 50) {
                direction = "down";
            } else if (i <= 75) {
                direction = "left";
            } else if (i <= 100) {
                direction = "right";
            }
            actionLockCounter = 0;
        }
    }
    public void speak() {
        super.speak();
    }
}
