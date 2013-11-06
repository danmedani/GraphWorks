import org.netbeans.lib.awtextra.*;
import java.io.File;
import java.io.FileNotFoundException;
import javax.swing.JFileChooser;

/**
 * <code>DrawGeneric</code> extends GraphDraw and is used to draw the
 * Generic display classes
 *
 * @author Team 1
 * @version 1.0
 */
public class DrawGeneric extends GraphDraw {

    /**
     * <code>DrawGeneric</code> creates a new DrawGraph form.
     *
     * @param edges an <code>int</code> value
     * @param vOrder an <code>int</code> value
     * @param currentFile a <code>File</code> value
     */
    public DrawGeneric(int[][] edges, int[] vOrder, File currentFile,
		       String title) {
	super(edges);
        initComponents(edges, vOrder, title);
	explanationPanel.setMessage(s);
	this.currentFile = currentFile;
    }
    
    /**
     * <code>initComponents</code> iniitializes the form. 
     *
     * @param edges an <code>int</code> value
     * @param vOrder an <code>int</code> value
     */
    private void initComponents(int[][] edges, int[] vOrder, String title){
	
	genDisplay = new GenericDisplayPanel(edges, vOrder);
	
	getContentPane().remove(0);
	titleLabel.setText(title); 
	titleLabel.setFont(new java.awt.Font("Tahoma", 1, 24));
	getContentPane().add(titleLabel,
			     new AbsoluteConstraints(235,10,-1,-1));
	
	genDisplay.setLayout(new AbsoluteLayout());
	getContentPane().add(genDisplay, 
			     new AbsoluteConstraints(320,220,250,190));
	pack();
    }
    
    private String[] s = {"The original graph, shown to the left, is",
			  "displayed below, completely unchanged."};

   
    /**
     * <code>backButtonMouseClicked</code> retruns to Main Menu.
     *
     * @param evt a <code>MouseEvent</code> value
     */
    protected void backButtonMouseClicked(java.awt.event.MouseEvent evt) {
	Generic g = new Generic(currentFile);
	g.setLocationRelativeTo( this );
	g.setVisible(true);
	this.setVisible(false);
	this.dispose();
    }

    private GenericDisplayPanel genDisplay;
    private File currentFile;

}
