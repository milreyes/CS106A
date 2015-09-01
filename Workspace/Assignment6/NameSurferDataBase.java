import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import acm.util.ErrorException;

/*
 * File: NameSurferDataBase.java
 * -----------------------------
 * This class keeps track of the complete database of names.
 * The constructor reads in the database from a file, and
 * the only public method makes it possible to look up a
 * name and get back the corresponding NameSurferEntry.
 * Names are matched independent of case, so that "Eric"
 * and "ERIC" are the same names.
 */

public class NameSurferDataBase implements NameSurferConstants {
	
/* Constructor: NameSurferDataBase(filename) */
/**
 * Creates a new NameSurferDataBase and initializes it using the
 * data in the specified file.  The constructor throws an error
 * exception if the requested file does not exist or if an error
 * occurs as the file is being read.
 */
	public NameSurferDataBase(String filename) {
	
		try {
    		
    		BufferedReader rd = new BufferedReader(new FileReader(filename));
    		
    		while (true) {
    			
    			String textLine = rd.readLine();
    			if (textLine == null) break;
    			NameSurferEntry entry = new NameSurferEntry(textLine) ;
    			database . put (entry . getName() , entry) ; 
    			
    		}
    		
    		rd.close();
    		
		} catch (IOException ex) {
			
			throw new ErrorException(ex);
		
		}
	
	}
	
/* Method: findEntry(name) */
/**
 * Returns the NameSurferEntry associated with this name, if one
 * exists.  If the name does not appear in the database, this
 * method returns null.
 */
	public NameSurferEntry findEntry(String name) {
		
		String normalName = normalizeName(name) ; 
		
		NameSurferEntry entry;
		
		if (database . containsKey (normalName)){
			
			entry = database . get(normalName) ; 
			
		} else {
			
			entry = null ; //  IMPORTANT: Do not show anything. Make method.
			
		}
		
		return entry ;
	
	}
	
	//This method makes sure that the name entered will match those present in the text file. It will make the name start with a capital letter and the rest will be lower case letters.
	private String normalizeName(String name) {
		
		String normalName = "" ; 
		char firstLetter ; 
		
		firstLetter = toCapital(name . charAt(0)) ; 
		
		normalName += firstLetter ; 
		
		for (int i = 1 ; i < name . length() ; i++) {
		
			normalName +=  toLowerCase ( name . charAt(i) ) ; 
			
		}
		
		return normalName ; 
		
	}
	
	//Makes a char capital.
	
	private char toCapital(char letter) {
		
		char capitalized = letter ;
		
		if( letter >= 'a' && letter <= 'z' ) {
			
			capitalized = (char) (letter + ('A' - 'a')) ; //Turns the letter to capital.
			
		}
		
		return capitalized ; 
	
	}
	
	//Makes a char lower case.
	
	private char toLowerCase (char letter) {
		
		char small = letter ;
		
		if( letter >= 'A' && letter <= 'Z' ) {
			
			small = (char) (letter - ('A' - 'a')) ; //Turns the letter to little.
			
		}
		
		return small ; 
	
	}
	
	private Map <String, NameSurferEntry> database = new HashMap<String,NameSurferEntry>() ; //This map is used to save the database and use the name as the key.

}

