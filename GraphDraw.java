import javax.swing.*;
import java.io.File;
import org.netbeans.lib.awtextra.*;

public class GraphDraw extends JFrame {

    /** Creates new form DrawGraph */
    public GraphDraw(int[][] edges, File currentFile) {
        initComponents(edges);
	this.currentFile = currentFile;
    }
    public GraphDraw(int[][] edges) {
     initComponents(edges);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     */
    private void initComponents(int[][] edges) {

        titleLabel = new JLabel();
        displayPanel = new GraphDisplayPanel(edges,false);
        jSeparator1 = new JSeparator();
        backButton = new JButton();
        graphInfoPanel = new EdgeDisplayPanel(edges);
        jSeparator2 = new JSeparator();
        explanationPanel = new InfoDisplayPanel(s);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(600, 450));
        setResizable(false);
        getContentPane().setLayout(new AbsoluteLayout());

        titleLabel.setFont(new java.awt.Font("Tahoma", 1, 24));
        titleLabel.setText("Draw Graph");
        getContentPane().add(titleLabel,
			     new AbsoluteConstraints(200,10,-1,-1));

        displayPanel.setLayout(new AbsoluteLayout());
        getContentPane().add(displayPanel, 
			     new AbsoluteConstraints(20,50,290,240));

        jSeparator1.setOrientation(SwingConstants.VERTICAL);
        getContentPane().add(jSeparator1, 
			     new AbsoluteConstraints(312,50,10,370));

        backButton.setText("Back");
        backButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backButtonMouseClicked(evt);
            }
        });
        getContentPane().add(backButton,
			     new AbsoluteConstraints(520,420,70,-1));

        graphInfoPanel.setLayout(new AbsoluteLayout());
        getContentPane().add(graphInfoPanel,
			     new AbsoluteConstraints(20,312,290,110));
        getContentPane().add(jSeparator2,
			     new AbsoluteConstraints(310,210,270,10));

        explanationPanel.setLayout(new AbsoluteLayout());
        getContentPane().add(explanationPanel,
			     new AbsoluteConstraints(320,50,250,140));

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
			  " graph."};


    protected JButton backButton;
    protected GraphDisplayPanel displayPanel;
    protected  InfoDisplayPanel explanationPanel;
    protected  EdgeDisplayPanel graphInfoPanel;
    private JSeparator jSeparator1;
    private JSeparator jSeparator2;
    protected JLabel titleLabel;
    private File currentFile;
 
}
