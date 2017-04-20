package com.aifeng.util;

import opensource.jpinyin.PinyinHelper;

/**
 * @author:cosco
 * @description : 对输入字符进行处理
 */
public class PinYinUtil extends PinyinHelper {
	

	/**
	 * 检测String是否全是中文
	 * 
	 */
	public static boolean checkNameChese(String name) {
		boolean res = true;
		char[] cTemp = name.toCharArray();

		for (int i = 0; i < name.length(); i++) {
			if (!isChinese(cTemp[i])) {
				res = false;
				break;
			}
		}

		return res;
	}

	/**
	 * 判定输入汉字是否是中文
	 * 
	 * @param c
	 */
	public static boolean isChinese(char c) {
		for (char param : chineseParam) {
			if (param == c) {
				return false;
			}
		}

		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
				|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
			return true;
		}

		return false;
	}

	public static final int INPUT_LIMIT_LEN = 20;
	private static char[] chineseParam = new char[] { '」', '，', '。', '？', '…', '：', '～', '【', '＃', '、', '％', '＊', '＆', '＄', '（', '‘', '’',
			'“', '”', '『', '〔', '｛', '【', '￥', '￡', '‖', '〖', '《', '「', '》', '〗', '】', '｝', '〕', '』', '”', '）', '！', '；', '—' };
}
