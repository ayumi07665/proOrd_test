/**
* FzkHinKsiPastePagingHelper  �t���i�\���}�X�^�[�ꊇ�\��t����ʑJ�ڕ⏕�N���X
*
*	�쐬��    2007/09/30
*	�쐬��    �i�m�h�h�j�c��
* �����T�v    ���j���[�Ƃ̉�ʑJ�ڎ��ɑO�����A�㏈�����s���N���X�B
*
*	[�ύX����]
*/

package com.jds.prodord.master.fzkhinksipaste;

import com.jds.prodord.common.PagingHelper;
import com.jds.prodord.common.CommonConst;
import javax.servlet.http.*;
import org.apache.struts.action.*;

public class FzkHinKsiPastePagingHelper extends PagingHelper {

	/**
	 * �O����
	 * @return �O�����Ŕ��������G���[�̃��b�Z�[�W�B����ɑJ�ڂł���Ƃ���null
	 */
	public ActionErrors pageIn(HttpServletRequest request){

//   	HttpSession session = request.getSession(); 
//   		com.jds.prodord.register.LogonForm logonForm 
//		  			= (com.jds.prodord.register.LogonForm)session.getAttribute(CommonConst.user);
//		String daiKaiSkbCod = 	logonForm.getDaikaiskbcod();
//
//		FzkHinKsiPasteForm form = new FzkHinKsiPasteForm();
//		form.setQueryDaiKaiSkbCod(daiKaiSkbCod);
		
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
		session.removeAttribute(CommonConst.FORMBEAN_NAME_MANUALORDER);
	} 


}

