package main;

import javax.swing.*;
public class Main extends JFrame {

    public static void main() {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //An x se thoat game luon
        window.setResizable(false); // Khong cho phep keo dan man hinh
        window.setTitle("2D Game Project");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack();
        window.setLocationRelativeTo(null); // Hien thi screen ra giua man hinh, neu khong co thi se hien ra o goc tren ben trai (toa do (0, 0))
        window.setVisible(true); // Hien thi screen len man hinh = true
        gamePanel.setUpGame();
        gamePanel.startGameThread();
    }
}