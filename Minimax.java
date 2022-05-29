package Caro;

import java.util.ArrayList;
import java.util.List;

public class Minimax {

    private static int n = 15;
	
    private static List<Integer> possibleMoves(int[][] position) {
        List<Integer> possibleMoves = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(position[i][j] != 0) {
                    if(i > 0)
                        if(position[i-1][j] == 0) possibleMoves.add((i-1) * n + j);
                    if(j > 0)
                        if(position[i][j-1] == 0) possibleMoves.add(i * n + (j-1));
                    if(i > 0 && j > 0)
                        if(position[i-1][j-1] == 0) possibleMoves.add((i-1) * n + (j-1));
                    if(j < n-1)
                        if(position[i][j+1] == 0) possibleMoves.add(i * n + (j+1));
                    if(i < n-1)
                        if(position[i+1][j] == 0) possibleMoves.add((i+1) * n + j);
                    if(i > 0 && j < n-1)
                        if(position[i-1][j+1] == 0) possibleMoves.add((i-1) * n + (j+1));
                    if(i < n-1 && j > 0)
                        if(position[i+1][j-1] == 0) possibleMoves.add((i+1) * n + (j-1));
                    if(i < n-1 && j < n-1)
                        if(position[i+1][j+1] == 0) possibleMoves.add((i+1) * n + (j+1));
                }
            }
        }
        return possibleMoves;
    }
    
    private static double heuristic(int[][] position, boolean player1Turn) {
        
        double playerScore = Heuristic.getScore(position, true, player1Turn);
        double botScore = Heuristic.getScore(position, false, player1Turn);

        if(playerScore == 0) playerScore = 1.0;

        return botScore / playerScore;
    }

    private static List<int[][]> child(int[][] position, boolean player1Turn) {
        List<int[][]> child = new ArrayList<>();
        int player = player1Turn ? 1 : 2;
        for(int i = 0; i < possibleMoves(position).size(); i++) {
            int pos = possibleMoves(position).get(i);
            int[][] current_state = new int[n][n];
            for(int j = 0; j < n; j++) 
                for(int k = 0; k < n; k++)
                    current_state[j][k] = position[j][k];
            current_state[pos / n][pos % n] = player;
            child.add(current_state);
        }

        return child;
    }

    private static double minimax(int[][] position, int depth, double alpha, double beta, boolean player1Turn) {
        if(depth == 0) {
            return heuristic(position, player1Turn);
        }
        if(player1Turn) {
            double maxValue = -10000000;
            for(int i = 0; i < child(position, player1Turn).size(); i++) {
                double value = minimax(child(position, player1Turn).get(i), depth-1, alpha, beta, false);
                maxValue = Math.max(value, maxValue);
                if(maxValue >= beta) break;
                alpha = Math.max(alpha, maxValue);
                
            }
            // System.out.print('\n');
            return maxValue;
        }
        else {
            double minValue = 10000000;
            for(int i = 0; i < child(position, player1Turn).size(); i++) {
                double value = minimax(child(position, player1Turn).get(i), depth-1, alpha, beta, true);
                minValue = Math.min(value, minValue);
                if(minValue <= alpha) break;
                beta = Math.min(beta, minValue);
            }
            return minValue;
        }
    }

    public static int best_move(int[][] position) {
        int location = 0;
        double value = -1000000000;

        for(int i = 0; i < child(position, false).size(); i++) {
            // System.out.println(possibleMoves(position).get(i) + " " + minimax(child(position, false).get(i), 1, false));
            if(minimax(child(position, false).get(i), 1, -10000000, 10000000, false) >= value) {
                value = minimax(child(position, false).get(i), 1, -10000000, 10000000, false);
                // System.out.print(value + " ");
                location = possibleMoves(position).get(i);
            }
        }
        // System.out.println("------------");
        return location;
    }

    // public static void main(String[] args) {
    //     int[][] position = new int[15][15];
    //     for(int i = 0; i < n; i++) {
    //         for(int j = 0; j < n; j++) {
    //             position[i][j] = 0;
    //         }
    //     }
    //     position[3][3] = 2;

    // } 
}
