package com.danko.greenhouse.validator;

import java.io.File;

public class CustomFileValidator {
    public static boolean isFileValid(String fileAddress) {
        if (fileAddress == null) {
            return false;
        }
        File file = new File(fileAddress);
        if (file.exists() && file.canRead() && file.isFile() && file.length() > 0) {
            return true;
        }
        return false;
    }
}
