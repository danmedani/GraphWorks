import javax.swing.JFileChooser;
import javax.swing.*;
import org.netbeans.lib.awtextra.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;
import java.awt.*;
import javax.imageio.ImageIO;


/**
 * <code>Connect</code> is a form containing buttons and listeners to choose 
 * between running 1-con && 2-con.
 *
 * @author Team 1
 * @version 1.0
 */
public class Connect extends JFrame {

    // State
    private JButton backButton;
    private JButton browseButton;
    private JLabel[] infoLabel;   
    private JTextField fileInputField;
    private JButton oneConnButton;
    private JLabel titleLabel;
    private JButton twoConnButton;
    private OneConn oneConn;
    private NonSeparable nonSep;
    private JFileChooser fileChooser;
    private File inputFile;
    private GraphFactory factory;
    private int[][] edges;
    private Graph graph; 
    private boolean backEnabled;


    // Finals
    private final int xCoorLabels = 45;
    private final int yCoorLabels = 60;
    private final int labelSpacing = 20;



    public Connect(boolean backEnabled) {
	this.backEnabled = backEnabled;
        initComponents();
	loadDefaultFile();
    }

    /**
     * Creates a new <code>Connect</code> instance.
     *
     * @param currentFile A <code>File</code> that will be used as the default
     * graph for this instance
     */
    public Connect(File currentFile) { 
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
	    fileInputField.setText("Incorrectly formatted input file.");
	}
    }

    /** This method is called from within the constructor to
     * initialize the form.
     */
    private void initComponents() {
	
	// Windowing
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new AbsoluteLayout());
	setResizable(false);  


	// Initialize
        titleLabel = new JLabel();
        fileInputField = new JTextField();
        browseButton = new JButton();        
        oneConnButton = new JButton();
        twoConnButton = new JButton();
        backButton = new JButton();
	fileChooser = new JFileChooser(".");


	// Add Components
        fileInputField.setEditable(false);
        getContentPane().add(fileInputField,
			     new AbsoluteConstraints(45, 272, 180, -1));

        browseButton.setText("Choose a Graph");
        browseButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                browseButtonMouseClicked(evt);
            }
        });
        getContentPane().add(browseButton,
			     new AbsoluteConstraints(240, 270, 155, -1));
       
        oneConnButton.setText("Connected");
        oneConnButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                oneConnButtonMouseClicked(evt);
            }
        });
        getContentPane().add(oneConnButton,
			     new AbsoluteConstraints(190, 320, -1, -1));

        twoConnButton.setText("Nonseparable");
        twoConnButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                twoConnButtonMouseClicked(evt);
            }
        });
        getContentPane().add(twoConnButton, 
			     new AbsoluteConstraints(177, 366, -1, -1));

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
        titleLabel.setFont(new java.awt.Font("Helvetica", 1, 24));
        titleLabel.setText("Connected Components");
        getContentPane().add(titleLabel, 
			     new AbsoluteConstraints(145, 10, -1, -1));
     	String[] info = {"Before an option is chosen, you must first "+
			 "select a",
			 "<html>graph by clicking on the <b>Choose a "+
			 "Graph</b> button.",
			 "<html>After selecting a graph, click <b>"+
			 "Connected</b> to see",
			 "<html>the graph's connected components" +
			 ", or click",
			 "<html><b>Nonseparable</b> to see the graph's "+
			 "nonseparable",
			 "components. The same graph will be used "+
			 "for ",
			 "<html>subsequent executions of <b>Connect"+
			 "ed</b> and ",
			 "<html><b>Nonseparable</b> until you return"+
			 " to the main menu",
			 "or choose a new graph."};
	infoLabel = new JLabel[info.length];
	printLabels(info);



	// Display Images
	displayImage("connHelp1.jpg",430,45,150,140);
	displayImage("connHelp2.jpg",430,190,150,115);
	displayImage("connHelp3.jpg",425,310,160,100);



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
	    runningDirectory = (new File(Connect.class.getProtectionDomain().
					 getCodeSource().
					 getLocation().toURI().getPath())).
		getParent();
	    inputFile = new File(runningDirectory + "/DefaultConnectGraph");
	    fileInputField.setText("DefaultConnectGraph");

	    factory = new GraphFactory(inputFile);
	    edges = factory.getEdgeArray();
	    graph = new AdjacencyList(edges);
	}
	catch(Exception e) { 
	    // So that didn't work. Let's see if they are running it from 
	    // terminal
	    try{
		inputFile = new File("DefaultConnectGraph");
		fileInputField.setText("DefaultConnectGraph");

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
     * <code>browseButtonMouseClicked</code> opens a new file chooser to enter
     * a graph that will be used in OneConn or TwoConn.
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

    /**
     * <code>oneConnButtonMouseClicked</code> creates a new OneConn object
     * that will find the connected components of a graph.
     *
     * @param evt a <code>MouseEvent</code> value
     */
    private void oneConnButtonMouseClicked(java.awt.event.MouseEvent evt) {
        
	if (inputFile == null){
	    fileInputField.setText("Please choose a graph first.");
	} else {
	    oneConn = new OneConn(graph);
	    int[] connected = oneConn.findConnected();
	    
	    DrawConnect dc = new DrawConnect(edges, connected, inputFile);
	    dc.setLocationRelativeTo( this );
	    dc.setVisible(true);
	    this.setVisible(false);
	    this.dispose();
	}
    }

    /**
     * <code>twoConnButtonMouseClicked</code> creates a new TwoConn object
     * that will find the non-separable components of a graph.
     *
     * @param evt a <code>java.awt.event.MouseEvent</code> value
     */
    private void twoConnButtonMouseClicked(java.awt.event.MouseEvent evt) {
	
	if (inputFile == null){
	    fileInputField.setText("Please choose a graph first.");
	} else {
	    nonSep = new NonSeparable(graph);
	    int[][] edges = nonSep.findNonSep();
	    
	    DrawNonSep dns = new DrawNonSep(edges, inputFile);
	    dns.setLocationRelativeTo( this );
	    dns.setVisible(true);
	    this.setVisible(false);
	    this.dispose();
	}
    }


    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
		public void run() {
		    new Connect(false).setVisible(true);
		}
	    });
    }    
}
