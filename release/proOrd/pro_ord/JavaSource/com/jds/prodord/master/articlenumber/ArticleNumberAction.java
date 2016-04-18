/**
* ArticleNumberAction  品番マスターメンテナンスアクションクラス
*
*	作成日    2003/08/25
*	作成者    （ＮＩＩ）村上  博基 
* 処理概要    処理呼び出しを行い、画面制御を行うクラス。
*	[変更履歴]
*        2003/09/11（ＮＩＩ）村上  博基
* 			・品番マスターでも更新できるように処理変更。
* 		 2003/10/07（ＮＩＩ）岡田  夏美
* 			・検索・登録時にFormにVOを保存、更新・削除時はそのVOを利用して
* 			  処理を行なうように変更
*/



package com.jds.prodord.master.articlenumber;

import javax.servlet.http.*;
import org.apache.struts.action.*;
import org.apache.struts.util.*;
import java.sql.*;
import java.util.*;
import com.jds.prodord.common.*;

public class ArticleNumberAction extends Action {

		private static final String INFOMSG_KEY = "INFOMSG_KEY";

		private static  ArticleNumberDelegate bzDelegate = new ArticleNumberDelegate();


	public ActionForward perform(ActionMapping mapping,ActionForm form,HttpServletRequest req,HttpServletResponse res){

		ActionErrors errors = null;
		HttpSession session = req.getSession();		//フォームのデータ
		ArticleNumberForm fmForm = (ArticleNumberForm)form;
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
	
//=====  以下、フォームの押したボタンから処理分岐を行う  ==========================================

		//***  マスターメニューから品番照会へのボタンを押した時  ***
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
			fmForm.setKaiSkbCodOptions(kaiList);
			fmForm.setKetCodOptions(logonForm.getKetCodList(),logonForm.getKetNm2List());
			fmForm.setHyoKbn(logonForm.getHyoKbn());

		}


		//***  品番マスター照会の実行ボタンが押しされた時  ***
		if(fmForm.getCommand().equals(ArticleNumberForm.COMMAND_JITTKOU)){

				if(fmForm.getPrcKbn().equals("1")){
						errors = this.find(fmForm,req,res);
					if(errors == null)
						req.setAttribute(INFOMSG_KEY,mr.getMessage("info.select.end"));
				
				}else if(fmForm.getPrcKbn().equals("2")){
						errors = this.insert(fmForm,req,res);
					if(errors == null)
						req.setAttribute(INFOMSG_KEY,mr.getMessage("info.insert.end"));

				}else if(fmForm.getPrcKbn().equals("3")){
						errors = this.update(fmForm,req,res);
					if(errors == null)
						req.setAttribute(INFOMSG_KEY,mr.getMessage("info.update.end"));

				}else if(fmForm.getPrcKbn().equals("4")){
						errors = this.delete(fmForm,req,res);
					if(errors == null)
						req.setAttribute(INFOMSG_KEY,mr.getMessage("info.delete.end"));
				}

		//***  品番マスター照会のクリアボタンが押された時  ***
		}else if(fmForm.getCommand().equals(ArticleNumberForm.COMMAND_CLEAR)){

			fmForm.clearAll();
			fmForm.removeArticleNumberVO();
			fmForm.setCommand("INIT");
			return (new ActionForward(mapping.getInput()));
		}

		if(errors != null){
			super.saveErrors(req,errors);
    		return (new ActionForward(mapping.getInput()));
		}

		//エラーなし
		return mapping.findForward("success");		
	}

//*****  検索  **************************************************************************

	private ActionErrors find (ArticleNumberForm fmForm,HttpServletRequest req,HttpServletResponse res){

		ActionErrors errors = null;
		ArticleNumberVO fmVO = new ArticleNumberVO();
		String[] ket = new String[1];


		String queryDaiKaiSkbCod = fmForm.getQueryDaiKaiSkbCod();
		String queryKaiSkbCod = fmForm.getQueryKaiSkbCod();
		ArrayList queryKaiSkbCodList = fmForm.getQueryKaiSkbCodList();

		//検索キーを入れるバリューオブジェクト
		fmVO.setQueryDaiKaiSkbCod(queryDaiKaiSkbCod);
		fmVO.setQueryKaiSkbCod(queryKaiSkbCod);
		String[] kai = fmForm.getKaiSkbCod();

		//会社識別コード	
		fmVO.setKaiSkbCod(kai[0]);
		//省略品番対応後ＶＯにセット
		fmVO.setKigoBan(DataFormatUtils.mk_srykig(fmForm.getKigoBan().trim()));

	    	HttpSession session = req.getSession(); 
    		com.jds.prodord.register.LogonForm logonForm
			  			= (com.jds.prodord.register.LogonForm)session.getAttribute("user");

		try{
			//検索実行
			ArticleNumberVO resultFmVO = bzDelegate.doFind(fmVO);

			fmForm.clearTableField1();

            if(resultFmVO.getFind_flag() == true){	

				//検索結果をフォームにセット
				fmForm.setArtist(resultFmVO.getArtist());
				fmForm.setTitle(resultFmVO.getTitle());

				ket[0] = resultFmVO.getKetCod();
				fmForm.setKetCod(ket);

				String hbidte = hbiDateFormat(resultFmVO.getHbiDte());
	
				if(!hbidte.equals("")){
					fmForm.setYear(hbidte.substring(0,2));
					fmForm.setMonth(hbidte.substring(2,4));
					fmForm.setDay(hbidte.substring(4,6));
				}

				fmForm.setPrsMkrCod(resultFmVO.getPrsMkrCod());
				fmForm.setJytPrsMkr(resultFmVO.getJytPrsMkr());
				fmForm.setHukSizCod(resultFmVO.getHukSizCod());
				fmForm.setDbName(resultFmVO.getDbName());
				fmForm.setFindedFlag(true);
				fmForm.setArticleNumberVO(resultFmVO);//2003/10/07 add
            }else{
				//データが存在しません
				fmForm.removeArticleNumberVO();//2003/10/07 add
				errors = new ActionErrors();
				errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.select.notexist"));
			}
			
		}catch(SQLException sqle){
			sqle.printStackTrace();

			errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.exception",sqle.toString()));
			
		}
		return errors;

	}  //検索処理終了



//*****  登録  **************************************************************************

	private ActionErrors insert (ArticleNumberForm fmForm,HttpServletRequest req,HttpServletResponse res){

		ActionErrors errors = null;
		ArticleNumberVO fmVO = new ArticleNumberVO();	

		String queryDaiKaiSkbCod = fmForm.getQueryDaiKaiSkbCod();
		String[] kai = fmForm.getKaiSkbCod();
		String hbiDte = "";

		//飛んできたフォームの値をバリューオブジェクトにセット
		fmVO.setDaiKaiSkbCod(queryDaiKaiSkbCod);
		fmVO.setKaiSkbCod(kai[0]);
		fmVO.setKigoBan(DataFormatUtils.mk_srykig(fmForm.getKigoBan().trim()));
		fmVO.setArtist(fmForm.getArtist());
		fmVO.setTitle(fmForm.getTitle());

		String[] ket = fmForm.getKetCod();
		fmVO.setKetCod(ket[0]);

		if(fmForm.getYear().equals(""))
			fmVO.setHbiDte("0");
		else
			fmVO.setHbiDte(Integer.toString((Integer.parseInt(fmForm.getYear(),10)*10000 +
			Integer.parseInt(fmForm.getMonth(),10)*100 + Integer.parseInt(fmForm.getDay(),10)),10));

		fmVO.setPrsMkrCod(fmForm.getPrsMkrCod());
		fmVO.setJytPrsMkr(fmForm.getJytPrsMkr());
		fmVO.setHukSizCod(fmForm.getHukSizCod());

		try{
			//登録実行
			ArticleNumberResult result = bzDelegate.doInsert(fmVO);

			//エラーチェック
			if(result != null && !result.isSuccess()){
				if(errors == null)
					errors = new ActionErrors();

				 //登録しようとしたデータがすでに存在
				if(result.getDescription().equals(ArticleNumberDelegate.EXIST))	
					errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.insert.duplicate"));
				 //登録しようとしたデータが論理削除中
				else if(result.getDescription().equals(ArticleNumberDelegate.LOGICAL_DELETE))	
					errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.insert.logicaldelete"));
				if(result.getDescription().equals(ArticleNumberDelegate.NOTEXIST_HACCOD)){
					ArrayList err = result.getError();
					for(int i = 0; i < err.size(); i++){
						errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.input.incorrect",err.get(i)+""));
					}
				}
			}else{//2003/10/07 add okada	
				fmForm.setArticleNumberVO(fmVO);
				fmForm.setDbName(fmVO.getDbName());			
			}

								
		}catch(SQLException sqle){
			sqle.printStackTrace();
			errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.exception",sqle.toString()));
		}	
		return errors;
	}



	
//*****  更新  **************************************************************************

	private ActionErrors update (ArticleNumberForm fmForm,HttpServletRequest req,HttpServletResponse res){


		ActionErrors errors = null;
		ArticleNumberVO fmVO = new ArticleNumberVO();	

		//飛んできたフォームの値をバリューオブジェクトにセット
		fmVO = fmForm.getArticleNumberVO();//2003/10/07 add
		fmVO.setPrcKbn(fmForm.getPrcKbn());  //03/09/11 追加
		
		fmVO.setArtist(fmForm.getArtist());
		fmVO.setTitle(fmForm.getTitle());

		String[] ket = fmForm.getKetCod();
		fmVO.setKetCod(ket[0]);
		
		if(fmForm.getYear().equals(""))
			fmVO.setHbiDte("0");
		else
			fmVO.setHbiDte(Integer.toString((Integer.parseInt(fmForm.getYear(),10)*10000 +
		 	Integer.parseInt(fmForm.getMonth(),10)*100 + Integer.parseInt(fmForm.getDay(),10)),10));


		fmVO.setPrsMkrCod(fmForm.getPrsMkrCod());
		fmVO.setJytPrsMkr(fmForm.getJytPrsMkr());
		fmVO.setHukSizCod(fmForm.getHukSizCod());

		try{
			//更新実行
			ArticleNumberResult result = bzDelegate.doUpdate(fmVO);
			
			//エラーだったのはないか調べる

			if(result != null && !result.isSuccess()){
				if(errors == null)
					errors = new ActionErrors();
						
				 //ほかのユーザーが変更済み
				if(result.getDescription().equals(ArticleNumberDelegate.ANOTHER_USER_UPDATE)||
				   result.getDescription().equals(ArticleNumberDelegate.LOGICAL_DELETE)||
				   result.getDescription().equals(ArticleNumberDelegate.NOT_EXIST))
					errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.anotheruserupdate"));

				if(result.getDescription().equals(ArticleNumberDelegate.NOTEXIST_HACCOD)){
					ArrayList err = result.getError();
					for(int i = 0; i < err.size(); i++){
						errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.input.incorrect",err.get(i)+""));
					}
				}
			}else{//2003/10/07 add okada	
				fmForm.setArticleNumberVO(fmVO);
				fmForm.setDbName(fmVO.getDbName());			
			}


		}catch(SQLException sqle){
			sqle.printStackTrace();
			errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.exception",sqle.toString()));
		}

		return errors;
	}


//*****  削除  **************************************************************************

	private ActionErrors delete (ArticleNumberForm fmForm,HttpServletRequest req,HttpServletResponse res){

		ActionErrors errors = null;
		ArticleNumberVO fmVO = new ArticleNumberVO();	
			
		fmVO = fmForm.getArticleNumberVO();//2003/10/07 add
		
	try{
			//削除実行
			ArticleNumberResult result = bzDelegate.doDelete(fmVO);

			//エラーチェック
				if(result != null && !result.isSuccess()){
					if(errors == null)
						errors = new ActionErrors();

					 //ほかのユーザーが変更済み
					if(result.getDescription().equals(ArticleNumberDelegate.ANOTHER_USER_UPDATE)) 
						errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.anotheruserupdate",String.valueOf(1)));
					 //削除しようとしたデータが論理削除中 か  存在しない
					else if(result.getDescription().equals(ArticleNumberDelegate.LOGICAL_DELETE)||
						    result.getDescription().equals(ArticleNumberDelegate.NOT_EXIST))	
						errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.select.notexist"));
					else if(result.getDescription().equals(ArticleNumberDelegate.CANNOT_DELETE))
						errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.cannot","品番マスターでの対象品番","削除"));
					else if(result.getDescription().equals(ArticleNumberDelegate.EXIST_HAC02))
						errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.cannot","対象品番","発注履歴に存在する為、削除"));

				}else{//2003/10/07 add
					fmForm.clearTableField1();
					fmForm.setDbName(fmVO.getDbName());
					fmForm.removeArticleNumberVO();
				}

			
		}catch(SQLException sqle){
			sqle.printStackTrace();
			errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.exception",sqle.toString()));
		}

		return errors;			
	}


//-----ＤＢから取得した発売日のサイズが６桁になっているか判定-------------------

	private String hbiDateFormat(String str){

		String hbiDte;

		if(str.equals("0") || str.equals("")){
			hbiDte = "";
			return hbiDte;
		}

		hbiDte = StringUtils.lpad(str,6,"0");

//		System.out.println("Action 検索後の発売日変換："+hbiDte);

	return hbiDte;

	}


}  //クラス終了
