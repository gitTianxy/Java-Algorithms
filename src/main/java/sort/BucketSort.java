package sort;

/**
 * 桶排序
 * 
 * 原理：
 * 1. 将数组分到有限数量的桶子里，
 * 2. 然后对每个桶子再分别排序（有可能再使用别的排序算法或是以递归方式继续使用桶排序进行排序），
 * 3. 最后将各个桶中的数据有序的合并起来。
 * 
 * 另：
 * 本例是每个桶的大小固定为10
 * 如果用递归，则设置每次切分桶的个数固定
 *
 * 适用条件:
 * 1. 排序数目巨大, 每次只能读取部分到内存实现排序
 * 2. 数列取值分布较均匀
 * 
 * @author XinyuTian
 * @date 2017年5月7日
 **/
public class BucketSort {
    final static int BUCKET_CAPACITY = 10;

    /**
     * 时间复杂度: O(n+n+m*f(n/m)), m为桶个数,f(n/m)为桶内部排序的时间复杂度;
     * 因为f(n/m)取值可为 O(n/m), O(n/mlog(n/m)), O((n/m)^2)
     * 所以桶排序的时间复杂度可为 O(3n), O(2n+nlog(n/m)), O(2n+n^2/m);
     * 若取m->n, 则上述复杂度一致退化为 O(n), 但同时空间复杂度由O(m*n)增加为O(n^2)
     *
     * @param arr
     */
    public static void sort(int[] arr) {
        int max = arr[0], min = arr[0];
        int arrayLen = arr.length;
        for (int a : arr) {
            if (max < a)
                max = a;
            if (min > a)
                min = a;
        }
        // define bucket
        int bucketNum = max / BUCKET_CAPACITY - min / BUCKET_CAPACITY + 1;
        int[][] buckets = new int[bucketNum][arrayLen];
        int[] counts = new int[bucketNum];
        // push into the bucket
        int bucketIdx;
        for (int i = 0; i < arrayLen; i++) {
            bucketIdx = (arr[i] - min)/BUCKET_CAPACITY;
            buckets[bucketIdx][counts[bucketIdx]] = arr[i];
            counts[bucketIdx]++;
        }
        // sort elements in bucket & put it into array
        int k = arrayLen - 1;
        int[] bucket;
        for (int i = bucketNum-1; i >= 0; i--) {
            bucket = buckets[i];
            InsertionSort.insertSort(bucket, 1);
            for(int j=0; j<counts[i]; j++) {
                arr[k--] = bucket[arrayLen-j-1];
            }
        }
    }
}
