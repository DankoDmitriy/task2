package com.danko.greenhouse.parser.Factory;

import com.danko.greenhouse.parser.SaxXmlParser;
import com.danko.greenhouse.parser.XmlParser;

public class SaxXmlParserFactory implements XmlParserFactory {
    @Override
    public XmlParser newParser() {
        return new SaxXmlParser();
    }
}
