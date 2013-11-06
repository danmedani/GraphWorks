import java.awt.*;
import javax.swing.*;
import java.lang.Math.*;
import java.io.File;

public class WeightedGraphDisplayPanel extends JPanel {
 
    protected int[][] edges;                // Array of edges
    protected int numVertices;              // Number of vertices
    protected int XCENTER = 140;        // Horizontal center of panel
    protected int YCENTER = 140;        // Vertical center of panel
    protected int RADIUS = 75;          // Graph radius      

    /**
     * Creates a new <code>GraphDisplayPanel</code> instance.
     *
     * @param e a 2-d array of edges
     */
    public WeightedGraphDisplayPanel(int[][] e){
	
	edges = e;
	
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
	g.drawString("Original Graph", 90, 25);
	g.setFont(new Font(g.getFont().getName(), Font.PLAIN, 12));
	for (int i=0; i < numVertices; i++){
	    g.fillOval(getXCoor(i)-3, getYCoor(i)-3, 7, 7);
	    paintLabel(g,i);
	}
	
	for (int i=0; i < edges.length; i++){
	    g.drawLine(getXCoor(edges[i][0]), getYCoor(edges[i][0]),
		       getXCoor(edges[i][1]), getYCoor(edges[i][1]));
	    drawWeight(g,edges[i][0],edges[i][1], edges[i][2]);
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
        g.drawString(Integer.toString(vNum), getXCoor(vNum)-(run/4)+ xOffSet,
		     getYCoor(vNum)-(rise/4) + yOffSet);
    }    

    /**
     * <code>getXCoor</code> gets the x-coordinate of the location to draw
     * vertex <code>vNum</code>.
     *
     * @param vNum vertex whose coordinate is being calculated
     */
    protected int getXCoor(int vNum){	
	return XCENTER + ((int)(Math.sin((2*Math.PI)*
					 vNum/numVertices)*RADIUS));
    }
    
    /**
     * <code>getYCoor</code> gets the y-coordinate of the location to draw
     * vertex <code>vNum</code>.
     *
     * @param vNum vertex whose coordinate is being calculated
     */
    protected int getYCoor(int vNum){	
	return YCENTER + ((int)(Math.cos((2*Math.PI)*
					 vNum/numVertices)*-RADIUS));
    }
    
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
	
	if(weight < 10) {
	    newX -=3;
	}
	else {
	    newX -=6;
	}
	newY +=5;
	g.setColor(Color.BLUE);
	g.setFont(new Font(g.getFont().getName(), Font.BOLD, 12));
	g.drawString(Integer.toString(weight), newX, newY);
	g.setColor(Color.BLACK);

    }   
}