package senac.estoque.model.dao;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import java.sql.*;

import senac.estoque.model.Conexao;
import senac.estoque.model.vo.SetorVO;
import senac.estoque.seletores.SeletorSetor;

public class SetorDAO {

	/**
	 * listar setores
	 * 
	 * @return
	 */
	public ArrayList<SetorVO> listar() {
		String sql = "SELECT * FROM tb_setor WHERE ativo = 0 ORDER BY descricao ASC";
		Connection conn = Conexao.getConnection();
		Statement stmt = Conexao.getStatement(conn);
		ResultSet result = null;
		ArrayList<SetorVO> listaSetor = new ArrayList<SetorVO>();

		try {
			result = stmt.executeQuery(sql);
			while (result.next()) {
				SetorVO setor = new SetorVO();
				setor.setId(Integer.parseInt(result.getString("id")));
				setor.setDescricao(result.getString("descricao"));
				listaSetor.add(setor);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		} finally {
			Conexao.closeResultSet(result);
			Conexao.closeStatement(stmt);
			Conexao.closeConnection(conn);
		}
		return listaSetor;
	}

	/**
	 * retorna um setor espec�fico
	 * 
	 * @param id
	 * @return
	 */
	public SetorVO encontrar(int id) {
		String sql = "SELECT * FROM tb_setor WHERE id = " + id;
		Connection conn = Conexao.getConnection();
		Statement stmt = Conexao.getStatement(conn);
		ResultSet result = null;
		SetorVO setor = new SetorVO();

		try {
			result = stmt.executeQuery(sql);
			while (result.next()) {
				setor.setId(Integer.parseInt(result.getString("id")));
				setor.setDescricao(result.getString("descricao"));
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		} finally {
			Conexao.closeResultSet(result);
			Conexao.closeStatement(stmt);
			Conexao.closeConnection(conn);
		}
		return setor;
	}

	public SetorVO encontrarPorNome(String nome) {
		String sql = "SELECT * FROM tb_setor WHERE descricao = '" + nome + "'";
		Connection conn = Conexao.getConnection();
		Statement stmt = Conexao.getStatement(conn);
		ResultSet result = null;
		SetorVO setor = new SetorVO();

		try {
			result = stmt.executeQuery(sql);
			while (result.next()) {
				setor.setId(Integer.parseInt(result.getString("id")));
				setor.setDescricao(result.getString("descricao"));
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		} finally {
			Conexao.closeResultSet(result);
			Conexao.closeStatement(stmt);
			Conexao.closeConnection(conn);
		}
		return setor;
	}
	
	/**
	 * verificar se está desativado
	 * @param setor
	 * @return
	 */
	public boolean verificarSeEstaDesativado(SetorVO setor) {
		String sql = "SELECT * FROM tb_setor WHERE ativo = 1 AND descricao = '";
		sql = sql.concat(setor.getDescricao() + "'");
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
	 * cadastrar novo setor
	 * 
	 * @param setor
	 * @return
	 */
	public int cadastrar(SetorVO setor) {
		String sql = "INSERT INTO tb_setor (descricao) VALUES ('" + setor.getDescricao() + "')";
		Connection conn = Conexao.getConnection();
		Statement stmt = Conexao.getStatement(conn);
		int result = 0;

		int id = this.encontrarPorNome(setor.getDescricao()).getId();
		setor.setId(id);
		if(this.verificarSeEstaDesativado(setor)) {
			this.ativar(setor);
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
	 * atualiza um setor
	 * 
	 * @param setor
	 * @param id
	 * @return
	 */
	public int atualizar(SetorVO setor, int id) {
		String sql = "UPDATE tb_setor SET descricao = '" + setor.getDescricao() + "' WHERE id = " + id;
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
	 * excluir um setor
	 * 
	 * @param id
	 * @return
	 */
	public int excluir(int id) {
		String sql = "DELETE FROM tb_setor WHERE id = " + id;
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

	public ArrayList<SetorVO> listarView(SeletorSetor seletorSetor) {
		String sql = "SELECT * FROM tb_setor WHERE ativo = 0 ";

		if (seletorSetor.getNomeSetor().trim().length() > 0) {
			sql = sql.concat(" AND descricao LIKE '%");
			sql = sql.concat(seletorSetor.getNomeSetor() + "%' ");
		}
		
		sql = sql.concat(" ORDER BY id DESC ");

		sql = sql.concat(" LIMIT " + seletorSetor.getNumeroPorPagina());
		sql = sql.concat(" OFFSET " + seletorSetor.getOffset());

		Connection conn = Conexao.getConnection();
		Statement stmt = Conexao.getStatement(conn);
		ResultSet result = null;
		ArrayList<SetorVO> listaSetor = new ArrayList<SetorVO>();

		try {
			result = stmt.executeQuery(sql);
			while (result.next()) {
				SetorVO setor = new SetorVO();
				setor.setId(Integer.parseInt(result.getString("id")));
				setor.setDescricao(result.getString("descricao"));

				listaSetor.add(setor);

			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		} finally {
			Conexao.closeResultSet(result);
			Conexao.closeStatement(stmt);
			Conexao.closeConnection(conn);
		}
		return listaSetor;
	}
	
	/**
	 * desativa o setor
	 * @param setorVO
	 * @return
	 */
	public boolean exclusaoLogica(SetorVO setorVO) {
		String sql = "UPDATE tb_setor SET ativo = 1 WHERE id = " + setorVO.getId();
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
		if(result == 0) return false;
		return true;
	}
	
	/**
	 * ativa setor
	 * @param setor
	 * @return
	 */
	public boolean ativar(SetorVO setor) {
		String sql = "UPDATE tb_setor SET ativo = 0 WHERE id = " + setor.getId();
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
		if(result == 0) return false;
		return true;
	}

}
