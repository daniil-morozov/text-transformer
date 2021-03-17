package textprocessing;

public class ReplaceStrategy implements ConverterStrategy {

    private final String search;
    private final String replace;

    private ReplaceStrategy(String search, String replace) {
        this.search = search;
        this.replace = replace;
    }

    public static ReplaceStrategy of(String search, String replace) {
        return new ReplaceStrategy(search, replace);
    }

    @Override
    public String apply(String from) {
        return from.replace(search, replace);
    }
}
