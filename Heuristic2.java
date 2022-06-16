package Caro;

public class Heuristic2 {
    private static int n = 15;
    public static final int winScore = 1000000;

    public static int getScore(int[][] position, boolean countingPlayer, boolean player1Turn) {
    
        return evaluateHorizontal(position, countingPlayer, player1Turn) +
                evaluateVertical(position, countingPlayer, player1Turn) +
                evaluateDiagonal(position, countingPlayer, player1Turn);
    }

    private static int evaluateHorizontal(int[][] position, boolean countingPlayer, boolean player1Turn) {
        
        int score = 0;

        int player = countingPlayer ? 1 : 2;

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(j+4 < n) {

/* 11111 */         if(position[i][j] == player && position[i][j+1] == player &&
                    position[i][j+2] == player && position[i][j+3] == player && position[i][j+4] == player) {
                        score += scoreExchange(1, 0, countingPlayer == player1Turn);
                        j = j+5 > n ? n : j+5;
                        continue;
                    }
                    int blocks = 0;
                    if(j == 0) blocks += 1;
                    else
                        if(position[i][j-1] == 3-player) blocks += 1;
                    if(j+5 >= n) blocks += 1;
                    else 
                        if(position[i][j+5] == 3-player) blocks += 1;

/* 11101 */         if(position[i][j] == player && position[i][j+1] == player &&
                    position[i][j+2] == player && position[i][j+3] == 0 && position[i][j+4] == player) {
                        score += scoreExchange(3, blocks, countingPlayer == player1Turn);
                        j = j+5 > n ? n : j+5;
                        continue;
                    }
/* 11011 */         if(position[i][j] == player && position[i][j+1] == player &&
                    position[i][j+2] == 0 && position[i][j+3] == player && position[i][j+4] == player) {
                        score += scoreExchange(3, blocks, countingPlayer == player1Turn);
                        j = j+5 > n ? n : j+5;
                        continue;
                    }
/* 10111 */         if(position[i][j] == player && position[i][j+1] == 0 &&
                    position[i][j+2] == player && position[i][j+3] == player && position[i][j+4] == player) {
                        score += scoreExchange(3, blocks, countingPlayer == player1Turn);
                        j = j+5 > n ? n : j+5;
                        continue;
                    }
/* 10101 */         if(position[i][j] == player && position[i][j+1] == 0 &&
                    position[i][j+2] == player && position[i][j+3] == 0 && position[i][j+4] == player) {
                        score += scoreExchange(5, blocks, countingPlayer == player1Turn);
                        j = j+5 > n ? n : j+5;
                        continue;
                    }
/* 11001 */         if(position[i][j] == player && position[i][j+1] == player &&
                    position[i][j+2] == 0 && position[i][j+3] == 0 && position[i][j+4] == player) {
                        score += scoreExchange(5, blocks, countingPlayer == player1Turn);
                        j = j+5 > n ? n : j+5;
                        continue;
                    }
/* 10011 */         if(position[i][j] == player && position[i][j+1] == 0 &&
                    position[i][j+2] == 0 && position[i][j+3] == player && position[i][j+4] == player) {
                        score += scoreExchange(5, blocks, countingPlayer == player1Turn);
                        j = j+5 > n ? n : j+5;
                        continue;
                    }
                }

                if(j+3 < n) {
                    
                    int blocks = 0;
                    if(j == 0) blocks += 1;
                    else
                        if(position[i][j-1] == 3-player) blocks += 1;
                    if(j+4 >= n) blocks += 1;
                    else 
                        if(position[i][j+4] == 3-player) blocks += 1;

/* 1111 */          if(position[i][j] == player && position[i][j+1] == player &&
                    position[i][j+2] == player && position[i][j+3] == player) {
                        score += scoreExchange(2, blocks, countingPlayer == player1Turn);
                        j = j+4 > n ? n : j+4;
                        continue;
                    }
/* 1101 */          if(position[i][j] == player && position[i][j+1] == player &&
                    position[i][j+2] == 0 && position[i][j+3] == player) {
                        score += scoreExchange(5, blocks, countingPlayer == player1Turn);
                        j = j+4 > n ? n : j+4;
                        continue;
                    }
/* 1011 */          if(position[i][j] == player && position[i][j+1] == 0 &&
                    position[i][j+2] == player && position[i][j+3] == player) {
                        score += scoreExchange(5, blocks, countingPlayer == player1Turn);
                        j = j+4 > n ? n : j+4;
                        continue;
                    }   
                }

                if(j+2 < n) {

                    int blocks = 0;
                    if(j == 0) blocks += 1;
                    else
                        if(position[i][j-1] == 3-player) blocks += 1;
                    if(j+3 >= n) blocks += 1;
                    else 
                        if(position[i][j+3] == 3-player) blocks += 1;

/* 111 */           if(position[i][j] == player && position[i][j+1] == player && position[i][j+2] == player) {
                        score += scoreExchange(4, blocks, countingPlayer == player1Turn);
                        j = j+3 > n ? n : j+3;
                        continue;
                    }
                }

                if(j+1 < n) {
                    
                    int blocks = 0;
                    if(j == 0) blocks += 1;
                    else
                        if(position[i][j-1] == 3-player) blocks += 1;
                    if(j+2 >= n) blocks += 1;
                    else 
                        if(position[i][j+2] == 3-player) blocks += 1;

/* 11 */            if(position[i][j] == player && position[i][j+1] == player) {
                        score += scoreExchange(6, blocks, countingPlayer == player1Turn);
                        j = j+2 > n ? n : j+2;
                        continue;
                    }
                }

            }
        }
        return score;
    }

    private static int evaluateVertical(int[][] position, boolean countingPlayer, boolean player1Turn) {
        
        int score = 0;
        int player = countingPlayer ? 1 : 2;

        for(int j = 0; j < n; j++) {
            for(int i = 0; i < n; i++) {
                if(i+4 < n) {

/* 11111 */         if(position[i][j] == player && position[i+1][j] == player &&
                    position[i+2][j] == player && position[i+3][j] == player && position[i+4][j] == player) {
                        score += scoreExchange(1, 0, countingPlayer == player1Turn);
                        i = i+5 > n ? n : i+5;
                        continue;
                    }
                    int blocks = 0;
                    if(i == 0) blocks += 1;
                    else
                        if(position[i-1][j] == 3-player) blocks += 1;
                    if(i+5 >= n) blocks += 1;
                    else 
                        if(position[i+5][j] == 3-player) blocks += 1;

/* 11101 */         if(position[i][j] == player && position[i+1][j] == player &&
                    position[i+2][j] == player && position[i+3][j] == 0 && position[i+4][j] == player) {
                        score += scoreExchange(3, blocks, countingPlayer == player1Turn);
                        i = i+5 > n ? n : i+5;
                        continue;
                    }
/* 11011 */         if(position[i][j] == player && position[i+1][j] == player &&
                    position[i+2][j] == 0 && position[i+3][j] == player && position[i+4][j] == player) {
                        score += scoreExchange(3, blocks, countingPlayer == player1Turn);
                        i = i+5 > n ? n : i+5;
                        continue;
                    }
/* 10111 */         if(position[i][j] == player && position[i+1][j] == 0 &&
                    position[i+2][j] == player && position[i+3][j] == player && position[i+4][j] == player) {
                        score += scoreExchange(3, blocks, countingPlayer == player1Turn);
                        i = i+5 > n ? n : i+5;
                        continue;
                    }
/* 10101 */         if(position[i][j] == player && position[i+1][j] == 0 &&
                    position[i+2][j] == player && position[i+3][j] == 0 && position[i+4][j] == player) {
                        score += scoreExchange(5, blocks, countingPlayer == player1Turn);
                        i = i+5 > n ? n : i+5;
                        continue;
                    }
/* 11001 */         if(position[i][j] == player && position[i+1][j] == player &&
                    position[i+2][j] == 0 && position[i+3][j] == 0 && position[i+4][j] == player) {
                        score += scoreExchange(5, blocks, countingPlayer == player1Turn);
                        i = i+5 > n ? n : i+5;
                        continue;
                    }
/* 10011 */         if(position[i][j] == player && position[i+1][j] == 0 &&
                    position[i+2][j] == 0 && position[i+3][j] == player && position[i+4][j] == player) {
                        score += scoreExchange(5, blocks, countingPlayer == player1Turn);
                        i = i+5 > n ? n : i+5;
                        continue;
                    }
                }

                if(i+3 < n) {
                    
                    int blocks = 0;
                    if(i == 0) blocks += 1;
                    else
                        if(position[i-1][j] == 3-player) blocks += 1;
                    if(i+4 >= n) blocks += 1;
                    else 
                        if(position[i+4][j] == 3-player) blocks += 1;

/* 1111 */          if(position[i][j] == player && position[i+1][j] == player &&
                    position[i+2][j] == player && position[i+3][j] == player) {
                        score += scoreExchange(2, blocks, countingPlayer == player1Turn);
                        i = i+4 > n ? n : i+4;
                        continue;
                    }
/* 1101 */          if(position[i][j] == player && position[i+1][j] == player &&
                    position[i+2][j] == 0 && position[i+3][j] == player) {
                        score += scoreExchange(5, blocks, countingPlayer == player1Turn);
                        i = i+4 > n ? n : i+4;
                        continue;
                    }
/* 1011 */          if(position[i][j] == player && position[i+1][j] == 0 &&
                    position[i+2][j] == player && position[i+3][j] == player) {
                        score += scoreExchange(5, blocks, countingPlayer == player1Turn);
                        i = i+4 > n ? n : i+4;
                        continue;
                    }   
                }

                if(i+2 < n) {

                    int blocks = 0;
                    if(i == 0) blocks += 1;
                    else
                        if(position[i-1][j] == 3-player) blocks += 1;
                    if(i+3 >= n) blocks += 1;
                    else 
                        if(position[i+3][j] == 3-player) blocks += 1;

                    if(position[i][j] == player && position[i+1][j] == player && position[i+2][j] == player) {
                        score += scoreExchange(4, blocks, countingPlayer == player1Turn);
                        i = i+3 > n ? n : i+3;
                        continue;
                    }
                }

                if(i+1 < n) {
                    
                    int blocks = 0;
                    if(i == 0) blocks += 1;
                    else
                        if(position[i-1][j] == 3-player) blocks += 1;
                    if(i+2 >= n) blocks += 1;
                    else 
                        if(position[i+2][j] == 3-player) blocks += 1;

                    if(position[i][j] == player && position[i+1][j] == player) {
                        score += scoreExchange(6, blocks, countingPlayer == player1Turn);
                        i = i+2 > n ? n : i+2;
                        continue;
                    }
                }
            }

        }
        return score;
    }    

    /* 
        Ví dụ với n = 6:        00 01 02 03 04 05
                                10 11 12 13 14 15
                                20 21 22 23 24 25
                                30 31 32 33 34 35
                                40 41 42 43 44 45
                                50 51 52 53 54 55
        Đường chéo chính:
            Start
            ij = 00, 01, 02, 03, 04, 05, 15, 25, 35, 45, 55
            End
            ij = 00, 10, 20, 30, 40, 50, 51, 52, 53, 54, 55 
            Index
            k  =  0,  1,  2,  3,  4,  5,  6,  7,  8,  9, 10
            
            iStart = k - n + 1 for n <= k <= 2(n-1)
            iStart = 0 for 0 <= k <= n-1
            iEnd = k for 0 <= k <= n-1
            iEnd = n-1 for n <= k <= 2(n-1)
            j = k - i

        Đường chéo phụ:
            Start
            ij = 05, 04, 03, 02, 01, 00, 10, 20, 30, 40, 50
            End
            ij = 05, 15, 25, 35, 45, 55, 54, 53, 52, 51, 50
            Index
            k  = -5, -4, -3, -2, -1,  0,  1,  2,  3,  4,  5

            iStart = max(0, k)
            iEnd = min(n+k-1, n-1)
            j = i-k
    */
    private static int evaluateDiagonal(int[][] position, boolean countingPlayer, boolean player1Turn) {
        
        int score = 0;

        int player = countingPlayer ? 1 : 2;

        for(int k = 0; k <= 2 * (n-1); k++) {
            int iStart = Math.max(0, k-n+1);
            int iEnd = Math.min(k, n-1);
            int length = Math.abs(iEnd - iStart)+1;

            for(int i = iStart; i <= iEnd; i++) {
                int j = k-i;

                if(i+4 < length) {

/* 11111 */         if(position[i][j] == player && position[i+1][j-1] == player &&
                    position[i+2][j-2] == player && position[i+3][j-3] == player && position[i+4][j-4] == player) {
                        score += scoreExchange(1, 0, countingPlayer == player1Turn);
                        i = i+5 > length ? length : i+5;
                        continue;
                    }
                    int blocks = 0;
                    if(i == 0 || j == n-1) blocks += 1;
                    else
                        if(position[i-1][j+1] == 3-player) blocks += 1;
                    if(j-5 < 0 || i+5 >= length) blocks += 1;
                    else 
                        if(position[i+5][j-5] == 3-player) blocks += 1;

/* 11101 */         if(position[i][j] == player && position[i+1][j-1] == player &&
                    position[i+2][j-2] == player && position[i+3][j-3] == 0 && position[i+4][j-4] == player) {
                        score += scoreExchange(3, blocks, countingPlayer == player1Turn);
                        i = i+5 > length ? length : i+5;
                        continue;
                    }
/* 11011 */         if(position[i][j] == player && position[i+1][j-1] == player &&
                    position[i+2][j-2] == 0 && position[i+3][j-3] == player && position[i+4][j-4] == player) {
                        score += scoreExchange(3, blocks, countingPlayer == player1Turn);
                        i = i+5 > length ? length : i+5;
                        continue;
                    }
/* 10111 */         if(position[i][j] == player && position[i+1][j-1] == 0 &&
                    position[i+2][j-2] == player && position[i+3][j-3] == player && position[i+4][j-4] == player) {
                        score += scoreExchange(3, blocks, countingPlayer == player1Turn);
                        i = i+5 > length ? length : i+5;
                        continue;
                    }
/* 10101 */         if(position[i][j] == player && position[i+1][j-1] == 0 &&
                    position[i+2][j-2] == player && position[i+3][j-3] == 0 && position[i+4][j-4] == player) {
                        score += scoreExchange(5, blocks, countingPlayer == player1Turn);
                        i = i+5 > length ? length : i+5;
                        continue;
                    }
/* 11001 */         if(position[i][j] == player && position[i+1][j-1] == player &&
                    position[i+2][j-2] == 0 && position[i+3][j-3] == 0 && position[i+4][j-4] == player) {
                        score += scoreExchange(5, blocks, countingPlayer == player1Turn);
                        i = i+5 > length ? length : i+5;
                        continue;
                    }
/* 10011 */         if(position[i][j] == player && position[i+1][j-1] == 0 &&
                    position[i+2][j-2] == 0 && position[i+3][j-3] == player && position[i+4][j-4] == player) {
                        score += scoreExchange(5, blocks, countingPlayer == player1Turn);
                        i = i+5 > length ? length : i+5;
                        continue;
                    }
                }

                if(i+3 < length) {
                    
                    int blocks = 0;
                    if(i == 0 || j == n-1) blocks += 1;
                    else
                        if(position[i-1][j+1] == 3-player) blocks += 1;
                    if(j-4 < 0 || i+4 >= length) blocks += 1;
                    else 
                        if(position[i+4][j-4] == 3-player) blocks += 1;

/* 1111 */          if(position[i][j] == player && position[i+1][j-1] == player &&
                    position[i+2][j-2] == player && position[i+3][j-3] == player) {
                        score += scoreExchange(2, blocks, countingPlayer == player1Turn);
                        i = i+4 > length ? length : i+4;
                        continue;
                    }
/* 1101 */          if(position[i][j] == player && position[i+1][j-1] == player &&
                    position[i+2][j-2] == 0 && position[i+3][j-3] == player) {
                        score += scoreExchange(5, blocks, countingPlayer == player1Turn);
                        i = i+4 > length ? length : i+4;
                        continue;
                    }
/* 1011 */          if(position[i][j] == player && position[i+1][j-1] == 0 &&
                    position[i+2][j-2] == player && position[i+3][j-3] == player) {
                        score += scoreExchange(5, blocks, countingPlayer == player1Turn);
                        i = i+4 > length ? length : i+4;
                        continue;
                    }   
                }

                if(i+2 < length) {

                    int blocks = 0;
                    if(i == 0 || j == n-1) blocks += 1;
                    else
                        if(position[i-1][j+1] == 3-player) blocks += 1;
                    if(j-3 < 0 || i+3 >= length) blocks += 1;
                    else 
                        if(position[i+3][j-3] == 3-player) blocks += 1;

                    if(position[i][j] == player && position[i+1][j-1] == player && position[i+2][j-2] == player) {
                        score += scoreExchange(4, blocks, countingPlayer == player1Turn);
                        i = i+3 > length ? length : i+3;
                        continue;
                    }
                }

                if(i+1 < length) {
                    
                    int blocks = 0;
                    if(i == 0 || j == n-1) blocks += 1;
                    else
                        if(position[i-1][j+1] == 3-player) blocks += 1;
                    if(j-2 < 0 || i+2 >= length) blocks += 1;
                    else 
                        if(position[i+2][j-2] == 3-player) blocks += 1;

                    if(position[i][j] == player && position[i+1][j-1] == player) {
                        score += scoreExchange(6, blocks, countingPlayer == player1Turn);
                        i = i+2 > length ? length : i+2;
                        continue;
                    }
                }
            }
        }

        for(int k = 1-n; k < n; k++) {
            int iStart = Math.max(0, k);
            int iEnd = Math.min(n+k-1, n-1);

            int length = Math.abs(iEnd - iStart)+1;
            for(int i = iStart; i <= iEnd; i++) {
                int j = i-k;
                
                if(i+4 < length) {

/* 11111 */         if(position[i][j] == player && position[i+1][j+1] == player &&
                    position[i+2][j+2] == player && position[i+3][j+3] == player && position[i+4][j+4] == player) {
                        score += scoreExchange(1, 0, countingPlayer == player1Turn);
                        i = i+5 > length ? length : i+5;
                        continue;
                    }
                    int blocks = 0;
                    if(i == 0 || j == 0) blocks += 1;
                    else
                        if(position[i-1][j-1] == 3-player) blocks += 1;
                    if(j+5 >= length || i+5 >= length) blocks += 1;
                    else 
                        if(position[i+5][j+5] == 3-player) blocks += 1;

/* 11101 */         if(position[i][j] == player && position[i+1][j+1] == player &&
                    position[i+2][j+2] == player && position[i+3][j+3] == 0 && position[i+4][j+4] == player) {
                        score += scoreExchange(3, blocks, countingPlayer == player1Turn);
                        i = i+5 > length ? length : i+5;
                        continue;
                    }
/* 11011 */         if(position[i][j] == player && position[i+1][j+1] == player &&
                    position[i+2][j+2] == 0 && position[i+3][j+3] == player && position[i+4][j+4] == player) {
                        score += scoreExchange(3, blocks, countingPlayer == player1Turn);
                        i = i+5 > length ? length : i+5;
                        continue;
                    }
/* 10111 */         if(position[i][j] == player && position[i+1][j+1] == 0 &&
                    position[i+2][j+2] == player && position[i+3][j+3] == player && position[i+4][j+4] == player) {
                        score += scoreExchange(3, blocks, countingPlayer == player1Turn);
                        i = i+5 > length ? length : i+5;
                        continue;
                    }
/* 10101 */         if(position[i][j] == player && position[i+1][j+1] == 0 &&
                    position[i+2][j+2] == player && position[i+3][j+3] == 0 && position[i+4][j+4] == player) {
                        score += scoreExchange(5, blocks, countingPlayer == player1Turn);
                        i = i+5 > length ? length : i+5;
                        continue;
                    }
/* 11001 */         if(position[i][j] == player && position[i+1][j+1] == player &&
                    position[i+2][j+2] == 0 && position[i+3][j+3] == 0 && position[i+4][j+4] == player) {
                        score += scoreExchange(5, blocks, countingPlayer == player1Turn);
                        i = i+5 > length ? length : i+5;
                        continue;
                    }
/* 10011 */         if(position[i][j] == player && position[i+1][j+1] == 0 &&
                    position[i+2][j+2] == 0 && position[i+3][j+3] == player && position[i+4][j+4] == player) {
                        score += scoreExchange(5, blocks, countingPlayer == player1Turn);
                        i = i+5 > length ? length : i+5;
                        continue;
                    }
                }

                if(i+3 < length) {
                    
                    int blocks = 0;
                    if(i == 0 || j == 0) blocks += 1;
                    else
                        if(position[i-1][j-1] == 3-player) blocks += 1;
                    if(j+4 >= length || i+4 >= length) blocks += 1;
                    else 
                        if(position[i+4][j+4] == 3-player) blocks += 1;

/* 1111 */          if(position[i][j] == player && position[i+1][j+1] == player &&
                    position[i+2][j+2] == player && position[i+3][j+3] == player) {
                        score += scoreExchange(2, blocks, countingPlayer == player1Turn);
                        i = i+4 > length ? length : i+4;
                        continue;
                    }
/* 1101 */          if(position[i][j] == player && position[i+1][j+1] == player &&
                    position[i+2][j+2] == 0 && position[i+3][j+3] == player) {
                        score += scoreExchange(5, blocks, countingPlayer == player1Turn);
                        i = i+4 > length ? length : i+4;
                        continue;
                    }
/* 1011 */          if(position[i][j] == player && position[i+1][j+1] == 0 &&
                    position[i+2][j+2] == player && position[i+3][j+3] == player) {
                        score += scoreExchange(5, blocks, countingPlayer == player1Turn);
                        i = i+4 > length ? length : i+4;
                        continue;
                    }   
                }

                if(i+2 < length) {

                    int blocks = 0;
                    if(i == 0 || j == 0) blocks += 1;
                    else
                        if(position[i-1][j-1] == 3-player) blocks += 1;
                    if(j+3 >= length || i+3 >= length) blocks += 1;
                    else 
                        if(position[i+3][j+3] == 3-player) blocks += 1;


                    if(position[i][j] == player && position[i+1][j+1] == player && position[i+2][j+2] == player) {
                        score += scoreExchange(4, blocks, countingPlayer == player1Turn);
                        i = i+3 > length ? length : i+3;
                        continue;
                    }
                }

                if(i+1 < length) {
                    
                    int blocks = 0;
                    if(i == 0 || j == 0) blocks += 1;
                    else
                        if(position[i-1][j-1] == 3-player) blocks += 1;
                    if(j+2 >= length || i+2 >= length) blocks += 1;
                    else 
                        if(position[i+2][j+2] == 3-player) blocks += 1;


                    if(position[i][j] == player && position[i+1][j+1] == player) {
                        score += scoreExchange(6, blocks, countingPlayer == player1Turn);
                        i = i+2 > length ? length : i+2;
                        continue;
                    }
                }
            }
        }
        return score;
    }

    private static int scoreExchange(int case_number, int blocks, boolean currentTurn) {
        if(blocks == 2 && case_number != 1) return 0;

        switch(case_number) {
            case 1: {
                return winScore;
            }
            case 2: {
                if(currentTurn) return 100000;
                else {
                    if(blocks == 0) return 25000;
                    else return 125;
                }
            }
            case 3: {
                if(currentTurn) return 10000;
                else {
                    if(blocks == 0) return 250;
                    else return 125;
                }
            }
            case 4: {
                if(blocks == 0) {
                    if(currentTurn) return 5000;
                    else return 125;
                }
                else {
                    if(currentTurn) return 25;
                    return 15;
                }
            }
            case 5: {
                if(blocks == 0) {
                    if(currentTurn) return 2500;
                    else return 125;
                }
                else return 15;
            }
            case 6:
                return 10;
        }

        return winScore * 2;
    }

}
