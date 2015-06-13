package com.compsci.gui;

import java.awt.Component;
import java.awt.Font;

import javax.swing.JList;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

public class FontComboRenderer<E> extends BasicComboBoxRenderer {

	private static final long serialVersionUID = -7894186927041296041L;

	@Override
	@SuppressWarnings("rawtypes")
	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
		super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		Object fontObj = value;
		String fontFamilyName = (String)fontObj;
		this.setFont(new Font(fontFamilyName, Font.PLAIN, 16));
		return this;
	}
}
