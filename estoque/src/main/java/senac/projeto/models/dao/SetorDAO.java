package senac.projeto.models.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import senac.projeto.database.Banco;
import senac.projeto.models.vo.Setor;

public class SetorDAO {
    public int criar(Setor s) {
        Connection conn = Banco.getConnection();
        Statement stmt = Banco.getStatement(conn);
        int resultado = 0;
        String query = "INSERT INTO tb_setor (descricao) VALUES(";

        query.concat("'" + s.getDescricao() + "'");
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

    public int atualizar(Setor s) {
        Connection conn = Banco.getConnection();
        Statement stmt = Banco.getStatement(conn);
        int resultado = 0;
        String query = "UPDATE tb_setor SET ";
        query.concat("descricao='" + s.getDescricao() + "'");
        query.concat(" WHERE id=" + s.getId());

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

    public int deletar(Setor s) {
        Connection conn = Banco.getConnection();
        Statement stmt = Banco.getStatement(conn);
        int resultado = 0;
        String query = "DELETE FROM tb_setor WHERE ";
        query.concat(" id=" + s.getId());

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

    public Setor buscarUm(Setor s) {

        Connection conn = Banco.getConnection();
        Statement stmt = Banco.getStatement(conn);
        ResultSet resultado = null;

        Setor setor = new Setor();

        String query = "SELECT id,descricao FROM tb_setor WHERE id=" + s.getId();

        try {
            resultado = stmt.executeQuery(query);

            while (resultado.next()) {

                setor.setId(resultado.getInt("id"));
                setor.setDescricao(resultado.getString("descricao"));

            }
        } catch (SQLException e) {

            System.out.println("Erro: " + e.getMessage());
        } finally {
            Banco.closeResultSet(resultado);
            Banco.closePreparedStatement(stmt);
            Banco.closeConnection(conn);
        }

        return setor;
    }

    public ArrayList<Setor> buscarTodos() {

        Connection conn = Banco.getConnection();
        Statement stmt = Banco.getStatement(conn);
        ResultSet resultado = null;

        ArrayList<Setor> listSetores = new ArrayList<Setor>();

        String query = "SELECT id,descricao FROM tb_setor";

        try {
            resultado = stmt.executeQuery(query);

            while (resultado.next()) {
                Setor setor = new Setor();

                setor.setId(resultado.getInt("id"));
                setor.setDescricao(resultado.getString("descricao"));
                listSetores.add(setor);
            }
        } catch (SQLException e) {

            System.out.println("Erro: " + e.getMessage());
        } finally {
            Banco.closeResultSet(resultado);
            Banco.closePreparedStatement(stmt);
            Banco.closeConnection(conn);
        }

        return listSetores;
    }

}
