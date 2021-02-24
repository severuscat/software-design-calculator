package states;

import tokens.BaseToken;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Tokenizer {
    protected State state = new SStart(this);

    int curPos = 0;

    List<BaseToken> tokenList = new ArrayList<>();

    public void process(char ch) {
        state.process(ch);
    }

    public void process() {
        state.process();
    }

    public List<BaseToken> getTokens(InputStream is) throws Exception {
        if (!(state instanceof SStart)) {
            throw new Exception("Illegal state, start expected.");
        }
        try (Reader reader = new BufferedReader(new InputStreamReader
                (is, Charset.forName(StandardCharsets.UTF_8.name())))) {
            int c;
            while ((c = reader.read()) != -1) {
                process((char) c);
                curPos++;
            }
        }

        process();
        return tokenList;
    }
}