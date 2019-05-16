package dynamicProgramming;

/**
 * 问题: 背包总承重W, 给出一众物品及其对应重量weights[i],
 * 求背包内能装入的最大物品重量
 *
 * @Author: XinyuTian
 * @Date: 2019/5/16
 */
public class KnapSackBasic {

    final int capacity = 9;
    final int[] weights = {2, 2, 4, 6, 3};
    final boolean[] group = new boolean[weights.length];
    final boolean[][] states = new boolean[weights.length][capacity + 1];

    public static void main(String[] args) {
        KnapSackBasic knapSack = new KnapSackBasic();
        knapSack.calc();
        knapSack.printResult();
    }

    void calc() {
        for (int i = 0; i < weights.length; i++) {
            states[i][0] = true;
        }
        // i=0
        states[0][0] = true;
        if (weights[0] <= capacity) {
            states[0][weights[0]] = true;
        }
        // get max
        for (int i = 1; i < weights.length; i++) {
            for (int j = 0; j < capacity; j++) {
                if (states[i - 1][j] == true) {
                    // 不放入
                    states[i][j] = true;
                    // 放入
                    if (j + weights[i] <= capacity) {
                        states[i][j + weights[i]] = true;
                    }
                } else if (weights[i] <= capacity) {
                    states[i][weights[i]] = true;
                }
            }
        }

        // get group
        int m = max();
        int tmp = m;
        for (int i=weights.length - 1; i>=0; i--) {
            if (tmp-weights[i]>=0 && states[i][tmp-weights[i]] == true) {
                group[i] = true;
                tmp -= weights[i];
            }
        }

    }

    void printResult() {
        // list states
        for (int i = 0; i < weights.length; i++) {
            for (int j = 0; j <= capacity; j++) {
                System.out.print((states[i][j] == true ? 1 : 0) + " ");
            }
            System.out.print("\n");
        }
        // print max
        System.out.println("max:" + max());
        // print group
        System.out.print("group:");
        for (int i=0; i<weights.length; i++) {
            if (group[i] == true) {
                System.out.print(i + ",");
            }
        }
    }

    int max() {
        for (int i = capacity; i > 0; i--) {
            if (states[weights.length - 1][i] == true) {
                return i;
            }
        }
        return 0;
    }

}
