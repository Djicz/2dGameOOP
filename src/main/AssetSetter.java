package main;

import entity.NPC_shop;
import entity.monster_orc;
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
        if(gp.currentMap.equals(gp.map_1)) {
            gp.npc[0] = new NPC_shop(gp);
            gp.npc[0].worldX = gp.getTileSize() * 14;
            gp.npc[0].worldY = gp.getTileSize() * 9;
            gp.npc[0].items.add(new hp_potion());
            gp.npc[0].items.add(new mana_potion());
            gp.npc[0].items.add(new object_sword());
            gp.npc[0].items.add(new blue_sword());
            gp.npc[0].items.add(new green_sword());
            gp.npc[0].items.add(new purpil_sword());
            gp.npc[0].items.add(new red_sword());
            gp.npc[0].items.add(new excalibur());
            gp.npc[0].items.add(new key());
            gp.npc[0].items.add(new skill_book());
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
        for(int i = 0; i < 10; i++) {
            gp.monster[i] = new monster_orc(gp);
            randomIndex(i);
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
        gp.monster[index] = new monster_orc(gp);
        randomIndex(index);
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
                    if(gp.monster[i].worldX / gp.getTileSize() == x && gp.monster[i].worldY / gp.getTileSize() == y) {
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
