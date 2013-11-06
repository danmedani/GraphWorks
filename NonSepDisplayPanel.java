/**
 * <code>GraphDisplayPanel</code> takes a 2-d array of edges and draws
 * the graph represented by that edge array.
 *
 * @author Team 1
 * @version 1.0
 */

import java.awt.*;
import javax.swing.*;
import java.lang.Math.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class NonSepDisplayPanel extends JPanel {
 
    protected int[][] edges;                // Array of edges
    protected int maxComp;              // Number of nonseparable components
    protected int XCENTER = 125;        // Horizontal center of panel
    protected int YCENTER = 110;        // Vertical center of panel
    protected int RADIUS = 60;          // Graph radius      
    protected int COMPRADIUS = 18;
    
    private Random rand;
    private Color[] color;
 
   /**
     * Order in which vertices must be drawn.     
     */
    private int[] vOrder; 


    /**
     * Creates a new <code>GraphDisplayPanel</code> instance.
     *
     * @param e a 2-d array of edges
     */
    public NonSepDisplayPanel(int[][] e){
	edges = e;
	maxComp = edges[edges.length-1][2];

	
	// Init the color array
	color = new Color[maxComp+1];
	rand = new Random(2554);	
	for (int i=0; i < color.length; i++){
	    color[i] = new Color(rand.nextInt(256),
				 rand.nextInt(256),
				 rand.nextInt(256));
	}


	NonSepHelper nHelp = new NonSepHelper(edges);
	vOrder = nHelp.produceOrder();


    }

    /**
     * <code>paintComponent</code> draws the graph specified by 
     * <code>edges</code>, with the vertices evenly spaced around the 
     * circumference of a circle..
     *
     * @param g a <code>Graphics</code> value
     */
    public void paintComponent(Graphics g) {

	super.paintComponent(g);


	g.setFont(new Font(g.getFont().getName(), Font.BOLD, 12));
	g.drawString("Nonseparable Components", 50, 15);
	g.setFont(new Font(g.getFont().getName(), Font.PLAIN, 12));


	for (int i=0; i < vOrder.length; i++){
	    g.fillOval(getXCoor(vOrder[i])-3, getYCoor(vOrder[i])-3, 7, 7);
	    paintUOLabel(g,vOrder[i], i);
	}
	
	for (int i=0; i < edges.length; i++){
	    
	    g.setColor(color[edges[i][2]]);
	    
	    g.drawLine(getXCoor(positionOf(edges[i][0])), 
		       getYCoor(positionOf(edges[i][0])),
		       getXCoor(positionOf(edges[i][1])), 
		       getYCoor(positionOf(edges[i][1])));
	}

	g.setColor(Color.black);

    }

    private int numVertsInComp(int cNum) {
	int retVal = 0;
	for(int i = 0; i < edges.length; i++) {
	    if(edges[i][2] == cNum) {
		retVal ++;
	    }
	}
	return retVal;
    }
      
    private int vPosNum(int[] corr, int vNum) {
	int retVal = -1;
	for(int i = 0; i < corr.length; i++) {
	    if(corr[i] == vNum) {		retVal = i;
	    }
	}
	return retVal;
    }

    private boolean isArticulationPoint(int vNum) {
	int cNum = -1; 
	for(int i = 0; i < edges.length; i++) {
	    if(edges[i][0] == vNum || edges[i][1] == vNum) {
			if(cNum == -1) { // This is our first
		    cNum = edges[i][2];
		}
		else if(cNum != edges[i][2]) {
		    return true;
		}

				
	    }
	}
	return false;
    }

    /**
     * <code>getXCoor</code> gets the x-coordinate of the location to draw
     * vertex <code>vNum</code>.
     *
     * @param vNum vertex whose coordinate is being calculated
     */
    protected int getXCoor(int vNum){
	
	return XCENTER + ((int)(Math.cos((2*Math.PI)*vNum/vOrder.length)*
				RADIUS));
    }
    
    /**
     * <code>getYCoor</code> gets the y-coordinate of the location to draw
     * vertex <code>vNum</code>.
     *
     * @param vNum vertex whose coordinate is being calculated
     */
    protected int getYCoor(int vNum){
	return YCENTER + ((int)(Math.sin((2*Math.PI)*vNum/vOrder.length)*
				RADIUS));
    }


    /**
     * <code>paintLabel</code> labels each vertex in the graph with its number.
     *
     * @param g a <code>Graphics</code> value
     * @param vNum vertex whose label is being printed
     */
    protected void paintLabel(Graphics g, int vNum){

	// Label Centering Information
	int xOffSet, yOffSet;
	if(vNum > 9) {
	    xOffSet = -6;
	}
	else {
	    xOffSet = -3;
	}
	yOffSet = 5;

	int rise = YCENTER - getYCoor(vNum);
        int run = XCENTER - getXCoor(vNum);
	g.drawString(Integer.toString(vNum), getXCoor(vNum)-(run/4) + xOffSet,
		     getYCoor(vNum)-(rise/4) + yOffSet);
    }
      
    private void paintUOLabel(Graphics g, int vNum, int vPos){

	int cutOffset;
	if(isArticulationPoint(vNum)) {
	    g.setFont(new Font(g.getFont().getName(), Font.BOLD, 15));
	    cutOffset = 4;
	}
	else {
	    cutOffset = 0;
	}


	// Label Centering Information
	int xOffSet, yOffSet;
	if(vNum > 9) {
	    xOffSet = -6;
	}
	else {
	    xOffSet = -3;
	}
	xOffSet -= cutOffset;
	yOffSet = 5;

	int rise = YCENTER - getYCoor(vPos);
        int run = XCENTER - getXCoor(vPos);
        g.drawString(Integer.toString(vNum), getXCoor(vPos)-(run/4) + xOffSet,
		     getYCoor(vPos)-(rise/4) + yOffSet);
	g.setFont(new Font(g.getFont().getName(), Font.PLAIN, 12));
    }

    private int positionOf(int vNum) {
	for(int i = 0; i < vOrder.length; i ++) {
	    if(vOrder[i] == vNum)
		return i;
	}
	return -1;
    }


   
}