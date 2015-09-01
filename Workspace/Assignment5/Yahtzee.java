/*
 * File: Yahtzee.java
 * ------------------
 * This program will eventually play the Yahtzee game.
 */

import acm.io.*;
import acm.program.*;
import acm.util.*;

public class Yahtzee extends GraphicsProgram implements YahtzeeConstants {
	
	public static void main(String[] args) {
		new Yahtzee().start(args);
	}
	
	//public void run() {
	//	IODialog dialog = getDialog();
	//	nPlayers = dialog.readInt("Enter number of players"); //Needs check for a maximum number...
	//	playerNames = new String[nPlayers];
	//	for (int i = 1; i <= nPlayers; i++) {
	//		playerNames[i - 1] = dialog.readLine("Enter name for player " + i);
	//	}
	//	display = new YahtzeeDisplay(getGCanvas(), playerNames);
	//	playGame();
	//}

	
	private void playGame() {
		
		categoriesChosen = new int[nPlayers][N_CATEGORIES] ; //This initializes the 2D array so that it can keep track of the categories already chosen by the players.
		scores = new int[nPlayers][N_CATEGORIES] ; // initializes the scores array.
		
		for(int i = 0 ; i < N_SCORING_CATEGORIES ; i++){ //This for loop is determined by the number of rounds.
			
			for (int p = 1 ; p <= nPlayers ; p++){ //This for loop is determined by the number of players.
				
				display . printMessage ("It's " + playerNames [p - 1] + "'s turn! Click \" Roll dice\" button to roll the dice.") ; 
				display . waitForPlayerToClickRoll( p ) ;
				display . displayDice( firstRoll() ) ; 
				
				for (int k = 0 ; k < CHANCES_TO_REROLL ; k++ ){
					
					display . printMessage ("Select the dice you wish to reroll and click \"Roll Again\". ") ;
					display . waitForPlayerToSelectDice() ;
					
					for (int l = 0 ; l < N_DICE ; l++ ){//This method checks each die to confirm if it has been selected and if so, rerolls it.
						
						if (display . isDieSelected (l)){ //This checks whether the die has been selected or not.
							
							dice[l] = rgen.nextInt(1 , N_SIDES) ; //Rerolls the die that has been selected.
							
						}
						
					}
					
					display . displayDice(dice) ; 
					
				}
				
				boolean validCategory = false ; //This variable is used to prompt the user for a valid category.
				int category = 0 ; //This integer is holds the category chosen by the user.
				display . printMessage ("Select a category for this roll.") ; //Asks the user for a category for the roll.
				
				while (!validCategory ){
					
					category = display . waitForPlayerToSelectCategory(); //This method asks for a category
					validCategory = checkIfValidCategory(category , p ) ;  //This method checks if the category chosen is valid, if not it will prompt the user for a valid one.
				
				}	
		 	
				resolveTurn( category , p ) ; //runs the method that checks whether the category is legal and assigns points.
			
			}
			
		}
	
		declareWinners(); //This method checks who is the winner.
		
	}
		
	
	/*This method creates the dice that is used in the first turn by the player.
	 */
	private int[] firstRoll() {
		
		for (int i = 0 ; i < N_DICE ; i++){
			
			dice[i] = rgen . nextInt (1 , N_SIDES) ; 
			
		}
		
		return dice ; 
	}
	
	/*This method checks if the category is valid, and if so, adds it to an array that keeps track 
	 * of the categories already chosen, so that the player cannot choose the same category more than
	 * once. 
	 * */ 
	private boolean checkIfValidCategory(int category , int player ){
		
		boolean isValid = false ;
		
		if (category == UPPER_SCORE) {
	
		} else if (category == UPPER_BONUS){
			
		} else if (category == LOWER_SCORE){
			
		} else if(category == TOTAL){
			
		} else {
			
			//This for loop checks if the category has been chosen before.
			for (int i = 0 ; i < categoriesChosen[player - 1] . length ; i++){
				
				if (categoriesChosen[player - 1][i] != category){
					
					isValid = true ;
				
				} else { 
				
					isValid = false ; 
					display . printMessage ("You already picked that category. Please choose another category!") ; //prompts the user for a valid category.
					break ; //This breaks the loop as soon as it finds that the category has been chosen.
				
				}
			}
		}
		
		//This if statement adds the category to the categoriesChosen array so that it can be kept into account for future checks.
		if (isValid){
			
			for (int j = 0 ; j < categoriesChosen[(player - 1)] . length ; j++){
				
				if( categoriesChosen[player - 1][j] == 0 ) {
					
					categoriesChosen[player - 1][j] = category ;
					break ; 
			
				}
				
			}
			
		}
		
		return isValid ;
	}
	
	/*This method checks whether the category is a valid choice and assigns the score to the player.
	 * */
	private void resolveTurn(int category , int player ){
		
		int scoreForRound = 0 ; //This instance variable tracks the score obtained in the round.
		
		switch (category) {
		
		case ONES: 
			
			if (checkCategory(dice, ONES)){
			
				for(int i = 0 ; i < N_DICE ; i++ ){
				
					if (dice[i] == ONES) {
						scoreForRound+= SCORE_ONES ; //Adds 1 for each one shown on the dice.
					
					}
					
				}
				
				scores [player-1][ONES - 1] = scoreForRound ; //Adds the score to the array that keeps track of the scores. 
				
			} break ; 
		
		case TWOS:
			
			if (checkCategory(dice, TWOS)){
				
				for(int i = 0 ; i < N_DICE ; i++ ){
				
					if (dice[i] == TWOS) {
						scoreForRound += SCORE_TWOS ; //Adds 2 for each two shown on the dice.
					}
					
				}
				
				scores [player-1][TWOS - 1] = scoreForRound ; //Adds the score to the array that keeps track of the scores. 
				
			} break ; 
			
		case THREES:
			
			if (checkCategory(dice, THREES)){
				
				for(int i = 0 ; i < N_DICE ; i++ ){
				
					if (dice[i] == THREES) {
						scoreForRound += SCORE_THREES  ; //Adds 3 for each three shown on the dice.
					}
					
				}
				
				scores [player-1][THREES - 1] = scoreForRound ; //Adds the score to the array that keeps track of the scores. 
			
			} break ; 
			
		case FOURS:
			
			if (checkCategory(dice, FOURS)){
				
				for(int i = 0 ; i < N_DICE ; i++ ){
				
					if (dice[i] == FOURS) {
						scoreForRound += SCORE_FOURS  ; //Adds 4 for each four shown on the dice.
					}
					
				}
				
				scores [player-1][FOURS - 1 ] = scoreForRound ; //Adds the score to the array that keeps track of the scores. 
			
			} break ;
	
		case FIVES:
			
			if (checkCategory(dice, FIVES)){
				
				for(int i = 0 ; i < N_DICE ; i++ ){
				
					if (dice[i] == FIVES) {
						scoreForRound += SCORE_FIVES  ; //Adds 5 for each five shown on the dice.
					}
					
				}
				
				scores [player-1][FIVES - 1] = scoreForRound ; //Adds the score to the array that keeps track of the scores. 
			
			} break ;
			
		case SIXES:
		
			if (checkCategory(dice, SIXES)){
				
				for(int i = 0 ; i < N_DICE ; i++ ){
				
					if (dice[i] == SIXES) {
						scoreForRound += SCORE_SIXES  ; //Adds 6 for each six shown on the dice.
					}
					
				}
				
				scores [player-1][SIXES - 1] = scoreForRound ; //Adds the score to the array that keeps track of the scores. 
			
			} break ;
	
		case THREE_OF_A_KIND:
			
			if (checkCategory(dice, THREE_OF_A_KIND)){
				
				for(int i = 0 ; i < N_DICE ; i++ ){
				
					scoreForRound += dice[i]  ; //Adds all the values shown on the dice.
					
				}
				
				scores [player-1][THREE_OF_A_KIND - 1] = scoreForRound ; //Adds the score to the array that keeps track of the scores. 
			
			} break ;
			
		case FOUR_OF_A_KIND:
			
			if (checkCategory(dice, FOUR_OF_A_KIND)){
				
				for(int i = 0 ; i < N_DICE ; i++ ){
				
					scoreForRound += dice[i]  ; //Adds all the values shown on the dice.
				
				}
				
				scores [player-1][FOUR_OF_A_KIND - 1] = scoreForRound ; //Adds the score to the array that keeps track of the scores. 
			
			} break ;
			
		case FULL_HOUSE:
			
			if (checkCategory(dice, FULL_HOUSE)){
				
					scoreForRound = SCORE_FULL_HOUSE  ; //Adds the score corresponding to this category.
					scores [player-1][FULL_HOUSE - 1 ] = scoreForRound ; //Adds the score to the array that keeps track of the scores. 
			} break ;
			
		case SMALL_STRAIGHT:
			
			if (checkCategory(dice, SMALL_STRAIGHT)){
				
				scoreForRound = SCORE_SMALL_STRAIGHT  ; //Adds the score corresponding to this category.
				scores [player-1][SMALL_STRAIGHT - 1 ] = scoreForRound ; //Adds the score to the array that keeps track of the scores. 
			} break ;
			
		case LARGE_STRAIGHT:
			
			if (checkCategory(dice, LARGE_STRAIGHT)){
				
				scoreForRound = SCORE_LARGE_STRAIGHT  ; //Adds the score corresponding to this category.
				scores [player-1][LARGE_STRAIGHT - 1 ] = scoreForRound ; //Adds the score to the array that keeps track of the scores. 
			} break ;
			
		case YAHTZEE:
		
			if (checkCategory(dice, YAHTZEE)){
				
				scoreForRound = SCORE_YAHTZEE  ; //Adds the score corresponding to this category.
				scores [player-1][YAHTZEE - 1 ] = scoreForRound ; //Adds the score to the array that keeps track of the scores. 
			} break ;
		
		case CHANCE:
			
			if (checkCategory(dice, CHANCE)){
				
				for(int i = 0 ; i < N_DICE ; i++ ){
				
					scoreForRound += dice[i]  ; //Adds all the values shown on the dice.
					scores [player-1][CHANCE - 1] = scoreForRound ; //Adds the score to the array that keeps track of the scores. 
				}
				
			} break ;
			
		}
		/*The following two loops update the scores according to their categories.
		 * 
		 */
		scores[player - 1][UPPER_SCORE - 1] = 0 ; //This reinitializes the upper score to be recalculated.
		for (int i = 0 ; i < UPPER_SCORE - 1 ; i++ ){
			
			scores[player - 1][UPPER_SCORE - 1] += scores[player - 1][i] ; 
			
		}
		
		checkForUpperBonus(player - 1) ; //This method checks if the player got the upper bonus points. 
		
		scores[player - 1][LOWER_SCORE - 1] = 0 ; //This reinitializes the lower score to be recalculated.
		for (int i = THREE_OF_A_KIND - 1 ; i < LOWER_SCORE - 1 ; i++ ){
			
			scores[player - 1][LOWER_SCORE - 1] += scores[player - 1][i] ; 
			
		}
		
		scores[player - 1][TOTAL - 1] = 0 ; //This reinitializes the total score to be recalculated.
		scores [player - 1][TOTAL - 1] = scores[player - 1][UPPER_SCORE - 1] + scores[player - 1][LOWER_SCORE - 1] ; 
		
		display . updateScorecard( category , player , scoreForRound ) ; //updates the score for the round of the particular player.
		display . updateScorecard( TOTAL , player , scores[ player -1 ][TOTAL - 1] ) ; //Updates the total score.
	}

	/*This method checks whether the player achieved the goal of the upper bonus, and if so adds those points.
	 */
	private void checkForUpperBonus(int player){
		
		if(scores [player][UPPER_SCORE - 1] >= UPPER_BONUS_GOAL ){
			
			scores[player][UPPER_BONUS - 1] = SCORE_UPPER_BONUS ; 
			scores[player][UPPER_SCORE - 1] += scores [player][UPPER_BONUS - 1] ;
			display . updateScorecard( UPPER_BONUS , player + 1 , SCORE_UPPER_BONUS ) ; //updates the  upper score bonus for the particular player.
			display . updateScorecard( UPPER_SCORE , player + 1 , scores[ player ][UPPER_SCORE - 1] ) ; //Updates the upper score.
			
		}
		
	}

	private boolean checkCategory(int[] dice , int category){ //This method checks if the category is applicable for the category.

		boolean isValid = false ; 

		switch(category) {

		case ONES:
			isValid = true ; 
			break;
		case TWOS:
			isValid = true ; 
			break;
		case THREES:
			isValid = true ; 
			break;
		case FOURS:
			isValid = true ; 
			break;
		case FIVES:
			isValid = true ; 
			break;
		case SIXES:
			isValid = true ; 
			break;
		case THREE_OF_A_KIND: {

			int counter = 0 ; //keeps track of how many instances are of the same value of a dice.
			int temp1, temp2, temp3 ; //temporary variables to check the different values of the dice.
			int i,j,k ; //Variables used for the loops that will compare the different values.

			for ( i = 0 ; i < dice.length - 2 ; i++){
				temp1 = dice[i];
				for ( j = i + 1 ; j < dice.length - 1 ; j++ ){

					temp2 = dice[j] ; 

					if(temp1 == temp2){
						
						counter = 2 ; //Two instances of the same value exist.
						
						for(k = j + 1 ; k < dice.length ; k++ ){

							temp3 = dice[k] ; 

							if(temp3 == temp2){

								counter++ ; //Three instances of the same value exist.
								break ; 
							}

						}

					}
					
					if (counter == 3) break; //This breaks the loop when the target is reached.
				
				}
				
				if (counter == 3) break; //This breaks the loop when the target is reached.
			
			}

			if (counter >= 3){

				isValid = true ; 

			}
		} break ; 
		case FOUR_OF_A_KIND:{
			int counter = 0 ; //keeps track of how many instances are of the same value of a dice.
			int temp1, temp2, temp3, temp4 ; //temporary variables to check the different values of the dice.
			int i,j,k,l ; //Variables used for the loops that will compare the different values.

			for ( i = 0 ; i < dice.length - 2 ; i++){
				
				temp1 = dice[i];
				
				for ( j = i + 1 ; j < dice.length - 1 ; j++ ){

					temp2 = dice[j] ; 

					if(temp1 == temp2){
						counter = 2 ; //Two instances of the same value exist.
						for(k = j + 1 ; k < dice.length ; k++ ){

							temp3 = dice[k] ; 

							if(temp3 == temp2){
								counter++ ; //Three instances of the same value exist.
								for(l = k + 1 ; k < dice.length ; l++){
									temp4 = dice[k];
									if(temp4 == temp3){
										counter++ ; //Four instances of the same value exist.
										break ; //The goal has been reached.
									}
								
								}
							
							}
							
							if (counter == 4) break ; //Breaks the for loop when the target has been reached.
						
						}

					}
					
					if (counter == 4) break ; //Breaks the for loop when the target has been reached.
				
				}
				
				if (counter == 4) break ; //Breaks the for loop when the target has been reached.
			
			}

			if (counter == 4){

				isValid = true ; 

			}
		} break ; 
		case FULL_HOUSE:{
			int counter1 = 0 ; //keeps track of how many instances are of the same value of a dice.
			int temp1, temp2, temp3 ; //temporary variables to check the different values of the dice.
			int ind1 = 0, ind2 = 0, ind3 = 0, ind4 = 0 ; //temporary variables to keep track of the indexes where the values have been found.
			int i,j,k ; //Variables used for the loops that will compare the different values.

			for ( i = 0 ; i < dice.length - 2 ; i++){
				
				temp1 = dice[i];//saves the first value to compare it with the following values.
				
				for ( j = i + 1 ; j < dice.length - 1 ; j++ ){

					temp2 = dice[j] ; 

					if(temp1 == temp2){
						
						counter1 = 2 ; //Two instances of the same value exist.
						ind1 = i ; 
						ind2 = j ; 
						
						for(k = j + 1 ; k < dice.length ; k++ ){

							temp3 = dice[k] ; 

							if(temp3 == temp2){
								
								ind3 = k ;
								counter1++ ; //Three instances of the same value exist.
								break ; 
							}

						}

					}
					
					if(counter1 == 3) break;//This makes sure to quit the loop if three of the same value have been found.
				
				}
				
				if(counter1 == 3) break;//This makes sure to quit the loop if three of the same value have been found.
			
			}
		
			temp1 = -1 ; //Change the value of temp1 to be evaluated in the next for loop.
			for (i = 0 ; i < dice . length ; i++){
				
				if (i != ind1 && i != ind2 && i != ind3){
					temp1 = dice[i];//Saves the value on temp variable to compare to the last dice.
					ind4 = i ; //Saves the last index in order to compare it with the last die.
					break; //breaks when finds the first value that is not part of the three of a kind.
				}
			}
			
			temp2 = 0 ; //Redefine temp2, reassign value in the next loop in order to compare it with temp1.
			for (i = 0 ; i < dice . length ; i++){
				
				if (i != ind1 && i != ind2 && i != ind3 && i != ind4 ){
					temp2 = dice[i];//Saves the value on temp variable to compare with the other value.
					break; //breaks when finds the last value that is not part of the three of a kind.
				}
			}
				
			if(temp1 == temp2 && counter1 == 3){//This if statement checks if both conditions have been met.
				isValid = true ; 
			}
			
		} break ; 
		case SMALL_STRAIGHT: //The values 3 and 4 will always be in the small straight configuration. The other values necessary are confirmed.
			if(isPresent(3 , dice) && isPresent ( 4, dice) && ((isPresent( 2 , dice) && isPresent(1 , dice)) || (isPresent(2, dice) && isPresent(5 , dice))
			  || (isPresent(5, dice) && isPresent(6,dice)) ) ){
				isValid = true ; 
			} 
		break ; 
		case LARGE_STRAIGHT://The values 2, 3, 4 and 5 will always be in the large straight configuration. The other value necessary must be confirmed.
			if( isPresent (2 , dice) && isPresent(3 , dice) && isPresent ( 4, dice) && isPresent(5, dice) && (isPresent(1 , dice)) || (isPresent(2, dice) && isPresent(6,dice)) ){
						isValid = true ; 
					} 
		break ; 
		case YAHTZEE:
			int temp1 = 0 ;
			int counter = 0  ; 
			
			for (int i = 0 ; i < dice.length ; i++){
			
				if(i == 0) {
					counter++ ;
					temp1 = dice[i];//Saves the first value, if it's really a Yahtzee, all values must be equal to this first one.
				} else {
					if(dice[i] == temp1){
						counter++ ; //Adds one whenever it finds a value equal to the first one.
					}
				}
				
			}
			
			if(counter == 5){
				isValid = true ;
			}
			
		break; 
		case CHANCE:
			isValid = true ; 
			break;
		}	
		
		return isValid ; 
	
	}
	
	//This method checks if the value is present in the array.
	private boolean isPresent(int value , int[] dice){
		boolean isPresent = false ; 
		
		for(int i = 0 ; i < dice . length ; i++){
			if(dice[i] == value){
				isPresent = true ; 
				break;
			}
		}
		
		return isPresent ;
	}
	
	/*This method declares the winner of the game.
	 * 
	 */
	private void declareWinners(){
		
		int winnerPlayer = 0 ;
		int winningScore = 0 ;
		
		for(int i=0 ; i < nPlayers ; i++){
			if(i == 0){
				winningScore = scores[i][TOTAL - 1];
			} else {
				if ( scores[i][TOTAL - 1] > winningScore ){
					winnerPlayer = i ;
					winningScore = scores[i][TOTAL -1] ; //Assigns the new high score to the variable that keeps track of it.
				}
				if ( scores[i][TOTAL - 1] == winningScore ){
					display . printMessage ("There's a tie, you guys will have to play again!!") ; 
				}
			}
			
			updateAllScores();
			display . printMessage ("The winner is " + playerNames[winnerPlayer] + "!! Contratulations!!") ; //Shows the message to the winner.
			
		}
	}
	
	/*This method updates the scorecard and shows it to the players.
	 * 
	 */
	private void updateAllScores(){
		for(int i = 0 ; i < nPlayers ; i++){
			display . updateScorecard( LOWER_SCORE , i + 1 , scores[ i ][LOWER_SCORE - 1] ) ; //Updates the lower score.
			display . updateScorecard( UPPER_SCORE , i + 1 , scores[ i ][UPPER_SCORE - 1] ) ; //Updates the upper score.
			display . updateScorecard( TOTAL , i + 1 , scores[ i ][TOTAL - 1] ) ; //Updates the total score.
		}
	}
	
	/* Private instance variables */
	private int nPlayers;
	private String[] playerNames;
	private YahtzeeDisplay display;
	private RandomGenerator rgen = new RandomGenerator();
	private int[] dice = new int[N_DICE] ; //This creates the dice array that will hold all the dice.
	private int[][] scores ; //This array will keep track of the scores of the players. 
	private int[][] categoriesChosen ; //This two-dimensional array keeps tracks of the categories chosen by each player.
	private static final int N_SIDES = 6 ; //Number of sides of the dice.
	private static final int CHANCES_TO_REROLL = 2 ; //Number of chances the player has to reroll the dice.

	//Constants for different scores used in the game.
	private static final int SCORE_ONES = 1 ;
	private static final int SCORE_TWOS = 2 ;
	private static final int SCORE_THREES = 3 ;
	private static final int SCORE_FOURS = 4 ;
	private static final int SCORE_FIVES = 5 ;
	private static final int SCORE_SIXES = 6 ;
	private static final int SCORE_UPPER_BONUS = 35 ;
	private static final int SCORE_FULL_HOUSE = 25 ;
	private static final int SCORE_SMALL_STRAIGHT = 30 ;
	private static final int SCORE_LARGE_STRAIGHT = 40 ;
	private static final int SCORE_YAHTZEE = 50 ;
	private static final int UPPER_BONUS_GOAL = 63 ; 
}
