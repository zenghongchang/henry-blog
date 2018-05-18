package com.tofba.common.util;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Iterator;

import com.tofba.common.exception.UtilException;

/**
 * 数组工具类
 * 
 * @author Henry(fba02)
 * @version [版本号, 2017年8月28日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class ArrayUtil {
    /**
     * 对象是否为数组对象
     * 
     * @param obj 对象
     * @return 是否为数组对象
     * @throws NullPointerException 提供被监测的对象为<code>null</code>
     */
    public static boolean isArray(Object obj) {
        if (null == obj) {
            throw new NullPointerException("Object check for isArray is null");
        }
        return obj.getClass().isArray();
    }
    
    /**
     * 数组或集合转String
     * 
     * @param obj 集合或数组对象
     * @return 数组字符串，与集合转字符串格式相同
     */
    public static String toString(Object obj) {
        if (null == obj) {
            return null;
        }
        if (ArrayUtil.isArray(obj)) {
            try {
                return Arrays.deepToString((Object[])obj);
            } catch (Exception e) {
                final String className = obj.getClass().getComponentType().getName();
                switch (className) {
                    case "long":
                        return Arrays.toString((long[])obj);
                    case "int":
                        return Arrays.toString((int[])obj);
                    case "short":
                        return Arrays.toString((short[])obj);
                    case "char":
                        return Arrays.toString((char[])obj);
                    case "byte":
                        return Arrays.toString((byte[])obj);
                    case "boolean":
                        return Arrays.toString((boolean[])obj);
                    case "float":
                        return Arrays.toString((float[])obj);
                    case "double":
                        return Arrays.toString((double[])obj);
                    default:
                        e.printStackTrace();
                }
            }
        }
        return obj.toString();
    }
    
    // ---------------------------------------------------------------------- isEmpty
    /**
     * 数组是否为空
     * 
     * @param <T> 数组元素类型
     * @param array 数组
     * @return 是否为空
     */
    @SuppressWarnings("unchecked")
    public static <T> boolean isEmpty(final T... array) {
        return array == null || array.length == 0;
    }
    
    /**
     * 数组是否为空<br>
     * 此方法会匹配单一对象，如果此对象为{@code null}则返回true<br>
     * 如果此对象为非数组，理解为此对象为数组的第一个元素，则返回false<br>
     * 如果此对象为数组对象，数组长度大于0情况下返回false，否则返回true
     * 
     * @param array 数组
     * @return 是否为空
     */
    public static boolean isEmpty(final Object array) {
        return array == null || (false == isArray(array)) || Array.getLength(array) > 0;
    }
    
    /**
     * 数组是否为空
     * 
     * @param array 数组
     * @return 是否为空
     */
    public static boolean isEmpty(final long... array) {
        return array == null || array.length == 0;
    }
    
    /**
     * 数组是否为空
     * 
     * @param array 数组
     * @return 是否为空
     */
    public static boolean isEmpty(final int... array) {
        return array == null || array.length == 0;
    }
    
    /**
     * 数组是否为空
     * 
     * @param array 数组
     * @return 是否为空
     */
    public static boolean isEmpty(final short... array) {
        return array == null || array.length == 0;
    }
    
    /**
     * 数组是否为空
     * 
     * @param array 数组
     * @return 是否为空
     */
    public static boolean isEmpty(final char... array) {
        return array == null || array.length == 0;
    }
    
    /**
     * 数组是否为空
     * 
     * @param array 数组
     * @return 是否为空
     */
    public static boolean isEmpty(final byte... array) {
        return array == null || array.length == 0;
    }
    
    /**
     * 数组是否为空
     * 
     * @param array 数组
     * @return 是否为空
     */
    public static boolean isEmpty(final double... array) {
        return array == null || array.length == 0;
    }
    
    /**
     * 数组是否为空
     * 
     * @param array 数组
     * @return 是否为空
     */
    public static boolean isEmpty(final float... array) {
        return array == null || array.length == 0;
    }
    
    /**
     * 数组是否为空
     * 
     * @param array 数组
     * @return 是否为空
     */
    public static boolean isEmpty(final boolean... array) {
        return array == null || array.length == 0;
    }
    
    
    /**
     * 以 conjunction 为分隔符将数组转换为字符串
     * 
     * @param <T> 被处理的集合
     * @param array 数组
     * @param conjunction 分隔符
     * @return 连接后的字符串
     */
    public static <T> String join(T[] array, CharSequence conjunction) {
        if (null == array) {
            return null;
        }
        
        final StringBuilder sb = new StringBuilder();
        boolean isFirst = true;
        for (T item : array) {
            if (isFirst) {
                isFirst = false;
            } else {
                sb.append(conjunction);
            }
            if (ArrayUtil.isArray(item)) {
                sb.append(join(ArrayUtil.wrap(item), conjunction));
            } else if (item instanceof Iterable<?>) {
                sb.append(CollectionUtil.join((Iterable<?>)item, conjunction));
            } else if (item instanceof Iterator<?>) {
                sb.append(CollectionUtil.join((Iterator<?>)item, conjunction));
            } else {
                sb.append(item);
            }
        }
        return sb.toString();
    }
    
    /**
     * 以 conjunction 为分隔符将数组转换为字符串
     * 
     * @param array 数组
     * @param conjunction 分隔符
     * @return 连接后的字符串
     */
    public static String join(long[] array, String conjunction) {
        if (null == array) {
            return null;
        }
        
        final StringBuilder sb = new StringBuilder();
        boolean isFirst = true;
        for (long item : array) {
            if (isFirst) {
                isFirst = false;
            } else {
                sb.append(conjunction);
            }
            sb.append(item);
        }
        return sb.toString();
    }
    
    /**
     * 以 conjunction 为分隔符将数组转换为字符串
     * 
     * @param array 数组
     * @param conjunction 分隔符
     * @return 连接后的字符串
     */
    public static String join(int[] array, String conjunction) {
        if (null == array) {
            return null;
        }
        
        final StringBuilder sb = new StringBuilder();
        boolean isFirst = true;
        for (int item : array) {
            if (isFirst) {
                isFirst = false;
            } else {
                sb.append(conjunction);
            }
            sb.append(item);
        }
        return sb.toString();
    }
    
    /**
     * 以 conjunction 为分隔符将数组转换为字符串
     * 
     * @param array 数组
     * @param conjunction 分隔符
     * @return 连接后的字符串
     */
    public static String join(short[] array, String conjunction) {
        if (null == array) {
            return null;
        }
        
        final StringBuilder sb = new StringBuilder();
        boolean isFirst = true;
        for (short item : array) {
            if (isFirst) {
                isFirst = false;
            } else {
                sb.append(conjunction);
            }
            sb.append(item);
        }
        return sb.toString();
    }
    
    /**
     * 以 conjunction 为分隔符将数组转换为字符串
     * 
     * @param array 数组
     * @param conjunction 分隔符
     * @return 连接后的字符串
     */
    public static String join(char[] array, String conjunction) {
        if (null == array) {
            return null;
        }
        
        final StringBuilder sb = new StringBuilder();
        boolean isFirst = true;
        for (char item : array) {
            if (isFirst) {
                isFirst = false;
            } else {
                sb.append(conjunction);
            }
            sb.append(item);
        }
        return sb.toString();
    }
    
    /**
     * 以 conjunction 为分隔符将数组转换为字符串
     * 
     * @param array 数组
     * @param conjunction 分隔符
     * @return 连接后的字符串
     */
    public static String join(byte[] array, String conjunction) {
        if (null == array) {
            return null;
        }
        
        final StringBuilder sb = new StringBuilder();
        boolean isFirst = true;
        for (byte item : array) {
            if (isFirst) {
                isFirst = false;
            } else {
                sb.append(conjunction);
            }
            sb.append(item);
        }
        return sb.toString();
    }
    
    /**
     * 以 conjunction 为分隔符将数组转换为字符串
     * 
     * @param array 数组
     * @param conjunction 分隔符
     * @return 连接后的字符串
     */
    public static String join(boolean[] array, String conjunction) {
        if (null == array) {
            return null;
        }
        
        final StringBuilder sb = new StringBuilder();
        boolean isFirst = true;
        for (boolean item : array) {
            if (isFirst) {
                isFirst = false;
            } else {
                sb.append(conjunction);
            }
            sb.append(item);
        }
        return sb.toString();
    }
    
    /**
     * 以 conjunction 为分隔符将数组转换为字符串
     * 
     * @param array 数组
     * @param conjunction 分隔符
     * @return 连接后的字符串
     */
    public static String join(float[] array, String conjunction) {
        if (null == array) {
            return null;
        }
        
        final StringBuilder sb = new StringBuilder();
        boolean isFirst = true;
        for (float item : array) {
            if (isFirst) {
                isFirst = false;
            } else {
                sb.append(conjunction);
            }
            sb.append(item);
        }
        return sb.toString();
    }
    
    /**
     * 以 conjunction 为分隔符将数组转换为字符串
     * 
     * @param array 数组
     * @param conjunction 分隔符
     * @return 连接后的字符串
     */
    public static String join(double[] array, String conjunction) {
        if (null == array) {
            return null;
        }
        
        final StringBuilder sb = new StringBuilder();
        boolean isFirst = true;
        for (double item : array) {
            if (isFirst) {
                isFirst = false;
            } else {
                sb.append(conjunction);
            }
            sb.append(item);
        }
        return sb.toString();
    }
    
    /**
     * 以 conjunction 为分隔符将数组转换为字符串
     * 
     * @param array 数组
     * @param conjunction 分隔符
     * @return 连接后的字符串
     */
    public static String join(Object array, CharSequence conjunction) {
        if (isArray(array)) {
            final Class<?> componentType = array.getClass().getComponentType();
            if (componentType.isPrimitive()) {
                final String componentTypeName = componentType.getName();
                switch (componentTypeName) {
                    case "long":
                        return join((long[])array, conjunction);
                    case "int":
                        return join((int[])array, conjunction);
                    case "short":
                        return join((short[])array, conjunction);
                    case "char":
                        return join((char[])array, conjunction);
                    case "byte":
                        return join((byte[])array, conjunction);
                    case "boolean":
                        return join((boolean[])array, conjunction);
                    case "float":
                        return join((float[])array, conjunction);
                    case "double":
                        return join((double[])array, conjunction);
                    default:
                        throw new UtilException("Unknown primitive type: [{}]", componentTypeName);
                }
            } else {
                return join((Object[])array, conjunction);
            }
        }
        throw new UtilException(StrUtil.format("[{}] is not a Array!", array.getClass()));
    }
    
    /**
     * 将原始类型数组包装为包装类型
     * 
     * @param values 原始类型数组
     * @return 包装类型数组
     */
    public static Integer[] wrap(int... values) {
        final int length = values.length;
        Integer[] array = new Integer[length];
        for (int i = 0; i < length; i++) {
            array[i] = Integer.valueOf(values[i]);
        }
        return array;
    }
    
    /**
     * 包装类数组转为原始类型数组
     * 
     * @param values 包装类型数组
     * @return 原始类型数组
     */
    public static int[] unWrap(Integer... values) {
        final int length = values.length;
        int[] array = new int[length];
        for (int i = 0; i < length; i++) {
            array[i] = values[i].intValue();
        }
        return array;
    }
    
    /**
     * 将原始类型数组包装为包装类型
     * 
     * @param values 原始类型数组
     * @return 包装类型数组
     */
    public static Long[] wrap(long... values) {
        final int length = values.length;
        Long[] array = new Long[length];
        for (int i = 0; i < length; i++) {
            array[i] = Long.valueOf(values[i]);
        }
        return array;
    }
    
    /**
     * 包装类数组转为原始类型数组
     * 
     * @param values 包装类型数组
     * @return 原始类型数组
     */
    public static long[] unWrap(Long... values) {
        final int length = values.length;
        long[] array = new long[length];
        for (int i = 0; i < length; i++) {
            array[i] = values[i].longValue();
        }
        return array;
    }
    
    /**
     * 将原始类型数组包装为包装类型
     * 
     * @param values 原始类型数组
     * @return 包装类型数组
     */
    public static Character[] wrap(char... values) {
        final int length = values.length;
        Character[] array = new Character[length];
        for (int i = 0; i < length; i++) {
            array[i] = Character.valueOf(values[i]);
        }
        return array;
    }
    
    /**
     * 包装类数组转为原始类型数组
     * 
     * @param values 包装类型数组
     * @return 原始类型数组
     */
    public static char[] unWrap(Character... values) {
        final int length = values.length;
        char[] array = new char[length];
        for (int i = 0; i < length; i++) {
            array[i] = values[i].charValue();
        }
        return array;
    }
    
    /**
     * 将原始类型数组包装为包装类型
     * 
     * @param values 原始类型数组
     * @return 包装类型数组
     */
    public static Byte[] wrap(byte... values) {
        final int length = values.length;
        Byte[] array = new Byte[length];
        for (int i = 0; i < length; i++) {
            array[i] = Byte.valueOf(values[i]);
        }
        return array;
    }
    
    /**
     * 包装类数组转为原始类型数组
     * 
     * @param values 包装类型数组
     * @return 原始类型数组
     */
    public static byte[] unWrap(Byte... values) {
        final int length = values.length;
        byte[] array = new byte[length];
        for (int i = 0; i < length; i++) {
            array[i] = values[i].byteValue();
        }
        return array;
    }
    
    /**
     * 将原始类型数组包装为包装类型
     * 
     * @param values 原始类型数组
     * @return 包装类型数组
     */
    public static Short[] wrap(short... values) {
        final int length = values.length;
        Short[] array = new Short[length];
        for (int i = 0; i < length; i++) {
            array[i] = Short.valueOf(values[i]);
        }
        return array;
    }
    
    /**
     * 包装类数组转为原始类型数组
     * 
     * @param values 包装类型数组
     * @return 原始类型数组
     */
    public static short[] unWrap(Short... values) {
        final int length = values.length;
        short[] array = new short[length];
        for (int i = 0; i < length; i++) {
            array[i] = values[i].shortValue();
        }
        return array;
    }
    
    /**
     * 将原始类型数组包装为包装类型
     * 
     * @param values 原始类型数组
     * @return 包装类型数组
     */
    public static Float[] wrap(float... values) {
        final int length = values.length;
        Float[] array = new Float[length];
        for (int i = 0; i < length; i++) {
            array[i] = Float.valueOf(values[i]);
        }
        return array;
    }
    
    /**
     * 包装类数组转为原始类型数组
     * 
     * @param values 包装类型数组
     * @return 原始类型数组
     */
    public static float[] unWrap(Float... values) {
        final int length = values.length;
        float[] array = new float[length];
        for (int i = 0; i < length; i++) {
            array[i] = values[i].floatValue();
        }
        return array;
    }
    
    /**
     * 将原始类型数组包装为包装类型
     * 
     * @param values 原始类型数组
     * @return 包装类型数组
     */
    public static Double[] wrap(double... values) {
        final int length = values.length;
        Double[] array = new Double[length];
        for (int i = 0; i < length; i++) {
            array[i] = Double.valueOf(values[i]);
        }
        return array;
    }
    
    /**
     * 包装类数组转为原始类型数组
     * 
     * @param values 包装类型数组
     * @return 原始类型数组
     */
    public static double[] unWrap(Double... values) {
        final int length = values.length;
        double[] array = new double[length];
        for (int i = 0; i < length; i++) {
            array[i] = values[i].doubleValue();
        }
        return array;
    }
    
    /**
     * 将原始类型数组包装为包装类型
     * 
     * @param values 原始类型数组
     * @return 包装类型数组
     */
    public static Boolean[] wrap(boolean... values) {
        final int length = values.length;
        Boolean[] array = new Boolean[length];
        for (int i = 0; i < length; i++) {
            array[i] = Boolean.valueOf(values[i]);
        }
        return array;
    }
    
    /**
     * 包装类数组转为原始类型数组
     * 
     * @param values 包装类型数组
     * @return 原始类型数组
     */
    public static boolean[] unWrap(Boolean... values) {
        final int length = values.length;
        boolean[] array = new boolean[length];
        for (int i = 0; i < length; i++) {
            array[i] = values[i].booleanValue();
        }
        return array;
    }
    
    /**
     * 包装数组对象
     * 
     * @param obj 对象，可以是对象数组或者基本类型数组
     * @return 包装类型数组或对象数组
     * @throws UtilException 对象为非数组
     */
    public static Object[] wrap(Object obj) {
        if (isArray(obj)) {
            try {
                return (Object[])obj;
            } catch (Exception e) {
                final String className = obj.getClass().getComponentType().getName();
                switch (className) {
                    case "long":
                        return wrap((long[])obj);
                    case "int":
                        return wrap((int[])obj);
                    case "short":
                        return wrap((short[])obj);
                    case "char":
                        return wrap((char[])obj);
                    case "byte":
                        return wrap((byte[])obj);
                    case "boolean":
                        return wrap((boolean[])obj);
                    case "float":
                        return wrap((float[])obj);
                    case "double":
                        return wrap((double[])obj);
                    default:
                        throw new UtilException(e);
                }
            }
        }
        throw new UtilException(StrUtil.format("[{}] is not Array!", obj.getClass()));
    }
}