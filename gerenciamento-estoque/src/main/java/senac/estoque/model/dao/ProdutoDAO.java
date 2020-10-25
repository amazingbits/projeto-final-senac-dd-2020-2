package senac.estoque.model.dao;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import java.sql.*;

import senac.estoque.model.Conexao;
import senac.estoque.model.dto.ProdutoDTO;
import senac.estoque.model.dto.ProdutoMaisVendidoDTO;
import senac.estoque.model.vo.CategoriaVO;
import senac.estoque.model.vo.LogProdutosVO;
import senac.estoque.model.vo.ProdutoVO;

public class ProdutoDAO {

	/**
	 * listar todos os produtos
	 * 
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
			while (result.next()) {
				ProdutoVO produto = new ProdutoVO();
				CategoriaVO categoria = new CategoriaVO();

				produto.setId(Integer.parseInt(result.getString("id")));
				produto.setDescricao(result.getString("descricao"));
				categoria.setId(Integer.parseInt(result.getString("categoria")));
				produto.setCategoria(categoria);
				produto.setQuantidade(Integer.parseInt(result.getString("quantidade")));
				produto.setPreco(Float.parseFloat(result.getString("preco")));
				produto.setData_ultima_entrada(result.getString("data_ultima_entrada"));
				produto.setData_ultima_saida(result.getString("data_ultima_saida"));

				listaProduto.add(produto);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		} finally {
			Conexao.closeResultSet(result);
			Conexao.closeStatement(stmt);
			Conexao.closeConnection(conn);
		}
		return listaProduto;
	}


	/**
	 * listar todos os produtos mais vendidos
	 * 
	 * @return
	 */
	public ArrayList<ProdutoMaisVendidoDTO> listarMaisVendidos() {
		String sql = "SELECT * FROM vw_produtos_mais_comprados";
		Connection conn = Conexao.getConnection();
		Statement stmt = Conexao.getStatement(conn);
		ResultSet result = null;
		ArrayList<ProdutoMaisVendidoDTO> listaProdutoMaisVendidos = new ArrayList<ProdutoMaisVendidoDTO>();
		
		try {
			result = stmt.executeQuery(sql);
			while(result.next()) {
				
				ProdutoMaisVendidoDTO produtoMaisVendidoDTO = new ProdutoMaisVendidoDTO();

				produtoMaisVendidoDTO.setQtd_vendas(Integer.parseInt(result.getString("qtd_vendas")));
				produtoMaisVendidoDTO.setDescricao(result.getString("descricao"));

				listaProdutoMaisVendidos.add(produtoMaisVendidoDTO);
			}
		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		} finally {
			Conexao.closeResultSet(result);
			Conexao.closeStatement(stmt);
			Conexao.closeConnection(conn);
		}
		return listaProdutoMaisVendidos;
	}

	public ArrayList<LogProdutosVO> listaLogProdutos(){
		
		String sql = "SELECT * FROM vw_produto_log";
		Connection conn = Conexao.getConnection();
		Statement stmt = Conexao.getStatement(conn);
		ResultSet result = null;
		ArrayList<LogProdutosVO> listaLogProdutosVO = new ArrayList<LogProdutosVO>();
		
		try {
			result = stmt.executeQuery(sql);
			while(result.next()) {
				
				LogProdutosVO logProdutos = new LogProdutosVO();

				logProdutos.setProduto(result.getString("produto"));
				logProdutos.setOperacao(result.getString("operacao"));
				logProdutos.setQuantidade(Integer.parseInt(result.getString("quantidade")));
				logProdutos.setData(result.getString("data"));

				listaLogProdutosVO.add(logProdutos);
			}
		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		} finally {
			Conexao.closeResultSet(result);
			Conexao.closeStatement(stmt);
			Conexao.closeConnection(conn);
		}
		return listaLogProdutosVO;
	}
	/**
	 * listagem de produtos a partir da view do banco de dados
	 * 
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
			while (result.next()) {
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
		} catch (SQLException e) {
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
	 * 
	 * @param id
	 * @return
	 */
	public ProdutoVO encontrar(int id) {
		String sql = "SELECT * FROM tb_produto WHERE id = " + id;
		Connection conn = Conexao.getConnection();
		Statement stmt = Conexao.getStatement(conn);
		ResultSet result = null;
		ProdutoVO produto = new ProdutoVO();
		CategoriaVO categoria = new CategoriaVO();

		try {
			result = stmt.executeQuery(sql);
			while (result.next()) {
				produto.setId(Integer.parseInt(result.getString("id")));
				produto.setDescricao(result.getString("descricao"));

				categoria.setId(Integer.parseInt(result.getString("categoria")));
				produto.setCategoria(categoria);
				produto.setQuantidade(Integer.parseInt(result.getString("quantidade")));
				produto.setPreco(Float.parseFloat(result.getString("preco")));
				produto.setData_ultima_entrada(result.getString("data_ultima_entrada"));
				produto.setData_ultima_saida(result.getString("data_ultima_saida"));
			}
		} catch (SQLException e) {
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
	 * 
	 * @param nome
	 * @return null ou ProdutoVO
	 */
	public ProdutoVO encontrar(String nome) {
		String sql = "SELECT * FROM tb_produto WHERE descricao = '" + nome + "'";

		Connection conn = Conexao.getConnection();
		Statement stmt = Conexao.getStatement(conn);
		ResultSet result = null;
		ProdutoVO produto = null;

		try {
			result = stmt.executeQuery(sql);
			while (result.next()) {
				produto = new ProdutoVO();
				CategoriaVO categoria = new CategoriaVO();

				produto.setId(Integer.parseInt(result.getString("id")));
				produto.setDescricao(result.getString("descricao"));
				categoria.setId(Integer.parseInt(result.getString("categoria")));
				produto.setCategoria(categoria);
				produto.setQuantidade(Integer.parseInt(result.getString("quantidade")));
				produto.setPreco(Float.parseFloat(result.getString("preco")));
				produto.setData_ultima_entrada(result.getString("data_ultima_entrada"));
				produto.setData_ultima_saida(result.getString("data_ultima_saida"));
			}
		} catch (SQLException e) {
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
	 * 
	 * @param produto
	 * @return
	 */
	public int cadastrar(ProdutoVO produto) {
		String sql = "INSERT INTO tb_produto (id,descricao,categoria,quantidade,preco,data_ultima_entrada,data_ultima_saida) VALUES (NULL,"
				+ "'" + produto.getDescricao() + "', " + produto.getCategoria().getId() + ", " + produto.getQuantidade()
				+ ", " + produto.getPreco() + ", " + "" + produto.getData_ultima_entrada() + ", " + ""
				+ produto.getData_ultima_saida() + ")";

		System.out.println(sql);
		Connection conn = Conexao.getConnection();
		Statement stmt = Conexao.getStatement(conn);
		int result = 0;

		try {
			result = stmt.executeUpdate(sql);
		} catch (SQLException e) {

			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		} finally {

			Conexao.closeStatement(stmt);
			Conexao.closeConnection(conn);
		}
		return result;
	}

	/**
	 * atualizar um produto
	 * 
	 * @param produto
	 * @param id
	 * @return
	 */
	public int atualizar(ProdutoVO produto, int id) {
		String sql = "UPDATE tb_produto SET " + "descricao = '" + produto.getDescricao() + "', " + "categoria = "
				+ produto.getCategoria().getId() + ", " + "quantidade = " + produto.getQuantidade() + ", " + "preco = "
				+ produto.getPreco() + ", " + "data_ultima_entrada = '" + produto.getData_ultima_entrada() + "', "
				+ "data_ultima_saida = '" + produto.getData_ultima_saida() + "' " + "WHERE id = " + id;
		Connection conn = Conexao.getConnection();
		Statement stmt = Conexao.getStatement(conn);
		int result = 0;

		try {
			result = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		} finally {
			Conexao.closeStatement(stmt);
			Conexao.closeConnection(conn);
		}
		return result;
	}

	/**
	 * exclui um produto
	 * 
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
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		} finally {
			Conexao.closeStatement(stmt);
			Conexao.closeConnection(conn);
		}
		return result;
	}

}
