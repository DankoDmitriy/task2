package com.danko.greenhouse.parser;

import com.danko.greenhouse.entity.AbstractFlower;
import com.danko.greenhouse.exception.FlowerException;
import com.danko.greenhouse.validator.CustomFileValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class SaxXmlParser implements XmlParser {

    public static Logger logger = LogManager.getLogger();
    private SAXParserFactory factory;
    private SAXParser parser;
    private Set<AbstractFlower> flowers;

    public SaxXmlParser() {
        flowers = new HashSet<>();
        try {
            factory = SAXParserFactory.newInstance();
            parser = factory.newSAXParser();
        } catch (ParserConfigurationException e) {
            logger.log(Level.ERROR, "Parser configuration Exception" + e);

        } catch (SAXException e) {
            logger.log(Level.ERROR, "Parser configuration Exception" + e);
        }
    }

    public Set<AbstractFlower> parserXml(String xmlFile) throws FlowerException {
        if (!CustomFileValidator.isFileValidation(xmlFile)) {
            logger.log(Level.ERROR, "File can not valid...");
            throw new FlowerException("File can not valid ...");
        }
        FlowerHandler flowerHandler = new FlowerHandler();
        flowerHandler.setFlowers(flowers);
        try {
            parser.parse(new File(xmlFile), flowerHandler);
        } catch (SAXException | IOException e) {
            logger.log(Level.ERROR, "File can not valid...");
            throw new FlowerException("File can not valid...");
        }
        return flowers;
    }
}
