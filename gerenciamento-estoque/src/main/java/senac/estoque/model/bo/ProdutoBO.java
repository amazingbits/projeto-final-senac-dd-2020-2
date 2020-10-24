package senac.estoque.model.bo;

import javax.swing.JOptionPane;

import senac.estoque.model.dao.ProdutoDAO;
import senac.estoque.model.vo.ProdutoVO;

public class ProdutoBO {

   private ProdutoDAO produtoDAO;
    /**
	 * validar os dados de produto
	 * @param Produto
	 * @return
	 */
	public boolean validarProduto(ProdutoVO produtoVO) {

        produtoDAO = new ProdutoDAO();
        
        if (produtoDAO.encontrar(produtoVO.getDescricao()) != null ){
			JOptionPane.showMessageDialog(null, "O produto já foi cadastrado", "Erro", JOptionPane.ERROR_MESSAGE);

            return false;
        }

        if(produtoVO.getQuantidade() < 0 ) {
			JOptionPane.showMessageDialog(null, "Somente é permitido valores a cima ou igual a 0", "Erro", JOptionPane.ERROR_MESSAGE);
			return false;
		}
        
        if (produtoVO.getPreco() < 0){
			JOptionPane.showMessageDialog(null, "Somente é permitido valores a cima ou igual a 0", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (produtoVO.getDescricao().length() >= 3 ) {
            JOptionPane.showMessageDialog(null, "A descrição deve conter mais de 3 caracteres", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    
        if (produtoVO.getData_ultima_saida().isEmpty()){
            JOptionPane.showMessageDialog(null, "A data de saida deve ser preenchida", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (produtoVO.getData_ultima_entrada().isEmpty()){
            JOptionPane.showMessageDialog(null, "A data de entrada deve ser preenchida", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (produtoVO.getCategoria().getDescricao().length() >= 3){
            JOptionPane.showMessageDialog(null, "A categória deve conter mais de 3 caracteres", "Erro", JOptionPane.ERROR_MESSAGE);
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
}