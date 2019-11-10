package DBACCESS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

public class DAOesecutore {

	public static LinkedList esegui_query(String url, String username, String password,String query){

		Connection connection = null;
		Statement stmt = null;
		ResultSet rset  = null;
		LinkedList lista=new LinkedList();

		try {
			// Load the JDBC driver
			String driverName = "oracle.jdbc.driver.OracleDriver";
			Class.forName(driverName);

			connection = DriverManager.getConnection(url, username, password);
			//System.out.println("Connesso!!!!");
			
			stmt = connection.createStatement();
			
			long inizio=System.currentTimeMillis();
			rset = stmt.executeQuery(query);
			long fine=System.currentTimeMillis();
			
			ResultSetMetaData rsmd = rset.getMetaData();
		
			
			int campi=rsmd.getColumnCount();

			// NOMI COLONNE
			LinkedList record=new LinkedList();
			for(int i=1;i<=campi;i++){
				record.add(rsmd.getColumnName(i));
			}
			lista.add(record);


			while (rset.next()){
				record=new LinkedList();

				for(int i=1;i<=campi;i++){
					record.add(rset.getString(i));
				}
				lista.add(record);

			}
			record=new LinkedList();
			record.add("Tempo di esecuzione: "+(fine-inizio));
			lista.add(record);
			rset.close();
			stmt.close();
			connection.close();

		} catch (ClassNotFoundException e) {
			// Could not find the database driver
			System.out.println("###################################");
			System.out.println("ERRORE CONNESSIONE DB");
			System.out.println("###################################");
			e.printStackTrace();
		} catch (SQLException e) {
			// Could not connect to the database
			System.out.println("###################################");
			System.out.println("ERRORE QUERY");
			System.out.println("###################################");
			e.printStackTrace();
		}

		return lista;
	}
	public static int esegui_update(String url, String username, String password,String query){

		Connection connection = null;
		Statement stmt = null;
		ResultSet rset  = null;
		int n=0;
		try {
			// Load the JDBC driver
			String driverName = "oracle.jdbc.driver.OracleDriver";
			Class.forName(driverName);

			connection = DriverManager.getConnection(url, username, password);
			//System.out.println("Connesso!!!!");

			stmt = connection.createStatement();
			n =stmt.executeUpdate(query);
			
			stmt.close();
			connection.close();

		} catch (ClassNotFoundException e) {
			// Could not find the database driver
			System.out.println("###################################");
			System.out.println("ERRORE UPDATE SGP");
			System.out.println("###################################");
			e.printStackTrace();
		} catch (SQLException e) {
			// Could not connect to the database
			System.out.println("###################################");
			System.out.println("ERRORE UPDATE SGP");
			System.out.println("###################################");
			e.printStackTrace();
		}
		return n;

	}
}
