package com.jds.prodord.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 日付を扱うUtilクラスです。<BR>
 * <dt><b>作成日: </b></dt>
 * <dd>2005/05/10</dd>
 * <dt><B>作成者: </b></dt>
 * <dd>(NII)岡田 夏美</dd>
 * <dt><B>処理概要: </b></dt>
 * <dd>現在日付を取得し、指定されたパターンでフォーマットして返します。</dd>
 * <br>
 * <b>変更履歴</b><br>
 * <table border="1">
 * 	<tr>
 * 		<th width="80">修正日付</th><th width="100">修正者</th><th width="300">修正内容</th>
 * 	</tr>
 * 	<tr>
 * 		<td>2005/07/15</td>
 * 		<td>(NII)鈴木 禎</td>
 * 		<td>・数値型の現在日付を返すメソッドを追加</td>
 * 	</tr>
 * 	<tr>
 * 		<td>2006/04/18</td>
 * 		<td>(NII)岡田 夏美</td>
 * 		<td>・文字列型でも日付をセットできるように変更。<br>
 *          ・日付の指定されたフィールドに指定された値を加えるメソッド(addToDate)追加</td>
 * 	</tr>
 * </table>
 */
public class DateUtils {
	
	/** システム日付・時刻 */
	private Date date = new Date();

	/** フォーマットパターン : yyMMdd */
	public static final String PTN_DATE_6 = "yyMMdd";
	/** フォーマットパターン : yyyyMMdd */
	public static final String PTN_DATE_8 = "yyyyMMdd";
	/** フォーマットパターン : HHmmss */
	public static final String PTN_TIME_6 = "HHmmss";
	/** フォーマットパターン : yyMMddHHmmss */
	public static final String PTN_DATETIME_12 = "yyMMddHHmmss";
	/** 6桁日付が2000年以降か1900年代かの境界値 */
	public static final int DTE_BOUNDARY_VALUE = 800000;
	/** 日付最大値 */
	public static final int DTE_MAX_VALUE      = 999999;

	/**
	 * コンストラクタ
	 */
	public DateUtils() {
	}
	
	/**
	 * コンストラクタ
	 * @param date
	 */
	public DateUtils(Date date) {
		this.date = new Date(date.getTime());
	}

	/**
	 * コンストラクタ
	 * @param date
	 * @param pattern
	 */
	public DateUtils(String date, String pattern) {
		this.setDate(date, pattern);
	}
	
	/**
	 * コンストラクタ
	 * @param year(yy)
	 * @param month(MM)
	 * @param day(dd)
	 */
	public DateUtils(String year, String month, String day) {
		this.setDate(year, month, day);
	}

	//====================================================================	

	/**
	 * 日付をセットします。
	 * @param date
	 */
	public void setDate(Date date) {
		this.date = new Date(date.getTime());
	}
	
	/**
	 * 日付をセットします。
	 * @param date
	 * @param pattern
	 */
	public void setDate(String date, String pattern) {
		int year;
		int month;
		int day;
				
		if (pattern.equals(PTN_DATE_6) || pattern.equals(PTN_DATE_8)) {
			if (pattern.equals(PTN_DATE_6))
				date = convertYYYYMMDD(date);
			else
				date = StringUtils.lpad(date, PTN_DATE_8.length(), "0");
			year = Integer.parseInt(date.substring(2, 4));
			month = Integer.parseInt(date.substring(4, 6));
			day = Integer.parseInt(date.substring(6, 8));
		} else {
			year = 0;
			month = 0;
			day = 0;
		}
		this.setDate(year, month, day);
	}

	/**
	 * 日付をセットします。
	 * @param year(yy)
	 * @param month(1～12)
	 * @param day
	 */
	public void setDate(int year, int month, int day) {
		year = convertYYYY(year);
		month = getMonth(month);
		GregorianCalendar cal = new GregorianCalendar();
		cal.set(year, month, day);
		this.date = cal.getTime();
	}

	/**
	 * 日付をセットします。
	 * @param year(yy)
	 * @param month(1～12)
	 * @param day
	 */
	public void setDate(String year, String month, String day) {
		setDate(
			Integer.parseInt(year),
			Integer.parseInt(month),
			Integer.parseInt(day));
	}
	
	/**
	 * 日付を取得します。	
	 * @return
	 */
	public Date getDate() {
		return this.date;
	}
	
	/**
	 * 日付をシステム日付で初期化します。
	 */
	public void initDate() {
		this.date = new Date();
	}
	
	/**
	 * 日付を指定されたパターンでフォーマットして返します。
	 * @return
	 */
	public String getDate(String pattern) {
		return new SimpleDateFormat(pattern).format(this.date);
	}
	
	/**
	 * 日付をyyMMdd形式でフォーマットして返します。
	 * @return
	 */
	public String getDate6(){
		return getDate(PTN_DATE_6);
	}
	
	/**
	 * yyMMdd形式の日付をint型で返します。
	 * @return yyMMdd形式の日付
	 */
	public int getDate6Int() {
		return Integer.parseInt(getDate6());
	}

	/**
	 * 日付をyyyyMMdd形式でフォーマットして返します。
	 * @return
	 */
	public String getDate8(){
		return getDate(PTN_DATE_8);
	}

	/**
	 * yyyyMMdd形式の日付をint型で返します。
	 * @return yyyyMMdd形式の日付
	 */
	public int getDate8Int() {
		return Integer.parseInt(getDate8());
		
	}
	/**
	 * 日付をHHmmss形式でフォーマットして返します。
	 * @return
	 */
	public String getTime6(){
		return getDate(PTN_TIME_6);
	}

	/**
	 * HHmmss形式の日付をint型で返します。
	 * @return HHmmss形式の日付
	 */
	public int getTime6Int() {
		return Integer.parseInt(getTime6());
	}

	/**
	 * 日付をyyMMddHHmmss形式でフォーマットして返します。
	 * @return
	 */
	public String getDateTime12(){
		return getDate(PTN_DATETIME_12);
	}

	/**
	 * yyMMddHHmmss形式の日付をlong型で返します。
	 * @return yyMMddHHmmss形式の日付
	 */
	public long getDateTime12Long() {
		return Long.parseLong(getDateTime12());
	}
	
	/**
	 * 曜日を表す数値を返します。
	 * @return 曜日を表す数値
     * @see Calendar#SUNDAY
     * @see Calendar#MONDAY
     * @see Calendar#TUESDAY
     * @see Calendar#WEDNESDAY
     * @see Calendar#THURSDAY
     * @see Calendar#FRIDAY
     * @see Calendar#SATURDAY
	 */
	public int getDayOfWeek() {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(this.date);
		return cal.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * 日付の指定されたフィールドに、指定された値を加えます。
	 * @param field
	 * @param amount
	 * @see java.util.Calendar#add(int, int)
	 */
	public void addToDate(int field, int amount){
		
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(this.date);
		cal.add(field, amount);
		this.date = cal.getTime();
		
	}

	/**
	 * 渡された月(0～11月)に対応する月(1～12月)を返します。
	 * @param month
	 * @return 月(1～12月)
	 */
	private static int getMonth(int month) {
		if (month == 1) {
			return Calendar.JANUARY;
		} else if (month == 2) {
			return Calendar.FEBRUARY;
		} else if (month == 3) {
			return Calendar.MARCH;
		} else if (month == 4) {
			return Calendar.APRIL;
		} else if (month == 5) {
			return Calendar.MAY;
		} else if (month == 6) {
			return Calendar.JUNE;
		} else if (month == 7) {
			return Calendar.JULY;
		} else if (month == 8) {
			return Calendar.AUGUST;
		} else if (month == 9) {
			return Calendar.SEPTEMBER;
		} else if (month == 10) {
			return Calendar.OCTOBER;
		} else if (month == 11) {
			return Calendar.NOVEMBER;
		} else if (month == 12) {
			return Calendar.DECEMBER;
		} else {
			return month;
		}
	}
	
	//====================================================================

	/**
	 * 6桁の日付を8桁に変換します。(VZJ003)
	 * @param date   6桁の日付
	 * @return newDate   8桁の日付
	 */
	public static int convertYYYYMMDD(int date){
		
		int newDate = 0;
		
		if(date==0){
			newDate = 0;
		}else if(date >= 0 && date < DTE_BOUNDARY_VALUE){
			newDate = 20000000 + date;			
		}else if(date >= DTE_BOUNDARY_VALUE && date <= DTE_MAX_VALUE){
			newDate = 19000000 + date;
		}else{
			newDate = date;
		}

		return newDate;
	}
	
	/**
	 * 6桁の日付(String)を8桁に変換し、Stringで返します。(VZJ003)
	 * @param date   6桁の日付
	 * @return newDate   8桁の日付
	 */
	public static String convertYYYYMMDD(String dateStr){
		String newDate = "";
		try {
			int date = convertYYYYMMDD(Integer.parseInt(dateStr.trim()));
			newDate = String.valueOf(date);
		} catch (NumberFormatException e) {
			newDate = dateStr;
		}
		return newDate;
	}
	
	/**
	 * 渡された2桁年を4桁に変換して返します。
	 * @param year
	 * @return 年(yyyy)
	 */
	public static int convertYYYY(int year){
		int newYear = 0;
		int YEAR_BOUNDARY_VALUE = DTE_BOUNDARY_VALUE / 10000;
		int YEAR_MAX_VALUE = DTE_MAX_VALUE / 10000;
		try {
			if(year >= 0 && year < YEAR_BOUNDARY_VALUE){
				newYear = 2000 + year;			
			}else if(year >= YEAR_BOUNDARY_VALUE && year <= YEAR_MAX_VALUE){
				newYear = 1900 + year;
			}
		} catch (NumberFormatException e) {
			newYear = year;
		}
		return newYear;
	}
	
	/**
	 * 渡された2桁年を4桁に変換して返します。
	 * @param yearStr
	 * @return 年(yyyy)
	 */
	public static String convertYYYY(String yearStr){
		String newYear = "";
		try {
			int date = convertYYYY(Integer.parseInt(yearStr.trim()));
			newYear = String.valueOf(date);
		} catch (NumberFormatException e) {
			newYear = yearStr;
		}
		return newYear;
	}
		
	/**
	 * 日付型チェック(カレンダーの範囲)
	 * @param year(yy)
	 * @param month(MM)
	 * @param day(dd)
	 * @return boolean カレンダーの範囲内ならtrue、そうでないならfalse
	 */
	public static boolean validateAsDate(String year,String month,String day){
		String date = concatYYMMDD(year, month, day);
		return validateAsDate(convertYYYYMMDD(date), PTN_DATE_8);		
	}
	
	/**
	 * 日付型チェック(カレンダーの範囲)
	 * @param date 日付文字列
	 * @param pattern フォーマットパターン（DateUtils.PTN_DATE_6 または PTN_DATE_8）
	 * @return boolean カレンダーの範囲内ならtrue、そうでないならfalse
	 */
	public static boolean validateAsDate(String date, String pattern) {
		try {
			Integer.parseInt(date);//数値型に変換できるかチェック
			date = convertYYYYMMDD(date);
			SimpleDateFormat sdf = new SimpleDateFormat(PTN_DATE_8);
			sdf.setLenient(false);
			sdf.parse(date);//厳密な日付型にフォーマットできるかチェック
		} catch (NumberFormatException e) {
			return false;
		} catch (ParseException nfe) {
			return false;
		}
		return true;
	}
	
	/**
	 * 渡された年月日を連結して返します(月日はそれぞれ2桁にゼロ埋め)。
	 * @param year(yy)
	 * @param month(MM)
	 * @param day(dd)
	 * @return 連結後の文字列(yyMMdd)
	 */
	public static String concatYYMMDD(String year, String month, String day) {
		month = StringUtils.lpad(month, 2, "0");
		day = StringUtils.lpad(day, 2, "0");
		String date = year + month + day;
		return date;
	}

}
