package commandline;

import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;

public class InputTest {

    @Test
    public void Test_getters() {
        final Input.Kind kind = Input.Kind.FILE;
        final String src = "test";

        final Input input = Mockito.mock(Input.class);
        Mockito.when(input.getKind()).thenReturn(kind);
        Mockito.when(input.getSrc()).thenReturn(src);

        assertEquals(kind, input.getKind());
        assertEquals(src, input.getSrc());
    }
}