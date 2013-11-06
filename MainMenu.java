import javax.swing.*;
import java.awt.event.*;
import org.netbeans.lib.awtextra.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.*;

/**
* <code>MainMenu</code> contains the buttons & the listeners
* used to access all of GraphWorks's features.
*
*/
public class MainMenu extends JFrame {

    // State
    private JButton genericButton;     
    private JButton aboutGenericButton;
    private JButton aboutConnectedButton;
    private JButton aboutDegSequencesButton;
    private JButton aboutDrawGraphButton;
    private JButton aboutEnumSpanningTreeButton;
    private JButton aboutEulerCircuitButton;
    private JButton aboutMCSTButton;
    private JButton aboutSearchButton;
    private JButton aboutSmallGraphsButton;
    private JButton exitButton;
    private JButton connectedButton;
    private JButton degSequencesButton;
    private JLabel descripLabel1;
    private JLabel descripLabel2;
    private JLabel descripLabel3;
    private JLabel descripLabel4;
    private JButton drawGraphButton;
    private JButton enteringGraphButton;
    private JButton enumSpanningTreeButton;
    private JButton eulerCircuitButton;
    private JLabel jLabel1;
    private JSeparator jSeparator1;
    private JSeparator jSeparator2;
    private JButton mcstButton;
    private JButton searchButton;
    private JButton smallGraphsButton;
    private JLabel titleLabel;
    private JButton whatIsGraphButton;
    private JLabel infoLabel1;
    private JLabel infoLabel2;


    // Finals
    private final int leftButtonXCoor = 70;
    private final int rightButtonXCoor = 310;
    private final int leftHelpButtonXCoor = 250;
    private final int rightHelpButtonXCoor = 490;

    private final int buttonWidth = 170;
    private final int buttonYSpacing = 40;
    private final int buttonYStart = 260;



    public MainMenu() {
        initComponents();
    }


    /**
     * <code>initComponents</code> inits & lays out the buttons.
     *
     */
    private void initComponents() {

	// Windowing
	getContentPane().setLayout(new AbsoluteLayout());
	setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);


	// Initialize
        titleLabel = new JLabel();
        descripLabel1 = new JLabel();
        descripLabel2 = new JLabel();
        descripLabel3 = new JLabel();
        descripLabel4 = new JLabel();
        jSeparator1 = new JSeparator();
        whatIsGraphButton = new JButton();
        enteringGraphButton = new JButton();
        jSeparator2 = new JSeparator();
        degSequencesButton = new JButton();
        aboutDegSequencesButton = new JButton();
        drawGraphButton = new JButton();
        aboutDrawGraphButton = new JButton();
        searchButton = new JButton();
	exitButton = new JButton();
        connectedButton = new JButton();
        enumSpanningTreeButton = new JButton();
        eulerCircuitButton = new JButton();
        smallGraphsButton = new JButton();
        mcstButton = new JButton();
	aboutSearchButton = new JButton();
        aboutSmallGraphsButton = new JButton();
        aboutConnectedButton = new JButton();
        aboutMCSTButton = new JButton();
        aboutEnumSpanningTreeButton = new JButton();
        aboutEulerCircuitButton = new JButton();
        jLabel1 = new JLabel();
	infoLabel1 = new JLabel();
	infoLabel2 = new JLabel();
	// Generic Button
	genericButton = new JButton();
	aboutGenericButton = new JButton();
	


	// Add Components	
        whatIsGraphButton.setText("What is a Graph?");
        whatIsGraphButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                whatIsGraphButtonMouseClicked(evt);
            }
        });
        getContentPane().add(whatIsGraphButton, 
			     new AbsoluteConstraints(leftButtonXCoor,
						     205,
						     220,
						     -1));

        enteringGraphButton.setText("Entering a Graph");
        enteringGraphButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                enteringGraphButtonMouseClicked(evt);
            }
        });
        getContentPane().add(enteringGraphButton,
			     new AbsoluteConstraints(rightButtonXCoor,
						     205,
						     220,
						     -1));
        getContentPane().add(jSeparator2, 
			     new AbsoluteConstraints(10, 235, 580, 10));

        degSequencesButton.setText("Degree Sequences");
        degSequencesButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                degSequencesButtonMouseClicked(evt);
            }
        });
        getContentPane().add(degSequencesButton, 
			     new AbsoluteConstraints(leftButtonXCoor,
						     buttonYStart + 
						     (3 * buttonYSpacing), 
						     buttonWidth, -1));

        aboutDegSequencesButton.setText("?");
        aboutDegSequencesButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                aboutDegSequencesButtonMouseClicked(evt);
            }
        });
        getContentPane().add(aboutDegSequencesButton, 
			     new AbsoluteConstraints(leftHelpButtonXCoor, 
						     buttonYStart + 
						     (3 * buttonYSpacing),
						     40, -1)
			     );

        drawGraphButton.setText("Draw Graph");
        drawGraphButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                drawGraphButtonMouseClicked(evt);
            }
        });
        getContentPane().add(drawGraphButton, 
			     new AbsoluteConstraints(leftButtonXCoor, 
						     buttonYStart +
						     (0 * buttonYSpacing),
						     buttonWidth, -1));

        aboutDrawGraphButton.setText("?");
        aboutDrawGraphButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                aboutDrawGraphButtonMouseClicked(evt);
            }
        });
        getContentPane().add(aboutDrawGraphButton, 
			     new AbsoluteConstraints(leftHelpButtonXCoor,
						     buttonYStart + 
						     (0 * buttonYSpacing), 
						     40, -1));

        searchButton.setText("Search");
        searchButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                searchButtonMouseClicked(evt);
            }
        });
        getContentPane().add(searchButton, 
			     new AbsoluteConstraints(rightButtonXCoor, 
						     buttonYStart + 
						     (0 * buttonYSpacing),
						     buttonWidth, -1));

        connectedButton.setText("Connected");
        connectedButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                connectedButtonMouseClicked(evt);
            }
        });
        getContentPane().add(connectedButton,
			     new AbsoluteConstraints(leftButtonXCoor, 
						     buttonYStart +
						     (1 * buttonYSpacing),
						     buttonWidth, -1));


        eulerCircuitButton.setText("Eulerian Circuits");
        eulerCircuitButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                eulerCircuitButtonMouseClicked(evt);
            }
        });
        getContentPane().add(eulerCircuitButton,
			     new AbsoluteConstraints(rightButtonXCoor,
						     buttonYStart + 
						     (1 * buttonYSpacing),
						     buttonWidth, -1)
			     );

        smallGraphsButton.setText("Identify Small Graphs");
        smallGraphsButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                smallGraphsButtonMouseClicked(evt);
            }
        });
        getContentPane().add(smallGraphsButton,
			     new AbsoluteConstraints(leftButtonXCoor, 
						     buttonYStart +
						     (2 * buttonYSpacing), 
						     buttonWidth, -1));

        mcstButton.setText("Spanning Trees");
        mcstButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                mcstButtonMouseClicked(evt);
            }
        });
        getContentPane().add(mcstButton, 
			     new AbsoluteConstraints(rightButtonXCoor,
						     buttonYStart + 
						     (2 * buttonYSpacing), 
						     buttonWidth, -1));
     
        aboutSearchButton.setText("?");

	aboutSearchButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                aboutSearchButtonMouseClicked(evt);
            }
        });

        getContentPane().add(aboutSearchButton, 
			     new AbsoluteConstraints(rightHelpButtonXCoor,
						     buttonYStart + 
						     (0 * buttonYSpacing), 
						     40, -1));

        aboutSmallGraphsButton.setText("?");
        aboutSmallGraphsButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                aboutSmallGraphsButtonMouseClicked(evt);
            }
        });
        getContentPane().add(aboutSmallGraphsButton, 
			     new AbsoluteConstraints(leftHelpButtonXCoor, 
						     buttonYStart + 
						     (2 * buttonYSpacing), 
						     40, -1));

        aboutConnectedButton.setText("?");
        aboutConnectedButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                aboutConnectedButtonMouseClicked(evt);
            }
        });
        getContentPane().add(aboutConnectedButton, 
			     new AbsoluteConstraints(leftHelpButtonXCoor,
						     buttonYStart + 
						     (1 * buttonYSpacing), 
						     40, -1));

        aboutMCSTButton.setText("?");
        aboutMCSTButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                aboutMCSTButtonMouseClicked(evt);
            }
        });
        getContentPane().add(aboutMCSTButton, 
			     new AbsoluteConstraints(rightHelpButtonXCoor, 
						     buttonYStart + 
						     (2 * buttonYSpacing),
						     40, -1));

      
        aboutEulerCircuitButton.setText("?");
	
        aboutEulerCircuitButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                aboutEulerCircuitButtonMouseClicked(evt);
            }
        });
	
        getContentPane().add(aboutEulerCircuitButton, 
			     new AbsoluteConstraints(rightHelpButtonXCoor, 
						     buttonYStart + 
						     (1 * buttonYSpacing),
						     40, -1));

	exitButton.setText("Exit");
        exitButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                exitButtonMouseClicked(evt);
            }
        });
        getContentPane().add(exitButton, 
			     new AbsoluteConstraints(520, 420, 70, -1));


	// Generic Button  -- Comment/Uncomment to Hide/Show Generic Button
	/*
	genericButton.setText("Generic");
        genericButton.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent evt) {
		    genericButtonMouseClicked(evt);
		}
	    });
        getContentPane().add(genericButton, 
			     new AbsoluteConstraints(rightButtonXCoor,
						     buttonYStart + 
						     (3 * buttonYSpacing),
						     buttonWidth, 
						     -1));
	
	aboutGenericButton.setText("?");
        aboutGenericButton.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent evt) {
		    aboutGenericButtonMouseClicked(evt);
		}
	    });
        getContentPane().add(aboutGenericButton, 
			     new AbsoluteConstraints(rightHelpButtonXCoor, 
						     buttonYStart + 
						     (3 * buttonYSpacing), 
						     -1, -1));
	*/
	


	// Add Labels

        titleLabel.setFont(new java.awt.Font("Helvetica", 1, 40)); 
        titleLabel.setText("Graph Works");

        getContentPane().add(titleLabel, 
			     new AbsoluteConstraints(160, 10, 310, 40));

        descripLabel1.setFont(new java.awt.Font("Helvetica", 0, 14)); 
        descripLabel1.setText("Graph Works is a visualization and animation"+
			      " program to demonstrate the");
        getContentPane().add(descripLabel1, 
			     new AbsoluteConstraints(30, 70, -1, -1));

        descripLabel2.setFont(new java.awt.Font("Helvetica", 0, 14));
        descripLabel2.setText("fundamental properties and classical theorems"+
			      " of graph theory. For information");
        getContentPane().add(descripLabel2, 
			     new AbsoluteConstraints(30, 90, -1, -1));

        descripLabel3.setFont(new java.awt.Font("Helvetica", 0, 14)); 
        descripLabel3.setText("<html>about a particular topic, click the <b>?"+
			      "</b> button next to the topic's name, or"+
			      " click</html>");
        getContentPane().add(descripLabel3, 
			     new AbsoluteConstraints(30, 110, -1, -1));

        descripLabel4.setFont(new java.awt.Font("Helvetica", 0, 14)); 
        descripLabel4.setText("<html><b>What is a Graph?</b> for general "+
			      "graph"+
			      " theory information. For information</html>");
        getContentPane().add(descripLabel4,
			     new AbsoluteConstraints(30, 130, -1, -1));
        getContentPane().add(jSeparator1,
			     new AbsoluteConstraints(10, 180, 580, -1));

	infoLabel1.setFont(new java.awt.Font("Helvetica", 0, 12)); 
	infoLabel1.setText("<html><b>Information</b></html>");
        getContentPane().add(infoLabel1,
			     new AbsoluteConstraints(10, 185, 100, -1));

	infoLabel2.setFont(new java.awt.Font("Helvetica", 0, 12)); 
	infoLabel2.setText("<html><b>Options to Explore</b></html>");
        getContentPane().add(infoLabel2,
			     new AbsoluteConstraints(10, 240, 200, -1));
	jLabel1.setFont(new java.awt.Font("Helvetica", 0, 14)); 
        jLabel1.setText("<html>concerning input file formatting,"+
			      " click <b>Entering a Graph</b>.</html>");
        getContentPane().add(jLabel1, 
			     new AbsoluteConstraints(30, 150, -1, -1));



      
	// Display Images

	displayImage("buckBison.jpg",0,0,100,55);
	displayImage("bucknell_logo.jpg",495,-5,95,55);
	


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
    private void displayImage(String path, int xPos, 
			      int yPos, int width, int height) {
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


    // Event Handlers

    private void genericButtonMouseClicked(MouseEvent evt) {  
	Generic s = new Generic(true);
	s.setLocationRelativeTo( this );
	s.setVisible(true);
	this.setVisible(false);
	this.dispose();
    }
    private void aboutGenericButtonMouseClicked(MouseEvent evt) {
	GenericHelp gh = new GenericHelp();
	gh.setLocationRelativeTo( this );
	gh.setVisible(true);
	this.setVisible(false);
	this.dispose();
    } 

    private void enteringGraphButtonMouseClicked(MouseEvent evt) {
	EnterAGraphHelp eagh = new EnterAGraphHelp();
	eagh.setLocationRelativeTo( this );
	eagh.setVisible(true);
	this.setVisible(false);
	this.dispose();
    }

    private void degSequencesButtonMouseClicked(MouseEvent evt) {
	DegreeSequence deg = new DegreeSequence(true);
	deg.setLocationRelativeTo(this);
	deg.setVisible(true);
	this.setVisible(false);
	this.dispose();
    }
    

    private void aboutDegSequencesButtonMouseClicked(MouseEvent evt) {
	DegreeHelp dh = new DegreeHelp();
	dh.setLocationRelativeTo( this );
	dh.setVisible(true);
	this.setVisible(false);
	this.dispose();
    }

    private void drawGraphButtonMouseClicked(MouseEvent evt) {
        DrawGraph dg = new DrawGraph(true);
	dg.setLocationRelativeTo( this );
	dg.setVisible(true);
	this.setVisible(false);
	this.dispose();
    }

    private void aboutDrawGraphButtonMouseClicked(MouseEvent evt) {
	DrawHelp gh = new DrawHelp();
	gh.setLocationRelativeTo( this );
	gh.setVisible(true);
	this.setVisible(false);
	this.dispose();	
    }

    private void searchButtonMouseClicked(MouseEvent evt) {  
	Search s = new Search(true);
	s.setLocationRelativeTo( this );
	s.setVisible(true);
	this.setVisible(false);
	this.dispose();
    }

    private void aboutSearchButtonMouseClicked(MouseEvent evt) {
	SearchHelp sh = new SearchHelp();
	sh.setLocationRelativeTo( this );
	sh.setVisible(true);
	this.setVisible(false);
	this.dispose();
    }

    private void smallGraphsButtonMouseClicked(MouseEvent evt) {	
	SmallGraph sg = new SmallGraph(true);
	sg.setLocationRelativeTo( this );
	sg.setVisible(true);
	this.setVisible(false);
	this.dispose();
    }

    private void aboutSmallGraphsButtonMouseClicked(MouseEvent evt) {
	SmallHelp sgh = new SmallHelp();
	sgh.setLocationRelativeTo(this);
	sgh.setVisible(true);
	this.setVisible(false);
	this.dispose();
    }

    private void connectedButtonMouseClicked(MouseEvent evt) {
	Connect c = new Connect(true);
	c.setLocationRelativeTo( this );
	c.setVisible(true);
	this.setVisible(false);
	this.dispose();
    }

    private void aboutConnectedButtonMouseClicked(MouseEvent evt) {
	ConnectedHelp ch = new ConnectedHelp();
	ch.setLocationRelativeTo( this );
	ch.setVisible(true);
	this.setVisible(false);
	this.dispose();
    }

    private void mcstButtonMouseClicked(MouseEvent evt) {
	MCST m = new MCST(true);
	m.setLocationRelativeTo(this);
	m.setVisible(true);
	this.setVisible(false);
	this.dispose();
    }

    private void aboutMCSTButtonMouseClicked(MouseEvent evt) {
	MCHelp mh = new MCHelp();
	mh.setLocationRelativeTo(this);
	mh.setVisible(true);
	this.setVisible(false);
	this.dispose();
    }

    private void eulerCircuitButtonMouseClicked(MouseEvent evt) {
        Euler e = new Euler(true);
	e.setLocationRelativeTo( this );
	e.setVisible(true);
	this.setVisible(false);
	this.dispose();
    }
    
    private void aboutEulerCircuitButtonMouseClicked(MouseEvent evt) {   
	EulerHelp eh = new EulerHelp();
	eh.setLocationRelativeTo( this );
	eh.setVisible(true);
	this.setVisible(false);
	this.dispose();
    }
        
    private void whatIsGraphButtonMouseClicked(MouseEvent evt) {
	WhatIsGraphHelp gh = new WhatIsGraphHelp();
	gh.setLocationRelativeTo( this );
	gh.setVisible(true);
	this.setVisible(false);
	this.dispose();
    }  

    private void exitButtonMouseClicked(MouseEvent evt) {
	System.exit(1);
    }

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
		MainMenu mm = new MainMenu();
		mm.setLocationRelativeTo( null );
		mm.setVisible(true);
	    }
        });
    }

}
