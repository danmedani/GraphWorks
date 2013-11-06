import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.Math.*;
import java.io.*;


/**
 * <code>SmallGraphDisplayPanel</code> displays a after performing
 *  a given search 
 * on it.
 *
 * @author 
 * @version 1.0
 */
public class SmallGraphDisplayPanel extends GraphDisplayPanel  {

    private int[] hararyNum;


    /**
     * Creates a new <code>SmallGraphDisplayPanel</code> instance.
     *
     * @param e 2-d edge array with graph data.
     * @param t 2-d edge array of tree edges.
     */
    public SmallGraphDisplayPanel(int[][] e, int[] hNum){
	super(e,false);
	hararyNum = hNum;
    } 
    
  
    /**
     * <code>paintComponent</code> draws the graph in the panel.
     *
     * @param g a <code>Graphics</code> value
     */
    public void paintComponent(Graphics g) {
	g.setFont(new Font(g.getFont().getName(), Font.BOLD, 12));
	g.drawString("Catalogue Information", 38,13);

	g.drawString("P", 10, 40);
	g.setFont(new Font(g.getFont().getName(), Font.PLAIN, 12));
	g.drawString("(Vertex Count): " + hararyNum[0],25,40);
	g.setFont(new Font(g.getFont().getName(), Font.BOLD, 12));
	g.drawString("Q", 10, 60);
	g.setFont(new Font(g.getFont().getName(), Font.PLAIN, 12));
	g.drawString("(Edge Count): " + hararyNum[1], 25, 60);
	g.setFont(new Font(g.getFont().getName(), Font.BOLD, 12));
	g.drawString("Harary #",10, 80);
	g.setFont(new Font(g.getFont().getName(), Font.PLAIN, 12));
	g.drawString(": " + hararyNum[2],65,80);

    }
}