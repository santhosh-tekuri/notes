public class LBS {
    public static int lbs(char s[]) {
        int open = 0;
        int len = 0;
        for (char ch : s) {
            if (ch == '(')
                open++;
            else if (open > 0) {
                open--;
                len += 2;
            }
        }
        return len;
    }

    public static void main(String[] args) {
        System.out.println(lbs("()())".toCharArray()));
        System.out.println(lbs("()(((((()".toCharArray()));
        System.out.println(lbs("))(()(())))(())".toCharArray()));
        // 4
        // 4
        // 12
    }
}
