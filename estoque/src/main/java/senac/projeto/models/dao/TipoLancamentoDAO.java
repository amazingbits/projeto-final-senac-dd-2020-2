package senac.projeto.models.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import senac.projeto.database.Banco;
import senac.projeto.models.vo.TipoLancamento;

public class TipoLancamentoDAO {
    public int criar(TipoLancamento tl) {
        Connection conn = Banco.getConnection();
        Statement stmt = Banco.getStatement(conn);
        int resultado = 0;
        String query = "INSERT INTO tb_tipo_lancamento (descricao) VALUES(";

        query.concat("'" + tl.getDescricao() + "'");
        query.concat(")");

        try {
            resultado = stmt.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            Banco.closePreparedStatement(stmt);
            Banco.closeConnection(conn);
        }

        return resultado;

    }

    public int atualizar(TipoLancamento tl) {
        Connection conn = Banco.getConnection();
        Statement stmt = Banco.getStatement(conn);
        int resultado = 0;
        String query = "UPDATE tb_tipo_lancamento SET ";
        query.concat("descricao='" + tl.getDescricao() + "'");
        query.concat(" WHERE id=" + tl.getId());

        try {
            resultado = stmt.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            Banco.closePreparedStatement(stmt);
            Banco.closeConnection(conn);
        }

        return resultado;
    }

    public int deletar(TipoLancamento tl) {
        Connection conn = Banco.getConnection();
        Statement stmt = Banco.getStatement(conn);
        int resultado = 0;
        String query = "DELETE FROM tb_tipo_lancamento WHERE ";
        query.concat(" id=" + tl.getId());

        try {
            resultado = stmt.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            Banco.closePreparedStatement(stmt);
            Banco.closeConnection(conn);
        }

        return resultado;
    }

    public TipoLancamento buscarUm(TipoLancamento tl) {

        Connection conn = Banco.getConnection();
        Statement stmt = Banco.getStatement(conn);
        ResultSet resultado = null;

        TipoLancamento tipoLancamento = new TipoLancamento();

        String query = "SELECT id,descricao FROM tb_tipo_lancamento WHERE id=" + tl.getId();

        try {
            resultado = stmt.executeQuery(query);

            while (resultado.next()) {

                tipoLancamento.setId(resultado.getInt("id"));
                tipoLancamento.setDescricao(resultado.getString("descricao"));

            }
        } catch (SQLException e) {

            System.out.println("Erro: " + e.getMessage());
        } finally {
            Banco.closeResultSet(resultado);
            Banco.closePreparedStatement(stmt);
            Banco.closeConnection(conn);
        }

        return tipoLancamento;
    }

    public ArrayList<TipoLancamento> buscarTodos() {

        Connection conn = Banco.getConnection();
        Statement stmt = Banco.getStatement(conn);
        ResultSet resultado = null;

        ArrayList<TipoLancamento> listTipoLancamentos = new ArrayList<TipoLancamento>();

        String query = "SELECT id,descricao FROM tb_tipo_lancamento";

        try {
            resultado = stmt.executeQuery(query);

            while (resultado.next()) {
                TipoLancamento tl = new TipoLancamento();

                tl.setId(resultado.getInt("id"));
                tl.setDescricao(resultado.getString("descricao"));
                listTipoLancamentos.add(tl);
            }
        } catch (SQLException e) {

            System.out.println("Erro: " + e.getMessage());
        } finally {
            Banco.closeResultSet(resultado);
            Banco.closePreparedStatement(stmt);
            Banco.closeConnection(conn);
        }

        return listTipoLancamentos;
    }

}
