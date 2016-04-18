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
 * 共通メニューから呼び出されたときに最初に通るActionクラス。<BR>
 * <dt><b>作成日: </b></dt>
 * <dd>2007/02/13</dd>
 * <dt><B>作成者: </b></dt>
 * <dd>(NII)</dd>
 * <dt><B>処理概要: </b></dt>
 * <dd>生産発注用ユーザーID・パスワード取得してLogonFormに格納します。</dd>
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
		
		//J-SymphonyユーザーIDの取得
		String jsmUser = req.getParameter("usrId");

//=====================================================
//TODO jsmUserがnullだったら
//infoメソッドを使用して「ユーザーIDがnullです。」というログを出力
//forward名 userErr に遷移させて終了(return)
//=====================================================

		if (jsmUser == null){
			info("ユーザーIDがnullです。");
			return mapping.findForward("userErr");
		}

		try {
			conn = new DBConnect().getDBConnection();	
			
			//J-SymphonyユーザーＩＤから生産発注ユーザーＩＤ・パスワードを取得

//=====================================================
//TODO J-SymphonyユーザーＩＤから生産発注ユーザーＩＤ・パスワードを取得するSQLを発行
//注）PreparedStatementを使用すること
//    (com.jds.prodord.common.OrderUtils#findHin12メソッド等を参考に)
//結果あり→LogonForm#setUser、LogonForm#setPwdメソッドを使用してUSRID、PWDをLogonFormにセット(値はtrimしてセット)
//結果なし→infoメソッドを使用して「ユーザー情報が取得できませんでした。usrId=(jsmUserを出力)」というログを出力
//        →forward名 userErr に遷移させて終了(return)
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
				info("ユーザー情報が取得できませんでした。usrId = " + jsmUser);
				return mapping.findForward("userErr");
			}			

		} catch (Exception e) {
			info(getClass().getName() + "でエラーが発生しました。" + e.getMessage());
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
