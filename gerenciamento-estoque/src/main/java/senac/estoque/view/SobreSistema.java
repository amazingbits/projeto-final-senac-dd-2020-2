package senac.estoque.view;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;

import senac.estoque.helpers.Uri;

import javax.swing.JTextArea;
import javax.swing.JTextPane;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SobreSistema extends JPanel {

	private String content = "Este é um sistema simples de controle de estoque. A ideia de desenvolver "
			+ "este sistema é abstrair tudo o que foi ensinado na terceira fase do curso de "
			+ "Análise e Desenvolvimento de Sistemas do SENAC do ano 2020/2.";
	
	/**
	 * Create the panel.
	 */
	public SobreSistema() {
		setLayout(null);
		
		JLabel lblOSistema = new JLabel("O SISTEMA");
		lblOSistema.setHorizontalAlignment(SwingConstants.CENTER);
		lblOSistema.setFont(new Font("Alpha Romanie G98", Font.BOLD, 12));
		lblOSistema.setBounds(10, 11, 580, 30);
		add(lblOSistema);
		
		/**
		 * imagem
		 */
		JLabel lblEstoque = new JLabel("");
		Image imgEstoque = new ImageIcon(this.getClass().getResource("/estoque.png")).getImage();
		lblEstoque.setIcon(new ImageIcon(imgEstoque));
		lblEstoque.setBounds(10, 66, 220, 150);
		add(lblEstoque);
		
		JTextPane txtPaneContent = new JTextPane();
		txtPaneContent.setBackground(new Color(240, 240, 240));
		txtPaneContent.setFont(new Font("Alpha Romanie G98", Font.PLAIN, 14));
		txtPaneContent.setText(content);
		txtPaneContent.setBounds(239, 86, 351, 93);
		add(txtPaneContent);
		
		JLabel lblProjeto = new JLabel("Github");
		lblProjeto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Uri.openWebpage("https://github.com/amazingbits/projeto-final-senac-dd-2020-2");
			}
		});
		lblProjeto.setHorizontalAlignment(SwingConstants.CENTER);
		lblProjeto.setFont(new Font("Alpha Romanie G98", Font.PLAIN, 16));
		lblProjeto.setBounds(502, 208, 88, 37);
		add(lblProjeto);

	}
}
