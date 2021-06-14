package com.danko.greenhouse.validator;

import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class CustomFileValidatorTest {
    public static final String CORRECT_FILE = "test_data\\flowers_test.xml";
    public static final String NOT_CORRECT_FILE = "test_data\\flowers_test_empty.xml";

    @Test
    public void testPositiveExistIsFileValidation() {
        boolean result = CustomFileValidator.isFileValidation(CORRECT_FILE);
        assertTrue(result);
    }

    @Test
    public void testNegativeExistIsFileValidation() {
        boolean result = CustomFileValidator.isFileValidation(NOT_CORRECT_FILE);
        assertFalse(result);
    }

    @Test
    public void testNegativePathIsFileValidation() {
        boolean result = CustomFileValidator.isFileValidation(null);
        assertFalse(result);
    }

    @Test
    public void testPositiveIsFileIsFileValidation() {
        boolean result = CustomFileValidator.isFileValidation(CORRECT_FILE);
        assertTrue(result);
    }

    @Test
    public void testNegativeIsFileIsFileValidation() {
        boolean result = CustomFileValidator.isFileValidation(NOT_CORRECT_FILE);
        assertFalse(result);
    }
}
