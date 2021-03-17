package commandline.utils;

import commandline.AppInput;
import commandline.AppOut;
import org.junit.Test;

import static org.junit.Assert.*;

public class InputOutputKindParserTest {

    @Test
    public void parseFileInput() {
        final String path = "test.txt";
        final AppInput appInput = InputOutputKindParser.parseInput(path);

        assertEquals(AppInput.Kind.FILE, appInput.getKind());
        assertEquals(path, appInput.getSrc());
    }

    @Test
    public void parseUrlInput() {
        final String url = "https://tech.uzabase.com/rss";
        final AppInput appInput = InputOutputKindParser.parseInput(url);

        assertEquals(AppInput.Kind.URL, appInput.getKind());
        assertEquals(url, appInput.getSrc());
    }

    @Test
    public void parseFileOutput() {
        final String path = "test.txt";
        final AppOut appOut = InputOutputKindParser.parseOutput(path);

        assertEquals(AppOut.Kind.FILE, appOut.getKind());
        assertEquals(path, appOut.getDst());
    }

    @Test
    public void parseStandardOutput() {
        final AppOut appOut = InputOutputKindParser.parseOutput(null);

        assertEquals(AppOut.Kind.STANDARD, appOut.getKind());
    }
}