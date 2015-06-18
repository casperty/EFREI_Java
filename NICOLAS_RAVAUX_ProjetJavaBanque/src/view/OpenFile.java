package view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
/**
 * @author Jerome Nicolas et Alexandre Ravaux
 *
 */
public class OpenFile {
	public OpenFile(String selectedFile, ArrayList<String>fileContent) throws IOException{
	     System.out.println("[Debug] Path : "+selectedFile);
	     /* Remarque : on aurait pu utiliser Scanner */
	     BufferedReader buff = new BufferedReader(new InputStreamReader(new FileInputStream(selectedFile), "UTF-8"));
    	 try {
    		 String line;
    		 /* lecture ligne par ligne */
    		 while ((line = buff.readLine()) != null) { // fin de lecture quand on retourne la valeur null
    			 fileContent.add(line);
    			 System.out.println("[Debug] Line read : "+line);
    		 }
    	 }finally {
    		 //dans tous les cas, on ferme nos flux
    		 buff.close();
    	 }
		
	}
}
