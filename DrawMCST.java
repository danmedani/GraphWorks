import java.io.File;
import java.io.FileNotFoundException;
import javax.swing.JFileChooser;
import org.netbeans.lib.awtextra.*;

/**
 * <code>DrawMCST</code>
 *
 * @author Team 1
 * @version 1.0
 */
public class DrawMCST extends GraphDraw {
  
    /**
     * <code>DrawMCST</code> creates a new DrawMCST form.
     *
     * @param edges an <code>int</code> value
     * @param tEdges an <code>int</code> value
     * @param currentFile a <code>File</code> value
     */
    public DrawMCST(int[][] edges, int[][] tEdges, File currentFile, 
		    String searchType) {
	super(edges);
        initComponents(edges, tEdges, searchType);
	explanationPanel.setMessage(s);
	this.currentFile = currentFile;
    }
    
    private void initComponents(int[][] edges, int[][] tEdges, 
				String searchType){
	s = new String[7];
	if(searchType.equals("p")) {
	    titleLabel.setText("   Prim's Algorithm");
	    s[0] = "With Prim's algorithm, a vertex is first";
	    s[1] = "arbitrarily chosen to be the building block";
	    s[2] = "of the tree. Then, the lightest edge";
	    s[3] = "between any vertex in the tree ( just the";
	    s[4] = "first vertex to start ) and any vertex not";
	    s[5] = "in the tree is added. This is repeated until";
	    s[6] = "a minimal cost spanning tree is formed.";
		 
	}
	else if(searchType.equals("k")) {
	    titleLabel.setText(" Kruskal's Algorithm");
	    s[0] = "With Kruskal's algorithm, the edges are";
	    s[1] = "first sorted by weight, and then the lightest";
	    s[2] = "edge is added to the tree and removed";
	    s[3] = "from further consideration. It is important";
	    s[4] = "that the edge being added does not form";
	    s[5] = "a cycle with previously added edges. This";
	    s[6] = "is repeated until a spanning tree forms.";
	}
	else {
	    titleLabel.setText("<html>Bor&#367;vka's Algorithm");
	    s[0] = "With Boruvka's algorithm, each vertex is";
	    s[1] = "initially its own tree. Then, the lightest";
	    s[2] = "edge steming from each tree is added to";
	    s[3] = "that tree (if the same edge is chosen by";
	    s[4] = "two different trees, then the two trees will";
	    s[5] = "merge), and this process is repeated until";
	    s[6] = "we are left with only one tree.";
	}
	


	getContentPane().remove(0);
	getContentPane().add(titleLabel,
			     new AbsoluteConstraints(165,
						     10,
						     -1,
						     -1));
	
	getContentPane().remove(graphInfoPanel);
	
	weightedEdgeDisplay = new WeightedEdgeDisplayPanel(edges);
	weightedEdgeDisplay.setLayout(new AbsoluteLayout());
        getContentPane().add(weightedEdgeDisplay,
			     new AbsoluteConstraints(20,
						     310,
						     290,
						     110));
	
	getContentPane().remove(displayPanel);
	weightedGraphDisplay = new WeightedGraphDisplayPanel(edges);
	weightedGraphDisplay.setLayout(new AbsoluteLayout());
        getContentPane().add(weightedGraphDisplay, 
			     new AbsoluteConstraints(20,
						     50,
						     290,
						     240));
	
	
	mcstDisplay = new MCSTDisplayPanel(edges, tEdges);	
	mcstDisplay.setLayout(new AbsoluteLayout());
	getContentPane().add(mcstDisplay, 
			     new AbsoluteConstraints(320,220,250,190));
	pack();
    }
    
    private String[] s;
   
    /**
     * <code>backButtonMouseClicked</code> returns to the Search form.
     *
     * @param evt a <code>java.awt.event.MouseEvent</code> value
     */
    protected void backButtonMouseClicked(java.awt.event.MouseEvent evt) {
	MCST m = new MCST(currentFile);
	m.setLocationRelativeTo( this );
	m.setVisible(true);
	this.setVisible(false);
	this.dispose();
    }

    private MCSTDisplayPanel mcstDisplay;
    private File currentFile;
    private WeightedEdgeDisplayPanel weightedEdgeDisplay;
    private WeightedGraphDisplayPanel weightedGraphDisplay;

}
