package senac.estoque.model.vo;

public class LogLancamentosVO {

    private int codigoproduto;
    private String produto;
    private String tipo;
    private int quantidade;
    private String operacacao;
    private String data;


    public LogLancamentosVO(){}


    public int getCodigoproduto() {
        return codigoproduto;
    }

    public void setCodigoproduto(int codigoproduto) {
        this.codigoproduto = codigoproduto;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getOperacacao() {
        return operacacao;
    }

    public void setOperacacao(String operacacao) {
        this.operacacao = operacacao;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    
}
