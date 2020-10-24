package senac.estoque.model.bo;

import java.util.ArrayList;

import senac.estoque.model.dao.SetorDAO;
import senac.estoque.model.vo.SetorVO;

public class SetorBO {

    private SetorDAO setorDAO;

    public ArrayList<SetorVO> listarSetor() {

        setorDAO = new SetorDAO();

        return setorDAO.listar();
    }

}
