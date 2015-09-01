/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes
 * or the window is resized.
 */

import acm.graphics.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class NameSurferGraph extends GCanvas
	implements NameSurferConstants, ComponentListener {

	/**
	* Creates a new NameSurferGraph object that displays the data.
	*/
	public NameSurferGraph() {
		
		addComponentListener(this);
		defineBasicGrid(); //This method adds the basic grid on the screen.
	}
	
	
	/**
	* Clears the list of name surfer entries stored inside this class.
	*/
	
	public void clear() {
		
		entries = new ArrayList<NameSurferEntry>() ; //Resets the entries array list.
		
	}
	
	
	/* Method: addEntry(entry) */
	/**
	* Adds a new NameSurferEntry to the list of entries on the display.
	* Note that this method does not actually draw the graph, but
	* simply stores the entry; the graph is drawn by calling update.
	*/
	public void addEntry(NameSurferEntry entry) {
		
		if (! entries . contains (entry) ){//If the array does not contain the entry it will add it.
		
			entries . add (entry) ;
		
		}
		
	}
	
	
	/**
	* Updates the display image by deleting all the graphical objects
	* from the canvas and then reassembling the display according to
	* the list of entries. Your application must call update after
	* calling either clear or addEntry; update is also called whenever
	* the size of the canvas changes.
	*/
	public void update() {
		
		removeAll() ; //First step is to remove anything that is being shown on the screen.
		defineBasicGrid() ; //Then the grid is defined according to the screen dimensions.
		addBasicGrid() ; //The basic grid is added.
		drawEntries() ; //And the entries saved in the entries array list is drawn.
		
	}
	
	//This method defines the basic grid comprised of vertical decade lines, the two horizontal lines of the top and bottom and the decade tags.
	private void defineBasicGrid () {
		
		defineVerticalLines();
		defineHorizontalLines();
		defineDecadeLabels();
		
	}
	
	private void defineVerticalLines(){
		
		verticalLines[0] = new GLine(X_1900 , 0 , X_1900 , getHeight() ) ; 
		
		for(int i = 1 ; i < NDECADES ; i++){
			
			verticalLines[i] = new GLine (X_1900 + i * (getWidth()) / NDECADES , 0 , 
										  X_1900 + i * (getWidth()) / NDECADES , getHeight() ) ; 
			
		}
		
	}
	
	private void defineHorizontalLines(){
		
		Y_TOP = getHeight() * 0.04 ; 
		
		horizontalLines[0] = new GLine( 0 , Y_TOP , getWidth() , Y_TOP ) ; 
		horizontalLines[1] = new GLine ( 0 , getHeight() - Y_TOP , getWidth() , getHeight() - Y_TOP ) ; //The bottom line is offset from the bottom just like the top line from the top.
		
	}
	
	//This method defines the decade labels and assigns the location to them.
	private void defineDecadeLabels(){
		
		LABEL_OFFSET = getHeight() * 0.02 ;
		
		for(int i = 0 ; i < NDECADES ; i++){
		
			String lineDecade = "" ; 
			int decade = STARTING_YEAR + 10 * i ; 
			lineDecade += decade ; 
			decadeLabels[i] = new GLabel(lineDecade , ( X_1900 + ( i * (getWidth()) / NDECADES ) + LABEL_OFFSET / 2 ) , getHeight() - LABEL_OFFSET );
		
		}	
		
	}
	
	//This method adds the basic grid to the canvas after being defined.
	
	private void addBasicGrid() {
		
		addVerticalLines() ; 
		addHorizontalLines() ; 
		addDecadeLabels() ; 
		
	}
	
	private void addVerticalLines(){
		
		for(int i = 0 ; i < NDECADES ; i++){
			
			add(verticalLines[i]) ; 
			
		}
		
	}
	
	private void addHorizontalLines(){
		
		for(int i = 0 ; i < N_HORIZONTAL_LINES ; i++){
			
			add(horizontalLines[i]) ; 
			
		}
		
	}
	
	private void addDecadeLabels(){
		
		for (int i = 0 ; i < NDECADES ; i++){
			
			add ( decadeLabels[i] ) ; 
			
		}
		
	}
	
	//This method draws the entries that are kept in the entries array list.
	private void drawEntries(){
		
	
		for (int entry = 0 ; entry < entries . size() ; entry++ ){
			
			double[] ranksYCoords = new double[NDECADES]  ;
			
			ranksYCoords = defineYCoords( ranksYCoords , entry ) ; //This method defines the Y coords for the graph.
			drawNameGraph( ranksYCoords , entry ) ;  //This method draws the lines corresponding to the entry.
			drawNameTags(ranksYCoords , entry ) ;  //This method adds the labels right next to the points of the graph.
		}
		
	}
	
	//This key method defines the points that the lines must connect to show the graph for each entry.
	private double[] defineYCoords( double[] ranksYCoords , int entry ){
		
		Y_TOP = getHeight() * 0.04 ; 
		
		for (int i = 0 ; i < NDECADES ; i++ ) {
			
			ranksYCoords[i] = entries . get( entry ) . getRank( i ) ; //Gets the rank of the entry . 
			
			if(ranksYCoords[i] == 0) { //This is to make sure the graph looks the way it should.
				
				ranksYCoords[i] = MAX_RANK ; //When the rank is equal to zero, it will appear at the bottom.
				
			}
		
		}
	
		
		return ranksYCoords ; 
	}
	
	//this method draws the lines of the graph after the points have been defined by the previous method.
	private void drawNameGraph (double[] ranksYCoords , int entry ) {
		
		Color color = setColor(entry) ;  //Defines the color to be used.
	
		
		for (int i = 1 ; i < NDECADES ; i++ ) {
			
			Y_TOP = getHeight() * 0.04 ; 
			sizeFactor = (getHeight() - 2 * Y_TOP) / MAX_RANK ; //this is a factor used for making the graph proportional to the screen.
			GLine line ; 
				 
			line = new GLine( ( X_1900 + (i - 1) * (getWidth()) / NDECADES ) , ( (ranksYCoords[ i- 1 ] * sizeFactor)   + Y_TOP) , 
							  ( X_1900 + (i) * (getWidth()) / NDECADES ) , ( ( ranksYCoords[ i ] * sizeFactor)   + Y_TOP) ) ; 
		
			
			line . setColor (color) ; 
			add(line) ; 
		
		}
		
	}
	
	//This method draws the name tags close to the rank for each decade to indicate the rank to the user.
	private void drawNameTags( double[] ranksYCoords , int entry ) {
		
		Color color = setColor(entry) ;  //Defines the color to be used.
		String name = "" ; 
		
		for (int i = 0 ; i < NDECADES ; i++ ) {
			
			name = entries . get(entry) . getName() ; 
			
			if(ranksYCoords[i] == MAX_RANK){
			
				name += " *" ; //When the rank is equal to zero the name gets an asterisk.
			
			} else {
			
				name += " " + entries . get(entry) . getRank(i) ; 
			
			}
			
			Y_TOP = getHeight() * 0.04 ;
			LABEL_OFFSET = 5 ;
			sizeFactor = (getHeight() - 2 * Y_TOP) / MAX_RANK ; //this is a factor used for making the graph proportional to the screen.
			GLabel nameLabel = new GLabel (name) ;
			nameLabel . setColor(color) ; 
			add ( nameLabel , (X_1900 + i * (getWidth()) / NDECADES + LABEL_OFFSET ) , ranksYCoords[i] * sizeFactor + Y_TOP - LABEL_OFFSET ) ;
	
		}
		
	}
	
	//This method defines the color to be used depending on which entry is it. The colors repeat every 4 entries.
	
	private Color setColor (int entry) {
		
		
		Color color = Color . yellow ; //Defines the color to be used.
		
		switch (entry % 4) {
		
		case 0: color = Color. black ; break ; 
		case 1: color = Color. red ; break ; 
		case 2: color = Color . blue ; break ; 
		case 3: color = Color . magenta ; break ; 
		
		}
		
		return color ; 
	}
	
	/* Implementation of the ComponentListener interface */

	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) { update(); }
	public void componentShown(ComponentEvent e) { }
	
	//Instance variables used for graphing
	
	private ArrayList<NameSurferEntry> entries = new ArrayList<NameSurferEntry>() ; //This array list stores the different entries provided by the user.
	private GLine[] verticalLines = new GLine[NDECADES] ; 
	private GLine[] horizontalLines = new GLine[ N_HORIZONTAL_LINES ] ; 
	private GLabel[] decadeLabels = new GLabel[NDECADES] ; 
	private double Y_TOP ; //Offset from top of the canvas.
	private double LABEL_OFFSET  ; //Offset used to add the labels.
	private double sizeFactor ; //this is a factor used for making the graph proportional to the screen.
	
	
	//Constants used for graph
	
	private static final int STARTING_YEAR = 1900 ; 
	private static final double X_1900 = 1 ; //This is the coordinates for the first vertical line.
	private static final int N_HORIZONTAL_LINES = 2 ; //Top and bottom lines.
	
	
	
	



}
