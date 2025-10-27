package main;

import entity.NPC_OldMan;
import entity.NPC_shop;
import entity.monster_orc;
import object.object_chest;
import object.object_door;
import object.object_key;

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
            gp.npc[0].items.add(new object_key());
            gp.npc[0].items.add(new object_chest());
        }

    }
    public void setMonster() {
        if(gp.currentMap.equals(gp.map_1)) {
            gp.monster[0] = new monster_orc(gp);
            gp.monster[0].worldX = gp.getTileSize() * 23;
            gp.monster[0].worldY = gp.getTileSize() * 5;
            gp.monster[1] = new monster_orc(gp);
            gp.monster[1].worldX = gp.getTileSize() * 25;
            gp.monster[1].worldY = gp.getTileSize() * 6;
            gp.monster[2] = new monster_orc(gp);
            gp.monster[2].worldX = gp.getTileSize() * 23;
            gp.monster[2].worldY = gp.getTileSize() * 8;
        }
    }
    public void setMonster(int index) {
        if(gp.currentMap.equals(gp.map_1)) {
            if (index == 0) {
                gp.monster[0] = new monster_orc(gp);
                gp.monster[0].worldX = gp.getTileSize() * 23;
                gp.monster[0].worldY = gp.getTileSize() * 5;
            }
            if(index == 1) {
                gp.monster[1] = new monster_orc(gp);
                gp.monster[1].worldX = gp.getTileSize() * 25;
                gp.monster[1].worldY = gp.getTileSize() * 6;
            }
            if(index == 2) {
                gp.monster[2] = new monster_orc(gp);
                gp.monster[2].worldX = gp.getTileSize() * 23;
                gp.monster[2].worldY = gp.getTileSize() * 8;
            }
        }
    }
}
