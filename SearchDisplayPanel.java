import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.Math.*;
import java.io.*;
import org.netbeans.lib.awtextra.*;



/**
 * <code>SearchDisplayPanel</code> displays a after performing a given search 
 * on it.
 *
 * @author 
 * @version 1.0
 */
public class SearchDisplayPanel extends GraphDisplayPanel 
    implements ActionListener {

    private int[][] edges;              //Array of edges
    private int[][][] treeEdges; 
    private JButton nextButton;
    private JComboBox combo;
    private int currentEdge;
    private int currentStartVertex;

    /**
     * Creates a new <code>SearchDisplayPanel</code> instance.
     *
     * @param e 2-d edge array with graph data.
     * @param t 2-d edge array of tree edges.
     */
    public SearchDisplayPanel(int[][] e, int[][][] t){
	
	super(e, false);
	edges = e;
	treeEdges = t;
	currentEdge = -1;
	currentStartVertex = 0;
	
	this.XCENTER = 95;
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

	g.setFont(new Font(g.getFont().getName(), Font.BOLD, 12));
	g.drawString("Search Tree", 75, 10);
	g.setFont(new Font(g.getFont().getName(), Font.PLAIN, 12));

	if(currentEdge == -1) {	  

	    for (int i=0; i < numVertices; i++){
		g.fillOval(getXCoor(i)-2, getYCoor(i)-2, 5, 5);
		paintLabel(g,i);
	    }
	    
	    this.setLayout(new AbsoluteLayout());
	    nextButton = new JButton();
	    nextButton.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
			currentEdge ++;
			repaint();
		    }
		});
	    nextButton.setFont(new Font("sansserif",Font.BOLD,10));
	    nextButton.setText("Next");
	    nextButton.setBounds(180,35,70,20);
	    //nextButton.setBounds(180,2,70,20);

	    combo = new JComboBox();
	    combo.setFont(new Font("sansserif",Font.BOLD,10));
	    combo.addActionListener(this);
	    for(int i = 0; i < numVertices; i++) {
	    	combo.addItem(new Integer(i));
	    }
	    combo.setBounds(206,2,35,30);

	    this.add(combo, new AbsoluteConstraints(206,2,35,30)); 
	    this.add(nextButton, new AbsoluteConstraints(180,35,70,20));
	    //this.add(nextButton, new AbsoluteConstraints(180,2,70,20));
	  	    
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
	    if(currentEdge < treeEdges[currentStartVertex].length) {
		Stroke drawingStroke = new BasicStroke(1,
						       BasicStroke.CAP_BUTT,
						       BasicStroke.JOIN_BEVEL, 
						       0, new float[]{9}, 0);
		
		Graphics2D g2d = (Graphics2D) g;	
		
		g2d.setStroke(new BasicStroke(2));		
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
