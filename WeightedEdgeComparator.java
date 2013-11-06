/**
 * Is used to compare two KruskalEdge objects.
 *
 * @author Team 1
 * @version 1.0
 */

import java.util.Comparator;

public class WeightedEdgeComparator implements Comparator {
 
    
    /**
     * Creates a new <code>WeightedEdgeComparator</code> instance.
     *
     */
    public WeightedEdgeComparator() {
    }
    
    /**
     * <code>compare</code> compares one Kruskal edge to another by weight.
     *
     * @param e1 the first edge
     * @param e2 the second edge
     * @return positive value if e1.weight > e2.weight, negative if
     * e2.weight > e1.weight, 0 if weights are equal
     */
    public int compare(Object e1, Object e2){
	
	return ((KruskalEdge)e1).getWeight() - ((KruskalEdge)e2).getWeight();
    }
}