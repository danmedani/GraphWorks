import java.io.*;

/**
 * <code>DFS</code> takes a graph that the user enters and produces a 2-D array
 * containing the edges of the graph which are tree edges.
 *
 * @author Dan
 * @version 1.0
 */
public class DFS {
    
    /**
     * <code>graph</code> is the Graph that needs to be searched.
     *
     */
    private Graph graph; 
    /**
     * <code>treeEdges</code> is the 2-D int array that
     *  will hold the tree edges.
     *
     */
    private int[][] treeEdges;
    /**
     * <code>treeEdgesFound</code> keeps track of the # of edges currently 
     * added to the tree.
     *
     */
    private int treeEdgesFound = 0;

    final int VISITED = 1;
    final int NOT_VISITED = 0;
    
    /**
     * Creates a new <code>DFS</code> instance.
     *
     * @param graph a <code>Graph</code> value
     */
    public DFS(Graph graph) { 
	this.graph = graph;
	treeEdges = new int[graph.getNumVert()-1][3];
    }
    

    /**
     * <code>getTreeEdges</code> returns a 2-d array containing the tree
     * edges of g, using dfs.
     *
     * @param start the starting vertex of the search
     * @return the tree edges of graph
     */
    public int[][] getTreeEdges(int start){
	
	// Init the "Visited" array
	int[][] visited = new int[graph.getNumVert()][2];
	for (int i=0; i < graph.getNumVert(); i++){
	    visited[i][0] = i;
	    visited[i][1] = NOT_VISITED;
	}
	
	// Fill in treeEdges & return it
	dfs(start, visited, 1);	
	return treeEdges;
    }
    
    /**
     * <code>dfs</code> traverses the graph and fills in treeEdges
     * appropriately
     *
     * @param startVertex current vertex
     * @param visited which vertices have been visited
     */
    private void dfs(int startVertex, int[][] visited, int depth){
	
	visited[startVertex][1] = VISITED; 
	
	for (int w=startVertex; w < graph.getNumVert(); w++){
	    if (graph.isAdj(startVertex,w)){ 
		if (visited[w][1] == NOT_VISITED){ // Visit it!
		    treeEdges[treeEdgesFound][0] = startVertex;
		    treeEdges[treeEdgesFound][1] = w;
		    treeEdges[treeEdgesFound][2] = depth;
		    treeEdgesFound++;
		    dfs(w, visited, depth+1); // Search from this vertex.
		}
	    }
	}

	for (int w=0; w < startVertex; w++){
	    if (graph.isAdj(startVertex,w)){
		if (visited[w][1] == NOT_VISITED){ // Visit it!
		    treeEdges[treeEdgesFound][0] = startVertex;
		    treeEdges[treeEdgesFound][1] = w;
		    treeEdges[treeEdgesFound][2] = depth;
		    treeEdgesFound++;
		    dfs(w, visited, depth+1); // Search from this vertex.
		}
	    }
	}
    }    

    public boolean isConnected() {
	
	// Init the "Visited" array
	int[][] visited = new int[graph.getNumVert()][2];
	for (int i=0; i < graph.getNumVert(); i++){
	    visited[i][0] = i;
	    visited[i][1] = NOT_VISITED;
	}
	
	// Search It
	dfs(0, visited,1);

	
	for(int i = 0; i < graph.getNumVert(); i ++) {
	    if(visited[i][1] == NOT_VISITED) {
		return false;
	    }
	}
	return true;

    }


}