import java.util.Arrays;

public class DuplicateZeros {
    public static void main(String[] args) {
        int[] arr = { 8, 4, 5, 0, 0, 0, 0, 7 };

        System.out.println(Arrays.toString(arr));
        duplicateZeros(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void duplicateZeros(int[] arr) {
        int i = 0;
        while (i < arr.length) {
            if (arr[i] == 0 && (i + 1) != arr.length) {
                shiftArray(arr, i + 1);
                arr[i + 1] = 0;
                i++;
            }
            i++;
        }
    }

    private static void shiftArray(int arr[], int fromPos) {
        for (int i = arr.length - 1; i > fromPos; i--) {
            arr[i] = arr[i - 1];
        }

    }
}