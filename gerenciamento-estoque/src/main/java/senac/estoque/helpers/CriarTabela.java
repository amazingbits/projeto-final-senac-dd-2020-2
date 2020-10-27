package senac.estoque.helpers;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class CriarTabela {
	
	public void novaTabela(JTable tabela, Object[] itens, Object[][] dados) {
		tabela.setDefaultRenderer(Object.class, new RenderizarTabela());
		DefaultTableModel modeloTabela = new DefaultTableModel(dados, itens);
		tabela.setModel(modeloTabela);
	}

}
