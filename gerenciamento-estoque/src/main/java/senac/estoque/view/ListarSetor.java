package senac.estoque.view;

import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
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

	private SeletorSetor seletorSetor = new SeletorSetor();
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

		JLabel lblNomeSetor = new JLabel("Nome do Setor");
		lblNomeSetor.setBounds(10, 43, 160, 14);
		add(lblNomeSetor);

		txtNomeSetor = new JTextField();
		txtNomeSetor.setColumns(10);
		txtNomeSetor.setBounds(10, 58, 209, 36);
		add(txtNomeSetor);

		final JButton btnAnterior = new JButton("<<");
		final JButton btnProxima = new JButton(">>");

		// carregar itens da tabela
		SetorController setorController = new SetorController();
		this.seletorSetor.setOffset(offset);
		this.seletorSetor.setNomeSetor(nomeSetor);
		ArrayList<SetorVO> setores = setorController.listarSetorSeletor(this.seletorSetor);
		/* ==================================================================== */

		/* ======estados dos botões de paginação===== */
		if (setores.size() < Constantes.ITEM_POR_PAGINA) {
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
		String[] colunas = { "ID", "Descrição" };

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
		for (int i = 0; i < setores.size(); i++) {
			modelo.addRow(new Object[] { setores.get(i).getId(), setores.get(i).getDescricao() });
		}

		// imprimindo a tabela na tela
		JScrollPane scrollPane = new JScrollPane(tabela);
		scrollPane.setBounds(10, 123, 610, 239);
		add(scrollPane);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(491, 58, 129, 36);

		btnExcluir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				int linhaSelecionada = tabela.getSelectedRow();

				if (linhaSelecionada == -1) {
					JOptionPane.showMessageDialog(null, "Selecione ao menos um registro");
				} else {

					int confirm = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir este registro?");

					if (confirm == 0) {
						int id = (int) tabela.getValueAt(linhaSelecionada, 0);

						SetorController setorController = new SetorController();
						SetorVO setorVO = new SetorVO();
						setorVO.setId(id);

						if (setorController.desativar(setorVO)) {
							JOptionPane.showMessageDialog(null, "Sucesso em deletar o setor");
							((DefaultTableModel) tabela.getModel()).removeRow(linhaSelecionada);
							((DefaultTableModel) tabela.getModel()).fireTableDataChanged();
						}
					}

				}

			}

		});
		add(btnExcluir);

		tabela.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			@Override
			public void valueChanged(ListSelectionEvent e) {
				int linhaSelecionada = tabela.getSelectedRow();

				 String descricao = (String) tabela.getValueAt(linhaSelecionada, 1);
				 
				 txtNomeSetor.setText(descricao);
			}
		});


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

				if (totalDeLinhasSelecionadas > 1 && verificacao == 0) {
					JOptionPane.showMessageDialog(null, "Selecione apenas um registro da tabela!");
					verificacao++;
				}

				if (nmSetor.length() <= 0 && verificacao == 0) {
					JOptionPane.showMessageDialog(null, "Você precisa digitar um nome para a setor!");
					verificacao++;
				}

				if (verificacao == 0) {
					int confirm = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja editar este registro?");
					if (confirm == 0) {
						
						int id = (int) tabela.getValueAt(linhaSelecionada, 0);
						SetorController setorController = new SetorController();
						
						SetorVO setorVO = new SetorVO();
						setorVO.setId(id);
						setorVO.setDescricao(nmSetor);
						
						if(setorController.editarSetor(setorVO)) {
							JOptionPane.showMessageDialog(null, "Setor editado com sucesso!");
							((DefaultTableModel) tabela.getModel()).setValueAt(nmSetor, linhaSelecionada, 1);
							txtNomeSetor.setText("");
						}
						
					}

				}

			}
		});
		add(btnEditar);

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
		
		
		
		btnAnterior.setBounds(10, 373, 89, 23);
		btnAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (offset > 0) {
					offset -= Constantes.ITEM_POR_PAGINA;
				}
				
				SeletorSetor filtro = new SeletorSetor();
				filtro.setNomeSetor(txtNomeSetor.getText());
				filtro.setOffset(offset);
				
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
						((DefaultTableModel) tabela.getModel()).addRow(new Object[] { 
								setoresFiltrados.get(i).getId(),
								setoresFiltrados.get(i).getDescricao()
								}
						);
					}
					((DefaultTableModel) tabela.getModel()).fireTableDataChanged();
				}
				
				
				
			}
		});
		add(btnAnterior);
		
		
		btnProxima.setBounds(531, 373, 89, 23);
		btnProxima.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				offset += Constantes.ITEM_POR_PAGINA;
				
				SeletorSetor filtro = new SeletorSetor();
				filtro.setNomeSetor(txtNomeSetor.getText());
				filtro.setOffset(offset);
				
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
						((DefaultTableModel) tabela.getModel()).addRow(new Object[] { 
								setoresFiltrados.get(i).getId(),
								setoresFiltrados.get(i).getDescricao()
								}
						);
					}
					((DefaultTableModel) tabela.getModel()).fireTableDataChanged();
				}
				
			}
		});
		add(btnProxima);

	}
}
