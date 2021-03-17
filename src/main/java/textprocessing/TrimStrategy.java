package textprocessing;

public class TrimStrategy implements ConverterStrategy {

    private final int trimLength;

    private TrimStrategy(int trimLength) {
        this.trimLength = trimLength;
    }

    public static TrimStrategy of(int trimLength) {
        return new TrimStrategy(trimLength);
    }

    @Override
    public String apply(String from) {
        return from.length() > trimLength ? from.substring(0, trimLength) : from;
    }
}
