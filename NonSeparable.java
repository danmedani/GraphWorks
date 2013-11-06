import java.util.Stack;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.Math;

/**
 * <code>NonSeparable</code> is used to find the non-separable components
 * of a graph.
 *
 * @author Team 1
 * @version 1.0
 */
public class NonSeparable {
    
    private Graph g;
    private Stack<Integer> edgeStack;
    private int[][] comps;
    private int[] number;
    private int[] lowpt;
    private int i;
    public int numEdges;
    private int numComps;
    
    /**
     * Creates a new <code>NonSeparable</code> instance.
     *
     * @param graph the graph being analyzed
     */
    public NonSeparable(Graph graph){
	
	g = graph;
	edgeStack = new Stack<Integer>();
	comps = new int[((g.getNumVert())*(g.getNumVert()-1))/2][3];
	number = new int[g.getNumVert()];
	for (int i=0; i < number.length; i++){
	    number[i] = -1;
	}
	lowpt = new int[g.getNumVert()];
	for (int i=0; i < lowpt.length; i++){
	    lowpt[i] = -1;
	}
	numComps=0;
	numEdges = 0;
    }
    
    /**
     * <code>findNonSep</code> returns a 2-d array containing the 
     * nonseparable components of the graph.
     *
     * @return 2-d array of nonseparable components
     */
    public int[][] findNonSep(){
	i=0;
	for (int j=0; i < g.getNumVert(); j++){
	    if (number[j] == -1){
		biconnect(j,0);
	    }
	}
	
	int[][] compsFinal = new int[numEdges][3];
	for (int j=0; j < numEdges; j++){
	    compsFinal[j][0] = comps[j][0];
	    compsFinal[j][1] = comps[j][1];
	    compsFinal[j][2] = comps[j][2];
	}
	
	
	return compsFinal;
    }
    
    /**
     * <code>biconnect</code> is used by findNonSep to find the graph's
     * nonseparable components by performing a depth-first search..
     *
     * @param currentVertex the vertex currently being examined
     * @param u the last vertex to be visited
     */
    private void biconnect(int currentVertex, int u){
	
	number[currentVertex] = i;
	i++;
	
	lowpt[currentVertex] = number[currentVertex];
	for (int i=0; i < g.getNumVert(); i++){
	    if (g.isAdj(currentVertex,i)){
		if (number[i] == -1){
		    edgeStack.push(new Integer(i));
		    edgeStack.push(new Integer(currentVertex));
		    biconnect(i,currentVertex);
		    lowpt[currentVertex] = Math.min(lowpt[currentVertex],
						    lowpt[i]);
		    if (lowpt[i] >= number[currentVertex]){
			while(number[edgeStack.peek()] >= number[i]){
			    comps[numEdges][0] = edgeStack.pop();
			    comps[numEdges][1] = edgeStack.pop();
			    comps[numEdges][2] = numComps;
			    numEdges++;
			}
			comps[numEdges][0] = edgeStack.pop();
			comps[numEdges][1] = edgeStack.pop();
			comps[numEdges][2] = numComps;
			numEdges++;
			numComps++;
		    }		    
		} else if ((number[i] < number[currentVertex]) && (i != u)){
		    edgeStack.push(new Integer(i));
		    edgeStack.push(new Integer(currentVertex));
		    lowpt[currentVertex] = Math.min(lowpt[currentVertex],
						    number[i]);
		}
	    }
	}
    }
    
    public static void main(String[] args) 
	throws FileNotFoundException, InvalidInputException {
	
	GraphFactory factory = new GraphFactory(new File(args[0]));
	Graph ng = new AdjacencyList(factory.getEdgeArray());
	NonSeparable ns = new NonSeparable(ng);
	int[][] nonSepComps = ns.findNonSep();
	
	for (int i=0; i < ns.numEdges; i++){
	    System.out.println(i+":("+nonSepComps[i][0]+
			       ","+nonSepComps[i][1]+
			       "):"+nonSepComps[i][2]);
	}
	
    }
}