package jdom;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import java.io.FileOutputStream;
import java.util.*;

public class LabJDOM {
    public static void main(String[] args) {
        int inFileArg = Arrays.asList(args).indexOf("-i") + 1;
        int outFileArg = Arrays.asList(args).indexOf("-o") + 1;
        String inFilename = (inFileArg == 0) ? "bme.xml" : args[inFileArg];
        String outFilename = (outFileArg == 0) ? "bme_bus.xml" : args[outFileArg];

        int latArg = Arrays.asList(args).indexOf("-lat") + 1;
        int lonArg = Arrays.asList(args).indexOf("-lon") + 1;
        double latitude = (latArg == 0) ? 47.4786346 : Double.parseDouble(args[latArg]);
        double longitude = (lonArg == 0) ? 19.0555773 : Double.parseDouble(args[lonArg]);

        try {
            Document doc;
            SAXBuilder b = new SAXBuilder();
            doc = b.build(new java.io.File(inFilename));
            var root = doc.getRootElement();
            List<Element> removable = new ArrayList<>(); //avoid modifying children list during iterator work
            for (var e : root.getChildren()){
                if(!e.getName().equals("node") ||
                        e.getChildren().stream().noneMatch(p -> p.getAttribute("v").getValue().equals("bus_stop")))
                    removable.add(e);
                else {
                    double thisLat = e.getAttribute("lat").getDoubleValue();
                    double thisLon = e.getAttribute("lon").getDoubleValue();
                    Element toAdd = new Element("tag");
                    toAdd.setAttribute("k", "distance");
                    toAdd.setAttribute("v", String.valueOf(dist1(latitude, thisLat, longitude, thisLon)));
                    e.addContent(toAdd);
                }
            }
            for(var e : removable) e.detach();

            XMLOutputter xout = new XMLOutputter(Format.getPrettyFormat());
            xout.output(doc, new FileOutputStream(outFilename));
        } catch (Exception e) {e.printStackTrace();}

    }

    static double dist1(double lat1, double lon1, double lat2, double lon2) {
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
