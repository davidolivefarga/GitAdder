package components;

import gui.ApplicationGUI;
import gui.GUIConstants;

import java.awt.Dimension;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class FileTreePanel extends JPanel {
	
	////////////////
	// ATTRIBUTES //
	////////////////
	
	private ApplicationGUI parentPanel;
	
	private FileTree fileTree;
	
	/////////////////
	// CONSTRUCTOR //
	/////////////////
	
	public FileTreePanel(ApplicationGUI parent) {
		
		super();
		
		parentPanel = parent;
		
		configurateComponents();
		
	}
	
	/////////////
	// METHODS //
	/////////////
	
	private void configurateComponents() {
		
		this.setBackground(GUIConstants.FILE_TREE_PANEL_COLOR);
		this.setPreferredSize(new Dimension(GUIConstants.FILE_TREE_PANEL_WIDTH, GUIConstants.FILE_TREE_PANEL_LENGTH));
		this.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 0));
		this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		
	}
	
	public void generateTree() {
		
		this.removeAll();
		
		String rootFile = parentPanel.getFileRootPanel().getTextField().getText();
		fileTree = new FileTree(new File(rootFile), this);
		fileTree.setPreferredSize(new Dimension(GUIConstants.FILE_TREE_PANEL_TREE_WIDTH, GUIConstants.FILE_TREE_PANEL_TREE_LENGTH));
		
		addTreeToPanel();
		
	}
	
	private void addTreeToPanel() {
		
		this.add(Box.createHorizontalGlue());
		this.add(fileTree);
		this.add(Box.createHorizontalGlue());
		this.validate();
		this.repaint();
	
	}
	
	/////////////////////////
	// SETTERS AND GETTERS //
	/////////////////////////
	
	public ApplicationGUI getParentPanel() {
		return parentPanel;
	}
	
	public FileTree getFileTree() {
		return fileTree;
	}

}
