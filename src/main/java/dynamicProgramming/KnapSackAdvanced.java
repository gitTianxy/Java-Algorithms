package dynamicProgramming;

/**
 * 问题: 背包总承重W, 给出一众物品及其对应重量weights[i]和价值values[i],
 * 请求背包内能装入的最大价值
 *
 * @Author: XinyuTian
 * @Date: 2019/5/16
 */
public class KnapSackAdvanced {
    final int capacity = 9;
    final int num = 5;
    final int[] weights = {2, 2, 4, 6, 3};
    final int[] values = {3, 4, 8, 9, 6};
    final int[][] states = new int[num][capacity + 1];
    int maxValue = -1;

    public static void main(String[] args) {
        KnapSackAdvanced knapSack = new KnapSackAdvanced();
        knapSack.calc();
        knapSack.printResult();
    }

    void calc() {
        states[0][0] = 0;
        if (weights[0] <= capacity) {
            states[0][weights[0]] = values[0];
        }
        for (int i = 1; i < num; i++) {
            // 基于states[i-1]推导states[i]的取值
            for (int j = 0; j <= capacity; j++) {
                if (states[i - 1][j] == 0 && j == weights[i]) {
                    states[i][weights[i]] = values[i];
                } else if (states[i - 1][j] > 0) {
                    if ((j + weights[i]) <= capacity) {
                        states[i][j + weights[i]] = states[i - 1][j] + values[i];
                    }
                    if (j == weights[i]) {
                        states[i][j] = Math.max(values[i], states[i - 1][j]);
                    } else {
                        states[i][j] = states[i - 1][j];
                    }
                }

            }
        }
    }

    int getMaxValue() {
        if (maxValue == -1) {
            for (int i = 0; i <= capacity; i++) {
                if (maxValue < states[num - 1][i]) {
                    maxValue = states[num - 1][i];
                }
            }
        }
        return maxValue;
    }

    void printResult() {
        for (int i = 0; i < num; i++) {
            for (int j = 0; j <= capacity; j++) {
                System.out.printf("%2d ", states[i][j]);
            }
            System.out.print("\n");
        }
        System.out.println("max value:" + getMaxValue());
    }
}
