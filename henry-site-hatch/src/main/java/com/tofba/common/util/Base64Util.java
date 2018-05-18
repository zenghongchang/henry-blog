package com.tofba.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.codec.binary.Base64;

public class Base64Util {
    
    public static String encodeBase64File(String path) {
        File file = new File(path);
        FileInputStream inputFile = null;
        try {
            inputFile = new FileInputStream(file);
            byte[] buffer = new byte[(int)file.length()];
            inputFile.read(buffer);
            return new String(Base64.encodeBase64(buffer));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (null != inputFile) {
                try {
                    inputFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
    }
    
    public static void decoderBase64File(String base64Code, String targetPath)
        throws Exception {
        byte[] buffer = Base64.decodeBase64(base64Code);
        FileOutputStream out = new FileOutputStream(targetPath);
        out.write(buffer);
        out.close();
    }    
}