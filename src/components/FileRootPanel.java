package components;

import gui.ApplicationGUI;
import gui.GUIConstants;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class FileRootPanel extends JPanel {

	////////////////
	// ATTRIBUTES //
	////////////////
	
	private ApplicationGUI parentPanel;
	
	private JPanel containerPanel;
	private JButton selectorButton;
	private JTextField textField;

	/////////////////
	// CONSTRUCTOR //
	/////////////////

	public FileRootPanel(ApplicationGUI parent) {

		super();
		
		parentPanel = parent;
		
		initializeComponents();
		configurateComponents();
		addListeners();
		buildComponents();

	}

	/////////////
	// METHODS //
	/////////////
	
	private void initializeComponents() {
		
		containerPanel = new JPanel();
		selectorButton = new JButton(GUIConstants.FILE_ROOT_PANEL_BUTTON_TEXT);
		textField = new JTextField();
		
	}

	private void configurateComponents() {

		this.setBackground(GUIConstants.FILE_ROOT_PANEL_COLOR);
		this.setPreferredSize(new Dimension(GUIConstants.FILE_ROOT_PANEL_WIDTH, GUIConstants.FILE_ROOT_PANEL_LENGTH));
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		containerPanel.setBackground(GUIConstants.FILE_ROOT_PANEL_CONTAINER_COLOR);
		
		selectorButton.setPreferredSize(new Dimension(GUIConstants.FILE_ROOT_PANEL_BUTTON_WIDTH, GUIConstants.FILE_ROOT_PANEL_BUTTON_LENGTH));
		selectorButton.setFont(GUIConstants.APPLICATION_DEFAULT_FONT);
		selectorButton.setFocusPainted(false);
		
		textField.setPreferredSize(new Dimension(GUIConstants.FILE_ROOT_PANEL_TEXTFIELD_WIDTH, GUIConstants.FILE_ROOT_PANEL_TEXTFIELD_LENGTH));
		textField.setFont(GUIConstants.APPLICATION_DEFAULT_FONT);
		
	}
	
	private void buildComponents() {
		
		containerPanel.add(selectorButton);
		containerPanel.add(textField);
		
		this.add(Box.createVerticalGlue());
		this.add(containerPanel);
		this.add(Box.createVerticalGlue());
		
	}
	
	public void addListeners() {
		
		selectorButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int result = fileChooser.showOpenDialog(null);
				if (result == JFileChooser.APPROVE_OPTION) {
				    File selectedFile = fileChooser.getSelectedFile();
				    if (selectedFile.isDirectory()) {
				    	 textField.setText(selectedFile.getAbsolutePath());
				    	 parentPanel.getFileSelectionPanel().removeAllElements();
				    	 parentPanel.getFileTreePanel().generateTree();
				    	 parentPanel.getStatusBarPanel().updateStatus(GUIConstants.SUCCESS, GUIConstants.STATUS_BAR_PANEL_MESSAGE_SUCCESS_ROOT);
				    }
				    else {
				    	 parentPanel.getStatusBarPanel().updateStatus(GUIConstants.FAILURE, GUIConstants.STATUS_BAR_PANEL_MESSAGE_FAILURE_ROOT);
				    }
				}
				
			}
		});
		
	}

	/////////////////////////
	// SETTERS AND GETTERS //
	/////////////////////////
	
	public ApplicationGUI getParentPanel() {
		return parentPanel;
	}

	public JPanel getContainerPanel() {
		return containerPanel;
	}

	public JButton getSelectorButton() {
		return selectorButton;
	}

	public JTextField getTextField() {
		return textField;
	}

}
