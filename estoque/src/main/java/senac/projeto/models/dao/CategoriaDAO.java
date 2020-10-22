package senac.projeto.models.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import senac.projeto.database.Banco;
import senac.projeto.models.vo.Categoria;

public class CategoriaDAO {
    public int criar(Categoria c) {
        Connection conn = Banco.getConnection();
        Statement stmt = Banco.getStatement(conn);
        int resultado = 0;
        String query = "INSERT INTO tb_categoria (descricao) VALUES(";

        query.concat("'" + c.getDescricao() + "'");
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

    public int atualizar(Categoria c) {
        Connection conn = Banco.getConnection();
        Statement stmt = Banco.getStatement(conn);
        int resultado = 0;
        String query = "UPDATE tb_categoria SET ";
        query.concat("descricao='" + c.getDescricao() + "'");
        query.concat(" WHERE id=" + c.getId());

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

    public int deletar(Categoria c) {
        Connection conn = Banco.getConnection();
        Statement stmt = Banco.getStatement(conn);
        int resultado = 0;
        String query = "DELETE FROM tb_categoria WHERE ";
        query.concat(" id=" + c.getId());

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

    public Categoria buscarUm(Categoria c) {

        Connection conn = Banco.getConnection();
        Statement stmt = Banco.getStatement(conn);
        ResultSet resultado = null;

        Categoria categoria = new Categoria();

        String query = "SELECT id,descricao FROM tb_categoria WHERE id=" + c.getId();

        try {
            resultado = stmt.executeQuery(query);

            while (resultado.next()) {

                categoria.setId(resultado.getInt("id"));
                categoria.setDescricao(resultado.getString("descricao"));

            }
        } catch (SQLException e) {

            System.out.println("Erro: " + e.getMessage());
        } finally {
            Banco.closeResultSet(resultado);
            Banco.closePreparedStatement(stmt);
            Banco.closeConnection(conn);
        }

        return categoria;
    }

    public ArrayList<Categoria> buscarTodos() {

        Connection conn = Banco.getConnection();
        Statement stmt = Banco.getStatement(conn);
        ResultSet resultado = null;

        ArrayList<Categoria> listCategorias = new ArrayList<Categoria>();

        String query = "SELECT id,descricao FROM tb_categoria";

        try {
            resultado = stmt.executeQuery(query);

            while (resultado.next()) {
                Categoria categoria = new Categoria();

                categoria.setId(resultado.getInt("id"));
                categoria.setDescricao(resultado.getString("descricao"));
                listCategorias.add(categoria);
            }
        } catch (SQLException e) {

            System.out.println("Erro: " + e.getMessage());
        } finally {
            Banco.closeResultSet(resultado);
            Banco.closePreparedStatement(stmt);
            Banco.closeConnection(conn);
        }

        return listCategorias;
    }

}
