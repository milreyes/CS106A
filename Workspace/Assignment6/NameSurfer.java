/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.program.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class NameSurfer extends Program implements NameSurferConstants {

/* Method: init() */
/**
 * This method has the responsibility for reading in the data base
 * and initializing the interactors at the top of the window.
 */
	public void init() {
		
		database = new NameSurferDataBase ("names-data.txt") ;
		
		addInteractors();
		addActionListeners();
		
		graph = new NameSurferGraph() ; 
		add ( graph ) ;
		
	}

/* Method: actionPerformed(e) */
/**
 * This class is responsible for detecting when the buttons are
 * clicked, so you will have to define a method to respond to
 * button actions.
 */
	
	private void addInteractors(){
		
		JLabel name = new JLabel("Name:");
		
		nameField = new JTextField(MAX_NAME);
		nameField . addActionListener(this) ; 
		
		graphButton = new JButton ("Graph") ; 
		clearButton = new JButton ("Clear") ; 
		
		//Adding the interactors in the right order.
		add (name, NORTH);
		add (nameField, NORTH) ; 
		add (graphButton, NORTH);
		add (clearButton, NORTH);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		
		Object source = e . getSource() ; 
		
		if( source == graphButton || source == nameField){
			
			String line =  nameField . getText() ; //Gets the text from the user.
			NameSurferEntry entry = database . findEntry ( line ) ;  //Compares the text to the database and returns the corresponding entry if it exists.
			if(entry != null) graph . addEntry(entry) ; //Adds the entry to the graph class so that it can be drawn.
			graph . update() ; //Updates the graph to show the new entry.
			
		} else if (source == clearButton){
			
			graph . clear() ; //Clears all the entries.
			graph . update() ; //Updates the canvas to show that it is empty.
		
		}
		
	}

	
	//Instance variables used.
	private JTextField nameField ; 
	private JButton graphButton ;
	private JButton clearButton ; 
	private NameSurferGraph graph ; 
	private NameSurferDataBase database ; 
	
}
