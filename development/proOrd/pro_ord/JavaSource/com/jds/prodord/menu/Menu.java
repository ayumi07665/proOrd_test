package com.jds.prodord.menu;
public class Menu {
	private String menuKbn;
	private String menuId;
	private String menuName;
	private String forwardPath;
	
	public Menu(String menuKbn, String menuId, String menuName, String forwardPath){
		this.menuKbn = menuKbn;
		this.menuId = menuId;
		this.menuName = menuName;
		this.forwardPath = forwardPath;
	}
	
	public String getMenuKbn(){
		return menuKbn;
	}
	
	public String getMenuId(){
		return menuId;
	}
	
	public String getMenuName(){
		return menuName;
	}
	
	public String getForwardPath(){
		return forwardPath;
	}
}

