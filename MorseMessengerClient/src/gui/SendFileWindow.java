/*
 * Created by JFormDesigner on Sat May 05 19:32:24 EEST 2012
 */

package gui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Random;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingWorker;

/**
 * @author Ghennadi
 */
public class SendFileWindow extends JFrame implements PropertyChangeListener {

	public JScrollPane scrollPane1;
	private JList list1;
	public JPanel mainPanel;
	private Vector<SendFilePanel> transferPanels;
	private Vector<Task> tasks;

	public SendFileWindow() {
		super("File Transfer");
		initComponents();
	}

	private void initComponents() {
		mainPanel = new JPanel();
		scrollPane1 = new JScrollPane();
		list1 = new JList();

		transferPanels = new Vector<SendFilePanel>();
		tasks = new Vector<Task>();

		mainPanel.setLayout(new GridLayout(0, 1));
		scrollPane1.setViewportView(mainPanel);

		// ======== this ========
		setAutoRequestFocus(false);
		setResizable(false);
		Container contentPane = getContentPane();
		contentPane.setLayout(new GridBagLayout());
		((GridBagLayout) contentPane.getLayout()).columnWidths = new int[] {
				15, 0, 11, 0 };
		((GridBagLayout) contentPane.getLayout()).rowHeights = new int[] { 15,
				0, 10, 0 };
		((GridBagLayout) contentPane.getLayout()).columnWeights = new double[] {
				0.0, 1.0, 0.0, 1.0E-4 };
		((GridBagLayout) contentPane.getLayout()).rowWeights = new double[] {
				0.0, 0.0, 0.0, 1.0E-4 };

		scrollPane1.setPreferredSize(new Dimension(550, 200));
		contentPane.add(scrollPane1, new GridBagConstraints(1, 1, 1, 1, 0.0,
				0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 5, 5), 0, 0));
		pack();

		setLocationRelativeTo(getOwner());
	}

	void addTransfers() {
		Task task;
		SendFilePanel panel = new SendFilePanel();

		mainPanel.add(panel);
		transferPanels.add(panel);

		task = new Task(panel);
		task.addPropertyChangeListener(this);
		task.execute();

		tasks.add(task);
		scrollPane1.setViewportView(mainPanel);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if ("progress" == evt.getPropertyName()) {
			for (int i = 0; i < tasks.size(); i++) {
				if (evt.getSource() == tasks.get(i)) {
					int progress = (Integer) evt.getNewValue();
					transferPanels.get(i).progressBar.setValue(progress);
				}
			}
		}
	}

	private class Task extends SwingWorker<Void, Void> {

		private SendFilePanel sendPanel;

		public Task(SendFilePanel panel) {
			sendPanel = panel;
		}

		@Override
		public Void doInBackground() {
			Random random = new Random();
			int progress = 0;
			// Initialize progress property)
			setProgress(0);
			while (progress < 100) {
				// Sleep for up to one second.
				try {
					Thread.sleep(random.nextInt(1000));
				} catch (InterruptedException ignore) {
				}
				// Make random progress.
				progress += random.nextInt(10);
				setProgress(Math.min(progress, 100));
			}

			return null;
		}

		@Override
		public void done() {
			sendPanel.stateLabel.setIcon(new ImageIcon(Resources.CHECK_ICON));
			Toolkit.getDefaultToolkit().beep();
		}
	}

	public static void main(String[] args) {
		GUISettings.getSettings();
		SendFileWindow sw = new SendFileWindow();
		sw.setVisible(true);
		sw.setDefaultCloseOperation(EXIT_ON_CLOSE);

		for (int i = 0; i < 10; i++) {
			sw.addTransfers();
		}
	}
}
