import java.io.File;
import java.io.FileNotFoundException;
import javax.swing.JFileChooser;
import org.netbeans.lib.awtextra.*;

/**
 * <code>DrawSmallGraph</code> extends GraphDraw and is 
 * used to draw the connected
 *
 * @author Team 1
 * @version 1.0
 */
public class DrawSmallGraph extends GraphDraw {

  
    /**
     * <code>DrawEuler</code> creates a new DrawEuler form.
     *
     * @param edges an <code>int</code> value
     * @param tEdges an <code>int</code> value
     * @param currentFile a <code>File</code> value
     */
    public DrawSmallGraph(int[][] edges, int[] hararyNum, 
			  File currentFile ) {
	super(edges);
        initComponents(edges, hararyNum);
	explanationPanel.setMessage(s);
	this.currentFile = currentFile;
    }
    
    private void initComponents(int[][] edges, int[] hararyNum){
	
	getContentPane().remove(0);
	titleLabel.setText("Small Graphs");
	getContentPane().add(titleLabel,
			     new AbsoluteConstraints(200,10,-1,-1));



	smallGraphDisplay = new SmallGraphDisplayPanel(edges, hararyNum);
	smallGraphDisplay.setLayout(new AbsoluteLayout());
	getContentPane().add(smallGraphDisplay, 
			     new AbsoluteConstraints(340,220,250,190));
	pack();
    }
    
    private String[] s = {"In the panel below, the graph's vertex (P)",
			  "and edge (Q) counts are shown, in",
			  "addition to the harary number."};
       

    /**
     * <code>backButtonMouseClicked</code> returns to the Search form.
     *
     * @param evt a <code>java.awt.event.MouseEvent</code> value
     */
    protected void backButtonMouseClicked(java.awt.event.MouseEvent evt) {
	SmallGraph sg = new SmallGraph(currentFile);
	sg.setLocationRelativeTo( this );
	sg.setVisible(true);
	this.setVisible(false);
	this.dispose();
    }

    private SmallGraphDisplayPanel smallGraphDisplay;
    private File currentFile;


}
