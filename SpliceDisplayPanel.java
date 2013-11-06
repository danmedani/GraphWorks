import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.Math.*;
import java.io.*;
import org.netbeans.lib.awtextra.*;
import java.util.Random;


/**
 * <code>SpliceDisplayPanel</code> displays a after performing a given search 
 * on it.
 *
 * @author 
 * @version 1.0
 */
public class SpliceDisplayPanel extends JPanel 
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
    private int[] componentList;
    private boolean circuitWalk;
    private int[] vOrder;
    private int[] compNum;

    /**
     * Our random number generator, which is used for making random colors.
     */
    private Random rand;

    /**
     * The array of colors representing the color of each connected component.
     */    
    private Color[] color;


    /**
     * Creates a new <code>SpliceDisplayPanel</code> instance.
     *
     * @param e 2-d edge array with graph data.
     * @param t 2-d edge array of tree edges.
     */
    public SpliceDisplayPanel(int[][] e, int[][][] t) {
	    
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

	circuitWalk = false;
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

	this.XCENTER = 85;
	this.YCENTER = 110;
	this.RADIUS = 60;

	// Find the dimensions of the 2D color array
	int largestComp = -1
;
	for(int k = 0; k < treeEdges.length; k ++) {
	    for(int i = 0; i < treeEdges[k].length; i ++) {
		if(treeEdges[k][i][2] > largestComp) {
		    largestComp = treeEdges[k][i][2];
		}
	    }
	}
	
	color = new Color[largestComp + 1];

	rand = new Random(999099);
	
	for (int i = 0; i < color.length; i++){
	    color[i] = new Color(rand.nextInt(256),
				 rand.nextInt(256),
				 rand.nextInt(256));
	}
	
	vOrder = new int[treeEdges[0].length];	
	compNum = new int[treeEdges[0].length];
	setComponentOrder();	


	// button stuff
	nextButton = new JButton();
	nextButton.addMouseListener(new java.awt.event.MouseAdapter() {
		public void mouseClicked(
					 java.awt.event.
					 MouseEvent evt) {
		    currentEdge ++;
		    repaint();
		}
	    });
	nextButton.setFont(new Font("sansserif",Font.BOLD,10));
	nextButton.setText("Next");
	nextButton.setBounds(180,68,70,20);
	
	combo = new JComboBox();
	combo.setFont(new Font("Tahoma",Font.BOLD,10));
	
	combo.addActionListener(this);
	for(int i = 0; i < numVertices; i++) {
	    combo.addItem(new Integer(i));
	}
	combo.setBounds(190,35,60,24);
	
	this.add(combo, new AbsoluteConstraints(190,35,60,24)); 
	this.add(nextButton, new AbsoluteConstraints(180,35,70,20));
    } 

    
    public void actionPerformed(ActionEvent e) {
	if(e.getModifiers() != 0) { // Then they changed it!
	    JComboBox j = (JComboBox)e.getSource();
	    int vert = j.getSelectedIndex();
	    currentStartVertex = vert;
	    currentEdge = -1;
	    circuitWalk = false;
	    setComponentOrder();
	    repaint();
	}
    }

    /**
     * <code>paintComponent</code> draws the graph in the panel.
     *
     * @param g a <code>Graphics</code> value
     */
    public void paintComponent(Graphics g) {
	//super.paintComponent(g);

	if(hasEulerCircuit) {
	    Graphics2D g2d = (Graphics2D) g;
	    
	    g.setFont(new Font(g.getFont().getName(), Font.BOLD, 12));
	    g.drawString("Search Tree", 55, 10);
	    g.setFont(new Font(g.getFont().getName(), Font.PLAIN, 12));
	    g.drawString("Starting Vertex", 160, 28);
	    
	    if(currentEdge == -1) {	  
		
		for (int i=0; i < numVertices; i++){
		    g.fillOval(getXCoor(i)-3, getYCoor(i)-3, 7, 7);
		    paintLabel(g,i);
		}
		
		this.setLayout(new AbsoluteLayout());
		
		Stroke drawingStroke = new BasicStroke(1);


		// The Stuff
		g2d.setStroke(new BasicStroke(1));
		drawingStroke = new BasicStroke(1,BasicStroke.CAP_BUTT,
						BasicStroke.JOIN_BEVEL,
						0, new float[]{9}, 0);
		
		
		for(int i = 0; i < edges.length; i ++) {  
		    Line2D line = new Line2D.Double(getXCoor(edges[i][0]),
						    getYCoor(edges[i][0]),
						    getXCoor(edges[i][1]),
						    getYCoor(edges[i][1]));
		    
		    g2d.setStroke(drawingStroke);
		    g2d.draw(line);
		    g2d.setStroke(new BasicStroke(1));
		}
		
	    }
	    else {
		if(!circuitWalk) {
		    if(currentEdge < treeEdges[currentStartVertex].length) {
			Stroke drawingStroke = 
			    new BasicStroke(2,BasicStroke.CAP_BUTT,
					    BasicStroke.JOIN_BEVEL,
					    0, new float[]{9}, 0);
		
			g2d.setStroke(drawingStroke);			
			g.setColor(color[treeEdges[currentStartVertex][vOrder[currentEdge]][2]]);
			g.drawLine(getXCoor(treeEdges[currentStartVertex]
					    [vOrder[currentEdge]][0]),
				   getYCoor(treeEdges[currentStartVertex]
					    [vOrder[currentEdge]][0]),
				   getXCoor(treeEdges[currentStartVertex]
					    [vOrder[currentEdge]][1]),
				   getYCoor(treeEdges[currentStartVertex]
					    [vOrder[currentEdge]][1]));	
			
			g.setColor(Color.black);		
			
			if(currentEdge == treeEdges[currentStartVertex]
			   .length -1) {
			    
			    // drawing the actual circuit.
			    circuitWalk = true;
			    currentEdge = -1;
			}			
		    }
		    g2d.setStroke(new BasicStroke(2));
		}
		else {
		    if(currentEdge < treeEdges[currentStartVertex].length) {
			/*	Stroke drawingStroke = new BasicStroke(1,
			  BasicStroke.CAP_BUTT,
			  BasicStroke.JOIN_BEVEL, 
			  0, new float[]{9}, 0);*/
			g2d.setStroke(new BasicStroke(2));
			g.setColor(color[treeEdges[currentStartVertex]
					 [currentEdge][2]]);
			
			g.drawLine(getXCoor(treeEdges[currentStartVertex]
					    [currentEdge][0]),
				   getYCoor(treeEdges[currentStartVertex]
					    [currentEdge][0]),
				   getXCoor(treeEdges[currentStartVertex]
					    [currentEdge][1]),
				   getYCoor(treeEdges[currentStartVertex]
					    [currentEdge][1]));		
			
			g.setColor(Color.black);		
		    }	
		}
	    }
	}	
	else {
	    g.setFont(new Font(g.getFont().getName(), Font.BOLD, 12));
	    g.drawString("This graph has no Euler Circuit!", 30, 80);
	    g.setFont(new Font(g.getFont().getName(), Font.PLAIN, 12));
	}
    }

    private void setComponentOrder() {
	
	for(int i = 0; i < vOrder.length; i ++) {
	    compNum[i] = treeEdges[currentStartVertex][i][2];
	    vOrder[i] = i;
	}
	
	int lowV;
	// Selection Sort
	for(int i = 0; i < compNum.length; i ++) {
	    lowV = i;
	    for(int j = i+1; j < compNum.length; j ++) {
		if(compNum[j] < compNum[lowV]) {
		    lowV = j;
		}
	    }
	    if(lowV != i) {
		swapAndSlide(lowV,i);
	    }
	}
	
    }
    private void swapAndSlide(int vPos, int vNewPos) {
	int temp = compNum[vPos];
	int tempV = vOrder[vPos];
	for(int i = vPos; i > vNewPos; i --) {
	    compNum[i] = compNum[i-1];
	    vOrder[i] = vOrder[i-1];
	}
	compNum[vNewPos] = temp;
	vOrder[vNewPos] = tempV;
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
