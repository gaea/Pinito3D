import java.io.*;
import java.net.*;
import javax.swing.table.*;

public class Server 
{
    private ControladorBaseDatos controlBase;
    private ServerSocket SocketServidor;
    private Socket conexion;

    public Server() throws Exception
    {
    	controlBase = new ControladorBaseDatos();
    	SocketServidor = new ServerSocket(1234);
	
	while(true) 
	{ 
		conexion = SocketServidor.accept(); 
		
		new Thread( new Runnable(){ public void run()
			{
				try{ ejecutarServidor(conexion); }
				catch (Exception ex){ ex.printStackTrace(); }
			}}).start();
	}
    }
       
    public void ejecutarServidor ( Socket conexion ) throws Exception
    {
	String consulta = "";
	ObjectOutputStream salida = null;
	ObjectInputStream entrada = null;
    	try
    	{ 
		    salida = new ObjectOutputStream( conexion.getOutputStream() );
		    salida.flush();
		    entrada = new ObjectInputStream( conexion.getInputStream() );

		while (true)
		{
			consulta = (String)entrada.readObject();
			System.out.println("leo los datos de entrada");
			System.out.println(consulta);
			controlBase.consultar(consulta);
			System.out.println("mande la consulta a la base de datos");
			salida.writeObject((DefaultTableModel)controlBase.getModel());
			
			salida.flush();
			System.out.println("mande la consulta al cliente");
		}
	}

	catch ( Exception ex ) 
	{ }
	
	finally 
	{
		salida.close();
		entrada.close();
		conexion.close();
	}
     }
    
    public static void main(String [] gus)
    {
	try
	{
		new Server();
	}
	catch (Exception ex){ System.err.println("algo paso");}
    }
}