import javax.swing.*;
import java.io.File;
import org.netbeans.lib.awtextra.*;

public class CompareSearch extends JFrame {

    /** Creates new form DrawGraph */
    public CompareSearch(int[][] edges, int[][][] treeEdgesDFS,
			int[][][] treeEdgesBFS) {
        initComponents(edges,treeEdgesDFS, treeEdgesBFS);
    }
    public CompareSearch(int[][] edges, int[][][] treeEdgesDFS,
			int[][][] treeEdgesBFS, File currentFile) {
	initComponents(edges, treeEdgesDFS, treeEdgesBFS);	
	this.currentFile = currentFile;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     */
    private void initComponents(int[][] edges, int[][][] treeEdgesDFS,
				int[][][] treeEdgesBFS) {

	
        titleLabel = new JLabel();
        compareDisplay = new SearchCompareDisplayPanel(edges,treeEdgesDFS,
						 treeEdgesBFS);

        backButton = new JButton();
	jSeparator2 = new JSeparator();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(600, 450));
        setResizable(false);
        getContentPane().setLayout(new AbsoluteLayout());

        titleLabel.setFont(new java.awt.Font("Helvetica", 1, 24));
        titleLabel.setText("Compare Searches");
        getContentPane().add(titleLabel,
			     new AbsoluteConstraints(190,10,-1,-1));


        compareDisplay.setLayout(new AbsoluteLayout());
        getContentPane().add(compareDisplay, 
			     new AbsoluteConstraints(50,50,500,260));


        backButton.setText("Back");
        backButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backButtonMouseClicked(evt);
            }
        });
        getContentPane().add(backButton,
			     new AbsoluteConstraints(520,420,70,-1));

   
        getContentPane().add(jSeparator2,
			     new AbsoluteConstraints(170,320,250,10));


	graphInfoPanel = new EdgeDisplayPanel(edges);
	getContentPane().add(graphInfoPanel,
				 new AbsoluteConstraints(160,330,290,110));


        pack();
    }

    protected void backButtonMouseClicked(java.awt.event.MouseEvent evt) {
	Search ce = new Search(currentFile);
	ce.setLocationRelativeTo( this );
	ce.setVisible(true);
	this.setVisible(false);
	this.dispose();
    }

    private String[] s = {"The graph that you entered is displayed",
			  "on the left side of the screen. Its ",
			  "number of vertices and edges and a listing",
			  "of its edges are displayed underneath the",
			  "graph."};


    protected JButton backButton;
    protected  EdgeDisplayPanel graphInfoPanel;
    private JSeparator jSeparator2;
    protected JLabel titleLabel;
    private File currentFile;
    private SearchCompareDisplayPanel compareDisplay;
 
}
