public class Test1 {

    public static void main(String[] args) {
        int[] nums = new int[] {0, 1, 2, 2, 3, 0, 4, 2};
        int val = 2;
        removeElement(nums, val);
    }

    public static int removeElement(int[] nums, int val) {
        if (nums.length == 0) {
            return 0;
        }

        int lastIndex = nums.length - 1;
        int preIndex = 0;
        int count = 0;
        while (preIndex <= lastIndex) {
            if (nums[preIndex] != val) {
                preIndex++;
            } else {
                count++;
                nums[preIndex] = nums[lastIndex];
                nums[lastIndex] = val;
                lastIndex--;
            }
        }
        return count;
    }
}
