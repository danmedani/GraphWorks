
/**
 * DIMACSReader is a subclass of GraphReader that is able to parse input files
 * where each integer in the file is separated by an integer, then create 
 * either an adjacency matrix or an adjacency list that represents the graph 
 * in the input file.
 *
 * @version 1.0
 *
 * @author Team 1
 *
 */

import java.util.*;

public class DIMACSReader extends GraphReader {
    
    private LinkedList<String> list;
        
    /**
     * Creates a new <code>CommaReader</code> instance.
     *
     * @param inputString String containing graph data
     */
    public DIMACSReader(LinkedList<String> list) {
	this.list = list;
    }

    /**
     * <code>getEdgeArray()</code> returns a 2-d array of integers where
     * each row represents one edge in the graph.
     *
     * @return a 2-d array of edges
     */
    public int[][] getEdgeArray() throws InvalidInputException {

	int numEdges = getNumEdges();
	int[][] retEdges = new int[numEdges][2];
	int numEdgesAdded = 0;
	String[] tmpLine;

	try {
	    for(int i = 0; i < list.size(); i ++) {
		tmpLine = list.get(i).split(" ");
		if(tmpLine[0].equals("e")) {
		    retEdges[numEdgesAdded][0] = Integer.parseInt(tmpLine[1]);
		    retEdges[numEdgesAdded][1] = Integer.parseInt(tmpLine[2]);
		    numEdgesAdded ++;
		}
	    }
	} catch (NumberFormatException e){
	    throw new InvalidInputException();
	}
	return retEdges;
    }

    private int getNumEdges() {
	int sum = 0;
	for(int i = 0; i < list.size(); i ++) {
	    if(list.get(i).substring(0,1).equals("e")) {
		sum ++;
	    }
	}
	return sum;
    }

    
}