package db;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Vector;


/**
 * @author Ghennadi Procopciuc
 */

public class DBConnection {
	private String server;
	private String database;
	private String username;
	private String password;
	private Boolean errors;
	public Boolean multiplesQueries;
	
	public Connection connection;
	public Statement st;
	
	public DBConnection(){
		server   = R.SERVER;
		database = R.DATABASE;
		username = R.USERNAME;
		password = R.PASSWORD;
		errors   = true;
		multiplesQueries = false;
	}
	
	public DBConnection(String server, 
				 String port,
				 String database,
				 String username,
				 String password){
		this.server = server;
		this.database = database;
		this.username = username;
		this.password = password;
		errors = true;
		multiplesQueries = false;
	}
	
	public Boolean connect(){
    	connection = null;
    	st = null;
    
    	try {
			connection = DatabaseConnectionInfo.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
		try {
			st = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
			        					    ResultSet.CONCUR_UPDATABLE);
		} 
		catch (SQLException e) {
			if(errors){
				new Error("Eroare la conexiune.\n\n"
						  + e.getLocalizedMessage());
				e.printStackTrace();
		}
			return false;
		}
		return true;
	}
	
	/**
	 * Does a select operation using 'query'. 
	 * @param query
	 * @param columnsNumber
	 * @return
	 */
	public Vector<Vector<String>> select(String query, Integer columnsNumber){
		if(!multiplesQueries){
			connect();
		}
		
		Vector<Vector<String>> result = new Vector<Vector<String>>();
    	ResultSet rs = null;
		System.out.println(query);
    	try {
			rs = st.executeQuery(query);
		} 
    	catch (SQLException e) {
			if(errors){
				new Warning("Interogare ilegal\u0103.\n\n" + e.getLocalizedMessage());
				e.printStackTrace();
			}
			
			if(!multiplesQueries){
				close();
			}
			return result;
		}
	
    	try {
			while (rs.next()) {
				Vector<String> line = new Vector<String>();
				// Parcurg toate coloanele din raspuns
				for(int i = 1; i <= columnsNumber; i++ ){
					line.add(rs.getString(i));
				}	
				result.add(line);
			}
		} 
    	catch (SQLException e) {
			if(errors){
				new Warning("Eroare la acesarea datelor din baza de date.\n"
							+"Metoda ResultSet.next()\n\n"
							+ e.getLocalizedMessage());
				e.printStackTrace();
			}
		}
    	
    	try {
			rs.close();
		} catch (SQLException e) {
			if(errors){
				new Warning("Eroare la acesarea datelor din baza de date.\n"
							+ "Metoda ResultSet.close()\n\n"
							+ e.getLocalizedMessage());
				e.printStackTrace();
			}
			if(!multiplesQueries){
				close();
			}
			return result;
		}
		
		if(!multiplesQueries){
			close();
		}
		
		return result;
	}
	
	
	public Boolean insert(String tableName, Map<String, String> columnsAndValues){
		String query;
		String columnsString = "(";
		String valuesString = "(";


		// Obtinerea iteratorului peste coloane
		Set<String> keySet = (Set<String>) columnsAndValues.keySet();
		Integer size = keySet.size();
		Iterator<String> it = keySet.iterator();

		// Contorizeaza coloanele parcurse
		Integer counter = 0;

		// Conectare
		if(!multiplesQueries){
			connect();
		}

		// Cat timp mai sunt coloane neparcurse
		while(it.hasNext()){
			String column = it.next();
			String value = columnsAndValues.get(column);

			// Daca nu este ultima coloana din actualizare
			if(counter != size - 1){
				columnsString += (column + ", ");
				valuesString += ("'" + value + "', ");
			}else{
				columnsString += (column + ")");
				valuesString += ("'" + value + "')");
			}

			counter++;
		}	

		query = "INSERT INTO " + 
				tableName + 
				columnsString + 
				" VALUES " + valuesString;

		System.out.println(query);

		try { 
			st.executeUpdate(query);
		} catch (SQLException e) {
			if(errors){
				new Error("Eroare la INSERT.\n\n"
						+ e.getLocalizedMessage());
				e.printStackTrace();
			}
		}


		// Inchidere conexiune
		if(!multiplesQueries){
			close();
		}
		return true;
	}
	
	public Boolean executeQueryInsert(String query) {
		// Conectare
		
		if(!multiplesQueries){
			connect();
		}
		
		ResultSet rs = null;

		try { 
			rs = st.executeQuery(query);
		} catch (SQLException e) {
			if(errors && rs != null) {
				new Error("Eroare la INSERT.\n\n"
						+ e.getLocalizedMessage());
				e.printStackTrace();
			}
		
		// Inchidere conexiune
			if(!multiplesQueries){
			close();
			}
			
			return false;
		}
		
		// Inchidere conexiune
		if(!multiplesQueries){
			close();
		}
		
		return true;
	}
	
	public Boolean update(String tableName, 
					      String primaryKeyValue,
					      TreeMap<String, String> columnsAndValues)
	{
		String update;
		String changes = "";
		String primaryKey = R.getTablePrimaryKeys(tableName).elementAt(0);

		

		// Obtinerea iteratorului peste coloane
		Set<String> keySet = (Set<String>) columnsAndValues.keySet();
		Integer size = keySet.size();
		Iterator<String> it = keySet.iterator();
		
		// Contorizeaza coloanele parcurse
		Integer counter = 0;
		
		// Conectare
		if(!multiplesQueries){
			connect();
		}
		
		// Cat timp mai sunt coloane neparcurse
		while(it.hasNext()){
			String column = it.next();
			String value = columnsAndValues.get(column);
			
			// Daca nu este ultima coloana din actualizare
			if(counter != size - 1){
				changes += (column + "='" + value + "', ");
			}else{
				changes += (column + "='" + value + "' ");
			}
			
			counter++;
		}	
		
		update = "UPDATE " + tableName + 
				 " SET " + changes + 
				 " WHERE " + primaryKey + "='" + primaryKeyValue + "'";
				
		// Executie actualizare
    	try {
			st.executeUpdate(update);
		} catch (SQLException e) {
			if(errors){
				new Error("Eroare la UPDATE.\n\n"
						  + e.getLocalizedMessage());
				e.printStackTrace();
			}
			// Inchidere conexiune
			if(!multiplesQueries){
				close();
			}
			
			return false;
		} 
		
		// Inchidere conexiune
		if(!multiplesQueries){
			close();
		}
		
		return true;
	}
	
	// Stergerea are loc in functie de coloana primaryKey si valorile acesteia
	public Boolean delete(String tableName, Vector <String> primaryKeyValues){
		String update;
		String changes = "";

		String primaryKey = R.getTablePrimaryKeys(tableName).elementAt(0);
		

		// Conectare
		if(!multiplesQueries){
			connect();
		}
		
		Integer size = primaryKeyValues.size();
		for(int i = 0; i < size; i++){
			// Daca nu este ultima coloana din actualizare
			if(i != size - 1){
				changes += (primaryKey + "='" + primaryKeyValues.get(i) + "' OR ");
			}else{
				changes += (primaryKey + "='" + primaryKeyValues.get(i) + "' ");
			}
		}
		
		update = "DELETE FROM " +
				tableName +
				" WHERE " +
				changes;
		
		System.out.println(update);
		
		// Executie actualizare
		try {
			st.executeUpdate(update);
		} catch (SQLException e) {
			if(errors){
				new Error("Eroare la DELETE.\n\n"
						+ e.getLocalizedMessage());
				e.printStackTrace();
			}
			
			// Inchidere conexiune
			if(!multiplesQueries){
				close();
			}

			return false;
		} 
		
		// Inchidere conexiune
		if(!multiplesQueries){
			close();
		}

		return true;
	}
	
	
	public Boolean close(){
		try {
			st.close();
		} catch (SQLException e) {
			if(errors){
				new Warning("Eroare la acesarea datelor din baza de date.\n"
							+ "Metoda Statement.close()\n\n"
							+ e.getLocalizedMessage());
			}
			e.printStackTrace();
			return false;
		}

		// Inchiderea conexiunii
    	try {
			connection.close();
		} catch (SQLException e) {
			if(errors){
				new Warning("Eroare la acesarea datelor din baza de date.\n"
						+ "Metoda Connection.close()\n\n"
						+ e.getLocalizedMessage());
				e.printStackTrace();
			}
			return false;
		}
		
		return true;
	}
	
	/**
	 * Returns a PreparedStatement containing the "query" query.
	 * @param s
	 * @return
	 */
	public PreparedStatement prepareStatement(String query) {
		PreparedStatement ps = null;
		try {
			ps = this.connection.prepareStatement(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ps;
	}
	
	/**
	 * Returns a Statement.
	 * @return
	 * @throws SQLException
	 */
	public Statement createStatement() 
			throws SQLException 
	{
		return this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
												   ResultSet.CONCUR_UPDATABLE);
	}
	
	public void setErrors(Boolean errors){
		this.errors = errors;
	}
	
	public void setMultiplesQueries(){
		multiplesQueries = true;
	}
}
