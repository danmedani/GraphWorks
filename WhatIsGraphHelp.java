import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import org.netbeans.lib.awtextra.*;

public class WhatIsGraphHelp extends JFrame {

    // State
    private JButton backButton;
    private JLabel[] infoLabel;
    private JLabel titleLabel;
  
    // Finals
    private final int xCoorLabels = 14;
    private final int yCoorLabels = 40;
    private final int labelSpacing = 20;

    
    public WhatIsGraphHelp() {
        initComponents();
    }

    private void initComponents() {


	// Windowing
	setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new AbsoluteLayout());
	setMinimumSize(new Dimension(600, 450));

	// Initialize
        backButton = new JButton();
        titleLabel = new JLabel();
      

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
        titleLabel.setFont(new Font("Helvetica", 1, 24)); 
        titleLabel.setText("What is a Graph?"); 
        getContentPane().add(titleLabel, 
			     new AbsoluteConstraints(172, 10, -1, -1));

	String[] info = {"<html>A graph <b>G = (V,E)</b> consists of a"+
			 " finite nonempty",
			 "<html>set <b>V</b> of elements, called the "+
			 "vertices of the",
			 "<html>graph and a set <b>E</b> of unordered "+
			 "pairs of vertices",
			 "called the edges of the graph. The elements of an",
			 "<html>edge are called its ends. An edge with "+
			 "end <b>v</b> is said",
			 "<html>to be incident to the vertex <b>v</b>. "+
			 "Two edges with a",
			 "common end are said to be adjacent. The number of",
			 "edges incident to a vertex is the degree of"+
			 " the vertex.",
			 "",
			 "A vertex is odd or even depending on whether its",
			 "degree is odd or even. A vertex of degree one is",
			 "said to be an isolated vertex.",
			 "",
			 "<html>A subgraph <b>H(V1,E1)</b> of graph "+
			 "<b>G(V,E)</b> is a graph for",
			 "<html>which <b>V1</b> is a subset of <b>V</b>"+
			 " and the edges <b>E1</b> have both ",
			 "<html>their ends = <b>(V1,E1)</b>. A subgraph "+
			 "is called spanning",
			 "<html>when <b>V1 = V</b>. An induced subgraph"+
			 " <b>H</b> of <b>G</b> is a",
			 "<html>subgraph for which the edges of <b>H</b>"+
			 " are all the",
			 "<html>edges of <b>G</b> with both ends in <b>V1"+
			 "</b>."};

	infoLabel = new JLabel[info.length];
	printLabels(info);



	// Display Images
	displayImage("pic1JPG.jpg",385,1,200,190);	   
	displayImage("pic2JPG.jpg",385,190,200,230);

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

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new WhatIsGraphHelp().setVisible(true);
            }
        });
    }

}