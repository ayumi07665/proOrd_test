package com.jds.prodord.print.printorders;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import com.jds.prodord.common.CommonConst;
import com.jds.prodord.common.DataFormatUtils;
import com.jds.prodord.common.NumberingUtils;
import com.jds.prodord.common.SystemException;

/**
 * PrintOrdersDelegate05 発注書印刷処理実行クラス（LAN(BAN)社用）.<BR>
 * <dt><b>作成日: </b></dt>
 * <dd>2013/06/04</dd>
 * <dt><B>作成者: </b></dt>
 * <dd>(NII)</dd>
 * <dt><B>処理概要: </b></dt>
 * <dd>発注書印刷処理を実行するクラス.</dd>
 * <br>
 * <b>変更履歴</b><br>
 * <table border="1">
 * 	<tr>
 * 		<th width="80">修正日付</th><th width="100">修正者</th><th width="300">修正内容</th>
 * 	</tr>
 * 	<tr>
 * 		<td>yyyy/MM/dd</td>
 * 		<td>(NII)</td>
 * 		<td>・</td>
 * 	</tr>
 * </table>
 */
public class PrintOrdersDelegate05 extends PrintOrdersDelegate{
	

	/* (非 Javadoc)
	 * @see com.jds.prodord.print.printorders.PrintOrdersDelegate#getPageLayout(com.jds.prodord.print.printorders.PrintOrdersQueryVO)
	 */
	public ArrayList getPageLayout(
		PrintOrdersDAO fmDao,
		PrintOrdersQueryVO poQueryVO,
		int date)
		throws SQLException {
			
		ArrayList layout = fmDao.getPageLayout05(poQueryVO,date);
		
		return layout;
	}
	
	/**
	 * 発注書番号の取得<br>
	 * 会社・発注先で連番（６桁）
	 * */
	public String[] doCreateHacSyoBng(String kaiSkbCod, ArrayList numbers)throws SQLException{


		ArrayList returnArr = new ArrayList();
		try{
			//会社で連番（6桁）
			DecimalFormat df = new DecimalFormat("000000");
			int pageCountTtl = 0;
			for(int i = 0; i < numbers.size(); i++){
				//発注先ごとのページ数をカウントアップ
				pageCountTtl += ((PrintOrdersBreakKey)numbers.get(i)).getPageCount();
			}
			int[] number = NumberingUtils.createNumbers(kaiSkbCod,"******",pageCountTtl,"");
			for(int i = 0; i<number.length; i++){
				returnArr.add(df.format(number[i]));
			}
					
		}catch(SQLException sqle){
			SystemException se = new SystemException(sqle);
			se.printStackTrace();
		}
		return (String[])returnArr.toArray(new String[returnArr.size()]);		
	}

	/**
	 * 行番号の取得<br>
	 * ページごとに0～10（NOテーブルは使わない）（３桁）
	 * */
	public String[] doCreateGyoBng(String kaiSkbCod, ArrayList pageLayout)throws SQLException {

		ArrayList returnArr = new ArrayList();
		
		//ページごとに0～10（NOテーブルは使わない）（３桁）
		DecimalFormat df = new DecimalFormat("000");
		int rowCount = 0;
		for(int i = 0; i < pageLayout.size(); i++){
			PrintOrdersBreakKey bk = (PrintOrdersBreakKey)pageLayout.get(i);
			rowCount = bk.getCount();
			for(int j = 0; j<rowCount; j++){
				returnArr.add(DataFormatUtils.formatGyoBng(df.format(j+1)));
			}
		}
		return (String[])returnArr.toArray(new String[returnArr.size()]);		
	}

	/* (非 Javadoc)
	 * @see com.jds.prodord.print.printorders.PrintOrdersDelegate#getHacSyoPtn()
	 */
	protected String getHacSyoPtn() {
		return CommonConst.PTN05;
	}

}

