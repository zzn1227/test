package com.test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

public class Test {

	public static void testAscii() {
		String str = " ";
		// String s = String.valueOf((char) 1);
		String s = "^";
		str = "aa" + s + "bb" + s + "cc";
		// str = "aabbcc";
		System.out.println(str.length() + "  " + str);
		String array[] = StringUtils.split(str, s);
		str = StringUtils.join(array, s);
		System.out.println(str);
	}

	public static void main(String[] args) {
		testAscii();

		Date date = new Date(1399305600000L);
		System.out.println(date);

		Double dd = 13.34645;

		Date now = new Date();
		System.out.println(now.getTime());
		now = DateUtils.addDays(now, 1);
		System.out.println(now.getTime());

		now = DateUtils.truncate(now, Calendar.DATE);
		System.out.println(now);
		now = DateUtils.addSeconds(now, 86399);
		System.out.println(now);

		System.out.println(String.format("添加用户 %s 到event %s 失败", 11, 22));
		try {
			DateUtils.parseDate("2015-03-09 23:59:59", "yyyy-MM-dd HH:mm:ss");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		dd = Math.random() * 100;
		System.out.println(dd);
		int aa = (int) dd.doubleValue();
		System.out.println(aa);
		dd = Math.floor(dd);
		System.out.println(dd);

		int t = Math.max(3, 0);
		System.out.println(t);

		System.out.println(String.format("%-8s%-8s%-8s", "应用名称", "访问人数",
				"累计安装人数"));

		dd = 92d;
		BigDecimal big = new BigDecimal(dd);
		big = big.setScale(2, RoundingMode.HALF_UP);
		String[] array = big.toString().split(".");
		array = StringUtils.split(big.toString(), ".");

		System.out.println(big.intValue());
		System.out.println(big.toString());
	}
}
