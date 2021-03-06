package senac.estoque.view;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class App extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App frame = new App();

					CadastroLancamentos cadastrarLancamentos = new CadastroLancamentos();
					frame.setContentPane(cadastrarLancamentos);
					frame.setVisible(true);
					frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public App() {
		setLocationRelativeTo(null);
		setTitle("Controle de Estoque");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 540);

		JMenuBar topMenu = new JMenuBar();
		setJMenuBar(topMenu);

		JMenu mnLancamento = new JMenu("Lançamentos");
		topMenu.add(mnLancamento);

		JMenuItem mnLancamentoCadastrar = new JMenuItem("Novo Lançamento");

		mnLancamentoCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CadastroLancamentos cadastrarLancamentos = new CadastroLancamentos();
				setContentPane(cadastrarLancamentos);
				revalidate();
			}
		});

		mnLancamento.add(mnLancamentoCadastrar);

		JMenuItem mnLancamentoListar = new JMenuItem("Listar Lançamentos");
		mnLancamentoListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ListarLancamentos listarLancamentos = new ListarLancamentos();
				setContentPane(listarLancamentos);
				revalidate();
			}
		});
		mnLancamento.add(mnLancamentoListar);

		JMenu mnProduto = new JMenu("Produtos");
		topMenu.add(mnProduto);

		JMenuItem mnProdutoCadastrar = new JMenuItem("Novo Produto");

		mnProdutoCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CadastrarProduto cadastrarProduto = new CadastrarProduto();
				setContentPane(cadastrarProduto);
				revalidate();
			}
		});
		mnProduto.add(mnProdutoCadastrar);

		JMenuItem mnProdutoListar = new JMenuItem("Listar Produtos");
		mnProdutoListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ListarProdutos listarProdutos = new ListarProdutos();
				setContentPane(listarProdutos);
				revalidate();
			}
		});
		mnProduto.add(mnProdutoListar);

		JMenu mnCategoria = new JMenu("Categorias");
		topMenu.add(mnCategoria);

		JMenuItem mnCategoriaCadastrar = new JMenuItem("Nova Categoria");
		mnCategoriaCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CadastrarCategoria cadastrarCategoria = new CadastrarCategoria();
				setContentPane(cadastrarCategoria);
				revalidate();
			}
		});
		mnCategoria.add(mnCategoriaCadastrar);

		JMenuItem mnCategoriaListar = new JMenuItem("Listar Categorias");
		mnCategoriaListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListarCategorias listarCategorias = new ListarCategorias();
				setContentPane(listarCategorias);
				revalidate();
			}
		});
		mnCategoria.add(mnCategoriaListar);

		JMenu mnSetor = new JMenu("Setores");
		topMenu.add(mnSetor);

		JMenuItem mnSetorCadastrar = new JMenuItem("Novo Setor");
		mnSetorCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroSetor cadastroSetor = new CadastroSetor();
				setContentPane(cadastroSetor);
				revalidate();
			}
		});
		mnSetor.add(mnSetorCadastrar);

		JMenuItem mnSetorListar = new JMenuItem("Listar Setores");
		mnSetorListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListarSetor listarSetor = new ListarSetor();
				setContentPane(listarSetor);
				revalidate();
			}
		});
		mnSetor.add(mnSetorListar);

		JMenu mnRelatorio = new JMenu("Relatórios");
		topMenu.add(mnRelatorio);

		JMenuItem mnRelatorioMaisComprados = new JMenuItem("Produtos Mais Comprados");
		mnRelatorioMaisComprados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListarProdutosMaisUsados listarProdutosMaisUsados = new ListarProdutosMaisUsados();
				setContentPane(listarProdutosMaisUsados);
				revalidate();
			}
		});

		mnRelatorio.add(mnRelatorioMaisComprados);

		JMenuItem mnRelatorioMaisUsados = new JMenuItem("Produtos Mais Usados");
		mnRelatorioMaisUsados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListarProdutosMaisVendidos listarProdutosMaisVendidos = new ListarProdutosMaisVendidos();
				setContentPane(listarProdutosMaisVendidos);
				revalidate();
			}
		});
		mnRelatorio.add(mnRelatorioMaisUsados);

		JMenu mnLog = new JMenu("Logs");
		topMenu.add(mnLog);

		JMenuItem mnLogProduto = new JMenuItem("Log Produtos");
		mnLogProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaLogProdutos listaLogProdutos = new ListaLogProdutos();
				setContentPane(listaLogProdutos);
				revalidate();
			}
		});
		mnLog.add(mnLogProduto);

		JMenuItem mnLogLancamento = new JMenuItem("Log Lançamentos");
		mnLogLancamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaLogLancamentos listaLogLancamentos = new ListaLogLancamentos();
				setContentPane(listaLogLancamentos);
				revalidate();
			}
		});
		mnLog.add(mnLogLancamento);

		JMenu mnSobre = new JMenu("Sobre");
		topMenu.add(mnSobre);

		JMenuItem mnSobreSistema = new JMenuItem("O Sistema");
		mnSobreSistema.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SobreSistema sobreSistema = new SobreSistema();
				setContentPane(sobreSistema);
				revalidate();
			}
		});
		mnSobre.add(mnSobreSistema);

		JMenuItem mnSobreEquipe = new JMenuItem("A Equipe");
		mnSobreEquipe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SobreEquipe sobreEquipe = new SobreEquipe();
				setContentPane(sobreEquipe);
				revalidate();
			}
		});
		mnSobre.add(mnSobreEquipe);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblImagemPrincipal = new JLabel("");
		Image imagemPrincipal = new ImageIcon(this.getClass().getResource("/imagemPrincipal.png")).getImage();
		lblImagemPrincipal.setIcon(new ImageIcon(imagemPrincipal));
		lblImagemPrincipal.setBounds(10, 11, 614, 368);
		contentPane.add(lblImagemPrincipal);

	}
}
