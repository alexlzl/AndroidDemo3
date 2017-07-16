package com.weeho.petim.lib.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Describe:wangkui
 * <p/>
 * Date:2015-6-25
 * <p/>
 * Author:wangkui
 */

public class StringUtil {
	/**
	 * 7位ASCII字符，也叫作ISO646-US、Unicode字符集的基本拉丁块
	 */
	public static final String US_ASCII = "US-ASCII";

	/**
	 * ISO 拉丁字母表 No.1，也叫作 ISO-LATIN-1
	 */
	public static final String ISO_8859_1 = "ISO-8859-1";

	/**
	 * 8 位 UCS 转换格式
	 */
	public static final String UTF_8 = "UTF-8";

	/**
	 * 16 位 UCS 转换格式，Big Endian（最低地址存放高位字节）字节顺序
	 */
	public static final String UTF_16BE = "UTF-16BE";

	/**
	 * 16 位 UCS 转换格式，Little-endian（最高地址存放低位字节）字节顺序
	 */
	public static final String UTF_16LE = "UTF-16LE";

	/**
	 * 16 位 UCS 转换格式，字节顺序由可选的字节顺序标记来标识
	 */
	public static final String UTF_16 = "UTF-16";

	/**
	 * 中文超大字符集
	 */
	public static final String GBK = "GBK";

	private static final char QUOTE_ENCODE[] = "&quot;".toCharArray();
	private static final char AMP_ENCODE[] = "&amp;".toCharArray();
	private static final char LT_ENCODE[] = "&lt;".toCharArray();
	private static final char GT_ENCODE[] = "&gt;".toCharArray();
	private static char numbersAndLetters[] = "0123456789abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ"
			.toCharArray();

	static String BASE64_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
	static char BASE64_PAD = '=';

	private static Random rdm = new Random();

	private StringUtil() {
		super();
	}

	public static boolean hasValue(String s) {
		return s != null && s.length() > 0;
	}

	public static boolean containsSpace(String s) {
		return s != null && s.indexOf(' ') != -1;
	}

	public static String replace(String string, String oldString,
			String newString) {
		if (string == null) {
			return null;
		}
		if (newString == null) {
			return string;
		}
		int i = 0;
		if ((i = string.indexOf(oldString, i)) >= 0) {
			char string2[] = string.toCharArray();
			char newString2[] = newString.toCharArray();
			int oLength = oldString.length();
			StringBuffer buf = new StringBuffer(string2.length);
			buf.append(string2, 0, i).append(newString2);
			i += oLength;
			int j;
			for (j = i; (i = string.indexOf(oldString, i)) > 0; j = i) {
				buf.append(string2, j, i - j).append(newString2);
				i += oLength;
			}

			buf.append(string2, j, string2.length - j);
			return buf.toString();
		} else {
			return string;
		}
	}

	public static final String escapeStringForJava(String s) {
		StringTokenizer st = new StringTokenizer(s, "\"", true);
		StringBuilder sb = new StringBuilder();
		while (st.hasMoreTokens()) {
			String token = st.nextToken();
			if ("\"".equals(token)) {
				sb.append("\\\"");
			} else {
				sb.append(token);
			}
		}
		return sb.toString();
	}

	public static final String escapeStringForXml(String string) {
		if (string == null) {
			return null;
		}
		int i = 0;
		int last = 0;
		char input[] = string.toCharArray();
		int len = input.length;
		StringBuffer out = new StringBuffer((int) ((double) len * 1.3D));
		for (; i < len; i++) {
			char ch = input[i];
			if (ch > '>') {
				continue;
			}
			if (ch == '<') {
				if (i > last) {
					out.append(input, last, i - last);
				}
				last = i + 1;
				out.append(LT_ENCODE);
				continue;
			}
			if (ch == '>') {
				if (i > last) {
					out.append(input, last, i - last);
				}
				last = i + 1;
				out.append(GT_ENCODE);
				continue;
			}
			if (ch == '&') {
				if (i > last) {
					out.append(input, last, i - last);
				}
				last = i + 1;
				out.append(AMP_ENCODE);
				continue;
			}
			if (ch == '"') {
				if (i > last) {
					out.append(input, last, i - last);
				}
				last = i + 1;
				out.append(QUOTE_ENCODE);
				continue;
			}
			if (ch == '\n' || ch == '\r' || ch == '\t' || ch >= ' ') {
				continue;
			}
			if (i > last) {
				out.append(input, last, i - last);
			}
			last = i + 1;
		}

		if (last == 0) {
			return string;
		}
		if (i > last) {
			out.append(input, last, i - last);
		}
		return out.toString();
	}

	public static String removeHtml(String str) {
		return replaceHtml(str).replace("&lt;", "").replace("&gt;", "")
				.replace("&quot;", "").replace("&amp;", "")
				.replace("&nbsp;", "");
	}

	public static String replaceHtml(String html) {
		String regEx = "<.+?>"; // 表示标签
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(html);
		String s = m.replaceAll("");
		return s;
	}

	public static final String unescapeStringFromXML(String string) {
		string = replace(string, "&lt;", "<");
		string = replace(string, "&gt;", ">");
		string = replace(string, "&quot;", "\"");
		return replace(string, "&amp;", "&");
	}

	public static final String escapeStringForHtml(String s) {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			switch (ch) {
			case '<':
				buffer.append("&lt;");
				break;
			case '>':
				buffer.append("&gt;");
				break;
			case '&':
				buffer.append("&amp;");
				break;
			case '"':
				buffer.append("&quot;");
				break;
			case '\'':
				buffer.append("&#39;");
				break;
			default:
				buffer.append(ch);
			}
		}
		return buffer.toString();
	}

	public static final String getStripString(String source, int len) {
		String ss = stripTags(source, true);
		if (ss.length() < len) {
			return ss;
		}
		return ss.substring(0, len);
	}

	public static final String stripTags(String in, boolean stripBRTag) {
		if (in == null) {
			return null;
		}
		int i = 0;
		int last = 0;
		char input[] = in.toCharArray();
		int len = input.length;
		StringBuffer out = new StringBuffer((int) ((double) len * 1.3D));
		for (; i < len; i++) {
			char ch = input[i];
			if (ch > '>') {
				continue;
			}
			if (ch == '<') {
				if (!stripBRTag && i + 3 < len && input[i + 1] == 'b'
						&& input[i + 2] == 'r' && input[i + 3] == '>') {
					i += 3;
					continue;
				}
				if (i > last) {
					if (last > 0) {
						out.append(" ");
					}
					out.append(input, last, i - last);
				}
				last = i + 1;
				continue;
			}
			if (ch == '>') {
				last = i + 1;
			}
		}

		if (last == 0) {
			return in;
		}
		if (i > last) {
			out.append(input, last, i - last);
		}
		return out.toString();
	}

	/* ------------------------------------------------- */

	public static boolean isTrue(String s) {
		return "true".equalsIgnoreCase(s);
	}

	public static boolean isEmpty(String str) {
		if (null == str) {
			return true;
		}

		if ("".equals(str.trim())) {
			return true;
		}
		return false;
	}

	public static boolean isEmpty(String[] ss) {
		if (null == ss) {
			return true;
		} else if (0 == ss.length) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 
	 * Describe:判断字符串是否是数字
	 * 
	 * Date:2015-9-29
	 * 
	 * Author:wangkui
	 */
	public static boolean isNumber(String str) {
		if (!hasValue(str)) {
			return false;
		}
		str = str.trim();
		int nLength = str.length();
		int index = 0;
		if (str.charAt(0) == '-') {
			index = 1;
		}
		while (index < nLength) {
			if (!Character.isDigit(str.charAt(index))) {
				return false;
			}
			index++;
		}
		return true;
	}

	/**
	 * @describe:匹配是否是邮编格式
	 * @author:wangkui
	 * @time:2014-4-1下午2:52:10
	 */
	public static boolean isZipValid(String zip) {
		boolean retval = false;
		String zipCodePattern = "/d{5}(-/d{4})?";
		retval = zip.matches(zipCodePattern);

		return retval;
	}

	/*
	 * public static boolean isNumber(String str) { return
	 * str.matches("[0-9]+"); }
	 */

	public static boolean isEmail(String email) {
		if (null == email) {
			return false;
		}
		String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
		Pattern p = Pattern.compile(str);
		Matcher m = p.matcher(email);
		return m.matches();
	}

	/**
	 * 判断手机号格式是否正确(只验证11位1开头就表示为手机号)
	 * 
	 * @param phone
	 * @return
	 * @author Skyin_wd
	 */
	public static boolean isMobileNo(String phone) {
		try {
			if (!StringUtil.isEmpty(phone)) {
				String phoneNumberText = phone.trim();
				String firstNumberText = phoneNumberText.substring(0, 1);
				// LogUtil.d("StringUtil isMobileNo phoneNumberText = " +
				// phoneNumberText + " firstNumberText = " + firstNumberText
				// + " length = " + phoneNumberText.length());
				if (phoneNumberText.length() == 11
						&& "1".equals(firstNumberText)) {
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
		/*
		 * return Pattern
		 * .compile("^((13[0-9])|(15[^4,\\D])|(18[0,3,5-9]))\\d{8}$")
		 * .matcher(phone).matches();
		 */
	}
	/*
	 * 
	 * 是否是座机号
	 * */
	public static boolean isLandline(String phone) {
		try {
			if (!StringUtil.isEmpty(phone)) {
				String phoneNumberText = phone.trim();
				String firstNumberText = phoneNumberText.substring(0, 1);
				// LogUtil.d("StringUtil isMobileNo phoneNumberText = " +
				// phoneNumberText + " firstNumberText = " + firstNumberText
				// + " length = " + phoneNumberText.length());
				if (phoneNumberText.length() > 6
						&& "0".equals(firstNumberText)) {
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
		/*
		 * return Pattern
		 * .compile("^((13[0-9])|(15[^4,\\D])|(18[0,3,5-9]))\\d{8}$")
		 * .matcher(phone).matches();
		 */
	}

	public static boolean isContainsNumber3(String str) {
		// 定义一个boolean值，用来表示是否包含数字
		boolean isDigit = false;
		// 定义一个boolean值，用来表示是否包含字母
		boolean isLetter = false;
		// 循环遍历字符串
		for (int i = 0; i < str.length(); i++) {
			if (Character.isDigit(str.charAt(i))) {
				// 用char包装类中的判断数字的方法判断每一个字符
				isDigit = true;
			}
			if (Character.isLetter(str.charAt(i))) {
				// 用char包装类中的判断字母的方法判断每一个字符
				isLetter = true;
			}
		}
		int count = 0;
		if (isDigit) {
			count++;
		}
		if (isLetter) {
			count++;
		}
		if (str.contains("_")) {
			count++;
		}
		if (count >= 2) {
			return true;
		}
		return false;
	}

	/**
	 * 判断两个字段是否一样
	 * 
	 * @author wudi
	 */
	public static boolean judgeStringEquals(String s0, String s1) {
		if (null != s0 && s0.equals(s1)) {
			return true;
		}
		return false;
	}

	public static boolean ObjectToBool(Object obj, boolean defbool) {
		if (obj == null) {
			return defbool;
		}

		String str = obj.toString().trim();
		if (str.equalsIgnoreCase("true")) {
			return true;
		}
		if (str.equalsIgnoreCase("false")) {
			return false;
		}
		return defbool;
	}

	public static Integer ObjectToInt(Object obj, Integer defint) {
		if (obj == null) {
			return defint;
		}
		String str = obj.toString();
		if (isNumber(str)) {
			return Integer.parseInt(str);
		}
		return defint;
	}

	public static Long ObjectToLong(Object obj, Long deflong) {
		if (obj == null) {
			return deflong;
		}

		String str = obj.toString();
		if (isNumber(str)) {
			return Long.parseLong(str);
		}
		return deflong;
	}

	public static String ObjectToString(Object obj, String defStr) {
		if (obj == null) {
			return defStr;
		}
		return obj.toString();
	}

	public static String capitalize(CharSequence s) {
		if (null == s) {
			return null;
		}
		int len = s.length();
		if (len == 0) {
			return "";
		}
		char char0 = s.charAt(0);
		if (Character.isUpperCase(char0)) {
			return s.toString();
		}
		StringBuilder sb = new StringBuilder(len);
		sb.append(Character.toUpperCase(char0)).append(s.subSequence(1, len));
		return sb.toString();
	}

	public static String mapToXml(Map<?, ?> m) {
		StringBuilder sb = new StringBuilder();
		sb.append("<map>");
		Set<?> s = m.keySet();
		Iterator<?> it = s.iterator();
		while (it.hasNext()) {
			String k = it.next().toString();
			String v = m.get(k).toString();
			sb.append("<" + k.trim() + ">");
			sb.append(escapeStringForXml(v));
			sb.append("</" + k.trim() + ">");
		}
		sb.append("</map>");
		return sb.toString();
	}

	public static final int randomInt(int number) {
		if (number > 0) {
			return rdm.nextInt(number) + 1;
		} else {
			return 1;
		}
	}

	public static final String randomString(int length) {
		if (length < 1) {
			return null;
		}
		char randBuffer[] = new char[length];
		for (int i = 0; i < randBuffer.length; i++) {
			randBuffer[i] = numbersAndLetters[rdm.nextInt(71)];
		}
		return new String(randBuffer);
	}

	public static String toHexString(byte[] bts) {
		if (null == bts) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		int len = bts.length;
		for (int i = 0; i < len; i++) {
			if ((0xFF & bts[i]) < 0x10) {
				sb.append("0" + Integer.toHexString(0xFF & bts[i]));
			} else {
				sb.append(Integer.toHexString(0xFF & bts[i]));
			}
		}
		return sb.toString();
	}

	public static String md5(String str) {
		if (str == null) {
			return "";
		}
		StringBuffer strBuffer = new StringBuffer();
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes());
			byte[] hash = md.digest();
			for (int i = 0; i < hash.length; i++) {
				if ((0xFF & hash[i]) < 0x10) {
					strBuffer.append("0" + Integer.toHexString(0xFF & hash[i]));
				} else {
					strBuffer.append(Integer.toHexString(0xFF & hash[i]));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strBuffer.toString();
	}

	public static String md5File(File file) {
		String ret = "";
		if (!file.isFile()) {
			return ret;
		}
		StringBuffer strBuffer = new StringBuffer();

		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			FileInputStream ins = new FileInputStream(file);
			byte buffer[] = new byte[1024];
			int len = 0;
			while ((len = ins.read(buffer, 0, 1024)) != -1) {
				md.update(buffer, 0, len);
			}
			ins.close();

			byte[] hash = md.digest();
			for (int i = 0; i < hash.length; i++) {
				if ((0xFF & hash[i]) < 0x10) {
					strBuffer.append("0" + Integer.toHexString(0xFF & hash[i]));
				} else {
					strBuffer.append(Integer.toHexString(0xFF & hash[i]));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strBuffer.toString();
	}

	public static boolean isValidEmail(String s) {
		if (!hasValue(s)) {
			return false;
		}
		return Pattern
				.compile(
						"^[_\\.0-9a-zA-Z+-]+@([0-9a-zA-Z]+[0-9a-zA-Z-]*\\.)+[a-zA-Z]{2,4}$")
				.matcher(s).find();
	}

	public static boolean isValidMobilNum(String s) {
		if (!hasValue(s)) {
			return false;
		}
		return Pattern.matches("1\\d{10}", s);
	}

	/* ---------------------------------------------------------- */
	public static String HASH_KEY = "xn_ln";
	public static final String HASH_CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-=+";

	public static String getMD5String(String data) {
		MessageDigest alg = null;
		try {
			alg = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		if (alg == null || data == null) {
			return null;
		}
		alg.update(data.getBytes());
		byte[] digest = alg.digest();

		String hs = "";
		String stmp = "";
		for (int i = 0; i < digest.length; i++) {
			stmp = (Integer.toHexString(digest[i] & 0XFF));
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else
				hs = hs + stmp;
		}
		return hs.toUpperCase();
	}

	public static String urlEncoder(String url, String charset) {
		try {
			String urlJsonEncoder = URLEncoder.encode(url, charset);
			return urlJsonEncoder;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Describe:合并数组
	 * <p/>
	 * Date:2015-6-25
	 * <p/>
	 * Author:wangkui
	 */
	public static Object[] mergeArray(Object[] from, Object[] to) {
		Object[] newData = new Object[from.length + to.length];
		System.arraycopy(from, 0, newData, 0, from.length);
		System.arraycopy(to, 0, newData, from.length, to.length);
		return newData;
	}

	/**
	 * 获取图片地址
	 * 
	 * @param url
	 *            原地址
	 * @param width
	 *            宽度 px
	 * @param heigth
	 *            高度px
	 * @return
	 */
	public static String getImageUrl(String url, int width, int heigth) {
		if (isEmpty(url)) {
			return "";
		} else {
			String newUrl;
			try {
				String startUrl = url.substring(0, url.lastIndexOf("."));
				String endUrl = url.substring(url.lastIndexOf("."),
						url.length());
				newUrl = String.format("%s/%dx%d%s", startUrl, width, heigth,
						endUrl);
			} catch (Exception e) {
				e.printStackTrace();
				return "";
			}
			return newUrl;
		}
	}

	/**
	 * 返回两个字符串中间的内容
	 * 
	 * @param all
	 * @param start
	 * @param end
	 * @return
	 */
	public static String getMiddleString(String all, String start, String end) {
		int beginIdx = all.indexOf(start) + start.length();
		int endIdx = all.indexOf(end);
		return all.substring(beginIdx, endIdx);
	}

	public static String changeCharset(String str, String newCharset)
			throws UnsupportedEncodingException {
		if (str != null) {
			// 用默认字符编码解码字符串。
			byte[] bs = str.getBytes();
			// 用新的字符编码生成字符串
			return new String(bs, newCharset);
		}
		return null;
	}

	public static String StringToUTF8(String name) {
		String nameUtf8 = null;
		if (name != null) {
			try {
				nameUtf8 = new String(name.getBytes("ISO-8859-1"), "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return nameUtf8;
	}

	/**
	 * 字符串编码转换的实现方法
	 * 
	 * @param str
	 *            待转换编码的字符串
	 * @param oldCharset
	 *            原编码
	 * @param newCharset
	 *            目标编码
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String changeCharset(String str, String oldCharset,
			String newCharset) throws UnsupportedEncodingException {
		if (str != null) {
			// 用旧的字符编码解码字符串。解码可能会出现异常。
			byte[] bs = str.getBytes(oldCharset);
			// 用新的字符编码生成字符串
			return new String(bs, newCharset);
		}
		return null;
	}

	/**
	 * 判断url是否匹配
	 * 
	 * @param urlString
	 *            是否匹配的url
	 * @return
	 */

	public static boolean isUrl(String urlString) {
		if (urlString == null) {
			return false;
		}
		String regex = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";

		Pattern patt = Pattern.compile(regex);

		Matcher matcher = patt.matcher(urlString);

		boolean isMatch = matcher.matches();

		if (!isMatch) {

			return false;

		} else {
			return true;

		}
	}

	/**
	 * 过滤字符串中的空格
	 * 
	 * @param str
	 * @return
	 */
	public static String filterSpace(String str) {
		str = str.replaceAll(" +", "");
		return str;
	}

	/**
	 * 
	 * Describe:判断字符串是否是数字
	 * 
	 * Date:2015-9-29
	 * 
	 * Author:wangkui
	 */
	public static boolean isNumeric(String str) {
		for (int i = 0; i < str.length(); i++) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 
	 * Describe:判断字符串是否是数字
	 * 
	 * Date:2015-9-29
	 * 
	 * Author:wangkui
	 */
	public boolean isNumericRegular(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}

}
