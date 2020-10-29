package senac.estoque.model.vo;

import java.sql.Date;

public class LogProdutosVO {
    private String produto;
    private int quantidade;
    private String  operacao;
    private String data ;

    public LogProdutosVO(){}

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getOperacao() {
        return operacao;
    }

    public void setOperacao(String operacao) {
        this.operacao = operacao;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

   
    

}
