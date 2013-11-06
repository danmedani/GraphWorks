import javax.swing.*;
import java.io.File;


/*
 * DrawGraph.java
 *
 * Created on Mar 25, 2010, 11:27:45 AM
 */

/**
 *
 * @author Team1
 */
public class DrawSmallGraphs extends JFrame {

    /** Creates new form DrawGraph */
    public DrawSmallGraphs(int[][] edges, int[] hNum, File currentFile) {
        initComponents(edges);
	this.currentFile = currentFile;
	harary = hNum;
	s[0] = "The graph that you entered is ";
	s[1] = "        harary number:";
	s[2] = "          p="+harary[0];
	s[3] = "          q="+harary[1];
	s[4] = "          #="+harary[2];
    }
    public DrawSmallGraphs(int[][] edges) {
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
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        titleLabel.setFont(new java.awt.Font("Lucida Sans", 1, 30));
        titleLabel.setText("Small Graphs");
        getContentPane().add(titleLabel,
			     new org.netbeans.lib.awtextra.AbsoluteConstraints(200,
									       0,
									       210,
									       50));

        displayPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(displayPanel, 
			     new org.netbeans.lib.awtextra.AbsoluteConstraints(20,
									       50,
									       290,
									       240));

        jSeparator1.setOrientation(SwingConstants.VERTICAL);
        getContentPane().add(jSeparator1, 
			     new org.netbeans.lib.awtextra.AbsoluteConstraints(310,
									       50,
									       10,
									       370));

        backButton.setText("Back");
        backButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backButtonMouseClicked(evt);
            }
        });
        getContentPane().add(backButton,
			     new org.netbeans.lib.awtextra.AbsoluteConstraints(510,
									       420,
									       70,
									       -1));

        graphInfoPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(graphInfoPanel,
			     new org.netbeans.lib.awtextra.AbsoluteConstraints(20,
									       310,
									       290,
									       110));
        getContentPane().add(jSeparator2,
			     new org.netbeans.lib.awtextra.AbsoluteConstraints(310,
									       210,
									       270,
									       10));

        explanationPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(explanationPanel,
			     new org.netbeans.lib.awtextra.AbsoluteConstraints(320,
									       50, 
									       250, 
									       140));

        pack();
    }

    protected void backButtonMouseClicked(java.awt.event.MouseEvent evt) {
	new DrawGraph(currentFile).setVisible(true);
	this.setVisible(false);
	this.dispose();
    }

    private String[] s = new String[5];


    protected JButton backButton;
    private GraphDisplayPanel displayPanel;
    protected  InfoDisplayPanel explanationPanel;
    private  EdgeDisplayPanel graphInfoPanel;
    private JSeparator jSeparator1;
    private JSeparator jSeparator2;
    private JLabel titleLabel;
    private File currentFile;
    private int[] harary;
 
}
