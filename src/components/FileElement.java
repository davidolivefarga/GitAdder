package components;

import gui.GUIConstants;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class FileElement extends JPanel {
	
	////////////////
	// ATTRIBUTES //
	////////////////
	
	private FileSelectionPanel parentPanel;
	
	private JLabel fileLabel;
	private JButton removeButton;
	
	private Image buttonImage;
	private Image buttonImageSelected;
	
	private String fileName;
	private String filePath;
	
	/////////////////
	// CONSTRUCTOR //
	/////////////////
	
	public FileElement(String name, String path, FileSelectionPanel parent) {
		
		super();
		
		fileName = name;
		filePath = path;
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
		
		fileLabel = new JLabel(fileName);
		
		try {
			buttonImage = ImageIO.read(getClass().getResource("/images/removeIcon.png"));
			buttonImageSelected = ImageIO.read(getClass().getResource("/images/removeIconSelected.png"));
			removeButton = new JButton(new ImageIcon(buttonImage));
		} catch (IOException ex) {
			System.out.println("Button images not found");
			removeButton = new JButton();
		}
		
		
	}
	
	private void configurateComponents() {
		
		this.setPreferredSize(new Dimension(GUIConstants.FILE_ELEMENT_WIDTH, GUIConstants.FILE_ELEMENT_LENGTH));
		this.setMaximumSize(new Dimension(GUIConstants.FILE_ELEMENT_WIDTH, GUIConstants.FILE_ELEMENT_LENGTH));
		this.setBorder(BorderFactory.createEtchedBorder());
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		fileLabel.setPreferredSize(new Dimension(GUIConstants.FILE_ELEMENT_LABEL_WIDTH, GUIConstants.FILE_ELEMENT_LABEL_LENGTH));
		
		removeButton.setMinimumSize(new Dimension(GUIConstants.FILE_ELEMENT_BUTTON_WIDTH, GUIConstants.FILE_ELEMENT_BUTTON_LENGTH));
		removeButton.setPreferredSize(new Dimension(GUIConstants.FILE_ELEMENT_BUTTON_WIDTH, GUIConstants.FILE_ELEMENT_BUTTON_LENGTH));
		removeButton.setMaximumSize(new Dimension(GUIConstants.FILE_ELEMENT_BUTTON_WIDTH, GUIConstants.FILE_ELEMENT_BUTTON_LENGTH));
		removeButton.setOpaque(false);
		removeButton.setContentAreaFilled(false);
		removeButton.setBorderPainted(false);
		removeButton.setFocusPainted(false);
		
	}
	
	private void addListeners() {
		
		removeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				parentPanel.removeElement(fileName, filePath);
			}
		});
		
		removeButton.addMouseListener(new MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	removeButton.setIcon(new ImageIcon(buttonImageSelected));
		    }
		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	removeButton.setIcon(new ImageIcon(buttonImage));
		    }
		});
		
	}
	
	private void buildComponents() {
		
		this.add(Box.createHorizontalGlue());
		this.add(fileLabel);
		this.add(Box.createHorizontalGlue());
		this.add(removeButton);
		this.add(Box.createHorizontalGlue());
		
	}
	
	/////////////////////////
	// SETTERS AND GETTERS //
	/////////////////////////
	
	public String getFileName() {
		return fileName;
	}
	
	public String getFilePath() {
		return filePath;
	}
	
	public FileSelectionPanel getParentPanel() {
		return parentPanel;
	}
	
}
