package main;

import java.awt.*;

public class EventHandler {
    GamePanel gp;
    Rectangle eventRect;
    int eventRectDefaultX, eventRectDefaultY;
    public int loadMapCounter = 0;
    public EventHandler(GamePanel gp) {
        this.gp = gp;
        eventRect = new Rectangle();
        eventRect.x = 23;
        eventRect.y = 23;
        eventRect.width = 2;
        eventRect.height = 2;
        eventRectDefaultX = eventRect.x;
        eventRectDefaultY = eventRect.y;
    }
    public void checkEvent() {
//        if(hit(27, 16, "right"))    damagePit(gp.dialogueState);
//        if(hit(23, 12, "up"))   healHeart(gp.dialogueState);

        if(gp.currentMap.equals(gp.map_1) && hit(40, 35, "up") && gp.teleSet == false) {
            System.out.println(gp.checkGate);
            gp.tmpMap = gp.map_2;
            mapTele(gp.dialogueState);
        }
        if(gp.currentMap.equals(gp.map_2) && hit(3, 3, "up") && gp.teleSet == false) {
            System.out.println(gp.checkGate);
            gp.tmpMap = gp.map_1;
            mapTele(gp.dialogueState);
        }

        if(gp.currentMap.equals(gp.map_2) && hit(46, 46, "down") && gp.teleSet == false) {
            System.out.println(gp.checkGate);
            gp.tmpMap = gp.map_3;
            mapTele(gp.dialogueState);
        }
        if(gp.currentMap.equals(gp.map_3) && hit(20, 2, "up") && gp.teleSet == false) {
            System.out.println(gp.checkGate);
            gp.tmpMap = gp.map_2;
            mapTele(gp.dialogueState);
        }
        if(gp.currentMap.equals(gp.map_3) && hit(28, 31, "down") && gp.teleSet == false) {
            System.out.println(gp.checkGate);
            gp.tmpMap = gp.map_1;
            mapTele(gp.dialogueState);
        }
        if(gp.currentMap.equals(gp.map_1) && hit(3, 17, "up") && gp.teleSet == false) {
            System.out.println(gp.checkGate);
            gp.tmpMap = gp.map_3;
            mapTele(gp.dialogueState);
        }
        if(gp.currentMap.equals(gp.map_1) && hit(43, 5, "up") && gp.teleSet == false) {
            System.out.println(gp.checkGate);
            gp.tmpMap = gp.map_4;
            mapTele(gp.dialogueState);
        }
        if(gp.currentMap.equals(gp.map_4) && hit(2, 2, "up") && gp.teleSet == false) {
            System.out.println(gp.checkGate);
            gp.tmpMap = gp.map_1;
            mapTele(gp.dialogueState);
        }
        if(gp.currentMap.equals(gp.map_4) && hit(31, 45, "left") && gp.teleSet == false) {
            System.out.println(gp.checkGate);
            gp.tmpMap = gp.map_5;
            mapTele(gp.dialogueState);
        }
        if(gp.currentMap.equals(gp.map_5) && hit(24, 1, "up") && gp.teleSet == false) {
            System.out.println(gp.checkGate);
            gp.tmpMap = gp.map_4;
            mapTele(gp.dialogueState);
        }
        if(gp.currentMap.equals(gp.map_5) && hit(24, 44, "right") && gp.teleSet == false) {
            System.out.println(gp.checkGate);
            gp.tmpMap = gp.map_6;
            mapTele(gp.dialogueState);
        }
        if(gp.currentMap.equals(gp.map_6) && hit(1, 24, "left") && gp.teleSet == false) {
            System.out.println(gp.checkGate);
            gp.tmpMap = gp.map_5;
            mapTele(gp.dialogueState);
        }
        if(gp.currentMap.equals(gp.map_6) && hit(47, 3, "right") && gp.teleSet == false) {
            System.out.println(gp.checkGate);
            gp.tmpMap = gp.map_7;
            mapTele(gp.dialogueState);
        }
        if(gp.currentMap.equals(gp.map_7) && hit(24, 5, "up") && gp.teleSet == false) {
            System.out.println(gp.checkGate);
            gp.tmpMap = gp.map_6;
            mapTele(gp.dialogueState);
        }
        if(gp.currentMap.equals(gp.map_7) && hit(48, 48, "right") && gp.teleSet == false) {
            System.out.println(gp.checkGate);
            gp.tmpMap = gp.map_8;
            mapTele(gp.dialogueState);
        }
        if(gp.currentMap.equals(gp.map_8) && hit(1, 8, "left") && gp.teleSet == false) {
            System.out.println(gp.checkGate);
            gp.tmpMap = gp.map_7;
            mapTele(gp.dialogueState);
        }
        if(gp.currentMap.equals(gp.map_8) && hit(48, 43, "right") && gp.teleSet == false) {
            System.out.println(gp.checkGate);
            gp.tmpMap = gp.map_9;
            mapTele(gp.dialogueState);
        }
        if(gp.currentMap.equals(gp.map_9) && hit(48, 1, "up") && gp.teleSet == false) {
            System.out.println(gp.checkGate);
            gp.tmpMap = gp.map_8;
            mapTele(gp.dialogueState);
        }
        if(gp.currentMap.equals(gp.map_9) && hit(48, 31, "right") && gp.teleSet == false) {
            System.out.println(gp.checkGate);
            gp.tmpMap = gp.map_10;
            mapTele(gp.dialogueState);
        }
        if(gp.currentMap.equals(gp.map_10) && hit(1, 25, "left") && gp.teleSet == false) {
            System.out.println(gp.checkGate);
            gp.tmpMap = gp.map_9;
            mapTele(gp.dialogueState);
        }

    }
    public void healHeart(int gameState){
        if(gp.keyHandler.enterPressed == true) {
            gp.gameState = gameState;
            gp.ui.currentDialogue = "1 coc nuoc da'i vao mom";
            gp.getPlayer().life++;
        }
        gp.keyHandler.enterPressed = false;
    }
    public void damagePit(int gameState){
        gp.gameState = gameState;
        gp.ui.currentDialogue = "Dam trung bay roi thang ngu";
        gp.getPlayer().life--;
    }
    public void mapTele(int gameState) {
        gp.gameState = gameState;
        gp.ui.currentDialogue = "Ban co muon chac dich chuyen toi me cung khong?";
        gp.checkGate = true;
    }
    public boolean hit(int eventCol, int eventRow, String reqDirection) {
        boolean hit = false;
        gp.getPlayer().solidArea.x += gp.getPlayer().worldX;
        gp.getPlayer().solidArea.y += gp.getPlayer().worldY;
        eventRect.x += eventCol * gp.getTileSize();
        eventRect.y += eventRow * gp.getTileSize();

        if(gp.getPlayer().solidArea.intersects(eventRect)){
            if(gp.getPlayer().direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
                hit = true;
            }
        }
        gp.getPlayer().solidArea.x = gp.getPlayer().solidAreaDefaultX;
        gp.getPlayer().solidArea.y = gp.getPlayer().solidAreaDefaultY;
        eventRect.x = eventRectDefaultX;
        eventRect.y = eventRectDefaultY;
        return hit;
    }
}
