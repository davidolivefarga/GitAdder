package components;

import gui.ApplicationGUI;
import gui.GUIConstants;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

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
				removeAll();
			}
		});
		
		executeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
	}
	
	public void removeAll() {
		
		fileContainer.removeAll();
		fileContainer.revalidate();
		
		fileList.clear();
		
		parentPanel.getFileTreePanel().generateTree();
		
	}

	private void buildComponents() {

		buttonContainer.add(clearButton);
		buttonContainer.add(executeButton);

		this.add(scrollPane, BorderLayout.CENTER);
		this.add(buttonContainer, BorderLayout.SOUTH);

	}

	public void addElement(String fileName, String filePath) {

		FileElement fileElement = new FileElement(fileName, filePath);
		fileList.add(fileElement);
		regenerateSelectedFiles();

	}

	public void removeElement(String fileName) {

		int l = fileList.size();
		int elementPosition = -1;
		for (int i=0; i<l; ++i) {
			if (fileList.get(i).getFileLabel().getText().equals(fileName)) {
				elementPosition = i;
				break;
			}
		}
		if (elementPosition != -1) fileList.remove(elementPosition);
		regenerateSelectedFiles();

	}

	private void regenerateSelectedFiles() {

		fileContainer.removeAll();

		int l = fileList.size();
		for (int i=0; i<l; ++i) {
			final FileElement fileElement = fileList.get(i);
			fileContainer.add(fileElement);
			fileContainer.revalidate();
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					fileElement.scrollRectToVisible(fileElement.getBounds());
				}
			});
		}

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
