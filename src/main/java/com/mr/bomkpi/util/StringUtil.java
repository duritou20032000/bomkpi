package com.mr.bomkpi.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.HtmlUtils;

import java.io.*;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class StringUtil {

    public static final String STANDARD_TIME_FORMAT = "HH:mm:ss";
    public static final String STANDARD_DATE_FORMAT = "yyyy-MM-dd";
    public static final String STANDARD_DATE_FORMAT2 = "yyyyMMdd";

    private static final Logger LOGGER = LoggerFactory.getLogger(StringUtil.class);

    /**
     * 判断字符串是否为null值或空值
     * null值或空值返回 true
     * 非空值返回 false
     *
     * @param str
     * @return
     */
    public static boolean isEmptyOrNull(String str) {
        if (null == str || "".equals(str.trim()) || ("null").equals(str.trim())) {
            return true;
        }
        return false;
    }

    public static String isEmptyAndReturn(Object object) {
        if (null == object || "".equals(object.toString()) || ("null").equals(object.toString())) {
            return "";
        }
        return object.toString();
    }

    /**
     * 去除字符串中的空格、回车、换行符、制表符
     *
     * @param str
     * @return
     */
    public static String replaceBlank(String str) {
        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
            dest = replaceSlash(dest);
        }
        return dest;
    }

    public static String replaceSlash(String str) {
        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile("\\\\|/");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }

    public static String trim(String s) {
        if (null != s) {
            return s.trim();
        } else {
            return s;
        }
    }

    public static String emptyToHorizonLine(String value) {
        if (isEmptyOrNull(value)) {
            return "-";
        }
        return value;
    }

	/*public static boolean checkDateFormat(String dateStr){
        String eL = "^([\\d]{4}(((0[13578]|1[02])((0[1-9])|([12][0-9])|(3[01])))|(((0[469])|11)((0[1-9])|([12][1-9])|30))|(02((0[1-9])|(1[0-9])|(2[1-8])))))|((((([02468][048])|([13579][26]))00)|([0-9]{2}(([02468][048])|([13579][26]))))(((0[13578]|1[02])((0[1-9])|([12][0-9])|(3[01])))|(((0[469])|11)((0[1-9])|([12][1-9])|30))|(02((0[1-9])|(1[0-9])|(2[1-9])))))$";
		Pattern p = Pattern.compile(eL);
		Matcher m = p.matcher(dateStr);
		boolean b = m.matches();
		return b;
	}*/

    /**
     * 判断字符串是否为空
     * null或空值返回 null
     * 非空值返回原值
     *
     * @param str
     * @return
     */
    public static String emptyStringToNull(String str) {
        if (isEmptyOrNull(str))
            return null;
        else
            return str.trim();
    }

    /**
     * 用来处理状态码查询条件
     * 正常返回“0”以上的字串
     * 遇到"-1"返回null
     *
     * @param param
     * @return
     */
    public static String statusParam(String param) {
        String s = emptyStringToNull(param);
        if (s == null)
            return s;
        if ("-1".equals(s))
            return null;
        return s;
    }

    /**
     * 判断字符串是否为null
     * null返回空值
     * 非空值返回原值
     *
     * @param str
     * @return
     */
    public static String nullToEmptyString(String str) {
        if (null == str || str.equals("null")) {
            return "";
        }
        return str;
    }

    /**
     * 根据日期的起始值生成取值范围
     * 用于SQL的数据库查询
     * 参数有一项为null值或空值时，返回单边条件语句.
     * 两个参数都为null值或空值时 返回 null.
     *
     * @param dateFrom
     * @param dateTo
     * @return
     */
    public static String generatDateRangeString(String dateFrom, String dateTo) {

        String str = "";
        boolean flag = false;

        if (!isEmptyOrNull(dateFrom)) {
            str += ":columnname>='" + dateFrom + "'";
            flag = true;
        }

        if (!isEmptyOrNull(dateTo)) {

            if (flag) {
                str += " and ";
            }
            if (dateTo.length() > 10)
                str += ":columnname<='" + dateTo + "'";
            else
                str += ":columnname<='" + getDateEnd(dateTo) + "'";
        }

        return emptyStringToNull(str);

    }

    /**
     * 根据日期的起始值生成取值范围
     * 用于SQL的数据库查询
     * 参数有一项为null值或空值时，返回单边条件语句.
     * 两个参数都为null值或空值时 返回 null.
     *
     * @param dateFrom
     * @param dateTo
     * @return
     */
    public static String generatDateRangeExactString(String dateFrom, String dateTo) {

        String str = "";
        boolean flag = false;

        if (!isEmptyOrNull(dateFrom)) {
            str += ":columnname>='" + dateFrom + "'";
            flag = true;
        }

        if (!isEmptyOrNull(dateTo)) {

            if (flag) {
                str += " and ";
            }
            str += ":columnname<='" + dateTo + "'";
        }

        return emptyStringToNull(str);

    }

    /**
     * 发送get请求获得结果
     *
     * @param url
     * @return
     */
    public static String sendGet(String url, Integer connectTimeout, Integer readTimeout) {

        String result = "";
        BufferedReader in = null;

        try {
            String urlName = url;
            URL realUrl = new URL(urlName);

            //打开和URL之间的连接
            HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();

            //设置通用的请求属性
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
            conn.setRequestProperty("Accept-Charset", "UTF-8");
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestProperty("version", "ems_track_cn_1.0");
            conn.setRequestProperty("authenticate", "szxhbtx_12kledu90sau");
            conn.setConnectTimeout(connectTimeout);
            conn.setReadTimeout(readTimeout);
            //建立实际的连接
            conn.connect();

            //获取所有响应头字段
            Map<String, List<String>> map = conn.getHeaderFields();

            //定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), "UTF-8"));

            String line;

            while ((line = in.readLine()) != null) {
                result += "\n" + line;
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        //使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    public static String sendGet(String url) {
        return sendGet(url, 10000, 10000);
    }

    public static String sendGet(String url, String charset) {

        String result = "";
        BufferedReader in = null;

        try {
            String urlName = url;
            URL realUrl = new URL(urlName);
            //打开和URL之间的连接
            HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();

            //设置通用的请求属性
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + charset);
            conn.setRequestProperty("Accept-Charset", charset);
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setConnectTimeout(10000);
            conn.setReadTimeout(10000);

            //建立实际的连接
            conn.connect();
            //获取所有响应头字段
            Map<String, List<String>> map = conn.getHeaderFields();
            //遍历所有的响应头字段
            for (String key : map.keySet()) {
            }
            //定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), charset));

            String line;

            while ((line = in.readLine()) != null) {
                result += "\n" + line;
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        //使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    public static String buildParamStr(HashMap<String, String> param) {
        String paramStr = "";
        Object[] keyArray = param.keySet().toArray();
        for (int i = 0; i < keyArray.length; i++) {
            String key = (String) keyArray[i];

            if (0 == i) {
                paramStr += (key + "=" + param.get(key));
            } else {
                paramStr += ("&" + key + "=" + param.get(key));
            }
        }

        return paramStr;
    }

    public static String getParamStr(HashMap<String, String> parames) {
        String str = "";
        try {
            str = URLEncoder.encode(StringUtil.buildParamStr(buildCompleteParams(parames)), "UTF-8")
                    .replace("%3A", ":")
                    .replace("%2F", "/")
                    .replace("%26", "&")
                    .replace("%3D", "=")
                    .replace("%3F", "?");
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }

        return str;
    }

    private static HashMap<String, String> buildCompleteParams(HashMap<String, String> parames) throws Exception {
        HashMap<String, String> commonParams = new HashMap<>();
        for (String key : parames.keySet()) {
            if (commonParams.containsKey(key)) {
                throw new Exception("参数名冲突");
            }
            commonParams.put(key, parames.get(key));
        }

        return commonParams;
    }

    public static String sendPost(String url, HashMap<String, String> param) {
        return sendPost(url, getParamStr(param));
    }

    public static String sendPost(String url, HashMap<String, String> param, Integer connectTimeout, Integer readTimeout) {
        return sendPost(url, getParamStr(param), connectTimeout, readTimeout, null);
    }

    public static String sendPost(String url, String param) {
        return sendPost(url, param, 10000, 20000, null);
    }

    public static String sendJSONPost(String url, String param) {
        return sendPost(url, param, 10000, 20000, "application/json");
    }

    /**
     * 发送post请求获得结果
     */
    public static String sendPost(String url, String param, Integer connectTimeout, Integer readTimeout, String contentType) {
        PrintWriter out = null;
        BufferedReader in = null;
        String sendContentType = "application/x-www-form-urlencoded";
        if (contentType != null) {
            sendContentType = contentType;
        }
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
            conn.setConnectTimeout(connectTimeout);
            conn.setReadTimeout(readTimeout);
            // 设置通用的请求属性
            conn.setRequestProperty("Content-Type", sendContentType + ";charset=utf-8");
            conn.setRequestProperty("Accept-Charset", "UTF-8");
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestMethod("POST");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(new OutputStreamWriter(conn.getOutputStream(), "UTF-8"));
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (ConnectException e) {
            LOGGER.error(e.getMessage(), e);

        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 根据数字的起始值生成取值范围
     * 用于SQL的数据库查询
     * 参数有一项为null值或空值时，返回单边条件语句.
     * 两个参数都为null值或空值时 返回 null.
     *
     * @param numberFrom
     * @param numberTo
     * @return
     */
    public static String generatNumberRangeString(String numberFrom, String numberTo) {

        String str = "";
        boolean flag = false;
        if (numberFrom == null)
            numberFrom = "0";
        if (numberTo == null)
            numberTo = "98";
        if (!isEmptyOrNull(numberFrom)) {
            str += ":columnname>=" + numberFrom;
            flag = true;
        }

        if (!isEmptyOrNull(numberTo)) {

            if (flag) {
                str += " and ";
            }
            str += ":columnname<=" + numberTo;
        }

        return emptyStringToNull(str);

    }

    /**
     * 根据文本的起始值生成取值范围
     * 用于SQL的数据库查询
     * 参数有一项为null值或空值时，返回单边条件语句.
     * 两个参数都为null值或空值时 返回 null.
     *
     * @param stringFrom
     * @param stringTo
     * @return
     */
    public static String generatStringRangeString(String stringFrom, String stringTo) {

        String str = "";
        boolean flag = false;

        if (!isEmptyOrNull(stringFrom)) {
            str += ":columnname>='" + stringFrom + "'";
            flag = true;
        }

        if (!isEmptyOrNull(stringTo)) {

            if (flag) {
                str += " and ";
            }
            str += ":columnname<='" + stringTo + "'";
        }

        return emptyStringToNull(str);

    }

    public static String generateStringInStrings(List<String> strList) {
        if (strList == null && strList.size() == 0)
            return null;

        StringBuilder sb = new StringBuilder();
        sb.append(":columnname IN (");
        boolean first = true;
        for (String str : strList) {
            if (first) {
                first = false;
                sb.append("'");
            } else {
                sb.append(",'");
            }
            sb.append(str);
            sb.append("'");
        }
        sb.append(")");
        return sb.toString();
    }

    /**
     * 获取日期的开始时间 输入日期格式应为 YYYY-MM-DD 或 YYYY-MM-DD HH:mm:ss
     * 返回日期为字符串类型 YYYY-MM-DD 00:00:00
     *
     * @param strDate
     * @return
     */
    public static String getDateStart(String strDate) {
        if (isEmptyOrNull(strDate)) {
            return strDate;
        }
        if (strDate.length() < 10) {
            return strDate;
        }
        return strDate.substring(0, 10) + " 00:00:00";
    }

    /**
     * 获取日期的结束时间 输入日期格式应为 YYYY-MM-DD 或 YYYY-MM-DD HH:mm:ss
     * 返回日期为字符串类型 YYYY-MM-DD 23:59:59
     *
     * @param strDate
     * @return
     */
    public static String getDateEnd(String strDate) {
        if (isEmptyOrNull(strDate)) {
            return strDate;
        }
        if (strDate.length() < 10) {
            return strDate;
        }
        return strDate.substring(0, 10) + " 23:59:59";
    }

    /**
     * 转义HTML字串在JSON解析中的问题
     *
     * @param s
     * @return
     */
    public static String jsonEncoding(String s) {
        if (s == null)
            return null;
        s = s.replaceAll("\"", "\\\\\"");
        s = s.replaceAll("\r\n", "\\\\r\\\\n");
        s = s.replaceAll("\t", "\\\\t");
        s = s.replaceAll("\b", "\\\\b");
        s = s.replaceAll("&quot;", "\"");

//      s=s.replaceAll("\\", "\\\\");		
        s = s.replaceAll("'", "\\\\\'");
//		System.out.println(s);
        return s;
    }

    /**
     * If date is null, return "-"
     *
     * @param date
     * @return
     */
    public static String dateToStandardDateStr(Date date) {
        if (date == null)
            return "-";
        SimpleDateFormat format = new SimpleDateFormat(STANDARD_DATE_FORMAT);
        return format.format(date);
    }

    /**
     * 转换Timestamp为标准STRING
     *
     * @param obj
     * @return
     * @throws ParseException
     */
    public static String timeStampToString(Object obj) throws ParseException {
        if (obj == null) {
            return null;
        }
        Timestamp timestamp = (Timestamp) obj;
        return dateToStandardDateStr(timestampToDate(timestamp));
    }

    /**
     * If ts is null, return "-"
     *
     * @param ts
     * @return
     */
    public static String tsToStandardDateStr(Timestamp ts) {
        if (ts == null)
            return "-";
        SimpleDateFormat format = new SimpleDateFormat(STANDARD_DATE_FORMAT);
        return format.format(ts);
    }

    /**
     * If ts is null, return "-"
     *
     * @param ts
     * @return
     */
    public static String tsToStandardDateStr2(Timestamp ts) {
        if (ts == null)
            return "-";
        SimpleDateFormat format = new SimpleDateFormat(STANDARD_DATE_FORMAT2);
        return format.format(ts);
    }

    public static Timestamp removeTimestampTimeInfo(Timestamp ts) throws ParseException {
        String s = tsToStandardDateStr(ts);
        Date date = StringUtil.strToDate(s);
        return new Timestamp(date.getTime());
    }

    public static Date timestampToDate(Timestamp ts) throws ParseException {
        String s = tsToStandardDateStr(ts);
        Date date = StringUtil.strToDate(s);
        return date;
    }

    public static Date trimToDate(Date d) throws ParseException {
        String s = dateToStandardDateStr(d);
        return StringUtil.strToDate(s);
    }


    public static String toStandardTimeStampStr(Date date) {
        if (date == null)
            return "-";
        SimpleDateFormat format = new SimpleDateFormat(STANDARD_DATE_FORMAT + " " + STANDARD_TIME_FORMAT);
        return format.format(date);
    }

    public static String toStandardTimeStampStr(Timestamp ts) {
        if (ts == null)
            return "-";
        SimpleDateFormat format = new SimpleDateFormat(STANDARD_DATE_FORMAT + " " + STANDARD_TIME_FORMAT);
        return format.format(ts);
    }

    /**
     * If ts == null, return "-"
     *
     * @param ts
     * @param formatStr
     * @return
     */
    public static String dateToStr(Date ts, String formatStr) {
        if (ts == null)
            return "-";
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        return format.format(ts);
    }

    /**
     * Format is "yyyy-MM-dd"
     *
     * @param s
     * @return
     * @throws ParseException
     */
    public static Timestamp strToSqlTimestamp(String s) throws ParseException {
        if (s == null || ("").equals(s)) {
            return null;
        }
        SimpleDateFormat format = new SimpleDateFormat(STANDARD_DATE_FORMAT);
        Date date = format.parse(s);
        return new Timestamp(date.getTime());
    }

    public static Timestamp strToSqlTimestamp(String s,String formatStr) throws ParseException {
        if (s == null || ("").equals(s)) {
            return null;
        }
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        Date date = format.parse(s);
        return new Timestamp(date.getTime());
    }

    public static Date strToDate(String s) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat(STANDARD_DATE_FORMAT);
        return format.parse(s);
    }

    public static Date strToDate(String s, String formatStr) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        return format.parse(s);
    }

    public static String dateToStr(Date date) {
        if (date == null)
            return "";
        SimpleDateFormat format = new SimpleDateFormat(STANDARD_DATE_FORMAT);
        return format.format(date);
    }

    public static String dateToFmtstr(Date date, String fmtStr) {
        if (date == null)
            return "";
        SimpleDateFormat format = new SimpleDateFormat(fmtStr);
        return format.format(date);
    }


    /**
     * Different system has diffent slash
     *
     * @return
     */
    public static String getSystemSlash() {
        String os = System.getenv("OS");
        String slash = "\\";
        if (os == null || !os.startsWith("Windows"))
            slash = "/";
        return slash;
    }

    /**
     * If s is null, just return null.<br>
     * Suitable for DA framework
     *
     * @param s
     * @return
     */
    public static Integer daStrToInteger(String s) {
        if (s == null)
            return null;
        return Integer.parseInt(s);
    }

    /**
     * If s is null, just return null.<br>
     *
     * @param s
     * @return
     */
    public static Double daStrToDouble(String s) {
        if (s == null)
            return null;
        return Double.parseDouble(s);
    }

    /**
     * If s is null, just return null<br>
     *
     * @param s
     * @return
     * @throws ParseException
     */
    public static Timestamp daStrToTimestamp(String s) throws ParseException {
        if (s == null)
            return null;
        return StringUtil.strToSqlTimestamp(s);
    }

    /**
     * If value longer than maxLength, value will be cut, and first part will be returned<br>
     * If value is null, null will be returned.<br>
     *
     * @param value
     * @param maxLength
     * @return
     */
    public static String daGetLimitedLengthStr(String value, int maxLength) {
        if (value == null)
            return null;
        if (maxLength > 0 && value.length() > maxLength)
            return value.substring(0, maxLength);
        return value;
    }

    /**
     * Return fixed length string, if value.len < len, use c as prefix
     *
     * @param value
     * @param len
     * @param c
     * @return
     */
    public static String daGetFixLengthStr(String value, int len, char c) {
        if (value == null)
            value = "";
        if (value.length() > len)
            return daGetLimitedLengthStr(value, len);
        String s = "";
        for (int i = 0; i < len - value.length(); i++)
            s += c;
        s += value;
        return s;
    }

    public static String fixLengthStrOnRight(String str, int len, String c) {
        if (str == null)
            str = "";
        if (str.length() > len)
            return str;

        for (; str.length() < len; ) {
            str += c;
        }
        return str;
    }


    public static String getRandomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < length; ++i) {
            int number = random.nextInt(62);//[0,62)

            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    public static String getRandomString2(int length) {
        Random random = new Random();

        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < length; ++i) {
            int number = random.nextInt(3);
            long result = 0;

            switch (number) {
                case 0:
                    result = Math.round(Math.random() * 25 + 65);
                    sb.append(String.valueOf((char) result));
                    break;
                case 1:
                    result = Math.round(Math.random() * 25 + 97);
                    sb.append(String.valueOf((char) result));
                    break;
                case 2:
                    sb.append(String.valueOf(new Random().nextInt(10)));
                    break;
            }
        }
        return sb.toString();
    }

    public static Integer doubleToInt(Double dval) {
        if (dval == null)
            return 0;
        return dval.intValue();
    }


    public static Double intToDouble(Integer ival) {
        if (ival == null)
            return 0.0;
        return ival.doubleValue();
    }

    public static String trimStr(String s) {
        if (s == null) return null;
        return s.trim();
    }

    public static String formatMoney(Double money, int floatNbr) {
        if (null == money) {
            money = 0.0;
        }
        return String.format("%3." + floatNbr + "f", money);
    }

    public static String formatMoney(Double money) {
        if (null == money) {
            money = 0.0;
        }
        return String.format("%3.2f", money);
    }

    public static String formatDouble(Double money) {
        return formatMoney(money);
    }

    public static String formatDouble(Double amount, int floatNbr) {
        if (null == amount) {
            amount = 0.0;
        }
        return String.format("%3." + floatNbr + "f", amount);
    }

    public static String formatQty(Object q) {
        Object value = q;
        if (q instanceof Double) {
            value = doubleToInt((Double) q);
        }
        return String.format("%d", value);
    }

    public static String formatPercent(Double q) {
        if (q == null)
            q = 0.0;
        return String.format("%3.2f%%", (q * 100));
    }


    /**
     * Cut the first n char of a string
     *
     * @param s
     * @param endPos
     * @return
     */
    public static String subString(String s, int endPos) {
        if (s == null)
            return null;

        if (s.length() > endPos && endPos > -1)
            return s.substring(0, endPos);
        return s;
    }

    public static String removeReturnLine(String s) {
        if (s == null)
            return null;
        String rtnChars = "\n";
        if (s.contains("\r\n")) {
            rtnChars = "\r\n";
        }
        String[] ss = s.split(rtnChars);
        String rtnStr = "";
        for (String str : ss) {
            rtnStr += str + " ";
        }
        return rtnStr.trim();
    }


    /**
     * 用替换掉所有的空格 回车符,换行符,和TAB符
     *
     * @param str
     * @return
     */
    public static String replaceByBlank(String str) {
        if (null == str) {
            return str;
        }

        return str.replaceAll("\\s", "");
    }

    public static String encodeHTML(String s) {
        if (s == null)
            return null;
//		return s;
        StringBuffer out = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c > 127 || c == '"' || c == '<' || c == '>' || c == '\'') {
                out.append("&#" + (int) c + ";");
            } else {
                out.append(c);
            }
        }
        return out.toString();
    }

    private static final char[] HEX_LOOKUP_STRING = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /**
     * 将字节数组转换为16进制字符串的形式.
     */
    public static String bytesToHexStr(byte[] bcd) {
        StringBuffer s = new StringBuffer(bcd.length * 2);

        for (byte aBcd : bcd) {
            s.append(HEX_LOOKUP_STRING[(aBcd >>> 4) & 0x0f]);
            s.append(HEX_LOOKUP_STRING[aBcd & 0x0f]);
        }

        return s.toString();
    }

    /**
     * 将16进制字符串还原为字节数组.
     */
    public static byte[] hexStrToBytes(String s) {
        byte[] bytes;

        bytes = new byte[s.length() / 2];

        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) Integer.parseInt(s.substring(2 * i, 2 * i + 2), 16);
        }

        return bytes;
    }

    public static String genInvnKey(String channelClientId, String whseCode, String skuCode) {
        return "INV_" + channelClientId + "~" + whseCode + "~" + skuCode;
    }

    public static String genInvnKey(String channelClientId, String whseCode, String skuCode, boolean lqFlag,
                                    Timestamp xpire_date) {
        if (lqFlag)
            return "LINV_" + channelClientId + "~" + whseCode + "~" + skuCode + "~" + tsToStandardDateStr2(xpire_date);
        else
            return "INV_" + channelClientId + "~" + whseCode + "~" + skuCode;
    }

    public static String genInvnKeyPattern(String channelClientId, String whseCode, String skuCode, Integer inventoryType, String xpireDate) {
        String cPattern;
        String wPattern;
        String skuPattern;
        String invPattern;
        String xdPattern;
        if (isEmptyOrNull(channelClientId)) {
            cPattern = "*";
        } else {
            cPattern = channelClientId;
        }
        if (isEmptyOrNull(whseCode)) {
            wPattern = "*";
        } else {
            wPattern = whseCode;
        }
        if (isEmptyOrNull(skuCode)) {
            skuPattern = "*";
        } else {
            skuPattern = skuCode;
        }
        if (isEmptyOrNull(xpireDate)) {
            xdPattern = "*";
        } else {
            xdPattern = "~" + xpireDate;
        }
        if (inventoryType == null) {
            invPattern = "*";
        } else if (inventoryType == 1) {
            invPattern = "";
        } else if (inventoryType == 10) {
            invPattern = "L";
        } else {
            invPattern = "*";
        }

        return invPattern + "INV_" + cPattern + "~" + wPattern + "~" + skuPattern + xdPattern;
    }

    public static RedisPatten getKeyInfo(String key) {
        String[] keyList = key.split("~");
        RedisPatten redisPatten = new RedisPatten();
        String typeAndClient = keyList[0];
        redisPatten.setChannelClientId(typeAndClient.split("_")[1]);
        redisPatten.setLqFlag(typeAndClient.substring(0, 1).equals("L"));
        redisPatten.setWhseCode(keyList[1]);
        redisPatten.setSkuCode(keyList[2]);
        if (keyList.length == 4) {
            redisPatten.setXpireDate(keyList[3]);
        }

        return redisPatten;
    }

    public static class RedisInvInfo {
        private RedisPatten redisPatten;
        private Integer value;

        public RedisPatten getRedisPatten() {
            return redisPatten;
        }

        public void setRedisPatten(RedisPatten redisPatten) {
            this.redisPatten = redisPatten;
        }

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }
    }

    public static class RedisPatten {
        private String channelClientId;
        private String whseCode;
        private String skuCode;
        private Boolean lqFlag;
        private String vdrCode;
        private String xpireDate;

        public String getChannelClientId() {
            return channelClientId;
        }

        public void setChannelClientId(String channelClientId) {
            this.channelClientId = channelClientId;
        }

        public String getWhseCode() {
            return whseCode;
        }

        public void setWhseCode(String whseCode) {
            this.whseCode = whseCode;
        }

        public String getSkuCode() {
            return skuCode;
        }

        public void setSkuCode(String skuCode) {
            this.skuCode = skuCode;
        }

        public String getVdrCode() {
            return vdrCode;
        }

        public void setVdrCode(String vdrCode) {
            this.vdrCode = vdrCode;
        }

        public Boolean getLqFlag() {
            return lqFlag;
        }

        public void setLqFlag(Boolean lqFlag) {
            this.lqFlag = lqFlag;
        }

        public String getXpireDate() {
            return xpireDate;
        }

        public void setXpireDate(String xpireDate) {
            this.xpireDate = xpireDate;
        }
    }

    public static String extractInvnChannelClientId(String key) {
        String[] ss = key.split("~");
        return ss[0].substring(4);
    }

    public static String extractInvnWhseCode(String key) {
        String[] ss = key.split("~");
        return ss[1];
    }

    public static String extractInvnSkuCode(String key) {
        String[] ss = key.split("~");
        return ss[2];
    }

    public static String handleSpecialChar(String sourcestr) {
        String result = sourcestr;
        if (isEmptyOrNull(result)) {
            return "";
        }
        result = result.replace("（", "(");
        result = result.replace("）", ")");
        result = result.replace("\"", "-");
        result = result.replace("\r", "");
        result = result.replace("\n", "");
        result = result.replace("&", "");
        result = result.replace("'", " ");
        result = result.replace("<", "(");
        result = result.replace(">", ")");
        return result;
    }

    public static String escapeJsonString(String text) {
        if (text == null) return "";

        return text.replace("\\", "\\\\").replace("'", "\\'").replace("\"", "\\\"");
    }

    /**
     * 比较两个对象是否为相等
     *
     * @param str1
     * @param str2
     * @return
     */

    public static boolean equals(Object str1, Object str2) {
        if (str1 == null)
            return str2 == null;
        return str1.equals(str2);
    }

    public static String join(Iterable<String> strings, String separator) {
        StringBuffer sb = new StringBuffer();
        boolean first = true;
        for (String str : strings) {
            if (first) {
                sb.append(str);
                first = false;
            } else {
                sb.append(separator);
                sb.append(str);
            }
        }
        return sb.toString();
    }

    public static String head(String text, int count) {
        if (text != null && text.length() > 2) {
            return text.substring(0, count);
        }

        return text;
    }

    public static boolean isFormatTime(String time) {
        String format = "\\d{2}[:]\\d{2}";
        Pattern pattern = Pattern.compile(format);
        Matcher matcher = pattern.matcher(time);
        return matcher.matches();
    }

    public static String quoteCommaSeparate(Collection<String> strings) {
        StringBuilder csq = new StringBuilder();
        boolean first = true;
        for (String str : strings) {
            if (first) {
                first = false;
            } else {
                csq.append(",");
            }

            csq.append("'" + str + "'");
        }
        return csq.toString();
    }

    public static String commaSeparate(Collection<String> strings) {
        StringBuilder csv = new StringBuilder();
        boolean first = true;
        for (String str : strings) {
            if (first) {
                first = false;
            } else {
                csv.append(",");
            }

            csv.append(str);
        }
        return csv.toString();
    }

    /**
     * @param info  匹配信息
     * @param regex 匹配规则
     * @return 是否匹配
     */
    public static boolean regexCheckString(String info, String regex) {
        Pattern p = Pattern.compile(regex);
        //进行匹配，并将匹配结果放在Matcher对象中
        Matcher m = p.matcher(info);
        return m.matches();
    }


    public static String validateString(String str) {
        return validateString(str, true);
    }

    public static String validateString(String str, Boolean all) {
        if (!isEmptyOrNull(str)) {
            Pattern p = Pattern.compile("\\s|\t|\r|\n");
            Matcher m = p.matcher(str);
            str = m.replaceAll("");

            if (all) {
                String regEx = "[`~!@#$%^&*()+=|{}':;,\\[\\].<>/?~！@#￥%……&*（）——+|{}\\\\【】‘；：”“’。，、？\'\"]";
                Pattern p1 = Pattern.compile(regEx);
                Matcher m1 = p1.matcher(str);
                return m1.replaceAll("").trim();
            } else {
                return str.trim();
            }
        }
        return null;
    }

    public static String genJDCtnTrackNbrPostFix(int ctnseq, int ctnqty) {
        return "-" + ctnseq + "-" + ctnqty + "-";
    }


    public static String genStarCharInStrMiddle(String str) {
        if (isEmptyOrNull(str))
            return "";
        int len = str.length();
        if (len > 5) {
            return str.substring(0, 2) + "******" + str.substring(len - 2, len);
        } else {
            if (len < 3) {
                return str + "******";
            } else {
                return str.substring(0, 2) + "******" + str.substring(len - 2, len);
            }
        }

    }

    public static String proccessPrintSpecialChar(String sour){
        if(StringUtil.isEmptyOrNull(sour)){
            return "";
        }
        return HtmlUtils.htmlEscape(StringUtil.nullToEmptyString(sour),"utf-8").replace("\"","");
    }

    /**
     * 处理发送给承运商信息中的特殊字符
     * @param sourcestr
     * @return
     */
    public static String handleCarrierSpecialChar(String sourcestr) {
        String result = sourcestr;
        if (isEmptyOrNull(result)) {
            return "";
        }
        result = result.replace("<", "(");
        result = result.replace(">", ")");
        result = result.replace("&", " ");
        return result;
    }

    /**
     * 处理字符串转换为数组 100,102 转换为[100,102]
     * @param sour
     * @return
     */
    public static Integer[] stringToIntegerArray(String sour){
        Integer[] result = null;
        if (isEmptyOrNull(sour)){
            return result;
        }
        String[] sourArray = sour.split(",");
        List<Integer> LString = new ArrayList<>();
        for (int i =0;i<sourArray.length;i++){
            LString.add(new Integer(sourArray[i]));
        }
        int size = LString.size();
        result = LString.toArray(new Integer[size]);
        return result;
    }
 }
