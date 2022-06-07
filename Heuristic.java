/* 
    Hàm đánh giá
    Ta sẽ đánh giá tình trạng tuỳ theo số combo hiện có của mỗi bên
    Ví dụ cho các combo:    FiveInRow (5 quân thẳng hàng)
                            DeadFour (4 quân thẳng hàng, bị chặn 1 đầu)
                            LiveFour (4 quân thẳng hàng, 2 đầu hở)
                            DeadThree (3 quân thẳng hàng, bị chặn 1 đầu)
                            ...
    Với mỗi combo, tuỳ theo lượt hiện tại đang của ai, sẽ đổi ra số điểm tương ứng
    Ví dụ:  Trắng có combo DeadFour, và đang trong lượt của Trắng, 
            Trắng sẽ đc đảm bảo chiến thắng (vì chỉ cần đánh thêm 1 nước là Trắng win)
    Tuy nhiên, cũng với combo DeadFour, nhg lại là lượt của Đen, 
    Trắng sẽ chỉ đc cộng 1 phần số điểm (vì Đen chặn 1 cái là xong)

    Ta sẽ đếm số combo hiện có trên 2 đường ngang, dọc và 2 đường chéo, sau đó cộng lại
*/

package Caro;

public class Heuristic {
    private static int n = 15;
    public static final int winScore = 100000000;

    public static int getScore(int[][] position, boolean countingPlayer, boolean player1Turn) {
        
        int[][] currentState = new int[n][n];
        for(int i = 0; i < n; i++) 
            for(int j = 0; j < n; j++) currentState[i][j] = position[i][j];

        return evaluateHorizontal(position, countingPlayer, player1Turn) +
                evaluateVertical(position, countingPlayer, player1Turn) +
                evaluateDiagonal(position, countingPlayer, player1Turn);
    }

    private static int evaluateHorizontal(int[][] position, boolean countingPlayer, boolean player1Turn) {
        int length = 0;
        int blocks = 2;
        int score = 0;

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(position[i][j] == (countingPlayer ? 1 : 2)) {
                    length++;
                }
                else if(position[i][j] == 0) {
                    if(length > 0) {
                        blocks--;
                        score += scoreExchange(length, blocks, countingPlayer == player1Turn);
                        length = 0;
                        blocks = 1;
                    }
                    else blocks = 1;
                }
                else if(length > 0) {
                    score += scoreExchange(length, blocks, countingPlayer == player1Turn);
                    length = 0;
                    blocks = 2;
                }
                else blocks = 2;
            }
            if(length > 0) {
                score += scoreExchange(length, blocks, countingPlayer == player1Turn);
            }
            length = 0;
            blocks = 2;
        }
        return score;
    }

    private static int evaluateVertical(int[][] position, boolean countingPlayer, boolean player1Turn) {
        int length = 0;
        int blocks = 2;
        int score = 0;

        for(int j = 0; j < n; j++) {
            for(int i = 0; i < n; i++) {
                if(position[i][j] == (countingPlayer ? 1 : 2)) {
                    length++;
                }
                else if(position[i][j] == 0) {
                    if(length > 0) {
                        blocks--;
                        score += scoreExchange(length, blocks, countingPlayer == player1Turn);
                        length = 0;
                        blocks = 1;
                    }
                    else blocks = 1;
                }
                else if(length > 0) {
                    score += scoreExchange(length, blocks, countingPlayer == player1Turn);
                    length = 0;
                    blocks = 2;
                }
                else blocks = 2;
            }
            if(length > 0) {
                score += scoreExchange(length, blocks, countingPlayer == player1Turn);
            }
            length = 0;
            blocks = 2;
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
        int length = 0;
        int blocks = 2;
        int score = 0;

        for(int k = 1; k < 2 * (n-1); k++) {
            int iStart = Math.max(0, k-n+1);
            int iEnd = Math.min(k, n-1);
            for(int i = iStart; i <= iEnd; i++) {
                int j = k-i;

                if(position[i][j] == (countingPlayer ? 1 : 2)) {
                    length++;
                }
                else if(position[i][j] == 0) {
                    if(length > 0) {
                        blocks--;
                        score += scoreExchange(length, blocks, countingPlayer == player1Turn);
                        length = 0;
                        blocks = 1;
                    }
                    else blocks = 1;
                }
                else if(length > 0) {
                    score += scoreExchange(length, blocks, countingPlayer = player1Turn);
                    length = 0;
                    blocks = 2;
                }
                else blocks = 2;
            }
            if(length > 0) {
                score += scoreExchange(length, blocks, countingPlayer = player1Turn);
            }
            length = 0;
            blocks = 2;
        }

        for(int k = 2-n; k < n-1; k++) {
            int iStart = Math.max(0, k);
            int iEnd = Math.min(n+k-1, n-1);
            for(int i = iStart; i <= iEnd; i++) {
                int j = i-k;

                if(position[i][j] == (countingPlayer ? 1 : 2)) {
                    length++;
                }
                else if(position[i][j] == 0) {
                    if(length > 0) {
                        blocks--;
                        score += scoreExchange(length, blocks, countingPlayer == player1Turn);
                        length = 0;
                        blocks = 1;
                    }
                    else blocks = 1;
                }
                else if(length > 0) {
                    score += scoreExchange(length, blocks, countingPlayer = player1Turn);
                    length = 0;
                    blocks = 2;
                }
                else blocks = 2;
            }
            if(length > 0) {
                score += scoreExchange(length, blocks, countingPlayer = player1Turn);
            }
            length = 0;
            blocks = 2;
        }
        return score;
    }

    /*
    Cách tính điểm:
    - Bị chặn 2 đầu và số quân < 5:                                     0 
    - 2 chuỗi 5 quân liên tiếp:                                         200,000,000
    - 5 quân liên tiếp:                                                 100,000,000
    - 4 quân liên tiếp: + Lượt của mình:                                1,000,000
                        + Lượt của đối phương:  ko bị chặn 2 đầu:       250,000
                                                bị chặn 1 đầu:          200
    - 3 quân liên tiếp: + Ko bị chặn 2 đầu:     lượt của mình:          50,000
                                                lượt của đối phương:    200
                        + Bị chặn 1 đầu:        lượt của mình:          10
                                                lượt của đối phương:    5
    - 2 quân liên tiếp: + Ko bị chặn 2 đầu:     lượt của mình:          7
                                                lượt của đối phương:    5
                        + Bị chặn 1 đầu:                                3
    - 1 quân ko bị chặn:                                                1

    * Note: Cần thêm Threat Detecting
    
    */
    private static int scoreExchange(int count, int blocks, boolean currentTurn) {
        final int winGuarantee = 1000000;
        if(blocks == 2 && count < 5) return 0;

        switch(count) {
            case 5: {
                return winScore;
            }
            case 4: {
                if(currentTurn) return winGuarantee;
                else {
                    if(blocks == 0) return winGuarantee / 4;
                    else return 200;
                }
            }
            case 3: {
                if(blocks == 0) {
                    if(currentTurn) return 50000;
                    else return 200;
                }
                else {
                    if(currentTurn) return 10;
                    else return 5;
                }
            }
            case 2: {
                if(blocks == 0) {
                    if(currentTurn) return 7;
                    else return 5;
                }
                else return 3;
            }
            case 1: {
                return 1;
            }
        }

        return winScore * 2;
    }
}
