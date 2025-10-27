package entity;

import main.GamePanel;

import java.util.Random;

public class monster_orc extends Entity{
    public monster_orc(GamePanel gp){
        super(gp);
        getMonster();
        direction = "down";
        defaultSpeed = 3;
        speed = defaultSpeed;

        maxLife = 100;
        life = maxLife;
        expWhenKill = 5;
        coinWanted = 5;
        monsterNum = 1;
    }
    public void getMonster(){
        up1 = getImage("/monster/orc_up_1");
        up2 = getImage("/monster/orc_up_2");
        down1 = getImage("/monster/orc_down_1");
        down2 = getImage("/monster/orc_down_2");
        left1 = getImage("/monster/orc_left_1");
        left2 = getImage("/monster/orc_left_2");
        right1 = getImage("/monster/orc_right_1");
        right2 = getImage("/monster/orc_right_2");
    }
    public void update() {
        super.update();
        int xDistance = Math.abs(worldX - gp.getPlayer().worldX);
        int yDistance = Math.abs(worldY - gp.getPlayer().worldY);
        int tileDistance = (xDistance + yDistance) / gp.getTileSize();
        if(onPath == false && tileDistance < 5) {
            onPath = true;
        }
    }
    public void setAction() {
        if(onPath == true) {
            int goalCol = (gp.getPlayer().worldX + gp.getPlayer().solidArea.x) / gp.getTileSize();
            int goalRow = (gp.getPlayer().worldY + gp.getPlayer().solidArea.y) / gp.getTileSize();

            searchPath(goalCol, goalRow);
        }
        else {
            actionLockCounter++;
            if (actionLockCounter == 120) {
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
    }
}
