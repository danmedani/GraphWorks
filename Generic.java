import java.awt.Image;
import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import org.netbeans.lib.awtextra.*;

/**
 * <code>Generic</code> is a form containing buttons and listeners to choose 
 * between running depth first search and breadth first search.
 *
 * @author Team 1
 * @version 1.0
 */
public class Generic extends JFrame {

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
    private JButton genericButton1;
    private JButton genericButton0;    
    private JLabel titleLabel;
    private JLabel[] infoLabel;     
    private JTextField fileInputField;
    private boolean backEnabled;

    // Finals
    private final int xCoorLabels = 45;
    private final int yCoorLabels = 60;
    private final int labelSpacing = 20;



    public Generic(boolean backEnabled) {
	this.backEnabled = backEnabled;
	initComponents();
	loadDefaultFile();
    }

   
    /**
     * Creates a new <code>Generic</code> instance.
     *
     * @param currentFile A <code>File</code> that will be used as the default
     * graph for this instance
     */
    public Generic(File currentFile) {
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
        genericButton0 = new JButton();
	genericButton1 = new JButton();
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

        genericButton0.setText("Generic Button 0");
	genericButton0.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                genericButton0MouseClicked(evt);
            }
        });
        getContentPane().add(genericButton0,
			     new AbsoluteConstraints(200, 320, -1, -1));

        genericButton1.setText("Generic Button 1");
        genericButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                genericButton1MouseClicked(evt);
            }
        });
        getContentPane().add(genericButton1, 
			     new AbsoluteConstraints(200, 366, -1, -1));

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
        titleLabel.setText("Generic");
        getContentPane().add(titleLabel, 
			     new AbsoluteConstraints(230, 10, -1, -1));

	String[] info = {"Welcome to the generic second level topic!",
			 " ",
			 "Lorem ipsum dolor sit amet, consectetur adipiscing ",
			 "elit. Ut dictum orci eu nisi egestas ultrices "+
			 "aliquet ",
			 "diam sagittis. Nunc posuere vehicula arcu. Quisque",
			 "auctor ipsum et metus scelerisque suscipit. "+
			 "Vestibulum",
			 "metus ligula, placerat at aliquam sit amet, "+
			 "hendrerit a",
			 "enim. Mauris tempor, eros id vulputate tortor quis",
			 "suspendisse potenti. Nullam consectetur arcuac "+
			 "est cursus."};


	infoLabel = new JLabel[info.length];
	printLabels(info);
   

	// Display Images
	displayImage("generic1.jpg",430,40,150,150);
	displayImage("generic2.jpg",430,200,150,150);


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
	    inputFile = new File(runningDirectory + "/DefaultGenericGraph");
	    fileInputField.setText("DefaultGenericGraph");
	    
	    factory = new GraphFactory(inputFile);
	    edges = factory.getEdgeArray();
	    graph = new AdjacencyList(edges);
	}
	catch(Exception e) { 	    
	    // So that didn't work. Let's see if they are running it from 
	    // terminal
	    try{
		inputFile = new File("DefaultGenericGraph");
		fileInputField.setText("DefaultGenericGraph");
		
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
     * <code>genericButton1MouseClicked</code> creates a new generic1 object 
     * and calls DrawSearch.
     *
     * @param evt a <code>java.awt.event.MouseEvent</code> value
     */
    private void genericButton1MouseClicked(java.awt.event.MouseEvent evt) {
	if (inputFile == null){
	    fileInputField.setText("Please choose a graph first.");
	} else {

	    GenericList gList = new GenericList(graph);
	    int[] randomOrder = gList.getOrder();

	    DrawGeneric dg = new DrawGeneric(edges,
					     randomOrder,
					     inputFile,
					     "Generic 1");
	    dg.setLocationRelativeTo( this );
	    dg.setVisible(true);
	    this.setVisible(false);
	    this.dispose();
	}
    }

    /**
     * <code>genericButton0MouseClicked</code> creates a new generic0 object
     * and calls DrawSearch
     *
     * @param evt a <code>java.awt.event.MouseEvent</code> value
     */
    private void genericButton0MouseClicked(java.awt.event.MouseEvent evt) {
	if (inputFile == null){
	    fileInputField.setText("Please choose a graph first.");
	} else {

	    GenericList gList = new GenericList(graph);
	    int[] randomOrder = gList.getOrder();

	    DrawGeneric dg = new DrawGeneric(edges,
					      randomOrder,
					      inputFile,
					     "Generic 0");
	    dg.setLocationRelativeTo( this );
	    dg.setVisible(true);
	    this.setVisible(false);
	    this.dispose();
	}
    }    

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
		public void run() {
		    new Generic(false).setVisible(true);
		}
	    });
    }
    
    


}
