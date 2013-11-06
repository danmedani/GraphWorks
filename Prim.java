import java.util.*;

public class Prim {

    private Graph graph;
    private int[][] edges;
    private int[][] mcstEdges; 
    private int numAdded;
    private int numVertices;
    
    private LinkedList<Integer> inGraph;
    private LinkedList<Integer> available;
    private LinkedList<Integer> notSeen;
    
    public Prim(Graph g, int[][] e) {
	graph = g;
	edges = e;
	numVertices = g.getNumVert();
	numAdded = 0;

	mcstEdges = new int[numVertices - 1][3];
	inGraph = new LinkedList<Integer>();
	available = new LinkedList<Integer>();
	notSeen = new LinkedList<Integer>();

    }

    public int[][] getEdges() {
	
	// Initialize the three lists
	inGraph.add(0);	
	for(int i = 1; i < numVertices; i ++) {
	    if(edgeExists(0,i) != -1) {
		available.add(i);
	    }
	    else {
		notSeen.add(i);
	    }
	}


	// Run Prim's Algorithm
	int temp, lowEdgeWeight=-1, lowV1=-1, lowV2=-1;
	LinkedList<Integer> tempList = new LinkedList<Integer>();
	while(inGraph.size() < numVertices) {
	    lowEdgeWeight = 900000;
	    for(int i = 0; i < inGraph.size(); i ++) {
		for(int j = 0; j < available.size(); j ++) {
		 
		    if((temp = edgeExists(inGraph.get(i),available.get(j))) 
		       != -1) {
			if(temp < lowEdgeWeight) {
			    lowEdgeWeight = temp;
			    lowV1 = inGraph.get(i);
			    lowV2 = available.get(j);

			}
		    }
		}
	    }

	    // Add the edge
	    mcstEdges[numAdded][0] = lowV1;
	    mcstEdges[numAdded][1] = lowV2;
	    mcstEdges[numAdded][2] = lowEdgeWeight;
	    numAdded ++;

	    // Add new Vert
	    inGraph.add(lowV2);
	    available.remove(new Integer(lowV2));
	    


	    // Update Available Verts
	    tempList.clear();
	    for(int i = 0; i < notSeen.size(); i ++) {
		if(edgeExists(lowV2, notSeen.get(i)) != -1) {
		    tempList.add(notSeen.get(i));
		}
	    }
	    for(int i = 0; i < tempList.size(); i++) {
		available.add(new Integer(tempList.get(i)));
		notSeen.remove(new Integer(tempList.get(i)));
	    }
	   

	    
	}	
	return mcstEdges;
	
    } 

    private int edgeExists(int v1, int v2) {
	for(int i = 0; i < edges.length; i++) {
	    if((edges[i][0] == v1 && edges[i][1] == v2) || 
	       (edges[i][0] == v2 && edges[i][1] == v1)) {
		return edges[i][2];
	    }
	}
	return -1;
    }
}