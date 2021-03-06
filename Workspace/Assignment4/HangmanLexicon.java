/*
 * File: HangmanLexicon.java
 * -------------------------
 * This file contains a stub implementation of the HangmanLexicon
 * class that you will reimplement for Part III of the assignment.
 */

import java.util.*;
import acm.util.*;
import java.io.*;
import acm.program.*;
import java.awt.*;
import java.lang.*;

public class HangmanLexicon {

	public static ArrayList<String> createWordList(){
		
		ArrayList<String> wordList = new ArrayList<String>() ; 
		
		try{
			BufferedReader hangmanLexicon = new BufferedReader (new FileReader( "HangmanLexicon.txt") );
		
			while(true){
				String word = hangmanLexicon . readLine();
				if (word == null) break ;
				wordList . add ( word ) ; 
			}
			hangmanLexicon . close() ;
		
		} catch (IOException ex) {
		
			throw new ErrorException(ex);
		
		}
		
		return wordList ; 
	}

 
	
	/** Returns the number of words in the lexicon. */
	public static int getWordCount() {
		return 10;
	}

	/** Returns the word at the specified index. */
	public static String getWord(int index) {
		switch (index) {
		case 0: return "BUOY";
		case 1: return "COMPUTER";
		case 2: return "CONNOISSEUR";
		case 3: return "DEHYDRATE";
		case 4: return "FUZZY";
		case 5: return "HUBBUB";
		case 6: return "KEYHOLE";
		case 7: return "QUAGMIRE";
		case 8: return "SLITHER";
		case 9: return "ZIRCON";
		default: throw new ErrorException("getWord: Illegal index");
		}
	};
}
