import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class ValidParentheses {
    public static void main(String[] args) {
        String[] testExpr = { "()", "()[]{[]}", ")([]]" };

        for (String expr : testExpr) {
            System.out.printf("Expression: %s - %s\n", expr, isValid(expr));
        }

    }

    private static boolean isValid(String expression) {
        Map<Character, Character> brackets = new HashMap<>(Map.of(')', '(', ']', '[', '}', '{'));

        Deque<Character> stack = new ArrayDeque<>();

        for (int i = 0; i < expression.length(); i++) {
            char bracket = expression.charAt(i);
            if (brackets.containsKey(bracket)) {
                if (stack.isEmpty() || brackets.get(bracket) != stack.pop()) {
                    return false;
                }
            } else {
                stack.push(bracket);
            }
        }

        return stack.isEmpty();
    }
}
