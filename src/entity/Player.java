package entity;

import main.GamePanel;
import main.KeyHandler;
import objects.newSkill;
import objects.object_fireball;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Player extends Entity {

    KeyHandler keyH;
    public final int screenX, screenY; // Toa do vi tri player duoc ve tren man hinh
    public int hpQAverage, manaQAverage;
    public boolean hpQC, manaQC;
    public int hpQCounter, manaQCounter;
    public boolean projectSkill;
    public int hasKey;
    public Player(GamePanel gp, KeyHandler keyH) {
        super(gp);
        this.keyH = keyH;
        // Can giua man hinh
        screenX = gp.getScreenWidth() / 2 - gp.getTileSize() / 2;
        screenY = gp.getScreenHeight() / 2 - gp.getTileSize() / 2;
        solidArea = new Rectangle(6, 20, 24, 28); // x, y la toa do goc tren ben trai cua hinh chu nhat trong object (o day la player)
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        attackArea.width = 24;
        attackArea.height = 30;
        items = new ArrayList<>();
        setDefaultValues(); // Dat chi so ban dau cho player
        getPlayerImage(); // Lay anh player
        getPlayerAttackImage();

        // Set chi so nhan vat ban dau
        level = 1;
        strength = 1;
        dexterity = 1;
        exp = 1;
        nextLevelExp = 5;
        coin = 100000;
        currentWeapon = null;
        currentShield = null;
        attack = getPlayerAttack();
        defense = getPlayerDefense();
        addItems();


    }
    public void addItems() {

    }
    public int getPlayerAttack() {
        if(currentWeapon != null)   return currentWeapon.stat + strength * 2; // Attack = chi so cua vu khi + suc manh
        return strength * level;

    }
    public int getPlayerDefense() {
        if(currentShield != null)   return currentShield.stat + dexterity * 2;
        return dexterity * level;
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
        attack_up1 = getImage("/player/attack/up/1");
        attack_up2 = getImage("/player/attack/up/2");
        attack_up3 = getImage("/player/attack/up/3");
        attack_up4 = getImage("/player/attack/up/4");
        attack_up5 = getImage("/player/attack/up/5");
        attack_up6 = getImage("/player/attack/up/6");
        attack_up7 = getImage("/player/attack/up/7");
        attack_up8 = getImage("/player/attack/up/8");
        attack_down1 = getImage("/player/attack/down/1");
        attack_down2 = getImage("/player/attack/down/2");
        attack_down3 = getImage("/player/attack/down/3");
        attack_down4 = getImage("/player/attack/down/4");
        attack_down5 = getImage("/player/attack/down/5");
        attack_down6 = getImage("/player/attack/down/6");
        attack_down7 = getImage("/player/attack/down/7");
        attack_down8 = getImage("/player/attack/down/8");
        attack_left1 = getImage("/player/attack/left/1");
        attack_left2 = getImage("/player/attack/left/2");
        attack_left3 = getImage("/player/attack/left/3");
        attack_left4 = getImage("/player/attack/left/4");
        attack_left5 = getImage("/player/attack/left/5");
        attack_left6 = getImage("/player/attack/left/6");
        attack_left7 = getImage("/player/attack/left/7");
        attack_left8 = getImage("/player/attack/left/8");
        attack_right1 = getImage("/player/attack/right/1");
        attack_right2 = getImage("/player/attack/right/2");
        attack_right3 = getImage("/player/attack/right/3");
        attack_right4 = getImage("/player/attack/right/4");
        attack_right5 = getImage("/player/attack/right/5");
        attack_right6 = getImage("/player/attack/right/6");
        attack_right7 = getImage("/player/attack/right/7");
        attack_right8 = getImage("/player/attack/right/8");

    }

    public void setDefaultValues() {
        hpQAverage = 1;
        manaQAverage = 1;
        // worldX va worldY la toa do vi tri cua player tren ban do the gioi
        worldX = gp.getTileSize() * 25;
        worldY = gp.getTileSize() * 16;
        defaultSpeed = 4;
        speed = defaultSpeed;
        direction = "down"; // Mac dinh player dang nhin xuong duoi
        maxLife = 100;
        life = maxLife;
        maxStamina = 100;
        stamina = maxStamina;
        immortalCounter = 0;
        immortalState = false;
        projectSkill = false;
        projectile = new object_fireball(gp);
        hpQC = false;
        manaQC = false;
        hpQCounter = 0;
        manaQCounter = 0;
        hasKey = 0;
    }

    public void update() {
        // Update status player
        updateState();
        // Check trang thai tan cong
        if(attackMode == true && currentWeapon != null) {
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
            gp.getcChecker().checkTile(this);
            // Kiem tra object
            int objIndex = gp.getcChecker().checkObject(this, true);
            pickUpObject(objIndex);

//            if(keyH.shiftPressed != true) {
                //Kiem tra NPC
                int npcIndex = gp.getcChecker().checkEntity(this, gp.getNpc());
                int monsterIndex = gp.getcChecker().checkEntity(this, gp.getMonster());
                soloMonster(monsterIndex);
                interactNPC(npcIndex);
//            }
            gp.geteHandler().checkEvent();
//            gp.checkGate = false;
//            gp.callWithShop = false;
            if (collisionOn == false && keyH.enterPressed == false) {

                switch (direction) {
                    case "up":
                        if(keyH.shiftPressed == true && stamina > 0) {
                            for(int i = 1; i <= 3; i++) {
                                worldY -= speed;
                                gp.getcChecker().checkEntity(this, gp.getNpc());
                                gp.getcChecker().checkEntity(this, gp.getMonster());
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
                                gp.getcChecker().checkEntity(this, gp.getNpc());
                                gp.getcChecker().checkEntity(this, gp.getMonster());
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
                                gp.getcChecker().checkEntity(this, gp.getNpc());
                                gp.getcChecker().checkEntity(this, gp.getMonster());
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
                                gp.getcChecker().checkEntity(this, gp.getNpc());
                                gp.getcChecker().checkEntity(this, gp.getMonster());
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

        if(keyH.shotKeyPressed == true && projectile.aliveState == false && projectSkill == true) {
            projectile.set(worldX, worldY, direction, true, this);
            gp.getProjectileList().add(projectile);


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
        if(hpQC == true) {
            ++hpQCounter;
            if(hpQCounter > 60) {
                if(life <= maxLife - 20) {
                    life += 20;
                }
                else {
                    life = maxLife;
                }
                gp.getUi().currentHpCd -= 10;
                hpQCounter = 0;
            }
            if(gp.getUi().currentHpCd <= 0) {
                hpQC = false;
            }
        }
        if(manaQC == true) {
            ++manaQCounter;
            if(manaQCounter > 60) {
                if(stamina <= maxStamina - 20) {
                    stamina += 20;
                }
                else {
                    stamina = maxStamina;
                }
                gp.getUi().currentManaCd -= 10;
                manaQCounter = 0;
            }
            if(gp.getUi().currentManaCd <= 0) {
                manaQC = false;
            }
        }
    }
    public void updateState() {
        if(exp >= nextLevelExp) {
            ++level;
            exp -= nextLevelExp;
            maxLife += 100;
            life = maxLife;
            maxStamina += 100;
            stamina = maxStamina;
            strength += 1;
            dexterity += 1;
            gp.getUi().addMessage("Level Up!");
        }
        nextLevelExp = level * 5;
        attack = getPlayerAttack();
        defense = getPlayerDefense();
    }
    public void attackProcess() {
        ++spriteCounter;
        if(spriteCounter <= 3) {
            spriteNum = 1;
        }
        else if(spriteCounter <= 6) {
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
            int monsterIndex = gp.getcChecker().checkEntity(this, gp.getMonster());
            damageMonster(monsterIndex, currentWeapon.knockBackPower);

            // Khoi phuc cac stat
            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;

        }
        else if(spriteCounter <= 9) {
            spriteNum = 3;
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
            int monsterIndex = gp.getcChecker().checkEntity(this, gp.getMonster());
            damageMonster(monsterIndex, currentWeapon.knockBackPower);

            // Khoi phuc cac stat
            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;

        }
        else if(spriteCounter <= 12) {
            spriteNum = 4;
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
            int monsterIndex = gp.getcChecker().checkEntity(this, gp.getMonster());
            damageMonster(monsterIndex, currentWeapon.knockBackPower);

            // Khoi phuc cac stat
            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;

        }
        else if(spriteCounter <= 15) {
            spriteNum = 5;
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
            int monsterIndex = gp.getcChecker().checkEntity(this, gp.getMonster());
            damageMonster(monsterIndex, currentWeapon.knockBackPower);

            // Khoi phuc cac stat
            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;

        }
        else if(spriteCounter <= 18) {
            spriteNum = 6;
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
            int monsterIndex = gp.getcChecker().checkEntity(this, gp.getMonster());
            damageMonster(monsterIndex, currentWeapon.knockBackPower);

            // Khoi phuc cac stat
            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;

        }
        else if(spriteCounter <= 21) {
            spriteNum = 7;
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
            int monsterIndex = gp.getcChecker().checkEntity(this, gp.getMonster());
            damageMonster(monsterIndex, currentWeapon.knockBackPower);

            // Khoi phuc cac stat
            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;

        }
        else if(spriteCounter <= 24) {
            spriteNum = 8;
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
            int monsterIndex = gp.getcChecker().checkEntity(this, gp.getMonster());
            damageMonster(monsterIndex, currentWeapon.knockBackPower);

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
    public void damageMonster(int i, int knockBackPower) {
        if(i != 999) {
            if(gp.getMonster()[i].immortalState == false) {
                gp.getMonster()[i].drawHP = true;
                gp.getMonster()[i].drawHPCounter = 0;
                if(knockBackPower > 0)  knockBack(gp.getMonster()[i], knockBackPower);
                int damage = attack * 10 - gp.getMonster()[i].defense;
                if(damage <= 0) damage = 1;
                gp.getMonster()[i].life -= damage;
                gp.getUi().addMessage(damage + " damage!");
                gp.getMonster()[i].immortalState = true;
                if(gp.getMonster()[i].monsterFlag == false)   gp.getMonster()[i].monsterFlag = true;
                if(gp.getMonster()[i].life <= 0) {
                    gp.getMonster()[i].dying = true;
                }
            }
        }

    }
    public void pickUpObject(int index) {
        if(index != 999) {
            gp.getUi().addMessage("You have picked up the item: " + gp.getObj()[index].name);
            gp.getPlayer().items.add(gp.getObj()[index]);
            gp.getObj()[index] = null;

        }
    }

    public void interactNPC(int index) {
        if(index != 999) {
            if(keyH.enterPressed == true) {
                gp.setGameState(gp.getDialogueState());
                gp.getNpc()[index].speak();
                if(gp.getNpc()[index].name.equals("shop")){
                    gp.setCallWithShop(true);
                }
                keyH.enterPressed = false;
            }
        }

    }
    public void knockBack(Entity entity, int knockBackPower) {
        entity.direction = direction;
        entity.speed += knockBackPower;
        entity.knockBack = true;
    }
    public void soloMonster(int index) {
//        if(index != 999) {
//            if(gp.getPlayer().immortalState == false) {
//                gp.getPlayer().life -= 10;
//                gp.ui.addMessage("-10 HP");
//                gp.getPlayer().immortalState = true;
//            }
//        }
    }

    public void draw(Graphics2D g2, int tileSize) {
        BufferedImage image = null; // Khoi tao anh ban dau = null
        int tmpScreenX = screenX;
        int tmpScreenY = screenY;
        int tmpWidth = 18 * 2;
        int tmpHeight = 24 * 2;
        // Draw attack
        if(attackMode == true && currentWeapon != null) {
            switch (direction) {
                case "up": //Neu direction == "up" => image = up1
                    if (spriteNum == 1) {
                        image = attack_up1;
                        tmpWidth += 8;
                        tmpHeight += 2;
                        tmpScreenX -= 10;
                        tmpScreenY -= 2;
                    }
                    else if (spriteNum == 2) {
                        image = attack_up2;
                        tmpWidth += 8;
                        tmpHeight += 0;
                        tmpScreenX -= 10;
                        tmpScreenY -= 0;
                    }
                    else if (spriteNum == 3) {
                        image = attack_up3;
                        tmpWidth += 8;
                        tmpHeight += 2;
                        tmpScreenX -= 10;
                        tmpScreenY -= 2;
                    }
                    else if (spriteNum == 4) {
                        image = attack_up4;
                        tmpWidth += 16;
                        tmpHeight -= 2;
                        tmpScreenX -= 14;
                        tmpScreenY += 2;
                    }
                    else if (spriteNum == 5) {
                        image = attack_up5;
                        tmpWidth += 32;
                        tmpHeight += 2;
                        tmpScreenX -= 22;
                        tmpScreenY -= 2;
                    }
                    else if (spriteNum == 6) {
                        image = attack_up6;
                        tmpWidth += 14;
                        tmpHeight += 14;
                        tmpScreenX -= 12;
                        tmpScreenY -= 14;
                    }
                    else if (spriteNum == 7) {
                        image = attack_up7;
                        tmpWidth += 24;
                        tmpHeight += 14;
                        tmpScreenX -= 20;
                        tmpScreenY -= 14;
                    }
                    else if (spriteNum == 8) {
                        image = attack_up8;
                        tmpWidth += 6;
                        tmpHeight -= 2;
                        tmpScreenX -= 8;
                        tmpScreenY += 2;
                    }
                    break;
                case "down":
                    if (spriteNum == 1) {
                        image = attack_down1;
                        tmpWidth += 2;
                        tmpHeight += 2;
                        tmpScreenX -= 11;
                    }
                    else if (spriteNum == 2) {
                        image = attack_down2;
                        tmpWidth += 12;
                        tmpHeight += 0;
                        tmpScreenX -= 14;
                    }
                    else if (spriteNum == 3) {
                        image = attack_down3;
                        tmpWidth += 12;
                        tmpHeight += 2;
                        tmpScreenX -= 14;
                    }
                    else if (spriteNum == 4) {
                        image = attack_down4;
                        tmpWidth += 14;
                        tmpHeight += 8;
                        tmpScreenX -= 15;
                    }
                    else if (spriteNum == 5) {
                        image = attack_down5;
                        tmpWidth += 32;
                        tmpHeight += 14;
                        tmpScreenX -= 24;
                    }
                    else if (spriteNum == 6) {
                        image = attack_down6;
                        tmpWidth += 30;
                        tmpHeight += 22;
                        tmpScreenX -= 23;
                    }
                    else if (spriteNum == 7) {
                        image = attack_down7;
                        tmpWidth += 24;
                        tmpHeight += 22;
                        tmpScreenX -= 20;
                    }
                    else if (spriteNum == 8) {
                        image = attack_down8;
                        tmpWidth += 6;
                        tmpHeight += 2;
                        tmpScreenX -= 11;
                    }
                    break;
                case "left":
                    if (spriteNum == 1) {
                        image = attack_left1;
                        tmpWidth += 8;

                        tmpScreenX -= 8;
                    }
                    else if (spriteNum == 2) {
                        image = attack_left2;
                        tmpWidth += 4;

                        tmpScreenX -= 4;
                    }
                    else if (spriteNum == 3) {
                        image = attack_left3;
                        tmpWidth += 0;
                        tmpScreenX -= 0;
                    }
                    else if (spriteNum == 4) {
                        image = attack_left4;
                        tmpWidth += 4;
                        tmpHeight += 4;
                        tmpScreenX -= 4;
                    }
                    else if (spriteNum == 5) {
                        image = attack_left5;
                        tmpWidth += 20;
                        tmpHeight += 4;
                        tmpScreenX -= 20;
                    }
                    else if (spriteNum == 6) {
                        image = attack_left6;
                        tmpWidth += 22;
                        tmpHeight += 6;
                        tmpScreenX -= 22;
                    }
                    else if (spriteNum == 7) {
                        image = attack_left7;
                        tmpWidth += 22;
                        tmpHeight += 6;
                        tmpScreenX -= 22;
                    }
                    else if (spriteNum == 8) {
                        image = attack_left8;
                        tmpWidth += 0;
                        tmpHeight += 6;
                        tmpScreenX -= 0;
                    }

                    break;
                case "right":
                    if (spriteNum == 1) {
                        image = attack_right1;
                        tmpWidth += 0;
                    }
                    else if (spriteNum == 2) {
                        image = attack_right2;
                        tmpWidth += 6;
                    }
                    else if (spriteNum == 3) {
                        image = attack_right3;
                        tmpWidth += 12;
                    }
                    else if (spriteNum == 4) {
                        image = attack_right4;
                        tmpWidth += 18;
                        tmpHeight += 4;
                    }
                    else if (spriteNum == 5) {
                        image = attack_right5;
                        tmpWidth += 24;
                        tmpHeight += 4;
                    }
                    else if (spriteNum == 6) {
                        image = attack_right6;
                        tmpWidth += 18;
                        tmpHeight += 6;
                    }
                    else if (spriteNum == 7) {
                        image = attack_right7;
                        tmpWidth += 18;
                        tmpHeight += 6;
                    }
                    else if (spriteNum == 8) {
                        image = attack_right8;
                    }

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
                    tmpScreenX -= 12;
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
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f)); // Reset lai de cac component khac khong bi lam mo theo
    }

}