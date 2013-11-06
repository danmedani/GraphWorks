/**
 * AdjacencyList holds an adjacency list representation of a graph and allows
 * the user to check vertices and edges, add edges, check if an edge is
 * incident on a vertex, and check if two vertices are adjacent.
 *
 * @version 1.0
 *
 * @author Team 1
 *
 */

import java.util.LinkedList;
import java.util.Iterator;

public class AdjacencyList implements Graph {
    
    private final int EOL = -1;  // End of list constant
    //Vertex List
    private LinkedList<Vertex> vertices = new LinkedList<Vertex>();
    



    public AdjacencyList() {
	
    }

    /**
     * Takes an array representing the edges of a graph and creates an
     * adjacency list that represents the graph.
     *
     * @param edges 2-d array of edges in the graph
     */
    public AdjacencyList(int[][] edges){
	/* Make first pass to construct list of vertices */
	int largestVertex = 0;
	int nextVertex;
	
	for (int i=0; i < edges.length; i++){
	    if (edges[i][0] > largestVertex){
		largestVertex = edges[i][0];
	    }
	    if (edges[i][1] > largestVertex){
		largestVertex = edges[i][1];
	    }
	}
	
	for (int i=0; i <= largestVertex; i++){
	    vertices.add(new Vertex(i));
	}
	
	/* Make second pass to add edges. */
	int vertexOne;
	int vertexTwo;
	for (int i=0; i < edges.length; i++){
	    vertexOne = edges[i][0];
	    vertexTwo = edges[i][1];
	    vertices.get(vertexOne).addEdge(vertexTwo);
	    vertices.get(vertexTwo).addEdge(vertexOne);
	}	
    }

    public AdjacencyList(int[][] edges, boolean isWeighted) {
	/* Make first pass to construct list of vertices */
	int largestVertex = 0;
	int nextVertex;
	
	for (int i=0; i < edges.length; i++){
	    if (edges[i][0] > largestVertex){
		largestVertex = edges[i][0];
	    }
	    if (edges[i][1] > largestVertex){
		largestVertex = edges[i][1];
	    }
	}
	
	for (int i=0; i <= largestVertex; i++){
	    vertices.add(new Vertex(i));
	}
	
	/* Make second pass to add edges. */
	int vertexOne;
	int vertexTwo;
	for (int i=0; i < edges.length; i++){
	    vertexOne = edges[i][0];
	    vertexTwo = edges[i][1];
	    vertices.get(vertexOne).addEdge(vertexTwo,edges[i][2]);
	    vertices.get(vertexTwo).addEdge(vertexOne,edges[i][2]);
	}	
    }
    
    /**
     * <code>addEdge</code> adds an edge between vertices v1 and v2.
     *
     * @param v1 first vertex on the new edge
     * @param v2 second vertex on the new edge
     */
    public void addEdge(int v1, int v2){
	
	if ((v1 < vertices.size()) && (v2 < vertices.size())){
	    vertices.get(v1).addEdge(v2);
	    vertices.get(v2).addEdge(v1);
	} else {
	    System.out.println("Invalid vertex.");
	}
	
    }
    
    /**
     * <code>removeEdge</code> removes the edge between vertices v1 and v2.
     *
     * @param v1 first vertex on the edge to be deleted
     * @param v2 second vertex on the edge to be deleted
     */
    public void removeEdge(int v1, int v2) {
	vertices.get(v1).removeEdge(v2);
	vertices.get(v2).removeEdge(v1);
    }

    
    /**
     * <code>displayGraph</code> draws a graphical representation of the graph
     * and prints the graph's edge set.
     *
     */
    public void displayGraph(){
	
	System.out.println("Number of vertices: " + vertices.size());
	for (int i=0; i < vertices.size(); i++){
	    Vertex v = vertices.get(i);
	    System.out.print(v.getVName() + ": ");
	    LinkedList<Edge> edges = v.getEdgesOfV();
	    for (int j=0; j < edges.size(); j++){
		Edge e = edges.get(j);
		System.out.print(e.getOtherEnd() + " ");
	    }
	    System.out.println();
	}
    }
    
    /**
     * <code>firstVertex</code> returns the graph's first vertex.
     *
     * @return the first vertex
     */
    public int firstVertex(){
	
	int vertex;

	if (vertices.size() > 0) {
	    vertex = vertices.get(0).getVName();
	} else {
	    vertex = EOL;
	}

	return vertex;
    }
    
    /**
     * <code>nextVertex</code> returns the vertex after <code>vt</code>
     *
     * @param vt current vertex
     * @return the next vertex
     */
    public int nextVertex(int vt){
	
	int vertex;
	
	if (vertices.size()-2 >= vt) {
	    vertex = vertices.get(vt+1).getVName();
	} else {
	    vertex = EOL;
	}
	
	return vertex;
    }
    
    /**
     * <code>getEdges</code> returns an array of edges incident on 
     * <code>vt</code>
     *
     * @param vt vertex to get edges from
     * @return edges incident on <code>vt</code>
     */
    public LinkedList<Edge> getEdges(int vt){
	
	Vertex v;
	
	try {
	    v = vertices.get(vt);
	} catch (Exception e){
	    System.out.println("Invalid vertex, returning null");
	    return null;
	}
	
	return v.getEdgesOfV();
    }
    /**
     * <code>firstEdge</code> returns the first edge attatched to 
     * <code>vt</code>.
     *
     * @return the first edge of <code>vt</code>
     */
    public int firstEdge(int vt){
	
	Vertex v;
	
	try {
	    v = vertices.get(vt);
	} catch (Exception e){
	    System.out.println("Invalid vertex, returning EOL");
	    return EOL;
	}
	
	int edge;
	if (v.getEdgesOfV().size() == 0) {
	    return EOL;
	} else {
	    edge = v.getEdgesOfV().get(0).getOtherEnd();
	}
	
	return edge;
    }
    
    /**
     * <code>nextEdge</code> returns the next edge attached to <code>vt</code>
     * after <code>edge</code>.
     *
     * @param vt a vertex
     * @param edge an edge attached to <code>vt</code>
     * @return the next edge
     */
    public int nextEdge(int vt, int edge){
	
	Vertex v;
	
	try {
	    v = vertices.get(vt);
	} catch (Exception e){
	    System.out.println("Invalid vertex, returning EOL");
	    return EOL;
	}
	
	Iterator iter = v.getEdgesOfV().iterator();
	while(iter.hasNext()){
	    Edge e = (Edge) iter.next();
	    if (e.getOtherEnd() == edge){
		if (iter.hasNext()){
		    return ((Edge) iter.next()).getOtherEnd();
		}
	    }
	}
	
	return EOL;
    }
    
    /**
     * <code>isAdj</code> returns true if <code>v1</code> is adjacent to 
     * <code>v2</code>, or false otherwise.
     *
     * @param v1 first vertex
     * @param v2 second vertex
     * @return whether <code>v1</code> and <code>v2</code> are adjacent
     */
    public boolean isAdj(int v1, int v2){
	
	if ((v1 < vertices.size()) && (v2 < vertices.size())){
	    boolean adj = false;
	    Vertex v = vertices.get(v1);
	    
	    for (int i=0; i < v.getEdgesOfV().size(); i++){
		if (v2 == v.getEdgesOfV().get(i).getOtherEnd()){
		    adj = true;
		}
	    }
	    
	    return adj;
	} else {
	    return false; // Invalid vertex
	}
    }
    
    /**
     * <code>getNumVert</code> returns the number of vertices in this graph
     *
     * @return the number of vertices
     */
    public int getNumVert(){

	return vertices.size();
    }
    
    /**
     * <code>getDegree</code> returns the degree of vertex <code>vt</code>.
     *
     * @param vt a vertex
     * @return the degree of <code>vt</code>
     */
    public int getDegree(int vt){
	
	if (vt < vertices.size()){
	    return vertices.get(vt).getEdgesOfV().size();
	} else {
	    System.out.println("Invalid vertex, returning -1");
	    return -1;
	}
    }
    
    /**
     * <code>isIncident</code> returns true if <code>edge</code> is incident
     * on <code>vertex</code>, and false otherwise.
     *
     * @return whether <code>edge</code> is incident on <code>vt</code>
     */
    public boolean isIncident(int edge, int vertex){
	
	return isAdj(edge, vertex);
    }
    
    
}