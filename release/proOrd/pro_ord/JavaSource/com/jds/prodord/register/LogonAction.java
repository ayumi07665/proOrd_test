package com.jds.prodord.register;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.jds.prodord.common.CommonConst;
import com.jds.prodord.common.LogUtils;
import com.jds.prodord.common.QueryUtils;
import com.jds.prodord.common.StringUtils;
import com.jds.prodord.menu.MenuControl;
import com.jds.prodord.menu.mastermenu.MasterMenuControl;


public class LogonAction extends Action{

	private final static String FLG_TRUE = "1";
	private final static String FLG_FALSE = "0";
	private Logger log = Logger.getLogger(this.getClass());

    public ActionForward perform(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest req,
                                 HttpServletResponse res
    ) throws java.io.IOException, javax.servlet.ServletException {


    	HttpSession session = req.getSession(); 
		ResultSet rs = null;
        Statement stmt = null;
		Connection conn = null;
		String remoteHost = null;
		
    	LogonForm _logonForm = (LogonForm)form;
    	LogonForm logonForm = new LogonForm();
    	logonForm.setUser(_logonForm.getUser());
    	logonForm.setPwd(_logonForm.getPwd());
    	
    	String user = logonForm.getUser();
    	String pwd  = logonForm.getPwd();

		ArrayList kaiList = new ArrayList();
		ArrayList ketCodList = new ArrayList();
		ArrayList ketNmList = new ArrayList();
		ArrayList ketNm2List = new ArrayList();
		ArrayList mkrBunList = new ArrayList();
		
		ActionErrors errors = new ActionErrors();
		
		//�N���C�A���gIP�A�h���X�̎擾
		remoteHost = getUserAddress(req);

	   	try{
	        
	    	com.jds.prodord.common.DBConnect db2c = new com.jds.prodord.common.DBConnect();
		   	conn = db2c.getDBConnection();
	
	     	stmt = conn.createStatement();
  			String query = "";
  			rs = null;
  			
			info("Check Start");

			// �������e�[�u������o�b�`���t�E�T�[�r�X����FROM�`TO�擾
		
			String batdate = "";
			String svcFrom = "";
			String svcTo   = "";
			
			String chkIP = "";
			String ackbn = "";
			
			boolean chkByDate = true;
			boolean chkByTime = true;

			query ="SELECT SUBSTR(TXT,1,22) AS BATDATE FROM VTBCNL09 " 
				+	" WHERE KAISKBCOD = 'JDS' AND KEYFLD LIKE 'M25010F%'";

		    	rs = stmt.executeQuery(query);

		   	if(rs.next()){
				batdate = rs.getString("BATDATE").substring(0,8);
				svcFrom = rs.getString("BATDATE").substring(8,14);
				svcTo   = rs.getString("BATDATE").substring(14,20);

				if(rs.getString("BATDATE").substring(20,21).equals(FLG_FALSE)){
					chkByDate = false;
				}
				if(rs.getString("BATDATE").substring(21,22).equals(FLG_FALSE)){
					chkByTime = false;
				}
		   	}else{
		    		errors.add(ActionErrors.GLOBAL_ERROR,
		    				new ActionError("errors.internal"));
			}
			rs.close();
	    	if(!errors.empty()){
	    		saveErrors(req,errors);
				if(conn != null){
					conn.close();
				}
	    		return (new ActionForward(mapping.getInput()));
	    	}

			// -1. �T�[�r�X���ԓ��^�O�̃`�F�b�N
		
			Date sysdate = new Date(System.currentTimeMillis());
		
			if(chkByTime){
		 		int chktime = Integer.parseInt((new SimpleDateFormat("HHmmss")).format(sysdate));
				if(chktime >= Integer.parseInt(svcFrom) &&
				   chktime <= Integer.parseInt(svcTo)){

				}else{
					if(conn != null){
						conn.close();
					}
					return (mapping.findForward("offservice"));    		
				}
			}

			// 0. �ғ����^��ғ����̃`�F�b�N
			String chkdate = (new SimpleDateFormat("yyyyMMdd")).format(sysdate);
		
		
			// �o�b�`���t�Ń`�F�b�N���邩�ۂ��𐧌�
		
			if(chkByDate){
				if(batdate.equals(chkdate)){
				}else{
					if(conn != null){
						conn.close();
					}
					return (mapping.findForward("offservice"));    		
				}
			}

	        // 1. ���[�U�[�h�c�E�p�X���[�h�̃`�F�b�N
			info("### USERID CHECK START ###");
			//2003/06/09 CHKIP�i����IP,���������敪,�\���敪 �̒ǉ��j
			//2005/02/03 BSHCOD�i�ꏊ���� �̒ǉ��j
	  	    query = "SELECT DAIKAISKBCOD,KAISKBCOD,USRID,PWD,KAIRLTFLG,CHKIP,ACSCHKKBN,HYOKBN,HACSYOPTN,BSHCOD"+
	  	    		"," + QueryUtils.makeOrderColQuery("KAISKBCOD", "0") + //�\�[�g�p�J����(ORDER_COL)
	  	    			   " FROM FTBKAI01" +
	  					   " WHERE USRID = '" + StringUtils.replace(user.trim(),"'","''") + "'"+
	  					   " AND PWD = '" + StringUtils.replace(pwd.trim(),"'","''") + "'"+
	  					   " ORDER BY ORDER_COL ASC, KAISKBCOD DESC";
	    	
	    	rs = stmt.executeQuery(query);
	    	boolean flg = false;
	    	while(rs.next()){
	    		if(!flg){
			    	logonForm.setDaikaiskbcod(rs.getString("DAIKAISKBCOD").trim());
					//�ꏊ�R�[�h�̃Z�b�g
					logonForm.setBshCod(rs.getString("BSHCOD").trim());//2005/02/03 add
			    	flg = true;
	    		}
	    		if(rs.getString("KAIRLTFLG").trim().equals("1")){
	    			logonForm.setKaiskbcod(rs.getString("KAISKBCOD").trim());
	    			
	    			//�i����IP,���������敪,�\���敪,�������p�^�[���j�̎擾
				    chkIP = rs.getString("CHKIP").trim();
				    ackbn = rs.getString("ACSCHKKBN").trim();
				    logonForm.setHyoKbn(rs.getString("HYOKBN").trim());
				    logonForm.setHacSyoPtn(rs.getString("HACSYOPTN").trim());
	    		}
	    		//��Јꗗ�Z�b�g
	    		if(kaiList.indexOf(rs.getString("KAISKBCOD").trim()) == -1)
			    	kaiList.add(rs.getString("KAISKBCOD").trim());

	     	}
	     	
	     	logonForm.setKaiSkbCodList(kaiList);
	     	if(kaiList.size() == 0){
	    		errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.user.rejected"));
	    			    	
	   		}else{
	   			//�`�ԃR�[�h�ꗗ�̎擾
		   		info("### KETCOD GET START ###");
		  	    query = "SELECT KETCOD,KETNMKJ2 FROM FTBMST06 WHERE DAIKAISKBCOD = '" + logonForm.getKaiskbcod() + "'";		  	    			
			    	
		    	rs = stmt.executeQuery(query);
		    	while(rs.next()){
		    		//�`�ԃR�[�h�ꗗ�Z�b�g
				    ketCodList.add(rs.getString("KETCOD").trim());
				    //�`�Ԗ��̂Q�ꗗ�Z�b�g
				    ketNm2List.add(StringUtils.removeSuffix(rs.getString("KETNMKJ2"),"�@"));
		     	}
		     	logonForm.setKetCodList(ketCodList);
		     	logonForm.setKetNm2List(ketNm2List);
		     	
		     	//�`�Ԗ��̈ꗗ�̎擾
		   		info("### KETNMKJ GET START ###");
		  	    query = "SELECT DISTINCT KETNMKJ," +
		  	    		"CASE WHEN KETNMKJ2 = G'���̑�' THEN '1' ELSE '0' END AS ORDER_COL" + //�\�[�g�p�J����(ORDER_COL)
		  	    			" FROM FTBMST06" +
		  	    			" WHERE DAIKAISKBCOD = '" + logonForm.getKaiskbcod() + "'" +
		  	    			" ORDER BY ORDER_COL, KETNMKJ";		  	    			
			    	
		    	rs = stmt.executeQuery(query);
		    	while(rs.next()){
		    		//�`�Ԗ��̈ꗗ�Z�b�g
				    ketNmList.add(StringUtils.removeSuffix(rs.getString("KETNMKJ"),"�@"));
		     	}		     	
		     	logonForm.setKetNmList(ketNmList);
		     	
		     	//���[�J�[���ވꗗ�̎擾
		   		info("### MKRBUN GET START ###");
		  	    query = "SELECT MKRBUN" +
						"," + QueryUtils.makeOrderColQuery("MKRBUN", "1") + //�\�[�g�p�J����(ORDER_COL)
		  	    			" FROM FTBMST07" +
		  	    			" WHERE DAIKAISKBCOD = '" + logonForm.getDaikaiskbcod() + "'" +
		  	    			" ORDER BY ORDER_COL, MKRBUN";
			    	
		    	rs = stmt.executeQuery(query);
		    	while(rs.next()){
		    		//���[�J�[���ވꗗ�Z�b�g
				    mkrBunList.add(rs.getString("MKRBUN").trim());
		     	}
		     	logonForm.setMkrBunList(mkrBunList);

	     	}
	     	//�`�ԃR�[�h�����̂�HashMap�Ɋi�[
//			logonForm.setKetCod_map(ketCodList,ketNmList);
	     	
	    	if(!errors.empty()){
	    		saveErrors(req,errors);
				if(conn != null){
					conn.close();
				}
	    		return (new ActionForward(mapping.getInput()));
	    	}

			rs.close();
			// ���[�U�[�h�c�E�p�X���[�h�o�^�Ȃ��Ȃ炱���ŃG���[��Ԃ�
	
			// 1.5. ���[�U�[ID��IP�A�h���X�`�F�b�N
	
			if(!ackbn.equals("0")){
	          	boolean ipchk = chkIPadr(remoteHost,chkIP,ackbn);
	
				if(!ipchk){
		    		errors.add(ActionErrors.GLOBAL_ERROR,
		    				new ActionError("errors.ipaddress.unmatch"));
		   		}
			}
	   		

	    	if(!errors.empty()){
    			saveErrors(req,errors);
				if(conn != null){
					conn.close();
				}
    			return (new ActionForward(mapping.getInput()));
    		}
	     	
		}catch(Exception e){
		   	try{
			     if(conn != null){
		 	    	conn.close();
		  		 }
		   	}catch(SQLException sqle){
		   		 servlet.log(sqle.getMessage());
			}
		   	servlet.log(e.getMessage());
		    errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.internal"));
		    saveErrors(req,errors);
		    return (new ActionForward(mapping.getInput()));
		}finally{
			try {
				if(rs != null){
					rs.close();
				}
				if(stmt != null){
					stmt.close();
				}
				if(conn != null){
					conn.close();
				}
			} catch (SQLException sqle) {
				servlet.log(sqle.getMessage());
			}
		}
  	
    session.setAttribute(CommonConst.user,logonForm); 
	
	MenuControl menuControl = new MenuControl(logonForm.getHyoKbn());
	MasterMenuControl masterMenuControl = new MasterMenuControl();
	//���j���[���Z�b�V�����ɃZ�b�g
	session.setAttribute(CommonConst.menuControl,menuControl); 
	session.setAttribute(CommonConst.masterMenuControl,masterMenuControl);
	
	info("Check Success");
	return (mapping.findForward("success"));    		
}
    
//**********************************************************************************************    
    // �h�o�A�h���X�`�F�b�N���\�b�h

  private boolean chkIPadr(String _remotehost,String _chkip,String _acschkkbn){
	
    boolean ipchk = false;

    String chkipComp  = convertIPcomp(_chkip);
    String remoteComp = convertIPcomp(_remotehost);
    ipchk = compareIP(_acschkkbn,chkipComp,remoteComp);

    return ipchk;
  }

  //
  // IP�A�h���X�Q�i�ϊ� �i�J���}�����ŕԂ��j

  private String convertIPcomp(String _ipadr){
    int ixx = _ipadr.indexOf(".");
    int iyy = _ipadr.indexOf(".",ixx+1);
    int izz = _ipadr.lastIndexOf(".");

    int adr_1 = Integer.parseInt(_ipadr.substring(0,ixx));
    int adr_2 = Integer.parseInt(_ipadr.substring(ixx+1,iyy));
    int adr_3 = Integer.parseInt(_ipadr.substring(iyy+1,izz));
    int adr_4 = Integer.parseInt(_ipadr.substring(izz+1,_ipadr.length()));

    StringBuffer sb = new StringBuffer();

    sb.append(convertIPoctet(adr_1));
    sb.append(convertIPoctet(adr_2));
    sb.append(convertIPoctet(adr_3));
    sb.append(convertIPoctet(adr_4));

    return sb.toString();
  }

  //
  // �P�I�N�e�b�g�̕ϊ�

  private String convertIPoctet(int _adrbyte){
    StringBuffer sb = new StringBuffer("");
    String strAdr = Integer.toBinaryString(_adrbyte);
    int adrLen = strAdr.length();

    for (int i = 1; i <= 8 - adrLen; i++){
      sb.append("0");
    }

    sb.append(strAdr);

    return  sb.toString();
  }

  //
  //
  // IP�A�h���X�̔�r  

  private boolean compareIP(String _acschkkbn,String _chkip,String _remotehost){

    int chkKbn = Integer.parseInt(_acschkkbn);
    int chk_flg = 0;
    String strChkip = null,strRemote = null;
    if(chkKbn > 32){
info("IP�����w");
      return false;
    }else{
      strChkip  = _chkip.substring(0,chkKbn);
      strRemote = _remotehost.substring(0,chkKbn);

      if(!strChkip.equals(strRemote)){
info("IP�����w");
        return false;
      }
    }
info("IP�����n�j");
    return true;
  }



  // HTTP���N�G�X�g�w�b�_����A���[�U�[�̂h�o�A�h���X���擾
  private String getUserAddress(HttpServletRequest req){

	String remoteHost = req.getRemoteAddr();
	if(remoteHost == null){
		info("### REMOTE ADDR NULL ###");
	}else{
		info("### REMOTE ADDR : " +remoteHost);
	}
	return remoteHost;

  }
  
	
  protected void debug(Object o){
	  LogUtils.debug(log, o);
  }
	
  protected void info(Object o){
	  LogUtils.info(log, o);
  }


}



