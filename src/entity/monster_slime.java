package entity;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class monster_slime extends Entity{
    int attackCounter;
    public monster_slime(GamePanel gp){
        super(gp);
        getMonster();
        getAttackImage();
        attackCounter = 0;
        direction = "down";
        defaultSpeed = 1;
        speed = defaultSpeed;

        maxLife = 100;
        life = maxLife;
        expWhenKill = 5;
        coinWanted = 5;
        monsterNum = 1;
        solidArea.x = 4;
        solidArea.y = 4;
        solidArea.width = 40;
        solidArea.height = 44;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        attackArea.width = 48;
        attackArea.height = 48;
    }
    public void getMonster(){
        down1 = getImage("/monster/slime/tile000");
        down2 = getImage("/monster/slime/tile001");
        down3 = getImage("/monster/slime/tile002");
        down4 = getImage("/monster/slime/tile003");
        down5 = getImage("/monster/slime/tile004");
        down6 = getImage("/monster/slime/tile005");
        down7 = getImage("/monster/slime/tile006");
        down8 = getImage("/monster/slime/tile007");

        up1 = getImage("/monster/slime/tile008");
        up2 = getImage("/monster/slime/tile009");
        up3 = getImage("/monster/slime/tile010");
        up4 = getImage("/monster/slime/tile011");
        up5 = getImage("/monster/slime/tile012");
        up6 = getImage("/monster/slime/tile013");
        up7 = getImage("/monster/slime/tile014");
        up8 = getImage("/monster/slime/tile015");

        left1 = getImage("/monster/slime/tile016");
        left2 = getImage("/monster/slime/tile017");
        left3 = getImage("/monster/slime/tile018");
        left4 = getImage("/monster/slime/tile019");
        left5 = getImage("/monster/slime/tile020");
        left6 = getImage("/monster/slime/tile021");
        left7 = getImage("/monster/slime/tile022");
        left8 = getImage("/monster/slime/tile023");

        right1 = getImage("/monster/slime/tile024");
        right2 = getImage("/monster/slime/tile025");
        right3 = getImage("/monster/slime/tile026");
        right4 = getImage("/monster/slime/tile027");
        right5 = getImage("/monster/slime/tile028");
        right6 = getImage("/monster/slime/tile029");
        right7 = getImage("/monster/slime/tile030");
        right8 = getImage("/monster/slime/tile031");
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
        if(onPath == true && tileDistance < 2) {
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
            onPath = false;
        }
        if(monsterFlag == true && attackMode == false) {
            onPath = true;
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
            collisionOn = false;
            gp.cChecker.checkTile(this);
            gp.cChecker.checkObject(this, false);
            gp.cChecker.checkPlayer(this);
            int i_mon = gp.cChecker.checkEntity(this, gp.monster);
            int i_npc = gp.cChecker.checkEntity(this, gp.npc);

            if(knockBack == true) {
                if(collisionOn == true) {
                    knockBackCounter = 0;
                    knockBack = false;
                    speed = defaultSpeed;

                }
                else if(collisionOn == false) {
                    switch (gp.getPlayer().direction) {
                        case "up":
                            worldY -= speed;
                            break;
                        case "down":
                            worldY += speed;
                            break;
                        case "left":
                            worldX -= speed;
                            break;
                        case "right":
                            worldX += speed;
                            break;
                    }
                }
                knockBackCounter++;
                if(knockBackCounter == 10) {
                    knockBackCounter = 0;
                    knockBack = false;
                    speed = defaultSpeed;
                }
            }
            else {
                setAction();

                collisionOn = false;
                gp.cChecker.checkTile(this);
                gp.cChecker.checkObject(this, false);
                gp.cChecker.checkPlayer(this);
                gp.cChecker.checkEntity(this, gp.monster);
                gp.cChecker.checkEntity(this, gp.npc);

                if (collisionOn && attackMode == false) {
                    // Đổi hướng ngẫu nhiên khi va chạm
                    String[] dirs = {"up", "down", "left", "right"};
                    direction = dirs[new Random().nextInt(dirs.length)];
                    collisionOn = false;
                    gp.cChecker.checkTile(this);
                    gp.cChecker.checkObject(this, false);
                    gp.cChecker.checkPlayer(this);
                    gp.cChecker.checkEntity(this, gp.monster);
                    gp.cChecker.checkEntity(this, gp.npc);
                }

                if (!collisionOn) {
                    switch (direction) {
                        case "up": worldY -= speed; break;
                        case "down": worldY += speed; break;
                        case "left": worldX -= speed; break;
                        case "right": worldX += speed; break;
                    }
                }
            }



            spriteCounter++; // => lenh nay lam player di chuyen ke ca khi dung yen
            if (spriteCounter > 12) {
                if (spriteNum < 8) {
                    spriteNum++;
                } else if (spriteNum == 8) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
            // Moi 1000000000/FPS giay spriteCounter de them 1 tuc la cu 12 anh duoc ve thi se cap nhat image 1 lan sau do reset spriteCounter ve 0
            ++immortalCounter;
            if (immortalCounter > 60) {
                if (immortalState == true) {
                    immortalState = false;
                }
                immortalCounter = 0;
            }
        }

    }
    public void setAction() {
        if(attackMode == true) {
            return;
        }
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
    public void attackProcess() {
        colisPlayer = false;
        ++spriteCounter;
        if(spriteCounter <= 10) {
            spriteNum = 1;
        }
        else if(spriteCounter <= 30) {
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
            gp.cChecker.checkPlayer(this);
            if(colisPlayer ==  true) {
                if(gp.getPlayer().immortalState == false) {
                    gp.getPlayer().life -= 10;
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
        int widthTmp = gp.getTileSize();
        int heightTmp = gp.getTileSize();
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
                        if (spriteNum == 3) {
                            image = up3;
                        }
                        if (spriteNum == 4) {
                            image = up4;
                        }
                        if (spriteNum == 5) {
                            image = up5;
                        }
                        if (spriteNum == 6) {
                            image = up6;
                        }
                        if (spriteNum == 7) {
                            image = up7;
                        }
                        if (spriteNum == 8) {
                            image = up8;
                        }
                        break;
                    case "down":
                        if (spriteNum == 1) {
                            image = down1;
                        }
                        if (spriteNum == 2) {
                            image = down2;
                        }
                        if (spriteNum == 3) {
                            image = down3;
                        }
                        if (spriteNum == 4) {
                            image = down4;
                        }
                        if (spriteNum == 5) {
                            image = down5;
                        }
                        if (spriteNum == 6) {
                            image = down6;
                        }
                        if (spriteNum == 7) {
                            image = down7;
                        }
                        if (spriteNum == 8) {
                            image = down8;
                        }
                        break;
                    case "left":
                        if (spriteNum == 1) {
                            image = left1;
                        }
                        if (spriteNum == 2) {
                            image = left2;
                        }
                        if (spriteNum == 3) {
                            image = left3;
                        }
                        if (spriteNum == 4) {
                            image = left4;
                        }
                        if (spriteNum == 5) {
                            image = left5;
                        }
                        if (spriteNum == 6) {
                            image = left6;
                        }
                        if (spriteNum == 7) {
                            image = left7;
                        }
                        if (spriteNum == 8) {
                            image = left8;
                        }
                        break;
                    case "right":
                        if (spriteNum == 1) {
                            image = right1;
                        }
                        if (spriteNum == 2) {
                            image = right2;
                        }
                        if (spriteNum == 3) {
                            image = right3;
                        }
                        if (spriteNum == 4) {
                            image = right4;
                        }
                        if (spriteNum == 5) {
                            image = right5;
                        }
                        if (spriteNum == 6) {
                            image = right6;
                        }
                        if (spriteNum == 7) {
                            image = right7;
                        }
                        if (spriteNum == 8) {
                            image = right8;
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
