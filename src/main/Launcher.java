package main;

import controllers.ApplicationController;

public class Launcher  {
	
	/////////////////
	// MAIN METHOD //
	/////////////////
	
	public static void main (String[] args) {
		
		ApplicationController controller = ApplicationController.getInstance();
		controller.initializeController();
		controller.showApplication(true);
		
	}

}
