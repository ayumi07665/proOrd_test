/**
* PrsOrderForm  vXEÞ­æÊtH[NX
*
*	ì¬ú    2003/04/28
*	ì¬Ò    imhhjªc Äü
* Tv    HttpNGXgÆNGXgÌÊði[·éNXB
*
*	[ÏXð]
* 		2003/05/01imhhjgc hq
* 			EüÍ`FbNÇÁ
* 		2003/01/13imhhjX@³â©
* 			E[üæNAÇÁÉº¤C³
* 		2004/03/08@(mhh) X
* 			EVæª"»Ì¼"ÇÁÉº¤C³
*		2005/02/03imhhjgc
*			EêR[hÌÇÁ
*		2005/05/26imhhjgc
*			E­}CiXüÍÂÉC³
*		2005/09/07imhhjgc
*			EÞ­æÊAÞR[hðv_EÚÉÏXiVAPÐÎj
*		2005/09/21imhhjgc
*			E[úêÏX@\ÇÁ
*/

package com.jds.prodord.order.prsorder;

import org.apache.struts.action.*;

import javax.servlet.http.*;
import java.util.*;
import com.jds.prodord.common.*;

public class PrsOrderForm extends ActionForm {

	public static final String COMMAND_HACHUSHIJI = "COMMAND_HACHUSHIJI";
	public static final String COMMAND_DENPYOHAKKOU = "COMMAND_DENPYOHAKKOU";
	public static final String COMMAND_PRSHACHU = "COMMAND_PRSHACHU";
	public static final String COMMAND_FKUSHIZAIHACHU = "COMMAND_FKUSHIZAIHACHU";
	public static final String COMMAND_KBNHENKO = "COMMAND_KBNHENKO";	
	public static final String COMMAND_NHNCLEAR = "COMMAND_NHNCLEAR";//2004.01.13 add
	public static final String COMMAND_NKICHANGE = "COMMAND_NKICHANGE";//2005.09.21 add
	
	public static final String ManualOrder = "ManualOrder";
	public static final String ManualOrderPaste = "ManualOrderPaste";
	public static final String IkkatsuRefer = "IkkatsuRefer";
	
	public static final String PRSHACHU = "0";
	public static final String FUKHACHU = "1";
	
	public static final String SUCCESS_HACHUSHIJI = "SUCCESS_HACHUSHIJI";
	public static final String SUCCESS_DENPYOHAKKOU = "SUCCESS_DENPYOHAKKOU";
	public static final String SUCCESS_KBNHENKO = "SUCCESS_KBNHENKO";
	public static final String SUCCESS_NHNCLEAR = "SUCCESS_NHNCLEAR";//2004.01.13 add
	public static final String SUCCESS_NKICHANGE = "SUCCESS_NKICHANGE";//2005.09.21 add
	
	public static final String COMMAND_CLEAR = "COMMAND_CLEAR";
	public static final String COMMAND_BACK = "COMMAND_BACK";
	
	public static final String NEXT = "NEXT";
	public static final String PREV = "PREV";
	public static final String SUCCESS_PREVNEXT = "SUCCESS_PREVNEXT";
	
//	public static final int MAX_ROW_COUNT = 5;
	public static final int MAX_ROW_COUNT = 100;
	
    private ArrayList details = new ArrayList();

	private String command = "";
    private String count = "";
    private String newWindow = "";//óüpæÊðJ­©Ç¤©ÌtO
    private String pre_page = "";//OÌy[WiÇÌy[W©çJÚµÄ«½©j
    private String prs_FukSgn = "";//vX­æÊ©Þ­æÊ©ÌTC
    
    private ArrayList check_prs1_index = new ArrayList();
    private ArrayList check_fuk1_index = new ArrayList();
    
    private int currentPage;//»ÝÌy[W
    private int disp_currentPage;//»ÝÌy[Wi\¦pj
    private int pageCount;//y[W
    private int referredMaxPage;//QÆ³ê½Ååy[W
    private int allRowCount;//Sy[WÌ
    
	private String queryDaiKaiSkbCod = "";
	private String queryKaiSkbCod = "";
	private ArrayList queryKaiSkbCodList = null;
	private String bshCod = "";
	private String hyoKbn = "";
	
	private String kbn = "";
	
	public ArrayList fukSziList = new ArrayList(); //2005/09/07 add
	private String nki_Y ="";
	private String nki_M ="";
	private String nki_D ="";


	public PrsOrderForm(){
		super();
//System.out.println("RXgN^ : "+command);
		this.removePrsOrderVO();
		this.setCommand("INIT");
	}
	
	

	//-----------------------------------------------------------
	//-----------------------------------------------------------
	//o[IuWFNgÌi[
	private ArrayList voList = new ArrayList();
	
    public void setPrsOrderVO(ArrayList arr){
    	
    	voList.clear();
		voList.addAll(arr);
		
	}
	public void addPrsOrderVO(PrsOrderVO fmVO){

		voList.add(fmVO);
		
	}
	public ArrayList getPrsOrderVO(){
		ArrayList arr = new ArrayList();
		for(int i = 0; i < voList.size(); i++){
			arr.add(voList.get(i));
		}
		return arr;	
		
	}
    public PrsOrderVO getPrsOrderVO(int i){
		
		return (PrsOrderVO)voList.get(i);	
		
	}
	public void setPrsOrderVO(int i, PrsOrderVO fmVO){
    	
		voList.set(i, fmVO);
		
	}
	public void removePrsOrderVO(){
    	
		voList.clear();
		
	}	
	public void removePrsOrderVO(int i){
    	
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
	
	//æª
	public String getKbn(){
		return kbn;
	}
	public void setKbn(String s){
		kbn = s;
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
    
    //ïÐ¯ÊR[h
    public ArrayList getQueryKaiSkbCodList(){
    	return queryKaiSkbCodList;
    }
    public void setQueryKaiSkbCodList(ArrayList s){
    	queryKaiSkbCodList = s;
    }
//--------------------------------------------------------------------

    /**
     * ÌZbg
     */
    public void setCount(int i) {
        this.count = i+"";
    }
    
    /**
     * Ìæ¾
     */
    public String getCount() {
        return this.count;
    }
    
    /**
     * óüpæÊðJ­©Ç¤©ÌtO
     */
    public void setNewWindow(String s) {
        this.newWindow = s;
    }
    public String getNewWindow() {
        return this.newWindow;
    }
    
	/**
	 * pre_pageÌæ¾
	 * 
	 * @return pre_page
	 */
	public String getPre_page() {
		
		return pre_page;
		
	}
	/**
	 * pre_pageÌZbg
	 * 
	 * @param pre_page
	 */
	public void setPre_page(String pre_page) {
		
		this.pre_page = pre_page;
		
	}
	
	/**
	 * vX­æÊ©Þ­æÊ©ÌTCÌæ¾
	 * 
	 * @param prs_FukSgn
	 */
	public String getPrs_FukSgn() {
		
		return prs_FukSgn;
		
	}
	/**
	 * vX­æÊ©Þ­æÊ©ÌTCÌZbg
	 * 
	 * @param prs_FukSgn
	 */
	public void setPrs_FukSgn(String prs_FukSgn) {
		
		this.prs_FukSgn = prs_FukSgn;
		
	}

	/**
	 * @return
	 */
	public String getNki_Y() {
		return nki_Y;
	}

	/**
	 * @param string
	 */
	public void setNki_Y(String string) {
		nki_Y = string;
	}

	/**
	 * @return
	 */
	public String getNki_M() {
		return nki_M;
	}

	/**
	 * @param string
	 */
	public void setNki_M(String string) {
		nki_M = string;
	}

	/**
	 * @return
	 */
	public String getNki_D() {
		return nki_D;
	}

	/**
	 * @param string
	 */
	public void setNki_D(String string) {
		nki_D = string;
	}


	//--------------------------------------------------------------------


    /**
     * »ÝÌy[WÌZbg
     * 
     * @param currentPage »ÝÌy[W
     */
    public void setCurrentPage(int currentPage) {


        this.currentPage = currentPage;


    }
    /**
     * »ÝÌy[WÌæ¾
     * 
     * @return »ÝÌy[W
     */
    public int getCurrentPage() {


        return this.currentPage;


    }
    
    /**
     * »ÝÌy[WÌZbgi\¦pj
     * 
     * @param disp_currentPage »ÝÌy[Wi\¦pj
     */
    public void setDisp_currentPage(int disp_currentPage) {


        this.disp_currentPage = disp_currentPage;


    }
    /**
     * »ÝÌy[WÌæ¾i\¦pj
     * 
     * @return »ÝÌy[Wi\¦pj
     */
    public int getDisp_currentPage() {
		int i = this.currentPage + 1;
        return i;


    }
    /**
     * y[WÌZbg
     * 
     * @param pageCount y[W
     */
    public void setPageCount(int pageCount) {


        this.pageCount = pageCount;


    }
    /**
     * y[WÌæ¾
     * 
     * @return y[W
     */
    public int getPageCount() {


        return this.pageCount;


    }
    
    /**
     * QÆ³ê½Ååy[WÌZbg
     * 
     * @param referredMaxPage QÆ³ê½Ååy[W
     */
    public void setReferredMaxPage(int referredMaxPage) {
		
		if(this.referredMaxPage < referredMaxPage)
        	this.referredMaxPage = referredMaxPage;

    }
    public void clearReferredMaxPage() {

        	this.referredMaxPage = 0;

    }
    /**
     * QÆ³ê½Ååy[WÌæ¾
     * 
     * @return QÆ³ê½Ååy[W
     */
    public int getReferredMaxPage() {


        return this.referredMaxPage;


    }
     /**
     * Sy[WÌÌZbg
     * 
     * @param allRowCount Sy[WÌ
     */
    public void setAllRowCount(int allRowCount) {


        this.allRowCount = allRowCount;


    }
    /**
     * Sy[WÌÌæ¾
     * 
     * @return Sy[WÌ
     */
    public int getAllRowCount() {


        return this.allRowCount;


    }
    /**
     * VOÌXgiy[WPÊjÌæ¾
     * 
     * @return VOÌXgiy[WPÊj
     */
    public PrsOrderVO[] getVosList(int i) {
    	ArrayList arr = null;
    	try{
//System.out.println("i * MAX_ROW_COUNT : (i + 1) * MAX_ROW_COUNT  "+i * MAX_ROW_COUNT+" : "+(i + 1) * MAX_ROW_COUNT);
//System.out.println("voList.size() : "+voList.size());
			int max = (voList.size() < ((i + 1) * MAX_ROW_COUNT))? voList.size() : (i + 1) * MAX_ROW_COUNT;

			arr = new ArrayList(this.voList.subList(i * MAX_ROW_COUNT, max));
//System.out.println("arr.size() : "+arr.size());
    	}catch(IllegalArgumentException e){
			return null;
		}
        return (PrsOrderVO[])arr.toArray(new PrsOrderVO[arr.size()]);
    }
    
	
	//---------------------------------------------------------------------------------
	public int getSize(){
		return details.size();
	}
	
	public void setSize(int size){
		if(size > details.size()){
			int j = size - this.getSize();			
		  for(int k=0; k<j; k++){
		    details.add(new PrsOrderFormRow());			  	
		  }						
		}else if(size < details.size()){
			for(int i = details.size();i > size;i--){
				details.remove((i-1));
			}
		}
	}

	
	public PrsOrderFormRow getFormRow_R(int i){
		PrsOrderFormRow fr = (PrsOrderFormRow)details.get(i);
		if(fr == null){
			fr = new PrsOrderFormRow();
			details.set(i,fr);
		}
		return fr;
	}
	

	public void clearTableField(){
		for(int i = 0;i < details.size();i++){
			PrsOrderFormRow fr = (PrsOrderFormRow)details.get(i);
			if(fr != null)
	            details.set(i,new PrsOrderFormRow());			
		}	
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

	/**
	 * \¦æªÌæ¾
	 * @return
	 */
	public String getHyoKbn() {
		return hyoKbn;
	}

	/**
	 * \¦æªÌZbg
	 * @param string
	 */
	public void setHyoKbn(String string) {
		hyoKbn = string;
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
	public void setCheck_false(){
		for(int i = 0; i < this.getSize();i++){
			PrsOrderFormRow row  = this.getFormRow_R(i);
			row.setCheck_prs1(false);
			row.setCheck_fuk1(false);
		}
	}
	
	//`FbN³êÄ¢é`FbN{bNXÌCfbNX
	
	public ArrayList getCheck_prs1_index(){
		return check_prs1_index;
	}
	public void setCheck_prs1_index(ArrayList arr){
		check_prs1_index = arr;
	}
	
	public ArrayList getCheck_fuk1_index(){
		return check_fuk1_index;
	}
	public void setCheck_fuk1_index(ArrayList arr){
		check_fuk1_index = arr;
	}
	
	public void setCheck_index(ArrayList arr1,ArrayList arr2){
		check_prs1_index = arr1;
		check_fuk1_index = arr2;
	}
	
	/**
	 * ÞR[hv_EÚæ¾
	 * @return
	 */
	public ArrayList getFukSziList() {
		return fukSziList;
	}

	/**
	 * ÞR[hv_EÚÝè
	 * @param list
	 */
	public void setFukSziList(ArrayList list) {
		fukSziList = list;
	}

	/**
	 * n³ê½Þ¼ÌÌxðæ¾µÄÔµÜ·
	 */
	public String getFukSziNmOptionsLabel(String value){
		String ret = "";
		int i = 0;
		for (Iterator iter = getFukSziList().iterator(); iter.hasNext(); i++) {
			LabelValueBean lvb = (LabelValueBean) iter.next();
			if(lvb.getValue().equals(value)){
				ret = lvb.getLabel().substring(4);
				break;
			}
		}
		return ret;
	}
	//---------------------------------------------------------------------------------

	public ActionErrors validate(ActionMapping mapping,HttpServletRequest req){
//System.out.println("PrsOrderForm--> validate");
		HttpSession session = req.getSession();
		//bZ[WðZbV©çæè­
		session.removeAttribute(CommonConst.INFOMSG_KEY);
		
		if(command.equals("INIT"))
			return null;
		if(command.equals(COMMAND_CLEAR))
			return null;
			
	
		ActionErrors errors = new ActionErrors();

		//­w¦ Ü½Í `[­s ÌÆ«			
		if(command.equals(COMMAND_HACHUSHIJI)||command.equals(COMMAND_DENPYOHAKKOU)){
			//XV À`FbN
			boolean authorityChecked = false;
			authorityChecked = CheckCommon.checkHyoKbn(this.getQueryDaiKaiSkbCod(),this.getHyoKbn());
			if(!authorityChecked){
				errors.add("",new ActionError("errors.cannot","±ÌAJEgÅ","o^^XV^íª"));
				return errors;	
			}
			//Ü¾QÆ³êÄ¢È¢y[Wª Á½ç
			if(this.referredMaxPage < this.voList.size() / MAX_ROW_COUNT){
				errors.add("",new ActionError("errors.page.notreferredto"));
				return errors;
			}
		}
		//­w¦ Ü½Í `[­s Ü½Í O100100 ÌÆ«			
		if(command.equals(COMMAND_HACHUSHIJI)||command.equals(COMMAND_DENPYOHAKKOU)
		||command.equals(NEXT)||command.equals(PREV)){

			DateUtils dateUtils = new DateUtils();
			int today = dateUtils.getDate6Int();
			
			for(int i = 0; i < this.getSize();i++){
				PrsOrderFormRow row  = this.getFormRow_R(i);
				
				if(row == null)
					continue;
				
				if(row.getCheck_prs1()){
//System.out.println("üÍ`FbN");
					/** ÞR[h **/	//2004.03.02 add
  					if(this.getPrs_FukSgn().equals(PrsOrderForm.FUKHACHU)){
						if(row.getFukSziCod().trim().equals("")){
							//t®i\¬}X^Éf[^ª éÆ«ÌÝ
							errors.add("",new ActionError("errors.regist_row",String.valueOf(i+1),"ÞR[h"));
							continue;
						}
					}

					/** ­ **/
					if(row.getPrsHacSuu().trim().equals("")){
						errors.add("",new ActionError("errors.input.required_row",String.valueOf(i+1),"­"));
					}else{			
//						//¼pÌÝ
//						if(!StringUtils.isAsciiDigit(row.getPrsHacSuu().trim())){
//							errors.add("",new ActionError("errors.input.1_row",String.valueOf(i+1),"­","¼p"));
//						}
						//­}CiXüÍÂÉC³ 2005/05/26 add
						try{
							if(!StringUtils.isAscii(row.getPrsHacSuu().trim()))
								throw new NumberFormatException();
							int hacSuu = Integer.parseInt(row.getPrsHacSuu().trim());
						}catch (NumberFormatException e) {
							errors.add("",new ActionError("errors.input.1_row",String.valueOf(i+1),"vX­","¼p"));
						}
					}					

					/** [ú **/
					if(row.getPrsNkiYear().trim().equals("") || row.getPrsNkiMonth().trim().equals("") || row.getPrsNkiDay().trim().equals("")){
						errors.add("",new ActionError("errors.input.required_row",String.valueOf(i+1),"[ú"));
					}else{			
						//útÌÝ
						if(!CheckCommon.validateAsDate(row.getPrsNkiYear(),row.getPrsNkiMonth(),row.getPrsNkiDay())){
							errors.add("",new ActionError("errors.input.1_row",String.valueOf(i+1),"[ú","út"));
						}
//						else{
//							int prsnki = Integer.parseInt(row.getPrsNkiYear().trim() + 
//									   	 StringUtils.lpad(row.getPrsNkiMonth().trim(),2,"0") + 
//										 StringUtils.lpad(row.getPrsNkiDay().trim(),2,"0"));
//							if(prsnki < today){
//								errors.add("",new ActionError("errors.input.1_row",String.valueOf(i+1),"[ú","úÈ~Ìút"));
//							}
//						}2003/07/16 `FbNðO·
					}
					/** ­æ **/
					if(row.getPrsMkrCod().trim().equals("")){
						errors.add("",new ActionError("errors.input.required_row",String.valueOf(i+1),"­æ"));
					}else{			
						//¼ppÌÝ
						if(!StringUtils.isAscii(row.getPrsMkrCod().trim())){
							errors.add("",new ActionError("errors.input.1_row",String.valueOf(i+1),"­æ","¼pp"));
						}
					}
					/** [iæ **/
					if(!row.getNhnMei().trim().equals("")){
						//SpÌÝ
						if(StringUtils.containsAsciiOrHarfWidthKatakana(row.getNhnMei().trim())){
							errors.add("",new ActionError("errors.input.1_row",String.valueOf(i+1),"[iæ","Sp"));
						}
					}
					/** Rg **/
					if(!row.getComment().trim().equals("")){
						//SpÌÝ
						if(StringUtils.containsAsciiOrHarfWidthKatakana(row.getComment().trim())){
							errors.add("",new ActionError("errors.input.1_row",String.valueOf(i+1),"Rg","Sp"));
						}
					}
				}
						
			}
			
//			//ÎÛwèÈµ
//			boolean executeChecked = false;
//			
//			if(command.equals(COMMAND_HACHUSHIJI) || command.equals(COMMAND_DENPYOHAKKOU)){
//					for(int i = 0; i < this.getSize();i++){
//						PrsOrderFormRow row  = this.getFormRow_R(i);
//						if(row != null && row.getCheck_prs1()){
//						executeChecked = true;
//							break;
//						}
//					}
//			}else if(command.equals(NEXT)||command.equals(PREV))//100O100
//				executeChecked = true;
//				
//			if(!executeChecked){
//				errors.add("",new ActionError("errors.input.required","ÎÛ"));
//			}
//			

		}
//		2004.03.08 add
		if(command.equals(COMMAND_KBNHENKO) && (this.getPrs_FukSgn().equals("0"))){
			if(this.getKbn().equals("5")){
				errors.add("",new ActionError("errors.input.prohibited","vX­ÌêAæªÌ»Ì¼"));
				return errors;
			}
		}		
		if(command.equals(COMMAND_PRSHACHU)){
			if(this.getKbn().equals("5")){
				errors.add("",new ActionError("errors.input.prohibited","vX­ÌêAæªÌ»Ì¼"));
				return errors;
			}
			PrsOrderLeftFrame leftFrame = (PrsOrderLeftFrame)session.getAttribute(CommonConst.BEAN_NAME_PRSORDER_LEFT);
			PrsOrderLeftRow formRow_L = leftFrame.getFormRow_L(0);
			if(formRow_L.getKbn().equals("»Ì¼")){
				errors.add("",new ActionError("errors.input.prohibited","vX­ÌêAæªÌ»Ì¼"));
				return errors;			
			}		
		}		
		
		return errors.empty() ? null : errors;
	}
	
	public void reset(ActionMapping maping,HttpServletRequest req){
//System.out.println("reset : "+command);			
		if(!this.command.equals(SUCCESS_DENPYOHAKKOU))
			this.setNewWindow("0");
		if(!this.command.equals(SUCCESS_HACHUSHIJI) 
		&& !this.command.equals(SUCCESS_DENPYOHAKKOU)
//		2004.01.13 add
//		&& !this.command.equals(this.SUCCESS_PREVNEXT) && !this.command.equals(this.SUCCESS_KBNHENKO))
		&& !this.command.equals(SUCCESS_PREVNEXT) 
		&& !this.command.equals(SUCCESS_KBNHENKO) 
		&& !this.command.equals(SUCCESS_NHNCLEAR)
		&& !this.command.equals(SUCCESS_NKICHANGE)) //2005/09/21 add
			this.command = "INIT";
		this.setCheck_false();//`FbN{bNXðZbg
	}
	


}

