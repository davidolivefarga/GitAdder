package components;

import gui.GUIConstants;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	
	private JLabel fileLabel;
	private JButton removeButton;
	private Image buttonImage;
	
	private String labelText;
	private String filePath;
	
	/////////////////
	// CONSTRUCTOR //
	/////////////////
	
	public FileElement(String fileName, String fullPath) {
		
		super();
		
		labelText = fileName;
		filePath = fullPath;
		
		initializeComponents();
		configurateComponents();
		addListeners();
		buildComponents();
		
	}
	
	/////////////
	// METHODS //
	/////////////
	
	private void initializeComponents() {
		
		fileLabel = new JLabel(labelText);
		
		try {
			buttonImage = ImageIO.read(getClass().getResource("/images/removeIcon.png"));
			removeButton = new JButton(new ImageIcon(buttonImage));
		} catch (IOException ex) {
			System.out.println("Button image not found");
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
				System.out.println("helooooooooooooooooooooooo");
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
	
	public JLabel getFileLabel() {
		return fileLabel;
	}
	
	public String getFilePath() {
		return filePath;
	}
	
}
