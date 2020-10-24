package senac.estoque.controller;

import java.util.ArrayList;

import senac.estoque.model.bo.SetorBO;
import senac.estoque.model.vo.SetorVO;

public class SetorController {
    public ArrayList<SetorVO> listarSetor() {
		SetorBO setorBO = new SetorBO();
		return setorBO.listarSetor();
	}
}
