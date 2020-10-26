package senac.estoque.model.bo;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import senac.estoque.helpers.Constantes;
import senac.estoque.model.dao.LancamentoDAO;
import senac.estoque.model.dto.LancamentoDTO;
import senac.estoque.model.vo.LancamentoVO;
import senac.estoque.model.vo.LogLancamentosVO;

public class LacamentoBO {

    private LancamentoDAO lancamentoDAO;
    private Constantes constantes;

    public boolean validaLancamento(LancamentoVO lancamento){

        if(lancamento.getProduto().getId() < 0){
            JOptionPane.showMessageDialog(null, constantes.MENSAGEM_VALIDACAO_FK_PRODUTO,"Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(lancamento.getSetor().getId() < 0){
            JOptionPane.showMessageDialog(null, constantes.MENSAGEM_VALIDACAO_FK_SETOR,"Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(lancamento.getTipo().getId() < 0){
            JOptionPane.showMessageDialog(null,constantes.MENSAGEM_VALIDACAO_FK_TIPO,"Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(lancamento.getQuantidade() < 0){
            JOptionPane.showMessageDialog(null, constantes.MENSAGEM_VALIDACAO_LANCAMENTO_QUANTIDADE,"Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }
    public boolean cadastrarLacamento(LancamentoVO lancamento){
        if(validaLancamento(lancamento)){
            lancamentoDAO = new LancamentoDAO();

            return  lancamentoDAO.cadastrar(lancamento) == 1? true:false;
        }else {
            JOptionPane.showMessageDialog(null, constantes.MENSAGEM_FALHA_CADASTRO_LANCAMENTO,"Erro", JOptionPane.ERROR_MESSAGE);

            return false;

        }
    }
	public ArrayList<LancamentoDTO>listarLancamento() {
        lancamentoDAO = new LancamentoDAO();
		return lancamentoDAO.listarView();
    }   
    
    public ArrayList<LogLancamentosVO> listarLogLancamentos(){
        lancamentoDAO = new LancamentoDAO();
		return lancamentoDAO.listarLogLancamentos();
    }
    
}
