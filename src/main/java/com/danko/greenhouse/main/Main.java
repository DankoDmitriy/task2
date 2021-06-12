package com.danko.greenhouse.main;

import com.danko.greenhouse.entity.AbstractFlower;
import com.danko.greenhouse.parser.DomXmlParser;
import com.danko.greenhouse.parser.SaxXmlParser;

import java.util.Set;

public class Main {
    public static void main(String[] args) throws Exception {
        Set<AbstractFlower> flowers = null;

        DomXmlParser domXmlParser = new DomXmlParser();
        flowers = domXmlParser.parserXml("test_data\\flowers_test.xml");
        for (AbstractFlower flower : flowers) {
            System.out.println(flower.toString());
        }

        SaxXmlParser saxXmlParser = new SaxXmlParser();
        flowers = saxXmlParser.parserXml("test_data\\flowers_test.xml");
        for (AbstractFlower flower : flowers) {
            System.out.println(flower.toString());
        }

    }
}
