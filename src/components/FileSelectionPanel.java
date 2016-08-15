package components;

import gui.ApplicationGUI;
import gui.GUIConstants;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.tree.DefaultMutableTreeNode;

@SuppressWarnings("serial")
public class FileSelectionPanel extends JPanel {

	////////////////
	// ATTRIBUTES //
	////////////////

	private ApplicationGUI parentPanel;

	private JPanel fileContainer;
	private JPanel buttonContainer;
	private JScrollPane scrollPane;
	private JButton clearButton;
	private JButton executeButton;

	private ArrayList<FileElement> fileList;

	/////////////////
	// CONSTRUCTOR //
	/////////////////

	public FileSelectionPanel(ApplicationGUI parent) {

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

		fileContainer = new JPanel();
		buttonContainer = new JPanel();

		scrollPane = new JScrollPane(fileContainer);

		clearButton = new JButton(GUIConstants.FILE_SELECTION_PANEL_BUTTON_1_TEXT);
		executeButton = new JButton(GUIConstants.FILE_SELECTION_PANEL_BUTTON_2_TEXT);

		fileList = new ArrayList<FileElement>();

	}

	private void configurateComponents() {

		this.setBackground(GUIConstants.FILE_SELECTION_PANEL_COLOR);
		this.setPreferredSize(new Dimension(GUIConstants.FILE_SELECTION_PANEL_WIDTH, GUIConstants.FILE_SELECTION_PANEL_LENGTH));
		this.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 30));
		this.setLayout(new BorderLayout());

		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); 

		fileContainer.setBackground(GUIConstants.FILE_SELECTION_PANEL_CONTAINER_1_COLOR);
		fileContainer.setLayout(new BoxLayout(fileContainer, BoxLayout.Y_AXIS));

		buttonContainer.setBackground(GUIConstants.FILE_SELECTION_PANEL_CONTAINER_2_COLOR);
		buttonContainer.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

	}
	
	private void addListeners() {
		
		clearButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				removeAllSelectedElements();
			}
		});
		
		executeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
	}

	private void buildComponents() {

		buttonContainer.add(clearButton);
		buttonContainer.add(executeButton);

		this.add(scrollPane, BorderLayout.CENTER);
		this.add(buttonContainer, BorderLayout.SOUTH);

	}

	public void addElement(DefaultMutableTreeNode node) {

		FileTree.FileInfo fileInfo = (FileTree.FileInfo)node.getUserObject();
		String fileName = fileInfo.toString();
		String filePath = fileInfo.getFilePath();
		
		System.out.println("Adding file with name " + fileName + " and path " + filePath);
		
		FileElement fileElement = new FileElement(node, this);
		fileList.add(fileElement);
		fileInfo.setSelected(true);
		parentPanel.getStatusBarPanel().updateStatus(GUIConstants.SUCCESS, fileName + GUIConstants.STATUS_BAR_PANEL_MESSAGE_SELECTED);
		regenerateSelectedFiles();

	}

	public void removeElement(DefaultMutableTreeNode node) {
		
		FileTree.FileInfo fileInfo = (FileTree.FileInfo)node.getUserObject();
		String fileName = fileInfo.toString();
		String filePath = fileInfo.getFilePath();

		System.out.println("Removing file with name " + fileName + " and path " + filePath);
		
		Iterator<FileElement> it = fileList.iterator();
		FileElement fileElement;
		while (it.hasNext()) {
			fileElement = it.next();
		    if (fileElement.getFileName().equals(fileName) && fileElement.getFilePath().equals(filePath)) {
		        it.remove();
		        break;
		    }
		}
		fileInfo.setSelected(false);
		parentPanel.getStatusBarPanel().updateStatus(GUIConstants.SUCCESS, fileName + GUIConstants.STATUS_BAR_PANEL_MESSAGE_REMOVED);
		parentPanel.getFileTreePanel().getFileTree().refreshFileTree();
		regenerateSelectedFiles();

	}
	
	public void removeAllElements() {
		
		fileContainer.removeAll();
		fileContainer.revalidate();
		
		fileList.clear();
		
		parentPanel.getFileTreePanel().generateTree();
		
	}
	
	public void removeAllSelectedElements() {
		
		System.out.println("Removing all selected files");
		
		Iterator<FileElement> it = fileList.iterator();
		FileElement fileElement;
		DefaultMutableTreeNode node;
		FileTree.FileInfo fileInfo;
		while (it.hasNext()) {
			fileElement = it.next();
			node = fileElement.getNode();
			fileInfo = (FileTree.FileInfo)node.getUserObject();
			fileInfo.setSelected(false);
			it.remove();
		}
		
		parentPanel.getStatusBarPanel().updateStatus(GUIConstants.SUCCESS, GUIConstants.STATUS_BAR_PANEL_MESSAGE_ALL_REMOVED);
		regenerateSelectedFiles();
		parentPanel.getFileTreePanel().getFileTree().refreshFileTree();
		
	}

	private void regenerateSelectedFiles() {

		fileContainer.removeAll();

		Iterator<FileElement> it = fileList.iterator();
		FileElement fileElement;
		while (it.hasNext()) {
			fileElement = it.next();
			fileContainer.add(fileElement);
			fileElement.scrollRectToVisible(fileElement.getBounds());
		}
		fileContainer.revalidate();
		fileContainer.repaint();

	}

	/////////////////////////
	// SETTERS AND GETTERS //
	/////////////////////////

	public ApplicationGUI getParentPanel() {
		return parentPanel;
	}

	public JPanel getContainerPanel() {
		return fileContainer;
	}

	public JScrollPane getScrollPane() {
		return scrollPane;
	}

}
