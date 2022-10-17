package homework18;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SonnetHandler extends DefaultHandler {
    private static final String FIRSTNAME = "firstName";
    private static final String LASTNAME = "lastName";
    private static final String TITLE = "title";
    private static final String LINE = "line";
    private static final String LINES = "lines";

    private String firstname;
    private String lastName;
    private String title;
    private List<String> lines;
    private StringBuilder elementValue;
//    private Sonnet sonnet;

//    public SonnetHandler(String firstname, String lastName, String title, String line, List<String> lines) {
//        this.firstname = firstname;
//        this.lastName = lastName;
//        this.title = title;
//        this.line = line;
//        this.lines = lines;
//    }

//    @Override
//    public void startDocument() throws SAXException {
//        lines = new ArrayList<>();
//    }


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        switch (qName) {
            case FIRSTNAME:
            case LASTNAME:
            case TITLE:
            case LINE:
                elementValue = new StringBuilder();
                break;
            case LINES:
                lines=new ArrayList<>();
        }
    }


    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (elementValue == null) {
            elementValue = new StringBuilder();
        } else {
            elementValue.append(ch, start, length);
        }
    }


    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName) {
            case FIRSTNAME:
                firstname = elementValue.toString();
                break;
            case LASTNAME:
                lastName = elementValue.toString();
                break;
            case TITLE:
                title = elementValue.toString();
                break;
            case LINE:
                String line = elementValue.toString();
                lines.add(line);
                break;
        }
    }

    @Override
    public void endDocument() throws SAXException {
        try (FileWriter writer = new FileWriter(firstname + "_" + lastName + "_" + title + ".txt", true)) {


            for (String str : lines) {
                writer.write("\n" + str);
                writer.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
