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
 
public class DrawHelp extends JFrame {

    // State
    private JButton backButton;
    private JLabel titleLabel;
    private JLabel[] infoLabel;
    
    // Finals
    private final int xCoorLabels = 35;
    private final int yCoorLabels = 60;
    private final int labelSpacing = 20;


    /** Creates new form Help */
    public DrawHelp() {
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
        titleLabel.setText("Draw Graph"); 
        getContentPane().add(titleLabel, 
			     new AbsoluteConstraints(210, 10, -1, -1));

	String[] info = {"<html>The <b>Draw Graph</b> topic displays a "+
			 "graphical ",
			 "representation of a selected input file, given "+
			 "the file",
			 "is formatted correctly. Information concerning the",
			 "number of edges and the number of vertices in the",
			 "graph, in addition to a complete listing of "+
			 "the edges,",
			 "is also shown. If the graph does not contain a",
			 "hamiltonian cycle, it's vertices are layed "+
			 "out along",
			 "a circle in monotonically increasing order, as seen",
			 "<html>in <b>G</b>. If the graph is hamiltonian,"+
			 " however, and",
			 "there are twenty or fewer vertices, then the",
			 "vertices are drawn so that the cycle appears as",
			 "the perimeter of a polygon. In either case, a line",
			 "is drawn between a pair "+
			 "of vertices if there exists",
			 "an edge in the graph incident to both."};

	infoLabel = new JLabel[info.length];
	printLabels(info);

	
	// Display Images
	displayImage("draw.jpg",410,60,150,160);
	displayImage("draw2.jpg",420,230,150,100);
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
                new DrawHelp().setVisible(true);
            }
        });
    }

}