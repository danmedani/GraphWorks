/**
 * <code>TwoConn</code> finds the biconnected components of a graph and returns
 * an array of integers representing the order in which the graph's vertices
 * must be printed to correctly display the biconnected components.
 *
 * @author Team 1
 * @version 1.0
 */

import java.lang.Math;
import java.util.Stack;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.TreeSet;
import java.util.ArrayList;
import java.util.Iterator;

public class TwoConn {
    
    private Graph g;
    private Stack<Integer> nodes;
    private int count;
    private int numComps = 0;
    private int numEdgesFound = 0;
    private int[][] comps;
    private boolean[] visited;
    private int[] parent;
    private int[] d;
    private int[] low;
    
    /**
     * Creates a new <code>TwoConn</code> instance.
     *
     * @param newGraph Graph to be analyzed
     */
    public TwoConn(Graph newGraph){
	
	g = newGraph;
	comps = new int[(g.getNumVert()*(g.getNumVert()-1)/2)][3];
	visited = new boolean[g.getNumVert()];
	parent = new int[g.getNumVert()];
	d = new int[g.getNumVert()];
	low = new int[g.getNumVert()];
	
	for (int i=0; i < comps.length; i++){
	    comps[i][0] = -1;
	    comps[i][1] = -1;
	    comps[i][2] = -1;
	}
    }
    

    public int[][] findComps(){
	
	count = 0;
	nodes = new Stack<Integer>();
	for (int i=0; i < g.getNumVert(); i++){
	    visited[i] = false;
	    parent[i] = -1;
	}
	
	for (int i=0; i < g.getNumVert(); i++){
	    if (!visited[i]){
		dfsVisit(i);
	    }
	}

	return comps;
	
    }
    
    public void dfsVisit(int u){
	
	visited[u] = true;
	count++;
	d[u] = count;
	low[u] = d[u];
	for (int v=0; v < g.getNumVert(); v++){
	    if (g.isAdj(u,v)){
		if (!visited[v]){
		    nodes.push(new Integer(u));
		    nodes.push(new Integer(v));
		    parent[v] = u;
		    dfsVisit(v);
		    if (low[v] >= d[u]){
			outputComp(u,v);
		    }
		    low[u] = Math.min(low[u], low[v]);
		} else if(!(parent[u]==v) && (d[v] < d[u])){
		    nodes.push(new Integer(u));
		    nodes.push(new Integer(v));
		    low[u] = Math.min(low[u], d[v]);
		}
	    }
	}
    }
    
    public void outputComp(int u, int v){
	int v1;
	int v2;
	System.out.println(u + "  " + v);
	do {
	    v1 = nodes.pop().intValue();
	    v2 = nodes.pop().intValue();
	    System.out.println("("+v2+", "+v1+")");
	    comps[numEdgesFound][0] = v2;
	    comps[numEdgesFound][1] = v1;
	    comps[numEdgesFound][2] = numComps;
	    numEdgesFound++;
	} while (v2 != u && v1 != v);
	numComps++;
    }
    
}