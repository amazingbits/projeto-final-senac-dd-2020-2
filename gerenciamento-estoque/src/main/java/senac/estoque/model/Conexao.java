package senac.estoque.model;

import java.sql.*;

public class Conexao {
	
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String BANCODADOS = "db_estoque";
	private static final String CONEXAO = "jdbc:mysql://localhost:3306/" + BANCODADOS + "?useTimezone=true&serverTimezone=UTC";
	private static final String USER = "root";
	private static final String PASSWORD = "root";
	
	public static Connection getConnection() {
		try {
			Connection conn = null;
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(CONEXAO, USER, PASSWORD);
			return conn;
		}catch(ClassNotFoundException e) {
			System.out.println("Classe do driver n�o foi encontrada");
			System.out.println("Erro: " + e.getMessage());
			return null;
		}catch(SQLException e) {
			System.out.println("Erro ao obter conex�o");
			System.out.println("Erro: " + e.getMessage());
			return null;
		}
	}

	public static void closeConnection(Connection conn) {
		try {
			if(conn != null) {
				conn.close();
			}
		}catch(SQLException e) {
			System.out.println("Problema no fechamento da conex�o");
			System.out.println("Erro: " + e.getMessage());
		}
	}
	
	public static Statement getStatement(Connection conn) {
		try {
			Statement stmt = conn.createStatement();
			return stmt;
		}catch(SQLException e) {
			System.out.println("Erro ao obter Statement");
			System.out.println("Erro: " + e.getMessage());
			return null;
		}
	}
	
	public static void closeStatement(Statement stmt) {
		try {
			if(stmt != null) {
				stmt.close();
			}
		}catch(SQLException e) {
			System.out.println("Erro ao fechar Statement");
			System.out.println("Erro: " + e.getMessage());
		}
	}
	
	public static PreparedStatement getPreparedStatement(Connection conn, String sql) {
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			return stmt;
		}catch(Exception e) {
			System.out.println("Erro ao obter prepared statement");
			System.out.println("Erro: " + e.getMessage());
			return null;
		}
	}
	
	public static void closePreparedStatement(Statement stmt) {
		try {
			if(stmt != null) {
				stmt.close();
			}
		}catch(SQLException e) {
			System.out.println("Problema no fechamento da prepared statement");
			System.out.println("Erro: " + e.getMessage());
		}
	}
	
	public static void closeResultSet(ResultSet result) {
		try {
			if(result != null) {
				result.close();
			}
		}catch(SQLException e) {
			System.out.println("Problema com fechamento de resultset");
			System.out.println("Erro: " + e.getMessage());
		}
	}

}