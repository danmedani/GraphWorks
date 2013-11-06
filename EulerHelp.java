import javax.swing.*;
import org.netbeans.lib.awtextra.*;
import java.awt.image.*;
import java.awt.Image;
import java.io.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import org.netbeans.lib.awtextra.*;

public class EulerHelp extends JFrame {

    // State
    private JButton backButton;
    private JLabel[] infoLabel;
    private JLabel titleLabel;

    // Finals
    private final int xCoorLabels = 45;
    private final int yCoorLabels = 60;
    private final int labelSpacing = 20;


    /** Creates new form Help */
    public EulerHelp() {
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
        backButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backButtonMouseClicked(evt);
            }
        });
        getContentPane().add(backButton, 
			     new AbsoluteConstraints(520, 420, 70, -1));
	
	// Add Labels	
        titleLabel.setFont(new java.awt.Font("Helvetica", 1, 24)); 
        titleLabel.setText("Eulerian Circuits"); 
        getContentPane().add(titleLabel, 
			     new AbsoluteConstraints(200, 10, -1, -1));
	
	String[] info = {"An Eulerian circuit is a circuit that"+
			 " includes every edge",
			 "in a graph exactly once. In order for a "+
			 "graph to contain",
			 "an Eulerian circuit, it is necessary and "+
			 "sufficient that",
			 "the graph be connected and that each of "+
			 "its vertices",
			 "has even degree.",
			 "",
			 "<html>The <b> Eulerian Circuit</b> topic provides"+
			 " two algorithms",
			 "<html>for finding Eulerian circuits. The first,"+
			 " <b>Fleury's</b>",
			 "<html><b>algorithm</b>, builds the circuit by "+
			 "repeatedly removing",
			 "edges from the graph and appending each one to the",
			 "growing circuit. In doing so, however, it must be",
			 "ensured that the edge being removed is not a bridge",
			 "of the current graph. The second algorithm, which",
			 "<html>utilizes the principles of <b>splicing</b>,"+
			 " locates multiple",
			 "circuits and then joins them together to form the",
			 "complete Eulerian circuit."};
	
	infoLabel = new JLabel[info.length];
	printLabels(info);


	// Display Images
	displayImage("euler1.jpg",430,30,150,180);
	displayImage("euler2.jpg",430,231,150,190);


        pack();
   }


    /**
     * The <code>printLabels</code> method prints the windows labels.
     *
     * @param desc The <code>String[]</code> value that contains every label
     * line.
     */
    private void printLabels(String[] desc) {
	java.awt.Font labelFont = new java.awt.Font("Helvetica", 0, 14);
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


    private void backButtonMouseClicked(java.awt.event.MouseEvent evt) {
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
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EulerHelp().setVisible(true);
            }
        });
    }

}