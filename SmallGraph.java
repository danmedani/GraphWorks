import javax.swing.*;
import org.netbeans.lib.awtextra.*;
import java.io.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import org.netbeans.lib.awtextra.*;

/**
 * <code>SmallGraph</code> is a form containing buttons and listeners to 
 * eventually display the small-graph properties of a graph
 *
 * @author Team 1
 * @version 1.0
 */
public class SmallGraph extends JFrame {
 
    // State
    private JButton backButton;
    private JButton browseButton;
    private JLabel[] infoLabel;
    private JTextField fileInputField;
    private JButton SmallGraphButton;
    private JLabel titleLabel;
    private HararyNumber harary;
    private JFileChooser fileChooser;
    private File inputFile;
    private GraphFactory factory;
    private int[][] edges;
    private Graph graph;
    private boolean backEnabled;

    // Finals
    private final int xCoorLabels = 35;
    private final int yCoorLabels = 60;
    private final int labelSpacing = 20;
    
    
    /** Creates new form Search */
    public SmallGraph(boolean backEnabled) {
	this.backEnabled = backEnabled;
	initComponents();
	loadDefaultFile();
    }

    /**
     * Creates a new <code>SmallGraph</code> instance.
     *
     * @param currentFile A <code>File</code> that will be used as the default
     * graph for this instance
     */
    public SmallGraph(File currentFile) {
	backEnabled = true;
	initComponents();
	inputFile = currentFile;  
	fileInputField.setText(inputFile.getName());
        
	try {
	    factory = new GraphFactory(inputFile);
	    edges = factory.getEdgeArray();
	    graph = new AdjacencyList(edges);	
	} catch (FileNotFoundException e){
	    System.out.println(e);
	} catch (InvalidInputException e){
	    fileInputField.setText("Badly formatted input file.");
	}
	
    }

    /** This method is called from within the constructor to
     * initialize the form.
     */
    private void initComponents() {

	// Windowing
	setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(600, 443)); 
	setResizable(false);
        getContentPane().setLayout(new AbsoluteLayout());

		
	//Initialize
        titleLabel = new JLabel();
	fileInputField = new JTextField();
        browseButton = new JButton();
        backButton = new JButton();
        SmallGraphButton = new JButton();
	fileChooser = new JFileChooser(".");

       

	// Add Components
        fileInputField.setEditable(false);
        getContentPane().add(fileInputField, 
			     new AbsoluteConstraints(45, 252, 180, -1));

        browseButton.setText("Choose a Graph");
        browseButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                browseButtonMouseClicked(evt);
            }
        });
        getContentPane().add(browseButton, 
			     new AbsoluteConstraints(240, 250, 155, -1));

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

        SmallGraphButton.setText("View Graph");
	SmallGraphButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SmallGraphButtonMouseClicked(evt);
            }
        });
        getContentPane().add(SmallGraphButton,
			     new AbsoluteConstraints(185, 310, 140, -1));

	

	// Add labels
        titleLabel.setFont(new java.awt.Font("Helvetica", 1, 24));
        titleLabel.setText("Small Graphs");
        getContentPane().add(titleLabel, 
			     new AbsoluteConstraints(200, 10, -1, -1));
	String[] info = {"<html><b>Small Graphs</b> only supports "+
			 "graphs with five or fewer",
			 "<html>vertices. Once a graph has been selected,"+
			 " click <b>View</b>",
			 "<html><b>Graph</b> to view its small-graph prop"+
			 "erties. The same",
			 "<html>graph will be used for subsequent executi"+
			 "ons of",
			 "<html><b>View Graph</b> until you return to the "+
			 "main menu or",
			 "choose a new graph."};
	infoLabel = new JLabel[info.length];
	printLabels(info);


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
	    inputFile = new File(runningDirectory + "/DefaultSmallGraph");
	    fileInputField.setText("DefaultSmallGraph");

	    factory = new GraphFactory(inputFile);
	    edges = factory.getEdgeArray();
	    graph = new AdjacencyList(edges);
	}
	catch(Exception e) { 
	    // So that didn't work. Let's see if they are running it from
	    // terminal
	    try{
		inputFile = new File("DefaultSmallGraph");
		fileInputField.setText("DefaultSmallGraph");

		factory = new GraphFactory(inputFile);
		edges = factory.getEdgeArray();
		graph = new AdjacencyList(edges);
	    }
	    catch(Exception e1) {
		fileInputField.setText("Error loading default file!");
		inputFile = null;
	    }
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


    /**
     * <code>browseButtonMouseClicked</code> opens a new file chooser and 
     * builds a new graph based on the file input.
     * 
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
		graph = new AdjacencyList(edges);
		
		if(graph.getNumVert() > 5) {
		    fileInputField.setText("Graph is too big!");
		    inputFile = null;
		}
	
	    } catch (FileNotFoundException e){
		fileInputField.setText("File not found.");
		inputFile = null;
	    } catch (InvalidInputException e){
		fileInputField.setText("Badly formatted input file.");
		inputFile = null;
	    }
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
     * <code>dfSearchButtonMouseClicked</code> creates a new EulerCircuit 
     * object.
     *
     * @param evt a <code>java.awt.event.MouseEvent</code> value
     */
    private void SmallGraphButtonMouseClicked(java.awt.event.MouseEvent evt) {
	if (inputFile == null){
	    fileInputField.setText("Enter a file first.");
	} else {

	    harary = new HararyNumber(graph);
	    int[] hNum = harary.getHararyNumber();

	    DrawSmallGraph dsg = new DrawSmallGraph(edges,hNum,
						    inputFile);

	    dsg.setLocationRelativeTo( this );
	    dsg.setVisible(true);
	    this.setVisible(false);
	    this.dispose();
	}
    }

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SmallGraph(false).setVisible(true);
            }
        });
    }

 
}
