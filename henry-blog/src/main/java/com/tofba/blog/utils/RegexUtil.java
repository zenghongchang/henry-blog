package com.tofba.blog.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class RegexUtil {
    final static String MOBILE_PHONE = "^(?:\\+?\\(?86\\)?\\-?)?1(?:3\\d{3}|5[^4\\D]\\d{2}|8\\d{3}|7(?:[235-8]\\d{2}|4(?:0\\d|1[0-2]|9\\d))|9[0-35-9]\\d{2}|66\\d{2})\\d{6}$";
    final static String TELE_PHONE = "^(0\\d{2,3}\\-?)?\\d{7,8}$";
    
    public static boolean isMatch(String regex, String orginal) {
        if (StringUtils.isBlank(orginal)) { //$NON-NLS-1$
            return false;
        }
        Pattern pattern = Pattern.compile(regex);
        Matcher isNum = pattern.matcher(orginal);
        return isNum.matches();
    }
    
    public static Boolean isChinaPhone(String phone) {
        return isMatch(MOBILE_PHONE, phone) || isMatch(TELE_PHONE, phone);
    }
    
    public static void main(String[] args) {
        System.out.println(isChinaPhone("13266829617"));
        System.out.println(isChinaPhone("181246113450"));
    }
}
