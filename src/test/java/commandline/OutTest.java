package commandline;

import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class OutTest {

    @Test
    public void Test_getters() {
        final Out.Kind kind = Out.Kind.FILE;
        final String dst = "test";

        final Out output = Mockito.mock(Out.class);
        Mockito.when(output.getKind()).thenReturn(kind);
        Mockito.when(output.getDst()).thenReturn(dst);

        assertEquals(kind, output.getKind());
        assertEquals(dst, output.getDst());
    }
}