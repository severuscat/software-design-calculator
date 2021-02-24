package visitor;

import tokens.BaseToken;
import tokens.Brace;
import tokens.Operation;
import tokens.TNum;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Stack;

public class PrintVisitor implements TokenVisitor {
    OutputStream ostream;
    Stack<String> stack = new Stack<>();

    public void print(List<BaseToken> ts) {
        for (BaseToken t : ts) {
            t.accept(this);
        }
        try {
            ostream.write(stack.pop().getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void visit(TNum token) {
        stack.add(String.valueOf(token.getValue()));
    }

    @Override
    public void visit(Brace token) {
        try {
            throw new Exception("Unexpected brace, failed");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void visit(Operation token) {
        if (stack.size() < 2) {
            try {
                throw new Exception("Trying to apply non-binary operation, failed.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        String x = stack.pop();
        String y = stack.pop();
        switch (token.toString()) {
            case "DIV":
                stack.push("(" + x + "/" + y + ")");
                break;
            case "MUL":
                stack.push("(" + x + "*" + y + ")");
                break;
            case "ADD":
                stack.push("(" + x + "+" + y + ")");
                break;
            case "SUB":
                stack.push("(" + x + "-" + y + ")");
                break;
        }
    }
}
