/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package janelas;

import bd.beans.Funcionario;
import bd.dao.FuncionarioDAO;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

/**
 *
 * Autor: Lucas Limas e Hermane Boavida
 * <p> Tela: Dados da conta<br>
 *  Finalidade: ao consultas os funcionários, o usuário irá ver primeiro os dados básicos do usuário<br>
 *  OBS: ele pode apenas pesquisar e, para alterar dados, será chamado uma tela de login
 */
public class frmDadosFuncionario extends javax.swing.JFrame {

    /**
     * Cosntrutor da Classe
     */
    public frmDadosFuncionario() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.dadosBasicosDosFuncionarios = funcionarios.select_PesquisaUsuario("");
        this.indice = -1;
        this.proximoFuncionario();
    }
    
    // Instanciando o Registro de Funcionários
    private FuncionarioDAO funcionarios = new FuncionarioDAO();
    private List<Funcionario> dadosBasicosDosFuncionarios = new ArrayList<>();
    private int indice;
    
    //SETTERS E GETTERS
    public JComboBox<String> getCbbCargo() {
        return cbbCargo;
    }
    public void setCbbCargo(String cbbCargo) {
        this.cbbCargo.setSelectedItem(cbbCargo);
    }

    public JFormattedTextField getTxtCelular() {
        return txtCelular;
    }
    public void setTxtCelular(String txtCelular) {
        this.txtCelular.setText(txtCelular);
    }

    public JFormattedTextField getTxtDataContratacao() {
        return txtDataContratacao;
    }
    public void setTxtDataContratacao(String txtDataContratacao) {
        this.txtDataContratacao.setText(txtDataContratacao);
    }

    public JTextField getTxtEndereco() {
        return txtEndereco;
    }
    public void setTxtEndereco(String txtEndereco) {
        this.txtEndereco.setText(txtEndereco);
    }

    public JTextField getTxtCodUsuer() {
        return txtCodUser;
    }
    public void setTxtCodUser(String txtCodUser) {
        this.txtCodUser.setText(txtCodUser);
    }

    public JLabel getTxtNome() {
        return txtNome;
    }
    public void setTxtNome(String txtNome) {
        this.txtNome.setText(txtNome);
    }

    public int getIndice() {
        return indice;
    }
    public void setIndice(int indice) {
        this.indice = indice;
    }
    
    /**
    *
    * Autor: Lucas Limas e Hermane Boavida
    * <p> Data: 26/08/2020<br>
    *  Finalidade: função que acessa a lista de funcionários e mostra o próximo registro</p>
    */
    public void proximoFuncionario(){
        this.setIndice(this.getIndice()+1);
        int totalRegistros = dadosBasicosDosFuncionarios.size() - 1;
        if(this.getIndice() <= totalRegistros){
            Funcionario f = dadosBasicosDosFuncionarios.get(indice);
            txtCodUser.setText(Integer.toString(f.getCodUsuario()));
            txtNome.setText(f.getNome());
            txtEndereco.setText(f.getEndereco());
            txtCelular.setText(f.getCelular());
            cbbCargo.setSelectedItem(f.getCargo());
            txtDataContratacao.setText(f.getDataContratacao());
        }else{
            JOptionPane.showMessageDialog(null, "Este já é o último funcionário registrado");
        }
    }
    /**
    *
    * Autor: Lucas Limas e Hermane Boavida
    * <p> Data: 26/08/2020<br>
    *  Finalidade: função que acessa a lista de funcionários e mostra o registro anterior</p>
    */
    public void anteriorFuncionario(){
        this.setIndice(this.getIndice()-1);
        int totalRegistros = dadosBasicosDosFuncionarios.size() - 1;
        if(this.getIndice() >= 0){
            Funcionario f = dadosBasicosDosFuncionarios.get(indice);
            txtCodUser.setText(Integer.toString(f.getCodUsuario()));
            txtNome.setText(f.getNome());
            txtEndereco.setText(f.getEndereco());
            txtCelular.setText(f.getCelular());
            cbbCargo.setSelectedItem(f.getCargo());
            txtDataContratacao.setText(f.getDataContratacao());
        }else{
            JOptionPane.showMessageDialog(null, "Este já é o primeiro funcionário registrado");
        }
    }
    /**
    *
    * Autor: Lucas Limas e Hermane Boavida
    * <p> Data: 26/08/2020<br>
    *  Finalidade: função que a tela da tabela de funcionários usa para retornar qual o indice do funcionário selecionado</p>
    */
    public void valorSelecionadoDaTabela(int codFuncionário){
        for(Funcionario f: dadosBasicosDosFuncionarios){
            if(f.getCodUsuario() == codFuncionário){
                this.setIndice(dadosBasicosDosFuncionarios.indexOf(f)-1);
                this.proximoFuncionario();
                break;
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        txtCelular = new javax.swing.JFormattedTextField();
        txtDataContratacao = new javax.swing.JFormattedTextField();
        cbbCargo = new javax.swing.JComboBox<>();
        txtCodUser = new javax.swing.JTextField();
        txtEndereco = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        btnAnterior = new javax.swing.JToggleButton();
        btnPesquisar = new javax.swing.JToggleButton();
        btnProximo = new javax.swing.JToggleButton();
        btnAlterar = new javax.swing.JToggleButton();
        txtNome = new javax.swing.JLabel();

        jLabel2.setText("jLabel2");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Dados do Funcionário");
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        try {
            txtCelular.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)#####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtCelular.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCelular.setText("(00)99999-9999");
        txtCelular.setToolTipText("Ex: (00)00000-0000");
        txtCelular.setEnabled(false);
        txtCelular.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        try {
            txtDataContratacao.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtDataContratacao.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDataContratacao.setText("01/01/2020");
        txtDataContratacao.setToolTipText("Ex: 01/01/2000");
        txtDataContratacao.setEnabled(false);
        txtDataContratacao.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        cbbCargo.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        cbbCargo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cargos", "Administrador", "Gerente", "Funcionario" }));
        cbbCargo.setSelectedIndex(1);
        cbbCargo.setToolTipText("Selecione um cargo");
        cbbCargo.setAutoscrolls(true);
        cbbCargo.setEnabled(false);

        txtCodUser.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtCodUser.setText("#Cod_User");
        txtCodUser.setEnabled(false);

        txtEndereco.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtEndereco.setText("R. Delegado Manoel Pereira Pinto");
        txtEndereco.setEnabled(false);

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel6.setText("Cod. Funcionario");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel5.setText("Data de Contratação");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel4.setText("Celular:");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel3.setText("Cargo:");

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setText("Endereço:");

        btnAnterior.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        btnAnterior.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/btnAnterior.png"))); // NOI18N
        btnAnterior.setText("Anterior");
        btnAnterior.setToolTipText("Apagar funcionário");
        btnAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnteriorActionPerformed(evt);
            }
        });

        btnPesquisar.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        btnPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/btnPesquisar.png"))); // NOI18N
        btnPesquisar.setText("Pesquisar");
        btnPesquisar.setToolTipText("Pesquisar funcionário");
        btnPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarActionPerformed(evt);
            }
        });

        btnProximo.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        btnProximo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/btnProximo.png"))); // NOI18N
        btnProximo.setText("Próximo");
        btnProximo.setToolTipText("Apagar funcionário");
        btnProximo.setPreferredSize(new java.awt.Dimension(125, 33));
        btnProximo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProximoActionPerformed(evt);
            }
        });

        btnAlterar.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        btnAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/icoCadastroUsuarios.png"))); // NOI18N
        btnAlterar.setText("Alterar");
        btnAlterar.setToolTipText("Alterar dados");
        btnAlterar.setPreferredSize(new java.awt.Dimension(125, 33));
        btnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarActionPerformed(evt);
            }
        });

        txtNome.setFont(new java.awt.Font("Times New Roman", 3, 34)); // NOI18N
        txtNome.setText("Lucas Henrique de Souza Lima");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnProximo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(72, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(txtEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 471, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(196, 196, 196)
                        .addComponent(jLabel3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(cbbCargo, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(96, 96, 96)
                        .addComponent(jLabel6))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(txtNome))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtDataContratacao, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(txtCodUser, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(46, 46, 46))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(txtNome)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(11, 11, 11)
                .addComponent(txtEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbCargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDataContratacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCodUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnProximo, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
        /**
        *
        * Autor: Lucas Limas e Hermane Boavida
        * <p> Data: 14/05/2020<br>
        *  Finalidade: Abrir a tela de Registro de Funcionário para alterar os dados<br>
        *  OBS: será aberto uma tela de login antes de poder alterar os dados</p>
        */
    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed
        // BOTÃO ALTERAR
        // Instâncias
        frmRegistroFuncionario funcionario = new frmRegistroFuncionario();
        jDialogAlteraDados loginAlterarDados = new jDialogAlteraDados(this, true);
        
        loginAlterarDados.setVisible(true);
        if(loginAlterarDados.isPermissao()){
            funcionario.AlterarDados(Integer.parseInt(txtCodUser.getText()));
            // fechando a tela de consulta de dados
            this.dispose();
        }
    }//GEN-LAST:event_btnAlterarActionPerformed
        /**
        *
        * Autor: Lucas Limas e Hermane Boavida
        * <p> Data: 08/08/2020<br>
        *  Finalidade: abre uma tela com tabela e campos de pesquisa, monstrando todos os registros de funcionário</p>
        */
    private void btnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarActionPerformed
        frmListaDeFuncionarios listaFuncionarios = new frmListaDeFuncionarios();
        listaFuncionarios.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnPesquisarActionPerformed
    /**
    * Autor: Lucas Limas e Hermane Boavida
    * <p> Data: 26/08/2020<br>
    *  Finalidade: chama a função que passa para o próximo registro de funcionário</p>
    */
    private void btnProximoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProximoActionPerformed
        this.proximoFuncionario();
    }//GEN-LAST:event_btnProximoActionPerformed
    /**
    * Autor: Lucas Limas e Hermane Boavida
    * <p> Data: 26/08/2020<br>
    *  Finalidade: chama a função que passa para o registro de funcionário anterior</p>
    */
    private void btnAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnteriorActionPerformed
        this.anteriorFuncionario();
    }//GEN-LAST:event_btnAnteriorActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmDadosFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmDadosFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmDadosFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmDadosFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmDadosFuncionario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton btnAlterar;
    private javax.swing.JToggleButton btnAnterior;
    private javax.swing.JToggleButton btnPesquisar;
    private javax.swing.JToggleButton btnProximo;
    private javax.swing.JComboBox<String> cbbCargo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JFormattedTextField txtCelular;
    private javax.swing.JTextField txtCodUser;
    private javax.swing.JFormattedTextField txtDataContratacao;
    private javax.swing.JTextField txtEndereco;
    private javax.swing.JLabel txtNome;
    // End of variables declaration//GEN-END:variables
}
