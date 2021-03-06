/**
* OrderHistoryResult  発注履歴照会画面 処理結果クラス
*
*	作成日    2003/04/18
*	作成者    （ＮＩＩ）蛭田 敬子
* 処理概要    処理結果を格納するクラス。
*
*	[変更履歴]
* 		2005/09/15（ＮＩＩ）蛭田
* 			・履歴更新日キーの追加
*/

package com.jds.prodord.reference.orderhistory;

import java.util.HashMap;

public class OrderHistoryResult {

	private OrderHistoryVO fmVO;
	private boolean success;
	private String desc;
	
	private HashMap keyMap = new HashMap();
	private OrderHistoryVO[] resultList = new OrderHistoryVO[0];//検索結果を格納
	private int count = 0;//件数(件数制限を超えた場合にセット)
	
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

