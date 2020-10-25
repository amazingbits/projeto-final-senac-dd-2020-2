package senac.estoque.controller;


import java.util.ArrayList;

import senac.estoque.model.bo.LacamentoBO;
import senac.estoque.model.dto.LancamentoDTO;
import senac.estoque.model.vo.LancamentoVO;
import senac.estoque.model.vo.LogLancamentosVO;

public class LancamentoController {

    private LacamentoBO lacamentoBO;

    public boolean cadastrarLacamento(LancamentoVO lancamento){
        lacamentoBO = new LacamentoBO();

        return lacamentoBO.cadastrarLacamento(lancamento);
    }   

    public ArrayList<LancamentoDTO> listarLancamento(){
            lacamentoBO = new LacamentoBO();
        return lacamentoBO.listarLancamento();
    }

    public ArrayList<LogLancamentosVO> listarLogLancamentos(){
        lacamentoBO = new LacamentoBO();
		return lacamentoBO.listarLogLancamentos();
    }
}
