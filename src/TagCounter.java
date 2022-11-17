import org.xml.sax.Attributes;

import java.util.HashMap;
import java.util.Map;

public class TagCounter extends org.xml.sax.helpers.DefaultHandler{
        HashMap<String, Integer> tags = new HashMap<>();

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        Integer value = tags.get(qName);
        tags.put(qName, value == null ? 1 : value + 1);
    }

    @Override
    public void endDocument() {
        var result = tags.entrySet().stream()
                .sorted(Map.Entry.comparingByValue());

        result.forEach((entry) -> System.out.println(entry.getKey() + ": " + entry.getValue()));
    }
}
