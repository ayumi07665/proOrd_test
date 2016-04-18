package com.jds.prodord.common;

import java.util.*;

public class StringUtils {

	private StringUtils(){
	}


	/**
	 * 文字列がUS-ASCII文字のみか調べるユーティリティメソッド
	 * @param s 検査対照の文字列
	 * @return US-ASCII文字のみなら true、そうでない場合は false。引数が空の文字列のときはfalse。
	 * @exception NullPointerException - 引数がnullの場合
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
	 * 文字列がUS-ASCIIの数字文字のみか調べるユーティリティメソッド
	 * @param s 検査対照の文字列
	 * @return US-ASCIIの数字文字のみなら true、そうでない場合は false。引数が空の文字列のときはfalse。
	 * @exception NullPointerException - 引数がnullの場合
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
	 * 文字列中の部分文字列の置換を行うユーティリティメソッド
	 * @exception NullPointerException - 引数がnullの場合
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
	 * 文字列がUS-ASCII or 半角カタカナのみか調べるユーティリティメソッド
	 * @param s 検査対照の文字列
	 * @return US-ASCII or 半角カタカナのみなら true、そうでない場合は false。引数が空の文字列のときはfalse。
	 * @exception NullPointerException - 引数がnullの場合
	 */
	public static boolean isAsciiOrHarfWidthKatakana(String s){
		if(s == null) 
			throw new java.lang.NullPointerException();
		if(s.equals(""))
			return false;	

		for(int i = 0;i < s.length();i++){
			if(isLowerCase(s.charAt(i))) //小文字不可
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
	 * 文字列がUS-ASCII or 半角カタカナ含まれているか調べるユーティリティメソッド
	 * @param s 検査対照の文字列
	 * @return US-ASCII or 半角カタカナ含まれているなら true、そうでない場合は false。引数が空の文字列のときはfalse。
	 * @exception NullPointerException - 引数がnullの場合
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
	 * 文字列右から指定した文字を取り除くユーティリティメソッド
	 * @param target 検査対照の文字列
	 * @exception NullPointerException - 引数がnullの場合
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
	 * 文字列の右字埋めを行うユーティリティメソッド
	 * @param target 字埋め対象
	 * @param length 字埋め後の文字列長
	 * @param pad 追加される文字
	 * @return targetの後ろにpadが追加された文字列。追加された結果がlength以上の長さになるときは、0からlengthまでの部分文字列。
	 * @exception NullPointerException - 引数がnullの場合
	 * @exception IllegalArgumentException - lengthが負の場合
	 * @exception IllegalArgumentException - padが空の文字列の場合
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
	 * 文字列の左字埋めを行うユーティリティメソッド
	 * @param target 字埋め対象
	 * @param length 字埋め後の文字列長
	 * @param pad 追加される文字
	 * @return targetの後ろにpadが追加された文字列。追加された結果がlength以上の長さになるときは、(結果の長さ-length)からの部分文字列。
	 * @exception NullPointerException - 引数がnullの場合
	 * @exception IllegalArgumentException - lengthが負の場合
	 * @exception IllegalArgumentException - padが空の文字列の場合
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
	 * プラス(+)マイナス(-)の付いた文字列の左字埋めを行うユーティリティメソッド（符号は字埋め後、左端に追加）
	 * @param target 字埋め対象
	 * @param length 字埋め後の文字列長
	 * @param pad 追加される文字
	 * @return targetの後ろにpadが追加された文字列。追加された結果がlength以上の長さになるときは、(結果の長さ-length)からの部分文字列。
	 * @exception NullPointerException - 引数がnullの場合
	 * @exception IllegalArgumentException - lengthが負の場合
	 * @exception IllegalArgumentException - padが空の文字列の場合
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
	 * 指定された区切り文字で、文字列の分割を行います。
	 * @param str 対象文字列
	 * @param delim 区切り文字列
	 * @return 分割後の文字列
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
	 * 半角英数記号を全角に変換する。
	 * @param str 半角英数記号を含む文字列
	 * @return 半角英数記号が全角に変換された文字列
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
	 * 浮動小数点型妥当性チェックと小数点以下の桁数チェックを行います。<br>
	 * @param str 検査対象の文字列
	 * @param position 桁数
	 * @return 妥当な場合はtrue,そうでない場合falseを返します。
	 */
	public static boolean isFloat(String str, int position) {
		
		boolean state = true;
		final char PERIOD = '.';
		
		//Float型として正しくなかったら
		if (!isFloat(str)){
			state = false;
		}
		//ピリオドが文字列中に存在したら
		else if(str.indexOf(PERIOD) > 0){
			//ピリオド以降の桁数チェック
			state = checkDecimalPlace(str,position);
		}

		return state;
	}


	/**
	 * 浮動小数点型妥当性チェックを行います。<br>
	 * @param str 検査対象の文字列
	 * @return 妥当な場合はtrue,そうでない場合falseを返します。
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
			state = false; //Floatに変換できなかったときはfalse
		}

		return state;
	}


	/**
	 * 渡された文字列の形式が小数かどうかチェックします。
	 * @param str ピリオドを含む文字列
	 * @return 妥当な場合はtrue,そうでない場合falseを返します。
	 */
	private static boolean isDecimalNumber(String str) {
		
		boolean state = true;	
		final char PERIOD = '.';
		if (str.indexOf(PERIOD) == 0)
			state = false; //ピリオドで始まるときはfalse
		else if (str.indexOf(PERIOD) == str.length()-1)
			state = false; //ピリオドで終わるときはfalse
		return state;
	}


	/**
	 * 小数点以下の桁数チェックを行います。<br>
	 * @param str 検査対象の文字列
	 * @param position 桁数
	 * @return 妥当な場合はtrue,そうでない場合falseを返します。
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
	 * US-ASCIIの小文字ならばtrue そうでなければ false を返します。
	 * @param c
	 * @return boolean
	 */
	private static boolean isLowerCase(char c) {

		return c >= '\u0061' && c <= '\u007A';
	}
	
	/**
	 * 値がnullまたはブランクかどうかを返す。
	 * @param value チェック対象の値
	 * @return 値がnullまたはブランクならtrue、そうでないならfalse
	 */
	public static boolean isBlankOrNull(String value) {
		return ((value == null) || (value.trim().length() == 0));
	}

}

