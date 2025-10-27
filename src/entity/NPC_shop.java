package entity;

import main.GamePanel;
import object.SuperObject;

import java.util.ArrayList;

public class NPC_shop  extends Entity{
    public NPC_shop(GamePanel gp) {
        super(gp);
        getNPCImage();
        setDialogue();
        direction = "down";
        name = "shop";
        items = new ArrayList<>();
    }
    public void getNPCImage() {
        down1 = getImage("/NPC/shop/merchant_down_1");
        down2 = getImage("/NPC/shop/merchant_down_2");
    }
    public void setDialogue() {
        dialogues[0] = "Are you have money?\nLet's check my shopd";
    }
    public void speak() {
        super.speak();
    }
    @Override
    public void update() {
        spriteCounter++; // => lenh nay lam player di chuyen ke ca khi dung yen
        if (spriteCounter > 30) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }
}
