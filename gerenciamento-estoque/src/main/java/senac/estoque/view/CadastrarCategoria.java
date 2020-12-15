package senac.estoque.view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import senac.estoque.controller.CategoriaController;
import senac.estoque.model.vo.CategoriaVO;

import javax.swing.JTextField;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CadastrarCategoria extends JPanel {
	private JTextField txtCategoria;

	/**
	 * Create the panel.
	 */
	public CadastrarCategoria() {
		setLayout(null);
		
		JLabel lblTitle = new JLabel("CADASTRAR CATEGORIA");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(10, 11, 630, 36);
		add(lblTitle);
		
		JLabel lblNome = new JLabel("Nome da Categoria");
		lblNome.setBounds(10, 71, 168, 22);
		add(lblNome);
		
		txtCategoria = new JTextField();
		txtCategoria.setBounds(10, 101, 610, 36);
		txtCategoria.setToolTipText("Somente letras");
		add(txtCategoria);
		txtCategoria.setColumns(10);
		
		JButton btnSalvar = new JButton("Cadastrar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				CategoriaVO categoria = new CategoriaVO();
				categoria.setDescricao(txtCategoria.getText());
				
				CategoriaController categoriaController = new CategoriaController();
				if(categoriaController.cadastrarCategoria(categoria)) {
					txtCategoria.setText("");
				}
			}
		});
		btnSalvar.setBounds(10, 148, 131, 40);
		add(btnSalvar);
		


	}
}
