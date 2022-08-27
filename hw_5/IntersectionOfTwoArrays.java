import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IntersectionOfTwoArrays {
    public static void main(String[] args) {
        // int[] nums1 = { 4, 9, 5 };
        // int[] nums2 = { 9, 4, 9, 8, 4 };
        int[] nums1 = { 1, 2, 2, 1 };
        int[] nums2 = { 2, 2 };

        int[] inter = intersect(nums1, nums2);

        System.out.println(Arrays.toString(inter));
    }

    public static int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> numsMap = new HashMap<>();

        if (nums1.length > nums2.length) {
            int[] tmp = nums1;
            nums1 = nums2;
            nums2 = tmp;
        }

        for (var value : nums1) {
            numsMap.put(value, numsMap.getOrDefault(value, 0) + 1);
        }

        List<Integer> resList = new ArrayList<>();

        for (var value : nums2) {
            if (numsMap.containsKey(value) && numsMap.get(value) > 0) {
                resList.add(value);
                numsMap.put(value, numsMap.get(value) - 1);
            }
        }

        int[] res = new int[resList.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = resList.get(i);
        }

        return res;
    }
}