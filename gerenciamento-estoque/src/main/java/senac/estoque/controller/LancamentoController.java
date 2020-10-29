package senac.estoque.controller;


import java.util.ArrayList;

import senac.estoque.model.bo.LancamentoBO;
import senac.estoque.model.dto.LancamentoDTO;
import senac.estoque.model.vo.LancamentoVO;
import senac.estoque.model.vo.LogLancamentosVO;
import senac.estoque.seletores.SeletorLancamento;

public class LancamentoController {

    private LancamentoBO lacamentoBO;

    public boolean cadastrarLacamento(LancamentoVO lancamento){
        lacamentoBO = new LancamentoBO();

        return lacamentoBO.cadastrarLacamento(lancamento);
    }   

    public ArrayList<LancamentoDTO> listarLancamento(SeletorLancamento seletorLancamento){
        lacamentoBO = new LancamentoBO();
        return lacamentoBO.listarLancamento(seletorLancamento);
    }

    public ArrayList<LogLancamentosVO> listarLogLancamentos(SeletorLancamento seletorLancamento){
        lacamentoBO = new LancamentoBO();
		return lacamentoBO.listarLogLancamentos(seletorLancamento);
    }
    
    public boolean excluirLancamento(int id) {
    	LancamentoBO lancamentoBO = new LancamentoBO();
    	if(lancamentoBO.excluirLancamento(id)) {
    		return true;
    	}
    	return false;
    }
    
    public ArrayList<LancamentoDTO> filtrarLancamentos(SeletorLancamento seletorLancamento) {
    	LancamentoBO lancamentoBO = new LancamentoBO();
    	return lancamentoBO.filtrarLancamentos(seletorLancamento);
    }
}
