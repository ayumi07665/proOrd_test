
package com.jds.prodord.print.printorders;


/**
 * PrintOrdersBreakKey�N���X.<BR>
 * <dt><b>�쐬��: </b></dt>
 * <dd>2005/06/28</dd>
 * <dt><B>�쐬��: </b></dt>
 * <dd>(NII)�g�c</dd>
 * <dt><B>�����T�v: </b></dt>
 * <dd>�����T�v���L�q</dd>
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
public class PrintOrdersBreakKey {

	private String hacCod = "";
	private String sinKyuKbn = "";
	private int count = 0;
	private int pageCount = 0;
	
	public PrintOrdersBreakKey(){
	}
	
	public PrintOrdersBreakKey(String hacCod, int count){
		this.hacCod = hacCod;
		this.count = count;
	}
	

	/**
	 * ������R�[�h�̎擾
	 * @return
	 */
	public String getHacCod() {
		return hacCod;
	}

	/**
	 * ������R�[�h�̐ݒ�
	 * @param string
	 */
	public void setHacCod(String string) {
		hacCod = string;
	}

	/**
	 * �V�������敪�̎擾
	 * @return
	 */
	public String getSinKyuKbn() {
		return sinKyuKbn;
	}

	/**
	 * �V�������敪�̐ݒ�
	 * @param string
	 */
	public void setSinKyuKbn(String string) {
		sinKyuKbn = string;
	}

	/**
	 * �J�E���g�̎擾
	 * @return
	 */
	public int getCount() {
		return count;
	}

	/**
	 * �J�E���g�̐ݒ�
	 * @param i
	 */
	public void setCount(int i) {
		count = i;
	}

	/**
	 * �y�[�W�J�E���g�̎擾
	 * @return
	 */
	public int getPageCount() {
		return pageCount;
	}

	/**
	 * �y�[�W�J�E���g�̐ݒ�
	 * @param i
	 */
	public void setPageCount(int i) {
		pageCount = i;
	}

}
