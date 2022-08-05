public class RemoveElement {
    public static void main(String[] args) {
        int[] nums = { 5 };
        int val = 5;

        int newLength = removeElement(nums, val);

        System.out.printf("New length: %d \n", newLength);
        for (int i = 0; i < newLength; i++) {
            System.out.printf("%d ", nums[i]);
        }
        System.out.println("");
    }

    public static int removeElement(int[] nums, int val) {
        int newLength = 0;
        int low = 0;
        int high = nums.length - 1;

        while (low <= high) {
            if (nums[low] != val) {
                newLength++;
                low++;
            } else {
                if (nums[high] == val) {
                    high--;
                } else {
                    int tmp = nums[low];
                    nums[low] = nums[high];
                    nums[high] = tmp;
                }
            }
        }

        return newLength;
    }
}