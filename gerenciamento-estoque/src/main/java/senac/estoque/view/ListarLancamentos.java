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

public class ListarLancamentos extends JPanel {
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
		scrollPane.setBounds(10, 50, 600, 239);
		add(scrollPane);

	}   
}
