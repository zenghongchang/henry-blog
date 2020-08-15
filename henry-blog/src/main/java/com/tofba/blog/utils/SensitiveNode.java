package com.tofba.blog.utils;

import java.io.Serializable;
import java.util.TreeSet;

/**
 * 敏感词节点,每个节点包含了以相同的2个字符开头的所有词
 * @author  Henry(fba02)
 * @version  [版本号, 2020年7月24日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@SuppressWarnings("serial")
public class SensitiveNode implements Serializable {
    
    /**
     * 头两个字符的mix，mix相同，两个字符相同
     */
    protected final int headTwoCharMix;
    
    /**
     * 所有以这两个字符开头的词表
     */
    protected final TreeSet<StringPointer> words = new TreeSet<StringPointer>();
    
    /**
     * 下一个节点
     */
    protected SensitiveNode next;
    
    public SensitiveNode(int headTwoCharMix) {
        this.headTwoCharMix = headTwoCharMix;
    }
    
    public SensitiveNode(int headTwoCharMix, SensitiveNode parent) {
        this.headTwoCharMix = headTwoCharMix;
        parent.next = this;
    }
    
}
