package com.jds.prodord.common;

import org.apache.log4j.Logger;

/**
 * 処理時間の測定・ログ出力を行うユーティリティクラス。<BR>
 * <dt><b>作成日: </b></dt>
 * <dd>2006/09/22</dd>
 * <dt><B>作成者: </b></dt>
 * <dd>(NII)岡田 夏美</dd>
 * <dt><B>処理概要: </b></dt>
 * <dd>処理時間の測定を行う</dd>
 * <br>
 * <b>変更履歴</b><br>
 * <table border="1">
 * 	<tr>
 * 		<th width="80">修正日付</th><th width="100">修正者</th><th width="300">修正内容</th>
 * 	</tr>
 * 	<tr>
 * 		<td>yyyy/MM/dd</td>
 * 		<td>(NII)</td>
 * 		<td>・</td>
 * 	</tr>
 * </table>
 */
public class ProcessLogUtils {
	
	private Logger log = Logger.getLogger(this.getClass());
		
	/**
	 * @param string
	 */
	public ProcessLogUtils(String processName) {
		this(processName, 1);
	}
	
	/**
	 * @param string
	 */
	public ProcessLogUtils(String processName, int level) {
		this.processName = processName;
		this.level = level;
	}
	
	private String processName = "";
	private int level = 0;
	
	private long before = 0;
	private long after = 0;
	
	/**
	 * 処理開始
	 */
	public void start() {
		log(processName + " START" + " " + getPattern());
		before = System.currentTimeMillis();
	}

	/**
	 * 処理終了
	 */
	public void end() {
		after = System.currentTimeMillis();
		outTime();		
		log(processName + " END   " + getPattern());		
	}

	/**
	 * 処理時間を出力します。
	 */
	public void outTime() {
		log("TIME ：" + getTime() / 1000.0 + "秒");
	}

	/**
	 * 処理時間を出力します。
	 */
	public void outTotal(int count) {
		log("TOTAL：" + count + "件");
	}
	
	/**
	 * 処理時間を返します。
	 * @return
	 */
	public long getTime() {;
		return after - before;
	}
	
	/**
	 * ログを出力します。
	 * @param str
	 */
	public void log(Object o) {
		LogUtils.info(log, getPattern() + " " + o);
	}

	private String getPattern() {
		String pattern = "";
		switch (level) {
			case 1 :
				pattern = "###";
				break;
			case 2 :
				pattern = "=====";
				break;
			case 3 :
				pattern = "-------";
				break;
			default :
				break;
		}
		return pattern;
	}

}
