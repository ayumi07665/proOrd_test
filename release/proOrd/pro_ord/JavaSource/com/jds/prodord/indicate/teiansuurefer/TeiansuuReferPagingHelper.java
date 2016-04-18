/**
* TeiansuuReferPagingHelper  ��Đ��Ɖ�w����ʑJ�ڕ⏕�N���X
*
*	�쐬��    2003/02/20
*	�쐬��    �i�m�h�h�j���c �Ĕ�
* �����T�v    ���j���[�Ƃ̉�ʑJ�ڎ��ɑO�����A�㏈�����s���N���X�B
*
*	[�ύX����]
*             ���t ���O
*  �E���e
*/

package com.jds.prodord.indicate.teiansuurefer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionErrors;

import com.jds.prodord.common.CommonConst;
import com.jds.prodord.common.PagingHelper;
import com.jds.prodord.order.prsorder.PrsOrderPagingHelper;
import com.jds.prodord.reference.ikkatsurefer.IkkatsuReferPagingHelper;

public class TeiansuuReferPagingHelper extends PagingHelper {

	/**
	 * �O����
	 * @return �O�����Ŕ��������G���[�̃��b�Z�[�W�B����ɑJ�ڂł���Ƃ���null
	 */
	public ActionErrors pageIn(HttpServletRequest request){

    	HttpSession session = request.getSession(); 
   		com.jds.prodord.register.LogonForm logonForm 
		  			= (com.jds.prodord.register.LogonForm)session.getAttribute(CommonConst.user);
		String daiKaiSkbCod = 	logonForm.getDaikaiskbcod();

		TeiansuuReferForm from = new TeiansuuReferForm();
		from.setQueryDaiKaiSkbCod(daiKaiSkbCod);
//		session.setAttribute(CommonConst.FORMBEAN_NAME_TEIANSUU,from);
		
		return null;
	} 

	/**
	 * �㏈��
	 * @return �㏈���Ŕ��������G���[�̃��b�Z�[�W�B����ɑJ�ڂł���Ƃ���null
	 */
	public ActionErrors pageOut(HttpServletRequest request){
    	HttpSession session = request.getSession(); 
		clear(session);
		IkkatsuReferPagingHelper iktHelper = new IkkatsuReferPagingHelper();
		iktHelper.clear(session);
		PrsOrderPagingHelper poHelper = new PrsOrderPagingHelper();
		poHelper.clear(session);
		return null;
	}

	public void clear(HttpSession session) {
		session.removeAttribute(CommonConst.FORMBEAN_NAME_TEIANSUU);
		session.removeAttribute(CommonConst.TEIANSUUREFER_VO);
	} 


}

