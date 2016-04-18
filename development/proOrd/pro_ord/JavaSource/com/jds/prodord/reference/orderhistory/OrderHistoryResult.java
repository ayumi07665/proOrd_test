/**
* OrderHistoryResult  ���������Ɖ��� �������ʃN���X
*
*	�쐬��    2003/04/18
*	�쐬��    �i�m�h�h�j�g�c �h�q
* �����T�v    �������ʂ��i�[����N���X�B
*
*	[�ύX����]
* 		2005/09/15�i�m�h�h�j�g�c
* 			�E�����X�V���L�[�̒ǉ�
*/

package com.jds.prodord.reference.orderhistory;

import java.util.HashMap;

public class OrderHistoryResult {

	private OrderHistoryVO fmVO;
	private boolean success;
	private String desc;
	
	private HashMap keyMap = new HashMap();
	private OrderHistoryVO[] resultList = new OrderHistoryVO[0];//�������ʂ��i�[
	private int count = 0;//����(���������𒴂����ꍇ�ɃZ�b�g)
	
	public OrderHistoryResult(OrderHistoryVO fmVO,boolean success,String desc){
		this.fmVO = fmVO;
		this.success = success;
		this.desc = desc;
	}
	public OrderHistoryVO getVO(){
		return fmVO;
	}
	public boolean isSuccess(){
		return success;
	}
	public String getDescription(){
		return desc;
	}


	/**
	 * @return
	 */
	public HashMap getKeyMap() {
		return keyMap;
	}

	/**
	 * @param map
	 */
	public void setKeyMap(HashMap map) {
		keyMap = map;
	}

	/**
	 * @return
	 */
	public OrderHistoryVO[] getResultList() {
		return resultList;
	}

	/**
	 * @param historyVOs
	 */
	public void setResultList(OrderHistoryVO[] historyVOs) {
		resultList = historyVOs;
	}

	/**
	 * @return
	 */
	public int getCount() {
		return count;
	}

	/**
	 * @param i
	 */
	public void setCount(int i) {
		count = i;
	}

}

