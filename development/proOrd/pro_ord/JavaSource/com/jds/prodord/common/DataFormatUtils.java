package com.jds.prodord.common;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class DataFormatUtils {

	private DataFormatUtils(){
	}



/**
 *  ３桁毎にカンマを入れるメソッドです
 *  @param   _str  フォーマット前のデータ
 *  @return        ３桁区切りフォーマット済みのデータ
 */
	public static String setFormatString(String _str)
	{
	    String s;
	    long l;
  	  DecimalFormat _format;

   	 int idx = 1;
         if(_str == null){
     		 return " ";
    	 }else if(_str.equals("")){
   		   return " ";
   	 }else{
  		l = 0L;
  		s = "";
	    	if((idx = _str.indexOf(".")) == -1){
       			 l = Long.parseLong(_str);
        		_format = new DecimalFormat("###,###,###,###,##0");
    			s = _format.format(l);
      		}else{
        		String head = _str.substring(0,idx);
        		String tail = _str.substring(idx);
     		   	l = Long.parseLong(head);
      			_format = new DecimalFormat("###,###,###,###,##0");
     			s = _format.format(l) + tail;
  	        }
 		return s;
          }
	}

/**
 *  ３桁毎にカンマを入れるメソッドです
 *  @param   _l    フォーマット前のデータ
 *  @return        ３桁区切りフォーマット済みのデータ
 */
	public static String setFormatLong(long _l)
	{
	   	String s;
    		long l;
    		DecimalFormat _format;

		l = 0L;
		s = "";

		_format = new DecimalFormat("###,###,###,###,##0");
		s = _format.format(_l);
		return s;
	}

/**
 *  小数点のフォーマットをするメソッドです
 *  @param   _str  フォーマット前のデータ
 *  @return        小数点第１位までフォーマット済みのデータ
 */
	public static String setFormatfloat(float _f)
	{
		return String.valueOf(_f).substring(0,String.valueOf(_f).indexOf(".")+2);
	}

/**
 *  日付のフォーマットをするメソッドです
 *  @param   _str  フォーマット前のデータ
 *  @return        日付フォーマット済みのデータ
 */

	public static String setFormatYYMMDD(String s){
		
		if(s == null){
     		 return "";
    	}else if(s.equals("")){
   		   return "";
		}
		s = StringUtils.lpad(s,6,"0");
		return s.substring(0,2) + "/" + s.substring(2,4) + "/" + s.substring(4,6);
	}
	
/**
 *  "+"または" "が左端についていた場合、それを取り除くメソッドです
 *  @param   str  フォーマット前のデータ
 *  @return        左端から"+"または" "を取り除いたデータ
 */
	public static long parseLongUnmark(String str){
  	  if(str.substring(0,1).equals(" ") ||
   	    str.substring(0,1).equals("+")){
  	    str = str.substring(1);
  	  }
  	  return Long.parseLong(str);
 	}

/**
 *  省略記号から記号番号を作成するメソッドです
 *  @param   str  フォーマット前のデータ
 *  @return  記号番号
 */
	public static String mk_srykig(String kigBng){
		if(kigBng.indexOf(".") != -1){
			String syoryakuKigo = "";
			String put_zero = "";
			String head = "";
			String tail = "";
			int i = 0;
			for(i=0; i<=13-kigBng.length(); i++){
				put_zero = put_zero + "0";
			}
			head = kigBng.substring(0,kigBng.indexOf("."));
			tail = kigBng.substring(kigBng.indexOf(".")+1);
			head = head.concat(put_zero);
			syoryakuKigo = head.concat(tail);
			return syoryakuKigo;
		}else{
			return kigBng;
		}
	}
	
/**
 *  記号部の省略を補って返す
 *  @param   ArrayList arr  フォーマット前のデータ
 *  @return  記号番号arr
 */
	public static ArrayList supplement_kigBng(ArrayList arr){
		ArrayList returnArr = new ArrayList();
		String kigBng = "";
		String head = "";
		String tail = "";
		for(int i = 0; i < arr.size(); i++){
			kigBng = arr.get(i)+"";
			if(kigBng.indexOf(".") != -1){
				if(kigBng.indexOf(".") == 0){
					tail = kigBng.substring(kigBng.indexOf(".")+1);
					kigBng = head.concat(".").concat(tail);
				}else
					head = kigBng.substring(0,kigBng.indexOf("."));
			}
			returnArr.add(kigBng);
		}
		return returnArr;
	}

	/**
	 * 省略品番の編集（１品番ずつ）<br>
	 * getKigHeader(String kigBng, String header)と併用して使用します。<br>
	 * @param kigBng 記号番号
	 * @param header 記号部
	 * @return hinBan_r  変換後の記号番号
	 * @throws FinderException
	 */
	public static String editKigBng(String kigBng, String header)  {

		String hinBan_r = "";		

		//入力ﾃﾞｰﾀ＝省略品番の時
		if(kigBng.startsWith(".")){
				
			//記号番号+入力ﾃﾞｰﾀ
			hinBan_r = header + kigBng;
		
		}else{
			//入力ﾃﾞｰﾀ＝記号番号付品番の時
			hinBan_r = kigBng;
			
		}
		
		return mk_srykig(hinBan_r);	
		
	}


	/**
	 * 省略品番の編集メソッド(editHinBan(String,String))に渡す記号部の編集を行って返します。
	 * @param kigBng 記号番号
	 * @param header 保存しておいた記号部
	 * @return 記号部
	 */
	public static String getKigHeader(String kigBng, String header) {

		//"."を含まない時はそのまま返す
		if(kigBng.indexOf(".") < 0)
			return header;
		//"."から始まる時はそのまま返す
		if(kigBng.startsWith("."))
			return header;
		else{
		//入力ﾃﾞｰﾀ＝記号部付の時は記号部を取得する
			int i = kigBng.indexOf(".",0);
			header = kigBng.substring(0,i);
			return header;
		}
	}
	

	public static final String KYUHU = "旧譜";
	public static final String SINPU = "新譜";
	public static final String SAMPLE = "サンプル";
	public static final String DEMOBAN = "デモ盤";
	public static final String TOKHAN = "特販";	
	public static final String SONOTA = "その他";
	public static final String HICATALOG	= "非ｶﾀﾛｸﾞ";
/**
 *  新譜旧譜区分の表示文字列を返す 
 *  @param   sgn  区分(0～4)
 *  @return  区分の表示文字列
 * */
	public static String getKbnString(String sgn){
		String kbn = "";
		try{
			int _sgn = Integer.parseInt(sgn);
			switch(_sgn){
				case 0:
					kbn = KYUHU;
					break;
				case 1:
					kbn = SINPU;
					break;
				case 2:
					kbn = SAMPLE;
					break;
				case 3:
					kbn = DEMOBAN;
					break;
				case 4:
					kbn = TOKHAN;
					break;
				case 5:  //2004.03.08 add
					kbn = SONOTA;
					break;
				case 6:  //2005.06.24 add
					kbn = HICATALOG;
					break;
				default:
					kbn = sgn;
			}
		}catch(NumberFormatException e){
			return sgn;
		}
		return kbn;
	}
	
/**
 *  新譜旧譜区分のコードを返す 
 *  @param   sgn  区分(旧譜・新譜・サンプル・デモ盤・特販)
 *  @return  区分のコード
 * */
	public static String getKbnCod(String kbn){
		
		if(kbn.equals(""))
			return "";
		if(kbn.equals(KYUHU))
			return "0";
		else if(kbn.equals(SINPU))
			return "1";
		else if(kbn.equals(SAMPLE))
			return "2";
		else if(kbn.equals(DEMOBAN))
			return "3";
		else if(kbn.equals(TOKHAN))
			return "4";
		else if(kbn.equals(SONOTA)) //2004.03.08 add
			return "5";
		else if(kbn.equals(HICATALOG)) //2005.06.24 add
			return "6";
			
		return "";	
	}

	public static final String TAKASE = "タカセ";
	public static final String JARED = "ＪＡＲＥＤ";
	//2005/02/03 add
	/**
	 * 場所コードによって倉庫名を取得するメソッド
	 * @param bshCod
	 * @return 倉庫名
	 */
	public static String getSkoNm(String bshCod){

		String skoNm = "";
		//場所コード＝"C320"の場合
		if(bshCod.equals("C320"))
			skoNm = TAKASE;
		//場所コード＝"C400"の場合
		else if(bshCod.equals("C400"))
			skoNm = JARED;

		return skoNm;
	}

/**
 *  発注書番号のフォーマット用メソッド
 *  @param   String hacSyoBng
 *  @return  hacSyoBngを８桁になるまで左側をスペースで埋めて返す
 * */
	public static String formatHacSyoBng(String hacSyoBng){
		if(hacSyoBng == null || hacSyoBng.trim().equals(""))
			return "";
		
		return StringUtils.lpad(hacSyoBng.trim(),8," ");
	}
	
/**
 *  行番号のフォーマット用メソッド
 *  @param   String gyoBng
 *  @return  gyoBngを８桁になるまで左側をスペースで埋めて返す
 * */
	public static String formatGyoBng(String gyoBng){
		if(gyoBng == null || gyoBng.trim().equals(""))
			return "";
		
		return StringUtils.lpad(gyoBng.trim(),8," ");
	}
	
	/**
	 * 前回履歴テーブルに今回日付を加えて返します。
	 * MAX9回に達していた場合は、9番目にセットして返します。
	 * @param rrkTbl_old
	 * @param today
	 * @return 履歴テーブル
	 */
	public static String makeRrkTblStr(String rrkTbl_old, int today) {		
		//最大繰り返し回数
		final int MAX = 9;	
		//1回分のデータ長
		final int LENGTH = 4;
		//現在日付(16進)
		String today_hex = Integer.toHexString(today); 
		
		//今回の繰り返し回数取得
		int newSize = (rrkTbl_old.length() + today_hex.length()) / LENGTH;
		//MAX回まで履歴更新日を増やす
		String rrkTbl_new = "";
		if(newSize >= MAX)
			//９番目にセット
			rrkTbl_new = rrkTbl_old.substring(0, LENGTH * (MAX - 1)) + today_hex;		
		else
			rrkTbl_new = rrkTbl_old + today_hex;//一番最後にセット
	
		return rrkTbl_new;
	}
	
	/**
	 * 渡されたリストから空の文字列の要素を取り除いて返します。
	 * @param arr
	 * @return 空の文字列を取り除いたリスト
	 */
	public static ArrayList removeBlankElement(ArrayList arr){

		while(arr.contains("")){
			arr.remove(arr.indexOf(""));
		}
		return arr;
	}
	
	/**
	 * separatorが連続した場合のために、separatorの直前にスペースを挿入する
	 * @param txt String
	 * @param separator String
	 * @return separatorで区切られたToken間にスペースを挿入した文字列
	 */
	public static String insertSpace(String txt, String separator) {
		StringTokenizer line = new StringTokenizer(txt, separator, true);
		StringBuffer sb = new StringBuffer();
		while(line.hasMoreTokens()){
			String token = line.nextToken();
			if(token.equals(separator))
				sb.append(" ");
			sb.append(token);
		}
		return sb.toString();
	}
	
	/**
	 * 渡されたデータの行数を取得する
	 * @param txt String
	 * @return 渡されたデータの行数
	 */
	public static int getDataRowCount(String txt) {
		StringTokenizer line = new StringTokenizer(txt, "\n");
		int count;
		for(count = 0; line.hasMoreTokens(); count++){
			line.nextToken();
		}
		return count;
	}
	
	//TODO 会社追加対応(パターンの分岐追加)
	/**
	 * 発注書番号のフォーマット
	 * @param bng
	 * @param hacSyoPtn
	 * @return フォーマット済み発注書番号
	 */
	public static String makeHacSyoBng(String bng, String hacSyoPtn){
		String hacSyoBng = "";
		if (hacSyoPtn.equals(CommonConst.PTN01)
			|| hacSyoPtn.equals(CommonConst.PTN03)
			|| hacSyoPtn.equals(CommonConst.PTN04)
			|| hacSyoPtn.equals(CommonConst.PTN05))
			hacSyoBng = StringUtils.lpad(bng.trim(),6,"0");
			
		if(hacSyoPtn.equals(CommonConst.PTN02))
			hacSyoBng = StringUtils.lpad(bng.trim(),8,"0");
	
		return hacSyoBng;	
	}

	/**
	 * 直送区分を返します。<br>
	 * nhnMeiStrが %タカセ% または %ＪＡＲＥＤ% → 直送区分="0"<br>
	 * それ以外 → 直送区分="1"
	 * @param nhnMeiStr
	 * @param bshCod
	 * @return 直送区分
	 */
	public static String getCykKbn(String nhnMeiStr, String bshCod) {
		String nhnMei = getSkoNm(bshCod);
		String cykKbn = "";
		
		for(int i = 0; i < nhnMeiStr.length()-(nhnMei.length()-1); i++){
			if(nhnMeiStr.startsWith(nhnMei,i)){
				cykKbn = "0";
				return  cykKbn;
			}
		}
		cykKbn = "1";
		return  cykKbn;
	}

	/**
	 * サンプル区分の取得<br>
	 * 新旧区分がサンプル かつ 発売日が渡された日付以降だったら"S"<br>
	 * そうでなかったらブランク
	 * @param sinKyuKbn
	 * @param hbiDteStr yyMMdd
	 * @param dteStr yyMMdd
	 * @return サンプル区分
	 */
	public static String getSmpKbn(String sinKyuKbn, String hbiDteStr, String dteStr){
		if(sinKyuKbn.equals(getKbnCod(SAMPLE))){
			if(hbiDteStr.equals(""))
				return "";
			
			DateUtils hbiDte = new DateUtils(hbiDteStr, DateUtils.PTN_DATE_6);
			DateUtils dte = new DateUtils(dteStr, DateUtils.PTN_DATE_6);
			
			//発売日が渡された日付以降だったら
			if(dte.getDate8Int() < hbiDte.getDate8Int())
				return "S";
		}
		return "";
	}
	
}