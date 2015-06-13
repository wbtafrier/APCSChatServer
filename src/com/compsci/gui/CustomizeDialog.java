package com.compsci.gui;

import java.awt.Font;
import java.awt.GraphicsEnvironment;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.compsci.gui.listener.CustomizeListener;
import com.compsci.util.DisplayInfo;

public class CustomizeDialog extends JDialog {

	private static final long serialVersionUID = 4223266559039204055L;

	private static GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	private static String[] fontFamilyNames = ge.getAvailableFontFamilyNames();
	
	public JPanel customizePanel = new JPanel();
	public JPanel chatColorPanel = new JPanel();
	public JPanel textColorPanel = new JPanel();
	public JPanel fontPanel = new JPanel();
	public JPanel donePanel = new JPanel();
	public JLabel chatColorLabel = new JLabel("Console Color: ");
	public JButton selectChatColor = new JButton("Select Console Color...");
	public JLabel textColorLabel = new JLabel("Text Color: ");
	public JButton selectTextColor = new JButton("Select Text Color...");
	public JLabel fontLabel = new JLabel ("Font: ");
	public JComboBox<String> fontDropdown = new JComboBox<String>(fontFamilyNames);
	public JButton doneButton = new JButton("Done");
	
	public CustomizeListener customizeListener = new CustomizeListener();
	@SuppressWarnings("rawtypes")
	private FontComboRenderer comboRenderer = new FontComboRenderer();
	
	@SuppressWarnings("unchecked")
	public CustomizeDialog() {
		super(FrameHandle.getFrame(), "Sloverse Server - Customize");
		customizePanel.setLayout(new BoxLayout(customizePanel, BoxLayout.Y_AXIS));
		
		chatColorPanel.setLayout(new BoxLayout(chatColorPanel, BoxLayout.X_AXIS));
		chatColorPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		chatColorLabel.setBorder(new EmptyBorder(0, 0, 0, 5));
		chatColorPanel.add(chatColorLabel);
		selectChatColor.addActionListener(customizeListener);
		selectChatColor.setToolTipText("Select the background color of your server console window.");
		chatColorPanel.add(selectChatColor);
		
		textColorPanel.setLayout(new BoxLayout(textColorPanel, BoxLayout.X_AXIS));
		textColorPanel.setBorder(new EmptyBorder(0, 10, 10, 10));
		textColorLabel.setBorder(new EmptyBorder(0, 0, 0, 5));
		textColorPanel.add(textColorLabel);
		selectTextColor.addActionListener(customizeListener);
		selectTextColor.setToolTipText("Select the color of the text in your server console window.");
		textColorPanel.add(selectTextColor);
		
		fontPanel.setLayout(new BoxLayout(fontPanel, BoxLayout.X_AXIS));
		fontPanel.setBorder(new EmptyBorder(0, 10, 10, 10));
		fontLabel.setBorder(new EmptyBorder(0, 0, 0, 5));
		fontPanel.add(fontLabel);
		fontDropdown.setSelectedItem(FrameHandle.getOutputPane().getFont().getName());
		fontDropdown.setFont(new Font(fontDropdown.getSelectedItem().toString(), Font.PLAIN, 16));
		fontDropdown.setRenderer(comboRenderer);
		fontDropdown.addItemListener(customizeListener);
		fontDropdown.setPrototypeDisplayValue(DisplayInfo.getLongestFont());
		fontDropdown.setBorder(new EmptyBorder(0, 0, 0, 10));
		fontPanel.add(fontDropdown);
		
		doneButton.addActionListener(customizeListener);
		donePanel.add(doneButton);
		
		customizePanel.add(chatColorPanel);
		customizePanel.add(textColorPanel);
		customizePanel.add(fontPanel);
		customizePanel.add(donePanel);
		this.add(customizePanel);
		this.pack();
		this.setResizable(false);
		this.setLocationRelativeTo(FrameHandle.getFrame());
		this.addWindowListener(customizeListener);
		this.setVisible(true);
	}
}
