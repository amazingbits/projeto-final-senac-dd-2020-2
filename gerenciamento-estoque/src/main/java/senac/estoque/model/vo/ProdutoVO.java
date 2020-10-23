package senac.estoque.model.vo;

public class ProdutoVO {
	
	private int id;
	private String descricao;
	private CategoriaVO categoria;
	private int quantidade;
	private float preco;
	private String data_ultima_entrada;
	private String data_ultima_saida;
	
	public ProdutoVO() {
		super();
		// TODO Auto-generated constructor stub
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
	 * @return the categoria
	 */
	public CategoriaVO getCategoria() {
		return categoria;
	}

	/**
	 * @param categoria the categoria to set
	 */
	public void setCategoria(CategoriaVO categoria) {
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
	 * @return the data_ultima_entrada
	 */
	public String getData_ultima_entrada() {
		return data_ultima_entrada;
	}

	/**
	 * @param data_ultima_entrada the data_ultima_entrada to set
	 */
	public void setData_ultima_entrada(String data_ultima_entrada) {
		this.data_ultima_entrada = data_ultima_entrada;
	}

	/**
	 * @return the data_ultima_saida
	 */
	public String getData_ultima_saida() {
		return data_ultima_saida;
	}

	/**
	 * @param data_ultima_saida the data_ultima_saida to set
	 */
	public void setData_ultima_saida(String data_ultima_saida) {
		this.data_ultima_saida = data_ultima_saida;
	}

}
