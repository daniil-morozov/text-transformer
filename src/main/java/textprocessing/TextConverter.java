package textprocessing;

import base.ConverterCommand;

public class TextConverter implements ConverterCommand<String, String> {
    private final ConverterStrategy strategy;

    private TextConverter(ConverterStrategy strategy) {
        this.strategy = strategy;
    }

    public static TextConverter of(ConverterStrategy strategy) {
        return new TextConverter(strategy);
    }

    @Override
    public String convert(String from) {
        return strategy.apply(from);
    }
}
