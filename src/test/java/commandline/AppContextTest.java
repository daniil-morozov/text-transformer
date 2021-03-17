package commandline;

import base.ConverterCommand;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class AppContextTest {

    @Test
    public void Test_getters() {
        final AppInput appInput = new AppInput(AppInput.Kind.FILE, "test");
        final AppOut output = AppOut.createStandard();
        final List<ConverterCommand<String, String>> commandList = new ArrayList<>();
        final AppContext context = Mockito.mock(AppContext.class);
        Mockito.when(context.getInput()).thenReturn(appInput);
        Mockito.when(context.getCommands()).thenReturn(commandList);
        Mockito.when(context.getOutput()).thenReturn(output);

        assertEquals(appInput, context.getInput());
        assertEquals(commandList, context.getCommands());
        assertEquals(output, context.getOutput());
    }
}