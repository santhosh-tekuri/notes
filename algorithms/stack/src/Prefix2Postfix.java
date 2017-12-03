import java.util.Stack;

/**
 * @author Santhosh Kumar Tekuri
 */
class Prefix2Postfix {
    static String toPostfix(String prefix) {
        Stack<String> stack = new Stack<>();
        for (int i = prefix.length() - 1; i >= 0; i--) {
            char ch = prefix.charAt(i);
            if (isOperator(ch)) {
                String op1 = stack.pop();
                String op2 = stack.pop();
                stack.push(op1 + op2 + String.valueOf(ch));
            } else
                stack.push(String.valueOf(ch));
        }
        return stack.pop();
    }

    static boolean isOperator(char ch) {
        switch (ch) {
            case '+':
            case '-':
            case '*':
            case '/':
                return true;
            default:
                return false;
        }
    }

    public static void main(String[] args) {
        System.out.println(toPostfix("+-435"));
        System.out.println(toPostfix("-*+ABC*-DE+FG"));
    }
}
