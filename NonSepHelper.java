import java.util.LinkedList;

public class NonSepHelper {

    private int[][] edges;
    int[][] componentMatrix;
    int numVerts, numComps;

    Component[] component;
    CutVertex[] cutVertex;

    // DFS Stuff
    int[] cutVisited, compVisited;
    final int VISITED = 1;
    final int NOT_VISITED = 0;

    
    // Ordering Stuff
    int[] order;
    int cutContext;
    

    public NonSepHelper(int[][] edges) {
	this.edges = edges;
	this.cutContext = -2;
    }

    public int[] produceOrder() {


	// Grab number of components
	numComps = edges[edges.length-1][2] + 1;


	// Grab number of vertices
	int highVertNum = -1;
	for(int i = 0; i < edges.length; i ++) {
	    if(edges[i][0] > highVertNum) {
		highVertNum = edges[i][0];
	    }
	    if(edges[i][1] > highVertNum) {
		highVertNum = edges[i][1];
	    }
	}
	numVerts = highVertNum + 1;
	
	
	// Create component matrix
	componentMatrix = new int[numComps][numVerts];
	for(int i = 0; i < edges.length; i ++) {
	    componentMatrix[edges[i][2]][edges[i][0]] = 1;
	    componentMatrix[edges[i][2]][edges[i][1]] = 1;
	}
	for(int i = 0; i < componentMatrix.length; i ++) {
	    for(int j = 0; j < componentMatrix[0].length; j ++) {
		if(componentMatrix[i][j] != 1) {
		    componentMatrix[i][j] = 0;
		}
	    }
	}
		
	// Create Cut Matrix
	int[][] cutMatrix = new int[numComps][numVerts];
	for(int j = 0; j < numVerts; j ++) {
	    if(! isCutVert(j)) {
		// Fill the column with -1s
		for(int i = 0; i < numComps; i ++) {
		    cutMatrix[i][j] = -1;
		}
	    }
	    else {
		// Copy the column
		for(int i = 0; i < numComps; i ++) {
		    cutMatrix[i][j] = componentMatrix[i][j];
		}
	    }
	}
	
	// Build Array of Components
	component = new Component[numComps];
	for(int i = 0; i < numComps; i ++) {
	    component[i] = new Component(i,cutMatrix);
	}


	// Build Array of Cut Vertices
	cutVertex = new CutVertex[numVerts];
	for(int j = 0; j < numVerts; j ++) {
	    cutVertex[j] = new CutVertex(j,cutMatrix);
	}

	// The final order
	order = new int[numVerts];


	// Init Visited Arrays
	compVisited = new int[numComps];
	for(int i = 0; i < numComps; i ++) {
	    compVisited[i] = NOT_VISITED;
	}

	cutVisited = new int[numVerts];
	for(int i = 0; i < numVerts; i ++) {
	    cutVisited[i] = NOT_VISITED;
	}

	// DFS!!!
	for(int i = 0; i < numComps; i ++) {
	    if(compVisited[i] == NOT_VISITED) {
		compDFS(i);
		cutContext = -1;
	    }
	}
	
	return order;
    }

   private void cutDFS(int vNum) {
	cutVisited[vNum] = VISITED;

	LinkedList<Integer> comps = cutVertex[vNum].getComponents();
	for(int i = 0; i < comps.size(); i ++) {
	    if(compVisited[comps.get(i)] == NOT_VISITED) {
		cutContext = vNum;
		compDFS(comps.get(i));
	    }
	}
    }
    private void compDFS(int cNum) {
	insert(cNum);
	compVisited[cNum] = VISITED;

	LinkedList<Integer> cuts = component[cNum].getCutVertices();
	for(int i = 0; i < cuts.size(); i ++) {
	    if(cutVisited[cuts.get(i)] == NOT_VISITED) {
		cutDFS(cuts.get(i));
	    }
	}
    }

    private boolean isCutVert(int vertex) {
	int count = 0;
	for(int i = 0; i < componentMatrix.length; i ++) {
	    count += componentMatrix[i][vertex];
	}
	if(count > 1) {
	    return true;
	}
	else {
	    return false;
	}
    }

    private void insert(int cNum) {


	int start;
	if(cutContext == -2) { // First Insert, init the order array.
	    for(int i = 0; i < order.length; i ++) {
		order[i] = -1;
	    }
	    start = 0;
	}
	else if(cutContext == -1) { // New Component.
	    start = indexInOrder(cutContext);
	}
	else {
	    start = indexInOrder(cutContext) + 1;	    
	}

	// Grab all vertices in the component
	LinkedList<Integer> vertexNumbers = new LinkedList<Integer>();  
	for(int j = 0; j < componentMatrix[0].length; j ++) {
	    if((componentMatrix[cNum][j] == 1) && (j != cutContext) &&
	       (indexInOrder(j) == -1)) {
		vertexNumbers.add(j);
	    }
	}	

	// Shift!
	for(int i = order.length - 1; i >= (start + vertexNumbers.size());
	    i --) {
	    order[i] = order[i-vertexNumbers.size()];
	}
	
	// Insert!
	for(int i = start; i < start + vertexNumbers.size(); i ++) {
	    order[i] = vertexNumbers.get(i - start);
	}

    }

    private int indexInOrder(int val) {
	for(int i = 0; i < order.length; i ++) {
	    if(order[i] == val) {
		return i;
	    }
	}
	return -1;
    }

    private void printOrder() {
	System.out.println();
	for(int i = 0; i < order.length; i ++) {
	    System.out.print(order[i] + " ");
	}
	System.out.println();
    }
   

}