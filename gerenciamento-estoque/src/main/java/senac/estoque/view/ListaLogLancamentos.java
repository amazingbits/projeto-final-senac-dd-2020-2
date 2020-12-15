package senac.estoque.view;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

import senac.estoque.controller.LancamentoController;
import senac.estoque.model.vo.LogLancamentosVO;
import senac.estoque.seletores.SeletorLancamento;

import javax.swing.JButton;
import senac.estoque.helpers.GerarPdf;
import javax.swing.JComboBox;

public class ListaLogLancamentos extends JPanel {

	
	private SeletorLancamento seletorLancamento = new SeletorLancamento();
	private int mes = LocalDate.now().getMonthValue();
	private int ano = LocalDate.now().getYear();

    /**
     * Create the panel.
     */
    public ListaLogLancamentos() {
        setLayout(null);

        JLabel lblTitle = new JLabel("LOG DE LANÇAMENTOS");
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setBounds(10, 11, 600, 36);
        add(lblTitle);

        //carregar itens da tabela
        this.seletorLancamento.setMes(this.mes);
        this.seletorLancamento.setAno(this.ano);
        LancamentoController lancamentoController = new LancamentoController();
        ArrayList<LogLancamentosVO> logLancamentos = lancamentoController.listarLogLancamentos(this.seletorLancamento);

        // definir colunas
        final String[] colunas = { "IDPRODUTO", "PRODUTO", "TIPO", "QUANTIDADE", "OPERACAO", "DATA" };

        // setando modelo padrão de tabela
        DefaultTableModel modeloTabela = new DefaultTableModel(colunas, 0) {
        	public boolean isCellEditable(int row, int column) {
				return false;
			}
        };

        // construindo a tabela seguindo o modelo criado
        final JTable tabela = new JTable(modeloTabela);
        DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
        tabela.setPreferredScrollableViewportSize(new Dimension(500, 50));
        tabela.setFillsViewportHeight(true);

        // populando a tabela
        for (int i = 0; i < logLancamentos.size(); i++) {
            modelo.addRow(new Object[] { logLancamentos.get(i).getCodigoproduto(), logLancamentos.get(i).getProduto(),
                    logLancamentos.get(i).getTipo(), logLancamentos.get(i).getQuantidade(),
                    logLancamentos.get(i).getOperacacao(), logLancamentos.get(i).getData() });


        }

        // imprimindo a tabela na tela
        JScrollPane scrollPane = new JScrollPane(tabela);
        scrollPane.setBounds(10, 90, 610, 239);
        add(scrollPane);

        JLabel lblMes = new JLabel("Mês");
        lblMes.setBounds(10, 32, 46, 14);
        add(lblMes);
        
        JLabel lblAno = new JLabel("Ano");
        lblAno.setBounds(155, 32, 46, 14);
        add(lblAno);
        
        String[] meses = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
		Object[] anos = lancamentoController.listaAnos().toArray();
        
        final JComboBox cbMes = new JComboBox(meses);
        cbMes.setBounds(10, 45, 135, 36);
        add(cbMes);
        
         final JComboBox cbAno = new JComboBox(anos);
        cbAno.setBounds(155, 45, 135, 36);
        add(cbAno);
        
        JButton btnFiltrar = new JButton("Filtrar");
        btnFiltrar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		
        		int mesSelecionado = cbMes.getSelectedIndex() + 1;
				int anoSelecionado = Integer.parseInt((String) cbAno.getSelectedItem());
				
				LancamentoController lancamentoController = new LancamentoController();
				SeletorLancamento seletorLancamento = new SeletorLancamento();
				seletorLancamento.setMes(mesSelecionado);
				seletorLancamento.setAno(anoSelecionado);
				ArrayList<LogLancamentosVO> filtro = lancamentoController.listarLogLancamentos(seletorLancamento);
				
				if(filtro != null) {
					((DefaultTableModel) tabela.getModel()).setRowCount(0);
					for(int i = 0; i < filtro.size(); i++) {
						((DefaultTableModel) tabela.getModel()).addRow(new Object[] { 
								filtro.get(i).getCodigoproduto(), 
								filtro.get(i).getProduto(),
								filtro.get(i).getTipo(), 
								filtro.get(i).getQuantidade(),
								filtro.get(i).getOperacacao(), 
								filtro.get(i).getData()  
								}
						);
					}
					((DefaultTableModel) tabela.getModel()).fireTableDataChanged();
				}
        		
        	}
        });
        btnFiltrar.setBounds(300, 45, 89, 36);
        add(btnFiltrar);
        
        
        JButton btnGerar = new JButton("Gerar PDF");
        btnGerar.setBounds(10, 340, 200, 23);

        btnGerar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                GerarPdf gerarPdf = new GerarPdf();

                gerarPdf.gerar(tabela, "Lancamentos", colunas);


            }
		});
        add(btnGerar);
        

    }
}
