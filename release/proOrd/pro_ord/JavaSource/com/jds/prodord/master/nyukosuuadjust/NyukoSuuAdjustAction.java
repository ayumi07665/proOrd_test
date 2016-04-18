/**
* NyukoSuuAdjustAction  入庫数調整アクションクラス
*
*	作成日    2003/09/30
*	作成者    （ＮＩＩ）岡田 夏美
* 処理概要    処理呼び出しを行い、画面制御を行うクラス。
*
*	[変更履歴]
*        2003/10/24（ＮＩＩ）岡田 夏美
* 			・検索条件に発注書日付Frm-To、納期Frm-To、記号番号×10を追加
* 		 2004/06/28（ＮＩＩ）蛭田
* 			・カット＆ペースト機能追加
*/

package com.jds.prodord.master.nyukosuuadjust;
import com.jds.prodord.common.*;
import javax.servlet.http.*;
import org.apache.struts.action.*;
import org.apache.struts.util.*;
import java.sql.*;
import java.util.*;
import java.io.*;


public class NyukoSuuAdjustAction extends Action {
		
	/**
	 * ビジネスロジック
	 */
	private static  NyukoSuuAdjustDelegate bzDelegate = new NyukoSuuAdjustDelegate();
	
	
	public ActionForward perform(ActionMapping mapping,ActionForm form,HttpServletRequest req,HttpServletResponse res){
		ActionErrors errors = null;
		HttpSession session = req.getSession();
		//フォームのデータ
		NyukoSuuAdjustForm fmForm = (NyukoSuuAdjustForm)form;
		NyukoSuuAdjustVO fmVO = new NyukoSuuAdjustVO();
		MessageResources mr = super.getResources();//通常メッセージ表示用にリソースファイルからメッセージをとってくるのに使う
		

		//サービス時間のチェック
		if(errors == null)
			errors = new ActionErrors();
		int check_result = CheckCommon.checkBatchDate(); //サービス時間のチェック
		switch(check_result){
			//内部エラーのとき
			case CheckCommon.errors_internal:
				errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.internal"));
				break;
			//サービス時間外のとき	
			case CheckCommon.offservice:
				errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.offservice"));
				break;
			//エラーなしのとき	
			case CheckCommon.success:
				errors = null;
		}
		
		//エラーあり
		if(errors != null){
			super.saveErrors(req,errors);
    		return (new ActionForward(mapping.getInput()));
		}



		//--------以下、フォームの押したボタンから処理分岐を行う
		
		//最初にメニューからきたとき、照会結果画面から「戻る」ボタンで戻ってきたとき
		if(fmForm.getCommand().equals("INIT")){
			fmForm.clearAll();

	   		com.jds.prodord.register.LogonForm logonForm 
		  			= (com.jds.prodord.register.LogonForm)session.getAttribute(CommonConst.user);
			String daiKaiSkbCod = 	logonForm.getDaikaiskbcod();
			String KaiSkbCod = 	logonForm.getKaiskbcod();
			ArrayList kaiList = logonForm.getKaiSkbCodList();
			
			fmForm.setQueryDaiKaiSkbCod(daiKaiSkbCod);
			fmForm.setQueryKaiSkbCod(KaiSkbCod);
			fmForm.setQueryKaiSkbCodList(kaiList);
			fmForm.setHyoKbn(logonForm.getHyoKbn());
			
		}
		//「クリア」押下時
		else if(fmForm.getCommand().equals(NyukoSuuAdjustForm.COMMAND_CLEAR)){
			fmForm.clearAll();
			fmForm.setUpdatable(false);
			fmForm.setCommand("INIT");
			session.removeAttribute(CommonConst.INFOMSG_KEY);
			return (new ActionForward(mapping.getInput()));

		//｢貼り付け｣押下時  2004/06/28 add
		}else if(fmForm.getCommand().equals(NyukoSuuAdjustForm.COMMAND_PASTE)){
			dataPaste(fmForm);
			fmForm.setCpPaste("");
			return (new ActionForward(mapping.getInput()));
		}


		//処理区分が照会
		if(fmForm.getPrcKbn().equals("1")){
			errors = this.select(fmForm,req,res);
			if(errors == null){	//処理成功
				req.setAttribute(CommonConst.INFOMSG_KEY,mr.getMessage("info.select.end"));
				fmForm.setUpdatable(true);
			}else
				fmForm.setUpdatable(false);		
		//処理区分が更新
		}else if(fmForm.getPrcKbn().equals("2")){
			errors = this.nyoSuuAdjust(fmForm,req,res);
			if(errors == null){	//処理成功
				req.setAttribute(CommonConst.INFOMSG_KEY,mr.getMessage("info.update.end"));
				fmForm.setUpdatable(false);
			}		
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

	
	/**
	 * 照会
	 * 
	 */
	private ActionErrors select (NyukoSuuAdjustForm fmForm,HttpServletRequest req,
								 HttpServletResponse res){

		ActionErrors errors = null;
		NyukoSuuAdjustVO fmVO = new NyukoSuuAdjustVO();		
		
		//飛んできたフォームの値をバリューオブジェクトにセット
		fmVO.setQueryDaiKaiSkbCod(fmForm.getQueryDaiKaiSkbCod());
		fmVO.setQueryKaiSkbCodList(fmForm.getQueryKaiSkbCodList());
		
		fmVO.setHacCod_H(fmForm.getHacCod_H().trim());
		fmVO.setHacDteFrm_H(fmForm.getHacDteFrm_H());
		fmVO.setHacDteTo_H(fmForm.getHacDteTo_H());
		fmVO.setNkiFrm_H(fmForm.getNkiFrm_H());
		fmVO.setNkiTo_H(fmForm.getNkiTo_H());
		//記号番号
		ArrayList arrKig = new ArrayList();
		ArrayList arr = DataFormatUtils.supplement_kigBng(fmForm.getKigBng_List());
		for(int i = 0; i < arr.size(); i++){
			arrKig.add(DataFormatUtils.mk_srykig(arr.get(i).toString()));
		}
		fmVO.setkigBng_arr_H(DataFormatUtils.removeBlankElement(arrKig));
		
		try{
			//検索実行
			NyukoSuuAdjustVO[] resultFmVOs = bzDelegate.doFind(fmVO);
			
			fmForm.removeNyukoSuuAdjustVO();
			
			if(resultFmVOs.length != 0){
				//検索結果をフォームにセット
				fmForm.setSize(Math.max((resultFmVOs.length),NyukoSuuAdjustForm.DEFAULT_ROW_COUNT));
				fmForm.clearTableField();
				
				for(int i = 0;i < resultFmVOs.length;i++){
					NyukoSuuAdjustFormRow formRow = fmForm.getFormRow(i);
					formRow.setHacCod(resultFmVOs[i].getHacCod());
					formRow.setKbn(DataFormatUtils.getKbnString(resultFmVOs[i].getKbn()));
					formRow.setKigBng((!resultFmVOs[i].getHjiHnb().equals(""))? resultFmVOs[i].getHjiHnb() : resultFmVOs[i].getKigBng());
					formRow.setHacSyoDte(DataFormatUtils.setFormatYYMMDD(resultFmVOs[i].getHacSyoDte()+""));
					formRow.setHacSyoBng(resultFmVOs[i].getHacSyoBng());
					formRow.setGyoBng(resultFmVOs[i].getGyoBng());
					formRow.setHacSuu(DataFormatUtils.setFormatString(resultFmVOs[i].getHacSuu()));
					formRow.setNyoSuu(DataFormatUtils.setFormatString(resultFmVOs[i].getNyoSuu()));
					formRow.setKnuSgn(resultFmVOs[i].getKnuSgn());
					formRow.setNki(DataFormatUtils.setFormatYYMMDD(resultFmVOs[i].getNki()));
					fmForm.addNyukoSuuAdjustVO(resultFmVOs[i]);
				}
				fmForm.setCount(fmForm.getSize());
			}else{
				fmForm.setCount(0);
				fmForm.setSize(NyukoSuuAdjustForm.DEFAULT_ROW_COUNT);
				fmForm.clearTableField();
				errors = new ActionErrors();
				errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.select.notexist")); 
			}
			
			
		}catch(SQLException sqle){
			sqle.printStackTrace();
			if(errors == null)
				errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.exception",sqle.toString()));
		}		
		return errors;			
	}
	

	
	/**
	 * 入庫数調整
	 * 
	 */
	private ActionErrors nyoSuuAdjust (NyukoSuuAdjustForm fmForm,HttpServletRequest req,HttpServletResponse res){

		ActionErrors errors = null;
		String queryKaiSkbCod = fmForm.getQueryKaiSkbCod();
		String queryDaiKaiSkbCod = fmForm.getQueryDaiKaiSkbCod();
		
		List lisFmVO = new ArrayList();  //バリューオブジェクトを入れる
		
		for(int i = 0; i < fmForm.getSize();i++){
			NyukoSuuAdjustFormRow formRow = fmForm.getFormRow(i);
			if(!formRow.getExecute()){
				lisFmVO.add(null);	
				continue;
			}
			NyukoSuuAdjustVO fmVO = fmForm.getNyukoSuuAdjustVO(i);
			fmVO.setKnuSgn(formRow.getKnuSgn());
				
			lisFmVO.add(fmVO);
		}
		
		try{
			//更新実行
			NyukoSuuAdjustResult[] results = bzDelegate.doNyukoSuuAdjust((NyukoSuuAdjustVO[])lisFmVO.toArray(new NyukoSuuAdjustVO[lisFmVO.size()]));
		
			//エラーだったのはないか調べる
			for(int i = 0; i < results.length;i++){
				if(results[i] != null && !results[i].isSuccess()){
					if(errors == null)
						errors = new ActionErrors();
						
					 //ほかのユーザーが変更済み
					if(results[i].getDescription().equals(NyukoSuuAdjustDelegate.ANOTHER_USER_UPDATE)||
					   results[i].getDescription().equals(NyukoSuuAdjustDelegate.LOGICAL_DELETE)||
					   results[i].getDescription().equals(NyukoSuuAdjustDelegate.NOT_EXIST))
						errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.anotheruserupdate"));
					else{
						errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.internal"));
					}
				}
			}
			if(errors == null){ 
				//完納サインを立てたら、それ以外の行を画面から消す
				for(int i = 0,index = 0; i < results.length;i++,index++){
					if(results[i] != null)
						continue;
					fmForm.removeTableField(index);
					fmForm.removeNyukoSuuAdjustVO(index);
					index--;
				}
				for(int i = 0; i < fmForm.getSize(); i++){
					fmForm.getFormRow(i).setNyoSuu(DataFormatUtils.setFormatString(fmForm.getNyukoSuuAdjustVO(i).getNyoSuu()));
				}
				fmForm.setCount(fmForm.getSize());
			}
			
		}catch(SQLException sqle){
			sqle.printStackTrace();
			errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.exception",sqle.toString()));
		}	

		return errors;			
	}

	/** 
	 * 貼り付け
	 * @param fmForm
	 */
	public void dataPaste(NyukoSuuAdjustForm fmForm){
		
		final String tab = "\t";
		String data = fmForm.getCpPaste();
		BufferedReader br = new BufferedReader(new StringReader(data));
		String txt = "";
		int count = DataFormatUtils.getDataRowCount(data);

		
		try {

			for (int i = 0; i < count; i++) {
			
				if((txt = br.readLine()) != null){

					txt = DataFormatUtils.insertSpace(txt, tab);
					StringTokenizer line = new StringTokenizer(txt, tab);	
		
					if(line.hasMoreTokens()){
									
						String str = line.nextToken().trim();
						
						if(fmForm.getKigBng1_H().equals("")){
							fmForm.setKigBng1_H(str);
							continue;
						}
						if(fmForm.getKigBng2_H().equals("")){
							fmForm.setKigBng2_H(str);					
							continue;
						}
						if(fmForm.getKigBng3_H().equals("")){
							fmForm.setKigBng3_H(str);
							continue;
						}
						if(fmForm.getKigBng4_H().equals("")){
							fmForm.setKigBng4_H(str);
							continue;
						}
						if(fmForm.getKigBng5_H().equals("")){
							fmForm.setKigBng5_H(str);
							continue;
						}
						if(fmForm.getKigBng6_H().equals("")){
							fmForm.setKigBng6_H(str);
							continue;
						}
						if(fmForm.getKigBng7_H().equals("")){
							fmForm.setKigBng7_H(str);
							continue;
						}
						if(fmForm.getKigBng8_H().equals("")){
							fmForm.setKigBng8_H(str);
							continue;
						}
						if(fmForm.getKigBng9_H().equals("")){
							fmForm.setKigBng9_H(str);
							continue;
						}
						if(fmForm.getKigBng10_H().equals("")){
							fmForm.setKigBng10_H(str);
							continue;
						}
					}
				}
			}

		}catch(IOException ex){
			ex.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	

}
