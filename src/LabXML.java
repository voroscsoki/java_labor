import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.util.Arrays;

public class LabXML {
    public static void main(String[] args) {
        int fileArg = Arrays.asList(args).indexOf("-i") + 1;
        String filename = (fileArg == 0) ? "bme.xml" : args[fileArg];

        DefaultHandler h = new TagCounter();
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser p = factory.newSAXParser();
            p.parse(new java.io.File(filename), h);
        } catch (Exception e) {e.printStackTrace();}
    }
}
