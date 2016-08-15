package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import components.FileRootPanel;
import components.FileSelectionPanel;
import components.FileTreePanel;
import components.StatusBarPanel;

@SuppressWarnings("serial")
public class ApplicationGUI extends JFrame {
	
	////////////////
	// ATTRIBUTES //
	////////////////
	
	private JPanel mainPanel;
	private FileRootPanel fileRootPanel;
	private FileTreePanel fileTreePanel;
	private FileSelectionPanel fileSelectionPanel;
	private StatusBarPanel statusBarPanel;
	
	/////////////////
	// CONSTRUCTOR //
	/////////////////
	
	public ApplicationGUI (String title) {
		
		super(title);
		
		initializeComponents();
		configureComponents();
		buildComponents();
		
	}
	
	/////////////
	// METHODS //
	/////////////
	
	private void initializeComponents() {
		
		mainPanel = (JPanel)this.getContentPane();
		
		fileRootPanel = new FileRootPanel(this);
		fileTreePanel = new FileTreePanel(this);
		fileSelectionPanel = new FileSelectionPanel(this);
		statusBarPanel = new StatusBarPanel(this);
		
	}
	
	private void configureComponents() {
		
		this.setBackground(GUIConstants.MAIN_FRAME_COLOR);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setResizable(false);
		
		mainPanel.setBackground(GUIConstants.MAIN_FRAME_COLOR);
		mainPanel.setPreferredSize(new Dimension(GUIConstants.MAIN_FRAME_WIDTH, GUIConstants.MAIN_FRAME_LENGTH));
		mainPanel.setLayout(new BorderLayout());
		
	}
	
	private void buildComponents() {
		
		mainPanel.add(fileRootPanel, BorderLayout.NORTH);
		mainPanel.add(fileTreePanel, BorderLayout.WEST);
		mainPanel.add(fileSelectionPanel, BorderLayout.EAST);
		mainPanel.add(statusBarPanel, BorderLayout.SOUTH);
		
		this.pack();
		this.setLocationRelativeTo(null);
		
	}

	
	/////////////////////////
	// SETTERS AND GETTERS //
	/////////////////////////
	
	public JPanel getMainPanel() {
		return mainPanel;
	}
	
	public FileRootPanel getFileRootPanel() {
		return fileRootPanel;
	}

	public FileTreePanel getFileTreePanel() {
		return fileTreePanel;
	}
	
	public FileSelectionPanel getFileSelectionPanel() {
		return fileSelectionPanel;
	}
	
	public StatusBarPanel getStatusBarPanel() {
		return statusBarPanel;
	}
	
}
