package senac.estoque.model.bo;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import senac.estoque.helpers.Caracteres;
import senac.estoque.model.dao.CategoriaDAO;
import senac.estoque.model.vo.CategoriaVO;
import senac.estoque.helpers.Constantes;

public class CategoriaBO {
	
	private CategoriaDAO categoriaDAO = new CategoriaDAO();
	private Caracteres caracteres = new Caracteres();
	private Constantes constantes;
	
	/**
	 * validar os dados da categoria
	 * @param categoria
	 * @return
	 */
	public boolean validarCategoria(CategoriaVO categoria) {
		
		String nomeCategoria = caracteres.permitirSomenteLetras(categoria.getDescricao());
		
		if(nomeCategoria.length() < 3) {
			JOptionPane.showMessageDialog(null, constantes.MENSAGEM_VALIDACAO_NOME_CATEGORIA, "Erro", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		if(categoriaDAO.encontrarCategoriaPeloNome(nomeCategoria)) {
			JOptionPane.showMessageDialog(null, constantes.MENSAGEM_VALIDACAO_SE_EXISTE_CATEGORIA, "Erro", JOptionPane.ERROR_MESSAGE);
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
				JOptionPane.showMessageDialog(null, constantes.MENSAGEM_CATEGORIA_CADASTRADA);
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

	/**
	 * retorna true quando deletado e false quando não deletado
	 * @return
	 */
	public boolean excluirCategoria(CategoriaVO categoriaVO) {
		categoriaDAO = new CategoriaDAO();

		return categoriaDAO.excluir(categoriaVO.getId()) == 1?true:false;
	}

}
