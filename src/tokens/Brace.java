package tokens;

import visitor.TokenVisitor;

public class Brace implements BaseToken {
    @Override
    public void accept(TokenVisitor visitor) {
        visitor.visit(this);
    }

}
