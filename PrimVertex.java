public class PrimVertex {
    
    private boolean in;
    private int distance;
    private int vNum;

    public PrimVertex(int vNum, int d, boolean b) {
	this.vNum = vNum;
	distance = d;
	in = b;
    }
    public int getVNum() {
	return vNum;
    }
    public int getDistance() {
	return distance;
    }
    public boolean inGraph() {
	return in;
    }
    public void setDistance(int i) {
	distance = i;
    }
    public void setIn(boolean b) {
	in = b;
    }
    
    public String toString() {
	return "Vertex: " + vNum + "   Weight: " + distance +
	    "  InGraph: " + in;
    }

}