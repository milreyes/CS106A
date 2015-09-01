/*
 * File: NameSurferEntry.java
 * --------------------------
 * This class represents a single entry in the database.  Each
 * NameSurferEntry contains a name and a list giving the popularity
 * of that name for each decade stretching back to 1900.
 */

import acm.util.*;
import java.util.*;
import java.io.*;

public class NameSurferEntry implements NameSurferConstants {

/* Constructor: NameSurferEntry(line) */
/**
 * Creates a new NameSurferEntry from a data line as it appears
 * in the data file.  Each line begins with the name, which is
 * followed by integers giving the rank of that name for each
 * decade.
 */
	public NameSurferEntry(String line) {
		
		name = readName(line) ; //Reads the name within the string.
		ranks = readRanks(line) ; //Reads the ranks per decades and stores them in an array.
	
	}

/* Method: getName() */
/**
 * Returns the name associated with this entry.
 */
	public String getName() {
		
		return name;
	}

/* Method: getRank(decade) */
/**
 * Returns the rank associated with an entry for a particular
 * decade.  The decade value is an integer indicating how many
 * decades have passed since the first year in the database,
 * which is given by the constant START_DECADE.  If a name does
 * not appear in a decade, the rank value is 0.
 */
	public int getRank(int decade) {
		
		int rank ; 
		
		rank = ranks[decade] ; 
		
		return rank;
	}

/* Method: toString() */
/**
 * Returns a string that makes it easy to see the value of a
 * NameSurferEntry.
 */
	public String toString() {
		String line = name ;
		
		for (int i = 0 ; i < NDECADES ; i++){
			
			line += " " + ranks[i] ; //Adds the different ranks to the line. 
		
		}
		
		line += "." ;
		
		return line;
	}

	//This method gets the name from the string extracted from the text file.
	private String readName(String line) {
		
		String name = "" ; 
		int i = 0 ; 
		
		while(true){
			
			name += line . charAt(i) ; //Saves the name.
			
			if (line . charAt(i + 1) == ' ') break ; //Breaks the loop when the there's a space
			i++ ;

		}
		
		return name ; 
	}
	
	//This method gets the ranks from each entry in the text file.
	private int[] readRanks(String line){
		
		int[] rankings = new int[NDECADES] ;
		String number = "" ; //This string saves a number that can be read as an int and stored in the array. 
		int lineIndex = 0 ; //This stores the index of the character that is being read on the line.
		int indexRankings = 0 ; //Keeps track of the index of the array that stores the rankings.
		boolean firstNumber = false ; //This boolean will return true when the method finds the first number.
		
		while (true){
			
			if(line . charAt(lineIndex) == ' '){
				
				firstNumber = true ; //This means that after this space there will be a number.
			
			}
			
			while (firstNumber){
				
				if (lineIndex + 1 < line . length() ){
					
					if (line . charAt(lineIndex + 1) != ' '){
					
						number += line . charAt(lineIndex + 1) ;
					
					} else {
					
						if (indexRankings < NDECADES){
							
							rankings[indexRankings] = Integer . parseInt(number) ; //Adds the integer to the array.
						
						}
						
						indexRankings++ ; //Changes the index to the next position in the array.
						number = "" ; //Resets the value so that it can store a new number.
					
					}
				}
				
				if((lineIndex + 1) == line . length()) {
					
					if (indexRankings < NDECADES){ //This if statements prevents errors if there's a space as a last character of the string.
						
						rankings[indexRankings] = Integer . parseInt(number) ; //Adds the integer to the array.
					
					}
					
					break ; 
				
				}
					
					lineIndex++ ; 
			
			}	
			
			if((lineIndex + 1) == line . length()) break ; 
			lineIndex++ ; 	
		
		}
		
		
		return rankings ; 
	
	}
	
	private String name ;
	private int[] ranks = new int[NDECADES] ; //Stores the ranks for each of the eleven decades.
}

