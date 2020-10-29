package senac.estoque.seletores;

import senac.estoque.helpers.Constantes;

public class SeletorProduto {
	
	private String nomeProduto;
	private boolean temFiltro = true;
	private Integer offset = 0;
	private Integer numeroPorPagina = Constantes.ITEM_POR_PAGINA;
	
	/* seletores para os logs */
	private int mes;
	private int ano;
	
	
	/**
	 * @return the mes
	 */
	public int getMes() {
		return mes;
	}

	/**
	 * @param mes the mes to set
	 */
	public void setMes(int mes) {
		this.mes = mes;
	}

	/**
	 * @return the ano
	 */
	public int getAno() {
		return ano;
	}

	/**
	 * @param ano the ano to set
	 */
	public void setAno(int ano) {
		this.ano = ano;
	}

	public SeletorProduto() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the nomeProduto
	 */
	public String getNomeProduto() {
		return nomeProduto;
	}

	/**
	 * @param nomeProduto the nomeProduto to set
	 */
	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	/**
	 * @return the temFiltro
	 */
	public boolean isTemFiltro() {
		return temFiltro;
	}

	/**
	 * @param temFiltro the temFiltro to set
	 */
	public void setTemFiltro(boolean temFiltro) {
		this.temFiltro = temFiltro;
	}

	/**
	 * @return the offset
	 */
	public Integer getOffset() {
		return offset;
	}

	/**
	 * @param offset the offset to set
	 */
	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	/**
	 * @return the numeroPorPagina
	 */
	public Integer getNumeroPorPagina() {
		return numeroPorPagina;
	}

	/**
	 * @param numeroPorPagina the numeroPorPagina to set
	 */
	public void setNumeroPorPagina(Integer numeroPorPagina) {
		this.numeroPorPagina = numeroPorPagina;
	}

}
