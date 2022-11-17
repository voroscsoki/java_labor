import org.xml.sax.Attributes;

import java.util.HashMap;
import java.util.Map;

public class TagCounter extends org.xml.sax.helpers.DefaultHandler{
    HashMap<String, Integer> tags = new HashMap<>();
    BusStop readStop = null;


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        Integer mapValue = tags.get(qName);
        tags.put(qName, mapValue == null ? 1 : mapValue + 1);

        if(qName.equals("node")) {
            readStop = new BusStop();
        }

        else if(qName.equals("tag")){
            String value = attributes.getValue("v");

            if(value.equals("bus_stop"))
                readStop.valid = true;

            else switch (attributes.getValue("k")){
                case "name" -> readStop.name = value;
                case "oldName" -> readStop.oldName = value;
                case "wheelchair" -> readStop.wheelchair = value;
            }
        }

    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if(qName.equals("node") && readStop.valid) {
            System.out.print("Megálló:\n\tNév: " + readStop.name);
            if(readStop.oldName != null) System.out.print(" (" + readStop.oldName + ")");
            if(readStop.wheelchair != null) System.out.print("\n\tKerekesszék: " + readStop.wheelchair);
            System.out.println("\n"); //hits a new line twice
        }
    }

    @Override
    public void endDocument() {
        var result = tags.entrySet().stream()
                .sorted(Map.Entry.comparingByValue());

        result.forEach((entry) -> System.out.println(entry.getKey() + ": " + entry.getValue()));
    }
}
