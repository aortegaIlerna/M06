package Videotutoria;

import com.thoughtworks.xstream.XStream;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.*;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class FicheroXML {

    public void parserDOM() {
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = documentBuilderFactory.newDocumentBuilder();
            Document document = builder.parse(new File("Ficheros/ficheros/fichero1.xml"));
            NodeList nList = document.getElementsByTagName("alumno");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node node = nList.item(temp);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;
                    /*TODO crear condición del centro y curso*/
                    if (eElement.getAttribute("curso").equals("M07")) {
                        System.out.println("Alumno del curso : " + eElement.getAttribute("curso"));
                        System.out.println("Nombre : " + eElement.getElementsByTagName("nombre").item(0).getTextContent());
                    }
                }
            }
        } catch (IOException | SAXException | ParserConfigurationException ex) {
            ex.printStackTrace();
            System.out.println("No se ha podido leer el fichero XML");

        }
    }

    public void parserSAX() {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();


            DefaultHandler handler = new DefaultHandler() {
                /*Variable creadas para poder comprobar si hay datos*/
                boolean bNombre = false;
                boolean bCentro = false;

                /*Funcion invocada al inicio de cada elemento*/
                public void startElement(String uri, String localName, String qName, Attributes attributes) {
                    if (qName.equalsIgnoreCase("alumnos"))
                        System.out.println(qName);
                    if (qName.equalsIgnoreCase("alumno")) {
                        System.out.print("\t" + qName);
                        String id = attributes.getValue("curso");
                        System.out.println("\tid: " + id);
                    }
                    if (qName.equalsIgnoreCase("nombre")) {
                        bNombre = true;
                    }
                    if (qName.equalsIgnoreCase("centro")) {
                        bCentro = true;
                    }
                }

                /*Funcion invocada al fin de cada elemento*/
                public void endElement(String uri, String localName, String qName) {
                    if (qName.equalsIgnoreCase("alumnos"))
                        System.out.println(qName);
                    if (qName.equalsIgnoreCase("alumno"))
                        System.out.println("\t" + qName);
                }

                /*Función invocada automaticamente y nos devuelve el contenido del elemento*/
                public void characters(char[] ch, int start, int length) {
                    if (bNombre) {
                        System.out.println("\t\tNombre: " + new String(ch, start, length));
                        bNombre = false;
                    }
                    if (bCentro) {
                        System.out.println("\t\tCentro: " + new String(ch, start, length));
                        bCentro = false;
                    }
                }
            };

            File file = new File("Ficheros/ficheros/fichero1.xml");
            InputStream inputStream = new FileInputStream(file);
            Reader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);

            InputSource is = new InputSource(reader);
            is.setEncoding("UTF-8");
            saxParser.parse(is, handler);

        } catch (SAXException | ParserConfigurationException | IOException e) {
            e.printStackTrace();
        }
    }


    public <T> void writeXML(File file, T object) {
        XStream xstream = new XStream();
        xstream.autodetectAnnotations(true);
        try {
            xstream.toXML(object, new FileOutputStream(file));
            System.out.println("Fichero " + file.getName() + " crado correctamente");
        } catch (FileNotFoundException e) {
            throw new MyXMLException("No se ha generado el fichero");
        }


    }

}
