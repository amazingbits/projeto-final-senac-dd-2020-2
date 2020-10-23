package senac.estoque.model.bo;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import senac.estoque.helpers.Caracteres;
import senac.estoque.model.dao.CategoriaDAO;
import senac.estoque.model.vo.CategoriaVO;

public class CategoriaBO {
	
	private CategoriaDAO categoriaDAO = new CategoriaDAO();
	private Caracteres caracteres = new Caracteres();
	
	/**
	 * validar os dados da categoria
	 * @param categoria
	 * @return
	 */
	public boolean validarCategoria(CategoriaVO categoria) {
		
		String nomeCategoria = caracteres.permitirSomenteLetras(categoria.getDescricao());
		
		if(nomeCategoria.length() < 3) {
			JOptionPane.showMessageDialog(null, "A categoria deve ter pelo menos 3 caracteres!", "Erro", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		if(categoriaDAO.encontrarCategoriaPeloNome(nomeCategoria)) {
			JOptionPane.showMessageDialog(null, "Já existe uma categoria com este nome no banco de dados!", "Erro", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		return true;
		
	}
	
	/**
	 * cadastrar a categoria se os dados estiverem validados
	 * @param categoria
	 * @return
	 */
	public boolean cadastrarCategoria(CategoriaVO categoria) {
		
		if(this.validarCategoria(categoria)) {
			int cadastrar = categoriaDAO.cadastrar(categoria);
			if(cadastrar != 0) {
				JOptionPane.showMessageDialog(null, "Categoria cadastrada com sucesso!");
				return true;
			}
		}
		return false;
	}
	
	/**
	 * retorna a lista de categorias
	 * @return
	 */
	public ArrayList<CategoriaVO> listarCategoria() {
		return this.categoriaDAO.listar();
	}

}
