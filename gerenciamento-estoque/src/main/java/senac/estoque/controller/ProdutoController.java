package senac.estoque.controller;

import java.util.ArrayList;

import senac.estoque.model.bo.ProdutoBO;
import senac.estoque.model.dto.ProdutoDTO;
import senac.estoque.model.dto.ProdutoMaisUsadosDTO;
import senac.estoque.model.dto.ProdutoMaisVendidoDTO;
import senac.estoque.model.vo.LogProdutosVO;
import senac.estoque.model.vo.ProdutoVO;
import senac.estoque.seletores.SeletorProduto;

public class ProdutoController {
    public boolean cadastrarProduto(ProdutoVO produtoVO) {
		ProdutoBO produtoBO = new ProdutoBO();
		return produtoBO.CadastrarProduto(produtoVO);
	}

	public ArrayList<ProdutoVO> listarProduto() {
		ProdutoBO produtoBO = new ProdutoBO();
		return produtoBO.listarProduto();
	}
	public ArrayList<ProdutoMaisVendidoDTO> listarProdutoMaisVendidos(){
		ProdutoBO produtoBO = new ProdutoBO();
		return produtoBO.listarProdutoMaisVendidos();
	}
	public ArrayList<ProdutoMaisUsadosDTO> listarMaisUsados(){
		ProdutoBO produtoBO = new ProdutoBO();
		return produtoBO.listarMaisUsados();
	}
	public ArrayList<LogProdutosVO> listaLogProdutos(){
		ProdutoBO produtoBO = new ProdutoBO();
		return produtoBO.listaLogProdutos();
	}
	
	public ArrayList<ProdutoDTO> listarProdutoSeletor(SeletorProduto seletorProduto) {
		ProdutoBO produtoBO = new ProdutoBO();
		return produtoBO.listarProdutoSeletor(seletorProduto);
	}
	
	public boolean editarProduto(ProdutoVO produtoVO) {
		ProdutoBO produtoBO = new ProdutoBO();
		return produtoBO.editarProduto(produtoVO);
	}

	public boolean excluir(ProdutoVO produtoVO) {
		ProdutoBO produtoBO = new ProdutoBO();
		return produtoBO.excluir(produtoVO);

	}
}
