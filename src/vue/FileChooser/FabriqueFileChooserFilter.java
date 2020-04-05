/*
 * Copyright (c) 2020.
 * Clément Truillet (clement@ctruillet.eu)
 */

package vue.FileChooser;


import javax.swing.filechooser.FileFilter;

/**
 * Fabrique de filtre
 */
public class FabriqueFileChooserFilter {

	/**
	 * @param fileChooseType
	 * @return
	 * @throws FileChooserException
	 */
	public static FileFilter create(FileChooseType fileChooseType) throws FileChooserException {
		FileFilter fileFilter;

		switch (fileChooseType) {
			case TEXT:
				fileFilter = new FileChooserFilterText();
				break;

			case IMAGE:
				fileFilter = new FileChooserFilterImage();
				break;

			case AUDIO:
				fileFilter = new FileChooserFilterAudio();
				break;

			default:
				throw new FileChooserException("Impossible de créer un filtre");
		}

		return fileFilter;
	}

}
