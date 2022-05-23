package Caro;

import java.util.ArrayList;
import java.util.List;

public class Minimax {
    // public static List<Integer> possibleMoves(int[][] position) {
    //     List<Integer> possibleMoves = new ArrayList<>();
    //     int n = 15;
    //     for(int i = 0; i < n; i++) {
    //         for(int j = 0; j < n; j++) {
    //             if(position[i][j] == 2) {
    //                 if(i > 0)
    //                     if(position[i-1][j] == 0) possibleMoves.add((i-1) + j * n);
    //                 if(j > 0)
    //                     if(position[i][j-1] == 0) possibleMoves.add(i + (j-1) * n);
    //                 if(i > 0 && j > 0)
    //                     if(position[i-1][j-1] == 0) possibleMoves.add((i-1) + (j-1) * n);
    //                 if(j < n-1)
    //                     if(position[i][j+1] == 0) possibleMoves.add(i + (j+1) * n);
    //                 if(i < n-1)
    //                     if(position[i+1][j] == 0) possibleMoves.add((i+1) + j * n);
    //                 if(i > 0 && j < n-1)
    //                     if(position[i-1][j+1] == 0) possibleMoves.add((i-1) + (j+1) * n);
    //                 if(i < n-1 && j > 0)
    //                     if(position[i+1][j-1] == 0) possibleMoves.add((i+1) + (j-1) * n);
    //                 if(i < n-1 && j < n-1)
    //                     if(position[i+1][j+1] == 0) possibleMoves.add((i+1) + (j+1) * n);
    //             }
    //         }
    //     }
    //     return possibleMoves;
    // }
}
