import java.awt.BorderLayout;
import java.awt.GraphicsConfiguration;
import com.sun.j3d.utils.universe.*; 
import com.sun.j3d.utils.geometry.*;
import javax.media.j3d.*;
import javax.vecmath.*;
import javax.swing.SwingUtilities;
import javax.swing.*;
import java.awt.event.*;
import java.awt.EventQueue;
import java.awt.*;
import java.net.URL;

public class Pinito3D extends JFrame 
{
    public Pinito3D() 
    {
        initComponents();
    }

    private void initComponents() 
    {
    	jFileChooser1 = new JFileChooser();
        jLayeredPane1 = new JLayeredPane();
        jButton1 = new JButton();
        jButton2 = new JButton();
        jButton3 = new JButton();
        jButton4 = new JButton();
        jButton5 = new JButton();
        jLabel1 = new JLabel();
        sonidoJuego = new Sound("../sounds/nuevo.wav");
	sonidoMenu = new Sound("../sounds/soundMenu.wav");
	subMenuOpcion = new Opciones();
      	splash = new Splash();
	ranking = new Ranking();

	screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Pinito3D");
        setMinimumSize(new Dimension(600, 344));
        setName("Pinito3D");
        setResizable(false);
        setUndecorated(true);
        setLocation( screenSize.width/2 - 300, screenSize.height/2 - 172);

        jLayeredPane1.setAlignmentX(0.0F);
        jLayeredPane1.setAlignmentY(0.0F);
        jLayeredPane1.setPreferredSize(new Dimension(600, 344));

        jButton1.setIcon(new ImageIcon("../images/boton1.gif"));
        jButton1.setAlignmentY(0.0F);
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jButton1.setBounds(433, 20, 130, 29);
        jLayeredPane1.add(jButton1, JLayeredPane.DEFAULT_LAYER);

        jButton2.setIcon(new ImageIcon("../images/boton2.gif"));
        jButton2.setAlignmentY(0.0F);
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jButton2.setBounds(440, 60, 120, 29);
        jLayeredPane1.add(jButton2, JLayeredPane.DEFAULT_LAYER);

        jButton3.setIcon(new ImageIcon("../images/boton3.gif"));
        jButton3.setBorderPainted(false);
        jButton3.setContentAreaFilled(false);
        jButton3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jButton3.setBounds(440, 100, 120, 20);
        jLayeredPane1.add(jButton3, JLayeredPane.DEFAULT_LAYER);

        jButton4.setIcon(new ImageIcon("../images/boton4.gif"));
        jButton4.setAlignmentY(0.0F);
        jButton4.setBorderPainted(false);
        jButton4.setContentAreaFilled(false);
        jButton4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jButton4.setBounds(440, 130, 120, 30);
        jLayeredPane1.add(jButton4, JLayeredPane.DEFAULT_LAYER);

        jButton5.setIcon(new ImageIcon("../images/bottonSalir.gif"));
        jButton5.setAlignmentY(0.0F);
        jButton5.setBorderPainted(false);
        jButton5.setContentAreaFilled(false);
        jButton5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jButton5.setBounds(470, 167, 60, 33);
        jLayeredPane1.add(jButton5, JLayeredPane.DEFAULT_LAYER);

        jLabel1.setIcon(new ImageIcon("../images/menu21.jpg"));
        jLabel1.setAlignmentY(0.0F);
        jLabel1.setBounds(0, 0, 600, 344);
        jLayeredPane1.add(jLabel1, JLayeredPane.DEFAULT_LAYER);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        );

        addWindowListener(new WindowAdapter(){
	          public void windowIconified(WindowEvent e){
			sonidoMenu.task("stop");
	          }

	          public void windowActivated(WindowEvent e){
			if(sonidoMenu!= null){ sonidoMenu.task("play");}
			else{ System.out.println("no se pudo cargar el sonido");}
	          }
	        }
        );

        pack();
    }

    private void jButton1ActionPerformed(ActionEvent evt) 
    {
    	setState(ICONIFIED);
	sonidoMenu.task("stop");
	color = subMenuOpcion.obtenerColor();
	nombrePinito = subMenuOpcion.obtenerNombre();
	tiempo = subMenuOpcion.obtenerTiempo();
	game = new ScreenGame(sonidoJuego, color, nombrePinito, tiempo);
	game.setVisible(true);
    }

    private void jButton2ActionPerformed(ActionEvent evt) 
    {
        ranking.consultarRanking();
	ranking.setVisible(true);
    }

    private void jButton3ActionPerformed(ActionEvent evt) 
    {
		int retVal = jFileChooser1.showOpenDialog(this);
        if (retVal == jFileChooser1.APPROVE_OPTION)
        	{
            	map = jFileChooser1.getSelectedFile().getAbsolutePath();
            }
            
            map = jFileChooser1.getSelectedFile().getAbsolutePath();
            try
            {
            	URL url = jFileChooser1.getSelectedFile().toURL();
            	String file = jFileChooser1.getSelectedFile().toString();
            }
            catch (Exception ioe)
			{
				jLabel1.setText("error" + ioe.getMessage());
			}
    }

    private void jButton4ActionPerformed(ActionEvent evt) 
    {
        subMenuOpcion.setVisible(true);
    }

    private void jButton5ActionPerformed(ActionEvent evt) 
    {
        System.exit(0);
    }

    public static void main(String args[]) 
    {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Pinito3D().setVisible(true);
            }
        });
    }
    
	private JFileChooser jFileChooser1;
	private int tiempo;
	private Ranking ranking;
	private String map;
	private String nombrePinito;
	private Color color;
	private JButton jButton1;
	private JButton jButton2;
	private JButton jButton3;
	private JButton jButton4;
	private JButton jButton5;
	private JLabel jLabel1;
	private JLayeredPane jLayeredPane1;
	private ScreenGame game;
	private Splash splash;
	private Sound sonidoJuego;
	private Sound sonidoMenu;
	private Dimension screenSize;
	private JFrame gameFrame;
	private Thread hilo;
	private Opciones subMenuOpcion;
}
