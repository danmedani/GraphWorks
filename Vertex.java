

/**
 * A Vertex object holds an integer identifier for this vertes as well as a 
 * LinkedList of Edges that are incident on this vertex.
 *
 * @version 1.0
 *
 * @author Team 1
 * 
 */
import java.util.LinkedList;

public class Vertex{
    
    private int vName;                  //Name of vertex
    private LinkedList<Edge> edgesOfV;  //Edges of vertes

    /**
     * Creates a new <code>Vertex</code> instance.
     *
     * @param name name of vertex
     */
    public Vertex(int name){
	
	vName = name;
	edgesOfV = new LinkedList<Edge>();
    }
    
    /**
     * Returns the name of the vertex.
     *
     * @return vName
     */
    public int getVName(){
	
	return vName;
    }
    
    /**
     * Adds the vertex <code>otherEnd</code> to <code>edgesOfV</code>
     *
     * @param otherEnd the vertex at the other end of the new edge
     */
    public void addEdge(int otherEnd) {
	edgesOfV.add(new Edge(otherEnd));
    }
    
    public void addEdge(int otherEnd, int weight) {
	edgesOfV.add(new Edge(otherEnd, weight));
    }
    
    public void removeEdge(int otherEnd) {
	for(int i = 0; i < edgesOfV.size(); i++) {
	    if(edgesOfV.get(i).getOtherEnd() == otherEnd)
		edgesOfV.remove(i);
	}
    }

    
    
    /**
     * <code>getEdgesOfV</code> returns the LinkedList of edges incident on 
     * this vertex
     *
     * @return this vertex's edge list
     */
    public LinkedList<Edge> getEdgesOfV(){
	
	return edgesOfV;
    }
    
}

    