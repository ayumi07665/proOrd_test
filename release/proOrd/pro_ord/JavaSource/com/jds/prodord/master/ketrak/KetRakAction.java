/**
* KetRakAction  形態ランク別マスターメンテナンスアクションクラス
*
*	作成日    2003/05/02
*	作成者    （ＮＩＩ）今井 美季
* 処理概要    処理呼び出しを行い、画面制御を行うクラス。
*
*	[変更履歴]
*        2003/05/15（ＮＩＩ）蛭田 敬子
* 			・会社識別コード、形態ランク ドロップダウン対応。
* 		 2003/05/22（ＮＩＩ）岡田 夏美
* 			・更新時の排他制御対応。
* 		 2003/06/02（ＮＩＩ）蛭田 敬子
* 			・｢削除｣の複数指定。｢登録｣の入力内容反映。｢クリア｣ボタン追加。
* 		 2003/08/22（ＮＩＩ）岡田 夏美
* 			・更新時の一括変更機能追加。
*/

package com.jds.prodord.master.ketrak;
import com.jds.prodord.common.*;

import javax.servlet.http.*;
import org.apache.struts.action.*;
import org.apache.struts.util.*;
import java.sql.*;
import java.util.*;

public class KetRakAction extends Action {
		
	/**
	 * ビジネスロジック
	 */
	private static  KetRakDelegate bzDelegate = new KetRakDelegate();
	
	
	public ActionForward perform(ActionMapping mapping,ActionForm form,HttpServletRequest req,HttpServletResponse res){
		ActionErrors errors = null;
		HttpSession session = req.getSession();
		//フォームのデータ
		KetRakForm fmForm = (KetRakForm)form;
		KetRakVO fmVO = new KetRakVO();
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
			//2003/05/15
			fmForm.setKaiSkbCodOptions(kaiList);
			fmForm.setKetCodOptions(logonForm.getKetCodList(),logonForm.getKetNm2List());//2003/05/27 2003/08/01 修正
			fmForm.setHyoKbn(logonForm.getHyoKbn()); // add 2011/05/30
			
		}
		//「クリア」押下時
		else if(fmForm.getCommand().equals(KetRakForm.COMMAND_CLEAR)){
			fmForm.clearAll();
			fmForm.setCommand("INIT");
			session.removeAttribute(CommonConst.INFOMSG_KEY);
			return (new ActionForward(mapping.getInput()));
		}


		//処理区分が照会
		if(fmForm.getPrcKbn().equals("1")){
			errors = this.select(fmForm,req,res);
			if(errors == null){	//処理成功
				req.setAttribute(CommonConst.INFOMSG_KEY,mr.getMessage("info.select.end"));
			}
			
		//処理区分が登録
		}else if(fmForm.getPrcKbn().equals("2")){
			errors = this.insert(fmForm,req,res);
			if(errors == null){	//処理成功
				req.setAttribute(CommonConst.INFOMSG_KEY,mr.getMessage("info.insert.end"));
			}
							
		//処理区分が変更
		}else if(fmForm.getPrcKbn().equals("3")){
			errors = this.update(fmForm,req,res);
			if(errors == null){	//処理成功
				req.setAttribute(CommonConst.INFOMSG_KEY,mr.getMessage("info.update.end"));
			}		

		//処理区分が削除
		}else if(fmForm.getPrcKbn().equals("4")){
			errors = this.delete(fmForm,req,res);
			if(errors == null){	//処理成功
				req.setAttribute(CommonConst.INFOMSG_KEY,mr.getMessage("info.delete.end"));
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
	private ActionErrors select (KetRakForm fmForm,HttpServletRequest req,
								 HttpServletResponse res){

		ActionErrors errors = null;
		KetRakVO fmVO = new KetRakVO();		
		String queryKaiSkbCod = fmForm.getQueryKaiSkbCod();
		String queryDaiKaiSkbCod = fmForm.getQueryDaiKaiSkbCod();
		
		
		//飛んできたフォームの値をバリューオブジェクトにセット
		fmVO.setDaiKaiSkbCod(queryDaiKaiSkbCod);
		fmVO.setKaiSkbCod(queryKaiSkbCod);
		fmVO.setQueryKaiSkbCodList(fmForm.getQueryKaiSkbCodList());
		
		//2003/05/15
		String[] kai = fmForm.getKaiSkbCod();
		String[] ket = fmForm.getKetCod();
	
		//会社識別コード	
		fmVO.setKaiSkbCod(kai[0]);	
		//ランク
		fmVO.setRank(fmForm.getRank());			
		//形態コード
		fmVO.setKetCod(ket[0]);			

		try{
			//検索実行
			KetRakVO[] resultFmVOs = bzDelegate.doFind(fmVO);
			
			fmForm.removeKetRakVO();
			
			if(resultFmVOs.length != 0){
				//検索結果をフォームにセット
				fmForm.setSize(Math.max((resultFmVOs.length),KetRakForm.DEFAULT_ROW_COUNT));
				fmForm.clearTableField();
				
				for(int i = 0;i < resultFmVOs.length;i++){
					fmForm.setOutKaiSkbCod(i,resultFmVOs[i].getKaiSkbCod());
					fmForm.setOutRank(i,resultFmVOs[i].getRank());
					fmForm.setOutKetCod(i,resultFmVOs[i].getKetCod());
					fmForm.setSsnRedTim(i,resultFmVOs[i].getSsnRedTim());
					fmForm.setMinZaiSuu(i,resultFmVOs[i].getMinZaiSuu());
					fmForm.setMinRotSuu(i,resultFmVOs[i].getMinRotSuu());
					fmForm.setMrmSuu(i,resultFmVOs[i].getMrmSuu());
					fmForm.addKetRakVO(resultFmVOs[i]);//2003/05/22 add okada
				}
			
			}else{
				fmForm.setSize(KetRakForm.DEFAULT_ROW_COUNT);
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
	 * 登録
	 * 
	 */
	private ActionErrors insert (KetRakForm fmForm,HttpServletRequest req,HttpServletResponse res){

		ActionErrors errors = null;
		KetRakVO fmVO = new KetRakVO();		
		String queryKaiSkbCod = fmForm.getQueryKaiSkbCod();
		String queryDaiKaiSkbCod = fmForm.getQueryDaiKaiSkbCod();
		//2003/05/15
		String[] kai = fmForm.getKaiSkbCod();
		String[] ket = fmForm.getKetCod();
		List lisFmVO = new ArrayList();  //バリューオブジェクトを入れる
		
		//飛んできたフォームの値をバリューオブジェクトにセット
		fmVO.setDaiKaiSkbCod(queryDaiKaiSkbCod);
//		fmVO.setKaiSkbCod(queryKaiSkbCod);
		fmVO.setKaiSkbCod(kai[0]);
//		fmVO.setQueryKaiSkbCodList(fmForm.getQueryKaiSkbCodList());
		fmVO.setRank(fmForm.getRank());
		fmVO.setKetCod(ket[0]);
		
			
		for(int i = 0; i < KetRakForm.DEFAULT_ROW_COUNT;i++){

			fmVO.setSsnRedTim(fmForm.getSsnRedTim(i));
			fmVO.setMinZaiSuu(fmForm.getMinZaiSuu(i));
			fmVO.setMinRotSuu(fmForm.getMinRotSuu(i));
			fmVO.setMrmSuu(fmForm.getMrmSuu(i));		

		}
		
		lisFmVO.add(fmVO);
		
		try{
			//登録実行
			KetRakResult result = bzDelegate.doInsert(fmVO);
		
			//エラーチェック
			if(result != null && !result.isSuccess()){
				if(errors == null)
					errors = new ActionErrors();
					
				 //登録しようとしたデータがすでに存在
				if(result.getDescription().equals(KetRakDelegate.EXIST))	
					errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.insert.duplicate"));
				 //登録しようとしたデータが論理削除中
//				else if(result.getDescription().equals(KetRakDelegate.LOGICAL_DELETE))	
//					errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.insert.logicaldelete"));

			}

			//登録内容セット
			if(errors == null){
				for(int i = 0; i < KetRakForm.DEFAULT_ROW_COUNT;i++){

					fmForm.setOutKaiSkbCod(i,result.getVO().getKaiSkbCod());
					fmForm.setOutRank(i,result.getVO().getRank());
					fmForm.setOutKetCod(i,result.getVO().getKetCod());
					fmForm.addKetRakVO(result.getVO());//2003/07/14 add okada
				}
			}
			
		}catch(SQLException sqle){
			sqle.printStackTrace();
			errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.exception",sqle.toString()));
		}	
		
		return errors;			
	}

	
	/**
	 * 更新
	 * 
	 */
	private ActionErrors update (KetRakForm fmForm,HttpServletRequest req,HttpServletResponse res){

		ActionErrors errors = null;
		String queryKaiSkbCod = fmForm.getQueryKaiSkbCod();
		String queryDaiKaiSkbCod = fmForm.getQueryDaiKaiSkbCod();
		
		List lisFmVO = new ArrayList();  //バリューオブジェクトを入れる
		boolean ikkatsuHenko = false;
		//一括変更エリアに一つでも入力があったら
		if(!fmForm.getIkkatsu_ssnRedTim().equals("") || !fmForm.getIkkatsu_minZaiSuu().equals("") || 
		   !fmForm.getIkkatsu_minRotSuu().equals("") || !fmForm.getIkkatsu_mrmSuu().equals(""))
		   ikkatsuHenko = true;
		
		for(int i = 0; i < fmForm.getSize();i++){
			KetRakVO fmVO = fmForm.getKetRakVO(i);//2003/05/22 add okada
			
			if(ikkatsuHenko){//一括変更の時…表示されている全ての行を更新対象とする
				
				if(!fmForm.getIkkatsu_ssnRedTim().trim().equals(""))
					fmVO.setSsnRedTim(fmForm.getIkkatsu_ssnRedTim());
				else
					fmVO.setSsnRedTim(fmForm.getSsnRedTim(i));
					
				if(!fmForm.getIkkatsu_minZaiSuu().trim().equals(""))
					fmVO.setMinZaiSuu(fmForm.getIkkatsu_minZaiSuu());
				else
					fmVO.setMinZaiSuu(fmForm.getMinZaiSuu(i));
					
				if(!fmForm.getIkkatsu_minRotSuu().trim().equals(""))
					fmVO.setMinRotSuu(fmForm.getIkkatsu_minRotSuu());
				else
					fmVO.setMinRotSuu(fmForm.getMinRotSuu(i));
					
				if(!fmForm.getIkkatsu_mrmSuu().trim().equals(""))
					fmVO.setMrmSuu(fmForm.getIkkatsu_mrmSuu());
				else
					fmVO.setMrmSuu(fmForm.getMrmSuu(i));
				
			}else{//チェックの付いている行が更新対象
				
				if(!fmForm.getExecute(i)){
					lisFmVO.add(null);	
					continue;
				}
				fmVO.setSsnRedTim(fmForm.getSsnRedTim(i));
				fmVO.setMinZaiSuu(fmForm.getMinZaiSuu(i));
				fmVO.setMinRotSuu(fmForm.getMinRotSuu(i));
				fmVO.setMrmSuu(fmForm.getMrmSuu(i));
				
			}
			lisFmVO.add(fmVO);
		}
		
		try{
			//更新実行
			KetRakResult[] results = bzDelegate.doUpdate((KetRakVO[])lisFmVO.toArray(new KetRakVO[lisFmVO.size()]));
		
			//エラーだったのはないか調べる
			for(int i = 0; i < results.length;i++){
				if(results[i] != null && !results[i].isSuccess()){
					if(errors == null)
						errors = new ActionErrors();
						
					 //ほかのユーザーが変更済み
					if(results[i].getDescription().equals(KetRakDelegate.ANOTHER_USER_UPDATE)||
					   results[i].getDescription().equals(KetRakDelegate.LOGICAL_DELETE)||
					   results[i].getDescription().equals(KetRakDelegate.NOT_EXIST))
						errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.anotheruserupdate"));
				}
			}
			if(errors == null){ 
				for(int i = 0; i < results.length;i++){
					if(results[i] == null)
						continue;
					fmForm.setExecute(i,false);
					KetRakVO vo = results[i].getVO();
					fmForm.setSsnRedTim(i,vo.getSsnRedTim());
					fmForm.setMinZaiSuu(i,vo.getMinZaiSuu());
					fmForm.setMinRotSuu(i,vo.getMinRotSuu());
					fmForm.setMrmSuu(i,vo.getMrmSuu());
					fmForm.setKetRakVO(i,vo);
				}
				fmForm.setIkkatsu_ssnRedTim("");
				fmForm.setIkkatsu_minZaiSuu("");
				fmForm.setIkkatsu_minRotSuu("");
				fmForm.setIkkatsu_mrmSuu("");
			}
			
		}catch(SQLException sqle){
			sqle.printStackTrace();
			errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.exception",sqle.toString()));
		}	

		return errors;			
	}




	/**
	 * 削除
	 * 
	 */
	private ActionErrors delete (KetRakForm fmForm,HttpServletRequest req,HttpServletResponse res){
		ActionErrors errors = null;
		List lisFmVO = new ArrayList();  //バリューオブジェクトを入れる
		
		for(int i = 0; i < fmForm.getSize();i++){
			if(!fmForm.getExecute(i)){//チェックなし
				lisFmVO.add(null);	
				continue;
			}
			//チェックあり
			KetRakVO fmVO = fmForm.getKetRakVO(i);
			lisFmVO.add(fmVO);
		}
		
		try{
			//削除実行
			KetRakResult[] results = bzDelegate.doDelete((KetRakVO[])lisFmVO.toArray(new KetRakVO[lisFmVO.size()]));
		
			//エラーチェック
			for(int i = 0; i < results.length;i++){
				if(results[i] != null && !results[i].isSuccess()){
					if(errors == null)
						errors = new ActionErrors();
						
					 //ほかのユーザーが変更済み
					if(results[i].getDescription().equals(KetRakDelegate.ANOTHER_USER_UPDATE))
						errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.anotheruserupdate"));
					 //削除しようとしたデータが論理削除中 か  存在しない
					else if(results[i].getDescription().equals(KetRakDelegate.LOGICAL_DELETE)||
						    results[i].getDescription().equals(KetRakDelegate.NOT_EXIST))
						errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.select.notexist"));
				}
			}
			
			//削除したレコード画面から消す
			if(errors == null){
				int index = 0;
				for(int i = 0; i < results.length;i++,index++){
					if(results[i] == null)
						continue;

					fmForm.removeTableField(index);
					fmForm.removeKetRakVO(index);
					index--;
				}
			}
			
		}catch(SQLException sqle){
			sqle.printStackTrace();
			errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.exception",sqle.toString()));
		}
		
		return errors;			
	}
	
	
}
