package com.jds.prodord.print.printorders;

import java.sql.SQLException;
import java.util.ArrayList;

import com.jds.prodord.common.CommonConst;
import com.jds.prodord.common.DateUtils;
import com.jds.prodord.common.SystemException;
import com.jds.prodord.register.LogonForm;

/**
 * PrintOrdersDelegate  発注書印刷処理実行共通クラス。<BR>
 * <dt><b>作成日: </b></dt>
 * <dd>2003/04/18</dd>
 * <dt><B>作成者: 岡田 夏美</b></dt>
 * <dd>(NII)</dd>
 * <dt><B>処理概要: 発注書印刷処理を実行する共通クラス。</b></dt>
 * <dd></dd>
 * <br>
 * <b>変更履歴</b><br>
 * <table border="1">
 * 	<tr>
 * 		<th width="80">修正日付</th><th width="100">修正者</th><th width="300">修正内容</th>
 * 	</tr>
 * 	<tr>
 * 		<td>2003/09/05</td>
 * 		<td>(NII)岡田 夏美</td>
 * 		<td>・発注書パターンによって発注書番号・行番号の採番方法を分岐させる</td>
 * 	</tr>
 * 	<tr>
 * 		<td>2005/09/05</td>
 * 		<td>(NII)蛭田</td>
 * 		<td>・発注履歴・発注書データの更新（訂正伝票発行用）を追加</td>
 * 	</tr>
 * 	<tr>
 * 		<td>2006/11/21</td>
 * 		<td>(NII)岡田 夏美</td>
 * 		<td>・抽象クラス化</td>
 * 		<td>・PrintOrdersDelegate01, PrintOrdersDelegate02のサブクラスに機能を分離</td>
 * 	</tr>
 * </table>
 */
public abstract class PrintOrdersDelegate {	

	/**
	 * 発注書データの取得を実行します。
	 * @param poQueryVO
	 * @param logonForm
	 * @return
	 * @throws SQLException
	 */
	public PrintOrdersVO[] doFindPrintData
				(PrintOrdersQueryVO poQueryVO, LogonForm logonForm)throws SQLException{
		
		//当日日付(発注書日付)
		int date = new DateUtils().getDate6Int();
		
		PrintOrdersDAO fmDao = null;
		PrintOrdersVO[] finded = null;
		try{		
		    fmDao = new PrintOrdersDAO();
			//発注書データを取得
		    finded = fmDao.findHac01(poQueryVO,date,getHacSyoPtn());
			//finded[0]に会社情報を取得
    		fmDao.findKai01(finded[0],logonForm);
		    
		}finally{
			if(fmDao != null){
				try{
					fmDao.close();
				}catch(SQLException sqle2){
					SystemException se = new SystemException(sqle2);
					se.printStackTrace();
				}
			}
		}
		return finded;
	}
	
	/**
	 * ページレイアウトの取得を実行します。
	 * @param poQueryVO
	 * @return
	 * @throws SQLException
	 */
	public ArrayList doGetPageLayout(PrintOrdersQueryVO poQueryVO) throws SQLException{
		
		//当日日付(発注書日付)
		int date = new DateUtils().getDate6Int();
		
		PrintOrdersDAO fmDao = null;
		ArrayList layout = null;
		
		try {
			fmDao = new PrintOrdersDAO();
			layout = getPageLayout(fmDao,poQueryVO,date);
		}finally{
			if(fmDao != null){
				try{
					fmDao.close();
				}catch(SQLException sqle2){
					SystemException se = new SystemException(sqle2);
					se.printStackTrace();
				}
			}
		}
		return layout;
	}
	
	/**
	 * ページレイアウトの取得を実行します。
	 * @param fmDao
	 * @param poQueryVO
	 * @param date
	 * @return ページレイアウトを表すリスト
	 * @throws SQLException
	 */
	protected abstract ArrayList getPageLayout(PrintOrdersDAO fmDao, PrintOrdersQueryVO poQueryVO, int date)throws SQLException;
	
	/**
	 * 発注書番号の取得を実行します。
	 * @param kaiSkbCod
	 * @param numbers
	 * @return
	 * @throws SQLException
	 */
	public abstract String[] doCreateHacSyoBng(String kaiSkbCod, ArrayList numbers)throws SQLException;
	
	/**
	 * 行番号の取得を実行します。
	 * @param kaiSkbCod
	 * @param pageLayout
	 * @return
	 * @throws SQLException
	 */
	public abstract String[] doCreateGyoBng(String kaiSkbCod, ArrayList pageLayout)throws SQLException;

	/**
	 * 発注履歴・発注書データの更新を実行します。
	 * @param vos
	 * @param updateFlg
	 * @param upddte
	 * @param updjkk
	 * @throws SQLException
	 */
	public void doUpDateHac01Hac02(PrintOrdersVO[] vos, boolean updateFlg, int upddte, int updjkk)throws SQLException{

		PrintOrdersDAO fmDao = null;
		try{
			fmDao = new PrintOrdersDAO();
			for(int i = 0; i < vos.length; i++){
				if(updateFlg){
					//発注履歴と発注書データに発注書番号・行番号を書き込む
					fmDao.updateHac01(vos[i],upddte,updjkk);
					fmDao.updateHac02(vos[i],upddte,updjkk);
				}else{
					//訂正伝票発行の場合、発注履歴と発注書データに履歴更新日を書き込む
					fmDao.agnUpdateHac01(vos[i],upddte,updjkk);
					fmDao.agnUpdateHac02(vos[i],upddte,updjkk);
				}
			}
		}finally{
			if(fmDao != null){
				try{
					fmDao.close();
				}catch(SQLException sqle2){
					SystemException se = new SystemException(sqle2);
					se.printStackTrace();
				}
			}
		}
	
	}

	/**
	 * 発注書パターンを返します。
	 * @return 発注書パターン
	 * @see CommonConst#PTN01
	 * @see CommonConst#PTN02
	 * @see CommonConst#PTN03
	 */
	protected abstract String getHacSyoPtn();


}

