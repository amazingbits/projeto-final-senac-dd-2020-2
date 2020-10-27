package senac.estoque.view;

import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.Dimension;

import senac.estoque.controller.ProdutoController;
import senac.estoque.model.vo.ProdutoVO;
import javax.swing.JTextField;
import javax.swing.JButton;

public class ListarProdutos extends JPanel {
	private JTextField txtNomeProduto;
    
	/**
	 * Create the panel.
	 */
	public ListarProdutos() {
		setLayout(null);
		
		JLabel lblTitle = new JLabel("LISTAR PRODUTOS");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(10, 11, 600, 36);
		add(lblTitle);
		
		/**
		 * carregar lista de categorias
		 */
		ProdutoController produtoController = new ProdutoController();
		ArrayList<ProdutoVO> produtos = produtoController.listarProduto();
		/* ==================================================================== */
		
		//definir colunas
		String[] colunas = {"ID", "Descrição"};
		
		//setando modelo padrão de tabela
		DefaultTableModel modeloTabela = new DefaultTableModel(colunas, 0);
		
		//construindo a tabela seguindo o modelo criado
		JTable tabela = new JTable(modeloTabela);
		DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
		tabela.setPreferredScrollableViewportSize(new Dimension(500,50));
		tabela.setFillsViewportHeight(true);
		
		//populando a tabela
		for(int i = 0; i < produtos.size(); i++) {
			modelo.addRow(new Object[] {produtos.get(i).getId(), produtos.get(i).getDescricao()});
		}
		
		//imprimindo a tabela na tela
		JScrollPane scrollPane = new JScrollPane(tabela);
		scrollPane.setBounds(10, 123, 610, 239);
		add(scrollPane);
		
		JLabel lblNomeProduto = new JLabel("Nome do produto");
		lblNomeProduto.setBounds(10, 43, 97, 14);
		add(lblNomeProduto);
		
		txtNomeProduto = new JTextField();
		txtNomeProduto.setBounds(10, 58, 209, 36);
		add(txtNomeProduto);
		txtNomeProduto.setColumns(10);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(491, 58, 129, 36);
		add(btnExcluir);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setBounds(358, 58, 129, 36);
		add(btnEditar);
		
		JButton btnFiltrar = new JButton("Filtrar");
		btnFiltrar.setBounds(224, 58, 129, 36);
		add(btnFiltrar);
		
		JButton btnAnterior = new JButton("<<");
		btnAnterior.setBounds(10, 373, 89, 23);
		add(btnAnterior);
		
		JButton btnProxima = new JButton(">>");
		btnProxima.setBounds(531, 373, 89, 23);
		add(btnProxima);

	}
}
