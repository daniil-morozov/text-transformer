package textprocessing;

public interface ConverterStrategy {

    static ConverterStrategy makeReplaceStrategy(String search, String replace) {
        return ReplaceStrategy.of(search, replace);
    }

    static ConverterStrategy makeTrimStrategyDefaultSizeTen() {
        return TrimStrategy.of(10);
    }

    static ConverterStrategy makeTrimStrategy(int trimLength) {
        return TrimStrategy.of(trimLength);
    }

    String apply(String from);
}
