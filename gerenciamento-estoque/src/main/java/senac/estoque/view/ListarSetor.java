package senac.estoque.view;

import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.Dimension;
import senac.estoque.controller.SetorController;
import senac.estoque.model.vo.SetorVO;

public class ListarSetor  extends JPanel{
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
		
		//definir colunas
		String[] colunas = {"ID", "Descrição"};
		
		//setando modelo padrão de tabela
		DefaultTableModel modeloTabela = new DefaultTableModel(colunas, 0);
		
		//construindo a tabela seguindo o modelo criado
		JTable tabela = new JTable(modeloTabela);
		DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
		tabela.setPreferredScrollableViewportSize(new Dimension(500,50));
		tabela.setFillsViewportHeight(true);
		
		//populando a tabela
		for(int i = 0; i < setores.size(); i++) {
			modelo.addRow(new Object[] {setores.get(i).getId(), setores.get(i).getDescricao()});
		}
		
		//imprimindo a tabela na tela
		JScrollPane scrollPane = new JScrollPane(tabela);
		scrollPane.setBounds(10, 50, 600, 239);
		add(scrollPane);

	}
}
