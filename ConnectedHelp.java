import javax.swing.*;
import org.netbeans.lib.awtextra.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

public class ConnectedHelp extends JFrame {
  
    // State
    private JButton backButton;
    private JLabel titleLabel;
    private JLabel[] infoLabel;    

    // Finals
    private final int xCoorLabels = 45;
    private final int yCoorLabels = 60;
    private final int labelSpacing = 20;



    public ConnectedHelp() {
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


	// Add labels
        titleLabel.setFont(new java.awt.Font("Helvetica", 1, 24)); 
        titleLabel.setText("Connected Components"); 
        getContentPane().add(titleLabel, 
			     new AbsoluteConstraints(145, 10, -1, -1));

	String[] info = {"A graph is connected if there exists"+
			 " a path between ",
			 "each pair of vertices. A graph that is "+
			 "not connected is",
			 "called disconnected.  A "+
			 "cut vertex is defined as",
			 "a vertex whose removal, along with all "+
			 "its incident",
			 "edes, will cause the graph to become disconnected. ",
			 "A graph is nonseparable if it " +
			 "contains no cut vertices.",
			 "",
			 "Maximal subgraphs that are connected "+
			 "or nonseparable",
			 "are called the connected and nonseparable"+
			 " components ",
			 "of the graph, respectively. These notions "+
			 "are important",
			 "when a property of a graph can be determined by ",
			 "verifying the property for each of its components",
			 "separately.",
			 "",
			 "<html>The <b>Connected</b> topic displays "+
			 "the connected or",
			 "nonseparable components of a given graph. Each",
			 "component is shown in a different color, and",
			 "articulation points are drawn in bold."};

	infoLabel = new JLabel[info.length];
	printLabels(info);
   
	
	displayImage("connHelp1.jpg",430,45,150,140);
	displayImage("connHelp2.jpg",430,190,150,115);
	displayImage("connHelp3.jpg",425,310,160,100);
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
    private void displayImage(String path, int xPos, int yPos,
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
                new ConnectedHelp().setVisible(true);
            }
        });
    }


}