import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import org.netbeans.lib.awtextra.*;
import java.io.File;
import java.io.FileNotFoundException;

public class DegreeSequence extends JFrame {

    public DegreeSequence(boolean backEnabled) {
	this.backEnabled = backEnabled;
        initComponents();
    }    
    
    private void initComponents() {

	// Initialize
	titleLabel = new JLabel();
	backButton = new JButton();
	genDegSeqButton = new JButton();
	testDegSeqButton = new JButton();
	infoLabel1 = new JLabel();
        infoLabel2 = new JLabel();
	infoLabel3 = new JLabel();
	infoLabel4 = new JLabel();
	infoLabel5 = new JLabel();	


	// Windowing
	setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	getContentPane().setLayout(new AbsoluteLayout()); 
	setResizable(false);


	// Add components
	backButton.setText("Back");
        backButton.addMouseListener(new java.awt.event.MouseAdapter() {
		public void mouseClicked(java.awt.event.MouseEvent evt) {
		    backButtonMouseClicked(evt);
		}
	    });
        getContentPane().add(backButton, 
			     new AbsoluteConstraints(520, 420, 70, -1));
	if(!backEnabled) {
	    backButton.setVisible(false);
	    backButton.setEnabled(false);
	}

	genDegSeqButton.setText("Generate Degree Sequences");
        genDegSeqButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                genDegSeqButtonMouseClicked(evt);
            }
        });
        getContentPane().add(genDegSeqButton,
			     new AbsoluteConstraints(210, 190, -1, -1));

        testDegSeqButton.setText("Hakimi: Test Degree Sequence");
        testDegSeqButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                testDegSeqButtonMouseClicked(evt);
            }
        });
        getContentPane().add(testDegSeqButton, 
			     new AbsoluteConstraints(203, 230, -1, -1));


	// Add Labels
	titleLabel.setFont(new java.awt.Font("Tahoma", 1, 24));
        titleLabel.setText("Degree Sequences");
        getContentPane().add(titleLabel, 
			     new AbsoluteConstraints(180, 10, -1, -1));

	infoLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); 
	infoLabel1.setText("<html>Clicking on <b>Generate Degree "+
			   "Sequences</b> allows you to input a positive");
        getContentPane().add(infoLabel1, 
			     new AbsoluteConstraints(70, 60, -1, 36));

        infoLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); 
	infoLabel2.setText("<html>integer <b>n</b> and gives you the "+
			   "option to generate either all degree");	
        getContentPane().add(infoLabel2, 
			     new AbsoluteConstraints(70, 80, -1, 36));

	infoLabel3.setFont(new java.awt.Font("Tahoma", 0, 14));
        infoLabel3.setText("<html>sequences, or just the graphical "+
			   "degree sequences. Clicking on <b>Hakimi:</b>");
        getContentPane().add(infoLabel3, 
			     new AbsoluteConstraints(70, 100, -1, 36));

 	infoLabel4.setFont(new java.awt.Font("Tahoma", 0, 14));
        infoLabel4.setText("<html><b>Test Degree Sequence</b> allows "+
			   "you to use Hakimi's Theorem in order to");
        getContentPane().add(infoLabel4,
			     new AbsoluteConstraints(70, 120, -1, 36));

 	infoLabel5.setFont(new java.awt.Font("Tahoma", 0, 14));
        infoLabel5.setText("determine whether a given degree sequence"+
			   " is graphical or not.");
        getContentPane().add(infoLabel5, 
			     new AbsoluteConstraints(70, 140, -1, 36)); 


	
	// Display Images
	displayImage("hakJPG.jpg",70,275,450,100);

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
							 width,
							 height));
	}
	catch(IOException e) {
	    System.err.println("Error displaying pictures.");
	}
    }

  /**
     *  <code>backButtonMouseClicked</code> returns to Main Menu.
     *
     * @param evt a <code>MouseEvent</code> value
     */
    private void backButtonMouseClicked(java.awt.event.MouseEvent evt) {
        MainMenu mm = new MainMenu();
	mm.setLocationRelativeTo( this );
	mm.setVisible(true);
	this.setVisible(false);
    }

    private void genDegSeqButtonMouseClicked(java.awt.event.MouseEvent evt) {
	genSeq = new Generate();	
	genSeq.setLocationRelativeTo( this );
	genSeq.setVisible(true);
	this.setVisible(false);
	this.dispose();
    }

    private void testDegSeqButtonMouseClicked(java.awt.event.MouseEvent evt) {
	testSeq = new TestSequence();
	testSeq.setLocationRelativeTo( this );
	testSeq.setVisible(true);
	this.setVisible(false);
	this.dispose();
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
		public void run() {
		    DegreeSequence deg = new DegreeSequence(false);
		    deg.setLocationRelativeTo( null );
		    deg.setVisible(true);
		}
	    });
    }
    
    private JLabel titleLabel;
    private JLabel infoLabel1;
    private JLabel infoLabel2;
    private JLabel infoLabel3;
    private JLabel infoLabel4;
    private JLabel infoLabel5;   
    private JButton backButton;
    private JButton genDegSeqButton;
    private JButton testDegSeqButton;
    private Generate genSeq;
    private TestSequence testSeq;
    private boolean backEnabled;
    
}