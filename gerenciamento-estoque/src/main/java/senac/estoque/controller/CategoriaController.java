package senac.estoque.controller;

import java.util.ArrayList;

import senac.estoque.model.bo.CategoriaBO;
import senac.estoque.model.vo.CategoriaVO;

public class CategoriaController {
	
	public boolean cadastrarCategoria(CategoriaVO categoria) {
		CategoriaBO categoriaBO = new CategoriaBO();
		return categoriaBO.cadastrarCategoria(categoria);
	}
	
	public ArrayList<CategoriaVO> listarCategoria() {
		CategoriaBO categoriaBO = new CategoriaBO();
		return categoriaBO.listarCategoria();
	}

}
