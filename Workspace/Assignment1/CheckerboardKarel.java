/*
 * File: CheckerboardKarel.java
 * ----------------------------
 * When you finish writing it, the CheckerboardKarel class should draw
 * a checkerboard using beepers, as described in Assignment 1.  You
 * should make sure that your program works for all of the sample
 * worlds supplied in the starter folder.
 */

import stanford.karel.*;

public class CheckerboardKarel extends SuperKarel {

	public void run() {
		putFirstBeeper(); //Karel puts the first beeper.
		fillRestOfCheckerboard(); //Karel fills the rest of the checkerboard.
	}
	

	/*This method places the first beeper on the corner of first street and first avenue.
	 * Precondition: Karel starts facing east.
	 * Postcondition: Karel faces east in the same starting point.
	 * */
	
	private void putFirstBeeper(){
				putBeeper();
				}
	
	
	/*fillRestOfCheckerboard will fill the rest of the checkerboard making
	 * sure that is complying with the requirement that around black spots is white.
	 * 
	 * The way that this method works is by assuming that Karel starts facing east and moves as follows:
	 * 
	 *     |^ ->->... |And so on...
	 * WALL||<-<-<-<-^|
	 *  START->->->->||WALL
	 * 
	 *  This is the logic behind this function. It will stop when it makes the check that it cannot move further because it has
	 *  come to the logical end.
	 * */
	
	private void fillRestOfCheckerboard(){
		
		if(frontIsClear()){
			
			while (frontIsClear()){
		
						while (facingEast()){
							if (frontIsClear()){
								move();
								fillIfApplicable();
							}else {
								if (leftIsClear()){ // checks if haven't reached to the logical end
									turnLeft();
									}
								if (frontIsClear()){ // checks if haven't reached to the logical end
									move();
									fillIfApplicable();
									turnLeft();
								}
							}
						}
						
						while (facingWest()){
							if (frontIsClear()){
								move();
								fillIfApplicable();
							}else {
								if (rightIsClear()){ // checks if haven't reached to the logical end
									turnRight();
									}
								if (frontIsClear()){ //checks if haven't reached to the logical end
									move();
									fillIfApplicable();
									turnRight();
								}	
							}
						}
			}
		}
	/*
	 * This section of code is to prevent a bug in vertical cases. Karel turns left and faces north, and starts filling
	 * the corresponding corners with beepers.
	 * 
	 * */
		if (frontIsBlocked()){
			turnLeft();
			while (frontIsClear()){
				move();
				fillIfApplicable();
			}
			
			
		}
		
	
	
	}	
	
				/*fillIfApplicable()
				 * This cool method will confirm if Karel should fill the spot he is standing or not. This method relies on the logic 
				 * behind the way Karel is parsing the area. In other words, this method just checks the corner behind Karel to make 
				 * sure that he can actually fill the current corner. The logic behind Karel's movement makes it possible and correct
				 * to just check the spot behind.
				 * 
				 * Precondition: Karel must be standing on the spot that he is going to check if it needs to be
				 * filled.
				 * Postcondition: Karel fills it and is ready to move to the next logical corner.
				 * */
				
				
				private void fillIfApplicable(){
					
					turnAround();
					move();
						if (beepersPresent()){
							turnAround();
							move();
						}else { 
							turnAround();
							move();
							putBeeper();
						}
						
						
				}
	
				
}

