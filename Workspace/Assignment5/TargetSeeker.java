

import acm.graphics.* ; 
import acm.program.*;
import java.awt.*;
import java.awt.event.*;

public class TargetSeeker extends GraphicsProgram {

	private static final int SEEKER_SIZE = 20 ; 
	private static final int TARGET_SIZE = 10 ;
	private static final double PAUSE_TIME = 10 ; 
	private GRect seeker ;
	private GRect target ; 
	
	public void init(){
		
		addSeeker() ; 
		addTarget(getWidth() / 2 , getHeight() / 2) ;
		addMouseListeners() ; 
		
	}
	
	public void mouseClicked (MouseEvent e){
		
		addTarget(e.getX() , e.getY()) ; 
		seekerFollow(e.getX() , e.getY()) ; 
	
	}
	
	private void addSeeker(){
		
		seeker = new GRect(SEEKER_SIZE , SEEKER_SIZE, 0, 0);
		add(seeker); 
		
	}

	private void addTarget(double x , double y){
		
		if(target == null){
			
			target = new GRect(TARGET_SIZE , TARGET_SIZE) ; 
			target . setFilled(true) ; 
			target . setColor (Color . RED) ;
			add(target , x - TARGET_SIZE / 2 , y - TARGET_SIZE / 2) ; 
			
		} else {
			
			target . setLocation (x - TARGET_SIZE / 2 , y - TARGET_SIZE / 2) ; 
			
		}
		
	}
	
	private void seekerFollow(double x , double y){
		
		while (seeker . getX() != x - SEEKER_SIZE / 2 && seeker . getY() != y - SEEKER_SIZE / 2) {
			
			if (seeker . getX() < x) {
				
				seeker . move(1,0) ; 
				pause(PAUSE_TIME) ; 
			
			} else if (seeker . getX() > x) {
				
				seeker . move(-1,0) ; 
				pause(PAUSE_TIME) ; 
			
			}
			
			if (seeker . getY() < y) {
				
				seeker . move(0,1) ; 
				pause(PAUSE_TIME) ; 
			
			} else if (seeker . getY() > y) {
				
				seeker . move(0,1) ; 
				pause(PAUSE_TIME) ; 
			
			}
		
		}
		
	}
	
}
