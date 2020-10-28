package senac.estoque.model.bo;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import senac.estoque.helpers.Constantes;
import senac.estoque.model.dao.ProdutoDAO;
import senac.estoque.model.dto.ProdutoDTO;
import senac.estoque.model.dto.ProdutoMaisUsadosDTO;
import senac.estoque.model.dto.ProdutoMaisVendidoDTO;
import senac.estoque.model.vo.LogProdutosVO;
import senac.estoque.model.vo.ProdutoVO;
import senac.estoque.seletores.SeletorProduto;

public class ProdutoBO {

   private ProdutoDAO produtoDAO;
   private Constantes constantes;
    /**
	 * validar os dados de produto
	 * @param Produto
	 * @return
	 */
	public boolean validarProduto(ProdutoVO produtoVO) {

        ProdutoDAO produtoDAO = new ProdutoDAO();
        
        if (produtoDAO.encontrarPorNome(produtoVO.getDescricao()) != null ){
            
			JOptionPane.showMessageDialog(null, constantes.MENSAGEM_VALIDACAO_SE_EXISTE_PRODUTO, "Erro", JOptionPane.ERROR_MESSAGE);

            return false;
        }

        if(produtoVO.getQuantidade() < 0 ) {
			JOptionPane.showMessageDialog(null,constantes.MENSAGEM_VALIDACAO_PRODUTO_QUANTIDADE , "Erro", JOptionPane.ERROR_MESSAGE);
			return false;
		}
        
        if (produtoVO.getPreco() < 0){
			JOptionPane.showMessageDialog(null, constantes.MENSAGEM_VALIDACAO_PRODUTO_PRECO, "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (produtoVO.getDescricao().length() <= 3 ) {
            JOptionPane.showMessageDialog(null, constantes.MENSAGEM_VALIDACAO_PRODUTO_DESCRICAO, "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (produtoVO.getCategoria().getDescricao().length() <= 3){
            JOptionPane.showMessageDialog(null, constantes.MENSAGEM_VALIDACAO_PRODUTO_CATEGORIA_DESCRICAO, "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }
	
	public boolean editarProduto(ProdutoVO produtoVO) {
		int verificacao = 0;
		ProdutoDAO eProd = new ProdutoDAO();
		String nomeAtual = eProd.encontrar(produtoVO.getId()).getDescricao();
	
		if(nomeAtual.equalsIgnoreCase(produtoVO.getDescricao())) {
			JOptionPane.showMessageDialog(null, "O nome do produto não pode ser o mesmo que o nome atual!", "Erro", JOptionPane.ERROR_MESSAGE);
			verificacao++;
		}
		
	
		
		if(eProd.verificarSeExistePorNome(produtoVO.getDescricao())) {
			JOptionPane.showMessageDialog(null, "Este nome pertence a outro produto no banco de dados. Tente outro nome!", "Erro", JOptionPane.ERROR_MESSAGE);
			verificacao++;
		}
		
		if(verificacao == 0) {
			boolean editar = eProd.atualizar(produtoVO);
			if(editar) return true; 
		}
		return false;
	}
	
	
	
    public boolean CadastrarProduto(ProdutoVO produtoVO){

        boolean resultado = false;

        if (validarProduto(produtoVO)){
            produtoDAO = new ProdutoDAO();

            resultado = produtoDAO.cadastrar(produtoVO) == 1?true:false; ;

        }else {
            resultado = false;
        }

        return resultado;
        
    }
    
    
    
	public ArrayList<ProdutoVO> listarProduto() {
        produtoDAO = new ProdutoDAO();

		return  produtoDAO.listar();
    }
	
	
	public ArrayList<ProdutoDTO> listarProdutoSeletor(SeletorProduto seletorProduto) {
		ProdutoDAO produtoDAO = new ProdutoDAO();
		return produtoDAO.listarView(seletorProduto);
	}
	
	
	public ArrayList<ProdutoMaisVendidoDTO> listarProdutoMaisVendidos() {
        
        produtoDAO = new ProdutoDAO();

		return  produtoDAO.listarMaisVendidos();
	}
	
	
    
    public ArrayList<LogProdutosVO> listaLogProdutos(){
        produtoDAO = new ProdutoDAO();

		return  produtoDAO.listaLogProdutos();
    }
    
    
    
	public ArrayList<ProdutoMaisUsadosDTO> listarMaisUsados() {
        produtoDAO = new ProdutoDAO();

		return  produtoDAO.listarMaisUsados();
	}

	public boolean excluir(ProdutoVO produtoVO) {
		produtoDAO = new ProdutoDAO();

		return  produtoDAO.excluir(produtoVO.getId()) == 1?true:false;
	}
}
