import javax.swing.*;
import org.netbeans.lib.awtextra.*;

public class MCSTHelp extends JFrame {

    /** Creates new form MCSTHelp */
    public MCSTHelp() {
        initComponents();
    }

    private void initComponents() {

        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        jLabel3 = new JLabel();
        jLabel4 = new JLabel();
        jLabel5 = new JLabel();
        jLabel6 = new JLabel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Lucida Sans", 1, 30)); 
        jLabel1.setText("MCST");
        getContentPane().add(jLabel1,
			     new AbsoluteConstraints(250, 20, 96, 44));

        jLabel2.setText("A minimum cost spanning tree consists of a"+
			" set of vertices and edges such that for some");
        getContentPane().add(jLabel2, 
			     new AbsoluteConstraints(80, 80, 430, -1));

        jLabel3.setText("weighted graph G, every vertex in G is contained"+
			" in the minimum cost spanning tree and");
        getContentPane().add(jLabel3,
			     new AbsoluteConstraints(80, 100, 430, -1));

        jLabel4.setText("there are |V|-1 edges, such that the sum of the"+
			" weights of the edges is as low as possible.");
        getContentPane().add(jLabel4,
			     new AbsoluteConstraints(80, 120, -1, -1));

        jLabel5.setText("MCST uses three different algorithms, Prim's,"+
			" Kruskal's, and Boruvka's, to find and ");
        getContentPane().add(jLabel5, 
			     new AbsoluteConstraints(80, 180, 410, -1));

        jLabel6.setText("display a minimum cost spanning tree of the graph"+
			" entered by the user.");
        getContentPane().add(jLabel6, 
			     new AbsoluteConstraints(80, 200, 390, -1));

        pack();
    }

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MCSTHelp().setVisible(true);
            }
        });
    }

    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel6;

}
