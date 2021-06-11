package com.danko.greenhouse.main;

import com.danko.greenhouse.customXmlParser.DomXmlParser;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;

public class Main {
    public static void main(String[] args) throws Exception {

//        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
//        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
//        Document document = documentBuilder.parse("test_data\\flowers_test.xml");
//        document.getDocumentElement().normalize();
//
//        NodeList nodeList = document.getElementsByTagName("cut-flower");
////        NodeList nodeList = document.getElementsByTagName("flower");
//        for (int i = 0; i < nodeList.getLength(); i++) {
//
//            NamedNodeMap namedNodeMap = nodeList.item(i).getAttributes();
//            System.out.println(namedNodeMap.getNamedItem("cut-date").getNodeValue());
//
//            Node node = nodeList.item(i);
//            Element element = (Element) node;
//            NodeList title = element.getElementsByTagName("title");
//            NodeList titleList = title.item(0).getChildNodes();
//            String titleValue = titleList.item(0).getNodeValue();
//            System.out.println(node);
//            System.out.println(titleValue);
//
//            System.out.println(FlowerXmlTag.BUD_COLOR.getTagName());
//    }

//        String schemaNameSpace = XMLConstants.W3C_XML_SCHEMA_NS_URI;
//        SchemaFactory factory = SchemaFactory.newInstance(schemaNameSpace);
//        Source xmlSource = new StreamSource("test_data\\flowers_test.xml");
//        Source xsdSource = new StreamSource("test_data\\schema.xsd");
//
//        Schema schema = factory.newSchema(xsdSource);
//        Validator validator = schema.newValidator();
//        validator.validate(xmlSource);

//
//        DomXmlParser domXmlParser = new DomXmlParser();
//        domXmlParser.purserXml("test_data\\flowers_test.xml");

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        parser.parse(new File("test_data\\flowers_test.xml"),new CustomHandler());
//        parser.parse("test_data\\flowers_test.xml",new DefaultHandler());
    }

    public static  class CustomHandler extends DefaultHandler{

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
//            super.startElement(uri, localName, qName, attributes);

            if (qName.equals("cut-flower")){
                System.out.println(attributes.getValue("vendor-code"));
                System.out.println(attributes.getValue("planting-date"));
                System.out.println(attributes.getValue("cut-date"));
                System.out.println(attributes.getValue("poisonous"));
            }
            if (qName.equals("title")){
//                System.out.println(attributes.);
            }


        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            super.endElement(uri, localName, qName);

        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            super.characters(ch, start, length);

        }
    }
}
