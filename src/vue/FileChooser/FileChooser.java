/*
 * Copyright (c) 2020.
 * Clément Truillet (clement@ctruillet.eu)
 */

package vue.FileChooser;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Frame de selction de fichier
 *
 * @see JFileChooser
 */
@SuppressWarnings("JavaDoc")
public class FileChooser extends JFileChooser {
	//Attributs
	private File file;
	private String repertory;

	//Constructeur
	public FileChooser(String repertory) {
		super(new File("./impeesa/baseDeDocuments"), FileSystemView.getFileSystemView());
		this.repertory = repertory;
		File rep = new File(repertory);
		if (!rep.exists()) {
			rep.mkdir();
		}
	}


	//Méthodes

	/**
	 * Affichage de la fenetre
	 *
	 * @param chooseType
	 */
	public void display(FileChooseType chooseType) {
		this.setMultiSelectionEnabled(false);
		this.setFileSelectionMode(JFileChooser.FILES_ONLY);


		this.setAcceptAllFileFilterUsed(false);

		FileFilter fileFilter = null;
		try {
			fileFilter = FabriqueFileChooserFilter.create(chooseType);
			this.setFileFilter(fileFilter);
		} catch (FileChooserException e) {
			e.printStackTrace();
		}

		if (this.showDialog(null, "Importer") == JFileChooser.APPROVE_OPTION) {
			this.file = this.getSelectedFile();
		}

		if (fileFilter != null) this.removeChoosableFileFilter(fileFilter);
	}

	/**
	 * @return File
	 * @see File
	 */
	public File getFile() {
		return (this.file);
	}

	/**
	 * Retourne le nom du fichier
	 *
	 * @return String
	 */
	public String getFileName() {
		return ((this.file == null) ? null : this.file.getName());
	}


}
