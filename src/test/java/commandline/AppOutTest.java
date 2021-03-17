package commandline;

import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class AppOutTest {

    @Test
    public void Test_getters() {
        final AppOut.Kind kind = AppOut.Kind.FILE;
        final String dst = "test";

        final AppOut output = Mockito.mock(AppOut.class);
        Mockito.when(output.getKind()).thenReturn(kind);
        Mockito.when(output.getDst()).thenReturn(dst);

        assertEquals(kind, output.getKind());
        assertEquals(dst, output.getDst());
    }
}