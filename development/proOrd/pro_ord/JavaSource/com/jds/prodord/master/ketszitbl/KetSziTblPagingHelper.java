/**
* KetSziTblPagingHelper  �`�ԕʍ\�����ރ}�X�^�[�����e�i���X��ʑJ�ڕ⏕�N���X
*
*	�쐬��    2004/02/01
*	�쐬��    �i�m�h�h�j�X
* �����T�v    ���j���[�Ƃ̉�ʑJ�ڎ��ɑO�����A�㏈�����s���N���X�B
*
*	[�ύX����]
*             ���t ���O
*  �E���e
*/

package com.jds.prodord.master.ketszitbl;

import com.jds.prodord.common.PagingHelper;
import com.jds.prodord.common.CommonConst;
import javax.servlet.http.*;
import org.apache.struts.action.*;

public class KetSziTblPagingHelper extends PagingHelper {

	/**
	 * �O����
	 * @return �O�����Ŕ��������G���[�̃��b�Z�[�W�B����ɑJ�ڂł���Ƃ���null
	 */
	public ActionErrors pageIn(HttpServletRequest request){

    	HttpSession session = request.getSession(); 
   		com.jds.prodord.register.LogonForm logonForm 
		  			= (com.jds.prodord.register.LogonForm)session.getAttribute(CommonConst.user);
		String daiKaiSkbCod = 	logonForm.getDaikaiskbcod();

		KetSziTblForm form = new KetSziTblForm();
		form.setQueryDaiKaiSkbCod(daiKaiSkbCod);
		
		return null;
	} 

	/**
	 * �㏈��
	 * @return �㏈���Ŕ��������G���[�̃��b�Z�[�W�B����ɑJ�ڂł���Ƃ���null
	 */
	public ActionErrors pageOut(HttpServletRequest request){
    	HttpSession session = request.getSession(); 
		session.removeAttribute(CommonConst.FORMBEAN_NAME_KETSZITBL);
		return null;
	} 


}
