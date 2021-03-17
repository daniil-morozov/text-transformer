package commandline;

import base.ConverterCommand;

import java.util.List;

public interface AbstractAppContext {
    Input getInput();
    List<ConverterCommand<String, String>> getCommands();
    Out getOutput();
}
