package sort;

/**
 * 堆排序
 * 
 * http://bubkoo.com/2014/01/14/sort-algorithm/heap-sort/
 * 
 * @author XinyuTian
 * @date 2017年5月6日
 **/
public class HeapSort {

    /**
     * 按parent, left-son, right-son为单位进行处理
     * 如果parent位置不是最大值, 交换parent节点和最大值节点的值, 然后以最大值节点为parent进行递归
     * 
     * @param array
     * @param index
     * @param heapSize
     */
    static void maxHeapify(int[] array, int index, int heapSize) {
        int iMax = index;
        int iLeft = 2 * index + 1;
        int iRight = 2 * (index + 1);
        if (iLeft < heapSize && array[index] < array[iLeft]) {
            iMax = iLeft;
        }
        if (iRight < heapSize && array[iMax] < array[iRight]) {
            iMax = iRight;
        }
        if (iMax != index) {
            int temp = array[iMax];
            array[iMax] = array[index];
            array[index] = temp;
            maxHeapify(array, iMax, heapSize);
        }
    }

    static void sort(int[] array) {
        /**
         * 第一步：将数组堆化
         * beginIndex = 第一个非叶子节点
         */
        for (int i = array.length / 2 - 1; i >= 0; i--) {
            maxHeapify(array, i, array.length);
        }
        /**
         * 第二步：对堆化数据排序
         */
        for (int i = array.length - 1; i > 0; i--) {
            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;
            maxHeapify(array, 0, i);
        }
    }
}
