import com.sun.j3d.utils.universe.*; 
import com.sun.j3d.utils.geometry.*;
import javax.media.j3d.*;
import javax.vecmath.*;
import com.sun.j3d.utils.behaviors.keyboard.KeyNavigatorBehavior;
import com.sun.j3d.utils.behaviors.keyboard.*;
import java.io.*;
import com.sun.j3d.utils.image.TextureLoader;
import javax.media.j3d.Behavior.*;
import javax.media.j3d.WakeupOnAWTEvent.*;
import java.awt.event.KeyEvent;
import java.awt.*;
import java.util.Enumeration;

public class SimpleKeyBehavior extends Behavior{
	
	private TransformGroup targetTG;
    private Transform3D traslacion = new Transform3D();
    private float posX = 0.0f;
    private float posY = 0.0f;
    private float posZ = 0.0f;
    private WakeupOnAWTEvent trigger = new WakeupOnAWTEvent(KeyEvent.KEY_PRESSED);

    public SimpleKeyBehavior(TransformGroup targetTG) {
    	this.targetTG = targetTG;
    }
    
    public void initialize() {
        this.wakeupOn(trigger);
    }
        
    public void processStimulus(Enumeration criteria) {
        while (criteria.hasMoreElements()) 
        {
            WakeupCriterion wakeup = (WakeupCriterion) criteria.nextElement();
            if(wakeup instanceof WakeupOnAWTEvent)
            {
                AWTEvent[] arr=((WakeupOnAWTEvent)(wakeup)).getAWTEvent();
                KeyEvent ke=(KeyEvent)arr[0];
                Vector3f vector = new Vector3f(posX, posY, posZ);
                switch(ke.getKeyCode())
                {
                    case KeyEvent.VK_D:
                        //debo hacer un get de la posicion del tansform de toy;
                        System.out.println("moviendo");
                        posX += 0.8f;
                        vector.set(posX, posY, posZ);
                        traslacion.set(vector);
                        targetTG.setTransform(traslacion);
                        break;
                        
                    case KeyEvent.VK_W:
                    	System.out.println("moviendo");
                        posZ -= 0.8f;
                        vector.set(posX, posY, posZ);
                        traslacion.set(vector);
                        targetTG.setTransform(traslacion);
                        break;
                        
                    case KeyEvent.VK_A:
                    	System.out.println("moviendo");
                        posX -= 0.8f;
                        vector.set(posX, posY, posZ);
                        traslacion.set(vector);
                        targetTG.setTransform(traslacion);
                        break;
                        
                    case KeyEvent.VK_S:
                    	System.out.println("moviendo");
                        posZ += 0.8f;
                        vector.set(posX, posY, posZ);
                        traslacion.set(vector);
                        targetTG.setTransform(traslacion);
                        break;
                        
                }
            }
        }
        this.wakeupOn(trigger);
	}
}