package states;

import tokens.*;

public class SStart extends  State {
    public SStart(Tokenizer tokenizer) {
        super(tokenizer);
    }

    @Override
    void process(char ch) {
        if (ch == '(') {
            newToken(new TLeftBrace());
            return;
        }
        if (ch == ')') {
            newToken(new TRightBrace());
            return;
        }
        if (ch == '+') {
            newToken(new TAdd());
            return;
        }
        if (ch == '-') {
            newToken(new TSub());
            return;
        }
        if (ch == '/') {
            newToken(new TDiv());
            return;
        }
        if (ch == '*') {
            newToken(new TMul());
            return;
        }
        if (Character.isDigit(ch)) {
            t.state = new SNum(t);
            t.process(ch);
            return;
        }
        if (!Character.isWhitespace(ch)) {
            t.state = new SFail(t);
            t.process(ch);
        }
    }

    @Override
    void process() {

    }
}
