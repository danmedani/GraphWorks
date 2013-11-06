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
import java.util.LinkedList;
import java.io.File;

public class GraphDisplayPanel extends JPanel {
 
    protected int[][] edges;                // Array of edges
    protected int numVertices;              // Number of vertices
    protected int XCENTER = 140;        // Horizontal center of panel
    protected int YCENTER = 140;        // Vertical center of panel
    protected int RADIUS = 75;          // Graph radius      
    protected boolean titleVisible;
    protected int[] vOrder;
    private boolean isWeighted;


    /**
     * Creates a new <code>GraphDisplayPanel</code> instance.
     *
     * @param e a 2-d array of edges
     */
    public GraphDisplayPanel(int[][] e,boolean displayWeights){

	if(e[0].length > 2 && displayWeights) {
	    isWeighted = true;
	} 
	else {
	    isWeighted = false;
	}

	edges = e;
	titleVisible = true;

	int maxVertex = 0;
	for (int i=0; i < edges.length; i++){
	    int j = edges[i][0];
	    int k = edges[i][1];
	    
	    if (j > k){
		if (j > maxVertex) {
		    maxVertex = j;
		}
	    } else {
		if (k > maxVertex) {
		    maxVertex = k;
		}
	    }
	}
	
	numVertices = maxVertex+1;

	vOrder = new int[numVertices];
	for(int i = 0; i < numVertices; i++) {
	    vOrder[i] = i;
	}
	    
    }

    public void findHams() {
	HamiltonianFinder hFind = new HamiltonianFinder(edges, numVertices);
	LinkedList<Integer> list = hFind.findCycle();
	if(list != null) {
	    for(int i = 0; i < list.size(); i++) {
		vOrder[i] = list.get(i);
	    }
	}
    }

    public void setDimensions(int xCenter, int yCenter, int radius) {
	RADIUS = radius;
	XCENTER = xCenter;
	YCENTER = yCenter;
    }

    public void setTitleVisible(boolean value) {
	this.titleVisible = value;
	repaint();
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

	if(titleVisible) {
	    g.setFont(new Font(g.getFont().getName(), Font.BOLD, 12));
	    g.drawString("Original Graph", 90, 25);
	    g.setFont(new Font(g.getFont().getName(), Font.PLAIN, 12));
	}
	for (int i=0; i < numVertices; i++){
	    g.fillOval(getXCoor(vOrder[i])-3, getYCoor(vOrder[i])-3, 7, 7);
	    paintUOLabel(g,vOrder[i], i);
	}
	
	for (int i=0; i < edges.length; i++){
	    g.drawLine(getXCoor(positionOf(edges[i][0])), 
		       getYCoor(positionOf(edges[i][0])),
		       getXCoor(positionOf(edges[i][1])), 
		       getYCoor(positionOf(edges[i][1])));
	    if(isWeighted) {
		drawWeight(g,indexOf(edges[i][0]), indexOf(edges[i][1]),
			   edges[i][2]);
	    }
	}
	g.setColor(Color.black);		
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

	// Label Centering Information
	int xOffSet, yOffSet;
	if(vNum > 9) {
	    xOffSet = -6;
	}
	else {
	    xOffSet = -3;
	}
	yOffSet = 5;

	int rise = YCENTER - getYCoor(vPos);
        int run = XCENTER - getXCoor(vPos);
        g.drawString(Integer.toString(vNum), getXCoor(vPos)-(run/4) + xOffSet,
		     getYCoor(vPos)-(rise/4) + yOffSet);
    }

    private int positionOf(int vNum) {
	for(int i = 0; i < numVertices; i ++) {
	    if(vOrder[i] == vNum)
		return i;
	}
	return -1;
    }
    

    /**
     * <code>getXCoor</code> gets the x-coordinate of the location to draw
     * vertex <code>vNum</code>.
     *
     * @param vNum vertex whose coordinate is being calculated
     */
    protected int getXCoor(int vNum){
	return XCENTER + 
	    ((int)(Math.sin((2*Math.PI)*vNum/numVertices)*RADIUS));
    }
    
    /**
     * <code>getYCoor</code> gets the y-coordinate of the location to draw
     * vertex <code>vNum</code>.
     *
     * @param vNum vertex whose coordinate is being calculated
     */
    protected int getYCoor(int vNum){
	return YCENTER + 
	    ((int)(Math.cos((2*Math.PI)*vNum/numVertices)*-RADIUS));
    }



  /**
     * <code>drawWeight</code> paints the weight of an edge on the graph.
     *
     * @param g a <code>Graphics</code> value
     * @param v1 vertex 1
     * @param v2 vertex 2
     * @param weight edge weight
     */
    private void drawWeight(Graphics g, int v1, int v2, int weight){

	int temp;
	if(getXCoor(v2) < getXCoor(v1)) {
	    temp = v2;
	    v2 = v1;
	    v1 = temp;
	}
	
	int xMid = ((getXCoor(v1)+getXCoor(v2))/2);
	int yMid = ((getYCoor(v1)+getYCoor(v2))/2);
	double slope;

	if((getXCoor(v2) - getXCoor(v1)) != 0) {
	    slope = ( (double)(getYCoor(v2) - getYCoor(v1)) / 
		      (double)(getXCoor(v2) - getXCoor(v1)) );
	}
	else {
	    slope = 1000;
	}

	double perpSlope;
	if(slope != 0) {
	    perpSlope = -1 * (1/slope);
	}
	else {
	    if(yMid < YCENTER) {
		perpSlope = -1000;
 	    }
	    else {
		perpSlope = 1000;
	    }
	}
	
	int newX, newY;
	if(xMid >= XCENTER) {
	    newX = xMid + (int)(12.0 * (Math.cos(Math.atan(perpSlope)))); 
	    newY = yMid + (int)(12.0 * (Math.sin(Math.atan(perpSlope)))); 
	}
	else {
	    newX = xMid - (int)(12.0 * (Math.cos(Math.atan(perpSlope)))); 
	    newY = yMid - (int)(12.0 * (Math.sin(Math.atan(perpSlope))));   
	}

	// Get point half way between old and new points
	int midWayX = (xMid + newX) / 2;
	int midWayY = (yMid + newY) / 2;

	g.drawLine(xMid,yMid,midWayX,midWayY);
	g.setFont(new Font(g.getFont().getName(), Font.BOLD, 12));
	if(weight < 10) {
	    newX -=3;
	}
	else {
	    newX -= 6;
	}
	newY +=5;
	g.setColor(Color.BLUE);
	g.drawString(Integer.toString(weight), newX, newY);
	g.setColor(Color.BLACK);
	g.setFont(new Font(g.getFont().getName(), Font.PLAIN, 12));
    }


    private int indexOf(int v) {
	for(int i = 0; i < vOrder.length; i ++) {
	    if(vOrder[i] == v) {
		return i;
	    }
	}
	return -1;
    }





}