package senac.estoque.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.JSpinner.DefaultEditor;

import senac.estoque.controller.CategoriaController;
import senac.estoque.controller.ProdutoController;
import senac.estoque.helpers.Constantes;
import senac.estoque.helpers.Formatos;
import senac.estoque.model.vo.CategoriaVO;
import senac.estoque.model.vo.ProdutoVO;

public class CadastrarProduto extends JPanel {
    private static final long serialVersionUID = 1L;

    private JLabel lblTitle;
    private JLabel lblNomeProduto;
    private JLabel lblCategoria;
    private JLabel lblPrecoUnidade;
    private JLabel lblQuantidade;

    private JTextField tfNomeProduto;
    private JFormattedTextField tfPrecoUnidade;

    private JSpinner sQuantidade;

    private JComboBox<String> cbCategoria;
    private JButton bCadastrar;
    private ArrayList<Integer> listIdCategorias = new ArrayList<Integer>();
    private CategoriaController categoriaController;
    private ProdutoController produtoController;

    private Formatos formato = new Formatos();
    private Constantes constantes;

    public CadastrarProduto() {
        setLayout(null);

        lblTitle = new JLabel("CADASTRO DE PRODUTOS");
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setBounds(10, 11, 600, 36);
        add(lblTitle);

        lblNomeProduto = new JLabel("Nome do Produtos");
        lblNomeProduto.setBounds(10, 50, 600, 36);
        add(lblNomeProduto);

        tfNomeProduto = new JTextField();
        tfNomeProduto.setBounds(10, 80, 400, 36);
        add(tfNomeProduto);

        lblPrecoUnidade = new JLabel("Preco Un. R$");
        lblPrecoUnidade.setBounds(420, 50, 200, 36);
        add(lblPrecoUnidade);

        tfPrecoUnidade = new JFormattedTextField();
        (tfPrecoUnidade).setFormatterFactory(formato.money());

        tfPrecoUnidade.setBounds(420, 80, 200, 36);
        add(tfPrecoUnidade);

        lblCategoria = new JLabel("Categoria");
        lblCategoria.setBounds(10, 150, 200, 36);
        add(lblCategoria);

        cbCategoria = new JComboBox<String>();
        categoriaController = new CategoriaController();

        for (int i = 0; i < categoriaController.listarCategoria().size(); i++) {
            CategoriaVO categoria = categoriaController.listarCategoria().get(i);
            listIdCategorias.add(categoria.getId());
            cbCategoria.addItem(categoria.getDescricao());
        }

        cbCategoria.setBounds(10, 190, 400, 36);
        add(cbCategoria);

        lblQuantidade = new JLabel("Qtde");
        lblQuantidade.setBounds(420, 150, 200, 36);
        add(lblQuantidade);

        Integer value = new Integer(1);
        Integer min = new Integer(1);
        Integer max = new Integer(100);
        Integer step = new Integer(1);
        SpinnerNumberModel spinnerModel = new SpinnerNumberModel(value, min, max, step);
        sQuantidade = new JSpinner(spinnerModel);
        ((DefaultEditor) sQuantidade.getEditor()).getTextField().setEditable(false);
        sQuantidade.setBounds(420, 190, 200, 36);
        add(sQuantidade);

        bCadastrar = new JButton("Cadastrar");
        bCadastrar.setBounds(10, 237, 200, 36);
        add(bCadastrar);

        bCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                /**
                 * tratando o valor do preço para evitar dar crash no programa
                 */
                String preco = tfPrecoUnidade.getText();
                if (preco.trim().length() == 0 || preco.equalsIgnoreCase(",00")) {
                    preco = "0";
                } else if (preco.trim().length() <= 6) {
                    preco = tfPrecoUnidade.getText().replace(",", ".");
                } else {
                    preco = tfPrecoUnidade.getText().replace(".", "").replace(",", ".");
                }
                System.out.println(preco);

                ProdutoVO produtoVO = new ProdutoVO();
                CategoriaVO categoriaVO = new CategoriaVO();
                produtoController = new ProdutoController();

                produtoVO.setPreco(Float.parseFloat(preco));
                produtoVO.setDescricao(tfNomeProduto.getText());
                produtoVO.setQuantidade((int) sQuantidade.getValue());
                categoriaVO.setDescricao("" + cbCategoria.getItemAt(cbCategoria.getSelectedIndex()) + "");
                categoriaVO.setId(listIdCategorias.get(cbCategoria.getSelectedIndex()));
                produtoVO.setCategoria(categoriaVO);

                System.out.println(produtoVO.toString());

                if (produtoController.cadastrarProduto(produtoVO)) {
                    JOptionPane.showMessageDialog(null, constantes.MENSAGEM_SUCESSO_CADASTRO_PRODUTO);
                } else {
                    JOptionPane.showMessageDialog(null, constantes.MENSAGEM_FALHA_CADASTRO_PRODUTO, "Erro",
                            JOptionPane.ERROR_MESSAGE);

                }

            }
        });

    }

}