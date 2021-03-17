package rssreader;

import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;

public class RssItemTest {

    @Test
    public void Test_getters() {
        final String title = "title";
        final String body = "body";

        final RssItem item = Mockito.mock(RssItem.class);
        Mockito.when(item.getTitle()).thenReturn(title);
        Mockito.when(item.getBody()).thenReturn(body);

        assertEquals(title, item.getTitle());
        assertEquals(body, item.getBody());
    }
}