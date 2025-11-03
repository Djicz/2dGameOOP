package entity;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class monster_KingOrc extends Entity{
    int attackCounter;
    public monster_KingOrc(GamePanel gp){
        super(gp);
        getMonster();
        getAttackImage();
        attackCounter = 0;
        direction = "down";
        defaultSpeed = 3;
        speed = defaultSpeed;
        attack = 1;
        defense = 5;
        maxLife = 100;
        life = maxLife;
        expWhenKill = new Random().nextInt(5) + 1; // Random exp nhan duoc tu 1 den 5
        coinWanted = new Random().nextInt(5) + 1; // Random vang nhan duoc tu 1 den 5
        monsterNum = 1;
        solidArea.x = 16;
        solidArea.y = 16;
        solidArea.width = 64;
        solidArea.height = 80;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        attackArea.width = 96;
        attackArea.height = 96;
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
    public void getAttackImage() {
        attack_up1 = getImage("/monster/orc_attack_up_1");
        attack_up2 = getImage("/monster/orc_attack_up_2");
        attack_down1 = getImage("/monster/orc_attack_down_1");
        attack_down2 = getImage("/monster/orc_attack_down_2");
        attack_left1 = getImage("/monster/orc_attack_left_1");
        attack_left2 = getImage("/monster/orc_attack_left_2");
        attack_right1 = getImage("/monster/orc_attack_right_1");
        attack_right2 = getImage("/monster/orc_attack_right_2");
    }
    public void update() {
        int xDistance = Math.abs(worldX - gp.getPlayer().worldX);
        int yDistance = Math.abs(worldY - gp.getPlayer().worldY);
        int tileDistance = (xDistance + yDistance) / gp.getTileSize();
        if(onPath == true && tileDistance < 5) {
            // Xác định hướng tấn công dựa trên vị trí player
            int dx = gp.getPlayer().worldX - worldX;
            int dy = gp.getPlayer().worldY - worldY;

            if (Math.abs(dx) > Math.abs(dy)) {
                if (dx > 0) direction = "right";
                else direction = "left";
            } else {
                if (dy > 0) direction = "down";
                else direction = "up";
            }

            attackMode = true;
        }
        if(attackMode == true) {
            attackProcess();
            ++attackCounter;
            if(attackCounter > 60) {
                attackCounter = 0;
                attackMode = false;
            }
        }
        else {
            super.update();
        }

    }
    public void setAction() {
        if(attackMode == true) {
            return;
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
    public void attackProcess() {
        colisPlayer = false;
        ++spriteCounter;
        if(spriteCounter <= 45) {
            spriteNum = 1;
        }
        else if(spriteCounter <= 55) {
            spriteNum = 2;
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;
            switch (direction) {
                case "up":
                    worldY -= attackArea.height;
                    break;
                case "down":
                    worldY += attackArea.height;
                    break;
                case "left":
                    worldX -= attackArea.width;
                    break;
                case "right":
                    worldX += attackArea.width;
                    break;
            }
            // Attack area -> solid area
            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;
            // Check vung attack cham vao player hay khong
            gp.getcChecker().checkPlayer(this);
            if(colisPlayer ==  true) {
                if(gp.getPlayer().immortalState == false) {
                    int damage = attack * 10 - gp.getPlayer().defense;
                    if(damage <= 0) damage = 1;
                    gp.getPlayer().life -= damage;
                    gp.getUi().addMessage("-" + damage + " HP");
                    gp.getPlayer().immortalState = true;
                }
            }
            // Khoi phuc cac stat
            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;

        }
        else {
            spriteNum = 1;
            spriteCounter = 0;
            attackMode = false;
        }
    }
    public void draw(Graphics2D g2, GamePanel gp) {
        BufferedImage image = null;
        int screenX = worldX - gp.getPlayer().worldX + gp.getPlayer().screenX;
        // player.worldX la toa do X cua player trong map world, player.screenX la toa do vi tri nguoi choi hien thi tren man hinh (o giua map)
        // screenX la toa do x hien thi tren man hinh
        // Toa do hien thi tren man hinh = toa do trong world - toa do player trong world + toa do player tren man hinh
        int screenY = worldY - gp.getPlayer().worldY + gp.getPlayer().screenY;
        // Tuong tu screenX
        int widthTmp = gp.getTileSize() * 2;
        int heightTmp = gp.getTileSize() * 2;
        if (worldX + gp.getTileSize() > gp.getPlayer().worldX - gp.getPlayer().screenX &&
                worldX - gp.getTileSize() < gp.getPlayer().worldX + gp.getPlayer().screenX &&
                worldY + gp.getTileSize() > gp.getPlayer().worldY - gp.getPlayer().screenY &&
                worldY - gp.getTileSize() < gp.getPlayer().worldY + gp.getPlayer().screenY) {
            if(attackMode == true) {
                switch (direction) {
                    case "up": //Neu direction == "up" => image = up1
                        if (spriteNum == 1) {
                            image = attack_up1;
                        }
                        if (spriteNum == 2) {
                            image = attack_up2;
                        }
                        heightTmp += gp.getTileSize();
                        screenY -= gp.getTileSize();
                        break;
                    case "down":
                        if (spriteNum == 1) {
                            image = attack_down1;
                        }
                        if (spriteNum == 2) {
                            image = attack_down2;
                        }
                        heightTmp += gp.getTileSize();
                        break;
                    case "left":
                        if (spriteNum == 1) {
                            image = attack_left1;
                        }
                        if (spriteNum == 2) {
                            image = attack_left2;
                        }
                        widthTmp += gp.getTileSize();
                        screenX -= gp.getTileSize();
                        break;
                    case "right":
                        if (spriteNum == 1) {
                            image = attack_right1;
                        }
                        if (spriteNum == 2) {
                            image = attack_right2;
                        }
                        widthTmp += gp.getTileSize();
                        break;
                }
            }
            else {
                switch (direction) {
                    case "up": //Neu direction == "up" => image = up1
                        if (spriteNum == 1) {
                            image = up1;
                        }
                        if (spriteNum == 2) {
                            image = up2;
                        }
                        break;
                    case "down":
                        if (spriteNum == 1) {
                            image = down1;
                        }
                        if (spriteNum == 2) {
                            image = down2;
                        }
                        break;
                    case "left":
                        if (spriteNum == 1) {
                            image = left1;
                        }
                        if (spriteNum == 2) {
                            image = left2;
                        }

                        break;
                    case "right":
                        if (spriteNum == 1) {
                            image = right1;
                        }
                        if (spriteNum == 2) {
                            image = right2;
                        }
                        break;
                }

            }
            if (immortalState == true) {
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
            }
            if (dying == true) {
                dyingAnimation(g2);
            }
            g2.drawImage(image, screenX, screenY, widthTmp, heightTmp, null); // Ve component map
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f)); // sau khi ve thi reset ve do trong suot ve 0
            if(drawHP == true) {
                drawMonsterHP(g2, screenX, screenY);
                ++drawHPCounter;
                if(drawHPCounter > 60) {
                    drawHPCounter = 0;
                    drawHP = false;
                }
            }
        } // Chi khi toa do world o trong khu vuc man hinh co the hien thi thi moi in ra
    }
    public void drawMonsterHP(Graphics2D g2, int x, int y) {
        if(attackMode == true) {
            switch (direction) {
                case "up":
                    x += gp.getTileSize();
                    break;
                case "down":

                case "left":
                    x += gp.getTileSize();
                    break;
                case "right":
            }
        }
        g2.setColor(Color.black);
        g2.fillRect(x, y - 10, gp.getTileSize(), 5);
        int widthOfHP = (life * gp.getTileSize()) / maxLife;
        g2.setColor(Color.red);
        g2.fillRect(x, y - 10, widthOfHP, 5);
    }

}
