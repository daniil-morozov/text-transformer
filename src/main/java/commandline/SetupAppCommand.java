package commandline;

import base.ConverterCommand;
import com.github.rvesse.airline.HelpOption;
import com.github.rvesse.airline.annotations.Command;
import com.github.rvesse.airline.annotations.Option;
import com.github.rvesse.airline.annotations.OptionType;
import commandline.utils.CommandParser;
import commandline.utils.InputOutputKindParser;
import resultwriter.AppResultWriter;
import resultwriter.AppResultWriterImpl;
import rssreader.AppRssReader;
import rssreader.AppRssReaderImpl;
import rssreader.RssItem;
import textprocessing.ConverterStrategy;
import textprocessing.TextConverter;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Command(name = "setup-app", description = "RSS source: URL or file")
public class SetupAppCommand implements Runnable {
    @Inject
    private HelpOption<SetupAppCommand> help;

    private static final Logger LOGGER = Logger.getLogger(SetupAppCommand.class
            .getClass().getName());

    @Option(name = {"-i", "--input"},
            title = "--input <TXT FILE PATH | URL>",
            description = "Sets the input to be used as RSS source")
    @com.github.rvesse.airline.annotations.restrictions.Pattern(pattern="(https?:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_\\+.~#?&//=]*))|(.*.txt)")
    private String inputSrc;

    @Option(type = OptionType.COMMAND,
            name = {"-c", "--convert"},
            title = "--convert <convert operation>",
            description = "Sets the convert to be applied to the title and description of the RSS")
    private List<String> converts;

    @Option(name = {"-o", "--output"},
            title = "--output <TXT FILE PATH>",
            description = "Sets the output to be used as converted text destination. " +
                    "Default is standard output")
    @com.github.rvesse.airline.annotations.restrictions.Pattern(pattern=".*.txt")
    private String outputDst;

    @Override
    public void run() {
        if (inputSrc == null) {
            System.out.println("Input source is required to run the app");
            LOGGER.severe("Exiting, no input");
            return;
        }

        AppInput appInput = InputOutputKindParser.parseInput(inputSrc);
        Objects.requireNonNull(appInput);

        LOGGER.info("Source=" + inputSrc);

        if (converts == null) {
            System.out.println("Convert operations are required to run the app");
            LOGGER.severe("Exiting, no converters");
            return;
        }

        final List<ConverterCommand<String, String>> commands = CommandParser.parse(converts);

        LOGGER.info("CONVERTERS=" + Arrays.toString(converts.toArray()));

        AppOut output = InputOutputKindParser.parseOutput(outputDst);
        Objects.requireNonNull(output);

        LOGGER.info("Destination=" + outputDst);

        final AppContext context = new AppContext(appInput, commands, output);
        final AppRssReader reader = AppRssReaderImpl.of(context.getInput());
        final List<RssItem> rssItems = reader.read();

        final StringBuilder builder = new StringBuilder();

        for (RssItem item : rssItems) {
            String convertedTitle = applyConverters(commands, item.getTitle());
            builder.append(convertedTitle);
            String convertedBody = applyConverters(commands, item.getBody());
            builder.append(convertedBody);
        }

        final AppResultWriter appResultWriter = AppResultWriterImpl.of(context.getOutput());
        appResultWriter.write(builder.toString());
    }

    private String applyConverters(final List<ConverterCommand<String, String>> commands,
                                   final String val) {
        if (val.length() > 0) {
            String result = val;
            for (ConverterCommand<String, String> converter : commands) {
                result = converter.convert(result);
            }

            return result;
        }

        return "";
    }
}
