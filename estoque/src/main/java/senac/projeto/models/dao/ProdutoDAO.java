
package senac.projeto.models.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import senac.projeto.database.Banco;
import senac.projeto.models.vo.Categoria;
import senac.projeto.models.vo.Produto;

public class ProdutoDAO {
    public int criar(Produto p) {
        Connection conn = Banco.getConnection();
        Statement stmt = Banco.getStatement(conn);
        int resultado = 0;
        String query = "INSERT INTO tb_produto (descricao,categoria,quantidade,preco,data_ultima_entrada,data_ultima_saida) VALUES(";

        query.concat("'" + p.getDescricao() + "'");
        query.concat("'" + p.getCategoria() + "'");
        query.concat("" + p.getQuantidade() + "");
        query.concat("" + p.getPreco() + "");
        query.concat("'" + p.getData_ultima_entrada() + "'");
        query.concat("'" + p.getData_ultima_saida() + "'");
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

    public int atualizar(Produto p) {
        Connection conn = Banco.getConnection();
        Statement stmt = Banco.getStatement(conn);
        int resultado = 0;
        String query = "UPDATE tb_produto SET ";

        query.concat("descricao= '" + p.getDescricao() + "', ");
        query.concat("categoria= '" + p.getCategoria() + "', ");
        query.concat("quantidade= " + p.getQuantidade() + ", ");
        query.concat("preco= " + p.getPreco() + ",");
        query.concat("data_ultima_entrada= '" + p.getData_ultima_entrada() + "', ");
        query.concat("data_ultima_saida= '" + p.getData_ultima_saida() + "' ");
        query.concat(")");

        query.concat(" WHERE id=" + p.getId());

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

    public int deletar(Produto p) {
        Connection conn = Banco.getConnection();
        Statement stmt = Banco.getStatement(conn);
        int resultado = 0;
        String query = "DELETE FROM tb_produto WHERE ";
        query.concat(" id= " + p.getId());

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

    public Produto buscarUm(Produto p) {

        Connection conn = Banco.getConnection();
        Statement stmt = Banco.getStatement(conn);
        ResultSet resultado = null;

        Produto produto = new Produto();

        String query = "SELECT id,descricao,categoria,quantidade,preco,data_ultima_entrada,data_ultima_saida FROM tb_produto WHERE id="
                + p.getId();

        try {
            resultado = stmt.executeQuery(query);

            while (resultado.next()) {

                Categoria categoria = new Categoria();

                categoria.setDescricao(resultado.getString("categoria"));

                produto.setId(resultado.getInt("id"));
                produto.setDescricao(resultado.getString("descricao"));
                produto.setCategoria(categoria);
                produto.setPreco(resultado.getFloat("preco"));
                produto.setData_ultima_entrada(resultado.getDate("data_ultima_entrada"));
                produto.setData_ultima_entrada(resultado.getDate("data_ultima_saida"));

            }
        } catch (SQLException e) {

            System.out.println("Erro: " + e.getMessage());
        } finally {
            Banco.closeResultSet(resultado);
            Banco.closePreparedStatement(stmt);
            Banco.closeConnection(conn);
        }

        return produto;
    }

    public ArrayList<Produto> buscarTodos() {

        Connection conn = Banco.getConnection();
        Statement stmt = Banco.getStatement(conn);
        ResultSet resultado = null;

        ArrayList<Produto> listProdutos = new ArrayList<Produto>();

        String query = "SELECT id,descricao FROM tb_produto";

        try {
            resultado = stmt.executeQuery(query);

            while (resultado.next()) {
                Produto produto = new Produto();
                Categoria categoria = new Categoria();

                categoria.setDescricao(resultado.getString("categoria"));

                produto.setId(resultado.getInt("id"));
                produto.setDescricao(resultado.getString("descricao"));
                produto.setCategoria(categoria);
                produto.setPreco(resultado.getFloat("preco"));
                produto.setData_ultima_entrada(resultado.getDate("data_ultima_entrada"));
                produto.setData_ultima_entrada(resultado.getDate("data_ultima_saida"));

                listProdutos.add(produto);
            }
        } catch (SQLException e) {

            System.out.println("Erro: " + e.getMessage());
        } finally {
            Banco.closeResultSet(resultado);
            Banco.closePreparedStatement(stmt);
            Banco.closeConnection(conn);
        }

        return listProdutos;
    }

}
