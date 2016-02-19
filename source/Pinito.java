import java.awt.*;
import com.sun.j3d.utils.universe.*; 
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.geometry.*;

import javax.media.j3d.*;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.*;

public class Pinito extends BranchGroup
{
	Pinito(Color color)
	{
		setUserData("pinito");
		BranchGroup pinito = new BranchGroup();

		//cabeza
		Transform3D t3dCabeza = new Transform3D();
		TransformGroup tgCabeza = new TransformGroup();
		Appearance apparCabeza = new Appearance();
		Color3f ambientColourCabeza = new Color3f(color);
		Color3f emissiveColour = new Color3f(0.0f, 0.0f, 0.0f);
		Color3f specularColour = new Color3f(1.0f, 1.0f, 1.0f);
		Color3f diffuseColourCabeza = new Color3f(color);
		float shininess = 20.0f;
		apparCabeza.setMaterial(new Material(ambientColourCabeza, emissiveColour, diffuseColourCabeza, specularColour, shininess));
		ColoringAttributes colorAtri = new ColoringAttributes();
		colorAtri.setShadeModel(ColoringAttributes.SHADE_GOURAUD);
		apparCabeza.setColoringAttributes(colorAtri);
		Sphere cabeza = new Sphere(0.15f, apparCabeza);
		t3dCabeza.setTranslation(new Vector3f(0.0f, 0.0f, 0.0f));
		tgCabeza.setTransform(t3dCabeza);
		tgCabeza.addChild(cabeza);
		pinito.addChild(tgCabeza);

		//ojos
		Transform3D t3dOjoDer = new Transform3D();
		Transform3D rotOjoDer = new Transform3D();
		TransformGroup tgOjoDer = new TransformGroup();
		Appearance apparOjos = new Appearance();
		Color3f ambientColourOjos = new Color3f(1.0f, 1.0f, 1.0f);
		Color3f diffuseColourOjos = new Color3f(1.0f, 1.0f, 1.0f);
		apparOjos.setMaterial(new Material(ambientColourOjos, emissiveColour, diffuseColourOjos, specularColour, shininess));
		Cylinder ojoDer = new Cylinder (0.045f, 0.05f, apparOjos);
		rotOjoDer.rotX(Math.PI/2.0);
		t3dOjoDer.setTranslation(new Vector3f(0.055f, 0.125f, -0.020f));
		rotOjoDer.mul(t3dOjoDer);
		tgOjoDer.setTransform(rotOjoDer);
		tgOjoDer.addChild(ojoDer);
		pinito.addChild(tgOjoDer);

		Transform3D t3dOjoIrisDer = new Transform3D();
		Transform3D rotOjoIrisDer = new Transform3D();
		TransformGroup tgOjoIrisDer = new TransformGroup();
		Appearance apparOjosIris = new Appearance();
		Color3f ambientColourOjosIris = new Color3f(0.0f, 0.0f, 0.0f);
		Color3f diffuseColourOjosIris = new Color3f(0.0f, 0.0f, 0.0f);
		apparOjosIris.setMaterial(new Material(ambientColourOjosIris, emissiveColour, diffuseColourOjosIris, specularColour, shininess));
		
		//apparOjosIris.setColoringAttributes(new ColoringAttributes(0.0f, 0.0f, 0.0f,ColoringAttributes.NICEST));
		Cylinder ojoIrisDer = new Cylinder (0.015f, 0.05f, apparOjosIris);
		rotOjoIrisDer.rotX(Math.PI/2.0);
		t3dOjoIrisDer.setTranslation(new Vector3f(0.05f, 0.1255f, -0.020f));
		rotOjoIrisDer.mul(t3dOjoIrisDer);
		tgOjoIrisDer.setTransform(rotOjoIrisDer);
		tgOjoIrisDer.addChild(ojoIrisDer);
		pinito.addChild(tgOjoIrisDer);

		Transform3D t3dOjoIzq = new Transform3D();
		Transform3D rotOjoIzq = new Transform3D();
		TransformGroup tgOjoIzq = new TransformGroup();
		Cylinder ojoIzq = new Cylinder (0.045f, 0.05f, apparOjos);
		rotOjoIzq.rotX(Math.PI/2.0);
		t3dOjoIzq.setTranslation(new Vector3f(-0.055f, 0.125f, -0.020f));
		rotOjoIzq.mul(t3dOjoIzq);
		tgOjoIzq.setTransform(rotOjoIzq);
		tgOjoIzq.addChild(ojoIzq);
		pinito.addChild(tgOjoIzq);

		Transform3D t3dOjoIrisIzq = new Transform3D();
		Transform3D rotOjoIrisIzq = new Transform3D();
		TransformGroup tgOjoIrisIzq = new TransformGroup();
		Cylinder ojoIrisIzq = new Cylinder (0.015f, 0.05f, apparOjosIris);
		rotOjoIrisIzq.rotX(Math.PI/2.0);
		t3dOjoIrisIzq.setTranslation(new Vector3f(-0.05f, 0.1255f, -0.020f));
		rotOjoIrisIzq.mul(t3dOjoIrisIzq);
		tgOjoIrisIzq.setTransform(rotOjoIrisIzq);
		tgOjoIrisIzq.addChild(ojoIrisIzq);
		pinito.addChild(tgOjoIrisIzq);

		//cuello
		Transform3D t3dCuello = new Transform3D();
		TransformGroup tgCuello = new TransformGroup();
		Cylinder cuello = new Cylinder (0.020f, 0.05f, apparCabeza);
		t3dCuello.setTranslation(new Vector3f(0.0f, -0.165f, 0.0f));
		tgCuello.setTransform(t3dCuello);
		tgCuello.addChild(cuello);
		pinito.addChild(tgCuello);

		//cuerpo
		Transform3D t3dCuerpo = new Transform3D();
		TransformGroup tgCuerpo = new TransformGroup();
		Cylinder cuerpo = new Cylinder (0.10f, 0.4f, apparCabeza);
		t3dCuerpo.setTranslation(new Vector3f(0.0f, -0.375f, 0.0f));
		tgCuerpo.setTransform(t3dCuerpo);
		tgCuerpo.addChild(cuerpo);
		pinito.addChild(tgCuerpo);

		//hombros
		Transform3D t3dHombroDer = new Transform3D();
		TransformGroup tgHombroDer = new TransformGroup();
		Appearance apparHombros = new Appearance();
		Color3f ambientColourHombros = new Color3f(0.2f, 0.2f, 0.2f);
		Color3f diffuseColourHombros = new Color3f(0.2f, 0.2f, 0.2f);
		apparHombros.setMaterial(new Material(ambientColourHombros, emissiveColour, diffuseColourHombros, specularColour, shininess));
		Sphere hombroDer = new Sphere(0.030f, apparHombros);
		t3dHombroDer.setTranslation(new Vector3f(0.12f, -0.2f, 0.0f));
		tgHombroDer.setTransform(t3dHombroDer);
		tgHombroDer.addChild(hombroDer);
		pinito.addChild(tgHombroDer);

		Transform3D t3dHombroIzq = new Transform3D();
		TransformGroup tgHombroIzq = new TransformGroup();
		Sphere hombroIzq = new Sphere(0.030f, apparHombros);
		t3dHombroIzq.setTranslation(new Vector3f(-0.12f, -0.2f, 0.0f));
		tgHombroIzq.setTransform(t3dHombroIzq);
		tgHombroIzq.addChild(hombroIzq);
		pinito.addChild(tgHombroIzq);

		//brazos
		Transform3D t3dBrazoDer = new Transform3D();
		Transform3D rotBrazoDer = new Transform3D();
		TransformGroup tgBrazoDer = new TransformGroup();
		Cylinder brazoDer = new Cylinder (0.020f, 0.10f, apparCabeza);
		rotBrazoDer.rotZ(Math.PI/4.0);
		t3dBrazoDer.setTranslation(new Vector3f(-0.05f, -0.3f, 0.0f));
		rotBrazoDer.mul(t3dBrazoDer);
		tgBrazoDer.setTransform(rotBrazoDer);
		tgBrazoDer.addChild(brazoDer);
		pinito.addChild(tgBrazoDer);

		Transform3D t3dBrazoIzq = new Transform3D();
		Transform3D rotBrazoIzq = new Transform3D();
		TransformGroup tgBrazoIzq = new TransformGroup();
		Cylinder brazoIzq = new Cylinder (0.020f, 0.10f, apparCabeza);
		rotBrazoIzq.rotZ(Math.PI/(-4.0));
		t3dBrazoIzq.setTranslation(new Vector3f(0.05f, -0.3f, 0.0f));
		rotBrazoIzq.mul(t3dBrazoIzq);
		tgBrazoIzq.setTransform(rotBrazoIzq);
		tgBrazoIzq.addChild(brazoIzq);
		pinito.addChild(tgBrazoIzq);

		//codos
		Transform3D t3dCodoDer = new Transform3D();
		TransformGroup tgCodoDer = new TransformGroup();
		Sphere codoDer = new Sphere(0.030f, apparHombros);
		t3dCodoDer.setTranslation(new Vector3f(0.23f, -0.3f, 0.0f));
		tgCodoDer.setTransform(t3dCodoDer);
		tgCodoDer.addChild(codoDer);
		pinito.addChild(tgCodoDer);

		Transform3D t3dCodoIzq = new Transform3D();
		TransformGroup tgCodoIzq = new TransformGroup();
		Sphere codoIzq = new Sphere(0.030f, apparHombros);
		t3dCodoIzq.setTranslation(new Vector3f(-0.23f, -0.3f, 0.0f));
		tgCodoIzq.setTransform(t3dCodoIzq);
		tgCodoIzq.addChild(codoIzq);
		pinito.addChild(tgCodoIzq);

		//antebrazos
		Transform3D t3dAnteBrazoDer = new Transform3D();
		Transform3D rotAnteBrazoDer = new Transform3D();
		TransformGroup tgAnteBrazoDer = new TransformGroup();
		Cylinder antebrazoDer = new Cylinder (0.020f, 0.10f, apparCabeza);
		rotAnteBrazoDer.rotZ(Math.PI/-3.0);
		t3dAnteBrazoDer.setTranslation(new Vector3f(0.38f, 0.1f, 0.0f));
		rotAnteBrazoDer.mul(t3dAnteBrazoDer);
		tgAnteBrazoDer.setTransform(rotAnteBrazoDer);
		tgAnteBrazoDer.addChild(antebrazoDer);
		pinito.addChild(tgAnteBrazoDer);

		Transform3D t3dAnteBrazoIzq = new Transform3D();
		Transform3D rotAnteBrazoIzq = new Transform3D();
		TransformGroup tgAnteBrazoIzq = new TransformGroup();
		Cylinder antebrazoIzq = new Cylinder (0.020f, 0.10f, apparCabeza);
		rotAnteBrazoIzq.rotZ(Math.PI/4.0);
		t3dAnteBrazoIzq.setTranslation(new Vector3f(-0.38f, 0.025f, 0.0f));
		rotAnteBrazoIzq.mul(t3dAnteBrazoIzq);
		tgAnteBrazoIzq.setTransform(rotAnteBrazoIzq);
		tgAnteBrazoIzq.addChild(antebrazoIzq);
		pinito.addChild(tgAnteBrazoIzq);

		//guante
		Transform3D t3dGuante = new Transform3D();
		TransformGroup tgGuante = new TransformGroup();
		Appearance apparGuante = new Appearance();
		Color3f ambientColourGuante = new Color3f(0.823529412f, 0.411764706f, 0.117647059f);
		Color3f diffuseColourGuante = new Color3f(0.823529412f, 0.411764706f, 0.117647059f);
		apparGuante.setMaterial(new Material(ambientColourGuante, emissiveColour, diffuseColourGuante, specularColour, shininess));
		//apparGuante.setColoringAttributes(new ColoringAttributes(0.823529412f, 0.411764706f, 0.117647059f,ColoringAttributes.NICEST));
		Sphere guante = new Sphere(0.2f, apparGuante);
		t3dGuante.setTranslation(new Vector3f(0.48f, -0.16f, 0.0f));
		tgGuante.setTransform(t3dGuante);
		tgGuante.addChild(guante);
		pinito.addChild(tgGuante);

		//mano
		Transform3D t3dMano = new Transform3D();
		TransformGroup tgMano = new TransformGroup();
		Sphere mano = new Sphere(0.08f, apparHombros);
		t3dMano.setTranslation(new Vector3f(-0.36f, -0.18f, 0.0f));
		tgMano.setTransform(t3dMano);
		tgMano.addChild(mano);
		pinito.addChild(tgMano);

		//muslos
		Transform3D t3dMusloDer = new Transform3D();
		TransformGroup tgMusloDer = new TransformGroup();
		Cylinder musloDer = new Cylinder (0.020f, 0.08f, apparCabeza);
		t3dMusloDer.setTranslation(new Vector3f(0.05f, -0.6f, 0.0f));
		tgMusloDer.setTransform(t3dMusloDer);
		tgMusloDer.addChild(musloDer);
		pinito.addChild(tgMusloDer);

		Transform3D t3dMusloIzq = new Transform3D();
		TransformGroup tgMusloIzq = new TransformGroup();
		Cylinder musloIzq = new Cylinder (0.020f, 0.08f, apparCabeza);
		t3dMusloIzq.setTranslation(new Vector3f(-0.05f, -0.6f, 0.0f));
		tgMusloIzq.setTransform(t3dMusloIzq);
		tgMusloIzq.addChild(musloIzq);
		pinito.addChild(tgMusloIzq);

		//rodillas
		Transform3D t3dRodillaDer = new Transform3D();
		TransformGroup tgRodillaDer = new TransformGroup();
		Sphere rodillaDer = new Sphere(0.030f, apparHombros);
		t3dRodillaDer.setTranslation(new Vector3f(0.05f, -0.66f, 0.0f));
		tgRodillaDer.setTransform(t3dRodillaDer);
		tgRodillaDer.addChild(rodillaDer);
		pinito.addChild(tgRodillaDer);

		Transform3D t3dRodillaIzq = new Transform3D();
		TransformGroup tgRodillaIzq = new TransformGroup();
		Sphere rodillaIzq = new Sphere(0.030f, apparHombros);
		t3dRodillaIzq.setTranslation(new Vector3f(-0.05f, -0.66f, 0.0f));
		tgRodillaIzq.setTransform(t3dRodillaIzq);
		tgRodillaIzq.addChild(rodillaIzq);
		pinito.addChild(tgRodillaIzq);

		//piernas
		Transform3D t3dPiernaDer = new Transform3D();
		Transform3D rotPiernaDer = new Transform3D();
		TransformGroup tgPiernaDer = new TransformGroup();
		Cylinder piernaDer = new Cylinder (0.020f, 0.10f, apparCabeza);
		rotPiernaDer.rotZ(Math.PI/2.0);
		t3dPiernaDer.setTranslation(new Vector3f(-0.665f, -0.1f, 0.0f));
		rotPiernaDer.mul(t3dPiernaDer);
		tgPiernaDer.setTransform(rotPiernaDer);
		tgPiernaDer.addChild(piernaDer);
		pinito.addChild(tgPiernaDer);

		Transform3D t3dPiernaIzq = new Transform3D();
		Transform3D rotPiernaIzq = new Transform3D();
		TransformGroup tgPiernaIzq = new TransformGroup();
		Cylinder piernaIzq = new Cylinder (0.020f, 0.10f, apparCabeza);
		rotPiernaIzq.rotZ(Math.PI/2.0);
		t3dPiernaIzq.setTranslation(new Vector3f(-0.665f, 0.1f, 0.0f));
		rotPiernaIzq.mul(t3dPiernaIzq);
		tgPiernaIzq.setTransform(rotPiernaIzq);
		tgPiernaIzq.addChild(piernaIzq);
		pinito.addChild(tgPiernaIzq);

		//llantas
		Transform3D t3dLlantaDer = new Transform3D();
		Transform3D rotLlantaDer = new Transform3D();
		TransformGroup tgLlantaDer = new TransformGroup();
		Cylinder llantaDer = new Cylinder (0.2f, 0.06f, apparHombros);
		rotLlantaDer.rotZ(Math.PI/2.0);
		t3dLlantaDer.setTranslation(new Vector3f(-0.665f, -0.16f, 0.0f));
		rotLlantaDer.mul(t3dLlantaDer);
		tgLlantaDer.setTransform(rotLlantaDer);
		tgLlantaDer.addChild(llantaDer);
		pinito.addChild(tgLlantaDer);

		Transform3D t3dLlantaIzq = new Transform3D();
		Transform3D rotLlantaIzq = new Transform3D();
		TransformGroup tgLlantaIzq = new TransformGroup();
		Cylinder llantaIzq = new Cylinder (0.2f, 0.06f, apparHombros);
		rotLlantaIzq.rotZ(Math.PI/2.0);
		t3dLlantaIzq.setTranslation(new Vector3f(-0.665f, 0.16f, 0.0f));
		rotLlantaIzq.mul(t3dLlantaIzq);
		tgLlantaIzq.setTransform(rotLlantaIzq);
		tgLlantaIzq.addChild(llantaIzq);
		pinito.addChild(tgLlantaIzq);

		Transform3D pinito3d=new Transform3D();
		Transform3D rotPinito3d=new Transform3D();
		TransformGroup pinitoTrans=new TransformGroup();
		rotPinito3d.rotY(Math.PI);
		pinito3d.setTranslation(new Vector3f(0.0f, 0.0f, 0.0f));
		pinito3d.mul(rotPinito3d);
		pinitoTrans.setTransform(pinito3d);
		pinitoTrans.addChild(pinito);
		addChild(pinitoTrans);
		compile();
	}
}