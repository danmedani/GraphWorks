import java.util.LinkedList;

public class CutVertex {
    
    private LinkedList<Integer> components;
    private int cutNum;
    private boolean isCut;

    public boolean isCutV() {
	return isCut;
    }

    public LinkedList<Integer> getComponents() {
	return components;
    }

    public CutVertex(int cutNum, int[][] cutMatrix) {

	if(cutMatrix[0][cutNum] == -1) {
	    isCut = false;
	}
	else {
	    isCut = true;
	    this.cutNum = cutNum;
	    components = new LinkedList<Integer>();
	    
	    for(int i = 0; i < cutMatrix.length; i ++) {
		if(cutMatrix[i][cutNum] == 1) {
		components.add(new Integer(i));
		}
	    }
	}
    }
    
    public String toString() {
	
	String retVal = "";
	retVal +="Cut Vertex# " + cutNum + "\n";
	for(int i = 0; i < components.size(); i ++) {
	    retVal += components.get(i) + ", ";
	}
	return retVal;
    }

}