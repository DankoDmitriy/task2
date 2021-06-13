package com.danko.greenhouse.parser.Factory;

import com.danko.greenhouse.parser.StaxXmlParser;
import com.danko.greenhouse.parser.XmlParser;

public class StaxXmlParserFactory implements XmlParserFactory {
    @Override
    public XmlParser newParser() {
        return new StaxXmlParser();
    }
}
