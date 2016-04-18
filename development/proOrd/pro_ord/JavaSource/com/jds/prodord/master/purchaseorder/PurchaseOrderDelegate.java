/**
* PurchaseOrderDelegate  仕入発注先マスター照会処理実行クラス
*
*	作成日    2003/05/19
*	作成者    （ＮＩＩ）村上  博基
* 処理概要    仕入発注先マスター照会処理を実行するクラス。
* 
*/

package com.jds.prodord.master.purchaseorder;



import java.sql.*;


public class PurchaseOrderDelegate {

	//*** 検索  ***

	public PurchaseOrderVO doFind(PurchaseOrderVO fmVO)throws SQLException{

//System.out.println("Delegateにて設定を行います");
		PurchaseOrderDAO fmDao = null;
		PurchaseOrderForm fmForm = null;

		PurchaseOrderVO finded = null;

		try{

			fmDao = new PurchaseOrderDAO();
			finded = fmDao.find(fmVO);									

			   
		}finally{
			try{
				fmDao.close();
			}catch(SQLException sqle2){
				sqle2.printStackTrace();
			}
		}
		return finded;
	}

}
