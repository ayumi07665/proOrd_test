package com.jds.prodord.common;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * ���\�[�X�E�o���h���E�A�N�Z�T�[�N���X(ApplicationResources_ja�p).<BR>
 * <dt><b>�쐬��: </b></dt>
 * <dd>2007/09/10</dd>
 * <dt><B>�쐬��: </b></dt>
 * <dd>(NII)</dd>
 * <dt><B>�����T�v: </b></dt>
 * <dd>ApplicationResources_ja�̃��\�[�X�E�o���h���ɃA�N�Z�X���邽�߂̃N���X�ł��B</dd>
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
public class ResourceMessages {

	private static final String BUNDLE_NAME = "ApplicationResources_ja";

	private static final ResourceBundle RESOURCE_BUNDLE =
		ResourceBundle.getBundle(BUNDLE_NAME);
	
	/**
	 * ���\�[�X����L�[�ɑΉ��������b�Z�[�W���擾���ĕԂ��܂��B
	 * @param key
	 * @return ���\�[�X����擾�����l
	 */
	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
}
