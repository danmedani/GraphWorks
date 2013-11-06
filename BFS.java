import java.io.*;
import java.util.*;

/**
 * <code>BFS</code> takes a graph that the user enters and produces a 2-d array
 * containing the edges of the graph which are tree edges.
 *
 * @author Team 1
 * @version 1.0
 */
public class BFS {
    
    //The graph that is being tested for connectedness
    private Graph g; 
    //2-d array to hold the graph's tree edges
    private int[][] treeEdges; 
    private int treeEdgesFound = 0;
    private int[] depth;

    final int VISITED = 1;
    final int NOT_VISITED = 0;
    
    
    private LinkedList<Integer> queue;

    
    /**
     * Creates a new <code>BFS</code> instance.
     *
     */
    public BFS(Graph newGraph){
	g = newGraph;
	treeEdges = new int[g.getNumVert()-1][3];
	queue = new LinkedList<Integer>();
    }
    

    /**
     * <code>getTreeEdges</code> returns a 2-d array containing the tree
     * edges of g..
     *
     * @return tree edges of g
     */
    public int[][] getTreeEdges(int start){
	
	int[][] visited = new int[g.getNumVert()][2];
	
	for (int i=0; i < g.getNumVert(); i++){
	    visited[i][0] = i;
	    visited[i][1] = NOT_VISITED;
	}
	
	depth = new int[g.getNumVert()];
	depth[start] = 0;

	queue.add(start);
	bfs(visited);
	
	
	return treeEdges;
    }
    
    /**
     * <code>bfs</code> traverses the graph and builds a 2-d array of tree 
     * edges.
     *
     * @param v current vertex
     * @param visited which vertices have been visited
     */
    private void bfs(int[][] visited){
	
	int v = queue.remove();
	
	visited[v][1] = VISITED;
	for (int w=v; w < g.getNumVert(); w++){
	    if (g.isAdj(v,w)){
		if (visited[w][1] == NOT_VISITED){
		    treeEdges[treeEdgesFound][0] = v;
		    treeEdges[treeEdgesFound][1] = w;
		    		    		    
		    depth[w] = depth[v] + 1;
		    treeEdges[treeEdgesFound][2] = depth[w];

		    treeEdgesFound++;
		    		    
		    visited[w][1] = VISITED;
		    queue.addLast(w);
		}
	    }
	}
	for (int w=0; w < v; w++){
	    if (g.isAdj(v,w)){
		if (visited[w][1] == NOT_VISITED){
		    treeEdges[treeEdgesFound][0] = v;
		    treeEdges[treeEdgesFound][1] = w;

		    depth[w] = depth[v] + 1;	   
		    treeEdges[treeEdgesFound][2] = depth[w];
		    
		    treeEdgesFound++;

		    visited[w][1] = VISITED;
		    queue.addLast(w);
		}
	    }
	}

	if(!queue.isEmpty()) {
	    bfs(visited);
	}


    }
    
    
}