/**
 * <code>WeightedEdgeDisplayPanel</code> extends JPanel and 
 * printss the number of 
 * vertices, the number of edges, and lists the edges of a given graph.
 *
 * @author Team 1
 * @version 1.0
 */

import java.awt.*;
import javax.swing.*;

public class WeightedEdgeDisplayPanel extends JPanel {
 
    protected int[][] edges;                // Array of edges
    protected int numVertices;              // Number of vertices       

   
    /**
     * <code>WeightedEdgeDisplayPanel</code> creates a new instance of 
     * this class and 
     * determines the number of vertices based on the array of edges.
     *
     * @param e a 2-d array of edges
     */
    public WeightedEdgeDisplayPanel(int[][] e){
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
     * <code>paintComponent</code> outputs the number of vertices, number of 
     * edges, and list of edges to the panel.
     *
     * @param g a <code>Graphics</code> value
     */
    public void paintComponent(Graphics g) {
	super.paintComponent(g);
	g.setFont(new Font(g.getFont().getName(), Font.BOLD, 12));
	g.drawString("Edge Information", 75, 10);
	g.setFont(new Font(g.getFont().getName(), Font.PLAIN, 12));
	g.drawString("# Vertices: " + numVertices + 
		     "       # Edges: " + edges.length,45, 35);
	int xC = 40;
	int yC = 60;
	
	String[] str = new String[(int)(Math.ceil((double)edges.length / 
						  5.0))];
	int strLine = 0;

	for(int i = 0; i < str.length; i++) // Init
	    str[i] = "";

	for(int i = 0; i < edges.length; i ++) {
	    str[strLine] += "(" + edges[i][0] + "," + edges[i][1] + ","+
		edges[i][2]+") ";

	    if(i%5 == 4)
		strLine ++;
	}

	for(int i = 0; i < str.length; i++) {
	    g.drawString(str[i],xC,yC);
	    yC += 15;
	}
    }
    
}