package org.iMage.ProjectX.ChoosableFileFilters;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class PNGFileFilter extends FileFilter{
	String description = "png";

	@Override
	public boolean accept(File f) {
		if (f.getName().endsWith(".png")) {
			return true;
		}
		return false;
	}

	@Override
	public String getDescription() {
		return description;
	}
	
}
