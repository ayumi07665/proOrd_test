/**
* PrsOrderPagingHelper  �v���X�����E�����މ�ʑJ�ڕ⏕�N���X
*
*	�쐬��    2003/04/28
*	�쐬��    �i�m�h�h�j���c �Ĕ�
* �����T�v    ���j���[�Ƃ̉�ʑJ�ڎ��ɑO�����A�㏈�����s���N���X�B
*
*	[�ύX����]
*             ���t ���O
*  �E���e
*/

package com.jds.prodord.order.prsorder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionErrors;

import com.jds.prodord.common.CommonConst;
import com.jds.prodord.common.PagingHelper;
import com.jds.prodord.indicate.manualorder.ManualOrderPagingHelper;
import com.jds.prodord.indicate.manualorderpaste.ManualOrderPastePagingHelper;
import com.jds.prodord.indicate.teiansuurefer.TeiansuuReferPagingHelper;
import com.jds.prodord.reference.ikkatsurefer.IkkatsuReferPagingHelper;

public class PrsOrderPagingHelper extends PagingHelper {

	/**
	 * �O����
	 * @return �O�����Ŕ��������G���[�̃��b�Z�[�W�B����ɑJ�ڂł���Ƃ���null
	 */
	public ActionErrors pageIn(HttpServletRequest request){

    	HttpSession session = request.getSession(); 
   		com.jds.prodord.register.LogonForm logonForm 
		  			= (com.jds.prodord.register.LogonForm)session.getAttribute(CommonConst.user);
		String daiKaiSkbCod = 	logonForm.getDaikaiskbcod();

		PrsOrderForm from = new PrsOrderForm();
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
		IkkatsuReferPagingHelper iktHelper = new IkkatsuReferPagingHelper();
		iktHelper.clear(session);
		ManualOrderPagingHelper moHelper = new ManualOrderPagingHelper();
		moHelper.clear(session);
		ManualOrderPastePagingHelper mopHelper = new ManualOrderPastePagingHelper();
		mopHelper.clear(session);
		TeiansuuReferPagingHelper trHelper = new TeiansuuReferPagingHelper();
		trHelper.clear(session);
		return null;
	}

	public void clear(HttpSession session) {
		session.removeAttribute(CommonConst.INFOMSG_KEY);
		session.removeAttribute(CommonConst.FORMBEAN_NAME_PRSORDER);
		session.removeAttribute(CommonConst.BEAN_NAME_PRSORDER_LEFT);
		session.removeAttribute(CommonConst.PRSORDER_VOS);
		session.removeAttribute(CommonConst.BEAN_NAME_LEFTFORM);
	} 


}

