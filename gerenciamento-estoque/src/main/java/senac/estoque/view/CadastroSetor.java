package senac.estoque.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import senac.estoque.controller.SetorController;
import senac.estoque.model.vo.SetorVO;

public class CadastroSetor extends JPanel {
    private JLabel lblTitle;
    private JLabel lblSetor;

    private JTextField tfSetor;

    private JButton bCadastrar;

    public CadastroSetor() {
        setLayout(null);

        lblTitle = new JLabel("CADASTRO DE SETOR");
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setBounds(10, 11, 600, 36);
        add(lblTitle);

        lblSetor = new JLabel("Nome do Setor");
        lblSetor.setBounds(10, 50, 600, 36);
        add(lblSetor);

        tfSetor = new JTextField();
        tfSetor.setBounds(10, 80, 400, 36);
        add(tfSetor);

        bCadastrar = new JButton("Cadastrar");
        bCadastrar.setBounds(10, 300, 200, 36);
        add(bCadastrar);

        bCadastrar.addActionListener(new ActionListener() {
           
            @Override
            public void actionPerformed(ActionEvent e) {
                SetorVO setor = new SetorVO();
                SetorController setorController = new SetorController();

                setor.setDescricao(tfSetor.getText());

                if(setorController.cadastrarSetor(setor)){
                    JOptionPane.showMessageDialog(null, "Sucesso em cadastrar o Setor");
                }
            }
        });

        

    }

}
