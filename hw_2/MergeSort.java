import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        int[] nums = { 5, 2, 2, 3, 1 };
        int[] sortedNums = sort(nums);

        System.out.println(Arrays.toString(nums));
        System.out.println(Arrays.toString(sortedNums));
    }

    public static int[] sort(int[] arr) {
        int[] res = new int[arr.length];
        System.arraycopy(arr, 0, res, 0, arr.length);

        if (arr.length == 1) {
            return res;
        }

        int[] buf = new int[arr.length];

        mergeSort(res, buf, 0, arr.length - 1);
        return res;
    }

    private static void mergeSort(int[] arr, int[] buffer, int left, int right) {
        if (left >= right) {
            return;
        }
        int middle = (left + right) / 2;

        mergeSort(arr, buffer, left, middle);
        mergeSort(arr, buffer, middle + 1, right);

        merge(arr, buffer, left, middle, right);

    }

    private static void merge(int[] arr, int[] buffer, int left, int middle, int right) {
        int leftIdx = left;
        int rightIdx = middle + 1;
        int i = left;

        while (leftIdx <= middle && rightIdx <= right) {
            if (arr[leftIdx] < arr[rightIdx]) {
                buffer[i++] = arr[leftIdx++];
            } else {
                buffer[i++] = arr[rightIdx++];
            }
        }

        while (leftIdx <= middle) {
            buffer[i++] = arr[leftIdx++];
        }
        while (rightIdx <= right) {
            buffer[i++] = arr[rightIdx++];
        }

        for (int j = left; j <= right; j++) {
            arr[j] = buffer[j];
        }
    }
}
