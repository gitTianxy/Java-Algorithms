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
        demo.fillValues();
        demo.printResult();
    }

    void fillValues() {
        // 填充第一行
        int sum = 0;
        for (int col=0; col<size; col++) {
            sum += table[0][col];
            values[0][col] = sum;
        }
        // 填充第一列
        sum = 0;
        for (int row=0; row<size; row++) {
            sum += table[row][0];
            values[row][0] = sum;
        }
        // 填充其余空格
        for (int row=1; row<size; row++) {
            for (int col=1; col<size; col++) {
                values[row][col] = table[row][col] + Math.min(values[row-1][col], values[row][col - 1]);
            }
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
