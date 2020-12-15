package senac.estoque.view;

import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import senac.estoque.controller.ProdutoController;
import senac.estoque.helpers.GerarPdf;
import senac.estoque.helpers.Item;
import senac.estoque.model.vo.LogProdutosVO;
import senac.estoque.seletores.SeletorProduto;

import javax.swing.JButton;
import javax.swing.JComboBox;

public class ListaLogProdutos extends JPanel {

	
	private SeletorProduto seletorProduto = new SeletorProduto();
	private int mes = LocalDate.now().getMonthValue();
	private int ano = LocalDate.now().getYear();
	
	/**
	 * Create the panel.
	 */
	public ListaLogProdutos() {
		setLayout(null);

		JLabel lblTitle = new JLabel("LOG DE PRODUTOS");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(10, 11, 600, 36);
		add(lblTitle);

		//carregar itens da tabela
		ProdutoController produtoController = new ProdutoController();
		this.seletorProduto.setMes(this.mes);
		this.seletorProduto.setAno(this.ano);
		ArrayList<LogProdutosVO> logProdutos = produtoController.listaLogProdutos(this.seletorProduto);

		// definir colunas
		final String[] colunas = { "PRODUTO", "OPERAÇÃO", "QUANTIDADE", "DATA" };

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
		for (int i = 0; i < logProdutos.size(); i++) {
			modelo.addRow(new Object[] { 
					logProdutos.get(i).getProduto(), 
					logProdutos.get(i).getOperacao(),
					logProdutos.get(i).getQuantidade(), 
					logProdutos.get(i).getData() 
					}
			);
		}

		// imprimindo a tabela na tela
		JScrollPane scrollPane = new JScrollPane(tabela);
		scrollPane.setBounds(10, 90, 610, 239);
		add(scrollPane);

		JButton btnGerar = new JButton("Gerar PDF");
		btnGerar.setBounds(10, 340, 200, 23);
		btnGerar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				GerarPdf gerarPdf = new GerarPdf();

				gerarPdf.gerar(tabela, "Produtos", colunas);

			}

		});
		add(btnGerar);
		
		String[] meses = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
		//String[] anos = {"2020", "2021", "2022", "2023"};
		Object[] anos = produtoController.listaAnos().toArray();		

		
		final JComboBox cbMes = new JComboBox(meses);
		cbMes.setBounds(10, 46, 135, 36);
		add(cbMes);
		
		final JComboBox cbAno = new JComboBox(anos);
		cbAno.setBounds(155, 46, 135, 36);
		add(cbAno);
		
		JLabel lblMes = new JLabel("Mês");
		lblMes.setBounds(10, 33, 46, 14);
		add(lblMes);
		
		JLabel lblAno = new JLabel("Ano");
		lblAno.setBounds(155, 33, 46, 14);
		add(lblAno);
		
		JButton btnFiltrar = new JButton("Filtrar");
		btnFiltrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int mesSelecionado = cbMes.getSelectedIndex() + 1;
				int anoSelecionado = Integer.parseInt((String) cbAno.getSelectedItem());
				
				ProdutoController produtoController = new ProdutoController();
				SeletorProduto seletorProduto = new SeletorProduto();
				seletorProduto.setMes(mesSelecionado);
				seletorProduto.setAno(anoSelecionado);
				ArrayList<LogProdutosVO> filtro = produtoController.listaLogProdutos(seletorProduto);
				
				if (filtro != null) {
					((DefaultTableModel) tabela.getModel()).setRowCount(0);
					for (int i = 0; i < filtro.size(); i++) {
						((DefaultTableModel) tabela.getModel()).addRow(new Object[] { 
								filtro.get(i).getProduto(), 
								filtro.get(i).getOperacao(),
								filtro.get(i).getQuantidade(), 
								filtro.get(i).getData()  
								}
						);
					}
					((DefaultTableModel) tabela.getModel()).fireTableDataChanged();
				}
				
			}
		});
		btnFiltrar.setBounds(300, 46, 89, 36);
		add(btnFiltrar);
		

	}
}
