package senac.estoque.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import senac.estoque.model.vo.LancamentoVO;
import senac.estoque.model.vo.ProdutoVO;
import senac.estoque.model.vo.SetorVO;
import senac.estoque.model.vo.TipoLancamentoVO;
import senac.estoque.controller.LancamentoController;
import senac.estoque.controller.ProdutoController;
import senac.estoque.controller.SetorController;
import senac.estoque.controller.TipoLancamentoController;

public class CadastroLancamentos extends JPanel {

    private JLabel lblTitle;
    private JLabel lblProduto;
    private JLabel lblSetor;
    private JLabel lblTipo;
    private JLabel lblQuantidade;

    private JComboBox<String> cbProduto;
    private JComboBox<String> cbSetor;
    private JComboBox<String> cbTipo;

    private ArrayList<ProdutoVO> listProduto;
    private ArrayList<SetorVO> listSetor;
    private ArrayList<TipoLancamentoVO> listTipo;

    private JTextField tfQuantidade;

    private JButton bCadastrar;

    private ProdutoController produtoController;
    private SetorController setorController;
    private TipoLancamentoController tipoLancamentoController;

    public CadastroLancamentos() {
        setLayout(null);

        lblTitle = new JLabel("CADASTRO DE LANÇAMENTOS");
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setBounds(10, 11, 600, 36);
        add(lblTitle);

        lblProduto = new JLabel("Produto");
        lblProduto.setBounds(10, 50, 600, 36);
        add(lblProduto);

        cbProduto = new JComboBox<String>();
        produtoController = new ProdutoController();
        listProduto = new ArrayList<ProdutoVO>();

        for (int i = 0; i < produtoController.listarProduto().size(); i++) {
            cbProduto.addItem(produtoController.listarProduto().get(i).getDescricao());
            listProduto.add(produtoController.listarProduto().get(i));
        }

        cbProduto.setBounds(10, 80, 400, 36);
        add(cbProduto);

        lblSetor = new JLabel("Setor");
        lblSetor.setBounds(420, 50, 200, 36);
        add(lblSetor);

        cbSetor = new JComboBox<String>();
        setorController = new SetorController();

        listSetor = new ArrayList<SetorVO>();
        for (int i = 0; i < setorController.listarSetor().size(); i++) {
            cbSetor.addItem(setorController.listarSetor().get(i).getDescricao());
            listSetor.add(setorController.listarSetor().get(i));

        }

        cbSetor.setBounds(420, 80, 200, 36);
        add(cbSetor);

        lblTipo = new JLabel("Tipo");
        lblTipo.setBounds(10, 150, 200, 36);
        add(lblTipo);

        cbTipo = new JComboBox<String>();
        listTipo = new ArrayList<TipoLancamentoVO>();
        tipoLancamentoController = new TipoLancamentoController();

        for (int i = 0; i < tipoLancamentoController.listarTipoLancamento().size(); i++) {
            cbTipo.addItem(tipoLancamentoController.listarTipoLancamento().get(i).getDescricao());
            listTipo.add(tipoLancamentoController.listarTipoLancamento().get(i));
        }

        cbTipo.setBounds(10, 190, 400, 36);
        add(cbTipo);

        lblQuantidade = new JLabel("Qtde");
        lblQuantidade.setBounds(420, 150, 200, 36);
        add(lblQuantidade);

        tfQuantidade = new JTextField();
        tfQuantidade.setBounds(420, 190, 200, 36);
        add(tfQuantidade);

        bCadastrar = new JButton("Cadastrar");
        bCadastrar.setBounds(10, 300, 200, 36);
        add(bCadastrar);

        bCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LancamentoVO lancamentoVO = new LancamentoVO();
                ProdutoVO produtoVO = new ProdutoVO();
                SetorVO setorVO = new SetorVO();
                TipoLancamentoVO tipoLancamentoVO = new TipoLancamentoVO();
                LancamentoController lancamentoController = new LancamentoController();

                produtoVO.setId(listProduto.get(cbProduto.getSelectedIndex()).getId());
                setorVO.setId(listSetor.get(cbSetor.getSelectedIndex()).getId());
                tipoLancamentoVO.setId(listTipo.get(cbTipo.getSelectedIndex()).getId());
                lancamentoVO.setQuantidade(Integer.parseInt(tfQuantidade.getText()));
                lancamentoVO.setProduto(produtoVO);
                lancamentoVO.setSetor(setorVO);
                lancamentoVO.setTipo(tipoLancamentoVO);

                if (lancamentoController.cadastrarLacamento(lancamentoVO)) {
                    JOptionPane.showMessageDialog(null, "Sucesso em cadastrar Lancamento");
                }
            }
        });

    }

}
