/*
    GUI - Graphic User Interface
    
    Chức năng của các hàm:

    run() (thực thi interface Runnable): thực thi game
    init(): khởi tạo các biến, hình ảnh
    update(): cập nhật tình trạng của game theo thời gian thực (ô nào đã đánh, ô nào còn trống)
    
    render(): vẽ lên panel ẩn (hiểu dễ hơn là bản phác thảo, chưa đc publish)
    draw(): vẽ chính thức, publish lên cửa sổ ứng dụng

    mouseSensing() (thực thi interface MouseListener): nhận diện các thao tác của con trỏ chuột

*/

package Caro;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable, MouseListener {

    public static final int WIDTH = 720 + 120;            //  
    public static final int HEIGHT = 720;
    
    private BufferedImage image;                         //
    private Graphics2D g;
    
    private Thread thread;                               //
    private boolean running;

    private int FPS = 30;
    private int targetTime = 1000 / FPS;

    private int x, y;           // Vị trí click chuột 

    private int[][] position;

    private boolean player1Turn;

    private int player1Wins;

    private BufferedImage[] player1, player2;

    // private List<Integer> possibleMoves;

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

        player1 = new BufferedImage[1];
        player2 = new BufferedImage[1];
        try {
            player1[0] = ImageIO.read(new File("Caro/Material/X.png"));
            player2[0] = ImageIO.read(new File("Caro/Material/O.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void update() {

        if(x >= 15 && x < 18 && y == 7) {
            init();
        }

        if(GameOverChecking.gameOver(position, !player1Turn)) {
            player1Wins = (player1Turn ? 2 : 1);
            return;
        }

        if(player1Turn) {
            if(x >= 0 && y >= 0 && x < 15 && y < 15 && position[y][x] == 0) {
                // position[y][x] = 1;
            }
            else return;
        }
        else {
            y = Minimax.best_move(position)[0];
            x = Minimax.best_move(position)[1];
            // position[y][x] = 2;
            // position[location / 15][location % 15] = 2;
            
        }
        if(x < 0 || y < 0) return;

        if(position[y][x] == 0) {
            if(player1Turn) {
                position[y][x] = 1;
            }
            else {
                position[y][x] = 2;
            }
            player1Turn = !player1Turn;
        }
            
    }

    private void render() {
        g.clearRect(0, 0, WIDTH, HEIGHT);

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        g.setColor(Color.BLACK);

        for(int i = 0; i < 15; i++) {
            for(int j = 0; j < 15; j++) {
                if(position[j][i] == 1) {
                    g.drawImage(player1[0], i * 48 + 6, j * 48 + 6, null);
                }
                else if(position[j][i] == 2) {
                    g.drawImage(player2[0], i * 48 + 6, j * 48 + 6, null);
                }
                g.drawRect(i * 48, j * 48, 48, 48);
                // g.drawString(String.valueOf(i + j * 15), i * 48 + 20, j * 48 + 20);
            }
        }

        g.setFont(new Font("Arial", Font.TYPE1_FONT, 16));
        if(player1Wins != 0) {
            g.drawString("Player " + String.valueOf(player1Wins) + " wins !", WIDTH - 116, 30);
            if(!player1Turn) {
                g.drawImage(player1[0], WIDTH - 80, 40, null);
            }
            else g.drawImage(player2[0], WIDTH - 80, 40, null);
        }
        else {
            g.drawString("Player " + String.valueOf(player1Turn ? 1 : 2) + "'s turn", WIDTH - 116, 30);
            if(player1Turn) {
                g.drawImage(player1[0], WIDTH - 80, 40, null);
            }
            else g.drawImage(player2[0], WIDTH - 80, 40, null);
        }
        g.drawRect(WIDTH - 119, 0, 120, 80);
        g.drawRect(WIDTH - 120, 1, 120, 80);
        
        g.drawString("RESTART", WIDTH - 100, HEIGHT / 2 + 8);
        g.drawRect(WIDTH - 110, HEIGHT / 2 - 20, 100, 40);

        // for(int i = 0; i < possibleMoves.size(); i++) {
        //     int x = possibleMoves.get(i) / 15;
        //     int y = possibleMoves.get(i) % 15;
        //     // System.out.print(x + " " + y);
        //     g.drawOval(x * 48 + 20, y * 48 + 20, 8, 8);
        // }
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
        // System.out.println(x + " " + y);
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }

}