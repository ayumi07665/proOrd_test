package com.jds.prodord.print.printorders;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import com.jds.prodord.common.CommonConst;
import com.jds.prodord.common.NumberingUtils;
import com.jds.prodord.common.SystemException;

/**
 * PrintOrdersDelegate02 ����������������s�N���X�i�b�q�Зp�j.<BR>
 * <dt><b>�쐬��: </b></dt>
 * <dd>2006/11/20</dd>
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
public class PrintOrdersDelegate02 extends PrintOrdersDelegate{
	

	/* (�� Javadoc)
	 * @see com.jds.prodord.print.printorders.PrintOrdersDelegate#getPageLayout(com.jds.prodord.print.printorders.PrintOrdersQueryVO)
	 */
	public ArrayList getPageLayout(
		PrintOrdersDAO fmDao,
		PrintOrdersQueryVO poQueryVO,
		int date)
		throws SQLException {
			
		ArrayList layout = fmDao.getPageLayout02(poQueryVO,date);
		
		return layout;
	}
	
	/**
	 * �������ԍ��̎擾<br>
	 * ��ЂŘA�ԁi�W���j
	 * */
	public String[] doCreateHacSyoBng(String kaiSkbCod, ArrayList numbers)throws SQLException{

		ArrayList returnArr = new ArrayList();
		try{
			//��ЂŘA�ԁi�W���j
			DecimalFormat df = new DecimalFormat("00000000");
			int pageCountTtl = 0;
			for(int i = 0; i < numbers.size(); i++){
				//�����悲�Ƃ̃y�[�W�����J�E���g�A�b�v
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
	 * �s�ԍ��̎擾<br>
	 * ��ЁE������ŘA�ԁi������R�� + �T���j
	 * */
	public String[] doCreateGyoBng(String kaiSkbCod, ArrayList pageLayout)throws SQLException{

		ArrayList returnArr = new ArrayList();
		try{
			//��ЁE������ŘA�ԁi������R�� + �T���j
			DecimalFormat df = new DecimalFormat("00000");
			for(int i = 0; i < pageLayout.size(); i++){

				PrintOrdersBreakKey bk = (PrintOrdersBreakKey)pageLayout.get(i);
				
				int[] number = NumberingUtils.createNumbers(kaiSkbCod,bk.getHacCod(),bk.getCount(),"");
				
				for(int j = 0; j<number.length; j++){
					returnArr.add(bk.getHacCod().substring(0,3) + df.format(number[j]));
				}
			}
			
		}catch(SQLException sqle){
			SystemException se = new SystemException(sqle);
			se.printStackTrace();
		}
		return (String[])returnArr.toArray(new String[returnArr.size()]);		
	}

	/* (�� Javadoc)
	 * @see com.jds.prodord.print.printorders.PrintOrdersDelegate#getHacSyoPtn()
	 */
	protected String getHacSyoPtn() {
		return CommonConst.PTN02;
	}

}

