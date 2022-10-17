package homework18;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DomParser {

     static void writeFromXMLtoTxt(File pathToXml) throws Exception {

        Document dataDoc = getDomDocument(pathToXml);
        String fileName = getFileName(dataDoc);

        try (FileWriter writer = new FileWriter(fileName + ".txt", true)) {
            List<String> sonnetList = getSonnetList(dataDoc);

            for (String str : sonnetList) {
                writer.write("\n" + str);
                writer.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

   private static Document getDomDocument(File pathToXml) throws IOException {
       if (!pathToXml.isDirectory()) {
           throw new IOException((pathToXml.getName()) + " is not a directory!");
       } else if (pathToXml.listFiles().length > 1) {
           throw new IOException((pathToXml.getName()) + " contains more than one file");
       } else if (pathToXml.listFiles().length == 0 || !pathToXml.listFiles()[0].getName().endsWith(".xml")) {
           throw new IOException(pathToXml.getName() + " doesn't contain any xml file!");
       }

        Document dataDoc = null;

        try {
            dataDoc = DocumentBuilderFactory
                    .newInstance()
                    .newDocumentBuilder()
                    .parse(pathToXml.listFiles()[0]);

            dataDoc.getDocumentElement().normalize();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return dataDoc;
    }

    private static String getFileName(Document dataDoc) {
        return (dataDoc.getElementsByTagName("firstName").item(0).getTextContent() +
                "_" +
                dataDoc.getElementsByTagName("lastName").item(0).getTextContent() +
                "_" +
                dataDoc.getElementsByTagName("title").item(0).getTextContent());
    }

    private static List<String> getSonnetList(Document dataDoc) {

        List<String> sonnetList = new ArrayList<>();
        int length = dataDoc.getElementsByTagName("line").getLength();

        for (int i = 0; i < length; i++) {
            String line = dataDoc
                    .getElementsByTagName("line")
                    .item(i)
                    .getTextContent();

            sonnetList.add(line);
        }
        return sonnetList;
    }
}
