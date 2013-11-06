import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.Math.*;
import java.io.*;
import org.netbeans.lib.awtextra.*;


/**
 * <code>MCSTDisplayPanel</code> displays a MCST of a graph.
 *
 * @author 
 * @version 1.0
 */
public class MCSTDisplayPanel extends GraphDisplayPanel {

    private int[][] edges;              //Array of edges
    private int[][] treeEdges; 
    private JButton nextButton;
    private int currentEdge;
    private int currentPass;


    /**
     * Creates a new <code>MCSTDisplayPanel</code> instance.
     *
     * @param e 2-d edge array with graph data.
     * @param t 2-d edge array of tree edges.
     */
    public MCSTDisplayPanel(int[][] e, int[][] t){
	
	super(e,true);
	edges = e;
	treeEdges = t;
	currentEdge = -1;
	currentPass = -1;
	
	this.XCENTER = 100;
	this.YCENTER = 105;
	this.RADIUS = 63;
    } 
    
    /**
     * <code>paintComponent</code> draws the graph in the panel.
     *
     * @param g a <code>Graphics</code> value
     */
    public void paintComponent(Graphics g) {

	g.setFont(new Font(g.getFont().getName(), Font.BOLD, 12));
	g.drawString("Minimal Cost Spanning Tree", 35, 10);
	g.setFont(new Font(g.getFont().getName(), Font.PLAIN, 12));

	if(currentEdge == -1) {	  

	    for (int i=0; i < numVertices; i++){
		g.fillOval(getXCoor(i)-3, getYCoor(i)-3, 7, 7);
		paintLabel(g,i);
	    }
	    
	    this.setLayout(new AbsoluteLayout());
	    nextButton = new JButton();
	    nextButton.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
			if (treeEdges[0].length == 3){
			    currentEdge ++;
			} else {
			    currentPass ++;
			    if (currentEdge == -1){
				currentEdge++;
			    }
			}
			repaint();
		    }
		});
	    nextButton.setFont(new Font("sansserif",Font.BOLD,10));
	    nextButton.setText("Next");
	    nextButton.setBounds(180,25,70,20);
	 
	    this.add(nextButton, new AbsoluteConstraints(180,25,70,20));
	  
	  	    
	    Stroke drawingStroke = new BasicStroke(1,
						   BasicStroke.CAP_BUTT,
						   BasicStroke.JOIN_BEVEL, 
						   0, new float[]{9}, 0);
	    
	    Graphics2D g2d = (Graphics2D) g;
	    for(int i = 0; i < edges.length; i ++) {
		Line2D line = new Line2D.Double(getXCoor(edges[i][0]),
						getYCoor(edges[i][0]),
						getXCoor(edges[i][1]),
						getYCoor(edges[i][1]));
		
		g2d.setStroke(drawingStroke);
		g2d.draw(line);
	    }
	    
	}
	else {
	    if (treeEdges[0].length == 3){
		if(currentEdge < treeEdges.length) {
		    Stroke drawingStroke = new 
			BasicStroke(1,BasicStroke.CAP_BUTT,
				    BasicStroke.JOIN_BEVEL, 
				    0, new float[]{9}, 0);
		    
		    Graphics2D g2d = (Graphics2D) g;	
		    
		    g2d.setStroke(new BasicStroke(1));		
		    g.drawLine(getXCoor(treeEdges[currentEdge][0]),
			       getYCoor(treeEdges[currentEdge][0]),
			       getXCoor(treeEdges[currentEdge][1]),
			       getYCoor(treeEdges[currentEdge][1]));
		    drawWeight(g, treeEdges[currentEdge][0], 
			       treeEdges[currentEdge][1], 
			       treeEdges[currentEdge][2]);
		    
		    g.setColor(Color.black);		
		    
		}
	    } else {

		Stroke drawingStroke = new 
		    BasicStroke(1,BasicStroke.CAP_BUTT, 
				BasicStroke.JOIN_BEVEL, 
				0, new float[]{9}, 0);
		Graphics2D g2d = (Graphics2D) g;	
		g.setColor(Color.black);
		g2d.setStroke(new BasicStroke(1));

		if (currentPass <= treeEdges[treeEdges.length-1][3]){
		    while (treeEdges[currentEdge][3] == currentPass){
			g.drawLine(getXCoor(treeEdges[currentEdge][0]),
				   getYCoor(treeEdges[currentEdge][0]),
				   getXCoor(treeEdges[currentEdge][1]),
				   getYCoor(treeEdges[currentEdge][1]));
			drawWeight(g, treeEdges[currentEdge][0],
				   treeEdges[currentEdge][1], 
				   treeEdges[currentEdge][2]);
			currentEdge++;
			if (currentEdge >= treeEdges.length){
			    break;
			}
		    }
		}
	    }
	}
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
}
