package com.danko.greenhouse.validator;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.IOException;

public class FlowerXmlAndXsdValidator {
    private static Logger logger = LogManager.getLogger();

    public static boolean xmlAndXsdValid(String pathToXmlFile, String pathToXsdSchema) {
        boolean result = false;
        if (!(CustomFileValidator.isFileValid(pathToXmlFile) && CustomFileValidator.isFileValid(pathToXsdSchema))) {
            return result;
        }
        String schemaNameSpace = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        SchemaFactory factory = SchemaFactory.newInstance(schemaNameSpace);
        Source xmlSource = new StreamSource(pathToXmlFile);
        Source xsdSource = new StreamSource(pathToXsdSchema);
        try {
            Schema schema = factory.newSchema(xsdSource);
            Validator validator = schema.newValidator();
            validator.validate(xmlSource);
            result = true;
        } catch (SAXException | IOException e) {
            logger.log(Level.ERROR, "File XML or XSD are not valid" + e.toString());
        }
        return result;
    }
}
