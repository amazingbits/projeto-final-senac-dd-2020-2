package senac.estoque.model.bo;

import java.util.ArrayList;

import senac.estoque.model.dao.TipoLancamentoDAO;
import senac.estoque.model.vo.TipoLancamentoVO;

public class TipoLancamentoBO {
    private TipoLancamentoDAO tipoLancamentoDAO;

    public ArrayList<TipoLancamentoVO> listarTipoLancamento() {

        tipoLancamentoDAO = new TipoLancamentoDAO();

        return tipoLancamentoDAO.listar();
    }
}
