package rssreader;

public class RssItem {
    private final String title;
    private final String body;

    public RssItem(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }
}
