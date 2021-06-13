package com.danko.greenhouse.main;

import com.danko.greenhouse.entity.AbstractFlower;
import com.danko.greenhouse.parser.DomXmlParser;
import com.danko.greenhouse.parser.SaxXmlParser;
import com.danko.greenhouse.parser.StaxXmlParser;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.util.Set;

import static com.danko.greenhouse.parser.FlowerXmlTag.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Set<AbstractFlower> flowers = null;
        String xmlFile = "test_data\\flowers_test.xml";

       DomXmlParser domXmlParser = new DomXmlParser();
        flowers = domXmlParser.parserXml(xmlFile);
//        for (AbstractFlower flower : flowers) {
//            System.out.println(flower.toString());
//        }
////
        SaxXmlParser saxXmlParser = new SaxXmlParser();
        flowers = saxXmlParser.parserXml(xmlFile);
//        for (AbstractFlower flower : flowers) {
//            System.out.println(flower.toString());
//        }


//        StAX
        StaxXmlParser staxXmlParser = new StaxXmlParser();
        flowers = staxXmlParser.parserXml(xmlFile);
//        for (AbstractFlower flower : flowers) {
//            System.out.println(flower.toString());
//        }

//        XMLInputFactory xmlInputFactory = XMLInputFactory.newFactory();
//        XMLEventReader reader = xmlInputFactory.createXMLEventReader(new FileInputStream(xmlFile));
//        while (reader.hasNext()) {
//
//            XMLEvent xmlEvent = reader.nextEvent();
////            System.out.println(xmlEvent);
//            if (xmlEvent.isStartElement()) {
//                StartElement startElement = xmlEvent.asStartElement();
//
//                if (startElement.getName().getLocalPart().equals(CUT_FLOWER.tagName())) {
//                    Attribute attribute = startElement.getAttributeByName(new QName("vendor-code"));
//                    System.out.println("CUT_FLOWER");
////                       System.out.println(attribute.getValue());
//                    if (attribute != null) {
//                        System.out.println(attribute.getValue());
//                    }
//                }
//
//                if (startElement.getName().getLocalPart().equals(TITLE.tagName())) {
////                    Attribute attribute = startElement.getAttributeByName(new QName("title"));
//////                    System.out.println(attribute.getValue());
//                    xmlEvent = reader.nextEvent();
//                    System.out.println(xmlEvent.asCharacters().getData());
//                }
//            }
//
//
//        }

    }
}
