package senac.estoque.model.bo;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import senac.estoque.helpers.Constantes;
import senac.estoque.helpers.Utils;
import senac.estoque.model.dao.LancamentoDAO;
import senac.estoque.model.dto.LancamentoDTO;
import senac.estoque.model.vo.LancamentoVO;
import senac.estoque.model.vo.LogLancamentosVO;
import senac.estoque.seletores.SeletorLancamento;

public class LancamentoBO {

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
	public ArrayList<LancamentoDTO>listarLancamento(Integer limit, Integer offset) {
        lancamentoDAO = new LancamentoDAO();
		return lancamentoDAO.listarView(limit, offset);
    }   
    
    public ArrayList<LogLancamentosVO> listarLogLancamentos(){
        lancamentoDAO = new LancamentoDAO();
		return lancamentoDAO.listarLogLancamentos();
    }
    
    
    public boolean excluirLancamento(int id) {
    	LancamentoDAO lancamentoDAO = new LancamentoDAO();
    	if(lancamentoDAO.excluir(id) != 0) {
    		return true;
    	}
    	return false;
    }
    
    public ArrayList<LancamentoDTO> filtrarLancamentos(SeletorLancamento seletorLancamento) {
    	
    	LancamentoDAO lancamentoDAO = new LancamentoDAO();
    	Utils utils = new Utils();
    	String dt1 = seletorLancamento.getDataInicial();
    	String dt2 = seletorLancamento.getDataFinal();
    	int validacao = 0;
    	ArrayList<LancamentoDTO> filtroLancamentos = null;
    	
    	if(dt1.length() > 0 && dt2.length() > 0) {
    		if(!utils.compararDatas(dt1, dt2)) {
    			JOptionPane.showMessageDialog(null, "A data inicial deve ser inferior à data final!");
    			validacao++;
    		}
    	}
    	
    	if(dt1.length() > 0 && dt1.length() < 10) {
			validacao++;
    	}
    	
    	if(dt2.length() > 0 && dt2.length() < 10) {
			validacao++;
    	}
    	
    	if(validacao == 0) {
    		if(dt1.length() == 10) {
    			seletorLancamento.setDataInicial(utils.formatarDataParaSQL(dt1));
    		}
    		
    		if(dt2.length() == 10) {
    			seletorLancamento.setDataFinal(utils.formatarDataParaSQL(dt2));
    		}
    		
    		filtroLancamentos = lancamentoDAO.filtrarLancamentos(seletorLancamento);
    	}
    	
    	return filtroLancamentos;
    }
    
}
