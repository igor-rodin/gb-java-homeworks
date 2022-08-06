public class ReverseWords {
    public static void main(String[] args) {
        String str = "  hello world  ";
        System.out.printf("%s (%d)\n", str, str.length());
        String reverseString = reverseWords(str);

        System.out.printf("%s (%d)\n", reverseString, reverseString.length());
    }

    public static String reverseWords(String s) {
        StringBuilder sBuilder = new StringBuilder();

        int startWord = s.length() - 1;
        int endWord = s.length() - 1;

        while (startWord >= 0) {

            if (s.charAt(startWord) == ' ') {
                startWord--;
            } else {
                if (endWord != startWord) {
                    endWord = startWord;
                }

                while (startWord >= 0 && !(s.charAt(startWord) == ' ')) {
                    startWord--;
                }
                if (sBuilder.length() > 0) {
                    sBuilder.append(" ");
                }
                for (int i = startWord + 1; i <= endWord; i++) {
                    sBuilder.append(s.charAt(i));
                }
            }
        }

        return sBuilder.toString();
    }
}
