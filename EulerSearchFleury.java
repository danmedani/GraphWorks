import java.io.*;

/**
 * <code>EulerSearchFleury</code> takes a graph that the user enters and
 * produces a 2-D array
 * containing the edges of the graph which are tree edges.
 *
 * @author Dan
 * @version 1.0
 */
public class EulerSearchFleury {
    
    /**
     * <code>graph</code> is the Graph that needs to be searched.
     *
     */
    private Graph graph; 
    /**
     * <code>treeEdges</code> is the 2-D int array that will hold the 
     * tree edges.
     *
     */
    private int[][] treeEdges;
    /**
     * <code>treeEdgesFound</code> keeps track of the # of edges currently 
     * added to the tree.
     *
     */
    private int treeEdgesFound;

    
    final int VISITED = 1;
    final int NOT_VISITED = 0;

    private int[][] visited;
    private int[] degree;
    private int totalDegree;
    
    /**
     * Creates a new <code>EulerSearchFleury</code> instance.
     *
     * @param graph a <code>Graph</code> value
     */
    public EulerSearchFleury(Graph graph) { 
	this.treeEdgesFound = 0;
	this.graph = graph;

    }
    

    /**
     * <code>getTreeEdges</code> returns a 2-d array containing the tree
     * edges of g, using dfs.
     *
     * @param start the starting vertex of the search
     * @return the tree edges of graph
     */
    public int[][] getTreeEdges(int start){

	// Init the "Degree Sequence" array
	degree = new int[graph.getNumVert()]; 
	int numEdges = 0;
	totalDegree = 0;
	boolean hasEulerCircuit = true;
	
	for (int i=0; i < graph.getNumVert(); i++) {
	    degree[i] = graph.getDegree(i);
	    numEdges += graph.getDegree(i);
	    totalDegree += graph.getDegree(i);
	    
	    if(graph.getDegree(i) % 2 !=0) {
		hasEulerCircuit = false;
	    }
	}

	// There is no circuit if the graph is not connected!
	if(!isConn(graph,0)) {
	    hasEulerCircuit = false;
	}
	numEdges /= 2;
	treeEdges = new int[numEdges][3];

	if(hasEulerCircuit) {
	// Fill in treeEdges
	    euler(start);	
	}
	else {
	    // This is a sign that there does not exist any circuit!
	    treeEdges[0][0] = -1;
	}
	return treeEdges;
    }
    
    /**
     * <code>dfs</code> traverses the graph and fills in treeEdges 
     * appropriately
     *
     * @param startVertex current vertex
     * @param visited which vertices have been visited
     */
    private void euler(int startVertex){

	int currentVertex = startVertex;
	int vRemove;

	while(totalDegree > 0) {
	    
	    vRemove = graph.firstEdge(currentVertex);
	    
	    // Anticipate the degree changes
	    degree[currentVertex] --;
	    
	    graph.removeEdge(currentVertex, vRemove);
	    
	    if( !isConn(graph,vRemove) ) {
		
		// Add the edge back to the end of the edge list
		graph.addEdge(currentVertex, vRemove);
		
		// We know that the new first edge will not 
		// disconnect the graph.
		vRemove = graph.firstEdge(currentVertex);
		graph.removeEdge(currentVertex, vRemove);

		treeEdges[treeEdgesFound][2] = graph.firstEdge(currentVertex);
	    }
	    else {
		// We didn't try to remove a bad edge.
		treeEdges[treeEdgesFound][2] = -1;		
	    }
	    
	    /*
	    while( !isConn(graph, vRemove) ) {
		
		// Add the edge back to the end of the edge list
		graph.addEdge(currentVertex, vRemove);
		
		vRemove = graph.firstEdge(currentVertex);
		graph.removeEdge(currentVertex, vRemove);
	    }
	    */

	    // An edge has been removed!
	    degree[vRemove] --;
	    totalDegree -= 2;
	    
	    treeEdges[treeEdgesFound][0] = currentVertex;
	    treeEdges[treeEdgesFound][1] = vRemove;
	    treeEdgesFound ++;
	    
	    currentVertex = vRemove;
	}	    
    }
    
    /*  
    private int getIllegal(Graph g, int vertex) {

	int firstV = graph.firstEdge(vertex);
	int nextV;

	g.removeEdge(vertex, firstV);
	if(!isConn(g,firstV)) {	
	    g.addEdge(vertex, firstV);
	    return firstV;
	}
	g.addEdge(vertex, firstV); // Add the edge to the end of the list
	
	while(((nextV = g.firstEdge(vertex)) != firstV)) {
	    g.removeEdge(vertex, nextV);
	    if(!isConn(g,nextV)) {
		g.addEdge(vertex,nextV);
		return nextV;
	    }
	    g.addEdge(vertex,nextV);
	}
	return -1;	
    }
    */
    
    private boolean isConn(Graph g, int start) {
	
	// Init the "Visited" array
	visited = new int[graph.getNumVert()][2];
	for (int i=0; i < graph.getNumVert(); i++){
	    visited[i][0] = i;
	    visited[i][1] = NOT_VISITED;
	}
	dfs(start);
	for(int i = 0; i < graph.getNumVert(); i ++) {
	    if(visited[i][1] == NOT_VISITED && degree[i] != 0)
		return false;
	}
	return true;
    }

    
    private void dfs(int startVertex){
	
	visited[startVertex][1] = VISITED; 

	for (int w=startVertex; w < graph.getNumVert(); w++){
	    if (graph.isAdj(startVertex,w)){ 
		if (visited[w][1] == NOT_VISITED){ // Visit it!
		    dfs(w); // Search from this vertex.
		}	
	    }
	}

	for (int w=0; w < startVertex; w++){
	    if (graph.isAdj(startVertex,w)){
		if (visited[w][1] == NOT_VISITED){ // Visit it!
		    dfs(w); // Search from this vertex.
		}
	    }
	}
    }

}