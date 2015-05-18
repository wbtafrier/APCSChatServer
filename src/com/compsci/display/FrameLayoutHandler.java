package com.compsci.display;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.DefaultCaret;
import javax.swing.text.StyledDocument;

import com.compsci.util.CommandAction;

public class FrameLayoutHandler {
	
	private static ServerFrame serverFrame;
	
	static JPanel mainPanel = new JPanel();
	
	public static JMenuBar menuBar = new JMenuBar();
	public static JMenu fileMenu = new JMenu("File");
	public static JTextPane outPane = new JTextPane();
	public static StyledDocument doc = outPane.getStyledDocument();
	public static JScrollPane scrollPane = new JScrollPane(outPane);
	public static JTextField inField = new JTextField();
	
	public static void setupFrame(ServerFrame frame) {
		if (frame != null) {		
			serverFrame = frame;
			
			menuBar.add(fileMenu);
			serverFrame.setJMenuBar(menuBar);
			addGridBag();
			serverFrame.add(mainPanel);
			serverFrame.addWindowListener(new WindowListener());
			
			outPane.addFocusListener(new PaneFocusListener());
			outPane.setDragEnabled(true);
			outPane.setEditable(false);
			DefaultCaret caret = (DefaultCaret)outPane.getCaret();
			caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
			outPane.setBackground(new Color(200, 200, 200));
			
			String fontName = "Consolas";
			if (!DisplayInfo.isFontInstalled(fontName)) {
				fontName = "Courier New";
			}
			
			outPane.setFont(new Font(fontName, Font.PLAIN, 12));
			
			inField.addActionListener(new CommandAction());
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
}