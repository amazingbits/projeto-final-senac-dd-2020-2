package senac.estoque.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import senac.estoque.controller.CategoriaController;
import senac.estoque.controller.ProdutoController;
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
    private JTextField tfPrecoUnidade;
    private JTextField tfQuantidade;

    private JComboBox<String> cbCategoria;
    private JButton bCadastrar;
    private ArrayList<Integer> listIdCategorias = new ArrayList<Integer>();
    private CategoriaController categoriaController;
    private ProdutoController produtoController;

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

        tfPrecoUnidade = new JTextField();
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

        tfQuantidade = new JTextField();
        tfQuantidade.setBounds(420, 190, 200, 36);
        add(tfQuantidade);

        bCadastrar = new JButton("Cadastrar");
        bCadastrar.setBounds(10, 300, 200, 36);
        add(bCadastrar);

        bCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ProdutoVO produtoVO = new ProdutoVO();
                CategoriaVO categoriaVO = new CategoriaVO();
                produtoController = new ProdutoController();

                produtoVO.setPreco(Float.parseFloat(tfPrecoUnidade.getText()));
                produtoVO.setDescricao(tfNomeProduto.getText());
                produtoVO.setQuantidade(Integer.parseInt(tfQuantidade.getText()));
                categoriaVO.setDescricao("" + cbCategoria.getItemAt(cbCategoria.getSelectedIndex()) + "");
                categoriaVO.setId(listIdCategorias.get(cbCategoria.getSelectedIndex()));
                produtoVO.setCategoria(categoriaVO);

                if (produtoController.cadastrarProduto(produtoVO)) {
                    JOptionPane.showMessageDialog(null, "Sucesso em cadastrar o produto");
                }else{
                    JOptionPane.showMessageDialog(null, "Erro em cadastrar o produto", "Erro", JOptionPane.ERROR_MESSAGE);

                }

            }
        });

    }

}