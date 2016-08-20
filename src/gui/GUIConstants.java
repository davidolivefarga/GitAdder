package gui;

import java.awt.Color;
import java.awt.Font;

public class GUIConstants {
	
	// Main panel constants
	
	public static String APPLICATION_TITLE = "GitAdder Application";
	
	public static Color MAIN_FRAME_COLOR = Color.WHITE;
	public static int MAIN_FRAME_WIDTH = 800;
	public static int MAIN_FRAME_LENGTH = 600;
	
	// File root panel constants
	
	public static Color FILE_ROOT_PANEL_COLOR = Color.LIGHT_GRAY;
	public static int FILE_ROOT_PANEL_WIDTH = 800;
	public static int FILE_ROOT_PANEL_LENGTH = 70;
	
	public static Color FILE_ROOT_PANEL_CONTAINER_COLOR = Color.LIGHT_GRAY;
	
	public static String FILE_ROOT_PANEL_BUTTON_TEXT = "Select project folder";
	public static int FILE_ROOT_PANEL_BUTTON_WIDTH = 150;
	public static int FILE_ROOT_PANEL_BUTTON_LENGTH = 30;
	
	public static int FILE_ROOT_PANEL_TEXTFIELD_WIDTH = 450;
	public static int FILE_ROOT_PANEL_TEXTFIELD_LENGTH = 30;
	
	// File tree panel constants
	
	public static Color FILE_TREE_PANEL_COLOR = Color.WHITE;
	public static int FILE_TREE_PANEL_WIDTH = 400;
	public static int FILE_TREE_PANEL_LENGTH = 480;
	
	public static int FILE_TREE_PANEL_TREE_WIDTH = 300;
	public static int FILE_TREE_PANEL_TREE_LENGTH = 450;
	
	public static Color FILE_TREE_PANEL_SELECTED_F_COLOR = Color.BLUE;
	
	// File selection panel constants
	
	public static Color FILE_SELECTION_PANEL_COLOR = Color.WHITE;
	public static int FILE_SELECTION_PANEL_WIDTH = 380;
	public static int FILE_SELECTION_PANEL_LENGTH = 480;
	
	public static Color FILE_SELECTION_PANEL_CONTAINER_1_COLOR = Color.WHITE;
	
	public static Color FILE_SELECTION_PANEL_CONTAINER_2_COLOR = Color.WHITE;
	
	public static String FILE_SELECTION_PANEL_BUTTON_1_TEXT = "Clear all";
	public static String FILE_SELECTION_PANEL_BUTTON_2_TEXT = "Generate";
	
	public static String FILE_SELECTION_PANEL_OUTPUT_OK = "The output file gitAdderOutput.txt has been successfully generated!";
	public static String FILE_SELECTION_PANEL_OUTPUT_KO = "The generation of output file gitAdderOutput.txt failed";
	
	// File element constants
	
	public static int FILE_ELEMENT_WIDTH = 335;
	public static int FILE_ELEMENT_LENGTH = 35;
	
	public static int FILE_ELEMENT_LABEL_WIDTH = 275;
	public static int FILE_ELEMENT_LABEL_LENGTH = 30;
	
	public static int FILE_ELEMENT_BUTTON_WIDTH = 25;
	public static int FILE_ELEMENT_BUTTON_LENGTH = 25;
	
	// Status bar panel constants
	
	public static Color STATUS_BAR_PANEL_COLOR = Color.LIGHT_GRAY;
	public static int STATUS_BAR_PANEL_WIDTH = 800;
	public static int STATUS_BAR_PANEL_LENGTH = 50;
	
	public static Color STATUS_BAR_PANEL_CONTAINER_COLOR =  Color.WHITE;
	
	public static int STATUS_BAR_PANEL_MESSAGE_WIDTH = 600;
	public static int STATUS_BAR_PANEL_MESSAGE_LENGTH = 30;
	
	public static boolean SUCCESS = true;
	public static boolean FAILURE = false;
	
	public static String STATUS_BAR_PANEL_MESSAGE_SUCCESS_ROOT = "Root folder has been successfully selected!";
	public static String STATUS_BAR_PANEL_MESSAGE_FAILURE_ROOT = "ERROR: Selected file must exist and it has to be a folder";
	
	public static String STATUS_BAR_PANEL_MESSAGE_SELECTED = " has been successfully added to selected files";
	public static String STATUS_BAR_PANEL_MESSAGE_REMOVED = " has been successfully removed from selected files";
	public static String STATUS_BAR_PANEL_MESSAGE_ALL_REMOVED = "All files have been successfully removed from selected files";
	
	// Others
	
	public static Font APPLICATION_DEFAULT_FONT = new Font("Sans Serif", Font.BOLD, 12);
	public static Font APPLICATION_STATUS_FONT = new Font("Sans Serif", Font.BOLD, 12);
	
	public static Color APPLICATION_SUCCESS_COLOR = new Color(0,178,0);
	public static Color APPLICATION_FAILURE_COLOR = new Color(100,0,0);
	
}
