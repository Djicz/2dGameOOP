package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gp;
    public Tile[] tile; // Mang luu image component map
    public int[][] mapFromFile; // Mang luu txt file map
    public int mapNum = 1;

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[50];
        getTileImage(); // Lay image component map
        mapFromFile = new int[gp.getMaxWorldRow()][gp.getMaxWorldCol()]; // Khoi tao mang luu phan tu map 50 x 50
        loadMap("/maps/worldMap.txt"); // Load hinh anh map tu file component txt
    }
    public void loadMap(String s) {
        try {
            InputStream is = getClass().getResourceAsStream(s);
            BufferedReader br = new BufferedReader(new InputStreamReader(is)); // Doc file txt
            // Khoi tao cot hang
            int col = 0;
            int row = 0;
            while (col < gp.getMaxWorldCol() && row < gp.getMaxWorldRow()) {
                String line = br.readLine(); // Doc 1 dong trong file map
                String[] numbers = line.trim().split("\\s+"); // Tach tung ki tu thong qua dau " "
                while (col < gp.getMaxWorldCol()) {

                    int num = Integer.parseInt(numbers[col]); // Doc ki tu thu col
                    mapFromFile[row][col] = num; // Gan gia tri vao ma tran map
                    col++; // Tang so cot
                } // Neu cot du 16 thi dung loop
                System.out.println("Loaded row: " + row + ", line: " + col);

                if (col == gp.getMaxWorldCol()) {
                    col = 0; // Xuong dong khoi tao lai cot
                    row++; // Tang dong
                }

            }
            br.close(); // Dong luong doc file txt
        }catch(Exception e) {
            e.printStackTrace(); // Loai bo ngoai le
        }
    }
    public void getTileImage() {
        try {
            //add cac tiles vao mang nho
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/000.png"));
            tile[0].collision = true;

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/001.png"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/002.png"));

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/003.png"));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/004.png"));


            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/005.png"));

            tile[6] = new Tile();
            tile[6].image = ImageIO.read(getClass().getResourceAsStream("/tiles/006.png"));

            tile[7] = new Tile();
            tile[7].image = ImageIO.read(getClass().getResourceAsStream("/tiles/007.png"));

            tile[8] = new Tile();
            tile[8].image = ImageIO.read(getClass().getResourceAsStream("/tiles/008.png"));

            tile[9] = new Tile();
            tile[9].image = ImageIO.read(getClass().getResourceAsStream("/tiles/009.png"));

            tile[10] = new Tile();
            tile[10].image = ImageIO.read(getClass().getResourceAsStream("/tiles/010.png"));

            tile[11] = new Tile();
            tile[11].image = ImageIO.read(getClass().getResourceAsStream("/tiles/011.png"));

            tile[12] = new Tile();
            tile[12].image = ImageIO.read(getClass().getResourceAsStream("/tiles/012.png"));

            tile[13] = new Tile();
            tile[13].image = ImageIO.read(getClass().getResourceAsStream("/tiles/013.png"));

            tile[14] = new Tile();
            tile[14].image = ImageIO.read(getClass().getResourceAsStream("/tiles/014.png"));

            tile[15] = new Tile();
            tile[15].image = ImageIO.read(getClass().getResourceAsStream("/tiles/015.png"));

            tile[16] = new Tile();
            tile[16].image = ImageIO.read(getClass().getResourceAsStream("/tiles/016z.png"));
            tile[16].collision = true;

            tile[17] = new Tile();
            tile[17].image = ImageIO.read(getClass().getResourceAsStream("/tiles/017.png"));

            tile[18] = new Tile();
            tile[18].image = ImageIO.read(getClass().getResourceAsStream("/tiles/018.png"));
            tile[18].collision = true;

            tile[19] = new Tile();
            tile[19].image = ImageIO.read(getClass().getResourceAsStream("/tiles/019.png"));
            tile[19].collision = true;

            tile[20] = new Tile();
            tile[20].image = ImageIO.read(getClass().getResourceAsStream("/tiles/020.png"));
            tile[20].collision = true;

            tile[21] = new Tile();
            tile[21].image = ImageIO.read(getClass().getResourceAsStream("/tiles/021.png"));
            tile[21].collision = true;

            tile[22] = new Tile();
            tile[22].image = ImageIO.read(getClass().getResourceAsStream("/tiles/022.png"));
            tile[22].collision = true;

            tile[23] = new Tile();
            tile[23].image = ImageIO.read(getClass().getResourceAsStream("/tiles/023.png"));
            tile[23].collision = true;

            tile[24] = new Tile();
            tile[24].image = ImageIO.read(getClass().getResourceAsStream("/tiles/024.png"));
            tile[24].collision = true;

            tile[25] = new Tile();
            tile[25].image = ImageIO.read(getClass().getResourceAsStream("/tiles/025.png"));
            tile[25].collision = true;

            tile[26] = new Tile();
            tile[26].image = ImageIO.read(getClass().getResourceAsStream("/tiles/026.png"));
            tile[26].collision = true;

            tile[27] = new Tile();
            tile[27].image = ImageIO.read(getClass().getResourceAsStream("/tiles/027.png"));
            tile[27].collision = true;

            tile[28] = new Tile();
            tile[28].image = ImageIO.read(getClass().getResourceAsStream("/tiles/028.png"));
            tile[28].collision = true;

            tile[29] = new Tile();
            tile[29].image = ImageIO.read(getClass().getResourceAsStream("/tiles/029.png"));
            tile[29].collision = true;

            tile[30] = new Tile();
            tile[30].image = ImageIO.read(getClass().getResourceAsStream("/tiles/030.png"));
            tile[30].collision = true;

            tile[31] = new Tile();
            tile[31].image = ImageIO.read(getClass().getResourceAsStream("/tiles/031.png"));
            tile[31].collision = true;

            tile[32] = new Tile();
            tile[32].image = ImageIO.read(getClass().getResourceAsStream("/tiles/032.png"));
            tile[32].collision = true;

            tile[33] = new Tile();
            tile[33].image = ImageIO.read(getClass().getResourceAsStream("/tiles/033.png"));

            tile[34] = new Tile();
            tile[34].image = ImageIO.read(getClass().getResourceAsStream("/tiles/034.png"));

            tile[35] = new Tile();
            tile[35].image = ImageIO.read(getClass().getResourceAsStream("/tiles/035.png"));


            tile[36] = new Tile();
            tile[36].image = ImageIO.read(getClass().getResourceAsStream("/tiles/036.png"));

            tile[37] = new Tile();
            tile[37].image = ImageIO.read(getClass().getResourceAsStream("/tiles/037.png"));



        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2, int tileSize) {

        // Tính hàng và cột bắt đầu/kết thúc hiển thị
        int startCol = (gp.getPlayer().worldX - gp.getPlayer().screenX) / tileSize;
        int endCol   = (gp.getPlayer().worldX + gp.getPlayer().screenX) / tileSize;
        int startRow = (gp.getPlayer().worldY - gp.getPlayer().screenY) / tileSize;
        int endRow   = (gp.getPlayer().worldY + gp.getPlayer().screenY) / tileSize;

        for (int worldRow = startRow - 1; worldRow <= endRow + 1; worldRow++) {
            for (int worldCol = startCol - 1; worldCol <= endCol + 1; worldCol++) {

                int tileNum;

                // 🔸 Nếu vượt ngoài bản đồ thì lấy tile mặc định
                if (worldRow < 0 || worldRow >= gp.getMaxWorldRow() ||
                        worldCol < 0 || worldCol >= gp.getMaxWorldCol()) {
                    if(gp.getCurrentMap().equals(gp.getMap_1()) || gp.getCurrentMap().equals(gp.getMap_2()) || gp.getCurrentMap().equals(gp.getMap_3()))   tileNum = 19; // tile mặc định, ví dụ: cỏ
                    else tileNum = 0;
                } else {
                    tileNum = mapFromFile[worldRow][worldCol];
                    // Nếu tileNum không hợp lệ thì cũng fallback về tile 0
                    if (tileNum < 0 || tileNum >= tile.length || tile[tileNum] == null) {
                        tileNum = 0;
                    }
                }

                int worldX = worldCol * tileSize;
                int worldY = worldRow * tileSize;
                int screenX = worldX - gp.getPlayer().worldX + gp.getPlayer().screenX;
                int screenY = worldY - gp.getPlayer().worldY + gp.getPlayer().screenY;

                g2.drawImage(tile[tileNum].image, screenX, screenY, tileSize, tileSize, null);
            }
        }
    }
}
