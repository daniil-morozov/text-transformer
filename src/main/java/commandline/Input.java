package commandline;


public class Input {

    private final Kind kind;
    private final String src;

    public Input(Kind kind, String src) {
        this.kind = kind;
        this.src = src;
    }

    public Kind getKind() {
        return kind;
    }

    public String getSrc() {
        return src;
    }

    public enum Kind {
        URL,
        FILE
    }
}
