package commandline;

import base.ConverterCommand;

import java.util.List;

public class AppContext implements AbstractAppContext {
    private final Input input;
    private final Out output;
    private final List<ConverterCommand<String, String>> commands;

    public AppContext(Input input, List<ConverterCommand<String, String>> commands, Out output) {
        this.input = input;
        this.output = output;
        this.commands = commands;
    }

    @Override
    public Input getInput() {
        return input;
    }

    @Override
    public List<ConverterCommand<String, String>> getCommands() {
        return commands;
    }

    @Override
    public Out getOutput() {
        return output;
    }
}
