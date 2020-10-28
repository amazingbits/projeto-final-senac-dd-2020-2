package senac.estoque.view;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import senac.estoque.controller.LancamentoController;
import senac.estoque.helpers.Utils;
import senac.estoque.model.dto.LancamentoDTO;
import senac.estoque.seletores.SeletorLancamento;
import senac.estoque.tabelas.TabelaLancamentos;

import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class ListarLancamentos extends JPanel {
	private JTextField txtNomeProduto;
	private JTextField txtNomeSetor;
	private JFormattedTextField txtDataInicial;
	private JFormattedTextField txtDataFinal;
	
	private Integer numberPerPages = 10;
	private Integer offset = 0;
	private Utils utils = new Utils();
	
 /**
	 * Create the panel.
	 */
	public ListarLancamentos() {
		setLayout(null);
		
		JLabel lblTitle = new JLabel("LISTAGEM DE LANCAMENTOS");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(10, 11, 600, 36);
		add(lblTitle);
		
		//===========imprimindo tabela na tela========================
		final TabelaLancamentos tabelaLancamentos = new TabelaLancamentos();
		final JTable tabela = tabelaLancamentos.gerar(numberPerPages, offset);
		
		final JScrollPane scrollPane = new JScrollPane(tabela);
		scrollPane.setBounds(10, 230, 610, 200);
		add(scrollPane);
		//============================================================
		
		JLabel lblNomeProduto = new JLabel("Nome do Produto");
		lblNomeProduto.setBounds(20, 44, 154, 14);
		add(lblNomeProduto);
		
		txtNomeProduto = new JTextField();
		txtNomeProduto.setBounds(20, 58, 264, 36);
		add(txtNomeProduto);
		txtNomeProduto.setColumns(10);
		
		txtNomeSetor = new JTextField();
		txtNomeSetor.setColumns(10);
		txtNomeSetor.setBounds(346, 58, 264, 36);
		add(txtNomeSetor);
		
		JLabel lblNomeSetor = new JLabel("Nome do Setor");
		lblNomeSetor.setBounds(346, 44, 154, 14);
		add(lblNomeSetor);
		
		txtDataInicial = new JFormattedTextField();
		utils.mascara("##/##/####", txtDataInicial);
		txtDataInicial.setColumns(10);
		txtDataInicial.setBounds(20, 130, 264, 36);
		add(txtDataInicial);
		
		JLabel lblDataInicial = new JLabel("Data Inicial");
		lblDataInicial.setBounds(20, 116, 154, 14);
		add(lblDataInicial);
		
		txtDataFinal = new JFormattedTextField();
		utils.mascara("##/##/####", txtDataFinal);
		txtDataFinal.setColumns(10);
		txtDataFinal.setBounds(346, 130, 264, 36);
		add(txtDataFinal);
		
		JLabel lblDataFinal = new JLabel("Data Final");
		lblDataFinal.setBounds(346, 116, 154, 14);
		add(lblDataFinal);
		
		JRadioButton radioBtnEntrada = new JRadioButton("Entrada");
		radioBtnEntrada.setActionCommand("Entrada");
		radioBtnEntrada.setBounds(345, 187, 109, 23);
		add(radioBtnEntrada);
		
		JRadioButton radioBtnSaida = new JRadioButton("Saída");
		radioBtnSaida.setActionCommand("Saída");
		radioBtnSaida.setBounds(467, 187, 109, 23);
		add(radioBtnSaida);
		
		JRadioButton radioBtnTodos = new JRadioButton("Todos");
		radioBtnTodos.setActionCommand("Todos");
		radioBtnTodos.setSelected(true);
		radioBtnTodos.setBounds(228, 187, 109, 23);
		add(radioBtnTodos);
		
		final ButtonGroup radio = new ButtonGroup();
		radio.add(radioBtnEntrada);
		radio.add(radioBtnSaida);
		radio.add(radioBtnTodos);
		
		JButton btnFiltrar = new JButton("Filtrar");
		btnFiltrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int verificador = 0;
				String dataInicial = txtDataInicial.getText().trim().replace(" ", "");
				String dataFinal = txtDataFinal.getText().trim().replace(" ", "");
				
				if(!utils.validarData(dataInicial) && !dataInicial.equals("//")) {
					JOptionPane.showMessageDialog(null, "A data inicial deve ser válida!");
					verificador++;
				}
				
				if(!utils.validarData(dataFinal) && !dataFinal.equals("//")) {
					JOptionPane.showMessageDialog(null, "A data final deve ser válida!");
					verificador++;
				}
				
				if(dataInicial.equals("//")) dataInicial = "";
				if(dataFinal.equals("//")) dataFinal = "";
				
				String tipo = radio.getSelection().getActionCommand() == "Todos" ? "" : radio.getSelection().getActionCommand();
				
				SeletorLancamento seletorLancamento = new SeletorLancamento();
				seletorLancamento.setNomeProduto(txtNomeProduto.getText());
				seletorLancamento.setNomeSetor(txtNomeSetor.getText());
				seletorLancamento.setDataInicial(dataInicial);
				seletorLancamento.setDataFinal(dataFinal);
				seletorLancamento.setTipo(tipo);
				
				LancamentoController lancamentoController = new LancamentoController();
				ArrayList<LancamentoDTO> filtro = lancamentoController.filtrarLancamentos(seletorLancamento);
				if(filtro != null) {
					((DefaultTableModel)tabela.getModel()).setRowCount(0);
					for(int i = 0; i < filtro.size(); i++) {
						((DefaultTableModel)tabela.getModel()).addRow(
									new Object[] {
											filtro.get(i).getId(),
											filtro.get(i).getProduto(),
											filtro.get(i).getSetor(),
											filtro.get(i).getTipo(),
											filtro.get(i).getQuantidade(),
									        "R$ "+filtro.get(i).getPreco_total(),
									        filtro.get(i).getData()
									}
								);
					}
					((DefaultTableModel)tabela.getModel()).fireTableDataChanged();
				}
			}
		});
		btnFiltrar.setBounds(20, 177, 89, 42);
		add(btnFiltrar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int linhaSelecionada = tabela.getSelectedRow();
				int totalDeLinhasSelecionadas = tabela.getSelectedRowCount();
				int verificacao = 0;
				
				if(linhaSelecionada == -1) {
					JOptionPane.showMessageDialog(null, "Selecione ao menos um registro");
					verificacao++;
				}
				
				if(totalDeLinhasSelecionadas > 1) {
					JOptionPane.showMessageDialog(null, "Selecione apenas um registro da tabela!");
					verificacao++;
				}
				
				if(verificacao == 0) {
					int confirm = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir este registro?");
					if(confirm == 0) {
						int id = (int) tabela.getValueAt(linhaSelecionada, 0);
						LancamentoController lancamentoController = new LancamentoController();
						if(lancamentoController.excluirLancamento(id)) {
							JOptionPane.showMessageDialog(null, "Lançamento excluído com sucesso!");
							((DefaultTableModel)tabela.getModel()).removeRow(linhaSelecionada);
						} else {
							JOptionPane.showMessageDialog(null, "Houve um erro ao excluir o lançamento!", "Erro", JOptionPane.ERROR_MESSAGE);
						}
					}
					
				}
				
			}
		});
		btnExcluir.setBounds(119, 177, 89, 42);
		add(btnExcluir);
		
		JButton btnAnterior = new JButton("<<");
		btnAnterior.setBounds(10, 441, 89, 23);
		add(btnAnterior);
		
		JButton btnProxima = new JButton(">>");
		btnProxima.setBounds(531, 441, 89, 23);
		add(btnProxima);
		

	}   
}
