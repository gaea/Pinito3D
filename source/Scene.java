import com.sun.j3d.utils.universe.*; 
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
import com.sun.j3d.utils.image.TextureLoader;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;

public class Scene extends BranchGroup
{
    public Scene(SimpleUniverse simpleU, Color colorPinito, String nombrePinito, int time)
    {
    	this.nombrePinito = nombrePinito;
    	this.time=time;
    	color = colorPinito;
    	pickSound = new Sound("../sounds/pick.wav");
    	crash = new Sound("../sounds/crash.wav");
    	numItems = 0;
	vpTrans = simpleU.getViewingPlatform().getViewPlatformTransform();
	agragarPiso();
	agregarParedes();	
	agregarPersonajes();
	agregarLuces();
	agregarFondo();
	agregarItems();
	agregarTextos();
	agregarCollisionBehavior();
	compile();
    }

    public void agregarFondo()
    {
	Background background = new Background();
	background.setColor( 0.75f,0.85f,1f );
	background.setApplicationBounds(new BoundingSphere());
	addChild(background);
    }

    public void agregarLuces()
    {
    	Color3f white = new Color3f(0.7f, 0.7f, 0.7f);
	
	AmbientLight ambientLightNode = new AmbientLight(white);
	ambientLightNode.setInfluencingBounds(bounds);
	addChild(ambientLightNode);

	Vector3f light1Direction  = new Vector3f(-10.0f, -10.0f, -10.0f);
	Vector3f light2Direction  = new Vector3f(10.0f, 10.0f, 0.0f);

	DirectionalLight light1 = new DirectionalLight(white, light1Direction);
	light1.setInfluencingBounds(bounds);
	addChild(light1);
	
	DirectionalLight light2 = new DirectionalLight(white, light2Direction);
	light2.setInfluencingBounds(bounds);
	addChild(light2);
    }

    public void agragarPiso()
    {
    	float posicionY=-0.8f;	
	float ancho=2.0f;	
	float alto=0.02f;	
	float largo=2.0f;
	float escala=0.4f;	
	float colorR=0.0f;
	float colorG=0.0f;
	float colorB=0.0f;
	
	for(float i=0; i<60; i+=2) 
	{
		for(float j=0 ; j<60 ; j+=2) 
		{
			if(((i+j)%4) == 0) { addChild(new GaeaShape(i*escala, posicionY, j*escala, largo, alto, ancho, 0.0f, 0.0f,0.0f, escala));}
			else { addChild(new GaeaShape(i*escala, posicionY, j*escala, largo, alto, ancho, 1.0f, 1.0f, 1.0f, escala));}     
		}
	}
    }

    public void agregarPersonajes()
    {
		Transform3D t3dPi;
		Transform3D T3D;
		
		Vector3f translate = new Vector3f();
		pinito = new Pinito(color);
		
		T3D = new Transform3D();
		t3dPi = new Transform3D();
		t3dPi.setTranslation( new Vector3d(10.0, 0.1, 15.0));
		
		vpTrans2 = new TransformGroup();
		vpTrans2.setUserData("pinito");
		vpTrans2.setTransform(t3dPi);
		vpTrans2.addChild(pinito);
		vpTrans2.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		vpTrans2.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
		keyMove = new SimpleKeyBehavior(vpTrans2);
		keyMove.setSchedulingBounds(new BoundingSphere());
		addChild(keyMove);
		
		translate.set(11.6f, 13.0f, 46.0f);//translate.set(0.0f, 0.2f, 7f);//translate.set(10.0f, 1.5f, 40.0f);//translate.set(12.0f, 13.0f, 46.0f)
		Transform3D rotViewScene = new Transform3D();
		rotViewScene.rotX(Math.PI/-8.0);
		
		T3D.setTranslation(translate);
		T3D.mul(rotViewScene);
		vpTrans.setTransform(T3D);
	
		//keyNavCamara = new KeyNavigatorBehavior(vpTrans);
		//keyNavCamara.setSchedulingBounds(new BoundingSphere(new Point3d(), 1000f));
		
		keyNavPinito = new KeyNavigatorBehavior(vpTrans2);
		keyNavPinito.setSchedulingBounds(new BoundingSphere(new Point3d(), 1000f));
		
		//addChild(keyNavCamara);
		addChild(keyNavPinito);
		addChild(vpTrans2);
    }

    public void agregarParedes()
    {
    	Appearance app = new Appearance();
	
	PolygonAttributes pa = new PolygonAttributes();
	pa.setCullFace(PolygonAttributes.CULL_NONE);
	app.setPolygonAttributes(pa);
	
	TransparencyAttributes ta = new TransparencyAttributes();
	ta.setTransparencyMode( TransparencyAttributes.BLENDED );
	ta.setTransparency(0.7f); // 1.0f es totalmente transparente
	app.setTransparencyAttributes(ta);

	paredNorte = new GaeaShape(11.6f, -0.3f, -0.9f, 24.0f, 1.0f, 1.0f, app, 1.0f);
	paredNorte.setUserData("paredNorte");
	addChild(paredNorte);
	
	paredSur = new GaeaShape(11.6f, -0.3f, 24.1f, 24.0f, 1.0f, 1.0f, app, 1.0f);
	paredSur.setUserData("paredSur");
	addChild(paredSur);
	
	paredEste = new GaeaShape(24.1f, -0.3f, 11.6f, 1.0f, 24.0f, 1.0f, app, 1.0f);
	paredEste.setUserData("paredEste");
	addChild(paredEste);
	
	paredOeste = new GaeaShape(-0.9f, -0.3f, 11.6f, 1.0f, 24.0f, 1.0f, app, 1.0f);
	paredOeste.setUserData("paredOeste");
	addChild(paredOeste);
    }

    public void agregarItems()
    {
    	apparItem = new Appearance();
	Texture texturaItem =  new TextureLoader( "../images/fully.jpg", null).getTexture();
	apparItem.setTexture(texturaItem);
	
	apparObstacle = new Appearance();
	Texture texturaObstacle =  new TextureLoader( "../images/dd.jpg", null).getTexture();
	apparObstacle.setTexture(texturaObstacle);

	for(int i=0; i<22; i+=3)
	{
		for(int j=0; j<22; j+=3)
		{
			if(((i+j)%6) == 0)
			{
				item = new GaeaShape(1.0f + j, -0.5f, 1.0f + i, 1.0f, 1.0f, 1.0f, apparItem, 0.5f);
				item.setCapability(Shape3D.ALLOW_GEOMETRY_WRITE);
				item.setCapability(Shape3D.ALLOW_GEOMETRY_READ);
				item.setUserData("item");
				addChild(item);
				CollitionBehavior colDetItem = new CollitionBehavior(item, vpTrans2, bounds, pickSound, this);
				addChild(colDetItem);
			}
			else
			{
				obstacle = new GaeaShape(1f + j, -0.5f, 1f + i, 1.0f, 1.0f, 1.0f, apparObstacle, 0.5f);
				obstacle.setCapability(Shape3D.ALLOW_GEOMETRY_WRITE);
				obstacle.setCapability(Shape3D.ALLOW_GEOMETRY_READ);
				obstacle.setUserData("obstacle");
				addChild(obstacle);
				CollitionBehavior colDetObstacle = new CollitionBehavior(obstacle, vpTrans2, bounds, crash, this);
				addChild(colDetObstacle);
			}
		}
	}
    }

    public void agregarTextos()
    {
	Transform3D t3dCabezaImage = new Transform3D();
	TransformGroup tgCabezaImage = new TransformGroup();
	Appearance apparCabezaImage = new Appearance();
	Color3f ambientColourCabezaImage = new Color3f(color);
	Color3f emissiveColour = new Color3f(0.0f, 0.0f, 0.0f);
	Color3f specularColour = new Color3f(1.0f, 1.0f, 1.0f);
	Color3f diffuseColourCabezaImage = new Color3f(color);
	float shininess = 20.0f;
	apparCabezaImage.setMaterial(new Material(ambientColourCabezaImage, emissiveColour, diffuseColourCabezaImage, specularColour, shininess));
	ColoringAttributes colorAtriImage = new ColoringAttributes();
	colorAtriImage.setShadeModel(ColoringAttributes.SHADE_GOURAUD);
	apparCabezaImage.setColoringAttributes(colorAtriImage);
	Sphere cabezaImage = new Sphere(0.15f, apparCabezaImage);
	t3dCabezaImage.setTranslation(new Vector3f(12.6f, 12.225f, 42.5f));
	tgCabezaImage.setTransform(t3dCabezaImage);
	tgCabezaImage.addChild(cabezaImage);
	addChild(tgCabezaImage);

	Transform3D t3dOjoDerImage = new Transform3D();
	Transform3D rotOjoDerImage = new Transform3D();
	TransformGroup tgOjoDerImage = new TransformGroup();
	Appearance apparOjosImage = new Appearance();
	Color3f ambientColourOjosImage = new Color3f(1.0f, 1.0f, 1.0f);
	Color3f diffuseColourOjosImage = new Color3f(1.0f, 1.0f, 1.0f);
	apparOjosImage.setMaterial(new Material(ambientColourOjosImage, emissiveColour, diffuseColourOjosImage, specularColour, shininess));
	Cylinder ojoDerImage = new Cylinder (0.045f, 0.05f, apparOjosImage);
	rotOjoDerImage.rotX(Math.PI/2.0);
	t3dOjoDerImage.setTranslation(new Vector3f(12.655f, 42.625f, -12.245f));
	rotOjoDerImage.mul(t3dOjoDerImage);
	tgOjoDerImage.setTransform(rotOjoDerImage);
	tgOjoDerImage.addChild(ojoDerImage);
	addChild(tgOjoDerImage);

	Transform3D t3dOjoIrisDerImage = new Transform3D();
	Transform3D rotOjoIrisDerImage = new Transform3D();
	TransformGroup tgOjoIrisDerImage = new TransformGroup();
	Appearance apparOjosIrisImage = new Appearance();
	Color3f ambientColourOjosIrisImage = new Color3f(0.0f, 0.0f, 0.0f);
	Color3f diffuseColourOjosIrisImage = new Color3f(0.0f, 0.0f, 0.0f);
	apparOjosIrisImage.setMaterial(new Material(ambientColourOjosIrisImage, emissiveColour, diffuseColourOjosIrisImage, specularColour, shininess));
	Cylinder ojoIrisDerImage = new Cylinder (0.015f, 0.05f, apparOjosIrisImage);
	rotOjoIrisDerImage.rotX(Math.PI/2.0);
	t3dOjoIrisDerImage.setTranslation(new Vector3f(12.65f, 42.6255f, -12.245f));//(0.05f, 0.1255f, -0.020f)
	rotOjoIrisDerImage.mul(t3dOjoIrisDerImage);
	tgOjoIrisDerImage.setTransform(rotOjoIrisDerImage);
	tgOjoIrisDerImage.addChild(ojoIrisDerImage);
	addChild(tgOjoIrisDerImage);

	Transform3D t3dOjoIzqImage = new Transform3D();
	Transform3D rotOjoIzqImage = new Transform3D();
	TransformGroup tgOjoIzqImage = new TransformGroup();
	Cylinder ojoIzqImage = new Cylinder (0.045f, 0.05f, apparOjosImage);
	rotOjoIzqImage.rotX(Math.PI/2.0);
	t3dOjoIzqImage.setTranslation(new Vector3f(12.545f, 42.625f, -12.245f));
	rotOjoIzqImage.mul(t3dOjoIzqImage);
	tgOjoIzqImage.setTransform(rotOjoIzqImage);
	tgOjoIzqImage.addChild(ojoIzqImage);
	addChild(tgOjoIzqImage);

	Transform3D t3dOjoIrisIzqImage = new Transform3D();
	Transform3D rotOjoIrisIzqImage = new Transform3D();
	TransformGroup tgOjoIrisIzqImage = new TransformGroup();
	Cylinder ojoIrisIzqImage = new Cylinder (0.015f, 0.05f, apparOjosIrisImage);
	rotOjoIrisIzqImage.rotX(Math.PI/2.0);
	t3dOjoIrisIzqImage.setTranslation(new Vector3f(12.55f, 42.6255f, -12.245f));
	rotOjoIrisIzqImage.mul(t3dOjoIrisIzqImage);
	tgOjoIrisIzqImage.setTransform(rotOjoIrisIzqImage);
	tgOjoIrisIzqImage.addChild(ojoIrisIzqImage);
	addChild(tgOjoIrisIzqImage);

	/*******************************************************************************************/

	Appearance textAppear = new Appearance();
	textAppear.setMaterial(new Material());

	Font3D font3D = new Font3D(new Font("Arial", Font.PLAIN, 1), new FontExtrusion());
	textCountTime = new Text3D(font3D);
	textCountTime.setAlignment(Text3D.ALIGN_CENTER);
	textCountTime.setCapability(Text3D.ALLOW_STRING_WRITE);
	textCountTime.setCapability(Text3D.ALLOW_STRING_READ);

	Shape3D textShape = new Shape3D();
	textShape.setGeometry(textCountTime);
	textShape.setAppearance(textAppear);

	Transform3D trasText = new Transform3D();
	trasText.setTranslation(new Vector3f(8.5f, 3.0f, 5.0f));
	TransformGroup movText = new TransformGroup(trasText);

	Transform3D rotText = new Transform3D();
	rotText.rotX(Math.PI/-8);
	trasText.mul(rotText);
	
	movText.setTransform(trasText);
	addChild(movText);
	movText.addChild(textShape);

	timer = new Timer (10, new ActionListener ()
	{
		public void actionPerformed(ActionEvent e)
		{
			contar();
			textCountTime.setString(construirTexto(hora) + ":" + construirTexto(minutos) + ":" + construirTexto(segundos));
			tiempo++;
		}
	});
	timer.start();
		
	/**********************************************************************************************************/
	textTime = new Text3D(font3D, "Tiempo");
	textTime.setAlignment(Text3D.ALIGN_CENTER);
	
	Shape3D textTimeShape = new Shape3D();
	textTimeShape.setGeometry(textTime);
	textTimeShape.setAppearance(textAppear);
	
	Transform3D trasTextTime = new Transform3D();
	trasTextTime.setTranslation(new Vector3f(8.5f, 4.5f, 5.0f));
	TransformGroup movTextTime = new TransformGroup(trasTextTime);

	Transform3D rotTextTime = new Transform3D();
	rotTextTime.rotX(Math.PI/-8);
	trasTextTime.mul(rotTextTime);
	
	movTextTime.setTransform(trasTextTime);
	addChild(movTextTime);
	movTextTime.addChild(textTimeShape);

	/********************************************************************************/

	textPinitoName = new Text3D(font3D, nombrePinito);
	textPinitoName.setAlignment(Text3D.ALIGN_CENTER);
	
	Shape3D textPinitoNameShape = new Shape3D();
	textPinitoNameShape.setGeometry(textPinitoName);
	textPinitoNameShape.setAppearance(textAppear);
	
	Transform3D trasTextPinitoName = new Transform3D();
	trasTextPinitoName.setTranslation(new Vector3f(18.5f, 3.0f, 5.0f));
	TransformGroup movTextPinitoName = new TransformGroup(trasTextPinitoName);

	Transform3D rotTextPinitoName = new Transform3D();
	rotTextPinitoName.rotX(Math.PI/-8);
	trasTextPinitoName.mul(rotTextPinitoName);
	
	movTextPinitoName.setTransform(trasTextPinitoName);
	addChild(movTextPinitoName);
	movTextPinitoName.addChild(textPinitoNameShape);

	/*************************************************************************************************/

	textName = new Text3D(font3D, "Nombre");
	textName.setAlignment(Text3D.ALIGN_CENTER);
	
	Shape3D textNameShape = new Shape3D();
	textNameShape.setGeometry(textName);
	textNameShape.setAppearance(textAppear);
	
	Transform3D trasTextName = new Transform3D();
	trasTextName.setTranslation(new Vector3f(18.5f, 4.5f, 5.0f));
	TransformGroup movTextName = new TransformGroup(trasTextName);

	Transform3D rotTextName = new Transform3D();
	rotTextName.rotX(Math.PI/-8);
	trasTextName.mul(rotTextName);
	
	movTextName.setTransform(trasTextName);
	addChild(movTextName);
	movTextName.addChild(textNameShape);

	/****************************************************************************************************/

	itemImage = new GaeaShape(10.4f, 12.1f, 42.0f, 1.0f, 1.0f, 1.0f, apparItem, 0.25f);
	addChild(itemImage);

	/******************************************************************************************************/

	textCountBox = new Text3D(font3D, "00/32");
	textCountBox.setAlignment(Text3D.ALIGN_CENTER);
	textCountBox.setCapability(Text3D.ALLOW_STRING_WRITE);
	textCountBox.setCapability(Text3D.ALLOW_STRING_READ);
	
	Shape3D textCountBoxShape = new Shape3D();
	textCountBoxShape.setGeometry(textCountBox);
	textCountBoxShape.setAppearance(textAppear);
	
	Transform3D trasTextCountBox = new Transform3D();
	trasTextCountBox.setTranslation(new Vector3f(3.4f, 3.5f, 5.0f));
	TransformGroup movTextCountBox = new TransformGroup(trasTextCountBox);

	Transform3D rotTextCountBox = new Transform3D();
	rotTextCountBox.rotX(Math.PI/-8);
	trasTextCountBox.mul(rotTextCountBox);
	
	movTextCountBox.setTransform(trasTextCountBox);
	addChild(movTextCountBox);
	movTextCountBox.addChild(textCountBoxShape);

	/********************************************************************************************************/

	textCountScore = new Text3D(font3D, "100");
	textCountScore.setAlignment(Text3D.ALIGN_CENTER);
	textCountScore.setCapability(Text3D.ALLOW_STRING_WRITE);
	textCountScore.setCapability(Text3D.ALLOW_STRING_READ);
	
	Shape3D textCountScoreShape = new Shape3D();
	textCountScoreShape.setGeometry(textCountScore);
	textCountScoreShape.setAppearance(textAppear);
	
	Transform3D trasTextCountScore = new Transform3D();
	trasTextCountScore.setTranslation(new Vector3f(13.4f, 3.0f, 5.0f));
	TransformGroup movTextCountScore = new TransformGroup(trasTextCountScore);

	Transform3D rotTextCountScore = new Transform3D();
	rotTextCountScore.rotX(Math.PI/-8);
	trasTextCountScore.mul(rotTextCountScore);
	
	movTextCountScore.setTransform(trasTextCountScore);
	addChild(movTextCountScore);
	movTextCountScore.addChild(textCountScoreShape);

	/************************************************************************************************************/

	textScore = new Text3D(font3D, "Puntaje");
	textScore.setAlignment(Text3D.ALIGN_CENTER);
	
	Shape3D textScoreShape = new Shape3D();
	textScoreShape.setGeometry(textScore);
	textScoreShape.setAppearance(textAppear);
	
	Transform3D trasTextScore = new Transform3D();
	trasTextScore.setTranslation(new Vector3f(13.4f, 4.5f, 5.0f));
	TransformGroup movTextScore = new TransformGroup(trasTextScore);
	
	Transform3D rotTextScore = new Transform3D();
	rotTextScore.rotX(Math.PI/-8);
	trasTextScore.mul(rotTextScore);
	
	movTextScore.setTransform(trasTextScore);
	addChild(movTextScore);
	movTextScore.addChild(textScoreShape);
    	
	/****************************************************************************************************************/
	
	Font3D font3D1 = new Font3D(new Font("Arial", Font.PLAIN, 2), new FontExtrusion());
	Appearance apparResult = new Appearance();
	Color3f ambientColourResult = new Color3f(new Color(153, 102, 0));
	Color3f diffuseColourResult = new Color3f(new Color(153, 102, 0));
	apparResult.setMaterial(new Material(ambientColourResult, emissiveColour, diffuseColourResult, specularColour, shininess));
	
	textResultado = new Text3D(font3D1);
	textResultado.setAlignment(Text3D.ALIGN_CENTER);
	textResultado.setCapability(Text3D.ALLOW_STRING_WRITE);
	textResultado.setCapability(Text3D.ALLOW_STRING_READ);
	
	Shape3D textResultadoShape = new Shape3D();
	textResultadoShape.setGeometry(textResultado);
	textResultadoShape.setAppearance(apparResult);
	
	Transform3D trasTextResultado = new Transform3D();
	trasTextResultado.setTranslation(new Vector3f(11.0f, 3.0f, 20.0f));
	TransformGroup movTextResultado = new TransformGroup(trasTextResultado);
	
	Transform3D rotTextResultado = new Transform3D();
	rotTextResultado.rotX(Math.PI/-8);
	trasTextResultado.mul(rotTextResultado);
		
	movTextResultado.setTransform(trasTextResultado);
	addChild(movTextResultado);
	movTextResultado.addChild(textResultadoShape);

	/***************************************************************************************************************/

	textPuntaje = new Text3D(font3D1);
	textPuntaje.setAlignment(Text3D.ALIGN_CENTER);
	textPuntaje.setCapability(Text3D.ALLOW_STRING_WRITE);
	textPuntaje.setCapability(Text3D.ALLOW_STRING_READ);
	
	Shape3D textPuntajeShape = new Shape3D();
	textPuntajeShape.setGeometry(textPuntaje);
	textPuntajeShape.setAppearance(apparResult);
	
	Transform3D trasTextPuntaje = new Transform3D();
	trasTextPuntaje.setTranslation(new Vector3f(11.0f, 0.0f, 20.0f));
	TransformGroup movTextPuntaje = new TransformGroup(trasTextPuntaje);

	Transform3D rotTextPuntaje = new Transform3D();
	rotTextPuntaje.rotX(Math.PI/-8);
	trasTextPuntaje.mul(rotTextPuntaje);
	
	movTextPuntaje.setTransform(trasTextPuntaje);
	addChild(movTextPuntaje);
	movTextPuntaje.addChild(textPuntajeShape);

    }

    public void contar()
    {
	if(segundos==99){ segundos=-1; minutos++; }
	if(minutos==59) { minutos=0; hora++; }
	segundos++;
	if(hora==time)
	{
		timer.stop();
		keyNavPinito.setEnable(false);
		mostrarResultado("tiempo");
	}
    }
	
    public void mostrarResultado(String result)
    {
	if(result.equals("tiempo"))
	{
		textResultado.setString("Tiempo Terminado");
		textPuntaje.setString("Tu Puntaje es " + score);
	}
	if(result.equals("cajas"))
	{
		timer.stop();
		keyNavPinito.setEnable(false);
		textResultado.setString("Terminaste");
		textPuntaje.setString("Tu Puntaje es " + score*(time*1000*60/tiempo));
	}
	if(result.equals("perdedor"))
	{
		timer.stop();
		keyNavPinito.setEnable(false);
		textResultado.setString("Perdiste !!!!!!");
		textPuntaje.setString("Tu Puntaje es " + score);
	}

	sendScore();
    }

    public void sendScore()
    {
	ObjectOutputStream salida = null;
	ObjectInputStream entrada = null;
	Socket cliente = null;
	
	int i = JOptionPane.showConfirmDialog(null,"Quieres enviar puntaje ?", "Enviar Puntaje", JOptionPane.YES_NO_OPTION);
	if (i==0)
	{
		try 
		{
			cliente = new Socket("localhost", 1234 );
		
			salida = new ObjectOutputStream( cliente.getOutputStream() );
			salida.flush();
		
			entrada = new ObjectInputStream( cliente.getInputStream() );
		
			salida.writeObject("insert into pinito3d values(" + time + "," + "'" + nombrePinito + "'" + "," + score + "," + "'" + hora + ":" + minutos + ":" + segundos + "'" + "," + numItems + ")");
			System.out.println("mande la consulta");
			salida.flush();
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
    }

    public String construirTexto(int cantidad)
    {
	if(cantidad<10) { cadena="0"+cantidad;}
	else {cadena=""+cantidad;}
	return cadena;
    }

    public void agregarCollisionBehavior()
    {
	CollitionBehavior colDetNorte = new CollitionBehavior(paredNorte, vpTrans2, bounds, crash, this);
	CollitionBehavior colDetSur = new CollitionBehavior(paredSur, vpTrans2, bounds, crash, this);
	CollitionBehavior colDetEste = new CollitionBehavior(paredEste, vpTrans2, bounds, crash, this);
	CollitionBehavior colDetOeste = new CollitionBehavior(paredOeste, vpTrans2, bounds, crash, this);
	addChild(colDetNorte);
	addChild(colDetSur);
	addChild(colDetEste);
	addChild(colDetOeste);
    }

    public void aumentarContador()
    {
	numItems++;
	score+=100;
	if(numItems<10) { textCountBox.setString("0"+numItems+"/"+32);}
	else { textCountBox.setString(numItems+"/"+32);}
	textCountScore.setString(""+score);
	if(numItems==32){mostrarResultado("cajas");}
    }

    public void decrementarContador()
    {
	score-=50;
	textCountScore.setString(""+score);
	if(score==0) {mostrarResultado("perdedor");}
    }

	private SimpleKeyBehavior keyMove;
	private int tiempo=0;
	private int minutos=0;
	private int hora=0;
	private int segundos=0;
	private int time;
	private int numItems;
	private int score=100;
	private String cadena;
	private String nombrePinito;
	private Appearance apparItem;
	private Appearance apparObstacle;
	private Text3D textResultado;
	private Text3D textPuntaje;
	private Text3D textScore;
	private Text3D textCountScore;
	private Text3D textCountBox;
	private Text3D textCountTime;
	private Text3D textTime;
	private Text3D textPinitoName;
	private Text3D textName;
	private Text3D nombre;
	private Timer timer;
	private Color color;
	private TransformGroup vpTrans2;
	private TransformGroup vpTrans;
	private GaeaShape paredNorte;
	private GaeaShape paredSur;
	private GaeaShape paredEste;
	private GaeaShape paredOeste;
	private GaeaShape item;
	private GaeaShape obstacle;
	private GaeaShape itemImage;
	private Pinito pinito;
	private BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 1000.0);
	private KeyNavigatorBehavior keyNavPinito;
	private KeyNavigatorBehavior keyNavCamara;
	private Sound pickSound;
	private Sound crash;
}