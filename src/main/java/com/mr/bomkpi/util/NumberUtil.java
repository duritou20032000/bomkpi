package com.mr.bomkpi.util;

//import com.bm.biz.common.BizException;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumberUtil {
	
	/**
	 * 把字符串转化成Integer类型
	 * 如果输入参数是 null值或空值，返回null
	 * @param str
	 * @return
	 */
	public static Integer parseStringToInteger(String str) {
		if (null == str || "".equals(str.trim())) {
			return null;
		}
		return Integer.parseInt(str);
	}
	
	/**
	 * 把字符串转化成Double类型
	 * 如果输入参数是 null值或空值，返回null
	 * @param str
	 * @return
	 */
	public static Double parseStringToDouble(String str) {
		if (null == str || "".equals(str.trim())) {
			return null;
		}
		return Double.parseDouble(str);
	}
	
	/**
	 * 把null转换成 0
	 * @param number
	 * @return
	 */
	public static Double parseNullToZero(Double number) {
		if (null == number) {
			return Double.valueOf(0);
		}
		return number;
	}
	
	/**
	 * 把null转换成 0
	 * @param number
	 * @return
	 */
	public static Integer parseNullToZero(Integer number) {
		if (null == number) {
			return Integer.valueOf(0);
		}
		return number;
	}
	
	public static Double roundUpFormatDouble(Double num, int digit){
		BigDecimal bd = new BigDecimal(Double.toString(num));
		
		return bd.setScale(digit, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	
	public static Double upFormatDouble(Double num, int digit){
		BigDecimal bd = new BigDecimal(Double.toString(num));
		return bd.setScale(digit, BigDecimal.ROUND_UP).doubleValue();
	}

//	/**
//	 * 判断是否是整数（含负号）
//	 */
//	public static double isInteger(String str) throws BizException {
//
//		if(StringUtil.isEmptyOrNull(str)){
//			throw new BizException("数量不能为空");
//		}
//
//		Pattern pattern = Pattern.compile("^-?[0-9]+");
//		if(!pattern.matcher(str).matches()){
//			throw new BizException(I18n.getString("LABEL.rf.inputqty"));
//		}
//
//		try {
//			return Double.parseDouble(str);
//		}catch (NumberFormatException e){
//			throw new BizException(I18n.getString("LABEL.rf.inputqty"));
//		}
//	}

	private static boolean match(String regex, String str) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		return matcher.matches();
	}

	public static Integer[] parseStr2IntArr(String arrStr){

		if(StringUtil.isEmptyOrNull(arrStr)){
			return null;
		}
		String[] strArr = arrStr.split(",");
		Integer[] arr = new Integer[strArr.length];
		for (int i = 0; i < strArr.length; i++) {
			arr[i] = new Integer(strArr[i]);
		}
		return arr;
	}

	public static void main(String args[]) {
		Integer n = null;
		System.out.println("=======" + NumberUtil.parseNullToZero(n));


		
		Double cartonweight = 1.004;
		Double ctn1 = NumberUtil.roundUpFormatDouble(cartonweight, 2);
		Double ctn11 = NumberUtil.roundUpFormatDouble(cartonweight, 4);
		Double ctn2 = NumberUtil.upFormatDouble(cartonweight, 2);
		System.out.println(ctn1+"---"+ctn11);
		System.out.println(ctn2);
		System.out.println(Math.ceil(ctn1));
		System.out.println(Math.ceil(ctn2));
	}
}
