package com.jds.prodord.register;


import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import com.jds.prodord.common.*;
import java.util.*;


public final class LogonForm extends ActionForm {
	
	private String user;
	private String pwd;
	private String daikaiskbcod = "";
	private String kaiskbcod = "";
	private CheckCommon cc = new CheckCommon();
	private ArrayList kaiList = new ArrayList();
	private ArrayList ketCodList = new ArrayList();
	private ArrayList ketNmList = new ArrayList();
	private ArrayList ketNm2List = new ArrayList();
	private ArrayList mkrBunList = new ArrayList();
	private HashMap ketCod_map = new HashMap();//形態コードと形態名称を格納
	private String hyoKbn = "";
	private String hacSyoPtn = "";
	private String bshCod = "";
	
	public void setUser(String user){
		this.user = user;
	}
	
	public String getUser(){
		return cc.getEscapedString(user);
	}
	public String getUserOriginal(){
		return user;
	}
	
	public void setPwd(String pwd){
		this.pwd = pwd;
	}
	
	public String getPwd(){
		return cc.getEscapedString(pwd);
	}
	public String getPwdOriginal(){
		return pwd;
	}
	
	public void setDaikaiskbcod(String daikaiskbcod){
		this.daikaiskbcod = daikaiskbcod;
	}
	
	public String getDaikaiskbcod(){
		return cc.getEscapedString(daikaiskbcod);
	}
	public String getDaikaiskbcodOriginal(){
		return daikaiskbcod;
	}
	
	public void setKaiskbcod(String kaiskbcod){
		this.kaiskbcod = kaiskbcod;
	}
	
	public String getKaiskbcod(){
		return cc.getEscapedString(kaiskbcod);
	}
	public String getKaiskbcodOriginal(){
		return kaiskbcod;
	}
	
	public void setKaiSkbCodList(ArrayList kaiList){
		this.kaiList = kaiList;
	}
	
	public ArrayList getKaiSkbCodList(){
		return kaiList;
	}
	
	public void setKetCodList(ArrayList ketCodList){
		this.ketCodList = ketCodList;
	}
	
	public ArrayList getKetCodList(){
		return ketCodList;
	}
	
	public void setKetNmList(ArrayList ketNmList){
		this.ketNmList = ketNmList;
	}
	
	public ArrayList getKetNmList(){
		return ketNmList;
	}
	
	public void setKetNm2List(ArrayList ketNm2List){
		this.ketNm2List = ketNm2List;
	}
	
	public ArrayList getKetNm2List(){
		return ketNm2List;
	}
	
	public void setMkrBunList(ArrayList mkrBunList){
		this.mkrBunList = mkrBunList;
	}
	
	public ArrayList getMkrBunList(){
		return mkrBunList;
	}
	
	//-----------------------------------------------------------形態名称
	
	public HashMap getKetCod_map(){
		return ketCod_map;
	}
	public void setKetCod_map(ArrayList ketCodList, ArrayList ketNmList){
		for(int i = 0; i<ketCodList.size(); i++){
			ketCod_map.put(ketCodList.get(i)+"",ketNmList.get(i)+"");
		}
	}
	
	
	//表示区分
	public void setHyoKbn(String hyoKbn){
		this.hyoKbn = hyoKbn;
	}
	
	public String getHyoKbn(){
		return  this.hyoKbn;
	}
	
	//発注書パターン
	public void setHacSyoPtn(String hacSyoPtn){
		this.hacSyoPtn = hacSyoPtn;
	}
	
	public String getHacSyoPtn(){
		return  this.hacSyoPtn;
	}
	
	
    public ActionErrors validate(ActionMapping mapping,
                                 HttpServletRequest request) {


        ActionErrors errors = new ActionErrors();
        if ((user == null) || (user.length() < 1))
            errors.add("username", new ActionError("errors.username.required"));
        if ((pwd == null) || (pwd.length() < 1))
            errors.add("password", new ActionError("errors.password.required"));


        return errors;


    }

	/**
	 * 場所コードの取得
	 * @return
	 */
	public String getBshCod() {
		return bshCod;
	}

	/**
	 * 場所コードの設定
	 * @param string
	 */
	public void setBshCod(String string) {
		bshCod = string;
	}

}


