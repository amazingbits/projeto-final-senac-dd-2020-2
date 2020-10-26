package senac.estoque.model.bo;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import senac.estoque.helpers.Constantes;
import senac.estoque.model.dao.ProdutoDAO;
import senac.estoque.model.dto.ProdutoMaisUsadosDTO;
import senac.estoque.model.dto.ProdutoMaisVendidoDTO;
import senac.estoque.model.vo.LogProdutosVO;
import senac.estoque.model.vo.ProdutoVO;

public class ProdutoBO {

   private ProdutoDAO produtoDAO;
   private Constantes constantes;
    /**
	 * validar os dados de produto
	 * @param Produto
	 * @return
	 */
	public boolean validarProduto(ProdutoVO produtoVO) {

        produtoDAO = new ProdutoDAO();
        
        if (produtoDAO.encontrar(produtoVO.getDescricao()) != null ){
            
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
}
