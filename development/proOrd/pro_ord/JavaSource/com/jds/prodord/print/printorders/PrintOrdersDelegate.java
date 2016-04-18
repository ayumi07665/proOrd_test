package com.jds.prodord.print.printorders;

import java.sql.SQLException;
import java.util.ArrayList;

import com.jds.prodord.common.CommonConst;
import com.jds.prodord.common.DateUtils;
import com.jds.prodord.common.SystemException;
import com.jds.prodord.register.LogonForm;

/**
 * PrintOrdersDelegate  ����������������s���ʃN���X�B<BR>
 * <dt><b>�쐬��: </b></dt>
 * <dd>2003/04/18</dd>
 * <dt><B>�쐬��: ���c �Ĕ�</b></dt>
 * <dd>(NII)</dd>
 * <dt><B>�����T�v: ������������������s���鋤�ʃN���X�B</b></dt>
 * <dd></dd>
 * <br>
 * <b>�ύX����</b><br>
 * <table border="1">
 * 	<tr>
 * 		<th width="80">�C�����t</th><th width="100">�C����</th><th width="300">�C�����e</th>
 * 	</tr>
 * 	<tr>
 * 		<td>2003/09/05</td>
 * 		<td>(NII)���c �Ĕ�</td>
 * 		<td>�E�������p�^�[���ɂ���Ĕ������ԍ��E�s�ԍ��̍̔ԕ��@�𕪊򂳂���</td>
 * 	</tr>
 * 	<tr>
 * 		<td>2005/09/05</td>
 * 		<td>(NII)�g�c</td>
 * 		<td>�E���������E�������f�[�^�̍X�V�i�����`�[���s�p�j��ǉ�</td>
 * 	</tr>
 * 	<tr>
 * 		<td>2006/11/21</td>
 * 		<td>(NII)���c �Ĕ�</td>
 * 		<td>�E���ۃN���X��</td>
 * 		<td>�EPrintOrdersDelegate01, PrintOrdersDelegate02�̃T�u�N���X�ɋ@�\�𕪗�</td>
 * 	</tr>
 * </table>
 */
public abstract class PrintOrdersDelegate {	

	/**
	 * �������f�[�^�̎擾�����s���܂��B
	 * @param poQueryVO
	 * @param logonForm
	 * @return
	 * @throws SQLException
	 */
	public PrintOrdersVO[] doFindPrintData
				(PrintOrdersQueryVO poQueryVO, LogonForm logonForm)throws SQLException{
		
		//�������t(���������t)
		int date = new DateUtils().getDate6Int();
		
		PrintOrdersDAO fmDao = null;
		PrintOrdersVO[] finded = null;
		try{		
		    fmDao = new PrintOrdersDAO();
			//�������f�[�^���擾
		    finded = fmDao.findHac01(poQueryVO,date,getHacSyoPtn());
			//finded[0]�ɉ�Џ����擾
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
	 * �y�[�W���C�A�E�g�̎擾�����s���܂��B
	 * @param poQueryVO
	 * @return
	 * @throws SQLException
	 */
	public ArrayList doGetPageLayout(PrintOrdersQueryVO poQueryVO) throws SQLException{
		
		//�������t(���������t)
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
	 * �y�[�W���C�A�E�g�̎擾�����s���܂��B
	 * @param fmDao
	 * @param poQueryVO
	 * @param date
	 * @return �y�[�W���C�A�E�g��\�����X�g
	 * @throws SQLException
	 */
	protected abstract ArrayList getPageLayout(PrintOrdersDAO fmDao, PrintOrdersQueryVO poQueryVO, int date)throws SQLException;
	
	/**
	 * �������ԍ��̎擾�����s���܂��B
	 * @param kaiSkbCod
	 * @param numbers
	 * @return
	 * @throws SQLException
	 */
	public abstract String[] doCreateHacSyoBng(String kaiSkbCod, ArrayList numbers)throws SQLException;
	
	/**
	 * �s�ԍ��̎擾�����s���܂��B
	 * @param kaiSkbCod
	 * @param pageLayout
	 * @return
	 * @throws SQLException
	 */
	public abstract String[] doCreateGyoBng(String kaiSkbCod, ArrayList pageLayout)throws SQLException;

	/**
	 * ���������E�������f�[�^�̍X�V�����s���܂��B
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
					//���������Ɣ������f�[�^�ɔ������ԍ��E�s�ԍ�����������
					fmDao.updateHac01(vos[i],upddte,updjkk);
					fmDao.updateHac02(vos[i],upddte,updjkk);
				}else{
					//�����`�[���s�̏ꍇ�A���������Ɣ������f�[�^�ɗ����X�V������������
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
	 * �������p�^�[����Ԃ��܂��B
	 * @return �������p�^�[��
	 * @see CommonConst#PTN01
	 * @see CommonConst#PTN02
	 * @see CommonConst#PTN03
	 */
	protected abstract String getHacSyoPtn();


}

