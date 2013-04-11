package db;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Properties;
import java.util.logging.Logger;

import javax.sql.DataSource;

/**
 * The Class PostgreSqlDataSource.
 */
public class PostgreSqlDataSource implements DataSource {
	
	protected boolean usingThinDriver;
	protected String description = "Oracle Data Source";
	protected String serverName;
	protected int portNumber;
	protected String databaseName;

	public PostgreSqlDataSource(String host, int port, String sid)
	{
		setServerName(host);
		setPortNumber(port);
		setDatabaseName(sid);
		setUsingThinDriver(true);
		
	}
	
	public PostgreSqlDataSource(String sid)
	{
		setDatabaseName(sid);
		setUsingThinDriver(true);
	}

	/**
	 * Gets the description.
	 *
	 * @return the description as String
	 */
	public String getDescription() { return description; }
	
	/**
	 * Gets the server name.
	 *
	 * @return the server name as String
	 */
	public String getServerName() { return serverName; }
	
	/**
	 * Gets the port number.
	 *
	 * @return the port number as int
	 */
	public int getPortNumber() { return portNumber; }
	
	/**
	 * Gets the database name.
	 *
	 * @return the database name as String
	 */
	public String getDatabaseName() { return databaseName; }
	
	/**
	 * Checks if is using thin driver.
	 *
	 * @return true, if is using thin driver
	 */
	public boolean isUsingThinDriver() { return usingThinDriver; }
	
	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) { this.description = description; }
	
	/**
	 * Sets the server name.
	 *
	 * @param serverName the new server name
	 */
	public void setServerName(String serverName) { this.serverName = serverName; }
	
	/**
	 * Sets the port number.
	 *
	 * @param portNumber the new port number
	 */
	public void setPortNumber(int portNumber) { this.portNumber = portNumber; }
	
	/**
	 * Sets the database name.
	 *
	 * @param databaseName the new database name
	 */
	public void setDatabaseName(String databaseName) { this.databaseName = databaseName; }
	
	/**
	 * Sets the using thin driver.
	 *
	 * @param thin the new using thin driver
	 */
	public void setUsingThinDriver(boolean thin) { this.usingThinDriver = thin; }

	/* (non-Javadoc)
	 * @see javax.sql.CommonDataSource#getLogWriter()
	 */
	@Override
	public PrintWriter getLogWriter() throws SQLException {
		return DriverManager.getLogWriter();
	}

	/* (non-Javadoc)
	 * @see javax.sql.CommonDataSource#getLoginTimeout()
	 */
	@Override
	public int getLoginTimeout() throws SQLException {
		return DriverManager.getLoginTimeout();
	}

	/* (non-Javadoc)
	 * @see javax.sql.CommonDataSource#setLogWriter(java.io.PrintWriter)
	 */
	@Override
	public void setLogWriter(PrintWriter out) throws SQLException {
		DriverManager.setLogWriter(out);
	}

	/* (non-Javadoc)
	 * @see javax.sql.CommonDataSource#setLoginTimeout(int)
	 */
	@Override
	public void setLoginTimeout(int seconds) throws SQLException {
		DriverManager.setLoginTimeout(seconds);
	}

	/* (non-Javadoc)
	 * @see java.sql.Wrapper#isWrapperFor(java.lang.Class)
	 */
	@Override
	public boolean isWrapperFor(Class<?> arg0) throws SQLException {
		return false;
	}

	/* (non-Javadoc)
	 * @see java.sql.Wrapper#unwrap(java.lang.Class)
	 */
	@Override
	public <T> T unwrap(Class<T> arg0) throws SQLException {
		return null;
	}

	/* (non-Javadoc)
	 * @see javax.sql.DataSource#getConnection()
	 */
	@Override
	public Connection getConnection() throws SQLException {
			return getConnection(null, null);
	}

	/* (non-Javadoc)
	 * @see javax.sql.DataSource#getConnection(java.lang.String, java.lang.String)
	 */
	@Override
	public Connection getConnection(String username, String password) throws SQLException {
		String url = "jdbc:postgresql://" + getServerName() + "/" + getDatabaseName();
		Properties props = new Properties();
		props.setProperty("user", username);
		props.setProperty("password", password);
		return DriverManager.getConnection(url, props);
	}

	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}


}
