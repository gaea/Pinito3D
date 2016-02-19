import com.sun.j3d.utils.applet.MainFrame;
import java.awt.BorderLayout;
import java.awt.GraphicsConfiguration;
import com.sun.j3d.utils.universe.*; 
import com.sun.j3d.utils.geometry.*;
import javax.media.j3d.*;
import javax.vecmath.*;
import javax.swing.*;
import java.awt.Toolkit;
import java.awt.*;
import java.awt.Dimension;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.awt.event.KeyEvent.*;

public class ScreenGame extends JFrame
{
	public ScreenGame(Sound sonidoJuego, Color colorPinito, String nomPinito, int tiempo)
	{
		color = colorPinito;
		music = sonidoJuego;
		//screenGame.setUndecorated(true);
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		//setSize( (int)screenSize.getWidth(), (int)screenSize.getHeight());
        	//setLocation(0,0);
		setSize( 1000, 600);
		initComponents(nomPinito, tiempo);
	}

	public void initComponents( String nomPinito, int tiempo)
	{
		game = new JApplet();
		game.setLayout(new BorderLayout());
		GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
		Canvas3D canvas3D = new Canvas3D(config);
		//canvas3D.setFocusable(true);
		//canvas3D.requestFocus();
		canvas3D.setStereoEnable(false);
		SimpleUniverse simpleU = new SimpleUniverse(canvas3D);
		simpleU.getViewingPlatform().setNominalViewingTransform();
		
		View view = simpleU.getViewer().getView();
		view.setTransparencySortingPolicy(View.TRANSPARENCY_SORT_GEOMETRY);
		
		scene = new Scene(simpleU, color, nomPinito, tiempo);
		simpleU.addBranchGraph(scene);
		game.add("Center", canvas3D);
		
		add(game);
		pack();
			
		if(music!= null){ music.task("play");}
		else{ System.out.println("no se pudo cargar el sonido");}
			
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				music.task("stop");
			}
		});
    }
	
	private Color color;
	private JApplet game;
	private Sound music;
	private ObjectOutputStream salida;
	private ObjectInputStream entrada;
	private ServerSocket servidor;
	private Socket conexion;
	private Scene scene;
	private Dimension screenSize;
}