package sort;

/**
 * 插入排序
 * 
 * 思想：
 * 把数组划分成已排序(左侧,从小到大排序)和未排序(右侧)的两部分
 * 取出未排序数据中最左端的元素，在已排序序列中从后向前扫描，找到相应位置并插入。
 * 方法：只要找到有序序列中小于等于插入元素的元素，该元素的后面就是插入元素应该的位置
 * 算法时间复杂度为O(n^2)。
 * 是稳定的排序方法。
 * 
 * @author XinyuTian
 * @date 2017年5月7日
 **/
public class InsertionSort {
    public static void sort(int[] array) {
        insertSort(array, 1);
    }

    public static void insertSort(int[] array, int gap) {
        int insertVal, insertPosition, len = array.length;
        // 认为gap开始的都是无序的
        for (int i = gap; i < len; i+=gap) {
            insertVal = array[i];
            insertPosition = i;
            while (insertPosition >= gap && array[insertPosition - gap] > insertVal) {
                array[insertPosition] = array[insertPosition - gap];
                insertPosition -= gap;
            }
            array[insertPosition] = insertVal;
        }
    }
}
