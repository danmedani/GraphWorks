/**
 * An Edge object contains one integer specifying the name of the vertex
 * at the other end of the edge.
 *
 * @version 1.0
 *
 * @author Team 1
 *
 */

public class Edge{
    
    private int otherEnd;
    private int weight;
    
    /**
     * Creates a new <code>Edge</code> instance.
     *
     * @param otherVertex the other end of the edge
     */
    public Edge(int otherVertex){
	
	otherEnd = otherVertex;
	weight = -1;
    }
    
    public Edge(int otherVertex, int edgeWeight){
	
	otherEnd = otherVertex;
	weight = edgeWeight;
    }
    
    /**
     * Returns this edge's other end
     *
     * @return otherEnd
     */
    public int getOtherEnd(){
	
	return otherEnd;
    }
    
    public int getWeight(){
	
	return weight;
    }
      
}