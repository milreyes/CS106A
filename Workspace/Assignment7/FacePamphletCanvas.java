/*
 * File: FacePamphletCanvas.java
 * -----------------------------
 * This class represents the canvas on which the profiles in the social
 * network are displayed.  NOTE: This class does NOT need to update the
 * display when the window is resized.
 */


import acm.graphics.*;
import java.awt.*;
import java.util.*;

public class FacePamphletCanvas extends GCanvas 
					implements FacePamphletConstants {
	
	/** 
	 * Constructor
	 * This method takes care of any initialization needed for 
	 * the display
	 */
	public FacePamphletCanvas() {
		
	}

	
	/** 
	 * This method displays a message string near the bottom of the 
	 * canvas.  Every time this method is called, the previously 
	 * displayed message (if any) is replaced by the new message text 
	 * passed in.
	 */
	public void showMessage(String msg) {
		
		GLabel message = new GLabel (msg) ; 
		message . setFont(MESSAGE_FONT) ; 
		add(message, getWidth() / 2 - message . getWidth() / 2 , getHeight() - BOTTOM_MESSAGE_MARGIN) ; 
		
	}
	
	
	/** 
	 * This method displays the given profile on the canvas.  The 
	 * canvas is first cleared of all existing items (including 
	 * messages displayed near the bottom of the screen) and then the 
	 * given profile is displayed.  The profile display includes the 
	 * name of the user from the profile, the corresponding image 
	 * (or an indication that an image does not exist), the status of
	 * the user, and a list of the user's friends in the social network.
	 */
	public void displayProfile(FacePamphletProfile profile) {
	
		removeAll() ; 
		displayName(profile) ; 
		displayFriends(profile) ; 
		displayPicture(profile) ; 
		displayStatus(profile) ; 
		
	}

	//This method shows the name label.
	
	private void displayName(FacePamphletProfile profile) {
		
		GLabel name = new GLabel(profile . getName()) ; 
		name . setFont (PROFILE_NAME_FONT) ; 
		name . setColor (Color . BLUE) ; 
		add(name , LEFT_MARGIN , TOP_MARGIN + name . getAscent() ) ; 
		
	}
	
	//This method shows the friends of the profile.
	
	private void displayFriends(FacePamphletProfile profile) {
		
		GLabel friends = new GLabel("Friends:") ; 
		friends . setFont (PROFILE_FRIEND_LABEL_FONT) ; 
		add(friends, getWidth() / 2 , TOP_MARGIN + PROFILE_NAME_FONT_HEIGHT + IMAGE_MARGIN ) ; //This is so that the baseline of the label appears in line with the image. 
		
		Iterator<String> friendList = profile . getFriends() ;
		double x = getWidth() / 2  , y = TOP_MARGIN + PROFILE_NAME_FONT_HEIGHT + IMAGE_MARGIN ;
		
		while ( friendList . hasNext() ){
			
			GLabel friend = new GLabel( friendList . next() ) ; 
			friend . setFont (PROFILE_FRIEND_FONT) ; 
			y += friend . getHeight() ; 
			add(friend , x , y) ; 
			
		}
	}

	//This method adds the picture
	
	private void displayPicture(FacePamphletProfile profile) {
		
		GImage image = profile . getImage() ; 
		
		if (image != null) {
			
			image . setSize (IMAGE_WIDTH , IMAGE_HEIGHT ) ; 
			add(image , LEFT_MARGIN , TOP_MARGIN + PROFILE_NAME_FONT_HEIGHT + IMAGE_MARGIN ) ; 
			
		} else { //In case there is no image for the specified profile
			
			GRect rect = new GRect( LEFT_MARGIN , TOP_MARGIN + PROFILE_NAME_FONT_HEIGHT + IMAGE_MARGIN , IMAGE_WIDTH , IMAGE_HEIGHT ) ;
			add(rect) ;
			
			GLabel noImage = new GLabel("No Image") ; 
			noImage .setFont (PROFILE_IMAGE_FONT);
			add(noImage , LEFT_MARGIN + IMAGE_WIDTH / 2 - noImage . getWidth() / 2 , 
				TOP_MARGIN + PROFILE_NAME_FONT_HEIGHT + IMAGE_MARGIN + IMAGE_HEIGHT / 2 + noImage . getAscent() / 2 );
			
		}
		
	}
	
	private void displayStatus(FacePamphletProfile profile) {
	
		if (! profile . getStatus() . equals("") ){
			
			GLabel status = new GLabel(profile . getName() + " is " + profile . getStatus()) ; 
			status . setFont (PROFILE_STATUS_FONT) ; 
			add( status , LEFT_MARGIN , TOP_MARGIN + PROFILE_NAME_FONT_HEIGHT + IMAGE_MARGIN + IMAGE_HEIGHT + STATUS_MARGIN + status . getAscent()  ) ;
		
		}
		
	}
	
	private static final int PROFILE_NAME_FONT_HEIGHT = 24 ; 
}
