import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.Math.*;
import java.io.*;
import org.netbeans.lib.awtextra.*;


/**
 * <code>FleuryDisplayPanel</code> displays a after performing a given search 
 * on it.
 *
 * @author 
 * @version 1.0
 */
public class FleuryDisplayPanel extends JPanel 
    implements ActionListener {

    private int[][] edges;              //Array of edges
    private int[][][] treeEdges; 
    private JButton nextButton;
    private JComboBox combo;
    private int currentEdge;
    private int currentStartVertex;
    private boolean hasEulerCircuit; 
    private int numVertices;              // Number of vertices
    private int XCENTER;        // Horizontal center of panel
    private int YCENTER ;        // Vertical center of panel
    private int RADIUS;          // Graph radius    
    private boolean badEdgeIncurred;


    /**
     * Creates a new <code>FleuryDisplayPanel</code> instance.
     *
     * @param e 2-d edge array with graph data.
     * @param t 2-d edge array of tree edges.
     */
    public FleuryDisplayPanel(int[][] e, int[][][] t){
	
	edges = e;
	treeEdges = t;
	currentEdge = -1;
	currentStartVertex = 0;
	
	if(t[0][0][0] == -1) {
	    hasEulerCircuit = false;
	}
	else {
	    hasEulerCircuit = true;
	}

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
	badEdgeIncurred = false;
	numVertices = maxVertex+1;

	this.XCENTER = 85;
	this.YCENTER = 110;
	this.RADIUS = 60;
	
    } 
    
    public void actionPerformed(ActionEvent e) {
	if(e.getModifiers() != 0) { // Then they changed it!
	    JComboBox j = (JComboBox)e.getSource();
	    int vert = j.getSelectedIndex();
	    currentStartVertex = vert;
	    currentEdge = -1;
	    repaint();
	}
    }

    /**
     * <code>paintComponent</code> draws the graph in the panel.
     *
     * @param g a <code>Graphics</code> value
     */
    public void paintComponent(Graphics g) {
	super.paintComponent(g);

	if(hasEulerCircuit) {
	    g.setFont(new Font(g.getFont().getName(), Font.BOLD, 12));
	    g.drawString("Euler Circuit", 48, 10);
	    g.setFont(new Font(g.getFont().getName(), Font.PLAIN, 12));
	    g.drawString("Starting Vertex", 160, 28);
	    
	    for (int i=0; i < numVertices; i++){
		g.fillOval(getXCoor(i)-3, getYCoor(i)-3, 7, 7);
		paintLabel(g,i);
	    }
	    
	    this.setLayout(new AbsoluteLayout());
	    nextButton = new JButton();
	    nextButton.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
			if(currentEdge < treeEdges[currentStartVertex]
			   .length-1) {
			    if(!badEdgeIncurred) {
				currentEdge ++;
			    }
			    repaint();
			}
		    }
		});	    
	    
	    nextButton.setFont(new Font("sansserif",Font.BOLD,10));
	    nextButton.setText("Next");
	    nextButton.setBounds(175,68,70,20);
	    
	    combo = new JComboBox();
	    combo.setFont(new Font("Tahoma",Font.BOLD,10));
	    combo.addActionListener(this);
	    for(int i = 0; i < numVertices; i++) {
		combo.addItem(new Integer(i));
	    }
	    combo.setBounds(203,35,30,30);
	    
	    this.add(combo, new AbsoluteConstraints(203,35,30,30)); 
	    this.add(nextButton, new AbsoluteConstraints(180,35,70,20));
	    
	    
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
		g2d.setStroke(new BasicStroke(1));
	    }
	    
	    if(currentEdge < treeEdges[currentStartVertex].length && 
	       currentEdge >= 0) {
		for(int i = 0; i <= currentEdge; i ++) {
		    drawingStroke = new 
			BasicStroke(1,BasicStroke.CAP_BUTT,
				    BasicStroke.JOIN_BEVEL, 
				    0, new float[]{9}, 0);
		    
		    g2d = (Graphics2D) g;	
		    
		    g2d.setStroke(new BasicStroke(2));		
		    g.drawLine(getXCoor(treeEdges[currentStartVertex]
					[i][0]),
			       getYCoor(treeEdges[currentStartVertex]
					[i][0]),
			       getXCoor(treeEdges[currentStartVertex]
					[i][1]),
			       getYCoor(treeEdges[currentStartVertex]
					[i][1]));		
		}
		
		if(currentEdge != (treeEdges[currentStartVertex].length-1)) {
		    if(treeEdges[currentStartVertex][currentEdge][2] != -1) {
			if(!badEdgeIncurred) {
			    
			    g.setColor(Color.red);
			    g.drawLine(getXCoor(treeEdges[currentStartVertex]
						[currentEdge][1]),
				       getYCoor(treeEdges[currentStartVertex]
						[currentEdge][1]),
				       getXCoor(treeEdges[currentStartVertex]
						[currentEdge][2]),
				       getYCoor(treeEdges[currentStartVertex]
						[currentEdge][2])); 
			    
			    badEdgeIncurred = true;
			}
			else {
			    
			    g.setColor(Color.green);
			    g.drawLine(getXCoor(treeEdges[currentStartVertex]
						[currentEdge+1][0]),
				       getYCoor(treeEdges[currentStartVertex]
						[currentEdge+1][0]),
				       getXCoor(treeEdges[currentStartVertex]
						[currentEdge+1][1]),
				       getYCoor(treeEdges[currentStartVertex]
						[currentEdge+1][1])); 

			    badEdgeIncurred = false;
			}

		    }
		    else {
			g.setColor(Color.green);
			g.drawLine(getXCoor(treeEdges[currentStartVertex]
					    [currentEdge+1][0]),
				   getYCoor(treeEdges[currentStartVertex]
					    [currentEdge+1][0]),
				   getXCoor(treeEdges[currentStartVertex]
					    [currentEdge+1][1]),
				   getYCoor(treeEdges[currentStartVertex]
					    [currentEdge+1][1])); 
		    }

		}
	    }
	    g.setColor(Color.black);	 	    
	}
	else {
	    g.setFont(new Font(g.getFont().getName(), Font.BOLD, 12));
	    g.drawString("This graph has no Euler Circuit!", 30, 80);
	    g.setFont(new Font(g.getFont().getName(), Font.PLAIN, 12));
	}
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
}