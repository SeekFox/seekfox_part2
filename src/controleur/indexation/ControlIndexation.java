/*
 * Copyright (c) 2020.
 * Clément Truillet (clement@ctruillet.eu)
 */

package controleur.indexation;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ControlIndexation {
	//Attributs
	protected String executableC;

	//Constructeur

	//Méthodes
	//ToDO
	public void indexation(String args){
		//Méthode qui lance l'indexation et verifie son bon déroulement
	}

	//ToDo
	private void runIndexation(String args){
		String s;
		try {
			Process p = Runtime.getRuntime().exec(executableC + " " + args);

			//Lecture du terminal
			BufferedReader br = new BufferedReader( new InputStreamReader(p.getInputStream()));

			while((s = br.readLine())!=null){
				System.out.println(s);
			}

			br.close();

		}catch(Exception e){
			System.out.println(e.toString());
		}
	}

	@Override
	public String toString() {
		return "ControlIndexation{" +
				"executableC='" + executableC + '\'' +
				'}';
	}
}
