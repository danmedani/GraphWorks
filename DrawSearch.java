import java.io.File;
import java.io.FileNotFoundException;
import javax.swing.JFileChooser;
import org.netbeans.lib.awtextra.*;

/**
 * <code>DrawSearch</code> extends GraphDraw and is used to draw the connected
 *
 * @author Team 1
 * @version 1.0
 */
public class DrawSearch extends GraphDraw {

  
    /**
     * <code>DrawSearch</code> creates a new DrawSearch form.
     *
     * @param edges an <code>int</code> value
     * @param tEdges an <code>int</code> value
     * @param currentFile a <code>File</code> value
     */
    public DrawSearch(int[][] edges, int[][][] tEdges, File currentFile,
		      String searchType) {
	super(edges);
        initComponents(edges, tEdges, searchType);
	if(searchType.equals("Depth First Search")) {
	    explanationPanel.setMessage(dfs);
	}
	else {
	    explanationPanel.setMessage(bfs);
	}
	this.currentFile = currentFile;
    }
    
    private void initComponents(int[][] edges, int[][][] tEdges,
				String searchType){
	
	getContentPane().remove(0);
	titleLabel.setText(searchType);
	titleLabel.setFont(new java.awt.Font("Tahoma", 1, 24));
	getContentPane().add(titleLabel,
			     new AbsoluteConstraints(185,10,-1,-1));

	searchDisplayTopDown = new SearchDisplayPanelTopDown(edges, tEdges);
	if(searchDisplayTopDown.isSmallEnough()) {
	    /* OK for the TopDown display panel */
	    searchDisplayTopDown.setLayout(new AbsoluteLayout());
	    getContentPane().add(searchDisplayTopDown, 
				 new AbsoluteConstraints(340,220,250,190));
	}
	else {
	    searchDisplay = new SearchDisplayPanel(edges, tEdges);	
	    searchDisplay.setLayout(new AbsoluteLayout());
	    getContentPane().add(searchDisplay,
				 new AbsoluteConstraints(320,220,250,190));
	}
	pack();
    }
    
    private String[] dfs = {"In the panel below, the search tree can be",
			    "constructed. Clicking on the Next button",
			    "will add tree edges to the graph one at a",
			    "time. In a depth first search, you travel",
			    "as deeply as possible into each branch",
			    "before backtracking. You can also select",
			    "the starting vertex of the traversal."};
			    
   
    private String[] bfs = {"In the panel below, the graph is shown with",
			    "dashed edges. Clicking on the Next button",
			    "will add tree edges to the graph one at a",
			    "time. In a breadth first search, you visit",
			    "each neighbor before moving deeper into",
			    "the graph. You can also select the starting",
			    "vertex of the traversal."};
    /**
     * <code>backButtonMouseClicked</code> returns to the Search form.
     *
     * @param evt a <code>java.awt.event.MouseEvent</code> value
     */
    protected void backButtonMouseClicked(java.awt.event.MouseEvent evt) {
	Search s = new Search(currentFile);
	s.setLocationRelativeTo( this );
	s.setVisible(true);
	this.setVisible(false);
	this.dispose();
    }

  

    private SearchDisplayPanel searchDisplay;
    private SearchDisplayPanelTopDown searchDisplayTopDown;
    private File currentFile;


}
