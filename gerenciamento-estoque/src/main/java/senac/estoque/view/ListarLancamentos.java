package senac.estoque.view;

import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.Dimension;
import senac.estoque.controller.LancamentoController;
import senac.estoque.model.dto.LancamentoDTO;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;

public class ListarLancamentos extends JPanel {
	private JTextField txtNomeProduto;
	private JTextField txtNomeSetor;
	private JTextField txtDataInicial;
	private JTextField txtDataFinal;
 /**
	 * Create the panel.
	 */
	public ListarLancamentos() {
		setLayout(null);
		
		JLabel lblTitle = new JLabel("LISTAGEM DE LANCAMENTOS");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(10, 11, 600, 36);
		add(lblTitle);
		
		/**
		 * carregar lista de categorias
		 */
		LancamentoController lancamentoController = new LancamentoController();
		ArrayList<LancamentoDTO> lancamentos = lancamentoController.listarLancamento();
		/* ============================package senac.estoque.view;

import java.util.ArrayList;

import javax.swing.JLabel;======================================== */
		
		//definir colunas
		String[] colunas = {"ID", "PRODUTO","SETOR","TIPO","QUANTIDADE","PRECO TOTAL","DATA"};
		
		//setando modelo padrão de tabela
		DefaultTableModel modeloTabela = new DefaultTableModel(colunas, 0);
		
		//construindo a tabela seguindo o modelo criado
		JTable tabela = new JTable(modeloTabela);
		DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
		tabela.setPreferredScrollableViewportSize(new Dimension(500,50));
		tabela.setFillsViewportHeight(true);
		
		//populando a tabela
		for(int i = 0; i < lancamentos.size(); i++) {
			modelo.addRow(
                new Object[] {
                    lancamentos.get(i).getId(),
                    lancamentos.get(i).getProduto(),
                    lancamentos.get(i).getSetor(),
                    lancamentos.get(i).getTipo(),
                    lancamentos.get(i).getQuantidade(),
                    lancamentos.get(i).getPreco_total(),
                    lancamentos.get(i).getData()

                });
		}
		
		//imprimindo a tabela na tela
		JScrollPane scrollPane = new JScrollPane(tabela);
		scrollPane.setBounds(10, 230, 610, 200);
		add(scrollPane);
		
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
		
		txtDataInicial = new JTextField();
		txtDataInicial.setColumns(10);
		txtDataInicial.setBounds(20, 130, 264, 36);
		add(txtDataInicial);
		
		JLabel lblDataInicial = new JLabel("Data Inicial");
		lblDataInicial.setBounds(20, 116, 154, 14);
		add(lblDataInicial);
		
		txtDataFinal = new JTextField();
		txtDataFinal.setColumns(10);
		txtDataFinal.setBounds(346, 130, 264, 36);
		add(txtDataFinal);
		
		JLabel lblDataFinal = new JLabel("Data Final");
		lblDataFinal.setBounds(346, 116, 154, 14);
		add(lblDataFinal);
		
		JButton btnFiltrar = new JButton("Filtrar");
		btnFiltrar.setBounds(20, 177, 89, 42);
		add(btnFiltrar);
		
		JRadioButton radioBtnEntrada = new JRadioButton("Entrada");
		radioBtnEntrada.setSelected(true);
		radioBtnEntrada.setBounds(345, 187, 109, 23);
		add(radioBtnEntrada);
		
		JRadioButton radioBtnSaida = new JRadioButton("Saída");
		radioBtnSaida.setBounds(467, 187, 109, 23);
		add(radioBtnSaida);
		
		 ButtonGroup radio = new ButtonGroup();
		 radio.add(radioBtnEntrada);
		 radio.add(radioBtnSaida);

	}   
}
