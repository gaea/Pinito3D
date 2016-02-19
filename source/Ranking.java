import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.*;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.*;
import java.awt.BorderLayout;
import java.awt.GraphicsConfiguration;
import com.sun.j3d.utils.universe.*; 
import com.sun.j3d.utils.geometry.*;
import javax.media.j3d.*;
import javax.vecmath.*;
import javax.swing.SwingUtilities;
import javax.swing.*;
import java.net.*;
import java.io.*;

public class Ranking extends JFrame {

    public Ranking() 
    {
        initComponents();
    }

    private void initComponents() {
        jLayeredPane1 = new JLayeredPane();
        jScrollPane1 = new JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setBackground(new Color(255, 255, 255));
	//setUndecorated(true);
        jScrollPane1.setBackground(new java.awt.Color(255, 255, 0));
        jScrollPane1.setAutoscrolls(true);
        jTable1.setModel(new DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Título 1", "Título 2", "Título 3", "Título 4"
            }
        ));
        jTable1.setOpaque(false);
        jScrollPane1.setViewportView(jTable1);

        jScrollPane1.setBounds(10, 70, 480, 460);
        jLayeredPane1.add(jScrollPane1, JLayeredPane.DEFAULT_LAYER);

        jLabel1.setBackground(new Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel1.setIcon(new ImageIcon("../images/ranking.gif"));
        jLabel1.setBounds(140, 20, 200, 34);
        jLayeredPane1.add(jLabel1, JLayeredPane.DEFAULT_LAYER);

        jLabel2.setBackground(new Color(191, 217, 255));
        jLabel2.setAlignmentY(0.0F);
        jLabel2.setOpaque(true);
        jLabel2.setBounds(0, 0, 500, 550);
        jLayeredPane1.add(jLabel2, JLayeredPane.DEFAULT_LAYER);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1, GroupLayout.DEFAULT_SIZE, 502, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1, GroupLayout.DEFAULT_SIZE, 546, Short.MAX_VALUE)
        );
        pack();
    }

    public void consultarRanking()
    {
        try 
        {
            cliente = new Socket("localhost", 1234 );

            salida = new ObjectOutputStream( cliente.getOutputStream() );
            salida.flush();

            entrada = new ObjectInputStream( cliente.getInputStream() );

            salida.writeObject("select * from pinito3d");
            System.out.println("mande la consulta");
            salida.flush();

            jTable1.setModel((DefaultTableModel)entrada.readObject());
            System.out.println("recibi la consulta");
        }

        catch ( Exception ex ) { ex.printStackTrace(); }

        finally 
        {
            try
            {
                salida.close();
                entrada.close();
                cliente.close();
            }
            catch (Exception ex) { ex.printStackTrace(); }
        }
    }

    private static ObjectOutputStream salida;
    private ObjectInputStream entrada;
    private Socket cliente;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLayeredPane jLayeredPane1;
    private JScrollPane jScrollPane1;
    private JTable jTable1;
}
