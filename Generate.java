import java.io.*;
import java.util.Scanner;
import javax.swing.*;
import org.netbeans.lib.awtextra.*;
import java.util.LinkedList;

/**
 * Generate
 * 
 * @author team1
 */
public class Generate extends JFrame {
    
    String outFileName;
    
    /** Creates new form Generate */
    public Generate() {
        initComponents();
    }
    

    private void initComponents() {

	//	degreeList = new LinkedList<int[]>();
	fileChooser = new JFileChooser(".");
        jScrollPane1 = new JScrollPane();
        jTextArea1 = new JTextArea();
        titleLabel = new JLabel();
        backButton = new JButton();
        genGraphSequences = new JButton();
        genSequences = new JButton();  
	browseButton = new JButton();
        enterSequenceLabel = new JLabel();
        lengthInput = new JTextField();
        outputFileLabel = new JLabel();
        jScrollPane2 = new JScrollPane();
        output = new JTextArea();
        outFileInput = new JTextField();
        numSequencesLabel = new JLabel();
        seqCount = new JTextField();
	
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);
	
        browseButton.setText("Select an Output File");
        browseButton.addActionListener(new java.awt.event.ActionListener() {
		public void actionPerformed(java.awt.event.ActionEvent evt) {
		    browseButtonActionPerformed(evt);
		}
	    });

	getContentPane().setLayout(new AbsoluteLayout());

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); 
	setResizable(false);
	
        titleLabel.setFont(new java.awt.Font("Lucida Sans", 1, 24));
        titleLabel.setText("Generate Degree Sequences");
	
        backButton.setText("Back");
        backButton.addMouseListener(new java.awt.event.MouseAdapter() {
		public void mouseClicked(java.awt.event.MouseEvent evt) {
		    backButtonMouseClicked(evt);
		}
	    });
	
        genGraphSequences.setText("Generate Graphical Sequences");
        genGraphSequences.addMouseListener(new java.awt.event.MouseAdapter() {
		public void mouseClicked(java.awt.event.MouseEvent evt) {
		    genGraphSequencesMouseClicked(evt);
		}
	    });
	
        genSequences.setText("Generate All Sequences");
        genSequences.addMouseListener(new java.awt.event.MouseAdapter() {
		public void mouseClicked(java.awt.event.MouseEvent evt) {
		    genSequencesMouseClicked(evt);
		}
	    });
	
        enterSequenceLabel.setText("Enter a Sequence Length:");
	outputFileLabel.setText("Output File:");
	
        output.setColumns(20);
        output.setRows(5);
	output.setEditable(false);
	jScrollPane2.setViewportView(output);
	
        numSequencesLabel.setText("sequences found.");
	seqCount.setEditable(false);
	outFileInput.setEditable(false);

	// Add the Components
	getContentPane().add(titleLabel,
			     new AbsoluteConstraints(75, 10, -1, -1));
	getContentPane().add(output, 
			     new AbsoluteConstraints(35, 60, 150, 250));
	getContentPane().add(enterSequenceLabel,
			     new AbsoluteConstraints(210, 60, -1, 20));
	getContentPane().add(lengthInput,
			     new AbsoluteConstraints(380, 60, 30, 20));
	getContentPane().add(genGraphSequences,
			     new AbsoluteConstraints(210, 203, -1, -1));
	getContentPane().add(genSequences, 
			     new AbsoluteConstraints(210, 170, -1, -1));
	getContentPane().add(numSequencesLabel, 
			     new AbsoluteConstraints(270, 240, -1, -1));
	getContentPane().add(seqCount, 
			     new AbsoluteConstraints(210, 238, 50, -1));
	getContentPane().add(browseButton,
			     new AbsoluteConstraints(210, 95, -1, -1));
	getContentPane().add(outputFileLabel,
			     new AbsoluteConstraints(250, 129, -1, -1));
	getContentPane().add(outFileInput,
			     new AbsoluteConstraints(340, 127, 110, -1));
	getContentPane().add(backButton,
			     new AbsoluteConstraints(400, 310, -1, -1));
	

        pack();
    }
    
    private void genSequencesMouseClicked(java.awt.event.MouseEvent evt) {
	
	if(!(outFileInput.getText().equals("") || 
	     outFileInput.getText().equals("No file specified."))) {
	    String input = lengthInput.getText();
	    int numVertices = -1;;
	    boolean incorrectInput = false;
	    try{
		numVertices = Integer.parseInt(input);
		if (numVertices > 9 || numVertices < 1)
		    output.setText("Sequence length must be \nin range [1,9]");
		else {
		    
		    output.setText("");
		    
		    // Get the sequences
		    GenSequence tree1 = new GenSequence(numVertices);
		    degreeList = tree1.genDegSequences();		    
		    
		    // Print them to the file
		    try{
			int numSeqs = 0;
			PrintStream pStream = new PrintStream(new FileOutputStream(inputFile, false));
			for (int i = 0; i < degreeList.size(); i ++) {
			    for(int j = 0; j < numVertices; j ++) {
				pStream.print(degreeList.get(i)[j]);
			    }
			    numSeqs ++;
			    pStream.println();
			}
			seqCount.setText(""+numSeqs);
			pStream.close();
		    }
		    catch (FileNotFoundException e) {
			System.err.println("Error writing to file");
		    }

		    // Stick the first 14 in the box
		    for (int i = 0; i < degreeList.size(); i ++) {
			if(i >= 14) {
			    break;
			}
			for(int j = 0; j < numVertices; j ++) {
			    output.append("" + degreeList.get(i)[j]);
			}
			output.append("\n");
		    }
		    
		}
		if(output.getLineCount() >= 14) {
		    output.append("[the full list can be\nfound in the ou"+
				  "tput file]");
		}
	    } catch (NumberFormatException e) {
		output.setText("Sequence length must be \nin range [1,9]");
	    }
	} else {
	    outFileInput.setText("No file specified.");
	}
    }
    
    private void genGraphSequencesMouseClicked(java.awt.event.MouseEvent evt) {
	if(!(outFileInput.getText().equals("") || 
	     outFileInput.getText().equals("No file specified."))) {
	    String input = lengthInput.getText();
	    int numVertices = -1;;
	    boolean incorrectInput = false;
	    try{
		numVertices = Integer.parseInt(input);
		if (numVertices > 9 || numVertices < 1)
		    output.setText("Sequence length must be \nin range [1,9]");
		else {
		    
		    output.setText("");
		    
		    // Get all sequences
		    GenSequence tree1 = new GenSequence(numVertices);
		    degreeList = tree1.genDegSequences();		    
		    
		    // Get Hakimi-Tested Sequences
		    Hakimi hakimi = new Hakimi(degreeList);
		    LinkedList<int[]> newList = hakimi.getNewList();


		    // Print them to the file
		    try{
			int numSeqs = 0;
			PrintStream pStream = new
			    PrintStream( 
					new FileOutputStream(inputFile, false) );
			for (int i = 0; i < newList.size(); i ++) {
			    for(int j = 0; j < numVertices; j ++) {
				pStream.print(newList.get(i)[j]);
			    }
			    numSeqs ++;
			    pStream.println();
			}
			seqCount.setText(""+numSeqs);
			pStream.close();
		    }
		    catch (FileNotFoundException e) {
			System.err.println("Error writing to file");
		    }
		  

		    // Stick the first 14 in the box
		    for (int i = 0; i < newList.size(); i ++) {
			if(i >= 14) {
			    break;
			}
			for(int j = 0; j < numVertices; j ++) {
			    output.append("" + newList.get(i)[j]);
			}
			output.append("\n");
		    }
	

		    
		    //if (outFileInput.getText().equals(""))
		    //outFileInput.setText(outFileName);
		}
		if(output.getLineCount() >= 14) {
		    output.append("[the full list can be\nfound in the ou"+
				  "tput file]");
		}
	    } catch (NumberFormatException e) {
		output.setText("Sequence length must be \nin range [1,9]");
	    }
	} else {
	    outFileInput.setText("No file specified.");
	}
	
    }
    
    private void backButtonMouseClicked(java.awt.event.MouseEvent evt) {
	DegreeSequence deg = new DegreeSequence(true);
	deg.setLocationRelativeTo(this);
	deg.setVisible(true);
	this.setVisible(false);
	this.dispose();
    }    

    private void browseButtonActionPerformed(java.awt.event.ActionEvent evt) {
	int status = fileChooser.showOpenDialog(null);
        if (status!=JFileChooser.APPROVE_OPTION){
	} else {
            inputFile = fileChooser.getSelectedFile();
            outFileInput.setText(inputFile.getName());
        }	
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
		public void run() {
		    new Generate().setVisible(true);
		}
	    });
    }
    
    private JButton genGraphSequences;  
    private JButton browseButton;
    private JButton genSequences;
    private JLabel titleLabel;
    private JLabel enterSequenceLabel;
    private JLabel outputFileLabel; 
    private JFileChooser fileChooser;
    private JLabel numSequencesLabel;
    private JScrollPane jScrollPane1;
    private File inputFile;
    private JScrollPane jScrollPane2;
    private JTextArea jTextArea1;
    private JTextField lengthInput;
    private JButton backButton;
    private JTextField outFileInput;
    private JTextArea output;
    private JTextField seqCount;

    private LinkedList<int[]> degreeList;
    
}
