package com.danko.greenhouse.validator;

import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class FlowerXmlAndXsdValidatorTest {
    public static final String CORRECT_XML_FILE = "test_data\\flowers_test.xml";
    public static final String NOT_CORRECT_XML_FILE_EMPTY = "test_data\\flowers_test_empty.xml";
    public static final String NOT_CORRECT_XML_FILE_ = "test_data\\flowers_test_empty_error.xml";
    public static final String SCHEMA_XSD = "test_data\\schema.xsd";

    @Test
    public void testPositiveCorrectXmlFile() {
        boolean result = FlowerXmlAndXsdValidator.xmlAndXsdValid(CORRECT_XML_FILE, SCHEMA_XSD);
        assertTrue(result);
    }

    @Test
    public void testNegativeCorrectXmlFile() {
        boolean result = FlowerXmlAndXsdValidator.xmlAndXsdValid(NOT_CORRECT_XML_FILE_EMPTY, SCHEMA_XSD);
        assertFalse(result);
    }

    @Test
    public void testNegativeCorrectXmlFileError() {
        boolean result = FlowerXmlAndXsdValidator.xmlAndXsdValid(NOT_CORRECT_XML_FILE_, SCHEMA_XSD);
        assertFalse(result);
    }
}
