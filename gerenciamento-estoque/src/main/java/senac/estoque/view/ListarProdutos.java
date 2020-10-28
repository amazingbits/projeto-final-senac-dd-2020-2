package senac.estoque.view;

import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.Dimension;

import senac.estoque.controller.LancamentoController;
import senac.estoque.controller.ProdutoController;
import senac.estoque.helpers.Constantes;
import senac.estoque.model.dto.ProdutoDTO;
import senac.estoque.model.vo.ProdutoVO;
import senac.estoque.seletores.SeletorProduto;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ListarProdutos extends JPanel {
	private JTextField txtNomeProduto;

	private SeletorProduto seletorProduto = new SeletorProduto();
	private Integer offset = 0;
	private String nomeProduto = "";

	/**
	 * Create the panel.
	 */
	public ListarProdutos() {
		setLayout(null);

		JLabel lblTitle = new JLabel("LISTAR PRODUTOS");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(10, 11, 600, 36);
		add(lblTitle);

		JLabel lblNomeProduto = new JLabel("Nome do produto");
		lblNomeProduto.setBounds(10, 43, 97, 14);
		add(lblNomeProduto);

		txtNomeProduto = new JTextField();
		txtNomeProduto.setBounds(10, 58, 209, 36);
		add(txtNomeProduto);
		txtNomeProduto.setColumns(10);

		final JButton btnAnterior = new JButton("<<");
		final JButton btnProxima = new JButton(">>");

		// carregando produtos da tabela
		this.seletorProduto.setOffset(offset);
		this.seletorProduto.setNomeProduto(nomeProduto);
		ProdutoController produtoController = new ProdutoController();
		ArrayList<ProdutoDTO> produtos = produtoController.listarProdutoSeletor(this.seletorProduto);
		/* ==================================================================== */

		/* ======estados dos botões de paginação===== */
		if (produtos.size() < Constantes.ITEM_POR_PAGINA) {
			btnProxima.setEnabled(false);
		} else {
			btnProxima.setEnabled(true);
		}

		if (offset == 0) {
			btnAnterior.setEnabled(false);
		} else {
			btnAnterior.setEnabled(true);
		}
		/* ======================================== */

		// definir colunas
		String[] colunas = { "ID", "Descrição", "Preço", "Categoria", "Quantidade" };

		// setando modelo padrão de tabela
		DefaultTableModel modeloTabela = new DefaultTableModel(colunas, 0) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		// construindo a tabela seguindo o modelo criado
		final JTable tabela = new JTable(modeloTabela);
		DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
		tabela.setPreferredScrollableViewportSize(new Dimension(500, 50));
		tabela.setFillsViewportHeight(true);

		// populando a tabela
		for (int i = 0; i < produtos.size(); i++) {
			modelo.addRow(new Object[] { produtos.get(i).getId(), produtos.get(i).getDescricao(),
					"R$ " + produtos.get(i).getPreco(), produtos.get(i).getCategoria(),
					produtos.get(i).getQuantidade() });
		}

		// imprimindo a tabela na tela
		JScrollPane scrollPane = new JScrollPane(tabela);
		scrollPane.setBounds(10, 123, 610, 239);
		add(scrollPane);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(491, 58, 129, 36);
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int linhaSelecionada = tabela.getSelectedRow();

				if (linhaSelecionada == -1) {
					JOptionPane.showMessageDialog(null, "Selecione ao menos um registro");
				} else {
					int id = (int) tabela.getValueAt(linhaSelecionada, 0);
					ProdutoController produtoController = new ProdutoController();
					ProdutoVO produtoVO = new ProdutoVO();
					produtoVO.setId(id);

					boolean resultado = produtoController.excluir(produtoVO);

					if (resultado) {
						JOptionPane.showMessageDialog(null, "Sucesso em deletar o produto");

					} else {
						JOptionPane.showMessageDialog(null, "Erro ao deletar o produto");

					}

				}

			}
		});
		add(btnExcluir);

		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int linhaSelecionada = tabela.getSelectedRow();
				int totalDeLinhasSelecionadas = tabela.getSelectedRowCount();
				int verificacao = 0;
				String nmProduto = txtNomeProduto.getText().trim();

				if (linhaSelecionada == -1) {
					JOptionPane.showMessageDialog(null, "Selecione ao menos um registro");
					verificacao++;
				}

				if (totalDeLinhasSelecionadas > 1) {
					JOptionPane.showMessageDialog(null, "Selecione apenas um registro da tabela!");
					verificacao++;
				}

				if (nmProduto.length() <= 0) {
					JOptionPane.showMessageDialog(null, "Você precisa digitar um nome para o produto!");
					verificacao++;
				}

				if (verificacao == 0) {
					int confirm = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja editar este registro?");
					if (confirm == 0) {
						int id = (int) tabela.getValueAt(linhaSelecionada, 0);
						ProdutoController produtoController = new ProdutoController();

						ProdutoVO produtoVO = new ProdutoVO();
						produtoVO.setDescricao(nmProduto);
						produtoVO.setId(id);

						if (produtoController.editarProduto(produtoVO)) {
							JOptionPane.showMessageDialog(null, "Produto editado com sucesso!");
							((DefaultTableModel) tabela.getModel()).fireTableDataChanged();
						} else {
							JOptionPane.showMessageDialog(null, "Houve um erro ao editar o produto!", "Erro",
									JOptionPane.ERROR_MESSAGE);
						}
					}

				}

			}
		});
		btnEditar.setBounds(358, 58, 129, 36);
		add(btnEditar);

		JButton btnFiltrar = new JButton("Filtrar");
		btnFiltrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				SeletorProduto filtro = new SeletorProduto();
				filtro.setNomeProduto(txtNomeProduto.getText());

				nomeProduto = txtNomeProduto.getText();
				offset = 0;

				ProdutoController produtoController = new ProdutoController();
				ArrayList<ProdutoDTO> produtosFiltrados = produtoController.listarProdutoSeletor(filtro);

				/* ======estados dos botões de paginação===== */
				if (produtosFiltrados.size() < Constantes.ITEM_POR_PAGINA) {
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

				if (produtosFiltrados != null) {
					((DefaultTableModel) tabela.getModel()).setRowCount(0);
					for (int i = 0; i < produtosFiltrados.size(); i++) {
						((DefaultTableModel) tabela.getModel()).addRow(new Object[] { produtosFiltrados.get(i).getId(),
								produtosFiltrados.get(i).getDescricao(), "R$ " + produtosFiltrados.get(i).getPreco(),
								produtosFiltrados.get(i).getCategoria(), produtosFiltrados.get(i).getQuantidade() });
					}
					((DefaultTableModel) tabela.getModel()).fireTableDataChanged();
				}

			}
		});
		btnFiltrar.setBounds(224, 58, 129, 36);
		add(btnFiltrar);

		btnAnterior.setBounds(10, 373, 89, 23);
		add(btnAnterior);

		btnProxima.setBounds(531, 373, 89, 23);
		add(btnProxima);

	}
}
