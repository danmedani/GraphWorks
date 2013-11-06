import java.awt.Image;
import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import org.netbeans.lib.awtextra.*;

/**
 * <code>Search</code> is a form containing buttons and listeners to choose 
 * between running depth first search and breadth first search.
 *
 * @author Team 1
 * @version 1.0
 */
public class Search extends JFrame {

    // State
    private JFileChooser fileChooser;
    private File inputFile;
    private GraphFactory factory;    
    private int[][] edges;
    private Graph graph;
    private DFS dfs; 
    private BFS bfs;    
    private JButton backButton;
    private JButton browseButton; 
    private JButton bfSearchButton;
    private JButton dfSearchButton;     
    private JButton compareButton;    
    private JLabel titleLabel;
    private JLabel[] infoLabel;     
    private JTextField fileInputField;
    private boolean backEnabled;

    // Finals
    private final int xCoorLabels = 45;
    private final int yCoorLabels = 60;
    private final int labelSpacing = 20;


    public Search(boolean backEnabled) {
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
    public Search(File currentFile) {
	backEnabled = true;
	initComponents();
	inputFile = currentFile;  
	fileInputField.setText(inputFile.getName());
        
	try {
	    factory = new GraphFactory(inputFile);
	    edges = factory.getEdgeArray();
	    graph = new AdjacencyList(edges);	
	} catch (FileNotFoundException e){
	    fileInputField.setText("Badly formatted input file.");
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


	//Initialize
	titleLabel = new JLabel();
        fileInputField = new JTextField();
        browseButton = new JButton();
        backButton = new JButton();
        dfSearchButton = new JButton();
	bfSearchButton = new JButton();	
	compareButton = new JButton();
	fileChooser = new JFileChooser(".");


	// Add Components
        fileInputField.setEditable(false);
        getContentPane().add(fileInputField,
			     new AbsoluteConstraints(45, 292, 180, -1));

        browseButton.setText("Choose a Graph");
        browseButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                browseButtonMouseClicked(evt);
            }
        });
        getContentPane().add(browseButton, 
			     new AbsoluteConstraints(240, 290, 155, -1));

        dfSearchButton.setText("DFS");
	dfSearchButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dfSearchButtonMouseClicked(evt);
            }
        });
        getContentPane().add(dfSearchButton,
			     new AbsoluteConstraints(40, 350, 90, -1));

        bfSearchButton.setText("BFS");
        bfSearchButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bfSearchButtonMouseClicked(evt);
            }
        });
        getContentPane().add(bfSearchButton, 
			     new AbsoluteConstraints(165, 350, 90, -1));

        compareButton.setText("Compare");
        compareButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                compareButtonMouseClicked(evt);
            }
        });
        getContentPane().add(compareButton, 
			     new AbsoluteConstraints(290, 350, 100, -1));



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
        titleLabel.setText("Search");
        getContentPane().add(titleLabel, 
			     new AbsoluteConstraints(230, 10, -1, -1));
	String[] info = {"Before any option is chosen, you must select"+
			 " a graph",
			 "<html>file by clicking on the <b>Choose"+
			 " a Graph</b> button. Once</html>",
			 "<html>a graph has been selected, click"+
			 " <b>DFS</b> to perform a</html>",
			 "<html>Depth First Search or click <b>BFS</b>"+
			 " to perform a Breadth</html>",
			 "<html>First Search on the given graph. Click <b>Compare</b>",
			 "see the searches side by side. The same "+
			 "graph will",
			 "<html>be used for subsequent executions of "+
			 "<b>DFS</b> and <b>BFS</b>",
			 "until you return to the main menu or choose"+
			 " a new",
			 "<html>graph. In the figures to the right,"+
			 " the numbers in <b>(*)</b></html>",
			 "indicates the order in which the vertices "+
			 "were visited."};
	infoLabel = new JLabel[info.length];
	printLabels(info);
   

	// Display Images
	displayImage("search1.jpg",430,24,150,110);
	displayImage("search2.jpg",430,141,150,160);
	displayImage("search3.jpg",440,310,150,110);


        pack();
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
	    inputFile = new File(runningDirectory + "/DefaultSearchGraph");
	    fileInputField.setText("DefaultSearchGraph");
	    
	    factory = new GraphFactory(inputFile);
	    edges = factory.getEdgeArray();
	    graph = new AdjacencyList(edges);
	}
	catch(Exception e) { 	    
	    // So that didn't work. Let's see if they are running it from 
	    // terminal
	    try{
		inputFile = new File("DefaultSearchGraph");
		fileInputField.setText("DefaultSearchGraph");
		
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
	    } catch (FileNotFoundException e){
		fileInputField.setText("File not found.");
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
     * <code>bfSearchButtonMouseClicked</code> creates a new BFS object 
     * and calls DrawSearch.
     *
     * @param evt a <code>java.awt.event.MouseEvent</code> value
     */
    private void bfSearchButtonMouseClicked(java.awt.event.MouseEvent evt) {
	if (inputFile == null){
	    fileInputField.setText("Please choose a graph first.");
	} else {
	    
	    // Build an array of all possible tree edges
	    int[][][] treeEdges = new 
		int[graph.getNumVert()][graph.getNumVert()-1][3];
	    
	    for(int i = 0; i < graph.getNumVert(); i ++) {
		bfs = new BFS(graph);		
		treeEdges[i] = bfs.getTreeEdges(i);
	    }

	    DrawSearch bs = new DrawSearch(edges,
					   treeEdges,
					   inputFile,
					   "Breadth First Search");
	    bs.setLocationRelativeTo( this );
	    bs.setVisible(true);
	    this.setVisible(false);
	    this.dispose();
	    }
    }

    /**
     * <code>dfSearchButtonMouseClicked</code> creates a new DFS object
     * and calls DrawSearch
     *
     * @param evt a <code>java.awt.event.MouseEvent</code> value
     */
    private void dfSearchButtonMouseClicked(java.awt.event.MouseEvent evt) {
	if (inputFile == null){
	    fileInputField.setText("Please choose a graph first.");
	} else {

	    // Build an array of all possible tree edges
	    int[][][] treeEdges = new 
		int[graph.getNumVert()][graph.getNumVert()-1][3];
	    
	    for(int i = 0; i < graph.getNumVert(); i ++) {
		dfs = new DFS(graph);		
		treeEdges[i] = dfs.getTreeEdges(i);
	    }

	    DrawSearch ds = new DrawSearch(edges,
					   treeEdges,
					   inputFile,
					   "Depth First Search");
	    ds.setLocationRelativeTo( this );
	    ds.setVisible(true);
	    this.setVisible(false);
	    this.dispose();
	}
    }    


    /**
     * <code>compareButtonMouseClicked</code> creates a new DFS object
     * and calls DrawSearch
     *
     * @param evt a <code>java.awt.event.MouseEvent</code> value
     */
    private void compareButtonMouseClicked(java.awt.event.MouseEvent evt) {
	if (inputFile == null){
	    fileInputField.setText("Please choose a graph first.");
	} else {

	    // Build an array of all possible tree edges
	    int[][][] treeEdgesDFS = new 
		int[graph.getNumVert()][graph.getNumVert()-1][3];
	    int[][][] treeEdgesBFS = new 
		int[graph.getNumVert()][graph.getNumVert()-1][3];


	    for(int i = 0; i < graph.getNumVert(); i ++) {
		dfs = new DFS(graph);
		bfs = new BFS(graph);		
		treeEdgesDFS[i] = dfs.getTreeEdges(i);
		treeEdgesBFS[i] = bfs.getTreeEdges(i);
	    }

	    CompareSearch ds = new 
		CompareSearch(edges,treeEdgesDFS,treeEdgesBFS,inputFile);

	    ds.setLocationRelativeTo( this );
	    ds.setVisible(true);
	    this.setVisible(false);
	    this.dispose();
	}
    }   






    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
		public void run() {
		    new Search(false).setVisible(true);
		}
	    });
    }
    
    


}
