package controllers;

import gui.ApplicationGUI;
import gui.GUIConstants;

public class ApplicationController {
	
	////////////////
	// ATTRIBUTES //
	////////////////
	
	static ApplicationController controller;
	
	private ApplicationGUI screen;
	
	//////////////////////////////
	// CONSTRUCTORS (SINGLETON) //
	//////////////////////////////
	
	private ApplicationController() {}
	
	public static ApplicationController getInstance() {
		
		if (controller == null) controller = new ApplicationController();
		return controller;
		
	}
	
	/////////////
	// METHODS //
	/////////////
	
	public void initializeController() {
		
		screen = new ApplicationGUI(GUIConstants.APPLICATION_TITLE);
		
	}
	
	public void showApplication(boolean b) {
		
		screen.setVisible(b);
		
	}
	
	/////////////////////////
	// SETTERS AND GETTERS //
	/////////////////////////
	
	public ApplicationGUI getScreen() {
		return screen;
	}

}
