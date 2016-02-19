
import java.awt.*;
import java.awt.event.*;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Splash {

	 void renderSplashFrame(Graphics2D g, int frame) 
	 {
		final String[] comps = {"datos", "interfaz", "aplicacion"};
		g.setComposite(AlphaComposite.Clear);
		g.fillRect(400, 60,280,40);
		g.setPaintMode();
		g.setColor(Color.BLACK);
		g.drawString("Cargando "+comps[(frame/5)%3]+"...", 400, 100);
		g.setColor(Color.ORANGE);
		g.fillRect(400, 105,(frame*10)%280, 10);
	 }

	 public Splash() 
	 {
		 final SplashScreen splash = SplashScreen.getSplashScreen(); 
		 if (splash == null) 
		 {
			 JOptionPane.showMessageDialog(null,"SplashScreen.getSplashScreen() returned null");
			 return;
		 }
		 Graphics2D g = splash.createGraphics();
		 if (g == null) 
		 {
			 JOptionPane.showMessageDialog(null, "g is null"); 
			 return; 
		 }

		 for(int i=0; i<14; i++) 
		 { 
			 renderSplashFrame(g, i);
			 splash.update();
			 try {
				 Thread.sleep(500); //tiempo de espera entre cada "carga" en micro seg
			 } catch(InterruptedException e) { } 
		}
		splash.close();
	} 
}
