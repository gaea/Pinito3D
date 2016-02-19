import java.sql.*;
import javax.swing.table.*;

public class ControladorBaseDatos 
{
    private DefaultTableModel modelo;

    public ControladorBaseDatos(){}
    
	public static Connection getConection() 
	{
	    Connection  conection = null;
	
	    String cadenaConexion = "jdbc:postgresql://malpelo.univalle.edu.co:5432:gaea";
	    String usuario = "gaea";
	    String clave = "gaea";
	
	    try 
	    {
	        Class.forName("org.postgresql.Driver");
	        conection = DriverManager.getConnection (cadenaConexion,usuario,clave);
	    }
	    catch(Exception e) 
	    {
		System.out.println("No se pudo cargar el Driver: " + e.getMessage());
	    }

	    return conection;
	}

	public void consultar(String sentencia)
	{
	    Statement stmt = null; 
	    Connection conection = getConection();
	    ResultSet rs = null;
	   
	    try 
	    {
		   stmt = conection.createStatement();
		   if (sentencia.charAt(0)=='s'){ rs = stmt.executeQuery(sentencia);}
		   if (sentencia.charAt(0)=='i'){ stmt.executeUpdate(sentencia);}
		   System.out.println("salio de consulta");
		   addModel(rs);
		   stmt.close();
	    }
	   
	    catch (Exception e2)
	    {
		   System.out.println("Fallo en SQL: "+e2.getMessage());
	    }

	    return;
	}

	public void addModel(ResultSet rset)
	{
		try 
		{
			int numero_col = (int)(rset.getMetaData()).getColumnCount();
			modelo = new DefaultTableModel();
	
			for( int i=1; i<=numero_col; i++ )
			{
				modelo.addColumn( ""+(rset.getMetaData()).getColumnLabel( i ));
			}
	
			Object[] fila = new Object[numero_col];
	       
			while(rset.next())
			{
				for(int i=0; i< numero_col; i++)
				{
					fila[i]=rset.getObject(i+1);
				}
				modelo.addRow( fila );
			}
			System.out.println("tabla creada");
		}	
		
		catch ( Exception e )
		{
			System.out.println( e.toString() );
			System.out.println( "NO SE PUDO REALIZAR LA BUSQUEDA" );
		}
	}

	public DefaultTableModel getModel()
	{
		return modelo;
	}
}
