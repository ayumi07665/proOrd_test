package com.jds.prodord.print.printorders;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import com.jds.prodord.common.CommonConst;
import com.jds.prodord.common.DataFormatUtils;
import com.jds.prodord.common.NumberingUtils;
import com.jds.prodord.common.SystemException;

/**
 * PrintOrdersDelegate04 ����������������s�N���X�i�e�w�Зp�j.<BR>
 * <dt><b>�쐬��: </b></dt>
 * <dd>2006/11/22</dd>
 * <dt><B>�쐬��: </b></dt>
 * <dd>(NII)���c �Ĕ�</dd>
 * <dt><B>�����T�v: </b></dt>
 * <dd>������������������s����N���X.</dd>
 * <br>
 * <b>�ύX����</b><br>
 * <table border="1">
 * 	<tr>
 * 		<th width="80">�C�����t</th><th width="100">�C����</th><th width="300">�C�����e</th>
 * 	</tr>
 * 	<tr>
 * 		<td>yyyy/MM/dd</td>
 * 		<td>(NII)</td>
 * 		<td>�E</td>
 * 	</tr>
 * </table>
 */
public class PrintOrdersDelegate04 extends PrintOrdersDelegate{
	

	/* (�� Javadoc)
	 * @see com.jds.prodord.print.printorders.PrintOrdersDelegate#getPageLayout(com.jds.prodord.print.printorders.PrintOrdersQueryVO)
	 */
	public ArrayList getPageLayout(
		PrintOrdersDAO fmDao,
		PrintOrdersQueryVO poQueryVO,
		int date)
		throws SQLException {
			
		ArrayList layout = fmDao.getPageLayout04(poQueryVO,date);
		
		return layout;
	}
	
	/**
	 * �������ԍ��̎擾<br>
	 * ��ЂŘA�ԁi�U���j
	 * */
	public String[] doCreateHacSyoBng(String kaiSkbCod, ArrayList numbers)throws SQLException{

		ArrayList returnArr = new ArrayList();
		try{
			//��ЂŘA�ԁi�U���j			
			DecimalFormat df = new DecimalFormat("000000");
			for(int i = 0; i < numbers.size(); i++){

				PrintOrdersBreakKey bk = (PrintOrdersBreakKey)numbers.get(i);
				
				int[] number = NumberingUtils.createNumbers(kaiSkbCod,"******",bk.getPageCount(),"");
				
				for(int j = 0; j<number.length; j++){
					returnArr.add(DataFormatUtils.formatHacSyoBng(df.format(number[j])));
				}
			}
			
		}catch(SQLException sqle){
			SystemException se = new SystemException(sqle);
			se.printStackTrace();
		}
		return (String[])returnArr.toArray(new String[returnArr.size()]);		
	}

	/**
	 * �s�ԍ��̎擾<br>
	 * �y�[�W���Ƃ�0�`10�iNO�e�[�u���͎g��Ȃ��j�i�R���j
	 * */
	public String[] doCreateGyoBng(String kaiSkbCod, ArrayList pageLayout)throws SQLException {

		ArrayList returnArr = new ArrayList();
		
		//�y�[�W���Ƃ�0�`10�iNO�e�[�u���͎g��Ȃ��j�i�R���j
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

	/* (�� Javadoc)
	 * @see com.jds.prodord.print.printorders.PrintOrdersDelegate#getHacSyoPtn()
	 */
	protected String getHacSyoPtn() {
		return CommonConst.PTN04;
	}

}

