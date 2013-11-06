import java.util.LinkedList;

public class Component {
    
    private LinkedList<Integer> cutVertices;
    private int compNum;

    public Component(int compNum, int[][] cutMatrix) {

	this.compNum = compNum;
	cutVertices = new LinkedList<Integer>();
	
	for(int j = 0; j < cutMatrix[0].length; j ++) {
	    if(cutMatrix[compNum][j] == 1) {
		cutVertices.add(new Integer(j));
	    }
	}
    }
    

    public LinkedList<Integer> getCutVertices() {
	return cutVertices;
    }

    public String toString() {
	String retVal = "";
	retVal +="Comp# " + compNum + "\n";
	for(int i = 0; i < cutVertices.size(); i ++) {
	    retVal += cutVertices.get(i) + ", ";
	}
	return retVal;
    }

}