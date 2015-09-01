/* 
 * File: FacePamphlet.java
 * -----------------------
 * When it is finished, this program will implement a basic social network
 * management system.
 */

import acm.program.*;
import acm.graphics.*;
import acm.util.*;
import java.awt.event.*;
import javax.swing.*;

public class FacePamphlet extends Program 
					implements FacePamphletConstants {

	/**
	 * This method has the responsibility for initializing the 
	 * interactors in the application, and taking care of any other 
	 * initialization that needs to be performed.
	 */
	public void init() {
	
		addInteractors();
		addActionListeners();
		database = new FacePamphletDatabase() ; 
		canvas = new FacePamphletCanvas() ; 
		add(canvas) ; 
    }
    

	private void addInteractors(){
		
		JLabel name = new JLabel("Name:");
		JLabel space1 = new JLabel(EMPTY_LABEL_TEXT) ;
		JLabel space2 = new JLabel(EMPTY_LABEL_TEXT) ;
		
		nameField = new JTextField(TEXT_FIELD_SIZE); //This field is to interact with the database. 
		nameField . addActionListener(this) ; 
	
		addButton = new JButton ("Add") ; 
		deleteButton = new JButton ("Delete") ;
		lookUp = new JButton ("Lookup") ;
		
		statusField = new JTextField (TEXT_FIELD_SIZE) ; 
		statusField . addActionListener (this) ;
		
		changeStatus = new JButton ("Change Status") ; 
		
		pictureField = new JTextField (TEXT_FIELD_SIZE) ; 
		pictureField . addActionListener(this) ; 
		
		changePicture = new JButton ("Change Picture") ; 
		
		friendZone = new JTextField (TEXT_FIELD_SIZE) ;
		friendZone .addActionListener(this) ; 
		
		addFriend = new JButton ("Add Friend") ; 
		
		
		//Adding the interactors:
		
		add (name, NORTH) ;
		add (nameField, NORTH) ; 
		add (addButton, NORTH) ;
		add (deleteButton, NORTH) ;
		add (lookUp, NORTH) ;
		
		add (statusField , WEST) ; 
		add (changeStatus , WEST) ; 
		add (space1 , WEST) ;
		add (pictureField , WEST) ; 
		add (changePicture , WEST) ; 
		add (space2 , WEST) ; 
		add (friendZone , WEST) ; 
		add (addFriend , WEST) ; 
		
		
		
	}
  
    /**
     * This class is responsible for detecting when the buttons are
     * clicked or interactors are used, so you will have to add code
     * to respond to these actions.
     */
    public void actionPerformed(ActionEvent e) {
	  
    	Object source = e . getSource() ; 
		
		if( source == addButton || source == nameField){ //When enter is hit the default process is to add the profile to the database.
			
			
			if ( ! (nameField . getText()) . equals("") ) { //Compares the text to the database and creates a new profile if it doesn't exist.
				
			
				if (!(database . containsProfile (nameField . getText()))){
					
					FacePamphletProfile profile = new FacePamphletProfile ( nameField . getText() ) ; 
					database . addProfile (profile) ; 
					currentProfile = profile . getName() ; 
					canvas . displayProfile ( database . getProfile ( currentProfile ) ) ; 
					canvas . showMessage("Displaying" + " " + currentProfile) ; 
					
				} else {
					
					canvas . displayProfile ( database . getProfile ( currentProfile ) ) ;
					canvas . showMessage("A profile with the name " + nameField . getText() + " already exists.") ;
					
				}
			
			} else {
				
				canvas . removeAll() ; 
				canvas . showMessage("Please add a name to the database.") ; 
				
			}
			
			
		} else if (source == deleteButton){ //Deletes the selected profile.
			
			if (!((nameField . getText()) . equals("")) && (database . containsProfile (nameField . getText()))) {
				
				database . deleteProfile( nameField . getText() ) ; 
				String deletedProfile = currentProfile ; 
				currentProfile = null ;
				canvas . removeAll() ; 
				canvas . showMessage ( deletedProfile + " has been deleted.") ;
				
			} else {
			
				canvas . removeAll() ;
				
				if (!((nameField . getText()) . equals(""))){
					
					canvas . showMessage ( nameField . getText() + " does not exist in the profile database.") ;
				
				} else {
					
					canvas . showMessage("Please type the profile you want to delete.") ;
					
				}
			
			}
		
		} else if (source == lookUp){
			
			if (!nameField . getText() . equals("")){
				
				if (database . containsProfile (nameField . getText())){ //This if statement makes sure that the profile already exists.
					
					FacePamphletProfile profile = database . getProfile(nameField . getText()) ; 
					currentProfile = profile . getName() ;
					canvas . displayProfile ( database . getProfile ( currentProfile ) ) ; 
					canvas . showMessage("Displaying" + " " + currentProfile) ;
					
				} else {
					
					canvas . displayProfile ( database . getProfile ( currentProfile ) ) ;
					canvas . showMessage("A profile with the name " + nameField . getText() + " does not exist.") ;
					
				}
			
			} else {
				
				canvas . removeAll() ;
				canvas . showMessage ( "Please type a profile name.") ;
				
			}
			
			
			
		} else if (source == statusField || source == changeStatus){
			
			if(currentProfile != null) {
			
				if (! (statusField . getText()) . equals("")) {
					
					database . getProfile(currentProfile) . setStatus(statusField . getText()) ; 
					canvas . displayProfile ( database . getProfile ( currentProfile ) ) ;
					canvas . showMessage (currentProfile + "\'s status has been updated.") ;
					
				} else {
					
					canvas . displayProfile ( database . getProfile ( currentProfile ) ) ;
					canvas . showMessage ("Please write something to update profile.") ;
				
				}
				
			} else {
				
				canvas .removeAll() ; 
				canvas . showMessage ("Please select a profile and then change the status.") ;
				
			}   
			
		} else if (source == changePicture || source == pictureField){
			
			GImage image = null;
			
			if (! (pictureField . getText() . equals("")) && currentProfile != null ){
				
				try {
					
					if (! (pictureField . getText() . equals("")) ) image = new GImage(pictureField . getText()); //Makes sure that the user enters text.
					
				} catch (ErrorException ex) {
				
					canvas . displayProfile ( database . getProfile ( currentProfile ) ) ;
					canvas . showMessage ("That image could not be opened, please specify another one.") ; //Message shown if no image could be found.
				
				}
				
				if(image != null){ //Updates the image if there is a valid file.
					
					database . getProfile (currentProfile) . setImage (image) ; 
					canvas . displayProfile ( database . getProfile ( currentProfile ) ) ; 
					canvas . showMessage (currentProfile + "\'s picture has been updated.") ; 
				
				}
				
			} else {
				
				canvas . removeAll() ;
				
				if (currentProfile == null){ //If no profile is chosen and the user did not type anything on the text field, they are prompted for a profile and a name of the picture.
					
					canvas . showMessage ("Please specify a profile and type the name of the image.") ;
				
				} else {
					
					canvas . showMessage ("Please type the name of the image.") ;
					
				}
				
			}
				
			
		} else if (source == addFriend || source == friendZone){ //Detects when the user wants to add a friend.
			
			if (currentProfile != null){
				
				if(database . containsProfile (friendZone . getText())){
					
					database . getProfile(currentProfile) . addFriend( friendZone . getText() ) ; //Friend added.
					database . getProfile( friendZone . getText() ) . addFriend(currentProfile) ; //Friend added both ways.
					canvas . displayProfile ( database . getProfile ( currentProfile ) ) ; 
					canvas . showMessage(currentProfile + " became friends with " + friendZone . getText() ) ;
					
				} else {
					
					canvas .removeAll() ; 
					canvas . showMessage("Please add a friend that has a profile already.") ; 
					
				}
				
			} else {
				
				canvas .removeAll() ; 
				canvas . showMessage("Please create or select a profile to add a friend.") ; 
				
			}
			
		}
		
    	
	}
    
    private String currentProfile ; //This string keeps track of the current profile being shown to the user.

    //Instance variables for interactors
    
    private JTextField nameField ; 
    private JTextField statusField ; 
    private JTextField pictureField ;
    private JTextField friendZone ; //This is the friend zone, where you add friends. ;)
    
    private JButton addButton ; 
    private JButton deleteButton ;
    private JButton lookUp ; 
    private JButton changeStatus ; 
    private JButton changePicture ; 
    private JButton addFriend ; 
   
    //Class instance variables
    
    private FacePamphletDatabase database ; 
    private FacePamphletCanvas canvas ; 
    
}
