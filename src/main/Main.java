package main;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import dao.DaoVille;
import helpers.XMLHelper;

public class Main {
	private static String fileName = "villes.xml";
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		String cp;
		if(args.length==0)
			cp = JOptionPane.showInputDialog("Code postal");
		else
			cp = args[0];
		
		if(cp.length()>5){
			System.out.println("Erreur de saisie pour le code postal");
			return;
		}
		
		DaoVille dao = new DaoVille();
		ResultSet rs = dao.getVillesByCodePostal(cp);
		XMLHelper.createFile(rs,fileName);
		dao.close();
	}

}
