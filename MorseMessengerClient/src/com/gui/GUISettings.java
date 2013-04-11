package com.gui;

import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class GUISettings {
	public static void getSettings() {
		Font font = null;
		String lookAndFeelClassName = null;

		try {
			FileInputStream fis = new FileInputStream(Resources.CONFIG_FILE);
			ObjectInputStream ois = new ObjectInputStream(fis);

			font = (Font) ois.readObject();
			lookAndFeelClassName = (String) ois.readObject();
		} catch (FileNotFoundException e) {
		} catch (ClassNotFoundException e) {
		} catch (IOException e) {
		}

		if (font == null) {
			font = UIManager.getDefaults().getFont("TabbedPane.font");
		}

		if (lookAndFeelClassName == null) {
			lookAndFeelClassName = UIManager
					.getCrossPlatformLookAndFeelClassName();
		}

		try {
			UIManager.setLookAndFeel(lookAndFeelClassName);
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		UIManager.put("Button.font", font);
		UIManager.put("ToggleButton.font", font);
		UIManager.put("RadioButton.font", font);
		UIManager.put("CheckBox.font", font);
		UIManager.put("ColorChooser.font", font);
		UIManager.put("ComboBox.font", font);
		UIManager.put("Label.font", font);
		UIManager.put("List.font", font);
		UIManager.put("MenuBar.font", font);
		UIManager.put("MenuItem.font", font);
		UIManager.put("RadioButtonMenuItem.font", font);
		UIManager.put("CheckBoxMenuItem.font", font);
		UIManager.put("Menu.font", font);
		UIManager.put("PopupMenu.font", font);
		UIManager.put("OptionPane.font", font);
		UIManager.put("Panel.font", font);
		UIManager.put("ProgressBar.font", font);
		UIManager.put("ScrollPane.font", font);
		UIManager.put("Viewport.font", font);
		UIManager.put("TabbedPane.font", font);
		UIManager.put("Table.font", font);
		UIManager.put("TableHeader.font", font);
		UIManager.put("TextField.font", font);
		UIManager.put("PasswordField.font", font);
		UIManager.put("TextArea.font", font);
		UIManager.put("TextPane.font", font);
		UIManager.put("EditorPane.font", font);
		UIManager.put("TitledBorder.font", font);
		UIManager.put("ToolBar.font", font);
		UIManager.put("ToolTip.font", font);
		UIManager.put("Tree.font", font);
	}

	public static void saveSettings(Font font, int size, String className) {

		Font newFont = new Font(font.getName(), Font.PLAIN, size);

		/* Sterg vechiul fiser de configurari */
		File file = new File(Resources.CONFIG_FILE);
		file.delete();

		FileOutputStream fos;

		try {
			fos = new FileOutputStream(Resources.CONFIG_FILE);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(newFont);
			oos.writeObject(className);
		} catch (IOException e) {
		}
	}
}
