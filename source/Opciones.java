import java.awt.BorderLayout;
import java.awt.GraphicsConfiguration;
import com.sun.j3d.utils.universe.*; 
import com.sun.j3d.utils.geometry.*;
import javax.media.j3d.*;
import javax.vecmath.*;

import javax.swing.SwingUtilities;
import java.awt.Toolkit;
import java.awt.Image;
import java.awt.Graphics;
import javax.swing.JLayeredPane;
import javax.swing.*;
import javax.swing.JApplet;
import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.WindowConstants;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.*;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle;
import java.awt.EventQueue;

import java.awt.*;
import java.awt.Color;

public class Opciones
{
    public Opciones() 
    {
        initComponents();
    }

    private void initComponents() 
    {
	escogerColor = new SelectorColor();
	menuOpcion = new JFrame("Opciones");
        jLayeredPane1 = new JLayeredPane();
        jButton1 = new JButton();
        jButton2 = new JButton();
        jButton3 = new JButton();
        jButton4 = new JButton();
        jLabel1 = new JLabel();
	color = new Color(102, 102, 102);
	nombrePinito = "pinito";
	tiempo = 2;
        menuOpcion.setTitle("Opciones");

        menuOpcion.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
		color = escogerColor.getColor();
		System.out.println(color.toString());
		if(nombrePinito == null) {nombrePinito = "Pinito";}
		System.out.println(nombrePinito);
		System.out.println(tiempo);
		setVisible(false);
            }
        });

        jLayeredPane1.setAlignmentX(0.0F);
        jLayeredPane1.setAlignmentY(0.0F);

        jButton1.setIcon(new ImageIcon("../images/imageButonColor.gif"));
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setBounds(160, 60, 160, 33);
		jButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jLayeredPane1.add(jButton1, JLayeredPane.DEFAULT_LAYER);

        jButton2.setIcon(new ImageIcon("../images/bottonNombre.gif"));
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.setBounds(160, 100, 170, 33);
        jButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jLayeredPane1.add(jButton2, JLayeredPane.DEFAULT_LAYER);
		
        jButton3.setIcon(new ImageIcon("../images/tiempoBoton.gif"));
        jButton3.setAlignmentY(0.0F);
        jButton3.setBorderPainted(false);
        jButton3.setContentAreaFilled(false);
        jButton3.setBounds(190, 140, 100, 33);
        jButton3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jLayeredPane1.add(jButton3, JLayeredPane.DEFAULT_LAYER);

        jButton4.setIcon(new ImageIcon("../images/creditosBoton.gif"));
        jButton4.setAlignmentY(0.0F);
        jButton4.setBorderPainted(false);
        jButton4.setContentAreaFilled(false);
        jButton4.setBounds(190, 180, 100, 33);
        jLayeredPane1.add(jButton4, JLayeredPane.DEFAULT_LAYER);

        jLabel1.setIcon(new ImageIcon("../images/imageMenuOpciones.gif"));
        jLabel1.setAlignmentY(0.0F);
        jLabel1.setBounds(0, 0, 346, 300);
        jLayeredPane1.add(jLabel1, JLayeredPane.DEFAULT_LAYER);

        GroupLayout layout = new GroupLayout(menuOpcion.getContentPane());
        menuOpcion.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1, GroupLayout.PREFERRED_SIZE, 346, GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1, GroupLayout.PREFERRED_SIZE, 297, GroupLayout.PREFERRED_SIZE)
        );

        menuOpcion.pack();
    }

    private void jButton1ActionPerformed(ActionEvent evt) 
    {
		escogerColor.setVisible(true);
    }
    
    private void jButton2ActionPerformed(ActionEvent evt) 
    {
		nombrePinito = JOptionPane.showInputDialog(menuOpcion, "Ingresa un nombre para Pinito", "Pinito", JOptionPane.DEFAULT_OPTION );
    }
    
    private void jButton3ActionPerformed(ActionEvent evt) 
    {
		try 
		{
			tiempo = Integer.parseInt(JOptionPane.showInputDialog(menuOpcion, "Ingresa el tiempo que durara la partida",
			"Tiempo", JOptionPane.DEFAULT_OPTION ));
		}
		catch(Exception ex)
		{
			tiempo = 2;
		}
    }

    public void setVisible(boolean visible)
    {
		menuOpcion.setVisible(visible);
    }
    
    public String obtenerNombre()
    {
    	return nombrePinito;
    }
    
    public Color obtenerColor()
    {
    	return color;		
    }
    
    public int obtenerTiempo()
    {
    	return tiempo;		
    }
    
	private int tiempo;
	private String nombrePinito;
	private Color color;
	private SelectorColor escogerColor;
	private JFrame menuOpcion;
	private JButton jButton1;
	private JButton jButton2;
	private JButton jButton3;
	private JButton jButton4;
	private JLabel jLabel1;
	private JLayeredPane jLayeredPane1;
}
