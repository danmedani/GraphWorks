/**
 * <code>OneConn</code> uses Tarjan's strongly connected components 
 * algorithm to find the connected components of a graph and returns
 * an array of integers representing the order in which the graph's vertices
 * must be printed to correctly display the connected components.
 *
 * @author Team 1
 * @version 1.0
 */

import java.util.Stack;
import java.lang.Math;

public class OneConn {
    
    private Graph g;               // The graph being analyzed
    private Stack<Integer> nodes;  // Stack to hold order of vertex visits
    private int index;             // Number indicating depth of current vertex
    private int numVertsFound;     // Number of vertices already processed
    private int[] comps;           // New order for vertices
    private int[] indices;         // Depth at which each vertex was found
    private int[] lowlink;         // Furthest back that a vertex can reach
    private int addVal;

    /**
     * Creates a new <code>OneConn</code> instance.
     *
     * @param newGraph Graph to be analyzed
     */
    public OneConn(Graph newGraph){
	
	g = newGraph;
	
	comps = new int[g.getNumVert()];
	indices = new int[g.getNumVert()];
	lowlink = new int[g.getNumVert()];

	for(int i = 0; i < g.getNumVert(); i ++) {
	    indices[i] = -1;
	}
    }


    /**
     * <code>findConnected</code> uses Tarjan's algorithm to find the strongly
     * connected components of the graph <code>g</code>.
     *
     * @return an array of vertices reordered by component
     */
    public int[] findConnected(){
	
	addVal = 0;
	index = 0;
	numVertsFound = 0;
	nodes = new Stack<Integer>();
	
	// Process each vertex if it hasn't yet been visited
	for (int v=0; v < g.getNumVert(); v++){
	    if (indices[v] == -1){
		tarjan(v);
	    }
	    addVal += 100;
	}
	
	return comps;	
    }


    /**
     * <code>tarjan</code> performs a depth-first search of each vertex
     * in order to find the connected components.
     *
     * @param v vertex currently being processed
     */
    private void tarjan(int v){
	
	indices[v] = index;
	lowlink[v] = index;
	index++;
	
	nodes.push(new Integer(v));
	for (int i=0; i < g.getNumVert(); i++){
	    // Process next adjacent vertex if it hasn't yet been processed
	    if (g.isAdj(v,i)){
		if (indices[i] == -1){
		    tarjan(i);
		    lowlink[v] = Math.min(lowlink[v], lowlink[i]);
		} else if (nodes.search(new Integer(i)) != -1) {
		    lowlink[v] = Math.min(lowlink[v], lowlink[i]);
		}
	    }
	}
	if (lowlink[v] == indices[v]){
	    // Pop each vertex of current component off the stack
	    do {
		comps[numVertsFound] = nodes.pop().intValue() + addVal;
		numVertsFound++;
	    } while (v + addVal != comps[numVertsFound-1]);
	}
    }
}