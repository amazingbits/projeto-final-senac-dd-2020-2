package senac.estoque.model.bo;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import senac.estoque.helpers.Constantes;
import senac.estoque.model.dao.SetorDAO;
import senac.estoque.model.vo.SetorVO;

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

}
