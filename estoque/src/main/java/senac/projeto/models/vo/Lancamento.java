package senac.projeto.models.vo;

import java.sql.Date;

public class Lancamento {
    private int id;
    private Produto produto;
    private Setor setor;
    private TipoLancamento tipo;
    private int quantidade;
    private float preco_total;
    private Date data;

    public Lancamento() {
    }

    public Lancamento(int id, Produto produto, Setor setor, TipoLancamento tipo, int quantidade, float preco_total,
            Date data) {
        this.id = id;
        this.produto = produto;
        this.setor = setor;
        this.tipo = tipo;
        this.quantidade = quantidade;
        this.preco_total = preco_total;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }

    public TipoLancamento getTipo() {
        return tipo;
    }

    public void setTipo(TipoLancamento tipo) {
        this.tipo = tipo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public float getPreco_total() {
        return preco_total;
    }

    public void setPreco_total(float preco_total) {
        this.preco_total = preco_total;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

}
