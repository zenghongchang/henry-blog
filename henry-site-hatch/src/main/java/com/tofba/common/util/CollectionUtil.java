package com.tofba.common.util;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 集合相关工具类，包括数组
 * 
 * @author Henry(fba02)
 * @version [版本号, 2017年8月24日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class CollectionUtil {
    
    private CollectionUtil() {
    }
    
    /**
     * 多个集合的并集<br>
     * 针对一个集合中存在多个相同元素的情况，计算两个集合中此元素的个数，保留最多的个数<br>
     * 例如：集合1：[a, b, c, c, c]，集合2：[a, b, c, c]<br>
     * 结果：[a, b, c, c, c]，此结果中只保留了三个c
     * 
     * @param <T> 集合元素类型
     * @param coll1 集合1
     * @param coll2 集合2
     * @param otherColls 其它集合
     * @return 并集的集合，返回 {@link ArrayList}
     */
    @SafeVarargs
    public static <T> Collection<T> union(final Collection<T> coll1, final Collection<T> coll2, final Collection<T>... otherColls) {
        Collection<T> union = union(coll1, coll2);
        for (Collection<T> coll : otherColls) {
            union = union(union, coll);
        }
        return union;
    }
    
    /**
     * 多个集合的交集<br>
     * 针对一个集合中存在多个相同元素的情况，计算两个集合中此元素的个数，保留最少的个数<br>
     * 例如：集合1：[a, b, c, c, c]，集合2：[a, b, c, c]<br>
     * 结果：[a, b, c, c]，此结果中只保留了两个c
     * 
     * @param <T> 集合元素类型
     * @param coll1 集合1
     * @param coll2 集合2
     * @param otherColls 其它集合
     * @return 并集的集合，返回 {@link ArrayList}
     */
    @SafeVarargs
    public static <T> Collection<T> intersection(final Collection<T> coll1, final Collection<T> coll2, final Collection<T>... otherColls) {
        Collection<T> intersection = intersection(coll1, coll2);
        if (isEmpty(intersection)) {
            return intersection;
        }
        for (Collection<T> coll : otherColls) {
            intersection = intersection(intersection, coll);
            if (isEmpty(intersection)) {
                return intersection;
            }
        }
        return intersection;
    }
    
    /**
     * 其中一个集合在另一个集合中是否至少包含一个元素，既是两个集合是否至少有一个共同的元素
     * 
     * @param coll1 集合1
     * @param coll2 集合2
     * @return 其中一个集合在另一个集合中是否至少包含一个元素
     * @since 2.1
     * @see #intersection
     */
    public static boolean containsAny(final Collection<?> coll1, final Collection<?> coll2) {
        if (isEmpty(coll1) || isEmpty(coll2)) {
            return false;
        }
        if (coll1.size() < coll2.size()) {
            for (Object object : coll1) {
                if (coll2.contains(object)) {
                    return true;
                }
            }
        } else {
            for (Object object : coll2) {
                if (coll1.contains(object)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * 根据集合返回一个元素计数的 {@link Map}<br>
     * 所谓元素计数就是假如这个集合中某个元素出现了n次，那将这个元素做为key，n做为value<br>
     * 例如：[a,b,c,c,c] 得到：<br>
     * a: 1<br>
     * b: 1<br>
     * c: 3<br>
     * 
     * @param <T> 集合元素类型
     * @param collection 集合
     * @return {@link Map}
     */
    public static <T> Map<T, Integer> countMap(Collection<T> collection) {
        HashMap<T, Integer> countMap = new HashMap<>();
        Integer count;
        for (T t : collection) {
            count = countMap.get(t);
            if (null == count) {
                countMap.put(t, 1);
            } else {
                countMap.put(t, count + 1);
            }
        }
        return countMap;
    }
    
    /**
     * 以 conjunction 为分隔符将集合转换为字符串
     * 
     * @param <T> 集合元素类型
     * @param iterable {@link Iterable}
     * @param conjunction 分隔符
     * @return 连接后的字符串
     */
    public static <T> String join(Iterable<T> iterable, CharSequence conjunction) {
        if (null == iterable) {
            return null;
        }
        return join(iterable.iterator(), conjunction);
    }
    
    /**
     * 以 conjunction 为分隔符将集合转换为字符串
     * 
     * @param <T> 集合元素类型
     * @param iterator 集合
     * @param conjunction 分隔符
     * @return 连接后的字符串
     */
    public static <T> String join(Iterator<T> iterator, CharSequence conjunction) {
        if (null == iterator) {
            return null;
        }
        
        final StringBuilder sb = new StringBuilder();
        boolean isFirst = true;
        T item;
        while (iterator.hasNext()) {
            if (isFirst) {
                isFirst = false;
            } else {
                sb.append(conjunction);
            }
            
            item = iterator.next();
            if (ArrayUtil.isArray(item)) {
                sb.append(ArrayUtil.join(ArrayUtil.wrap(item), conjunction));
            } else if (item instanceof Iterable<?>) {
                sb.append(join((Iterable<?>)item, conjunction));
            } else if (item instanceof Iterator<?>) {
                sb.append(join((Iterator<?>)item, conjunction));
            } else {
                sb.append(item);
            }
        }
        return sb.toString();
    }
    
    /**
     * 将多个集合排序并显示不同的段落（分页）
     * 
     * @param <T> 集合元素类型
     * @param pageNo 页码，从1开始
     * @param numPerPage 每页的条目数
     * @param comparator 比较器
     * @param colls 集合数组
     * @return 分页后的段落内容
     */
    @SafeVarargs
    public static <T> List<T> sortPageAll(int pageNo, int numPerPage, Comparator<T> comparator, Collection<T>... colls) {
        final List<T> result = new ArrayList<>();
        for (Collection<T> coll : colls) {
            result.addAll(coll);
        }
        
        Collections.sort(result, comparator);
        
        int resultSize = result.size();
        // 每页条目数大于总数直接返回所有
        if (resultSize <= numPerPage) {
            return result;
        }
        final int[] startEnd = PageUtil.transToStartEnd(pageNo, numPerPage);
        if (startEnd[1] > resultSize) {
            // 越界直接返回空
            return new ArrayList<>();
        }
        
        return result.subList(startEnd[0], startEnd[1]);
    }
    
    /**
     * 将Set排序（根据Entry的值）
     * 
     * @param set 被排序的Set
     * @return 排序后的Set
     */
    public static List<Entry<Long, Long>> sortEntrySetToList(Set<Entry<Long, Long>> set) {
        List<Entry<Long, Long>> list = new LinkedList<>(set);
        Collections.sort(list, new Comparator<Entry<Long, Long>>() {
            @Override
            public int compare(Entry<Long, Long> o1, Entry<Long, Long> o2) {
                if (o1.getValue() > o2.getValue()) {
                    return 1;
                }
                if (o1.getValue() < o2.getValue()) {
                    return -1;
                }
                return 0;
            }
        });
        return list;
    }
    
    /**
     * 切取部分数据
     * 
     * @param <T> 集合元素类型
     * @param surplusAlaDatas 原数据
     * @param partSize 每部分数据的长度
     * @return 切取出的数据或null
     */
    public static <T> List<T> popPart(Stack<T> surplusAlaDatas, int partSize) {
        if (isEmpty(surplusAlaDatas)) {
            return null;
        }
        
        final List<T> currentAlaDatas = new ArrayList<>();
        int size = surplusAlaDatas.size();
        // 切割
        if (size > partSize) {
            for (int i = 0; i < partSize; i++) {
                currentAlaDatas.add(surplusAlaDatas.pop());
            }
        } else {
            for (int i = 0; i < size; i++) {
                currentAlaDatas.add(surplusAlaDatas.pop());
            }
        }
        return currentAlaDatas;
    }
    
    /**
     * 切取部分数据
     * 
     * @param <T> 集合元素类型
     * @param surplusAlaDatas 原数据
     * @param partSize 每部分数据的长度
     * @return 切取出的数据或null
     */
    public static <T> List<T> popPart(Deque<T> surplusAlaDatas, int partSize) {
        if (isEmpty(surplusAlaDatas)) {
            return null;
        }
        
        final List<T> currentAlaDatas = new ArrayList<>();
        int size = surplusAlaDatas.size();
        // 切割
        if (size > partSize) {
            for (int i = 0; i < partSize; i++) {
                currentAlaDatas.add(surplusAlaDatas.pop());
            }
        } else {
            for (int i = 0; i < size; i++) {
                currentAlaDatas.add(surplusAlaDatas.pop());
            }
        }
        return currentAlaDatas;
    }
    
    // ----------------------------------------------------------------------------------------------- new HashMap
    /**
     * 新建一个HashMap
     * 
     * @param <K> Key类型
     * @param <V> Value类型
     * @return HashMap对象
     */
    public static <K, V> HashMap<K, V> newHashMap() {
        return new HashMap<K, V>();
    }
    
    /**
     * 新建一个HashMap
     * 
     * @param <K> Key类型
     * @param <V> Value类型
     * @param size 初始大小，由于默认负载因子0.75，传入的size会实际初始大小为size / 0.75
     * @param isOrder Map的Key是否有序，有序返回 {@link LinkedHashMap}，否则返回 {@link HashMap}
     * @return HashMap对象
     * @since 3.0.4
     */
    public static <K, V> HashMap<K, V> newHashMap(int size, boolean isOrder) {
        int initialCapacity = (int)(size / 0.75);
        return isOrder ? new LinkedHashMap<K, V>(initialCapacity) : new HashMap<K, V>(initialCapacity);
    }
    
    /**
     * 新建一个HashMap
     * 
     * @param <K> Key类型
     * @param <V> Value类型
     * @param size 初始大小，由于默认负载因子0.75，传入的size会实际初始大小为size / 0.75
     * @return HashMap对象
     */
    public static <K, V> HashMap<K, V> newHashMap(int size) {
        return newHashMap(size, false);
    }
    
    // ----------------------------------------------------------------------------------------------- new HashSet
    /**
     * 新建一个HashSet
     * 
     * @param <T> 集合元素类型
     * @param ts 元素数组
     * @return HashSet对象
     */
    @SafeVarargs
    public static <T> HashSet<T> newHashSet(T... ts) {
        return newHashSet(false, ts);
    }
    
    /**
     * 新建一个HashSet
     * 
     * @param <T> 集合元素类型
     * @param isSorted 是否有序，有序返回 {@link LinkedHashSet}，否则返回 {@link HashSet}
     * @param ts 元素数组
     * @return HashSet对象
     */
    @SafeVarargs
    public static <T> HashSet<T> newHashSet(boolean isSorted, T... ts) {
        if (null == ts) {
            return isSorted ? new LinkedHashSet<T>() : new HashSet<T>();
        }
        int initialCapacity = Math.max((int)(ts.length / .75f) + 1, 16);
        HashSet<T> set = isSorted ? new LinkedHashSet<T>(initialCapacity) : new HashSet<T>(initialCapacity);
        for (T t : ts) {
            set.add(t);
        }
        return set;
    }
    
    /**
     * 新建一个HashSet
     * 
     * @param <T> 集合元素类型
     * @param collection 集合
     * @return HashSet对象
     */
    public static <T> HashSet<T> newHashSet(Collection<T> collection) {
        return newHashSet(false, collection);
    }
    
    /**
     * 新建一个HashSet
     * 
     * @param <T> 集合元素类型
     * @param isSorted 是否有序，有序返回 {@link LinkedHashSet}，否则返回{@link HashSet}
     * @param collection 集合，用于初始化Set
     * @return HashSet对象
     */
    public static <T> HashSet<T> newHashSet(boolean isSorted, Collection<T> collection) {
        return isSorted ? new LinkedHashSet<T>(collection) : new HashSet<T>(collection);
    }
    
    /**
     * 新建一个HashSet
     * 
     * @param <T> 集合元素类型
     * @param isSorted 是否有序，有序返回 {@link LinkedHashSet}，否则返回{@link HashSet}
     * @param iter {@link Iterator}
     * @return HashSet对象
     * @since 3.0.8
     */
    public static <T> HashSet<T> newHashSet(boolean isSorted, Iterator<T> iter) {
        if (null == iter) {
            return newHashSet(isSorted, (T[])null);
        }
        final HashSet<T> set = isSorted ? new LinkedHashSet<T>() : new HashSet<T>();
        while (iter.hasNext()) {
            set.add(iter.next());
        }
        return set;
    }
    
    /**
     * 新建一个HashSet
     * 
     * @param <T> 集合元素类型
     * @param isSorted 是否有序，有序返回 {@link LinkedHashSet}，否则返回{@link HashSet}
     * @param enumration {@link Enumeration}
     * @return HashSet对象
     * @since 3.0.8
     */
    public static <T> HashSet<T> newHashSet(boolean isSorted, Enumeration<T> enumration) {
        if (null == enumration) {
            return newHashSet(isSorted, (T[])null);
        }
        final HashSet<T> set = isSorted ? new LinkedHashSet<T>() : new HashSet<T>();
        while (enumration.hasMoreElements()) {
            set.add(enumration.nextElement());
        }
        return set;
    }
    
    // ----------------------------------------------------------------------------------------------- new ArrayList
    /**
     * 新建一个ArrayList
     * 
     * @param <T> 集合元素类型
     * @param values 数组
     * @return ArrayList对象
     */
    @SafeVarargs
    public static <T> ArrayList<T> newArrayList(T... values) {
        if (null == values) {
            return new ArrayList<>();
        }
        ArrayList<T> arrayList = new ArrayList<T>(values.length);
        for (T t : values) {
            arrayList.add(t);
        }
        return arrayList;
    }
    
    /**
     * 新建一个ArrayList
     * 
     * @param <T> 集合元素类型
     * @param collection 集合
     * @return ArrayList对象
     */
    public static <T> ArrayList<T> newArrayList(Collection<T> collection) {
        if (null == collection) {
            return new ArrayList<>();
        }
        return new ArrayList<T>(collection);
    }
    
    /**
     * 新建一个ArrayList
     * 
     * @param <T> 集合元素类型
     * @param iter {@link Iterator}
     * @return ArrayList对象
     * @since 3.0.8
     */
    public static <T> ArrayList<T> newArrayList(Iterator<T> iter) {
        final ArrayList<T> list = new ArrayList<>();
        if (null == iter) {
            return list;
        }
        while (iter.hasNext()) {
            list.add(iter.next());
        }
        return list;
    }
    
    /**
     * 新建一个ArrayList
     * 
     * @param <T> 集合元素类型
     * @param enumration {@link Enumeration}
     * @return ArrayList对象
     * @since 3.0.8
     */
    public static <T> ArrayList<T> newArrayList(Enumeration<T> enumration) {
        final ArrayList<T> list = new ArrayList<>();
        if (null == enumration) {
            return list;
        }
        while (enumration.hasMoreElements()) {
            list.add(enumration.nextElement());
        }
        return list;
    }
    
    /**
     * 新建一个CopyOnWriteArrayList
     * 
     * @param <T> 集合元素类型
     * @param collection 集合
     * @return {@link CopyOnWriteArrayList}
     */
    public static <T> CopyOnWriteArrayList<T> newCopyOnWriteArrayList(Collection<T> collection) {
        return (null == collection) ? (new CopyOnWriteArrayList<T>()) : (new CopyOnWriteArrayList<T>(collection));
    }
    
    /**
     * 去重集合
     * 
     * @param <T> 集合元素类型
     * @param collection 集合
     * @return {@link ArrayList}
     */
    public static <T> ArrayList<T> distinct(Collection<T> collection) {
        if (isEmpty(collection)) {
            return new ArrayList<>();
        } else if (collection instanceof Set) {
            return new ArrayList<>(collection);
        } else {
            return new ArrayList<>(new LinkedHashSet<>(collection));
        }
    }
    
    /**
     * 截取数组的部分
     * 
     * @param <T> 集合元素类型
     * @param list 被截取的数组
     * @param start 开始位置（包含）
     * @param end 结束位置（不包含）
     * @return 截取后的数组，当开始位置超过最大时，返回null
     */
    public static <T> List<T> sub(List<T> list, int start, int end) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        
        if (start < 0) {
            start = 0;
        }
        if (end < 0) {
            end = 0;
        }
        
        if (start > end) {
            int tmp = start;
            start = end;
            end = tmp;
        }
        
        final int size = list.size();
        if (end > size) {
            if (start >= size) {
                return null;
            }
            end = size;
        }
        
        return list.subList(start, end);
    }
    
    /**
     * 截取集合的部分
     * 
     * @param <T> 集合元素类型
     * @param list 被截取的数组
     * @param start 开始位置（包含）
     * @param end 结束位置（不包含）
     * @return 截取后的数组，当开始位置超过最大时，返回null
     */
    public static <T> List<T> sub(Collection<T> list, int start, int end) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        
        return sub(new ArrayList<T>(list), start, end);
    }
    
    /**
     * 对集合按照指定长度分段，每一个段为单独的集合，返回这个集合的列表
     * 
     * @param <T> 集合元素类型
     * @param collection 集合
     * @param size 每个段的长度
     * @return 分段列表
     */
    public static <T> List<List<T>> split(Collection<T> collection, int size) {
        final List<List<T>> result = new ArrayList<>();
        
        ArrayList<T> subList = new ArrayList<>(size);
        for (T t : collection) {
            if (subList.size() > size) {
                result.add(subList);
                subList = new ArrayList<>(size);
            }
            subList.add(t);
        }
        result.add(subList);
        return result;
    }
    
    // ---------------------------------------------------------------------- isEmpty
    /**
     * 集合是否为空
     * 
     * @param collection 集合
     * @return 是否为空
     */
    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }
    
    public static boolean isEmpty(Object object) {
        return object == null;
    }
    
    /**
     * Map是否为空
     * 
     * @param map 集合
     * @return 是否为空
     */
    public static boolean isEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }
    
    /**
     * Iterable是否为空
     * 
     * @param iterable Iterable对象
     * @return 是否为空
     */
    public static boolean isEmpty(Iterable<?> iterable) {
        return null == iterable || isEmpty(iterable.iterator());
    }
    
    /**
     * Iterator是否为空
     * 
     * @param Iterator Iterator对象
     * @return 是否为空
     */
    public static boolean isEmpty(Iterator<?> Iterator) {
        return null == Iterator || false == Iterator.hasNext();
    }
    
    /**
     * Enumeration是否为空
     * 
     * @param enumeration {@link Enumeration}
     * @return 是否为空
     */
    public static boolean isEmpty(Enumeration<?> enumeration) {
        return null == enumeration || false == enumeration.hasMoreElements();
    }
    
    // ---------------------------------------------------------------------- isNotEmpty
    
    /**
     * 集合是否为非空
     * 
     * @param collection 集合
     * @return 是否为非空
     */
    public static boolean isNotEmpty(Collection<?> collection) {
        return false == isEmpty(collection);
    }
    
    /**
     * 数组是否为非空
     * 
     * @param array 数组
     * @return 是否为非空
     */
    public static <T> boolean isNotEmpty(final T[] array) {
        return (array != null && array.length != 0);
    }
    
    /**
     * Map是否为非空
     * 
     * @param map 集合
     * @return 是否为非空
     */
    public static boolean isNotEmpty(Map<?, ?> map) {
        return false == isEmpty(map);
    }
    
    /**
     * Iterable是否为空
     * 
     * @param iterable Iterable对象
     * @return 是否为空
     */
    public static boolean isNotEmpty(Iterable<?> iterable) {
        return null != iterable && isNotEmpty(iterable.iterator());
    }
    
    /**
     * Iterator是否为空
     * 
     * @param Iterator Iterator对象
     * @return 是否为空
     */
    public static boolean isNotEmpty(Iterator<?> Iterator) {
        return null != Iterator && Iterator.hasNext();
    }
    
    /**
     * Enumeration是否为空
     * 
     * @param enumeration {@link Enumeration}
     * @return 是否为空
     */
    public static boolean isNotEmpty(Enumeration<?> enumeration) {
        return null != enumeration && enumeration.hasMoreElements();
    }
    
    /**
     * 是否包含{@code null}元素
     * 
     * @param iterable 被检查的Iterable对象
     * @return 是否包含{@code null}元素
     * @since 3.0.7
     */
    public static boolean hasNull(Iterable<?> iterable) {
        if (isNotEmpty(iterable)) {
            for (Object element : iterable) {
                if (null == element) {
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * 映射键值（参考Python的zip()函数）<br>
     * 例如：<br>
     * keys = [a,b,c,d]<br>
     * values = [1,2,3,4]<br>
     * 则得到的Map是 {a=1, b=2, c=3, d=4}<br>
     * 如果两个数组长度不同，则只对应最短部分
     * 
     * @param <K> 键类型
     * @param <V> 值类型
     * @param keys 键列表
     * @param values 值列表
     * @return Map
     */
    public static <K, V> Map<K, V> zip(Collection<K> keys, Collection<V> values) {
        if (isEmpty(keys) || isEmpty(values)) {
            return null;
        }
        
        final List<K> keyList = new ArrayList<K>(keys);
        final List<V> valueList = new ArrayList<V>(values);
        
        final int size = Math.min(keys.size(), values.size());
        final Map<K, V> map = new HashMap<K, V>((int)(size / 0.75));
        for (int i = 0; i < size; i++) {
            map.put(keyList.get(i), valueList.get(i));
        }
        
        return map;
    }
    
    /**
     * 将Entry集合转换为HashMap
     * 
     * @param <K> 键类型
     * @param <V> 值类型
     * @param entryCollection entry集合
     * @return Map
     */
    public static <K, V> HashMap<K, V> toMap(Collection<Entry<K, V>> entryCollection) {
        final HashMap<K, V> map = new HashMap<K, V>();
        if (isNotEmpty(entryCollection)) {
            for (Entry<K, V> entry : entryCollection) {
                map.put(entry.getKey(), entry.getValue());
            }
        }
        return map;
    }
    
    /**
     * 将数组转换为Map（HashMap），支持数组元素类型为：
     * 
     * <pre>
     * Map.Entry
     * 长度大于1的数组（取前两个值），如果不满足跳过此元素
     * Iterable 长度也必须大于1（取前两个值），如果不满足跳过此元素
     * Iterator 长度也必须大于1（取前两个值），如果不满足跳过此元素
     * </pre>
     * 
     * <pre>
     * Map&lt;Object, Object&gt; colorMap = CollectionUtil.toMap(new String[][] {{
     *     {"RED", "#FF0000"},
     *     {"GREEN", "#00FF00"},
     *     {"BLUE", "#0000FF"}});
     * </pre>
     * 
     * 参考：commons-lang
     * 
     * @param array 数组。元素类型为Map.Entry、数组、Iterable、Iterator
     * @return {@link HashMap}
     * @since 3.0.8
     */
    @SuppressWarnings("rawtypes")
    public static HashMap<Object, Object> toMap(Object[] array) {
        if (array == null) {
            return null;
        }
        final HashMap<Object, Object> map = new HashMap<>((int)(array.length * 1.5));
        for (int i = 0; i < array.length; i++) {
            Object object = array[i];
            if (object instanceof Map.Entry) {
                Map.Entry entry = (Map.Entry)object;
                map.put(entry.getKey(), entry.getValue());
            } else if (object instanceof Object[]) {
                final Object[] entry = (Object[])object;
                if (entry.length > 1) {
                    map.put(entry[0], entry[1]);
                }
            } else if (object instanceof Iterable) {
                Iterator iter = ((Iterable)object).iterator();
                if (iter.hasNext()) {
                    final Object key = iter.next();
                    if (iter.hasNext()) {
                        final Object value = iter.next();
                        map.put(key, value);
                    }
                }
            } else if (object instanceof Iterator) {
                Iterator iter = ((Iterator)object);
                if (iter.hasNext()) {
                    final Object key = iter.next();
                    if (iter.hasNext()) {
                        final Object value = iter.next();
                        map.put(key, value);
                    }
                }
            } else {
                // throw new IllegalArgumentException(StrUtil.format("Array element {}, '{}', is not type of Map.Entry or Array or Iterable or Iterator", i, object));
                new Exception();
            }
        }
        return map;
    }
    
    /**
     * 将集合转换为排序后的TreeSet
     * 
     * @param <T> 集合元素类型
     * @param collection 集合
     * @param comparator 比较器
     * @return treeSet
     */
    public static <T> TreeSet<T> toTreeSet(Collection<T> collection, Comparator<T> comparator) {
        final TreeSet<T> treeSet = new TreeSet<T>(comparator);
        for (T t : collection) {
            treeSet.add(t);
        }
        return treeSet;
    }
    
    /**
     * 排序集合
     * 
     * @param <T> 集合元素类型
     * @param collection 集合
     * @param comparator 比较器
     * @return treeSet
     */
    public static <T> List<T> sort(Collection<T> collection, Comparator<T> comparator) {
        List<T> list = new ArrayList<T>(collection);
        Collections.sort(list, comparator);
        return list;
    }
    
    /**
     * {@link Iterator} 转为 {@link Iterable}
     * 
     * @param <E> 元素类型
     * @param iter {@link Iterator}
     * @return {@link Iterable}
     */
    public static <E> Iterable<E> asIterable(final Iterator<E> iter) {
        return new Iterable<E>() {
            @Override
            public Iterator<E> iterator() {
                return iter;
            }
        };
    }
    
    /**
     * 将多个数组合并在一起<br>
     * 忽略null的数组
     * 
     * @param arrays 数组集合
     * @return 合并后的数组
     */
    @SafeVarargs
    public static <T> T[] addAll(T[]... arrays) {
        if (arrays.length == 1) {
            return arrays[0];
        }
        
        int length = 0;
        for (T[] array : arrays) {
            if (array == null) {
                continue;
            }
            length += array.length;
        }
        T[] result = newArray(arrays.getClass().getComponentType().getComponentType(), length);
        
        length = 0;
        for (T[] array : arrays) {
            if (array == null) {
                continue;
            }
            System.arraycopy(array, 0, result, length, array.length);
            length += array.length;
        }
        return result;
    }
    
    @SuppressWarnings("unchecked")
    public static <T> T[] newArray(Class<?> componentType, int newSize) {
        return (T[])Array.newInstance(componentType, newSize);
    }
    
    /**
     * 以 conjunction 为分隔符将集合转换为字符串
     * 
     * @param <T> 被处理的集合
     * @param collection 集合
     * @param conjunction 分隔符
     * @return 连接后的字符串
     */
    public static <T> String join(Iterable<T> collection, String conjunction) {
        StringBuilder sb = new StringBuilder();
        boolean isFirst = true;
        for (T item : collection) {
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
     * @param <T> 被处理的集合
     * @param array 数组
     * @param conjunction 分隔符
     * @return 连接后的字符串
     */
    public static <T> String join(T[] array, String conjunction) {
        StringBuilder sb = new StringBuilder();
        boolean isFirst = true;
        for (T item : array) {
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
     * 加入全部
     * 
     * @param <T> 集合元素类型
     * @param collection 被加入的集合 {@link Collection}
     * @param iterator 要加入的{@link Iterator}
     * @return 原集合
     */
    public static <T> Collection<T> addAll(Collection<T> collection, Iterator<T> iterator) {
        if (null != collection && null != iterator) {
            while (iterator.hasNext()) {
                collection.add(iterator.next());
            }
        }
        return collection;
    }
    
    /**
     * 加入全部
     * 
     * @param <T> 集合元素类型
     * @param collection 被加入的集合 {@link Collection}
     * @param iterable 要加入的内容{@link Iterable}
     * @return 原集合
     */
    public static <T> Collection<T> addAll(Collection<T> collection, Iterable<T> iterable) {
        return addAll(collection, iterable.iterator());
    }
    
    /**
     * 加入全部
     * 
     * @param <T> 集合元素类型
     * @param collection 被加入的集合 {@link Collection}
     * @param enumeration 要加入的内容{@link Enumeration}
     * @return 原集合
     */
    public static <T> Collection<T> addAll(Collection<T> collection, Enumeration<T> enumeration) {
        if (null != collection && null != enumeration) {
            while (enumeration.hasMoreElements()) {
                collection.add(enumeration.nextElement());
            }
        }
        return collection;
    }
    
    /**
     * 加入全部
     * 
     * @param <T> 集合元素类型
     * @param collection 被加入的集合 {@link Collection}
     * @param values 要加入的内容数组
     * @return 原集合
     * @since 3.0.8
     */
    public static <T> Collection<T> addAll(Collection<T> collection, T[] values) {
        if (null != collection && null != values) {
            for (T value : values) {
                collection.add(value);
            }
        }
        return collection;
    }
    
    /**
     * 将另一个列表中的元素加入到列表中，如果列表中已经存在此元素则忽略之
     * 
     * @param <T> 集合元素类型
     * @param list 列表
     * @param otherList 其它列表
     * @return 此列表
     */
    public static <T> List<T> addAllIfNotContains(List<T> list, List<T> otherList) {
        for (T t : otherList) {
            if (false == list.contains(t)) {
                list.add(t);
            }
        }
        return list;
    }
    
    /**
     * 获取集合的第一个元素
     * 
     * @param <T> 集合元素类型
     * @param iterable {@link Iterable}
     * @return 第一个元素
     * @since 3.0.1
     */
    public static <T> T getFirst(Iterable<T> iterable) {
        if (null != iterable) {
            return getFirst(iterable.iterator());
        }
        return null;
    }
    
    /**
     * 获取集合的第一个元素
     * 
     * @param <T> 集合元素类型
     * @param iterator {@link Iterator}
     * @return 第一个元素
     * @since 3.0.1
     */
    public static <T> T getFirst(Iterator<T> iterator) {
        if (null != iterator && iterator.hasNext()) {
            return iterator.next();
        }
        return null;
    }
    
    /**
     * 获得{@link Iterable}对象的元素类型（通过第一个非空元素判断）
     * 
     * @param iterable {@link Iterable}
     * @return 元素类型，当列表为空或元素全部为null时，返回null
     * @since 3.0.8
     */
    public static Class<?> getElementType(Iterable<?> iterable) {
        if (null != iterable) {
            Iterator<?> iterator = iterable.iterator();
            return getElementType(iterator);
        }
        return null;
    }
    
    /**
     * 获得{@link Iterator}对象的元素类型（通过第一个非空元素判断）
     * 
     * @param iterator {@link Iterator}
     * @return 元素类型，当列表为空或元素全部为null时，返回null
     * @since 3.0.8
     */
    public static Class<?> getElementType(Iterator<?> iterator) {
        if (null != iterator) {
            Object t;
            while (iterator.hasNext()) {
                t = iterator.next();
                if (null != t) {
                    return t.getClass();
                }
            }
        }
        return null;
    }
    
    /**
     * 从Map中获取指定键列表对应的值列表<br>
     * 如果key在map中不存在或key对应值为null，则返回值列表对应位置的值也为null
     * 
     * @param <K> 键类型
     * @param <V> 值类型
     * @param map {@link Map}
     * @param keys 键列表
     * @return 值列表
     * @since 3.0.8
     */
    @SuppressWarnings("unchecked")
    public static <K, V> ArrayList<V> valuesOfKeys(Map<K, V> map, K... keys) {
        final ArrayList<V> values = new ArrayList<>();
        for (K k : keys) {
            values.add(map.get(k));
        }
        return values;
    }
    
    // ------------------------------------------------------------------------------------------------- sort
    /**
     * 针对List排序，排序会修改原List
     * 
     * @param <T> 元素类型
     * @param list 被排序的List
     * @param c {@link Comparator}
     * @return 原list
     * @see Collections#sort(List, Comparator)
     */
    public <T> List<T> sort(List<T> list, Comparator<? super T> c) {
        Collections.sort(list, c);
        return list;
    }
    
    // ------------------------------------------------------------------------------------------------- forEach
    
    /**
     * 循环遍历 {@link Iterator}，使用{@link Consumer} 接受遍历的每条数据，并针对每条数据做处理
     * 
     * @param <T> 集合元素类型
     * @param iterator {@link Iterator}
     * @param consumer {@link Consumer} 遍历的每条数据处理器
     */
    public static <T> void forEach(Iterator<T> iterator, Consumer<T> consumer) {
        int index = 0;
        while (iterator.hasNext()) {
            consumer.accept(iterator.next(), index);
            index++;
        }
    }
    
    /**
     * 循环遍历 {@link Enumeration}，使用{@link Consumer} 接受遍历的每条数据，并针对每条数据做处理
     * 
     * @param <T> 集合元素类型
     * @param enumeration {@link Enumeration}
     * @param consumer {@link Consumer} 遍历的每条数据处理器
     */
    public static <T> void forEach(Enumeration<T> enumeration, Consumer<T> consumer) {
        int index = 0;
        while (enumeration.hasMoreElements()) {
            consumer.accept(enumeration.nextElement(), index);
            index++;
        }
    }
    
    /**
     * 循环遍历Map，使用{@link KVConsumer} 接受遍历的每条数据，并针对每条数据做处理
     * 
     * @param <K> Key类型
     * @param <V> Value类型
     * @param map {@link Map}
     * @param kvConsumer {@link KVConsumer} 遍历的每条数据处理器
     */
    public static <K, V> void forEach(Map<K, V> map, KVConsumer<K, V> kvConsumer) {
        int index = 0;
        for (Entry<K, V> entry : map.entrySet()) {
            kvConsumer.accept(entry.getKey(), entry.getValue(), index);
            index++;
        }
    }
    
    /**
     * 针对一个参数做相应的操作
     * 
     * @author Henry(fba02)
     *
     * @param <T> 处理参数类型
     */
    public static interface Consumer<T> {
        /**
         * 接受并处理一个参数
         * 
         * @param value 参数值
         * @param index 参数在集合中的索引
         */
        void accept(T value, int index);
    }
    
    /**
     * 针对两个参数做相应的操作，例如Map中的KEY和VALUE
     * 
     * @author Henry(fba02)
     *
     * @param <K> KEY类型
     * @param <V> VALUE类型
     */
    public static interface KVConsumer<K, V> {
        /**
         * 接受并处理一对参数
         * 
         * @param key 键
         * @param value 值
         * @param index 参数在集合中的索引
         */
        void accept(K key, V value, int index);
    }
    
    /**
     * 对象是否为数组对象
     * 
     * @param obj 对象
     * @return 是否为数组对象
     */
    public static boolean isArray(Object obj) {
        if (null == obj) {
            throw new NullPointerException("Object check for isArray is null");
        }
        return obj.getClass().isArray();
    }
    
    public static String toString(Object value) {
        return null;
    }
}
