package ca.mcgill.ecse223.quoridor.controller;
import java.io.*;
import ca.mcgill.ecse223.quoridor.features.*;
import ca.mcgill.ecse223.quoridor.model.Game;

public class QuoridorController{
	
	/**
	 * 
	 * @author Bozhong Lu
	 * Checks that file with specified name exists in my folder
	 * @param filename
	 * @return whether the file with the specified name exists
	 */
	public static boolean checkFileExistence(String filename) {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * Feature:SavePosition
	 * 
	 * @author Bozhong Lu
	 * @param filename
	 * @return String: the content of the new file;
	 * @throws IOException 
	 */
	// 1. Compute the string to be saved
	// 2. Save to file 
	// 3. Return the computed string 
	public static String saveGame(Game game , String filename) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(filename));
		StringBuilder sb = new StringBuilder();
	    String line = br.readLine();

	    while (line != null) {
	        sb.append(line);
	        sb.append(System.lineSeparator());
	        line = br.readLine();
	    }
	    String everything = sb.toString();
	    br.close();
	    return everything ;
	    
	}
	
	
	/**
	 * Feature:SavePosition
	 * 
	 * @author Bozhong Lu
	 * @param none
	 * @return void
	 */
	public static void overwriteExistingFile() {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * Feature:SavePosition
	 * 
	 * @author Bozhong Lu
	 * @param none
	 * @return void
	 */
	public static void cancelOverwriteExistingFile() {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * Feature:ValidatePosition
	 * 
	 * @author Bozhong Lu
	 * @param none
	 * @return boolean
	 */
	public static boolean validatePosition() {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * Feature:SavePosition
	 * 
	 * @author Bozhong Lu
	 * @param filename
	 * @return boolean
	 */
	public static boolean fileIsUpdated(String filename) {
		throw new UnsupportedOperationException();
	}
	
}