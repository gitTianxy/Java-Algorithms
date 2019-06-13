package divideconquer;

import org.junit.Test;

/**
 * @Author: kevin
 * @Date: 2019/6/13
 */
public class PowTest {

    Pow pow = new Pow();

    @Test
    public void pow() {
        int x = 2;
        for (int n=-5; n<10; n++) {
            System.out.printf("%s^%s: %s\n", x, n, pow.pow2(x, n));
        }
    }
    @Test
    public void pow2() {
        int x = 2;
        for (int n=-5; n<10; n++) {
            System.out.printf("%s^%s: %s\n", x, n, pow.powNonRecursive(x, n));
        }
    }
}
