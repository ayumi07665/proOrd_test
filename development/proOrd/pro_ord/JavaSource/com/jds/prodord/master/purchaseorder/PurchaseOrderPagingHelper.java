/**
* PurchaseOrderPagingHelper  �d��������}�X�^�[�Ɖ�}�X�^�[�����e�i���X��ʑJ�ڕ⏕�N���X
*
*	�쐬��    2003/05/19
*	�쐬��    �i�m�h�h�j����  ����
* �����T�v    ���j���[�Ƃ̉�ʑJ�ڎ��ɑO�����A�㏈�����s���N���X�B
*
*/

package com.jds.prodord.master.purchaseorder;

import com.jds.prodord.common.PagingHelper;
import com.jds.prodord.common.CommonConst;
import javax.servlet.http.*;
import org.apache.struts.action.*;

public class PurchaseOrderPagingHelper extends PagingHelper {


	/**
	 * �O����
	 * @return �O�����Ŕ��������G���[�̃��b�Z�[�W�B����ɑJ�ڂł���Ƃ���null
	 */
	public ActionErrors pageIn(HttpServletRequest request){

		return null;
	} 

	/**
	 * �㏈��
	 * @return �㏈���Ŕ��������G���[�̃��b�Z�[�W�B����ɑJ�ڂł���Ƃ���null
	 */
	public ActionErrors pageOut(HttpServletRequest request){
    	HttpSession session = request.getSession(); 
		session.removeAttribute(CommonConst.FORMBEAN_NAME_PURCHASEORDER);
		return null;
	} 


}

