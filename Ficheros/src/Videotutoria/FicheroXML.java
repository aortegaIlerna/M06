package Videotutoria;

import com.thoughtworks.xstream.XStream;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FicheroXML {

    public void parserDOM() {
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = documentBuilderFactory.newDocumentBuilder();
            Document document = builder.parse(new File("ficheros/fichero1.xml"));
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


    public<T> void writeXML(File file, T object) {
        XStream xstream = new XStream();
        xstream.autodetectAnnotations(true);
        try {
            xstream.toXML(object,new FileOutputStream(file));
            System.out.println("Fichero "+file.getName()+" crado correctamente");
        } catch (FileNotFoundException e) {
            throw new MyXMLException("No se ha generado el fichero");
        }


    }

}
