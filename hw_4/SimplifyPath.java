import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

public class SimplifyPath {
    public static void main(String[] args) {
        String[] testPath = { "/home/", "/../", "/home//foo/" };

        for (String path : testPath) {
            String canonicalPath = simplifyPath(path);
            System.out.printf("[Original path] %s -> [Canonical path] %s\n", path, canonicalPath);
        }
    }

    private static String simplifyPath(String path) {
        Deque<String> stack = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();

        String[] tokens = path.split("/");

        for (String token : tokens) {
            if (token.length() != 0 & !token.equals(".") & !token.equals("..")) {
                stack.push(token);
            }
            if (token.equals("..") & !stack.isEmpty()) {
                stack.pop();
            }
        }

        Iterator<String> iter = stack.descendingIterator();
        while (iter.hasNext()) {
            sb.append("/").append(iter.next());
        }

        return sb.length() == 0 ? "/" : sb.toString();
    }
}