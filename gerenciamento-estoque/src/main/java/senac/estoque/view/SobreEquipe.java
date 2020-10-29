package senac.estoque.view;

import javax.swing.JPanel;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import senac.estoque.helpers.Uri;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SobreEquipe extends JPanel {

	private String instagramGuilherme = "https://www.instagram.com/___guiandrade/";
	private String githubGuilherme = "https://github.com/amazingbits";
	private String linkedinGuilherme = "https://www.linkedin.com/in/devguiandrade/";
	private String instagramJadson = "https://www.instagram.com/gtjadsonsantos/";
	private String githubJadson = "https://github.com/jadson179";
	private String linkedinJadson = "https://www.linkedin.com/in/jadson-santos-a9aa9b145/";
	
	/**
	 * Create the panel.
	 */
	public SobreEquipe() {
		setLayout(null);
		
		/**
		 * ícone autor: Guilherme
		 */
		JLabel lblGuilherme = new JLabel("");
		Image imgGuilherme = new ImageIcon(this.getClass().getResource("/gui.png")).getImage();
		lblGuilherme.setIcon(new ImageIcon(imgGuilherme));
		lblGuilherme.setBounds(31, 60, 120, 120);
		add(lblGuilherme);
		
		JLabel lblTituloGuilherme = new JLabel("GUILHERME ANDRADE");
		lblTituloGuilherme.setFont(new Font("Alpha Romanie G98", Font.BOLD, 12));
		lblTituloGuilherme.setHorizontalAlignment(SwingConstants.CENTER);
		lblTituloGuilherme.setBounds(195, 84, 207, 30);
		add(lblTituloGuilherme);
		
		JLabel lblInstagramGuilherme = new JLabel("Instagram");
		lblInstagramGuilherme.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Uri.openWebpage(instagramGuilherme);
			}
		});
		lblInstagramGuilherme.setFont(new Font("Alpha Romanie G98", Font.PLAIN, 16));
		lblInstagramGuilherme.setHorizontalAlignment(SwingConstants.CENTER);
		lblInstagramGuilherme.setBounds(161, 118, 88, 37);
		add(lblInstagramGuilherme);
		
		JLabel lblGithubGuilherme = new JLabel("Github");
		lblGithubGuilherme.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Uri.openWebpage(githubGuilherme);
			}
		});
		lblGithubGuilherme.setHorizontalAlignment(SwingConstants.CENTER);
		lblGithubGuilherme.setFont(new Font("Alpha Romanie G98", Font.PLAIN, 16));
		lblGithubGuilherme.setBounds(259, 118, 88, 37);
		add(lblGithubGuilherme);
		
		JLabel lblLinkedinGuilherme = new JLabel("LinkedIn");
		lblLinkedinGuilherme.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Uri.openWebpage(linkedinGuilherme);
			}
		});
		lblLinkedinGuilherme.setHorizontalAlignment(SwingConstants.CENTER);
		lblLinkedinGuilherme.setFont(new Font("Alpha Romanie G98", Font.PLAIN, 16));
		lblLinkedinGuilherme.setBounds(357, 118, 88, 37);
		add(lblLinkedinGuilherme);
		
		/**
		 * ícone autor: Jadson
		 */
		JLabel lblJadson = new JLabel("");
		Image imgJadson = new ImageIcon(this.getClass().getResource("/jads.png")).getImage();
		lblJadson.setIcon(new ImageIcon(imgJadson));
		lblJadson.setBounds(500, 215, 120, 120);
		add(lblJadson);
		
		JLabel lblTituloJadson = new JLabel("JADSON SANTOS");
		lblTituloJadson.setHorizontalAlignment(SwingConstants.CENTER);
		lblTituloJadson.setFont(new Font("Alpha Romanie G98", Font.BOLD, 12));
		lblTituloJadson.setBounds(250, 235, 207, 30);
		add(lblTituloJadson);
		
		JLabel lblInstagramJadson = new JLabel("Instagram");
		lblInstagramJadson.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Uri.openWebpage(instagramJadson);
			}
		});
		lblInstagramJadson.setHorizontalAlignment(SwingConstants.CENTER);
		lblInstagramJadson.setFont(new Font("Alpha Romanie G98", Font.PLAIN, 16));
		lblInstagramJadson.setBounds(206, 272, 88, 37);
		add(lblInstagramJadson);
		
		JLabel lblGithubJadson = new JLabel("Github");
		lblGithubJadson.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Uri.openWebpage(githubJadson);
			}
		});
		lblGithubJadson.setHorizontalAlignment(SwingConstants.CENTER);
		lblGithubJadson.setFont(new Font("Alpha Romanie G98", Font.PLAIN, 16));
		lblGithubJadson.setBounds(304, 272, 88, 37);
		add(lblGithubJadson);
		
		JLabel lblLinkedinJadson = new JLabel("LinkedIn");
		lblLinkedinJadson.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Uri.openWebpage(linkedinJadson);
			}
		});
		lblLinkedinJadson.setHorizontalAlignment(SwingConstants.CENTER);
		lblLinkedinJadson.setFont(new Font("Alpha Romanie G98", Font.PLAIN, 16));
		lblLinkedinJadson.setBounds(402, 272, 88, 37);
		add(lblLinkedinJadson);
		
		JLabel lblTitulo = new JLabel("A EQUIPE");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Alpha Romanie G98", Font.BOLD, 12));
		lblTitulo.setBounds(185, 11, 207, 30);
		add(lblTitulo);

	}

}
