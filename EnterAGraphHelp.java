import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import org.netbeans.lib.awtextra.*;

public class EnterAGraphHelp extends JFrame {

    public EnterAGraphHelp() {
        initComponents();
    }

    private void initComponents() {

	// Windowing
	getContentPane().setLayout(new AbsoluteLayout());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new AbsoluteLayout());


	// Initialize
        backButton = new JButton();
        jLabel1 = new JLabel();
	jLabel2 = new JLabel();
	jLabel3 = new JLabel();
	jLabel4 = new JLabel();
	jLabel5 = new JLabel();
	jLabel6 = new JLabel();
	jLabel7 = new JLabel();
	jLabel8 = new JLabel();
	jLabel9 = new JLabel();
	jLabel10 = new JLabel();
	jLabel11 = new JLabel();
	jLabel12 = new JLabel();
	jLabel13 = new JLabel();
	jLabel14 = new JLabel();
	jLabel15 = new JLabel();
	jLabel16 = new JLabel();
	jLabel17 = new JLabel();
	jLabel18 = new JLabel();
	jLabel19 = new JLabel();

	// Add Components
        backButton.setText("Back");
        backButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backButtonMouseClicked(evt);
            }
        });
        getContentPane().add(backButton,
			     new AbsoluteConstraints(520, 420, 70, -1));


	// Add Labels	
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); 
        jLabel1.setText("Entering a Graph"); 
        getContentPane().add(jLabel1, 
			     new AbsoluteConstraints(190, 10, -1, -1));

	jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); 
        jLabel2.setText("To enter a graph into Graphworks, you must"+
			" first create and save a"); 
        getContentPane().add(jLabel2, 
			     new AbsoluteConstraints(35, 40, -1, 36));

	jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); 
        jLabel3.setText("text file. This file, which specifies a "+
			"list of adjacent vertex pairs, needs");
        getContentPane().add(jLabel3, 
			     new AbsoluteConstraints(35, 60, -1, 36)); 

	jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); 
        jLabel4.setText("<html>to be formatted correctly. The following"+
			" formats"+
			" are accepted (for <b>G</b>):"); 
        getContentPane().add(jLabel4, 
			     new AbsoluteConstraints(35, 80, -1, 36));






	jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); 
	jLabel6.setText("<html><b>Space Separated:</b> 0 1 1 2 1 3 0 4"); 
	getContentPane().add(jLabel6, 
			     new AbsoluteConstraints(75, 105, -1, 36));

	jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); 
	jLabel5.setText("<html><b>Comma Separated:</b> 0,1,1,2,1,3,0,4"); 
	getContentPane().add(jLabel5, 
			     new AbsoluteConstraints(75, 125, -1, 36));

	jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); 
        jLabel8.setText("<html><b>Dash Separated:</b> 0-1,1-2,1-3,0-4"); 
        getContentPane().add(jLabel8, 
			     new AbsoluteConstraints(75, 145, -1, 36));

	jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); 
        jLabel9.setText("<html><b>Double Dash Separated:</b>  0--1,1--"+
			"2,1--3,0--4"); 
        getContentPane().add(jLabel9, 
			     new AbsoluteConstraints(75, 165, -1, 36));


	jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); 
        jLabel7.setText(""); 
        getContentPane().add(jLabel7, 
			     new AbsoluteConstraints(35, 325, -1, 36));

	jLabel15.setFont(new java.awt.Font("Tahoma", 0, 14)); 
        jLabel15.setText("..."); 
        getContentPane().add(jLabel15, 
			     new AbsoluteConstraints(75, 325, -1, 36));


	jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); 
	jLabel10.setText("<html><b>McKay Style:</b>"); 
        getContentPane().add(jLabel10, 
			     new AbsoluteConstraints(75, 185, -1, 36));

	jLabel16.setFont(new java.awt.Font("Tahoma", 0, 14)); 
	jLabel16.setText("5 4"); 
        getContentPane().add(jLabel16, 
			     new AbsoluteConstraints(75, 205, -1, 36));

	jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); 
	jLabel11.setText("0 0 0 1 1 0 0 2 1 0 0 3 0 0 0 4"); 
        getContentPane().add(jLabel11, 
			     new AbsoluteConstraints(75, 225, -1, 36));
	
	jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); 
	jLabel12.setText("<html><b>DIMACS:</b> (http://prolland.free.f"+
			 "r/works/research/dsat/dimacs.html)"); 
        getContentPane().add(jLabel12, 
			     new AbsoluteConstraints(75, 245, -1, 36));

	jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); 
	jLabel13.setText("p edge 5 4"); 
        getContentPane().add(jLabel13, 
			     new AbsoluteConstraints(75, 265, -1, 36));

	jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); 
	jLabel14.setText("e 0 1"); 
        getContentPane().add(jLabel14, 
			     new AbsoluteConstraints(75, 285, -1, 36));


	jLabel17.setFont(new java.awt.Font("Tahoma", 0, 14)); 
	jLabel17.setText("e 0 2"); 
        getContentPane().add(jLabel17, 
			     new AbsoluteConstraints(75, 305, -1, 36));


	jLabel18.setFont(new java.awt.Font("Tahoma", 0, 14)); 
	jLabel18.setText("Weighted graphs are also accepted. Here is a"+
			 "n example (all edges are weight 9):"); 
        getContentPane().add(jLabel18, 
			     new AbsoluteConstraints(35, 345, -1, 36));



	jLabel19.setFont(new java.awt.Font("Tahoma", 0, 14)); 
	jLabel19.setText("<html><b>Weighted Dash Separated:</b> w:0-1-9,"+
			 "0-2-9,1-3-9,0-4-9</html>"); 
        getContentPane().add(jLabel19, 
			     new AbsoluteConstraints(75, 365, -1, 36));



	// Display Images
	displayImage("enterJPG.jpg",425,115,130,130);
	displayImage("G.jpg",475,228,20,20);

        pack();
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
							 width,height));
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
                new EnterAGraphHelp().setVisible(true);
            }
        });
    }

    private JButton backButton;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JLabel jLabel7;
    private JLabel jLabel8;
    private JLabel jLabel9;
    private JLabel jLabel10;
    private JLabel jLabel11;
    private JLabel jLabel12;
    private JLabel jLabel13;
    private JLabel jLabel14;
    private JLabel jLabel15; 
    private JLabel jLabel16;  
    private JLabel jLabel17;  
    private JLabel jLabel18;    
    private JLabel jLabel19;
}