package senac.estoque.controller;


import senac.estoque.model.bo.LacamentoBO;
import senac.estoque.model.vo.LancamentoVO;

public class LancamentoController {

    private LacamentoBO lacamentoBO;

    public boolean cadastrarLacamento(LancamentoVO lancamento){
        lacamentoBO = new LacamentoBO();

        return lacamentoBO.cadastrarLacamento(lancamento);
    }   
}
