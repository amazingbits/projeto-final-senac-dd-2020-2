package senac.estoque.model.dto;

public class ProdutoDTO {

	private int id;
	private String descricao;
	private float preco;
	private String categoria;
	private int quantidade;
	private String ultimaEntrada;
	private String ultimaSaida;

	public ProdutoDTO() {
		super();

	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return the preco
	 */
	public float getPreco() {
		return preco;
	}

	/**
	 * @param preco the preco to set
	 */
	public void setPreco(float preco) {
		this.preco = preco;
	}

	/**
	 * @return the categoria
	 */
	public String getCategoria() {
		return categoria;
	}

	/**
	 * @param categoria the categoria to set
	 */
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	/**
	 * @return the quantidade
	 */
	public int getQuantidade() {
		return quantidade;
	}

	/**
	 * @param quantidade the quantidade to set
	 */
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	/**
	 * @return the ultimaEntrada
	 */
	public String getUltimaEntrada() {
		return ultimaEntrada;
	}

	/**
	 * @param ultimaEntrada the ultimaEntrada to set
	 */
	public void setUltimaEntrada(String ultimaEntrada) {
		this.ultimaEntrada = ultimaEntrada;
	}

	/**
	 * @return the ultimaSaida
	 */
	public String getUltimaSaida() {
		return ultimaSaida;
	}

	/**
	 * @param ultimaSaida the ultimaSaida to set
	 */
	public void setUltimaSaida(String ultimaSaida) {
		this.ultimaSaida = ultimaSaida;
	}

}
