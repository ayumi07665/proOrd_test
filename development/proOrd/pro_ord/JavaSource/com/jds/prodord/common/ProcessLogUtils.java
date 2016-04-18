package com.jds.prodord.common;

import org.apache.log4j.Logger;

/**
 * �������Ԃ̑���E���O�o�͂��s�����[�e�B���e�B�N���X�B<BR>
 * <dt><b>�쐬��: </b></dt>
 * <dd>2006/09/22</dd>
 * <dt><B>�쐬��: </b></dt>
 * <dd>(NII)���c �Ĕ�</dd>
 * <dt><B>�����T�v: </b></dt>
 * <dd>�������Ԃ̑�����s��</dd>
 * <br>
 * <b>�ύX����</b><br>
 * <table border="1">
 * 	<tr>
 * 		<th width="80">�C�����t</th><th width="100">�C����</th><th width="300">�C�����e</th>
 * 	</tr>
 * 	<tr>
 * 		<td>yyyy/MM/dd</td>
 * 		<td>(NII)</td>
 * 		<td>�E</td>
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
	 * �����J�n
	 */
	public void start() {
		log(processName + " START" + " " + getPattern());
		before = System.currentTimeMillis();
	}

	/**
	 * �����I��
	 */
	public void end() {
		after = System.currentTimeMillis();
		outTime();		
		log(processName + " END   " + getPattern());		
	}

	/**
	 * �������Ԃ��o�͂��܂��B
	 */
	public void outTime() {
		log("TIME �F" + getTime() / 1000.0 + "�b");
	}

	/**
	 * �������Ԃ��o�͂��܂��B
	 */
	public void outTotal(int count) {
		log("TOTAL�F" + count + "��");
	}
	
	/**
	 * �������Ԃ�Ԃ��܂��B
	 * @return
	 */
	public long getTime() {;
		return after - before;
	}
	
	/**
	 * ���O���o�͂��܂��B
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
