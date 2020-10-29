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

import senac.estoque.controller.ProdutoController;
import senac.estoque.helpers.GerarPdf;
import senac.estoque.model.dto.ProdutoMaisVendidoDTO;
import javax.swing.JButton;

public class ListarProdutosMaisVendidos extends JPanel {

	public ListarProdutosMaisVendidos() {
		setLayout(null);

		JLabel lblTitle = new JLabel("LISTAR PRODUTOS MAIS USADOS");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(10, 11, 600, 36);
		add(lblTitle);
		
		
		//carregar itens da tabela
		ProdutoController produtoController = new ProdutoController();
		ArrayList<ProdutoMaisVendidoDTO> produtos = produtoController.listarProdutosMaisVendidos();
		/* ==================================================================== */

		// definir colunas
		final String[] colunas = { "PRODUTO", "TOTAL USADO" };

		// setando modelo padrão de tabela
		DefaultTableModel modeloTabela = new DefaultTableModel(colunas, 0);

		// construindo a tabela seguindo o modelo criado
		final JTable tabela = new JTable(modeloTabela);
		DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
		tabela.setPreferredScrollableViewportSize(new Dimension(500, 50));
		tabela.setFillsViewportHeight(true);

		// populando a tabela
		for (int i = 0; i < produtos.size(); i++) {
			modelo.addRow(new Object[] { produtos.get(i).getDescricao(), produtos.get(i).getQtd_vendas() });
		}

		// imprimindo a tabela na tela
		JScrollPane scrollPane = new JScrollPane(tabela);
		scrollPane.setBounds(10, 50, 610, 239);
		add(scrollPane);

		JButton btnGerar = new JButton("Gerar PDF");
		btnGerar.setBounds(10, 340, 200, 23);
		btnGerar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				GerarPdf gerarPdf = new GerarPdf();

				gerarPdf.gerar(tabela, "ListaProdutosMaisVendidos", colunas);

			}

		});
		add(btnGerar);
	}
}
