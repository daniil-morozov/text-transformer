package commandline;

import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;

public class AppInputTest {

    @Test
    public void Test_getters() {
        final AppInput.Kind kind = AppInput.Kind.FILE;
        final String src = "test";

        final AppInput appInput = Mockito.mock(AppInput.class);
        Mockito.when(appInput.getKind()).thenReturn(kind);
        Mockito.when(appInput.getSrc()).thenReturn(src);

        assertEquals(kind, appInput.getKind());
        assertEquals(src, appInput.getSrc());
    }
}