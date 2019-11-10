package principale;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Properties;

import DBACCESS.DAOesecutore;

import utility.Read;

public class lancia {

	public lancia() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub

		Properties conf = new Properties();
		conf.load(new FileInputStream("properties.conf"));
		String url=conf.getProperty("url");
		String user=conf.getProperty("user");
		String pw=conf.getProperty("pw");
		
		System.out.println("Benvenuto, inserire 0 e invio per uscire, help per aiuto.");
	    System.out.print("Inserisci la query: ");
	    String q = Read.readString();
	    System.out.println();
		
	    while(!q.equals("0")){
	    	
	    	if(q.toLowerCase().startsWith("select")){
	    		LinkedList risultati=DAOesecutore.esegui_query(url, user, pw, q);
	    	
	    		Iterator i1=risultati.iterator();
		    	while(i1.hasNext()){
		    		LinkedList record=(LinkedList)i1.next();
		    		
		    		Iterator i2=record.iterator();
		    		while(i2.hasNext()){
		    			System.out.print(i2.next()+"  ;  ");
		    		}
		    		System.out.println();
		    		System.out.println("------------------------------------------------------------------------");
		    	}
	    	}
	    	else if(q.toLowerCase().startsWith("update")){
	    		int n=DAOesecutore.esegui_update(url, user, pw, q);
	    		System.out.println();
	    		System.out.println(n+" row/s updated");
	    		System.out.println();
	    	}
	    	else if(q.toLowerCase().startsWith("insert")){
	    		int n=DAOesecutore.esegui_update(url, user, pw, q);
	    		System.out.println();
	    		System.out.println(n+" row/s inserted");
	    		System.out.println();
	    	}
	    	else if(q.toLowerCase().startsWith("delete")){
	    		int n=DAOesecutore.esegui_update(url, user, pw, q);
	    		System.out.println();
	    		System.out.println(n+" row/s deleted");
	    		System.out.println();
	    	}
	    	else if(q.toLowerCase().equals("help")){
	    		System.out.println();
	    		System.out.println("SELECT table_name FROM all_tables : Visualizza tutte le tabelle");
	    		System.out.println("SELECT COLUMN_NAME,DATA_TYPE,DATA_LENGTH,NULLABLE FROM user_tab_cols WHERE table_name = 'WMG_USER_ATTRIBUTE' order by COLUMN_ID : Visualizza le colonne della tabella");
	    		System.out.println("select * from WMG_USER_ATTRIBUTE where userid='stefano.eutizi' : Visualizza i record relativi all'utente indicato");
	    		System.out.println("select USERID,NAME,DATE '1970-01-01'+( 1/24/60/60/1000)*VALUE as LAST_LOGIN from WMG_USER_ATTRIBUTE where userid='stefano.eutizi' and NAME='user:last:login'");
	    		System.out.println("update WMG_USER_ATTRIBUTE set VALUE='[valore]' where userid='stefano.eutizi' and NAME='user:last:login'");
	    		System.out.println("select (to_date('11-05-2013 10:00:00','MM-DD-YYYY HH24:Mi:SS')- to_date('1-1-1970 00:00:00','MM-DD-YYYY HH24:Mi:SS'))*24*3600000 from DUAL : Converte in epoch");
	    		System.out.println("update WMG_USER_ATTRIBUTE set VALUE=(to_date('11-05-2013 10:00:00','MM-DD-YYYY HH24:Mi:SS')- to_date('1-1-1970 00:00:00','MM-DD-YYYY HH24:Mi:SS'))*24*3600000 where userid='stefano.eutizi' and NAME='user:last:login'");
	    		System.out.println("help : Mostra questa guida");
	    		System.out.println("0 : Esci");
	    		System.out.println();
	    	}
	    	
	    	
	    	
	    	
	    	System.out.println();
	    	System.out.print("Inserisci la query: ");
	 	    q = Read.readString();
	    }
	    	
	    System.out.println();
    	System.out.print("Ciao Fernande'!");
    	System.out.println();
    	System.out.println();
	}

}
