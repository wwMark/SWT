package org.iMage.ProjectX;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JToolBar;
import java.awt.BorderLayout;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.iMage.ProjectX.ChoosableFileFilters.JPGFileFilter;
import org.iMage.ProjectX.ChoosableFileFilters.PNGFileFilter;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.Dimension;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JSlider;

public class Gui {

	private JFrame window;
	private BufferedImage o;
	private ImageIcon oIcon;
	private BufferedImage p;
	private ImageIcon pIcon;
	JCheckBox blurFilter;
	JComboBox filters;
	JCheckBox thresholdCB;
	JSlider thresholdSlider;

	/**
	 * Create the application.
	 */
	public Gui() {
		File file = new File("src/main/resources/HelloAndroid.jpg");
		try {
			o = ImageIO.read(file);
		} catch (IOException e) {
			System.err.println("The template does not exist!");
		}
		oIcon = new ImageIcon(o);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		window = new JFrame();
		window.setResizable(false);
		window.setMinimumSize(new Dimension(600, 600));
		window.setPreferredSize(new Dimension(600, 600));
		window.setMaximumSize(new Dimension(600, 600));
		window.setBounds(100, 100, 450, 300);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		window.setJMenuBar(menuBar);

		JMenu file = new JMenu("File");
		menuBar.add(file);

		JMenuItem open = new JMenuItem("open");
		open.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				final JFileChooser openPath = new JFileChooser(System.getProperty("user.home"));
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
						"Only JPG and PNG are accepted", "jpg", "png");
				openPath.setFileFilter(filter);
				openPath.setFileSelectionMode(JFileChooser.FILES_ONLY);
				if (openPath.showOpenDialog(null) == openPath.APPROVE_OPTION) {
					File file = openPath.getSelectedFile();
					try {
						o = ImageIO.read(file);
					} catch (IOException ioe) {
						System.err.println("Not valid image!");
					}
					oIcon = new ImageIcon(o);
				}
			}
		});
		file.add(open);

		JMenuItem save = new JMenuItem("save");
		open.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				final JFileChooser savePath = new JFileChooser(System.getProperty("user.home"));
				savePath.addChoosableFileFilter(new JPGFileFilter());
				savePath.addChoosableFileFilter(new PNGFileFilter());
				savePath.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int returnVal = savePath.showSaveDialog(null);
				if (returnVal == savePath.APPROVE_OPTION) {
					String extension = savePath.getFileFilter().getDescription();
					String saveName = savePath.getSelectedFile().getName();
					try {
						ImageIO.write(p, extension, new File(saveName));
					} catch (IOException ioe) {
						System.err.println("Saving process is interrupted!");
					}
				}
			}
		});
		file.add(save);

		JMenuItem exit = new JMenuItem("exit");
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		file.add(exit);

		JMenu about = new JMenu("About");
		menuBar.add(about);

		JMenuItem license = new JMenuItem("License");
		license.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new LicenseDialog();
			}
		});
		about.add(license);
		window.getContentPane().setLayout(new BoxLayout(window.getContentPane(), BoxLayout.Y_AXIS));

		JPanel picturePanel = new JPanel();
		window.getContentPane().add(picturePanel);
		picturePanel.setLayout(new BoxLayout(picturePanel, BoxLayout.X_AXIS));

		JLabel original = new JLabel("");
		original.setPreferredSize(new Dimension(300, 300));
		original.setMinimumSize(new Dimension(300, 300));
		original.setMaximumSize(new Dimension(300, 300));
		picturePanel.add(original);

		JLabel preview = new JLabel("");
		preview.setMinimumSize(new Dimension(300, 300));
		preview.setMaximumSize(new Dimension(300, 300));
		preview.setPreferredSize(new Dimension(300, 300));
		picturePanel.add(preview);

		JPanel filterPanel = new JPanel();
		window.getContentPane().add(filterPanel);
		filterPanel.setLayout(new BoxLayout(filterPanel, BoxLayout.Y_AXIS));

		blurFilter = new JCheckBox("BlurFilter");
		blurFilter.setSelected(true);
		filterPanel.add(blurFilter);

		filters = new JComboBox();
		filterPanel.add(filters);
		filters.setSelectedIndex(0);

		JPanel thresholdAdjustment = new JPanel();
		filterPanel.add(thresholdAdjustment);
		thresholdAdjustment.setLayout(new BoxLayout(thresholdAdjustment, BoxLayout.X_AXIS));

		thresholdCB = new JCheckBox("Threshold");
		thresholdCB.setSelected(true);
		thresholdAdjustment.add(thresholdCB);

		thresholdSlider = new JSlider();
		thresholdSlider.setValue(127);
		thresholdSlider.setMaximum(255);
		thresholdAdjustment.add(thresholdSlider);

		window.setVisible(true);
	}

	private void changePreview() {
		boolean b = this.blurFilter.isSelected();
		String s = String.valueOf(this.filters.getSelectedItem());
		boolean t = this.thresholdCB.isSelected();
		int v = this.thresholdSlider.getValue();
		if (b) {
			// blurFilter will be applied
		}
		switch (s) {
		case "Sobel Filter"://
		case "Scharr Filter"://
		}
		if (t) {
			//use the threshold method with a param v
		}
	}
}
