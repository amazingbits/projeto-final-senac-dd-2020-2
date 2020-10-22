package senac.projeto.models.vo;

import java.sql.Date;

public class Produto {
    private int id;
    private String descricao;
    private Categoria categoria;
    private int quantidade;
    private float preco;
    private Date data_ultima_entrada;
    private Date data_ultima_saida;

    public Produto() {
    }

    public Produto(int id, String descricao, Categoria categoria, int quantidade, float preco, Date data_ultima_entrada,
            Date data_ultima_saida) {
        this.id = id;
        this.descricao = descricao;
        this.categoria = categoria;
        this.quantidade = quantidade;
        this.preco = preco;
        this.data_ultima_entrada = data_ultima_entrada;
        this.data_ultima_saida = data_ultima_saida;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public Date getData_ultima_entrada() {
        return data_ultima_entrada;
    }

    public void setData_ultima_entrada(Date data_ultima_entrada) {
        this.data_ultima_entrada = data_ultima_entrada;
    }

    public Date getData_ultima_saida() {
        return data_ultima_saida;
    }

    public void setData_ultima_saida(Date data_ultima_saida) {
        this.data_ultima_saida = data_ultima_saida;
    }

}
