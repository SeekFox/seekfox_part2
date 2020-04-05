/*
 * Copyright (c) 2020.
 * Clément Truillet (clement@ctruillet.eu)
 */

package vue.FileChooser;

import javax.swing.filechooser.FileFilter;
import java.io.File;

/**
 * Filtre de selection de fichiers Image
 *
 * @see FileFilter
 */
public class FileChooserFilterImage extends FileFilter {
	@Override
	public boolean accept(File f) {
		return f.isDirectory() || f.getName().endsWith(".jpg")
				|| f.getName().endsWith(".bmp");
	}

	@Override
	public String getDescription() {
		return "Fichiers Image";
	}
}