package com.compsci.display;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.StyledDocument;

import com.compsci.util.GraphicsHelper;

public class DisplayHandler {
	
	private static ServerFrame serverFrame;
	
	static JPanel mainPanel = new JPanel();
	
	static JMenuBar menuBar = new JMenuBar();
	static JTextPane outPane = new JTextPane();
	static StyledDocument doc = outPane.getStyledDocument();
	static JScrollPane scrollPane = new JScrollPane(outPane);
	static JTextField inField = new JTextField();
	
	public static void setupFrame(ServerFrame frame) {
		if (frame != null) {		
			serverFrame = frame;
			serverFrame.setJMenuBar(menuBar);
			addGridBag();
			serverFrame.add(mainPanel);
			outPane.setDragEnabled(true);
			outPane.setEditable(false);
			outPane.setBackground(new Color(200, 200, 200));
			
			String fontName = "Consolas";
			if (!GraphicsHelper.isFontInstalled(fontName)) {
				fontName = "Courier New";
			}
			
			outPane.setFont(new Font(fontName, Font.PLAIN, 12));
			
			inField.addActionListener(new EnterAction());
		}
	}
	
	private static void addGridBag() {
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		
		mainPanel.setLayout(gbl);
		
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(20, 20, 30, 20);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = gbc.weighty = 1.0;
		gbc.anchor = GridBagConstraints.CENTER;
		
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		mainPanel.add(scrollPane, gbc);
		
		gbc.insets = new Insets(0, 20, 40, 20);
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
