/**
 * <code>Kruskal</code> takes a weighted graph and returns the edges in that 
 * graph that make up a minimum cost spanning tree.
 *
 * @author Team 1
 * @version 1.0
 */

import java.util.*;
import java.io.*;

public class Kruskal {
    
    private Graph g;                    // The graph being analyzed
    private HashSet<Integer>[] forest;  // Sets being used to find the 
                                        // spanning tree
    private int[] visited;              // Whether a vertex is already in the 
                                        // tree
    
    private PriorityQueue<KruskalEdge> edges;  //Priority queue of edges
    private WeightedEdgeComparator wec 
	= new WeightedEdgeComparator(); // Used to compare weighted edges
    private int vertsVisited = 0;       // Number of vertices visited
    private int numEdgesFound = 0;      // Number of edges found
    private int[][] treeEdges;          // Array of tree edges
    
    
    /**
     * Creates a new <code>Kruskal</code> instance.
     *
     * @param newGraph the graph to be analyzed
     * @param edges a 2-d array of the edges in newGraph
     */
    public Kruskal(Graph newGraph, int[][] e){

	g = newGraph;
	forest = new HashSet[g.getNumVert()];
	for (int i=0; i < g.getNumVert(); i++){
	    forest[i] = new HashSet<Integer>();
	    forest[i].add(new Integer(i));
	}
	
	edges = new PriorityQueue<KruskalEdge>(10,wec);
	for (int i=0; i < e.length; i++){
	    edges.add(new KruskalEdge(e[i][0],e[i][1],e[i][2]));
	}
	
	int[] visited = new int[g.getNumVert()];
	treeEdges = new int[g.getNumVert()-1][3];
    }
    
    /**
     * <code>buildSpanningTree</code> returns a 2-d array of edges that are
     * in the minimal cost spanning tree.
     *
     * @return 2-d array of tree edges
     */
    public int[][] buildSpanningTree(){
	
	while (!edges.isEmpty() && numEdgesFound < g.getNumVert()-1){
	    KruskalEdge e = edges.poll();
	    HashSet tree1 = forest[e.getVert1()];
	    HashSet tree2 = forest[e.getVert2()];

	    if (!tree1.equals(tree2)){
		addAllV(e);
		treeEdges[numEdgesFound][0] = e.getVert1();
		treeEdges[numEdgesFound][1] = e.getVert2();
		treeEdges[numEdgesFound][2] = e.getWeight();
		numEdgesFound++;
	    }
	}
	
	return treeEdges;
    }
	

    private void addAllV(KruskalEdge e) {
	
	HashSet tree1 = forest[e.getVert1()];
	HashSet tree2 = forest[e.getVert2()];

	Iterator<Integer> iterator = tree1.iterator();
	LinkedList<Integer> list = new LinkedList<Integer>();
	

	while(iterator.hasNext()) {
	    list.add(iterator.next());
	}
	for(int i = 0; i < list.size(); i ++) {
	    forest[list.get(i)].addAll(tree2);
	}
	list.clear();
	
	iterator = tree2.iterator();
	while(iterator.hasNext()) {
	    list.add(iterator.next());
	}
	for(int i = 0; i < list.size(); i ++) {
	    forest[list.get(i)].addAll(tree1);
	}
    }

    
    public static void main(String[] args) throws FileNotFoundException, 
						  InvalidInputException {
	
	GraphFactory factory = new GraphFactory(new File(args[0]));	
	Graph ng = new AdjacencyList(factory.getEdgeArray());

	Kruskal k = new Kruskal(ng, factory.getEdgeArray());
	int[][] tree = k.buildSpanningTree();

	for (int i=0; i < tree.length; i++){
	    System.out.println("("+tree[i][0]+","+tree[i][1]+"):"+tree[i][2]);
	}
    }
    
    
    
}