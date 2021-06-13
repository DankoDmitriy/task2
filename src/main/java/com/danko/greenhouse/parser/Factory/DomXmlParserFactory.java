package com.danko.greenhouse.parser.Factory;

import com.danko.greenhouse.parser.DomXmlParser;
import com.danko.greenhouse.parser.XmlParser;

public class DomXmlParserFactory implements XmlParserFactory {
    @Override
    public XmlParser newParser() {
        return new DomXmlParser();
    }
}
