package visitor;

import tokens.Brace;
import tokens.Operation;
import tokens.TNum;

public interface TokenVisitor {
        void visit(TNum token);
        void visit(Brace token);
        void visit(Operation token);
}
