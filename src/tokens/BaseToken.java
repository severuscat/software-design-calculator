package tokens;

import visitor.TokenVisitor;

public interface BaseToken {
    void accept(TokenVisitor visitor);
}
