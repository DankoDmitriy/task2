package com.danko.greenhouse.main;

import com.danko.greenhouse.entity.AbstractFlower;
import com.danko.greenhouse.parser.DomXmlParser;
import com.danko.greenhouse.parser.SaxXmlParser;
import com.danko.greenhouse.parser.StaxXmlParser;

import java.util.Set;

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
    }
}
