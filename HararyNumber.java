/**
 * <code>HararyNumber</code> takes as input a graph on five or fewer 
 * vertices and outputs the graph's harary number..
 *
 * @author Team 1
 * @version 1.0
 */

import java.io.*;

public class HararyNumber {

    Graph g;   //Graph whose harary number is being checked.
    
    
    /**
     * Creates a new <code>HararyNumber</code> instance.
     *
     * @param input graph whose harary number
     */
    public HararyNumber(Graph g){
	this.g = g;
	
    }
    
    /**
     * <code>getHararyNumber</code> returns the graph's harary number.
     *
     * @return harary number of g
     */
    public int[] getHararyNumber(){
	
	int numVertices = g.getNumVert();
	int[] degSeq = new int[numVertices];
	int[] hararyNumber = new int[3];
	hararyNumber[0] = g.getNumVert();
	int v = g.firstVertex();
	
	for (int i=0; i < numVertices; i++){
	    degSeq[i] = g.getDegree(v);
	    v = g.nextVertex(v);
	}
	
	sort(degSeq);
	int degSeqInt = 0;
	for (int i=0; i < degSeq.length; i++){
	    degSeqInt = degSeqInt*10 + degSeq[i];
	}
	

	
	
	switch (numVertices){
	case 1: 
	    hararyNumber[1] = 0;
	    hararyNumber[2] = 1;
	    break;
	case 2:
	    switch (degSeqInt){
	    case 0:
		hararyNumber[1] = 0;
		hararyNumber[2] = 1;
		break;
	    case 11:
		hararyNumber[1] = 1;
		hararyNumber[2] = 1;
		break;
	    }
	case 3:
	    switch (degSeqInt){
	    case 0:
		hararyNumber[1] = 0;
		hararyNumber[2] = 1;
		break;
	    case 110:
		hararyNumber[1] = 1;
		hararyNumber[2] = 1;
		break;
	    case 211:
		hararyNumber[1] = 2;
		hararyNumber[2] = 1;
		break;
	    case 222:
		hararyNumber[1] = 3;
		hararyNumber[2] = 1;
		break;
	    }
	case 4: 
	    switch (degSeqInt){
	    case 0:
		hararyNumber[1] = 0;
		hararyNumber[2] = 1;
		break;
	    case 1100:
		hararyNumber[1] = 1;
		hararyNumber[2] = 1;
		break;
	    case 2110:
		hararyNumber[1] = 2;
		hararyNumber[2] = 1;
		break;
	    case 1111:
		hararyNumber[1] = 2;
		hararyNumber[2] = 2;
		break;
	    case 2220:
		hararyNumber[1] = 3;
		hararyNumber[2] = 1;
		break;
	    case 2211:
		hararyNumber[1] = 3;
		hararyNumber[2] = 2;
		break;
	    case 3111:
		hararyNumber[1] = 3;
		hararyNumber[2] = 3;
		break;
	    case 3221:
		hararyNumber[1] = 4;
		hararyNumber[2] = 1;
		break;
	    case 2222:
		hararyNumber[1] = 4;
		hararyNumber[2] = 2;
		break;
	    case 3322:
		hararyNumber[1] = 5;
		hararyNumber[2] = 1;
		break;
	    case 3333:
		hararyNumber[1] = 6;
		hararyNumber[2] = 1;
		break;
	    }
	case 5: 
	    switch (degSeqInt) {
	    case 0:
		hararyNumber[1] = 0;
		hararyNumber[2] = 1;
		break;
	    case 11000:
		hararyNumber[1] = 1;
		hararyNumber[2] = 1;
		break;
	    case 21100:
		hararyNumber[1] = 2;
		hararyNumber[2] = 1;
		break;
	    case 11110:
		hararyNumber[1] = 2;
		hararyNumber[2] = 2;
		break;
	    case 22110:
		hararyNumber[1] = 3;
		hararyNumber[2] = 1;
		break;
	    case 22200:
		hararyNumber[1] = 3;
		hararyNumber[2] = 2;
		break;
	    case 31110:
		hararyNumber[1] = 3;
		hararyNumber[2] = 3;
		break;
	    case 21111:
		hararyNumber[1] = 3;
		hararyNumber[2] = 4;
		break;
	    case 22220:
		hararyNumber[1] = 4;
		hararyNumber[2] = 1;
		break;
	    case 32210:
		hararyNumber[1] = 4;
		hararyNumber[2] = 2;
		break;
	    case 41111:
		hararyNumber[1] = 4;
		hararyNumber[2] = 3;
		break;
	    case 22211:
		int v1, v2;
		int vert = g.firstVertex();
		while (g.getDegree(vert) != 1){
		    vert = g.nextVertex(vert);
		}
		v1 = vert;
		vert = g.nextVertex(vert);
		while (g.getDegree(vert) != 1){
		    vert = g.nextVertex(vert);
		}
		v2 = vert;
		if (g.isAdj(v1, v2)){
		    hararyNumber[1] = 4;
		    hararyNumber[2] = 5;
		} else {
		    hararyNumber[1] = 4;
		    hararyNumber[2] = 4;
		}
		break;
	    case 32111:
		hararyNumber[1] = 4;
		hararyNumber[2] = 6;
		break;
	    case 33220:
		hararyNumber[1] = 5;
		hararyNumber[2] = 1;
		break;
	    case 33211:
		hararyNumber[1] = 5;
		hararyNumber[2] = 2;
		break;
	    case 32221:
		int v3, v4;
		int vert2 = g.firstVertex();
		while (g.getDegree(vert2) != 1){
		    vert2 = g.nextVertex(vert2);
		}
		v3 = vert2;
		vert2 = g.firstVertex();
		while (g.getDegree(vert2) != 3){
		    vert2 = g.nextVertex(vert2);
		}
		v4 = vert2;
		if (g.isAdj(v3, v4)){
		    hararyNumber[1] = 5;
		    hararyNumber[2] = 3;
		} else {
		    
		    hararyNumber[1] = 5;
		    hararyNumber[2] = 4;
		}
		break;
	    case 42211:
		hararyNumber[1] = 5;
		hararyNumber[2] = 5;
		break;
	    case 22222:
		hararyNumber[1] = 5;
		hararyNumber[2] = 6;
		break;
	    case 42222:
		hararyNumber[1] = 6;
		hararyNumber[2] = 1;
		break;
	    case 43221:
		hararyNumber[1] = 6;
		hararyNumber[2] = 2;
		break;
	    case 33330:
		hararyNumber[1] = 6;
		hararyNumber[2] = 3;
		break;
	    case 33222:
		int v5, v6;
		int vert3 = g.firstVertex();
		while (g.getDegree(vert3) != 3){
		    vert3 = g.nextVertex(vert3);
		}
		v5 = vert3;
		vert3 = g.nextVertex(vert3);
		while (g.getDegree(vert3) != 3){
		    vert3 = g.nextVertex(vert3);
		}
		v6 = vert3;
		if (g.isAdj(v5, v6)){
		    hararyNumber[1] = 6;
		    hararyNumber[2] = 4;
		} else {
		    hararyNumber[1] = 6;
		    hararyNumber[2] = 5;
		}
		break;
	    case 33321:
		hararyNumber[1] = 6;
		hararyNumber[2] = 6;
		break;
	    case 43322:
		hararyNumber[1] = 7;
		hararyNumber[2] = 1;
		break;
	    case 44222:
		hararyNumber[1] = 7;
		hararyNumber[2] = 2;
		break;
	    case 43331:
		hararyNumber[1] = 7;
		hararyNumber[2] = 3;
		break;
	    case 33332:
		hararyNumber[1] = 7;
		hararyNumber[2] = 4;
		break;
	    case 44332:
		hararyNumber[1] = 8;
		hararyNumber[2] = 1;
		break;
	    case 43333:
		hararyNumber[1] = 8;
		hararyNumber[2] = 2;
		break;
	    case 44433:
		hararyNumber[1] = 9;
		hararyNumber[2] = 1;
		break;
	    case 44444:
		hararyNumber[1] = 10;
		hararyNumber[2] = 1;
		break;
	    }
	    break;
	default: System.out.println("Invalid number of vertices");
	    hararyNumber[1] = -1;
	    hararyNumber[2] = 1;
	    break;
	}
	return hararyNumber;
    }

 
    
    /**
     * <code>sort</code> sorts the array of degree sequences in the 
     * <code>getHararyNumber</code> method.
     *
     * @param arr an <code>int</code> value
     */
    private void sort(int[] arr){
	
	for (int i=0; i < arr.length; i++){
	    int largest = -1; //Largest possible value is 4
	    int largestPos = -1;
	    for (int j=i; j < arr.length; j++){
		if(arr[j] > largest){
		    largest = arr[j];
		    largestPos = j;
		}
	    }
	    swap(arr, i, largestPos);	    
	}
    }
    
    /**
     * <code>swap</code> swaps the element at position i in arr with the
     * element at position j in arr.
     *
     * @param arr array holding the values being swapped
     * @param i position of first value
     * @param j position of second value
     */
    private void swap(int[] arr, int i, int j){
	int temp = arr[i];
	arr[i] = arr[j];
	arr[j] = temp;
    }
    
    public static void main(String[] args) throws FileNotFoundException {
	/*
	File inFile = new File(args[0]);
	
	GraphFactory factory = new GraphFactory(inFile);
	GraphReader reader = factory.getGraphReader();
	
	int[][] edges = reader.getEdgeArray();
	
	Graph g = new AdjacencyList(edges);
	
	HararyNumber harary = new HararyNumber();
	
	int[] hNum = harary.getHararyNumber();
	
	System.out.println("Harary Number p="+hNum[0]+" q="+hNum[1]+" #="+
			   hNum[2]);
	*/
	
    }
    

}