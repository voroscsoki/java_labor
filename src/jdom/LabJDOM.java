package jdom;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.File;
import java.io.FileOutputStream;
import java.util.*;

public class LabJDOM {
    public static void main(String[] args) {
        int inFileArg = Arrays.asList(args).indexOf("-i") + 1;
        int outFileArg = Arrays.asList(args).indexOf("-o") + 1;
        String inFilename = (inFileArg == 0) ? "bme.xml" : args[inFileArg];
        String outFilename = (outFileArg == 0) ? "bme_bus.xml" : args[outFileArg];

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
            }
            for(var e : removable) e.detach();

            XMLOutputter xout = new XMLOutputter(Format.getPrettyFormat());
            xout.output(doc, new FileOutputStream(outFilename));
        } catch (Exception e) {e.printStackTrace();}

    }
}
