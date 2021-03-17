package base;

import base.ConverterCommand;
import commandline.AppInput;
import commandline.AppOut;

import java.util.List;

public interface AbstractAppContext {
    AppInput getInput();
    List<ConverterCommand<String, String>> getCommands();
    AppOut getOutput();
}
