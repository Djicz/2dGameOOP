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
        if(hit(27, 16, "right"))    damagePit(gp.dialogueState);
        if(hit(23, 12, "up"))   healHeart(gp.dialogueState);
        if(hit(12, 9, "any"))  mapTele(gp.gameContinue);
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
//        gp.ui.currentDialogue = "Ban co muon chac dich chuyen toi me cung khong?";
//        int x = gp.getTileSize() * 2;
//        int y = gp.getTileSize() / 2;
//        int width = gp.getTileSize();
//        int height = gp.getTileSize() / 2;
//
        gp.tileM.loadMap("/maps/dungeon.txt");
        gp.getPlayer().worldX = 8 * gp.getTileSize();
        gp.getPlayer().worldY = 7 * gp.getTileSize();
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
