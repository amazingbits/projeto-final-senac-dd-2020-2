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
		lblNome.setBounds(108, 74, 168, 22);
		add(lblNome);
		
		txtCategoria = new JTextField();
		txtCategoria.setBounds(108, 101, 430, 26);
		add(txtCategoria);
		txtCategoria.setColumns(10);
		
		JButton btnSalvar = new JButton("Salvar");
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
		btnSalvar.setBounds(108, 138, 89, 40);
		add(btnSalvar);
		
		JLabel lblPermitirSomenteLetras = new JLabel("Somente letras");
		lblPermitirSomenteLetras.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPermitirSomenteLetras.setBounds(286, 138, 252, 14);
		add(lblPermitirSomenteLetras);

	}
}
