package com.danko.greenhouse.parser;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class FlowerErrorHandler implements ErrorHandler {
    public static Logger logger = LogManager.getLogger();

    @Override
    public void warning(SAXParseException e) throws SAXException {
        logger.log(Level.WARN, findErrorPosition(e) + " - " + e.getMessage());
    }

    @Override
    public void error(SAXParseException e) throws SAXException {
        logger.log(Level.ERROR, findErrorPosition(e) + " - " + e.getMessage());
    }

    @Override
    public void fatalError(SAXParseException e) throws SAXException {
        logger.log(Level.FATAL, findErrorPosition(e) + " - " + e.getMessage());
    }

    private String findErrorPosition(SAXParseException e) {
        return "Check the line " + e.getLineNumber() + ", column is " + e.getColumnNumber();
    }
}
