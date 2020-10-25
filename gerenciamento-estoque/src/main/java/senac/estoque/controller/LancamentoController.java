package senac.estoque.controller;


import java.util.ArrayList;

import senac.estoque.model.bo.LacamentoBO;
import senac.estoque.model.dto.LancamentoDTO;
import senac.estoque.model.vo.LancamentoVO;

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
}
