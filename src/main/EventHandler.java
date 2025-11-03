package main;

import java.awt.*;

public class EventHandler {
    GamePanel gp;
    Rectangle eventRect;
    int eventRectDefaultX, eventRectDefaultY;
    public int loadMapCounter = 0;
    public boolean loadMapMessage = false;
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

        if(gp.getCurrentMap().equals(gp.getMap_1()) && hit(40, 35, "up") && gp.getTeleSet() == false) {

//            System.out.println(gp.checkGate);
            if(gp.getPlayer().hasKey >= 1) {
                gp.setTmpMap(gp.getMap_2());
                mapTele(gp.getDialogueState());
            }
            else {
                if(loadMapMessage == false) {
                    gp.getUi().addMessage("You don't have key");
                    loadMapMessage = true;
                    loadMapCounter = 0;
                }
            }
        }
        if(gp.getCurrentMap().equals(gp.getMap_2()) && hit(3, 3, "up") && gp.getTeleSet() == false) {
//            System.out.println(gp.checkGate);
            gp.setTmpMap(gp.getMap_1());
            mapTele(gp.getDialogueState());
        }
        if(gp.getCurrentMap().equals(gp.getMap_2()) && hit(46, 46, "down") && gp.getTeleSet() == false) {
//            System.out.println(gp.checkGate);
            gp.setTmpMap(gp.getMap_3());
            mapTele(gp.getDialogueState());
        }
        if(gp.getCurrentMap().equals(gp.getMap_3()) && hit(20, 2, "up") && gp.getTeleSet() == false) {
//            System.out.println(gp.checkGate);
            gp.setTmpMap(gp.getMap_2());
            mapTele(gp.getDialogueState());
        }
        if(gp.getCurrentMap().equals(gp.getMap_3()) && hit(28, 31, "down") && gp.getTeleSet() == false) {
//            System.out.println(gp.checkGate);
            gp.setTmpMap(gp.getMap_1());
            mapTele(gp.getDialogueState());
        }
        if(gp.getCurrentMap().equals(gp.getMap_1()) && hit(3, 17, "up") && gp.getTeleSet() == false) {

//            System.out.println(gp.checkGate);
            if(gp.getPlayer().hasKey >= 1) {
                gp.setTmpMap(gp.getMap_3());
                mapTele(gp.getDialogueState());
            }
            else {
                if(loadMapMessage == false) {
                    gp.getUi().addMessage("You don't have key");
                    loadMapMessage = true;
                    loadMapCounter = 0;
                }
            }
        }
        if(gp.getCurrentMap().equals(gp.getMap_1()) && hit(43, 5, "up") && gp.getTeleSet() == false) {

//            System.out.println(gp.checkGate);
            if(gp.getPlayer().hasKey >= 1) {
                gp.setTmpMap(gp.getMap_4());
                mapTele(gp.getDialogueState());
            }
            else {
                if(loadMapMessage == false) {
                    gp.getUi().addMessage("You don't have key");
                    loadMapMessage = true;
                    loadMapCounter = 0;
                }
            }
        }
        if(gp.getCurrentMap().equals(gp.getMap_4()) && hit(2, 2, "up") && gp.getTeleSet() == false) {
//            System.out.println(gp.checkGate);
            gp.setTmpMap(gp.getMap_1());
            mapTele(gp.getDialogueState());
        }
        if(gp.getCurrentMap().equals(gp.getMap_4()) && hit(31, 45, "left") && gp.getTeleSet() == false) {
//            System.out.println(gp.checkGate);
            if(gp.getPlayer().hasKey >= 2) {
                gp.setTmpMap(gp.getMap_5());
                mapTele(gp.getDialogueState());
            }
            else {
                if(loadMapMessage == false) {
                    gp.getUi().addMessage("You don't have key");
                    loadMapMessage = true;
                    loadMapCounter = 0;
                }
            }
        }
        if(gp.getCurrentMap().equals(gp.getMap_5()) && hit(24, 1, "up") && gp.getTeleSet() == false) {
//            System.out.println(gp.checkGate);
            gp.setTmpMap(gp.getMap_4());
            mapTele(gp.getDialogueState());
        }
        if(gp.getCurrentMap().equals(gp.getMap_5()) && hit(24, 44, "right") && gp.getTeleSet() == false) {
//            System.out.println(gp.checkGate);
            if(gp.getPlayer().hasKey >= 3) {
                gp.setTmpMap(gp.getMap_6());
                mapTele(gp.getDialogueState());
            }
            else {
                if(loadMapMessage == false) {
                    gp.getUi().addMessage("You don't have key");
                    loadMapMessage = true;
                    loadMapCounter = 0;
                }
            }
        }
        if(gp.getCurrentMap().equals(gp.getMap_6()) && hit(1, 24, "left") && gp.getTeleSet() == false) {
//            System.out.println(gp.checkGate);
            gp.setTmpMap(gp.getMap_5());
            mapTele(gp.getDialogueState());
        }
        if(gp.getCurrentMap().equals(gp.getMap_6()) && hit(47, 3, "right") && gp.getTeleSet() == false) {
//            System.out.println(gp.checkGate);
            if(gp.getPlayer().hasKey >= 4) {
                gp.setTmpMap(gp.getMap_7());
                mapTele(gp.getDialogueState());
            }
            else {
                if(loadMapMessage == false) {
                    gp.getUi().addMessage("You don't have key");
                    loadMapMessage = true;
                    loadMapCounter = 0;
                }
            }
        }
        if(gp.getCurrentMap().equals(gp.getMap_7()) && hit(24, 5, "up") && gp.getTeleSet() == false) {
//            System.out.println(gp.checkGate);
            gp.setTmpMap(gp.getMap_6());
            mapTele(gp.getDialogueState());
        }
        if(gp.getCurrentMap().equals(gp.getMap_7()) && hit(48, 48, "right") && gp.getTeleSet() == false) {
//            System.out.println(gp.checkGate);
            if(gp.getPlayer().hasKey >= 5) {
                gp.setTmpMap(gp.getMap_8());
                mapTele(gp.getDialogueState());
            }
            else {
                if(loadMapMessage == false) {
                    gp.getUi().addMessage("You don't have key");
                    loadMapMessage = true;
                    loadMapCounter = 0;
                }
            }
        }
        if(gp.getCurrentMap().equals(gp.getMap_8()) && hit(1, 8, "left") && gp.getTeleSet() == false) {
//            System.out.println(gp.checkGate);
            gp.setTmpMap(gp.getMap_7());
            mapTele(gp.getDialogueState());
        }
        if(gp.getCurrentMap().equals(gp.getMap_8()) && hit(48, 43, "right") && gp.getTeleSet() == false) {
//            System.out.println(gp.checkGate);
            if(gp.getPlayer().hasKey >= 6) {
                gp.setTmpMap(gp.getMap_9());
                mapTele(gp.getDialogueState());
            }
            else {
                if(loadMapMessage == false) {
                    gp.getUi().addMessage("You don't have key");
                    loadMapMessage = true;
                    loadMapCounter = 0;
                }
            }
        }
        if(gp.getCurrentMap().equals(gp.getMap_9()) && hit(48, 1, "up") && gp.getTeleSet() == false) {
//            System.out.println(gp.checkGate);
            gp.setTmpMap(gp.getMap_8());
            mapTele(gp.getDialogueState());
        }
        if(gp.getCurrentMap().equals(gp.getMap_9()) && hit(48, 31, "right") && gp.getTeleSet() == false) {
//            System.out.println(gp.checkGate);
            if(gp.getPlayer().hasKey >= 7) {
                gp.setTmpMap(gp.getMap_10());
                mapTele(gp.getDialogueState());
            }
            else {
                if(loadMapMessage == false) {
                    gp.getUi().addMessage("You don't have key");
                    loadMapMessage = true;
                    loadMapCounter = 0;
                }
            }
        }
        if(gp.getCurrentMap().equals(gp.getMap_10()) && hit(1, 25, "left") && gp.getTeleSet() == false) {
//            System.out.println(gp.checkGate);
            gp.setTmpMap(gp.getMap_9());
            mapTele(gp.getDialogueState());
        }

    }
    public void healHeart(int gameState){
        if(gp.getKeyHandler().enterPressed == true) {
            gp.setGameState(gameState);
            gp.getUi().currentDialogue = "1 coc nuoc da'i vao mom";
            gp.getPlayer().life++;
        }
        gp.getKeyHandler().enterPressed = false;
    }
    public void damagePit(int gameState){
        gp.setGameState(gameState);
        gp.getUi().currentDialogue = "Dam trung bay roi thang ngu";
        gp.getPlayer().life--;
    }
    public void mapTele(int gameState) {
        gp.setGameState(gameState);
        gp.getUi().currentDialogue = "Ban co muon chac dich chuyen toi me cung khong?";
        gp.setCheckGate(true);
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
