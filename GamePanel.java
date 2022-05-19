package Caro;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JPanel;

// class Point {
//     public int x, y;

//     Point(int x, int y) {
//         this.x = x;
//         this.y = y;
//     }
// }

public class GamePanel extends JPanel implements Runnable, MouseListener, MouseWheelListener {

    public static final int WIDTH = 720;            //  
    public static final int HEIGHT = 720;
    
    private BufferedImage image;                         //
    private Graphics2D g;
    
    private Thread thread;                               //
    private boolean running;

    private int FPS = 30;
    private int targetTime = 1000 / FPS;

    private int x, y;           // Vị trí click chuột 

    private int[][] position;                   // Lưu vị trí các quân 2 bên đã ăn

    private boolean player1Turn;

    private int player1Wins;

    // private boolean left, right, up, down;      // Mouse sensing

    // private Point top_left, top_right, bot_left, bot_right;

    // private int horizontal;                       // Mouse Wheel sensing
    // private int vertical;

    public GamePanel() {
        super();
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        requestFocus();
    }

    public void addNotify() {
        super.addNotify();
        if(thread == null) {
            thread = new Thread(this);
            thread.start();
        }
        addMouseListener(this);
        addMouseWheelListener(this);
    }

    public void run() {
        init();

        long startTime;
        long urdTime;
        long waitTime;

        while(running) {
            startTime = System.nanoTime();
            update();
            render();
            draw();

            urdTime = (System.nanoTime() - startTime) / 300000;
            waitTime = targetTime - urdTime;
            if(waitTime < 0) waitTime = targetTime;

            try {
                Thread.sleep(waitTime);
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void init() {
        running = true;
        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        g = (Graphics2D) image.getGraphics();

        x = -1;
        y = -1;

        position = new int[15][15];

        for(int i = 0; i < 15; i++) {
            for(int j = 0; j < 15; j++) {
                position[i][j] = 0;
            }
        }

        player1Turn = true;
        player1Wins = 0;

    }

    private boolean gameOver() {
        
        // int left = max_length(-1, 0, player1Turn);
        // int right = max_length(1, 0, player1Turn);
        // int up = max_length(0, -1, player1Turn);
        // int down = max_length(0, 1, player1Turn);
        // int top_left = max_length(-1, -1, player1Turn);
        // int top_right = max_length(1, -1, player1Turn);
        // int bot_left = max_length(-1, 1, player1Turn);
        // int bot_right = max_length(1, 1, player1Turn);

        // int[] max = {left, right, up, down, top_left, top_right, bot_left, bot_right};
        // int answer = left;
        // for(int i = 1; i < 8; i++) {
        //     if(answer < max[i]) answer = max[i];
        // }
        // return answer == 4;

        int answer = Math.max(GameOverChecking.max_straight(position, player1Turn), GameOverChecking.max_cross(position, player1Turn));
        return answer == 5;

    }

    private void update() {
        
        // if(horizontal > 0) {
        //     top_left.x--;
        //     bot_left.x--;
        //     top_right.x--;
        //     bot_right.x--;
        // }
        // if(horizontal < 0) {
        //     top_right.x++;
        //     bot_right.x++;
        //     top_left.x++;
        //     bot_left.x++;
        // }
        // if(vertical > 0) {
        //     top_left.y--;
        //     bot_left.y--;
        //     top_right.y--;
        //     bot_right.y--;
        // }
        // if(vertical < 0) {
        //     top_left.y++;
        //     bot_left.y++;
        //     top_right.y++;
        //     bot_right.y++;
        // }

        if(player1Wins != 0) {
            running = false;
        }

        if(x >= 0 && y >= 0 && x < 15 && y < 15 && position[y][x] == 0) {
            if(player1Turn) {
                position[y][x] = 1;
            }
            else {
                position[y][x] = 2;
            }
            if(gameOver()) {
                player1Wins = (player1Turn ? 1 : 2);
            }
            player1Turn = !player1Turn;
        }
        
    }

    private void render() {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.PLAIN, 18));
        // for(int i = top_left.x; i < top_right.x; i++) {
        //     for(int j = top_left.y; j < bot_left.y; j++) {
        //         g.drawRect((j - top_left.y) * 60, (i - top_left.x) * 60, 60, 60);
        //         if((i - top_left.y) * 10 + (j - top_left.x) == 11) g.drawString(String.valueOf((i - top_left.y) * 10 + (j - top_left.x)), (j - top_left.y) * 60 + 15, (i - top_left.x) * 60 + 15);
        //     }
        // }

        for(int i = 0; i < 15; i++) {
            for(int j = 0; j < 15; j++) {
                if(position[j][i] == 1) {
                    g.drawLine(i * 48 + 12, j * 48 + 12, (i+1) * 48 - 12, (j+1) * 48 - 12);
                    g.drawLine((i+1) * 48 - 12, j * 48 + 12, i * 48 + 12, (j+1) * 48 - 12);
                }
                else if(position[j][i] == 2) {
                    g.drawOval(i * 48 + 12, j * 48 + 12, 24, 24);
                }
                g.drawRect(i * 48, j * 48, 48, 48);
            }
        }

        if(player1Wins != 0) {
            g.drawString("Player " + String.valueOf(player1Wins) + " wins !", 300, 300);
        }

    }

    private void draw() {
        Graphics g2 = getGraphics();
        g2.drawImage(image, 0, 0, null);
        g2.dispose();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        x = e.getX() / 48;
        y = e.getY() / 48;
        // System.out.println("------------------------------");
        // for(int i = 0; i < 15; i++) {
        //     for(int j = 0; j < 15; j++) {
        //         System.out.print(position[i][j] + " ");
        //     }
        //     System.out.print('\n');
        // }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // x = e.getX();
        // y = e.getY();
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        // if(e.isShiftDown()) {
        //     horizontal = e.getWheelRotation();
        //     // System.out.println("Hor " + horizontal);
        // }
        // else {
        //     vertical = e.getWheelRotation();
        //     // System.out.println("Ver " + vertical);
        // }
    }

}