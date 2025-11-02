package objects;

import entity.Projectile;
import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class newSkill extends Projectile {
    GamePanel gp;
    public newSkill(GamePanel gp) {
        super(gp);
        this.gp = gp;
        speed = 5;
        maxLife = 80;
        life = maxLife;
        attack = 2;
        useCost = 1;
        knockBackPower = 0;
        aliveState = false;
        getFireballImage();
    }
    public void getFireballImage() {
        attack_up1 = getImage("/projectiles/newSkill/tile000");
        attack_up2 = getImage("/projectiles/newSkill/tile001");
        attack_up3 = getImage("/projectiles/newSkill/tile002");
        attack_up4 = getImage("/projectiles/newSkill/tile003");
        attack_up5 = getImage("/projectiles/newSkill/tile004");
        attack_up6 = getImage("/projectiles/newSkill/tile005");
        attack_up7 = getImage("/projectiles/newSkill/tile006");
        attack_up8 = getImage("/projectiles/newSkill/tile007");
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
            if(spriteNum == 1){
                image = attack_up1;
            }
            if(spriteNum == 2) {
                image = attack_up2;
            }
            if(spriteNum == 3){
                image = attack_up3;
            }
            if(spriteNum == 4){
                image = attack_up4;
            }
            if(spriteNum == 5){
                image = attack_up5;
            }
            if(spriteNum == 6){
                image = attack_up6;
            }
            if(spriteNum == 7){
                image = attack_up7;
            }
            if(spriteNum == 8){
                image = attack_up8;
            }
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
    public void update() {

        int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
        if(monsterIndex != 999) {
            gp.getPlayer().damageMonster(monsterIndex, knockBackPower);
            aliveState = false;
        }
        switch (direction) {
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
        --life;
        if(life <= 0) {
            aliveState = false;
        }
        spriteCounter++;
        if(spriteCounter > 30) {
            if(spriteNum < 8) {
                spriteNum++;
            }
            else if(spriteNum == 8) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }

}
