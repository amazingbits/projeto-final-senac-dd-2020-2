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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import senac.estoque.controller.SetorController;
import senac.estoque.helpers.Constantes;
import senac.estoque.model.vo.SetorVO;
import senac.estoque.seletores.SeletorSetor;

import javax.swing.JTextField;
import javax.swing.JButton;

public class ListarSetor extends JPanel {
	private JTextField txtNomeSetor;
	private String nomeSetor = "";
	private Integer offset = 0;

	/**
	 * Create the panel.
	 */
	public ListarSetor() {
		setLayout(null);

		JLabel lblTitle = new JLabel("LISTAR SETORES");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(10, 11, 600, 36);
		add(lblTitle);

		/**
		 * carregar lista de categorias
		 */
		SetorController setorController = new SetorController();
		ArrayList<SetorVO> setores = setorController.listarSetor();
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
		for (int i = 0; i < setores.size(); i++) {
			modelo.addRow(new Object[] { setores.get(i).getId(), setores.get(i).getDescricao() });
		}

		// imprimindo a tabela na tela
		JScrollPane scrollPane = new JScrollPane(tabela);
		scrollPane.setBounds(10, 123, 610, 239);
		add(scrollPane);

		JLabel lblNomeSetor = new JLabel("Nome do Setor");
		lblNomeSetor.setBounds(10, 43, 97, 14);
		add(lblNomeSetor);

		txtNomeSetor = new JTextField();
		txtNomeSetor.setColumns(10);
		txtNomeSetor.setBounds(10, 58, 209, 36);
		add(txtNomeSetor);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(491, 58, 129, 36);

		btnExcluir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				int linhaSelecionada = tabela.getSelectedRow();

				if (linhaSelecionada == -1) {
					JOptionPane.showMessageDialog(null, "Selecione ao menos um registro");
				} else {
					int id = (int) tabela.getValueAt(linhaSelecionada, 0);
					SetorController setorController = new SetorController();
					SetorVO setorVO = new SetorVO();
					setorVO.setId(id);

					boolean resultado = setorController.excluirSetor(setorVO);

					if (resultado) {
						JOptionPane.showMessageDialog(null, "Sucesso em deletar setor");

					} else {
						JOptionPane.showMessageDialog(null, "Erro ao deletar a setor");

					}

				}

			}

		
		
		});
		add(btnExcluir);
		add(btnExcluir);
	
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setBounds(358, 58, 129, 36);

		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int linhaSelecionada = tabela.getSelectedRow();
				int totalDeLinhasSelecionadas = tabela.getSelectedRowCount();
				int verificacao = 0;
				String nmSetor = txtNomeSetor.getText().trim();

				if (linhaSelecionada == -1) {
					JOptionPane.showMessageDialog(null, "Selecione ao menos um registro");
					verificacao++;
				}

				if (totalDeLinhasSelecionadas > 1) {
					JOptionPane.showMessageDialog(null, "Selecione apenas um registro da tabela!");
					verificacao++;
				}

				if (nmSetor.length() <= 0) {
					JOptionPane.showMessageDialog(null, "Você precisa digitar um nome para a setor!");
					verificacao++;
				}

				if (verificacao == 0) {
					int confirm = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja editar este registro?");
					if (confirm == 0) {
						int id = (int) tabela.getValueAt(linhaSelecionada, 0);
						SetorController setorController = new SetorController();

						SetorVO setorVO = new SetorVO();
						setorVO.setDescricao(nmSetor);
						setorVO.setId(id);

						if (setorController.editarSetor(setorVO)) {
							JOptionPane.showMessageDialog(null, "Setor editadado com sucesso!");
							((DefaultTableModel) tabela.getModel()).fireTableDataChanged();
						} else {
							JOptionPane.showMessageDialog(null, "Houve um erro ao editar o setor!", "Erro",JOptionPane.ERROR_MESSAGE);
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

				SeletorSetor filtro = new SeletorSetor();
				filtro.setNomeSetor(txtNomeSetor.getText());

				nomeSetor = txtNomeSetor.getText();
				offset = 0;

				SetorController setorController = new SetorController();
				ArrayList<SetorVO> setoresFiltrados = setorController.listarSetorSeletor(filtro);

				/* ======estados dos botões de paginação===== */
				if (setoresFiltrados.size() < Constantes.ITEM_POR_PAGINA) {
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

				if (setoresFiltrados != null) {
					((DefaultTableModel) tabela.getModel()).setRowCount(0);
					for (int i = 0; i < setoresFiltrados.size(); i++) {
						((DefaultTableModel) tabela.getModel()).addRow(
							new Object[] { 
								setoresFiltrados.get(i).getId(),
								setoresFiltrados.get(i).getDescricao()
							});
					}
					((DefaultTableModel) tabela.getModel()).fireTableDataChanged();
				}

			}
		});
		
		add(btnFiltrar);

	}
}
