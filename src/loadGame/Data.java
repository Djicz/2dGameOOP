package loadGame;

import objects.SuperObject;

import java.io.Serializable;
import java.util.ArrayList;

public class Data implements Serializable {
    int level;
    int strength;
    int dexterity;
    int attack;
    int defense;
    int exp;
    int nextLevelExp;
    int coin;
    int x, y;
    int life, maxLife;
    int stamina, maxStamina;
    int hpPotion, manaPotion;
    int key;
    String currentMap;
    String currentWeapon;
    String currentShield;
    ArrayList<String> inventory = new ArrayList<>();
    ArrayList<String> shop = new ArrayList<>();
}
