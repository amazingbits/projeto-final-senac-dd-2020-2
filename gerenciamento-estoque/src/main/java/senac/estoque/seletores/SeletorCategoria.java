package senac.estoque.seletores;

import senac.estoque.helpers.Constantes;

public class SeletorCategoria {
    private String nomeCategoria;
	private boolean temFiltro = true;
	private Integer offset = 0;
    private Integer numeroPorPagina = Constantes.ITEM_POR_PAGINA;
    
    public SeletorCategoria() {
		super();
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

    public boolean isTemFiltro() {
        return temFiltro;
    }

    public void setTemFiltro(boolean temFiltro) {
        this.temFiltro = temFiltro;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getNumeroPorPagina() {
        return numeroPorPagina;
    }

    public void setNumeroPorPagina(Integer numeroPorPagina) {
        this.numeroPorPagina = numeroPorPagina;
    }
    
    
}
