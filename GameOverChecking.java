package Caro;

public class GameOverChecking {
    
    public static int max_straight(int[][] position, boolean player1Turn) {
        int n = 15;
        int player = (player1Turn ? 1 : 2);
        int[][] left = new int[n][n];
        int[][] right = new int[n][n];
        int[][] up = new int[n][n];
        int[][] down = new int[n][n];
        for(int i = 0; i < n; i++) {
            left[i][n-1] = (position[i][n-1] == player ? 1 : 0);
            right[i][0] = (position[i][0] == player ? 1 : 0);
            up[0][i] = (position[0][i] == player ? 1 : 0);
            down[n-1][i] = (position[n-1][i] == player ? 1 : 0);

        }
        int max_length = 0;
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
                if(value > max_length) max_length = value;
            }
        }
        return max_length; 
    }
    
    public static int max_cross(int[][] position, boolean player1Turn) {
        int n = 15;
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
        
        int max_length = 0;
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
                if(value > max_length) max_length = value;
            }
        }
        return max_length; 
    }

    
}
