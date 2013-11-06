import java.io.File;
import java.util.Scanner;
import javax.swing.*;
import org.netbeans.lib.awtextra.*;

/**
 * <code>TestSequence</code> Generates the graphical user interface for the 
 * Test Sequence component of GraphWorksII.
 *
 * @author Team1
 * @version 1.0
 */
public class TestSequence extends JFrame {
    
     // To browse for input files
    File inputFile;                            // Holds a sequence to be tested
    boolean browsedLast = false;               // Mark that a file is open
    String fileName = "browsed";               // Name of input file
    
    /** Creates new form TestSequence */
    public TestSequence() {  
        initComponents();   
    }
    
    private void initComponents() {
	
        fileChooser = new JFileChooser(".");
        titleLabel = new JLabel();
        chooseFileLabel = new JLabel();
        jLabel3 = new JLabel();
        fileInputField = new JTextField();
        browseButton = new JButton();
        jScrollPane1 = new JScrollPane();
        output = new JTextArea();
        checkSeqButton = new JButton();
        backButton = new JButton();
	
	/* Associate events with operations */
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); 
	setResizable(false);
	getContentPane().setLayout(new AbsoluteLayout());
  
	
	/*  Provide text for the labels */
        titleLabel.setFont(new java.awt.Font("Lucida Sans", 1, 24));
        titleLabel.setText("Test Degree Sequence");	
        chooseFileLabel.setText("Choose an input file:");
	fileInputField.setEditable(false);
        jLabel3.setText("Results:");
	
	
        fileInputField.addKeyListener(new java.awt.event.KeyAdapter() {
		public void keyTyped(java.awt.event.KeyEvent evt) {
		    fileInputFieldKeyTyped(evt);
		}
	    });
	
        browseButton.setText("Choose a File");
        browseButton.addActionListener(new java.awt.event.ActionListener() {
		public void actionPerformed(java.awt.event.ActionEvent evt) {
		    browseButtonActionPerformed(evt);
		}
	    });
	
        output.setColumns(20);
        output.setEditable(false);
        output.setRows(5);
        jScrollPane1.setViewportView(output);
	
        checkSeqButton.setText("Check Sequence");
        checkSeqButton.addMouseListener(new java.awt.event.MouseAdapter() {
		public void mouseClicked(java.awt.event.MouseEvent evt) {
		    checkSeqButtonMouseClicked(evt);
		}
	    });
	
        backButton.setText("Back");
        backButton.addMouseListener(new java.awt.event.MouseAdapter() {
		public void mouseClicked(java.awt.event.MouseEvent evt) {
		    backButtonMouseClicked(evt);
		}
	    }); 
	
	getContentPane().add(titleLabel, 
			     new AbsoluteConstraints(100, 10, -1, -1));
	getContentPane().add(chooseFileLabel, 
			     new AbsoluteConstraints(35, 65, -1, -1));
	getContentPane().add(jLabel3, 
			     new AbsoluteConstraints(50, 160, -1, -1));
	getContentPane().add(browseButton, 
			     new AbsoluteConstraints(315, 83, -1, -1));
	getContentPane().add(checkSeqButton, 
			     new AbsoluteConstraints(160, 127, -1, -1));
	getContentPane().add(backButton, 
			     new AbsoluteConstraints(400, 300, -1, -1));
	getContentPane().add(output, 
			     new AbsoluteConstraints(50, 180, 340, 80));
	getContentPane().add(fileInputField,
			     new AbsoluteConstraints(35, 85, 260, 20));
	

	// Load the default file
	String runningDirectory = "";
	try {
	    // First, We assuming its being run from a jar file...
	    runningDirectory = (new File(Connect.class.getProtectionDomain()
					 .getCodeSource().
					 getLocation().toURI().getPath()))
		.getParent();
	    inputFile = new File(runningDirectory + "/DefaultDegreeSequence");
	    fileInputField.setText("DefaultDegreeSequence");

	}
	catch(Exception e) { 
	}

	if(!inputFile.exists()) {
	    // So that didn't work. Let's see if they are running it 
	    // from terminal
	    try{
		inputFile = new File("DefaultDegreeSequence");
		fileInputField.setText("DefaultDegreeSequence");
		
	    }
	    catch(Exception e1) {
		fileInputField.setText("Error loading default file!");
		inputFile = null;
	    }
	}
	

	browsedLast = true;
	
        pack();
    }
    private void browseButtonActionPerformed(java.awt.event.ActionEvent evt) {
	int status = fileChooser.showOpenDialog(null);
        if (status == JFileChooser.APPROVE_OPTION){
            inputFile = fileChooser.getSelectedFile();
            fileInputField.setText(inputFile.getName());
	    browsedLast = true;
	}
	
    }
    private void checkSeqButtonMouseClicked(java.awt.event.MouseEvent evt) {
	if (!browsedLast){                       // No file input
            fileName = fileInputField.getText();  // Input file name
            inputFile = new File(fileName);      // Initialize new input file
        }
	if(!(fileInputField.getText().equals("") ||
	     fileInputField.getText().equals("Please select an "+
					     "input file!"))) {
	    String inputSequence = "";     
	    try{
		Scanner inFile = new Scanner(inputFile); 
		inputSequence = inFile.next();           
		
		inputSequence = inputSequence.replace(",", ""); 
		output.setText("");
		Hakimi seqTester = new Hakimi(); 
		if (seqTester.testSequence(inputSequence)) {  
		    output.append("\n\n\t" + inputSequence +
				  ":\tGraphical");
		} else {  // Non-graphical sequence
		    output.append("\n\n\t" + inputSequence + 
				  ":\tNot Graphical");
		}
	    } catch (Exception e){
		output.setText("\n\tError reading the input file"+
			       ".\n\tExample: 0,1,1,2,3,4,2,1,3");
	    }
	}
	else {
	    fileInputField.setText("Please select an input file!");
	}
	
    }

    private void fileInputFieldKeyTyped(java.awt.event.KeyEvent evt) {
        browsedLast = false;
    }
   
    private void backButtonMouseClicked(java.awt.event.MouseEvent evt) {
        DegreeSequence deg = new DegreeSequence(true);
	deg.setLocationRelativeTo( this );
	deg.setVisible(true);
	this.setVisible(false);
	this.dispose();
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
		public void run() {    // Start running java thread
		    new TestSequence().setVisible(true);
		}
	    });
    }
    
    private JButton browseButton;
    private JButton checkSeqButton;
    private JTextField fileInputField;
    private JFileChooser fileChooser;
    private JLabel titleLabel;
    private JLabel chooseFileLabel;
    private JLabel jLabel3;
    private JScrollPane jScrollPane1;
    private JButton backButton;
    private JTextArea output;
}
