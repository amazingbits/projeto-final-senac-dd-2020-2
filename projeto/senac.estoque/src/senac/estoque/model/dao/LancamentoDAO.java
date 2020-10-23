package senac.estoque.model.dao;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import java.sql.*;

import senac.estoque.model.Conexao;
import senac.estoque.model.dto.LancamentoDTO;
import senac.estoque.model.vo.LancamentoVO;

public class LancamentoDAO {
	
	/**
	 * listar todos os lançamentos
	 * @return
	 */
	public ArrayList<LancamentoVO> listar() {
		String sql = "SELECT * FROM tb_lancamento";
		Connection conn = Conexao.getConnection();
		Statement stmt = Conexao.getStatement(conn);
		ResultSet result = null;
		ArrayList<LancamentoVO> listaLancamento = new ArrayList<LancamentoVO>();
		
		try {
			result = stmt.executeQuery(sql);
			while(result.next()) {
				LancamentoVO lancamento = new LancamentoVO();
				lancamento.setId(Integer.parseInt(result.getString("id")));
				lancamento.getProduto().setId(Integer.parseInt(result.getString("idproduto")));
				lancamento.getSetor().setId(Integer.parseInt(result.getString("idsetor")));
				lancamento.getTipo().setId(Integer.parseInt(result.getString("tipo")));
				lancamento.setQuantidade(Integer.parseInt(result.getString("quantidade")));
				lancamento.setPrecoTotal(Float.parseFloat(result.getString("preco_total")));
				lancamento.setData(result.getString("data"));
				listaLancamento.add(lancamento);
			}
		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		} finally {
			Conexao.closeResultSet(result);
			Conexao.closeStatement(stmt);
			Conexao.closeConnection(conn);
		}
		return listaLancamento;
	}
	
	/**
	 * lista todos os lançamentos a partir da view do banco de dados
	 * @return
	 */
	public ArrayList<LancamentoDTO> listarView() {
		String sql = "SELECT * FROM vw_lancamento";
		Connection conn = Conexao.getConnection();
		Statement stmt = Conexao.getStatement(conn);
		ResultSet result = null;
		ArrayList<LancamentoDTO> listaLancamento = new ArrayList<LancamentoDTO>();
		
		try {
			result = stmt.executeQuery(sql);
			while(result.next()) {
				LancamentoDTO lancamento = new LancamentoDTO();
				lancamento.setId(Integer.parseInt(result.getString("codigo")));
				lancamento.setIdproduto(Integer.parseInt(result.getString("codigoproduto")));
				lancamento.setPreco_total(Float.parseFloat(result.getString("preco_total")));
				lancamento.setProduto(result.getString("produto"));
				lancamento.setSetor(result.getString("setor"));
				lancamento.setTipo(result.getString("tipo"));
				lancamento.setQuantidade(Integer.parseInt(result.getString("quantidade")));
				lancamento.setData(result.getString("data"));
				listaLancamento.add(lancamento);
			}
		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		} finally {
			Conexao.closeResultSet(result);
			Conexao.closeStatement(stmt);
			Conexao.closeConnection(conn);
		}
		return listaLancamento;
	}
	
	/**
	 * retorna um lançamento específico
	 * @param id
	 * @return
	 */
	public LancamentoVO encontrar(int id) {
		String sql = "SELECT * FROM tb_lancamento WHERE id = " + id;
		Connection conn = Conexao.getConnection();
		Statement stmt = Conexao.getStatement(conn);
		ResultSet result = null;
		LancamentoVO lancamento = new LancamentoVO();
		
		try {
			result = stmt.executeQuery(sql);
			while(result.next()) {
				lancamento.setId(Integer.parseInt(result.getString("id")));
				lancamento.getProduto().setId(Integer.parseInt(result.getString("idproduto")));
				lancamento.getSetor().setId(Integer.parseInt(result.getString("idsetor")));
				lancamento.getTipo().setId(Integer.parseInt(result.getString("tipo")));
				lancamento.setQuantidade(Integer.parseInt(result.getString("quantidade")));
				lancamento.setPrecoTotal(Float.parseFloat(result.getString("preco_total")));
				lancamento.setData(result.getString("data"));
			}
		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		} finally {
			Conexao.closeResultSet(result);
			Conexao.closeStatement(stmt);
			Conexao.closeConnection(conn);
		}
		return lancamento;
	}
	
	/**
	 * cadastra um novo lançamento
	 * @param lancamento
	 * @return
	 */
	public int cadastrar(LancamentoVO lancamento) {
		String sql = "INSERT INTO tb_lancamento VALUES (NULL, "
					  + lancamento.getProduto().getId() +", "
					  + lancamento.getSetor().getId() +", "
					  + lancamento.getTipo().getId() +", "
					  + lancamento.getQuantidade() +", "
					  + lancamento.getPrecoTotal() +", "
					  + "'"+ lancamento.getData() +"')";
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
	 * atualiza um lançamento
	 * @param lancamento
	 * @param id
	 * @return
	 */
	public int atualizar(LancamentoVO lancamento, int id) {
		String sql = "UPDATE tb_lancamento SET "
				+ "idproduto = " + lancamento.getProduto().getId() + ", "
				+ "idsetor = " + lancamento.getSetor().getId() + ", "
				+ "tipo = " + lancamento.getTipo().getId() + ", "
				+ "quantidade = " + lancamento.getQuantidade() + ", "
				+ "preco_total = " + lancamento.getPrecoTotal() + ", "
				+ "data = '"+ lancamento.getData() +"' "
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
	 * excluir um lançamento
	 * @param id
	 * @return
	 */
	public int excluir(int id) {
		String sql = "DELETE FROM tb_lancamento WHERE id = " + id;
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
