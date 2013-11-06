import java.io.*;
import javax.swing.JTextArea;
import java.util.LinkedList;

/**
 * <code>Hakimi</code> implements the Hakimi-Havel theorem, which determines
 * whether or not a degree sequence is graphical
 *
 * @author Team 1
 * @version 1.0
 */
public class Hakimi {
    
    LinkedList<int[]> oldList;    
    LinkedList<int[]> newList;    

    public Hakimi() {
    }
    public Hakimi(LinkedList<int[]> origList) {
	oldList = origList;
	newList = new LinkedList<int[]>();
    }
    
    public boolean testSequence(String sequence){
        int[] seqArray = convert(sequence);
        return testGraph(seqArray);
    }
    private int[] convert(String s) {
	int[] t = new int[s.length()];
	for(int i = 0; i < s.length(); i++) {
	    t[i] = Integer.parseInt(s.substring(i,i+1));
	}
	return t;
    }
    

    public LinkedList<int[]> getNewList() {
	for(int i = 0; i < oldList.size(); i ++) {
	    
	    // Copy the list
	    int[] tmp = new int[oldList.get(i).length];
	    for (int z = 0; z < oldList.get(i).length; z ++) {
		tmp[z] = oldList.get(i)[z];
	    }
	    
	    if(testGraph(tmp)) {
		newList.add(oldList.get(i));
	    }
	}
	return newList;
    }

    /* This method tests whether or not a degree sequence
     * is graphical, using Hakimi's method. */
    private boolean testGraph(int[] g) {
	int s = g.length;
	boolean end = false;
	boolean correct = false;
	boolean checkC = true;

	for(int i = s-1; i > 0 && !end; i --) {
	    g = reArrange(g,i+1);
           
	    if(g[0] < 0) { // then we know its not correct.
		correct = false;
		end = true;
	    }
	    else { // no -1's spotted yet
                if((i - g[i]) >= 0) {
                    for(int j = i-1; j >= (i - g[i]); j--) {
                        g[j]--;
                    }
                    g = reArrange(g,i); // Ignore the last items
                }
                else {
                    end = true;
                    correct = false;
                }
            }
	    if(end != true) {
                // Tests for correctness
                checkC = true;
                for(int k = 0; k < i; k++) {
                    if(g[k] != 0) {
                        checkC = false;
                    }
                }
                if(checkC) {
                    // We did it!
                    end = true;
                    correct = true;
                }
            }
	}
	return correct;
    }

    /*
     * This method will take an array of any length and
     * re-arrange it in increasing order.
     */
    private int[] reArrange(int[] g, int len) {
	int smallest;
	for(int i = 0; i < len; i++) {
	    // Set the first element as the current smallest value.
	    smallest = i; 
	    for(int j = i+1; j < len; j++) {
		if(g[j] < g[smallest]) {
		    smallest = j;
		}
	    }
	    if(smallest != i) {
		swap(g, i,smallest);
	    }
	}
	return g;
    }
    /*
     * This method swaps two elements in an array
     */
    private int[] swap(int[] g, int i, int j) {
	int temp = g[i];
	g[i] = g[j];
	g[j] = temp;
	return g;
    }
}