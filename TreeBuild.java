import java.util.*;
import java.io.*;
import javax.swing.*;

/**
 * TreeBuild is a class that creates and stores an array of TreeNodes
 * representing all possible degree sequences of a certain length.
 *
 * @author Team 1
 */
public class TreeBuild {
    
    private int sequenceLength;     //Length of sequences
    private TreeNode[] roots;


    /* File output variables */
    FileOutputStream out;
    PrintStream p;
    
    /**
     * Takes an integer, input, as a parameter and initializes sequence length
     * to input.
     *
     * @param input in integer representing the length of degree sequences
     */
    public TreeBuild(int input, String fileName) {
        try {
            if(fileName != "-1") {
                FileOutputStream eraser = new FileOutputStream(fileName,false);
            }
        } catch(Exception e) { System.out.println("Error writing to file!"); }
	sequenceLength = input;
    }
    
    /**
     * genDegSequences() starts at the root of each tree and from that
     * root recursively builds a tree, each of whose paths from root to
     * leaf represent one possible degree sequence of the specified length.
     */
    public void genDegSequences() {
	int maxDegree = sequenceLength-1;
	roots = new TreeNode[sequenceLength];

	for (int i=0; i < sequenceLength; i++){
	    roots[i] = new TreeNode(i);
	    roots[i].buildNextLayer(sequenceLength-1, maxDegree);
	}
    }
	
	
    /**
     * printSequences() takes a JTextArea and a File as parameters, and writes
     * each of the degree sequences both to the text area and to the file. The
     * method returns the number of degree sequences written to the file.
     *
     * @param textArea the JTextArea to be written to
     * @param outFile the file to be written to
     * @return number of sequences written to outFile
     */
    public int printSequences(JTextArea textArea, File outFile) {

        int counter = 0;

        try{
            this.out = new FileOutputStream(outFile,false);
	    this.p = new PrintStream( this.out );
	}
	catch (FileNotFoundException e) {
	    System.err.println("Error writing to file");
	}

	for (int i=0; i < sequenceLength; i++) {
	    counter += roots[i].printSequence("", textArea, p);
	}

        return counter;

    }

    /**
     * printSequences() takes a File as a parameter and prints each degree
     * sequence to the file. It returns the number of degree sequences written.
     *
     * @param outFile The file to be written to
     * @return The number of degree sequences written
     */
    public int printSequences(File outFile) {

        int counter = 0;

        try{
            this.out = new FileOutputStream(outFile,true);
	    this.p = new PrintStream( this.out );
	}
	catch (FileNotFoundException e) {
	    System.err.println("Error writing to file");
	}
        
	for (int i=0; i < sequenceLength; i++) {
	    counter += roots[i].printSequence("", p);
	}

        return counter;
    }
    
}


	