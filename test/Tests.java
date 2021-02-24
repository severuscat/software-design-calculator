import states.Tokenizer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import tokens.*;
import visitor.ParserVisitor;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Tests {
    private static final String SAMPLE = "(1 + 3) * 5 - 6 / (1 + 2)";
    private static final List<BaseToken> TOKENS = new ArrayList<>(
            Arrays.asList(
                    new TLeftBrace(), new TNum(1), new TAdd(),
                    new TNum(3), new TRightBrace(), new TMul(),
                    new TNum(5), new TSub(), new TNum(6),
                    new TDiv(), new TLeftBrace(), new TNum(1),
                    new TAdd(), new TNum(2), new TRightBrace()
            )
    );

    private static final List<BaseToken> TOKENS_PARSED = new ArrayList<>(
            Arrays.asList(
                    new TNum(1), new TNum(3),
                    new TAdd(), new TNum(5), new TMul(),
                    new TNum(6), new TNum(1),
                    new TNum(2), new TAdd(),
                    new TDiv(), new TSub()
            )
    );
    private Tokenizer t;
    private ParserVisitor p;

    @BeforeEach
    void setUp() {
        t = new Tokenizer();
        p = new ParserVisitor();
    }

    @Test
    void TokenizerTest() throws Exception {
        assertEquals(TOKENS.toString(), t.getTokens(new ByteArrayInputStream(SAMPLE.getBytes())).toString());
    }

    @Test
    void taskSample() throws Exception {
        assertEquals(TOKENS_PARSED.toString(), p.parse(TOKENS).toString());
    }
}
