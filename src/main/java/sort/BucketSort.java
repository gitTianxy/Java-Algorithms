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
 * @author XinyuTian
 * @date 2017年5月7日
 **/
public class BucketSort {
    final static int BUCKET_CAPACITY = 10;

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
