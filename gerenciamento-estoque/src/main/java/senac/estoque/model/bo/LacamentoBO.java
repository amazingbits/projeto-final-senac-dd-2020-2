package senac.estoque.model.bo;

import javax.swing.JOptionPane;

import senac.estoque.model.dao.LancamentoDAO;
import senac.estoque.model.vo.LancamentoVO;

public class LacamentoBO {

    private LancamentoDAO lancamentoDAO;

    public boolean validaLancamento(LancamentoVO lancamento){

        if(lancamento.getProduto().getId() < 0){
            JOptionPane.showMessageDialog(null, "O produto não pode estar vazio","Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(lancamento.getSetor().getId() < 0){
            JOptionPane.showMessageDialog(null, "O setor não pode estar vazio","Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(lancamento.getTipo().getId() < 0){
            JOptionPane.showMessageDialog(null, "O tipo de lancamento não pode estar vazio","Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(lancamento.getQuantidade() < 0){
            JOptionPane.showMessageDialog(null, "A quantidade não pode ser menor que 0 e somente aceita números","Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }
    public boolean cadastrarLacamento(LancamentoVO lancamento){
        if(validaLancamento(lancamento)){
            lancamentoDAO = new LancamentoDAO();

            return  lancamentoDAO.cadastrar(lancamento) == 1? true:false;
        }else {
            JOptionPane.showMessageDialog(null, "Não foi possivel cadastrar o Lancamento","Erro", JOptionPane.ERROR_MESSAGE);

            return false;

        }
    }   
    
}
