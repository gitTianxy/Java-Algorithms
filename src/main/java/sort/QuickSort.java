package sort;

/**
 * 快速排序
 * 
 * 快排是稳定排序
 * 算法复杂度O(nlogn)
 * 
 * @author XinyuTian
 * @date 2017年4月28日
 **/
public class QuickSort {

    public static void quicksort(int n[], int left, int right) {
        int dp;
        if (left < right) {
            dp = locatePivot(n, left, right);
            // dp坐标处的元素已定位,接下来只要处理Pivot左侧和右侧的序列即可
            quicksort(n, left, dp - 1);
            quicksort(n, dp + 1, right);
        }
    }

    /**
     * 首先确认一点,[left,right]范围内的所有元素在排完序后仍然在[left,right]范围内
     * 此函数的功能：
     * 将[left,right]的元素和n[left]进行一系列的比较和位置交换，
     * 使小于n[left]的被放在n[left]的左边,大于n[left]的被放在它的右边；
     * 最后，n[left]所处的位置就是它排序后的位置
     * 
     * @param n
     * @param left
     * @param right
     * @return
     */
    static int locatePivot(int n[], int left, int right) {
        int pivot = n[left];
        while (left < right) {
            while (left < right && n[right] >= pivot)
                right--;
            if (left < right)
                n[left++] = n[right];

            while (left < right && n[left] <= pivot)
                left++;
            if (left < right)
                n[right--] = n[left];
        }
        n[left] = pivot;
        return left;
    }
}
