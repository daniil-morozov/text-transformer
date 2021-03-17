package commandline.utils;

import base.ConverterCommand;
import textprocessing.ConverterStrategy;
import textprocessing.TextConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandParser {
    public static final String REPLACE = "replace/(.*)/(.*)/";
    public static final Pattern PATTERN = Pattern.compile(REPLACE);
    public static final String UZABASE = "uzabase";
    public static final String UZABASE_INC = "Uzabase, Inc";

    public static List<ConverterCommand<String, String>> parse(List<String> commands) {
        List<ConverterCommand<String, String>> result = new ArrayList<>();

        for (String convert : commands) {
            switch (convert) {
                case "cut":
                    result.add(TextConverter.of(ConverterStrategy.makeTrimStrategyDefaultSizeTen()));
                    break;
                case "cut,convert":
                    result.add(TextConverter.of(ConverterStrategy.makeTrimStrategyDefaultSizeTen()));
                    result.add(TextConverter.of(ConverterStrategy.makeReplaceStrategy(UZABASE, UZABASE_INC)));
                    break;
                default:
                    Matcher matcher = PATTERN.matcher(convert);

                    if (matcher.matches()) {
                        result.add(TextConverter.of(ConverterStrategy.makeReplaceStrategy(matcher.group(1),
                                matcher.group(2))));
                    }
                    break;
            }
        }

        return result;
    }
}
