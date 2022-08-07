import java.util.Arrays;

public class DuplicateZeros {
    public static void main(String[] args) {
        int[] arr3 = { 0, 0, 0, 0, 0, 0, 0 };
        int[] arr2 = { 8, 4, 5, 0, 0, 0, 0, 7 };
        int[] arr5 = { 0, 1, 7, 6, 0, 2, 0, 7 };
        int[] arr6 = { 1, 0, 2, 3, 0, 4, 5, 0 };
        int[] arr = { 0, 4, 1, 0, 0, 8, 0, 0, 3 };
        int[] arr4 = { 9, 9, 9, 4, 8, 0, 0, 3, 7, 2, 0, 0, 0, 0, 9, 1, 0, 0, 1, 1, 0,
                5, 6, 3, 1, 6, 0, 0, 2, 3, 4, 7, 0,
                3, 9, 3, 6, 5, 8, 9, 1, 1, 3, 2, 0, 0, 7, 3, 3, 0, 5, 7, 0, 8, 1, 9, 6, 3, 0,
                8, 8, 8, 8, 0, 0, 5, 0, 0,
                0, 3, 7, 7, 7, 7, 5, 1, 0, 0, 8, 0, 0 };

        System.out.println(Arrays.toString(arr));
        // duplicateZeros(arr);
        duplicateZerosV2(arr);
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

    public static void duplicateZerosV2(int[] arr) {
        int countShifts = 0;
        int newLength = arr.length - 1;

        for (int i = 0; i <= newLength - countShifts; i++) {
            if (arr[i] == 0) {
                if (i == newLength - countShifts) {
                    arr[newLength] = 0;
                    newLength--;
                    break;
                }
                countShifts++;
            }
        }

        if (countShifts == 0) {
            return;
        }

        int idx = newLength - countShifts;

        while (idx >= 0) {
            if (arr[idx] == 0) {
                arr[idx + countShifts] = 0;
                arr[idx + countShifts - 1] = 0;
                countShifts--;
            } else {
                arr[idx + countShifts] = arr[idx];
            }
            idx--;
        }
    }

    private static void shiftArray(int arr[], int fromPos) {
        for (int i = arr.length - 1; i > fromPos; i--) {
            arr[i] = arr[i - 1];
        }

    }
}