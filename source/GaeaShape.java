
import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.universe.*;
import javax.media.j3d.*;
import javax.vecmath.*;
import javax.media.j3d.QuadArray;
import javax.media.j3d.Shape3D;

public class GaeaShape extends Shape3D
{

    public GaeaShape(){this(1.0f);}

    public GaeaShape(float d){this(0.0f, 0.0f, 0.0f, d);}

    public GaeaShape(float x, float y, float z, float escala){this(x, y, z, 1.0f, 1.0f, 1.0f, escala);}

    public GaeaShape(float x, float y, float z, float largo, float alto, float ancho, float escala)
    {
        QuadArray quadarray = new QuadArray(24, 5);
        gus(x, y, z, largo, ancho, alto, escala);
        quadarray.setCoordinates(0, af);
        quadarray.setColors(0, colors);
        setGeometry(quadarray);
    }

    public GaeaShape(float x, float y, float z, float largo, float alto, float ancho, Appearance appear, float escala)
    {
        QuadArray quadarray = new QuadArray(24, GeometryArray.COORDINATES| GeometryArray.TEXTURE_COORDINATE_2);
        gus(x, y, z, largo, ancho, alto, escala);
		Point2f q = new Point2f();
		quadarray.setCoordinates(0, af);

		for (int i=0;i<4;i++)
		{	
			if(i==0){q.set(0.0f, 1.0f);}	
			if(i==1){q.set(0.0f, 0.0f);}
			if(i==2){q.set(1.0f, 0.0f);}
			if(i==3){q.set(1.0f, 1.0f);}	
				
			for(int g=0;g<24;g+=4)
			{
				quadarray.setTextureCoordinate((g+i), q);
			}
		}
		Color3f ambientColour = new Color3f(0.5f, 0.5f, 0.5f);
		Color3f emissiveColour = new Color3f(0.0f, 0.0f, 0.0f);
		Color3f specularColour = new Color3f(1.0f, 1.0f, 1.0f);
		Color3f diffuseColour = new Color3f(0.5f, 0.5f, 0.5f);
		float shininess = 20.0f;
		appear.setMaterial(new Material(ambientColour, emissiveColour, diffuseColour, specularColour, shininess));
		setGeometry(quadarray);
		setAppearance(appear);
    }

    public GaeaShape(float x, float y, float z, float largo, float ancho, float alto, float r, float g, float b, float escala)
    {
        QuadArray quadarray = new QuadArray(24, 5);
        gus(x, y, z, largo, ancho, alto, escala);

        float afc[] = new float [verts.length];

        for(int j=0, k=1, l=2; l < verts.length ; j+=3,k+=3,l+=3)
        {
            afc[j] = r;
            afc[k] = g;
            afc[l] = b;
        }

        quadarray.setCoordinates(0, af);
        quadarray.setColors(0, afc);
        setGeometry(quadarray);
    }

    public void gus(float x, float y, float z, float largo, float ancho, float alto, float escala) 
    {
	scale = escala;
        coorX = x;
        coorY = y;
        coorZ = z;

        for(int j=0, k=1, l=2; l < verts.length ; j+=3,k+=3,l+=3)
        {
            af[j] = ((verts[j] * escala * (largo / 2)) + x);
            af[k] = ((verts[k] * escala * (ancho / 2)) + y);
            af[l] = ((verts[l] * escala * (alto / 2)) + z);
        }
    }

    public Shape3D getShape(){return this;}

    public double getScale(){return scale;}

    public double getCoorX(){return coorX;}

    public double getCoorY(){return coorY;}

    public double getCoorZ(){return coorZ;}

    private static final float verts[] = {
        1.0F, -1F, 1.0F, 1.0F, 1.0F, 1.0F, -1.0F, 1.0F, 1.0F, -1F, -1.0F, 1.0F,
        -1.0F, -1F, -1F, -1F, 1.0F, -1F, 1.0F, 1.0F, -1F, 1.0F, -1F, -1F,
        1.0F, -1F, -1F, 1.0F, 1.0F, -1F, 1.0F, 1.0F, 1.0F, 1.0F, -1F, 1.0F,
        -1F, -1F, 1.0F, -1F, 1.0F, 1.0F, -1F, 1.0F, -1F, -1F, -1F, -1F,
        1.0F, 1.0F, 1.0F, 1.0F, 1.0F, -1F, -1F, 1.0F, -1F, -1F, 1.0F, 1.0F,
        -1F, -1F, 1.0F, -1F, -1F, -1F, 1.0F, -1F, -1F, 1.0F, -1F, 1.0F
    };

    private static final float colors[] = {
        1.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F,//rojo
        0.0F, 1.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 1.0F, 0.0F,//verde
        0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 1.0F,//azul
        1.0F, 1.0F, 0.0F, 1.0F, 1.0F, 0.0F, 1.0F, 1.0F, 0.0F, 1.0F, 1.0F, 0.0F,//amarillo
        1.0F, 0.0F, 1.0F, 1.0F, 0.0F, 1.0F, 1.0F, 0.0F, 1.0F, 1.0F, 0.0F, 1.0F,//rosado
        0.0F, 1.0F, 1.0F, 0.0F, 1.0F, 1.0F, 0.0F, 1.0F, 1.0F, 0.0F, 1.0F, 1.0F//azul claro
    };

    float af[] = new float[verts.length];
    double scale;
    float coorX;
    float coorY;
    float coorZ;
}
