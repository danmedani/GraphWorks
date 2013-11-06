import java.util.*;
import java.io.*;
import javax.swing.*;

/**
 * GenSequence is a class that creates and stores an array of TreeNodes
 * representing all possible degree sequences of a certain length.
 *
 * @author Team 1
 */
public class GenSequence {
    
    private int sequenceLength;     // Length of sequences

    /**
     * Takes an integer, input, as a parameter and initializes sequence length
     * to input.
     *
     * @param input in integer representing the length of degree sequences
     */
    public GenSequence(int input) {
     	sequenceLength = input;
    }
    
    /**
     * genDegSequences() builds a list of all possible 
     * degree sequences
     */
    public LinkedList<int[]> genDegSequences() {

	LinkedList<int[]> list = new LinkedList<int[]>();

	// Init the list
	for(int i = 0; i < sequenceLength; i ++) {
	    int[] tmp = new int[sequenceLength];
	    tmp[sequenceLength-1] = i;
	    list.add(tmp);
	}

	int origLen;
	int[] tmp;
	int start = 0; // Where to start
	for(int i = sequenceLength-2; i >= 0; i --) {
	    origLen = list.size();
	    for(int j = start; j < origLen; j ++) {
		for(int k = 0; k < list.get(j)[i+1]; k ++) {
		    tmp = new int[sequenceLength];
		    // clone it!
		    for(int z = 0; z < sequenceLength; z ++) {
			tmp[z] = list.get(j)[z];
		    }
		    tmp[i] = k+1;
		    list.add(tmp);
		}
	    }
	    start = origLen;
	}	
	return list;
    }

}


	