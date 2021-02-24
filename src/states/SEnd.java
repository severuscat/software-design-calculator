package states;

public class SEnd extends State {
    public SEnd(Tokenizer t) {
        super(t);
    }

    @Override
    void process(char ch) {
        try {
            throw new Exception("Bad state");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    void process() {
    }
}
