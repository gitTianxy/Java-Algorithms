package dynamicProgramming;

/**
 * 状态转移表法
 *
 * @Author: XinyuTian
 * @Date: 2019/5/16
 */
public class StateTransTable {
    final int size = 4;
    final int[][] table = {{1, 3, 5, 9}, {2, 1, 3, 4}, {5, 2, 6, 7}, {6, 8, 4, 3}};
    int[][] values = new int[size][size];

    public static void main(String[] args) {
        StateTransTable demo = new StateTransTable();
        System.out.println("ori table:");
        for (int r=0; r<demo.size; r++) {
            for (int c=0; c<demo.size; c++) {
                System.out.printf("%3d", demo.table[r][c]);
            }
            System.out.print("\n");
        }
        System.out.println();
        demo.fillValues();
        demo.printResult();
    }

    void fillValues() {
        int s = 0;
        while (s < size) {
            int col = s;
            int row = s;

            int left = 0;
            int upper = 0;
            // 横向填充
            while (col < size) {
                if (col > 0) {
                    left = values[row][col - 1];
                }
                if (row > 0) {
                    upper = values[row - 1][col];
                }
                values[row][col] = table[row][col] + Math.min(left, upper);
                System.out.printf("fill (%s,%s). left:%s, upper:%s, sit:%s, result:%s\n", row, col, left, upper,
                        table[row][col], values[row][col]);
                col++;
            }
            row++;
            col = s;
            // 纵向填充
            left = 0;
            upper = 0;
            while (row < size) {
                if (col > 0) {
                    left = values[row][col - 1];
                }
                if (row > 0) {
                    upper = values[row - 1][col];
                }
                values[row][col] = table[row][col] + Math.min(left, upper);
                row++;
            }

            s++;
        }
    }

    void printResult() {
        System.out.println("result:");
        for (int r = 0; r<size; r++) {
            for (int c = 0; c<size; c++) {
                System.out.printf("%3d", values[r][c]);
            }
            System.out.print("\n");
        }
        System.out.println("min:" + values[size-1][size-1]);
    }
}
