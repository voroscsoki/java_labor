package jdom;

import org.jdom2.Document;
import org.jdom2.input.SAXBuilder;

import java.util.Arrays;

public class LabJDOM {
    public static void main(String[] args) {
        int fileArg = Arrays.asList(args).indexOf("-i") + 1;
        String filename = (fileArg == 0) ? "bme.xml" : args[fileArg];
        Document doc;

        try {
            SAXBuilder b = new SAXBuilder();
            doc = b.build(new java.io.File(filename));
        } catch (Exception e) {e.printStackTrace();}

        doc.getRootElement().getChildren()
    }
}
