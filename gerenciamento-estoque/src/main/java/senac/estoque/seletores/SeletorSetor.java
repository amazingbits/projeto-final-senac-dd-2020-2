package senac.estoque.seletores;

import senac.estoque.helpers.Constantes;

public class SeletorSetor {
    private String nomeSetor;
    private boolean temFiltro = true;
    private Integer offset = 0;
    private Integer numeroPorPagina = Constantes.ITEM_POR_PAGINA;

    public SeletorSetor() {
        super();
    }

    public String getNomeSetor() {
        return nomeSetor;
    }

    public void setNomeSetor(String nomeSetor) {
        this.nomeSetor = nomeSetor;
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
