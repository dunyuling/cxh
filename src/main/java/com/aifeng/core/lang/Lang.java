package com.aifeng.core.lang;

import java.io.Reader;
import java.io.StringReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * java语法糖类
 * @ClassName: Lang 
 * @Description: TODO
 * @author: cosco gt.fan@msn.cn
 * @date: 2016年12月18日 上午11:40:28
 */
public class Lang {

    /**
     * 较方便的创建一个数组，比如：
     * 
     * <pre>
     * String[] strs = Lang.array("A", "B", "A"); => ["A","B","A"]
     * </pre>
     * 
     * @param eles
     *            可变参数
     * @return 数组对象
     */
    public static <T> T[] array(T... eles) {
        return eles;
    }
    
    /**
     * 根据一段文本模拟出一个文本输入流对象
     * 
     * @param cs
     *            文本
     * @return 文本输出流对象
     */
    public static Reader inr(CharSequence cs) {
        return new StringReader(cs.toString());
    }
    
    /**
     * 较方便的创建一个列表，比如：
     * 
     * <pre>
     * List&lt;Pet&gt; pets = Lang.list(pet1, pet2, pet3);
     * </pre>
     * 
     * 注，这里的 List，是 ArrayList 的实例
     * 
     * @param eles
     *            可变参数
     * @return 列表对象
     */
    public static <T> ArrayList<T> list(T... eles) {
        ArrayList<T> list = new ArrayList<T>(eles.length);
        for (T ele : eles)
            list.add(ele);
        return list;
    }
    
    /**
     * 创建一个 Hash 集合
     * 
     * @param eles
     *            可变参数
     * @return 集合对象
     */
    public static <T> Set<T> set(T... eles) {
        Set<T> set = new HashSet<T>();
        for (T ele : eles)
            set.add(ele);
        return set;
    }
    
    /**
     * 将多个数组，合并成一个数组。如果这些数组为空，则返回 null
     * 
     * @param arys
     *            数组对象
     * @return 合并后的数组对象
     */
    @SuppressWarnings("unchecked")
    public static <T> T[] merge(T[]... arys) {
        Queue<T> list = new LinkedList<T>();
        for (T[] ary : arys)
            if (null != ary)
                for (T e : ary)
                    if (null != e)
                        list.add(e);
        if (list.isEmpty())
            return null;
        Class<T> type = (Class<T>) list.peek().getClass();
        return list.toArray((T[]) Array.newInstance(type, list.size()));
    }
    
    
	
}
