package senac.estoque.controller;

import java.util.ArrayList;

import senac.estoque.model.bo.ProdutoBO;
import senac.estoque.model.vo.ProdutoVO;

public class ProdutoController {
    public boolean cadastrarProduto(ProdutoVO produtoVO) {
		ProdutoBO produtoBO = new ProdutoBO();
		return produtoBO.CadastrarProduto(produtoVO);
	}

	public ArrayList<ProdutoVO> listarProduto() {
		ProdutoBO produtoBO = new ProdutoBO();
		return produtoBO.listarProduto();
	}
}
