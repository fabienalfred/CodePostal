package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DaoVille {
	private String user = "root";
	private String password = "";
	private String url = "jdbc:mysql:///france";
	private String driver = "com.mysql.jdbc.Driver";
	
	private Connection connection;
	
	public DaoVille() throws ClassNotFoundException {
		//Class.forName(driver);
	}

	public ResultSet getVillesByCodePostal(String cp) throws SQLException {
		openConnection();
		String sql = "SELECT * FROM villes WHERE code_postal LIKE ?";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, cp+"%");
		return ps.executeQuery();
	}

	private void openConnection() throws SQLException {
		connection = DriverManager.getConnection(url, user, password);	
	}

	public void close() throws SQLException {
		if(connection!=null)
			connection.close();
		connection = null;
	}
	
}
