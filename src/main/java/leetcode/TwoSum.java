package leetcode;

/**
 * @Description: TODO
 * @Author: kevin
 * @Date: 2019/6/2
 */
public class TwoSum {
    public static void main(String[] args) {
        TwoSum twoSum = new TwoSum();
        int[] nums = new int[] {2, 7, 11, 15};
        int target = 9;
        for (int indice : twoSum.twoSum(nums, target)) {
            System.out.println(indice);
        }
    }

    public int[] twoSum(int[] nums, int target) {
        for(int i=0; i<nums.length; i++) {
            for (int j=i+1; j<nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[0];
    }

}
