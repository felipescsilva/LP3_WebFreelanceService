package ec.ftt.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDAO {
	private Connection conexao;
	public ConexaoDAO() {
		String connectionUrl = "jdbc:sqlserver://localhost:1433;"
				+ "databaseName=EC7N22";
		
		Connection conn = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
			
			conn = DriverManager.getConnection(connectionUrl, "sa", "123456");
			conexao = conn;
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
		} catch (Exception e) {
			System.out.println("Não foi possível conectar");
			System.out.println(e.toString());
		}
	}
	
	public void FecharConexao() {
		if(conexao != null){
			try {
				conexao.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public Connection getConexao() {
		return conexao;
	}
}
