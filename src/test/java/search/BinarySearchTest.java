package search;

import org.junit.Test;

/**
 * @Author: kevin
 * @Date: 2019/6/26
 */
public class BinarySearchTest {

    BinarySearch bs = new BinarySearch();
    int[] nums = {-1, 1, 3, 3, 3, 4, 5, 5, 6, 13};

    @Test
    public void equals() {
        for (int i = -5; i < 20; i += 2) {
            System.out.printf("first equals %s: %s\n", i, bs.firstEquals(nums, i));
            System.out.printf("last equals %s: %s\n", i, bs.lastEquals(nums, i));
        }
    }

    @Test
    public void notEquals() {
        for (int i = -5; i < 20; i += 2) {
            System.out.printf("first >= %s: %s\n", i, bs.firstEqualsOrGreater(nums, i));
            System.out.printf("last <= %s: %s\n", i, bs.lastEqualsOrLess(nums, i));
        }
    }
}
