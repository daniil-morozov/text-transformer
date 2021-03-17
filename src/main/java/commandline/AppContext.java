package commandline;

import base.AbstractAppContext;
import base.ConverterCommand;

import java.util.List;

public class AppContext implements AbstractAppContext {
    private final AppInput appInput;
    private final AppOut output;
    private final List<ConverterCommand<String, String>> commands;

    public AppContext(AppInput appInput, List<ConverterCommand<String, String>> commands, AppOut output) {
        this.appInput = appInput;
        this.output = output;
        this.commands = commands;
    }

    @Override
    public AppInput getInput() {
        return appInput;
    }

    @Override
    public List<ConverterCommand<String, String>> getCommands() {
        return commands;
    }

    @Override
    public AppOut getOutput() {
        return output;
    }
}
