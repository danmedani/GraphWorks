import javax.swing.*;
import org.netbeans.lib.awtextra.*;

public class DegreeHelp extends JFrame {

    /** Creates new form Help */
    public DegreeHelp() {
        initComponents();
    }

    private void initComponents() {

	// Initialize
        backButton = new JButton();
        jLabel1 = new JLabel();
	jLabel2 = new JLabel();
	jLabel3 = new JLabel();
	jLabel4 = new JLabel();
	jLabel5 = new JLabel();
	jLabel6 = new JLabel();
	jLabel7 = new JLabel();
	jLabel8 = new JLabel();
	jLabel9 = new JLabel();
	jLabel10 = new JLabel();
	jLabel11 = new JLabel();
	jLabel12 = new JLabel();
	jLabel13 = new JLabel();
	jLabel14 = new JLabel();
	jLabel15 = new JLabel();

	// Windowing
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new AbsoluteLayout());

	
	// Add components
        backButton.setText("Back");
        backButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backButtonMouseClicked(evt);
            }
        });
        getContentPane().add(backButton,
			     new AbsoluteConstraints(520, 420, 70, -1));


	// Add labels
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); 
        jLabel1.setText("Degree Sequences"); 
        getContentPane().add(jLabel1,
			     new AbsoluteConstraints(185, 10, -1, -1));

	jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); 
        jLabel2.setText("<html>For a positive integer <b>n</b>,"+
			" a degree sequence of length <b>n</b> "+
			"is a set of integers"); 
        getContentPane().add(jLabel2, 
			     new AbsoluteConstraints(50, 60, -1, 36));


	jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); 
        jLabel4.setText("<html><b>d(1), d(2), ..., d(n)</b></html>");
	getContentPane().add(jLabel4,
			     new AbsoluteConstraints(240, 85, -1, 36));

	jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); 
        jLabel5.setText("such that");
        getContentPane().add(jLabel5, 
			     new AbsoluteConstraints(50, 110, -1, 36));

	jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); 
	jLabel6.setText("<html><b>0 &lt= d(i) &lt n  where 1 &lt= i "+
			"&lt= n</b></html>");
	getContentPane().add(jLabel6,
			     new AbsoluteConstraints(195, 135, -1, 36));

	jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); 
        jLabel7.setText("If a degree sequence corresponds to the degrees"+
			" of the vertices of a graph,");
        getContentPane().add(jLabel7, 
			     new AbsoluteConstraints(50, 180, -1, 36));

	jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); 
        jLabel8.setText("the sequence is called graphical. Basic questions"+
			" about degree sequences");
        getContentPane().add(jLabel8,
			     new AbsoluteConstraints(50, 200, -1, 36));


	jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); 
        jLabel14.setText("involve how one might generate all of them and "+
			 "how one can decide which");
	getContentPane().add(jLabel14, 
			     new AbsoluteConstraints(50, 220, -1, 36));

	jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); 
        jLabel3.setText("are graphical.");
        getContentPane().add(jLabel3,
			     new AbsoluteConstraints(50, 240, -1, 36));

	jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); 
        jLabel9.setText("<html>The <b>Degree Sequences</b> topic, "+
			"which can be accessed through the main menu,");  
        getContentPane().add(jLabel9, 
			     new AbsoluteConstraints(50, 275, -1, 36));

	jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); 
	jLabel10.setText("generates all degree sequences and all graphical"+
			 " degree sequences for small");
        getContentPane().add(jLabel10,
			     new AbsoluteConstraints(50, 295, -1, 36));

	jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); 
	jLabel11.setText("integers. The classical Hakimi-Havel" +
			 " Theorem that gives necessary");
        getContentPane().add(jLabel11, 
			     new AbsoluteConstraints(50, 315, -1, 36));
	
	jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); 
	jLabel12.setText("and sufficent conditions for a degree sequence"+
			 " to be graphical is also"); 
        getContentPane().add(jLabel12, 
			     new AbsoluteConstraints(50, 335, -1, 36));

	
	jLabel15.setFont(new java.awt.Font("Tahoma", 0, 14)); 
	jLabel15.setText("implemented."); 
        getContentPane().add(jLabel15, 
			     new AbsoluteConstraints(50, 355, -1, 36));






        pack();
   }

    private void backButtonMouseClicked(java.awt.event.MouseEvent evt) {
	MainMenu mm = new MainMenu();
	mm.setLocationRelativeTo( this );
	mm.setVisible(true);	
	this.setVisible(false);
	this.dispose();
    }
	

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DegreeHelp().setVisible(true);
            }
        });
    }

    private JButton backButton;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JLabel jLabel7;
    private JLabel jLabel8;
    private JLabel jLabel9;
    private JLabel jLabel10;
    private JLabel jLabel11;
    private JLabel jLabel12;
    private JLabel jLabel13; 
    private JLabel jLabel14;  
    private JLabel jLabel15;  
}
