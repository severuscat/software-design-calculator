package tokens;

import visitor.TokenVisitor;

public class Operation implements BaseToken  {
    @Override
    public void accept(TokenVisitor visitor) {
        visitor.visit(this);
    }
}
