package com.tofba.common.util;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import com.tofba.common.system.UserSingleton;

/**
 * 获取资源文件
 * 
 * @author Henry(fba02)
 * @version [版本号, 2018年5月17日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class MyLocale {
    private String propertyName = "package";    
    private String country;    
    private String launage;    
    private Locale locale;    
    private ResourceBundle resourceBundle;    
    MyDate myDate = new MyDate();
    
    UserSingleton userSingleton = new UserSingleton();    
    public String getCountry() {
        return country;
    }
    
    public void setCountry(String country) {
        this.country = country;
    }
    
    public String getLaunage() {
        return launage;
    }
    
    public void setLaunage(String launage) {
        this.launage = launage;
    }
    
    public Locale getLocale() {
        return locale;
    }
    
    public void setLocale(Locale locale) {
        this.locale = locale;
    }
    
    public ResourceBundle getResourceBundle() {
        return resourceBundle;
    }
    
    public void setResourceBundle(ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
    }
    
    public String getPropertyName() {
        return propertyName;
    }
    
    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }
    
    public MyLocale() {
        String str = "cn";
        setLocale(new Locale(str));
        setResourceBundle(ResourceBundle.getBundle(this.propertyName, this.locale));
    }
    
    public MyLocale(String paramString) {
        setCountry(paramString);
        setLocale(new Locale(paramString));
        setResourceBundle(ResourceBundle.getBundle(this.propertyName, this.locale));
    }
    
    public String getText(String paramString) {
        paramString = convert(paramString);
        String str = paramString;
        try {
            str = this.resourceBundle.getString(paramString);
        } catch (Exception localException1) {
        }
        str = str.replaceAll("\\{time\\}", this.myDate.getCurrentDateTime());
        try {
            if (null != this.userSingleton.getUser()) {
                str = str.replaceAll("\\{name\\}", this.userSingleton.getUser().getUsername());
                str = str.replaceAll("\\{ip\\}", this.userSingleton.getUser().getRemoteAddr());
            }
        } catch (Exception localException2) {
        }
        return str;
    }
    
    public String getText(String paramString1, String paramString2) {
        paramString1 = convert(paramString1);
        String str = paramString1;
        try {
            str = this.resourceBundle.getString(paramString1);
        } catch (Exception localException1) {
        }
        if (null != str) {
            if (null == paramString2) {
                paramString2 = "";
            }
            str = str.replaceAll("\\{0\\}", paramString2);
        }
        str = str.replaceAll("\\{time\\}", this.myDate.getCurrentDateTime());
        try {
            if (null != this.userSingleton.getUser()) {
                str = str.replaceAll("\\{name\\}", this.userSingleton.getUser().getUsername());
                str = str.replaceAll("\\{ip\\}", this.userSingleton.getUser().getRemoteAddr());
            }
        } catch (Exception localException2) {
        }
        return str;
    }
    
    public String getText(String paramString, List<?> paramList) {
        paramString = convert(paramString);
        String str1 = paramString;
        try {
            str1 = this.resourceBundle.getString(paramString);
        } catch (Exception localException1) {
        }
        if (null != str1) {
            for (int i = 0; i < paramList.size(); ++i) {
                String str2 = "";
                if (null != str2) {
                    str2 = "";
                    if (null != paramList.get(i)) {
                        str2 = paramList.get(i).toString();
                    }
                }
                str1 = str1.replaceAll("\\{" + i + "\\}", str2);
            }
        }
        str1 = str1.replaceAll("\\{time\\}", this.myDate.getCurrentDateTime());
        try {
            if (null != this.userSingleton.getUser()) {
                str1 = str1.replaceAll("\\{name\\}", this.userSingleton.getUser().getUsername());
                str1 = str1.replaceAll("\\{ip\\}", this.userSingleton.getUser().getRemoteAddr());
            }
        } catch (Exception localException2) {
        }
        return str1;
    }
    
    public String getText(String paramString, String[] paramArrayOfString) {
        paramString = convert(paramString);
        String str1 = paramString;
        try {
            str1 = this.resourceBundle.getString(paramString);
        } catch (Exception localException1) {
            localException1.printStackTrace();
        }
        if (null != str1) {
            for (int i = 0; i < paramArrayOfString.length; ++i) {
                String str2 = "";
                if (null != str2) {
                    str2 = paramArrayOfString[i].toString();
                }
                str1 = str1.replaceAll("\\{" + i + "\\}", str2);
            }
        }
        str1 = str1.replaceAll("\\{time\\}", this.myDate.getCurrentDateTime());
        try {
            if (null != this.userSingleton.getUser()) {
                str1 = str1.replaceAll("\\{name\\}", this.userSingleton.getUser().getUsername());
                str1 = str1.replaceAll("\\{ip\\}", this.userSingleton.getUser().getRemoteAddr());
            }
        } catch (Exception localException2) {
            localException2.printStackTrace();
        }
        return str1;
    }
    
    public String convert(String paramString) {
        return paramString.replaceAll("_", ".").toLowerCase();
    }
}