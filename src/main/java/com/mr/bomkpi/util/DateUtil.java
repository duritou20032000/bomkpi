package com.mr.bomkpi.util;

import org.codehaus.groovy.util.StringUtil;
import org.springframework.util.StringUtils;

import java.text.*;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.sql.Timestamp;


public class DateUtil {
	
	public final static long DAY = 24L * 60L * 60L * 1000L;
	public final static long HOUR = 60L * 60L * 1000L;
	public final static long MINUTES =  60L * 1000L;
	public final static String STANDARD_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public final static Timestamp MAX_TIME = new Timestamp(253339228800000L); // 9998-01-01T00:00:00.000 UTC

	private static Calendar getCalendarFormYear(int year){
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		cal.set(Calendar.YEAR, year);
		return cal;
	}
	public static String getStartDayOfWeekNo(int year,int weekNo){
		Calendar cal = getCalendarFormYear(year);
		cal.set(Calendar.WEEK_OF_YEAR, weekNo);
		return cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DAY_OF_MONTH);

	}
	public static String getEndDayOfWeekNo(int year,int weekNo){
		Calendar cal = getCalendarFormYear(year);
		cal.set(Calendar.WEEK_OF_YEAR, weekNo);
		cal.add(Calendar.DAY_OF_WEEK, 6);
		return cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DAY_OF_MONTH);
	}
	public static int getWeekOfYearFromMon(Date date) {
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setMinimalDaysInFirstWeek(7);
		c.setTime(date);

		return c.get(Calendar.WEEK_OF_YEAR);
	}

	/**
	 * start from sunday
	 * @param date
	 * @param format
	 * @return
	 * @throws ParseException
     */
	public static Date parseDateString(String date, String format) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.parse(date);
	}

	public static int getWeekOfYear (Date thisDate) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(thisDate);
		return cal.get(Calendar.WEEK_OF_YEAR);
	}

	public static int getMonthOfYear (Date thisDate) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(thisDate);
		return cal.get(Calendar.MONTH)+1;
	}

	public static int getYear (Date thisDate) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(thisDate);
		return cal.get(Calendar.YEAR);
	}
	
	public static Timestamp parseTimestampString(String s, String format) throws ParseException {
		if(StringUtils.isEmpty(s)){
			return null;
		}
		Date d = parseDateString(s, format);
		return new Timestamp(d.getTime());
	}
	
	public static Timestamp parseStandardTimestampString(String s) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(STANDARD_TIME_FORMAT);
		return new Timestamp(sdf.parse(s).getTime());
	}
	
	/**
	 * Return one month before current time
	 * @return Date
	 */
	public static Date lastMonth(){
		Calendar cal = GregorianCalendar.getInstance();
		int month = cal.get(Calendar.MONTH);
		cal.roll(Calendar.MONTH, -1);
		if(month == 0)
			cal.roll(Calendar.YEAR, -1);
		return cal.getTime();
	}

	public static Date lastMonthZeroSecond(){
		Calendar cal = GregorianCalendar.getInstance();
		int month = cal.get(Calendar.MONTH);
		cal.roll(Calendar.MONTH, -1);
		if(month == 0)
			cal.roll(Calendar.YEAR, -1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		return cal.getTime();
	}
	
	public static Date firstDayOfMonth(Date d){
		Calendar cal = GregorianCalendar.getInstance();
		cal.setTime(d);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return cal.getTime();
	}
	
	/*public static Date lastDayOfMonth(Date d){
		java.util.Calendar cal = GregorianCalendar.getInstance();
		cal.setTime(d);
		cal.roll(Calendar.MONTH, 1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.add(Calendar.DATE, -1);
		return cal.getTime();
	}*/
	
	public static Date lastDayOfMonth(Date d){
		Calendar cal = GregorianCalendar.getInstance();
		cal.setTime(d);
		cal.set(Calendar.DATE, 1);
		cal.add(Calendar.MONTH,1);
		cal.add(Calendar.DATE, -1);
		return cal.getTime();
	}
	
	/**
	 * Return one month after current time
	 * @return
	 */
	public static Date nextMonth() {
		Calendar cal = Calendar.getInstance();
		cal.roll(Calendar.MONTH, 1);
		return cal.getTime();
	}
	
	/**
	 * Move days before/after current time
	 * @param days
	 * @return
	 */
	public static Date addDays(int days){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, days);
//		cal.roll(Calendar.DATE, days);
		return cal.getTime();
	}
	
	public static Date addMonths(Date date, int months){
		Calendar cal = GregorianCalendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, months);
		return cal.getTime();
	}
	
	public static Date addDays(Date date, int days){
		Calendar cal = GregorianCalendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, days);
		return cal.getTime();
	}
	
	public static Date addMinutes(Date date, int minutes){
		Calendar cal = GregorianCalendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MINUTE, minutes);
		return cal.getTime();
	}
	
	public static Date addHours(Date date, int hours){
		Calendar cal = GregorianCalendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.HOUR_OF_DAY, hours);
		return cal.getTime();
	}

	public static Date nextMonthDays(Date date, int days){
		Calendar cal = GregorianCalendar.getInstance();
		cal.setTime(date);
		cal.roll(Calendar.MONTH, 2);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.add(Calendar.DATE, days);
		return cal.getTime();
	}
	/**
	 * Just return the current date
	 * @return
	 */
	public static Date currentDate(){
		return new Date();
	}
	
	public static Timestamp currenTimestamp() {
		return new Timestamp(System.currentTimeMillis()); 
	}

	/**
	 *	当日开始时间
	 */
	public static Timestamp currDayStartTime() {
		Calendar todayStart = Calendar.getInstance();
		todayStart.set(Calendar.HOUR_OF_DAY, 0);
		todayStart.set(Calendar.MINUTE, 0);
		todayStart.set(Calendar.SECOND, 0);
		todayStart.set(Calendar.MILLISECOND, 0);
		return new Timestamp(todayStart.getTimeInMillis());
	}

	/**
	 *	当日结束时间
	 */
	public static Timestamp currDayEndTime() {
		Calendar todayEnd = Calendar.getInstance();
		todayEnd.set(Calendar.HOUR_OF_DAY, 23);
		todayEnd.set(Calendar.MINUTE, 59);
		todayEnd.set(Calendar.SECOND, 59);
		todayEnd.set(Calendar.MILLISECOND, 999);
		return new Timestamp(todayEnd.getTimeInMillis());
	}

	public static Timestamp currenTimestamp(String format) throws ParseException {		
		return parseTimestampString(getCurrStrDate(format),format); 
	}
	
	public static Date currentDate(String format)  throws ParseException {		
		return parseDateString(getCurrStrDate(format),format); 
	}
	
	
	public static String getCurrStrDate(String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		Date Now = new Date();
		String NDate = formatter.format(Now);
		return NDate;
	}

	public static int daysBetweenDates(Date d1, Date d2) throws ParseException{
		long btw = d2.getTime() - d1.getTime();
		return (int)Math.ceil((double)btw/DAY);
	}

	public static int daysFloorBetweenDates(Date d1, Date d2) throws ParseException{
		long btw = d2.getTime() - d1.getTime();
		return (int)Math.floor((double)btw/DAY);
	}

	public static int hoursBetweenDates(Date d1, Date d2) throws ParseException{
		long btw = d2.getTime() - d1.getTime();
		return (int)Math.ceil((double)btw/HOUR);
	}

	public static double hoursBetweenTimestamp(Timestamp t1, Timestamp t2) throws ParseException{
		long btw = t2.getTime() - t1.getTime();
		return NumberUtil.upFormatDouble((double)btw/HOUR, 4);
	}

	public static int minutesBetweenDates(Date d1, Date d2) throws ParseException{
        long btw = d2.getTime() - d1.getTime();
        return (int)Math.ceil((double)btw/MINUTES);
    }

	public static String getStringDate(Timestamp timeDate, String format) {
		SimpleDateFormat df = new SimpleDateFormat(format);
		return df.format(timeDate);
	}
	
	public static String getStringDate(Date date, String format) {
		DateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}
	
	public static String getStandardTimeStr(Timestamp ts){
		return DateUtil.getStringDate(ts, STANDARD_TIME_FORMAT);
	}

	public static String getCurrentDate() {
		return getStringDate(currentDate(), "yyyy-MM-dd HH:mm:ss");
	}

	public static Timestamp parseStringtoTimestamp(String time) throws ParseException {
		String standardTimeString = "0000-00-00 "+time+":00";
		return parseStandardTimestampString(standardTimeString);
	}

	public static boolean isSameDate(Timestamp date1, Timestamp date2) {

		if(date1 == null){
			return date2 == null;
		}

		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);

		boolean isSameYear = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR);

		boolean isSameMonth = cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH);

		boolean isSameDate = cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH);

		return isSameYear && isSameMonth && isSameDate;
	}

	/**
	 * 获取日期所在周的周日
	 * @param InputDate
	 * @return
	 * @throws ParseException
	 */
	public static String getSundayOfWeek(Date InputDate) throws ParseException {
		Calendar cDate = Calendar.getInstance();
		cDate.setFirstDayOfWeek(Calendar.SUNDAY);
		cDate.setTime(InputDate);

		Calendar firstDate = Calendar.getInstance();

		firstDate.setFirstDayOfWeek(Calendar.SUNDAY);
		firstDate.setTime(InputDate);

		if (cDate.get(Calendar.WEEK_OF_YEAR) == 1 && cDate.get(Calendar.MONTH) == 11) {
			firstDate.set(Calendar.YEAR, cDate.get(Calendar.YEAR) + 1);
		}

		int typeNum = cDate.get(Calendar.WEEK_OF_YEAR);

		firstDate.set(Calendar.WEEK_OF_YEAR, typeNum);
		firstDate.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		// 所在周开始日期
		return getStringDate(firstDate.getTime(),"yyyy-MM-dd");
	}

	/**
	 * 获取日期所在周的周日
	 * @param InputDate
	 * @return
	 * @throws ParseException
	 */
	public static String getSaturdayOfWeek(Date InputDate) throws ParseException {
		Calendar cDate = Calendar.getInstance();
		cDate.setFirstDayOfWeek(Calendar.SUNDAY);
		cDate.setTime(InputDate);

		Calendar lastDate = Calendar.getInstance();
		lastDate.setFirstDayOfWeek(Calendar.SUNDAY);
		lastDate.setTime(InputDate);

		if (cDate.get(Calendar.WEEK_OF_YEAR) == 1 && cDate.get(Calendar.MONTH) == 11) {
			lastDate.set(Calendar.YEAR, cDate.get(Calendar.YEAR) + 1);
		}

		int typeNum = cDate.get(Calendar.WEEK_OF_YEAR);

		lastDate.set(Calendar.WEEK_OF_YEAR, typeNum);
		lastDate.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
		// 所在周结束日期
		return getStringDate(lastDate.getTime(),"yyyy-MM-dd");
	}

	/**
	 * 返回指定日期的季的第一天
	 *
	 * @param
	 * @return
	 */
	public static Date getFirstDayOfQuarter(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return getFirstDayOfQuarter(calendar.get(Calendar.YEAR),
				getQuarterOfYear(date));
	}
	/**
	 * 返回指定年季的季的第一天
	 *
	 * @param year
	 * @param quarter
	 * @return
	 */
	public static Date getFirstDayOfQuarter(Integer year, Integer quarter) {
		Calendar calendar = Calendar.getInstance();
		Integer month = new Integer(0);
		if (quarter == 1) {
			month = 1 - 1;
		} else if (quarter == 2) {
			month = 4 - 1;
		} else if (quarter == 3) {
			month = 7 - 1;
		} else if (quarter == 4) {
			month = 10 - 1;
		} else {
			month = calendar.get(Calendar.MONTH);
		}
		return firstDayOfMonth(year, month);
	}

	/**
	 * 返回指定日期的季的最后一天
	 *
	 * @param
	 * @return
	 */
	public static Date getLastDayOfQuarter(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return getLastDayOfQuarter(calendar.get(Calendar.YEAR),getQuarterOfYear(date));
	}

	/**
	 * 返回指定年季的季的最后一天
	 *
	 * @param year
	 * @param quarter
	 * @return
	 */
	public static Date getLastDayOfQuarter(Integer year, Integer quarter) {
		Calendar calendar = Calendar.getInstance();
		Integer month = new Integer(0);
		if (quarter == 1) {
			month = 3 - 1;
		} else if (quarter == 2) {
			month = 6 - 1;
		} else if (quarter == 3) {
			month = 9 - 1;
		} else if (quarter == 4) {
			month = 12 - 1;
		} else {
			month = calendar.get(Calendar.MONTH);
		}
		return lastDayOfMonth(year, month);
	}

	/**
	 * 返回指定年月的月的最后一天
	 *
	 * @param year
	 * @param month
	 * @return
	 */
	public static Date lastDayOfMonth(Integer year, Integer month) {
		Calendar calendar = Calendar.getInstance();
		if (year == null) {
			year = calendar.get(Calendar.YEAR);
		}
		if (month == null) {
			month = calendar.get(Calendar.MONTH);
		}
		calendar.set(year, month, 1);
		calendar.roll(Calendar.DATE, -1);
		return calendar.getTime();
	}

	/**
	 * 返回指定年月的月的第一天
	 *
	 * @param year
	 * @param month
	 * @return
	 */
	public static Date firstDayOfMonth(Integer year, Integer month) {
		Calendar calendar = Calendar.getInstance();
		if (year == null) {
			year = calendar.get(Calendar.YEAR);
		}
		if (month == null) {
			month = calendar.get(Calendar.MONTH);
		}
		calendar.set(year, month, 1);
		return calendar.getTime();
	}


	/**
	 * 返回指定日期的季度
	 *
	 * @param date
	 * @return
	 */
	public static int getQuarterOfYear(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH) / 3 + 1;
	}


	public static void main(String[] args) throws Exception{

		Timestamp t1 = parseStandardTimestampString("2017-01-01 00:00:00");
		Timestamp t2 = parseStandardTimestampString("2017-01-01 00:06:00");

		double h = hoursBetweenTimestamp(t1, t2);
		System.out.println(h);


		System.out.println(currDayStartTime());

		System.out.println(DateUtil.currenTimestamp("yyyy-MM-dd"));


		System.out.println(currDayEndTime());

		System.out.println(DateUtil.addDays(-10));
		System.out.println(DateUtil.parseDateString(DateUtil.getStandardTimeStr(DateUtil.currenTimestamp()), "yyyy-MM-dd HH:mm:ss"));
		Date d1 = DateUtil.parseDateString("2013-07-06 09:00:00","yyyy-MM-dd HH:mm:ss");
		Date d2 = DateUtil.parseDateString("2013-07-07 09:00:01","yyyy-MM-dd HH:mm:ss");
		System.out.println(d1);
		System.out.println(d2);

		long btw = d2.getTime() - d1.getTime();
		System.out.println(btw);
		System.out.println(Math.ceil((double)btw/DAY));
		System.out.println("Hours:"+hoursBetweenDates(d1,d2));
		System.out.println("Days:"+daysBetweenDates(d1,d2));

		String strXpireDate = "2015-12-29";
		Timestamp xpireDate = DateUtil.parseTimestampString(strXpireDate,"yyyy-MM-dd");
		String strXpireDate2 = DateUtil.getStringDate(xpireDate, "yyyy-MM-dd");
		System.out.println(xpireDate);
		System.out.println(strXpireDate2);

		Date currdate = DateUtil.currentDate();
		Date lstMonthDate = DateUtil.addMonths(currdate, 3);
		System.out.println(currdate);
		System.out.println(lstMonthDate);

//		System.out.println(StringUtil.dateToStandardDateStr(DateUtil.lastDayOfMonth(DateUtil.lastMonth())));

//		Date d3 = DateUtil.parseDateString("2015-12-06 09:00:00","yyyy-MM-dd HH:mm:ss");
//		System.out.println(StringUtil.dateToStandardDateStr(DateUtil.lastDayOfMonth(d3)));

        System.out.println(daysBetweenDates(parseTimestampString("2016-11-11 09:00:00","yyyy-MM-dd HH:mm:ss"),currenTimestamp()));

	}
}
