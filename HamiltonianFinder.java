 import java.util.LinkedList;

public class HamiltonianFinder {

    int[][] edges;
    int numVertices;
    
    public HamiltonianFinder(int[][] edges, int numVertices) {
	this.edges = edges;
	this.numVertices = numVertices;

    }

    public LinkedList<Integer> findCycle() {
	return ham(0, new LinkedList<Integer>());
    }
    
    private LinkedList<Integer> ham(int V, LinkedList<Integer> list) {

	list.push(V);

	if(list.size() == numVertices) {
	    if(edgeExists(V, list.get(numVertices-1))) {
		return list;
	    }
	}
	else {
	    LinkedList<Integer> tempList = new LinkedList<Integer>();
	    for(int i = 0; i < numVertices; i++) {
		if(edgeExists(V, i) && (list.indexOf(i) == -1)) {
		    tempList = copyList(list);
		    if((tempList = ham(i, tempList)) != null) {
			return tempList;
		    }
		}
	    }
	}
		
	return null;
    }
    private boolean edgeExists(int v1, int v2) {
	for(int i = 0; i < edges.length; i++) {
	    if((edges[i][0] == v1 && edges[i][1] == v2) || 
	       (edges[i][0] == v2 && edges[i][1] == v1)) {
		return true;
	    }
	}
	return false;
    }
    
    private void printList(LinkedList<Integer> list) {
	for(int i = 0; i < list.size(); i ++) {
	    System.out.print(list.get(i) + " ");
	}System.out.println();
    }

    private LinkedList<Integer> copyList(LinkedList<Integer> list1) {
	LinkedList<Integer> list2 = new LinkedList<Integer>();
	for(int i = list1.size()-1; i >= 0;i--) {
	    list2.push(list1.get(i));
	}
	return list2;
    }

}
