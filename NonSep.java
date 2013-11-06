import java.util.TreeSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.File;
import java.io.FileNotFoundException;

public class NonSep {
    
    Graph g;
    int[][] newComps;

    public NonSep(Graph graph){
	
	g = graph;
	NonSeparable tc = new NonSeparable(graph);
	newComps = tc.findNonSep();
    }
    
    public int[] reorder(){

	ArrayList<TreeSet> sets = new ArrayList<TreeSet>();
	int currentComp = newComps[0][2];
	TreeSet<Integer> currentSet = new TreeSet<Integer>();
	
	//Form sets for each component
	for (int i=0; i < newComps.length; i++){
	    if (newComps[i][2] != currentComp){
		sets.add(currentSet);
		currentSet = new TreeSet();
		currentComp = newComps[i][2];
	    }
	    currentSet.add(new Integer(newComps[i][0]));
	    currentSet.add(new Integer(newComps[i][1]));
	}
	
      
	
	// Construct initial set number matrix
	int[][] mat1 = new int[g.getNumVert()][sets.size()];
	for (int i=0; i < g.getNumVert(); i++){
	    for (int j=0; j < sets.size(); j++){
		if (sets.get(j).contains(new Integer(i))){
		    mat1[i][j] = 1;
		} else {
		    mat1[i][j] = 0;
		}
	    }
	}
	
	

	// Construct second set number matrix
	int sum = 0;
	int numCols = 0;
	ArrayList<Integer> cols = new ArrayList<Integer>();
	for (int i=0; i < g.getNumVert(); i++){
	    for (int j=0; j < sets.size(); j++){
		sum += mat1[i][j];
	    }
	    if (sum > 1){
		numCols++;
		cols.add(new Integer(i));
	    }
	    sum = 0;
	}

	int[][] mat2 = new int[sets.size()][numCols];
	for (int i=0; i < cols.size(); i++){
	    int[] currentCol = mat1[cols.get(i)];
	    for (int j=0; j < sets.size(); j++){
		mat2[j][i] = currentCol[j];
	    }
	}
	
	
	
	// Reorder matrix for nonseparable components
	boolean foundFirst = false;
	boolean foundSecond = false;
	int[] compOrder = new int[sets.size()];

	for (int i=0; i < compOrder.length; i++){
	    compOrder[i] = i;
	}


	for (int i=0; i < cols.size(); i++){
	    for (int j=0; j < sets.size(); j++){
		if (mat2[j][i] == 1){
		    if (!foundFirst){
			if (j != (sets.size()-(1+i)) && j !=
			    (sets.size()-(2+i))){
			    swapRow(mat2, j, sets.size()-(1+i));
			    swap(compOrder, j, sets.size()-(1+i));
			    foundFirst = true;
			}
			
		    } else if (!foundSecond){
			if (j != (sets.size()-(2+i)) && j != 
			    (sets.size()-(1+i))){
			    swapRow(mat2, j, sets.size()-(2+i));
			    swap(compOrder, j, sets.size()-(2+i));
			    foundSecond = true;
			}
			
		    }
		}
	    }
	    foundFirst = false;
	    foundSecond = false;
	}
	
	
    
	// Merge component sets into correctly ordered list.
	ArrayList<int[]> x = new ArrayList<int[]>();

	for (int i=0; i < compOrder.length; i++){
	    x.add(new int[sets.get(compOrder[i]).size()]);
	    
	    Iterator iter = sets.get(compOrder[i]).iterator();
	    int numAdded = 0;
	    while (iter.hasNext()){
		x.get(i)[numAdded] = (Integer)iter.next();
		numAdded++;
	    }
	}
	
	int sum2=0;
	for (int i=sets.size()-1; i >=0; i--){
	    for (int j=0; j < cols.size(); j++){
		sum2 += mat2[i][j];
	    }
	    if (sum2 == 1){
		if (i == sets.size()-1){
		     moveToFront(x.get(i),cols.get(sets.size()-(i+1)));
		} else {
		    moveToEnd(x.get(i),cols.get(cols.size()-1));
		}
	    } else {
		moveToEnd(x.get(i), cols.get(sets.size()-(i+2)));
		moveToFront(x.get(i), cols.get(sets.size()-(i+1)));
	    }
	 

	    sum2 = 0;
	}
	
	
	
	int[] reorder = new int[g.getNumVert()];
	int reorderPos = 0;
	for (int i=0; i < x.size(); i++){
	    int[] temp;
	    temp = x.get(i);
	    for (int j=0; j < temp.length; j++){
		if (reorderPos == 0){
		    reorder[reorderPos] = temp[j];
		    reorderPos++;
		} else {
		    if (reorder[reorderPos-1] != temp[j]){
			reorder[reorderPos] = temp[j];
			reorderPos++;
		    }
		}
	    }
	}
	
	for (int i=0; i < reorder.length; i++){
	    System.out.print(reorder[i]+" ");
	}
	
	System.out.println();
	
	return reorder;
	
    }

    public void swapRow(int[][] mat, int i, int j){
	
	int[] temp = mat[i];
	mat[i] = mat[j];
	mat[j] = temp;
    }
    
    public void swap(int[] mat, int i, int j){
	
	int temp = mat[i];
	mat[i] = mat[j];
	mat[j] = temp;
    }
    
    public void moveToFront(int[] mat, Integer i){
	
	int num = i.intValue();
	
	int pos;
	
	for (pos=0; pos < mat.length; pos++){
	    if (mat[pos] == num){
		break;
	    }
	}
	
	swap(mat, 0, pos);
    }
    
     public void moveToEnd(int[] mat, Integer i){
	
	int num = i.intValue();
	
	
	int pos;
	System.out.println("Length "+mat.length);
	
	for (pos=0; pos < mat.length; pos++){
	    if (mat[pos] == num){
		break;
	    }
	}
	
	swap(mat, mat.length-1, pos);
    }
    
    public static void main(String[] args) 
	throws FileNotFoundException, InvalidInputException {
	
	GraphFactory factory = new GraphFactory(new File(args[0]));
	Graph ng = new AdjacencyList(factory.getEdgeArray());
	
	NonSep ns = new NonSep(ng);
	ns.reorder();
    }
}