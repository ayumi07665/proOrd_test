/**
* OrderHistoryTopAction  発注履歴照会画面検索条件アクションクラス
*
*	作成日    2003/04/18
*	作成者    （ＮＩＩ）蛭田 敬子
* 処理概要    処理呼び出しを行い、画面制御を行うクラス。
*
*	[変更履歴]
*        2003/09/05（ＮＩＩ）岡田 夏美
* 			・発注書番号のフォーマットメソッド追加
* 		 2004/04/01（ＮＩＩ）森
* 			・指定条件に入庫日(From～To)追加
* 		 2005/05/25（ＮＩＩ）蛭田
* 			・場所コードの追加
* 
*/

package com.jds.prodord.reference.orderhistory;
import com.jds.prodord.common.*;
import com.jds.prodord.register.LogonForm;
import javax.servlet.http.*;
import org.apache.struts.action.*;
import org.apache.struts.util.*;
import java.sql.*;
import java.util.*;

public class OrderHistoryTopAction extends Action {

	/**
	 * ビジネスロジック
	 */
	private static  OrderHistoryDelegate bzDelegate = new OrderHistoryDelegate();
	
	
	public ActionForward perform(ActionMapping mapping,ActionForm form,HttpServletRequest req,HttpServletResponse res){

		ActionErrors errors = null;
		HttpSession session = req.getSession();
		
		//フォームのデータ
		OrderHistoryTopForm fmForm = (OrderHistoryTopForm)form;			
		OrderHistoryVO fmVO = new OrderHistoryVO();

		MessageResources mr = super.getResources();//通常メッセージ表示用にリソースファイルからメッセージをとってくるのに使う
	

		//--------以下、フォームの押したボタンから処理分岐を行う
		//最初にメニューからきたとき
		if(fmForm.getCommand().equals("INIT")){
	   		LogonForm logonForm = (LogonForm)session.getAttribute("user");

			String daiKaiSkbCod = 	logonForm.getDaikaiskbcod();
			ArrayList kaiList = logonForm.getKaiSkbCodList();
			
			fmForm.setQueryDaiKaiSkbCod(daiKaiSkbCod);
			fmForm.setQueryKaiSkbCodList(kaiList);
			fmForm.setHyoKbn(logonForm.getHyoKbn());
			fmForm.setBshCod(logonForm.getBshCod());//2005/05/25 add

			//選択項目のオプションをセット
			fmForm.setMkrBunOptions(logonForm.getMkrBunList());
			
			session.removeAttribute(CommonConst.ORDERHISTORY_VO);
			session.removeAttribute(CommonConst.INFOMSG_KEY);
			fmForm.clear_text();
			if(errors == null){	//処理成功

			}
		}
		
		//「実行」押下時
		else if(fmForm.getCommand().equals(OrderHistoryTopForm.COMMAND_JIKKOU)){
			errors = this.jikkou(fmForm,req,res,fmVO);
			if(errors == null){	//処理成功
				session.setAttribute(CommonConst.ORDERHISTORY_VO,fmVO);
				return (new ActionForward(mapping.getInput()));
			}else{
				session.removeAttribute(CommonConst.ORDERHISTORY_VO);
			}

		//「クリア」押下時
		}else if(fmForm.getCommand().equals(OrderHistoryTopForm.COMMAND_CLEAR)){
			fmForm.clear_text();
			fmForm.setCommand("INIT");
			session.removeAttribute(CommonConst.ORDERHISTORY_VO);
			session.removeAttribute(CommonConst.INFOMSG_KEY);
			return (new ActionForward(mapping.getInput()));
		}		
		//--------処理分岐終わり
		
		//エラーあり
		if(errors != null){
			super.saveErrors(req,errors);
    		return (new ActionForward(mapping.getInput()));    
		}
			
		//エラーなし
		return mapping.findForward("success");		
	}
	
	
//**************************************************************************************************

	/**
	 * 実行
	 * 
	 */
	private ActionErrors jikkou (OrderHistoryTopForm fmForm,HttpServletRequest req,HttpServletResponse res,OrderHistoryVO fmVO){

		ActionErrors errors = null;
		HttpSession session = req.getSession();
		String hacSyoPtn = ((LogonForm)session.getAttribute(CommonConst.user)).getHacSyoPtn();
		
		//飛んできたフォームの値をバリューオブジェクトにセット
		fmVO.setDaiKaiSkbCod(fmForm.getQueryDaiKaiSkbCod());
		fmVO.setKaiSkbCod(fmForm.getQueryKaiSkbCod());
		fmVO.setKaiSkbCodList(fmForm.getQueryKaiSkbCodList());
		fmVO.setBshCod(fmForm.getBshCod());//2005/05/25 add

		//コマンド
		fmVO.setCommand(fmForm.getCommand());
		//処理区分
		fmVO.setPrcKbn(fmForm.getPrcKbn());
		
		fmVO.setSyrZmiSgn_H(fmForm.getRb_HacSyo());//発注書
		fmVO.setBunCod_H(fmForm.getRb_BunCod());  //分類コード
		fmVO.setNyuko_H(fmForm.getRb_Nyuko());  //入庫状況
		fmVO.setHacCod_H(fmForm.getQueryHacCod());//発注先コード
		fmVO.setKigBng_H(DataFormatUtils.mk_srykig(fmForm.getQueryKigBng()));//記号番号
		//発売日
		fmVO.setHbiDte_H(fmForm.getQueryHbiDte_Y(),fmForm.getQueryHbiDte_M(),fmForm.getQueryHbiDte_D());
		//発注書番号
		if(fmForm.getCheckHacBng()){
			if(!fmForm.getQueryHacBngTo().equals("")){
				fmVO.setHacSyoBngFrm_H(DataFormatUtils.makeHacSyoBng(fmForm.getQueryHacBngFrm(),hacSyoPtn));
				fmVO.setHacSyoBngTo_H(DataFormatUtils.makeHacSyoBng(fmForm.getQueryHacBngTo(),hacSyoPtn));
				fmForm.setQueryHacBngFrm(fmVO.getHacSyoBngFrm_H());
				fmForm.setQueryHacBngTo(fmVO.getHacSyoBngTo_H());
			}else{
				if(!fmForm.getQueryHacBngFrm().equals("")){
					fmVO.setHacSyoBngFrm_H(DataFormatUtils.makeHacSyoBng(fmForm.getQueryHacBngFrm(),hacSyoPtn));
					fmForm.setQueryHacBngFrm(fmVO.getHacSyoBngFrm_H());
				}
			}
		}
		//発注日
		if(fmForm.getCheckHacDte()){
			if(!fmForm.getQueryHacDteFrm_Y().equals("") && !fmForm.getQueryHacDteFrm_M().equals("")  
			   && !fmForm.getQueryHacDteFrm_D().equals("")){
					fmVO.setHacSyoDteFrm_H(fmForm.getQueryHacDteFrm_Y(),fmForm.getQueryHacDteFrm_M(),fmForm.getQueryHacDteFrm_D());
					fmVO.setHacSyoDteTo_H(fmForm.getQueryHacDteTo_Y(),fmForm.getQueryHacDteTo_M(),fmForm.getQueryHacDteTo_D());
			}else{
				if(!fmForm.getQueryHacDteTo_Y().equals("") && !fmForm.getQueryHacDteTo_M().equals("") && 
			       !fmForm.getQueryHacDteTo_D().equals("")){
			       		fmVO.setHacSyoDteFrm_H(fmForm.getQueryHacDteFrm_Y(),fmForm.getQueryHacDteFrm_M(),fmForm.getQueryHacDteFrm_D());
			    }
			}
		}
		//納期
		if(fmForm.getCheckNki()){
			if(!fmForm.getQueryNkiFrm_Y().equals("") && !fmForm.getQueryNkiFrm_M().equals("") 
			  && !fmForm.getQueryNkiFrm_D().equals("")){
				fmVO.setNkiFrm_H(fmForm.getQueryNkiFrm_Y(),fmForm.getQueryNkiFrm_M(),fmForm.getQueryNkiFrm_D());
				fmVO.setNkiTo_H(fmForm.getQueryNkiTo_Y(),fmForm.getQueryNkiTo_M(),fmForm.getQueryNkiTo_D());
			}else{
				if(!fmForm.getQueryNkiTo_Y().equals("") && !fmForm.getQueryNkiTo_M().equals("") 
			  	  && !fmForm.getQueryNkiTo_D().equals("")){
					fmVO.setNkiFrm_H(fmForm.getQueryNkiFrm_Y(),fmForm.getQueryNkiFrm_M(),fmForm.getQueryNkiFrm_D());
			  	}
			}
		}
		//メーカー分類
		if(fmForm.getCheckMkrBun()){
			if(fmForm.getMkrBun() != null)
				fmVO.setMkrBun(fmForm.getMkrBun());
		}
		//区分
		if(fmForm.getCheckKbn()){
			fmVO.setKbn_H(fmForm.getKbn());
		}
		//入庫日  2004/04/01 add
		if((fmForm.getCheckNyoDte())){
			if(!fmForm.getQueryNyoDteFrm_Y().equals("") && !fmForm.getQueryNyoDteFrm_M().equals("") 
			  && !fmForm.getQueryNyoDteFrm_D().equals("")){
				fmVO.setNyoDteFrm_H(fmForm.getQueryNyoDteFrm_Y(),fmForm.getQueryNyoDteFrm_M(),fmForm.getQueryNyoDteFrm_D());
				fmVO.setNyoDteTo_H(fmForm.getQueryNyoDteTo_Y(),fmForm.getQueryNyoDteTo_M(),fmForm.getQueryNyoDteTo_D());
			}else{
				if(!fmForm.getQueryNyoDteTo_Y().equals("") && !fmForm.getQueryNyoDteTo_M().equals("") 
				  && !fmForm.getQueryNyoDteTo_D().equals("")){
					fmVO.setNyoDteFrm_H(fmForm.getQueryNyoDteFrm_Y(),fmForm.getQueryNyoDteFrm_M(),fmForm.getQueryNyoDteFrm_D());
				}
			}
		}
		//発注書順 2005/09/16 add
		fmVO.setChkHacJun(fmForm.isChkHacJun());
		
		try{
			//エラーチェック実行
			OrderHistoryResult result = bzDelegate.doJikkou((OrderHistoryVO)fmVO);
		
			//エラーだったのはないか調べる
			if(result != null && !result.isSuccess()){
				if(errors == null)
					errors = new ActionErrors();
				if(result.getDescription().equals(OrderHistoryDelegate.KIGBNG_NOT_EXIST))
					errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.input.incorrect","記号番号"));
                if(result.getDescription().equals(OrderHistoryDelegate.HACSAKI_NOT_EXIST))
					errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.input.incorrect","発注先コード"));
			}
		
		}catch(SQLException sqle){
			SystemException se = new SystemException(sqle);
			se.printStackTrace();
			errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.exception",sqle.toString()));
		}		
		return errors;			
	}
	
}

