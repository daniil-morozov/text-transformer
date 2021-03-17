package commandline.utils;

import base.ConverterCommand;
import org.junit.Test;
import textprocessing.TextConverter;

import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;

public class CommandParserTest {

    @Test
    public void Should_parse_cut_When_cut_added() {
        final List<ConverterCommand<String, String>> converterCommands = CommandParser.parse(List.of("cut"));
        final String input = "test_test_test_test_test";

        assertEquals(1, converterCommands.size());

        final String result = converterCommands.get(0).convert(input);

        assertEquals(10, result.length());
    }

    @Test
    public void Should_parse_replace_When_replace_added() {
        final List<ConverterCommand<String, String>> converterCommands = CommandParser.parse(List.of("replace/abc/def/"));
        final String input = "abc_abc";

        assertEquals(1, converterCommands.size());

        final String result = converterCommands.get(0).convert(input);

        assertEquals("def_def", result);
    }

    @Test
    public void Should_parse_cut_and_default_replace_When_cut_and_convert_added() {
        final List<ConverterCommand<String, String>> converterCommands = CommandParser.parse(List.of("cut,convert"));
        final String input = "uzabase_uzabase";

        assertEquals(2, converterCommands.size());

        String result = converterCommands.get(0).convert(input);

        assertEquals("uzabase_uz", result);

        result = converterCommands.get(1).convert(result);

        assertEquals("Uzabase, Inc_uz", result);
    }

    @Test
    public void Should_parse_cut_and_replace_When_cut_and_convert_added() {
        final List<ConverterCommand<String, String>> converterCommands = CommandParser.parse(List.of("replace/abc/def/",
                "replace/def/a/"));
        final String input = "abc_abc";

        assertEquals(2, converterCommands.size());

        String result = converterCommands.get(0).convert(input);

        assertEquals("def_def", result);

        result = converterCommands.get(1).convert(result);

        assertEquals("a_a", result);
    }

    @Test
    public void Should_parse_replace_and_cut_When_replace_and_cut_added() {
        final List<ConverterCommand<String, String>> converterCommands = CommandParser.parse(List.of("replace/abc/000000000011111/",
                "cut"));
        final String input = "abc";

        assertEquals(2, converterCommands.size());

        String result = converterCommands.get(0).convert(input);

        assertEquals("000000000011111", result);

        result = converterCommands.get(1).convert(result);

        assertEquals("0000000000", result);
    }
}