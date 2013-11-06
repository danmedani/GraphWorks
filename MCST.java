import javax.swing.JFileChooser;
import javax.swing.*;
import java.awt.*;
import org.netbeans.lib.awtextra.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

/**
 * <code>MCST</code> is a form containing buttons and listeners to 
 * choose between running Prim's, Kruskal's, or Boruvka's algorithms
 *
 * @author Team 1
 * @version 1.0
 */
public class MCST extends JFrame {

    // State
    private JButton backButton;
    private JButton browseButton;
    private JLabel[] infoLabel;
    private JTextField fileInputField;
    private JButton primButton;
    private JButton boruvkaButton;
    private JLabel titleLabel;
    private JButton kruskalButton;
    private Prim prim;
    private Boruvka boruvka;
    private Kruskal kruskal;
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


    public MCST(boolean backEnabled) {
	this.backEnabled = backEnabled;
	initComponents();
	loadDefaultFile();
    }
   
    /**
     * Creates a new <code>MCST</code> instance.
     *
     * @param currentFile A <code>File</code> that will be used as the default
     * graph for this instance
     */
    public MCST(File currentFile) {
	backEnabled = true;
	initComponents();
	inputFile = currentFile;  
	fileInputField.setText(inputFile.getName());
        
	try {
	    factory = new GraphFactory(inputFile);
	    edges = factory.getEdgeArray();
	    graph = new AdjacencyList(edges,true);	
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
	setResizable(false);
        setMinimumSize(new java.awt.Dimension(600, 443));
        getContentPane().setLayout(new AbsoluteLayout());


	// Initialize
        titleLabel = new JLabel();
        fileInputField = new JTextField();
        browseButton = new JButton();
        backButton = new JButton();
        primButton = new JButton();
	kruskalButton = new JButton();
	boruvkaButton = new JButton();
	fileChooser = new JFileChooser(".");



	// Add Components 
	fileInputField.setEditable(false);
        getContentPane().add(fileInputField,
			     new AbsoluteConstraints(45, 290, 180, -1));

        browseButton.setText("Choose a Graph");
        browseButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                browseButtonMouseClicked(evt);
            }
        });
        getContentPane().add(browseButton, 
			     new AbsoluteConstraints(240, 288, 155, -1));

        primButton.setText("Prim");
	primButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                primButtonMouseClicked(evt);
            }
        });
        getContentPane().add(primButton, 
			     new AbsoluteConstraints(40, 350, 100, -1));

        kruskalButton.setText("Kruskal");
        kruskalButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                kruskalButtonMouseClicked(evt);
            }
        });
        getContentPane().add(kruskalButton, 
			     new AbsoluteConstraints(175, 350, 100, -1));
	
	boruvkaButton.setText("<html>Bor&#367;vka");
        boruvkaButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                boruvkaButtonMouseClicked(evt);
            }
        });
        getContentPane().add(boruvkaButton, 
			     new AbsoluteConstraints(310, 350, 100, -1));

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


	// Add Labels

        titleLabel.setFont(new java.awt.Font("Tahoma", 1, 24));
        titleLabel.setText("Minimal Cost Spanning Trees");
        getContentPane().add(titleLabel,
			     new AbsoluteConstraints(125, 10, -1, -1));
	String[] info = {"Before any option is chosen, you must"+
			 " select a graph file",
			 "<html>by clicking on the <b>Choose a "+
			 "Graph</b> button. Once a graph",
			 "<html>has been selected, click <b>Prim</b>"+
			 " to find a minimal cost",
			 "<html>spanning tree using Prim's algorithm,"+
			 " <b>Kruskal</b> to use",
			 "<html>Kruskal's algorithm, or <b>"+
			 "Bor&#367;vka</b> to use Bor&#367;vka's",
			 "<html>algorithm. The tree shown under <b>G</b>"+
			 " is a minimal cost",
			 "<html> spanning tree of <b>G</b>."+
			 " The same graph will be used for",
			 "subsequent executions of the algorithms until"+
			 " you",
			 "return to the main menu or choose a new graph."};

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
	    inputFile = new File(runningDirectory + "/DefaultMCSTGraph");
	    fileInputField.setText("DefaultMCSTGraph");

	    factory = new GraphFactory(inputFile);
	    edges = factory.getEdgeArray();
	    graph = new AdjacencyList(edges,true);
	}
	catch(Exception e) { 
	    // So that didn't work. Let's see if they are running
	    //it from terminal
	    try{
		inputFile = new File("DefaultMCSTGraph");
		fileInputField.setText("DefaultMCSTGraph");

		factory = new GraphFactory(inputFile);
		edges = factory.getEdgeArray();
		graph = new AdjacencyList(edges,true);
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
		graph = new AdjacencyList(edges,true);	
	    } catch (FileNotFoundException e){
		fileInputField.setText("File not found!");
		inputFile = null;
	    } catch (InvalidInputException e){
		fileInputField.setText("Badly formatted input file.");
		inputFile = null;
	    } catch (Exception e) {
		fileInputField.setText("Badly formatted input file.");
		inputFile = null;
	    }

	    DFS dfs = new DFS(graph);
	    if(!dfs.isConnected()) {
		fileInputField.setText("Graph is disconnected!");
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
     * <code>kruskalButtonMouseClicked</code> creates a new Kruskal object.
     *
     * @param evt a <code>java.awt.event.MouseEvent</code> value
     */
    private void kruskalButtonMouseClicked(java.awt.event.MouseEvent evt) {
	if (inputFile == null){
	    fileInputField.setText("Please choose a graph first.");
	} else {

	    // Build an array of all possible tree edges
	    int[][] treeEdges;
	    kruskal = new Kruskal(graph,edges);
	    treeEdges = kruskal.buildSpanningTree();
	    
	    DrawMCST draw = new DrawMCST(edges,treeEdges,inputFile,"k");
	    draw.setLocationRelativeTo( this );
	    draw.setVisible(true);
	    this.setVisible(false);
	    this.dispose();
	}
    }

    /**
     * <code>primButtonMouseClicked</code> creates a new Prim object.
     *
     * @param evt a <code>java.awt.event.MouseEvent</code> value
     */
    private void primButtonMouseClicked(java.awt.event.MouseEvent evt) {
	if (inputFile == null){
	    fileInputField.setText("Please choose a graph first.");
	} else {

	    // Build an array of all possible tree edges
	    int[][] treeEdges;	    
	    Prim p2 = new Prim(graph,edges);
	    treeEdges = p2.getEdges();
	    //	    prim = new Prim(graph,edges);		
	    //treeEdges = prim.buildSpanningTree();
	    	    
	    DrawMCST draw = new DrawMCST(edges,treeEdges,inputFile,"p");
	    draw.setLocationRelativeTo( this );
	    draw.setVisible(true);
	    this.setVisible(false);
	    this.dispose();
	    
	}
    }
    
    /**
     * <code>boruvkaButtonMouseClicked</code> creates a new Boruvka object.
     *
     * @param evt a <code>java.awt.event.MouseEvent</code> value
     */
    private void boruvkaButtonMouseClicked(java.awt.event.MouseEvent evt) {
	if (inputFile == null){
	    	    fileInputField.setText("Please choose a graph first.");
	} else {

	    // Build an array of all possible tree edges
	    int[][] treeEdges;
	    boruvka = new Boruvka(graph);
	    treeEdges = boruvka.findTreeEdges();
	    
	    DrawMCST draw = new DrawMCST(edges,treeEdges,inputFile,"b");
	    draw.setLocationRelativeTo( this );
	    draw.setVisible(true);
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
                new MCST(false).setVisible(true);
            }
        });
    } 

    

}
