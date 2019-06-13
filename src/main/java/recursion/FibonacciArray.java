package recursion;

import java.util.HashMap;
import java.util.Map;

/**
 * 递归实例--斐波那契数列
 *
 * @Author: kevin
 * @Date: 2019/6/13
 */
public class FibonacciArray {

    Map<Integer, Integer> mem = new HashMap<>();

    public int fib(int n) {
        if (n == 0 || n == 1) {
            return n;
        }
        // 防止重复计算
        if (mem.get(n) != null) {
            return mem.get(n);
        }
        int r = fib(n - 1) + fib(n - 2);
        mem.put(n, r);
        return r;
    }

    public int[] fibArr(int count) {
        int[] arr = new int[count];
        for (int i=0; i<count; i++) {
            arr[i] = fib(i);
        }
        return arr;
    }
}
