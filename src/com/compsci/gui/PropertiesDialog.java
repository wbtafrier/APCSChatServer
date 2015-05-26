package com.compsci.gui;

import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.compsci.gui.listener.PropertiesListener;

public class PropertiesDialog extends JDialog {

	private static final long serialVersionUID = 4223266559039204055L;

	public JPanel propertiesPanel = new JPanel();
	public JPanel colorPanel = new JPanel();
	public JPanel donePanel = new JPanel();
	public JLabel colorLabel = new JLabel("Console Color: ");
	public JButton selectColor = new JButton("Select Color...");
	public JButton doneButton = new JButton("Done");
	
	public PropertiesListener propertiesListener = new PropertiesListener();
	
	public PropertiesDialog() {
		super(FrameHandle.getFrame(), "Sloverse Server - Properties");
		propertiesPanel.setLayout(new GridLayout(2, 0));
		colorPanel.setLayout(new BoxLayout(colorPanel, BoxLayout.X_AXIS));
		colorPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		colorLabel.setBorder(new EmptyBorder(0, 0, 0, 5));
		colorPanel.add(colorLabel);
		selectColor.addActionListener(propertiesListener);
		selectColor.setToolTipText("Select the background color of your server console window.");
		colorPanel.add(selectColor);
		doneButton.addActionListener(propertiesListener);
		donePanel.add(doneButton);
		propertiesPanel.add(colorPanel);
		propertiesPanel.add(donePanel);
		this.add(propertiesPanel);
		this.pack();
		this.setResizable(false);
		this.setLocationRelativeTo(FrameHandle.getFrame());
		this.addWindowListener(propertiesListener);
		this.setVisible(true);
	}
}
