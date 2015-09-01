/*
 * File: MidpointFindingKarel.java
 * -------------------------------
 * When you finish writing it, the MidpointFindingKarel class should
 * leave a beeper on the corner closest to the center of 1st Street
 * (or either of the two central corners if 1st Street has an even
 * number of corners).  Karel can put down additional beepers as it
 * looks for the midpoint, but must pick them up again before it
 * stops.  The world may be of any size, but you are allowed to
 * assume that it is at least as tall as it is wide.
 */

import stanford.karel.*;

public class MidpointFindingKarel extends SuperKarel {

	/*This class will make Karel find the midpoint of a rectangular area
	 * and mark it on the first street at the middle avenue.
	 * Precondition: Karel is facing east in the first street and first avenue corner
	 * Postcondition: Karel is in the midpoint of first avenue facing east.
	 */
	 
	public void run(){
		
		calculateMidpoint(); // Karel uses a counting method to calculate the midpoint of the first street.
		placeBeeperOnMidpoint(); // Karel moves a put a beeper in the midpoint corner.
		cleanUp(); // cleans auxiliary beepers used to find the midpoint.
		returnToMidpoint(); //leaves Karel standing on top of the midpoint smiling facing east and feeling proud of his job.  
	 }

	/*calculateMidpoint() will allow Karel find out where the midpoint is.
	 * Precondition: Karel is standing on top of first street and first avenue facing east.
	 * Postcondition: Karel is left standing on top of the counter pile facing north.
	 * */
				private void calculateMidpoint(){
						
					countIntersectionsOnFirstStreet(); // this method calculates the number of intersections with first street.
					determineMiddleIntersection(); //this method determines what would be the position for the middle avenue.
				}
	
					private void countIntersectionsOnFirstStreet(){
						
						while (frontIsClear()){
							if(noBeepersPresent()){
								putBeeper();
								returnToCounterPile();//this method helps Karel return to his counter pile and keep track of corners.
								putBeeper();
								goToNextCorner(); //this method is for Karel to move to the next logical corner to count from counter pile.
							}
						}
						if (frontIsBlocked()){
							cleanUp(); //this method cleans the first street and leaves it without beepers.
						}
						
					}
					/*This method will allow Karel to determine which one is the middle avenue.
					 * Precondition: Karel is standing on top of the counter pile facing north.
					 * Postcondition: Karel is standing on the middle avenue, ready to placeBeeperOnMidpoint()
					 * */
					
					private void determineMiddleIntersection(){
						
						while(beepersPresent()){
							pickBeeper();
							if (beepersPresent()){ // to prevent crash in case of odd number of beepers.
								pickBeeper(); // Karel picks up two beepers because this is the way that he can divide by two the total number of corners.
							}
							goToNextCorner(); //This allows Karel to go the next position on first street.
							putBeeper();
							returnToCounterPile(); //This helps Karel come back to his counter pile.
						}
					}
							/*The method addOneToCounter() makes Karel add one beeper to his counter pile
							 * in order to keep track of the corners on the first street. 
							 * Precondition: Karel is standing on the last corner that he just checked facing east.
							 * Postcondition: Karel is left standing on the counter pile after adding a beeper to his pile facing north.
							 * */
							private void returnToCounterPile(){
								
								turnAround();
								moveToWall(); //Karel will return to the intersection of first street and first avenue.
								turnRight();
								move();
							}
							
							/*Precondition: Karel is facing north right after adding one beeper
							 * to his counter pile.
							 * Postcondition: Karel is back on the first street and on the next corner that
							 * he needs to count.
							 * 
							 * */
							private void goToNextCorner(){
								
								turnAround();
								move();
								turnLeft();
								while (beepersPresent()){
									move(); //Karel will move until he finds no more beepers.
								}
								
							}
							
							/*This method will make Karel clean the beepers that are to his back.
							 * Precondition: Karel is facing east and will remove the beepers behind him
							 * Postcondition: Karel is facing north after removing unnecessary beepers on top of the counter pile.
							 * */
							
							private void cleanUp(){
								
								turnAround();
								move();
								while (beepersPresent()){
									pickBeeper();
									if (frontIsClear()){
										move();
									}
								}	
								if (frontIsBlocked()){
									turnRight();
									move(); //Karel is on top of the counter pile facing north.
								}
							}
							
							/*This method makes Karel move to the wall.
							 * Precondition: Karel is facing any direction.
							 * Postcondition: Karel is left standing in front of the wall that he was facing.
							 * */
							private void moveToWall(){
								
								while (frontIsClear()){
									move();
								}
							}
		/*This method allows Karel to put the beeper on the exact midpoint
		 * Precondition: Karel is facing north on where the counter pile was.
		 * Postcondition: Karel is facing east on the exact midpoint after placing a beeper.
		 * */
		
							
		private void placeBeeperOnMidpoint(){
			
			goToNextCorner();
			putBeeper();
		}
		
		/* Karel returns to midpoint from the previous location of the counter pile.
		 *Precondition: Karel is standing on where the counter pile was facing north.
		 * Postcondition: Karel is standing on top of the midpoint with a beeper under him, beaming with joy.
		 */
		private void returnToMidpoint(){
			
			turnAround();
			move();
			turnLeft();
			while (noBeepersPresent()){
				move(); //On top of the middle beeper.
			}
			
		}
}
