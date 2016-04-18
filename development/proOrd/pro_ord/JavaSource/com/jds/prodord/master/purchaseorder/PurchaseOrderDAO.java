/**
* PurchaseOrderDAO  �d��������}�X�^�[�Ɖ�f�[�^�A�N�Z�X�I�u�W�F�N�g�N���X
*
*	�쐬��    2003/05/19
*	�쐬��    �i�m�h�h�j����  ����
* �����T�v    �d��������}�X�^�[�ɃA�N�Z�X����N���X�B
* 
*			2004/04/05(NII)�X
*			�ECR�Ђ̏ꍇ�A�d��������}�X�^�[�ɃA�N�Z�X���Ă����ʂ������Ȃ������̂ł��̏C���B
*           2006/02/15(NII)�c��
*�@�@�@�@�@�@�E�d�b�ԍ��A�X�֔ԍ��̕\�����t�]���Ă����ׂ��̏C���B
*
**/


package com.jds.prodord.master.purchaseorder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.jds.prodord.common.CommonDAO;
import com.jds.prodord.common.StringUtils;

public class PurchaseOrderDAO extends CommonDAO{


	public PurchaseOrderDAO() throws SQLException{
    	super();
	}

//======================================================================================

    //�������s
	public PurchaseOrderVO find(PurchaseOrderVO proto)throws SQLException{

//	System.out.println("SQL�̐ݒ���s���܂�");

		String sql = "SELECT SIRHACCOD,SIRHACNM1,SIRHACNM2,SIRHACADR1,SIRHACADR2,"
					+	"SIRHACTELNO,SIRHACYBNNO2"
					+ " FROM FTBMST03"
					+ " WHERE KAISKBCOD = '" + proto.getDaiKaiSkbCod() + "'"
					+ " AND SIRHACCOD = '" + proto.getHattyuCod() + "'"
					;
//	System.out.println("SQL�̐ݒ芮��");

		PurchaseOrderVO fmVo = null;
		ResultSet rs = null;
		Statement stmt = null;
		ArrayList finded_Arr = new ArrayList();


		//��Ў��ʃR�[�h�@�@//2004.04.05 Del
//		for (int i=0 ; i<proto.getKaiSkbCodList().size(); i++){
//		    if(i == 0){
//		        sql +=  " AND KAISKBCOD IN ('";
//		    }
//		    sql += proto.getKaiSkbCodList().get(i).toString();
//	
//		    if(i == proto.getKaiSkbCodList().size() - 1){
//		        sql  += "')";
//		    }else{
//		        sql  += "','";
//		    }
//		}

		try{
		    stmt = conn.createStatement();

//		System.out.println("SQL�ɂČ������J�n���܂�");
			
	        rs = stmt.executeQuery(sql);

			if(rs.next()){
//			System.out.println("�Y���f�[�^����A�l��Ԃ��܂�");
				
				fmVo = new PurchaseOrderVO();
				
				fmVo.setHattyuCod(rs.getString("SIRHACCOD").trim());	
				fmVo.setOrderName1(StringUtils.removeSuffix(rs.getString("SIRHACNM1"),"�@"));
				fmVo.setOrderName2(StringUtils.removeSuffix(rs.getString("SIRHACNM2"),"�@"));
				fmVo.setOrderAdr1(StringUtils.removeSuffix(rs.getString("SIRHACADR1"),"�@"));
				fmVo.setOrderAdr2(StringUtils.removeSuffix(rs.getString("SIRHACADR2"),"�@"));
//2006/02/15	fmVo.setPostNum(rs.getString("SIRHACTELNO").trim());	
//2006/02/15	fmVo.setTelNum(rs.getString("SIRHACYBUNO2").trim());
				fmVo.setPostNum(rs.getString("SIRHACYBNNO2").trim());   //2006/02/15 add
				fmVo.setTelNum(rs.getString("SIRHACTELNO").trim());     //2006/02/15 add
		

/*				System.out.println("�d��������R�[�h" + rs.getString("SIRHACCOD").trim());	
				System.out.println("�d�������於�̂P" + rs.getString("SIRHACNM1").trim());
				System.out.println("�d�������於�̂Q" + rs.getString("SIRHACNM2").trim());			
				System.out.println("�d��������Z���P" + rs.getString("SIRHACADR1").trim());
				System.out.println("�d��������Z���Q" + rs.getString("SIRHACADR2").trim());			
				System.out.println("�d�b�ԍ�" + rs.getString("SIRHACTELNO").trim());
				System.out.println("�X�֔ԍ�" + rs.getString("SIRHACYBUNO2").trim());	
*/
				fmVo.setFinded_flg(true);

		   }else{  //�������ʂ��O���̂Ƃ�
//			System.out.println("�Y���f�[�^������܂���");
				fmVo = new PurchaseOrderVO();
		   		fmVo.setDaiKaiSkbCod(proto.getDaiKaiSkbCod());
		   		fmVo.setHattyuCod(proto.getHattyuCod().trim());
		   		fmVo.setFinded_flg(false);

			}

		}finally{
			if(rs != null)
				rs.close();
			if(stmt != null)
				stmt.close();
		}	
		return fmVo;		
	}

}

