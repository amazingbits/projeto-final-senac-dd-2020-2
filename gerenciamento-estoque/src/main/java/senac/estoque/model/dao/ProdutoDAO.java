package senac.estoque.model.dao;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import java.sql.*;

import senac.estoque.model.Conexao;
import senac.estoque.model.dto.ProdutoDTO;
import senac.estoque.model.vo.ProdutoVO;

public class ProdutoDAO {
	
	/**
	 * listar todos os produtos
	 * @return
	 */
	public ArrayList<ProdutoVO> listar() {
		String sql = "SELECT * FROM tb_produto";
		Connection conn = Conexao.getConnection();
		Statement stmt = Conexao.getStatement(conn);
		ResultSet result = null;
		ArrayList<ProdutoVO> listaProduto = new ArrayList<ProdutoVO>();
		
		try {
			result = stmt.executeQuery(sql);
			while(result.next()) {
				ProdutoVO produto = new ProdutoVO();
				produto.setId(Integer.parseInt(result.getString("id")));
				produto.setDescricao(result.getString("descricao"));
				produto.getCategoria().setId(Integer.parseInt(result.getString("categoria")));
				produto.setQuantidade(Integer.parseInt(result.getString("quantidade")));
				produto.setPreco(Float.parseFloat(result.getString("preco")));
				produto.setData_ultima_entrada(result.getString("data_ultima_entrada"));
				produto.setData_ultima_saida(result.getString("data_ultima_saida"));

				listaProduto.add(produto);
			}
		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		} finally {
			Conexao.closeResultSet(result);
			Conexao.closeStatement(stmt);
			Conexao.closeConnection(conn);
		}
		return listaProduto;
	}
	
	/**
	 * listagem de produtos a partir da view do banco de dados
	 * @return
	 */
	public ArrayList<ProdutoDTO> listarView() {
		String sql = "SELECT * FROM vw_produto";
		Connection conn = Conexao.getConnection();
		Statement stmt = Conexao.getStatement(conn);
		ResultSet result = null;
		ArrayList<ProdutoDTO> listaProduto = new ArrayList<ProdutoDTO>();
		
		try {
			result = stmt.executeQuery(sql);
			while(result.next()) {
				ProdutoDTO produto = new ProdutoDTO();
				produto.setId(Integer.parseInt(result.getString("codigo")));
				produto.setDescricao(result.getString("descricao"));
				produto.setPreco(Float.parseFloat(result.getString("preco")));
				produto.setCategoria(result.getString("categoria"));
				produto.setQuantidade(Integer.parseInt(result.getString("quantidade")));
				produto.setUltimaEntrada(result.getString("ultima_entrada"));
				produto.setUltimaSaida(result.getString("ultima_saida"));
				listaProduto.add(produto);
			}
		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		} finally {
			Conexao.closeResultSet(result);
			Conexao.closeStatement(stmt);
			Conexao.closeConnection(conn);
		}
		return listaProduto;
	}
	
	/**
	 * listar um produto espec�fico
	 * @param id
	 * @return
	 */
	public ProdutoVO encontrar(int id) {
		String sql = "SELECT * FROM tb_produto WHERE id = " + id;
		Connection conn = Conexao.getConnection();
		Statement stmt = Conexao.getStatement(conn);
		ResultSet result = null;
		ProdutoVO produto = new ProdutoVO();
		
		try {
			result = stmt.executeQuery(sql);
			while(result.next()) {
				produto.setId(Integer.parseInt(result.getString("id")));
				produto.setDescricao(result.getString("descricao"));
				produto.getCategoria().setId(Integer.parseInt(result.getString("categoria")));
				produto.setQuantidade(Integer.parseInt(result.getString("quantidade")));
				produto.setPreco(Float.parseFloat(result.getString("preco")));
				produto.setData_ultima_entrada(result.getString("data_ultima_entrada"));
				produto.setData_ultima_saida(result.getString("data_ultima_saida"));
			}
		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		} finally {
			Conexao.closeResultSet(result);
			Conexao.closeStatement(stmt);
			Conexao.closeConnection(conn);
		}
		return produto;
	}

	/**
	 * listar um produto especifico
	 * @param nome
	 * @return null ou ProdutoVO
	 */
	public ProdutoVO encontrar(String nome) {
		String sql = "SELECT * FROM tb_produto WHERE ";
		
		sql.concat("descricao = '" + nome + "'");
		Connection conn = Conexao.getConnection();
		Statement stmt = Conexao.getStatement(conn);
		ResultSet result = null;
		ProdutoVO produto = null;
		
		try {
			result = stmt.executeQuery(sql);
			while(result.next()) {
				produto = new ProdutoVO();
				produto.setId(Integer.parseInt(result.getString("id")));
				produto.setDescricao(result.getString("descricao"));
				produto.getCategoria().setId(Integer.parseInt(result.getString("categoria")));
				produto.setQuantidade(Integer.parseInt(result.getString("quantidade")));
				produto.setPreco(Float.parseFloat(result.getString("preco")));
				produto.setData_ultima_entrada(result.getString("data_ultima_entrada"));
				produto.setData_ultima_saida(result.getString("data_ultima_saida"));
			}
		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		} finally {
			Conexao.closeResultSet(result);
			Conexao.closeStatement(stmt);
			Conexao.closeConnection(conn);
		}
		return produto;
	}
	
	/**
	 * cadastra um novo produto
	 * @param produto
	 * @return
	 */
	public int cadastrar(ProdutoVO produto) {
		String sql = "INSERT INTO tb_produto VALUES (NULL, "
		+ "'"+ produto.getDescricao() +"', "
		+ produto.getCategoria().getId() +", "
		+ produto.getQuantidade() +", "
		+ produto.getPreco() +", "
		+ "'"+ produto.getData_ultima_entrada() +"', "
		+ "'"+ produto.getData_ultima_saida() +"')";
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
	 * atualizar um produto
	 * @param produto
	 * @param id
	 * @return
	 */
	public int atualizar(ProdutoVO produto, int id) {
		String sql = "UPDATE tb_produto SET "
					  + "descricao = '"+ produto.getDescricao() +"', "
					  + "categoria = " + produto.getCategoria().getId() + ", "
					  + "quantidade = " + produto.getQuantidade() + ", "
					  + "preco = " + produto.getPreco() + ", "
					  + "data_ultima_entrada = '"+ produto.getData_ultima_entrada() +"', "
					  + "data_ultima_saida = '"+ produto.getData_ultima_saida() +"' "
					  + "WHERE id = " + id;
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
	 * exclui um produto
	 * @param id
	 * @return
	 */
	public int excluir(int id) {
		String sql = "DELETE FROM tb_produto WHERE id = " + id;
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
