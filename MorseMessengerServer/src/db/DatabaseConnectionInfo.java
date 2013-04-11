/*
 * 
 */
package db;

import java.sql.Connection;
import java.sql.SQLException;


import javax.sql.DataSource;


/**
 * The Class DatabaseConnectionInfo.
 */
public class DatabaseConnectionInfo {
	
	public static String server;
	public static String username;
	public static String password;
	public static String database;
	
	/**
	 * Gets the connection.
	 *
	 * @param params the server, username, password, database as String
	 * @return the connection
	 * @throws InvalidNumberOfArgsException the invalid number of args exception
	 * @throws SQLException the sQL exception
	 */
	public static Connection getConnection(String...params) throws InvalidNumberOfArgsException, SQLException
	{
		if(params.length == 0) {
			server = R.SERVER;
			username = R.DB_USERNAME;
			password = R.DB_PASSWORD;
			database = R.DATABASE;
		}
		else if(params.length > 0 && params.length < 4) {
			throw new InvalidNumberOfArgsException();
		}
		else if(params.length >= 4) {
			server = params[0];
			username = params[1];
			password = params[2];
			database = params[3];
		}
		
		DataSource ds = new PostgreSqlDataSource(server, Integer.parseInt(R.PORT), database);
		return ds.getConnection(username, password);
	}
}
