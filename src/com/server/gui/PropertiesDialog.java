package com.server.gui;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.server.gui.listener.PropertiesListener;

public class PropertiesDialog extends JDialog {

	private static final long serialVersionUID = 4223266559039204055L;

	public JPanel propertiesPanel = new JPanel();
	public JLabel colorLabel = new JLabel("Console Color:");
	private String[] availableColors = new String[] {
		"Gray", "Red", "Purple"
	};
	public JComboBox<String> colorDropdown = new JComboBox<String>(availableColors);
	public JButton selectColor = new JButton("Select Color...");
	public JButton doneButton = new JButton("Done");
	
	public PropertiesListener propertiesListener = new PropertiesListener();
	
	public PropertiesDialog() {
		super(FrameHandle.getFrame(), "Sloverse Server - Properties");
		propertiesPanel.setLayout(new GridLayout(3, 0));
		propertiesPanel.add(colorLabel);
		colorDropdown.addActionListener(propertiesListener);
//		propertiesPanel.add(colorDropdown);
		selectColor.addActionListener(propertiesListener);
		selectColor.setToolTipText("Select the background color of your server console window.");
		propertiesPanel.add(selectColor);
		doneButton.addActionListener(propertiesListener);
		propertiesPanel.add(doneButton);		
		this.add(propertiesPanel);
		this.pack();
		this.setResizable(false);
		this.setLocationRelativeTo(FrameHandle.getFrame());
		this.addWindowListener(propertiesListener);
		this.setVisible(true);
	}
}
