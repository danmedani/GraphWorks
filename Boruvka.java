

/**
 * <code>Boruvka</code> takes a weighted graph and returns the edges in that 
 * graph that make up a minimum cost spanning tree.
 *
 * @author Team 1
 * @version 1.0
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.io.File;
import java.io.FileNotFoundException;


public class Boruvka {
    
    private ArrayList<ArrayList<Integer>> forest; //Trees used to find MCST
    private Graph g;                              //Graph being analyzed
    private ArrayList<KruskalEdge> edges;         //Weighted edges in graph
    private int pass;                             //Number of passes made
    
    /**
     * Creates a new <code>Boruvka</code> instance.
     *
     * @param graph a <code>Graph</code> value
     */
    public Boruvka(Graph graph){
	
	g = graph;
	forest = new ArrayList<ArrayList<Integer>>();
	for (int i=0; i < g.getNumVert(); i++){
	    forest.add(new ArrayList<Integer>());
	    forest.get(i).add(new Integer(i));
	}
	pass = 0;
    }

    /**
     * <code>findTreeEdges</code> finds the edges of the graph that go in
     * the graph's minimal cost spanning tree.
     *
     * @return an array of edges
     */
    public int[][] findTreeEdges(){
	
	int[][] treeEdges = new int[g.getNumVert()-1][4];
	ArrayList<KruskalEdge>possEdges = new ArrayList<KruskalEdge>();
	
	while(forest.size() > 1){
	    for (int i=0; i < forest.size(); i++){
		ArrayList<Integer> currentTree = forest.get(i);
		int minWeight = 90000;
		int minVertPos = -1;
		int minVert = -1;
		int minEdgePos = -1;
		for (int j=0; j < currentTree.size(); j++){
		    LinkedList<Edge> currentEdges = g.
			getEdges(currentTree.get(j));
		    for (int k=0; k < currentEdges.size(); k++){
			if (currentEdges.get(k).getWeight() < minWeight){
			    minWeight = currentEdges.get(k).getWeight();
			    minVertPos = j;
			    minVert = currentTree.get(j);
			    minEdgePos = k;
			}
		    }
		}      
		KruskalEdge possEdge = 
		    new KruskalEdge(minVert, 
				    g.getEdges(currentTree.
					       get(minVertPos)).
				    get(minEdgePos).getOtherEnd(),
				    minWeight, pass);
		possEdges.add(possEdge);	
	    }
	    
	    removeDuplicates(possEdges);
	    for (int i=0; i < possEdges.size(); i++){
		int vert1 = possEdges.get(i).getVert1();
		int vert2 = possEdges.get(i).getVert2();
		g.removeEdge(vert1, vert2);
		int vert1Set = -1;
		int vert2Set = -1;
		for (int j=0; j < forest.size(); j++){
		    ArrayList<Integer> currentTree = forest.get(j);
		    for (int k=0; k < currentTree.size(); k++){
			if (currentTree.get(k) == vert1){
			    vert1Set = j;
			}
			if (currentTree.get(k) == vert2){
			    vert2Set = j;
			}
		    }
		}
		if (vert1Set != vert2Set){
		    forest.get(vert1Set).addAll(forest.get(vert2Set));
		    forest.remove(vert2Set);
		}
	    }
	    pass++;
	}
	for (int i=0; i < possEdges.size(); i++){
	    treeEdges[i][0] = possEdges.get(i).getVert1();
	    treeEdges[i][1] = possEdges.get(i).getVert2();
	    treeEdges[i][2] = possEdges.get(i).getWeight();
	    treeEdges[i][3] = possEdges.get(i).getPass();
	}
	return treeEdges;	
    }
    
    /**
     * <code>removeDuplicates</code> takes an ArrayList of edges
     * and removes duplicate edges from the list.
     *
     */
    public void removeDuplicates(ArrayList<KruskalEdge> edges){
	for (int i=0; i < edges.size(); i++){
	    KruskalEdge currentEdge = edges.get(i);
	    for (int j=0; j < edges.size(); j++){
		if (j != i){
		    KruskalEdge nextEdge = edges.get(j);
		    if (currentEdge.compareTo(nextEdge) == 0){
			edges.remove(nextEdge);
		    }
		}
	    }
	}
    }


    public static void main(String[] args) throws FileNotFoundException,
						  InvalidInputException {
	
	GraphFactory factory = new GraphFactory(new File(args[0]));
	int[][] ed = factory.getEdgeArray();
	
	Graph ng = new AdjacencyList(ed,true);	
	Boruvka b = new Boruvka(ng);
	int[][] treeEdges = b.findTreeEdges();
	
	for (int i=0; i < treeEdges.length; i++){
	    System.out.println("("+treeEdges[i][0]+","+
			       treeEdges[i][1]+"):"+treeEdges[i][2]);
	}
	
    }
 
}

