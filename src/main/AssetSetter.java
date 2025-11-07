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
            gp.getNpc()[0] = new NPC_shop(gp);
            gp.getNpc()[0].worldX = gp.getTileSize() * 14;
            gp.getNpc()[0].worldY = gp.getTileSize() * 9;
            gp.getNpc()[0].items.add(new hp_potion());
            gp.getNpc()[0].items.add(new mana_potion());
            gp.getNpc()[0].items.add(new object_sword());
            gp.getNpc()[0].items.add(new blue_sword());
            gp.getNpc()[0].items.add(new green_sword());
            gp.getNpc()[0].items.add(new purple_sword());
            gp.getNpc()[0].items.add(new red_sword());
            gp.getNpc()[0].items.add(new excalibur());
            gp.getNpc()[0].items.add(new key());
            gp.getNpc()[0].items.add(new skill_book());
            gp.getNpc()[0].items.add(new shield_1());
            gp.getNpc()[0].items.add(new shield_2());
            gp.getNpc()[0].items.add(new shield_3());
            gp.getNpc()[0].items.add(new shield_4());
            gp.getNpc()[0].items.add(new shield_5());
        }

    }
    public void setMonster() {
        if(gp.getCurrentMap().equals(gp.getMap_1()) || gp.getCurrentMap().equals(gp.getMap_2()) || gp.getCurrentMap().equals(gp.getMap_3())) {
            for (int i = 0; i < 10; i++) {
                gp.getMonster()[i] = new monster_orc(gp);
                randomIndex(i);
            }
        }
        if(gp.getCurrentMap().equals(gp.getMap_4())) {
            gp.getMonster()[10] = new monster_kingSkeleton(gp);
            gp.getMonster()[10].worldX = gp.getTileSize() * 5;
            gp.getMonster()[10].worldY = gp.getTileSize() * 23;
        }
        if(gp.getCurrentMap().equals(gp.getMap_5())) {
            gp.getMonster()[10] = new monster_kingSkeleton(gp);
            gp.getMonster()[10].worldX = gp.getTileSize() * 7;
            gp.getMonster()[10].worldY = gp.getTileSize() * 6;
        }
        if(gp.getCurrentMap().equals(gp.getMap_6())) {
            gp.getMonster()[10] = new monster_kingSkeleton(gp);
            gp.getMonster()[10].worldX = gp.getTileSize() * 13;
            gp.getMonster()[10].worldY = gp.getTileSize() * 43;
        }
        if(gp.getCurrentMap().equals(gp.getMap_7())) {
            gp.getMonster()[10] = new monster_kingSkeleton(gp);
            gp.getMonster()[10].worldX = gp.getTileSize() * 6;
            gp.getMonster()[10].worldY = gp.getTileSize() * 40;
        }
        if(gp.getCurrentMap().equals(gp.getMap_8())) {
            gp.getMonster()[10] = new monster_kingSkeleton(gp);
            gp.getMonster()[10].worldX = gp.getTileSize() * 24;
            gp.getMonster()[10].worldY = gp.getTileSize() * 19;
        }
        if(gp.getCurrentMap().equals(gp.getMap_9())) {
            gp.getMonster()[10] = new monster_kingSkeleton(gp);
            gp.getMonster()[10].worldX = gp.getTileSize() * 12;
            gp.getMonster()[10].worldY = gp.getTileSize() * 19;
        }
        if(gp.getCurrentMap().equals(gp.getMap_10())) {
            gp.getMonster()[10] = new monster_kingSkeleton(gp);
            gp.getMonster()[10].worldX = gp.getTileSize() * 24;
            gp.getMonster()[10].worldY = gp.getTileSize() * 14;
        }
    }
    public void setMonster(int index) {
        if(gp.getCurrentMap() == gp.getMap_1() || gp.getCurrentMap() == gp.getMap_2() || gp.getCurrentMap() == gp.getMap_3()) {
            gp.getMonster()[index] = new monster_orc(gp);
            randomIndex(index);
        }
    }
    public void randomIndex(int index) {
        int x = -1, y = -1;
        while(true) {
            x = new Random().nextInt(50); // Random toa do x 0 -> 50
            y = new Random().nextInt(50); // Random toa do y 0 -> 50
            if (gp.getTileM().tile[gp.getTileM().mapFromFile[x][y]].collision == true) continue;
            boolean checkColis = false;
            for(int i = 0; i < gp.getMonster().length; i++) {
                if(i == index)  continue;
                if(gp.getMonster()[i] != null) {
                    if(x >= gp.getMonster()[i].worldX / gp.getTileSize()
                            && x <= (gp.getMonster()[i].worldX / gp.getTileSize()) + 3
                            && y >= gp.getMonster()[i].worldY / gp.getTileSize()
                            && y <= (gp.getMonster()[i].worldY / gp.getTileSize()) + 3) {
                        checkColis = true;
                    }
                }
            }
            if(!checkColis) {
                break;
            }
        }
        gp.getMonster()[index].worldX = gp.getTileSize() * y;
        gp.getMonster()[index].worldY = gp.getTileSize() * x;
    }
}
