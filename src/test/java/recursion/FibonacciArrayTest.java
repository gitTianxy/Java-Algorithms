package recursion;

import org.junit.Test;

/**
 * @Author: kevin
 * @Date: 2019/6/13
 */
public class FibonacciArrayTest {

    FibonacciArray fa = new FibonacciArray();

    @Test
    public void fib() {
        System.out.println(fa.fib(19));
    }

    @Test
    public void fibArr() {
        for (int fib : fa.fibArr(20)) {
            System.out.print(fib + " ");
        }
    }
}
