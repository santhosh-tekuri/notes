import java.util.Stack;

/**
 * @author Santhosh Kumar Tekuri
 */
public class EvalPostfix {
    static int evaluate(String postfix) {
        Stack<Integer> operands = new Stack<>();
        for(String token: postfix.split(" ")) {
            if(isInt(token)) {
                operands.push(Integer.parseInt(token));
            } else {
                int operand2 = operands.pop();
                int operand1 = operands.pop();
                int value = evaluate(token, operand1, operand2);
                operands.push(value);
            }
        }
        return operands.pop();
    }

    public static void main(String[] args) {
        System.out.println(evaluate("6 2 3 + - 3 8 2 / + * 2 ^ 3 +"));
        System.out.println(evaluate("2 1 + 3 *"));
        System.out.println(evaluate("4 13 5 / +"));

        // Output:
        // 52
        // 9
        // 6
    }

    static boolean isInt(String token) {
        try {
            Integer.parseInt(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    static int evaluate(String operator, int op1, int op2) {
        switch (operator.charAt(0)) {
            case '+':
                return op1 + op2;
            case '-':
                return op1 - op2;
            case '*':
                return op1 * op2;
            case '/':
                return op1 / op2;
            case '^':
                return (int)Math.pow(op1, op2);
        }
        throw new AssertionError();
    }
}
