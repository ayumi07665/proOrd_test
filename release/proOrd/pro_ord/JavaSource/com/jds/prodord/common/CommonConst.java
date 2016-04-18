package com.jds.prodord.common;

public interface CommonConst {

	String DS_JNDI_NAME = "java:comp/env/pro_ord";

	//-----------------------------------------------------------

	String user = "user";
	String menuControl = "menuControl";
	String masterMenuControl = "masterMenuControl";
	String INFOMSG_KEY = "INFOMSG_KEY";
	String ERROR_COUNT = "errorCount";

	String TEIANSUUREFER_VO = "TEIANSUUREFER_VO";
	String MANUALORDER_VO = "MANUALORDER_VO";
	String ORDERHISTORY_VO = "ORDERHISTORY_VO";
	String PRSORDER_VOS = "PRSORDER_VOS";
	String PO_QUERY_VO = "PO_QUERY_VO";

	String FORMBEAN_NAME_TEIANSUU = "teiansuuReferForm";
	String BEAN_NAME_PRINT = "printOrdersPages";
	String BEAN_NAME_IKKATSU_LEFT = "ikkatsuReferLeftFrame";
	String BEAN_NAME_PRSORDER_LEFT = "prsOrderLeftFrame";
	String BEAN_NAME_LEFTFORM = "leftForm";
	String FORMBEAN_NAME_IKKATSU = "ikkatsuReferForm";
	String FORMBEAN_NAME_MANUALORDER = "manualOrderForm";
	String FORMBEAN_NAME_MANUALORDERPASTE = "manualOrderPasteForm";
	String FORMBEAN_NAME_PRSORDER = "prsOrderForm";
	String FORMBEAN_NAME_INDICATEPRINT = "indicatePrintForm";
	String FORMBEAN_NAME_ORDERHIST_TOP = "orderHistoryTopForm";
	String FORMBEAN_NAME_ORDERHIST_MID = "orderHistoryMidForm";
	String FORMBEAN_NAME_ARTICLENUMBER = "ArticleNumberForm";

	String FORMBEAN_NAME_PURCHASEORDER = "PurchaseOrderForm";
	String FORMBEAN_NAME_STOCKDAYS = "StockDaysForm";
	String FORMBEAN_NAME_KETRAK = "ketRakForm";
	String FORMBEAN_NAME_SUBMATESTOCK = "SubMateStockForm";
	String FORMBEAN_NAME_SUBMATEMASTER = "SubMateMasterForm";
	String FORMBEAN_NAME_SUBMATEPMASTER = "SubMatePMasterForm";
	String FORMBEAN_NAME_NYUKOSUUADJUST = "nyukoSuuAdjustForm";
	String FORMBEAN_NAME_KETSZITBL = "ketSziTblForm";
	String FORMBEAN_NAME_FZKHINKSI = "fzkHinKsiForm";

	//TODO 会社追加対応(パターンの追加)
	//----発注書パターン-----------------------------------------------------  
	String PTN01 = "01"; //VAP
	String PTN02 = "02"; //CR
	String PTN03 = "03"; //K
	String PTN04 = "04"; //FX
	String PTN05 = "05"; //LAN(BAN)

	//TODO 会社追加対応(会社の追加)
	//----会社---------------------------------------------------------------
	String KAICOD_K = "K";
	String KAICOD_VAP = "VAP";
	String KAICOD_CR = "CR";
	String KAICOD_FX = "FX";
	String KAICOD_LAN = "LAN";
	String KAICOD_BAN = "BAN";

	//----区分---------------------------------------------------------------
	final String KYUHU = "0";
	final String SINPU = "1";
	final String SAMPLE = "2";
	final String DEMOBAN = "3";
	final String TOKHAN = "4";
	final String OTHERS = "5";
	final String HICATALOG = "6";

}
