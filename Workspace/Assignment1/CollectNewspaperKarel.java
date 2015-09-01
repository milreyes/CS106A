/*
 * File: CollectNewspaperKarel.java
 * --------------------------------
 * At present, the CollectNewspaperKarel subclass does nothing.
 * Your job in the assignment is to add the necessary code to
 * instruct Karel to walk to the door of its house, pick up the
 * newspaper (represented by a beeper, of course), and then return
 * to its initial position in the upper left corner of the house.
 */

import stanford.karel.*;

public class CollectNewspaperKarel extends SuperKarel {
	
	/*The house can be any size as long as there's a wall surrounding it 
	 * with a gap for the door in eastern side of the house.
	 * Precondition: Karel is sitting in the northwestern corner of the house facing east 
	 * having a cup of hot and delicious java, 
	 * and the newspaper is right outside the entrance of the house.
	 * Postcondition: Karel returns to sit on couch facing east to read his newspaper.
	 * 
	*/
	public void run() {
		
		goToNewspaper(); 
		pickNewspaperUp();
		returnToCouch(); 
	}
		
	/*Precondition: Karel is sitting in the northwestern corner and has a wall to guide him to the entrance of the house.
	 *Postcondition: Karel is left standing on top of the newspaper.
	 * 
	 * */
		private void goToNewspaper() {
			while (leftIsBlocked() && frontIsClear()){
				move();
			}
			turnRight();
			while (leftIsBlocked() && frontIsClear()){
				move();
			}
			turnLeft();
			move();
		}
		/*Precondition: Karel is standing on top of the newspaper.
		 * Postcondition: Karel picks the newspaper up and is ready to go back into the house.
		 * 
		 */
		
		private void pickNewspaperUp() {
			pickBeeper();
			turnAround();
		}
		/*Precondition: Karel is right outside the house eager to read his newspaper.
		 * Postcondition: Karel ends up again sitting on his couch facing east, reading 
		 * the newspaper and enjoying his java.
		 * */
		
		private void returnToCouch(){
			move();
			turnRight();
			move();
			
				while (rightIsBlocked() && frontIsClear()){
					move();
				}
				
			turnLeft();
			
				while(rightIsBlocked() && frontIsClear()){
					move();
				}
				
			turnAround();
		}
		
}
