package senac.estoque.model.bo;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import senac.estoque.helpers.Caracteres;
import senac.estoque.model.dao.CategoriaDAO;
import senac.estoque.model.vo.CategoriaVO;
import senac.estoque.seletores.SeletorCategoria;
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
		
		categoria.setDescricao(nomeCategoria);
		boolean desativado = this.categoriaDAO.verificarSeEstaDesativado(categoria);
		if(desativado) {
			this.categoriaDAO.ativar(categoria); 
			return true;
		}
		
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

	public boolean editarCategoria(CategoriaVO categoriaVO) {
		int verificacao = 0;
		categoriaDAO = new CategoriaDAO();
		String nomeAtual = categoriaDAO.encontrar(categoriaVO.getId()).getDescricao();

		if(nomeAtual.equalsIgnoreCase(categoriaVO.getDescricao())) {
			JOptionPane.showMessageDialog(null, "O nome da categoria não pode ser o mesmo que o nome atual!", "Erro", JOptionPane.ERROR_MESSAGE);
			verificacao++;
		}

		if(categoriaDAO.encontrarCategoriaPeloNome(categoriaVO.getDescricao()) && verificacao == 0) {
			JOptionPane.showMessageDialog(null, "Este nome pertence a outra categoria no banco de dados. Tente outro nome!", "Erro", JOptionPane.ERROR_MESSAGE);
			verificacao++;
		}

		if(verificacao == 0) {
			boolean editar = categoriaDAO.atualizar(categoriaVO,categoriaVO.getId()) == 1?true:false;
			if(editar) return true; 
		}

		return false;
	}

	public ArrayList<CategoriaVO> listarCategoriaSeletor(SeletorCategoria seletorCategoria) {
		categoriaDAO = new CategoriaDAO();

		return categoriaDAO.listarView(seletorCategoria);

	}
	
	public boolean desativar(CategoriaVO categoriaVO) {
		CategoriaDAO categoriaDAO = new CategoriaDAO();
		return categoriaDAO.exclusaoLogica(categoriaVO.getId());
	}

}
