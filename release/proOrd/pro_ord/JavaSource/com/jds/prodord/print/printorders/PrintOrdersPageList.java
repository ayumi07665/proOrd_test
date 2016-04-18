/**
* PrintOrdersPageList  発注書印刷ページリストクラス
*
*	作成日    2003/04/18
*	作成者    （ＮＩＩ）岡田 夏美
* 処理概要    Httpリクエストとリクエストの結果のうちの繰り返し項目（ページ）を格納するクラス。
*
*	[変更履歴]
*             日付 名前
*  ・内容
*/

package com.jds.prodord.print.printorders;

import java.util.ArrayList;
import java.util.Collection;

public class PrintOrdersPageList extends ArrayList {
	
	public Collection getOrdersPageArr(){
		return(this);
	}


}

