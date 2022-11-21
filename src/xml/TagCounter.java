package xml;

import org.xml.sax.Attributes;

import java.util.*;

public class TagCounter extends org.xml.sax.helpers.DefaultHandler{
    BusStop currentStop = null;
    ArrayList<BusStop> busStops = new ArrayList<>();
    HashMap<String, Integer> tags = new HashMap<>();
    double lat, lon;

    public TagCounter(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        Integer mapValue = tags.get(qName);
        tags.put(qName, mapValue == null ? 1 : mapValue + 1);

        if(qName.equals("node")) {
            currentStop = new BusStop();
            currentStop.distance = dist1(lat, lon,
                        Double.parseDouble(attributes.getValue("lat")),
                        Double.parseDouble(attributes.getValue("lon")));
        }

        else if(qName.equals("tag")){
            String value = attributes.getValue("v");

            if(value.equals("bus_stop"))
                currentStop.valid = true;

            else switch (attributes.getValue("k")){
                case "name" -> currentStop.name = value;
                case "oldName" -> currentStop.oldName = value;
                case "wheelchair" -> currentStop.wheelchair = value;
            }
        }

    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if(qName.equals("node") && currentStop.valid)
            busStops.add(currentStop);
    }

    @Override
    public void endDocument() {
        busStops.sort(Comparator.comparing(s -> s.distance));
        var tagStream = tags.entrySet().stream()
                .sorted(Map.Entry.comparingByValue());
        busStops.forEach((stop) -> {
            System.out.print("Megálló:\n\tNév: " + stop.name);
            if(stop.oldName != null) System.out.print(" (" + stop.oldName + ")");
            if(stop.wheelchair != null) System.out.print("\n\tKerekesszék: " + stop.wheelchair);
            System.out.println("\n\tTávolság: " + String.format("%.2f", stop.distance) + " m\n"); //hits a new line twice
        });
        tagStream.forEach((entry) -> System.out.println(entry.getKey() + ": " + entry.getValue()));
    }

    //copied from task guide
    double dist1(double lat1, double lon1, double lat2, double lon2) {
        double R = 6371000; // metres
        double phi1 = Math.toRadians(lat1);
        double phi2 = Math.toRadians(lat2);
        double dphi = phi2-phi1;
        double dl = Math.toRadians(lon2-lon1);
        double a = Math.sin(dphi/2) * Math.sin(dphi/2) +
                Math.cos(phi1) * Math.cos(phi2) *
                        Math.sin(dl/2) * Math.sin(dl/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        return R * c;
    }
}
