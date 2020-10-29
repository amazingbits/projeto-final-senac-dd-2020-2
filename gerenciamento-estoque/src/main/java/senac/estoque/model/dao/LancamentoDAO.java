package senac.estoque.model.dao;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import java.sql.*;

import senac.estoque.helpers.Constantes;
import senac.estoque.model.Conexao;
import senac.estoque.model.dto.LancamentoDTO;
import senac.estoque.model.vo.LancamentoVO;
import senac.estoque.model.vo.LogLancamentosVO;
import senac.estoque.seletores.SeletorLancamento;

public class LancamentoDAO {
	
	/**
	 * listar todos os lan�amentos
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
	 * listar todos os lan�amentos
	 * @return
	 */
	public ArrayList<LogLancamentosVO> listarLogLancamentos(SeletorLancamento seletorLancamento) {
		String sql = "SELECT * FROM vw_lancamento_log";
		int primeiro = 0;
		
		if(seletorLancamento.getMes() > 0 && seletorLancamento.getMes() <= 12) {
			if(primeiro == 0) {
				sql = sql.concat(" WHERE ");
				primeiro++;
			} else {
				sql = sql.concat(" AND ");
			}
			sql = sql.concat(" MONTH(data_sql) = " + seletorLancamento.getMes());
		}
		
		if(seletorLancamento.getAno() > 1500) {
			if(primeiro == 0) {
				sql = sql.concat(" WHERE ");
				primeiro++;
			} else {
				sql = sql.concat(" AND ");
			}
			sql = sql.concat(" YEAR(data_sql) = " + seletorLancamento.getAno());
		}
		
		Connection conn = Conexao.getConnection();
		Statement stmt = Conexao.getStatement(conn);
		ResultSet result = null;
		ArrayList<LogLancamentosVO> logLancamentosVO = new ArrayList<LogLancamentosVO>();
		
		try {
			result = stmt.executeQuery(sql);
			while(result.next()) {
				LogLancamentosVO logLancamento = new LogLancamentosVO();

				logLancamento.setCodigoproduto(result.getInt("codigoproduto"));
				logLancamento.setData(result.getString("data"));
				logLancamento.setOperacacao(result.getString("operacao"));
				logLancamento.setProduto(result.getString("produto"));
				logLancamento.setQuantidade(result.getInt("quantidade"));
				logLancamento.setTipo(result.getString("tipo"));
				
				logLancamentosVO.add(logLancamento);
			}
		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		} finally {
			Conexao.closeResultSet(result);
			Conexao.closeStatement(stmt);
			Conexao.closeConnection(conn);
		}
		return logLancamentosVO;
	}
	
	/**
	 * listar ou filtrar todas as views de lançamento
	 * @param seletorLancamento
	 * @return
	 */
	public ArrayList<LancamentoDTO> filtrarLancamentos(SeletorLancamento seletorLancamento) {
		
		String nomeProduto = (seletorLancamento.isTemFiltro()) ? seletorLancamento.getNomeProduto().trim() : "";
		String nomeSetor = (seletorLancamento.isTemFiltro()) ? seletorLancamento.getNomeSetor().trim() : "";
		String dataInicial = (seletorLancamento.isTemFiltro()) ? seletorLancamento.getDataInicial() : "";
		String dataFinal = (seletorLancamento.isTemFiltro()) ? seletorLancamento.getDataFinal() : "";
		String tipo = (seletorLancamento.isTemFiltro()) ? seletorLancamento.getTipo() : "";
		int primeiro = 0;
		
		String sql = "SELECT * FROM vw_lancamento ";
		
		if(nomeProduto.length() > 0) {
			if(primeiro == 0) {
				sql = sql.concat(" WHERE ");
				primeiro++;
			} else {
				sql = sql.concat(" AND ");
			}
			primeiro++;
			sql = sql.concat(" produto LIKE '%");
			sql = sql.concat(nomeProduto);
			sql = sql.concat("%' ");
		}
		
		if(nomeSetor.length() > 0) {
			if(primeiro == 0) {
				sql = sql.concat(" WHERE ");
				primeiro++;
			} else {
				sql = sql.concat(" AND ");
			}
			sql = sql.concat(" setor LIKE '%");
			sql = sql.concat(nomeSetor);
			sql = sql.concat("%'");
		}
		
		if(tipo.length() > 0) {
			if(primeiro == 0) {
				sql = sql.concat(" WHERE ");
				primeiro++;
			} else {
				sql = sql.concat(" AND ");
			}
			sql = sql.concat(" tipo = '");
			sql = sql.concat(tipo);
			sql = sql.concat("' ");
		}
		
		if(dataInicial.length() > 0 && dataFinal.length() > 0) {
			if(primeiro == 0) {
				sql = sql.concat(" WHERE ");
				primeiro++;
			} else {
				sql = sql.concat(" AND ");
			}
			sql = sql.concat(" data_sql BETWEEN '");
			sql = sql.concat(dataInicial);
			sql = sql.concat("' AND '");
			sql = sql.concat(dataFinal);
			sql = sql.concat("' ");
		}
		
		if(dataInicial.length() > 0 && dataFinal.length() == 0) {
			if(primeiro == 0) {
				sql = sql.concat(" WHERE ");
				primeiro++;
			} else {
				sql = sql.concat(" AND ");
			}
			sql = sql.concat(" data_sql >= '");
			sql = sql.concat(dataInicial);
			sql = sql.concat("' ");
		}
		
		if(dataFinal.length() > 0 && dataInicial.length() == 0) {
			if(primeiro == 0) {
				sql = sql.concat(" WHERE ");
				primeiro++;
			} else {
				sql = sql.concat(" AND ");
			}
			sql = sql.concat(" data_sql <= ");
			sql = sql.concat(dataFinal);
			sql = sql.concat("' ");
		}
		
		sql = sql.concat(" LIMIT " + seletorLancamento.getNumeroPorPagina());
		
		if(seletorLancamento.getOffset() != null) {
			sql = sql.concat(" OFFSET " + seletorLancamento.getOffset());
		}
		
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
	 * retorna um lan�amento espec�fico
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
	 * cadastra um novo lan�amento
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
					  + "NOW()"+ ")";
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
	 * atualiza um lan�amento
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
	 * excluir um lan�amento
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
	
	/**
	 * retorna o total de registros
	 * @return
	 */
	public int totalDeRegistros() {
		String sql = "SELECT COUNT(*) AS quantidade FROM tb_lancamento";
		Connection conn = Conexao.getConnection();
		Statement stmt = Conexao.getStatement(conn);
		ResultSet result = null;
		int total = 0;
		
		try {
			result = stmt.executeQuery(sql);
			while(result.next()) {
				total += Integer.parseInt(result.getString("quantidade"));
			}
		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		} finally {
			Conexao.closeResultSet(result);
			Conexao.closeStatement(stmt);
			Conexao.closeConnection(conn);
		}
		return total;
	}
	
	/**
	 * retorna número de páginas
	 * @param numeroDeRegistros
	 * @return
	 */
	public double numeroDePaginas(double numeroDeRegistros) {
		return Math.ceil(numeroDeRegistros / Constantes.ITEM_POR_PAGINA);
	}

}
