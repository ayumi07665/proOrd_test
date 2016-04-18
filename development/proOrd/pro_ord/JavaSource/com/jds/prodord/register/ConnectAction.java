package com.jds.prodord.register;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.jds.prodord.common.DBConnect;
import com.jds.prodord.common.LogUtils;

/**
 * ���ʃ��j���[����Ăяo���ꂽ�Ƃ��ɍŏ��ɒʂ�Action�N���X�B<BR>
 * <dt><b>�쐬��: </b></dt>
 * <dd>2007/02/13</dd>
 * <dt><B>�쐬��: </b></dt>
 * <dd>(NII)</dd>
 * <dt><B>�����T�v: </b></dt>
 * <dd>���Y�����p���[�U�[ID�E�p�X���[�h�擾����LogonForm�Ɋi�[���܂��B</dd>
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
public class ConnectAction extends Action {

	private Logger log = Logger.getLogger(this.getClass());

	public ActionForward perform(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest req,
		HttpServletResponse res)
		throws java.io.IOException, javax.servlet.ServletException {

		ResultSet rs = null;
		PreparedStatement ps = null;
		Connection conn = null;

		LogonForm logonForm = (LogonForm) form;

		info("### ConnectAction START ### " + req.getRemoteAddr());
		
		//J-Symphony���[�U�[ID�̎擾
		String jsmUser = req.getParameter("usrId");

//=====================================================
//TODO jsmUser��null��������
//info���\�b�h���g�p���āu���[�U�[ID��null�ł��B�v�Ƃ������O���o��
//forward�� userErr �ɑJ�ڂ����ďI��(return)
//=====================================================

		if (jsmUser == null){
			info("���[�U�[ID��null�ł��B");
			return mapping.findForward("userErr");
		}

		try {
			conn = new DBConnect().getDBConnection();	
			
			//J-Symphony���[�U�[�h�c���琶�Y�������[�U�[�h�c�E�p�X���[�h���擾

//=====================================================
//TODO J-Symphony���[�U�[�h�c���琶�Y�������[�U�[�h�c�E�p�X���[�h���擾����SQL�𔭍s
//���jPreparedStatement���g�p���邱��
//    (com.jds.prodord.common.OrderUtils#findHin12���\�b�h�����Q�l��)
//���ʂ��聨LogonForm#setUser�ALogonForm#setPwd���\�b�h���g�p����USRID�APWD��LogonForm�ɃZ�b�g(�l��trim���ăZ�b�g)
//���ʂȂ���info���\�b�h���g�p���āu���[�U�[��񂪎擾�ł��܂���ł����BusrId=(jsmUser���o��)�v�Ƃ������O���o��
//        ��forward�� userErr �ɑJ�ڂ����ďI��(return)
//=====================================================

			String sql = "SELECT USRID,PWD FROM FTBKAI01"
						  + " WHERE JSMUSRID = ?";
    	
			ps = conn.prepareStatement(sql);

			ps.setString(1,jsmUser);
			
			rs = ps.executeQuery();
			
			if(rs.next()){
				logonForm.setUser(rs.getString("USRID").trim());
				logonForm.setPwd(rs.getString("PWD").trim());
	  		}else{
				info("���[�U�[��񂪎擾�ł��܂���ł����BusrId = " + jsmUser);
				return mapping.findForward("userErr");
			}			

		} catch (Exception e) {
			info(getClass().getName() + "�ŃG���[���������܂����B" + e.getMessage());
			return mapping.findForward("failure");
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException sqle) {
				servlet.log(sqle.getMessage());
			}
		}

		return mapping.findForward("success");
	}

	protected void debug(Object o) {
		LogUtils.debug(log, o);
	}

	protected void info(Object o) {
		LogUtils.info(log, o);
	}

}
