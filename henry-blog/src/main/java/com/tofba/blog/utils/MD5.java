package com.tofba.blog.utils;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.web.multipart.MultipartFile;

/**
 * MD5加密
 * 
 * @author Henry(fba02)
 * @version [版本号, 2020年7月25日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class MD5 {
    private String originalString;
    
    public MD5(String originalString) {
        this.originalString = originalString;
    }
    
    /**
     * 计算文件MD5编码
     * 
     * @param file
     * @return
     * @throws Exception
     */
    public static byte[] createChecksum(MultipartFile file)
        throws Exception {
        InputStream fis = file.getInputStream();        
        byte[] buffer = new byte[1024];
        MessageDigest complete = MessageDigest.getInstance("MD5");
        int numRead;
        
        do {
            numRead = fis.read(buffer);
            if (numRead > 0) {
                complete.update(buffer, 0, numRead);
            }
        } while (numRead != -1);
        
        fis.close();
        return complete.digest();
    }
    
    /**
     * 生成文件hash值
     * 
     * @param file
     * @return
     * @throws Exception
     */
    public static String getMD5Checksum(MultipartFile file)
        throws Exception {
        byte[] b = createChecksum(file);
        String result = "";
        
        for (int i = 0; i < b.length; i++) {
            result += Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1);
        }
        return result;
    }
    
    public String toMD5() {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(this.originalString.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            System.out.println("NoSuchAlgorithmException caught!");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }        
        byte[] byteArray = messageDigest.digest();        
        StringBuilder md5StrBuff = new StringBuilder();        
        for (int i = 0; i < byteArray.length; i++) {
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
                md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
            else
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
        }        
        return md5StrBuff.toString();
    }
}