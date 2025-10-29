package main;

import entity.Entity;

public class CollisionChecker {
    GamePanel gp;
    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }
    public void checkTile(Entity entity) { // Su dung Entity ma khong phai Player vi con phai check monster, NPC...
        int entityLeftWorldX = entity.worldX + entity.solidArea.x; // Toa do x phia ben trai
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width; // Toa do x phia ben phai
        int entityTopWorldY = entity.worldY + entity.solidArea.y; // Toa do y phia tren
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height; // Toa do y phia duoi

        int entityLeftCol = entityLeftWorldX / gp.getTileSize();
        int entityRightCol = entityRightWorldX / gp.getTileSize();
        int entityTopRow = entityTopWorldY / gp.getTileSize();
        int entityBottomRow = entityBottomWorldY / gp.getTileSize();

        int tileNum1, tileNum2;

        switch (entity.direction){
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed) / gp.getTileSize();
                tileNum1 = gp.getTileM().mapFromFile[entityTopRow][entityLeftCol];
                tileNum2 = gp.getTileM().mapFromFile[entityTopRow][entityRightCol];
                if(gp.getTileM().tile[tileNum1].collision == true || gp.getTileM().tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.getTileSize();
                tileNum1 = gp.getTileM().mapFromFile[entityBottomRow][entityLeftCol];
                tileNum2 = gp.getTileM().mapFromFile[entityBottomRow][entityRightCol];
                if(gp.getTileM().tile[tileNum1].collision == true || gp.getTileM().tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.getTileSize();
                tileNum1 = gp.getTileM().mapFromFile[entityTopRow][entityLeftCol];
                tileNum2 = gp.getTileM().mapFromFile[entityBottomRow][entityLeftCol];
                if(gp.getTileM().tile[tileNum1].collision == true || gp.getTileM().tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed) / gp.getTileSize();
                tileNum1 = gp.getTileM().mapFromFile[entityTopRow][entityRightCol];
                tileNum2 = gp.getTileM().mapFromFile[entityBottomRow][entityRightCol];
                if(gp.getTileM().tile[tileNum1].collision == true || gp.getTileM().tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
        }

    }
    public int checkObject(Entity entity, boolean player) {
        int index = 999;
        for(int i = 0; i < gp.obj.length; i++) {
            if(gp.obj[i] != null) {
                entity.solidArea.x += entity.worldX;
                entity.solidArea.y += entity.worldY;

                gp.obj[i].solidArea.x += gp.obj[i].worldX;
                gp.obj[i].solidArea.y += gp.obj[i].worldY;

                switch (entity.direction) {
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
                            if(gp.obj[i].collision == true) {
                                entity.collisionOn = true;
                            }
                            if(player == true) {
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
                            if(gp.obj[i].collision == true) {
                                entity.collisionOn = true;
                            }
                            if(player == true) {
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
                            if(gp.obj[i].collision == true) {
                                entity.collisionOn = true;
                            }
                            if(player == true) {
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
                            if(gp.obj[i].collision == true) {
                                entity.collisionOn = true;
                            }
                            if(player == true) {
                                index = i;
                            }
                        }
                        break;
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
                gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
            }
        }

        return index;
    }
    // Kiem tra va cham NPC va monster
    public int checkEntity(Entity entity, Entity[] target) {
        int index = 999;
        for(int i = 0; i < target.length; i++) {
            if(target[i] != null) {
                // Cap nhat cac gioi han va cham tren map
                entity.solidArea.x += entity.worldX;
                entity.solidArea.y += entity.worldY;

                target[i].solidArea.x += target[i].worldX;
                target[i].solidArea.y += target[i].worldY;

                switch (entity.direction) {
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        break;
                }
                if(entity.solidArea.intersects(target[i].solidArea)) {
                    if(target[i] != entity) entity.collisionOn = true;
                    index = i;
                }
                // Tra ve cac gioi han va cham ban dau
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                target[i].solidArea.x = target[i].solidAreaDefaultX;
                target[i].solidArea.y = target[i].solidAreaDefaultY;
            }
        }

        return index;
    }
    public void checkPlayer(Entity entity) {
        // Cap nhat cac gioi han va cham tren map
        entity.solidArea.x += entity.worldX;
        entity.solidArea.y += entity.worldY;

        gp.getPlayer().solidArea.x += gp.getPlayer().worldX;
        gp.getPlayer().solidArea.y += gp.getPlayer().worldY;

        switch (entity.direction) {
            case "up":
                entity.solidArea.y -= entity.speed;
                if(entity.solidArea.intersects(gp.getPlayer().solidArea)) {
                    entity.collisionOn = true;
                    entity.colisPlayer = true;
                }
                break;
            case "down":
                entity.solidArea.y += entity.speed;
                if(entity.solidArea.intersects(gp.getPlayer().solidArea)) {
                    entity.collisionOn = true;
                    entity.colisPlayer = true;
                }
                break;
            case "left":
                entity.solidArea.x -= entity.speed;
                if(entity.solidArea.intersects(gp.getPlayer().solidArea)) {
                    entity.collisionOn = true;
                    entity.colisPlayer = true;
                }
                break;
            case "right":
                entity.solidArea.x += entity.speed;
                if(entity.solidArea.intersects(gp.getPlayer().solidArea)) {
                    entity.collisionOn = true;
                    entity.colisPlayer = true;
                }
                break;
        }
        // Tra ve cac gioi han va cham ban dau
        entity.solidArea.x = entity.solidAreaDefaultX;
        entity.solidArea.y = entity.solidAreaDefaultY;
        gp.getPlayer().solidArea.x = gp.getPlayer().solidAreaDefaultX;
        gp.getPlayer().solidArea.y = gp.getPlayer().solidAreaDefaultY;
    }
    public int checkEntityStep(Entity entity, Entity[] target) {
        int index = 999;
        for(int i = 0; i < target.length; i++) {
            if(target[i] != null) {
                // Cap nhat cac gioi han va cham tren map
                entity.solidArea.x += entity.worldX;
                entity.solidArea.y += entity.worldY;

                target[i].solidArea.x += target[i].worldX;
                target[i].solidArea.y += target[i].worldY;

                switch (entity.direction) {
                    case "up":
                        entity.solidArea.y -= (3 * entity.speed);
                        break;
                    case "down":
                        entity.solidArea.y += (3 * entity.speed);
                        break;
                    case "left":
                        entity.solidArea.x -= (3* entity.speed);
                        break;
                    case "right":
                        entity.solidArea.x += (3 * entity.speed);
                        break;
                }
                if(entity.solidArea.intersects(target[i].solidArea)) {
                    if(target[i] != entity) entity.collisionOn = true;
                    index = i;
                }
                // Tra ve cac gioi han va cham ban dau
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                target[i].solidArea.x = target[i].solidAreaDefaultX;
                target[i].solidArea.y = target[i].solidAreaDefaultY;
            }
        }

        return index;
    }
}
