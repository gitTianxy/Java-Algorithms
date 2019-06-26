package search;

/**
 * binary search:
 * 1. find 1st equals
 * 2. find last equals
 * 3. 1st equalsOrGreater
 * 4. last equalsOrLess
 *
 * @Author: kevin
 * @Date: 2019/6/26
 */
public class BinarySearch {

    public int firstEquals(int[] nums, int target) {
        int lower = 0;
        int upper = nums.length - 1;
        // note
        while (lower <= upper) {
            int mid = lower + (upper - lower) / 2;
            if (nums[mid] == target) {
                int pos = mid;
                // note
                while (pos > 1 && nums[pos - 1] == target) {
                    pos--;
                }
                return pos;
            } else if (nums[mid] > target) {
                upper = mid - 1;
            } else {
                lower = mid + 1;
            }
        }
        return -1;
    }

    public int lastEquals(int[] nums, int target) {
        int lower = 0;
        int upper = nums.length - 1;
        // note
        while (lower <= upper) {
            int mid = lower + (upper - lower) / 2;
            if (nums[mid] == target) {
                int pos = mid;
                // note
                while (pos < nums.length - 1 && nums[pos + 1] == target) {
                    pos++;
                }
                return pos;
            } else if (nums[mid] > target) {
                upper = mid - 1;
            } else {
                lower = mid + 1;
            }
        }
        return -1;
    }

    public int firstEqualsOrGreater(int[] nums, int target) {
        // note
        if (nums[0] >= target) {
            return 0;
        } else if (nums[nums.length - 1] < target) {
            return -1;
        }
        int lower = 1;
        int upper = nums.length - 1;
        // note
        while (lower <= upper) {
            int mid = lower + (upper - lower) / 2;
            // note
            if (nums[mid] >= target) {
                if (mid == 0 || nums[mid - 1] < target) {
                    return mid;
                } else {
                    upper = mid - 1;
                }
            } else {
                lower = mid + 1;
            }
        }
        return -1;
    }

    public int lastEqualsOrLess(int[] nums, int target) {
        // note
        if (nums[0] > target) {
            return -1;
        } else if (nums[nums.length - 1] <= target) {
            return nums.length - 1;
        }
        int lower = 0;
        int upper = nums.length - 2;
        while (lower <= upper) {
            int mid = lower + (upper - lower) / 2;
            if (nums[mid] <= target) {
                if (nums[mid + 1] > target) {
                    return mid;
                } else {
                    lower = mid + 1;
                }
            } else {
                upper = mid - 1;
            }
        }
        return -1;
    }

}
