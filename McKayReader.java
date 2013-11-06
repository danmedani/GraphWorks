
/**
 * McKayReader is a subclass of GraphReader that is able to parse input files
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

public class McKayReader extends GraphReader {
    
    int[][] returnEdge;
    String line2;
 
    public McKayReader(String line1, String line2){
	String[] str = line1.split(" ");
	this.line2 = line2;
	returnEdge = new int[Integer.parseInt(str[1])][3];
    }

    /**
     * <code>getEdgeArray()</code> returns a 2-d array of integers where
     * each row represents one edge in the graph.
     *
     * @return a 2-d array of edges
     */
    public int[][] getEdgeArray() throws InvalidInputException {

	try {
	    String[] l2 = line2.split(" ");
	    for(int i = 0; i < returnEdge.length; i ++) {
		returnEdge[i][0] = Integer.parseInt(l2[4*i]);
		returnEdge[i][1] = Integer.parseInt(l2[4*i+3]);
		returnEdge[i][2] = Integer.parseInt(l2[4*i+1]);
	    }		
		
	} catch (NumberFormatException e){
	    throw new InvalidInputException();
	}
	return returnEdge;

    }    
}