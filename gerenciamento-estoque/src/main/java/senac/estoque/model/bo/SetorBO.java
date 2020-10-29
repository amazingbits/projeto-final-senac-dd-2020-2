package senac.estoque.model.bo;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import senac.estoque.helpers.Constantes;
import senac.estoque.model.dao.SetorDAO;
import senac.estoque.model.vo.SetorVO;
import senac.estoque.seletores.SeletorSetor;

public class SetorBO {

    private SetorDAO setorDAO;
    private Constantes constantes;


    public boolean validarSetor(SetorVO setor){

        setorDAO = new SetorDAO();
        
        if (setorDAO.encontrar(setor.getDescricao()) != null ){
            
			JOptionPane.showMessageDialog(null, constantes.MENSAGEM_VALIDACAO_SE_EXISTE_SETOR, "Erro", JOptionPane.ERROR_MESSAGE);

            return false;
        }

        if (setor.getDescricao().length() < 3 ){
            JOptionPane.showMessageDialog(null, constantes.MENSAGEM_VALIDACAO_SETOR_DESCRICAO, "Erro", JOptionPane.ERROR_MESSAGE);

            return false;
        }

        return true;
    }

    public boolean cadastrarSetor(SetorVO  setor){
        if (this.validarSetor(setor)){
            setorDAO = new SetorDAO();
            return setorDAO.cadastrar(setor) == 1?true:false;
        }else {
            JOptionPane.showMessageDialog(null, constantes.MENSAGEM_FALHA_CADASTRO_SETOR, "Erro", JOptionPane.ERROR_MESSAGE);

            return false;
        }
    }
    public ArrayList<SetorVO> listarSetor() {

        setorDAO = new SetorDAO();

        return setorDAO.listar();
    }

	public boolean excluirSetor(SetorVO setorVO) {
        setorDAO = new SetorDAO();

        return setorDAO.excluir(setorVO.getId()) == 1?true:false;
	}

	public boolean editarSetor(SetorVO setorVO) {

        int verificacao = 0;
		setorDAO = new SetorDAO();
		String nomeAtual = setorDAO.encontrar(setorVO.getId()).getDescricao();

		if(nomeAtual.equalsIgnoreCase(setorVO.getDescricao())) {
			JOptionPane.showMessageDialog(null, "O nome do setor não pode ser o mesmo que o nome atual!", "Erro", JOptionPane.ERROR_MESSAGE);
			verificacao++;
		}

		if(setorDAO.encontrar(setorVO.getDescricao()).equals(null) ) {
			JOptionPane.showMessageDialog(null, "Este nome pertence a outra setor no banco de dados. Tente outro nome!", "Erro", JOptionPane.ERROR_MESSAGE);
			verificacao++;
		}

		if(verificacao == 0) {
			boolean editar = setorDAO.atualizar(setorVO,setorVO.getId()) == 1?true:false;
			if(editar) return true; 
		}

		return false;
		
	}

	public ArrayList<SetorVO> listarSetorSeletor(SeletorSetor seletorSetor) {
        setorDAO = new SetorDAO();

		return setorDAO.listarView(seletorSetor);
	}

}
