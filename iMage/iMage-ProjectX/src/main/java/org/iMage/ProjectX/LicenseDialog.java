package org.iMage.ProjectX;

import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.JButton;

public class LicenseDialog extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the application.
	 */
	public LicenseDialog() {
		setTitle("License Information");
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		JLabel licenseInfo = new JLabel("License here.");
		licenseInfo.setHorizontalAlignment(SwingConstants.CENTER);
		licenseInfo.setAlignmentX(Component.CENTER_ALIGNMENT);
		getContentPane().add(licenseInfo, BorderLayout.CENTER);

		JButton ok = new JButton("OK");
		ok.setVerticalAlignment(SwingConstants.TOP);
		ok.addActionListener(this);
		getContentPane().add(ok, BorderLayout.SOUTH);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.dispose();
	}

}
