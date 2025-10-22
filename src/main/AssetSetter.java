package main;

import entity.NPC_OldMan;
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
        gp.npc[0] = new NPC_OldMan(gp);
        gp.npc[0].worldX = gp.getTileSize() * 21;
        gp.npc[0].worldY = gp.getTileSize() * 21;

    }
    public void setMonster() {
        gp.monster[0] = new monster_orc(gp);
        gp.monster[0].worldX = gp.getTileSize() * 21;
        gp.monster[0].worldY = gp.getTileSize() * 22;
    }
    public void setMonster(int index) {
        if(index == 0) {
            gp.monster[index] = new monster_orc(gp);
            gp.monster[index].worldX = gp.getTileSize() * 21;
            gp.monster[index].worldY = gp.getTileSize() * 22;
        }
    }
}
