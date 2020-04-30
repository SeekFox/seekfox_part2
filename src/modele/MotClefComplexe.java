/*
 * Copyright (c) 2020.
 * Clément Truillet (clement@ctruillet.eu)
 */

package modele;

/**
 * Classe gérant les mot clefs complexes afin de gérer la recherche complexe
 */
public class MotClefComplexe {
	//Attributs
	private String mot;
	private char polarite;

	//Constructeur

	/**
	 * Constructeur d'un mot clef complexe
	 * @param requete
	 */
	public MotClefComplexe(String requete){
		if(requete.length()<=1){
			throw new IllegalArgumentException("le motClef n'est pas un mot valide");
		}

		switch (requete.charAt(0)){
			case '+':
				this.mot = requete.substring(1);
				this.polarite='+';

				break;

			case '-':
				this.mot = requete.substring(1);
				this.polarite='-';

				break;

			default:
				if(Character.isLetter(requete.charAt(0))){
					this.mot = requete;
					this.polarite='+';
				}else{
					throw new IllegalArgumentException("La polarité n'est pas valide");
				}
				break;


		}
	}

	//Méthodes
	public String getMot() {
		return mot;
	}

	public char getPolarite() {
		return polarite;
	}

	@Override
	public String toString() {
		return "MotClefComplexe{" +
				"mot='" + mot + '\'' +
				", polarite=" + polarite +
				'}';
	}
}
