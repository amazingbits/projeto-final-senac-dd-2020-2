package senac.estoque.model.dto;

public class ProdutoMaisUsadosDTO {
    private int qtd_usados;
    private String descricao;

    public ProdutoMaisUsadosDTO() {
    }

    public int getQtd_usados() {
        return qtd_usados;
    }

    public void setQtd_usados(int qtd_usados) {
        this.qtd_usados = qtd_usados;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
