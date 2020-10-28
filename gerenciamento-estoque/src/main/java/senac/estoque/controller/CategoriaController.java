package senac.estoque.controller;

import java.util.ArrayList;

import senac.estoque.model.bo.CategoriaBO;
import senac.estoque.model.vo.CategoriaVO;
import senac.estoque.seletores.SeletorCategoria;

public class CategoriaController {
	
	public boolean cadastrarCategoria(CategoriaVO categoria) {
		CategoriaBO categoriaBO = new CategoriaBO();
		return categoriaBO.cadastrarCategoria(categoria);
	}
	
	public ArrayList<CategoriaVO> listarCategoria() {
		CategoriaBO categoriaBO = new CategoriaBO();
		return categoriaBO.listarCategoria();
	}

	public boolean excluirCategoria(CategoriaVO categoriaVO) {
		CategoriaBO categoriaBO = new CategoriaBO();
		return categoriaBO.excluirCategoria(categoriaVO);
	}

	public boolean editarCategoria(CategoriaVO categoriaVO) {
		CategoriaBO categoriaBO = new CategoriaBO();

		return categoriaBO.editarCategoria(categoriaVO);
	}

	public ArrayList<CategoriaVO> listarCategoriaSeletor(SeletorCategoria seletorCategoria) {
		CategoriaBO categoriaBO = new CategoriaBO();

		return categoriaBO.listarCategoriaSeletor(seletorCategoria);
	}

}
