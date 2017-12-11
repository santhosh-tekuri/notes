import java.util.Stack;

/**
 * @author Santhosh Kumar Tekuri
 */
public class EvalPrefix {
    static int evaluate(String prefix) {
        String tokens[] = prefix.split(" ");
        Stack<Integer> operands = new Stack<>();
        for(int i=tokens.length-1; i>=0; i--) {
            if(isInt(tokens[i])) {
                operands.push(Integer.parseInt(tokens[i]));
            } else {
                int operand1 = operands.pop();
                int operand2 = operands.pop();
                int value = evaluate(tokens[i], operand1, operand2);
                operands.push(value);
            }
        }
        return operands.pop();
    }

    public static void main(String[] args) {
        System.out.println(evaluate("+ ^ * - 6 + 2 3 + 3 / 8 2 2 3"));
        System.out.println(evaluate("* + 2 1 3"));
        System.out.println(evaluate("+ 4 / 13 5"));

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
