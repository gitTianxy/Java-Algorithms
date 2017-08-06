package sort;

import java.util.Random;

/**
 * 主要的排序算法有八种：
 * 冒泡排序，快速排序（这两种统称为交换排序）
 * 直接选择排序，堆排序（这两种统称为选择排序）
 * 直接插入排序，希尔排序（这两种统称为插入排序）
 * 归并排序
 * 基数排序。
 * 
 * @author XinyuTian
 * @date 2017年5月6日
 **/
public class Test {
    
    public static void main(String[] args) {
        Random ran = new Random();
        int[] array = new int[33];
        System.out.print("排序前的数组为：");
        for (int i = 0; i < array.length; i++) {
            array[i] = ran.nextInt(500);
            System.out.print(array[i] + " ");
        }
        int[] sort = new int[array.length];
        System.out.println();
        System.out.println("----------------------------");
        // 交换排序_冒泡排序
        for (int i = 0; i < sort.length; i++) {
            sort[i] = array[i];
        }
        BubbleSort.bubbleSort(sort);
        System.out.print("冒泡排序后的数组为：");
        for (int i : sort) {
            System.out.print(i + " ");
        }
        // 交换排序_快速排序
        for (int i = 0; i < sort.length; i++) {
            sort[i] = array[i];
        }
        QuickSort.quicksort(sort, 0, sort.length - 1);
        System.out.println();
        System.out.print("快速排序后的数组为：");
        for (int i : sort) {
            System.out.print(i + " ");
        }
        // 选择排序_直接选择排序
        for (int i = 0; i < sort.length; i++) {
            sort[i] = array[i];
        }
        SelectSort.ascendSort(sort);
        System.out.println();
        System.out.print("选择排序后的数组为：");
        for (int i : sort) {
            System.out.print(i + " ");
        }
        // 选择排序_堆排序
        for (int i = 0; i < sort.length; i++) {
            sort[i] = array[i];
        }
        HeapSort.sort(sort);
        System.out.println();
        System.out.print("堆排序后的数组为：");
        for (int i : sort) {
            System.out.print(i + " ");
        }
        // 插入排序_直接插入排序
        for (int i = 0; i < sort.length; i++) {
            sort[i] = array[i];
        }
        InsertionSort.sort(sort);
        System.out.println();
        System.out.print("插入排序后的数组为：");
        for (int i : sort) {
            System.out.print(i + " ");
        }
        // 插入排序_希尔排序
        for (int i = 0; i < sort.length; i++) {
            sort[i] = array[i];
        }
        ShellSort.sort(sort);
        System.out.println();
        System.out.print("希尔排序后的数组为：");
        for (int i : sort) {
            System.out.print(i + " ");
        }
        // 归并排序
        for (int i = 0; i < sort.length; i++) {
            sort[i] = array[i];
        }
        MergeSort.sort(sort);
        System.out.println();
        System.out.print("归并排序后的数组为：");
        for (int i : sort) {
            System.out.print(i + " ");
        }
        // 基数排序
        for (int i = 0; i < sort.length; i++) {
            sort[i] = array[i];
        }
        RadixSort.sort(sort);
        System.out.println();
        System.out.print("基数排序后的数组为：");
        for (int i : sort) {
            System.out.print(i + " ");
        }
        // 桶排序
        for (int i = 0; i < sort.length; i++) {
            sort[i] = array[i];
        }
        BucketSort.sort(sort);
        System.out.println();
        System.out.print("桶排序后的数组为：");
        for (int i : sort) {
            System.out.print(i + " ");
        }
        
    }
}
