package entity;

import main.GamePanel;
import objects.SuperObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Entity {
    GamePanel gp;
    public int worldX, worldY; // Toa do (x, y)
    public int speed; // Toc do di chuyen

    public BufferedImage up1, up2, up3, up4, up5, up6,
            down1, down2, down3, down4, down5, down6,
            left1, left2, left3, left4, left5, left6,
            right1, right2, right3, right4, right5, right6; // BufferedImage la lop luu tru du lieu hinh anh
    public BufferedImage attack_up1, attack_up2, attack_up3, attack_up4, attack_up5, attack_up6, attack_up7, attack_up8,
    attack_down1, attack_down2, attack_down3, attack_down4, attack_down5, attack_down6, attack_down7, attack_down8,
    attack_left1, attack_left2, attack_left3, attack_left4, attack_left5, attack_left6, attack_left7, attack_left8,
    attack_right1, attack_right2, attack_right3, attack_right4, attack_right5, attack_right6, attack_right7, attack_right8;
    public String direction;
    public boolean colisPlayer = false;
    public boolean aliveState = true;
    public int aliveCounter = 0;

    public int spriteCounter = 0;
    public int spriteNum = 1;

    public Rectangle solidArea = new Rectangle(6, 12, 36, 36);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;

    public int actionLockCounter = 0;
    public String[] dialogues = new String[20];

    public String name;

    public ArrayList<SuperObject> items;

    public int maxLife;
    public int life;
    public int maxStamina;
    public int stamina;
    public int staminaCounterIncrea = 0;
    public int staminaCounterDecrea = 0;
    public int knockBackCounter = 0;
    public boolean immortalState = false;
    public boolean dying = false;
    public int immortalCounter = 0;
    public boolean knockBack = false;
    public int knockBackPower = 0;
    public boolean drawHP = false;
    public int drawHPCounter = 0;
    public boolean attackMode = false;
    public int expWhenKill;
    public int coinWanted;
    public int monsterNum = 0;
    public Rectangle attackArea = new Rectangle(0, 0, 0, 0);
    // Chi so nhan vat
    public int defaultSpeed;
    public int level;
    public int strength;
    public int dexterity;
    public int attack;
    public int defense;
    public int exp;
    public int nextLevelExp;
    public int coin;
    public SuperObject currentWeapon; // Vu khi hien tai
    public SuperObject currentShield;
    public Projectile projectile;
    public int useCost;
    public boolean monsterFlag = false; // Khi bat co thi update quai onPath = true de tim duong di
    public boolean onPath = false;

    public Entity(GamePanel gp) {
        this.gp = gp;
    }

    public BufferedImage getImage(String imageName) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(getClass().getResourceAsStream(imageName + ".png"));
        }catch(IOException e){
            e.printStackTrace();
        }
        return image;
    }

    public void draw(Graphics2D g2, GamePanel gp) {
        BufferedImage image = null;
        int screenX = worldX - gp.getPlayer().worldX + gp.getPlayer().screenX;
        // player.worldX la toa do X cua player trong map world, player.screenX la toa do vi tri nguoi choi hien thi tren man hinh (o giua map)
        // screenX la toa do x hien thi tren man hinh
        // Toa do hien thi tren man hinh = toa do trong world - toa do player trong world + toa do player tren man hinh
        int screenY = worldY - gp.getPlayer().worldY + gp.getPlayer().screenY;
        // Tuong tu screenX
        if(worldX + gp.getTileSize() > gp.getPlayer().worldX - gp.getPlayer().screenX &&
                worldX - gp.getTileSize() < gp.getPlayer().worldX + gp.getPlayer().screenX &&
                worldY + gp.getTileSize() > gp.getPlayer().worldY - gp.getPlayer().screenY &&
                worldY - gp.getTileSize() < gp.getPlayer().worldY + gp.getPlayer().screenY) {
            switch(direction) {
                case "up": //Neu direction == "up" => image = up1
                    if(spriteNum == 1){
                        image = up1;
                    }
                    if(spriteNum == 2) {
                        image = up2;
                    }
                    break;
                case "down":
                    if(spriteNum == 1){
                        image = down1;
                    }
                    if(spriteNum == 2) {
                        image = down2;
                    }
                    break;
                case "left":
                    if(spriteNum == 1){
                        image = left1;
                    }
                    if(spriteNum == 2) {
                        image = left2;
                    }

                    break;
                case "right":
                    if(spriteNum == 1){
                        image = right1;
                    }
                    if(spriteNum == 2) {
                        image = right2;
                    }
                    break;
            }
            if(immortalState == true) {
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
            }
            if(dying == true) {
                dyingAnimation(g2);
            }
            g2.drawImage(image, screenX, screenY, gp.getTileSize(), gp.getTileSize(), null); // Ve component map
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f)); // sau khi ve thi reset ve do trong suot ve 0
        } // Chi khi toa do world o trong khu vuc man hinh co the hien thi thi moi in ra
    }

    public void dyingAnimation(Graphics2D g2) {
        ++aliveCounter;
        if(aliveCounter < 5) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));

        }
        else if(aliveCounter < 10) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        }
        else if(aliveCounter < 15) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        }
        else if(aliveCounter < 20) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        }
        else if(aliveCounter < 25) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        }
        else if(aliveCounter < 30) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        }
        else if(aliveCounter < 35) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        }
        else if(aliveCounter < 40) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        }
        else {
            dying = false;
            aliveState = false;
            aliveCounter = 0;
        }
    }
    public void setAction() {

    }
    public void checkCollision() {
        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkPlayer(this);
        int i_mon = gp.cChecker.checkEntity(this, gp.monster);
        int i_npc = gp.cChecker.checkEntity(this, gp.npc);
    }
    public void update() {

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
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
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

    public void speak() {
        gp.ui.currentDialogue = dialogues[0];
    }
    public void checkDrop() {
//        int rd = new Random().nextInt(100) + 1;
//        if(rd <= 25) {
//            dropItems(new object_coin());
//        }
//        else if(rd <= 50) {
//            dropItems(new object_sword());
//        }
//        else {
//            dropItems(new object_shield());
//        }

    }
    public void dropItems(SuperObject item) {
//        for(int i = 0; i < gp.obj.length; i++) {
//            if(gp.obj[i] == null) {
//                gp.obj[i] = item;
//                gp.obj[i].worldX = worldX;
//                gp.obj[i].worldY = worldY;
//                break;
//            }
//        }
    }
    public void searchPath(int goalCol, int goalRow) {
        int startCol = (worldX + solidArea.x) / gp.getTileSize();
        int startRow = (worldY + solidArea.y) / gp.getTileSize();
        gp.pFinder.setNodes(startCol, startRow, goalCol, goalRow, this);
        if(gp.pFinder.search() == true) {
            int nextX = gp.pFinder.pathList.get(0).col * gp.getTileSize();
            int nextY = gp.pFinder.pathList.get(0).row * gp.getTileSize();
            int enLeftX = worldX + solidArea.x;
            int enRightX = worldX + solidArea.x + solidArea.width;
            int enTopY = worldY + solidArea.y;
            int enBottomY = worldY + solidArea.y + solidArea.height;

            if(enTopY > nextY && enLeftX >= nextX && enRightX < nextX + gp.getTileSize()) {
                direction = "up";
            }
            else if(enTopY < nextY && enLeftX >= nextX && enRightX < nextX + gp.getTileSize()) {
                direction = "down";
            }
            else if(enTopY >= nextY && enBottomY < nextY + gp.getTileSize()) {
                if(enLeftX > nextX) {
                    direction = "left";
                }
                if(enLeftX < nextX) {
                    direction = "right";
                }
            }
            else if(enTopY > nextY && enLeftX > nextX) {
                direction = "up";
                checkCollision();
                if(collisionOn == true) {
                    direction = "left";
                }

            }
            else if(enTopY > nextY && enLeftX < nextX) {
                direction = "up";
                checkCollision();
                if(collisionOn == true) {
                    direction = "right";
                }
            }
            else if(enTopY < nextY && enLeftX > nextX) {
                direction = "down";
                checkCollision();
                if(collisionOn == true) {
                    direction = "left";
                }
            }
            else if(enTopY < nextY && enLeftX < nextX) {
                direction = "down";
                checkCollision();
                if(collisionOn == true) {
                    direction = "right";
                }
            }
            int nextCol = gp.pFinder.pathList.get(0).col;
            int nextRow = gp.pFinder.pathList.get(0).row;
            if(nextCol == goalCol && nextRow == goalRow) {
                onPath = false;
            }
        }

    }
}
