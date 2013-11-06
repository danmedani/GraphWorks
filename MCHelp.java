import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import org.netbeans.lib.awtextra.*;

public class MCHelp extends JFrame {

    // State
    private JButton backButton;
    private JLabel[] infoLabel;
    private JLabel titleLabel;
  
    // Finals
    private final int xCoorLabels = 35;
    private final int yCoorLabels = 60;
    private final int labelSpacing = 20;


    public MCHelp() {
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
	titleLabel.setFont(new java.awt.Font("Tahoma", 1, 24));
        titleLabel.setText("Minimal Cost Spanning Trees");
        getContentPane().add(titleLabel,
			     new AbsoluteConstraints(125, 10, -1, -1));
        
	String[] info = {"<html>Let <b>G = (V,E)</b> be a graph. A "+
			 "spanning subgraph of <b>G</b> is",
			 "<html>any subgraph with vertex set <b>V</b>."+
			 " A spanning tree is a",
			 "<html>spanning subgraph of <b>G</b> that is a tree.",
			 "",
			 "<html>A weighted graph <b>G = (V,E)</b> is"+
			 " a graph for which each",
			 "edge is assigned a real number called its "+
			 "weight. For",
			 "weighted graphs with each edge having a "+
			 "positive weight,",
			 "the weight of the graph is the sum of the "+
			 "weights of each",
			 "of its edges.",
			 "",
			 "A minimal cost spanning tree in a weighted "+
			 "graph is a",
			 "spanning tree that has minimal weight."};

	infoLabel = new JLabel[info.length];
	printLabels(info);



	// Display Images
	displayImage("mcst1.jpg",435,50,150,150);
	displayImage("mcst2.jpg",435,210,150,150);


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
                new MCHelp().setVisible(true);
            }
        });
    }

}