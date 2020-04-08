/*
 * Copyright (c) 2020.
 * Clément Truillet (clement@ctruillet.eu)
 */

package vue.FileChooser;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
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
		super();
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

			//Copie du fichier dans le repertoire requete
			try {
				//Si un fichier du même nom est déjà dans le repertoire, on le supprime
				File f = new File((Paths.get(this.repertory, this.getFileName())).toString());

				if (f.exists()) {
					f.delete();
				}

				Files.copy(this.file.toPath(), Paths.get(this.repertory, this.getFileName()));
			} catch (IOException e) {
				e.printStackTrace();
			}

			this.file = new File((Paths.get(this.repertory, this.getFileName())).toString());
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
