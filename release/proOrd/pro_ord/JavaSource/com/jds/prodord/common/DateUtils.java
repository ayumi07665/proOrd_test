package com.jds.prodord.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * ���t������Util�N���X�ł��B<BR>
 * <dt><b>�쐬��: </b></dt>
 * <dd>2005/05/10</dd>
 * <dt><B>�쐬��: </b></dt>
 * <dd>(NII)���c �Ĕ�</dd>
 * <dt><B>�����T�v: </b></dt>
 * <dd>���ݓ��t���擾���A�w�肳�ꂽ�p�^�[���Ńt�H�[�}�b�g���ĕԂ��܂��B</dd>
 * <br>
 * <b>�ύX����</b><br>
 * <table border="1">
 * 	<tr>
 * 		<th width="80">�C�����t</th><th width="100">�C����</th><th width="300">�C�����e</th>
 * 	</tr>
 * 	<tr>
 * 		<td>2005/07/15</td>
 * 		<td>(NII)��� ��</td>
 * 		<td>�E���l�^�̌��ݓ��t��Ԃ����\�b�h��ǉ�</td>
 * 	</tr>
 * 	<tr>
 * 		<td>2006/04/18</td>
 * 		<td>(NII)���c �Ĕ�</td>
 * 		<td>�E������^�ł����t���Z�b�g�ł���悤�ɕύX�B<br>
 *          �E���t�̎w�肳�ꂽ�t�B�[���h�Ɏw�肳�ꂽ�l�������郁�\�b�h(addToDate)�ǉ�</td>
 * 	</tr>
 * </table>
 */
public class DateUtils {
	
	/** �V�X�e�����t�E���� */
	private Date date = new Date();

	/** �t�H�[�}�b�g�p�^�[�� : yyMMdd */
	public static final String PTN_DATE_6 = "yyMMdd";
	/** �t�H�[�}�b�g�p�^�[�� : yyyyMMdd */
	public static final String PTN_DATE_8 = "yyyyMMdd";
	/** �t�H�[�}�b�g�p�^�[�� : HHmmss */
	public static final String PTN_TIME_6 = "HHmmss";
	/** �t�H�[�}�b�g�p�^�[�� : yyMMddHHmmss */
	public static final String PTN_DATETIME_12 = "yyMMddHHmmss";
	/** 6�����t��2000�N�ȍ~��1900�N�ォ�̋��E�l */
	public static final int DTE_BOUNDARY_VALUE = 800000;
	/** ���t�ő�l */
	public static final int DTE_MAX_VALUE      = 999999;

	/**
	 * �R���X�g���N�^
	 */
	public DateUtils() {
	}
	
	/**
	 * �R���X�g���N�^
	 * @param date
	 */
	public DateUtils(Date date) {
		this.date = new Date(date.getTime());
	}

	/**
	 * �R���X�g���N�^
	 * @param date
	 * @param pattern
	 */
	public DateUtils(String date, String pattern) {
		this.setDate(date, pattern);
	}
	
	/**
	 * �R���X�g���N�^
	 * @param year(yy)
	 * @param month(MM)
	 * @param day(dd)
	 */
	public DateUtils(String year, String month, String day) {
		this.setDate(year, month, day);
	}

	//====================================================================	

	/**
	 * ���t���Z�b�g���܂��B
	 * @param date
	 */
	public void setDate(Date date) {
		this.date = new Date(date.getTime());
	}
	
	/**
	 * ���t���Z�b�g���܂��B
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
	 * ���t���Z�b�g���܂��B
	 * @param year(yy)
	 * @param month(1�`12)
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
	 * ���t���Z�b�g���܂��B
	 * @param year(yy)
	 * @param month(1�`12)
	 * @param day
	 */
	public void setDate(String year, String month, String day) {
		setDate(
			Integer.parseInt(year),
			Integer.parseInt(month),
			Integer.parseInt(day));
	}
	
	/**
	 * ���t���擾���܂��B	
	 * @return
	 */
	public Date getDate() {
		return this.date;
	}
	
	/**
	 * ���t���V�X�e�����t�ŏ��������܂��B
	 */
	public void initDate() {
		this.date = new Date();
	}
	
	/**
	 * ���t���w�肳�ꂽ�p�^�[���Ńt�H�[�}�b�g���ĕԂ��܂��B
	 * @return
	 */
	public String getDate(String pattern) {
		return new SimpleDateFormat(pattern).format(this.date);
	}
	
	/**
	 * ���t��yyMMdd�`���Ńt�H�[�}�b�g���ĕԂ��܂��B
	 * @return
	 */
	public String getDate6(){
		return getDate(PTN_DATE_6);
	}
	
	/**
	 * yyMMdd�`���̓��t��int�^�ŕԂ��܂��B
	 * @return yyMMdd�`���̓��t
	 */
	public int getDate6Int() {
		return Integer.parseInt(getDate6());
	}

	/**
	 * ���t��yyyyMMdd�`���Ńt�H�[�}�b�g���ĕԂ��܂��B
	 * @return
	 */
	public String getDate8(){
		return getDate(PTN_DATE_8);
	}

	/**
	 * yyyyMMdd�`���̓��t��int�^�ŕԂ��܂��B
	 * @return yyyyMMdd�`���̓��t
	 */
	public int getDate8Int() {
		return Integer.parseInt(getDate8());
		
	}
	/**
	 * ���t��HHmmss�`���Ńt�H�[�}�b�g���ĕԂ��܂��B
	 * @return
	 */
	public String getTime6(){
		return getDate(PTN_TIME_6);
	}

	/**
	 * HHmmss�`���̓��t��int�^�ŕԂ��܂��B
	 * @return HHmmss�`���̓��t
	 */
	public int getTime6Int() {
		return Integer.parseInt(getTime6());
	}

	/**
	 * ���t��yyMMddHHmmss�`���Ńt�H�[�}�b�g���ĕԂ��܂��B
	 * @return
	 */
	public String getDateTime12(){
		return getDate(PTN_DATETIME_12);
	}

	/**
	 * yyMMddHHmmss�`���̓��t��long�^�ŕԂ��܂��B
	 * @return yyMMddHHmmss�`���̓��t
	 */
	public long getDateTime12Long() {
		return Long.parseLong(getDateTime12());
	}
	
	/**
	 * �j����\�����l��Ԃ��܂��B
	 * @return �j����\�����l
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
	 * ���t�̎w�肳�ꂽ�t�B�[���h�ɁA�w�肳�ꂽ�l�������܂��B
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
	 * �n���ꂽ��(0�`11��)�ɑΉ����錎(1�`12��)��Ԃ��܂��B
	 * @param month
	 * @return ��(1�`12��)
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
	 * 6���̓��t��8���ɕϊ����܂��B(VZJ003)
	 * @param date   6���̓��t
	 * @return newDate   8���̓��t
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
	 * 6���̓��t(String)��8���ɕϊ����AString�ŕԂ��܂��B(VZJ003)
	 * @param date   6���̓��t
	 * @return newDate   8���̓��t
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
	 * �n���ꂽ2���N��4���ɕϊ����ĕԂ��܂��B
	 * @param year
	 * @return �N(yyyy)
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
	 * �n���ꂽ2���N��4���ɕϊ����ĕԂ��܂��B
	 * @param yearStr
	 * @return �N(yyyy)
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
	 * ���t�^�`�F�b�N(�J�����_�[�͈̔�)
	 * @param year(yy)
	 * @param month(MM)
	 * @param day(dd)
	 * @return boolean �J�����_�[�͈͓̔��Ȃ�true�A�����łȂ��Ȃ�false
	 */
	public static boolean validateAsDate(String year,String month,String day){
		String date = concatYYMMDD(year, month, day);
		return validateAsDate(convertYYYYMMDD(date), PTN_DATE_8);		
	}
	
	/**
	 * ���t�^�`�F�b�N(�J�����_�[�͈̔�)
	 * @param date ���t������
	 * @param pattern �t�H�[�}�b�g�p�^�[���iDateUtils.PTN_DATE_6 �܂��� PTN_DATE_8�j
	 * @return boolean �J�����_�[�͈͓̔��Ȃ�true�A�����łȂ��Ȃ�false
	 */
	public static boolean validateAsDate(String date, String pattern) {
		try {
			Integer.parseInt(date);//���l�^�ɕϊ��ł��邩�`�F�b�N
			date = convertYYYYMMDD(date);
			SimpleDateFormat sdf = new SimpleDateFormat(PTN_DATE_8);
			sdf.setLenient(false);
			sdf.parse(date);//�����ȓ��t�^�Ƀt�H�[�}�b�g�ł��邩�`�F�b�N
		} catch (NumberFormatException e) {
			return false;
		} catch (ParseException nfe) {
			return false;
		}
		return true;
	}
	
	/**
	 * �n���ꂽ�N������A�����ĕԂ��܂�(�����͂��ꂼ��2���Ƀ[������)�B
	 * @param year(yy)
	 * @param month(MM)
	 * @param day(dd)
	 * @return �A����̕�����(yyMMdd)
	 */
	public static String concatYYMMDD(String year, String month, String day) {
		month = StringUtils.lpad(month, 2, "0");
		day = StringUtils.lpad(day, 2, "0");
		String date = year + month + day;
		return date;
	}

}
