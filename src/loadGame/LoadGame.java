package loadGame;

import main.GamePanel;
import objects.*;

import java.io.*;

public class LoadGame {
    GamePanel gp;
    public LoadGame(GamePanel gp) {
        this.gp = gp;
    }
    public SuperObject getObject(String name) {
        SuperObject item = null;
        switch (name) {
            case "Blue Sword":
                item = new blue_sword();
                break;
            case "Bone Key 1":
                item = new bone_key();
                break;
            case "Bone Key 2":
                item = new bone_key_2();
                break;
            case "Bone Key 3":
                item = new bone_key_3();
                break;
            case "Bone Key 4":
                item = new bone_key_4();
                break;
            case "Bone Key 5":
                item = new bone_key_5();
                break;
            case "Bone Key 6":
                item = new bone_key_6();
                break;
            case "Bone Key 7":
                item = new bone_key_7();
                break;
            case "EXCALIBUR":
                item = new excalibur();
                break;
            case "Green Sword":
                item = new green_sword();
                break;
            case "Key":
                item = new key();
                break;
            case "Normal Sword":
                item = new object_sword();
                break;
            case "Purple Sword":
                item = new purple_sword();
                break;
            case "Skill Book":
                item = new skill_book();
                break;
            case "Red Sword":
                item = new red_sword();
                break;
            case "Shield Level 1":
                item = new shield_1();
                break;
            case "Shield Level 2":
                item = new shield_2();
                break;
            case "Shield Level 3":
                item = new shield_3();
                break;
            case "Shield Level 4":
                item = new shield_4();
                break;
            case "Shield Level 5":
                item = new shield_5();
                break;
            case "HP Potion":
                item = new hp_potion();
                break;
            case "Mana Potion":
                item = new mana_potion();
                break;
        }
        return item;
    }
    public void save() {
        try{
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("save.dat")));
            Data data = new Data();
            data.level = gp.getPlayer().level;
            data.strength = gp.getPlayer().strength;
            data.dexterity = gp.getPlayer().dexterity;
            data.attack = gp.getPlayer().attack;
            data.defense = gp.getPlayer().defense;
            data.exp = gp.getPlayer().exp;
            data.nextLevelExp = gp.getPlayer().nextLevelExp;
            data.coin = gp.getPlayer().coin;
            data.x = gp.getPlayer().worldX;
            data.y = gp.getPlayer().worldY;
            data.life = gp.getPlayer().life;
            data.maxLife = gp.getPlayer().maxLife;
            data.stamina = gp.getPlayer().stamina;
            data.maxStamina = gp.getPlayer().maxStamina;
            data.currentMap = gp.getCurrentMap();
            data.hpPotion = gp.getPlayer().hpQAverage;
            data.manaPotion = gp.getPlayer().manaQAverage;
            if(data.currentWeapon!= null)   data.currentWeapon = gp.getPlayer().currentWeapon.name;
            else    data.currentWeapon = "null";
            if(data.currentShield != null)  data.currentShield = gp.getPlayer().currentShield.name;
            else    data.currentShield = "null";
            for(int i = 0; i < gp.getPlayer().items.size(); i++) {
                data.inventory.add(gp.getPlayer().items.get(i).name);
            }
            if(data.currentMap.equals(gp.getMap_1())) {
                for (int i = 0; i < gp.getNpc()[0].items.size(); i++) {
                    data.shop.add(gp.getNpc()[0].items.get(i).name);
                }
            }
            oos.writeObject(data);
            oos.close();
        }catch(Exception e){}
    }
    public void load() {
        try{
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("save.dat")));
            Data data = (Data)ois.readObject();
            ois.close();
            gp.getPlayer().level = data.level;
            gp.getPlayer().strength = data.strength;
            gp.getPlayer().dexterity = data.dexterity;
            gp.getPlayer().attack = data.attack;
            gp.getPlayer().defense = data.defense;
            gp.getPlayer().exp = data.exp;
            gp.getPlayer().nextLevelExp = data.nextLevelExp;
            gp.getPlayer().coin = data.coin;
            gp.getPlayer().worldX = data.x;
            gp.getPlayer().worldY = data.y;
            gp.getPlayer().life = data.life;
            gp.getPlayer().maxLife = data.maxLife;
            gp.getPlayer().stamina = data.stamina;
            gp.getPlayer().maxStamina = data.maxStamina;
            gp.setCurrentMap(data.currentMap);
            gp.getTileM().loadMap(gp.getCurrentMap());
            gp.getPlayer().currentWeapon = getObject(data.currentWeapon);
            gp.getPlayer().currentShield = getObject(data.currentShield);
            gp.getPlayer().hpQAverage = data.hpPotion;
            gp.getPlayer().manaQAverage = data.manaPotion;
            gp.getPlayer().items.clear();
            gp.getNpc()[0].items.clear();
            for(int i = 0; i < data.inventory.size(); i++) {
                gp.getPlayer().items.add(getObject(data.inventory.get(i)));
            }
            if(data.currentMap.equals(gp.getMap_1())) {
                for (int i = 0; i < data.shop.size(); i++) {
                    gp.getNpc()[0].items.add(getObject(data.shop.get(i)));
                }
            }
        }catch(Exception e){}
    }

}
