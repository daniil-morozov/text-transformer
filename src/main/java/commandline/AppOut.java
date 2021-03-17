package commandline;

public class AppOut {
    private final Kind kind;
    private final String dst;

    private AppOut(Kind kind, String dst) {
        this.kind = kind;
        this.dst = dst;
    }

    public static AppOut createFileType(String dst) {
        return new AppOut(Kind.FILE, dst);
    }

    public static AppOut createStandard() {
        return new AppOut(Kind.STANDARD, null);
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
