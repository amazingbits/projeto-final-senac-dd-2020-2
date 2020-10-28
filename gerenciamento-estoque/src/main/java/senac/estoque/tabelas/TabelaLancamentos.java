package senac.estoque.tabelas;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import senac.estoque.controller.LancamentoController;
import senac.estoque.model.dto.LancamentoDTO;

public class TabelaLancamentos {
	
	public JTable gerar(Integer limit, Integer offset) {
		
		// carregando os itens da tabela
		LancamentoController lancamentoController = new LancamentoController();
		ArrayList<LancamentoDTO> lancamentos = lancamentoController.listarLancamento(limit, offset);
		
		// título das colunas
		String[] colunas = {"ID", "PRODUTO","SETOR","TIPO","QUANTIDADE","PRECO TOTAL","DATA"};
		
		//setando modelo padrão de tabela
		DefaultTableModel modeloTabela = new DefaultTableModel(colunas, 0) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		//construindo a tabela seguindo o modelo criado
		final JTable tabela = new JTable(modeloTabela);
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
		           "R$ "+lancamentos.get(i).getPreco_total(),
		           lancamentos.get(i).getData()
		      });
		}
		
		modeloTabela.fireTableDataChanged();
		return tabela;
	}
}
