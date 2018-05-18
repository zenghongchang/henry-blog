package com.tofba.common.util;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.cglib.beans.BeanMap;

/**
 * Bean工具类
 * 
 * @author Henry(fba02)
 * @version [版本号, 2017年8月24日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class BeanUtil {
    /**
     * 对象转Map，不进行驼峰转下划线，不忽略值为空的字段
     * 
     * @param <T> Bean类型
     * @param bean bean对象
     * @return Map
     */
    public static <T> Map<String, Object> beanToMap(T bean) {
        return beanToMap(bean, false, false);
    }
    
    /**
     * 对象转Map
     * 
     * @param <T> Bean类型
     * @param bean bean对象
     * @param isToUnderlineCase 是否转换为下划线模式
     * @param ignoreNullValue 是否忽略值为空的字段
     * @return Map
     */
    public static <T> Map<String, Object> beanToMap(T bean, boolean isToUnderlineCase, boolean ignoreNullValue) {
        
        if (bean == null) {
            return null;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            final PropertyDescriptor[] propertyDescriptors = getPropertyDescriptors(bean.getClass());
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                // 过滤class属性
                if (false == key.equals("class") && false == key.equals("declaringClass")) {
                    // 得到property对应的getter方法
                    Method getter = property.getReadMethod();
                    Object value = getter.invoke(bean);
                    if (false == ignoreNullValue || (null != value && false == value.equals(bean))) {
                        map.put(isToUnderlineCase ? StringUtil.toUnderlineCase(key) : key, value);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
    
    /**
     * Bean转Map
     * @param bean
     * @return
     * @author  Henry(fba02)
     * @version  [版本号, 2017年12月1日]
     * @see [类、类#方法、类#成员]
     */
    public static <T> Map<String, Object> beanToMap3rdParty(T bean) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (bean != null) {
            BeanMap beanMap = BeanMap.create(bean);
            for (Object key : beanMap.keySet()) {
                map.put(key + "", beanMap.get(key));
            }
        }
        return map;
    }
    
     /**
     * 获得Bean字段描述数组
     * 
     * @param clazz Bean类
     * @return 字段描述数组
     * @throws IntrospectionException 获取属性异常
     */
    public static PropertyDescriptor[] getPropertyDescriptors(Class<?> clazz)
        throws IntrospectionException {
        return Introspector.getBeanInfo(clazz).getPropertyDescriptors();
    }
    
    /**
     * 获得字段值，通过反射直接获得字段值，并不调用getXXX方法<br>
     * 对象同样支持Map类型，fieldName即为key
     * 
     * @param bean Bean对象
     * @param fieldName 字段名
     * @return 字段值
     */
    public static Object getFieldValue(Object bean, String fieldName) {
        if (null == bean || StringUtils.isBlank(fieldName)) {
            return null;
        }
        
        if (bean instanceof Map) {
            return ((Map<?, ?>)bean).get(fieldName);
        } else if (bean instanceof List) {
            return ((List<?>)bean).get(Integer.parseInt(fieldName));
        } else if (bean instanceof Collection) {
            return ((Collection<?>)bean).toArray()[Integer.parseInt(fieldName)];
        } else if (ArrayUtil.isArray(bean)) {
            return Array.get(bean, Integer.parseInt(fieldName));
        } else {// 普通Bean对象
            Field field;
            try {
                field = getDeclaredField(bean.getClass(), fieldName);
                if (null != field) {
                    field.setAccessible(true);
                    return field.get(bean);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    
    /**
     * 查找指定类中的所有字段（包括非public字段）， 字段不存在则返回<code>null</code>
     * 
     * @param clazz 被查找字段的类
     * @param fieldName 字段名
     * @return 字段
     * @throws SecurityException 安全异常
     */
    public static Field getDeclaredField(Class<?> clazz, String fieldName)
        throws SecurityException {
        if (null == clazz || StringUtils.isBlank(fieldName)) {
            return null;
        }
        try {
            return clazz.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static Object typeConversion(String fieldType,Object value) {
        if(StringUtils.isNotBlank(fieldType) && null != value) {
            if (fieldType.equals("class java.lang.String")){
                value = value.toString();
            }else if (fieldType.equals("class java.lang.Double")) {
                value = Double.parseDouble(value.toString());
            }else if (fieldType.equals("class java.lang.Integer")) {
                value = Integer.parseInt(value.toString());
            }else if (fieldType.equals("class java.lang.Boolean")) {
                value = Boolean.parseBoolean(value.toString());
            }else if (fieldType.equals("class java.lang.Long")) {
                value = Long.parseLong(value.toString());
            }else if (fieldType.equals("class java.lang.Float")) {
                value = Float.parseFloat(value.toString());
            }else if (fieldType.equals("class java.lang.Short")) {
                value = Short.parseShort(value.toString());
            }
        }
        return value;
    }
}
