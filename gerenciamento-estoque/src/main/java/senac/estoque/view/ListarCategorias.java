package senac.estoque.view;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import senac.estoque.controller.CategoriaController;
import senac.estoque.helpers.Constantes;
import senac.estoque.model.vo.CategoriaVO;
import senac.estoque.seletores.SeletorCategoria;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;

public class ListarCategorias extends JPanel {
	private JTextField txtNomeCategoria;
	private Integer offset = 0;
	private String nomeCategoria = "";

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

		// definir colunas
		String[] colunas = { "ID", "Descrição" };

		// setando modelo padrão de tabela
		DefaultTableModel modeloTabela = new DefaultTableModel(colunas, 0);

		// construindo a tabela seguindo o modelo criado
		final JTable tabela = new JTable(modeloTabela);
		DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
		tabela.setPreferredScrollableViewportSize(new Dimension(500, 50));
		tabela.setFillsViewportHeight(true);

		// populando a tabela
		for (int i = 0; i < categorias.size(); i++) {
			modelo.addRow(new Object[] { categorias.get(i).getId(), categorias.get(i).getDescricao() });
		}

		// imprimindo a tabela na tela
		JScrollPane scrollPane = new JScrollPane(tabela);
		scrollPane.setBounds(10, 123, 610, 239);
		add(scrollPane);

		JLabel lblNomeCategoria = new JLabel("Nome da Categoria");
		lblNomeCategoria.setBounds(10, 43, 149, 14);
		add(lblNomeCategoria);

		txtNomeCategoria = new JTextField();
		txtNomeCategoria.setColumns(10);
		txtNomeCategoria.setBounds(10, 58, 209, 36);
		add(txtNomeCategoria);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(491, 58, 129, 36);

		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int linhaSelecionada = tabela.getSelectedRow();

				if (linhaSelecionada == -1) {
					JOptionPane.showMessageDialog(null, "Selecione ao menos um registro");
				} else {
					int id = (int) tabela.getValueAt(linhaSelecionada, 0);
					CategoriaController categoriaController = new CategoriaController();
					CategoriaVO categoriaVO = new CategoriaVO();
					categoriaVO.setId(id);

					boolean resultado = categoriaController.excluirCategoria(categoriaVO);

					if (resultado) {
						JOptionPane.showMessageDialog(null, "Sucesso em deletar categória");

					} else {
						JOptionPane.showMessageDialog(null, "Erro ao deletar a categória");

					}

				}

			}

		
		});
		add(btnExcluir);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setBounds(358, 58, 129, 36);
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int linhaSelecionada = tabela.getSelectedRow();
				int totalDeLinhasSelecionadas = tabela.getSelectedRowCount();
				int verificacao = 0;
				String nmCategoria = txtNomeCategoria.getText().trim();

				if (linhaSelecionada == -1) {
					JOptionPane.showMessageDialog(null, "Selecione ao menos um registro");
					verificacao++;
				}

				if (totalDeLinhasSelecionadas > 1) {
					JOptionPane.showMessageDialog(null, "Selecione apenas um registro da tabela!");
					verificacao++;
				}

				if (nmCategoria.length() <= 0) {
					JOptionPane.showMessageDialog(null, "Você precisa digitar um nome para a categoria!");
					verificacao++;
				}

				if (verificacao == 0) {
					int confirm = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja editar este registro?");
					if (confirm == 0) {
						int id = (int) tabela.getValueAt(linhaSelecionada, 0);
						CategoriaController categoriaController = new CategoriaController();

						CategoriaVO categoriaVO = new CategoriaVO();
						categoriaVO.setDescricao(nmCategoria);
						categoriaVO.setId(id);

						if (categoriaController.editarCategoria(categoriaVO)) {
							JOptionPane.showMessageDialog(null, "Categoria editada com sucesso!");
							((DefaultTableModel) tabela.getModel()).fireTableDataChanged();
						} else {
							JOptionPane.showMessageDialog(null, "Houve um erro ao editar a categoria!", "Erro",JOptionPane.ERROR_MESSAGE);
						}
					}

				}

			}
		});
		add(btnEditar);
		
		final JButton btnAnterior = new JButton("<<");
		btnAnterior.setBounds(10, 373, 89, 23);
		add(btnAnterior);
		
		final JButton btnProxima = new JButton(">>");
		btnProxima.setBounds(531, 373, 89, 23);
		add(btnProxima);

			
		JButton btnFiltrar = new JButton("Filtrar");
		btnFiltrar.setBounds(224, 58, 129, 36);
		btnFiltrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				SeletorCategoria filtro = new SeletorCategoria();
				filtro.setNomeCategoria(txtNomeCategoria.getText());

				nomeCategoria = txtNomeCategoria.getText();
				offset = 0;

				CategoriaController categoriaController = new CategoriaController();
				ArrayList<CategoriaVO> categoriasFiltradas = categoriaController.listarCategoriaSeletor(filtro);

				/* ======estados dos botões de paginação===== */
				if (categoriasFiltradas.size() < Constantes.ITEM_POR_PAGINA) {
					btnProxima.setEnabled(false);
				} else {
					btnProxima.setEnabled(true);
				}

				if (offset == 0) {
					btnAnterior.setEnabled(false);
				} else {
					btnAnterior.setEnabled(true);
				}
				/* ================================ */

				if (categoriasFiltradas != null) {
					((DefaultTableModel) tabela.getModel()).setRowCount(0);
					for (int i = 0; i < categoriasFiltradas.size(); i++) {
						((DefaultTableModel) tabela.getModel()).addRow(
							new Object[] { 
								categoriasFiltradas.get(i).getId(),
								categoriasFiltradas.get(i).getDescricao()
							});
					}
					((DefaultTableModel) tabela.getModel()).fireTableDataChanged();
				}

			}
		});
		
		add(btnFiltrar);

	}
}
