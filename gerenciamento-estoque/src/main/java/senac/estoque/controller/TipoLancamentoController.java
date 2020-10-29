package senac.estoque.controller;

import java.util.ArrayList;

import senac.estoque.model.vo.TipoLancamentoVO;
import senac.estoque.model.bo.TipoLancamentoBO;

public class TipoLancamentoController {
    public ArrayList<TipoLancamentoVO> listarTipoLancamento() {
        TipoLancamentoBO tipoLancamentoBO = new TipoLancamentoBO();
        return tipoLancamentoBO.listarTipoLancamento();
    }
}
