package backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 八皇后问题
 * 在8*8的棋盘中放入8颗棋子，要求每颗棋子所在的行、列、对角线上都没有其他棋子，求所有可能的棋子摆放方式。
 *
 * @Author: kevin
 * @Date: 2019/5/19
 */
public class EightQueen {
    final int size = 8;
    int[] board = new int[size];
    List<int[]> results = new ArrayList<>();

    public static void main(String[] args) {
        EightQueen demo = new EightQueen();
        demo.calc8Queen(0);
        demo.printResult();
    }

    void calc8Queen(int row) {
        if (row == size) {
            results.add(Arrays.copyOf(board, size));
            return;
        }
        for (int loc=0; loc<size; loc++) {
            if (isValidLocation(board, row, loc)) {
                board[row] = loc;
                calc8Queen(row+1);
            }
        }
    }

    int[] initBoard() {
        int[] board = new int[size];
        for (int i = 0; i < size; i++) {
            board[i] = -1;
        }
        return board;
    }

    boolean isValidLocation(int[] board, int currRow, int currLoc) {
        for (int row = 0; row < currRow; row++) {
            // 同一列
            if (board[row] == currLoc) {
                return false;
            }
            // 同一对角线
            if (board[row] + (currRow - row) == currLoc) {
                return false;
            } else if (board[row] - (currRow - row) == currLoc) {
                return false;
            }
        }
        return true;
    }

    void printResult() {
        System.out.printf("results (size:%s):\n", results.size());
        for (int[] r : results) {
            for (int pos : r) {
                for (int i = 0; i < size; i++) {
                    if (pos == i) {
                        System.out.printf("%3d", 1);
                    } else {
                        System.out.printf("%3d", 0);
                    }
                }
                System.out.print("\n");
            }
            System.out.println();
        }
    }
}
