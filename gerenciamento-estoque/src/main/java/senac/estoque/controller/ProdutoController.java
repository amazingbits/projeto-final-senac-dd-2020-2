package senac.estoque.controller;

import java.util.ArrayList;

import senac.estoque.model.bo.ProdutoBO;
import senac.estoque.model.dao.ProdutoDAO;
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

	public ArrayList<LogProdutosVO> listaLogProdutos(SeletorProduto seletorProduto){
		ProdutoBO produtoBO = new ProdutoBO();
		return produtoBO.listaLogProdutos(seletorProduto);
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
	
	
	public ArrayList<ProdutoMaisUsadosDTO> listarProdutosMaisUsados() {
		ProdutoBO produtoBO = new ProdutoBO();
		return produtoBO.listarProdutosMaisUsados();
	}
	
	public ArrayList<ProdutoMaisVendidoDTO> listarProdutosMaisVendidos() {
		ProdutoBO produtoBO = new ProdutoBO();
		return produtoBO.listarProdutosMaisVendidos();
	}

	public ArrayList<String> listaAnos(){
		ProdutoDAO produtoDAO = new ProdutoDAO();
		return produtoDAO.listaAnos();
	}
}
