package homework18;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class XMLParserSolution {

    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {
            File pathToXml = new File("xmlfiles");


            System.out.println("Press 1 for Sax parsing of xml file, or 2 for Dom parsing");

            while (scanner.hasNext()) {
                int typeOfParsing = scanner.nextInt();
                if (typeOfParsing == 1) {
                    writeFromXmlToTxtBySaxParsing(pathToXml);
                    break;
                } else if (typeOfParsing == 2) {
                    DomParser.writeFromXMLtoTxt(pathToXml);
                    break;
                } else System.out.println("You should choose number 1 or 2. Please, try again");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static void writeFromXmlToTxtBySaxParsing(File pathToXml) throws ParserConfigurationException, SAXException, IOException {
        if (!pathToXml.isDirectory()) {
            throw new IOException((pathToXml.getName()) + " is not a directory!");
        } else if (pathToXml.listFiles().length > 1) {
            throw new IOException((pathToXml.getName()) + " contains more than one file");
        } else if (pathToXml.listFiles().length == 0 || !pathToXml.listFiles()[0].getName().endsWith(".xml")) {
            throw new IOException(pathToXml.getName() + " doesn't contain any xml file!");
        }

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();
        DefaultHandler handler = new SonnetHandler();
        saxParser.parse(pathToXml.listFiles()[0], handler);
    }

}
