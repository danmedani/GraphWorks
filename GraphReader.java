/**
 * GraphReader is an abstract class that specifies the methods provided by
 * its subclasses to read an input file and return a data structure 
 * representing the graph in that input file.
 *
 * @version 1.0
 *
 * @author Team 1
 *
 */

import java.util.*;
import java.io.*;

public abstract class GraphReader {
    
    protected String inputString;  //String containing graph data    
    public abstract int[][] getEdgeArray() throws InvalidInputException;
    
   
}