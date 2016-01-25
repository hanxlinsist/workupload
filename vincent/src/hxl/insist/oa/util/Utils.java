package hxl.insist.oa.util;

import java.util.Calendar;

public class Utils {
	/**
	 * 用当前时间的 年月日 时分秒 来拼接字符串
	 */
	public static String consistNameByDate() {
		Calendar calendar = Calendar.getInstance();
		return  "" + calendar.get(Calendar.YEAR) + calendar.get(Calendar.MONTH)
				+ calendar.get(Calendar.DAY_OF_MONTH)
				+ calendar.get(Calendar.HOUR_OF_DAY)
				+ calendar.get(Calendar.MINUTE) + calendar.get(Calendar.SECOND);
	}
}
