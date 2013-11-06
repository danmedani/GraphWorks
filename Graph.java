import java.util.*;



/**
 * Graph is an interface defining the necessary methods to interact with a
 * class that holds a data structure representing a graph.
 *
 * @version 1.0
 *
 * @author Team 1
 *
 */
interface Graph {
    
    /**
     * <code>addEdge</code> adds an edge between vertices v1 and v2.
     *
     * @param v1 first vertex on the new edge
     * @param v2 second vertex on the new edge
     */
    public void addEdge(int v1, int v2);
    
    /**
     * <code>removeEdge</code> removes the edge between vertices v1 and v2.
     *
     * @param v1 first vertex on the edge to be deleted
     * @param v2 second vertex on the edge to be deleted
     */
    public void removeEdge(int v1, int v2);
    
    /**
     * <code>displayGraph</code> draws a graphical representation of the graph
     * and prints the graph's edge set.
     *
     */
    public void displayGraph();

    /**
     * <code>firstVertex</code> returns the graph's first vertex.
     *
     * @return the first vertex
     */
    public int firstVertex();

    /**
     * <code>nextVertex</code> returns the vertex after <code>vt</code>
     *
     * @param vt current vertex
     * @return the next vertex
     */
    public int nextVertex(int vt);

    /**
     * <code>firstEdge</code> returns the first edge attatched to 
     * <code>vt</code>.
     *
     * @return the first edge of <code>vt</code>
     */
    public int firstEdge(int vt);

    /**
     * <code>nextEdge</code> returns the next edge attached to <code>vt</code>
     * after <code>edge</code>.
     *
     * @param vt a vertex
     * @param edge an edge attached to <code>vt</code>
     * @return the next edge
     */
    public int nextEdge(int vt, int edge);

    /**
     * <code>isAdj</code> returns true if <code>v1</code> is adjacent to 
     * <code>v2</code>, or false otherwise.
     *
     * @param v1 first vertex
     * @param v2 second vertex
     * @return whether <code>v1</code> and <code>v2</code> are adjacent
     */
    public boolean isAdj(int v1, int v2);

    /**
     * <code>getNumVert</code> returns the number of vertices in this graph
     *
     * @return the number of vertices
     */
    public int getNumVert();

    /**
     * <code>getDegree</code> returns the degree of vertex <code>vt</code>.
     *
     * @param vt a vertex
     * @return the degree of <code>vt</code>
     */
    public int getDegree(int vt);

    /**
     * <code>isIncident</code> returns true if <code>edge</code> is incident
     * on <code>vertex</code>, and false otherwise.
     *
     * @return whether <code>edge</code> is incident on <code>vt</code>
     */
    public boolean isIncident(int edge, int vertex);

    public LinkedList<Edge> getEdges(int vt);
}