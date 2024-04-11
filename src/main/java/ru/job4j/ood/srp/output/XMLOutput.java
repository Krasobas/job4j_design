package ru.job4j.ood.srp.output;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;


public class XMLOutput implements Output {

    private final String collectionName;
    private final String itemName;
    private  DocumentBuilder builder;
    private Transformer transformer;

    public XMLOutput(String collection, String element) {
        this.collectionName = collection;
        this.itemName = element;
    }

    public DocumentBuilder getBuilder() throws ParserConfigurationException {
        if (builder == null) {
            builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        }
        return builder;
    }

    public Transformer getTransformer() throws TransformerConfigurationException {
        if (transformer == null) {
            transformer = TransformerFactory.newInstance() .newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
        }
        return transformer;
    }

    public String beautify(Document doc) throws TransformerException {
        DOMSource source = new DOMSource(doc);
        StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult(writer);
        getTransformer().transform(source, result);
        return writer.toString();
    }

    private String prepareText(String text) {
        if (text.contains(";")) {
            text = text.replace(";", "");
        }
        return text;
    }

    @Override
    public String print(List<Map<String, String>> data) {
        try {
            Document doc = getBuilder().newDocument();
            Element collection = doc.createElement(collectionName);
            doc.appendChild(collection);
            for (Map<String, String> map : data) {
                Element collectionElement = doc.createElement(itemName);
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    Element item = doc.createElement(prepareText(entry.getKey()));
                    item.appendChild(doc.createTextNode(prepareText(entry.getValue())));
                    collectionElement.appendChild(item);
                }
                collection.appendChild(collectionElement);
            }
            return beautify(doc);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
