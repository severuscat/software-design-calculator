package states;

public class SFail extends State {
    public SFail(Tokenizer t) {
        super(t);
    }

    @Override
    void process(char ch) {
        try {
            throw new Exception("Wrong character" + Character.toString(ch) + " appeared! Can't be recognized as token.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    void process() {
        try {
            throw new Exception("Unexpected line end.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
