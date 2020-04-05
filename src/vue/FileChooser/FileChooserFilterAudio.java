/*
 * Copyright (c) 2020.
 * Clément Truillet (clement@ctruillet.eu)
 */

package vue.FileChooser;

import javax.swing.filechooser.FileFilter;
import java.io.File;

/**
 * Filtre de selection de fichiers audio
 *
 * @see FileFilter
 */
public class FileChooserFilterAudio extends FileFilter {
	@Override
	public boolean accept(File f) {
		return f.isDirectory() || f.getName().endsWith(".bin");
	}

	@Override
	public String getDescription() {
		return "Fichiers Audio";
	}
}