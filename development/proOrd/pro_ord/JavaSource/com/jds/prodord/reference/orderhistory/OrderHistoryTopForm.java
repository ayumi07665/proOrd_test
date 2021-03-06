/**
* OrderHistoryTopForm  ­ðÆïæÊõðtH[NX
*
*	ì¬ú    2003/04/18
*	ì¬Ò    imhhjgc hq
* Tv    HttpNGXgÆNGXgÌÊði[·éNXB
*
*	[ÏXð]
* 		2004/03/31	(NII)X
* 			EwèðÉüÉúðÇÁ
* 		2005/05/25imhhjgc
* 			EêR[hÌÇÁ
* 		2005/09/16imhhjgc
* 			EðÚÉ­ðÇÁ
*/

package com.jds.prodord.reference.orderhistory;

import org.apache.struts.action.*;
import javax.servlet.http.*;
import java.util.*;
import com.jds.prodord.common.*;

public class OrderHistoryTopForm extends ActionForm {

	public static final String COMMAND_JIKKOU = "Às";
	public static final String COMMAND_CLEAR = "NA";
	
	public static final String PRCKBN_SEARCH = "1";
	public static final String PRCKBN_DOWNLOAD = "2";

    private ArrayList details = new ArrayList();

	private String command = "";
    
	private String queryDaiKaiSkbCod = "";
	private String queryKaiSkbCod  = "";
	private ArrayList queryKaiSkbCodList = null;
	private String hyoKbn = "";
	private String bshCod = "";
	private String prcKbn = "";
	
	/**
     * ­IðWI{^
     *   ¢oÍEoÍÏE`kk
     */
    private String rb_HacSyo = "";
    

    /**
     * ªÞR[hIðWI{^
     *   vXEÞE`kk
     */
    private String rb_BunCod = "";

	/**
     * üÉóµIðWI{^
     *   ¢üÉE`kk
     */
    private String rb_Nyuko = "";
	

    /**
     * ­æR[h
     */
    private String queryHacCod = "";
    
    /**
     * LÔ
     */
    private String queryKigBng = "";
    
    /**
     * ­ú
     */
    private String queryHbiDte_Y = "";
    private String queryHbiDte_M = "";
    private String queryHbiDte_D = "";
    
    /**
     * ­ÔFrom&To
     */
    private boolean checkHacBng = true;
    private String queryHacBngFrm = "";
    private String queryHacBngTo = "";
    
    /**
     * ­úFrom&To
     */
    private boolean checkHacDte = true;
    private String queryHacDteFrm_Y = "";
    private String queryHacDteFrm_M = "";
    private String queryHacDteFrm_D = "";
    private String queryHacDteTo_Y = "";
    private String queryHacDteTo_M = "";
    private String queryHacDteTo_D = "";
    
    /**
     * [úFrom&To
     */
    private boolean checkNki = true;
    private String queryNkiFrm_Y = "";
    private String queryNkiFrm_M = "";
    private String queryNkiFrm_D = "";
    private String queryNkiTo_Y = "";
    private String queryNkiTo_M = "";
    private String queryNkiTo_D = "";
    
    /**
     * [J[ªÞ
     */
    private boolean checkMkrBun = true;
    private String[] mkrBun = null;
	private ArrayList vl_mkrBun = new ArrayList();
	
	public Collection getVl_mkrBun(){
		return (Collection)vl_mkrBun;
	}
	public void setMkrBunOptions(ArrayList mkrBunList){
		vl_mkrBun.clear();
		for(int i = 0; i<mkrBunList.size(); i++){
			vl_mkrBun.add(mkrBunList.get(i)+"");
		}
	}
	
	/**
     * æª
     */
    private boolean checkKbn = true;
	private String kbn = "";
	
 	public OrderHistoryTopForm(){
		super();
//System.out.println("TopRXgN^ : "+command);
		this.removeOrderHistoryVO();
		this.setCommand("INIT");
		clear_text();
 	}	

	/**
	 * üÉúFrom&To
	 */
	private boolean checkNyoDte = true;
	private String queryNyoDteFrm_Y = "";
	private String queryNyoDteFrm_M = "";
	private String queryNyoDteFrm_D = "";
	private String queryNyoDteTo_Y = "";
	private String queryNyoDteTo_M = "";
	private String queryNyoDteTo_D = "";
 	
	/**
	 * ­
	 */
	private boolean chkHacJun = true;

	//-----------------------------------------------------------
	//-----------------------------------------------------------

	//o[IuWFNgÌi[
	private ArrayList voList = new ArrayList();
	
    public void setOrderHistoryVO(ArrayList arr){
    	
    	voList.clear();
		voList.addAll(arr);
		
	}
	public void addOrderHistoryVO(OrderHistoryVO fmVO){

		voList.add(fmVO);
		
	}
	public ArrayList getOrderHistoryVO(){
		
		return voList;	
		
	}
	public void setOrderHistoryVO(int i, OrderHistoryVO fmVO){
    	
		voList.set(i, fmVO);
		
	}
    public OrderHistoryVO getOrderHistoryVO(int i){
		
		return (OrderHistoryVO)voList.get(i);	
		
	}
	public void removeOrderHistoryVO(){
    	
		voList.clear();
		
	}	
	public void removeOrderHistoryVO(int i){
    	
		voList.remove(i);
		
	}
	

	//-----------------------------------------------------------tb^[
	 //µ½{^
	public String getCommand(){
		return command;
	}
	public void setCommand(String s){
		command = s;
	}

	//-----------------------------------------------------------©oµ
	
     //ã\ïÐ¯ÊR[h
    public String getQueryDaiKaiSkbCod(){
    	return queryDaiKaiSkbCod;
    }
    public void setQueryDaiKaiSkbCod(String s){
    	queryDaiKaiSkbCod = s;
    }

    //ïÐ¯ÊR[h
    public String getQueryKaiSkbCod(){
    	return queryKaiSkbCod;
    }
    public void setQueryKaiSkbCod(String s){
    	queryKaiSkbCod = s;
    }
    
    //ïÐ¯ÊR[hXg
    public ArrayList getQueryKaiSkbCodList(){
    	return queryKaiSkbCodList;
    }
    public void setQueryKaiSkbCodList(ArrayList arr){
    	queryKaiSkbCodList = arr;
    }

	/**
	 * êR[hÌæ¾
	 * @return
	 */
	public String getBshCod() {
		return bshCod;
	}

	/**
	 * êR[hÌZbg
	 * @param string
	 */
	public void setBshCod(String string) {
		bshCod = string;
	}

//--------------------------------------------------------------------
    /**
     * ­IðWI{^ÌZbg
     * 
     * @param choiceHacSyo ­IðWI{^
     */
    public void setRb_HacSyo(String rb_HacSyo) {

        this.rb_HacSyo = rb_HacSyo;
    }


    /**
     * ­IðWI{^Ìæ¾
     * 
     * @return ­IðWI{^
     */
    public String getRb_HacSyo() {

        return this.rb_HacSyo;

    }


    /**
     * ªÞR[hIðWI{^ÌZbg
     * 
     * @param choiceBunCod ªÞR[hIðWI{^
     */
    public void setRb_BunCod(String rb_BunCod) {

        this.rb_BunCod = rb_BunCod;

    }


    /**
     * ªÞR[hIðWI{^Ìæ¾
     * 
     * @return ªÞR[hIðWI{^
     */
    public String getRb_BunCod() {

        return this.rb_BunCod;

    }

	/**
     * üÉóµIðWI{^ÌZbg
     * 
     * @param rb_Nyuko üÉóµIðWI{^
     */
    public void setRb_Nyuko(String rb_Nyuko) {

        this.rb_Nyuko = rb_Nyuko;

    }


    /**
     * üÉóµIðWI{^Ìæ¾
     * 
     * @return üÉóµIðWI{^
     */
    public String getRb_Nyuko() {

        return this.rb_Nyuko;

    }


    /**
     * ­æR[hÌZbg
     * 
     * @param queryHacCod ­æR[h
     */
    public void setQueryHacCod(String queryHacCod) {

        this.queryHacCod = queryHacCod;

    }


    /**
     * ­æR[hÌæ¾
     * 
     * @return ­æR[h
     */
    public String getQueryHacCod() {

        return this.queryHacCod;

    }


    /**
     * LÔÌZbg
     * 
     * @param queryKigBng LÔ
     */
    public void setQueryKigBng(String queryKigBng) {

        this.queryKigBng = queryKigBng;

    }


    /**
     * LÔÌæ¾
     * 
     * @return LÔ
     */
    public String getQueryKigBng() {

        return this.queryKigBng;

    }

//** ­ú **//
    /**
     * ­ú(N)ÌZbg
     * 
     * @param queryHbiDte_Y ­ú(N)
     */
    public void setQueryHbiDte_Y(String queryHbiDte_Y) {
        this.queryHbiDte_Y = queryHbiDte_Y;
    }
    /**
     * ­ú(N)Ìæ¾
     * 
     * @return ­ú(N)
     */
    public String getQueryHbiDte_Y() {
        return this.queryHbiDte_Y;
    }
    /**
     * ­ú()ÌZbg
     * 
     * @param queryHbiDte_M ­ú()
     */
    public void setQueryHbiDte_M(String queryHbiDte_M) {
        this.queryHbiDte_M = queryHbiDte_M;
    }
    /**
     * ­ú()Ìæ¾
     * 
     * @return ­ú()
     */
    public String getQueryHbiDte_M() {
        return this.queryHbiDte_M;
    }
    /**
     * ­ú(ú)ÌZbg
     * 
     * @param queryHbiDte_D ­ú(ú)
     */
    public void setQueryHbiDte_D(String queryHbiDte_D) {
        this.queryHbiDte_D = queryHbiDte_D;
    }
    /**
     * ­ú(ú)Ìæ¾
     * 
     * @return ­ú(ú)
     */
    public String getQueryHbiDte_D() {
        return this.queryHbiDte_D;
    }


//** ­Ô **//
    /**
     * ­Ô`FbNÌZbg
     * 
     * @param checkHacBng ­Ô`FbN
     */
    public void setCheckHacBng(boolean checkHacBng) {
        this.checkHacBng = checkHacBng;
    }
    /**
     * ­Ô`FbNÌæ¾
     * 
     * @return ­Ô`FbN
     */
    public boolean getCheckHacBng() {
        return this.checkHacBng;
    }
    /**
     * ­ÔFromÌZbg
     * 
     * @param queryHacBngFrm ­ÔFrom
     */
    public void setQueryHacBngFrm(String queryHacBngFrm) {
        this.queryHacBngFrm = queryHacBngFrm;
    }
    /**
     * ­ÔFromÌæ¾
     * 
     * @return ­ÔFrom
     */
    public String getQueryHacBngFrm() {
        return this.queryHacBngFrm;
    }
    /**
     * ­ÔToÌZbg
     * 
     * @param queryHacBngTo ­ÔTo
     */
    public void setQueryHacBngTo(String queryHacBngTo) {
        this.queryHacBngTo = queryHacBngTo;
    }
    /**
     * ­ÔToÌæ¾
     * 
     * @return ­ÔTo
     */
    public String getQueryHacBngTo() {
        return this.queryHacBngTo;
    }

//** ­ú **//
    /**
     * ­ú`FbNÌZbg
     * 
     * @param checkHacDte ­ú`FbN
     */
    public void setCheckHacDte(boolean checkHacDte) {
        this.checkHacDte = checkHacDte;
    }
    /**
     * ­ú`FbNÌæ¾
     * 
     * @return ­ú`FbN
     */
    public boolean getCheckHacDte() {
        return this.checkHacDte;
    }
    /**
     * ­ú(N)FromÌZbg
     * 
     * @param queryHacDteFrm_Y ­ú(N)From
     */
    public void setQueryHacDteFrm_Y(String queryHacDteFrm_Y) {
        this.queryHacDteFrm_Y = queryHacDteFrm_Y;
    }
    /**
     * ­ú(N)FromÌæ¾
     * 
     * @return ­ú(N)From
     */
    public String getQueryHacDteFrm_Y() {
        return this.queryHacDteFrm_Y;
    }
    /**
     * ­ú()FromÌZbg
     * 
     * @param queryHacDteFrm_M ­ú()From
     */
    public void setQueryHacDteFrm_M(String queryHacDteFrm_M) {
        this.queryHacDteFrm_M = queryHacDteFrm_M;
    }
    /**
     * ­ú()FromÌæ¾
     * 
     * @return ­ú()From
     */
    public String getQueryHacDteFrm_M() {
        return this.queryHacDteFrm_M;
    }
    /**
     * ­ú(ú)FromÌZbg
     * 
     * @param queryHacDteFrm_D ­ú(ú)From
     */
    public void setQueryHacDteFrm_D(String queryHacDteFrm_D) {
        this.queryHacDteFrm_D = queryHacDteFrm_D;
    }
    /**
     * ­ú(ú)FromÌæ¾
     * 
     * @return ­ú(ú)From
     */
    public String getQueryHacDteFrm_D() {
        return this.queryHacDteFrm_D;
    }
    /**
     * ­ú(N)ToÌZbg
     * 
     * @param queryHacDteTo_Y ­ú(N)To
     */
    public void setQueryHacDteTo_Y(String queryHacDteTo_Y) {
        this.queryHacDteTo_Y = queryHacDteTo_Y;
    }
    /**
     * ­ú(N)ToÌæ¾
     * 
     * @return ­ú(N)To
     */
    public String getQueryHacDteTo_Y() {
        return this.queryHacDteTo_Y;
    }
    /**
     * ­ú()ToÌZbg
     * 
     * @param queryHacDteTo_M ­ú()To
     */
    public void setQueryHacDteTo_M(String queryHacDteTo_M) {
        this.queryHacDteTo_M = queryHacDteTo_M;
    }
    /**
     * ­ú()ToÌæ¾
     * 
     * @return ­ú()To
     */
    public String getQueryHacDteTo_M() {
        return this.queryHacDteTo_M;
    }
    /**
     * ­ú(ú)ToÌZbg
     * 
     * @param queryHacDteTo_D ­ú(ú)To
     */
    public void setQueryHacDteTo_D(String queryHacDteTo_D) {
        this.queryHacDteTo_D = queryHacDteTo_D;
    }
    /**
     * ­ú(ú)ToÌæ¾
     * 
     * @return ­ú(ú)To
     */
    public String getQueryHacDteTo_D() {
        return this.queryHacDteTo_D;
    }


//** [ú **//
    /**
     * [ú`FbNÌZbg
     * 
     * @param checkNki [ú`FbN
     */
    public void setCheckNki(boolean checkNki) {
        this.checkNki = checkNki;
    }
    /**
     * [ú`FbNÌæ¾
     * 
     * @return [ú`FbN
     */
    public boolean getCheckNki() {
        return this.checkNki;
    }    
    /**
     * [ú(N)FromÌZbg
     * 
     * @param queryNkiFrm_Y [ú(N)From
     */
    public void setQueryNkiFrm_Y(String queryNkiFrm_Y) {
        this.queryNkiFrm_Y = queryNkiFrm_Y;
    }
    /**
     * [ú(N)FromÌæ¾
     * 
     * @return [ú(N)From
     */
    public String getQueryNkiFrm_Y() {
        return this.queryNkiFrm_Y;
    }
    /**
     * [ú()FromÌZbg
     * 
     * @param queryNkiFrm_M [ú()From
     */
    public void setQueryNkiFrm_M(String queryNkiFrm_M) {
        this.queryNkiFrm_M = queryNkiFrm_M;
    }
    /**
     * [ú()FromÌæ¾
     * 
     * @return [ú()From
     */
    public String getQueryNkiFrm_M() {
        return this.queryNkiFrm_M;        
    }
    /**
     * [ú(ú)FromÌZbg
     * 
     * @param queryNkiFrm_D [ú(ú)From
     */
    public void setQueryNkiFrm_D(String queryNkiFrm_D) {
        this.queryNkiFrm_D = queryNkiFrm_D;
    }
    /**
     * [ú(ú)FromÌæ¾
     * 
     * @return [ú(ú)From
     */
    public String getQueryNkiFrm_D() {
        return this.queryNkiFrm_D;
    }
    /**
     * [ú(N)ToÌZbg
     * 
     * @param queryNkiTo_Y [ú(N)To
     */
    public void setQueryNkiTo_Y(String queryNkiTo_Y) {
        this.queryNkiTo_Y = queryNkiTo_Y;
    }
    /**
     * [ú(N)ToÌæ¾
     * 
     * @return [ú(N)To
     */
    public String getQueryNkiTo_Y() {
        return this.queryNkiTo_Y;
    }
    /**
     * [ú()ToÌZbg
     * 
     * @param queryNkiTo_M [ú()To
     */
    public void setQueryNkiTo_M(String queryNkiTo_M) {
        this.queryNkiTo_M = queryNkiTo_M;
    }
    /**
     * [ú()ToÌæ¾
     * 
     * @return [ú()To
     */
    public String getQueryNkiTo_M() {
        return this.queryNkiTo_M;
    }
    /**
     * [ú(ú)ToÌZbg
     * 
     * @param queryNkiTo_D [ú(ú)To
     */
    public void setQueryNkiTo_D(String queryNkiTo_D) {
        this.queryNkiTo_D = queryNkiTo_D;
    }
    /**
     * [ú(ú)ToÌæ¾
     * 
     * @return [ú(ú)To
     */
     public String getQueryNkiTo_D() {
        return this.queryNkiTo_D;
    }
    
	/**
     * [J[ªÞ`FbNÌZbg
     * 
     * @param checkNki [J[ªÞ`FbN
     */
    public void setCheckMkrBun(boolean checkMkrBun) {
        this.checkMkrBun = checkMkrBun;
    }
    /**
     * [J[ªÞ`FbNÌæ¾
     * 
     * @return [J[ªÞ`FbN
     */
    public boolean getCheckMkrBun() {
        return this.checkMkrBun;
    } 
	/**
     * [J[ªÞÌZbg
     * 
     * @param mkrBun [J[ªÞ
     */
    public void setMkrBun(String[] s) {

        this.mkrBun = s;

    }
    /**
     * [J[ªÞÌæ¾
     * 
     * @return [J[ªÞ
     */
    public String[] getMkrBun() {

        return this.mkrBun;

    }
    
    /**
     * æª`FbNÌZbg
     * 
     * @param kbn æª`FbN
     */
    public void setCheckKbn(boolean checkKbn) {
        this.checkKbn = checkKbn;
    }
    /**
     * æª`FbNÌæ¾
     * 
     * @return æª`FbN
     */
    public boolean getCheckKbn() {
        return this.checkKbn;
    } 
	/**
     * æªÌZbg
     * 
     * @param kbn æª
     */
    public void setKbn(String s) {

        this.kbn = s;

    }
    /**
     * æªÌæ¾
     * 
     * @return æª
     */
    public String getKbn() {

        return this.kbn;

    }


	/**
	 * üÉú`FbNÌZbg
	 * 
	 * @param checkNyoDte@üÉú`FbN
	 */
	public void setCheckNyoDte(boolean checkNyoDte) {
		this.checkNyoDte = checkNyoDte;
	}

	/**
	 * üÉú`FbNÌæ¾
	 * 
	 * @return checkNyoDte üÉú`FbN
	 */
	public boolean getCheckNyoDte() {
		return checkNyoDte;
	}

	/**
	 *üÉú(N)FromÌZbg
	 *
	 *  @param queryNyoDteFrm_Y@üÉú(N)From
	 */
	public void setQueryNyoDteFrm_Y(String queryNyoDteFrm_Y) {
		this.queryNyoDteFrm_Y = queryNyoDteFrm_Y;
	}

	/**
	 * üÉú(N)FromÌæ¾
	 * 
	 * @return@üÉú(N)From
	 */
	public String getQueryNyoDteFrm_Y() {
		return queryNyoDteFrm_Y;
	}

	/**
	 * üÉú()FromÌZbg
	 * 
	 * @param queryNyoDteFrm_M@üÉú()From
	 */
	public void setQueryNyoDteFrm_M(String queryNyoDteFrm_M) {
		this.queryNyoDteFrm_M = queryNyoDteFrm_M;
	}


	/**
	 * üÉú()FromÌæ¾
	 * 
	 * @return@üÉú()From
	 **/
	public String getQueryNyoDteFrm_M() {
		return queryNyoDteFrm_M;
	}

	/**
	 * üÉú(ú)FromÌZbg
	 * 
	 * @param queryNyoDteFrm_D@üÉú(ú)From
	 */
	public void setQueryNyoDteFrm_D(String queryNyoDteFrm_D) {
		this.queryNyoDteFrm_D = queryNyoDteFrm_D;
	}

	/**
	 * üÉú(ú)FromÌæ¾
	 * 
	 * @return@üÉú(ú)From
	 */
	public String getQueryNyoDteFrm_D() {
		return queryNyoDteFrm_D;
	}

	/**
	 * üÉú(N)toÌZbg
	 * 
	 * @param queryNyoDteTo_Y üÉú(N)to
	 */
	public void setQueryNyoDteTo_Y(String queryNyoDteTo_Y) {
		this.queryNyoDteTo_Y = queryNyoDteTo_Y;
	}

	/**
	 * üÉú(N)toÌæ¾
	 * 
	 * @return@ üÉú(N)to
	 */
	public String getQueryNyoDteTo_Y() {
		return queryNyoDteTo_Y;
	}

	/**
	 * üÉú()toÌZbg
	 * 
	 * @param queryNyoDteTo_M üÉú()to
	 */
	public void setQueryNyoDteTo_M(String queryNyoDteTo_M) {
		this.queryNyoDteTo_M = queryNyoDteTo_M;
	}

	/**
	 * üÉú()toÌæ¾
	 * 
	 * @return üÉú()to
	 */
	public String getQueryNyoDteTo_M() {
		return queryNyoDteTo_M;
	}


	/**
	 * üÉú()toÌæ¾
	 * 
	 * @return üÉú()to
	 */
	public String getQueryNyoDteTo_D() {
		return queryNyoDteTo_D;
	}

	/**
	 * üÉú()toÌæ¾
	 * 
	 * @param queryNyoDteTo_D üÉú()to
	 */
	public void setQueryNyoDteTo_D(String queryNyoDteTo_D) {
		this.queryNyoDteTo_D = queryNyoDteTo_D;
	}


	/**
	 * `FbN{bNX­Ìæ¾
	 * @return
	 */
	public boolean isChkHacJun() {
		return chkHacJun;
	}

	/**
	 * `FbN{bNX­ÌÝè
	 * @param b
	 */
	public void setChkHacJun(boolean b) {
		chkHacJun = b;
	}

	/**
	 * @return
	 */
	public String getPrcKbn() {
		return prcKbn;
	}

	/**
	 * @param string
	 */
	public void setPrcKbn(String string) {
		prcKbn = string;
	}
	
	//---------------------------------------------------------------------------------
	public int getSize(){
		return details.size();
	}
	
	public void setSize(int size){
		if(size > details.size()){
			int j = size - this.getSize();			
		  for(int k=0; k<j; k++){
		    details.add(new OrderHistoryFormRow());			  	
		  }						
		}else if(size < details.size()){
			for(int i = details.size();i > size;i--){
				details.remove((i-1));
			}
		}
	}

	
	public OrderHistoryFormRow getFormRow_R(int i){
		OrderHistoryFormRow fr = (OrderHistoryFormRow)details.get(i);
		if(fr == null){
			fr = new OrderHistoryFormRow();
			details.set(i,fr);
		}
		return fr;
	}
	
	public void clearTableField(){
		for(int i = 0;i < details.size();i++){
			OrderHistoryFormRow fr = (OrderHistoryFormRow)details.get(i);
			if(fr != null)
	            details.set(i,new OrderHistoryFormRow());			
		}	
	}
	
	
	//\¦æª
	public void setHyoKbn(String hyoKbn){
		this.hyoKbn = hyoKbn;
	}
	
	public String getHyoKbn(){
		return  this.hyoKbn;
	}
	


	public void clearAll(){
		command = "";
		queryDaiKaiSkbCod = "";
		clearTableField();		
	}
		
	public ArrayList getFormRowList_R(){
		return details;
	}
	
	public void setFormRowList_R(ArrayList arr){
		details = arr;
	}
	
	public void removeAllRow(){
		details.clear();
	}
	
	//`FbN{bNXðZbg·é\bh
	public void reset_checkbox(){
		this.setCheckHacBng(false);
		this.setCheckHacDte(false);
		this.setCheckNki(false);		
		this.setCheckKbn(false);
		this.setCheckNyoDte(false);
		this.setChkHacJun(false);
	}
	//ú»\bh(text)
	public void clear_text(){
		this.setPrcKbn(PRCKBN_SEARCH);
		this.setRb_HacSyo("2");
		this.setRb_BunCod("1");
		this.setRb_Nyuko("2");
		this.setCheckHacBng(false);
		this.setCheckHacDte(false);
		this.setCheckNki(false);
		this.setCheckMkrBun(false);
		this.setCheckKbn(false);	
		this.setCheckNyoDte(false);	
		this.setQueryHacCod("");
		this.setQueryKigBng("");
		this.setQueryHbiDte_Y("");
		this.setQueryHbiDte_M("");
		this.setQueryHbiDte_D("");
		this.setQueryHacBngFrm("");
		this.setQueryHacBngTo("");
		this.setQueryHacDteFrm_Y("");
		this.setQueryHacDteFrm_M("");
		this.setQueryHacDteFrm_D("");
		this.setQueryHacDteTo_Y("");
		this.setQueryHacDteTo_M("");
		this.setQueryHacDteTo_D("");
		this.setQueryNkiFrm_Y("");
		this.setQueryNkiFrm_M("");
		this.setQueryNkiFrm_D("");
		this.setQueryNkiTo_Y("");
		this.setQueryNkiTo_M("");
		this.setQueryNkiTo_D("");
		this.setMkrBun(null);
		this.setKbn("");
		this.setQueryNyoDteFrm_Y("");
		this.setQueryNyoDteFrm_M("");
		this.setQueryNyoDteFrm_D("");
		this.setQueryNyoDteTo_Y("");
		this.setQueryNyoDteTo_M("");
		this.setQueryNyoDteTo_D("");
		this.setChkHacJun(false);
	}
	

	//---------------------------------------------------------------------------------
	public ActionErrors validate(ActionMapping mapping,HttpServletRequest req){
		if(command.equals("INIT"))
			return null;
		if(command.equals(COMMAND_CLEAR))
			return null;
		
		ActionErrors errors = new ActionErrors();

		//ÀsÌÆ«				
		if(command.equals(COMMAND_JIKKOU)){
			DateUtils dateUtils = new DateUtils();
			int today = dateUtils.getDate6Int();
        
	/** üÍ`FbN **/
 	    	/** ­æR[h **/
			if(!queryHacCod.trim().equals("")){ //

    			//¼pÌÝ
				if(!StringUtils.isAscii(queryHacCod.trim())){
					errors.add("",new ActionError("errors.input.1","­æR[h","¼p"));
				}
			}
             /** LÔ **/
            if(!queryKigBng.trim().equals("")){
            	
            	//¼p
            	if(!StringUtils.isAscii(queryKigBng.trim())){
            		errors.add("",new ActionError("errors.input.1","LÔ","¼p"));
            	}
            }
            
 			/** ­ú **/
    		if(!queryHbiDte_Y.trim().equals("") || !queryHbiDte_M.trim().equals("") || !queryHbiDte_D.trim().equals("")){
            	//¼p
            	if( (!StringUtils.isAscii(queryHbiDte_Y.trim()) && !queryHbiDte_Y.trim().equals("")) ||
            	    (!StringUtils.isAscii(queryHbiDte_M.trim()) && !queryHbiDte_M.trim().equals("")) || 
            	    (!StringUtils.isAscii(queryHbiDte_D.trim()) && !queryHbiDte_D.trim().equals("")) ){
            		errors.add("",new ActionError("errors.input.1","­ú","¼p"));
            	}else{
				//út^
					if(!CheckCommon.validateAsDate(queryHbiDte_Y,queryHbiDte_M,queryHbiDte_D)){
							errors.add("",new ActionError("errors.input.1","­ú","út"));
					}
				}
			}
			
			/** ­ÔFrom&To **/
			if(rb_HacSyo.equals("1")){
				if(checkHacBng || !queryHacBngFrm.trim().equals("") || !queryHacBngTo.trim().equals("")){
					errors.add("",new ActionError("errors.input.prohibited","¢oÍIðA­Ô"));
				}
			}else{		
				if(checkHacBng){
					boolean isAsciiDigitErr = false;
					if(queryHacBngFrm.trim().equals("")){
						errors.add("",new ActionError("errors.input.required","­Ô(From)"));
					}else if(!StringUtils.isAsciiDigit(queryHacBngFrm.trim())){
						errors.add("",new ActionError("errors.input.1","­Ô(From)","¼p"));
						isAsciiDigitErr = true;
					}
					if(!queryHacBngTo.trim().equals("")){
						if(!StringUtils.isAsciiDigit(queryHacBngTo.trim())){
							errors.add("",new ActionError("errors.input.1","­Ô(To)","¼p"));
							isAsciiDigitErr = true;
						}
						if(!queryHacBngFrm.trim().equals("") && !isAsciiDigitErr){
							int hacBngFrm = Integer.parseInt(queryHacBngFrm);
							int hacBngTo = Integer.parseInt(queryHacBngTo);
							if(hacBngFrm > hacBngTo){
								errors.add("",new ActionError("errors.input.incorrect","­ÔÌÍÍwè"));
							}
						}
					}
				}else{
					if(!queryHacBngFrm.trim().equals("") || !queryHacBngTo.trim().equals("")){
						errors.add("",new ActionError("errors.input.prohibited","­Ô"));
					}
				}
			}		

			/** ­úFrom&To **/
		    if(checkHacDte){//­úüÍÂÌÆ«
		    	boolean hacDte_required = false;
		    	//FromªÇê©uN
		    	if(queryHacDteFrm_Y.trim().equals("") || queryHacDteFrm_M.trim().equals("") || queryHacDteFrm_D.trim().equals("")){
					hacDte_required = true;
		    	    errors.add("",new ActionError("errors.input.required","­ú(From)"));
		    	}else{
		    		//¼p
             		if((!StringUtils.isAscii(queryHacDteFrm_Y.trim()) && !queryHacDteFrm_Y.trim().equals("")) || 
		    		   (!StringUtils.isAscii(queryHacDteFrm_M.trim()) && !queryHacDteFrm_M.trim().equals("")) || 
            	       (!StringUtils.isAscii(queryHacDteFrm_D.trim()) && !queryHacDteFrm_D.trim().equals("")) ){
            				errors.add("",new ActionError("errors.input.1","­ú","¼p"));
      	      		}else{

		    		//Fromút^`FbN
		    	   		if(!CheckCommon.validateAsDate(queryHacDteFrm_Y,queryHacDteFrm_M,queryHacDteFrm_D)){
							errors.add("",new ActionError("errors.input.1","­ú(From)","út"));		
		    	   		}else{
							int hacdte_from = Integer.parseInt(queryHacDteFrm_Y.trim() + 
						   	 	StringUtils.lpad(queryHacDteFrm_M.trim(),2,"0") + 
							 	StringUtils.lpad(queryHacDteFrm_D.trim(),2,"0"));
								//úÈ~w
					    		if(hacdte_from > today)
					    			errors.add("",new ActionError("errors.input.1","­ú(From)","úÈOÌút"));
				   		}
            	    }
		        }
		        if(!(queryHacDteTo_Y.trim().equals("") && queryHacDteTo_M.trim().equals("") && queryHacDteTo_D.trim().equals(""))){
		    		if(queryHacDteFrm_Y.trim().equals("") || queryHacDteFrm_M.trim().equals("") || queryHacDteFrm_D.trim().equals("")){
		    	    	if(!hacDte_required)
		    	    		errors.add("",new ActionError("errors.input.required","­ú(From)"));
		    		}
		    		if(queryHacDteTo_Y.trim().equals("") || queryHacDteTo_M.trim().equals("") || queryHacDteTo_D.trim().equals("")){
		    	    	errors.add("",new ActionError("errors.input.required","­ú(To)"));
		    		}else{ 
		    		//Toút^`FbN
		    	   		if(!CheckCommon.validateAsDate(queryHacDteTo_Y,queryHacDteTo_M,queryHacDteTo_D)){
							errors.add("",new ActionError("errors.input.1","­ú(To)","út"));		
		    	   		}else{
							int hacdte_to = Integer.parseInt(queryHacDteTo_Y.trim() + 
						 	  	 StringUtils.lpad(queryHacDteTo_M.trim(),2,"0") + 
								 StringUtils.lpad(queryHacDteTo_D.trim(),2,"0"));
							//úÈ~w
					  		if(hacdte_to > today)
					    		errors.add("",new ActionError("errors.input.1","­ú","úÈOÌút"));

							int hacdte_from = Integer.parseInt(queryHacDteFrm_Y.trim() + 
						   	 	StringUtils.lpad(queryHacDteFrm_M.trim(),2,"0") + 
							 	StringUtils.lpad(queryHacDteFrm_D.trim(),2,"0"));
							if(!queryHacDteFrm_Y.trim().equals("") && !queryHacDteFrm_M.trim().equals("") && !queryHacDteFrm_D.trim().equals("")){
								if(hacdte_to < hacdte_from)
					    			errors.add("",new ActionError("errors.input.incorrect","­úÌÍÍwè"));
							}		 	
				 		}
		     	    }
		        }		
		    }else{
		    	if(!queryHacDteFrm_Y.trim().equals("") || !queryHacDteFrm_M.trim().equals("")|| 
		    	   !queryHacDteFrm_D.trim().equals("") || !queryHacDteTo_Y.trim().equals("") || 
		    	   !queryHacDteTo_M.trim().equals("")  || !queryHacDteTo_D.trim().equals("")){
		    		errors.add("",new ActionError("errors.input.prohibited","­ú"));
		    	}
  		    }
		    
		    /** [úFrom&To **/
		    if(checkNki){//[úüÍÂÌÆ«
		    	boolean nki_required = false;
		    	//FromªÇê©uN
		    	if(queryNkiFrm_Y.trim().equals("") || queryNkiFrm_M.trim().equals("") || queryNkiFrm_D.trim().equals("")){
		    	    nki_required = true;
		    	    errors.add("",new ActionError("errors.input.required","[ú(From)"));
		    	}else{
		    		//¼p
             		if((!StringUtils.isAscii(queryNkiFrm_Y.trim()) && !queryNkiFrm_Y.trim().equals("")) || 
		    		   (!StringUtils.isAscii(queryNkiFrm_M.trim()) && !queryNkiFrm_M.trim().equals("")) || 
            	       (!StringUtils.isAscii(queryNkiFrm_D.trim()) && !queryNkiFrm_D.trim().equals("")) ){
            				errors.add("",new ActionError("errors.input.1","[ú","¼p"));
      	      		}else{
		    			//Fromút^`FbN
		    	   		if(!CheckCommon.validateAsDate(queryNkiFrm_Y,queryNkiFrm_M,queryNkiFrm_D)){
							errors.add("",new ActionError("errors.input.1","[ú(From)","út"));		
				   		}
		        	}
		    	}
		        if(!(queryNkiTo_Y.trim().equals("") && queryNkiTo_M.trim().equals("") && queryNkiTo_D.trim().equals(""))){
		    		if(queryNkiFrm_Y.trim().equals("") || queryNkiFrm_M.trim().equals("") || queryNkiFrm_D.trim().equals("")){
		    	    	if(!nki_required)
		    	    		errors.add("",new ActionError("errors.input.required","[ú(From)"));
		    		}
		    		if(queryNkiTo_Y.trim().equals("") || queryNkiTo_M.trim().equals("") || queryNkiTo_D.trim().equals("")){
		    	    	errors.add("",new ActionError("errors.input.required","[ú(To)"));
		    		}else{ 
		    		//Toút^`FbN
		    	   		if(!CheckCommon.validateAsDate(queryNkiTo_Y,queryNkiTo_M,queryNkiTo_D)){
							errors.add("",new ActionError("errors.input.1","[ú(To)","út"));		
		    	   		}else{
							int nki_to = Integer.parseInt(queryNkiTo_Y.trim() + 
						 	  	 StringUtils.lpad(queryNkiTo_M.trim(),2,"0") + 
								 StringUtils.lpad(queryNkiTo_D.trim(),2,"0"));

							int nki_from = Integer.parseInt(queryNkiFrm_Y.trim() + 
						   	 	StringUtils.lpad(queryNkiFrm_M.trim(),2,"0") + 
							 	StringUtils.lpad(queryNkiFrm_D.trim(),2,"0"));

							if(!queryNkiFrm_Y.trim().equals("") && !queryNkiFrm_M.trim().equals("") && !queryNkiFrm_D.trim().equals("")){
								if(nki_to < nki_from){
					    			errors.add("",new ActionError("errors.input.incorrect","[úÌÍÍwè"));
								}	
							} 	
				 		}
		     	    }
		        }		
		    }else{
		    	if(!queryNkiFrm_Y.trim().equals("") || !queryNkiFrm_M.trim().equals("")|| 
		    	   !queryNkiFrm_D.trim().equals("") || !queryNkiTo_Y.trim().equals("") || 
		    	   !queryNkiTo_M.trim().equals("")  || !queryNkiTo_D.trim().equals("")){
		    		errors.add("",new ActionError("errors.input.prohibited","[ú"));
		    	}
  		    }
  		    //[J[ªÞ
			if(checkMkrBun){
				if(mkrBun == null){
					errors.add("",new ActionError("errors.input.required","[J[ªÞ"));		
				}
			}
			/** üÉúFrom&To **/  //2004/04/01 add
			if(checkNyoDte){//üÉúüÍÂÌÆ«
				boolean nyodte_required = false;
				//FromªÇê©uN
				if(queryNyoDteFrm_Y.trim().equals("") || queryNyoDteFrm_M.trim().equals("") || queryNyoDteFrm_D.trim().equals("")){
					nyodte_required = true;
					errors.add("",new ActionError("errors.input.required","üÉú(From)"));
				}else{
					//¼p
					if((!StringUtils.isAscii(queryNyoDteFrm_Y.trim()) && !queryNyoDteFrm_Y.trim().equals("")) || 
					   (!StringUtils.isAscii(queryNyoDteFrm_M.trim()) && !queryNyoDteFrm_M.trim().equals("")) || 
					   (!StringUtils.isAscii(queryNyoDteFrm_D.trim()) && !queryNyoDteFrm_D.trim().equals("")) ){
							errors.add("",new ActionError("errors.input.1","üÉú","¼p"));
					}else{
						//Fromút^`FbN
						if(!CheckCommon.validateAsDate(queryNyoDteFrm_Y,queryNyoDteFrm_M,queryNyoDteFrm_D)){
							errors.add("",new ActionError("errors.input.1","üÉú(From)","út"));		
						}
					}
				}
				if(!(queryNyoDteTo_Y.trim().equals("") && queryNyoDteTo_M.trim().equals("") && queryNyoDteTo_D.trim().equals(""))){
					if(queryNyoDteTo_Y.trim().equals("") || queryNyoDteTo_M.trim().equals("") || queryNyoDteTo_D.trim().equals("")){
						errors.add("",new ActionError("errors.input.required","üÉú(To)"));
					}else{ 
					//Toút^`FbN
						if(!CheckCommon.validateAsDate(queryNyoDteTo_Y,queryNyoDteTo_M,queryNyoDteTo_D)){
							errors.add("",new ActionError("errors.input.1","üÉú(To)","út"));		
						}else{
							int nyodte_to = Integer.parseInt(queryNyoDteTo_Y.trim() + 
								 StringUtils.lpad(queryNyoDteTo_M.trim(),2,"0") + 
								 StringUtils.lpad(queryNyoDteTo_D.trim(),2,"0"));

							int nyodte_from = Integer.parseInt(queryNyoDteFrm_Y.trim() + 
								StringUtils.lpad(queryNyoDteFrm_M.trim(),2,"0") + 
								StringUtils.lpad(queryNyoDteFrm_D.trim(),2,"0"));

							if(!queryNyoDteFrm_Y.trim().equals("") && !queryNyoDteFrm_M.trim().equals("") && !queryNyoDteFrm_D.trim().equals("")){
								if(nyodte_to < nyodte_from){
									errors.add("",new ActionError("errors.input.incorrect","üÉúÌÍÍwè"));
								}	
							} 	
						}
					}
				}		
			}else{
				if(!queryNyoDteFrm_Y.trim().equals("") || !queryNyoDteFrm_M.trim().equals("")|| 
				   !queryNyoDteFrm_D.trim().equals("") || !queryNyoDteTo_Y.trim().equals("") || 
				   !queryNyoDteTo_M.trim().equals("")  || !queryNyoDteTo_D.trim().equals("")){
					errors.add("",new ActionError("errors.input.prohibited","üÉú"));
				}
			}

		}
		
		
		return errors.empty() ? null : errors;
	}
	
	public void reset(ActionMapping maping,HttpServletRequest req){
		this.command = "INIT";
		reset_checkbox();
		clear_text();
	}

}

