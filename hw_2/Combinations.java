import java.util.ArrayList;
import java.util.List;

//Given two integers n and k, return all possible combinations of k numbers out of the range [1, n]

public class Combinations {
    public static void main(String[] args) {
        int n = 4;
        int k = 2;

        List<List<Integer>> comb = combine(n, k);
        System.out.println("Result: ");
        System.out.println(comb);
    }

    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> allComb = new ArrayList<>();
        genSubsequnce(new ArrayList<Integer>(), 1, n, k, allComb);

        return allComb;
    }

    private static void genSubsequnce(List<Integer> comb, int start, int n, int k,
            List<List<Integer>> allComb) {
        if (comb.size() == k) {
            allComb.add(new ArrayList<>(comb));
            return;
        }

        for (int i = start; i <= n; i++) {
            comb.add(i);
            genSubsequnce(comb, i + 1, n, k, allComb);
            comb.remove(comb.size() - 1);
        }
    }
}
