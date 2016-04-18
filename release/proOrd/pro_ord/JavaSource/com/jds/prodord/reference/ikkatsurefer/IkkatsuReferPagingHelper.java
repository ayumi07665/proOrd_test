/**
* IkkatsuReferPagingHelper  �ꊇ�Ɖ� ��ʑJ�ڕ⏕�N���X
*
*	�쐬��    2003/03/31
*	�쐬��    �i�m�h�h�j���c �Ĕ�
* �����T�v    ���j���[�Ƃ̉�ʑJ�ڎ��ɑO�����A�㏈�����s���N���X�B
*
*	[�ύX����]
*             ���t ���O
*  �E���e
*/

package com.jds.prodord.reference.ikkatsurefer;

import com.jds.prodord.common.PagingHelper;
import com.jds.prodord.common.CommonConst;
import com.jds.prodord.indicate.teiansuurefer.TeiansuuReferPagingHelper;
import com.jds.prodord.order.prsorder.PrsOrderPagingHelper;

import javax.servlet.http.*;
import org.apache.struts.action.*;

public class IkkatsuReferPagingHelper extends PagingHelper {

	/**
	 * �O����
	 * @return �O�����Ŕ��������G���[�̃��b�Z�[�W�B����ɑJ�ڂł���Ƃ���null
	 */
	public ActionErrors pageIn(HttpServletRequest request){

    	HttpSession session = request.getSession(); 
   		com.jds.prodord.register.LogonForm logonForm 
		  			= (com.jds.prodord.register.LogonForm)session.getAttribute(CommonConst.user);
		String daiKaiSkbCod = 	logonForm.getDaikaiskbcod();

		IkkatsuReferForm from = new IkkatsuReferForm();
		from.setQueryDaiKaiSkbCod(daiKaiSkbCod);
//		session.setAttribute(CommonConst.FORMBEAN_NAME_IKKATSU,from);
		
		return null;
	} 

	/**
	 * �㏈��
	 * @return �㏈���Ŕ��������G���[�̃��b�Z�[�W�B����ɑJ�ڂł���Ƃ���null
	 */
	public ActionErrors pageOut(HttpServletRequest request){
    	HttpSession session = request.getSession();
		clear(session);
		PrsOrderPagingHelper poHelper = new PrsOrderPagingHelper();
		poHelper.clear(session);
		TeiansuuReferPagingHelper trHelper = new TeiansuuReferPagingHelper();
		trHelper.clear(session);
		return null;
	}

	public void clear(HttpSession session) {
		session.removeAttribute(CommonConst.FORMBEAN_NAME_IKKATSU);
		session.removeAttribute(CommonConst.BEAN_NAME_IKKATSU_LEFT);
		session.removeAttribute(CommonConst.INFOMSG_KEY);
		session.removeAttribute(CommonConst.BEAN_NAME_LEFTFORM);
	} 


}

