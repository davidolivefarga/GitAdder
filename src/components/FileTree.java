package components;

import gui.GUIConstants;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.io.File;
import java.util.Collections;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

@SuppressWarnings("serial")
public class FileTree extends JPanel {
	
	////////////////
	// ATTRIBUTES //
	////////////////
	
	private FileTreePanel parentPanel;
	private File rootFile;
	
	private JTree tree;
	private JScrollPane scrollPane;
	
	/////////////////
	// CONSTRUCTOR //
	/////////////////
	
	public FileTree(File dir, FileTreePanel parent) {
		
		parentPanel = parent;
		rootFile = dir;
		
		initializeComponents();
		configureComponents();
		addListeners();
		buildComponents();

	}
	
	/////////////
	// METHODS //
	/////////////
	
	private void initializeComponents() {
		
		tree = new JTree(addNodes(null, rootFile));
		scrollPane = new JScrollPane();
		
	}
	
	private void configureComponents() {
		
		this.setLayout(new BorderLayout());
		
		tree.setCellRenderer(new MyTreeCellRenderer());
		
	}
	
	private void addListeners() {
		
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			
			public void valueChanged(TreeSelectionEvent e) {
				
				DefaultMutableTreeNode node = (DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
				if (node!=null) {
					
					FileInfo fileInfo = (FileInfo)node.getUserObject();
					String nodeName = fileInfo.toString();
					String filePath = fileInfo.getFilePath();
					
					File f = new File(filePath);
					if (!f.isDirectory()) {
						System.out.println("File " + nodeName + " with path " + filePath + " has been clicked");
						if (!fileInfo.isSelected()) {
							parentPanel.getParentPanel().getFileSelectionPanel().addElement(nodeName, filePath);
							parentPanel.getParentPanel().getStatusBarPanel().updateStatus(GUIConstants.SUCCESS, nodeName + GUIConstants.STATUS_BAR_PANEL_MESSAGE_SELECTED);
							fileInfo.setSelected(true);
						} else {
							parentPanel.getParentPanel().getFileSelectionPanel().removeElement(nodeName, filePath);
							parentPanel.getParentPanel().getStatusBarPanel().updateStatus(GUIConstants.SUCCESS, nodeName + GUIConstants.STATUS_BAR_PANEL_MESSAGE_REMOVED);
							fileInfo.setSelected(false);
						}
					}
					
				}
				
			}
			
		});
		
	}
	
	private void buildComponents() {
		
		scrollPane.getViewport().add(tree);
		this.add(BorderLayout.CENTER, scrollPane);
		
	}

	private DefaultMutableTreeNode addNodes(DefaultMutableTreeNode curTop, File dir) {
		
		// Creates a new node associated to the received file and it is added as a children of the top node (if it exists)
		
		String curPath = dir.getPath();
		String fileName = dir.getName();
		
		DefaultMutableTreeNode curDir = new DefaultMutableTreeNode(new FileInfo(fileName, curPath));
		
		if (curTop != null) curTop.add(curDir);
		
		// Creates a sorted list of the files and directories contained on the received file
		
		Vector<String> ol = new Vector<String>();
		String[] tmp = dir.list();
		for (int i = 0; i < tmp.length; i++) ol.addElement(tmp[i]);
		Collections.sort(ol, String.CASE_INSENSITIVE_ORDER);
		
		// For directories (unless hidden), calls addNodes method; for files, adds a children node to the current node
		
		File f;
		Vector<String> files = new Vector<String>();
		
		for (int i = 0; i < ol.size(); i++) {
			
			String thisObject = (String) ol.elementAt(i);
			String newPath;
			
			if (curPath.equals(".")) newPath = thisObject;
			else newPath = curPath + File.separator + thisObject;
			
			f = new File(newPath);
			if (f.isDirectory() && !f.isHidden()) addNodes(curDir, f);
			else files.addElement(thisObject);
			
		}
		
		for (int fnum = 0; fnum < files.size(); fnum++) {
			
			String nodeName = files.elementAt(fnum);
			String filePath = curPath + File.separator + nodeName;
			DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(new FileInfo(nodeName, filePath));
			curDir.add(newNode);
			
		}
		
		// Returns the node structure
		
		return curDir;
		
	}
	
	/////////////////////////
	// SETTERS AND GETTERS //
	/////////////////////////
	
	public FileTreePanel getParentPanel() {
		return parentPanel;
	}
	
	///////////////////
	// INNER CLASSES //
	///////////////////
	
	private class MyTreeCellRenderer extends DefaultTreeCellRenderer {

	    @Override
	    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean exp, boolean leaf, int row, boolean hasFocus) {
	        
	    	super.getTreeCellRendererComponent(tree, value, sel, exp, leaf, row, hasFocus);

	    	DefaultMutableTreeNode node = (DefaultMutableTreeNode)value;
	        FileInfo nodeInfo = (FileInfo)node.getUserObject();
	        File f = new File(nodeInfo.getFilePath());
	        if (!f.isDirectory() && nodeInfo.isSelected()) {
	            setForeground(GUIConstants.FILE_TREE_PANEL_SELECTED_F_COLOR);
	        }

	        return this;
	    }
	}
	
	public class FileInfo {
		
        private String fileName;
        private String filePath;
        private boolean selected = false;

        public FileInfo(String fName, String fPath) {
            fileName = fName;
            filePath = fPath;
        }

        public String toString() {
            return fileName;
        }
        
        public String getFilePath() {
        	return filePath;
        }
        
        public boolean isSelected() {
        	return selected;
        }
        
        public void setSelected(boolean s) {
        	selected = s;
        }
		
	}

	/////////////
	// TESTING //
	/////////////
	
	public static void main(String[] av) {

		JFrame frame = new JFrame("FileTree");
		frame.setForeground(Color.black);
		frame.setBackground(Color.lightGray);
		Container cp = frame.getContentPane();

		if (av.length == 0) {
			cp.add(new FileTree(new File("."), null));
		} else {
			cp.setLayout(new BoxLayout(cp, BoxLayout.X_AXIS));
			for (int i = 0; i < av.length; i++)
				cp.add(new FileTree(new File(av[i]), null));
		}

		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

}
