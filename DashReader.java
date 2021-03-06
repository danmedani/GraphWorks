
/**
 * DashReader is a subclass of GraphReader that is able to parse input files
 * where each integer in the file is separated by a comma, then create either
 * an adjacency matrix or an adjacency list that represents the graph in the 
 * input file.
 *
 * @version 1.0
 *
 * @author Team 1
 *
 */

import java.util.LinkedList;

public class DashReader extends GraphReader {

    private String[] vertices;  // Array containing vertices in input string
    private boolean isWeighted;  // Flag for weighted graph entries
	
    /**
     * Creates a new <code>DashReader</code> instance.
     *
     * @param inputString String containing graph data
     */
    public DashReader(String input, boolean weighted){
	input = input.replaceAll(":","-");
	input = input.replaceAll("-",",");

	vertices = input.split(",");
	isWeighted = weighted;

    }
    
    /**
     * <code>getEdgeArray()</code> returns a 2-d array of integers where
     * each row represents one edge in the graph.
     *
     * @return a 2-d array of edges
     */
    public int[][] getEdgeArray() throws InvalidInputException {
	
	int[][] edgeArray = new int[vertices.length/2][2];
	int[][] wEdgeArray = new int[vertices.length/3][3];

	try {
	    if (isWeighted) {
		for (int i=0; i < vertices.length/3; i++){
		    wEdgeArray[i][0] = Integer.parseInt(vertices[3*i+1]);
		    wEdgeArray[i][1] = Integer.parseInt(vertices[3*i+2]);
		    wEdgeArray[i][2] = Integer.parseInt(vertices[3*i+3]);
		    if (wEdgeArray[i][0] < 0 || wEdgeArray[i][1] < 0 ||
			wEdgeArray[i][2] < 0){
			throw new InvalidInputException();
		    }
		}
	    } else {
		for (int i=0; i < vertices.length/2; i++){
		    edgeArray[i][0] = Integer.parseInt(vertices[2*i]);
		    edgeArray[i][1] = Integer.parseInt(vertices[2*i+1]);
		    if (edgeArray[i][0] < 0 || edgeArray[i][1] < 0){
			throw new InvalidInputException();
		    }
		}
	    }
	} catch (NumberFormatException e){
	    throw new InvalidInputException();
	}
	if (isWeighted)
	    return wEdgeArray;
	else
	    return edgeArray;
    }
	    
}