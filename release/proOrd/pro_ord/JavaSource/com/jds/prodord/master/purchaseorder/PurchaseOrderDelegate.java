/**
* PurchaseOrderDelegate  �d��������}�X�^�[�Ɖ�����s�N���X
*
*	�쐬��    2003/05/19
*	�쐬��    �i�m�h�h�j����  ����
* �����T�v    �d��������}�X�^�[�Ɖ�������s����N���X�B
* 
*/

package com.jds.prodord.master.purchaseorder;



import java.sql.*;


public class PurchaseOrderDelegate {

	//*** ����  ***

	public PurchaseOrderVO doFind(PurchaseOrderVO fmVO)throws SQLException{

//System.out.println("Delegate�ɂĐݒ���s���܂�");
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
