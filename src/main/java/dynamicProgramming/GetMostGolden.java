package dynamicProgramming;

import java.util.Arrays;

/**
 * TODO
 * 
 * @author XinyuTian
 * @date 2017年6月20日
 **/
public class GetMostGolden {
    int quartNum;// 金矿数量
    Integer[] goldens;// 每个金矿的黄金储量
    Integer[] peoples;// 每个金矿需要的人数
    double peoplePerGolden = 0.1;// 单位金矿需要的人数
    Integer[] ifDigs;// 记录每个矿是否挖：0表示不挖，1表示挖
    int workerNum;// 矿工人数

    public GetMostGolden(int quartNum, int workerNum) {
        super();
        this.quartNum = quartNum;
        this.goldens = new Integer[quartNum];
        this.ifDigs = new Integer[quartNum];
        this.peoples = new Integer[quartNum];
        /**
        for (int i = 0; i < quartNum; i++) {
            goldens[i] = (int) Math.round(100 * Math.random());
            ifDigs[i] = 0;
            peoples[i] = (int) Math.round(goldens[i] * peoplePerGolden);
        }
        **/
        this.workerNum = workerNum;
    }

    /**
     * '最大开采量'和金矿数目n及投入工人数相关，可以表示为f(n,w)。
     * 则问题转换为求n=quartNum,w=workerNum时f(n,w)的最大值
     * 
     * 动态规划求解思路： 1. 最优子: f(n,w) 2. 边界条件: f(0,w),f(1,w); 3. 状态转移方程: f(n,w) vs
     * f(n-1,w)
     * 
     */
    public int getMost() {
        int[] preLine = new int[workerNum];
        int[] currline = new int[workerNum];
        // fill 1st line
        for (int w = 0; w < workerNum; w++) {
            if ((w + 1) < peoples[0]) {
                preLine[w] = 0;
            } else {
                preLine[w] = goldens[0];
            }
        }
        // fill other lines
        for (int g = 1; g < goldens.length; g++) {
            for (int w = 0; w < workerNum; w++) {
                if ((w + 1) < peoples[g]) {
                    currline[w] = preLine[w];
                } else if (w + 1 == peoples[g]) {
                    currline[w] = Math.max(preLine[w], goldens[g]);
                } else {
                    currline[w] = Math.max(preLine[w],
                            preLine[w - peoples[g]] + goldens[g]);
                }
            }
            preLine = currline;
        }
        return currline[workerNum - 1];
    }

    public static void main(String[] args) {
        GetMostGolden test = new GetMostGolden(5, 10);
        test.goldens = Arrays.asList(200,300,350,400,500).toArray(new Integer[5]);
        test.peoples = Arrays.asList(3,4,3,5,5).toArray(new Integer[5]);
        System.out.println("get most: " + test.getMost());
    }
}
