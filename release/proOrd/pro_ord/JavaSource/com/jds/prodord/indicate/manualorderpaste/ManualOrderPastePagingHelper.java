package com.jds.prodord.indicate.manualorderpaste;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionErrors;

import com.jds.prodord.common.CommonConst;
import com.jds.prodord.common.PagingHelper;
/**
 * ManualOrderPastePagingHelper  �}�j���A�������w���ꊇ�\�t��ʑJ�ڕ⏕�N���X�B<BR>
 * <dt><b>�쐬��: </b></dt>
 * <dd>2007/09/10</dd>
 * <dt><B>�쐬��: </b></dt>
 * <dd>(NII)</dd>
 * <dt><B>�����T�v: </b></dt>
 * <dd>���j���[�Ƃ̉�ʑJ�ڎ��ɑO�����A�㏈�����s���N���X�B</dd>
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

public class ManualOrderPastePagingHelper extends PagingHelper {

	/**
	 * �O����
	 * @return �O�����Ŕ��������G���[�̃��b�Z�[�W�B����ɑJ�ڂł���Ƃ���null
	 */
	public ActionErrors pageIn(HttpServletRequest request){

//    	HttpSession session = request.getSession(); 
//   		com.jds.prodord.register.LogonForm logonForm 
//		  			= (com.jds.prodord.register.LogonForm)session.getAttribute(CommonConst.user);
//		String daiKaiSkbCod = 	logonForm.getDaikaiskbcod();
//
//		ManualOrderForm from = new ManualOrderForm();
//		from.setQueryDaiKaiSkbCod(daiKaiSkbCod);

		
		return null;
	} 

	/**
	 * �㏈��
	 * @return �㏈���Ŕ��������G���[�̃��b�Z�[�W�B����ɑJ�ڂł���Ƃ���null
	 */
	public ActionErrors pageOut(HttpServletRequest request){
    	HttpSession session = request.getSession(); 
		clear(session);
		return null;
	}

	public void clear(HttpSession session) {
		session.removeAttribute(CommonConst.FORMBEAN_NAME_MANUALORDERPASTE);
	} 


}

