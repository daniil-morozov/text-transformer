package textprocessing;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TextConverterIntegrationTest {
    @Test
    public void Should_return_expected_When_replace_strategy_applied() {
        final String sample = "test";
        final String replace = "replace";

        final String input = "test_test";
        final String expected = input.replace(sample, replace);

        final ConverterStrategy strategy = ConverterStrategy.makeReplaceStrategy(sample, replace);

        final TextConverter converter = TextConverter.of(strategy);
        final String result = converter.convert(input);

        assertEquals(expected, result);
    }

    @Test
    public void Should_return_expected_When_replace_strategy_applied_and_no_search_found() {
        final String sample = "test-1";
        final String replace = "replace";

        final String input = "test_test";
        final String expected = input.replace(sample, replace);

        final ConverterStrategy strategy = ConverterStrategy.makeReplaceStrategy(sample, replace);

        final TextConverter converter = TextConverter.of(strategy);
        final String result = converter.convert(input);

        assertEquals(expected, result);
    }

    @Test
    public void Should_return_expected_When_trim_strategy_applied() {
        final String expected = "string";
        final int length = expected.length();
        final String input = "string_too_long";


        final ConverterStrategy strategy = ConverterStrategy.makeTrimStrategy(length);

        final TextConverter converter = TextConverter.of(strategy);
        final String result = converter.convert(input);

        assertEquals(expected, result);
    }

    @Test
    public void Should_return_expected_When_trim_strategy_applied_and_input_shorter() {
        final String expected = "string";
        final int length = expected.length() + 1;
        final String input = expected;


        final ConverterStrategy strategy = ConverterStrategy.makeTrimStrategy(length);

        final TextConverter converter = TextConverter.of(strategy);
        final String result = converter.convert(input);

        assertEquals(expected, result);
    }
}
