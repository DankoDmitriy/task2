package com.danko.greenhouse.parser;

import com.danko.greenhouse.entity.AbstractFlower;
import com.danko.greenhouse.exception.FlowerException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.Set;

import static org.testng.Assert.*;

public class XmlParserTest {
    private static final String CORRECT_XML_FILE = "test_data\\flowers_test.xml";
    public static final String NOT_CORRECT_XML_FILE = "test_data\\flowers_test_empty.xml";
    private static final int ACTUAL_FLOWERS_IN_FILE = 16;

    @DataProvider
    public Object[][] makeParse() {
        return new Object[][]{
                {"DOM", new DomXmlParser()},
                {"SAX", new SaxXmlParser()},
                {"STAX", new StaxXmlParser()},
        };
    }

    @Test(dataProvider = "makeParse")
    public void positiveCountParserTest(String type, XmlParser parser) throws Exception {
        Set<AbstractFlower> flowers = new HashSet<>();
        flowers = parser.parserXml(CORRECT_XML_FILE);
        int expected = flowers.size();
        assertEquals(ACTUAL_FLOWERS_IN_FILE, expected);
    }

    @Test(dataProvider = "makeParse")
    public void positiveExceptionParseTest(String type, XmlParser parser) {
        assertThrows(FlowerException.class, () -> parser.parserXml(NOT_CORRECT_XML_FILE));
    }


}
