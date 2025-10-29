package main;

import objects.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

// class giao dien nguoi dung (in ra cac chi so phu tren man hinh)
public class UI {

    GamePanel gp;
    public Font arial;
    public Font maruMonica;
    public boolean messageOn = false;
    public String message = "";
    public int messageCounter = 0;
    public int titleIn = 1;
    public int slotNumX = 0;
    public int slotNumY = 0;
    public int cdHpMax = 100;
    public int currentHpCd = cdHpMax;
    public int cdManaMax = 100;
    public int currentManaCd = cdManaMax;
    public int reTryCounter = 1;
    public int delayTime = 10;
    public void showMessage(String text) {
        g2.setFont(maruMonica.deriveFont(Font.PLAIN, 15));
        message = text;
        messageOn = true;
    }
    public Graphics2D g2;
    public String currentDialogue = "";
    public UI(GamePanel gp) {
        this.gp = gp;
        arial = new Font("Arial", Font.PLAIN, 20); // font chu, kieu chu (nghieng, thang, in dam...), co chu
        try {
            InputStream is = getClass().getResourceAsStream("/font/MaruMonica.ttf");
            maruMonica = Font.createFont(Font.TRUETYPE_FONT, is);
        }catch(FontFormatException e) {
            e.printStackTrace();
        }catch(IOException e) {
            e.printStackTrace();
        }
//        maruMonica = maruMonica.deriveFont(Font.BOLD, 20f);
    }
    // In ra man hinh
    public void draw(Graphics2D g2) {
        this.g2 = g2;
        if(gp.gameState == gp.titleState) {
            drawTitleScreen();
            gp.callWithShop = false;
            gp.checkGate = false;
        }
        if(gp.gameState == gp.gameContinue) {
            drawSpItems();
//            drawPlayerLife();
            drawHpAndStamina();
            if(messageOn == true) {
                g2.setFont(arial);
                g2.setColor(Color.WHITE);
                g2.drawString(message, gp.getTileSize() / 2, gp.getTileSize() * 6);
            }
            drawPosition();
            if(gp.getPlayer().life <= 0) {
                gp.gameState = gp.gameOverState;
            }
        }
        if(gp.gameState == gp.gamePause) {
            drawPause(g2);
        }
        if(gp.gameState == gp.dialogueState) {
//            drawPlayerLife();
            drawHpAndStamina();
            drawDialogueScreen();
            if(gp.callWithShop == true && gp.keyHandler.enterPressed == true) {
                slotNumX = 0;
                slotNumY = 0;
                gp.gameState = gp.shopState;
                gp.callWithShop = false;
                gp.keyHandler.enterPressed = false;
            }
            if(gp.checkGate == true && gp.keyHandler.enterPressed == true) {
                gp.gameState = gp.gameContinue;
                gp.tileM.loadMap(gp.tmpMap);
                gp.teleSet = true;
                if(gp.currentMap == gp.map_1 && gp.tmpMap == gp.map_2) {
                    gp.getPlayer().worldX = gp.getTileSize() * 3;
                    gp.getPlayer().worldY = gp.getTileSize() * 3;
                }
                if(gp.currentMap == gp.map_2 && gp.tmpMap == gp.map_1) {
                    gp.getPlayer().worldX = gp.getTileSize() * 40;
                    gp.getPlayer().worldY = gp.getTileSize() * 35;
                }
                if(gp.currentMap == gp.map_2 && gp.tmpMap == gp.map_3) {
                    gp.getPlayer().worldX = gp.getTileSize() * 20;
                    gp.getPlayer().worldY = gp.getTileSize() * 2;
                }
                if(gp.currentMap == gp.map_3 && gp.tmpMap == gp.map_2) {
                    gp.getPlayer().worldX = gp.getTileSize() * 46;
                    gp.getPlayer().worldY = gp.getTileSize() * 46;
                }
                if(gp.currentMap == gp.map_3 && gp.tmpMap == gp.map_1) {
                    gp.getPlayer().worldX = gp.getTileSize() * 3;
                    gp.getPlayer().worldY = gp.getTileSize() * 17;
                }
                if(gp.currentMap == gp.map_1 && gp.tmpMap == gp.map_3) {
                    gp.getPlayer().worldX = gp.getTileSize() * 28;
                    gp.getPlayer().worldY = gp.getTileSize() * 31;
                }
                if(gp.currentMap == gp.map_1 && gp.tmpMap == gp.map_4) {
                    gp.getPlayer().worldX = gp.getTileSize() * 2;
                    gp.getPlayer().worldY = gp.getTileSize() * 2;
                }
                if(gp.currentMap == gp.map_4 && gp.tmpMap == gp.map_1) {
                    gp.getPlayer().worldX = gp.getTileSize() * 43;
                    gp.getPlayer().worldY = gp.getTileSize() * 5;
                }
                if(gp.currentMap == gp.map_4 && gp.tmpMap == gp.map_5) {
                    gp.getPlayer().worldX = gp.getTileSize() * 24;
                    gp.getPlayer().worldY = gp.getTileSize() * 1;
                }
                if(gp.currentMap == gp.map_5 && gp.tmpMap == gp.map_4) {
                    gp.getPlayer().worldX = gp.getTileSize() * 31;
                    gp.getPlayer().worldY = gp.getTileSize() * 45;
                }
                if(gp.currentMap == gp.map_5 && gp.tmpMap == gp.map_6) {
                    gp.getPlayer().worldX = gp.getTileSize() * 1;
                    gp.getPlayer().worldY = gp.getTileSize() * 24;
                }
                if(gp.currentMap == gp.map_6 && gp.tmpMap == gp.map_5) {
                    gp.getPlayer().worldX = gp.getTileSize() * 24;
                    gp.getPlayer().worldY = gp.getTileSize() * 44;
                }
                if(gp.currentMap == gp.map_6 && gp.tmpMap == gp.map_7) {
                    gp.getPlayer().worldX = gp.getTileSize() * 24;
                    gp.getPlayer().worldY = gp.getTileSize() * 5;
                }
                if(gp.currentMap == gp.map_7 && gp.tmpMap == gp.map_6) {
                    gp.getPlayer().worldX = gp.getTileSize() * 47;
                    gp.getPlayer().worldY = gp.getTileSize() * 3;
                }
                if(gp.currentMap == gp.map_7 && gp.tmpMap == gp.map_8) {
                    gp.getPlayer().worldX = gp.getTileSize() * 1;
                    gp.getPlayer().worldY = gp.getTileSize() * 8;
                }
                if(gp.currentMap == gp.map_8 && gp.tmpMap == gp.map_7) {
                    gp.getPlayer().worldX = gp.getTileSize() * 48;
                    gp.getPlayer().worldY = gp.getTileSize() * 48;
                }
                if(gp.currentMap == gp.map_8 && gp.tmpMap == gp.map_9) {
                    gp.getPlayer().worldX = gp.getTileSize() * 48;
                    gp.getPlayer().worldY = gp.getTileSize() * 1;
                }
                if(gp.currentMap == gp.map_9 && gp.tmpMap == gp.map_8) {
                    gp.getPlayer().worldX = gp.getTileSize() * 48;
                    gp.getPlayer().worldY = gp.getTileSize() * 43;
                }
                if(gp.currentMap == gp.map_9 && gp.tmpMap == gp.map_10) {
                    gp.getPlayer().worldX = gp.getTileSize() * 1;
                    gp.getPlayer().worldY = gp.getTileSize() * 25;
                }
                if(gp.currentMap == gp.map_10 && gp.tmpMap == gp.map_9) {
                    gp.getPlayer().worldX = gp.getTileSize() * 48;
                    gp.getPlayer().worldY = gp.getTileSize() * 31;
                }
                gp.currentMap = gp.tmpMap;
                gp.setUpGame();

                gp.checkGate = false;
                gp.keyHandler.enterPressed = false;
            }
        }
        if(gp.gameState == gp.characterState) {

            drawCharacterStatus();
            drawInventory();
            if(gp.keyHandler.enterPressed == true) {
                int itemsNum = 5 * slotNumY + slotNumX;
                if (itemsNum < gp.getPlayer().items.size()) {
                    gp.keyHandler.enterPressed = false;
                    gp.gameState = gp.characterState_notify;
                }
            }
        }
        if(gp.gameState == gp.shopState) {
            drawShop();
            if(gp.keyHandler.escPressed == true) {
                gp.gameState = gp.gameContinue;
            }
            if(gp.keyHandler.enterPressed == true) {
                gp.keyHandler.enterPressed = false;
                int itemsNum = 7 * slotNumY + slotNumX;
                if(itemsNum < gp.npc[0].items.size()) {
                    gp.gameState = gp.shopState_notify;
                }
            }
        }
        if(gp.gameState == gp.shopState_notify) {
            drawShop();
            drawNotify();
            if(gp.keyHandler.enterPressed == true) {
                int itemsNum = 7 * slotNumY + slotNumX;
                if(itemsNum < gp.npc[0].items.size()) {
                    if(gp.npc[0].items.get(itemsNum).type == SuperObject.weaponItems) {
                        gp.getPlayer().items.add(gp.npc[0].items.get(itemsNum));
                        gp.npc[0].items.remove(itemsNum);
                    }
                    else if(gp.npc[0].items.get(itemsNum).name.equals("HP Potion")) {
                        gp.getPlayer().hpQAverage++;
                    }
                    else if(gp.npc[0].items.get(itemsNum).name.equals("Mana Potion")) {
                        gp.getPlayer().manaQAverage++;
                    }
                    else if(gp.npc[0].items.get(itemsNum).type == SuperObject.skillItems) {
                        gp.getPlayer().items.add(gp.npc[0].items.get(itemsNum));
                        gp.npc[0].items.remove(itemsNum);
                    }
                }
                gp.keyHandler.enterPressed = false;
                gp.gameState = gp.shopState;
            }
            if(gp.keyHandler.escPressed == true) {
                gp.keyHandler.escPressed = false;
                gp.gameState = gp.shopState;
            }
        }
        if(gp.gameState == gp.characterState_notify) {
            drawCharacterStatus();
            drawInventory();
            drawNotify();
            if(gp.keyHandler.enterPressed == true) {
                int itemsNum = 5 * slotNumY + slotNumX;
                if (itemsNum < gp.getPlayer().items.size()) {
                    if (gp.getPlayer().items.get(itemsNum).type == new SuperObject().weaponItems) {
                        SuperObject sword = gp.getPlayer().items.get(itemsNum);
                        if(gp.getPlayer().currentWeapon != null) {
                            gp.getPlayer().items.set(itemsNum, gp.getPlayer().currentWeapon);
                        }
                        else {
                            gp.getPlayer().items.remove(itemsNum);
                        }
                        gp.getPlayer().currentWeapon = sword;
                    }
                    else if(gp.getPlayer().items.get(itemsNum).type == new SuperObject().skillItems) {
                        gp.getPlayer().projectSkill = true;
                        gp.getPlayer().items.remove(itemsNum);
                    }
                }
                gp.keyHandler.enterPressed = false;
                gp.gameState = gp.characterState;
            }
            if(gp.keyHandler.escPressed == true) {
                gp.keyHandler.escPressed = false;
                gp.gameState = gp.characterState;
            }
        }
        if(gp.gameState == gp.gameOverState) {
            drawRetry();
            ++reTryCounter;
            if(reTryCounter > 60) {
                delayTime--;
                reTryCounter = 0;
            }
            if(delayTime <= 0) {
                gp.tileM.loadMap(gp.map_1);
                gp.getPlayer().worldX = gp.getTileSize() * 40;
                gp.getPlayer().worldY = gp.getTileSize() * 35;
                gp.currentMap = gp.map_1;
                gp.tmpMap = gp.map_1;
                gp.setUpGame();
                gp.getPlayer().life = gp.getPlayer().maxLife;
                gp.getPlayer().stamina = gp.getPlayer().maxStamina;
                gp.gameState = gp.gameContinue;

            }
        }
    }
    public void drawRetry() {
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        g2.setFont(maruMonica.deriveFont(Font.BOLD, 40));
        g2.setColor(Color.white);
        g2.drawString("YOU DIED!", getXInMiddleScreen("YOU DIED!"), gp.getTileSize() * 3);
        g2.setFont(maruMonica.deriveFont(Font.BOLD, 40));
        g2.drawString("" + delayTime, getXInMiddleScreen("" + delayTime), gp.getTileSize() * 7);
    }
    public void drawNotify() {
        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRoundRect(gp.getTileSize() * 5, gp.getTileSize() * 5, gp.getTileSize() * 6, gp.getTileSize() * 2, 10, 10);
        g2.setStroke(new BasicStroke(2));
        g2.drawRoundRect(gp.getTileSize() * 5, gp.getTileSize() * 5, gp.getTileSize() * 6, gp.getTileSize() * 2, 10, 10);
        g2.setFont(maruMonica.deriveFont(Font.PLAIN, 30));
        g2.setColor(Color.white);
        g2.drawString("Are you sure about that?", gp.getTileSize() * 5 + 15, gp.getTileSize() * 5 + 35);
        g2.setFont(maruMonica.deriveFont(Font.PLAIN, 10));
        g2.drawString("Press enter to confirm", gp.getTileSize() * 5 + 15, gp.getTileSize() * 6 + 11);
        g2.drawString("Press ESC to exit", gp.getTileSize() * 5 + 15, gp.getTileSize() * 6 + 35);

    }
    public void drawPosition() {
        g2.setFont(maruMonica.deriveFont(Font.BOLD, 20));
        g2.setColor(Color.white);
        g2.drawString("" + gp.getPlayer().worldX / gp.getTileSize() + " " + gp.getPlayer().worldY / gp.getTileSize(), gp.getTileSize(), gp.getTileSize() * 9);
    }
    public void drawSpItems() {
        int xTmp = gp.getTileSize() / 2;
        int yTmp = gp.getTileSize() * 11 - 10;
        int widthTmp = gp.getTileSize();
        int heightTmp = gp.getTileSize();
        g2.fillRoundRect(xTmp, yTmp, widthTmp, heightTmp, 10, 10);
        g2.fillRoundRect(xTmp + gp.getTileSize(), yTmp, widthTmp, heightTmp, 10, 10);
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(2));
        g2.drawRoundRect(xTmp, yTmp, widthTmp, heightTmp, 10, 10);
        g2.drawRoundRect(xTmp + gp.getTileSize(), yTmp, widthTmp, heightTmp, 10, 10);
        g2.drawImage(new hp_potion().image, xTmp + 5, yTmp + 5, widthTmp - 10, heightTmp - 10, null);
        g2.drawImage(new mana_potion().image, xTmp + 5 + gp.getTileSize(), yTmp + 5, widthTmp - 10, heightTmp - 10, null);
        Font press = new Font("Arial", Font.BOLD, 15);
        g2.setFont(press);
        g2.drawString("1", xTmp + gp.getTileSize() / 2 - 5, yTmp - 5);
        g2.drawString("2", xTmp + gp.getTileSize() / 2 - 5 + gp.getTileSize(), yTmp - 5);
        Font quantity = new Font("Arial", Font.PLAIN, 13);
        g2.setFont(quantity);

        g2.drawString("" + gp.getPlayer().hpQAverage, xTmp + 38, yTmp + 45);
        g2.drawString("" + gp.getPlayer().manaQAverage, xTmp + 38 + gp.getTileSize(), yTmp + 45);
        if(gp.getPlayer().hpQC == true) {
            Color cd = new Color(0, 0, 0, 150);
            g2.setColor(cd);
            int yCd = yTmp + gp.getTileSize() - (currentHpCd * gp.getTileSize()) / cdHpMax;
            int heightCd = heightTmp - (yCd - yTmp);
            g2.fillRoundRect(xTmp + 2, yCd + 2, widthTmp - 4, heightCd - 4, 0, 0);
        }
        if(gp.getPlayer().manaQC == true) {
            Color cd = new Color(0, 0, 0, 150);
            g2.setColor(cd);
            int yCd = yTmp + gp.getTileSize() - (currentManaCd * gp.getTileSize()) / cdManaMax;
            int heightCd = heightTmp - (yCd - yTmp);
            g2.fillRoundRect(xTmp + gp.getTileSize() + 2, yCd + 2, widthTmp - 4, heightCd - 4, 0, 0);
        }
    }
    public void drawShop() {
        int xTmp = gp.getTileSize();
        int yTmp = gp.getTileSize();
        int widthTmp = gp.getTileSize() * 8;
        int heightTmp = gp.getTileSize() * 10;
        Color c = new Color(0, 0, 0, 150);
        g2.setColor(c);
        g2.fillRoundRect(xTmp, yTmp, widthTmp, heightTmp, 35, 35);
        Font se = new Font("Arial", Font.BOLD, 25);
        g2.setFont(se);
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(5)); // Tao 1 vien co do day la 5
        g2.drawRoundRect(xTmp, yTmp, widthTmp, heightTmp, 25, 25);
        g2.drawString("SHOP", xTmp + gp.getTileSize() * 3 + 15, yTmp + gp.getTileSize());

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
        drawShopItems(xSlotStart, ySlotStart);

        // Draw item's information


        if(slotNumY * 7 + slotNumX < gp.npc[0].items.size()) {
            int xIF = gp.getTileSize() * 10 - gp.getTileSize() / 2;
            int yIF = gp.getTileSize() * 8;
            int widthIF = gp.getTileSize() * 6;
            int heightIF = gp.getTileSize() * 3;
            g2.setColor(Color.black);
            g2.fillRoundRect(xIF, yIF, widthIF, heightIF, 35, 35);
            g2.setStroke(new BasicStroke(3));
            g2.setColor(Color.white);
            g2.drawRoundRect(xIF, yIF, widthIF, heightIF, 35, 35);
            g2.setFont(maruMonica.deriveFont(Font.PLAIN, 20));
            g2.drawString("[" + gp.npc[0].items.get(slotNumY * 7 + slotNumX).name + "]", xIF + gp.getTileSize() / 4, yIF + gp.getTileSize() / 2);
            g2.drawString(gp.npc[0].items.get(slotNumY * 7 + slotNumX).information, xIF + gp.getTileSize() / 4, yIF + (gp.getTileSize() * 3) / 2);
        }
    }
    public void drawShopItems(int x, int y) {
        int xr = x, yr = y, itemCounter = 0;
        for(int i = 0; i < gp.npc[0].items.size(); i++) {
            g2.drawImage(gp.npc[0].items.get(i).image, xr + 5, yr + 5, gp.getTileSize(), gp.getTileSize(), null);
            if(itemCounter < 6) {
                itemCounter++;
                xr += gp.getTileSize();
            }
            else {
                xr = x;
                yr += gp.getTileSize();
                itemCounter = 0;
            }
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
        g2.drawString("INVENTORY", xTmp + gp.getTileSize() * 2 + 8, yTmp + gp.getTileSize());

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
                itemCounter++;
                xr += gp.getTileSize();
            }
            else {
                xr = x;
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

        g2.setFont(maruMonica.deriveFont(Font.PLAIN, 20));
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
        if(gp.getPlayer().currentWeapon != null)    g2.drawImage(gp.getPlayer().currentWeapon.image, xTmp + gp.getTileSize() * 2, yTmp - 20, gp.getTileSize() / 2, gp.getTileSize() / 2, null);
        yTmp += 40;
        g2.drawString("Shield: ", xTmp, yTmp);
        if(gp.getPlayer().currentShield != null)    g2.drawImage(gp.getPlayer().currentShield.image, xTmp + gp.getTileSize() * 2, yTmp - 20, gp.getTileSize() / 2, gp.getTileSize() / 2, null);
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
        Font r = maruMonica.deriveFont(Font.BOLD, 35f);
        g2.setFont(r);
        g2.setColor(Color.gray);
        int x = getXInMiddleScreen(text);
        int y = gp.getTileSize() * 3;
        x = getXInMiddleScreen(text);
        y += gp.getTileSize() * 6;
        g2.drawString(text, x, y);
        if(titleIn == 1) {
            g2.drawString(">", x - 40, y);
        }

        text = "Game Load";
        x = getXInMiddleScreen(text);
        y += gp.getTileSize();
        g2.drawString(text, x, y);
        if(titleIn == 2) {
            g2.drawString(">", x - 40, y);
        }

        text = "Exit";
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
        g2.setFont(maruMonica.deriveFont(Font.PLAIN, 25f));
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
        String text = "Game Paused"; // Van ban muon in ra
        g2.setFont(maruMonica.deriveFont(Font.BOLD, 40));
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
