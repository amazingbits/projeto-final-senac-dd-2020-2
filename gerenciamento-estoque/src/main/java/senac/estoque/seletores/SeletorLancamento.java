package senac.estoque.seletores;

import senac.estoque.helpers.Constantes;

public class SeletorLancamento {
	
	private String nomeProduto;
	private String nomeSetor;
	private String dataInicial;
	private String dataFinal;
	private String tipo;
	private boolean temFiltro = true;
	private boolean temPaginacao = true;
	private Integer numeroDeRegistros;
	private Integer offset = 0;
	private Integer numeroPorPagina = Constantes.ITEM_POR_PAGINA;
	private double numeroDePaginas = 1;
	
	/**
	 * @return the numeroDePaginas
	 */
	public double getNumeroDePaginas() {
		return numeroDePaginas;
	}

	/**
	 * @param numeroDePaginas the numeroDePaginas to set
	 */
	public void setNumeroDePaginas(double numeroDePaginas) {
		this.numeroDePaginas = numeroDePaginas;
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

	/**
	 * @return the numeroDeRegistros
	 */
	public Integer getNumeroDeRegistros() {
		return numeroDeRegistros;
	}

	/**
	 * @param numeroDeRegistros the numeroDeRegistros to set
	 */
	public void setNumeroDeRegistros(Integer numeroDeRegistros) {
		this.numeroDeRegistros = numeroDeRegistros;
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
	 * @return the temPaginacao
	 */
	public boolean isTemPaginacao() {
		return temPaginacao;
	}

	/**
	 * @param temPaginacao the temPaginacao to set
	 */
	public void setTemPaginacao(boolean temPaginacao) {
		this.temPaginacao = temPaginacao;
	}

	public SeletorLancamento() {
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
	 * @return the nomeSetor
	 */
	public String getNomeSetor() {
		return nomeSetor;
	}

	/**
	 * @param nomeSetor the nomeSetor to set
	 */
	public void setNomeSetor(String nomeSetor) {
		this.nomeSetor = nomeSetor;
	}

	/**
	 * @return the dataInicial
	 */
	public String getDataInicial() {
		return dataInicial;
	}

	/**
	 * @param dataInicial the dataInicial to set
	 */
	public void setDataInicial(String dataInicial) {
		this.dataInicial = dataInicial;
	}

	/**
	 * @return the dataFinal
	 */
	public String getDataFinal() {
		return dataFinal;
	}

	/**
	 * @param dataFinal the dataFinal to set
	 */
	public void setDataFinal(String dataFinal) {
		this.dataFinal = dataFinal;
	}

	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
