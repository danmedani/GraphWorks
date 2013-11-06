/**
 * <code>GenericList</code> takes a graph that the user enters and
 * does nothing with it!
 *
 * @author Team 1
 * @version 1.0
 */
public class GenericList {

    /**
     * <code>graph</code> is the graph.
     *
     */
    private Graph graph;
    
    /**
     * Creates a new <code>GenericList</code> instance.
     *
     */
    public GenericList(Graph graph) { 
	this.graph = graph;
    }
    
    /**
     * <code>getOrder</code> returns a 1D array representing a random
     * ordering of the vertices.
     *
     * @return the  edges of graph
     */
    public int[] getOrder(){
	
	int[] retVal = new int[graph.getNumVert()];
	
	for(int i = 0; i < retVal.length; i ++) {
	    retVal[i] = i;
	}
	return retVal;
    }
    
}
