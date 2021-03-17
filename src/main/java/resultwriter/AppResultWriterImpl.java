package resultwriter;

import commandline.Out;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;

public class AppResultWriterImpl implements AppResultWriter {
    private static final Logger LOGGER = Logger.getLogger(AppResultWriterImpl.class
            .getClass().getName());

    private final Out output;

    private AppResultWriterImpl(Out output) {
        this.output = output;
    }

    public static AppResultWriterImpl of(Out output) {
        return new AppResultWriterImpl(output);
    }

    @Override
    public void write(String text) {
        if (output.getKind() == Out.Kind.STANDARD) {
            System.out.println(text);
        } else if (output.getKind() == Out.Kind.FILE) {
            try {
                File file = new File(output.getDst());

                if (!file.exists()) {
                    file.createNewFile();
                }

                try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                    writer.write(text);
                    writer.flush();
                }
            } catch (IOException e) {
                LOGGER.warning(e.getMessage());
            }
        }
    }
}
