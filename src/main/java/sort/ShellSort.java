package sort;

/**
 * 希尔排序
 * 
 * http://bubkoo.com/2014/01/15/sort-algorithm/shell-sort/
 * 希尔排序是插入排序的改进版本，因为插入排序一般来说是低效的--每次比较只能将数据移动一位
 * 它的算法思想是：而是初期选用大跨步（增量较大）间隔比较，使记录跳跃式接近它的排序位置；然后增量缩小；最后增量为 1
 * ，这样记录移动次数大大减少，提高了排序效率。
 * 
 * @author XinyuTian
 * @date 2017年5月7日
 **/
public class ShellSort {
    public static void sort(int[] array) {
        /**
         * 注意，切分的倍数要取2的幂次方
         */
        for (int gap = array.length/2; gap > 0; gap /= 2) {
            InsertionSort.insertSort(array, gap);
        }
    }
}
