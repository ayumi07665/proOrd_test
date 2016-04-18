package com.jds.prodord.common;

import java.util.*;

public class StringUtils {

	private StringUtils(){
	}


	/**
	 * ������US-ASCII�����݂̂����ׂ郆�[�e�B���e�B���\�b�h
	 * @param s �����ΏƂ̕�����
	 * @return US-ASCII�����݂̂Ȃ� true�A�����łȂ��ꍇ�� false�B��������̕�����̂Ƃ���false�B
	 * @exception NullPointerException - ������null�̏ꍇ
	 */
	public static boolean isAscii(String s){
		if(s == null) 
			throw new java.lang.NullPointerException();
		if(s.equals(""))
			return false;	
		
		for(int i = 0;i < s.length();i++){
			if(!Character.UnicodeBlock.of(s.charAt(i)).equals(Character.UnicodeBlock.BASIC_LATIN))
				return false;
		}
		return true;
	}


	/**
	 * ������US-ASCII�̐��������݂̂����ׂ郆�[�e�B���e�B���\�b�h
	 * @param s �����ΏƂ̕�����
	 * @return US-ASCII�̐��������݂̂Ȃ� true�A�����łȂ��ꍇ�� false�B��������̕�����̂Ƃ���false�B
	 * @exception NullPointerException - ������null�̏ꍇ
	 */
	public static boolean isAsciiDigit(String s){
		if(s == null) 
			throw new java.lang.NullPointerException();
		if(s.equals(""))
			return false;	

		if(!isAscii(s))
			return false;
		for(int i = 0;i < s.length();i++){
			if(!Character.isDigit(s.charAt(i)))
				return false;
				
		}
		return true;
	}
	
	/**
	 * �����񒆂̕���������̒u�����s�����[�e�B���e�B���\�b�h
	 * @exception NullPointerException - ������null�̏ꍇ
	 */
	public static String replace(String target,String oldStr,String newStr){
		if(target == null || oldStr == null || newStr == null) 
			throw new java.lang.NullPointerException();

		int index = target.indexOf(oldStr);
		if(index < 0)
			return target;


		StringTokenizer st = new StringTokenizer(target,oldStr,true);
		StringBuffer sb = new StringBuffer();
		while(st.hasMoreTokens()){
			String token = st.nextToken();
			sb.append( token.equals(oldStr) ? newStr : token );
		}		
		return sb.toString();
	}



	
	/**
	 * ������US-ASCII or ���p�J�^�J�i�݂̂����ׂ郆�[�e�B���e�B���\�b�h
	 * @param s �����ΏƂ̕�����
	 * @return US-ASCII or ���p�J�^�J�i�݂̂Ȃ� true�A�����łȂ��ꍇ�� false�B��������̕�����̂Ƃ���false�B
	 * @exception NullPointerException - ������null�̏ꍇ
	 */
	public static boolean isAsciiOrHarfWidthKatakana(String s){
		if(s == null) 
			throw new java.lang.NullPointerException();
		if(s.equals(""))
			return false;	

		for(int i = 0;i < s.length();i++){
			if(isLowerCase(s.charAt(i))) //�������s��
				return false;
			
			if(Character.UnicodeBlock.of(s.charAt(i)).equals(Character.UnicodeBlock.BASIC_LATIN))
				continue;

			if(s.charAt(i) >= '\uff61' && s.charAt(i) <= '\uff9f') //Halfwidth Katakana variants or Halfwidth CJK punctuation 
				continue;

			return false;			
		}
		return true;
	}
	
	
	/**
	 * ������US-ASCII or ���p�J�^�J�i�܂܂�Ă��邩���ׂ郆�[�e�B���e�B���\�b�h
	 * @param s �����ΏƂ̕�����
	 * @return US-ASCII or ���p�J�^�J�i�܂܂�Ă���Ȃ� true�A�����łȂ��ꍇ�� false�B��������̕�����̂Ƃ���false�B
	 * @exception NullPointerException - ������null�̏ꍇ
	 */
	public static boolean containsAsciiOrHarfWidthKatakana(String s){
		if(s == null) 
			throw new java.lang.NullPointerException();
		if(s.equals(""))
			return false;	

		for(int i = 0;i < s.length();i++){
			if(Character.UnicodeBlock.of(s.charAt(i)).equals(Character.UnicodeBlock.BASIC_LATIN))
				return true;
			if(s.charAt(i) >= '\uff61' && s.charAt(i) <= '\uff9f')
				return true;

		}
		return false;			
	}
	
	
	/**
	 * ������E����w�肵����������菜�����[�e�B���e�B���\�b�h
	 * @param target �����ΏƂ̕�����
	 * @exception NullPointerException - ������null�̏ꍇ
	 */
    public static String removeSuffix(String target,String suffix){
    	if(target==null || suffix==null)
    	   throw new java.lang.NullPointerException();
    	if(target.equals("") || suffix.equals(""))
    	   return target;

        String str = target;
    	while(str.endsWith(suffix)){
    	   str = str.substring(0,str.length()-suffix.length());
    	}    		
   	   
    	return str;   	    
    }


	/**
	 * ������̉E�����߂��s�����[�e�B���e�B���\�b�h
	 * @param target �����ߑΏ�
	 * @param length �����ߌ�̕�����
	 * @param pad �ǉ�����镶��
	 * @return target�̌���pad���ǉ����ꂽ������B�ǉ����ꂽ���ʂ�length�ȏ�̒����ɂȂ�Ƃ��́A0����length�܂ł̕���������B
	 * @exception NullPointerException - ������null�̏ꍇ
	 * @exception IllegalArgumentException - length�����̏ꍇ
	 * @exception IllegalArgumentException - pad����̕�����̏ꍇ
	 */	
	public static String rpad(String target,int length,String pad){
		if(target == null || pad == null)		
			throw new java.lang.NullPointerException();
		if(length < 0)
			throw new java.lang.IllegalArgumentException("lenght is too small");
		if(pad.equals(""))
			throw new java.lang.IllegalArgumentException("pad has no character");
		
		StringBuffer sb = new StringBuffer(target);
		while(sb.length() < length){
			sb.append(pad);
		}		
		
		return sb.substring(0,length);	
	}
	
	
	/**
	 * ������̍������߂��s�����[�e�B���e�B���\�b�h
	 * @param target �����ߑΏ�
	 * @param length �����ߌ�̕�����
	 * @param pad �ǉ�����镶��
	 * @return target�̌���pad���ǉ����ꂽ������B�ǉ����ꂽ���ʂ�length�ȏ�̒����ɂȂ�Ƃ��́A(���ʂ̒���-length)����̕���������B
	 * @exception NullPointerException - ������null�̏ꍇ
	 * @exception IllegalArgumentException - length�����̏ꍇ
	 * @exception IllegalArgumentException - pad����̕�����̏ꍇ
	 */	
	public static String lpad(String target,int length,String pad){
		if(target == null || pad == null)		
			throw new java.lang.NullPointerException();
		if(length < 0)
			throw new java.lang.IllegalArgumentException("lenght is too small");
		if(pad.equals(""))
			throw new java.lang.IllegalArgumentException("pad has no character");

		StringBuffer sb = new StringBuffer(target);
		while(sb.length() < length){
			sb.insert(0,pad);
		}
		
		return sb.substring(sb.length() - length);	
	}
	
	/**
	 * �v���X(+)�}�C�i�X(-)�̕t����������̍������߂��s�����[�e�B���e�B���\�b�h�i�����͎����ߌ�A���[�ɒǉ��j
	 * @param target �����ߑΏ�
	 * @param length �����ߌ�̕�����
	 * @param pad �ǉ�����镶��
	 * @return target�̌���pad���ǉ����ꂽ������B�ǉ����ꂽ���ʂ�length�ȏ�̒����ɂȂ�Ƃ��́A(���ʂ̒���-length)����̕���������B
	 * @exception NullPointerException - ������null�̏ꍇ
	 * @exception IllegalArgumentException - length�����̏ꍇ
	 * @exception IllegalArgumentException - pad����̕�����̏ꍇ
	 */	
	public static String lpadPlusMinus(String target,int length,String pad){
		String decimalCode = "";
		if(target == null || pad == null)		
			throw new java.lang.NullPointerException();
		if(length < 0)
			throw new java.lang.IllegalArgumentException("lenght is too small");
		if(pad.equals(""))
			throw new java.lang.IllegalArgumentException("pad has no character");
			
		if(target.substring(0,1).equals("+") || target.substring(0,1).equals("-")){
			decimalCode = target.substring(0,1);
			target = target.substring(1);
		}

		StringBuffer sb = new StringBuffer(target);
		while(sb.length() < length - 1){
			sb.insert(0,pad);
		}
		if(!decimalCode.equals(""))
			sb.insert(0,decimalCode);
		else
			sb.insert(0,pad);

		return sb.substring(sb.length() - length);	
	}
	
	/**
	 * �w�肳�ꂽ��؂蕶���ŁA������̕������s���܂��B
	 * @param str �Ώە�����
	 * @param delim ��؂蕶����
	 * @return ������̕�����
	 */
	public static String[] split(String str, String delim) {
		final int delimLength = delim.length();
		int pos = 0;
		int index = 0;
		ArrayList list = new ArrayList();

		while((index = str.indexOf(delim, pos)) != -1) {
				list.add(str.substring(pos, index));
				pos = index + delimLength;
		}
		list.add(str.substring(pos));

		return (String[])list.toArray(new String[0]);
	}

	/**
	 * ���p�p���L����S�p�ɕϊ�����B
	 * @param str ���p�p���L�����܂ޕ�����
	 * @return ���p�p���L�����S�p�ɕϊ����ꂽ������
	 */
	public static String toFullANS(String str) {
		String ret = "";
		for (int i = 0; i < str.length(); i++) {
			int code = str.charAt(i);
			if ((code >= 0x21) && (code <= 0x7e)) {
				ret = ret + (char) (code + 0xfee0);
			} else {
				ret = ret + (char) code;
			}
		}
		return ret;
	}

	/**
	 * ���������_�^�Ó����`�F�b�N�Ə����_�ȉ��̌����`�F�b�N���s���܂��B<br>
	 * @param str �����Ώۂ̕�����
	 * @param position ����
	 * @return �Ó��ȏꍇ��true,�����łȂ��ꍇfalse��Ԃ��܂��B
	 */
	public static boolean isFloat(String str, int position) {
		
		boolean state = true;
		final char PERIOD = '.';
		
		//Float�^�Ƃ��Đ������Ȃ�������
		if (!isFloat(str)){
			state = false;
		}
		//�s���I�h�������񒆂ɑ��݂�����
		else if(str.indexOf(PERIOD) > 0){
			//�s���I�h�ȍ~�̌����`�F�b�N
			state = checkDecimalPlace(str,position);
		}

		return state;
	}


	/**
	 * ���������_�^�Ó����`�F�b�N���s���܂��B<br>
	 * @param str �����Ώۂ̕�����
	 * @return �Ó��ȏꍇ��true,�����łȂ��ꍇfalse��Ԃ��܂��B
	 */
	public static boolean isFloat(String str) {

		if (str == null || str.trim().equals(""))
			return false;

		boolean state = true;
		final char PERIOD = '.';
		try {
			Float f = new Float(str);
			state = isDecimalNumber(str);
		} catch (NumberFormatException e) {
			state = false; //Float�ɕϊ��ł��Ȃ������Ƃ���false
		}

		return state;
	}


	/**
	 * �n���ꂽ������̌`�����������ǂ����`�F�b�N���܂��B
	 * @param str �s���I�h���܂ޕ�����
	 * @return �Ó��ȏꍇ��true,�����łȂ��ꍇfalse��Ԃ��܂��B
	 */
	private static boolean isDecimalNumber(String str) {
		
		boolean state = true;	
		final char PERIOD = '.';
		if (str.indexOf(PERIOD) == 0)
			state = false; //�s���I�h�Ŏn�܂�Ƃ���false
		else if (str.indexOf(PERIOD) == str.length()-1)
			state = false; //�s���I�h�ŏI���Ƃ���false
		return state;
	}


	/**
	 * �����_�ȉ��̌����`�F�b�N���s���܂��B<br>
	 * @param str �����Ώۂ̕�����
	 * @param position ����
	 * @return �Ó��ȏꍇ��true,�����łȂ��ꍇfalse��Ԃ��܂��B
	 */
	private static boolean checkDecimalPlace(String str, int position){
		boolean state = true;
		final char PERIOD = '.';

		for(int i=0; i < str.length(); i++){
			if(str.charAt(i) == PERIOD){
				String wkStr = str.substring(i+1,str.length());
				if(wkStr.length() > position){
					state = false;
				}
				break;				
			}
		}
		return state;
	}

	/**
	 * US-ASCII�̏������Ȃ��true �����łȂ���� false ��Ԃ��܂��B
	 * @param c
	 * @return boolean
	 */
	private static boolean isLowerCase(char c) {

		return c >= '\u0061' && c <= '\u007A';
	}
	
	/**
	 * �l��null�܂��̓u�����N���ǂ�����Ԃ��B
	 * @param value �`�F�b�N�Ώۂ̒l
	 * @return �l��null�܂��̓u�����N�Ȃ�true�A�����łȂ��Ȃ�false
	 */
	public static boolean isBlankOrNull(String value) {
		return ((value == null) || (value.trim().length() == 0));
	}

}

