package objects;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObject {
    public BufferedImage image, image2, image3;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;
    public int stat;
    public int type;
    public static final int weaponItems = 1;
    public static final int potionItems = 2;
    public static final int keyItems = 3;
    public static final int skillItems = 4;
    public String information;
    public int knockBackPower = 0;
    public void draw(Graphics2D g2, GamePanel gp) {
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
            g2.drawImage(image, screenX, screenY, gp.getTileSize(), gp.getTileSize(), null); // Ve component map
        } // Chi khi toa do world o trong khu vuc man hinh co the hien thi thi moi in ra
    }

}
