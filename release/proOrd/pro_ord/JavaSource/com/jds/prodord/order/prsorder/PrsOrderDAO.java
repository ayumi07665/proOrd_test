/**
* PrsOrderDAO  プレス・副資材発注画面データアクセスオブジェクトクラス
*
*	作成日    2003/04/28
*	作成者    （ＮＩＩ）岡田 夏美
* 処理概要    発注履歴テーブル(FTBHAC02)、発注書データ(FTBHAC01)、発注先マスター(FTBMST03)にアクセスするクラス。
* 
*	[変更履歴]
*		2003/05/01 （ＮＩＩ）蛭田 敬子
* 			・「発注指示」「伝票発行」の追加。
*		2003/05/16 （ＮＩＩ）岡田 夏美
* 			・発注先存在チェックに発注先名称の取得追加。
* 		2004/04/02  (NII) 森
* 			・副資材発注の修正(分類コード１複数存在に対応)
* 		2005/05/19 （ＮＩＩ）蛭田
*			・発注書一括出力対応（発注履歴テーブル・発注書データに'USRID'登録）
*		2005/08/26 （ＮＩＩ）蛭田
* 			・発注書内編集方法変更（発注履歴テーブル・発注書データに'RRKTBL,'TAN'登録）
* 		2005/09/14（ＮＩＩ）蛭田
* 			・副資材発注画面、副資材コードをプルダウン項目に変更（VAP社対応）
*/

package com.jds.prodord.order.prsorder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.jds.prodord.common.CommonDAO;
import com.jds.prodord.common.LabelValueBean;
import com.jds.prodord.common.StringUtils;

public class PrsOrderDAO extends CommonDAO{

	
	public PrsOrderDAO() throws SQLException{
    	super();
	}
	
	//発注履歴テーブル登録*******************************************************************
	
	public void insertHac02(
		PrsOrderVO fmVO,
		int upddte,
		int updjkk,
		String syrsuu,
		String seqno,
		String syrzmisgn,
		String cykkbn,
		String syrkbn)
		throws SQLException {
		String sql = "INSERT INTO FTBHAC02 (DAIKAISKBCOD,KAISKBCOD,HACSYODTE"
					 + ",HACSYOBNG,SYRSUU,SEQNO,HACCOD,SYRZMISGN,SINKYUKBN,GYOBNG,KIGBNG,TITKJ"
					 + ",BUNCOD,PRSFUKSZICOD,SETSUU,HACSUU,NYOSUU,NKI,NHNMEIKJ,CYKKBN"
					 + ",UPDKBN,UPDDTE,UPDJKK,CMT,FUKZAISUU,USRID,RRKTBL,TAN,BIKOU2) "
					 + " VALUES (?,?,?,?,?,?,?,?,?,?, ?,?,?,?,?,?,?,?,?,?, ?,?,?,?,?,?,?,?,?)";
					 
		PreparedStatement ps = null;
		try{
			ps = conn.prepareStatement(sql);
				
			ps.setString(1,fmVO.getDaiKaiSkbCod());
			ps.setString(2,fmVO.getKaiSkbCod());
			
			ps.setInt(3,upddte);
			ps.setString(4,"");
			ps.setString(5,syrsuu);
			ps.setString(6,seqno);
			ps.setString(7,fmVO.getPrsMkrCod());
			ps.setString(8,syrzmisgn);
			ps.setString(9,fmVO.getKbn());
			ps.setString(10,"");
			ps.setString(11,fmVO.getKigBng());
			ps.setString(12,fmVO.getTitKj());
			if(syrkbn.equals(PrsOrderForm.PRSHACHU)){
				ps.setString(13,"0");
				ps.setString(14,"");
			}else if(syrkbn.equals(PrsOrderForm.FUKHACHU)){
				ps.setString(13,fmVO.getBunCod());  //本当の分類コードを登録
				ps.setString(14,fmVO.getFukSziCod());
			}
			ps.setString(15,fmVO.getSetSuu());
			ps.setString(16,fmVO.getPrsHacSuu());
			ps.setString(17,"0");
			ps.setInt(18,fmVO.getPrsNki());
			ps.setString(19,fmVO.getNhnMei());
			ps.setString(20,cykkbn);
	
			ps.setString(21,"A");
			ps.setInt(22,upddte);
			ps.setInt(23,updjkk);
			ps.setString(24,fmVO.getComment());
			ps.setString(25,fmVO.getFukSziSuu());//2003/07/28 add
			ps.setString(26,fmVO.getUsrId());//2005/05/19 add
			ps.setString(27,"");	//2005/08/29 add
			ps.setString(28,"0");
			ps.setString(29,fmVO.getBiKou2());
			
			ps.executeUpdate();
			
		}finally{
			if(ps != null)
				ps.close();
		}			
	}
	

	
	public static String NOT_EXIST = "NOT_EXIST";
	//発注先存在チェック****************************************************************
	
	public void hasHacSaki(PrsOrderVO fmVO)throws SQLException{
		String sql = "SELECT SIRHACCOD, SIRHACNM1 || SIRHACNM2 AS HACNM FROM FTBMST03 "
					+ " WHERE KAISKBCOD = ? AND SIRHACCOD = ?"
					;

		ResultSet rs = null;	
		PreparedStatement ps = null;
		
		try{
			ps = conn.prepareStatement(sql);
//System.out.println("発注先存在チェック");
//System.out.println("代表会社 : "+fmVO.getDaiKaiSkbCod());	
//System.out.println("発注先 : "+fmVO.getPrsMkrCod());		
			ps.setString(1,fmVO.getDaiKaiSkbCod());
			ps.setString(2,fmVO.getPrsMkrCod());

			rs = ps.executeQuery();
			
			if(rs.next()){
				fmVO.setHacNm(StringUtils.removeSuffix(rs.getString("HACNM"),"　"));
			}else{
				fmVO.setHacNm(NOT_EXIST);
			}			
		}finally{
			if(rs != null)
				rs.close();
			if(ps != null)
				ps.close();
		}
	}


	/**
	 * 副資材マスター検索（副資材コードプルダウン情報取得）
	 * @param daiKaiSkbCod
	 * @param kaiSkbCod
	 */
	public ArrayList getFukSziList(String daiKaiSkbCod, String kaiSkbCod) 
		throws SQLException {
		
		String query = "";
		ResultSet rs = null;
		PreparedStatement ps = null;
		ArrayList fukSziList = new ArrayList();

		try{
			query = "SELECT FUKSZICOD,FUKSZINMKJ FROM FTBFUK01 " +
					" WHERE DAIKAISKBCOD = ? "+
					" AND KAISKBCOD = ? " +
					" ORDER BY FUKSZICOD";
					
			ps = conn.prepareStatement(query);
			ps.setString(1,daiKaiSkbCod);
			ps.setString(2,kaiSkbCod);
			rs = ps.executeQuery();

			while(rs.next()){
				//副資材一覧（副資材コード・副資材名称）のセット
				String[] fukSzi = new String[2];
				fukSzi[0] = rs.getString("FUKSZICOD");
				fukSzi[1] = StringUtils.removeSuffix(rs.getString("FUKSZINMKJ"),"　");
				fukSziList.add(new LabelValueBean(fukSzi[0]+" "+fukSzi[1],fukSzi[0]));
			}
		}finally{
			if(ps != null) ps.close();
			if(rs != null) rs.close();
		}

		return fukSziList;

	}
}

