package sort;

/**
 * 冒泡排序
 * 
 * 依次比较相邻的两个数，将小数放在前面，大数放在后面
 * 则每一轮过后右边的数为最大数
 * 冒泡排序，具有稳定性  
 * 时间复杂度为O(n^2）
 * 不及堆排序，快速排序 O(nlogn，底数为2)
 * 
 * @author XinyuTian
 * @date 2017年5月6日
 **/
public class BubbleSort {
    static void test(int[] array) {
        int i = 0;
        int j = 1;
        int temp = 0;
        int len = array.length;
        while (j < len - 1) {
            i = 0;
            while (i < len - j) {
                if (array[i] > array[i+1]) {
                    temp = array[i];
                    array[i] = array[i+1];
                    array[i+1] = temp;
                }
                i++;
            }
            j++;
        }
    }

    /**
     * RECOMMENDED edition
     *
     * @param items
     */
    public void sort(Items.Item[] items) {
        int right = items.length - 1;
        while (right > 0) {
            for (int i=0; i<right; i++) {
                if (items[i].value > items[i+1].value) {
                    Items.Item tmp = items[i];
                    items[i] = items[i + 1];
                    items[i+1]=tmp;
                }
            }
            right--;
        }
    }
    
    /**
     * 冒泡排序
     *
     * 时间复杂度: O(n+(n-1)+..+1) = O(n(n+1)/2) = O(n^2)
     * 
     * @param sort
     */
    public static void bubbleSort(int[] sort) {
        for (int i = 1; i < sort.length; i++) {
            for (int j = 0; j < sort.length - i; j++) {
                if (sort[j] > sort[j + 1]) {
                    int temp = sort[j + 1];
                    sort[j + 1] = sort[j];
                    sort[j] = temp;
                }
            }
        }
    }
}
