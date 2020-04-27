/*
 * Copyright (c) 2020.
 * Clément Truillet (clement@ctruillet.eu)
 */

package modele;

public class MotClefComplexe {
	//Attributs
	private String mot;
	private char polarite;

	//Constructeur
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
