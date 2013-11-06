import javax.swing.*;

/**
 *
 * @author Team1
 */

import java.io.File;
import java.io.FileNotFoundException;
import javax.swing.JFileChooser;

public class SmallGraphs extends JFrame {

    /** Creates new form DrawGraph */
    public SmallGraphs() {
        initComponents();
    }
    
    public SmallGraphs(File currentFile) { 
	initComponents();
	inputFile = currentFile;
	inputFileField.setText(inputFile.getName());
	
	try {
	    factory = new GraphFactory(inputFile);
	    edges = factory.getEdgeArray();
	    graph = new AdjacencyList(edges);
	} catch (FileNotFoundException e){
	    System.out.println(e);
	} catch (InvalidInputException e){
	    inputFileField.setText("Incorrectly formatted input file.");
	}
    }
   
    private void initComponents() {

        titleLabel = new JLabel();
        infoLabel = new JLabel();
	infoLabel2 = new JLabel();
        inputFileField = new JTextField();
        browseButton = new JButton();
        displayButton = new JButton();
        backButton = new JButton();
	
	fileChooser = new JFileChooser();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        titleLabel.setFont(new java.awt.Font("Lucida Sans", 1, 30)); // NOI18N
        titleLabel.setText("Small Graphs");
        getContentPane().add(titleLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 0, 220, 50));

        infoLabel.setText("Enter a graph file by clicking on the \"Browse..\" button below, then click \"Display\" to");
        getContentPane().add(infoLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 550, -1));

	infoLabel2.setText("display your graph.");
	getContentPane().add(infoLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 85, 150, -1));

        inputFileField.setEditable(false);
        getContentPane().add(inputFileField, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 180, 180, -1));

        browseButton.setText("Browse..");
        browseButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                browseButtonMouseClicked(evt);
            }
        });
        getContentPane().add(browseButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 180, 100, -1));

        displayButton.setText("Display");
        displayButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                displayButtonMouseClicked(evt);
            }
        });
        getContentPane().add(displayButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 240, 110, -1));

        backButton.setText("Back");
        backButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backButtonMouseClicked(evt);
            }
        });
        getContentPane().add(backButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 420, 80, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void browseButtonMouseClicked(java.awt.event.MouseEvent evt) {
	int status = fileChooser.showOpenDialog(null);
	if (status == JFileChooser.APPROVE_OPTION){
            inputFile = fileChooser.getSelectedFile();
            inputFileField.setText(inputFile.getName());
        }
	try {
	    factory = new GraphFactory(inputFile);
	    edges = factory.getEdgeArray();
	    graph = new AdjacencyList(edges);
	} catch (FileNotFoundException e){
	    System.out.println(e);
	} catch (InvalidInputException e){
	    inputFileField.setText("Badly formatted input file.");
	}
    }

    private void displayButtonMouseClicked(java.awt.event.MouseEvent evt) {
	
	if (inputFile == null){
	    inputFileField.setText("Enter a file first");
	} else {
	    harary = new HararyNumber(graph);
	    int[] hNum = harary.getHararyNumber();
	    new DrawSmallGraphs(edges, hNum, inputFile).setVisible(true);
	    this.setVisible(false);
	    this.dispose();
	}
    }

    private void backButtonMouseClicked(java.awt.event.MouseEvent evt) {
        new MainMenu().setVisible(true);
	this.setVisible(false);
    }

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SmallGraphs().setVisible(true);
            }
        });
    }

    private JButton backButton;
    private JButton browseButton;
    private JButton displayButton;
    private JLabel infoLabel;
    private JLabel infoLabel2;
    protected JTextField inputFileField;
    private JLabel titleLabel;
    private JFileChooser fileChooser;
    private File inputFile;
    private GraphFactory factory;
    private int[][] edges;
    private Graph graph;
    private HararyNumber harary;

}
