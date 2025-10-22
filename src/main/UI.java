package main;

import object.SuperObject;
import object.object_heart;
import object.object_sword;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

// class giao dien nguoi dung (in ra cac chi so phu tren man hinh)
public class UI {

    GamePanel gp;
    public Font arial;
    BufferedImage keyImage;
    BufferedImage heart_full, heart_half, heart_blank;
    public boolean messageOn = false;
    public String message = "";
    public int messageCounter = 0;
    public int titleIn = 1;
    public int slotNumX = 0;
    public int slotNumY = 0;
    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }
    public Graphics2D g2;
    public String currentDialogue = "";
    public UI(GamePanel gp) {
        this.gp = gp;
        arial = new Font("Arial", Font.PLAIN, 20); // font chu, kieu chu (nghieng, thang, in dam...), co chu
    }
    // In ra man hinh
    public void draw(Graphics2D g2) {
        this.g2 = g2;
        if(gp.gameState == gp.titleState) {
            drawTitleScreen();
        }
        if(gp.gameState == gp.gameContinue) {
//            drawPlayerLife();
            drawHpAndStamina();
            if(messageOn == true) {
                g2.setFont(arial);
                g2.setColor(Color.WHITE);
                g2.drawString(message, gp.getTileSize() / 2, gp.getTileSize() * 6);

            }
        }
        if(gp.gameState == gp.gamePause) {
            drawPause(g2);
        }
        if(gp.gameState == gp.dialogueState) {
//            drawPlayerLife();
            drawHpAndStamina();
            drawDialogueScreen();
        }
        if(gp.gameState == gp.characterState) {
            drawCharacterStatus();
            drawInventory();
        }
    }
    public void drawInventory() {
        int xTmp = gp.getTileSize() * 10;
        int yTmp = gp.getTileSize() * 2;
        int widthTmp = gp.getTileSize() * 6;
        int heightTmp = gp.getTileSize() * 8;
        Color c = new Color(0, 0, 0, 150);
        g2.setColor(c);
        g2.fillRoundRect(xTmp, yTmp, widthTmp, heightTmp, 35, 35);
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(5)); // Tao 1 vien co do day la 5
        g2.drawRoundRect(xTmp + 5, yTmp + 5, widthTmp - 10, heightTmp - 10, 25, 25);
        g2.drawString("INVENTORY", xTmp + gp.getTileSize() * 2 - 10, yTmp + gp.getTileSize());

        // Draw slot
        int xSlotStart = xTmp + 20;
        int ySlotStart = yTmp + gp.getTileSize() + 20;
        int widthSlot = gp.getTileSize();
        int heightSlot = gp.getTileSize();

        int xNSlot = xSlotStart + (widthSlot * slotNumX);
        int yNSlot = ySlotStart + (heightSlot * slotNumY);
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(xNSlot + 5, yNSlot + 5, widthSlot, heightSlot, 10, 10);
        drawItems(xSlotStart, ySlotStart);


    }
    public void drawItems(int x, int y) {
        int xr = x, yr = y, itemCounter = 0;
        for(int i = 0; i < gp.getPlayer().items.size(); i++) {
            g2.drawImage(gp.getPlayer().items.get(i).image, xr + 5, yr + 5, gp.getTileSize(), gp.getTileSize(), null);
            if(itemCounter < 4) {
                xr += gp.getTileSize();
            }
            else {
                yr += gp.getTileSize();
                itemCounter = 0;
            }
        }
    }
    public void drawCharacterStatus() {
        int xTmp = gp.getTileSize() / 2;
        int yTmp = gp.getTileSize() / 2;
        int widthTmp = gp.getTileSize() * 5;
        int heightTmp = gp.getTileSize() * 8;
        g2.fillRoundRect(xTmp, yTmp, widthTmp, heightTmp, 35, 35);
        Color c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5)); // Tao 1 vien co do day la 5
        g2.drawRoundRect(xTmp + 5, yTmp + 5, widthTmp - 10, heightTmp - 10, 25, 25);
        Font t = new Font("Arial", Font.PLAIN, 20);
        g2.setFont(t);
        xTmp += 25;
        yTmp += 40;
        g2.drawString("Level: " + gp.getPlayer().level, xTmp, yTmp);
        yTmp += 40;
        g2.drawString("Attack: " + gp.getPlayer().attack, xTmp, yTmp);
        yTmp += 40;
        g2.drawString("Defense: " + gp.getPlayer().defense, xTmp, yTmp);
        yTmp += 40;
        g2.drawString("Exp: " + gp.getPlayer().exp, xTmp, yTmp);
        yTmp += 40;
        g2.drawString("Next Level Exp: " + gp.getPlayer().nextLevelExp, xTmp, yTmp);
        yTmp += 40;
        g2.drawString("Coin: " + gp.getPlayer().coin, xTmp, yTmp);
        yTmp += 40;
        g2.drawString("Weapon: ", xTmp, yTmp);
        g2.drawImage(gp.getPlayer().currentWeapon.image, xTmp + gp.getTileSize() * 2, yTmp - 20, gp.getTileSize() / 2, gp.getTileSize() / 2, null);
        yTmp += 40;
        g2.drawString("Shield: ", xTmp, yTmp);
        g2.drawImage(gp.getPlayer().currentShield.image, xTmp + gp.getTileSize() * 2, yTmp - 20, gp.getTileSize() / 2, gp.getTileSize() / 2, null);
    }
    public void drawHpAndStamina() {
        int xTmp = gp.getTileSize() / 2;
        int yTmp = gp.getTileSize() / 2;
        int widthTmp = gp.getTileSize() * 4;
        int heightTmp = gp.getTileSize() / 3;

        // hp
        Color hp = new Color(0, 0, 0, 150);
        g2.fillRoundRect(xTmp, yTmp, widthTmp, heightTmp, 0, 0);
        g2.setColor(hp);
        g2.setStroke(new BasicStroke(2)); // Tao 1 vien co do day la 5
        g2.drawRoundRect(xTmp, yTmp, widthTmp, heightTmp, 0, 0);
        int widthHP = (gp.getPlayer().life * widthTmp) / gp.getPlayer().maxLife;
        g2.setColor(Color.RED);
        g2.fillRect(xTmp, yTmp, widthHP, heightTmp);

        // mana
        Color mana = new Color(0, 0, 0, 150);
        g2.fillRoundRect(xTmp, yTmp + heightTmp + 4, widthTmp - 36, heightTmp - 15, 0, 0);
        g2.setColor(mana);
        g2.fillRoundRect(xTmp, yTmp + heightTmp + 4, widthTmp - 36, heightTmp - 15, 0, 0);
        g2.setStroke(new BasicStroke(2)); // Tao 1 vien co do day la 5
        g2.drawRoundRect(xTmp, yTmp + heightTmp + 4, widthTmp - 36, heightTmp - 15, 0, 0);
        int widthMana = (gp.getPlayer().stamina * (widthTmp -36)) / gp.getPlayer().maxStamina;
        g2.setColor(Color.blue);
        g2.fillRect(xTmp, yTmp + heightTmp + 4, widthMana, heightTmp - 15);
    }
    public void drawPlayerLife() {
        int x = gp.getTileSize() / 2;
        int y = gp.getTileSize() / 2;
        int i = 0;
        while(i < gp.getPlayer().maxLife / 2) {
            g2.drawImage(heart_blank, x, y, 40, 40, null);
            ++i;
            x += gp.getTileSize();
        }

        x = gp.getTileSize() / 2;
        y = gp.getTileSize() / 2;
        i = 0;
        while(i < gp.getPlayer().life) {
            g2.drawImage(heart_half, x, y, 40, 40, null);
            ++i;
            if(i < gp.getPlayer().life) {
                g2.drawImage(heart_full, x, y, 40, 40, null);
            }
            ++i;
            x += gp.getTileSize();
        }
    }
    public void drawTitleScreen() {
//        g2.setColor(new Color(33, 107, 168));
//        g2.fillRect(0, 0, gp.getScreenWidth(), gp.getScreenHeight());
//
//        Font nF = new Font("Arial", Font.BOLD, 40);
//        g2.setFont(nF);
//        String text = "5 phut nua bat dau lo tap the";
//        int x = getXInMiddleScreen(text);
//        int y = gp.getTileSize() * 3;
//        g2.setColor(Color.black);
//        g2.drawString(text, x + 5, y + 5);
//        g2.setColor(Color.white);
//        g2.drawString(text, x, y);
        BufferedImage titleImage = null;
        try {
            titleImage = ImageIO.read(getClass().getResourceAsStream("/tiles/title.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }


        g2.drawImage(titleImage, 0, 0, gp.getScreenWidth(), gp.getScreenHeight(), null);

        String text = "Game Start";
        Font r = new Font("Arial", Font.BOLD, 40);
        g2.setFont(r);
        g2.setColor(Color.WHITE);
        int x = getXInMiddleScreen(text);
        int y = gp.getTileSize() * 3;
        x = getXInMiddleScreen(text);
        y += gp.getTileSize() * 6;
        g2.drawString(text, x, y);
        if(titleIn == 1) {
            g2.drawString(">", x - 40, y);
        }

        text = "vai ca lon";
        x = getXInMiddleScreen(text);
        y += gp.getTileSize();
        g2.drawString(text, x, y);
        if(titleIn == 2) {
            g2.drawString(">", x - 40, y);
        }

        text = "cut";
        x = getXInMiddleScreen(text);
        y += gp.getTileSize();
        g2.drawString(text, x, y);
        if(titleIn == 3) {
            g2.drawString(">", x - 40, y);
        }
    }
    public void drawDialogueScreen() {
        // Ve cua so doi thoai
        int x = gp.getTileSize() * 2;
        int y = gp.getTileSize() / 2;
        int width = gp.getScreenWidth() - (gp.getTileSize() * 4);
        int height = gp.getTileSize() * 3;
        drawSubWindow(x, y, width, height);
        x += gp.getTileSize();
        y += gp.getTileSize();
        g2.setFont(arial);
        g2.drawString(currentDialogue, x, y);
    }
    public void drawSubWindow(int x, int y, int width, int height) {
        Color c = new Color(0, 0, 0, 150); // Tao mau RGB (0,0,0) la mau den, gia tri thu 4 la do trong suot cua khung thoai, cang nho thi khung thoai cang trong suot, max 255 tuc la khung thoai khong trong suot
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35); // arcWidth, arcHeight la do bo goc cua dialogue
        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5)); // Tao 1 vien co do day la 5
        g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);

    }

    public void drawPause(Graphics2D g2) {
        String text = "Em oi win roi day!"; // Van ban muon in ra
        Font pauseG = new Font("Arial", Font.BOLD, 40);
        g2.setFont(pauseG);
        g2.setColor(Color.white);
        int x = getXInMiddleScreen(text);
        int y = gp.getScreenHeight() / 2; // Can giua theo truc y
        g2.drawString(text, x, y); // Ve van ban
    }
    public int getXInMiddleScreen(String text) {
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth(); // Lay do dai van ban
        return (gp.getScreenWidth() / 2) - (length / 2); // Can deu van ban theo truc x
    }

}
