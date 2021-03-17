package resultwriter;

import commandline.AppOut;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;

public class AppResultWriterImpl implements AppResultWriter {
    private static final Logger LOGGER = Logger.getLogger(AppResultWriterImpl.class
            .getClass().getName());

    private final AppOut output;

    private AppResultWriterImpl(AppOut output) {
        this.output = output;
    }

    public static AppResultWriterImpl of(AppOut output) {
        return new AppResultWriterImpl(output);
    }

    @Override
    public void write(String text) {
        if (output.getKind() == AppOut.Kind.STANDARD) {
            System.out.println(text);
        } else if (output.getKind() == AppOut.Kind.FILE) {
            try {
                File file = new File(output.getDst());

                if (!file.exists()) {
                    file.createNewFile();
                }

                try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                    writer.write(text);
                }
            } catch (IOException e) {
                LOGGER.warning(e.getMessage());
            }
        }
    }
}
