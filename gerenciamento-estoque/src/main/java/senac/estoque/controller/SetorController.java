package senac.estoque.controller;

import java.util.ArrayList;

import senac.estoque.model.bo.SetorBO;
import senac.estoque.model.vo.SetorVO;
import senac.estoque.seletores.SeletorSetor;

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

	public boolean excluirSetor(SetorVO setorVO) {

		SetorBO setorBO = new SetorBO();
		return setorBO.excluirSetor(setorVO);
	}

	public boolean editarSetor(SetorVO setorVO) {
		SetorBO setorBO = new SetorBO();

		return setorBO.editarSetor(setorVO);
	}

	public ArrayList<SetorVO> listarSetorSeletor(SeletorSetor seletorSetor) {


			SetorBO setorBO = new SetorBO();

		return setorBO.listarSetorSeletor(seletorSetor);
	}
	
	public boolean desativar(SetorVO setorVO) {
		SetorBO setorBO = new SetorBO();
		return setorBO.desativar(setorVO);
	}
}
