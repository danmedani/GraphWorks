/**
 * <code>KruskalEdge</code> represents a weighted edge. It is used in the MCST
 * classes.
 *
 * @author 
 * @version 1.0
 */
public class KruskalEdge {
    
    private int vert1;   //Vertex 1
    private int vert2;   //Vertex 2
    private int weight;  //Edge weight
    private int pass;    //Which pass of the algorithm this edge was found on
    
    /**
     * Creates a new <code>KruskalEdge</code> instance.
     *
     * @param v1 vertex1
     * @param v2 vertex2
     * @param w weight
     */
    public KruskalEdge(int v1, int v2, int w){
	
	vert1 = v1;
	vert2 = v2;
	weight = w;
	pass = -1;
    }
    
    /**
     * Creates a new <code>KruskalEdge</code> instance.
     *
     * @param v1 vertex1
     * @param v2 vertex2
     * @param w weight
     * @param p pass
     */
    public KruskalEdge(int v1, int v2, int w, int p){
	
	vert1 = v1;
	vert2 = v2;
	weight = w;
	pass = p;
    }

    public int getWeight() {
	
	return weight;
    }
    
    public int getVert1(){
	
	return vert1;
    }
    
    public int getVert2(){
	
	return vert2;
    }
    
    public int getPass(){
	
	return pass;
    }
    
    public int compareTo(KruskalEdge e2){
	if ((vert1==e2.getVert1() && vert2==e2.getVert2()) ||
	    (vert1==e2.getVert2() && vert2==e2.getVert1())){
	    return 0;
	} else {
	    return 1;
	}
    }
    
    public String toString() {
	
	return "("+vert1+","+vert2+"):"+weight;
    }
    
    public static void main(String[] args){
	
	KruskalEdge e1 = new KruskalEdge(1,2,1);
	KruskalEdge e2 = new KruskalEdge(2,1,1);
	KruskalEdge e3 = new KruskalEdge(3,4,1);
	KruskalEdge e4 = new KruskalEdge(1,2,1);
	
    }
}