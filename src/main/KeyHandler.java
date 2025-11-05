package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed, shiftPressed, shotKeyPressed, escPressed, sellPressed;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode(); // Tra ve phim da duoc nhan tren keyboard (dang ma Aski)

        if(code == KeyEvent.VK_1) {
            if(gp.getPlayer().hpQC == false && gp.getPlayer().hpQAverage > 0) {
                gp.getPlayer().hpQAverage--;
                gp.getPlayer().hpQC = true;
                gp.getUi().currentHpCd = gp.getUi().cdHpMax;
            }
        }

        if(code == KeyEvent.VK_2) {
            if(gp.getPlayer().manaQC == false && gp.getPlayer().manaQAverage > 0) {
                gp.getPlayer().manaQAverage--;
                gp.getPlayer().manaQC = true;
                gp.getUi().currentManaCd = gp.getUi().cdManaMax;
            }
        }

        if(code == KeyEvent.VK_ESCAPE) {
            escPressed = true;
        }
        if(code == KeyEvent.VK_F) {
            shotKeyPressed = true;
        }
        if(code == KeyEvent.VK_SHIFT) {
            shiftPressed = true;
        }
        if(code == KeyEvent.VK_C) {
            if(gp.getGameState() == gp.getGameContinue()) {
                gp.setGameState(gp.getCharacterState());
                gp.playSE(3);
            }
            else if(gp.getGameState() == gp.getCharacterState()) {
                gp.getUi().slotNumX = 0;
                gp.getUi().slotNumY = 0;
                gp.setGameState(gp.getGameContinue());
                gp.playSE(2);
            }
        }
        if(gp.getGameState() == gp.getTitleState()) {
            if(code == KeyEvent.VK_W){
                gp.getUi().titleIn--;
            }
            if(code == KeyEvent.VK_S){
                gp.getUi().titleIn++;
            }
            if(gp.getUi().titleIn < 1){
                gp.getUi().titleIn = 3;
            }
            if(gp.getUi().titleIn > 3){
                gp.getUi().titleIn = 1;
            }
            if(code == KeyEvent.VK_ENTER) {
                if(gp.getUi().titleIn == 1) {
                    // New game
                    gp.setGameState(gp.getGameContinue());
                }
                else if(gp.getUi().titleIn == 2) {
                    // Load game
                }
                else {
                    // Out game
                    System.exit(0);
                }
            }
        }
        if(code == KeyEvent.VK_P) {
            if(gp.getGameState() == gp.getGameContinue()) {
                gp.setGameState(gp.getGamePause());
            }
            else if(gp.getGameState() == gp.getGamePause()) {
                gp.setGameState(gp.getGameContinue());;
            }
        }
        if(code == KeyEvent.VK_E) {
            if(gp.getGameState() == gp.getDialogueState()) {
                gp.setGameState(gp.getGameContinue());;
            }
            gp.getPlayer().attackMode = true;
        }

        if(code == KeyEvent.VK_W){

            if(gp.getGameState() == gp.getCharacterState()) {
                if(gp.getUi().slotNumY > 0)  gp.getUi().slotNumY--;
                else    gp.getUi().slotNumY = 5;
            }
            if(gp.getGameState() == gp.getShopState()) {
                if(gp.getUi().slotNumY > 0)  gp.getUi().slotNumY--;
                else    gp.getUi().slotNumY = 7;
            }
            upPressed = true;
        }

        if(code == KeyEvent.VK_S){

            if(gp.getGameState() == gp.getCharacterState()) {
                if(gp.getUi().slotNumY < 5)  gp.getUi().slotNumY++;
                else {
                    gp.getUi().slotNumY = 0;
                }
            }
            if(gp.getGameState() == gp.getShopState()) {
                if(gp.getUi().slotNumY < 7)  gp.getUi().slotNumY++;
                else {
                    gp.getUi().slotNumY = 0;
                }
            }
            downPressed = true;
        }


        if(code == KeyEvent.VK_A){

            if(gp.getGameState() == gp.getCharacterState()) {
                if(gp.getUi().slotNumX > 0)  gp.getUi().slotNumX--;
                else if(gp.getUi().slotNumX == 0) {
                    if(gp.getUi().slotNumY == 0) {
                        gp.getUi().slotNumX = 4;
                        gp.getUi().slotNumY = 5;
                    }
                    else {
                        gp.getUi().slotNumY--;
                        gp.getUi().slotNumX = 4;
                    }
                }

            }
            if(gp.getGameState() == gp.getShopState()) {
                if(gp.getUi().slotNumX > 0)  gp.getUi().slotNumX--;
                else if(gp.getUi().slotNumX == 0) {
                    if(gp.getUi().slotNumY == 0) {
                        gp.getUi().slotNumX = 6;
                        gp.getUi().slotNumY = 7;
                    }
                    else {
                        gp.getUi().slotNumY--;
                        gp.getUi().slotNumX = 6;
                    }
                }

            }
            leftPressed = true;
        }

        if(code == KeyEvent.VK_D){

            if(gp.getGameState() == gp.getCharacterState()) {
                if(gp.getUi().slotNumX < 4)  gp.getUi().slotNumX++;
                else if(gp.getUi().slotNumX == 4){
                    if(gp.getUi().slotNumY < 5){

                        gp.getUi().slotNumY++;
                        gp.getUi().slotNumX = 0;
                    }
                    else {
                        gp.getUi().slotNumX = 0;
                        gp.getUi().slotNumY = 0;
                    }
                }
            }
            if(gp.getGameState() == gp.getShopState()) {
                if(gp.getUi().slotNumX < 6)  gp.getUi().slotNumX++;
                else if(gp.getUi().slotNumX == 6){
                    if(gp.getUi().slotNumY < 7){

                        gp.getUi().slotNumY++;
                        gp.getUi().slotNumX = 0;
                    }
                    else {
                        gp.getUi().slotNumX = 0;
                        gp.getUi().slotNumY = 0;
                    }
                }
            }
            rightPressed = true;
        }

        if(code == KeyEvent.VK_ENTER) {
            enterPressed = true;
        }
        if(code == KeyEvent.VK_X) {
            sellPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode(); // Lay phim duoc nha ra
        if(code == KeyEvent.VK_ESCAPE) {
            escPressed = false;
        }
        if(code == KeyEvent.VK_ENTER) {
            enterPressed = false;
        }

        if(code == KeyEvent.VK_F) {
            shotKeyPressed = false;
        }

        if(code == KeyEvent.VK_SHIFT) {
            shiftPressed = false;
        }

        if(code == KeyEvent.VK_W){ // Vi du nha ra phim W
            upPressed = false; // => Cap nhat upPressed = false
        }

        if(code == KeyEvent.VK_S){
            downPressed = false;
        }

        if(code == KeyEvent.VK_A){
            leftPressed = false;
        }

        if(code == KeyEvent.VK_D){
            rightPressed = false;
        }
    }
}
