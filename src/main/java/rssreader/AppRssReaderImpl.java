package rssreader;

import com.apptastic.rssreader.Item;
import com.apptastic.rssreader.RssReader;
import commandline.AppInput;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AppRssReaderImpl implements AppRssReader {
    private static final Logger LOGGER = Logger.getLogger(AppRssReaderImpl.class
            .getClass().getName());

    private final RssReader rssReader = new RssReader();
    private final AppInput appInput;

    private AppRssReaderImpl(AppInput appInput) {
        this.appInput = appInput;
    }

    public static AppRssReaderImpl of(AppInput appInput) {
        return new AppRssReaderImpl(appInput);
    }

    @Override
    public List<RssItem> read() {
        Stream<Item> rssContent = Stream.empty();

        if (appInput.getKind() == AppInput.Kind.URL) {
            try {
                rssContent = rssReader.read(appInput.getSrc());
            } catch (IOException e) {
                LOGGER.warning(e.getMessage());
            }
        } else if (appInput.getKind() == AppInput.Kind.FILE) {
            File initialFile = new File(appInput.getSrc());
            try {
                InputStream targetStream = new FileInputStream(initialFile);
                rssContent = rssReader.read(targetStream);
            } catch (FileNotFoundException e) {
                LOGGER.warning(e.getMessage());
            }
        }

        return rssContent.map(this::makeRssItem)
                .collect(Collectors.toList());
    }

    private RssItem makeRssItem(Item item) {
        return new RssItem(item.getTitle().orElse(""),
                item.getDescription().orElse(""));
    }
}
