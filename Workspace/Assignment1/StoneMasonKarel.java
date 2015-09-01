/*
 * File: StoneMasonKarel.java
 * --------------------------
 * The StoneMasonKarel subclass as it appears here does nothing.
 * When you finish writing it, it should solve the "repair the quad"
 * problem from Assignment 1.  In addition to editing the program,
 * you should be sure to edit this comment so that it no longer
 * indicates that the program does nothing.
 */

import stanford.karel.*;

public class StoneMasonKarel extends SuperKarel {

	/*In this program Karel fixes the columns of the Main Quad
	 * so that the undergrads can have a safe and beautiful
	 * Full Moon at The Quad!
	 * 
	 * */
	
	public void run() {
		
		fixFirstColumn();
		fixOtherColumns();
	}
	/*Fixes first column.
	 * Precondition: Karel is standing at the bottom of the first column, facing east.
	 * Postcondition: Karel is standing at the bottom of the first column, facing east,
	 * feeling very proud of his great job of fixing the column!
	 * 
	 * */
	private void fixFirstColumn(){
		
		fixColumn();
	}
	
	private void fixOtherColumns() {
		
		while (frontIsClear()){
			moveToNextColumn();
			fixColumn();
		}
	}
	/* In this method Karel fills the corners without beepers with the 
	 * missing structural elements to hold the column.
	 * Precondition: Karel is facing east at the base of the column
	 * Postcondition: Karel is facing east at the base of the column
	 * */
	private void fixColumn(){
			
			turnLeft();
			
			while (frontIsClear()){
				if (noBeepersPresent()){
				putBeeper();
				move();
				}	else { 
					move();
					}
	
			}
		
			if (noBeepersPresent()){
				if (notFacingEast()){ //This is to make sure that Karel doesn't start a column where he shouldn't.
					putBeeper();
				}
			}
		
			returnToBaseOfColumn();
	}
	
	/*Precondition: Karel is at the top of the column facing north.
	 * Postcondition: Karel is at the bottom of the column facing east, 
	 * ready to move to the next column.
	 * */

	private void returnToBaseOfColumn(){
		turnAround();
		while (frontIsClear()){
			move();
		}
		turnLeft();
	
		}
	/*Karel moves to the next column and makes sure that he won't run into
	 * a wall.
	 * 
	 */
		private void moveToNextColumn(){
			
					if (frontIsClear()){
						move();
						if (frontIsBlocked()){ // This is to verify that Karel moved 4 spots before starting a new column.
							turnRight(); //This is to make sure that Karel doesn't start a column where he shouldn't.
						}
					}
					if (frontIsClear()){
						move();	
						if (frontIsBlocked()){ // This is to verify that Karel moved 4 spots before starting a new column.
							turnRight(); //This is to make sure that Karel doesn't start a column where he shouldn't.
						}
					}
					if (frontIsClear()){
						move();
						if (frontIsBlocked()){ // This is to verify that Karel moved 4 spots before starting a new column.
							turnRight(); //This is to make sure that Karel doesn't start a column where he shouldn't.
						}		
					}
					if (frontIsClear()){
						move();		
					}
		
	}


}
