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
     * RECOMMENDED!
     *
     * @param items
     */
    public void sort(Items.Item[] items) {
        int left = 0;
        while (left < items.length - 1) {
            // 遇到连续相同元素
            while (left > 0 && items[left].value == items[left - 1].value) {
                left++;
                continue;
            }
            int minPos = findMin(items, left);
            if (minPos > left) {
                Items.Item min = items[minPos];
                items[minPos] = items[left];
                items[left] = min;
            }
            left++;
        }
    }

    public int findMin(Items.Item[] items, int start) {
        int minPos = start;
        for (int i=start+1; i<items.length; i++) {
            if (items[minPos].value > items[i].value) {
                minPos = i;
            }
        }
        return minPos;
    }
    
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
