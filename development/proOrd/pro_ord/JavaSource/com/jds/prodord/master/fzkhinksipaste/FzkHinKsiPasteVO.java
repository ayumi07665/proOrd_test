package com.jds.prodord.master.fzkhinksipaste;

import java.util.*;
/**
* FzkHinKsiPasteVO  付属品構成マスター一括貼り付けバリューオブジェクトクラス
*
*	作成日    2007/09/30
*	作成者    （ＮＩＩ）田中
* 処理概要    画面から取得したデータを格納するクラス。
*
*	[変更履歴]
*/

public class FzkHinKsiPasteVO {

	private String command = "";

	private String daiKaiSkbCod = "";
	private String queryKaiSkbCod = "";
	private ArrayList queryKaiSkbCodList = null;
	
	private String kaiskbcod = "";
	private String kigbng = "";
	private String fukszicod = "";
	private String kbn = "";
	private String prsFks = "";
	private String cpPaste =""; 
	private String bunCodString = new String();

	private LinkedList formRows = new LinkedList();  

	private String fuksziCod08String = "";
	private String sirSk08String = "";

	/**
	 * 品番マスター(FTBHIN01)存在フラグ
	 */
	private boolean exsitHin01 = false;
	
	/**
	 * 副資材マスター(FTBFUK01)存在フラグ
	 */
	private boolean exsitFuk01 = false; 


	
	/**
	 * コマンドのセット
	 * 
	 * @param command コマンド
	 */
	public void setCommand(String command) {

		this.command = command;

	}


	/**
	 * コマンドの取得
	 * 
	 * @return コマンド
	 */
	public String getCommand() {

		return this.command;

	}

	/**
	 * ユーザーの代表会社識別コードのセット
	 * 
	 * @param daiKaiSkbCod ユーザーの表会社識別コード
	 */
	public void setDaiKaiSkbCod(String daiKaiSkbCod) {

		this.daiKaiSkbCod = daiKaiSkbCod;

	}


	/**
	 * ユーザーの代表会社識別コードの取得
	 * 
	 * @return ユーザーの代表会社識別コード
	 */
	public String getDaiKaiSkbCod() {

		return this.daiKaiSkbCod;

	}
    
	/**
	 * ユーザーの会社識別コードのセット
	 * 
	 * @param kaiSkbCod ユーザーの会社識別コード
	 */
	public void setQueryKaiSkbCod(String queryKaiSkbCod) {

		this.queryKaiSkbCod = queryKaiSkbCod;

	}


	/**
	 * ユーザーの会社識別コードの取得
	 * 
	 * @return ユーザーの会社識別コード
	 */
	public String getQueryKaiSkbCod() {

		return this.queryKaiSkbCod;

	}
    

//--------------------------------------------------------------------------

  public ArrayList getBunCodArr(){
	  ArrayList bunCod = new ArrayList();
	  for(int i = 0; i<bunCodString.length(); i+=1){
		  try{
			  bunCod.add(bunCodString.substring(i, i+1));
		  }catch(IndexOutOfBoundsException e){
			  break;
		  }
	  }
	  return bunCod;
  }
  public void addBunCod(String bunCod){
	  bunCodString += bunCod;
  }
  public void clearBunCod(){
	  bunCodString = "";
  }

    
  /**
   * 会社識別コードのセット
   * 
   * @param kaiskbcod 会社識別コード
   */
  public void setKaiSkbCod(String kaiskbcod) {

	  this.kaiskbcod = kaiskbcod;

  }


  /**
   * 会社識別コードの取得
   * 
   * @return 会社識別コード
   */
  public String getKaiSkbCod() {

	  return this.kaiskbcod;

  }

  /**
   * 記号番号のセット
   * 
   * @param kigbng 記号番号
   */
  public void setKigBng(String kigbng) {

	  this.kigbng = kigbng;

  }


  /**
   * 記号番号の取得
   * 
   * @return 記号番号
   */
  public String getKigBng() {

	  return this.kigbng;

  }

  /**
   * 副資材コードのセット
   * 
   * @param fukszicod 副資材コード
   */
  public void setFukSziCod(String fukszicod) {

	  this.fukszicod = fukszicod;

  }


  /**
   * 副資材コードの取得
   * 
   * @return 副資材コード
   */
  public String getFukSziCod() {

	  return this.fukszicod;

  }

	/**
	 * 区分のセット
	 * 
	 * @param kbn 区分
	 */
	public void setKbn(String kbn) {

		this.kbn = kbn;

	}


	/**
	 * 区分の取得
	 * 
	 * @return 区分
	 */
	public String getKbn() {

		return this.kbn;

	}

	/**
	 * プレス／副資材のセット
	 * @return
	 */
	public String getPrsFks() {
		return prsFks;
	}

	/**
	 * プレス／副資材の取得
	 * @param string
	 */
	public void setPrsFks(String string) {
		prsFks = string;
	}

	//--------------------------------------------------------------------------------------------
	/**
	 * @return
	 */
	public String getCpPaste() {
		return cpPaste;
	}

	/**
	 * @param string
	 */
	public void setCpPaste(String string) {
		cpPaste = string;
	}

	/**
	 * @return
	 */
	public LinkedList getFormRows() {
		return formRows;
	}

	/**
	 * @param list
	 */
	public void setFormRows(LinkedList list) {
		formRows = list;
	}

	/**
	 * @return
	 */
	public String getFuksziCod08String() {
		return fuksziCod08String;
	}
	public void setFuksziCod08String(String sziTbl) {
		fuksziCod08String = sziTbl;
	}

	/**
	 * @param string
	 */
	
	public void removeFukusziCod08Space(){
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<fuksziCod08String.length(); i+=3){
			sb.append(fuksziCod08String.substring(i, i+3).trim());
		}
		fuksziCod08String = sb.toString();		
	}
	public ArrayList get08FuksziCodArr(){
		ArrayList fuksziCod = new ArrayList();
		for(int i = 0; i<fuksziCod08String.trim().length(); i+=3){
			try{
				fuksziCod.add(fuksziCod08String.substring(i, i+3));
			}catch(IndexOutOfBoundsException e){
				break;
			}
		}
		return fuksziCod;
	}

	/**
	 * @return
	 */
	public String getSirSk08String() {
		return sirSk08String;
	}
	public void setSirSk08String(String sirTbl) {
		sirSk08String = sirTbl;
	}

	/**
	 * @param string
	 */
	
	public void removeSirSk08Space(){
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<sirSk08String.length(); i+=6){
			sb.append(sirSk08String.substring(i, i+6));
		}
		fuksziCod08String = sb.toString();		
	}
	public ArrayList get08SirSkCodArr(){
		ArrayList sirSk = new ArrayList();
		for(int i = 0; i<sirSk08String.length(); i+=6){
			try{
				sirSk.add(sirSk08String.substring(i, i+6));
			}catch(IndexOutOfBoundsException e){
				break;
			}
		}
		return sirSk;
	}

}

