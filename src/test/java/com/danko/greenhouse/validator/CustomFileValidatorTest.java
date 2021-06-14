package com.danko.greenhouse.validator;

import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class CustomFileValidatorTest {
    public static final String CORRECT_FILE = "test_data\\flowers_test.xml";
    public static final String NOT_CORRECT_FILE = "test_data\\flowers_test_empty.xml";

    @Test
    public void testPositiveExistIsFileValid() {
        boolean result = CustomFileValidator.isFileValid(CORRECT_FILE);
        assertTrue(result);
    }

    @Test
    public void testNegativeExistIsFileValid() {
        boolean result = CustomFileValidator.isFileValid(NOT_CORRECT_FILE);
        assertFalse(result);
    }

    @Test
    public void testNegativePathIsFileValid() {
        boolean result = CustomFileValidator.isFileValid(null);
        assertFalse(result);
    }

    @Test
    public void testPositiveIsFileIsFileValid() {
        boolean result = CustomFileValidator.isFileValid(CORRECT_FILE);
        assertTrue(result);
    }

    @Test
    public void testNegativeIsFileIsFileValid() {
        boolean result = CustomFileValidator.isFileValid(NOT_CORRECT_FILE);
        assertFalse(result);
    }
}
