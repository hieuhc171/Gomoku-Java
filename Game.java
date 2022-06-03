// Khởi tạo cửa sổ - màn hình chơi game

package Caro;

import javax.swing.JFrame;

public class Game {
    public static void main(String[] args) {
        // Khởi tạo cửa sổ chương trình
        JFrame window = new JFrame("Caro");

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setContentPane(new GamePanel());
        
        window.pack();

        window.setVisible(true);
        window.setResizable(false);
        
    }
}