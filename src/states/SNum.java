package states;

import tokens.TNum;

public class SNum extends State {

    private int value;

    public SNum(Tokenizer t) {
        super(t);
    }

    @Override
    void process(char ch) {
        if (Character.isDigit(ch)) {
            value = 10 * value + Integer.parseInt(String.valueOf(ch));
        } else {
            newToken(new TNum(value));
            t.state = new SStart(t);
            t.process(ch);
        }
    }

    @Override
    void process() {
        newToken(new TNum(value));
    }
}
