package main;
import AI.PathFinder;
import entity.Entity;
import entity.Player;
import objects.SuperObject;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable {
    private final int originalTileSize = 16; // 16 x 16
    private final int scale = 3;
    private final int tileSize = originalTileSize * scale; //48 x 48
    private final int maxScreenCol = 16; // 16 col (Moi col 48)
    private final int maxScreenRow = 12; // 12 row (Moi row 48)
    // => 3 x 4 screen
    private final int screenWidth = tileSize * maxScreenCol; // 768
    private final int screenHeight = tileSize * maxScreenRow; // 576
    // Map world stat
    private final int maxWorldCol = 50; // Toi da 50 pixel
    private final int maxWorldRow = 50;
    private final int worldWidth = tileSize * maxWorldCol; // Chieu rong = 50 * 48
    private final int worldHeight = tileSize * maxWorldRow; // Chieu cao = 50 * 48
    private int gameState;
    private final int titleState = 0;
    private final int gameContinue = 1;
    private final int gamePause = 2;
    private final int dialogueState = 3;
    private final int characterState = 4;
    private final int shopState = 5;
    private final int shopState_notify = 6;
    private final int characterState_notify = 7;
    private final int gameOverState = 8;
    private final int gameWinState = 9;
    private boolean callWithShop = false;
    private boolean checkGate = false;
    private int[] reviveCounter = new int[20];
    private final String map_1 = "/maps/worldMap.txt";
    private final String map_2 = "/maps/worldMap2.txt";
    private final String map_3 = "/maps/worldMap3.txt";
    private final String map_4 = "/maps/dungeon1.txt";
    private final String map_5 = "/maps/dungeon2.txt";
    private final String map_6 = "/maps/dungeon3.txt";
    private final String map_7 = "/maps/dungeon4.txt";
    private final String map_8 = "/maps/dungeon5.txt";
    private final String map_9 = "/maps/dungeon6.txt";
    private final String map_10 = "/maps/dungeon7.txt";
    private String currentMap;
    private String tmpMap;
    private int teleCounter = 0;
    private boolean teleSet = false;
    public int getMaxWorldCol() {
        return maxWorldCol;
    }
    public int getMaxWorldRow() {
        return maxWorldRow;
    }
    public int getWorldWidth() {
        return worldWidth;
    }
    public int getWorldHeight() {
        return worldHeight;
    }
    public int getTileSize() {
        return tileSize;
    }
    public int getMaxScreenCol() {
        return maxScreenCol;
    }
    public int getMaxScreenRow() {
        return maxScreenRow;
    }
    public int getScreenWidth() {
        return screenWidth;
    }
    public int getScreenHeight() {
        return screenHeight;
    }
    public int getOriginalTileSize() {
        return originalTileSize;
    }
    public int getScale() {
        return scale;
    }
    public int getGameState() {
        return gameState;
    }
    public int getTitleState() {
        return titleState;
    }
    public int getGameContinue() {
        return gameContinue;
    }
    public int getGamePause() {
        return gamePause;
    }
    public int getDialogueState() {
        return dialogueState;
    }
    public int getCharacterState() {
        return characterState;
    }
    public int getShopState() {
        return shopState;
    }
    public int getShopState_notify() {
        return shopState_notify;
    }
    public int getCharacterState_notify() {
        return characterState_notify;
    }
    public int getGameOverState() {
        return gameOverState;
    }
    public int getGameWinState() {
        return gameWinState;
    }
    public boolean isCallWithShop() {
        return callWithShop;
    }
    public boolean isCheckGate() {
        return checkGate;
    }
    public int[] getReviveCounter() {
        return reviveCounter;
    }
    public String getMap_1() {
        return map_1;
    }
    public String getMap_2() {
        return map_2;
    }
    public String getMap_3() {
        return map_3;
    }
    public String getMap_4() {
        return map_4;
    }
    public String getMap_5() {
        return map_5;
    }
    public String getMap_6() {
        return map_6;
    }
    public String getMap_7() {
        return map_7;
    }
    public String getMap_8() {
        return map_8;
    }
    public String getMap_9() {
        return map_9;
    }
    public String getMap_10() {
        return map_10;
    }
    public String getCurrentMap() {
        return currentMap;
    }
    public String getTmpMap() {
        return tmpMap;
    }
    public int getTeleCounter() {
        return teleCounter;
    }
    public boolean getTeleSet() {
        return teleSet;
    }
    public int getFPS() {
        return FPS;
    }
    public long getTimer() {
        return timer;
    }
    public int getDrawCount() {
        return drawCount;
    }
    public KeyHandler getKeyHandler() {
        return keyHandler;
    }
    public AssetSetter getaSetter() {
        return aSetter;
    }
    public SuperObject[] getObj() {
        return obj;
    }
    public ArrayList<Entity> getProjectileList() {
        return projectileList;
    }
    public Thread getGameThread() {
        return gameThread;
    }
    public CollisionChecker getcChecker() {
        return cChecker;
    }
    public UI getUi() {
        return ui;
    }
    public EventHandler geteHandler() {
        return eHandler;
    }
    public Entity[] getNpc() {
        return npc;
    }
    public Entity[] getMonster() {
        return monster;
    }
    public PathFinder getpFinder() {
        return pFinder;
    }
    public void setGameState(int gameState) {
        this.gameState = gameState;
    }
    public void setCallWithShop(boolean callWithShop) {
        this.callWithShop = callWithShop;
    }
    public void setCheckGate(boolean checkGate) {
        this.checkGate = checkGate;
    }
    public void setReviveCounter(int[] reviveCounter) {
        this.reviveCounter = reviveCounter;
    }
    public void setCurrentMap(String currentMap) {
        this.currentMap = currentMap;
    }
    public void setTmpMap(String tmpMap) {
        this.tmpMap = tmpMap;
    }
    public void setTeleCounter(int teleCounter) {
        this.teleCounter = teleCounter;
    }
    public void setTeleSet(boolean teleSet) {
        this.teleSet = teleSet;
    }
    public void setFPS(int FPS) {
        this.FPS = FPS;
    }
    public void setTimer(long timer) {
        this.timer = timer;
    }
    public void setDrawCount(int drawCount) {
        this.drawCount = drawCount;
    }
    public void setTileM(TileManager tileM) {
        this.tileM = tileM;
    }
    public void setKeyHandler(KeyHandler keyHandler) {
        this.keyHandler = keyHandler;
    }
    public void setaSetter(AssetSetter aSetter) {
        this.aSetter = aSetter;
    }
    public void setObj(SuperObject[] obj) {
        this.obj = obj;
    }
    public void setProjectileList(ArrayList<Entity> projectileList) {
        this.projectileList = projectileList;
    }
    public void setGameThread(Thread gameThread) {
        this.gameThread = gameThread;
    }
    public void setcChecker(CollisionChecker cChecker) {
        this.cChecker = cChecker;
    }
    public void setPlayer(Player player) {
        this.player = player;
    }
    public void setUi(UI ui) {
        this.ui = ui;
    }
    public void seteHandler(EventHandler eHandler) {
        this.eHandler = eHandler;
    }
    public void setNpc(Entity[] npc) {
        this.npc = npc;
    }
    public void setMonster(Entity[] monster) {
        this.monster = monster;
    }
    public void setpFinder(PathFinder pFinder) {
        this.pFinder = pFinder;
    }
    //FPS
    int FPS = 60;
    long timer = 0;
    int drawCount = 0;
    TileManager tileM = new TileManager(this);
    public KeyHandler keyHandler = new KeyHandler(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public SuperObject[] obj = new SuperObject[20];
    public TileManager getTileM() {
        return tileM;
    }
    public ArrayList<Entity> projectileList = new ArrayList<>();
    Thread gameThread; // Khi khoi tao gameThread se tu dong chay phuong thuc Run()
    public CollisionChecker cChecker = new CollisionChecker(this);
    Player player = new Player(this, keyHandler);
    public UI ui = new UI(this);
    public EventHandler eHandler = new EventHandler(this);
    public Player getPlayer() {
        return player;
    }
    public Entity[] npc = new Entity[20];
    public Entity[] monster = new Entity[11];
    public PathFinder pFinder = new PathFinder(this);
    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); // Set size yeu thich, trong nay mac dinh se hien size width * height
        this.setBackground(Color.black); // Nen mac dinh se co mau den
        this.setDoubleBuffered(true); // Do hoa hien len lien tuc, tranh bi nhap nhay man hinh (trong luc ve do hoa)
        this.addKeyListener(keyHandler); // Them phim da nhap de GamePanel nhan ra
        this.setFocusable(true); // GamePanel focus vao key vua nhap
        gameState = titleState; // Mac dinh trang thai game continue;
        currentMap = map_1;
        tmpMap = map_1;
    }

    public void setUpGame() {
        for(int i = 0; i < 11; i++) {
            npc[i] = null;
            monster[i] = null;
            obj[i] = null;
        }
        aSetter.setObject();
        aSetter.setNPC();
        aSetter.setMonster();
    }

    public void startGameThread() {
        gameThread = new Thread(this); // Truyen object GamePanel vao;
        gameThread.start(); // Bat dau chay phuong thuc Run()
    }
    public void update() {

        if(this.gameState == this.gameContinue) {
            player.update();
            for(int i = 0; i < npc.length; i++){
                if(npc[i] != null) {
                    npc[i].update();
                }
            }
            for(int i = 0; i < monster.length; i++){

                if(monster[i] != null) {
                    if(monster[i].aliveState == true) {
                        monster[i].update();
                    }
                    else {
                        player.exp += monster[i].expWhenKill;
                        player.coin += monster[i].coinWanted;
                        ui.addMessage("+" + monster[i].expWhenKill + " exp");
                        ui.addMessage("+" + monster[i].coinWanted + " coin");
                        monster[i].checkDrop();
                        monster[i] = null;
                    }
                }
                else {
                    reviveCounter[i]++;
                    if(reviveCounter[i] > 600) {
                        reviveCounter[i] = 0;
                        if(i != 10) aSetter.setMonster(i);
                    }
                }
            }
            for(int i = 0; i < projectileList.size(); i++){
                if(projectileList.get(i) != null) {
                    if(projectileList.get(i).aliveState == true) {
                        projectileList.get(i).update();
                    }
                }
                if(projectileList.get(i).aliveState == false) {
                    projectileList.remove(i);
                }
            }
        }
        player.updateState();
    }
    // Phuong thuc co san trong thu vien ???
    public void paintComponent(Graphics g) { // Truyen Object Graphics g (Graphics trong tv awt)
        super.paintComponent(g); //super => JPanel
        // Chuyen tu Graphics => Graphics2D
        Graphics2D g2 = (Graphics2D) g; //Graphics2D extends Graphics (co nhieu phuong thuc hon Graphics)

        if(gameState == titleState){
            ui.draw(g2);
        }
        else {
            // Dam bao tile duoc ve trc player vi neu ve sau thi tile se che player lam nguoi choi khong the thay nhan vat
            tileM.draw(g2, tileSize);
            for (int i = 0; i < obj.length; i++) {
                if (obj[i] != null) {
                    obj[i].draw(g2, this);
                }
            }
            for (int i = 0; i < npc.length; i++) {
                if (npc[i] != null) {
                    npc[i].draw(g2, this);
                }
            }
            for (int i = 0; i < monster.length; i++) {
                if (monster[i] != null) {
                    monster[i].draw(g2, this);
                }
            }
            for (int i = 0; i < projectileList.size(); i++) {
                if (projectileList.get(i) != null) {
                    projectileList.get(i).draw(g2, this);
                }
            }
            player.draw(g2, tileSize);

            ui.draw(g2);
        }
        if(teleSet == true) {
            ++teleCounter;
            if(teleCounter > 300) {
                teleCounter = 0;
                teleSet = false;
            }
        }
        if(eHandler.loadMapMessage == true) {
            ++eHandler.loadMapCounter;
            if(eHandler.loadMapCounter > 180) {
                eHandler.loadMapMessage = false;
                eHandler.loadMapCounter = 0;
            }
        }

        g2.dispose(); // Loai bo ngu canh va giai phong tai nguyen
    }
    @Override
    public void run() {  // Game loop

        double drawInterval = 1000000000 / FPS; // Su dung nano giay 1000000000Ns = 1s
        // 1 anh duoc ve trong drawInterval Ns thi 1 Ns se ve duoc FPS (60) anh
        double nextDrawTime = System.nanoTime() + drawInterval;
        // Thoi diem ve tiep theo se la thoi diem ve hien tai + thoi gian can delay de ve 1 anh
        long lstTime = System.nanoTime();

        while(gameThread != null) { //luong gameThread con ton tai thi game loop van lap
            long currentTime = System.nanoTime();
            timer += (currentTime - lstTime);
            lstTime = currentTime;
            ++drawCount;
            if(timer >= 1000000000){
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
            // Update vi tri nhan vat
            update();
            // Ve lai map
            repaint(); // Phuong thuc goi paintComponent()

            try {
                double remainingTime = nextDrawTime - System.nanoTime(); // Thoi gian con phai cho cho lan ve tiep theo

                remainingTime /= 1000000; // vi sleep chi chap nhan miliseconds
                // => Chuyen tu nanoseconds -> miliseconds (1000000000 Ns = 1000 Ms)

                if(remainingTime < 0){
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime); // Tam dung game loop cho den khi het thoi gian cho

                nextDrawTime += drawInterval; // Cap nhat lai thoi gian ve tiep theo
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
