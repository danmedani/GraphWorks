/**
 * <code>InfoDisplayPanel</code> extends JPanel and takes in a message in the 
 * form of a String array to be displayed within a larger JFrame.
 *
 * @author Team 1
 * @version 1.0
 */

import java.awt.*;
import javax.swing.*;
import org.netbeans.lib.awtextra.*;

public class InfoDisplayPanel extends JPanel {
 
    protected String[] msg;
    
    /**
     * Creates a new <code>InfoDisplayPanel</code> instance.
     *
     * @param e a 2-d array of edges
     */
    public InfoDisplayPanel(String[] m){
	msg = m;
    }
        
    /**
     * <code>paintComponent</code> outputs the given message to the panel.
     *
     * @param g a <code>Graphics</code> value
     */
    public void paintComponent(Graphics g) {
	
	super.paintComponent(g);
		
	g.setFont(new Font(g.getFont().getName(), Font.BOLD, 12));
	g.drawString("Explanation", 90, 25);
	g.setFont(new Font(g.getFont().getName(), Font.PLAIN, 12));
	int yC = 45;
	for (int i = 0; i < msg.length; i++) {
	g.drawString(msg[i], 0, yC);
	yC += 15;
	}
    }
    
    /**
     * <code>setMessage</code> sets the message to be displayed by the panel.
     *
     * @param s the new message
     */
    public void setMessage(String[] s){
	msg = s;
	repaint();
    }
}