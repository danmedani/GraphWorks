import javax.swing.*;
import java.io.File;
import org.netbeans.lib.awtextra.*;

public class GraphDisplay extends JFrame {

    /** Creates new form DrawGraph */
    public GraphDisplay(int[][] edges, File currentFile) {
        initComponents(edges);
	this.currentFile = currentFile;
    }
    public GraphDisplay(int[][] edges) {
     initComponents(edges);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     */
    private void initComponents(int[][] edges) {

        titleLabel = new JLabel();
        displayPanel = new GraphDisplayPanel(edges,true);
	if(edges.length < 20) {
	    displayPanel.findHams();
	}
        backButton = new JButton();
	jSeparator2 = new JSeparator();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(600, 450));
        setResizable(false);
        getContentPane().setLayout(new AbsoluteLayout());

        titleLabel.setFont(new java.awt.Font("Tahoma", 1, 24));
        titleLabel.setText("Draw Graph");
        getContentPane().add(titleLabel,
			     new AbsoluteConstraints(220,10,-1,-1));
	
        displayPanel.setLayout(new AbsoluteLayout());
	displayPanel.setTitleVisible(false);
	displayPanel.setDimensions(140,120,90);
        getContentPane().add(displayPanel, 
			     new AbsoluteConstraints(155,50,290,260));
		
        backButton.setText("Back");
        backButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backButtonMouseClicked(evt);
            }
        });
        getContentPane().add(backButton,
			     new AbsoluteConstraints(520,420,70,-1));
	
   
	if(edges[0].length > 2) {
	    weightedGraphInfoPanel = new WeightedEdgeDisplayPanel(edges);
	    getContentPane().add(weightedGraphInfoPanel,
				 new AbsoluteConstraints(160,330,290,110));
	}
	else {
	    graphInfoPanel = new EdgeDisplayPanel(edges);
	    getContentPane().add(graphInfoPanel,
				 new AbsoluteConstraints(160,330,290,110));
	}

        getContentPane().add(jSeparator2,
			     new AbsoluteConstraints(170,320,250,10));


        pack();
    }

    protected void backButtonMouseClicked(java.awt.event.MouseEvent evt) {
	DrawGraph dg = new DrawGraph(currentFile);
	dg.setLocationRelativeTo( this );
	dg.setVisible(true);
	this.setVisible(false);
	this.dispose();
    }

    private String[] s = {"The graph that you entered is displayed",
			  "on the left side of the screen. Its ",
			  "number of vertices and edges and a listing",
			  "of its edges are displayed underneath the",
			  "graph."};


    protected JButton backButton;
    protected GraphDisplayPanel displayPanel;
    protected  EdgeDisplayPanel graphInfoPanel;
    protected  WeightedEdgeDisplayPanel weightedGraphInfoPanel;
    private JSeparator jSeparator2;
    protected JLabel titleLabel;
    private File currentFile;
 
}
