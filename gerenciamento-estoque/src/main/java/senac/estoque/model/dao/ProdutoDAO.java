package senac.estoque.model.dao;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import java.sql.*;

import senac.estoque.model.Conexao;
import senac.estoque.model.dto.ProdutoDTO;
import senac.estoque.model.dto.ProdutoMaisUsadosDTO;
import senac.estoque.model.dto.ProdutoMaisVendidoDTO;
import senac.estoque.model.vo.CategoriaVO;
import senac.estoque.model.vo.LogProdutosVO;
import senac.estoque.model.vo.ProdutoVO;
import senac.estoque.model.vo.SetorVO;
import senac.estoque.seletores.SeletorProduto;

public class ProdutoDAO {

	/**
	 * listar todos os produtos
	 * 
	 * @return
	 */
	public ArrayList<ProdutoVO> listar() {
		String sql = "SELECT * FROM tb_produto WHERE ativo = 0 ORDER BY descricao ASC";
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

	public ArrayList<LogProdutosVO> listaLogProdutos(SeletorProduto seletorProduto){
		
		String sql = "SELECT * FROM vw_produto_log";
		int primeiro = 0;
		
		if(seletorProduto.getMes() > 0 && seletorProduto.getMes() <= 12) {
			if(primeiro == 0) {
				sql = sql.concat(" WHERE ");
				primeiro++;
			} else {
				sql = sql.concat(" AND ");
			}
			sql = sql.concat(" MONTH(data_sql) = " + seletorProduto.getMes());
		}
		
		if(seletorProduto.getAno() > 1500) {
			if(primeiro == 0) {
				sql = sql.concat(" WHERE ");
				primeiro++;
			} else {
				sql = sql.concat(" AND ");
			}
			sql = sql.concat(" YEAR(data_sql) = " + seletorProduto.getAno());
		}
		
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
	public ArrayList<ProdutoDTO> listarView(SeletorProduto seletorProduto) {
		String sql = "SELECT * FROM vw_produto ";
		
		if(seletorProduto.getNomeProduto().trim().length() > 0) {
			sql = sql.concat(" WHERE descricao LIKE '%");
			sql = sql.concat(seletorProduto.getNomeProduto() + "%' ");
		}
		
		sql = sql.concat(" LIMIT " + seletorProduto.getNumeroPorPagina());
		sql = sql.concat(" OFFSET " + seletorProduto.getOffset());
		
		
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
	public boolean encontrarPorNome(String nome) {
		String sql = "SELECT * FROM tb_produto WHERE descricao = '" + nome + "'";

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
	
	public ProdutoVO retornarPorNome(String nome) {
		String sql = "SELECT * FROM tb_produto WHERE descricao = '" + nome + "'";
		Connection conn = Conexao.getConnection();
		Statement stmt = Conexao.getStatement(conn);
		ResultSet result = null;
		ProdutoVO produto = new ProdutoVO();

		try {
			result = stmt.executeQuery(sql);
			while (result.next()) {
				produto.setId(Integer.parseInt(result.getString("id")));
				produto.setDescricao(result.getString("descricao"));
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
	 * verificar se está desativado
	 * @param setor
	 * @return
	 */
	public boolean verificarSeEstaDesativado(ProdutoVO produto) {
		String sql = "SELECT * FROM tb_produto WHERE ativo = 1 AND descricao = '";
		sql = sql.concat(produto.getDescricao() + "'");
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

		Connection conn = Conexao.getConnection();
		Statement stmt = Conexao.getStatement(conn);
		int result = 0;
		
		int id = this.retornarPorNome(produto.getDescricao()).getId();
		produto.setId(id);
		if(this.verificarSeEstaDesativado(produto)) {
			this.ativar(produto);
			return 1;
		}

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
	public boolean atualizar(ProdutoVO produto) {
		String sql = "UPDATE tb_produto SET descricao = '";
		sql = sql.concat(produto.getDescricao());
		sql = sql.concat("' WHERE id = " + produto.getId());
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
		if(result != 0) return true;
		return false;
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
	
	/**
	 * verificar se produto existe no banco
	 * @param id
	 * @return
	 */
	public boolean verificarSeExiste(int id) {
		String sql = "SELECT * FROM tb_produto WHERE id = " + id;
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
	 * verificar se produto existe no banco
	 * @param id
	 * @return
	 */
	public boolean verificarSeExistePorNome(String descricao) {
		String sql = "SELECT * FROM tb_produto WHERE descricao = '" + descricao + "'";
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
	 * desativar um produto no banco de dados
	 * @param id
	 * @return
	 */
	public boolean desativar(int id) {
		String sql = "UPDATE tb_produto SET ativo = 1 WHERE id = " + id;
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
	
	/**
	 * ativar um produto no banco de dados
	 * @param id
	 * @return
	 */
	public boolean ativar(ProdutoVO produto) {
		String sql = "UPDATE tb_produto SET ativo = 0, categoria = "+produto.getCategoria().getId()+", quantidade = "+produto.getQuantidade()+", preco = "+produto.getPreco()+" WHERE id = " + produto.getId();
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
	
	
	/**
	 * retorna lista de produtos mais usados
	 */
	public ArrayList<ProdutoMaisUsadosDTO> listarProdutosMaisUsados() {
		String sql = "select * from vw_produtos_mais_comprados";
		Connection conn = Conexao.getConnection();
		Statement stmt = Conexao.getStatement(conn);
		ResultSet result = null;
		ArrayList<ProdutoMaisUsadosDTO> lista = new ArrayList<ProdutoMaisUsadosDTO>();
		
		try {
			result = stmt.executeQuery(sql);
			while(result.next()) {
				ProdutoMaisUsadosDTO produto = new ProdutoMaisUsadosDTO();
				produto.setDescricao(result.getString("des"));
				produto.setQtd_usados(Integer.parseInt(result.getString("qtd")));
				lista.add(produto);
			}
		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		} finally {
			Conexao.closeResultSet(result);
			Conexao.closeStatement(stmt);
			Conexao.closeConnection(conn);
		}
		return lista;
	}
	
	
	/**
	 * retorna lista de produtos mais vendidos
	 */
	public ArrayList<ProdutoMaisVendidoDTO> listarProdutosMaisVendidos() {
		String sql = "select * from vw_produtos_mais_usados";
		Connection conn = Conexao.getConnection();
		Statement stmt = Conexao.getStatement(conn);
		ResultSet result = null;
		ArrayList<ProdutoMaisVendidoDTO> lista = new ArrayList<ProdutoMaisVendidoDTO>();
		
		try {
			result = stmt.executeQuery(sql);
			while(result.next()) {
				ProdutoMaisVendidoDTO produto = new ProdutoMaisVendidoDTO();
				produto.setDescricao(result.getString("des"));
				produto.setQtd_vendas(Integer.parseInt(result.getString("qtd")));
				lista.add(produto);
			}
		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		} finally {
			Conexao.closeResultSet(result);
			Conexao.closeStatement(stmt);
			Conexao.closeConnection(conn);
		}
		return lista;
	}

	public ArrayList<String> listaAnos(){

		String sql = "SELECT DISTINCT YEAR(data_ultima_entrada) as years FROM `tb_produto` WHERE data_ultima_entrada IS NOT NULL";
		Connection conn = Conexao.getConnection();
		Statement stmt = Conexao.getStatement(conn);
		ResultSet result = null;
		ArrayList<String> listaAnos = new  ArrayList<String>();
		
		try {
			result = stmt.executeQuery(sql);
			while(result.next()) {
				listaAnos.add(result.getString("years"));
			}
		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		} finally {
			Conexao.closeResultSet(result);
			Conexao.closeStatement(stmt);
			Conexao.closeConnection(conn);
		}
		return listaAnos;

	}

}
