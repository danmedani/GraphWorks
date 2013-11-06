import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.Math.*;
import java.io.*;
import org.netbeans.lib.awtextra.*;
import java.util.Random;

/**
 * <code>CompareDisplayPanel</code> displays a after performing a given search 
 * on it.
 *
 * @author 
 * @version 1.0
 */
public class CompareDisplayPanel extends JPanel implements ActionListener {

    private int[][] edges;              // Array of edges
    private int[][][] treeEdgesFleury; 
    private int[][][] treeEdgesSplice; 
    private JButton nextButton;
    private JComboBox combo;
    private int currentEdge;
    private int currentStartVertex;
    private boolean hasEulerCircuit;  
    private int numVertices;   

    private int XCENTER0;
    private int YCENTER0;
    private int XCENTER1;
    private int YCENTER1;
    private int RADIUS;  

    /**
     * Our random number generator, which is used for making random colors.
     */
    private Random rand;

    /**
     * The array of colors representing the color of each connected component.
     */    
    private Color[] color;


    public CompareDisplayPanel(int[][] edges, int[][][] treeEdgesFleury,
			     int[][][] treeEdgesSplice) {
	this.edges = edges;
	this.treeEdgesFleury = treeEdgesFleury;
	this.treeEdgesSplice = treeEdgesSplice;
	currentEdge = -1;
	currentStartVertex = 0;
	
	if(treeEdgesFleury[0][0][0] == -1) {
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
	
	numVertices = maxVertex+1;


	// Find the dimensions of the 2D color array
	int largestComp = -1
;
	for(int k = 0; k < treeEdgesFleury.length; k ++) {
	    for(int i = 0; i < treeEdgesFleury[k].length; i ++) {
		if(treeEdgesFleury[k][i][2] > largestComp) {
		    largestComp = treeEdgesFleury[k][i][2];
		}
	    }
	}
	
	color = new Color[largestComp + 1];

	rand = new Random(2558);
	
	for (int i = 0; i < color.length; i++){
	    color[i] = new Color(rand.nextInt(256),
				 rand.nextInt(256),
				 rand.nextInt(256));
	}	


	XCENTER0 = 95;
	YCENTER0 = 130;
	XCENTER1 = 395;
	YCENTER1 = 130;
	RADIUS = 75;



	// Button Stuff
		
	this.setLayout(new AbsoluteLayout());	

	nextButton = new JButton();
	nextButton.addMouseListener(new java.awt.event.MouseAdapter() {
		public void mouseClicked(java.awt.event.MouseEvent 
					 evt) {
		    getCurrentEdge();
		    repaint();
		}
	    });


	nextButton.setFont(new Font("sansserif",Font.BOLD,10));
	nextButton.setText("Next");
	nextButton.setBounds(215,200,70,20);
	
	combo = new JComboBox();
	combo.setFont(new Font("Tahoma",Font.BOLD,10));
	combo.addActionListener(this);
	for(int i = 0; i < numVertices; i++) {
	    combo.addItem(new Integer(i));
	}
	combo.setBounds(273,235,60,24);
	
	this.add(combo, new AbsoluteConstraints(273,235,60,24)); 
	this.add(nextButton, new AbsoluteConstraints(215,200,70,20));

    } 
    public void getCurrentEdge() {
	if(currentEdge < treeEdgesFleury[currentStartVertex].length-1) {
	    currentEdge ++;
	    repaint();
	}
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
	    // Labels
	    
	    g.setFont(new Font(g.getFont().getName(), Font.BOLD, 12));
	    g.drawString("Starting Vertex:", 170, 250);
	    
	    //g.setFont(new Font(g.getFont().getName(), Font.BOLD, 12));
	    g.drawString("Fleury's Algorithm", 40, 25);
	    
	    //g.setFont(new Font(g.getFont().getName(), Font.BOLD, 12));
	    g.drawString("Splicing Algorithm", 350, 25);
	    
	    
	    // Fleury
	    if(hasEulerCircuit) {
	
		for (int i=0; i < numVertices; i++){
		    g.fillOval(getXCoor(i,0)-3, getYCoor(i,0)-3, 7, 7);
		    paintLabel(g,i,0);
		}
		
		this.setLayout(new AbsoluteLayout());
		
		Stroke drawingStroke = new BasicStroke(1,
						       BasicStroke.CAP_BUTT,
						       BasicStroke.JOIN_BEVEL, 
						       0, new float[]{9}, 0);
		
		Graphics2D g2d = (Graphics2D) g;
		for(int i = 0; i < edges.length; i ++) {
		    Line2D line = new Line2D.Double(getXCoor(edges[i][0],0),
						    getYCoor(edges[i][0],0),
						    getXCoor(edges[i][1],0),
						    getYCoor(edges[i][1],0));
		    
		    g2d.setStroke(drawingStroke);
		    g2d.draw(line);
		    g2d.setStroke(new BasicStroke(1));
		}
		
		if(currentEdge < treeEdgesFleury[currentStartVertex].length && 
		   currentEdge >= 0) {
		    for(int i = 0; i <= currentEdge; i ++) {
			drawingStroke = new 
			    BasicStroke(1,BasicStroke.CAP_BUTT,
					BasicStroke.JOIN_BEVEL, 
					0, new float[]{9}, 0);
			
			g2d = (Graphics2D) g;	
			
			g2d.setStroke(new BasicStroke(2));		
			g.drawLine(getXCoor(treeEdgesFleury[currentStartVertex]
					    [i][0],0),
				   getYCoor(treeEdgesFleury[currentStartVertex]
					    [i][0],0),
				   getXCoor(treeEdgesFleury[currentStartVertex]
					    [i][1],0),
				   getYCoor(treeEdgesFleury[currentStartVertex]
					    [i][1],0));		
		    }
		    
		}	    
		g.setColor(Color.black);	 	    
	    }	
	    else {
		g.setFont(new Font(g.getFont().getName(), Font.BOLD, 12));
		g.drawString("This graph has no Euler Circuit!", 30, 80);
		g.setFont(new Font(g.getFont().getName(), Font.PLAIN, 12));
	    } 
	    
	    
	    
	    // Splicing Method
	    
	    if(hasEulerCircuit) {
		Graphics2D g2d = (Graphics2D) g;
		
		//	if(currentEdge == -1) {	  
		    
		    for (int i=0; i < numVertices; i++){
			g.fillOval(getXCoor(i,1)-3, getYCoor(i,1)-3, 7, 7);
			paintLabel(g,i,1);
		    }
		    
		    this.setLayout(new AbsoluteLayout());
		    
		    Stroke drawingStroke = new BasicStroke(1);

		    
		    drawingStroke = new BasicStroke(1,BasicStroke.CAP_BUTT,
						    BasicStroke.JOIN_BEVEL,
						    0, new float[]{9}, 0);
		    
		    
		    for(int i = 0; i < edges.length; i ++) {  
			Line2D line =
			    new Line2D.Double(getXCoor(edges[i][0],1),
					      getYCoor(edges[i][0],1),
					      getXCoor(edges[i][1],1),
					      getYCoor(edges[i][1],1));
			
			g2d.setStroke(drawingStroke);
			g2d.draw(line);
			g2d.setStroke(new BasicStroke(1));
		    }
		    
		    //	}
		    //	else {
		    if(currentEdge < treeEdgesSplice[currentStartVertex].
		       length &&
		       currentEdge != -1) {
			
			for(int i = 0; i <= currentEdge; i ++) {
			    g2d.setStroke(new BasicStroke(2));	
			    // g.setColor(color[treeEdgesSplice
			    //  [currentStartVertex][i][2]]);
			    g.drawLine(getXCoor(treeEdgesSplice
						[currentStartVertex]
						[i][0],1),
				       getYCoor(treeEdgesSplice
						[currentStartVertex]
						[i][0],1),
				       getXCoor(treeEdgesSplice
						[currentStartVertex]
						[i][1],1),
				       getYCoor(treeEdgesSplice
						[currentStartVertex]
						[i][1],1));		
			    
			}
			g.setColor(Color.black);		
			//  }	
		}


	    }	
	    else {
		g.setFont(new Font(g.getFont().getName(), Font.BOLD, 12));
		g.drawString("This graph has no Euler Circuit!", 30, 80);
		g.setFont(new Font(g.getFont().getName(), Font.PLAIN, 12));
	    }
	  
	}
	else {
	    g.setFont(new Font(g.getFont().getName(), Font.BOLD, 12));
	    g.drawString("This graph has no Euler Circuit!", 140, 100);
	    g.setFont(new Font(g.getFont().getName(), Font.PLAIN, 12));
	}
    }

    private void paintLabel(Graphics g, int vNum, int gNum){

	// Label Centering Information
	int xOffSet, yOffSet;
	if(vNum > 9) {
	    xOffSet = -6;
	}
	else {
	    xOffSet = -3;
	}
	yOffSet = 5;

	int rise,run;

	if(gNum==0) {
	    rise = YCENTER0 - getYCoor(vNum,gNum);
	    run = XCENTER0 - getXCoor(vNum,gNum);
	}
	else {
	    rise = YCENTER1 - getYCoor(vNum,gNum);
	    run = XCENTER1 - getXCoor(vNum,gNum);
	}

	g.drawString(Integer.toString(vNum), getXCoor(vNum,gNum)-(run/4) +
		     xOffSet,
		     getYCoor(vNum,gNum)-(rise/4) + yOffSet);

    }

    protected int getXCoor(int vNum, int gNum){
	if(gNum == 0) {
	    return XCENTER0 + ((int)(Math.sin((2*Math.PI)*vNum/numVertices)*
				     RADIUS));
	}
	else {
	    return XCENTER1 + ((int)(Math.sin((2*Math.PI)*vNum/numVertices)*
				     RADIUS));
	}
    }
    
    /**
     * <code>getYCoor</code> gets the y-coordinate of the location to draw
     * vertex <code>vNum</code>.
     *
     * @param vNum vertex whose coordinate is being calculated
     */
    protected int getYCoor(int vNum, int gNum){
	if(gNum == 0) {
	    return YCENTER0 + 
		((int)(Math.cos((2*Math.PI)*vNum/numVertices)*-RADIUS));
	}
	else {
	    return YCENTER1 + 
		((int)(Math.cos((2*Math.PI)*vNum/numVertices)*-RADIUS));
	}
    }

}