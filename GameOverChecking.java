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
        int[][] main_cross = new int[n][n];
        int[][] sub_cross = new int[n][n];
        for(int i = 0; i < n; i++) {
            main_cross[i][0] = (position[i][0] == player ? 1 : 0);
            main_cross[0][i] = (position[0][i] == player ? 1 : 0);

            sub_cross[i][n-1] = (position[i][n-1] == player ? 1 : 0);
            sub_cross[n-1][i] = (position[n-1][i] == player ? 1 : 0);
        }
        
        int max_length = 0;
        for(int i = 1; i < n; i++) {
            for(int j = 1; j < n; j++) {
                if(position[i][j] == player) {
                    main_cross[i][j] = main_cross[i-1][j-1] + 1;
                }
                else main_cross[i][j] = 0;

                j = n - 1 - j;
                if(position[j][i] == player) {
                    sub_cross[j][i] = sub_cross[j+1][i+1] + 1;
                }
                else sub_cross[j][i] = 0;

                j = n - 1 - j;
            }
        }      
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                int value = Math.max(main_cross[i][j], sub_cross[i][j]);
                if(value > max_length) max_length = value;
            }
        }
        return max_length; 
    }

}
