package visitor;

import tokens.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ParserVisitor implements TokenVisitor {
    private Stack<BaseToken> stack = new Stack<>();
    private List<BaseToken> parsed = new ArrayList<>();

    public List<BaseToken> parse(List<BaseToken> tokens) {
        for (BaseToken t : tokens) {
            t.accept(this);
        }

        while (!stack.isEmpty()) {
            BaseToken prev = stack.peek();
            if (prev instanceof Operation) {
                parsed.add(prev);
                stack.pop();
            } else {
                try {
                    throw new Exception("Invalid state.");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return parsed;
    }

    @Override
    public void visit(TNum token) {
        parsed.add(token);
    }

    @Override
    public void visit(Brace token) {
//        System.err.println(stack);

        if (token instanceof TLeftBrace) {
            stack.push(token);
            return;
        }
        if (!(token instanceof TRightBrace)) return;
        while (!stack.isEmpty()) {

//            System.err.println(stack);
            BaseToken prev = stack.peek();

            if (prev instanceof TLeftBrace) {
                stack.pop();
                break;
            }
            if (prev instanceof Operation) {
                parsed.add(prev);
                stack.pop();
            } else {
                try {
                    throw new Exception("Illegal state.");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private int p(Operation op) {
        switch (op.toString()){
            case "MUL":
            case "DIV":
                return 2;
            case "SUB":
            case "ADD":
                return 1;
            default:
                return 0;
        }
    }

    @Override
    public void visit(Operation token) {
        while (!stack.isEmpty()) {
            BaseToken last = stack.peek();
            if (last instanceof Operation &&
            p(token) <= p((Operation) last)) {
                parsed.add(last);
                stack.pop();
            } else
                break;
        }
        stack.push(token);
    }
}
