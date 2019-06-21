package sort;

/**
 * 归并排序
 * 思想：
 * 迭代地将相邻的有序序列归并成大的有序序列；从相邻两个元素开始，知道最后剩下一个有序序列
 * 
 * @author XinyuTian
 * @date 2017年5月7日
 **/
public class MergeSort {
    /**
     * port
     * 
     * @param array
     */
    public static void mergeSort(int[] array) {
//        int[] tempArray = new int[array.length];
//        recursiveMerge(array, tempArray, 0, array.length - 1);
//        iterativeMerge(array);
        sort(array, 0, array.length - 1);
    }

    /**
     * RECOMMENDED: local, stable
     *
     * @param nums
     * @param start
     * @param end
     */
    public static void sort(int[] nums, int start, int end) {
        if (start == end) {
            return;
        }
        int median = start + (end - start) / 2;
        sort(nums, start, median);
        sort(nums, median + 1, end);
        int c1 = start;
        int c2 = median + 1;
        int tmp;
        while (c1<c2 && c2 <= end) {
            if (nums[c1] <= nums[c2]) {
                c1++;
            } else {
                tmp = nums[c2];
                System.arraycopy(nums, c1, nums, c1 + 1, c2 - c1);
                nums[c1] = tmp;
                c2++;
                c1++;
            }
        }
    }

    /**
     * 迭代算法
     * 归并过程
     * [0,0],[1,1] [2,2],[3,3]
     * [0,1],[2,3] [4,5],[6,7]
     * [0,3],[4,7] [8,11],[12,15]
     * ...
     * [0,len/2-1],[len/2,len-1]
     * @param array
     */
    public static void iterativeMerge(int[] array) {
        int len = array.length;
        int[] tempArray = new int[len];
        int gap = 1;
        int span = gap - 1;
        while (gap < len) {
            int i=0;
            while(i<len-span-1) {
                if (i+gap+span>len-1) {
                    mergeArray(array, tempArray, i, i+span, len-1);
                    break;
                } else {
                    mergeArray(array, tempArray, i, i+span, i+gap+span);
                }
                i = i+gap+span + 1;
            }
            gap = gap*2;
            span = gap-1;
        }
    }

    /**
     * 递归算法 
     * 数组首先被切成单个单个的元素，然后再归并。 
     * 首先对左半部分进行归并排序，然后对右半部分进行归并排序，
     * 最后整体归并。
     * 
     * @param array
     * @param tempArray
     * @param left
     * @param right
     */
    public static void recursiveMerge(int[] array, int[] tempArray, int left,
            int right) {
        if (left < right) {
            int center = (left + right) / 2;
            recursiveMerge(array, tempArray, left, center);
            recursiveMerge(array, tempArray, center + 1, right);
            mergeArray(array, tempArray, left, center, right);
        }
    }

    /**
     * 将数组中的[start,mid],[mid+1,end]两个部分进行有序归并 其中temp是一个和a[]等大的数组
     * 
     * @param a
     * @param start
     * @param mid
     * @param end
     * @param temp
     */
    static void mergeArray(int a[], int temp[], int start, int mid, int end) {
        int i = start, j = mid + 1;
        int k = 0;

        while (i <= mid && j <= end) {
            if (a[i] < a[j])
                temp[k++] = a[i++];
            else
                temp[k++] = a[j++];
        }
        while (i <= mid)
            temp[k++] = a[i++];
        while (j <= end)
            temp[k++] = a[j++];

        for (i = 0; i < k; i++)
            a[start + i] = temp[i];
    }
}
