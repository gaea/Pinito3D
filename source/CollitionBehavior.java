import com.sun.j3d.utils.geometry.*;
import javax.media.j3d.*;
import javax.vecmath.*;
import com.sun.j3d.utils.behaviors.keyboard.KeyNavigatorBehavior;
import com.sun.j3d.utils.behaviors.keyboard.*;
import java.io.*;
import com.sun.j3d.utils.image.TextureLoader;
import javax.media.j3d.Behavior.*;
import javax.media.j3d.WakeupOnAWTEvent;
import java.awt.event.KeyEvent;
import java.awt.*;
import java.util.Enumeration;
import javax.media.j3d.BoundingBox;

public class CollitionBehavior extends Behavior 
{
	private Sound sound;
	private Shape3D shape;
	private WakeupCriterion[] theCriteria;
	private WakeupOr oredCriteria;
	private TransformGroup vpTrans;
	private Scene scene;
	private int random;
	
	public CollitionBehavior(Shape3D objShape, TransformGroup objTrans,Bounds bounds, Sound objSound, Scene objScene) 
	{
		scene = objScene;
		sound = objSound;
		vpTrans = objTrans;
		shape = objShape;
		setSchedulingBounds(bounds);
	}

	public void initialize() 
	{
		theCriteria = new WakeupCriterion[3];
		WakeupOnCollisionEntry startsCollision = new WakeupOnCollisionEntry( shape);
		WakeupOnCollisionExit endsCollision = new WakeupOnCollisionExit( shape);
		WakeupOnCollisionMovement moveCollision = new WakeupOnCollisionMovement( shape);
		
		theCriteria[0] = startsCollision;
		theCriteria[1] = endsCollision;
		theCriteria[2] = moveCollision;

		oredCriteria = new WakeupOr(theCriteria);
		wakeupOn(oredCriteria);
	}
	
	public void processStimulus(Enumeration criteria) 
	{
		while (criteria.hasMoreElements()) 
		{
			WakeupCriterion theCriterion = (WakeupCriterion) criteria.nextElement();
			if (theCriterion instanceof WakeupOnCollisionEntry) 
			{
				if("paredNorte" == shape.getUserData()){ moveToBack();}
				if("paredSur" == shape.getUserData()){ moveToFront();}
				if("paredEste" == shape.getUserData()){ moveToLeft();}
				if("paredOeste" == shape.getUserData()){ moveToRight();}
				if("item" == shape.getUserData()){ pickUpItem();}
				if("obstacle" == shape.getUserData()){ random();}
			} 
			else if (theCriterion instanceof WakeupOnCollisionExit) 
			{
				if("paredNorte" == shape.getUserData()){ moveToBack();}
				if("paredOeste" == shape.getUserData()){ moveToRight();}

			} else 
			{
				if("paredNorte" == shape.getUserData()){ moveToBack();}
				if("paredOeste" == shape.getUserData()){ moveToRight();}
			}
		}
		wakeupOn(oredCriteria);
	}
	
	public void moveToBack()
	{
		Transform3D transform1 = new Transform3D();
		Transform3D transform2 = new Transform3D();
		Vector3f vector = new Vector3f();
		float [] componentes = new float [3];
		vpTrans.getTransform(transform1);
		transform1.get(vector);
		vector.get(componentes);
		componentes[2]+=0.3;
		vector.set(componentes);
		transform1.set(vector);
		transform2.rotY(Math.PI);
		transform1.mul(transform2);
		vpTrans.setTransform(transform1);
		sound.task("play");
	}
	
	public void moveToFront()
	{
		Transform3D transform1 = new Transform3D();
		Vector3f vector = new Vector3f();
		float [] componentes = new float [3];
		vpTrans.getTransform(transform1);
		transform1.get(vector);
		vector.get(componentes);
		componentes[2]-=0.3;
		vector.set(componentes);
		transform1.set(vector);
		vpTrans.setTransform(transform1);
		sound.task("play");
	}
	
	public void moveToLeft()
	{
		Transform3D transform1 = new Transform3D();
		Transform3D transform2 = new Transform3D();
		Vector3f vector = new Vector3f();
		float [] componentes = new float [3];
		vpTrans.getTransform(transform1);
		transform1.get(vector);
		vector.get(componentes);
		componentes[0]-=0.3;
		vector.set(componentes);
		transform1.set(vector);
		transform2.rotY(Math.PI/2);
		transform1.mul(transform2);
		vpTrans.setTransform(transform1);
		sound.task("play");
	}
	
	public void moveToRight()
	{
		Transform3D transform1 = new Transform3D();
		Transform3D transform2 = new Transform3D();
		Vector3f vector = new Vector3f();
		float [] componentes = new float [3];
		vpTrans.getTransform(transform1);
		transform1.get(vector);
		vector.get(componentes);
		componentes[0]+=0.3;
		vector.set(componentes);
		transform1.set(vector);
		transform2.rotY(Math.PI/-2);
		transform1.mul(transform2);
		vpTrans.setTransform(transform1);
		sound.task("play");
	}
	
	public void pickUpItem()
	{
		shape.removeAllGeometries();
		sound.task("play");
		scene.aumentarContador();
	}
	
	public void random()
	{
		random = (int)(Math.random()*4);
		if(random==0){ moveToBack();}
		if(random==2){ moveToFront();}
		if(random==1){ moveToLeft();}
		if(random==3){ moveToRight();}
		scene.decrementarContador();
	}
}