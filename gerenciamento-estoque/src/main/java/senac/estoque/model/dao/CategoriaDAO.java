package senac.estoque.model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import senac.estoque.model.Conexao;
import senac.estoque.model.vo.CategoriaVO;

public class CategoriaDAO {
	
	/**
	 * listar categorias
	 * @return
	 */
	public ArrayList<CategoriaVO> listar() {
		String sql = "SELECT * FROM tb_categoria";
		Connection conn = Conexao.getConnection();
		Statement stmt = Conexao.getStatement(conn);
		ResultSet result = null;
		ArrayList<CategoriaVO> listaCategoria = new ArrayList<CategoriaVO>();
		
		try {
			result = stmt.executeQuery(sql);
			while(result.next()) {
				CategoriaVO categoria = new CategoriaVO();
				categoria.setId(Integer.parseInt(result.getString("id")));
				categoria.setDescricao(result.getString("descricao"));
				listaCategoria.add(categoria);
			}
		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		} finally {
			Conexao.closeResultSet(result);
			Conexao.closeStatement(stmt);
			Conexao.closeConnection(conn);
		}
		return listaCategoria;
	}
	
	/**
	 * retorna uma categoria espec�fica
	 * @param id
	 * @return
	 */
	public CategoriaVO encontrar(int id) {
		String sql = "SELECT * FROM tb_categoria WHERE id = " + id;
		Connection conn = Conexao.getConnection();
		Statement stmt = Conexao.getStatement(conn);
		ResultSet result = null;
		CategoriaVO categoria = new CategoriaVO();
		
		try {
			result = stmt.executeQuery(sql);
			while(result.next()) {
				categoria.setId(Integer.parseInt(result.getString("id")));
				categoria.setDescricao(result.getString("descricao"));
			}
		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		} finally {
			Conexao.closeResultSet(result);
			Conexao.closeStatement(stmt);
			Conexao.closeConnection(conn);
		}
		return categoria;
	}
	
	/**
	 * encontrar categoria pelo nome
	 * @param nome
	 * @return
	 */
	public boolean encontrarCategoriaPeloNome(String nome) {
		String sql = "SELECT * FROM tb_categoria WHERE descricao = '"+ nome +"'";
		Connection conn = Conexao.getConnection();
		Statement stmt = Conexao.getStatement(conn);
		ResultSet result = null;
		int contador = 0;
		
		try {
			result = stmt.executeQuery(sql);
			while(result.next()) {
				contador++;
			}
		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		} finally {
			Conexao.closeResultSet(result);
			Conexao.closeStatement(stmt);
			Conexao.closeConnection(conn);
		}
		if(contador > 0) return true;
		return false;
	}
	
	/**
	 * cadastrar nova categoria
	 * @param categoria
	 * @return
	 */
	public int cadastrar(CategoriaVO categoria) {
		String sql = "INSERT INTO tb_categoria VALUES (NULL, "
		+ "'"+ categoria.getDescricao() +"')";
		Connection conn = Conexao.getConnection();
		Statement stmt = Conexao.getStatement(conn);
		int result = 0;
		
		try {
			result = stmt.executeUpdate(sql);
		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		} finally {
			Conexao.closeStatement(stmt);
			Conexao.closeConnection(conn);
		}
		return result;
	}
	
	/**
	 * atualiza uma categoria
	 * @param categoria
	 * @param id
	 * @return
	 */
	public int atualizar(CategoriaVO categoria, int id) {
		String sql = "UPDATE tb_categoria SET descricao = '"+ categoria.getDescricao() +"' WHERE id = " + id;
		Connection conn = Conexao.getConnection();
		Statement stmt = Conexao.getStatement(conn);
		int result = 0;
		
		try {
			result = stmt.executeUpdate(sql);
		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		} finally {
			Conexao.closeStatement(stmt);
			Conexao.closeConnection(conn);
		}
		return result;
	}
	
	/**
	 * excluir uma categoria
	 * @param id
	 * @return
	 */
	public int excluir(int id) {
		String sql = "DELETE FROM tb_categoria WHERE id = " + id;
		Connection conn = Conexao.getConnection();
		Statement stmt = Conexao.getStatement(conn);
		int result = 0;
		
		try {
			result = stmt.executeUpdate(sql);
		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		} finally {
			Conexao.closeStatement(stmt);
			Conexao.closeConnection(conn);
		}
		return result;
	}

}
