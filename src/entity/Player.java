package entity;

import com.sun.security.jgss.GSSUtil;
import main.CollisionChecker;
import main.GamePanel;
import main.KeyHandler;
import object.SuperObject;
import object.object_fireball;
import object.object_shield;
import object.object_sword;

import javax.crypto.spec.PSource;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;

public class Player extends Entity {

    KeyHandler keyH;
    public final int screenX, screenY; // Toa do vi tri player duoc ve tren man hinh
    public ArrayList<SuperObject> items = new ArrayList<>();


    public Player(GamePanel gp, KeyHandler keyH) {
        super(gp);
        this.keyH = keyH;
        // Can giua man hinh
        screenX = gp.getScreenWidth() / 2 - gp.getTileSize() / 2;
        screenY = gp.getScreenHeight() / 2 - gp.getTileSize() / 2;
        solidArea = new Rectangle(8, 16, 32, 32); // x, y la toa do goc tren ben trai cua hinh chu nhat trong object (o day la player)
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        attackArea.width = 36;
        attackArea.height = 36;
        setDefaultValues(); // Dat chi so ban dau cho player
        getPlayerImage(); // Lay anh player
        getPlayerAttackImage();

        // Set chi so nhan vat ban dau
        level = 1;
        strength = 1;
        dexterity = 1;
        exp = 1;
        nextLevelExp = 5;
        coin = 0;
        currentWeapon = new object_sword();
        currentShield = new object_shield();
        attack = getPlayerAttack();
        defense = getPlayerDefense();
        addItems();


    }
    public void addItems() {
        items.add(currentWeapon);
        items.add(currentShield);
    }
    public int getPlayerAttack() {
        return currentWeapon.stat + strength * level; // Attack = chi so cua vu khi + suc manh * level

    }
    public int getPlayerDefense() {
        return currentShield.stat + dexterity * level;
    }

    public void getPlayerImage() {
        up1 = getImage("/player/walk/up/1");
        up2 = getImage("/player/walk/up/2");
        up3 = getImage("/player/walk/up/3");
        up4 = getImage("/player/walk/up/4");
        up5 = getImage("/player/walk/up/5");
        up6 = getImage("/player/walk/up/6");

        down1 = getImage("/player/walk/down/1");
        down2 = getImage("/player/walk/down/2");
        down3 = getImage("/player/walk/down/3");
        down4 = getImage("/player/walk/down/4");
        down5 = getImage("/player/walk/down/5");
        down6 = getImage("/player/walk/down/6");

        left1 = getImage("/player/walk/left/1");
        left2 = getImage("/player/walk/left/2");
        left3 = getImage("/player/walk/left/3");
        left4 = getImage("/player/walk/left/4");
        left5 = getImage("/player/walk/left/5");
        left6 = getImage("/player/walk/left/6");

        right1 = getImage("/player/walk/right/1");
        right2 = getImage("/player/walk/right/2");
        right3 = getImage("/player/walk/right/3");
        right4 = getImage("/player/walk/right/4");
        right5 = getImage("/player/walk/right/5");
        right6 = getImage("/player/walk/right/6");

    }
    public void getPlayerAttackImage() {
        attack_up1 = getImage("/player/boy_attack_up_1");
        attack_up2 = getImage("/player/boy_attack_up_2");
        attack_down1 = getImage("/player/boy_attack_down_1");
        attack_down2 = getImage("/player/boy_attack_down_2");
        attack_left1 = getImage("/player/boy_attack_left_1");
        attack_left2 = getImage("/player/boy_attack_left_2");
        attack_right1 = getImage("/player/boy_attack_right_1");
        attack_right2 = getImage("/player/boy_attack_right_2");

    }

    public void setDefaultValues() {
        // worldX va worldY la toa do vi tri cua player tren ban do the gioi
        worldX = gp.getTileSize() * 23;
        worldY = gp.getTileSize() * 21;
        speed = 4;
        direction = "down"; // Mac dinh player dang nhin xuong duoi
        maxLife = 100;
        life = maxLife;
        maxStamina = 100;
        stamina = maxStamina;
        immortalCounter = 0;
        immortalState = false;
        projectile = new object_fireball(gp);

    }

    public void update() {
        // Update status player
        updateState();
        // Check trang thai tan cong
        if(attackMode == true) {
            attackProcess();
        }
        // Toa do ben trai tren cung la (0, 0)
        // => Truc x tang theo chieu tu trai sang phai
        // => Truc y tang theo chieu tu tren xuong duoi
        else if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true || keyH.enterPressed == true) {
            if (keyH.upPressed == true) {
                spriteCounter++; // Player chi di chuyen khi an nut di chuyen
                direction = "up"; // Chuyen animation player

            } else if (keyH.downPressed == true) {
                spriteCounter++;
                direction = "down";

            } else if (keyH.leftPressed == true) {
                spriteCounter++;
                direction = "left";

            } else if (keyH.rightPressed == true) {
                spriteCounter++;
                direction = "right";

            }

            collisionOn = false;
            gp.cChecker.checkTile(this);
            // Kiem tra object
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

//            if(keyH.shiftPressed != true) {
                //Kiem tra NPC
                int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
                int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
                soloMonster(monsterIndex);
                interactNPC(npcIndex);
//            }
            gp.eHandler.checkEvent();

            if (collisionOn == false && keyH.enterPressed == false) {

                switch (direction) {
                    case "up":
                        if(keyH.shiftPressed == true && stamina > 0) {
                            for(int i = 1; i <= 3; i++) {
                                worldY -= speed;
                                gp.cChecker.checkEntity(this, gp.npc);
                                gp.cChecker.checkEntity(this, gp.monster);
                                if(collisionOn == true) {
                                    break;
                                }
                            }
//                            worldY -= (3 * speed);
                            ++staminaCounterDecrea;
                            if(staminaCounterDecrea > 10) {
                                if(stamina > 20)    stamina -= 20;
                                else    stamina = 0;
                                staminaCounterDecrea = 0;
                            }
                        }
                        else    worldY -= speed;
                        break;
                    case "down":
                        if(keyH.shiftPressed == true && stamina > 20) {
                            for(int i = 1; i <= 3; i++) {
                                worldY += speed;
                                gp.cChecker.checkEntity(this, gp.npc);
                                gp.cChecker.checkEntity(this, gp.monster);
                                if(collisionOn == true) {
                                    break;
                                }
                            }
//                            worldY += (3 * speed);
                            ++staminaCounterDecrea;
                            if(staminaCounterDecrea > 10) {
                                if(stamina > 20)    stamina -= 20;
                                else    stamina = 0;
                                staminaCounterDecrea = 0;
                            }
                        }
                        else    worldY += speed;
                        break;
                    case "left":
                        if(keyH.shiftPressed == true && stamina > 20) {
                            for(int i = 1; i <= 3; i++) {
                                worldX -= speed;
                                gp.cChecker.checkEntity(this, gp.npc);
                                gp.cChecker.checkEntity(this, gp.monster);
                                if(collisionOn == true) {
                                    break;
                                }
                            }
//                            worldX -= (3 * speed);
                            ++staminaCounterDecrea;
                            if(staminaCounterDecrea > 10) {
                                if(stamina > 20)    stamina -= 20;
                                else    stamina = 0;
                                staminaCounterDecrea = 0;
                            }
                        }
                        else    worldX -= speed;
                        break;
                    case "right":
                        if(keyH.shiftPressed == true && stamina > 20) {
                            for(int i = 1; i <= 3; i++) {
                                worldX += speed;
                                gp.cChecker.checkEntity(this, gp.npc);
                                gp.cChecker.checkEntity(this, gp.monster);
                                if(collisionOn == true) {
                                    break;
                                }
                            }
//                            worldX += (3 * speed);
                            ++staminaCounterDecrea;
                            if(staminaCounterDecrea > 10) {
                                if(stamina > 20)    stamina -= 20;
                                else    stamina = 0;
                                staminaCounterDecrea = 0;
                            }
                        }
                        else    worldX += speed;
                        break;
                }
            }
            keyH.enterPressed = false;
            //spriteCounter++; => lenh nay lam player di chuyen ke ca khi dung yen
            if (spriteCounter > 5) {
                if (spriteNum < 6) {
                    spriteNum++;
                } else if (spriteNum == 6) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
            // Moi 1000000000/FPS giay spriteCounter de them 1 tuc la cu 12 anh duoc ve thi se cap nhat image 1 lan sau do reset spriteCounter ve 0

        }

        if(keyH.shotKeyPressed == true && projectile.aliveState == false) {
            projectile.set(worldX, worldY, direction, true, this);
            gp.projectileList.add(projectile);


        }

        // Cu 120 khung hinh thi het kha nang bat tu
        ++immortalCounter;
        if(immortalCounter > 120) {
            if(immortalState == true) {
                immortalState = false;
            }
            immortalCounter = 0;
        }
        // Cu 120 khung hinh thi tang 20 stamina
        ++staminaCounterIncrea;
        if(staminaCounterIncrea > 120) {
            if(stamina <= maxStamina - 10) {
                stamina += 10;
            }
            else {
                stamina = maxStamina;
            }
            staminaCounterIncrea = 0;
        }
    }

    public void updateState() {
        if(exp >= nextLevelExp) {
            ++level;
            exp -= nextLevelExp;
        }
        strength = level * 2;
        dexterity = level * 2;
        nextLevelExp = level * 5;
        attack = getPlayerAttack();
        defense = getPlayerDefense();
    }
    public void attackProcess() {
        ++spriteCounter;
        if(spriteCounter < 5) {
            spriteNum = 1;
        }
        if(spriteCounter >= 5 && spriteCounter <= 15) {
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
            // Check vung attack cham vao monster hay khong
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            damageMonster(monsterIndex);

            // Khoi phuc cac stat
            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;

        }
        if(spriteCounter > 15) {
            spriteNum = 1;
            spriteCounter = 0;
            attackMode = false;
        }
    }
    public void damageMonster(int i) {
        if(i != 999) {
            if(gp.monster[i].immortalState == false) {
                gp.monster[i].life -= attack * 10;
                gp.monster[i].immortalState = true;
                if(gp.monster[i].life <= 0) {
                    gp.monster[i].dying = true;
                }
            }
        }

    }
    public void pickUpObject(int index) {
        if(index != 999) {
            gp.ui.showMessage("Ban da nhat duoc vat pham: " + gp.obj[index].name);
            gp.obj[index] = null;
        }
    }

    public void interactNPC(int index) {
        if(index != 999) {
            if(keyH.enterPressed == true) {
                gp.gameState = gp.dialogueState;
                gp.npc[index].speak();
            }
        }

    }

    public void soloMonster(int index) {
        if(index != 999) {
            if(gp.getPlayer().immortalState == false) {
                gp.getPlayer().life -= 10;
                gp.getPlayer().immortalState = true;
            }
        }
    }

    public void draw(Graphics2D g2, int tileSize) {
        BufferedImage image = null; // Khoi tao anh ban dau = null
        int tmpScreenX = screenX;
        int tmpScreenY = screenY;
        int tmpWidth = 18 * 2;
        int tmpHeight = 28 * 2;
        // Draw attack
        if(attackMode == true) {
            switch (direction) {
                case "up": //Neu direction == "up" => image = up1
                    if (spriteNum == 1) {
                        image = attack_up1;
                    }
                    else if (spriteNum == 2) {
                        image = attack_up2;
                    }
                    tmpHeight += gp.getTileSize();
                    tmpScreenY -= gp.getTileSize();
                    break;
                case "down":
                    if (spriteNum == 1) {
                        image = attack_down1;
                    }
                    if (spriteNum == 2) {
                        image = attack_down2;
                    }
                    tmpHeight += gp.getTileSize();
                    break;
                case "left":
                    if (spriteNum == 1) {
                        image = attack_left1;
                    }
                    if (spriteNum == 2) {
                        image = attack_left2;
                    }
                    tmpWidth += gp.getTileSize();
                    tmpScreenX -= gp.getTileSize();
                    break;
                case "right":
                    if (spriteNum == 1) {
                        image = attack_right1;
                    }
                    if (spriteNum == 2) {
                        image = attack_right2;
                    }
                    tmpWidth += gp.getTileSize();
                    break;
            }

        }
        else {
//        g2.setColor(Color.white); // Set mau trang de ve
//        g2.fillRect(x, y, tileSize, tileSize);
            switch (direction) {
                case "up": //Neu direction == "up" => image = up1
                    if (spriteNum == 1) {
                        image = up1;
                    }
                    else if (spriteNum == 2) {
                        image = up2;
                    }
                    else if (spriteNum == 3) {
                        image = up3;
                    }
                    else if (spriteNum == 4) {
                        image = up4;
                    }
                    else if (spriteNum == 5) {
                        image = up5;
                    }
                    else if (spriteNum == 6) {
                        image = up6;
                    }

                    break;
                case "down":
                    if (spriteNum == 1) {
                        image = down1;
                    }
                    else if (spriteNum == 2) {
                        image = down2;
                    }
                    else if (spriteNum == 3) {
                        image = down3;
                    }
                    else if (spriteNum == 4) {
                        image = down4;
                    }
                    else if (spriteNum == 5) {
                        image = down5;
                    }
                    else if (spriteNum == 6) {
                        image = down6;
                    }
                    break;
                case "left":
                    if (spriteNum == 1) {
                        image = left1;
                    }
                    else if (spriteNum == 2) {
                        image = left2;
                    }
                    else if (spriteNum == 3) {
                        image = left3;
                    }
                    else if (spriteNum == 4) {
                        image = left4;
                    }
                    else if (spriteNum == 5) {
                        image = left5;
                    }
                    else if (spriteNum == 6) {
                        image = left6;
                    }
                    break;
                case "right":
                    if (spriteNum == 1) {
                        image = right1;
                    }
                    else if (spriteNum == 2) {
                        image = right2;
                    }
                    else if (spriteNum == 3) {
                        image = right3;
                    }
                    else if (spriteNum == 4) {
                        image = right4;
                    }
                    else if (spriteNum == 5) {
                        image = right5;
                    }
                    else if (spriteNum == 6) {
                        image = right6;
                    }
                    break;
            }
        }
        if (immortalState == true) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f)); // Lam mo 70% nhan vat khi o immortalState
        }
        g2.drawImage(image, tmpScreenX, tmpScreenY, tmpWidth, tmpHeight, null); // In image ra man hinh ( observe ??? )
    }

}