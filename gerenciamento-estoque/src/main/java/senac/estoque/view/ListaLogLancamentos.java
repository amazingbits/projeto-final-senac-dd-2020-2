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
import java.util.ArrayList;

import senac.estoque.controller.LancamentoController;
import senac.estoque.model.vo.LogLancamentosVO;
import javax.swing.JButton;
import senac.estoque.helpers.GerarPdf;

public class ListaLogLancamentos extends JPanel {


    /**
     * Create the panel.
     */
    public ListaLogLancamentos() {
        setLayout(null);

        JLabel lblTitle = new JLabel("LOG DE PRODUTOS");
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setBounds(10, 11, 600, 36);
        add(lblTitle);

        /**
         * carregar lista de log de lancamentos'
         */
        LancamentoController lancamentoController = new LancamentoController();
        ArrayList<LogLancamentosVO> logLancamentos = lancamentoController.listarLogLancamentos();

        // definir colunas
        final String[] colunas = { "IDPRODUTO", "PRODUTO", "TIPO", "QUANTIDADE", "OPERACAO", "DATA" };

        // setando modelo padrão de tabela
        DefaultTableModel modeloTabela = new DefaultTableModel(colunas, 0);

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
        scrollPane.setBounds(10, 50, 610, 239);
        add(scrollPane);

        JButton btnAnterior = new JButton("<<");
        btnAnterior.setBounds(10, 300, 89, 23);
        add(btnAnterior);

        JButton btnProxima = new JButton(">>");
        btnProxima.setBounds(531, 300, 89, 23);
        add(btnProxima);

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
