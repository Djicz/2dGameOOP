package main;

import entity.*;
import objects.*;

import java.util.Random;

public class AssetSetter {
    GamePanel gp;
    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }
    public void setObject() {

    }


    public void setNPC() {
        if(gp.getCurrentMap().equals(gp.getMap_1())) {
            gp.npc[0] = new NPC_shop(gp);
            gp.npc[0].worldX = gp.getTileSize() * 14;
            gp.npc[0].worldY = gp.getTileSize() * 9;
            gp.npc[0].items.add(new hp_potion());
            gp.npc[0].items.add(new mana_potion());
            gp.npc[0].items.add(new object_sword());
            gp.npc[0].items.add(new blue_sword());
            gp.npc[0].items.add(new green_sword());
            gp.npc[0].items.add(new purple_sword());
            gp.npc[0].items.add(new red_sword());
            gp.npc[0].items.add(new excalibur());
            gp.npc[0].items.add(new key());
            gp.npc[0].items.add(new skill_book());
            gp.npc[0].items.add(new shield_1());
            gp.npc[0].items.add(new shield_2());
            gp.npc[0].items.add(new shield_3());
            gp.npc[0].items.add(new shield_4());
            gp.npc[0].items.add(new shield_5());
        }

    }
    public void setMonster() {
//        if(gp.currentMap.equals(gp.map_1)) {
//            gp.monster[0] = new monster_orc(gp);
//            gp.monster[0].worldX = gp.getTileSize() * 23;
//            gp.monster[0].worldY = gp.getTileSize() * 5;
//            gp.monster[1] = new monster_orc(gp);
//            gp.monster[1].worldX = gp.getTileSize() * 25;
//            gp.monster[1].worldY = gp.getTileSize() * 6;
//            gp.monster[2] = new monster_orc(gp);
//            gp.monster[2].worldX = gp.getTileSize() * 23;
//            gp.monster[2].worldY = gp.getTileSize() * 8;
//        }
        if(gp.getCurrentMap() == gp.getMap_1() || gp.getCurrentMap() == gp.getMap_2() || gp.getCurrentMap() == gp.getMap_3()) {
            for (int i = 0; i < 10; i++) {
                gp.monster[i] = new monster_orc(gp);
                randomIndex(i);
            }
        }
        if(gp.getCurrentMap() == gp.getMap_4()) {
            gp.monster[10] = new monster_kingSkeleton(gp);
            gp.monster[10].worldX = gp.getTileSize() * 5;
            gp.monster[10].worldY = gp.getTileSize() * 23;
        }
        if(gp.getCurrentMap() == gp.getMap_5()) {
            gp.monster[10] = new monster_kingSkeleton(gp);
            gp.monster[10].worldX = gp.getTileSize() * 7;
            gp.monster[10].worldY = gp.getTileSize() * 6;
        }
        if(gp.getCurrentMap() == gp.getMap_6()) {
            gp.monster[10] = new monster_kingSkeleton(gp);
            gp.monster[10].worldX = gp.getTileSize() * 13;
            gp.monster[10].worldY = gp.getTileSize() * 43;
        }
        if(gp.getCurrentMap() == gp.getMap_7()) {
            gp.monster[10] = new monster_kingSkeleton(gp);
            gp.monster[10].worldX = gp.getTileSize() * 6;
            gp.monster[10].worldY = gp.getTileSize() * 40;
        }
        if(gp.getCurrentMap() == gp.getMap_8()) {
            gp.monster[10] = new monster_kingSkeleton(gp);
            gp.monster[10].worldX = gp.getTileSize() * 24;
            gp.monster[10].worldY = gp.getTileSize() * 19;
        }
        if(gp.getCurrentMap() == gp.getMap_9()) {
            gp.monster[10] = new monster_kingSkeleton(gp);
            gp.monster[10].worldX = gp.getTileSize() * 12;
            gp.monster[10].worldY = gp.getTileSize() * 19;
        }
        if(gp.getCurrentMap() == gp.getMap_10()) {
            gp.monster[10] = new monster_kingSkeleton(gp);
            gp.monster[10].worldX = gp.getTileSize() * 24;
            gp.monster[10].worldY = gp.getTileSize() * 14;
        }
    }
    public void setMonster(int index) {
//        if(gp.currentMap.equals(gp.map_1)) {
//            if (index == 0) {
//                gp.monster[0] = new monster_orc(gp);
//                gp.monster[0].worldX = gp.getTileSize() * 23;
//                gp.monster[0].worldY = gp.getTileSize() * 5;
//            }
//            if(index == 1) {
//                gp.monster[1] = new monster_orc(gp);
//                gp.monster[1].worldX = gp.getTileSize() * 25;
//                gp.monster[1].worldY = gp.getTileSize() * 6;
//            }
//            if(index == 2) {
//                gp.monster[2] = new monster_orc(gp);
//                gp.monster[2].worldX = gp.getTileSize() * 23;
//                gp.monster[2].worldY = gp.getTileSize() * 8;
//            }
//        }
        if(gp.getCurrentMap() == gp.getMap_1() || gp.getCurrentMap() == gp.getMap_2() || gp.getCurrentMap() == gp.getMap_3()) {
            gp.monster[index] = new monster_orc(gp);
            randomIndex(index);
        }
    }
    public void randomIndex(int index) {
        int x = -1, y = -1;
        while(true) {
            x = new Random().nextInt(50); // Random toa do x 0 -> 50
            y = new Random().nextInt(50); // Random toa do y 0 -> 50
            if (gp.tileM.tile[gp.tileM.mapFromFile[x][y]].collision == true) continue;
            boolean checkColis = false;
            for(int i = 0; i < gp.monster.length; i++) {
                if(i == index)  continue;
                if(gp.monster[i] != null) {
                    if(x >= gp.monster[i].worldX / gp.getTileSize()
                            && x <= (gp.monster[i].worldX / gp.getTileSize()) + 3
                            && y >= gp.monster[i].worldY / gp.getTileSize()
                            && y <= (gp.monster[i].worldY / gp.getTileSize()) + 3) {
                        checkColis = true;
                    }
                }
            }
            if(!checkColis) {
                break;
            }
        }
        gp.monster[index].worldX = gp.getTileSize() * y;
        gp.monster[index].worldY = gp.getTileSize() * x;
    }
}
