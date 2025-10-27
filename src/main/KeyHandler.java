package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed, shiftPressed, shotKeyPressed, escPressed;

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
                gp.ui.currentHpCd = gp.ui.cdHpMax;
            }
        }

        if(code == KeyEvent.VK_2) {
            if(gp.getPlayer().manaQC == false && gp.getPlayer().manaQAverage > 0) {
                gp.getPlayer().manaQAverage--;
                gp.getPlayer().manaQC = true;
                gp.ui.currentManaCd = gp.ui.cdManaMax;
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
            if(gp.gameState == gp.gameContinue) {
                gp.gameState = gp.characterState;

            }
            else if(gp.gameState == gp.characterState) {
                gp.ui.slotNumX = 0;
                gp.ui.slotNumY = 0;
                gp.gameState = gp.gameContinue;
            }
        }
        if(gp.gameState == gp.titleState) {
            if(code == KeyEvent.VK_W){
                gp.ui.titleIn--;
            }

            if(code == KeyEvent.VK_S){
                gp.ui.titleIn++;
            }
            if(gp.ui.titleIn < 1){
                gp.ui.titleIn = 3;
            }
            if(gp.ui.titleIn > 3){
                gp.ui.titleIn = 1;
            }
            if(code == KeyEvent.VK_ENTER) {
                if(gp.ui.titleIn == 1) {
                    // New game
                    gp.gameState = gp.gameContinue;
                }
                else if(gp.ui.titleIn == 2) {
                    // Load game
                }
                else {
                    // Out game
                    System.exit(0);
                }
            }
        }
        if(code == KeyEvent.VK_P) {
            if(gp.gameState == gp.gameContinue) {
                gp.gameState = gp.gamePause;
            }
            else if(gp.gameState == gp.gamePause) {
                gp.gameState = gp.gameContinue;
            }
        }
        if(code == KeyEvent.VK_E) {
            if(gp.gameState == gp.dialogueState) {
                gp.gameState = gp.gameContinue;
            }
            gp.getPlayer().attackMode = true;
        }

        if(code == KeyEvent.VK_W){

            if(gp.gameState == gp.characterState) {
                if(gp.ui.slotNumY > 0)  gp.ui.slotNumY--;
                else    gp.ui.slotNumY = 5;
            }
            if(gp.gameState == gp.shopState) {
                if(gp.ui.slotNumY > 0)  gp.ui.slotNumY--;
                else    gp.ui.slotNumY = 7;
            }
            upPressed = true;
        }

        if(code == KeyEvent.VK_S){

            if(gp.gameState == gp.characterState) {
                if(gp.ui.slotNumY < 5)  gp.ui.slotNumY++;
                else {
                    gp.ui.slotNumY = 0;
                }
            }
            if(gp.gameState == gp.shopState) {
                if(gp.ui.slotNumY < 7)  gp.ui.slotNumY++;
                else {
                    gp.ui.slotNumY = 0;
                }
            }
            downPressed = true;
        }


        if(code == KeyEvent.VK_A){

            if(gp.gameState == gp.characterState) {
                if(gp.ui.slotNumX > 0)  gp.ui.slotNumX--;
                else if(gp.ui.slotNumX == 0) {
                    if(gp.ui.slotNumY == 0) {
                        gp.ui.slotNumX = 4;
                        gp.ui.slotNumY = 5;
                    }
                    else {
                        gp.ui.slotNumY--;
                        gp.ui.slotNumX = 4;
                    }
                }

            }
            if(gp.gameState == gp.shopState) {
                if(gp.ui.slotNumX > 0)  gp.ui.slotNumX--;
                else if(gp.ui.slotNumX == 0) {
                    if(gp.ui.slotNumY == 0) {
                        gp.ui.slotNumX = 6;
                        gp.ui.slotNumY = 7;
                    }
                    else {
                        gp.ui.slotNumY--;
                        gp.ui.slotNumX = 6;
                    }
                }

            }
            leftPressed = true;
        }

        if(code == KeyEvent.VK_D){

            if(gp.gameState == gp.characterState) {
                if(gp.ui.slotNumX < 4)  gp.ui.slotNumX++;
                else if(gp.ui.slotNumX == 4){
                    if(gp.ui.slotNumY < 5){

                        gp.ui.slotNumY++;
                        gp.ui.slotNumX = 0;
                    }
                    else {
                        gp.ui.slotNumX = 0;
                        gp.ui.slotNumY = 0;
                    }
                }
            }
            if(gp.gameState == gp.shopState) {
                if(gp.ui.slotNumX < 6)  gp.ui.slotNumX++;
                else if(gp.ui.slotNumX == 6){
                    if(gp.ui.slotNumY < 7){

                        gp.ui.slotNumY++;
                        gp.ui.slotNumX = 0;
                    }
                    else {
                        gp.ui.slotNumX = 0;
                        gp.ui.slotNumY = 0;
                    }
                }
            }
            rightPressed = true;
        }

        if(code == KeyEvent.VK_ENTER) {
            enterPressed = true;
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
