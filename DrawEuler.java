import java.io.File;
import java.io.FileNotFoundException;
import javax.swing.JFileChooser;
import org.netbeans.lib.awtextra.*;

/**
 * <code>DrawEuler</code> extends GraphDraw and is used to draw the connected
 *
 * @author Team 1
 * @version 1.0
 */
public class DrawEuler extends GraphDraw {

  
    /**
     * <code>DrawEuler</code> creates a new DrawEuler form.
     *
     * @param edges an <code>int</code> value
     * @param tEdges an <code>int</code> value
     * @param currentFile a <code>File</code> value
     */
    public DrawEuler(int[][] edges, int[][][] tEdges, 
		     File currentFile, String searchType) {
	super(edges);
        initComponents(edges, tEdges, searchType);
	explanationPanel.setMessage(s);
	this.currentFile = currentFile;
    }
    
    private void initComponents(int[][] edges, int[][][] tEdges,
				String searchType){
	
	getContentPane().remove(0);
	titleLabel.setText(searchType);
	titleLabel.setFont(new java.awt.Font("Helvetica", 1, 24));
	getContentPane().add(titleLabel,
			     new AbsoluteConstraints(190,10,-1,-1));
	
	
	s = new String[7];
	if(searchType.equals("Splicing Algorithm")) {
	    spliceDisplay = new SpliceDisplayPanel(edges, tEdges);	
	    spliceDisplay.setLayout(new AbsoluteLayout());
	    getContentPane().add(spliceDisplay, 
				 new AbsoluteConstraints(320,220,250,190));
	    s[0] = "The splicing algorithm first builds up one";
	    s[1] = "or more separate circuits, which are shown";
	    s[2] = "in colored dashed lines. Afterwards, it";
	    s[3] = "splices these together to form the full";
	    s[4] = "Eulerian circuit. This circuit is traced with";
	    s[5] = "colored solid lines. You can also select";
	    s[6] = "the starting vertex of the circuit.";
	    
	}
	else {
	    fleuryDisplay = new FleuryDisplayPanel(edges, tEdges);	
	    fleuryDisplay.setLayout(new AbsoluteLayout());
	    getContentPane().add(fleuryDisplay, 
				 new AbsoluteConstraints(320,220,250,190));
	    
	    s[0] = "With Fleury's algorithm, it is important that";
	    s[1] = "we choose an edge that itsn't a bridge";
	    s[2] = "of the reduced graph. In the graph below,";
	    s[3] = "a red line means that the edge we are ";
	    s[4] = "about to add is a bridge. A green line";
	    s[5] = "indicates that the next edge is not";
	    s[6] = "a bridge, and is therefore safe to add.";
	}
	
	pack();
    }
    
    /**
     * <code>backButtonMouseClicked</code> returns to the Search form.
     *
     * @param evt a <code>java.awt.event.MouseEvent</code> value
     */
    protected void backButtonMouseClicked(java.awt.event.MouseEvent evt) {
	Euler e = new Euler(currentFile);
	e.setLocationRelativeTo( this );
	e.setVisible(true);
	this.setVisible(false);
	this.dispose();
    }
    
    private String[] s;
    private FleuryDisplayPanel fleuryDisplay;
    private SpliceDisplayPanel spliceDisplay;
    private File currentFile;


}
