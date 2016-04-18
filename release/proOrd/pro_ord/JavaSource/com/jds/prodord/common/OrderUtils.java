package com.jds.prodord.common;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class OrderUtils {
	
	private Connection conn;

	/**
	 * 渡されたDAOのコネクションを取り出してセットします。
	 * @param dao
	 * @throws SQLException
	 */
	public OrderUtils(CommonDAO dao) throws SQLException{
		conn = dao.getConnection();//渡されたDAOのConnectionを取り出してセット
	}
	
	public static int PRSHAC = 0;
	public static int FUKHAC = 1;
	/**
	 * 在庫テーブル(FTBZAI01)更新メソッド
	 * 
	 * @param daiKaiSkbCod,kigBng,hacSuu,upddte,updjkk,sgn
	 * @throws java.sql.SQLException 
	 */
	public void updateZai01(
		String daiKaiSkbCod,
		String kigBng,
		int hacSuu,
		int upddte,
		int updjkk,
		int sgn)
		throws SQLException {

		Statement stmt = null;
		
		try{
			
			String sql = "UPDATE FTBZAI01 SET ";
				if(sgn == PRSHAC)
					sql += "PRSMNYKEI = PRSMNYKEI + " + hacSuu
						 + ", PRSHACRUI = PRSHACRUI + " + hacSuu
						 + ", FUKZAISUU = FUKZAISUU - " + hacSuu;
				else if(sgn == FUKHAC)
					sql += "FUKMNYKEI = FUKMNYKEI + " + hacSuu
						 + ", FUKHACRUI = FUKHACRUI + " + hacSuu;
						 
				sql += ",UPDKBN = 'U', UPDDTE = " + upddte + ", UPDJKK = " + updjkk
					 + " WHERE KAISKBCOD = '" + daiKaiSkbCod + "'"
					 + " AND KIGBNG = '" + kigBng + "'";
//System.out.println("Zai01 sql："+sql);
			
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			
		}catch(SQLException sqle){
			conn.rollback();
			SystemException se = new SystemException(sqle);
			se.printStackTrace();
			throw sqle;
		}finally{
			if(stmt != null)
				stmt.close();
		}
	}
	
	/**
	 * 副資材在庫テーブル(FTBHIN12)更新メソッド①
	 * 
	 * @param daiKaiSkbCod,kaiSkbCod,kigBng,smpKbn,sabun,upddte,updjkk
	 * @throws java.sql.SQLException 
	 */
	public void updateHin12(
		String daiKaiSkbCod,
		String kaiSkbCod,
		String kigBng,
		String smpKbn,
		int sabun,
		int upddte,
		int updjkk)
		throws SQLException {

		Statement stmt = null;
		
		try{
			
			String sql = "UPDATE FTBHIN12 SET FUKSZISUU = FUKSZISUU + " + sabun
					 + ",UPDKBN = 'U',UPDDTE = " + upddte + ",UPDJKK = " + updjkk
					 + " WHERE DAIKAISKBCOD = '" + daiKaiSkbCod + "'"
					 + " AND KAISKBCOD = '" + kaiSkbCod + "'"
					 + " AND KIGBNG = '" + kigBng + "' AND SMPKBN = '" + smpKbn +"'";
//System.out.println("Hin12 sql："+sql);
			
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			
		}catch(SQLException sqle){
			conn.rollback();
			SystemException se = new SystemException(sqle);
			se.printStackTrace();
			throw sqle;
		}finally{
			if(stmt != null)
				stmt.close();
		}
	}
	
	/**
	 * 副資材在庫テーブル(FTBHIN12)更新メソッド②
	 * 
	 * @param daiKaiSkbCod,kaiSkbCod,kigBng,smpKbn,fukSziCod,sabun,upddte,updjkk
	 * @throws java.sql.SQLException 
	 */
	public void updateHin12(
		String daiKaiSkbCod,
		String kaiSkbCod,
		String kigBng,
		String smpKbn,
		String fukSziCod,
		int sabun,
		int upddte,
		int updjkk)
		throws SQLException {

		Statement stmt = null;
		
		try{
			
			String sql = "UPDATE FTBHIN12 SET FUKSZISUU = FUKSZISUU + " + sabun
					 + ",UPDKBN = 'U',UPDDTE = " + upddte + ",UPDJKK = " + updjkk
					 + " WHERE DAIKAISKBCOD = '" + daiKaiSkbCod + "'"
					 + " AND KAISKBCOD = '" + kaiSkbCod + "' AND FUKSZICOD = '" + fukSziCod + "'"
					 + " AND KIGBNG = '" + kigBng + "' AND SMPKBN = '" + smpKbn +"'";
//System.out.println("Hin12 sql："+sql);
			
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			
		}catch(SQLException sqle){
			conn.rollback();
			SystemException se = new SystemException(sqle);
			se.printStackTrace();
			throw sqle;
		}finally{
			if(stmt != null)
				stmt.close();
		}
	}
	
	/**
	 * 副資材在庫テーブル(FTBHIN12)登録メソッド
	 * @param daiKaiSkbCod,kaiSkbCod,kigBng,fukSziCod,hacSuu,bunCod,smpKbn,upddte,updjkk
	 * @throws java.sql.SQLException 
	 */
	public void insertHin12(
		String daiKaiSkbCod,
		String kaiSkbCod,
		String kigBng,
		String fukSziCod,
		int hacSuu,
		String bunCod,
		String smpKbn,
		int upddte,
		int updjkk)
		throws SQLException {
		String sql = "INSERT INTO FTBHIN12 (DAIKAISKBCOD,KAISKBCOD,KIGBNG"
					 + ",FUKSZICOD,FUKSZISUU,UPDKBN,UPDDTE,UPDJKK,BUNCOD,SMPKBN) "
					 + " VALUES (?,?,?,?,?,?,?,?,?,?)";
					 
		PreparedStatement ps = null;
		
		try{

			ps = conn.prepareStatement(sql);
				
			ps.setString(1,daiKaiSkbCod);
			ps.setString(2,kaiSkbCod);
			ps.setString(3,kigBng);
			ps.setString(4,fukSziCod);
			ps.setInt(5,hacSuu);
			ps.setString(6,"A");
			ps.setInt(7,upddte);
			ps.setInt(8,updjkk);
			ps.setString(9,bunCod);
			ps.setString(10,smpKbn);
				
			ps.executeUpdate();
			
		}catch(SQLException sqle){
			conn.rollback();
			SystemException se = new SystemException(sqle);
			se.printStackTrace();
			throw sqle;
		}finally{
			if(ps != null)
				ps.close();
		}		
	}

	/**
	 * 副資材在庫テーブル(FTBHIN12)検索メソッド
	 * 
	 * @param daiKaiSkbCod,kaiSkbCod,kigBng,bunCod,fukSziCod,smpKbn
	 * @throws java.sql.SQLException 
	 */

	public String findHin12(
		String daiKaiSkbCod,
		String kaiSkbCod,
		String kigBng,
		String bunCod,
		String fukSziCod,
		String smpKbn)
		throws SQLException {

		String sql = "SELECT FUKSZISUU FROM FTBHIN12"
					+ " WHERE DAIKAISKBCOD = ? AND KAISKBCOD = ? AND KIGBNG = ?"
//2005/06/08 del   	+ " AND BUNCOD = ? AND FUKSZICOD = ? AND SMPKBN = ?";
					+ " AND FUKSZICOD = ? AND SMPKBN = ?";
    	
		String fukSziSuu = "";
		ResultSet rs = null;	
		PreparedStatement ps = null;	
		
		try{

			ps = conn.prepareStatement(sql);
			
			ps.setString(1,daiKaiSkbCod);
			ps.setString(2,kaiSkbCod);
			ps.setString(3,kigBng);
//			ps.setString(4,bunCod);
			ps.setString(4,fukSziCod);
			ps.setString(5,smpKbn);
			
			rs = ps.executeQuery();
			
			if(rs.next()){
				fukSziSuu = rs.getString("FUKSZISUU").trim();
			}else{
				fukSziSuu = null;
			}
			
		}catch(SQLException sqle1){
			SystemException se = new SystemException(sqle1);
			se.printStackTrace();	  
			throw sqle1;	
		}finally{
			try{
				if(rs != null)
					rs.close();
				if(ps != null)
					ps.close();
			}catch(SQLException sqle){
				SystemException se = new SystemException(sqle);
				se.printStackTrace();
			}
		}			
		return fukSziSuu;
	}
	
	/**
	 * 副資材在庫テーブル(FTBHIN12)複数登録メソッド<br>
	 * 渡された情報に一致するHIN12の存在チェックを行い、なければ登録する
	 * @param daiKaiSkbCod,kaiSkbCod,kigBng,fukSziCodArr,smpKbn,bunCodArr,upddte,updjkk
	 * @throws java.sql.SQLException 
	 */
	public void insertHin12List(
		String daiKaiSkbCod,
		String kaiSkbCod,
		String kigBng,
		String smpKbn,
		ArrayList fukSziCodArr,
		ArrayList bunCodArr,
		int upddte,
		int updjkk)
		throws SQLException {
		
		for (int i = 0; i < fukSziCodArr.size(); i++) {
			
			String fukSziSuu =
				findHin12(
					daiKaiSkbCod,
					kaiSkbCod,
					kigBng,
					(String) bunCodArr.get(i),
					(String) fukSziCodArr.get(i),
					smpKbn);
					
			//HIN12が存在しなかったら登録
			if(fukSziSuu == null){
				insertHin12(
					daiKaiSkbCod,
					kaiSkbCod,
					kigBng,
					(String) fukSziCodArr.get(i),
					0,
					(String) bunCodArr.get(i),
					smpKbn,
					upddte,
					updjkk);
			}
		}
		
	}
	
	/**
	 * 付属品構成マスター(FTBMST08)を検索、副資材コード・仕入先のArrayListを返すメソッド
	 * 
	 * @param daiKaiSkbCod,kaiSkbCod,kigbng
	 * @throws java.sql.SQLException 
	 */
//2004.02.25 add 
	public HashMap findFukSzi_SirSkCodArr(
		String daiKaiSkbCod,
		String kaiSkbCod,
		String kigbng)
		throws SQLException {

		String sql = "SELECT FUKTBL,SIRTBL FROM FTBMST08 WHERE DAIKAISKBCOD = ? "
					+ " AND KAISKBCOD = ? AND KIGBNG = ?"
					;
					
		ArrayList fukSziCod_arr = new ArrayList();
		ArrayList fukSziHacSaki_arr = new ArrayList();
		String fukSziTbl = "";
		String SirskTbl = "";
		ResultSet rs = null;	
		PreparedStatement ps = null;
		boolean exist_mst08 = false;
		HashMap resultMap = new HashMap();
		
		try{

			ps = conn.prepareStatement(sql);
			ps.setString(1,daiKaiSkbCod);
			ps.setString(2,kaiSkbCod);
			ps.setString(3,kigbng);
			
			rs = ps.executeQuery();
		
			if(rs.next()){
				fukSziTbl = rs.getString("FUKTBL").trim();
//				SirskTbl = rs.getString("SIRTBL").trim();
				SirskTbl = rs.getString("SIRTBL");
				exist_mst08 = true;
			}
					
			if(exist_mst08){
				//副資材コード分解
				for(int i = 0; i<fukSziTbl.length(); i+=3){
					try{
						fukSziCod_arr.add(fukSziTbl.substring(i, i+3));
					}catch(IndexOutOfBoundsException e){
						break;
					}
				}
				//副資材コードが分解できなかったら
				if(fukSziCod_arr.size() == 0)
					fukSziCod_arr = null;
					
				//仕入先コード分解
				for(int i = 0; i<SirskTbl.length(); i+=6){
					try{
						if(SirskTbl.substring(i, i+6).trim().equals(""))
							break;
						fukSziHacSaki_arr.add(SirskTbl.substring(i, i+6));
					}catch(IndexOutOfBoundsException e){
						break;
					}
				}
				//仕入先コードが分解できなかったら
				if(fukSziHacSaki_arr.size() == 0)
					fukSziHacSaki_arr = null;

			}else{ //付属品構成テーブルが存在しなかったら
				fukSziCod_arr = null;
				fukSziHacSaki_arr = null;
			}

			resultMap.put("fukSziCod_arr",fukSziCod_arr);
			resultMap.put("fukSziHacSaki_arr",fukSziHacSaki_arr);
			
		}catch(SQLException sqle1){
			SystemException se = new SystemException(sqle1);
			se.printStackTrace();
			throw sqle1;
	  	
		}finally{
			try{
				if(rs != null)
					rs.close();
				if(ps != null)
					ps.close();
			}catch(SQLException sqle){
				SystemException se = new SystemException(sqle);
				se.printStackTrace();
				throw sqle;
			}
		}		
		return resultMap;
	}
	
	//TODO 会社追加対応(どのパターンで取得するか)
	/**
	 * 副資材発注先・名称、分類コードを取得してVOにセットします。
	 * @param fmVO
	 * @throws SQLException
	 */
	public void findFukSziHacSaki(CommonOrderVO fmVO) throws SQLException {
		
		//***********************************************************************/
		// Kの場合
		// 副資材発注は行なわない
		//***********************************************************************/
		if (fmVO.getDaiKaiSkbCod().equals(CommonConst.KAICOD_K))
			return;

	
		ResultSet rs = null;
		PreparedStatement ps = null;
		
		//***********************************************************************/
		// VAPの場合  
		// 副資材発注先 : 品番マスターのFUKSZIMKR(発注先マスターに存在する場合)
		// 副資材コード : 副資材マスターから取得(BUNCOD=1)
		//***********************************************************************/
		if (fmVO.getDaiKaiSkbCod().equals(CommonConst.KAICOD_VAP)){

			//発注先マスター検索
			String sql =
				"SELECT SIRHACCOD FROM FTBMST03 "
					+ " WHERE KAISKBCOD = ? AND SIRHACCOD = ?";
	
			boolean exist_mst03 = false;
			try {
				ps = conn.prepareStatement(sql);
				ps.setString(1, fmVO.getDaiKaiSkbCod());
				ps.setString(2, fmVO.getFukSziMkr());
	
				rs = ps.executeQuery();
	
				if (rs.next()) {
					//発注先マスターに存在すれば、発注先コードを副資材発注先とする		
					fmVO.setFukSziHacSaki(rs.getString("SIRHACCOD").trim());
					exist_mst03 = true;
				}
			} finally {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
			}
			try {
				//副資材コード、分類コードの取得
				ArrayList fukSziCod_arr = new ArrayList();
				ArrayList bunCod_arr = new ArrayList();

				//副資材マスター検索
				sql =
					"SELECT FUKSZINMKJ,FUKSZICOD,BUNCOD FROM FTBFUK01 "
						+ " WHERE DAIKAISKBCOD = ? AND KAISKBCOD = ? AND BUNCOD = '1'"
						+ " ORDER BY FUKSZICOD "; // 2005.09.09 add

				ps = conn.prepareStatement(sql);

				ps.setString(1, fmVO.getDaiKaiSkbCod());
				ps.setString(2, fmVO.getKaiSkbCod()); // 2004.03.04 add

				rs = ps.executeQuery();
				if (rs.next()) {
					fmVO.setFukSziCod(rs.getString("FUKSZICOD").trim());
					fmVO.setFukSziNm(StringUtils.removeSuffix(rs.getString("FUKSZINMKJ"), "　"));
					fmVO.setBunCod(rs.getString("BUNCOD").trim());
					
					fukSziCod_arr.add(rs.getString("FUKSZICOD").trim());
					bunCod_arr.add(rs.getString("BUNCOD").trim());
				}
				fmVO.setBunCod_arr(bunCod_arr); //分類コードarrをVOにセット
				fmVO.setFukSziCod_arr(fukSziCod_arr);//副資材コードarrをVOにセット
				fmVO.setExsitFuk01(true);
				return;
				
			} finally {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
			}
		}
		//***********************************************************************/
		// CR, FX, LANの場合 
		// 副資材発注先 : 品番マスターのFUKSZIMKR(発注先マスターに存在する場合)
		// 副資材コード : 副資材マスターから取得(BUNCOD=1)
		//***********************************************************************/
		else if (fmVO.getKaiSkbCod().equals(CommonConst.KAICOD_CR)
				|| fmVO.getDaiKaiSkbCod().equals(CommonConst.KAICOD_FX)
				|| fmVO.getKaiSkbCod().equals(CommonConst.KAICOD_LAN)) {
			
			//---発注先マスターになかったとき---------------------------------------------------
			//付属品構成マスター検索・副資材コードのセットを取得
			HashMap resultMap =
				findFukSzi_SirSkCodArr(
					fmVO.getDaiKaiSkbCod(),
					fmVO.getKaiSkbCod(),
					fmVO.getKigBng());
			ArrayList fukSziCod_arr = (ArrayList) resultMap.get("fukSziCod_arr");
			ArrayList fukSziHacSaki_arr = (ArrayList) resultMap.get("fukSziHacSaki_arr");

			if (fukSziCod_arr != null) {
				fmVO.setFukSziPtn(fmVO.getFukSziMkr()); //副資材コードをVOにセット
			} else {
				fukSziCod_arr = new ArrayList();
				if(!fmVO.getFukSziMkr().equals("")){
					fukSziCod_arr.add(fmVO.getFukSziMkr());//副資材メーカーコードを副資材コードとする
				}	
			}
			fmVO.setFukSziCod_arr(fukSziCod_arr); //副資材コードarrをVOにセット
			fmVO.setFukSziHacSaki_arr(fukSziHacSaki_arr);//仕入先コードarrをVOにセット 2004.02.25 add
			
			//分類コード、副資材名称の取得
			ArrayList bunCod_arr = new ArrayList();
			ArrayList fukSziNm_arr = new ArrayList();

			for(int i = 0; i<fukSziCod_arr.size(); i++){
				try{	
					//副資材マスター検索
					String sql = "SELECT FUKSZIHACCOD,FUKSZINMKJ,BUNCOD FROM FTBFUK01 "
					+ " WHERE DAIKAISKBCOD = ? AND KAISKBCOD = ? "
					+ " AND FUKSZICOD = ?";
					
					
					ps = conn.prepareStatement(sql);
					
					ps.setString(1,fmVO.getDaiKaiSkbCod());
					ps.setString(2,fmVO.getKaiSkbCod());
					ps.setString(3,fukSziCod_arr.get(i)+"");
					
					rs = ps.executeQuery();
					
					if(rs.next()){
						if(i == 0){
							fmVO.setFukSziHacSaki(fukSziHacSaki_arr.get(i) + "");
							fmVO.setFukSziNm(StringUtils.removeSuffix(rs.getString("FUKSZINMKJ"),"　"));
							fmVO.setFukSziCod(fukSziCod_arr.get(i) + "");
							fmVO.setBunCod(rs.getString("BUNCOD").trim());
							fmVO.setExsitFuk01(true);
						}
						fukSziNm_arr.add(StringUtils.removeSuffix(rs.getString("FUKSZINMKJ"),"　"));
						bunCod_arr.add(rs.getString("BUNCOD").trim());
					}else{
						fukSziNm_arr.add("");
						bunCod_arr.add("");
					}
					fmVO.setBunCod_arr(bunCod_arr); //分類コードarrをVOにセット
					fmVO.setFukSziNm_arr(fukSziNm_arr);//副資材名称arrをVOにセット
					
				}finally{
					if(rs != null)
						rs.close();
					if(ps != null)
						ps.close();
				}
			}
		}

	}

	/**
	 * 発注書データの登録
	 * @param fmVO
	 * @param upddte
	 * @param updjkk
	 * @param syrsuu
	 * @param seqno
	 * @param dtaExcFlg
	 * @throws SQLException
	 */
	public void insertHac01(
		String daiKaiSkbCod,
		String kaiSkbCod,
		String hacSyoDte,
		String syrSuu,
		String seqNo,
		int dtaExcFlg,
		int updDte,
		int updJkk)
		throws SQLException {
			
		String sql = "INSERT INTO FTBHAC01"
					+ " SELECT " 
					+	"HAC02.DAIKAISKBCOD, " 
					+	"HAC02.KAISKBCOD, " 
					+	"HAC02.HACSYODTE, " 
					+	"HAC02.HACSYOBNG, "
					+	"HAC02.SYRSUU, " 
					+	"HAC02.SEQNO, "
					+	"HAC02.HACCOD, " 
					+	"VALUE(MST03.SIRHACNM1 || MST03.SIRHACNM2, G'　'), "
					+	"HAC02.SINKYUKBN, " 
					+	"HAC02.GYOBNG, " 
					+	"HAC02.KIGBNG, "
					+	"HAC02.TITKJ, " 
					+	"HAC02.PRSFUKSZICOD, " 
					+	"VALUE(FUK01.FUKSZINMKJ, G'　'), "
					+	"HAC02.SETSUU, " 
					+	"HAC02.HACSUU, "
					+	"HAC02.NKI, " 
					+	"HAC02.NHNMEIKJ, " 
					+	"HAC02.BUNCOD, " 
					+	"'" + dtaExcFlg + "', " 
					+	"HAC02.CYKKBN, " 
					+	"'A', "
					+	 updDte + ", "  
					+	 updJkk + ", " 
					+	"HAC02.CMT, " 
					+	"HAC02.FUKZAISUU, "
					+	"HAC02.USRID, "		//2005/05/19 add
					+	"HAC02.RRKTBL, "	//2005/08/26 add
					+	"HAC02.TAN, "			//2005/08/26 add
					+	"HAC02.BIKOU2, "			//2007/12/25 add
					+	"HAC02.TAN2"			//2008/03/07 add
					+ " FROM FTBHAC02 HAC02"
					
					+ " LEFT OUTER JOIN FTBMST03 MST03 ON"
					+ " HAC02.DAIKAISKBCOD=MST03.KAISKBCOD"
					+ " AND HAC02.HACCOD=MST03.SIRHACCOD"
					
					+ " LEFT OUTER JOIN FTBFUK01 FUK01 ON"
					+ " HAC02.DAIKAISKBCOD=FUK01.DAIKAISKBCOD"
					+ " AND HAC02.KAISKBCOD=FUK01.KAISKBCOD"
					+ " AND HAC02.PRSFUKSZICOD=FUK01.FUKSZICOD"

					+ " WHERE HAC02.DAIKAISKBCOD='" + daiKaiSkbCod + "'"
					+ " AND HAC02.KAISKBCOD='" + kaiSkbCod + "'"
					+ " AND HAC02.HACSYODTE=" + hacSyoDte 
					+ " AND HAC02.SYRSUU='" + syrSuu + "'"
					+ " AND HAC02.SEQNO='" + seqNo + "'";


		Statement stmt = null;
		try{
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			
		}finally{
			if(stmt != null)
				stmt.close();
		}			
	}

}

