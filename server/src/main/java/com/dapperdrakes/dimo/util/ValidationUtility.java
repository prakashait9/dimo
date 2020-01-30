package com.dapperdrakes.dimo.util;

import java.io.File;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

public class ValidationUtility {

    public boolean isSameExtension(String actualExtension, String expectedExtension) {
	return actualExtension.equalsIgnoreCase(expectedExtension);
    }

    public boolean isSameExtension(File file, String expected) {
	return isSameExtension(FilenameUtils.getExtension(file.getName()), expected);
    }

    public boolean isSameExtension(MultipartFile file, String expected) {
	System.out.println("Recieved " + file.getOriginalFilename());
	return isSameExtension(FilenameUtils.getExtension(file.getOriginalFilename()), expected);
    }

    public boolean isSameExtension(File actual, File expected) {
	return isSameExtension(FilenameUtils.getExtension(actual.getName()),
		FilenameUtils.getExtension(expected.getName()));
    }

}
