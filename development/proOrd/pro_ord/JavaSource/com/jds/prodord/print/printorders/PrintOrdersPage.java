/**
* PrintOrdersPage  発注書印刷フォームクラス
*
*	作成日    2003/04/18
*	作成者    （ＮＩＩ）岡田 夏美
* 処理概要    Httpリクエストとリクエストの処理結果を格納するクラス。
*
*	[変更履歴]
*		2005/05/19（ＮＩＩ）蛭田
* 			・会社名漢字２(KAINMKJ2)、ユーザーＩＤの追加
* 		2005/08/29（ＮＩＩ）蛭田
* 			・更新履歴日・訂正回数の追加
*/

package com.jds.prodord.print.printorders;

import java.util.ArrayList;

import com.jds.prodord.common.DataFormatUtils;

public class PrintOrdersPage {

 	
	public PrintOrdersPage(){

	}
	
	public static final int DEFAULT_ROW_COUNT = 10;
	
	/**
     * 代表会社識別コード
     */
    private String queryDaiKaiSkbCod;
	
	/**
     * 会社識別コード
     */
    private String kaiSkbCod;
    
    
    /**
     * 会社郵便番号
     */
    private String kaiYbnNo;
    

    /**
     * 会社住所１
     */
    private String kaiAdr1Kj;
    

    /**
     * 会社住所２
     */
    private String kaiAdr2Kj;
    

    /**
     * 会社電話番号
     */
    private String kaiTelNo;
    

    /**
     * 会社FAX番号
     */
    private String kaiFaxBng;
    
    /**
     * 会社ロゴsrc
     */
    private String kaiLogoSrc;
    
    /**
     * 明細行
     */
    private ArrayList details = new ArrayList();
    
    /**
     * 改ページフラグ
     */
    private boolean pageBreak = true;//初期値：true
    
    /**
     * 副資材フラグ
     */
    private boolean fukSziFlg = false;
    
    /**
     * 現在時刻
     */
    private String currentDate;

	/**
	 * ユーザーＩＤ
	 */
	private String usrId;    
    
	//-----------------------------------------------------------見出し
	

    /**
     * 会社名
     */
    private String kaiNmKj;

    /**
     * 会社名２
     */
	private String kaiNmKj2;

    /**
     * 発注書日付
     */
    private String hacSyoDte;
    

    /**
     * 発注書番号
     */
    private String hacSyoBng;
    

    /**
     * 発注先コード
     */
    private String hacCod;
    

    /**
     * 発注先名称
     */
    private String sirHacNm;
    
	/**
	 * 更新履歴日1
	 */
	private String rrkTbl_1;
	/**
	 * 更新履歴日2
	 */
	private String rrkTbl_2;
	/**
	 * 更新履歴日3
	 */
	private String rrkTbl_3;
	/**
	 * 更新履歴日4
	 */
	private String rrkTbl_4;
	/**
	 * 更新履歴日5
	 */
	private String rrkTbl_5;
	
	/**
	 * 履歴更新日付
	 */
	private String rrkUpDte;
	/**
	 * 訂正回数
	 */
	private int teiNum;

    // ----------------------------------------------------------- Properties
    
    
    /**
     * 
     * 代表会社識別コード
     * 
     */
    public String getQueryDaiKaiSkbCod(){
    	return queryDaiKaiSkbCod;
    }
    public void setQueryDaiKaiSkbCod(String s){
    	queryDaiKaiSkbCod = s;
    }
    
    /**
     * 
     * 会社識別コードのセット
     * 
     * @param kaiSkbCod 会社識別コード
     */
    public void setKaiSkbCod(String kaiSkbCod) {

        this.kaiSkbCod = kaiSkbCod;

    }


    /**
     * 会社識別コードの取得
     * 
     * @return 会社識別コード
     */
    public String getKaiSkbCod() {

        return this.kaiSkbCod;

    }
    
    /**
     * 会社郵便番号のセット
     * 
     * @param kaiYbnNo 会社郵便番号
     */
    public void setKaiYbnNo(String kaiYbnNo) {

        this.kaiYbnNo = kaiYbnNo;

    }


    /**
     * 会社郵便番号の取得
     * 
     * @return 会社郵便番号
     */
    public String getKaiYbnNo() {

        return this.kaiYbnNo;

    }


    /**
     * 会社住所１のセット
     * 
     * @param kaiAdr1Kj 会社住所１
     */
    public void setKaiAdr1Kj(String kaiAdr1Kj) {

        this.kaiAdr1Kj = kaiAdr1Kj;

    }


    /**
     * 会社住所１の取得
     * 
     * @return 会社住所１
     */
    public String getKaiAdr1Kj() {

        return this.kaiAdr1Kj;

    }


    /**
     * 会社住所２のセット
     * 
     * @param kaiAdr2Kj 会社住所２
     */
    public void setKaiAdr2Kj(String kaiAdr2Kj) {

        this.kaiAdr2Kj = kaiAdr2Kj;

    }


    /**
     * 会社住所２の取得
     * 
     * @return 会社住所２
     */
    public String getKaiAdr2Kj() {

        return this.kaiAdr2Kj;

    }


    /**
     * 会社電話番号のセット
     * 
     * @param kaiTelNo 会社電話番号
     */
    public void setKaiTelNo(String kaiTelNo) {

        this.kaiTelNo = kaiTelNo;

    }


    /**
     * 会社電話番号の取得
     * 
     * @return 会社電話番号
     */
    public String getKaiTelNo() {

        return this.kaiTelNo;

    }


    /**
     * 会社FAX番号のセット
     * 
     * @param kaiFaxBng 会社FAX番号
     */
    public void setKaiFaxBng(String kaiFaxBng) {

        this.kaiFaxBng = kaiFaxBng;

    }


    /**
     * 会社FAX番号の取得
     * 
     * @return 会社FAX番号
     */
    public String getKaiFaxBng() {

        return this.kaiFaxBng;

    }

	/**
     * 
     * 会社ロゴsrcのセット
     * 
     * @param kaiLogoSrc 会社ロゴsrc
     */
    public void setKaiLogoSrc(String kaiLogoSrc) {
    	
        this.kaiLogoSrc = kaiLogoSrc;
    }


    /**
     * 会社ロゴsrcの取得
     * 
     * @return 会社ロゴsrc
     */
    public String getKaiLogoSrc() {
		String returnStr = this.kaiLogoSrc;
        return returnStr;

    }
	
	/**
     * 改ページフラグのセット
     * 
     * @param sort_ketCod 改ページフラグ
     */
    public void setPageBreak(boolean b) {

        this.pageBreak = b;

    }
    /**
     * 改ページフラグの取得
     * 
     * @return 改ページフラグ
     */
    public boolean getPageBreak() {

        return this.pageBreak;

    }
    
    /**
     * 副資材フラグのセット
     * 
     * @param fukSziFlg 副資材フラグ
     */
    public void setFukSziFlg(boolean b) {

        this.fukSziFlg = b;

    }
    /**
     * 副資材フラグの取得
     * 
     * @return 副資材フラグ
     */
    public boolean getFukSziFlg() {

        return this.fukSziFlg;

    }

    /**
     * 会社名のセット
     * 
     * @param kaiNmKj 会社名
     */
    public void setKaiNmKj(String kaiNmKj) {

        this.kaiNmKj = kaiNmKj;

    }


    /**
     * 会社名の取得
     * 
     * @return 会社名
     */
    public String getKaiNmKj() {

        return this.kaiNmKj;

    }


    /**
     * 発注書日付のセット
     * 
     * @param hacSyoDte 発注書日付
     */
    public void setHacSyoDte(String hacSyoDte) {

        this.hacSyoDte = hacSyoDte;

    }


    /**
     * 発注書日付の取得
     * 
     * @return 発注書日付
     */
    public String getHacSyoDte() {

        return this.hacSyoDte;

    }


    /**
     * 発注書番号のセット
     * 
     * @param hacSyoBng 発注書番号
     */
    public void setHacSyoBng(String hacSyoBng) {

        this.hacSyoBng = hacSyoBng;

    }


    /**
     * 発注書番号の取得
     * 
     * @return 発注書番号
     */
    public String getHacSyoBng() {

        return this.hacSyoBng;

    }


    /**
     * 発注先コードのセット
     * 
     * @param hacCod 発注先コード
     */
    public void setHacCod(String hacCod) {

        this.hacCod = hacCod;

    }


    /**
     * 発注先コードの取得
     * 
     * @return 発注先コード
     */
    public String getHacCod() {

        return this.hacCod;

    }


    /**
     * 発注先名称のセット
     * 
     * @param sirHacNm 発注先名称
     */
    public void setSirHacNm(String sirHacNm) {

        this.sirHacNm = sirHacNm;

    }


    /**
     * 発注先名称の取得
     * 
     * @return 発注先名称
     */
    public String getSirHacNm() {

        return this.sirHacNm;

    }

	/**
     * 現在日時のセット
     * 
     * @param sirHacNm 現在日時
     */
    public void setCurrentDate(String currentDate) {

        this.currentDate = currentDate;

    }


    /**
     * 現在日時の取得
     * 
     * @return 現在日時
     */
    public String getCurrentDate() {

        return this.currentDate;

    }

//2005/05/19 add
	/**
	 * 会社名漢字２の取得
	 * @return
	 */
	public String getKaiNmKj2() {
		return kaiNmKj2;
	}

	/**
	 * 会社名漢字２のセット
	 * @param string
	 */
	public void setKaiNmKj2(String string) {
		kaiNmKj2 = string;
	}

//2005/06/14 add
	/**
	 * ユーザーＩＤの取得
	 * @return
     */
    public String getUsrId() {
	    return usrId;
    }

    /**
     * ユーザーＩＤのセット
     * @param string
     */
    public void setUsrId(String string) {
	    usrId = string;
    }


//更新履歴日,訂正回数 2005/08/29 add

	/**
	 * 16進数の日付を10進数YY/MM/DDで返します。
	 */
	private String format_16(String str) {
		if (str == null || str.trim().equals(""))
			return "";
		int date16Int = Integer.parseInt(str, 16);
		str = DataFormatUtils.setFormatYYMMDD(date16Int + "");
		return str;
	}

	/**
	 * 16進数の日付を10進数YY/MM/DDでセットします。
	 * @param string
	 */
	public void setRrkTbl(
		String string1,
		String string2,
		String string3,
		String string4,
		String string5) {
			
		rrkTbl_1 = format_16(string1);
		rrkTbl_2 = format_16(string2);
		rrkTbl_3 = format_16(string3);
		rrkTbl_4 = format_16(string4);
		rrkTbl_5 = format_16(string5);
	}
	
	/**
	 * @return
	 */
	public String getRrkTbl_1() {
		return rrkTbl_1;
	}
	/**
	 * @param string
	 */
	public void setRrkTbl_1(String string) {
		rrkTbl_1 = string;
	}
	/**
	 * 
	 * @return
	 */
	public String getRrkTbl_2() {
	  return rrkTbl_2;
	}
	/**
	 * @param string
	 */
	public void setRrkTbl_2(String string) {
	  rrkTbl_2 = string;
	}
	/**
	 * @return
	 */
	public String getRrkTbl_3() {
	  return rrkTbl_3;
	}
	/**
	 * @param string
	 */
	public void setRrkTbl_3(String string) {
	  rrkTbl_3 = string;
	}
	/**
	 * @return
	 */
	public String getRrkTbl_4() {
	  return rrkTbl_4;
	}
	/**
	 * @param string
	 */
	public void setRrkTbl_4(String string) {
	  rrkTbl_4 = string;
	}
	/**
	 * @return
	 */
	public String getRrkTbl_5() {
		return rrkTbl_5;
	}
	/**
	 * @param string
	 */
	public void setRrkTbl_5(String string) {
		rrkTbl_5 = string;
	}

	/**
	 * @return
	 */
	public String getRrkUpDte() {
		return rrkUpDte;
	}

	/**
	 * @param string
	 */
	public void setRrkUpDte(String string) {
		rrkUpDte = string;
	}

	/**
	 * 訂正回数の取得
	 * @return
	 */
	public int getTeiNum() {
		return teiNum;
	}

	/**
	 * 訂正回数の設定
	 * @param i
	 */
	public void setTeiNum(int i) {
		teiNum = i;
	}


	//---------------------------------------------------------------------------------
	public int getSize(){
		return details.size();
	}
	
	public void setSize(int size){
		if(size > details.size()){
			int j = size - this.getSize();			
		  for(int k=0; k<j; k++){
		    details.add(new PrintOrdersRow());			  	
		  }						
		}else if(size < details.size()){
			for(int i = details.size();i > size;i--){
				details.remove((i-1));
			}
		}
	}
	public PrintOrdersRow getOrdersRow(int i){
		PrintOrdersRow or = (PrintOrdersRow)details.get(i);
		if(or == null){
			or = new PrintOrdersRow();
			details.set(i,or);
		}
		return or;
	}

	public void clearAll(){
		this.queryDaiKaiSkbCod = "";
		this.kaiSkbCod = "";
        this.kaiNmKj = "";
        this.hacSyoDte = "";
        this.hacSyoBng = "";
        this.hacCod = "";
        this.sirHacNm = "";
		this.kaiNmKj2=""; 	//2005/05/19 add
        this.usrId = ""; 	//2005/06/14 add
		this.rrkTbl_1 = ""; //2005/09/06 add
		this.rrkTbl_2 = "";
		this.rrkTbl_3 = "";
		this.rrkTbl_4 = "";
		this.rrkTbl_5 = "";
		this.rrkUpDte = "";
		this.teiNum = 0;

		removeAllRow();		
	}
	
	
	//行	
	public ArrayList getOrdersRowList(){
		return details;
	}
	
	public void setOrdersRowList(ArrayList arr){
		details = arr;
	}
	
	public void addOrdersRowList(PrintOrdersRow row){
		details.add(row);
	}
	
	public void removeAllRow(){
		details.clear();
	}
	

	//---------------------------------------------------------------------------------

	/**
	 * 会社情報をセットします。
	 * @param vo
	 */
	public void setKaiInfo(PrintOrdersVO vo) {

		setKaiNmKj(vo.getKaiNmKj());
		setKaiYbnNo(vo.getKaiYbnNo());
		setKaiAdr1Kj(vo.getKaiAdr1Kj());	
		setKaiAdr2Kj(vo.getKaiAdr2Kj());
		setKaiTelNo(vo.getKaiTelNo());	
		setKaiFaxBng(vo.getKaiFaxBng());
		setKaiLogoSrc(vo.getKaiLogoSrc());
		//2005/05/19 kaiNmKj2,queryDaiKaiSkbCod add
		setKaiNmKj2(vo.getKaiNmKj2());
		setQueryDaiKaiSkbCod(vo.getDaiKaiSkbCod());
		
	}	


}

