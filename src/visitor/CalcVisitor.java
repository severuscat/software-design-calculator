package visitor;

import tokens.Brace;
import tokens.Operation;
import tokens.TNum;

import java.util.Stack;

public class CalcVisitor implements TokenVisitor{
    private final Stack<Integer> calcStack = new Stack<>();

    @Override
    public void visit(TNum token) {
        calcStack.add(token.getValue());
    }

    @Override
    public void visit(Brace token) {
        try {
            throw new Exception("Wrong character (unexpected brace).");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void visit(Operation token) {
        if (calcStack.size() < 2) {
            try {
                throw new Exception("Trying to do non-binary operation, failed.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        int left = calcStack.pop();
        int right = calcStack.pop();
        calcStack.push(calc(token, left, right));

    }

    private Integer calc(Operation token, int left, int right) {
        switch (token.toString()) {
            case "ADD": return left + right;
            case "MUL": return left * right;
            case "SUB": return left - right;
            case "DIV": return left / right;
            default:
                try {
                    throw new Exception("Unrecognised operation.");
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }
        return -1;
    }
}
