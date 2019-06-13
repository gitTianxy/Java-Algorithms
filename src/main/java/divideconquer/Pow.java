package divideconquer;

/**
 * @Description: 指数函数
 * @Author: kevin
 * @Date: 2019/6/13
 */
public class Pow {

    public double pow(int x, int n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return x;
        }
        // 负指数处理
        if (n < 0) {
            return 1.0 / pow(x, -n);
        }
        // 底数不变，指数变
        double r = pow(x, n / 2);
        if (n % 2 == 0) {
            return r * r;
        } else {
            return r * r * x;
        }
    }

    public double pow2(int x, int n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return x;
        }
        // 负指数处理
        if (n < 0) {
            return 1.0 / pow(x, -n);
        }
        // 底数和指数切割
        if (n % 2 == 0) {
            return pow2(x * x, n / 2);
        } else {
            return x * pow2(x * x, n / 2);
        }
    }

    /**
     * 循环实现分治
     *
     * @param x
     * @param n
     * @return
     */
    public double powNonRecursive(double x, int n) {
        if (n < 0) {
            x = 1.0 / x;
            n = -n;
        }
        double pow = 1;
        while (n > 0) {
            if ((n & 1) == 1) {
                pow *= x;
            }
            x *= x;
            n >>= 1;
        }
        return pow;
    }
}
