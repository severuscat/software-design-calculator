package states;

import tokens.BaseToken;

public abstract class State {
    protected Tokenizer t;

    public State(Tokenizer t) {
        this.t = t;
    }

    public Tokenizer getT() {
        return t;
    }

    public void setT(Tokenizer tOther) {
        this.t = tOther;
    }

    public void newToken(BaseToken token) {
        t.tokenList.add(token);
    }

    abstract void process(char ch);
    abstract void process();
}
