import javax.swing.*;
import org.netbeans.lib.awtextra.*;
import java.io.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import org.netbeans.lib.awtextra.*;

/**
 * <code>Euler</code> is a form containing buttons and listeners to choose 
 * a file and then generate a Euler Circuit.
 *
 * @author Team 1
 * @version 1.0
 */
public class Euler extends JFrame {

    // State
    private JButton backButton;
    private JButton browseButton;
    private JLabel[] infoLabel;
    private JTextField fileInputField;
    private JButton EulerButtonFleury;  
    private JButton EulerButtonSplice;
    private JButton CompareButton;
    private JLabel titleLabel;
    private EulerSearchFleury eulerFleury;
    private EulerSearchSplice eulerSplice;
    private JFileChooser fileChooser;
    private File inputFile;
    private GraphFactory factory;
    private int[][] edges;
    private Graph graphFleury;
    private Graph graphSplice;  
    private boolean backEnabled;


    // Finals
    private final int xCoorLabels = 35;
    private final int yCoorLabels = 60;
    private final int labelSpacing = 20;



    public Euler(boolean backEnabled) {
	this.backEnabled = backEnabled;
        initComponents();
	loadDefaultFile();
    }

    /**
     * Creates a new <code>Search</code> instance.
     *
     * @param currentFile A <code>File</code> that will be used as the default
     * graph for this instance
     */
    public Euler(File currentFile) {
	backEnabled = true;
	initComponents();
	inputFile = currentFile;  
	fileInputField.setText(inputFile.getName());
        
	try {
	    factory = new GraphFactory(inputFile);
	    edges = factory.getEdgeArray();
	    graphFleury = new AdjacencyList(edges);	
	    graphSplice = new AdjacencyList(edges);	
	} catch (FileNotFoundException e){
	    fileInputField.setText("File not found!");
	    inputFile = null;
	} catch (Exception e){
	    fileInputField.setText("Badly formatted input file.");
	    inputFile = null;
	}
	
    }

    /** This method is called from within the constructor to
     * initialize the form.
     */
    private void initComponents() {

	// Windowing
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(600, 443));
        getContentPane().setLayout(new AbsoluteLayout());
        setResizable(false);

	// Initialize
        titleLabel = new JLabel();
        fileInputField = new JTextField();
        browseButton = new JButton();
        backButton = new JButton();
        EulerButtonFleury = new JButton();
        EulerButtonSplice = new JButton();
	CompareButton = new JButton();
	fileChooser = new JFileChooser(".");



	// Add components
        fileInputField.setEditable(false);
        getContentPane().add(fileInputField, 
			     new AbsoluteConstraints(45, 270, 180, -1));

        browseButton.setText("Choose a Graph");
        browseButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                browseButtonMouseClicked(evt);
            }
        });
        getContentPane().add(browseButton, 
			     new AbsoluteConstraints(240, 268, 155, -1));

        EulerButtonFleury.setText("Fleury");
	EulerButtonFleury.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                EulerButtonFleuryMouseClicked(evt);
            }
        });
        getContentPane().add(EulerButtonFleury, 
			     new AbsoluteConstraints(40, 330, 90, -1));
    
	EulerButtonSplice.setText("Splice");
	EulerButtonSplice.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                EulerButtonSpliceMouseClicked(evt);
            }
        });
        getContentPane().add(EulerButtonSplice, 
			     new AbsoluteConstraints(175, 330, 90, -1));

	CompareButton.setText("Compare");
	CompareButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CompareButtonMouseClicked(evt);
            }
        });
        getContentPane().add(CompareButton, 
			     new AbsoluteConstraints(310, 330, 90, -1));


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
        titleLabel.setFont(new java.awt.Font("Helvetica", 1, 24));
        titleLabel.setText("Eulerian Circuits");
        getContentPane().add(titleLabel,
			     new AbsoluteConstraints(170, 10, -1, -1));

	String[] info = {"To begin, you must select a graph file by"+
			 " clicking on",
			 "<html>the <b>Choose a Graph</b> button."+
			 " Once a graph has been",
			 "<html>selected, click <b>Fleury</b> to find"+
			 " an Eulerian circuit using",
			 "<html>Fleury's algorithm, <b>Splice</b> to"+
			 " do so using the splicing",
			 "<html>method, or <b>Compare</b> to look at"+
			 " both methods side by",
			 "side. The same graph will be used for "+
			 "subsequent",
			 "executions of the algorithms until you"+
			 " return to the",
			 "main menu or choose a new graph."};
	infoLabel = new JLabel[info.length];
	printLabels(info);


	// Display Images
	displayImage("euler1.jpg",430,30,150,180);
	displayImage("euler2.jpg",430,231,150,190);

	
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
	    inputFile = new File(runningDirectory + "/DefaultEulerGraph");
	    fileInputField.setText("DefaultEulerGraph");

	    factory = new GraphFactory(inputFile);
	    edges = factory.getEdgeArray();
	    graphFleury = new AdjacencyList(edges);	
	    graphSplice = new AdjacencyList(edges);
	}
	catch(Exception e) {
	    // So that didn't work. Let's see if they are running it from 
	    // terminal
	    try{
		inputFile = new File("DefaultEulerGraph");
		fileInputField.setText("DefaultEulerGraph");

		factory = new GraphFactory(inputFile);
		edges = factory.getEdgeArray();
		graphFleury = new AdjacencyList(edges);	
		graphSplice = new AdjacencyList(edges);
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
		graphFleury = new AdjacencyList(edges);	
		graphSplice = new AdjacencyList(edges);	
	    } catch (FileNotFoundException e){
		fileInputField.setText("File not found!");
		inputFile = null;
	    } catch (Exception e){
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
     *  object.
     *
     * @param evt a <code>java.awt.event.MouseEvent</code> value
     */
    private void EulerButtonSpliceMouseClicked(java.awt.event.MouseEvent evt) {
	if (inputFile == null){
	    fileInputField.setText("Please choose a graph first.");
	} else {

	    // Build an array of all possible tree edges

	    int numEdges = 0;
	    for (int i=0; i < graphSplice.getNumVert(); i++) {
		numEdges += graphSplice.getDegree(i);	
	    }	    
	    numEdges /= 2;
	    	    
	    int[][][] treeEdges = new int[graphSplice.getNumVert()]
		[numEdges][3];
	    
	    for(int i = 0; i < graphSplice.getNumVert(); i ++) {
		graphSplice = new AdjacencyList(edges);
		eulerSplice = new EulerSearchSplice(graphSplice);	
		treeEdges[i] = eulerSplice.getTreeEdges(i);
	    }

	    DrawEuler ds = new DrawEuler(edges,treeEdges,
					 inputFile,"Splicing Algorithm");
	    ds.setLocationRelativeTo( this );
	    ds.setVisible(true);
	    this.setVisible(false);
	    this.dispose();
	    }
    } 
    /**
     * <code>dfSearchButtonMouseClicked</code> creates a new EulerCircuit
     *  object.
     *
     * @param evt a <code>java.awt.event.MouseEvent</code> value
     */
    private void CompareButtonMouseClicked(java.awt.event.MouseEvent evt) {
	if (inputFile == null){
	    fileInputField.setText("Please choose a graph first.");
	} else {

	    // Build an array of all possible tree edges

	    int numEdges = 0;
	    for (int i=0; i < graphSplice.getNumVert(); i++) {
		numEdges += graphSplice.getDegree(i);	
	    }	    
	    numEdges /= 2;
	    	    
	    int[][][] treeEdgesFleury = new int[graphFleury.getNumVert()]
		[numEdges][3];
	    int[][][] treeEdgesSplice = new int[graphSplice.getNumVert()]
		[numEdges][3];
	    
	    for(int i = 0; i < graphSplice.getNumVert(); i ++) {
		graphFleury = new AdjacencyList(edges);
		graphSplice = new AdjacencyList(edges);
		
		eulerSplice = new EulerSearchSplice(graphSplice);	
		eulerFleury = new EulerSearchFleury(graphFleury);

		
		treeEdgesSplice[i] = eulerSplice.getTreeEdges(i);
		treeEdgesFleury[i] = eulerFleury.getTreeEdges(i);	
		
	    }
	    CompareEuler cE = new CompareEuler(edges, treeEdgesFleury, 
					       treeEdgesSplice,inputFile);
	    cE.setLocationRelativeTo( this );
	    cE.setVisible(true);
	    this.setVisible(false);
	    this.dispose();
	    }
    }

    /**
     * <code>dfSearchButtonMouseClicked</code> creates a new EulerCircuit
     *  object.
     *
     * @param evt a <code>java.awt.event.MouseEvent</code> value
     */
    private void EulerButtonFleuryMouseClicked(java.awt.event.MouseEvent evt) {
	if (inputFile == null){
	    fileInputField.setText("Please choose a graph first.");
	} else {

	    // Build an array of all possible tree edges

	    int numEdges = 0;
	    for (int i=0; i < graphFleury.getNumVert(); i++) {
		numEdges += graphFleury.getDegree(i);	
	    }	    
	    numEdges /= 2;
	    	    
	    int[][][] treeEdges = new int[graphFleury.getNumVert()]
		[numEdges][3];
	    
	    for(int i = 0; i < graphFleury.getNumVert(); i ++) {
		graphFleury = new AdjacencyList(edges);
		eulerFleury = new EulerSearchFleury(graphFleury);	
		treeEdges[i] = eulerFleury.getTreeEdges(i);
	    }

	    DrawEuler ds = new DrawEuler(edges,treeEdges,inputFile,
					 "Fleury's Algorithm");
	    ds.setLocationRelativeTo( this );
	    ds.setVisible(true);
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
                new Euler(false).setVisible(true);
            }
        });
    }


}
