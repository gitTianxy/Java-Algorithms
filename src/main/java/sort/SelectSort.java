package sort;
/**
 * 选择排序
 * 
 * 思想：
 * 数组的左半部分是有序区, 右半部分是无序区
 * 每一轮找到右半部分的最小值，和右半部分最左端的值进行交换
 * 直到全部待排序的数据元素排完。
 * 
 * 选择排序是不稳定的排序方法。
 * 算法复杂度为O(n^2)
 * 空间复杂度O(1)
 * 
 * @author XinyuTian
 * @date 2017年5月6日
**/
public class SelectSort {
    
    /**
     * 数组的左半部分是有序区, 右半部分是无序区
     * 每一轮找到右半部分的最小值，和右半部分最左端的值进行交换
     * 
     * @param array
     */
    public static void ascendSort(int[] array) {
        int arrayLen = array.length;
        int orderLen = 0;
        while (orderLen < arrayLen) {
            int i = orderLen;
            int minIdx = orderLen;
            while (i < arrayLen) {
                if (array[minIdx] > array[i]) {
                    minIdx = i;
                }
                i++;
            }
            int temp = array[orderLen];
            array[orderLen] = array[minIdx];
            array[minIdx] = temp;
            orderLen++;
        }
    }
}
