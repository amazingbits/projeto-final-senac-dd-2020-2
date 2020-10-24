package senac.estoque.view;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import senac.estoque.model.vo.ProdutoVO;
import senac.estoque.model.vo.SetorVO;
import senac.estoque.model.vo.TipoLancamentoVO;

public class CadastroLancamentos extends JPanel {

    private JLabel lblTitle;
    private JLabel lblProduto;
    private JLabel lblSetor;
    private JLabel lblTipo;
    private JLabel lblQuantide;

    private JComboBox<String> cbProduto;
    private JComboBox<String> cbSetor;
    private JComboBox<String> cbTipo;

    private ArrayList<ProdutoVO> listProduto;
    private ArrayList<SetorVO> listSetor;
    private ArrayList<TipoLancamentoVO> listTipo;

    private JTextField tfQuantide;

    private JButton bCadastrar;

    public CadastroLancamentos() {
        setLayout(null);


    }


}
