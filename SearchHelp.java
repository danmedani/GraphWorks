import javax.swing.JFileChooser;
import java.io.File;
import java.io.FileNotFoundException;
import java.awt.Image;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import org.netbeans.lib.awtextra.*;
 
public class SearchHelp extends JFrame {

    private JButton backButton;
    private JLabel titleLabel;
    private JLabel[] infoLabel;

    // Finals
    private final int xCoorLabels = 45;
    private final int yCoorLabels = 60;
    private final int labelSpacing = 20;


    /** Creates new form Help */
    public SearchHelp() {
        initComponents();
    }

    private void initComponents() {

	// Initialize
        backButton = new JButton();
        titleLabel = new JLabel();


	// Windowing
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new AbsoluteLayout());


	// Add components
        backButton.setText("Back");
        backButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                backButtonMouseClicked(evt);
            }
        });
        getContentPane().add(backButton, 
			     new AbsoluteConstraints(520, 420, 70, -1));


	// Add labels
        titleLabel.setFont(new Font("Helvetica", 1, 24)); 
        titleLabel.setText("Search"); 
        getContentPane().add(titleLabel, 
			     new AbsoluteConstraints(220, 10, -1, -1));
	String[] info = {"A fundamental step in many algorithms "+
			 "is to visit each",
			 "vertex and each edge. The class searches,"+
			 " depth first",
			 "search and breadth first search, both have "+
			 "complexity ",
			 "<html><b>O(|V|+|E|)</b> where <b>V</b> is the"+
			 " set of vertices and <b>E</b> is the set</html>",
			 "of edges. Due to this complexity measure, the"+
			 " search ",
			 "process does not normally play a dominant role in ",
			 "determining the complexity of an algorithm.",
			 "",
			 "<html> The <b>Search</b> topic provides"+
			 " interactive demonstrations",
			 "for both depth and breadth first searches."+
			 " In a depth",
			 "first search, one starts at the root "+
			 "and explores as",
			 "far as possible along each branch before"+
			 " backtracking.",
			 "In a breadth first search, one starts "+
			 "at the root and",
			 "explores to each neighbor before moving deeper into",
			 "the graph."};
			 
	infoLabel = new JLabel[info.length];
	printLabels(info);
   
	// Display Images
	displayImage("search1.jpg",430,30,150,110);
	displayImage("search2.jpg",430,141,150,160);
	displayImage("search3.jpg",430,310,150,110);

        pack();
    }
    
    
    /**
     * The <code>printLabels</code> method prints the windows labels.
     *
     * @param desc The <code>String[]</code> value that contains every label
     * line.
     */
    private void printLabels(String[] desc) {
	Font labelFont = new java.awt.Font("Helvetica", 0, 14);
	int yPos;
	for(int i = 0 ; i < desc.length; i ++) {
	    infoLabel[i] = new JLabel(desc[i]);
	    infoLabel[i].setFont(labelFont);
	    yPos = yCoorLabels + (labelSpacing * i);
	    getContentPane().add(infoLabel[i],
				 new AbsoluteConstraints(xCoorLabels, yPos,
							 -1, -1));
	}
    }
    
    /**
     * <code>displayImage</code> prints an image to the screen
     *
     * @param path a <code>String</code> value that specifies the location 
     * of the desired image
     * @param xPos an <code>int</code> value denoting the x-coordinate of the 
     * image
     * @param yPos an <code>int</code> value denoting the y-coordinate of the 
     * image
     * @param width an <code>int</code> value -- the width of the image
     * @param height an <code>int</code> value -- the height of the image
     */
    private void displayImage(String path, 
			      int xPos, int yPos,
			      int width, int height) {
	try {
	    InputStream in = ClassLoader.getSystemResourceAsStream(path);
	    BufferedImage image = ImageIO.read(in);
	    ImagePanel contentPane = new ImagePanel(image);
	    contentPane.setOpaque(false);
	    getContentPane().add(contentPane,
				 new AbsoluteConstraints(xPos,
							 yPos,
							 width,
							 height));
	}
	catch(IOException e) {
	    System.err.println("Error displaying pictures.");
	}
    }


    private void backButtonMouseClicked(MouseEvent evt) {
	MainMenu mm = new MainMenu();
	mm.setLocationRelativeTo( this );
	mm.setVisible(true);
	this.setVisible(false);
	this.dispose();
    }

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SearchHelp().setVisible(true);
            }
        });
    }

}