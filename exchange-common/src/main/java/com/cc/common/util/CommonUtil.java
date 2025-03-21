/**
 * 
 */
package com.cc.common.util;

import org.apache.commons.lang3.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

public final class CommonUtil<E> {
	public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_FORMAT = "yyyy-MM-dd";

	public static boolean isEmpty(List list) {
		return (list == null || list.size() == 0);
	}

	public static boolean isEmpty(Map map) {
		return (map == null || map.size() == 0);
	}

	public static boolean isEmpty(Set set) {
		return (set == null) || (set.size() == 0);
	}

	public static boolean isEmpty(String value) {
		return (value == null || "".equals(value.trim()));
	}

	public static boolean isEmpty(Object value) {
		return (value == null);
	}

	public static boolean isEmpty(Long value) {
		return (value == null);
	}

	public static boolean isEmpty(Integer value) {
		return (value == null);
	}

	public static boolean isEmpty(String[] arrValue) {
		return (arrValue == null || arrValue.length == 0);
	}

	public static boolean isEmpty(Object[] arrObject) {
		return (arrObject == null || arrObject.length == 0);
	}

	public static boolean isNotEmpty(List list) {
		return !isEmpty(list);
	}

	public static boolean isNotEmpty(Map map) {
		return !isEmpty(map);
	}

	public static boolean isNotEmpty(Set set) {
		return !isEmpty(set);
	}

	public static boolean isNotEmpty(String value) {
		return !isEmpty(value);
	}

	public static boolean isNotEmpty(Object value) {
		return !isEmpty(value);
	}

	public static boolean isNotEmpty(Long value) {
		return !isEmpty(value);
	}

	public static boolean isNotEmpty(Integer value) {
		return !isEmpty(value);
	}

	public static boolean isNotEmpty(String[] arrValue) {
		return !isEmpty(arrValue);
	}

	public static boolean isNotEmpty(Object[] arrObject) {
		return !isEmpty(arrObject);
	}

	public static boolean isNotZero(Integer aNum) {
		if (null == aNum || 0 == aNum) {
			return false;
		} else {
			return true;
		}
	}

	public static String getStringValue(Object value) {
		if (isEmpty(value)) {
			return "";
		} else {
			return value.toString();
		}
	}

	public static boolean isDate(Calendar sysDate, Date dbDate) {
		SimpleDateFormat frm = new SimpleDateFormat("yyyy-MM-dd");
		String dbdateStr = frm.format(dbDate);
		String sysdateStr = frm.format(sysDate.getTime());
		if (sysdateStr.equals(dbdateStr)) {
			return true;
		}
		return false;
	}

	// 跟系统时间对比
	public static boolean lessSysDateTime(Calendar sysDate, Date dbDate) {
		if (sysDate.getTime().getTime() > dbDate.getTime()) {
			return true;
		}
		return false;
	}

	// 当前时间格式为string
	/**
	 * @param pattern :"yyyyMMddHHmmss"
	 * @param field :Calendar.SECOND 按秒编移
	 * @param timeLapse ：编移数值
	 */
	public static String formatDateString(String pattern, int field, int timeLapse) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(field, timeLapse);
		SimpleDateFormat frm = new SimpleDateFormat(pattern);
		String dateStr = frm.format(calendar.getTime());
		return dateStr;
	}

	/**
	 * 根据传入的格式将日期对象转成对应格式的日期字符串
	 *
	 * @param date   日期对象
	 * @param format 日期格式
	 * @return 转换后的日期字符串
	 */
	public static String formatDate(Date date, String format) {
		if ((date == null) || StringUtils.isBlank(format)) {
			return null;
		}
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		return formatter.format(date);
	}

	/**
	 * K线图时间轴
	 * @param timeInSecond
	 * @param timeInSecond
	 * @return
	 */
	public static String formatTimeAxis(long timeInSecond) {
		timeInSecond *= 1000;
		SimpleDateFormat dateformat = new SimpleDateFormat("yy-MM-dd HH:mm");
		String dateStr = dateformat.format(timeInSecond);

		return dateStr;
	}

	/**
	 * @param pattern :"yyyyMMddHHmmss"
	 * @param date : 日期
	 */
	public static String dateString(String pattern, Date date) {
		SimpleDateFormat frm = new SimpleDateFormat(pattern);
		String dateStr = frm.format(date.getTime());
		return dateStr;
	}

	/**
	 * MD5 加密
	 */
	public static String getMD5Str(String str) {
		MessageDigest messageDigest = null;

		try {
			messageDigest = MessageDigest.getInstance("MD5");

			messageDigest.reset();

			messageDigest.update(str.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
			return "";
		} catch (UnsupportedEncodingException e) {
			return "";
		}

		byte[] byteArray = messageDigest.digest();

		StringBuffer md5StrBuff = new StringBuffer();

		for (int i = 0; i < byteArray.length; i++) {
			if (Integer.toHexString(0xFF & byteArray[i]).length() == 1) {
				md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
			} else {
				md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
			}
		}

		return md5StrBuff.toString();
	}

	/**
	 * 判断传输的分页参数和页码是否同时为空
	 * 
	 * @param allData
	 * @param page
	 * @return [参数说明]
	 * 
	 * @return boolean [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public static boolean pageParmsIsEmpty(String allData, String page) {
		if (isEmpty(allData) && isEmpty(page)) {
			return true;
		}
		return false;
	}

	/**
	 * 判断传入的参数是否为整数
	 * 
	 * @param str
	 * @return [参数说明]
	 * 
	 * @return boolean [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public static boolean isInt(String str) {
		try {
			new Integer(str);

			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public static boolean isLong(String str) {
		try {
			new Long(str);

			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public static String dateToTimeStamp() {

		return dateString("yyyy-MM-dd HH:mm:ss", new Date());
	}

	/**
	 * <一句话功能简述> 英文双引号转为中文双引号
	 * 
	 * @param paramString
	 * @return String []
	 * @see [类、类#方法、类#成员]
	 */
	public static String enStringToZhString(String paramString) {

		return paramString.replaceAll("\"", "”");
	}

	/**
	 * 最大长度输入6个字符 先转换成URLEncoder：%E8%B6%8A%E5%85%89%E5%AE%9D%E7%9B%92 再替换%为-
	 * 输出格式：-E8-B6-8A-E5-85-89-E5-AE-9D-E7-9B-92
	 * 
	 * @param resourceName
	 * @return
	 */
	public static String getCurrURLEncoder(String resourceName) {

		try {// 临时转码:中山标清中间件不认中文 及%, by 903702
			if (resourceName.length() > 6) {
				resourceName = resourceName.substring(6) + "...";// 最大6个字符
			}
			resourceName = URLEncoder.encode(resourceName, "UTF-8");
			resourceName = resourceName.replace("%", "-");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return resourceName;
	}

	/**
	 * 是否是整形数字 <一句话功能简述> <功能详细描述>
	 * 
	 * @author dengyong903760
	 * @version [V200R001, 2010-6-24]
	 * @see [相关类/方法]
	 * @since [DHM.Core.IEPGM-V200R001]
	 */
	public static boolean isInt2(String str) {
		if (str != null) {
			if (str.matches("\\d+")) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 封转订购时返回的backurl
	 * 
	 * @param url
	 * @param orderResult
	 * @param userId
	 * @return
	 */
	public static String getOrderBackurl(String url, String orderResult, String userId) {
		userId = userId == null ? "" : userId;
		String parmStr = "orderResult=" + orderResult + "&userId=" + userId;
		if (url != null) {
			if (url.indexOf(".action?") <= 0) {
				url = url + "?" + parmStr;
			} else {
				url = url + "&" + parmStr;
			}
		}
		return url;
	}

	/**
	 * 计算一个日期N天之前的日期
	 *
	 * @param intervalDays
	 * @return
	 */
	public static Date getTodayBefore(int intervalDays, String dateFormat) {
		Calendar calendar = Calendar.getInstance();

		calendar.add(Calendar.DATE, -intervalDays);
		if ("Date".equals(dateFormat)) {
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);
		}
		Date date = calendar.getTime();
		// SimpleDateFormat format = new SimpleDateFormat(dateFormat);
		// String dayBefore = format.format(date);
		return date;
	}

	/**
	 * 计算一个日期N天之前的日期
	 *
	 * @param intervalDays
	 * @return
	 */
	public static Date getToday2Before(int intervalDays) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -intervalDays);
		Date date = calendar.getTime();
		return date;
	}

	/**
	 * 计算一个日期N天之前的日期
	 *
	 * @param intervalDays
	 * @return
	 */
	public static String getTodayBefore(int intervalDays) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -intervalDays);
		Date date = calendar.getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String dayBefore = format.format(date);
		return dayBefore;
	}

	/**
	 * 计算一个日期N天之前的日期
	 *
	 * @param intervalDays
	 * @return
	 */
	public static Date getTodayDateBefore(int intervalDays, String dateFormat) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -intervalDays);
		Date date = calendar.getTime();
		return date;
	}

	/**
	 * 计算一个日期X分钟前的日期
	 *
	 * @return
	 */
	public static Date getMinuteBefore(Date date, int intervalMinute) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, -intervalMinute);
		Date result = calendar.getTime();
		return result;
	}

	/**
	 * 计算一个日期N天之后的日期
	 *
	 * @param intervalDays
	 * @return
	 */
	public static String getTodayAfter(int intervalDays) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, intervalDays);
		Date date = calendar.getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String dayAfter = format.format(date);
		return dayAfter;
	}

	/**
	 * 计算一个日期N天之后的日期
	 *
	 * @param intervalDays
	 * @return
	 */
	public static Date getToday2After(int intervalDays) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, intervalDays);
		Date date = calendar.getTime();
		return date;
	}

	public static Date getTodayBeforeHour(int afterHuor) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.HOUR, -afterHuor);
		Date date = calendar.getTime();

		return date;
	}

	/**
	 * 获取指定时间
	 * 
	 * @param dateTime
	 * @param field
	 * @param amount
	 * @return
	 */
	public static Date getDateTime(Date dateTime, int field, int amount) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dateTime);
		calendar.add(field, amount);
		Date date = calendar.getTime();

		return date;
	}

	/**
	 * 对传入的字符串，截掉后面的数字 例如name=电视剧：战地黄花15/26， 处理后变为 电视剧：战地黄花
	 * 如果传入的字符串第一个字符就是数字，例如name=360度说法，则不处理，直接返回原字符串
	 * 
	 * @param name
	 * @return
	 */
	public static String getNameRemoveDigital(String name) {
		String result = name;
		int index = 0;
		for (int i = 0; i < name.length(); i++) {
			char ch = name.charAt(i);
			if (ch >= '0' && ch <= '9') {
				index = i;
				break;
			}
		}

		if (index == 0) {
			return result;
		}
		result = name.substring(0, index);

		return result;
	}

	/**
	 * 过虑字符串中的字母，用于用户分组鉴权的时候取模
	 * 
	 * @return String
	 */

	public static String filtrateLetters(String userCode) {

		StringBuffer result = new StringBuffer(userCode);
		int count = 0;
		for (int i = 0; i < userCode.length(); i++) {
			char ch = userCode.charAt(i);
			if ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')) {
				result.deleteCharAt(i - count);
				count++;
			}
		}
		return result.toString();
	}

	/**
	 * 将 "时:分:秒" 这种格式的时间长度转换为秒数 如"00:22:30" 转换为 "1350"。
	 * 
	 * @author 905978
	 * @param runtime
	 *            "00:22:30"
	 * @return second "1350"
	 */
	public static int time2Second(String runtime) {
		int timeCount = 0;
		try {
			String[] celltime = runtime.split(":");
			int hour = Integer.parseInt(celltime[0]);
			int minute = Integer.parseInt(celltime[1]);
			int second = Integer.parseInt(celltime[2]);
			timeCount = 3600 * hour + 60 * minute + second;
		} catch (Exception e) {
			int temptime = 0;
			try {
				temptime = Integer.parseInt(runtime);

			} catch (Exception e2) {
				return 0;
			}
			return temptime;
		}
		return timeCount;
	}

	/**
	 * @author 905978
	 * @param resourceName
	 *            要截取的字符
	 * @param suffix
	 *            截取后添加在后边的后缀字符
	 * @param beginIndex
	 *            截取的开始位置，0开始
	 * @param endIndex
	 *            截取到第几个字符
	 * @return
	 */
	public static String Filtername(String resourceName, String suffix, int beginIndex, int endIndex) {
		if (isEmpty(resourceName)) {
			resourceName = "";
		}
		if (isEmpty(suffix)) {
			suffix = "";
		}
		String name = resourceName;
		if (resourceName.length() > endIndex) {
			StringBuffer sb = new StringBuffer();
			sb.append(resourceName.substring(beginIndex, endIndex));
			sb.append(suffix);
			name = sb.toString();
		}
		return name;
	}

	/**
	 * 将list 转化为 二位字节码
	 * 
	 * @param list
	 * @return
	 */
	public static byte[] getBytesByList(List list) {
		byte[] bytes = null;
		try {
			ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
			ObjectOutputStream objectOut = new ObjectOutputStream(byteOut);
			objectOut.writeObject(list);
			bytes = byteOut.toByteArray();

		} catch (Exception e) {
			return null;
		}
		return bytes;
	}

	/**
	 * 二位字节码 反转化为 list
	 * 
	 * @param bytes
	 * @return
	 */
	public static List getListBybytes(byte[] bytes) {
		List list = null;
		try {
			ByteArrayInputStream byteIn = new ByteArrayInputStream(bytes);
			ObjectInputStream objectInputStream = new ObjectInputStream(byteIn);
			list = (List<Object>) objectInputStream.readObject();

		} catch (Exception e) {
			return null;
		}
		return list;
	}

	/**
	 * 测试打印方法
	 */
	public static void prLog(Object obj) {
		String msg = "\ntest 打印---> start----\n";
		msg += obj.toString();
		msg += "\n\ntest 打印---> end----\n";
		System.out.println(msg);
	}

	/**
	 * 获取服务器IP地址
	 */
	public static String getServerIp() {
		String sIP = "";
		InetAddress ip = null;
		try {
			boolean bFindIP = false;
			Enumeration<NetworkInterface> netInterfaces = (Enumeration<NetworkInterface>) NetworkInterface.getNetworkInterfaces();
			while (netInterfaces.hasMoreElements()) {
				if (bFindIP) {
					break;
				}
				NetworkInterface ni = (NetworkInterface) netInterfaces.nextElement();
				Enumeration<InetAddress> ips = ni.getInetAddresses();
				while (ips.hasMoreElements()) {
					ip = (InetAddress) ips.nextElement();
					if (!ip.isLoopbackAddress() && ip.getHostAddress().matches("(\\d{1,3}\\.){3}\\d{1,3}")) {
						bFindIP = true;
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (null != ip) {
			sIP = ip.getHostAddress();
		}
		return sIP;
	}

	/**
	 * 获取客户端IP地址
	 */
	public static String getClientIp(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}

		return ip;
	}

	/**
	 * 指定key=value 
	 * @param key 字符串变量名
	 * @param value 值
	 */
	public static void set(String key, String value) {
		if (isEmpty(key) || !key.equals(value)) {
			key = value;
		}
	}
	
	/**
	 * 格式化性别，男、女或保密
	 */
	public static String formatSex(String sex) {
		if(isEmpty(sex) || sex.equals("null")) {
			sex = "保密";
		}
		return sex;
	}
	
	/**
	 * 获取32位唯一序列号
	 * @return
	 */
	public static String get32UUID() {
		String uuid = UUID.randomUUID().toString(); 
		//去掉“-”符号 
		return uuid.replaceAll("-", "");
	}
	
	/**
	 * 判断是否为2003以前的版本
	 * @return
	 */
	public static boolean isExcel2003(String filePath) {   
        return filePath.matches("^.+\\.(?i)(xls)$");    
    } 
	
	/**
	 * 判断是否为2007的版本
	 * @return
	 */
	public static boolean isExcel2007(String filePath) {    
        return filePath.matches("^.+\\.(?i)(xlsx)$");    
    }
	
	/**
	 * 四舍五入
	 * @param value
	 * @return
	 */
	public static String formatDouble(double value) {    
		BigDecimal bg = new BigDecimal(value).setScale(1, RoundingMode.HALF_UP);
		
        return bg.toString();
    }
	
	/**
	 * 格式化前端参数，若参数为null，则返回“default description”
	 * @return
	 */
	public static String parseDescription(String pageParam) {
		if(isEmpty(pageParam)) {
			return new String("NOITPIRCSEDTLUAFED");
		}
		return pageParam;
    }
	
	/**
	 * m进制转为n进制
	 * @param radixStr 原字符串
	 * @param m 原进制
	 * @param n 目的进制
	 * @return
	 */
	public static String m2n(String radixStr, int m, int n) {
		return new BigInteger(radixStr, m).toString(n);
	}
	
	public static String saveExcel(String radixStr, int m, int n) {
		return new BigInteger(radixStr, m).toString(n);
	}

	/**
	 * 判断是否含有特殊字符,包含 <>&:;,"*?|\
	 *
	 * @param str
	 * @return true为包含，false为不包含
	 */
	public static boolean hasSpecial(String str) {
		String regEx = "[<>&:;,\"\\*\\?\\|\\\\]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.find();
	}

	/**
	 *
	 * @param targetList 目标排序List
	 * @param sortField 排序字段(实体类属性名)
	 * @param sortMode 排序方式（asc or desc），默认为asc正序
	 */
	public void sort(List<E> targetList, final String sortField, final String sortMode) {
        Collections.sort(targetList, new Comparator() {
            public int compare(Object obj1, Object obj2) {
                int retVal = 0;
                try {
                    //首字母转大写
                    String newStr=sortField.substring(0, 1).toUpperCase() + sortField.replaceFirst("\\w","");
                    String methodStr = "get" + newStr;

                    Method method1 = ((E)obj1).getClass().getMethod(methodStr, null);
                    Method method2 = ((E)obj2).getClass().getMethod(methodStr, null);
                    if (sortMode != null && "desc".equals(sortMode)) {
                        retVal = method2.invoke(((E) obj2), null).toString().compareTo(method1.invoke(((E) obj1), null).toString()); // 倒序
                    } else {
                        retVal = method1.invoke(((E) obj1), null).toString().compareTo(method2.invoke(((E) obj2), null).toString()); // 正序
                    }
                } catch (Exception e) {
                    throw new RuntimeException();
                }
                return retVal;
            }
        });
    }
	
	 /**
	  * list 去重
	  * @param list
	  * @return
	  */
	public static List removeDuplicate(List list) {   
	    HashSet h = new HashSet(list);   
	    list.clear();   
	    list.addAll(h);   
	    return list;   
	}   
}
