package com.aifeng.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.IteratorUtils;

/**
 * 
 * @ClassName: DebugUtil
 * @Description: TODO
 * @author: cosco gt.fan@msn.cn
 * @date: 2016年8月19日 下午11:49:26
 */
public class DebugUtil {

	public static final boolean DEBUG = true;

	/**
	 * 输出对象值
	 * 
	 * @Title: printObjVal
	 * @Description: TODO
	 * @param obj
	 * @return: void
	 */
	public static void printObjVal(Object obj) {

		StringBuffer sb = new StringBuffer();
		Method[] ms = obj.getClass().getDeclaredMethods();
		try {
			sb.append(obj.getClass().getSimpleName());
			for (Method m : ms) {
				String mn = m.getName();
				if (mn.startsWith("get")) {

					sb.append("[" + mn.substring(3, mn.length()) + ": " + m.invoke(obj, null) + "]");

				}
			}

		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(sb.toString());
	}

	/**
	 * 输出到控制台
	 * 
	 * @param arg
	 */
	public static void print(Object arg) {
		if (DEBUG) {
			Iterator iter = null;
			if (arg.getClass().isArray())
				arg = Arrays.asList((Object[]) arg);
			if (arg instanceof Collection)
				iter = ((Collection) arg).iterator();
			if (arg instanceof Iterator)
				iter = (Iterator) arg;
			if (arg instanceof Map)
				iter = ((Map) arg).entrySet().iterator();
			if (arg instanceof Enumeration)
				iter = IteratorUtils.asIterator((Enumeration) arg);

			if (iter == null) {
				/** 输出字符串 */
				if (arg != null)
					System.out.println(arg.toString());
			} else {
				/** 输出Map值 */
				if (arg instanceof Map) {
					HashMap hmArg = (HashMap) arg;
					Set set = hmArg.keySet();
					Iterator iterSet = set.iterator();
					while (iterSet.hasNext()) {
						String strKeyName = (String) iterSet.next();
						String strKeyValue = (String) hmArg.get(strKeyName);
						System.out.println(strKeyName + "=>" + strKeyValue);
					}
				} else {
					/** 输出枚举值 */
					boolean noFirst = false;
					while (iter.hasNext()) {
						if (noFirst) {
							System.out.println(",");
						}
						System.out.println(StringUtil.converNull(iter.next()));

						noFirst = true;
					}
				}
			}
		}
	}

}
