package senac.estoque.model.dao;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import java.sql.*;

import senac.estoque.model.Conexao;
import senac.estoque.model.vo.SetorVO;

public class SetorDAO {
	
	/**
	 * listar setores
	 * @return
	 */
	public ArrayList<SetorVO> listar() {
		String sql = "SELECT * FROM tb_setor";
		Connection conn = Conexao.getConnection();
		Statement stmt = Conexao.getStatement(conn);
		ResultSet result = null;
		ArrayList<SetorVO> listaSetor = new ArrayList<SetorVO>();
		
		try {
			result = stmt.executeQuery(sql);
			while(result.next()) {
				SetorVO setor = new SetorVO();
				setor.setId(Integer.parseInt(result.getString("id")));
				setor.setDescricao(result.getString("descricao"));
				listaSetor.add(setor);
			}
		} catch(SQLException e) {
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
			while(result.next()) {
				setor.setId(Integer.parseInt(result.getString("id")));
				setor.setDescricao(result.getString("descricao"));
			}
		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		} finally {
			Conexao.closeResultSet(result);
			Conexao.closeStatement(stmt);
			Conexao.closeConnection(conn);
		}
		return setor;
	}
	public SetorVO encontrar(String nome) {
		String sql = "SELECT * FROM tb_setor WHERE descricao = '" + nome +"'";
		Connection conn = Conexao.getConnection();
		Statement stmt = Conexao.getStatement(conn);
		ResultSet result = null;
		SetorVO setor = new SetorVO();
		
		try {
			result = stmt.executeQuery(sql);
			while(result.next()) {
				setor.setId(Integer.parseInt(result.getString("id")));
				setor.setDescricao(result.getString("descricao"));
			}
		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		} finally {
			Conexao.closeResultSet(result);
			Conexao.closeStatement(stmt);
			Conexao.closeConnection(conn);
		}
		return setor;
	}
	
	/**
	 * cadastrar novo setor
	 * @param setor
	 * @return
	 */
	public int cadastrar(SetorVO setor) {
		String sql = "INSERT INTO tb_setor (descricao) VALUES ('"+ setor.getDescricao() +"')";
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
	 * atualiza um setor
	 * @param setor
	 * @param id
	 * @return
	 */
	public int atualizar(SetorVO setor, int id) {
		String sql = "UPDATE tb_setor SET descricao = '"+ setor.getDescricao() +"' WHERE id = " + id;
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
	 * excluir um setor
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
		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		} finally {
			Conexao.closeStatement(stmt);
			Conexao.closeConnection(conn);
		}
		return result;
	}

}
