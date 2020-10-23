package senac.estoque.model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import senac.estoque.model.Conexao;
import senac.estoque.model.vo.TipoLancamentoVO;

public class TipoLancamentoDAO {

	/**
	 * listar tipos de lançamentos
	 * @return
	 */
	public ArrayList<TipoLancamentoVO> listar() {
		String sql = "SELECT * FROM tb_tipo_lancamento";
		Connection conn = Conexao.getConnection();
		Statement stmt = Conexao.getStatement(conn);
		ResultSet result = null;
		ArrayList<TipoLancamentoVO> listaTipoLancamento = new ArrayList<TipoLancamentoVO>();
		
		try {
			result = stmt.executeQuery(sql);
			while(result.next()) {
				TipoLancamentoVO tipoLancamento = new TipoLancamentoVO();
				tipoLancamento.setId(Integer.parseInt(result.getString("id")));
				tipoLancamento.setDescricao(result.getString("descricao"));
				listaTipoLancamento.add(tipoLancamento);
			}
		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		} finally {
			Conexao.closeResultSet(result);
			Conexao.closeStatement(stmt);
			Conexao.closeConnection(conn);
		}
		return listaTipoLancamento;
	}
	
	/**
	 * retorna um tipo de lançamento específico
	 * @param id
	 * @return
	 */
	public TipoLancamentoVO encontrar(int id) {
		String sql = "SELECT * FROM tb_tipo_lancamento WHERE id = " + id;
		Connection conn = Conexao.getConnection();
		Statement stmt = Conexao.getStatement(conn);
		ResultSet result = null;
		TipoLancamentoVO tipoLancamento = new TipoLancamentoVO();
		
		try {
			result = stmt.executeQuery(sql);
			while(result.next()) {
				tipoLancamento.setId(Integer.parseInt(result.getString("id")));
				tipoLancamento.setDescricao(result.getString("descricao"));
			}
		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		} finally {
			Conexao.closeResultSet(result);
			Conexao.closeStatement(stmt);
			Conexao.closeConnection(conn);
		}
		return tipoLancamento;
	}
	
	/**
	 * cadastrar novo tipo de lançamento
	 * @param tipoLancamento
	 * @return
	 */
	public int cadastrar(TipoLancamentoVO tipoLancamento) {
		String sql = "INSERT INTO tb_tipo_lancamento VALUES (NULL, "
		+ tipoLancamento.getId() +", "
		+ "'"+ tipoLancamento.getDescricao() +"')";
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
	 * atualiza um tipo de lançamento
	 * @param tipoLancamento
	 * @param id
	 * @return
	 */
	public int atualizar(TipoLancamentoVO tipoLancamento, int id) {
		String sql = "UPDATE tb_tipo_lancamento SET descricao = '"+ tipoLancamento.getDescricao() +"' WHERE id = " + id;
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
	 * excluir um tipo de lançamento
	 * @param id
	 * @return
	 */
	public int excluir(int id) {
		String sql = "DELETE FROM tb_tipo_lancamento WHERE id = " + id;
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
