package senac.estoque.view;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import senac.estoque.controller.CategoriaController;
import senac.estoque.model.vo.CategoriaVO;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;

public class ListarCategorias extends JPanel {
	private JTextField txtNomeCategoria;

	/**
	 * Create the panel.
	 */
	public ListarCategorias() {
		setLayout(null);
		
		JLabel lblTitle = new JLabel("LISTAR CATEGORIAS");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(10, 11, 600, 36);
		add(lblTitle);
		
		/**
		 * carregar lista de categorias
		 */
		CategoriaController categoriaController = new CategoriaController();
		ArrayList<CategoriaVO> categorias = categoriaController.listarCategoria();
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
		for(int i = 0; i < categorias.size(); i++) {
			modelo.addRow(new Object[] {categorias.get(i).getId(), categorias.get(i).getDescricao()});
		}
		
		//imprimindo a tabela na tela
		JScrollPane scrollPane = new JScrollPane(tabela);
		scrollPane.setBounds(10, 123, 610, 239);
		add(scrollPane);
		
		JLabel lblNomeCategoria = new JLabel("Nome da Categoria");
		lblNomeCategoria.setBounds(10, 43, 149, 14);
		add(lblNomeCategoria);
		
		txtNomeCategoria = new JTextField();
		txtNomeCategoria.setColumns(10);
		txtNomeCategoria.setBounds(10, 58, 478, 36);
		add(txtNomeCategoria);
		
		JButton btnFiltrar = new JButton("Filtrar");
		btnFiltrar.setBounds(491, 58, 129, 36);
		add(btnFiltrar);

	}
}
