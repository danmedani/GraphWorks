import java.util.LinkedList;
import java.io.*;

/**
 * <code>EulerSearchSplice</code> takes a graph that the user enters and 
 * produces a 2-D array
 * containing the edges of the graph which are tree edges.
 *
 * @author Dan
 * @version 1.0
 */
public class EulerSearchSplice {
    
    /**
     * <code>graph</code> is the Graph that needs to be searched.
     *
     */
    private Graph graph; 
    private Graph eGraph;
    
    /**
     * <code>treeEdges</code> is the 2-D int array that will hold the
     * tree edges.
     *
     */
    private int[][] treeEdges;
    private int[][] tempEdges;
    private int tempEdgesAdded = 0;
   
    final int VISITED = 1;
    final int NOT_VISITED = 0;

    private int[][] visited;
    private int[] degree;
    private boolean[] vVisit;
    private int numEdges;

    private LinkedList<LinkedList<Integer>> queue;
    
    /**
     * Creates a new <code>EulerSearchSplice</code> instance.
     *
     * @param graph a <code>Graph</code> value
     */
    public EulerSearchSplice(Graph graph) { 
	this.graph = graph;
	this.eGraph = graph;
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
	vVisit = new boolean[graph.getNumVert()];
	numEdges = 0;
	boolean hasEulerCircuit = true;
	
	for (int i=0; i < graph.getNumVert(); i++) {
	    degree[i] = graph.getDegree(i);
	    vVisit[i] = false;
	    numEdges += graph.getDegree(i);
	    
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
	tempEdges = new int[numEdges][3];

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
    
    private LinkedList<Integer> getCircuit(int vStart, int compNumber) {

	LinkedList<Integer> retVal = new LinkedList<Integer>();
	retVal.add(new Integer(vStart));
	
	int currentVertex = vStart;
	int nextVertex;
	vVisit[vStart] = true;
	
	while((nextVertex = eGraph.firstEdge(currentVertex)) != -1) {
	    
	    vVisit[nextVertex] = true;	 	    
	    retVal.add(new Integer(nextVertex));	    
	    
	    // Remove the edge
	    eGraph.removeEdge(currentVertex, nextVertex);
	    degree[currentVertex] --;
	    degree[nextVertex] --;
	    numEdges --;

	    // Add to temp list
	    tempEdges[tempEdgesAdded][0] = currentVertex;
	    tempEdges[tempEdgesAdded][1] = nextVertex;
	    tempEdges[tempEdgesAdded][2] = compNumber;
	    tempEdgesAdded ++;
	    
	    currentVertex = nextVertex;	    
	}
	return retVal;
    }
    
    /**
     * <code>dfs</code> traverses the graph and fills in treeEdges
     *  appropriately
     *
     * @param startVertex current vertex
     * @param visited which vertices have been visited
     */
    private void euler(int startVertex){

	int compNumber = 0;
	queue = new LinkedList<LinkedList<Integer>>();
	// Loop through each vertex
	while(numEdges > 0) {
	    for(int i = startVertex; i < graph.getNumVert(); i ++) {
		if(degree[i] > 0 && (vVisit[i] || queue.size() == 0)) {
		    LinkedList<Integer> temp = getCircuit(i,compNumber);
		    queue.addLast(temp);
		    compNumber ++;
		}
	    }
	    for(int i = 0; i < startVertex; i ++) {
		if(degree[i] > 0 && (vVisit[i] || queue.size() == 0)) {
		    LinkedList<Integer> temp = getCircuit(i,compNumber);
		    queue.addLast(temp);		    
		    compNumber ++;
		}
	    }
	}
	for(int i = 0; i < queue.size() - 1; i ++) {
	    LinkedList<Integer> list1 = queue.get(i);	   
	    LinkedList<Integer> list2 = queue.get(i + 1);
	    list1 = mergeLists(list1,list2);

	    // Set queue(i+1) to the newly merged list
	    queue.set(i+1,list1);
	}

	// We have it! Time to make the edge array
	for(int i = 0; i < treeEdges.length; i ++) {
	    treeEdges[i][0] = queue.get(queue.size()-1).get(i);
	    treeEdges[i][1] = queue.get(queue.size()-1).get(i+1);
	    treeEdges[i][2] = getComponent(treeEdges[i][0], treeEdges[i][1]);
	}
    }

    private LinkedList<Integer> mergeLists(LinkedList<Integer> list1, 
					   LinkedList<Integer> list2) {

	LinkedList<Integer> retVal = new LinkedList<Integer>();
	int cutVertex = list2.getFirst();
	boolean merged = false;
	
	for(int i = 0; i < list1.size(); i ++) {
	    if(list1.get(i) != cutVertex) {
		retVal.add(list1.get(i));
	    }
	    else if(!merged) {
		for(int j = 0; j < list2.size(); j ++) {
		    retVal.add(list2.get(j));
		}
		merged = true;
	    }
	    else {
		retVal.add(list1.get(i));
	    }
	}
	if((list1.size() + list2.size() - 1) != retVal.size()) {
	    System.out.println("HEY!");
	}

	return retVal;	
    }

    private int getComponent(int v1, int v2) {
	for(int i = 0; i < tempEdges.length; i ++) {
	    if((tempEdges[i][0] == v1 && tempEdges[i][1] == v2) || 
	       (tempEdges[i][1] == v1 && tempEdges[i][0] == v2)) {
		return tempEdges[i][2];
	    }
	}
	return -1;
    }

    
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

    
    private void dfs(int startVertex) {
	
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