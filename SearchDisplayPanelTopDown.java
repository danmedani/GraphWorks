import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.Math.*;
import java.io.*;
import org.netbeans.lib.awtextra.*;
import java.util.LinkedList;


/**
 * <code>SearchDisplayPanel</code> displays a after performing a given search 
 * on it.
 *
 * @author 
 * @version 1.0
 */
public class SearchDisplayPanelTopDown extends JPanel 
    implements ActionListener {

    private int[][] edges;              //Array of edges
    private int numVerts;
    private int[][][] treeEdges; 
    private JButton nextButton;
    private JComboBox combo;
    private int currentEdge;
    private int currentStartVertex; 

    private LinkedList<Integer>[][] depthOrder;
    private int[] depth;


    // Finals for the top-down layout
    private final int xStart = -15;
    private final int xFinal = 185;
    private final int yStart = -5;
    private final int yFinal = 215;


    /**
     * Creates a new <code>SearchDisplayPanel</code> instance.
     *
     * @param e 2-d edge array with graph data.
     * @param t 2-d edge array of tree edges.
     */
    public SearchDisplayPanelTopDown(int[][] e, int[][][] t)  {
	edges = e;
	treeEdges = t;
	currentEdge = -1;
	currentStartVertex = 0;


	int largestVert = -1;
	for(int i = 0; i < t[0].length; i ++) {
	    if(t[0][i][0] > largestVert) {
		largestVert = t[0][i][0];
	    }
	    if(t[0][i][1] > largestVert) {
		largestVert = t[0][i][1];
	    }
	}
	numVerts = largestVert + 1; // We always start wih vert 0
	
	//	if(t[0].length < 15) { // Arbitrary for now
	    // Create the depthOrder lists
	findDepths();
	createOrderLists();
	    //	}

    }

    private void findDepths() {
	depth = new int[treeEdges.length];
	int deepest;
	for(int startV = 0; startV < treeEdges.length; startV ++) {
	    deepest = -1;
	    for(int i = 0; i < treeEdges[startV].length; i ++) {
		if(treeEdges[startV][i][2] > deepest) {
		    deepest = treeEdges[startV][i][2];
		}
	    }
	    depth[startV] = deepest + 1;
	}	
    }
    
    private void createOrderLists() {
	
	depthOrder = (LinkedList<Integer>[][]) 
	    new LinkedList[treeEdges.length][numVerts];
	LinkedList<Integer>[] test = (LinkedList<Integer>[]) 
	    new LinkedList[5];
	
	for(int startV = 0; startV < treeEdges.length; startV ++) {
	    
	    depthOrder[startV] = (LinkedList<Integer>[]) 
		new LinkedList[depth[startV]];
	    
	    depthOrder[startV][0] = new LinkedList();
	    depthOrder[startV][0].add(new Integer(startV));
	    
	    for(int d = 1; d < depth[startV]; d ++) {
		depthOrder[startV][d] = new LinkedList();
		for(int i = 0; i < treeEdges[0].length; i ++) {
		    if(treeEdges[startV][i][2] == d) {
			depthOrder[startV][d].addLast(treeEdges[startV][i][1]);
		    }
		}
	    }
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
     * <code>paintLabel</code> labels each vertex in the graph with its number.
     *
     * @param g a <code>Graphics</code> value
     * @param vNum vertex whose label is being printed
     */
    private void paintLabel(Graphics g, int vNum){
	g.setFont(new Font(g.getFont().getName(), Font.BOLD, 12));
	// Label Centering Information
	int xOffSet, yOffSet;
	if(vNum > 9) {
	    xOffSet = 10;
	}
	else {
	    xOffSet = 10;
	}
	yOffSet = 5;

	g.drawString(Integer.toString(vNum), 
		     getXCoor(vNum,currentStartVertex) + xOffSet,
		     getYCoor(vNum,currentStartVertex) + yOffSet);
    }



    /**
     * <code>paintComponent</code> draws the graph in the panel.
     *
     * @param g a <code>Graphics</code> value
     */
    public void paintComponent(Graphics g) {
	 
	Graphics2D g2d = (Graphics2D) g;

	g.setFont(new Font(g.getFont().getName(), Font.BOLD, 12));
	g.drawString("Search Tree", 55, 10);
	g.setFont(new Font(g.getFont().getName(), Font.PLAIN, 12));
	g.drawString("Starting Vertex", 160, 28);

	if(currentEdge == -1) {	  
	    
	    // Draw the initial vertex
	    g.fillOval(getXCoor(currentStartVertex,currentStartVertex)-3, 
		       getYCoor(currentStartVertex,
				currentStartVertex)-3, 7, 7);
	    paintLabel(g,currentStartVertex);
	    
	    this.setLayout(new AbsoluteLayout());

	    Stroke drawingStroke = new BasicStroke(1);
	    nextButton = new JButton();
	    nextButton.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
			currentEdge ++;
			repaint();
		    }
		});
	    nextButton.setFont(new Font("sansserif",Font.BOLD,10));
	    nextButton.setText("Next");
	    nextButton.setBounds(175,68,70,20);
	    
	    g2d.setStroke(new BasicStroke(1));
	    combo = new JComboBox();
	    combo.setFont(new Font("Tahoma",Font.BOLD,10));

	    combo.addActionListener(this);
	    for(int i = 0; i < numVerts; i++) {
	    	combo.addItem(new Integer(i));
	    }
	    g2d.setStroke(new BasicStroke(1));
	    combo.setBounds(203,35,30,30);

	    this.add(combo, new AbsoluteConstraints(203,35,30,30)); 
	    this.add(nextButton, new AbsoluteConstraints(180,35,70,20));

	}
	else {
	    if(currentEdge < treeEdges[currentStartVertex].length) {
			
		// Draw the Vertex && Label
		g.fillOval(getXCoor(treeEdges[currentStartVertex]
				    [currentEdge][1],currentStartVertex)-3,
		    getYCoor(treeEdges[currentStartVertex]
			     [currentEdge][1],currentStartVertex)-3, 7, 7);
		paintLabel(g,treeEdges[currentStartVertex][currentEdge][1]);

		g2d.setStroke(new BasicStroke(2));		
		g.drawLine(getXCoor(treeEdges[currentStartVertex]
				    [currentEdge][0],currentStartVertex),
			   getYCoor(treeEdges[currentStartVertex]
				    [currentEdge][0],currentStartVertex),
			   getXCoor(treeEdges[currentStartVertex]
				    [currentEdge][1],currentStartVertex),
			   getYCoor(treeEdges[currentStartVertex]
				    [currentEdge][1],currentStartVertex));
		g.setColor(Color.black);			
	    }	
	}
    }

    private int getDepth(int vNum, int startV) {
	for(int i = 0; i < depthOrder[startV].length; i ++) {
	    for(int j = 0; j < depthOrder[startV][i].size(); j ++) {
		if(depthOrder[startV][i].get(j) == vNum) {
		    return i;
		}
	    }
	}
	return -1;
    }
    private int getXPos(int vNum, int startV) {
	for(int i = 0; i < depthOrder[startV].length; i ++) {
	    for(int j = 0; j < depthOrder[startV][i].size(); j ++) {
		if(depthOrder[startV][i].get(j) == vNum) {
		    return j;
		}
	    }
	}
	return -1;	
    }


    private int getXCoor(int vNum, int startV) {
       	int totalWidth = depthOrder[startV][getDepth(vNum,startV)].size();
	int xDiff = (xFinal - xStart) / (totalWidth+1);	
	return xStart + ((getXPos(vNum, startV) + 1) * xDiff);
    }

    
    private int getYCoor(int vNum, int startV) {	
	int totalDepth = depthOrder[startV].length;
	int yDiff = (yFinal - yStart) / (totalDepth+1);
	return yStart + ((getDepth(vNum, startV) + 1) * yDiff);
    }
    

    public boolean isSmallEnough() {

	int widest = -1;
	int deepest = -1;
	for(int i = 0; i < depthOrder.length; i ++) {
	    if(depthOrder[i].length > deepest) {
		deepest = depthOrder[i].length;
	    }
	    for(int j = 0; j < depthOrder[i].length; j ++) {
		if(depthOrder[i][j].size() > widest) {
		    widest = depthOrder[i][j].size();
		}
	    }
	}
	
	if(deepest > 10 || widest > 9) {
	    return false;
	}
	else {
	    return true;
	}
	
    }
}
