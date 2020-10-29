package senac.estoque.model.vo;

public class LancamentoVO {
	
	private int id;
	private ProdutoVO produto;
	private SetorVO setor;
	private TipoLancamentoVO tipo;
	private int quantidade;
	private float precoTotal;
	private String data;
	
	public LancamentoVO() {
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
	 * @return the produto
	 */
	public ProdutoVO getProduto() {
		return produto;
	}

	/**
	 * @param produto the produto to set
	 */
	public void setProduto(ProdutoVO produto) {
		this.produto = produto;
	}

	/**
	 * @return the setor
	 */
	public SetorVO getSetor() {
		return setor;
	}

	/**
	 * @param setor the setor to set
	 */
	public void setSetor(SetorVO setor) {
		this.setor = setor;
	}

	/**
	 * @return the tipo
	 */
	public TipoLancamentoVO getTipo() {
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(TipoLancamentoVO tipo) {
		this.tipo = tipo;
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
	 * @return the precoTotal
	 */
	public float getPrecoTotal() {
		return precoTotal;
	}

	/**
	 * @param precoTotal the precoTotal to set
	 */
	public void setPrecoTotal(float precoTotal) {
		this.precoTotal = precoTotal;
	}

	/**
	 * @return the data
	 */
	public String getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(String data) {
		this.data = data;
	}

}
