package commandline;

public class Out {
    private final Kind kind;
    private final String dst;

    private Out(Kind kind, String dst) {
        this.kind = kind;
        this.dst = dst;
    }

    public static Out createFileType(String dst) {
        return new Out(Kind.FILE, dst);
    }

    public static Out createStandard() {
        return new Out(Kind.STANDARD, null);
    }

    public Kind getKind() {
        return kind;
    }

    public String getDst() {
        return dst;
    }

    public enum Kind {
        STANDARD,
        FILE
    }
}
