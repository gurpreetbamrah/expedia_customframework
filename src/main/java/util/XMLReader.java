package util;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class XMLReader {

    static List<String> list;

    public List<String> readXML() {

        try {
            File file = new File("testsuite.xml");
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(file);
            document.getDocumentElement().normalize();
            NodeList nodeList = document.getElementsByTagName("Component");
            list = new ArrayList<String>();
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node nNode = nodeList.item(i);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    String className = eElement.getElementsByTagName("ClassName").item(0).getTextContent();
                    String param = eElement.getElementsByTagName("Param").item(0).getTextContent();
                    list.add(className+"__"+param);
                }
            }


        } catch (Exception e) {
        }

        return list;
    }


}

