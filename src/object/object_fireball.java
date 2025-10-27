package object;

import entity.Projectile;
import main.GamePanel;

public class object_fireball extends Projectile {
    GamePanel gp;
    public object_fireball(GamePanel gp) {
        super(gp);
        this.gp = gp;
        speed = 5;
        maxLife = 80;
        life = maxLife;
        attack = 2;
        useCost = 1;
        knockBackPower = 0;
        aliveState = false;
        getFireballImage();
    }
    public void getFireballImage() {
        up1 = getImage("/projectiles/fireball_up_1");
        up2 = getImage("/projectiles/fireball_up_2");
        down1 = getImage("/projectiles/fireball_down_1");
        down2 = getImage("/projectiles/fireball_down_2");
        left1 = getImage("/projectiles/fireball_left_1");
        left2 = getImage("/projectiles/fireball_left_2");
        right1 = getImage("/projectiles/fireball_right_1");
        right2 = getImage("/projectiles/fireball_right_2");
    }
}
