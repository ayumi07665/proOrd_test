/**
* PurchaseOrderDAO  仕入発注先マスター照会データアクセスオブジェクトクラス
*
*	作成日    2003/05/19
*	作成者    （ＮＩＩ）村上  博基
* 処理概要    仕入発注先マスターにアクセスするクラス。
* 
*			2004/04/05(NII)森
*			・CR社の場合、仕入発注先マスターにアクセスしても結果が得られなかったのでその修正。
*           2006/02/15(NII)田中
*　　　　　　・電話番号、郵便番号の表示が逆転していた為その修正。
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

    //検索実行
	public PurchaseOrderVO find(PurchaseOrderVO proto)throws SQLException{

//	System.out.println("SQLの設定を行います");

		String sql = "SELECT SIRHACCOD,SIRHACNM1,SIRHACNM2,SIRHACADR1,SIRHACADR2,"
					+	"SIRHACTELNO,SIRHACYBNNO2"
					+ " FROM FTBMST03"
					+ " WHERE KAISKBCOD = '" + proto.getDaiKaiSkbCod() + "'"
					+ " AND SIRHACCOD = '" + proto.getHattyuCod() + "'"
					;
//	System.out.println("SQLの設定完了");

		PurchaseOrderVO fmVo = null;
		ResultSet rs = null;
		Statement stmt = null;
		ArrayList finded_Arr = new ArrayList();


		//会社識別コード　　//2004.04.05 Del
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

//		System.out.println("SQLにて検索を開始します");
			
	        rs = stmt.executeQuery(sql);

			if(rs.next()){
//			System.out.println("該当データあり、値を返します");
				
				fmVo = new PurchaseOrderVO();
				
				fmVo.setHattyuCod(rs.getString("SIRHACCOD").trim());	
				fmVo.setOrderName1(StringUtils.removeSuffix(rs.getString("SIRHACNM1"),"　"));
				fmVo.setOrderName2(StringUtils.removeSuffix(rs.getString("SIRHACNM2"),"　"));
				fmVo.setOrderAdr1(StringUtils.removeSuffix(rs.getString("SIRHACADR1"),"　"));
				fmVo.setOrderAdr2(StringUtils.removeSuffix(rs.getString("SIRHACADR2"),"　"));
//2006/02/15	fmVo.setPostNum(rs.getString("SIRHACTELNO").trim());	
//2006/02/15	fmVo.setTelNum(rs.getString("SIRHACYBUNO2").trim());
				fmVo.setPostNum(rs.getString("SIRHACYBNNO2").trim());   //2006/02/15 add
				fmVo.setTelNum(rs.getString("SIRHACTELNO").trim());     //2006/02/15 add
		

/*				System.out.println("仕入発注先コード" + rs.getString("SIRHACCOD").trim());	
				System.out.println("仕入発注先名称１" + rs.getString("SIRHACNM1").trim());
				System.out.println("仕入発注先名称２" + rs.getString("SIRHACNM2").trim());			
				System.out.println("仕入発注先住所１" + rs.getString("SIRHACADR1").trim());
				System.out.println("仕入発注先住所２" + rs.getString("SIRHACADR2").trim());			
				System.out.println("電話番号" + rs.getString("SIRHACTELNO").trim());
				System.out.println("郵便番号" + rs.getString("SIRHACYBUNO2").trim());	
*/
				fmVo.setFinded_flg(true);

		   }else{  //検索結果が０件のとき
//			System.out.println("該当データがありません");
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

