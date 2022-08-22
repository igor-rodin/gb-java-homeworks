import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CalcExpression {
    public static void main(String[] args) {
        // String mathExpr = "(13.5 - 2^3 + 4) / ( (-25 + 43) * (7-2))";
        // String mathExpr = "(13.5 - 0.5^3 + 1.4) / ( (-2.5 + 43) * (7-2))";
        String mathExpr = "(-2 + 3 * 2)";

        List<String> tokens = tokenize(mathExpr);

        List<String> postfix = infixToPostfix(tokens);

        System.out.printf("Infix expression: %s\n", mathExpr);
        System.out.print("Posfix expression: ");

        for (String token : postfix) {
            System.out.printf("%s ", token);
        }
        System.out.println();

        System.out.printf("Value of expression: %5.3f\n", calcPostfix(postfix));
    }

    // *Метод разбивает математическое выражение на токены */
    private static List<String> tokenize(String expr) {
        List<String> tokens = new ArrayList<>();

        int posDigit = 0;
        boolean isDigit = false;
        int i = 0;
        while (i < expr.length()) {
            if (expr.charAt(i) == ' ') {
                i++;
                continue;
            } else if (Character.isDigit(expr.charAt(i)) && !isDigit) {
                isDigit = true;
                posDigit = i;
                while (i < expr.length() && (Character.isDigit(expr.charAt(i)) || expr.charAt(i) == '.')) {
                    i++;
                }
                isDigit = false;
                tokens.add(expr.substring(posDigit, i));
            } else {
                tokens.add(String.valueOf((expr.charAt(i))));
                i++;
            }
        }

        return tokens;
    }

    // * Метод преобразует инфиксную запись в постфиксную*/
    private static List<String> infixToPostfix(List<String> infix) {
        List<String> postfix = new ArrayList<>();
        Deque<String> opStack = new ArrayDeque<>();

        Map<String, Integer> ops = new HashMap<>(Map.of("(", 1, "+", 2, "-", 2, "*", 3, "/", 3, "^", 4));

        if (infix.get(0).equals("-")) {
            postfix.add("0");
        }
        for (int i = 0; i < infix.size(); i++) {
            String token = infix.get(i);
            if (token.equals("(")) {
                opStack.push(token);
                if (infix.get(i + 1).equals("-")) {
                    postfix.add("0");
                }
            } else if (token.equals(")")) {
                String top = opStack.pop();
                while (!top.equals("(")) {
                    postfix.add(top);
                    top = opStack.pop();
                }
            } else if (!ops.keySet().contains(token)) {
                postfix.add(token);
            } else {
                while (!opStack.isEmpty() && ops.get(token) <= ops.get(opStack.peek())) {
                    postfix.add(opStack.pop());
                }
                opStack.push(token);
            }
        }
        while (!opStack.isEmpty()) {
            postfix.add(opStack.pop());
        }

        return postfix;
    }

    // *Метод вычисляет значение выражения, записанного в постфиксном виде */
    private static double calcPostfix(List<String> postfix) {
        Deque<Double> opStack = new ArrayDeque<>();
        Set<String> ops = new HashSet<>(Set.of("+", "-", "*", "/", "^"));

        for (String token : postfix) {
            if (!ops.contains(token)) {
                opStack.push(Double.parseDouble(token));
            } else {
                double oper2 = opStack.pop();
                double oper1 = opStack.pop();
                double res = calcOperation(token, oper1, oper2);
                opStack.push(res);
            }
        }
        return opStack.pop();
    }

    // *Метод возвращает результат операции operation над операндами oper1 и oper2
    // */
    private static double calcOperation(String operation, double oper1, double oper2) {
        return switch (operation) {
            case "+" -> oper1 + oper2;
            case "-" -> oper1 - oper2;
            case "*" -> oper1 * oper2;
            case "/" -> oper1 / oper2;
            case "^" -> Math.pow(oper1, oper2);
            default -> throw new UnsupportedOperationException();
        };
    }
}
