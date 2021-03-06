/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.*;
import javax.swing.*;
/*
 * GraphHelper.java
 *
 * Created on Apr 13, 2010, 5:01:44 PM
 */

/**
 *
 * @author Big Tim
 */
public class GraphHelper extends javax.swing.JFrame {

    /** Creates new form GraphHelper */
    public GraphHelper() {
        initComponents1();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
    * @param args the command line arguments
    */
     private void initComponents1() {
    jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jLabel1 = new javax.swing.JLabel();
	jLabel2 = new javax.swing.JLabel();
	jLabel3 = new javax.swing.JLabel();
	jLabel4 = new javax.swing.JLabel();
	jLabel5 = new javax.swing.JLabel();
	jLabel6 = new javax.swing.JLabel();
	jLabel7 = new javax.swing.JLabel();
	jLabel8 = new javax.swing.JLabel();
	jLabel9 = new javax.swing.JLabel();
	jLabel10 = new javax.swing.JLabel();
	jLabel11 = new javax.swing.JLabel();
	jLabel12 = new javax.swing.JLabel();
	jLabel13 = new javax.swing.JLabel();
	jLabel14 = new javax.swing.JLabel();
	jLabel15 = new javax.swing.JLabel();
	jLabel16 = new javax.swing.JLabel();
	jLabel17 = new javax.swing.JLabel();
	jLabel18 = new javax.swing.JLabel();
	jLabel19 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setFocusableWindowState(false);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setText("Back");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(515, 416, 76, -1));


        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24));
        jLabel1.setText("Graph");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 19, -1, 36));

	jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12));
        jLabel2.setText("A graph G = (V,E) consists of a finite nonempty set V of elements, called");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 65, -1, 36));

	jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12));
        jLabel3.setText("the vertices of the graph and a set E of unorderd pairs of vertices called the");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 80, -1, 36));

	jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12));
        jLabel4.setText("edges of the graph.  The elements of an edge are called its ends.  An edge with");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 95, -1, 36));

	jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12));
        jLabel5.setText("end V is said to be incident to the vertex v.  Two edges with a common end are");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 110, -1, 36));

	jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12));
	jLabel6.setText("said to be adjacent.  The number of edges incident to a vertex is the degree of");
	getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 125, -1, 36));

	jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12));
        jLabel7.setText("the vertex.  A vertex is odd or even depending on whether its degree is odd or");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 140, -1, 36));

	jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12));
        jLabel8.setText("even.  A vertex of degree one is said to be an isolated vertex.");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 155, -1, 36));

	jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12));
        jLabel9.setText("A subgraph H(V1,E1) of graph G(V,E) is a graph for which V1 is");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 185, -1, 36));

	jLabel10.setFont(new java.awt.Font("Tahoma", 0, 12));
	jLabel10.setText("a subset of V and the edges E1 have both their ends in V1. a subgraph is");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 200, -1, 36));

	jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12));
	jLabel11.setText("called spanning when V1 = V.  An induced subgraph H of G is a subgraph");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 215, -1, 36));

	jLabel12.setFont(new java.awt.Font("Tahoma", 0, 12));
	jLabel12.setText("for which the edges of H are all the edges of G with both ends in V1.");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 230, -1, 36));

	jLabel13.setFont(new java.awt.Font("Tahoma", 0, 12));
	jLabel13.setText("A walk in a graph H=(V,E) is a sequence of vertices and edges beginning");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 260, -1, 36));

	jLabel14.setFont(new java.awt.Font("Tahoma", 0, 12));
	jLabel14.setText("and ending with a vertex and having alternating entries an edge of G. No edge");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 275, -1, 36));

	jLabel15.setFont(new java.awt.Font("Tahoma", 0, 12));
	jLabel15.setText("of G occurs more than once in a walk. If all the edges of a walk are distinct, the");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 290, -1, 36));

	jLabel16.setFont(new java.awt.Font("Tahoma", 0, 12));
	jLabel16.setText("walk is called a path. A circuit is a walk for which the begining and ending");
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 305, -1, 36));

	jLabel17.setFont(new java.awt.Font("Tahoma", 0, 12));
	jLabel17.setText("vertices are the same. A cycle is a path for which the beggining and ending");
        getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 320, -1, 36));

	jLabel18.setFont(new java.awt.Font("Tahoma", 0, 12));
	jLabel18.setText("vertices are the same. The length of a walk or path is the number of edges");
        getContentPane().add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 335, -1, 36));

	jLabel19.setFont(new java.awt.Font("Tahoma", 0, 12));
	jLabel19.setText("it contains.");
        getContentPane().add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 350, -1, 36));

        ImageIcon icon = new ImageIcon("graphhelp.jpg");
        JLabel label = new JLabel();
        label.setIcon(icon);
        getContentPane().add(label,new org.netbeans.lib.awtextra.AbsoluteConstraints(535, 85, -1, 300));

        //setVisible(true);
	pack();
}


      private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {
	
    }
      
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GraphHelper().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
 private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JScrollPane jScrollPane1;
}
