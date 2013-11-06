import org.netbeans.lib.awtextra.*;
import java.io.File;
import java.io.FileNotFoundException;
import javax.swing.JFileChooser;

/**
 * <code>DrawConnect</code> extends GraphDraw and is used to draw the connected
 * components of a graph as determined by OneConn.java.
 *
 * @author Team 1
 * @version 1.0
 */
public class DrawNonSep extends GraphDraw {

    /**
     * <code>DrawConnect</code> creates a new DrawGraph form.
     *
     * @param edges an <code>int</code> value
     * @param vOrder an <code>int</code> value
     * @param currentFile a <code>File</code> value
     */
    public DrawNonSep(int[][] edges, File currentFile) {
	super(edges);
        initComponents(edges);
	explanationPanel.setMessage(s);
	this.currentFile = currentFile;
    }
    
    /**
     * <code>initComponents</code> iniitializes the form. 
     *
     * @param edges an <code>int</code> value
     * @param vOrder an <code>int</code> value
     */
    private void initComponents(int[][] edges){
	
	nonSepDisplay = new NonSepDisplayPanel(edges);
	
	getContentPane().remove(0);
	titleLabel.setText("Nonseparable Components");
	getContentPane().add(titleLabel,
			     new AbsoluteConstraints(130,10,-1,-1));
	
	nonSepDisplay.setLayout(new AbsoluteLayout());
	getContentPane().add(nonSepDisplay, 
			     new AbsoluteConstraints(320,220,250,190));
	pack();
    }

    private String[] s = {"The original graph, shown to the left, is",
			  "split into its nonseparable components",
			  "and re-drawn below. Each component is",
			  "drawn in a different color, with cut",
			  "vertices shown in bold. If there is",
			  "only one component, then the graph is",
			  "nonseparable."};
 
   
    /**
     * <code>backButtonMouseClicked</code> retruns to Main Menu.
     *
     * @param evt a <code>MouseEvent</code> value
     */
    protected void backButtonMouseClicked(java.awt.event.MouseEvent evt) {
	Connect c = new Connect(currentFile);
	c.setLocationRelativeTo( this );
	c.setVisible(true);
	this.setVisible(false);
	this.dispose();
    }

    private NonSepDisplayPanel nonSepDisplay;
    private File currentFile;

}
