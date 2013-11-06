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
public class DrawConnect extends GraphDraw {

    /**
     * <code>DrawConnect</code> creates a new DrawGraph form.
     *
     * @param edges an <code>int</code> value
     * @param vOrder an <code>int</code> value
     * @param currentFile a <code>File</code> value
     */
    public DrawConnect(int[][] edges, int[] vOrder, File currentFile) {
	super(edges);
        initComponents(edges, vOrder);
	explanationPanel.setMessage(s);
	this.currentFile = currentFile;
    }
    
    /**
     * <code>initComponents</code> iniitializes the form. 
     *
     * @param edges an <code>int</code> value
     * @param vOrder an <code>int</code> value
     */
    private void initComponents(int[][] edges, int[] vOrder){
	
	conDisplay = new ConDisplayPanel(edges, vOrder);
	
	getContentPane().remove(0);
	titleLabel.setText("Connected Components"); 
	titleLabel.setFont(new java.awt.Font("Tahoma", 1, 24));
	getContentPane().add(titleLabel,
			     new AbsoluteConstraints(145,10,-1,-1));
	
	conDisplay.setLayout(new AbsoluteLayout());
	getContentPane().add(conDisplay, 
			     new AbsoluteConstraints(320,220,250,190));
	pack();
    }
    
    private String[] s = {"The original graph, shown to the left, is",
			  "split into its connected components and ",
			  "re-drawn below. Each component is shown",
			  "in a different color. If there is only one",
			  "component, then the graph is connected. If",
			  "there are two or more components, the",
			  "graph is disconnected."};

   
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

    private ConDisplayPanel conDisplay;
    private File currentFile;

}
