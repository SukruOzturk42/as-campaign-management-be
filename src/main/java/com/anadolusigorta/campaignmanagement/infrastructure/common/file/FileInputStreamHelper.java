package com.anadolusigorta.campaignmanagement.infrastructure.common.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileInputStreamHelper extends FileInputStream {

    private final File file;

    public FileInputStreamHelper(File file) throws FileNotFoundException {
        super(file);
        this.file = file;
    }

    @Override
    public void close() throws IOException {
        super.close();
        try {
            if (file != null) {
                file.delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}