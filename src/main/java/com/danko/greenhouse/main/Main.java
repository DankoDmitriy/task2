package com.danko.greenhouse.main;

import com.danko.greenhouse.entity.AbstractFlower;
import com.danko.greenhouse.parser.XmlParser;
import com.danko.greenhouse.parser.factory.SaxXmlParserFactory;
import com.danko.greenhouse.parser.factory.XmlParserFactory;

import java.util.Set;

public class Main {


    public static void main(String[] args) throws Exception {
        Set<AbstractFlower> flowers = null;
        String xmlFile = "test_data\\flowers_test.xml";

        XmlParserFactory xmlParserFactory = new SaxXmlParserFactory();

        XmlParser xmlParser = xmlParserFactory.newParser();
        flowers = xmlParser.parserXml(xmlFile);
        for (AbstractFlower flower : flowers) {
            System.out.println(flower.toString());
        }
    }
}
