
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import org.netbeans.lib.awtextra.*;

public class SmallHelp extends JFrame {

    // State
    private JButton backButton;
    private JLabel[] infoLabel;
    private JLabel titleLabel;
  
    // Finals
    private final int xCoorLabels = 35;
    private final int yCoorLabels = 60;
    private final int labelSpacing = 20;


    /** Creates new form Help */
    public SmallHelp() {
        initComponents();
    }

    private void initComponents() {

	// Initialize
        backButton= new JButton();
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


	// Add labels
        titleLabel.setFont(new java.awt.Font("Helvetica", 1, 24)); 
        titleLabel.setText("Identify Small Graphs"); 
        getContentPane().add(titleLabel, 
			     new AbsoluteConstraints(160, 10, -1, -1));

	String[] info = {"For a given number of vertices and edges, "+
			 "there are a ",
			 "limited number of possible graphs that can be",
			 "constructed. Within each set of graphs on a given",
			 "number of vertices and edges, each different graph",
			 "is given a number called its Harary number, to",
			 "distinguish it from other graphs on the same number",
			 "of vertices and edges.",
			 "",
			 "<html>The <b>Identify Small Graphs</b> module"+
			 " takes a graph on",
			 "two through five vertices as its input and "+
			 "in turn displays",
			 "the graph's of vertex and edge counts in"+
			 " addition to",
			 "the graph's number in the Harary Catalogue"+
			 " (Harary, F,",
			 "Graph Theory, Addison-Wesley, 1969)."};

	infoLabel = new JLabel[info.length];
	printLabels(info);
	

	// Display Image
	displayImage("harary.jpg",430,60,150,310);

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
                new SmallHelp().setVisible(true);
            }
        });
    }
}