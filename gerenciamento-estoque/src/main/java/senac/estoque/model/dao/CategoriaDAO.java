package senac.estoque.model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import senac.estoque.model.Conexao;
import senac.estoque.model.vo.CategoriaVO;
import senac.estoque.seletores.SeletorCategoria;

public class CategoriaDAO {
	
	/**
	 * listar categorias
	 * @return
	 */
	public ArrayList<CategoriaVO> listar() {
		String sql = "SELECT * FROM tb_categoria WHERE ativo = 0 ORDER BY descricao ASC";
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
	 * retorna categoria pelo nome
	 * @param categoria
	 * @return
	 */
	public CategoriaVO retornarPeloNome(CategoriaVO categoria) {
		String sql = "SELECT * FROM tb_categoria WHERE descricao = '"+ categoria.getDescricao() +"' LIMIT 1";
		Connection conn = Conexao.getConnection();
		Statement stmt = Conexao.getStatement(conn);
		ResultSet result = null;
		
		try {
			result = stmt.executeQuery(sql);
			while(result.next()) {
				categoria.setId(Integer.parseInt(result.getString("id")));
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
	 * verificar se está desativado
	 * @param setor
	 * @return
	 */
	public boolean verificarSeEstaDesativado(CategoriaVO categoria) {
		String sql = "SELECT * FROM tb_categoria WHERE ativo = 1 AND descricao = '";
		sql = sql.concat(categoria.getDescricao() + "'");
		Connection conn = Conexao.getConnection();
		Statement stmt = Conexao.getStatement(conn);
		ResultSet result = null;
		int contador = 0;

		try {
			result = stmt.executeQuery(sql);
			while (result.next()) {
				contador++;
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		} finally {
			Conexao.closeResultSet(result);
			Conexao.closeStatement(stmt);
			Conexao.closeConnection(conn);
		}
		if(contador == 0) return false;
		return true;
	}
	
	/**
	 * cadastrar nova categoria
	 * @param categoria
	 * @return
	 */
	public int cadastrar(CategoriaVO categoria) {
		String sql = "INSERT INTO tb_categoria (descricao) VALUES ('";
		sql = sql.concat(categoria.getDescricao());
		sql = sql.concat("')");
		
		Connection conn = Conexao.getConnection();
		Statement stmt = Conexao.getStatement(conn);
		int result = 0;
		
		int id = this.retornarPeloNome(categoria).getId();
		categoria.setId(id);
		if(this.verificarSeEstaDesativado(categoria)) {
			this.ativar(categoria);
			return 1;
		}
		
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
		//String sql = "UPDATE tb_categoria SET descricao = '"+ categoria.getDescricao() +"' WHERE id = " + id;
		String sql = "UPDATE tb_categoria SET descricao = '";
		sql = sql.concat(categoria.getDescricao());
		sql = sql.concat("' WHERE id = ");
		sql = sql.concat(""+id);
		
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
	
	/**
	 * exclusão lógica no banco de dados
	 * @param id
	 * @return
	 */
	public boolean exclusaoLogica(int id) {
		String sql = "UPDATE tb_categoria SET ativo = 1 WHERE id = " + id;
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
		if(result == 0) return false;
		return true;
	}
	
	
	public boolean ativar(CategoriaVO categoria) {
		String sql = "UPDATE tb_categoria SET ativo = 0 WHERE id = " + categoria.getId();
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
		if(result == 0) return false;
		return true;
	}

	public ArrayList<CategoriaVO> listarView(SeletorCategoria seletorCategoria) {

		String sql = "SELECT * FROM tb_categoria WHERE ativo = 0 ";

		if(seletorCategoria.getNomeCategoria().trim().length() > 0) {
			sql = sql.concat(" AND descricao LIKE '%");
			sql = sql.concat(seletorCategoria.getNomeCategoria() + "%' ");
		}

		sql = sql.concat(" ORDER BY id DESC ");
		
		sql = sql.concat(" LIMIT " + seletorCategoria.getNumeroPorPagina());
		sql = sql.concat(" OFFSET " + seletorCategoria.getOffset());
		
		Connection conn = Conexao.getConnection();
		Statement stmt = Conexao.getStatement(conn);
		ResultSet result = null;
		ArrayList<CategoriaVO> listaCategoria = new ArrayList<CategoriaVO>();

		try {
			result = stmt.executeQuery(sql);
			while (result.next()) {
				CategoriaVO categoria = new CategoriaVO();
				categoria.setId(Integer.parseInt(result.getString("id")));
				categoria.setDescricao(result.getString("descricao"));
				
				listaCategoria.add(categoria);
				
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		} finally {
			Conexao.closeResultSet(result);
			Conexao.closeStatement(stmt);
			Conexao.closeConnection(conn);
		}
		return listaCategoria;
	}

}
