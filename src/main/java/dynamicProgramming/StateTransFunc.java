package dynamicProgramming;

/**
 * 状态转移方程法
 *
 * @Author: XinyuTian
 * @Date: 2019/5/16
 */
public class StateTransFunc {
    final int size = 4;
    final int[][] table = {{1, 3, 5, 9}, {2, 1, 3, 4}, {5, 2, 6, 7}, {6, 8, 4, 3}};

    public static void main(String[] args) {
        StateTransFunc demo = new StateTransFunc();
        int r = demo.min_dist(3, 3);
        System.out.println("min:" + r);
    }

    int min_dist(int row, int col) {
        if (row == 0 && col == 0) {
            return table[0][0];
        }
        if (row == 0) {
            return min_dist(row, col - 1) + table[row][col];
        }
        if (col == 0) {
            return min_dist(row - 1, col) + table[row][col];
        }
        int left = min_dist(row, col - 1);
        int upper = min_dist(row - 1, col);
        return table[row][col] + Math.min(left, upper);
    }

    int min_dist2(int row, int col) {
        if (row == 0 && col == 0) {
            return table[0][0];
        }
        // 不是实际值, 纯粹为了代码形式一致性
        int left = Integer.MAX_VALUE;
        if (col > 0) {
            left = min_dist(row, col - 1);
        }
        // 不是实际值, 纯粹为了代码形式一致性
        int upper = Integer.MAX_VALUE;
        if (row > 0) {
            upper = min_dist(row - 1, col);
        }
        return table[row][col] + Math.min(left, upper);
    }
}
