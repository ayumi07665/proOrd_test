/**
* IndicatePrintDAO  発注書出力指示データアクセスオブジェクトクラス
*
*	作成日    2003/04/23
*	作成者    （ＮＩＩ）岡田 夏美
* 処理概要    発注先マスター（FTBMST03）、発注履歴（FTBHAC02）、発注書データ（FTBHAC01）にアクセスするクラス。
* 
*	[変更履歴]
*       2003/06/05（ＮＩＩ）蛭田 敬子
* 			・「再出力」の条件項目（発注書日付、発注先コード）追加。
* 		2003/07/07
* 			・データ交換フラグのセット追加。
* 		2003/08/26 （ＮＩＩ）岡田 夏美
* 			・新品番マスター(HIN02)対応 追加
* 		2004/03/04 (ＮＩＩ）森
* 			・VAPのときは必ず副資材コードが表示されるように修正
* 		2004/03/29 (NII) 森
* 			・品番マスタのテーブル名変更
* 		2005/05/23（ＮＩＩ）蛭田
* 			・発注書一括出力対応（ユーザーＩＤの追加）
* 		2005/09/09（ＮＩＩ）蛭田
* 			・VAPの場合、副資材ｺｰﾄﾞ複数使用により副資材名称取得時、副資材ｺｰﾄﾞを条件に追加
*
*/

package com.jds.prodord.indicate.indicateprint;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.jds.prodord.common.CommonDAO;
import com.jds.prodord.common.DataFormatUtils;

public class IndicatePrintDAO extends CommonDAO{

	
	public IndicatePrintDAO() throws SQLException{
    	super();
	}
	

	//発注履歴検索***********************************************************************
	
	public IndicatePrintVO[] findHac02(IndicatePrintVO queryVO, boolean isSaiSyryk)throws SQLException{

		String sql = "SELECT KAISKBCOD,HACSYODTE,HACSYOBNG,SYRSUU,SEQNO,GYOBNG,HACCOD,"
					+"PRSFUKSZICOD "
					+ " FROM FTBHAC02"
					+ " WHERE DAIKAISKBCOD = '" + queryVO.getDaiKaiSkbCod() + "'"
					;
					

		//会社識別コード
        for(int i=0; i<queryVO.getQueryKaiSkbCodList().size(); i++){
        	if(i == 0){
        		sql +=  " AND KAISKBCOD IN ('";
        	}
        	sql += queryVO.getQueryKaiSkbCodList().get(i).toString();
          
        	if(i == queryVO.getQueryKaiSkbCodList().size() - 1){
        		sql  += "')";
        	}else{
        		sql  += "','";
        	}
        }

        //伝票発行
        if(!isSaiSyryk){
            //発注先コード
    		DataFormatUtils.removeBlankElement(queryVO.getHacCod_arr());
            for(int i=0; i<queryVO.getHacCod_arr().size(); i++){
            	if(i == 0){
            		sql +=  " AND HACCOD IN ('";
            	}
            	sql += queryVO.getHacCod_arr().get(i).toString();
              
            	if(i == queryVO.getHacCod_arr().size() - 1){
            		sql  += "')";
            	}else{
            		sql  += "','";
            	}
            }
        	
        	//発注書番号(ブランクのもの)
        	sql +=  " AND HACSYOBNG = ''";

        //再発行
        }else{
            //発注先コード
            if(!queryVO.getHacCod().equals("")){
            	sql +=  " AND HACCOD = '" + queryVO.getHacCod() +"'";
            }

        	//発注書番号(入力値かつブランク以外)
        	sql +=  " AND HACSYOBNG <> ''";
	        if(!queryVO.getHacSyoBng_to().equals("")){
	        	sql +=  " AND HACSYOBNG BETWEEN '" + DataFormatUtils.formatHacSyoBng(queryVO.getHacSyoBng_frm()) +"' AND '" + DataFormatUtils.formatHacSyoBng(queryVO.getHacSyoBng_to()) + "'";
	        }else{
	        	if(!queryVO.getHacSyoBng_frm().equals(""))
	        		sql +=  " AND HACSYOBNG = '" + DataFormatUtils.formatHacSyoBng(queryVO.getHacSyoBng_frm()) + "'";
	        }

	        //2003/06/05
			//発注書日付
			if(!queryVO.getHacSyoDte_to().equals("")){
	        	sql +=  " AND HACSYODTE BETWEEN " + queryVO.getHacSyoDte_frm() +" AND " + queryVO.getHacSyoDte_to() + "";
	        }else{
	        	if(!queryVO.getHacSyoDte_frm().equals(""))
	        		sql +=  " AND HACSYODTE = " + queryVO.getHacSyoDte_frm() + "";
	        }
			//end
        }
		   
		sql +=  " AND USRID = '" + queryVO.getUsrId() +"'";//2005/05/19 USRID add
         
        sql +=  " ORDER BY HACCOD, HACSYODTE, HACSYOBNG, GYOBNG";
			
		ArrayList finded_Arr = new ArrayList();
		ResultSet rs = null;
		Statement stmt = null;
		try{
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);			
			while(rs.next()){
				IndicatePrintVO fmVO = new IndicatePrintVO();
				fmVO.setDaiKaiSkbCod(queryVO.getDaiKaiSkbCod());
				fmVO.setKaiSkbCod(rs.getString("KAISKBCOD").trim());
				fmVO.setHacSyoDte(rs.getString("HACSYODTE").trim());
				fmVO.setHacSyoBng(rs.getString("HACSYOBNG").trim());
				fmVO.setGyoBng(rs.getString("GYOBNG").trim());
				fmVO.setHacCod(rs.getString("HACCOD").trim());
				fmVO.setSyrSuu(rs.getString("SYRSUU").trim());
				fmVO.setSeqNo(rs.getString("SEQNO").trim());
				fmVO.setUsrId(queryVO.getUsrId());//2005/05/23 add
				fmVO.setFukSziCod(rs.getString("PRSFUKSZICOD").trim());//2005/09/09 add
				finded_Arr.add(fmVO);
			}
		}finally{
			if(rs != null)
				rs.close();
			if(stmt != null)
				stmt.close();
		}
		return (IndicatePrintVO[])finded_Arr.toArray(new IndicatePrintVO[finded_Arr.size()]);
							
	}			
	
	//発注書データ存在チェック****************************************************************
	
	public boolean hasHac01(IndicatePrintVO fmVO)throws SQLException{
		
		String sql = "SELECT KIGBNG FROM FTBHAC01 "
					+ " WHERE DAIKAISKBCOD = ? AND KAISKBCOD = ? AND HACSYODTE = ?"
					+ " AND HACSYOBNG = ? AND SYRSUU = ? AND SEQNO = ? AND GYOBNG = ?"
					+ " AND USRID = ?";

		ResultSet rs = null;	
		PreparedStatement ps = null;	
		
		try{
			ps = conn.prepareStatement(sql);
			
			ps.setString(1,fmVO.getDaiKaiSkbCod());
			ps.setString(2,fmVO.getKaiSkbCod());
			ps.setString(3,fmVO.getHacSyoDte());
			ps.setString(4,DataFormatUtils.formatHacSyoBng(fmVO.getHacSyoBng()));
			ps.setString(5,fmVO.getSyrSuu());
			ps.setString(6,fmVO.getSeqNo());
			ps.setString(7,DataFormatUtils.formatGyoBng(fmVO.getGyoBng()));
			ps.setString(8,fmVO.getUsrId());//2005/05/19 add

			rs = ps.executeQuery();
			
			if(rs.next()){
				return true;
			}else{
				return false;
			}			
		}finally{
			if(rs != null)
				rs.close();
			if(ps != null)
				ps.close();
		}
		
	}	
	
	//発注履歴テーブル更新*******************************************************************

	public void updateHac02(IndicatePrintVO fmVO,int upddte,int updjkk)throws SQLException{
		String sql = "UPDATE FTBHAC02 SET SYRZMISGN = ?,UPDKBN = ?,UPDDTE = ?,UPDJKK = ?"
					 + " WHERE DAIKAISKBCOD = ? AND KAISKBCOD = ?"
				     + " AND HACSYODTE = ? AND SYRSUU = ? AND SEQNO = ?";

		PreparedStatement ps = null;
		try{
			ps = conn.prepareStatement(sql);
	
			ps.setString(1,"1");
			ps.setString(2,"U");
			ps.setInt(3,upddte);
			ps.setInt(4,updjkk);
			ps.setString(5,fmVO.getDaiKaiSkbCod());
			ps.setString(6,fmVO.getKaiSkbCod());
			ps.setString(7,fmVO.getHacSyoDte());
			ps.setString(8,fmVO.getSyrSuu());
			ps.setString(9,fmVO.getSeqNo());
			ps.executeUpdate();


		}finally{
			if(ps != null)
				ps.close();
		}			
	}


}




