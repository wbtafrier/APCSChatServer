package com.compsci.gui;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.text.DefaultCaret;
import javax.swing.text.StyledDocument;

import com.compsci.gui.listener.GuiListener;
import com.compsci.gui.listener.InputListener;
import com.compsci.gui.listener.MenuListener;
import com.compsci.gui.listener.PaneFocusListener;
import com.compsci.util.DisplayInfo;

/**
 * Provides setup information for the SloverseFrame and holds a handle to the frame.
 */
public class FrameHandle {
	
	private static ServerFrame serverFrame;
	
	private static JPanel mainPanel = new JPanel();
	
	private static JMenuBar menuBar = new JMenuBar();
	private static JMenu fileMenu = new JMenu("File");
	private static JMenuItem customizeItem = new JMenuItem("Customize...", KeyEvent.VK_P);
	private static JMenuItem exitItem = new JMenuItem("Exit");
	private static JTextPane outPane = new JTextPane();
	private static StyledDocument doc = outPane.getStyledDocument();
	private static JScrollPane scrollPane = new JScrollPane(outPane);
	private static JTextField inField = new JTextField();
	private static CustomizeDialog customizeDialog;
	
	private static MenuListener menuListener = new MenuListener();
	private static InputListener inputListener = new InputListener();
	
	public static void setupFrame(ServerFrame frame) {
		if (frame != null) {		
			serverFrame = frame;
			
			customizeItem.addActionListener(menuListener);
			customizeItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.SHIFT_DOWN_MASK + KeyEvent.CTRL_DOWN_MASK));
			customizeItem.setToolTipText("Modify the customize of the server console window.");
			fileMenu.add(customizeItem);
			exitItem.addActionListener(menuListener);
			exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, KeyEvent.ALT_MASK));
			exitItem.setToolTipText("Stops the server and exits the server window.");
			fileMenu.add(exitItem);
			fileMenu.setToolTipText("File functions enable the user to interact with the server window.");
			menuBar.add(fileMenu);
			serverFrame.setJMenuBar(menuBar);
			addGridBag();
			serverFrame.add(mainPanel);
			serverFrame.addWindowListener(new GuiListener());
			
			outPane.addFocusListener(new PaneFocusListener());
			outPane.setDragEnabled(true);
			outPane.setEditable(false);
			DefaultCaret caret = (DefaultCaret)outPane.getCaret();
			caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
			GuiOperations.randomizeColor();
			
			String fontName = "Consolas";
			if (!DisplayInfo.isFontInstalled(fontName)) {
				fontName = "Courier New";
			}
			
			outPane.setFont(new Font(fontName, Font.PLAIN, 16));
			inField.setFont(new Font(fontName, Font.PLAIN, 16));
			
			inField.addActionListener(inputListener);
			inField.addKeyListener(inputListener);
		}
	}
	
	private static void addGridBag() {
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		
		mainPanel.setLayout(gbl);
		
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(20, 20, 20, 20);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = gbc.weighty = 1.0;
		gbc.anchor = GridBagConstraints.CENTER;
		
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		mainPanel.add(scrollPane, gbc);
		
		gbc.insets = new Insets(0, 20, 20, 20);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = gbc.weighty = 0.0;
		gbc.anchor = GridBagConstraints.PAGE_END;
		
		inField.setBorder(BorderFactory.createCompoundBorder(inField.getBorder(), BorderFactory.createEmptyBorder(5, 2, 5, 2)));
		mainPanel.add(inField, gbc);
	}
	
	public static ServerFrame getFrame() {
		return serverFrame;
	}
	
	public static JPanel getMainPanel() {
		return mainPanel;
	}
	
	public static JMenuBar getMenuBar() {
		return menuBar;
	}
	
	public static JMenu getFileMenu() {
		return fileMenu;
	}
	
	public static JMenuItem getCustomizeItem() {
		return customizeItem;
	}
	
	public static JMenuItem getExitItem() {
		return exitItem;
	}
	
	public static JTextPane getOutputPane() {
		return outPane;
	}
	
	public static StyledDocument getDocument() {
		return doc;
	}
	
	public static JScrollPane getScrollPane() {
		return scrollPane;
	}
	
	public static JTextField getInputField() {
		return inField;
	}
	
	public static CustomizeDialog getCustomizeDialog() {
		return customizeDialog;
	}
	
	public static void setCustomizeDialog(CustomizeDialog prop) {
		customizeDialog = prop;
	}
}
