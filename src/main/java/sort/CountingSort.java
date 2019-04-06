package sort;

/**
 * 计数排序
 *
 * 计数排序是一个非基于比较的排序算法，该算法于1954年由 Harold H. Seward 提出。
 *
 * 算法思想: 一种空间换时间的算法; 用一个临时数组, 其长度对应元素的取值范围(max-min+1),
 * i下标元素的取值表示原数组中取值为i+min的元素的个数,
 * 最后将临时数组中取值非0的数组下标从小到大按计数个数展开, 并加上偏移量, 即得到原数组的顺序排列结果
 *
 * 适用条件: 1.整数; 2.取值范围(max-min+1)较元素个数小
 *
 * 复杂度: O(n+k), 其中n为元素个数, k是整数的范围
 *
 * @Author: kevin
 * @Date: 2019/4/6
 */
public class CountingSort {
    public static void main(String[] args) {
        //排序的数组
        int a[] = {100, 93, 97, 92, 96, 99, 92, 89, 93, 97, 90, 94, 92, 95};
        int b[] = countSort(a);
        for (int i : b) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    /**
     * 算法思想
     *
     * 复杂度分析: O(3n+k), k=max-min+1
     *
     * @param a
     * @return
     */
    public static int[] countSort(int[] a) {
        int b[] = new int[a.length];
        int max = a[0], min = a[0];
        for (int i : a) {
            if (i > max) {
                max = i;
            }
            if (i < min) {
                min = i;
            }
        }
        int c[] = new int[max - min + 1];
        for (int i = 0; i < a.length; ++i) {
            c[a[i] - min] += 1;
        }

        for (int i = 1; i < c.length; ++i) {
            c[i] = c[i] + c[i - 1];
        }
        for (int i = a.length - 1; i >= 0; --i) {
            b[--c[a[i] - min]] = a[i];
        }
        return b;
    }
}
