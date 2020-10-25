package senac.estoque.model.dto;

public class ProdutoMaisVendidoDTO {
    private int qtd_vendas;
    private String descricao;

   public ProdutoMaisVendidoDTO() {
    }

    public int getQtd_vendas() {
        return qtd_vendas;
    }

    public void setQtd_vendas(int qtd_vendas) {
        this.qtd_vendas = qtd_vendas;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
