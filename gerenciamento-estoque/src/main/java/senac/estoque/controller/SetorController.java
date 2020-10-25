package senac.estoque.controller;

import java.util.ArrayList;

import senac.estoque.model.bo.SetorBO;
import senac.estoque.model.vo.SetorVO;

public class SetorController {

	private SetorBO setorBO;
	
	public boolean cadastrarSetor(SetorVO setor){
		setorBO = new SetorBO();

		return setorBO.cadastrarSetor(setor);
	}

    public ArrayList<SetorVO> listarSetor() {
		SetorBO setorBO = new SetorBO();
		return setorBO.listarSetor();
	}
}
