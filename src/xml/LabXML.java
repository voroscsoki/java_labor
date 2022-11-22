package xml;

import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.util.Arrays;

public class LabXML {
    public static void main(String[] args) {
        int fileArg = Arrays.asList(args).indexOf("-i") + 1;
        int latArg = Arrays.asList(args).indexOf("-lat") + 1;
        int lonArg = Arrays.asList(args).indexOf("-lon") + 1;
        String filename = (fileArg == 0) ? "bme.xml" : args[fileArg];
        double latitude = (latArg == 0) ? 47.4786346 : Double.parseDouble(args[latArg]);
        double longitude = (lonArg == 0) ? 19.0555773 : Double.parseDouble(args[lonArg]);

        DefaultHandler h = new TagCounter(latitude, longitude);
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser p = factory.newSAXParser();
            p.parse(new java.io.File(filename), h);
        } catch (Exception e) {e.printStackTrace();}
    }
}
