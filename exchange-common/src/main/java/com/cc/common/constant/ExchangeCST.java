package com.cc.common.constant;

/**
 * @author chencheng0816@gmail.com
 * @date 2019/4/29 11:19
 * @Description ExchangeCST
 */
public class ExchangeCST {

	//服务端默认编码
	public static final String CHARSET_GBK = "GBK";
	//处理字符数组编码
	public static final String CHARSET_ISO = "ISO-8859-1";

	public static final String SEX = "保密";
	public static final String MAN = "男";
	public static final String WOMEN = "女";

	// 火币请求处理结果，"ok" 或"error"
	public static final String STATUS_OK = "ok";
	public static final String STATUS_ERROR = "error";
	public static final String STATUS_BAD = "bad";

	//统一返回码的key
	public static final String RESULT_CODE = "code";
	public static final String RESULT_INFO = "info";

	//统一成功返回码
	public static final String SUCCESS      = "1";
	public static final String SUCCESS_INFO = "操作成功";
	//统一失败返回码
	public static final String ERROR      = "0";
	public static final String ERROR_INFO = "后台操作失败";
	//部分操作失败
	public static final String ERROR_HALF      = "2";
	//提示需用户确认才消失
	public static final String SUCCESS_OK      = "3";
	//调用下一个页面，继续操作
	public static final String SUCCESS_CONTINUE   = "4";
	//退出登录，前端刷新页面
	public static final String SUCCESS_LOGOUT   = "5";

	/**
	 * 删除标记位 0：有效(默认)；1：删除
	 */
	public static final Short DELETE      = 1;
	public static final Short NOT_DELETED = 0;

	/**
	 * 默认密码"888888"的加密字符串
	 */
	public static final String DEFAULT_PWD = "H4LJQr79optu1IelHaGZ94/OfwU=";

	/**
	 * session中的登录用户key
	 */
	public static final String SESSION_KEY_LOGIN_USER = "loginUser";
}
