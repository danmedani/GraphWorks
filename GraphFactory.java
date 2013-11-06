import java.util.*;
import java.io.*;


/**
 * <code>GraphFactory</code> takes a file containing graph data as input and
 * is able to return a 2-d array containing the edges in the graph..
 *
 * @author Team 1
 * @version 1.0
 */
public class GraphFactory {

    /**
     * String that has been read from the input file.
     */
    private String inputString;   
    private String inputStringMcKay;
    private boolean isWeighted;
    private boolean dimacs;
    private LinkedList<String> dimacsString;

    /**
     * Creates a new <code>GraphFactory</code> instance.
     *
     * @param inputFile File containing graph data
     */
    public GraphFactory (File inFile) throws FileNotFoundException {
	try {
	    BufferedReader input = new BufferedReader(new FileReader(inFile));
	    inputString = input.readLine(); 
	    // Used in one of the four standard formats
	    
	    try{
		inputStringMcKay = input.readLine();
	    }
	    catch(NullPointerException e) {
		inputStringMcKay = null;
	    }

	    // Check if the graph is weighted
	    if (inputString.charAt(0) == 'w' || inputString.charAt(0) == 'W') {
		isWeighted = true;  
	    }
	    else {
		isWeighted = false; 
	    }

	    // Check if we are dealing with DIMACS!
	    if (inputString.charAt(0) == 'c' ||
		inputString.charAt(0) == 'p' ||
		inputString.charAt(0) == 'e') {
		
		dimacs = true;
		
		dimacsString = new LinkedList<String>();
		
		// build the list
		dimacsString.add(inputString);
		while((inputString = input.readLine()) != null) {
		    dimacsString.addLast(inputString);
		}		
	    }
	    else {
		dimacs = false;
	    }
	}
	catch (IOException e) {
	}
    }


    /**
     * getGraphReader examines the contents of inputFile and returns
     * some subclass of the GraphReader class that is appropriate for
     * parsing the input format in inputFile.
     *
     * @return a GraphReader for the given input file
     */
    private GraphReader getGraphReader() throws InvalidInputException{
	if(dimacs) {
	    return new DIMACSReader(dimacsString);
	}
	else {
	    if (inputString.indexOf("--") != -1){
		return new DoubleDashReader(inputString, isWeighted);
	    } else if (inputString.indexOf('-') != -1) {
		return new DashReader(inputString, isWeighted);
	    } else if (inputString.indexOf(',') != -1) {
		return new CommaReader(inputString, isWeighted);
	    } else if (inputString.indexOf(' ') != -1) {
		if(inputStringMcKay == null) {
		    return new SpaceReader(inputString, isWeighted);
		}
		else {
		    return new McKayReader(inputString, inputStringMcKay);
		}
	    } else {
		throw new InvalidInputException();
	    }
	}
    }
    
    /**
     * <code>getEdgeArray</code> uses an instance of <code>GraphReader</code>
     * to read the data from the input file and get a 2-d array of edges.
     *
     * @return a 2-d array of edges
     */
    public int[][] getEdgeArray() throws InvalidInputException {
	
	GraphReader reader = getGraphReader();
	if (isIsolated(reader.getEdgeArray())) {
	    throw new InvalidInputException();
	}
	return reader.getEdgeArray();
    }

    private boolean isIsolated(int[][] edges) {
	if(edges.length > 1) {
	    return false;
	}
	else {
	    if(edges[0][0] == edges[0][1]) {
		return true;
	    }
	}
	return false;
    }
    
   
}
