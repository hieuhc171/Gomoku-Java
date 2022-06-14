/* 
    Kiểm tra game đã kết thúc hay chưa (Kiểm tra đã có 5 con thẳng hàng nào hay ko)
    Thuật toán:
    - Tạo ra 8 mảng 2 chiều, tương ứng với 8 hướng cần xét
    - Gọi bảng gốc là initial
    - Xét vị trí initial[i][j]
        + Nếu initial[i][j] = 0 thì vị trí [i][j] tương ứng trg 8 bảng sẽ = 0
        + Nếu initial[i][j] != 0 thì sẽ = vị trí trước đó + 1
    - Tính xong 8 bảng, ta kiểm tra xem có vị trí nào trg 8 bảng có giá trị = 5 ko
*/

package Caro;

public class GameOverChecking {
    private static int n = 15;
    
    // Kiểm tra đường dọc và ngang
    private static int[] horizoncalAndVerticalChecking(int[][] position, boolean player1Turn) {

        int[] answer = new int[3];
        for(int i = 0; i < 3; i++) answer[i] = 0;
        int player = (player1Turn ? 1 : 2);

        // Khởi tạo các bảng
        int[][] left = new int[n][n];
        int[][] right = new int[n][n];
        int[][] up = new int[n][n];
        int[][] down = new int[n][n];

        // Tính giá trị của các ô bắt đầu trong mỗi bảng.
        // Ví dụ, bảng left chạy từ phải sang trái, 
        // thì ta tính giá trị các ô tận cùng bên phải trước
        for(int i = 0; i < n; i++) {
            left[i][n-1] = (position[i][n-1] == player ? 1 : 0);
            right[i][0] = (position[i][0] == player ? 1 : 0);
            up[0][i] = (position[0][i] == player ? 1 : 0);
            down[n-1][i] = (position[n-1][i] == player ? 1 : 0);
        }

        for(int i = 0; i < n; i++) {
            for(int j = 1; j < n; j++) {
                
                if(position[i][j] == player)
                    left[i][j] = left[i][j-1] + 1;
                else left[i][j] = 0;

                if(position[j][i] == player)
                    up[j][i] = up[j-1][i] + 1;
                else up[j][i] = 0;

                j = n - 1 - j;
                
                if(position[i][j] == player)
                    right[i][j] = right[i][j+1] + 1;
                else right[i][j] = 0;

                if(position[j][i] == player)
                    down[j][i] = down[j+1][i] + 1;
                else down[j][i] = 0;

                j = n - 1 - j;
            }
        }   
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                int value = Math.max(Math.max(left[i][j], right[i][j]), Math.max(up[i][j], down[i][j]));
                if(value > answer[0]) {
                    answer[0] = value;
                    answer[1] = i; answer[2] = j;
                }
            }
        }
        return answer; 
    }
    
    // Kiểm tra 2 đường chéo
    private static int[] diagonalChecking(int[][] position, boolean player1Turn) {

        int[] answer = new int[3];
        for(int i = 0; i < 3; i++) answer[i] = 0;
        int player = (player1Turn ? 1 : 2);

        int[][] top_left = new int[n][n];
        int[][] bot_left = new int[n][n];
        int[][] top_right = new int[n][n];
        int[][] bot_right = new int[n][n];

        for(int i = 0; i < n; i++) {
            top_left[i][0] = (position[i][0] == player ? 1 : 0);
            top_left[0][i] = (position[0][i] == player ? 1 : 0);

            bot_left[i][0] = (position[i][0] == player ? 1 : 0);
            bot_left[n-1][i] = (position[n-1][i] == player ? 1 : 0);

            top_right[0][i] = (position[0][i] == player ? 1 : 0);
            top_right[i][n-1] = (position[i][n-1] == player ? 1 : 0);

            bot_right[i][n-1] = (position[i][n-1] == player ? 1 : 0);
            bot_right[n-1][i] = (position[n-1][i] == player ? 1 : 0);
        }
        
        for(int i = 1; i < n; i++) {
            for(int j = 1; j < n; j++) {
                if(position[i][j] == player) {
                    top_left[i][j] = top_left[i-1][j-1] + 1;
                }
                else top_left[i][j] = 0;

                if(position[j][i] == player) {
                    bot_left[j][i] = bot_left[j+1][i-1] + 1;
                }
                else bot_left[j][i] = 0;

                j = n - 1 - j;
                
                if(position[j][i] == player) {
                    bot_right[j][i] = bot_right[j+1][i+1] + 1;
                }
                else bot_right[j][i] = 0;
                
                if(position[i][j] == player) {
                    top_right[i][j] = top_right[i-1][j+1] + 1;
                }
                else top_right[i][j] = 0;

                j = n - 1 - j;
            }
        }      
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                int value = Math.max(Math.max(top_left[i][j], bot_right[i][j]), Math.max(top_right[i][j], bot_left[i][j]));
                if(value > answer[0]) {
                    answer[0] = value;
                    answer[1] = i;
                    answer[2] = j;
                }
            }
        }
        return answer; 
    }

    public static boolean gameOver(int[][] position, boolean player1Turn) {
        int answer = Math.max(horizoncalAndVerticalChecking(position, player1Turn)[0], diagonalChecking(position, player1Turn)[0]);
        return answer == 5;
    }

    // public static int[] getPosition(int[][] position, boolean player1Turn) {
    //     int[] location = new int[3];
    //     location = horizoncalAndVerticalChecking(position, player1Turn)[0] > diagonalChecking(position, player1Turn)[0] ?
    //                horizoncalAndVerticalChecking(position, player1Turn) : diagonalChecking(position, player1Turn);

    //     for(int i = -1; i <= 1; i++) {
    //         for(int j = -1; j <= 1; j++) {

    //         }
    //     }

    //     return location;
    // }

}
