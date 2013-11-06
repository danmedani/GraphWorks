import javax.swing.*;
import org.netbeans.lib.awtextra.*;
import java.io.*;

/**
 * <code>DrawGraph</code> is a form that allows users to choose a graph
 * and display it. It includes the functionality to find and display
 * hamiltonian cycles accordingly.
 *
 * @author Team 1
 * @version 1.0
 */
public class DrawGraph extends JFrame {

    // State
    private JButton backButton;
    private JButton browseButton;
    private JButton displayButton;
    private JLabel[] infoLabel;
    protected JTextField fileInputField;
    private JLabel titleLabel;
    private JFileChooser fileChooser;
    private File inputFile;
    private GraphFactory factory;
    private int[][] edges;
    private boolean backEnabled;

    // Finals
    private final int xCoorLabels = 50;
    private final int yCoorLabels = 70;
    private final int labelSpacing = 20;


    public DrawGraph(boolean backEnabled) {
	this.backEnabled = backEnabled;
	initComponents();
	loadDefaultFile();
    }
    
    /**
     * Creates a new <code>DrawGraph</code> instance.
     *
     * @param currentFile A <code>File</code> that will be used as the default
     * graph for this instance
     */
    public DrawGraph(File currentFile) { 
	backEnabled = true;
	initComponents();
	inputFile = currentFile;
	fileInputField.setText(inputFile.getName());
	
	try {
	    factory = new GraphFactory(inputFile);
	    edges = factory.getEdgeArray();	
	} catch (FileNotFoundException e){
	    System.out.println(e);
	} catch (InvalidInputException e){
	    fileInputField.setText("Incorrectly formatted input file.");
	}
    }
   
    private void initComponents() {

	// Windowing
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	setResizable(false);
        getContentPane().setLayout(new AbsoluteLayout());


	// Initialize
	titleLabel = new JLabel();
        fileInputField = new JTextField();
        browseButton = new JButton();
        displayButton = new JButton();
        backButton = new JButton();	
	fileChooser = new JFileChooser(".");


	// Add components
        fileInputField.setEditable(false);
        getContentPane().add(fileInputField, 
			     new AbsoluteConstraints(120, 280, 180, -1));

        browseButton.setText("Choose a Graph");
        browseButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                browseButtonMouseClicked(evt);
            }
        });
        getContentPane().add(browseButton,
			     new AbsoluteConstraints(320, 278, -1, -1));

        displayButton.setText("Display");
        displayButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                displayButtonMouseClicked(evt);
            }
        });
        getContentPane().add(displayButton, 
			     new AbsoluteConstraints(240, 340, 110, -1));

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


	
	// Add labels
        titleLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); 
        titleLabel.setText("Draw Graph");
        getContentPane().add(titleLabel, 
			     new AbsoluteConstraints(210, 10, -1, -1));
	String[] info = {"<html>After selecting a graph, click "+"<b>"+
			 "Display</b> to view a graphical representation ",
			 "of your input file. The graph's vertices are "+
			 "layed out along a circle, and a",
			 "line is drawn between a pair of vertices if "+
			 "there exists an edge in the",
			 "graph incident to both. In addition, a "+
			 "complete listing of the edges,",
			 "along with information concerning the "+
			 "cardinality of both the edge",
			 "and vertex sets is displayed at the bottom "+
			 "of the screen. The same",
			 "<html>graph will be used for subsequent "+
			 "executions of <b>Display</b> until you",
			 "return to the main menu or choose a new graph."};


	infoLabel = new JLabel[info.length];
	printLabels(info);
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
     * The <code>loadDefaultFile</code> method simply loads the default file.
     *
     */
    private void loadDefaultFile() {
	String runningDirectory = "";
	try {
	    // First, We assuming its being run from a jar file...
	    runningDirectory = (new File(Connect.class.getProtectionDomain()
					 .getCodeSource().
					 getLocation().toURI().getPath()))
.getParent();
	    inputFile = new File(runningDirectory + "/DefaultDisplayGraph");
	    fileInputField.setText("DefaultDisplayGraph");

	    factory = new GraphFactory(inputFile);
	    edges = factory.getEdgeArray();
	}
	catch(Exception e) { 
	    // So that didn't work. Let's see if they are running it from
	    // terminal
	    try{
		inputFile = new File("DefaultDisplayGraph");
		fileInputField.setText("DefaultDisplayGraph");

		factory = new GraphFactory(inputFile);
		edges = factory.getEdgeArray();
	    }
	    catch(Exception e1) {
		fileInputField.setText("Error loading default file!");
		inputFile = null;
	    }
	}
    }


    /**
     * <code>browseButtonMouseClicked</code> opens a new file chooser to 
     * browse for a file that will be used to fill an edge array.
     *
     * @param evt a <code>java.awt.event.MouseEvent</code> value
     */
    private void browseButtonMouseClicked(java.awt.event.MouseEvent evt) {
	int status = fileChooser.showOpenDialog(null);
	if (status == JFileChooser.APPROVE_OPTION){
            inputFile = fileChooser.getSelectedFile();
            fileInputField.setText(inputFile.getName());
        
	    try {
		factory = new GraphFactory(inputFile);
		edges = factory.getEdgeArray();	
	    } catch (FileNotFoundException e){
		fileInputField.setText("Badly formatted input file.");
		inputFile = null;
	    } catch (Exception e){
		fileInputField.setText("Badly formatted input file.");
		inputFile = null;
	    }
	}
    }

    /**
     * <code>displayButtonMouseClicked</code> creates a new graph draw object
     * to display the graph input from the file chooser.
     *
     * @param evt a <code>java.awt.event.MouseEvent</code> value
     */
    private void displayButtonMouseClicked(java.awt.event.MouseEvent evt) {
	
	if (inputFile == null){
	    fileInputField.setText("Please choose a graph first.");
	} else {
	    GraphDisplay gd = new GraphDisplay(edges, inputFile);
	    gd.setLocationRelativeTo( this );
	    gd.setVisible(true);
	    this.setVisible(false);
	    this.dispose();
	}
    }

    /**
     * <code>backButtonMouseClicked</code> returns to Main Menu.
     *
     * @param evt a <code>java.awt.event.MouseEvent</code> value
     */
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
                new DrawGraph(false).setVisible(true);
            }
        });
    }

}
