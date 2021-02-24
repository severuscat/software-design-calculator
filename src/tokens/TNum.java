package tokens;

import visitor.TokenVisitor;

public class TNum implements BaseToken {
    private int value;

    public TNum(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    @Override
    public void accept(TokenVisitor visitor) {
        visitor.visit(this);
    }

    public String toString() {
        return "NUMBER " + "(" + value + ")";
    }
}
