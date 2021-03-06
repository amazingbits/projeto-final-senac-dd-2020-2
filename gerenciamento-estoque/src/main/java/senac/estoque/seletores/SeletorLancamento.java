package senac.estoque.seletores;

import senac.estoque.helpers.Constantes;

public class SeletorLancamento {
	
	private String nomeProduto;
	private String nomeSetor;
	private String dataInicial;
	private String dataFinal;
	private String tipo;
	
	private boolean temFiltro = true;
	private Integer offset = 0;
	private Integer numeroPorPagina = Constantes.ITEM_POR_PAGINA;
	
	private int mes = 0;
	private int ano = 0;


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
