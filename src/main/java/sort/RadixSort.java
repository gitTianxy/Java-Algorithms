package sort;

/**
 * 基数排序
 * 
 * 基本思想：
 * 将所有待比较数值(注意,必须是正整数)统一为同样的数位长度,数位较短的数前面补零. 
 * 然后, 从最低位开始, 依次进行一次稳定排序。
 * 这样从最低位排序一直到最高位排序完成以后, 数列就变成一个有序序列. 
 * 算法的时间复杂度是O(n).
 * ref:
 * http://www.cnblogs.com/sun/archive/2008/06/26/1230095.html
 * 
 * 思考：
 * 一般的做法是n个数做一次排序，现在变成如果最大位数为k,则要做k次排序;
 * 所以我想，除了按位来排序外，一定还有什么简化每次排序的技巧--即桶排序
 * 
 * @author XinyuTian
 * @date 2017年5月7日
 */
public class RadixSort {
    
    public static void sort(int[] array) {
        // find max
        int max = array[0];
        for (int i : array) {
            if (i>max) {
                max = i;
            }
        }
        int d = 1;
        while (true) {
            d*=10;
            if (max/10 == 0) {
                break;
            }
            max/=10;
        }
        int n = 1;// 取位数所用的底,依次为：1,10,100...
        int length = array.length;
        /**
         * 排序桶
         * 由于每一位总共有10种可能取值，所以总共创建10个桶，每个桶最多可以放array.length个元素
         * 之后把同位取值相同的数放入一个桶里
         */
        int[][] bucket = new int[10][length];
        int[] order = new int[10];// 用于保存每个桶里有多少个数字
        while (n < d) {
            // 将数组array里的每个数字放在相应的桶里
            for (int num : array) 
            {
                int digit = (num / n) % 10;
                bucket[digit][order[digit]] = num;
                order[digit]++;
            }
            // 将前一个循环生成的桶里的数据覆盖到原数组中用于保存这一位的排序结果
            int k = 0;
            for (int i = 0; i < 10; i++)
            {
                if (order[i] != 0) // 这个桶里有数据，从上到下遍历这个桶并将数据保存到原数组中
                {
                    for (int j = 0; j < order[i]; j++) {
                        array[k] = bucket[i][j];
                        k++;
                    }
                }
                order[i] = 0;// 将桶里计数器置0，用于下一次位排序
            }
            // 开始下一位的桶排序
            n *= 10;
        }
    }
}