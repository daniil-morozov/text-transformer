package textprocessing;

import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.eq;

public class TextConverterTest {

    @Test
    public void Should_return_expected_When_replace_strategy_applied() {
        final String expected = "replace";
        final String input = "test";

        final ReplaceStrategy strategy = Mockito.mock(ReplaceStrategy.class);
        Mockito.when(strategy.apply(eq(input))).thenReturn(expected);

        final TextConverter converter = TextConverter.of(strategy);
        final String result = converter.convert(input);

        Mockito.verify(strategy).apply(input);
        assertEquals(expected, result);
    }

    @Test
    public void Should_return_expected_When_trim_strategy_applied() {
        final String expected = "expected";
        final String input = "test";

        final TrimStrategy strategy = Mockito.mock(TrimStrategy.class);
        Mockito.when(strategy.apply(eq(input))).thenReturn(expected);

        final TextConverter converter = TextConverter.of(strategy);
        final String result = converter.convert(input);

        Mockito.verify(strategy).apply(input);
        assertEquals(expected, result);
    }
}