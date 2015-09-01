/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import acm.graphics.*;
import acm.util.* ; 
public class HangmanCanvas extends GCanvas {

/** Resets the display so that only the scaffold appears */
	public void reset() {
		removeAll() ;
		drawScaffold() ; //This method draws the scaffold. 
	}

/**
 * Updates the word on the screen to correspond to the current
 * state of the game.  The argument string shows what letters have
 * been guessed so far; unguessed letters are indicated by hyphens.
 */
	public void displayWord(String word) {
		
		int centerX = getWidth() / 2 ;
	
		GLabel newDisplay = new GLabel (word) ; 
		newDisplay . setFont( "ComicSans-24" );
		remove (display) ;
		add (newDisplay, centerX - newDisplay . getWidth()/2 , getHeight() - WORD_DISPLAY_OFFSET ) ;
		display = newDisplay ;
	}

/**
 * Updates the display to correspond to an incorrect guess by the
 * user.  Calling this method causes the next body part to appear
 * on the scaffold and adds the letter to the list of incorrect
 * guesses that appears at the bottom of the window.
 */
	public void noteIncorrectGuess( char letter , int guesses, String word ) {
	
		addLetterToDisplay (letter , word ) ; //This method adds the letter to the display.
		
		if(guesses == 7) drawHead() ; 
		if(guesses == 6) drawBody() ;
		if(guesses == 5) drawLArm() ;
		if(guesses == 4) drawRArm() ;
		if(guesses == 3) drawLLeg() ;
		if(guesses == 2) drawRLeg() ;
		if(guesses == 1) drawLFoot() ;
		if(guesses == 0) drawRFoot () ;
		
	}

	/*The following methods draw the different body parts.
	 * */
	private void drawScaffold() {
		
		int centerX = getWidth() / 2 ;
		int centerY = getHeight() / 2 ;
		
		GLine column = new GLine (centerX - BEAM_LENGTH , centerY - BEAM_OFFSET_FROM_CENTER , centerX - BEAM_LENGTH , centerY - BEAM_OFFSET_FROM_CENTER + SCAFFOLD_HEIGHT ) ;
		GLine beam = new GLine (centerX - BEAM_LENGTH , centerY - BEAM_OFFSET_FROM_CENTER , centerX , centerY - BEAM_OFFSET_FROM_CENTER ) ;
		GLine rope = new GLine (centerX , centerY - BEAM_OFFSET_FROM_CENTER , centerX , centerY - BEAM_OFFSET_FROM_CENTER + ROPE_LENGTH ) ;
		add (column) ; 
		add (beam) ; 
		add (rope) ;
	}
	
	private void drawHead() {
			
		int centerX = getWidth() / 2 ;
		int centerY = getHeight() / 2 ;
		
		GOval head = new GOval (centerX - HEAD_RADIUS , centerY - BEAM_OFFSET_FROM_CENTER + ROPE_LENGTH , HEAD_RADIUS * 2 , HEAD_RADIUS * 2 ) ; 
		dialogue( 1 , 3) ;
		add (head) ;
		
	}

	private void drawBody() {
		
		int centerX = getWidth() / 2 ;
		int centerY = getHeight() / 2 ;
		
		GLine body = new GLine (centerX , centerY - BEAM_OFFSET_FROM_CENTER + ROPE_LENGTH + HEAD_RADIUS * 2 , centerX , centerY - BEAM_OFFSET_FROM_CENTER 
								+ ROPE_LENGTH + HEAD_RADIUS * 2 + BODY_LENGTH ) ;
		dialogue( 4 , 6) ;
		add (body) ;
	
	}
	
	private void drawLArm() {
		
		int centerX = getWidth() / 2 ;
		int centerY = getHeight() / 2 ;
		
		GLine upperLarm = new GLine (centerX - UPPER_ARM_LENGTH , centerY - BEAM_OFFSET_FROM_CENTER + ROPE_LENGTH + HEAD_RADIUS * 2 + ARM_OFFSET_FROM_HEAD , centerX , 
									 centerY - BEAM_OFFSET_FROM_CENTER + ROPE_LENGTH + HEAD_RADIUS * 2 + ARM_OFFSET_FROM_HEAD );
		GLine lowerLarm = new GLine (centerX - UPPER_ARM_LENGTH , centerY - BEAM_OFFSET_FROM_CENTER + ROPE_LENGTH + HEAD_RADIUS * 2 + ARM_OFFSET_FROM_HEAD , 	
									 centerX - UPPER_ARM_LENGTH , centerY - BEAM_OFFSET_FROM_CENTER + ROPE_LENGTH + HEAD_RADIUS * 2 + ARM_OFFSET_FROM_HEAD + 
									 LOWER_ARM_LENGTH) ; 
		dialogue( 7 , 9) ;
		add (upperLarm) ;
		add (lowerLarm) ;
		
	}
	
	private void drawRArm() {
		
		int centerX = getWidth() / 2 ;
		int centerY = getHeight() / 2 ;
		
		GLine upperRarm = new GLine (centerX + UPPER_ARM_LENGTH , centerY - BEAM_OFFSET_FROM_CENTER + ROPE_LENGTH + HEAD_RADIUS * 2 + ARM_OFFSET_FROM_HEAD , centerX , 
									 centerY - BEAM_OFFSET_FROM_CENTER + ROPE_LENGTH + HEAD_RADIUS * 2 + ARM_OFFSET_FROM_HEAD );
		GLine lowerRarm = new GLine (centerX + UPPER_ARM_LENGTH , centerY - BEAM_OFFSET_FROM_CENTER + ROPE_LENGTH + HEAD_RADIUS * 2 + ARM_OFFSET_FROM_HEAD , 	
									 centerX + UPPER_ARM_LENGTH , centerY - BEAM_OFFSET_FROM_CENTER + ROPE_LENGTH + HEAD_RADIUS * 2 + ARM_OFFSET_FROM_HEAD + 
									 LOWER_ARM_LENGTH) ; 
		dialogue( 7 , 9) ;
		add (upperRarm) ;
		add (lowerRarm) ;
		
	}
	
	private void drawLLeg() {
		
		int centerX = getWidth() / 2 ;
		int centerY = getHeight() / 2 ;
		
		GLine hip = new GLine( centerX - HIP_WIDTH , centerY - BEAM_OFFSET_FROM_CENTER + ROPE_LENGTH + HEAD_RADIUS * 2 + BODY_LENGTH , 
							   centerX , centerY - BEAM_OFFSET_FROM_CENTER + ROPE_LENGTH + HEAD_RADIUS * 2 +BODY_LENGTH) ;
		GLine leg = new GLine (centerX - HIP_WIDTH , centerY - BEAM_OFFSET_FROM_CENTER + ROPE_LENGTH + HEAD_RADIUS * 2 + BODY_LENGTH , 
							   centerX - HIP_WIDTH , centerY - BEAM_OFFSET_FROM_CENTER + ROPE_LENGTH + HEAD_RADIUS * 2 + BODY_LENGTH + LEG_LENGTH ) ;
		
		dialogue( 10 , 12) ;
		add (hip) ; 
		add (leg) ;
		
	}
	
	private void drawRLeg() {
		
		int centerX = getWidth() / 2 ;
		int centerY = getHeight() / 2 ;
		
		GLine hip = new GLine( centerX + HIP_WIDTH , centerY - BEAM_OFFSET_FROM_CENTER + ROPE_LENGTH + HEAD_RADIUS * 2 + BODY_LENGTH , 
							   centerX , centerY - BEAM_OFFSET_FROM_CENTER + ROPE_LENGTH + HEAD_RADIUS * 2 +BODY_LENGTH) ;
		GLine leg = new GLine (centerX + HIP_WIDTH , centerY - BEAM_OFFSET_FROM_CENTER + ROPE_LENGTH + HEAD_RADIUS * 2 + BODY_LENGTH , 
							   centerX + HIP_WIDTH , centerY - BEAM_OFFSET_FROM_CENTER + ROPE_LENGTH + HEAD_RADIUS * 2 + BODY_LENGTH + LEG_LENGTH ) ;
		
		dialogue( 10 , 12) ;
		add (hip) ; 
		add (leg) ;
		
	}
	
	private void drawLFoot () {
		
		int centerX = getWidth() / 2 ;
		int centerY = getHeight() / 2 ;
		
		GLine foot = new GLine ( centerX - HIP_WIDTH - FOOT_LENGTH , centerY - BEAM_OFFSET_FROM_CENTER + ROPE_LENGTH + HEAD_RADIUS * 2 + BODY_LENGTH + LEG_LENGTH ,
								 centerX - HIP_WIDTH , centerY - BEAM_OFFSET_FROM_CENTER + ROPE_LENGTH + HEAD_RADIUS * 2 + BODY_LENGTH + LEG_LENGTH) ;
		
		dialogue( 13 , 16) ;
		add(foot) ; 
		
	}
	
	private void drawRFoot () {
		
		int centerX = getWidth() / 2 ;
		int centerY = getHeight() / 2 ;
		
		GLine foot = new GLine ( centerX + HIP_WIDTH + FOOT_LENGTH , centerY - BEAM_OFFSET_FROM_CENTER + ROPE_LENGTH + HEAD_RADIUS * 2 + BODY_LENGTH + LEG_LENGTH ,
								 centerX + HIP_WIDTH , centerY - BEAM_OFFSET_FROM_CENTER + ROPE_LENGTH + HEAD_RADIUS * 2 + BODY_LENGTH + LEG_LENGTH) ;
		
		dialogue( 13 , 16) ;
		add(foot) ; 
		
	}
	
	private void addLetterToDisplay(char letter , String checkWord ){
		
		int centerX = getWidth() / 2 ;
		
		wrongGuesses += letter ; //Adds the new letter to wrong guesses.
		
		if (wrongGuesses . length () == 1) word = checkWord ; //This method assigns the checkWord to instance variable word, in order to check in future if the same word is being used. 
		
		if (!word . equals(checkWord)) { //This if statement is to restart the method in case the player wants to keep playing.
			wrongGuesses = "" ;
			wrongGuesses += letter ; //Adds the new letter to wrong guesses.
			word = checkWord ;
		}
		
		GLabel incorrectGuesses = new GLabel ("Incorrect guesses: ");
		remove(incorrectGuesses);
		add (incorrectGuesses, centerX - incorrectGuesses . getWidth() / 2 , getHeight() - INC_GUESSES_OFFSET ) ;
		
		GLabel newWrongGuessesLabel = new GLabel (wrongGuesses) ;
		remove(wrongGuessesLabel);
		add(newWrongGuessesLabel, centerX - newWrongGuessesLabel . getWidth() / 2 , getHeight() - WRONG_GUESSES_OFFSET ) ;
		wrongGuessesLabel = newWrongGuessesLabel ; 
	}
	

	/*This method gets a dialogue from a list of a txt document in the assignment 4 folder.
	 * */
	private static ArrayList<String> hangmanDialogue(){
		
		ArrayList<String> wordList = new ArrayList<String>() ; 
		
		try{
			BufferedReader hangmanDialogue = new BufferedReader (new FileReader( "HangmanDialogue.txt") );
		
			while(true){
				String word = hangmanDialogue . readLine();
				if (word == null) break ;
				wordList . add ( word ) ; 
			}
			hangmanDialogue . close() ;
		
		} catch (IOException ex) {
		
			throw new ErrorException(ex);
		
		}
		
		return wordList ; 
	}
	
	private void dialogue(int first , int last ) {
		
		int centerX = getWidth() / 2 ;
		
		int random = rgen . nextInt (first , last) ; 
		String message = hangmanDialogue . get (random) ;
		
		remove(shownDialogue) ; //Removes the previous shown dialogue.
		GLabel showDialogue = new GLabel(message) ;
		showDialogue . setFont("ComicSans-10") ;
		add(showDialogue , centerX - showDialogue . getWidth() / 2 , DIALOGUE_Y_OFFSET ) ;
		shownDialogue = showDialogue ; //Adds the new dialogue to the instance variable so that it can be removed in the future.
	
	}
	
/* Constants for the simple version of the picture (in pixels) */
	private String wrongGuesses ="" ; 
	private String word ; //Stores the word being used in Hangman.
	GLabel wrongGuessesLabel = new GLabel(""); ;
	private GLabel display = new GLabel("");
	private ArrayList<String> hangmanDialogue = hangmanDialogue() ; // Creates a word list to be used as a dialogue for hangman.
	private RandomGenerator rgen = RandomGenerator.getInstance() ; //Creates an random number to be used for dialogues. 
	private GLabel shownDialogue = new GLabel("") ; //This is the dialogue being shown to the player.
	private static final int DIALOGUE_Y_OFFSET = 80 ; //Offset from top to show dialogue.
	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int BEAM_LENGTH = 144;
	private static final int BEAM_OFFSET_FROM_CENTER = 150 ; //This is the offset of the beam from the center of the screen, to leave enough room for the labels underneath.
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 28;
	private static final int WORD_DISPLAY_OFFSET = 150 ; 
	private static final int INC_GUESSES_OFFSET = 130 ; 
	private static final int WRONG_GUESSES_OFFSET = 110; 
}
