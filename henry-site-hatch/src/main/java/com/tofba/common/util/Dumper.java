package com.tofba.common.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Dumper {
    private static Boolean DUMPER_DISABLED;
    
    private static String DUMPER_DISABLED_KEY = "dumper";
    
    static public void dump(Object object) {
        if (null == DUMPER_DISABLED) {
            DUMPER_DISABLED = null != System.getenv(DUMPER_DISABLED_KEY) && System.getenv(DUMPER_DISABLED_KEY).equals("0");
        }
        if (DUMPER_DISABLED) {
            return;
        }
        try {
            StackTraceElement traces[] = Thread.currentThread().getStackTrace();
            System.out.println(traces[2]);
            Gson gson = new GsonBuilder().serializeSpecialFloatingPointValues().serializeNulls().setPrettyPrinting().create();
            System.out.println(gson.toJson(object));
        } catch (Exception e) {
            System.out.println(ToStringBuilder.reflectionToString(object, ToStringStyle.MULTI_LINE_STYLE));
        }
    }
    
    public static String getStackTrace(Throwable aThrowable) {
        final Writer result = new StringWriter();
        final PrintWriter printWriter = new PrintWriter(result);
        aThrowable.printStackTrace(printWriter);
        return result.toString();
    }
}

@SuppressWarnings("serial")
class RecursiveToStringStyle extends ToStringStyle {
    private static final int INFINITE_DEPTH = -1;
    
    /**
     * Setting {@link #maxDepth} to 0 will have the same effect as using original {@link #ToStringStyle}: it will print all 1st level values without traversing into them. Setting to 1 will traverse up
     * to 2nd level and so on.
     */
    private int maxDepth;
    
    private int depth;
    
    public RecursiveToStringStyle() {
        this(INFINITE_DEPTH);
        setUseShortClassName(true);
        setUseIdentityHashCode(false);
        setFieldSeparator("\n--------------------------------------------------------------------\n");
        setArraySeparator(" ");
        setFieldSeparatorAtEnd(true);
        setFieldSeparatorAtStart(true);
    }
    
    public RecursiveToStringStyle(int maxDepth) {
        setUseShortClassName(true);
        setUseIdentityHashCode(false);
        setFieldSeparator("\n--------------------------------------------------------------------\n");
        setArraySeparator(" ");
        setFieldSeparatorAtEnd(true);
        setFieldSeparatorAtStart(true);
        this.maxDepth = maxDepth;
    }
    
    @Override
    protected void appendDetail(StringBuffer buffer, String fieldName, Object value) {
        if (value.getClass().getName().startsWith("java.lang.") || (maxDepth != INFINITE_DEPTH && depth >= maxDepth)) {
            buffer.append(value);
        } else {
            depth++;
            buffer.append(ReflectionToStringBuilder.toString(value, this));
            depth--;
        }
    }
}