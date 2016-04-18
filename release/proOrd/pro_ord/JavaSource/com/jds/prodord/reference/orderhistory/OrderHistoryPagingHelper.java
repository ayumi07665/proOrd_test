/**
* OrderHistoryPagingHelper  ���������Ɖ� ��ʑJ�ڕ⏕�N���X
*
*	�쐬��    2003/04/18
*	�쐬��    �i�m�h�h�j�g�c �h�q
* �����T�v    ���j���[�Ƃ̉�ʑJ�ڎ��ɑO�����A�㏈�����s���N���X�B
*
*	[�ύX����]
*             ���t ���O
*  �E���e
*/

package com.jds.prodord.reference.orderhistory;

import com.jds.prodord.common.PagingHelper;
import com.jds.prodord.common.CommonConst;
import javax.servlet.http.*;
import org.apache.struts.action.*;

public class OrderHistoryPagingHelper extends PagingHelper {

	/**
	 * �O����
	 * @return �O�����Ŕ��������G���[�̃��b�Z�[�W�B����ɑJ�ڂł���Ƃ���null
	 */
	public ActionErrors pageIn(HttpServletRequest request){

    	HttpSession session = request.getSession(); 
   		com.jds.prodord.register.LogonForm logonForm 
		  			= (com.jds.prodord.register.LogonForm)session.getAttribute("user");
		String daiKaiSkbCod = 	logonForm.getDaikaiskbcod();

		OrderHistoryTopForm from = new OrderHistoryTopForm();
		from.setQueryDaiKaiSkbCod(daiKaiSkbCod);
		
		return null;
	} 

	/**
	 * �㏈��
	 * @return �㏈���Ŕ��������G���[�̃��b�Z�[�W�B����ɑJ�ڂł���Ƃ���null
	 */
	public ActionErrors pageOut(HttpServletRequest request){
    	HttpSession session = request.getSession(); 
		session.removeAttribute(CommonConst.FORMBEAN_NAME_ORDERHIST_TOP);
		session.removeAttribute(CommonConst.FORMBEAN_NAME_ORDERHIST_MID);
		session.removeAttribute(CommonConst.ORDERHISTORY_VO);
		session.removeAttribute(CommonConst.INFOMSG_KEY);
		return null;
	} 


}

