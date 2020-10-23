
package senac.projeto.models.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import senac.projeto.database.Banco;
import senac.projeto.models.vo.Lancamento;
import senac.projeto.models.vo.Produto;
import senac.projeto.models.vo.Setor;
import senac.projeto.models.vo.TipoLancamento;

public class LancamentoDAO {
    public int criar(Lancamento l) {
        Connection conn = Banco.getConnection();
        Statement stmt = Banco.getStatement(conn);
        int resultado = 0;
        String query = "INSERT INTO tb_lancamento (idproduto,idsetor,tipo,quantidade,preceo_total,data) VALUES(";

        query.concat("" + l.getIdproduto() + ",");
        query.concat("" + l.getIdsetor() + ",");
        query.concat("'" + l.getTipo() + "',");
        query.concat("" + l.getQuantidade() + ",");
        query.concat("" + l.getPreco_total() + ",");
        query.concat("'" + l.getData() + "'");
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

    public int atualizar(Lancamento l) {
        Connection conn = Banco.getConnection();
        Statement stmt = Banco.getStatement(conn);
        int resultado = 0;
        String query = "UPDATE tb_lancamento SET ";

        query.concat("idproduto= " + l.getIdproduto() + ",");
        query.concat("idsetor= " + l.getIdsetor() + ",");
        query.concat("tipo= '" + l.getTipo() + "',");
        query.concat("quantidade= " + l.getQuantidade() + ",");
        query.concat("preco_total = " + l.getPreco_total() + ",");
        query.concat("data= '" + l.getData() + "'");
        query.concat(")");
        query.concat(" WHERE id=" + l.getId());

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

    public int deletar(Lancamento l) {
        Connection conn = Banco.getConnection();
        Statement stmt = Banco.getStatement(conn);
        int resultado = 0;
        String query = "DELETE FROM tb_lancamento WHERE ";
        query.concat(" id= " + l.getId());

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

    public Lancamento buscarUm(Lancamento l) {

        Connection conn = Banco.getConnection();
        Statement stmt = Banco.getStatement(conn);
        ResultSet resultado = null;

        Lancamento lancamento = new Lancamento();

        String query = "SELECT tb_lancamento.id,tb_lancamento.tipo,tb_lancamento.quantidade,tb_lancamento.preceo_total,tb_lancamento.data ";
        query.concat(" tb_setor.descricao as sdescricao");
        query.concat(",tb_produto.descricao as pdescricao");
        query.concat(",tb_tipo_lancamento.descricao as tl_descricao");
        query.concat(" FROM tb_lancamento ");
        query.concat("INNER JOIN tb_produto ON tb_produto.id = tb_lancamento.idproduto ");
        query.concat("INNER JOIN tb_setor ON tb_setor.id = tb_lancamento.idsetor ");
        query.concat("INNER JOIN tb_tipo_lancamento ON tb_tipo_lancamento.id = tb_lancamento.tipo ");
        query.concat(" WHERE id=" + l.getId());

        try {
            resultado = stmt.executeQuery(query);

            while (resultado.next()) {
                TipoLancamento tl = new TipoLancamento();

                tl.setId(resultado.getInt(resultado.getInt("tipo")));
                tl.setDescricao(resultado.getString("tl_descricao"));

                Produto produto = new Produto();

                produto.setId(resultado.getInt("idproduto"));
                produto.setDescricao("pdescricao");

                Setor setor = new Setor();
                setor.setId(resultado.getInt("idsetor"));
                setor.setDescricao("sdescricao");

                lancamento.setId(resultado.getInt("id"));
                lancamento.setIdproduto(resultado.getInt("idproduto"));
                lancamento.setIdsetor(resultado.getInt("idsetor"));
                lancamento.setTipo(tl);
                lancamento.setQuantidade(resultado.getInt("quantidade"));
                lancamento.setProduto(produto);
                lancamento.setSetor(setor);
                lancamento.setPreco_total(resultado.getFloat("preco_total"));
                lancamento.setData(resultado.getDate("data"));

            }
        } catch (SQLException e) {

            System.out.println("Erro: " + e.getMessage());
        } finally {
            Banco.closeResultSet(resultado);
            Banco.closePreparedStatement(stmt);
            Banco.closeConnection(conn);
        }

        return lancamento;
    }

    public ArrayList<Lancamento> buscarTodos() {

        Connection conn = Banco.getConnection();
        Statement stmt = Banco.getStatement(conn);
        ResultSet resultado = null;

        ArrayList<Lancamento> listLancamentos = new ArrayList<Lancamento>();

        String query = "SELECT tb_lancamento.id,tb_lancamento.tipo,tb_lancamento.quantidade,tb_lancamento.preceo_total,tb_lancamento.data ";
        query.concat(" tb_setor.descricao as sdescricao");
        query.concat(",tb_produto.descricao as pdescricao");
        query.concat(",tb_tipo_lancamento.descricao as tl_descricao");
        query.concat(" FROM tb_lancamento ");
        query.concat("INNER JOIN tb_produto ON tb_produto.id = tb_lancamento.idproduto ");
        query.concat("INNER JOIN tb_setor ON tb_setor.id = tb_lancamento.idsetor ");
        query.concat("INNER JOIN tb_tipo_lancamento ON tb_tipo_lancamento.id = tb_lancamento.tipo ");

        try {
            resultado = stmt.executeQuery(query);

            while (resultado.next()) {
                Lancamento lancamento = new Lancamento();

                TipoLancamento tl = new TipoLancamento();

                tl.setId(resultado.getInt(resultado.getInt("tipo")));
                tl.setDescricao(resultado.getString("tl_descricao"));

                Produto produto = new Produto();

                produto.setId(resultado.getInt("idproduto"));
                produto.setDescricao("pdescricao");

                Setor setor = new Setor();
                setor.setId(resultado.getInt("idsetor"));
                setor.setDescricao("sdescricao");

                lancamento.setId(resultado.getInt("id"));
                lancamento.setIdproduto(resultado.getInt("idproduto"));
                lancamento.setIdsetor(resultado.getInt("idsetor"));
                lancamento.setTipo(tl);
                lancamento.setQuantidade(resultado.getInt("quantidade"));
                lancamento.setProduto(produto);
                lancamento.setSetor(setor);
                lancamento.setPreco_total(resultado.getFloat("preco_total"));
                lancamento.setData(resultado.getDate("data"));

                listLancamentos.add(lancamento);
            }
        } catch (SQLException e) {

            System.out.println("Erro: " + e.getMessage());
        } finally {
            Banco.closeResultSet(resultado);
            Banco.closePreparedStatement(stmt);
            Banco.closeConnection(conn);
        }

        return listLancamentos;
    }

}
