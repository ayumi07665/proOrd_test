/**
* OrderHistoryResult  ”­’—š—ğÆ‰ï‰æ–Ê ˆ—Œ‹‰ÊƒNƒ‰ƒX
*
*	ì¬“ú    2003/04/18
*	ì¬Ò    i‚m‚h‚hj•g“c Œhq
* ˆ—ŠT—v    ˆ—Œ‹‰Ê‚ğŠi”[‚·‚éƒNƒ‰ƒXB
*
*	[•ÏX—š—ğ]
* 		2005/09/15i‚m‚h‚hj•g“c
* 			E—š—ğXV“úƒL[‚Ì’Ç‰Á
*/

package com.jds.prodord.reference.orderhistory;

import java.util.HashMap;

public class OrderHistoryResult {

	private OrderHistoryVO fmVO;
	private boolean success;
	private String desc;
	
	private HashMap keyMap = new HashMap();
	private OrderHistoryVO[] resultList = new OrderHistoryVO[0];//ŒŸõŒ‹‰Ê‚ğŠi”[
	private int count = 0;//Œ”(Œ”§ŒÀ‚ğ’´‚¦‚½ê‡‚ÉƒZƒbƒg)
	
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

