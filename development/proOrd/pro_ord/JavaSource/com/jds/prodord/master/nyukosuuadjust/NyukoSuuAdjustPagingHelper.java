/**
* NyukoSuuAdjustPagingHelper  ���ɐ�������ʑJ�ڕ⏕�N���X
*
*	�쐬��    2003/09/30
*	�쐬��    �i�m�h�h�j���c �Ĕ�
* �����T�v    ���j���[�Ƃ̉�ʑJ�ڎ��ɑO�����A�㏈�����s���N���X�B
*
*	[�ύX����]
*             ���t ���O
*  �E���e
*/

package com.jds.prodord.master.nyukosuuadjust;

import com.jds.prodord.common.PagingHelper;
import com.jds.prodord.common.CommonConst;
import javax.servlet.http.*;
import org.apache.struts.action.*;

public class NyukoSuuAdjustPagingHelper extends PagingHelper {

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
		session.removeAttribute(CommonConst.FORMBEAN_NAME_NYUKOSUUADJUST);
		return null;
	} 


}

