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

    private static int evaluateDiagonal(int[][] position, boolean countingPlayer, boolean player1Turn) {
        int length = 0;
        int blocks = 2;
        int score = 0;

        for(int k = 0; k <= 2 * (n-1); k++) {
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

        for(int k = 1-n; k < n; k++) {
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
