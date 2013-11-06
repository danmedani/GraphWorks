/**
 * <code>GenericDisplayPanel</code> draws a graph with its vertices reordered
 * so that the vertices of each connected component are drawn consecutively 
 * around the graph's circumference, and also paints each connected component
 * with a different color..
 *
 * @author Team 1
 * @version 1.0
 */

import java.awt.*;
import javax.swing.*;
import java.lang.Math.*;
import java.io.File;
import java.util.Random;

public class GenericDisplayPanel extends GraphDisplayPanel {
    
    /**
     * Order in which vertices must be drawn.     
     */
    private int[] vOrder;                 
    /**
     * Component Number of each vertex
     */
    private int[] cNums;
    
    /**
     * Our random number generator, which is used for making random colors.
     */
    private Random rand;

    /**
     * The array of colors representing the color of each connected component.
     */    
    private Color[] color;

    /**
     * Creates a new <code>GenericDisplayPanel</code> instance.
     *
     * @param e an <code>int</code> value
     * @param order an <code>int</code> value
     */
    public GenericDisplayPanel(int[][] e, int[] order){
	super(e,false);
	vOrder = order;

	generateComponentNumbers();
	adjustCon();

	this.XCENTER = 123;
	this.YCENTER = 105;
	this.RADIUS = 60;

	rand = new Random(255);
	
	for (int i=0; i < color.length; i++){
	    color[i] = new Color(rand.nextInt(256),
				 rand.nextInt(256),
				 rand.nextInt(256));
	}	
    }    

    /**
     * The <code>getComponents</code> method generates the array of
     * integers which represent the component number of each vertex.
     *
     */
    private void generateComponentNumbers() {
	cNums = new int[vOrder.length];
	int compNum = 1;
	
	for(int i = 0; i < vOrder.length; i ++) {
	    if((vOrder[i] - (compNum * 100)) > 0)
		compNum ++;
	    cNums[i] = compNum;
	}
	color = new Color[compNum+1];
    }

    
    /**
     * The <code>adjustCon</code> method adjusts the vertex numbers
     * so that each one returns to its initial value. ( for example 
     * the array [0 4 102 103 205] => [0 4 2 3 5]
     *
     * The 100 unit separations were used to determine the component
     * number of each vertex, and are no longer needed.
     *
     */
    private void adjustCon() {
	for(int i = 0; i < vOrder.length; i ++) {
	    while(vOrder[i] >= 0)
		vOrder[i] -= 100;
	    vOrder[i] += 100;
	}
    }
    
    /**
     * <code>paintComponent</code> draws the graph with vertices correctly
     * reordered and with different colors for each connected component..
     *
     * @param g a <code>Graphics</code> value
     */
    public void paintComponent(Graphics g) {
	g.setFont(new Font(g.getFont().getName(), Font.BOLD, 12));
	g.drawString("Generic Display", 80, 10);
	g.setFont(new Font(g.getFont().getName(), Font.PLAIN, 12));
		
	int largestComp = -1;
	for(int i = 0; i < cNums.length; i++) {
	    if(cNums[i] > largestComp) {
		largestComp = cNums[i];
	    }
	}
	if(largestComp == 1) { // Draw verts the same way.
	    g.setColor(Color.black);
	    for (int i=0; i < edges.length; i++){
		g.drawLine(getXCoor(edges[i][0]), getYCoor(edges[i][0]),
			   getXCoor(edges[i][1]), getYCoor(edges[i][1]));
	    }
	    
	    for (int i=0; i < numVertices; i++){
		g.fillOval(getXCoor(i)-3, getYCoor(i)-3, 7, 7);
		paintLabel(g,i);
	    }
	}
	else {
	    
	    for (int i=0; i < edges.length; i++){
		int vert1=0;
		int vert2=0;
		int position = -1;
		for (int j=0; j < vOrder.length; j++){
		    if (vOrder[j] == edges[i][0]){
			vert1 = j;
			position = j;
		    }
		    if (vOrder[j] == edges[i][1]){
			vert2 = j;	
			position = j;
		    }
		}	    
		
		g.setColor(color[cNums[position]]);	    
		g.drawLine(getXCoor(vert1), getYCoor(vert1),
			   getXCoor(vert2), getYCoor(vert2));
		
	    }	    
	    g.setColor(Color.black);
	    for (int i=0; i < numVertices; i++){
		g.fillOval(getXCoor(i)-3, getYCoor(i)-3, 7, 7);
		paintLabel(g,vOrder[i],i);
	    }
	    
	}
	
    }
    
    /**
     * <code>paintLabel</code> labels each vertex in the graph with its number.
     *
     * @param g a <code>Graphics</code> value
     * @param vNum vertex whose label is being printed
     * @param pos position at which label should be printed
     */
    private void paintLabel(Graphics g, int vNum, int pos){
	int rise = YCENTER - getYCoor(pos);
        int run = XCENTER - getXCoor(pos);
        g.drawString(Integer.toString(vNum), getXCoor(pos)-(run/3)-3,
		     getYCoor(pos)-(rise/3)+5);
    }
}