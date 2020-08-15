package com.tofba.blog.utils;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

/**
 * 国际化工具类
 * 
 * @author Henry(fba02)
 * @version [版本号, 2020年7月25日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Component
public class LocaleMessageUtil {    
    @Autowired
    private MessageSource messageSource;
    
    public String getMessage(String code) {
        return this.getMessage(code, new Object[] {});
    }
    
    public String getMessage(String code, String defaultMessage) {
        return this.getMessage(code, null, defaultMessage);
    }
    
    public String getMessage(String code, String defaultMessage, Locale locale) {
        return this.getMessage(code, null, defaultMessage, locale);
    }
    
    public String getMessage(String code, Locale locale) {
        return this.getMessage(code, null, "", locale);
    }
    
    public String getMessage(String code, Object[] args) {
        return this.getMessage(code, args, "");
    }
    
    public String getMessage(String code, Object[] args, Locale locale) {
        return this.getMessage(code, args, "", locale);
    }
    
    public String getMessage(String code, Object[] args, String defaultMessage) {
        Locale locale = LocaleContextHolder.getLocale();
        return this.getMessage(code, args, defaultMessage, locale);
    }
    
    public String getMessage(String code, Object[] args, String defaultMessage, Locale locale) {
        return messageSource.getMessage(code, args, defaultMessage, locale);
    }
}
