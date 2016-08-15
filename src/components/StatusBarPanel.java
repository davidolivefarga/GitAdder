package components;

import gui.ApplicationGUI;
import gui.GUIConstants;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class StatusBarPanel extends JPanel {
	
	////////////////
	// ATTRIBUTES //
	////////////////
	
	private ApplicationGUI parentPanel;
	
	private JPanel containerPanel;
	private JLabel statusMessage;

	/////////////////
	// CONSTRUCTOR //
	/////////////////

	public StatusBarPanel(ApplicationGUI parent) {

		super();
		
		parentPanel = parent;

		initializeComponents();
		configurateComponents();
		buildComponents();

	}

	/////////////
	// METHODS //
	/////////////
	
	private void initializeComponents() {
		
		containerPanel = new JPanel();
		statusMessage = new JLabel();
		
	}

	private void configurateComponents() {
		
		this.setBackground(GUIConstants.STATUS_BAR_PANEL_COLOR);
		this.setPreferredSize(new Dimension(GUIConstants.STATUS_BAR_PANEL_WIDTH, GUIConstants.STATUS_BAR_PANEL_LENGTH));
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		containerPanel.setBackground(GUIConstants.STATUS_BAR_PANEL_CONTAINER_COLOR);
		
		statusMessage.setPreferredSize(new Dimension(GUIConstants.STATUS_BAR_PANEL_MESSAGE_WIDTH, GUIConstants.STATUS_BAR_PANEL_MESSAGE_LENGTH));
		statusMessage.setHorizontalAlignment(SwingConstants.CENTER);
		statusMessage.setVerticalAlignment(SwingConstants.CENTER);
		statusMessage.setFont(GUIConstants.APPLICATION_STATUS_FONT);

	}
	
	private void buildComponents() {
		
		containerPanel.add(statusMessage);
		
		this.add(containerPanel);
		
	}
	
	public void updateStatus (boolean success, String message) {
		
		if (success) statusMessage.setForeground(GUIConstants.APPLICATION_SUCCESS_COLOR);
		else statusMessage.setForeground(GUIConstants.APPLICATION_FAILURE_COLOR);
		statusMessage.setText(message);
		
	}

	/////////////////////////
	// SETTERS AND GETTERS //
	/////////////////////////
	
	public ApplicationGUI getParentPanel() {
		return parentPanel;
	}
	
	public JLabel getStatusMessage() {
		return statusMessage;
	}

}
