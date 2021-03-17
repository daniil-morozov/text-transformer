package commandline.utils;

import commandline.AppInput;
import commandline.AppOut;

public class InputOutputKindParser {
    public static AppInput parseInput(String arg) {
        AppInput appInput;
        if (arg.endsWith(".txt")) {
            appInput = new AppInput(AppInput.Kind.FILE, arg);
        } else {
            appInput = new AppInput(AppInput.Kind.URL, arg);
        }
        return appInput;
    }

    public static AppOut parseOutput(String arg) {
        AppOut output;
        if (arg != null) {
            output = AppOut.createFileType(arg);
        } else {
            output = AppOut.createStandard();
        }
        return output;
    }
}
